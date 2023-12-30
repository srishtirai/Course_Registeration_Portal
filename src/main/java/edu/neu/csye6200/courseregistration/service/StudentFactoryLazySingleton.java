package edu.neu.csye6200.courseregistration.service;

import edu.neu.csye6200.courseregistration.model.Student;
import edu.neu.csye6200.courseregistration.model.UserRole;

class StudentFactoryLazySingleton implements UserFactoryAPI {
	private static StudentFactoryLazySingleton instance;

	@Override
	public Student createUser(String csvData) {
		String[] contents = csvData.split(",");
        return new Student(contents[0], contents[1], UserRole.STUDENT, contents[3], contents[4], contents[5], contents[6], contents[7], Integer.parseInt(contents[8]));
    }
	
	public static synchronized StudentFactoryLazySingleton getInstance() {
        if (instance == null) {
            instance = new StudentFactoryLazySingleton();
        }
        return instance;
    }
}
