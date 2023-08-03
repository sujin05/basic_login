package com.victolee.signuplogin.service;

import com.victolee.signuplogin.domain.Role;
import com.victolee.signuplogin.domain.entity.MemberEntity;
import com.victolee.signuplogin.domain.repository.MemberRepository;
import com.victolee.signuplogin.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public Long signUp(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        memberRepository.save(memberEntity);
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByEmail(Email);
        MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(Email)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    public MemberDto login(MemberDto memberDTO){ //entity객체는 service에서만
        Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(memberDTO.getEmail());
        if(byMemberEmail.isPresent()){
            // 조회 결과가 있다
            MemberEntity memberEntity = byMemberEmail.get(); // Optional에서 꺼냄
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(memberDTO.getPassword(), memberEntity.getPassword())){
            //if(memberEntity.getPassword().equals(memberDTO.getPassword())) {
                //비밀번호 일치
                //entity -> dto 변환 후 리턴
                MemberDto dto = MemberDto.toMemberDto(memberEntity);
                return dto;
            } else {
                //비밀번호 불일치
                return null;
            }
        } else {
            // 조회 결과가 없다
            return null;
        }
    }
    public List<MemberDto> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        //Controller로 dto로 변환해서 줘야 함
        List<MemberDto> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList){
            memberDTOList.add(MemberDto.toMemberDto(memberEntity));

        }
        return memberDTOList;

    }
    public MemberDto findById(Long id) {
        // 하나 조회할때 optional로 감싸줌
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()){
            return MemberDto.toMemberDto(optionalMemberEntity.get()); // optional을 벗겨내서 entity -> dto 변환
        }else {
            return null;
        }

    }
    @Transactional(readOnly = true)
    public boolean isEmailAlreadyExists(String email) {
        Optional<MemberEntity> existingMember = memberRepository.findByEmail(email);
        return existingMember != null;
    }



    public void deleteByid(Long id) {
        memberRepository.deleteById(id);
    }
}

