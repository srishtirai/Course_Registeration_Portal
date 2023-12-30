package edu.neu.csye6200.courseregistration.service;

import edu.neu.csye6200.courseregistration.model.User;

interface UserFactoryAPI {
	public abstract User createUser(String csvData);
}
