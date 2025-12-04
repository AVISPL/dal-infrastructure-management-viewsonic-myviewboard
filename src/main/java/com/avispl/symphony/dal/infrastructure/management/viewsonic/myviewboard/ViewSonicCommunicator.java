/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.apache.commons.collections.CollectionUtils;

import com.avispl.symphony.api.dal.control.Controller;
import com.avispl.symphony.api.dal.dto.control.ControllableProperty;
import com.avispl.symphony.api.dal.dto.monitor.Statistics;
import com.avispl.symphony.api.dal.dto.monitor.aggregator.AggregatedDevice;
import com.avispl.symphony.api.dal.monitor.Monitorable;
import com.avispl.symphony.api.dal.monitor.aggregator.Aggregator;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.bases.BaseCommunicator;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.constants.Constant;

/**
 * Main adapter class for View Sonic (myViewBoard). Responsible for generating aggregated device, monitoring, controllable.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
public class ViewSonicCommunicator extends BaseCommunicator implements Aggregator, Monitorable, Controller {
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
		if (CollectionUtils.isEmpty(deviceIds)) {
			this.log.warn(Constant.DEVICE_IDS_EMPTY_WARNING);
			return Collections.emptyList();
		}
		return null;
	}

	@Override
	public void controlProperty(ControllableProperty controllableProperty) throws Exception {

	}

	@Override
	public void controlProperties(List<ControllableProperty> controllableProperties) throws Exception {
		if (CollectionUtils.isEmpty(controllableProperties)) {
			this.log.warn(Constant.CONTROLLABLE_PROPS_EMPTY_WARNING);
			return;
		}
		for (ControllableProperty controllableProperty : controllableProperties) {
			this.controlProperty(controllableProperty);
		}
	}
}
