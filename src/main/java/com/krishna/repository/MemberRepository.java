package com.krishna.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

}
