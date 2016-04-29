package com.amozh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringDataRestVirtualWarehouseApplication implements CommandLineRunner {

	@Value("${default.data.generate}")
	private boolean generateDefaultData;

	@Autowired
	private StartupService startupService;

	@Override
	public void run(String... strings) throws Exception {
		if(generateDefaultData) {
			startupService.onStartup();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRestVirtualWarehouseApplication.class, args);
	}
}
