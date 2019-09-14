package com.bits.ns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bits.ns", "com.bits.ns.controller", "com.bits.ns.auth" })
public class TotpApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TotpApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(TotpApplication.class, args);
	}

}
