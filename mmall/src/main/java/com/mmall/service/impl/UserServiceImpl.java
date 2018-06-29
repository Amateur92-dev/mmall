package com.mmall.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmall.common.ResponseMessage;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.UserService;
import com.mmall.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log=LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	/**
	 * 用户登陆
	 * username ,password
	 */
	public ResponseMessage<User> findUserByNameAndPassword(String username,String password) {
		
		if(null== username || null ==password){
			ResponseMessage.errorWithMessage("用户名或密码不能为空");
			log.error("登陆用户名或密码为空,输入用户名为{0}，用户名密码为{1}",username,password );
		}
		//MD5用户密码加密
		String md5Password=MD5Util.MD5EncodeUtf8(password);
		User user=userMapper.findUserByNaAndPs(username,md5Password);
		
		if(null == user){
			log.error("用户信息不存在");
			return ResponseMessage.errorWithMessage("用户信息不存在");
		}
		//将密码置空
		user.setPassword("");
		return  ResponseMessage.successWithData(user);
	}

}
