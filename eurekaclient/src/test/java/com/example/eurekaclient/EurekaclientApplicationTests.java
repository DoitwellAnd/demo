package com.example.eurekaclient;

import com.example.eurekaclient.dao.mapper.product.StudentMapper;
import com.example.eurekaclient.dto.StudentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "dev")
public class EurekaclientApplicationTests {
	@Autowired
	StudentMapper studentMapper;

	@Test
	public void test() {
		StudentDto studentDto = new StudentDto();
		studentDto.setSubjects(20);
		studentDto.setName("test");

		StudentDto studentDto_1 = new StudentDto();
		studentDto_1.setSubjects(30);
		studentDto_1.setName("test1");

		List<StudentDto> list = new ArrayList<>();
		list.add(studentDto);
		list.add(studentDto_1);


		studentMapper.batchInsert(list);

		studentMapper.update(2L,30);

		List<Long> ids = new ArrayList<>();
		ids.add(2L);
		studentMapper.delete(ids);
	}

}
