package kr.pe.team.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.team.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
//CrudRepository<T, ID> = T:엔티티 타입 클래스명 / ID:pk값의 타입
//CrudRepository는 엔티티 클래스에 대해 정교한 crud 작업을 제공
//crud 작업을 하려할 때 interface를 이용하여 사용하면 좋음  / 엔티티 클래스에 대해 dao역할 수행
}
