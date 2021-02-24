package com.web.dao;

public interface LoginDAO {

	int validate(String username, String password);

	boolean checkIfManager();

	
}