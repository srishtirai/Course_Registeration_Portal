package edu.neu.csye6200.courseregistration.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.neu.csye6200.courseregistration.model.User;
import edu.neu.csye6200.courseregistration.service.CourseService;
import edu.neu.csye6200.courseregistration.service.UserService;
import edu.neu.csye6200.courseregistration.model.Course;
import edu.neu.csye6200.courseregistration.model.Professor;
import edu.neu.csye6200.courseregistration.model.Student;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	UserService userService;
	
	@GetMapping
    public String showCourses(@ModelAttribute("currentUser") User user, Model model) {
		if(user == null) {
    		return "redirect:/login";
    	}
		List<Course> courses = courseService.getAllCourses();
		Collections.sort(courses);
		
		model.addAttribute("role", user.getRole().name());
		model.addAttribute("courseList", courses);
		
        return "course";
    }
	
	@GetMapping("/add")
    public String showAddCoursesForm(@ModelAttribute("currentUser") User user, Model model) {
		if(user == null) {
    		return "redirect:/login";
    	}
		model.addAttribute("role", user.getRole().name());
        model.addAttribute("course", new Course());
        model.addAttribute("editMode", false); 
        return "addCourse";
    }
    
    @PostMapping("/add")
    public String addCourse(@ModelAttribute("currentUser") User user, @ModelAttribute("course") Course course, Model model) {
    	if(user == null) {
    		return "redirect:/login";
    	}
    	course.setProfessor((Professor) user);
    	courseService.saveCourse(course);
    	
    	Professor professor = (Professor) user;
    	professor.getCourses().add(course);
    	userService.saveUser(professor);
    	
        return "redirect:/course";
    }

    @GetMapping("/search")
    public String searchCourses(@ModelAttribute("currentUser") User user, @RequestParam(name = "keyword") String keyword, Model model) {
    	if(user == null) {
    		return "redirect:/login";
    	}
    	List<Course> filteredCourses = courseService.getAllCourses()
							    			.stream()
							    			.filter(course -> 
							    				course.getCourseName().toLowerCase().contains(keyword.toLowerCase()) || 
							    				course.getCourseCode().toLowerCase().contains(keyword.toLowerCase()))
							    			.collect(Collectors.toList());
    	
        model.addAttribute("courseList", filteredCourses);
        model.addAttribute("role", user.getRole().name());
        
	    return "course";
    }
    
    @PostMapping("/edit")
    public String showEditCourseForm(@ModelAttribute("currentUser") User user, @RequestParam("courseId") Long id, Model model) {
    	if(user == null) {
    		return "redirect:/login";
    	}
        Course course = courseService.getCourseById(id);
        if (course != null) {
            model.addAttribute("course", course);
            model.addAttribute("editMode", true); 
            return "addCourse";
        } else {
            return "redirect:/professor-dashboard";
        }
    }

    @PostMapping("/editSave")
    public String editCourse(@ModelAttribute("currentUser") User user, @ModelAttribute Course course, Model model) {
    	if(user == null) {
    		return "redirect:/login";
    	}
    	Course existingCourse = courseService.getCourseById(course.getId());
    	course.setProfessor(existingCourse.getProfessor());
    	courseService.updateCourse(course);
    	
        return "redirect:/professor-dashboard";
    }
    
    @GetMapping("/details")
    public String showCourseRegisterForm(@ModelAttribute("currentUser") User user, @RequestParam("courseId") Long id, Model model) {
    	if(user == null) {
    		return "redirect:/login";
    	}
    	Course course = courseService.getCourseById(id);
        
        if (course != null) {
        	boolean alreadyRegistered = course.getStudents().stream().anyMatch(student -> student.getId().equals(user.getId()));
        	
        	Student student = (Student) user;
        	int totalRegistrations = student.getCourses().size();
        	boolean maxRegistrationReached = totalRegistrations == 2;

        	if(alreadyRegistered) {
        		model.addAttribute("success", "You have registered for this course");
        	} else if(maxRegistrationReached && alreadyRegistered) {
        		model.addAttribute("error", "You can only register for 2 courses. Withraw from registered course to register for this");
        	}
        	
        	model.addAttribute("registarionAllowed", !alreadyRegistered && !maxRegistrationReached);
            model.addAttribute("course", course);
            model.addAttribute("role", user.getRole().name());
            return "register";
        } else {
            return "redirect:/course";
        }
    }

    @PostMapping("/register")
    public String registerCourse(@ModelAttribute("currentUser") User user, 
    		@RequestParam("courseId") Long id, Model model, 
    		RedirectAttributes redirectAttributes) {
    	if(user == null) {
    		return "redirect:/login";
    	}
    	
    	Course course = courseService.getCourseById(id);
    	
    	int registrationCount = course.getStudents().size();
    	int capacity = course.getCapacity();
    	int availability = capacity - registrationCount;
    	
    	if(availability == 0) {
    		redirectAttributes.addFlashAttribute("error", "No seats available. Sorry we are filled !!");
    	} else {
    		
    		Student student = (Student) user;
        	student.getCourses().add(course);
        	userService.updateUser(student);

        	course.getStudents().add(student);
        	courseService.updateCourse(course);
        	
        	redirectAttributes.addFlashAttribute("alreadyRegistered", true);
    	}
    	
        return "redirect:/course/details?courseId=" + id;
    }
    
    @PostMapping("/withdraw")
    public String withdrawCourse(@ModelAttribute("currentUser") User user, @RequestParam("courseId") Long id, Model model) {
    	if(user == null) {
    		return "redirect:/login";
    	}
    	Course course = courseService.getCourseById(id);
        
        Student student = (Student) user;
        Course removeCourse = student.getCourses().stream()
                .filter(c -> c.getId().equals(course.getId()))
                .findFirst()
                .orElse(null);
    	student.getCourses().remove(removeCourse);
    	userService.updateUser(student);
    	
    	Student removeStudent = course.getStudents().stream()
                .filter(s -> s.getStudentId().equals(student.getStudentId()))
                .findFirst()
                .orElse(null);
    	course.getStudents().remove(removeStudent);
    	courseService.updateCourse(course);
        
        return "redirect:/student-dashboard";
    }

}
