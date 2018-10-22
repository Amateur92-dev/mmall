package com.mmall.service;

import com.mmall.common.ResponseMessage;
import com.mmall.pojo.User;

public interface UserService {
	
	/**
	 * 根据用户名和密码查找用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	ResponseMessage<User> findUserByNameAndPassword(String username,String password);

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	ResponseMessage<String> addUser(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	ResponseMessage<String> updateUser(User user);

}
