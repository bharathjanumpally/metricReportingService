package com.indigo.metricReportingService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.indigo.metricReportingService.exception.MyResourceNotFoundException;
import com.indigo.metricReportingService.model.InputRequest;
import com.indigo.metricReportingService.service.MetricsReportingService;

@RestController
public class MetricsReportingController {
	@Autowired
	MetricsReportingService reportingService;


	@GetMapping("/metric/{key}/sum")
	public Integer metrics(@PathVariable String key) {
		Integer result = null;
		try {
			result = reportingService.getMetricsSum(key);
		} catch (MyResourceNotFoundException ex) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Key not found", ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@PostMapping("/metric/{key}")
	public void saveMetrics(@RequestBody(required = true) InputRequest req, @PathVariable(required = true) String key) {
		try {
			reportingService.saveMetrics(key, req);
		} catch (MyResourceNotFoundException ex) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, " Request body must contain Value", ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
