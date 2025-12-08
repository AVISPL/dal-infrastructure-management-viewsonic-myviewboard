/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility class that defines constant values used across the application.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constant {
	//	Format
	public static final String PROPERTY_FORMAT = "%s#%s";

	//	Special characters
	public static final String COMMA = ",";

	//	Values
	public static final String NOT_AVAILABLE = "N/A";
	public static final String ZERO = "0";

	//	Groups
	public static final String GENERAL_GROUP = "General";

	//	Warning messages
	public static final String CONTROLLABLE_PROPS_EMPTY_WARNING = "ControllableProperties list is null or empty, skipping control operation";
	public static final String DEVICE_IDS_EMPTY_WARNING = "The deviceIds parameter is null or empty. Returning empty aggregated device list";

	//	Fail messages
	public static final String REQUEST_APIS_FAILED = "Unable to process requested API sections: [%s], error reported: [%s]";
	public static final String READ_PROPERTIES_FILE_FAILED = "Failed to load version properties file.";
	public static final String MAP_TO_UPTIME_FAILED = "Failed to mapToUptime with uptime: ";
	public static final String MAP_TO_UPTIME_MIN_FAILED = "Failed to mapToUptimeMin with uptime: ";
}
