package com.kns.tenquest;

import com.kns.tenquest.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.kns.tenquest.repository.MemberRepository;

import java.util.List;

@SpringBootTest
class TenquestApplicationTests {
	@Autowired
	MemberRepository memberRepository;
	@Test
	void getMember(){

		List<Member> all = memberRepository.findAll();
		System.out.println(all.size());
	}
}
