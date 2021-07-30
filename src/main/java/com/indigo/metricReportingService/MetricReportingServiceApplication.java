package com.indigo.metricReportingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class MetricReportingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricReportingServiceApplication.class, args);
	}

}
