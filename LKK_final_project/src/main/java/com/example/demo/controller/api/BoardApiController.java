package com.example.demo.controller.api;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.demo.config.auth.PrincipalDetail;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Board;
import com.example.demo.model.Reply;
import com.example.demo.model.User;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	// 글쓰기처리
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {
		boardService.글쓰기(board, principalDetail.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable("id") Integer id) {
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable("id") Integer id, @RequestBody Board board) {
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}


	// 댓글 작성
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable("boardId") Integer boardId, @RequestBody Reply reply,
			@AuthenticationPrincipal PrincipalDetail principalDetail) {

		boardService.댓글쓰기(principalDetail.getUser(), boardId, reply);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@AuthenticationPrincipal PrincipalDetail principalDetail,
			@PathVariable("boardId") Integer boardId, @PathVariable("replyId") Integer replyId) {

		User replyWriter = boardService.댓글주인(replyId);
		if (replyWriter == principalDetail.getUser()) {
			boardService.댓글삭제(replyId);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		} else {
			return new ResponseDto<Integer>(HttpStatus.FORBIDDEN.value(), 1);
		}

	}
}
