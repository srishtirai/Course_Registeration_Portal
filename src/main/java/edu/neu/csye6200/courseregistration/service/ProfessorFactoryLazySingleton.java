package edu.neu.csye6200.courseregistration.service;

import edu.neu.csye6200.courseregistration.model.Professor;
import edu.neu.csye6200.courseregistration.model.User;
import edu.neu.csye6200.courseregistration.model.UserRole;

class ProfessorFactoryLazySingleton implements UserFactoryAPI {
	private static ProfessorFactoryLazySingleton instance;

	@Override
	public User createUser(String csvData) {
		String[] contents = csvData.split(",");
        return new Professor(contents[0], contents[1], UserRole.PROFESSOR, contents[3], contents[4], contents[5], contents[6], contents[7]);
    }
	
	public static synchronized ProfessorFactoryLazySingleton getInstance() {
        if (instance == null) {
            instance = new ProfessorFactoryLazySingleton();
        }
        return instance;
    }
}
