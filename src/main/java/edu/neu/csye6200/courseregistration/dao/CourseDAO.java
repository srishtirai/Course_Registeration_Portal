package edu.neu.csye6200.courseregistration.dao;

import java.util.List;

import edu.neu.csye6200.courseregistration.model.Course;

public interface CourseDAO {
	public void saveCourse(Course course);
	public List<Course> getAllCourses();
    public void updateCourse(Course course);
    public void deleteCourse(Long id);
    public Course getCourseById(Long id);
}
