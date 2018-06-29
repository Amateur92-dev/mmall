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
	 * �û���½
	 * username ,password
	 */
	public ResponseMessage<User> findUserByNameAndPassword(String username,String password) {
		
		if(null== username || null ==password){
			ResponseMessage.errorWithMessage("�û��������벻��Ϊ��");
			log.error("��½�û���������Ϊ��,�����û���Ϊ{0}���û�������Ϊ{1}",username,password );
		}
		//MD5�û��������
		String md5Password=MD5Util.MD5EncodeUtf8(password);
		User user=userMapper.findUserByNaAndPs(username,md5Password);
		
		if(null == user){
			log.error("�û���Ϣ������");
			return ResponseMessage.errorWithMessage("�û���Ϣ������");
		}
		//�������ÿ�
		user.setPassword("");
		return  ResponseMessage.successWithData(user);
	}

}
