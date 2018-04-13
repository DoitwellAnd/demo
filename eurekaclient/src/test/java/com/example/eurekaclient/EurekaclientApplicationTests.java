package com.example.eurekaclient;

import com.alibaba.fastjson.JSON;
import com.example.eurekaclient.dto.UserInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "dev")
public class EurekaclientApplicationTests {

	@Test
	public void test() {
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setId(1L);
		userInfoDto.setUsername("test");

		System.out.printf(JSON.toJSONString(userInfoDto)+"-------------------");
//
//		StudentDto studentDto_1 = new StudentDto();
//		studentDto_1.setSubjects(30);
//		studentDto_1.setName("test1");
//
//		List<StudentDto> list = new ArrayList<>();
//		list.add(studentDto);
//		list.add(studentDto_1);
//
//
//		studentMapper.batchInsert(list);
//
//		studentMapper.update(2L,30);
//
//		List<Long> ids = new ArrayList<>();
//		ids.add(2L);
//		studentMapper.delete(ids);

//		String result = redisClient.set("key","helloworld");
//		System.out.println("set-------------------"+result);
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		String value = redisClient.get("key");
//		System.out.println("get-------------------"+value);
//
//		String value2 = redisClient.get("hello");
//		System.out.println("get2-------------------"+value2);

	}

}
