package com.mmall.service;

import com.mmall.common.ResponseMessage;
import com.mmall.pojo.User;

public interface UserService {

	ResponseMessage<User> findUserByNameAndPassword(String username,String password);

	ResponseMessage<String> addUser(User user);

}
