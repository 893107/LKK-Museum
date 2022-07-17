package kr.pe.team.util;


import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.pe.team.dao.MemberRepository;
import kr.pe.team.domain.Member;

@Aspect
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(MemberRepository repository) {
//		System.out.println(repository);

		return args -> {
			log.info("Preloading " + repository.save(new Member("유재석", "user1", "11", "1234@gmail.com","01012341234")));
			log.info("Preloading " + repository.save(new Member( "백종원", "user2", "22", "5678@gmail.com","01000000000")));
		};
	}
}