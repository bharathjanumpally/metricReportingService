package com.indigo.metricReportingService.model;

import java.time.OffsetDateTime;

public class Reporting {
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public OffsetDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(OffsetDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int value;
	public OffsetDateTime timeStamp;

	public Reporting(int value) {
		this.value = value;
		this.timeStamp = OffsetDateTime.now();
	}

}
