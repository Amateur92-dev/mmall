package com.mmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmall.common.ResponseMessage;
import com.mmall.pojo.User;
import com.mmall.service.UserService;

/**
 * 用户登录、登出
 * 
 * @author jiu2_li
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public ResponseMessage<User> userLogin(HttpSession session, String username, String password) {

		ResponseMessage<User> response = userService.findUserByNameAndPassword(username, password);

		// System.out.println("username="+username +"passord="+password);
		session.setAttribute("login_user", response.getData());

		return response;
	}

	public ResponseMessage<String> addUser(User user) {

		ResponseMessage<String> response = userService.addUser(user);
		return response;

	}
	/**
	 * 修改用户基本信息,不包括用户密码 需登陆后才能修改个人信息
	 * @param session
	 * @param user
	 * @return
	 */
	public ResponseMessage<String> updateUser(HttpSession session, User user) {
		// 检查用户是否登陆
		User loginUser = (User) session.getAttribute("login_user");
		if (null == loginUser) {
			return ResponseMessage.errorWithMessage("用户未登录，无法修改用户信息");
		}
		
		ResponseMessage<String> response = userService.updateUser(user);
		return response;
	}
}
