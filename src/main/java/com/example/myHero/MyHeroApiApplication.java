package com.example.myHero;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class MyHeroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHeroApiApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(
				new Info()
						.title("MyHeroAPI")
						.version("1.0")
						.description("Challenge for W2M.")
		);
	}
}
