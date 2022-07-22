package com.example.demo.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MailDto;
import com.example.demo.util.MailHandler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "kml1763@naver.com";

	public void mailSend(String email) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);

			// 받는 사람
			mailHandler.setTo(email);
			// 보내는 사람
			mailHandler.setFrom(MailService.FROM_ADDRESS);
			// 제목
			mailHandler.setSubject("LKK Art Gallery 가입을 환영합니다.");
			// HTML Layout
			
			String htmlContent = "<h2>" + "LKK Art Gallery 회원가입을 환영합니다!" + "</h2>" 
								+ "<p>" + "회원가입 절차가 완료 되었으니 로그인 후 서비스를 이용해 주시기를 바랍니다" + "<p>"
								+ "<div class='row'>" + "<img src='https://velog.velcdn.com/images/somm/post/c61aa94f-3345-4022-9856-2a1252b61017/image.png\n" + "' width='40%' height='40%' alt=''>" + "</div>";
			mailHandler.setText(htmlContent, true);
			// 이미지 삽입
			mailHandler.setInline("ticket", "static/ticket.png");

			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mailSend2(String emailinfo) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);

			// 받는 사람
			mailHandler.setTo(emailinfo);
			// 보내는 사람
			mailHandler.setFrom(MailService.FROM_ADDRESS);
			// 제목
			mailHandler.setSubject("LKK Art Gallery 전체컬럼 입니다.");
			// HTML Layout
			String htmlContent = "<h2>" + "Summer Days" + "</h2>"
								+ "<div class='row'>" + "<img src='https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Vicente_Palmaroli_Summer_Days.jpg/686px-Vicente_Palmaroli_Summer_Days.jpg?20120908091704' width='40%' height='40%' alt=''>" + "</div>" + "<br>"
								+ "<div class='col-lg-8 col-lg-offset-2'>" + "<p>" + "작품 명 : Summer Days (c. 1885)" + "<br>"
								+ "화가 : vicente palmaroli y gonzalez" + "<br>"
								+ "<br>" + "비센테 팔마롤리 곤살레스(vicente palmaroli y gonzalez)는 주로 초상화와 풍속화를 그리는 스페인의 화가입니다. 그는 이탈리아의 화가이자 석판화가인 가에타노 팔마롤리의 아들로 자르잘레호에서 태어났습니다. 1853년 그의 아버지가 사망한 후, 그는 왕실 미술품 수집품에서 그의 공식적인 지위를 이어받은 뒤 1857년 로마로 가서 학업을 마치기 위해 휴가를 신청합니다. 그곳에서 그는 루이스 알바레스 카탈라, 디오스코로 푸에블라, 호세 카사도 델 알리살, 에두아르도 로살레스, 베네트 메르카데, 마리시 포투니, 알레호 베라 등 스페인 화가들과 만나게 되며 1862년 이탈리아에서 자신이 만든 두 작품을 가지고 국립 전시회에 참가하여 일등상을 수상하였습니다."
								+ "</p>" +"</div>";
								
			mailHandler.setText(htmlContent, true);

			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}