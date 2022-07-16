package kr.pe.team.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity

//@ApiModel(value = "게시글 정보", description = "글 번호, 제목, 글쓴이, 내용의 정보가 담긴 Domain Class")
public class Board {
	@Id
	@GeneratedValue
//	@ApiModelProperty(example="1")
	private Long seq;

	private String title;

	@Column
	private String writer;

	private String content;

}

