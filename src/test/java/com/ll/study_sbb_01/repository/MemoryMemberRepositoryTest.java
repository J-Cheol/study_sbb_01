package com.ll.study_sbb_01.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ll.study_sbb_01.domain.Member;

public class MemoryMemberRepositoryTest {

	MemberRepository repository = new MemoryMemberRepository();

	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");

		repository.save(member);

		Member result = repository.findById(member.getId()).get();
		assertThat(member).isEqualTo(result);
	}
	
}
