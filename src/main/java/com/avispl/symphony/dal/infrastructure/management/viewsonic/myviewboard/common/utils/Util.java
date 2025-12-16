/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import com.avispl.symphony.dal.util.StringUtils;

/**
 * Utility class providing helper methods for all class.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Util {
	/**
	 * Maps the given value to a formatted string based on its type:
	 * <ul>
	 *   <li>If the input value is {@code null}, returns {@code null}.</li>
	 *   <li>If the input value is a {@link String}:
	 *     <ul>
	 *       <li>Returns {@code null} if the string is empty or {@code null}.</li>
	 *       <li>If the string represents a boolean value ("true" or "false", case-insensitive), returns the lowercase string.</li>
	 *       <li>Otherwise, converts the string to title case and returns it.</li>
	 *     </ul>
	 *   </li>
	 *   <li>If the input value is a {@link Boolean} or {@link Integer}, returns its {@code String} representation.</li>
	 *   <li>For any other type, returns {@code null}.</li>
	 * </ul>
	 *
	 * @param value the value to map
	 * @return the mapped string, or {@code null} if unavailable
	 */
	public static String mapToValue(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof String str) {
			if (StringUtils.isNullOrEmpty(str)) {
				return null;
			}
			if (isBooleanValue(str)) {
				return str.toLowerCase();
			}
			return toTitleCase(str);
		}
		if (value instanceof Boolean || value instanceof Integer) {
			return value.toString();
		}

		return null;
	}

	/**
	 * Capitalizes the first character of the input string.
	 * <p>
	 * If the input is {@code null}, empty, or the literal string {@code "null"}, this method returns {@code null}.
	 * If the input is {@code "true"} or {@code "false"}, the method returns the input unchanged.
	 * Otherwise, it returns the input string with its first character converted to uppercase.
	 * </p>
	 *
	 * @param value the input string to convert
	 * @return a string with the first character capitalized, or {@code null} if the input is invalid
	 */
	private static String toTitleCase(String value) {
		if (StringUtils.isNullOrEmpty(value) || value.equals("null")) {
			return null;
		}
		if (isBooleanValue(value)) {
			return value;
		}

		return Character.toUpperCase(value.charAt(0)) + value.substring(1);
	}

	private static boolean isBooleanValue(String value) {
		return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
	}
}
