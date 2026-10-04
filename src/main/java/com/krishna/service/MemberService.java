package com.krishna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishna.entity.Member;
import com.krishna.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		
		return memberRepository.save(member);
	}
	
	public List<Member> getAllMembers(){
		
		return memberRepository.findAll();
	}
	
	public Member getMemberById(int id) {
		
		return memberRepository.findById(id).orElse(null);
	}
	
	public void deleteMember(int id) {
		
		memberRepository.deleteById(id);
	}
	
	
}
