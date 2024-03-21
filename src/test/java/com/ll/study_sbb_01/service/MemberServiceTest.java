package com.ll.study_sbb_01.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ll.study_sbb_01.domain.Member;
import com.ll.study_sbb_01.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;

	// BeforeEach를 사용해줌으로써 DI 의존성 주입 디펜던시 인젝션
	// 서비스에도 memorymemberrepository를 사용하기 때문에 문제가 된다.
	// 지금 문제가 되진 않는 이유는 MemoryMemberRepository에서 static을 사용하기 때문에 문제가 없는 것
	// 애초에 문제가 생기지 않게 하기 위해 BeforeEach를 사용해주고 의존성 주입을 하여 같은 Repository를 사용하게 만듦
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");

		//when
		Long saveId = memberService.join(member);

		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
			() -> memberService.join(member2));

		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		// try {
		// 	memberService.join(member2);
		// 	fail("예외가 발생해야 합니다.");
		// } catch (IllegalStateException e) {
		// 	assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		// }

		//then
	}

	@Test
	void findMembers() {
	}

	@Test
	void findOne() {
	}
}
