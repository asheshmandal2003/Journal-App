package com.ashesh.journalApp.repositories;

import com.ashesh.journalApp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<User> getUsersForSA() {
		Query query = new Query();
		query.addCriteria(
				Criteria.where("email")
						.exists(true)
						.andOperator(
								Criteria.where("email").regex(".*@.*"),
								Criteria.where("email").regex(".*\\.com")
										.andOperator(
												Criteria.where("sentimentAnalysis")
														.is(true)
										)
						)
		);

		return mongoTemplate.find(query, User.class);
	}
}
