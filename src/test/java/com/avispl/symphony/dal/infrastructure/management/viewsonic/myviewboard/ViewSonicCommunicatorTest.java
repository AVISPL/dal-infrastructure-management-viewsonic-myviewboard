/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avispl.symphony.api.dal.dto.monitor.ExtendedStatistics;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.constants.Constant;

/**
 * Unit tests for the {@link ViewSonicCommunicator} class.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
class ViewSonicCommunicatorTest {
	private ExtendedStatistics extendedStatistics;
	private ViewSonicCommunicator communicator;

	@BeforeEach
	void setUp() throws Exception {
		this.communicator = new ViewSonicCommunicator();
		this.communicator.setHost("0.0.0.0");
		this.communicator.setPort(443);
		this.communicator.setLogin("");
		this.communicator.setPassword("");
		this.communicator.init();
		this.communicator.connect();
	}

	@AfterEach
	void destroy() throws Exception {
		this.communicator.disconnect();
		this.communicator.destroy();
	}

	@Test
	void testGetMultipleStatistics() throws Exception {
		this.extendedStatistics = (ExtendedStatistics) this.communicator.getMultipleStatistics().get(0);
		var statistics = this.extendedStatistics.getStatistics();

		this.verifyStatistics(statistics);
	}

	private void verifyStatistics(Map<String, String> statistics) {
		var groups = new LinkedHashMap<String, Map<String, String>>();
		groups.put(Constant.GENERAL_GROUP, this.filterGroupStatistics(statistics, null));

		for (Map<String, String> initGroup : groups.values()) {
			for (Map.Entry<String, String> initStatistics : initGroup.entrySet()) {
				Assertions.assertNotNull(initStatistics.getValue(), "Value is null with property: " + initStatistics.getKey());
			}
		}
	}

	private Map<String, String> filterGroupStatistics(Map<String, String> statistics, String groupName) {
		return statistics.entrySet().stream()
				.filter(e -> {
					if (groupName == null) {
						return !e.getKey().contains("#");
					}
					return e.getKey().startsWith(groupName);
				})
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
