package com.indigo.metricReportingService.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.indigo.metricReportingService.model.InputRequest;

@RunWith(MockitoJUnitRunner.class)
public class MetricReportingServiceTest {
	
	@InjectMocks
	private MetricsReportingService metricsReportingService;
	
	private String testKey;

	@Mock
	private InputRequest inputRequest;
	
	@Before
	public void setup() {
		testKey = "indigo";
	}
	
	@Test
	public void savedMetricsTest() throws Exception {
		inputRequest.setValue(4);
		metricsReportingService.saveMetrics(testKey, inputRequest);
		inputRequest.setValue(3);
		metricsReportingService.saveMetrics(testKey, inputRequest);
		
	}
	
	@Test
	public void getMetricsTest() throws Exception {
		metricsReportingService.saveMetrics(testKey, inputRequest);
		int result = metricsReportingService.getMetricsSum(testKey);
		Assert.assertEquals(0, result);
	}
	

	@Test
	public void deleteExpiredElementsTest() throws Exception {
		metricsReportingService.deleteExpiredElements();
		
	}
}
