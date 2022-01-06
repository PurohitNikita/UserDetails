package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.UserDetailsModel;
import com.demo.repository.UserRepoCustom;


@RestController
public class UserController 
{
	@Autowired
	private UserRepoCustom userRepoCustom;
	
	
	@PostMapping(value = "/add")	
	public String addUser(@RequestBody UserDetailsModel user)
	{
		String id = userRepoCustom.addUserDetails(user);
		return "User Created with Id"+id;
	}

	@GetMapping(value = "/get_all_users")
	public List<UserDetailsModel> getUsers()
	{	
		return userRepoCustom.getUserDetails();
	}
	
	@GetMapping(value = "/get_by_mbnumber/{mobileNumber}")
	public UserDetailsModel getUser(@PathVariable String mobileNumber)
	{
		return userRepoCustom.getUserDetailsByMbNumber(mobileNumber);
	}
	
	@PostMapping(value = "/update_user_details")
	public String updateUserDetails(@RequestBody UserDetailsModel user)
	{
		boolean isUpdated = userRepoCustom.updateUserDetails(user);
		return "Update Status :"+isUpdated;
	}
	 
	@GetMapping(value = "/delete_user_details/{mbNumber}")
	public String DeleteUser(@PathVariable String mbNumber)
	{
		boolean isDeleted = userRepoCustom.deleteUserDetails(mbNumber);
		return "Record Deleted :"+isDeleted;
	}




}
