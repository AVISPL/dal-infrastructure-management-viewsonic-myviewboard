/** Copyright (c) 2025 AVI-SPL, Inc. All Rights Reserved. */
package com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.bases;

import java.util.concurrent.locks.ReentrantLock;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.avispl.symphony.dal.communicator.RestCommunicator;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.Logger;
import com.avispl.symphony.dal.infrastructure.management.viewsonic.myviewboard.common.RequestStateHandler;

/**
 * Configures the communicator and provides helper methods for managing adapter properties.
 * <p>This class centralizes all communicator-related configuration and exposes utility methods to access adapter properties.
 *
 * @author Kevin / Symphony Dev Team
 * @since 1.0.0
 */
public abstract class BaseCommunicator extends RestCommunicator {
	/** Logger used for recording diagnostic and runtime information. */
	protected final Logger log;
	/** Lock for thread-safe operations. */
	protected final ReentrantLock reentrantLock;
	/** Object mapper used to convert JSON responses into model objects. */
	protected final ObjectMapper objectMapper;
	/** Handles request status tracking and error detection. */
	protected final RequestStateHandler requestStateHandler;

	protected BaseCommunicator() {
		this.log = new Logger(super.logger);
		this.reentrantLock = new ReentrantLock();
		this.objectMapper = new ObjectMapper();
		this.requestStateHandler = new RequestStateHandler();
	}

	@Override
	protected void internalInit() throws Exception {
		this.setAuthenticationScheme(AuthenticationScheme.None);
		this.setTrustAllCertificates(true);
		super.internalInit();
	}

	@Override
	protected void internalDestroy() {
		this.requestStateHandler.clear();
		super.internalDestroy();
	}
}
