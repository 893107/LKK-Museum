package kr.pe.team.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.team.dao.BoardRepository;
import kr.pe.team.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	public List<Board> getBoardList(Board board) {
		return (List<Board>) boardRepo.findAll();
		//findAll() = select * from
	}

	public void insertBoard(Board board) {
		System.out.println(board);
		boardRepo.save(board);
		//save() = insert
	}

	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
		//get() : read에 해당, 값을 읽어오는 역할
	}

	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();

		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}

}
