package com.example.rest_api_test.domain.member.repository;

import com.example.rest_api_test.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
