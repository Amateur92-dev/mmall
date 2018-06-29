package com.mmall.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmall.common.ResponseMessage;
import com.mmall.pojo.User;
import com.mmall.service.UserService;

/**
 * ÓÃ»§µÇÂ¼¡¢µÇ³ö
 * 
 * @author jiu2_li
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	
	@RequestMapping("/login")
	public ResponseMessage<User> userLogin(HttpSession session, @RequestBody String username,@RequestBody String password) {

		ResponseMessage<User> response = userService.findUserByNameAndPassword(username,password);
		
		System.out.println("username="+username +"passord="+password);
		session.setAttribute("login_user", response.getData());

		return response;
	}

}
