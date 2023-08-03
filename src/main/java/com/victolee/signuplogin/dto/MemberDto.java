package com.victolee.signuplogin.dto;

import com.victolee.signuplogin.domain.entity.MemberEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;

    private String email;
    private String password;
    private String bike_num;
    private String phone_num;
    private String name;
    private String birth;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

//    public MemberEntity toEntity(){
//        return MemberEntity.toMemberEntity()
//                .id(id)
//                .email(email)
//                .password(password)
//                .bike_num(bike_num)
//                .phone_num(phone_num)
//                .name(name)
//                .birth(birth)
//                //.createdate(createdDate)
//                .build();
//    }
//
//    @Builder
//    public MemberDto(Long id, String email, String password,String bike_num,String phone_num, String name, String birth) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.bike_num = bike_num;
//        this.phone_num = phone_num;
//        this.name = name;
//        this.birth = birth;
//        //this.createdDate = createdDate;
//    }
//}
    public static MemberDto toMemberDto(MemberEntity memberEntity){
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setEmail(memberEntity.getEmail());
        memberDto.setPassword(memberEntity.getPassword());
        memberDto.setBike_num(memberDto.getBike_num());
        memberDto.setPhone_num(memberDto.getPhone_num());
        memberDto.setName(memberEntity.getName());
        memberDto.setBirth(memberDto.getBirth());
        return memberDto;
    }
}
