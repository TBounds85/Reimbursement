package com.web.dao;

public interface LoginVerificationDAO {

	boolean validate(String username, String password);
	
}