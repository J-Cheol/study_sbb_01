package com.ll.study_sbb_01.repository;

import java.util.List;
import java.util.Optional;

import com.ll.study_sbb_01.domain.Member;

public interface MemberRepository {
	Member save(Member member);

	Optional<Member> findById(Long id);

	Optional<Member> findByName(String name);

	List<Member> findAll();
}
