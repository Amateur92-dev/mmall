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

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户登陆 username ,password
	 */
	public ResponseMessage<User> findUserByNameAndPassword(String username, String password) {

		if (null == username || null == password) {
			ResponseMessage.errorWithMessage("用户名或密码不能为空");
			log.error("登陆用户名或密码为空,输入用户名为{0}，用户名密码为{1}", username, password);
		}
		// MD5用户密码加密
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		User user = userMapper.findUserByNaAndPs(username, md5Password);

		if (null == user) {
			log.error("用户信息不存在");
			return ResponseMessage.errorWithMessage("用户信息不存在");
		}
		// 将密码置空
		user.setPassword("");
		return ResponseMessage.successWithData(user);
	}

	/**
	 * 新增用户信息
	 */
	public ResponseMessage<String> addUser(User user) {
		if (null == user) {

			return ResponseMessage.errorWithMessage("参数为空");
		}
		if (null == user.getUsername() || null == user.getPassword()) {
			return ResponseMessage.errorWithMessage("用户名或者密码都不能为空");
		}
		// 1 首先查找用户是否存在
		String md5EncodeUtf8 = MD5Util.MD5EncodeUtf8(user.getPassword());
		User getUser = userMapper.findUserByNaAndPs(user.getUsername(), md5EncodeUtf8);

		if (null != getUser) {
			log.error("用户已存在");
			return ResponseMessage.errorWithMessage("用户已存在");
		}
		// 密码加密处理
		// 保存用户
		user.setPassword(md5EncodeUtf8);
		int count = userMapper.insertSelective(user);

		if (count > 0) {

			return ResponseMessage.successWithMessage("添加用户成功");
		}
		return ResponseMessage.successWithMessage("添加用户失败");
	}

	/**
	 * 用户已经登陆，无需再次用户是否存在
	 */
	public ResponseMessage<String> updateUser(User user) {
		int iRet = userMapper.updateByPrimaryKeySelective(user);
		if (iRet == 1) {

			return ResponseMessage.successWithMessage("修改用户信息成功");
		}

		return ResponseMessage.errorWithMessage("修改用户信息异常");
	}

}
