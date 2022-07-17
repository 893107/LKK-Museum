package kr.pe.team.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.team.domain.Board;
import kr.pe.team.domain.Member;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v1/api")
public class SwaggerController {

//	@ApiIgnore
	// 각각의 리소스에 제목과 설명 표기
	@ApiOperation(value = "유저 검색", notes = "API 설명 부분 : 유저 한명 검색")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK !!"),
			@ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
			@ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !") })

	@GetMapping(value = "/Member")
	public Map<String, String> selectOneMember(
			@ApiParam(value = "id", required = true, example = "user11") @RequestParam String no) {
		Map<String, String> result = new HashMap<>();
		result.put("id", "user11");
		result.put("password", "1234");
		result.put("name", "유재석");
		result.put("role", "오락부장");
		return result;
	}
	
	@GetMapping(value = "/Board")
    public Map<String, String> selectOneBoard(@ApiParam(value = "seq", required = true, 
    													   example = "1") 
    											 @RequestParam String no) {
        Map<String, String> result = new HashMap<>();
        result.put("seq", "1");
        result.put("title", "1234");
        result.put("writer", "유재석");
        result.put("content", "오락부장");
        return result;
    }

	
    @ApiOperation(value = "게시글 정보 저장", notes = "API 설명 부분 : 게시글 저장 또는 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK !!"),
            @ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
            @ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !")
    })
    @PostMapping(value = "/Board")
    public String addOneBoard(@RequestBody Board b, @ApiIgnore HttpSession session) {
    	return "게시글 저장 성공";
    }
	

	@ApiOperation(value = "사원 정보 저장", notes = "API 설명 부분 : 사원 한명 저장 또는 수정")
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "OK !!"),
			@ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !"),
			@ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !") })
	@PostMapping(value = "/Member")
	public String addOneEmployee(@RequestBody Member mem, @ApiIgnore HttpSession session) {
		return "사원 저장 성공";
	}

}