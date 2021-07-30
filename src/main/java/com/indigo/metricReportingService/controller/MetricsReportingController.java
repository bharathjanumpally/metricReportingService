package com.indigo.metricReportingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.indigo.metricReportingService.model.InputRequest;
import com.indigo.metricReportingService.service.MetricsReportingService;

@RestController
public class MetricsReportingController {
	@Autowired
	MetricsReportingService reportingService;


	@GetMapping("/metric/{key}/sum")
	public int metrics(@PathVariable String key) {
		return reportingService.getMetricsSum(key);
	}

	@PostMapping("/metric/{key}")
	public void saveMetrics(@RequestBody(required = true) InputRequest req, @PathVariable(required = true) String key) {
		reportingService.saveMetrics(key, req);
	}

}
