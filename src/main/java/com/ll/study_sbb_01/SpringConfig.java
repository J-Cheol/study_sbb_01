package com.ll.study_sbb_01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ll.study_sbb_01.repository.MemberRepository;
import com.ll.study_sbb_01.repository.MemoryMemberRepository;
import com.ll.study_sbb_01.service.MemberService;

@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
