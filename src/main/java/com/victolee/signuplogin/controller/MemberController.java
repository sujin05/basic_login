package com.victolee.signuplogin.controller;

import com.victolee.signuplogin.dto.MemberDto;
import com.victolee.signuplogin.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class MemberController {
    private final MemberService memberService;


    // 메인 페이지
    public String index() {

        return "/index";
    }

    // 회원가입 페이지
    @GetMapping("/signup")
    public String dispSignup() {

        return "user/signup";
    }

    // 회원가입 처리
    @PostMapping("/signup1")
    public ResponseEntity<String> execSignup(@RequestBody MemberDto memberDto) {
        // Check if the email already exists in the database
        if (memberService.isEmailAlreadyExists(memberDto.getEmail())) {
            String response = "이미 사용 중인 이메일입니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // If the email is not a duplicate, proceed with the signup process
        memberService.signUp(memberDto);
        String response = "POST 요청이 성공적으로 처리되었습니다.";
        System.out.println(memberDto);
        return ResponseEntity.ok(response);
        //return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "user/login";
    }


    @PostMapping("/login1") // session : 로그인 유지
    public ResponseEntity<String> execSignup(@RequestBody MemberDto memberDto, HttpSession session) {
        MemberDto loginResult = memberService.login(memberDto);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getEmail());
            return ResponseEntity.ok("success");
            //return "main";
        } else {
            // login 실패
            return ResponseEntity.ok("fail");
        }
    }

    @GetMapping("/member") //모든 회원 조회
    public String findAll(Model model) {
        List<MemberDto> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("memberList", memberDTOList);
        return memberDTOList.toString();

    }
    @GetMapping("/member/{id}") //회원 정보 조회
    public String findById(@PathVariable Long id, Model model) {
        MemberDto memberDto = (MemberDto) memberService.findById(id);
        // login 처럼 return 값에 따라 분류 할 수 있음
        model.addAttribute("member", memberDto);
        return String.valueOf(memberDto);
    }
    @GetMapping("/member/delete/{id}") // 회원 삭제
    public String deleteById(@PathVariable Long id){
        memberService.deleteByid(id);

        return "redirect:/member/"; // list 로 쓰면 껍데기만 보여짐
    }

    // 내 정보 페이지
    @GetMapping("/info")
    public String dispMyInfo(Principal principal, ModelMap modelMap) {
        String loginId = principal.getName();
        MemberDto memberDto = (MemberDto) memberService.loadUserByUsername(loginId);
        modelMap.addAttribute("memberDto",memberDto);
        return String.valueOf(memberDto);
    }


    @GetMapping("/loginerror")
    public String loginerror(@RequestParam("login_error")String loginerror){
        return "user/loginerror";
    }


    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "user/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public String dispDenied() {
        return "/denied";
    }



    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "user/admin";
    }
}
