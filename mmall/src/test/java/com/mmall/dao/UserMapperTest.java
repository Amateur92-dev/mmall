package com.mmall.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mmall.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-*.xml"})
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	@Test
	public void testSelectByPrimaryKey() {
		
	
		User user =userMapper.selectByPrimaryKey(1);		
		System.out.println(user.getUsername());
	}
	@Test
	public void testFindUserByNaAndPass(){
		
	
		User user =userMapper.findUserByNaAndPs("admin", "admin");
		System.out.println(user.getUsername());
	}

}
