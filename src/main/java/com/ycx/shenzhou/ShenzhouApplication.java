package com.ycx.shenzhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ShenzhouApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShenzhouApplication.class, args);
	}

}
