package com.indigo.metricReportingService.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.indigo.metricReportingService.exception.MyResourceNotFoundException;
import com.indigo.metricReportingService.model.InputRequest;
import com.indigo.metricReportingService.model.Reporting;

@Service
public class MetricsReportingService {

	public Map<String, List<Reporting>> savedMetrics = new HashMap<>();

	public void saveMetrics(String key, InputRequest req) throws Exception {

		try {
			if (Objects.nonNull(savedMetrics.get(key))) {
				List<Reporting> reportList = savedMetrics.get(key);
				Reporting newReport = new Reporting(req.getValue());
				reportList.add(newReport);
				savedMetrics.put(key, reportList);
			} else {
				List<Reporting> reportList = new ArrayList<>();
				Reporting newReport = new Reporting(req.getValue());
				reportList.add(newReport);
				savedMetrics.put(key, reportList);
			}
		} catch (NullPointerException e) {
			throw new MyResourceNotFoundException("Please send the valid Key value");

		} catch (Exception e) {
			throw new Exception("Error while saving the data");
		}
	}

	public int getMetricsSum(String key) throws Exception {
		OffsetDateTime currentTime = OffsetDateTime.now().minus(Duration.ofHours(1));
		int metricSum = 0;
		List<Reporting> reporting = savedMetrics.get(key);
		try {
			for (Reporting rep : reporting) {
				if (rep.getTimeStamp().isAfter(currentTime)) {
					metricSum += rep.getValue();
				}
			}
		} catch (NullPointerException e) {
			throw new MyResourceNotFoundException("Please send the valid Key value");
		} catch (Exception e) {
			throw new Exception("Error while fetching data from saved Metrics");
		}

		return metricSum;
	}

	@Scheduled(fixedRate = 60 * 60 * 1000)
	public void deleteExpiredElements() {
		OffsetDateTime currentTime = OffsetDateTime.now().minus(Duration.ofHours(1));
		for (Map.Entry<String, List<Reporting>> entry : savedMetrics.entrySet()) {
			List<Reporting> reportList = entry.getValue();
			List<Reporting> tempList = new ArrayList<>();

			if (!reportList.isEmpty()) {
				for (Reporting rep : reportList) {
					if (rep.getTimeStamp().isAfter(currentTime)) {
						tempList.add(rep);
					}
				}
			}
			savedMetrics.put(entry.getKey(), tempList);
		}
	}

}
