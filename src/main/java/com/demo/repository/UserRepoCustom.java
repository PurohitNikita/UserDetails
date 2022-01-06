package com.demo.repository;

import java.util.List;

import com.demo.model.UserDetailsModel;

public interface UserRepoCustom {
	String addUserDetails(UserDetailsModel userDetailsModel);
	List<UserDetailsModel> getUserDetails();
	UserDetailsModel getUserDetailsByMbNumber(String mbNumber);
	boolean updateUserDetails(UserDetailsModel userDetailsModel);
	boolean deleteUserDetails(String mbNumber);

}
