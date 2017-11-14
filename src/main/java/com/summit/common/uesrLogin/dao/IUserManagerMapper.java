package com.summit.common.uesrLogin.dao;

import java.util.List;

import com.summit.common.uesrLogin.model.User;


public interface IUserManagerMapper {

	List<User> getUserList();

	User findByUsername(String username);
}
