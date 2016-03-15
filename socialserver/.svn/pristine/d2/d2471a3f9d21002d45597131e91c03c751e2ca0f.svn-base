package com.ss.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ss.pojo.Baseinfo;
import com.ss.service.BaseinfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class Tester {
	
	@Resource
	private BaseinfoService service;
	
	@Test
	public void test1(){
		Baseinfo info = service.getInfoById(1);
		System.out.println(info);
//		
//		Assert.assertEquals(info.getPhone(), "13951039627");
//		
//		System.out.println(info.getNick());
		
//		Baseinfo info = new Baseinfo();
//		info.setPhone("13951100890");
//		info.setPassword("hhhaaaa");
//		info.setRegister(new Date());
//		info.setLasttime(new Date());
//		
//		service.insertWithChildren(info);
	}
}
