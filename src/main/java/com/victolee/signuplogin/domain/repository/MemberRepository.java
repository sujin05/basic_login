package com.victolee.signuplogin.domain.repository;

import com.victolee.signuplogin.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository; //데이터 검색기능

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByEmail(String Email);
}
