package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.auth.PrincipalDetail;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.RoleType;
import com.example.demo.model.User;
import com.example.demo.service.MailService;
import com.example.demo.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Json 데이터를 받으려면 @RequestBody로 받아야함
    // 회원가입
    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바 오브젝트를 JSON으로 변환하여 전송 (JACKSON)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {

        userService.회원수정(user);
        // 여기서는 트랜잭션이 종료되기 때문에 DB값은 변경이 됐음
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 직접 세션 값을 변경해줘야함.
        // 한마디로 DB는 회원수정이 이뤄졌지만, 실제 웹에서는 세션정보는 DB수정 전 세션이라는 뜻
        // 해결하기 위해서 세션 정보를 직접 변경 해줘야함

        // 세션등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    
    @GetMapping("/auth/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("username") String username){
		
		int cnt = userService.idcheck(username);
		System.out.println(username);
		System.out.println(cnt);
		return cnt;
		
    }
    
    @GetMapping("/auth/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email){
		
		int cnt = userService.emailcheck(email);
		System.out.println(email);
		System.out.println(cnt);
		return cnt;
	
    }
    
    @Autowired
    private MailService mailService;

    @GetMapping("/mail")
    public String dispMail() {
        return "pages/mail";
    }

    @GetMapping("/auth/mailsend")
    public void execMail(@RequestParam("email") String email) {
        System.out.println(email);
    	mailService.mailSend(email);
    }
    
    @GetMapping("/auth/mailsend2")
    public void execMail2(@RequestParam("email") String email) {
        System.out.println(email);
    	mailService.mailSend2(email);
    }


}
