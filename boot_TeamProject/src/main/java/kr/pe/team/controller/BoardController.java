package kr.pe.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.pe.team.domain.Board;
import kr.pe.team.domain.Member;
import kr.pe.team.exception.BoardNotFoundException;
import kr.pe.team.service.BoardService;

@SessionAttributes("member")
@Controller
public class BoardController {

	@Autowired //bean 주입받아서 사용
	private BoardService boardService;

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}

	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		//로그인하지 않으면 강제로 로그인 창으로 보내기
		//session 활용 : model에 저장시 @SessionAttributes에서 설정한 값과 동일하면 세션에 저장하여 사용하는 방식... 이해가 덜 되어서 추가 학습 하기!
		if (member.getId() == null) {
			return "redirect:login.html";
		}

		List<Board> boardList = boardService.getBoardList(board);

		System.out.println(boardList);
		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}

	/* 1. @ModelAttribute가 붙은 객체를 자동으로 생성 : 이때 지정되는 클래스는 반드시 bean 클래스여야 하고, getter와 setter 필요 
	 * 	-> Member 클래스는 bean?
	 * 2. 생성된 오브젝트(Member member)에 http로 넘어온 데이터를 부여
	 * 3. 데이터가 해당 클래스의 setter를 통해 멤버 변수에게 저장됨
	 * 4. ModelAttribute가 붙은 객체가 model에 저장됨
	 */
	@GetMapping("/insertBoard")
	public String insertBoardView(@ModelAttribute("member") Member member) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		return "insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.insertBoard(board);
		System.out.println("-------insertBoard----------------------");
		return "redirect:getBoardList";
	}

	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		model.addAttribute("board", boardService.getBoard(board));
		return "getBoard";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		boardService.updateBoard(board);
		return "forward:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		boardService.deleteBoard(board);
		return "forward:getBoardList";
	}

}
