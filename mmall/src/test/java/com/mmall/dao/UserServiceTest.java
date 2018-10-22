package com.mmall.dao;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mmall.common.ResponseMessage;
import com.mmall.pojo.Category;
import com.mmall.pojo.User;
import com.mmall.service.CategoryService;
import com.mmall.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-*.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Test
	public void testSelectByPrimaryKey() {
		
	User user= new User();
	user.setUsername("liLei");
	user.setPassword("lilin0901");
	user.setRoleId(1);
	user.setPhone("18882972129");
	user.setCreateTime(new Date());
	user.setUpdateTime(new Date());
		ResponseMessage<String>  user1= userService.addUser(user);
		System.out.println(user1.getMessage());
	}
	
	@Test
	public void testFind(){
		
		User user=userService.findUserByNameAndPassword("liLin", "lilin0901").getData();
		
		assertNotNull(user);
	}
	
	@Test
	public void testAllChild(){
		
		List<Category> cList=Lists.newArrayList();
		List<Category>  list=categoryService.getAllChildByCategoryId(cList, 100001);
		String str=JSON.toJSONString(list);
		System.out.println(str);
	}

}


