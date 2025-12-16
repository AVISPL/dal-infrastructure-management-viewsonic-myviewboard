/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.types.properties.aggregator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.bases.BaseProperty;

/**
 * Represents general properties for the aggregator.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public enum General implements BaseProperty {
	ADAPTER_BUILD_DATE("AdapterBuildDate", "adapter.build.date"),
	ADAPTER_UPTIME("AdapterUptime", "adapter.uptime"),
	ADAPTER_UPTIME_MIN("AdapterUptime(min)", "adapter.uptime"),
	ADAPTER_VERSION("AdapterVersion", "adapter.version"),
	ACTIVE_PROPERTY_GROUPS("ActivePropertyGroups", "adapter.active.property.groups"),
	LAST_MONITORING_CYCLE_DURATION("LastMonitoringCycleDuration(s)", "adapter.cycle.duration"),
	MONITORED_DEVICES_TOTAL("MonitoredDevicesTotal", "adapter.devices.total");

	String name;
	String property;
}
