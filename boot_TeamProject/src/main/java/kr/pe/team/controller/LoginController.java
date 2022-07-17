package kr.pe.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.pe.team.domain.Member;
import kr.pe.team.service.MemberService;

@SessionAttributes("member")
@RestController
public class LoginController {
	
	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = memberService.getMember(member);
		
		if ( findMember != null 
				&& findMember.getId().equals(member.getId()) 
				&& findMember.getPassword().equals(member.getPassword()) ) {
			
			model.addAttribute("member", findMember);
			return "redirect:index.html";
			
		} else {
			model.addAttribute("msg","로그인에 실패하셨습니다. 아이디와 비밀번호를 다시 확인해주세요.");
			return "/login.jsp";
		}
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("id") String id){
		
		int cnt = memberService.idCheck(id);
		System.out.println(id);
		System.out.println(cnt);
		return cnt;
		
    }
	
	@PostMapping("/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email){
		
		int cnt = memberService.emailCheck(email);
		System.out.println(email);
		System.out.println(cnt);
		return cnt;
	
    }
	

	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
	}
	
	
	@PostMapping("/insert")
	public String insertuser(Member m) {
		Member insertMember = memberService.insertMember(m);
		
		return "/login.jsp";
	}
	

}
