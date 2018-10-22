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
	 * �û���½ username ,password
	 */
	public ResponseMessage<User> findUserByNameAndPassword(String username, String password) {

		if (null == username || null == password) {
			ResponseMessage.errorWithMessage("�û��������벻��Ϊ��");
			log.error("��½�û���������Ϊ��,�����û���Ϊ{0}���û�������Ϊ{1}", username, password);
		}
		// MD5�û��������
		String md5Password = MD5Util.MD5EncodeUtf8(password);
		User user = userMapper.findUserByNaAndPs(username, md5Password);

		if (null == user) {
			log.error("�û���Ϣ������");
			return ResponseMessage.errorWithMessage("�û���Ϣ������");
		}
		// �������ÿ�
		user.setPassword("");
		return ResponseMessage.successWithData(user);
	}

	/**
	 * �����û���Ϣ
	 */
	public ResponseMessage<String> addUser(User user) {
		if (null == user) {

			return ResponseMessage.errorWithMessage("����Ϊ��");
		}
		if (null == user.getUsername() || null == user.getPassword()) {
			return ResponseMessage.errorWithMessage("�û����������붼����Ϊ��");
		}
		// 1 ���Ȳ����û��Ƿ����
		String md5EncodeUtf8 = MD5Util.MD5EncodeUtf8(user.getPassword());
		User getUser = userMapper.findUserByNaAndPs(user.getUsername(), md5EncodeUtf8);

		if (null != getUser) {
			log.error("�û��Ѵ���");
			return ResponseMessage.errorWithMessage("�û��Ѵ���");
		}
		// ������ܴ���
		// �����û�
		user.setPassword(md5EncodeUtf8);
		int count = userMapper.insertSelective(user);

		if (count > 0) {

			return ResponseMessage.successWithMessage("����û��ɹ�");
		}
		return ResponseMessage.successWithMessage("����û�ʧ��");
	}

	/**
	 * �û��Ѿ���½�������ٴ��û��Ƿ����
	 */
	public ResponseMessage<String> updateUser(User user) {
		int iRet = userMapper.updateByPrimaryKeySelective(user);
		if (iRet == 1) {

			return ResponseMessage.successWithMessage("�޸��û���Ϣ�ɹ�");
		}

		return ResponseMessage.errorWithMessage("�޸��û���Ϣ�쳣");
	}

}
