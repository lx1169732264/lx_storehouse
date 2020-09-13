package com.lx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.lx.sys.mapper")
//@ComponentScan("com.lx.sys.config")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
