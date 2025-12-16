/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.apache.commons.collections.CollectionUtils;

import com.avispl.symphony.api.dal.control.Controller;
import com.avispl.symphony.api.dal.dto.control.ControllableProperty;
import com.avispl.symphony.api.dal.dto.monitor.ExtendedStatistics;
import com.avispl.symphony.api.dal.dto.monitor.Statistics;
import com.avispl.symphony.api.dal.dto.monitor.aggregator.AggregatedDevice;
import com.avispl.symphony.api.dal.monitor.Monitorable;
import com.avispl.symphony.api.dal.monitor.aggregator.Aggregator;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.bases.BaseCommunicator;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.constants.Constant;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.utils.MonitoringUtil;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.types.properties.aggregator.General;

/**
 * Main adapter class for View Sonic (myViewBoard). Responsible for generating aggregated device, monitoring, controllable.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
public class ViewSonicCommunicator extends BaseCommunicator implements Aggregator, Monitorable, Controller {
	/** Application configuration loaded from {@code version.properties}. */
	private final Properties versionProperties;
	/** Stores extended statistics to be sent to the aggregator. */
	private final ExtendedStatistics localExtendedStatistics;

	/** Device adapter instantiation timestamp. */
	private final long adapterInitializationTimestamp;

	public ViewSonicCommunicator() {
		super();
		this.versionProperties = new Properties();
		this.localExtendedStatistics = new ExtendedStatistics();

		this.adapterInitializationTimestamp = System.currentTimeMillis();
	}

	@Override
	protected void authenticate() throws Exception {

	}

	@Override
	protected void internalInit() throws Exception {
		this.loadVersionProperties(this.versionProperties);
		super.internalInit();
	}

	@Override
	protected void internalDestroy() {
		this.versionProperties.clear();
		Optional.ofNullable(this.localExtendedStatistics.getStatistics()).ifPresent(Map::clear);
		super.internalDestroy();
	}

	@Override
	protected HttpHeaders putExtraRequestHeaders(HttpMethod httpMethod, String uri, HttpHeaders headers) throws Exception {
		return super.putExtraRequestHeaders(httpMethod, uri, headers);
	}

	@Override
	public List<Statistics> getMultipleStatistics() throws Exception {
		this.reentrantLock.lock();
		try {
			var statistics = new HashMap<String, String>();
			statistics.putAll(MonitoringUtil.generateProperties(
					General.values(), null,
					property -> MonitoringUtil.mapToGeneral(this.versionProperties, property)
			));

			this.localExtendedStatistics.setStatistics(statistics);
		} finally {
			this.reentrantLock.unlock();
		}
		return Collections.singletonList(this.localExtendedStatistics);
	}

	@Override
	public List<AggregatedDevice> retrieveMultipleStatistics() throws Exception {
		return Collections.emptyList();
	}

	@Override
	public List<AggregatedDevice> retrieveMultipleStatistics(List<String> deviceIds) throws Exception {
		if (CollectionUtils.isEmpty(deviceIds)) {
			this.log.warn(Constant.DEVICE_IDS_EMPTY_WARNING);
			return Collections.emptyList();
		}
		return Collections.emptyList();
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

	/**
	 * Loads version properties and sets initial values used to create General properties for Aggregator.
	 *
	 * @param properties the properties to load and update
	 */
	private void loadVersionProperties(Properties properties) {
		try {
			properties.load(this.getClass().getResourceAsStream("/version.properties"));
			properties.setProperty(General.ADAPTER_UPTIME.getProperty(), String.valueOf(this.adapterInitializationTimestamp));
			properties.setProperty(General.ACTIVE_PROPERTY_GROUPS.getProperty(), Constant.NOT_AVAILABLE);
			properties.setProperty(General.LAST_MONITORING_CYCLE_DURATION.getProperty(), Constant.ZERO);
			properties.setProperty(General.MONITORED_DEVICES_TOTAL.getProperty(), Constant.ZERO);
		} catch (IOException ex) {
			this.log.error(Constant.READ_PROPERTIES_FILE_FAILED, ex);
		}
	}
}
