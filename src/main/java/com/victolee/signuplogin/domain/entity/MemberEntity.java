package com.victolee.signuplogin.domain.entity;

import com.victolee.signuplogin.dto.MemberDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String bike_num;
    @Column(length = 100, nullable = false)
    private String phone_num;
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String birth;



    //@Column(length = 20, nullable = false)
    //private LocalDateTime createdate;


    public static MemberEntity toMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId());
        memberEntity.setEmail(memberDto.getEmail());
        memberEntity.setPassword(memberDto.getPassword());
        memberEntity.setBike_num(memberDto.getBike_num());
        memberEntity.setPhone_num(memberDto.getPhone_num());
        memberEntity.setName(memberDto.getName());
        memberEntity.setBirth(memberDto.getBirth());
        return memberEntity;
    }

    public Long getId() {

        return id;
    }
}