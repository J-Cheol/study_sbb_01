package com.ll.study_sbb_01.service;

import com.ll.study_sbb_01.domain.Member;
import com.ll.study_sbb_01.repository.MemberRepository;
import com.ll.study_sbb_01.repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();

	// 회원 가입
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증

		memberRepository.save(member);
		return member.getId();
	}

	// 메소드 추출 단축키 컨트롤 + 알트 + 쉬프트 + T
	private void validateDuplicateMember(Member member) {
		// null이 있을 수 있는 경우가 있다면 옵셔널로 감싼다 옵셔널이 숨겨져있다.
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
}
