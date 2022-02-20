package backend.WebPo.api.controller;

import backend.WebPo.api.request.LoginRequest;
import backend.WebPo.api.response.BaseResponse;
import backend.WebPo.db.entity.Member;
import backend.WebPo.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest, HttpServletRequest request){
        String loginId = loginRequest.getLoginId();
        String loginPw = loginRequest.getLoginPw();

        Member member = memberRepository.findByLoginId(loginId);

        if (member == null){
            return ResponseEntity.status(404).body(BaseResponse.of(404,"Not Existing Member"));
        }
        if(passwordEncoder.matches(loginPw, member.getLoginPw())){
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_MEMBER", member);
            return ResponseEntity.status(200).body(BaseResponse.of(200,"Login Success"));
        }
        return ResponseEntity.status(401).body(BaseResponse.of(401, "Invalid Password"));
    }

    @GetMapping("/logout")
    ResponseEntity logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return ResponseEntity.status(200).body(BaseResponse.of(200,"Logout Success"));
    }
}
