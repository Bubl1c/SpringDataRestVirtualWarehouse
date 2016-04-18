package com.amozh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SpringDataRestVirtualWarehouseApplication implements CommandLineRunner {

	@Autowired
	private StartupService startupService;

	@Override
	public void run(String... strings) throws Exception {
		startupService.onStartup();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestVirtualWarehouseApplication.class, args);
	}
}
