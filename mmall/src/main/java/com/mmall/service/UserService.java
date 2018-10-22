package com.mmall.service;

import com.mmall.common.ResponseMessage;
import com.mmall.pojo.User;

public interface UserService {
	
	/**
	 * �����û�������������û���Ϣ
	 * @param username
	 * @param password
	 * @return
	 */
	ResponseMessage<User> findUserByNameAndPassword(String username,String password);

	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	ResponseMessage<String> addUser(User user);
	
	/**
	 * �޸��û���Ϣ
	 * @param user
	 * @return
	 */
	ResponseMessage<String> updateUser(User user);

}
