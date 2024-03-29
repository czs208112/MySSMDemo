package com.summit.common.uesrLogin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.summit.common.uesrLogin.dao.IUserManagerMapper;
import com.summit.common.uesrLogin.model.User;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserManagerService {

	@Autowired
	public IUserManagerMapper userManagerMapper;

	public List<User> getUserList(){
		return userManagerMapper.getUserList();
	}

	@Transactional
	public void findByUsername(String name) {

		userManagerMapper.findByUsername(name);

	}

}
