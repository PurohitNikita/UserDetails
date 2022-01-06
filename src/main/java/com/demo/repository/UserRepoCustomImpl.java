package com.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import com.demo.model.UserDetailsModel;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository("userRepoCustomImpl")
public class UserRepoCustomImpl implements UserRepoCustom{

	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public String addUserDetails(UserDetailsModel userDetailsModel) {
		
		if (StringUtils.isEmpty(userDetailsModel.getId())) {
			userDetailsModel.setId(UUID.randomUUID().toString());
		}
		mongoTemplate.insert(userDetailsModel, "UserDetailsModel");
		return userDetailsModel.getId().toString();
	}

	//GetInformation of all the users which are not deleted i.e whose isDeleted boolean is false.
	@Override
	public List<UserDetailsModel> getUserDetails() {
		Query query = new Query();
		query.addCriteria(Criteria.where("isDeleted").is(false));

		return mongoTemplate.find(query,UserDetailsModel.class);
	}

	@Override
	public UserDetailsModel getUserDetailsByMbNumber(String mobileNumber) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("mobileNumber").is(mobileNumber).and("isDeleted").is(false));
		UserDetailsModel userDetails = mongoTemplate.findOne(query,UserDetailsModel.class);
		return userDetails;
	}

	//update the userInfo using his ID.
	@Override
	public boolean updateUserDetails(UserDetailsModel userDetailsModel) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(userDetailsModel.getId()));
		
		Update update = new Update();
		update.set("fname",userDetailsModel.getFname());
		update.set("lname", userDetailsModel.getLname());
		update.set("dob",userDetailsModel.getDob());
		update.set("mobileNumber", userDetailsModel.getMobileNumber());
		update.set("city",userDetailsModel.getCity());
		
		UpdateResult result =  mongoTemplate.updateFirst(query, update, UserDetailsModel.class);
		
		return result.wasAcknowledged();
	}

	@Override
	public boolean deleteUserDetails(String mbNumber) {
		
		//Soft Delete Technique. UI team don't show records whose isDeleted Flag is set to true.
		
		Query query = new Query();
		query.addCriteria(Criteria.where("mobileNumber").is(mbNumber));
		
		Update update =  new Update();
		update.set("isDeleted", true);
		
		UpdateResult result =  mongoTemplate.updateFirst(query, update, UserDetailsModel.class);
		return result.wasAcknowledged();		
	}
}
