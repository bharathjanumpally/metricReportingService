package com.indigo.metricReportingService.controller;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito.Then;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.indigo.metricReportingService.model.InputRequest;
import com.indigo.metricReportingService.model.Reporting;
import com.indigo.metricReportingService.service.MetricsReportingService;

@RunWith(MockitoJUnitRunner.class)
public class MetricsReportingControllerTest {

	@InjectMocks
	MetricsReportingController metricsReportingController;
	@Mock
	private MetricsReportingService metricsReportingService;
	@Mock
	private InputRequest inputRequest;

	@Test
	public void testPostMetricsReporting() throws Exception {
		Mockito.doNothing().when(metricsReportingService).saveMetrics("indigo", inputRequest);
		metricsReportingController.saveMetrics(inputRequest, "indigo");
		verify(metricsReportingService, times(1)).saveMetrics("indigo", inputRequest);

	}

	@Test
	public void testGetMetricsReporting() throws Exception {
		when(metricsReportingService.getMetricsSum("indigo")).thenReturn(2);
		int total = metricsReportingController.metrics("indigo");
		assertTrue("Success", total == 2);
	}

}
