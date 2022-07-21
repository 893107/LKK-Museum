package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetail;
import com.example.demo.model.KakaoProfile;
import com.example.demo.model.OAuthToken;
import com.example.demo.model.User;
import com.example.demo.service.BoardService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;


    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }


    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
    
    @GetMapping("/auth/auction")
    public String showAuction(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("user", principalDetail.getUser());
        return "/user/auction";
    }
    
    @GetMapping("/auth/store")
    public String showStore() {
        return "user/store";
    }


    @GetMapping("/user/updateForm")
    public String updateForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("user", principalDetail.getUser());
        return "/user/updateForm";
    }

    // 작성글 보기
    @GetMapping("/user/myBoard")
    public String index(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("user", principalDetail.getUser());
        model.addAttribute("boards", boardService.작성글목록(principalDetail.getUser()));
        return "/user/myBoard";
    }
}
