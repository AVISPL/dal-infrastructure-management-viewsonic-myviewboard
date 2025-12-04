/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.avispl.symphony.api.dal.control.Controller;
import com.avispl.symphony.api.dal.dto.control.ControllableProperty;
import com.avispl.symphony.api.dal.dto.monitor.Statistics;
import com.avispl.symphony.api.dal.dto.monitor.aggregator.AggregatedDevice;
import com.avispl.symphony.api.dal.monitor.Monitorable;
import com.avispl.symphony.api.dal.monitor.aggregator.Aggregator;
import com.avispl.symphony.dal.communicator.RestCommunicator;

/**
 * Main adapter class for View Sonic (myViewBoard). Responsible for generating aggregated device, monitoring, controllable.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
public class ViewSonicCommunicator extends RestCommunicator implements Aggregator, Monitorable, Controller {
	public ViewSonicCommunicator() {
		super();
	}

	@Override
	protected void authenticate() throws Exception {

	}

	@Override
	protected void internalInit() throws Exception {
		super.internalInit();
	}

	@Override
	protected void internalDestroy() {
		super.internalDestroy();
	}

	@Override
	protected HttpHeaders putExtraRequestHeaders(HttpMethod httpMethod, String uri, HttpHeaders headers) throws Exception {
		return super.putExtraRequestHeaders(httpMethod, uri, headers);
	}

	@Override
	public List<Statistics> getMultipleStatistics() throws Exception {
		return null;
	}

	@Override
	public List<AggregatedDevice> retrieveMultipleStatistics() throws Exception {
		return null;
	}

	@Override
	public List<AggregatedDevice> retrieveMultipleStatistics(List<String> deviceIds) throws Exception {
		return null;
	}

	@Override
	public void controlProperty(ControllableProperty controllableProperty) throws Exception {

	}

	@Override
	public void controlProperties(List<ControllableProperty> controllableProperties) throws Exception {

	}
}
