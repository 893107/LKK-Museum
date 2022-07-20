package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.MemberTO;
import com.example.demo.entity.Member;
import com.example.demo.service.MailService;
import com.example.demo.service.MemberService;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String homeView() {
        return "pages/home";
    }

    @GetMapping("/login")
    public String loginView(MemberTO memberTO, Model model) {
    	return "pages/login";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "pages/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberTO memberTO) {
        memberService.save(memberTO);
        return "redirect:/login";
    }
    
    @GetMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("account") String account){
		
		int cnt = memberService.idCheck(account);
		System.out.println(account);
		System.out.println(cnt);
		return cnt;
		
    }
    @Autowired
    private MailService mailService;

    @GetMapping("/mail")
    public String dispMail() {
        return "pages/mail";
    }

    @GetMapping("/mailsend")
    public String execMail(@RequestParam("email") String email) {
        System.out.println(email);
    	mailService.mailSend(email);
        return "pages/home";
    }   
    
    
	
	@GetMapping("/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email){
		
		int cnt = memberService.emailCheck(email);
		System.out.println(email);
		System.out.println(cnt);
		return cnt;
	
    }
    

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/member/info")
    public String userInfoView(Principal principal, Model model) {
        
    	String membername = principal.getName();
    	Member mbinfo = memberService.getMember(membername);
    	System.out.println(mbinfo);
    	model.addAttribute("memberinfo", mbinfo);
    	return "pages/userinfo";
    }
    
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/member/update")
    public String UpdateMember(MemberTO memberTO) {
    	
        System.out.println(memberTO);
        return "pages/home";
    }
    

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminView() {
        return "pages/admin";
    }

    @GetMapping("/denied")
    public String deniedView() {
        return "pages/denied";
    }
    
    
    
    
    
    
}