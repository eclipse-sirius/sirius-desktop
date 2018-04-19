/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.workflow.services.api.workflow;

import java.util.Optional;

/**
 * The service class used to manipulate Sirius workflows.
 *
 * @author sbegaudeau
 */
public interface ISiriusWorkflowService {
	/**
	 * Returns the workflow of the session with the given sessionId.
	 *
	 * @param sessionId
	 *            The identifier of the session
	 * @return The workflow of the session or an empty optional if none has been found
	 */
	Optional<WorkflowDto> getWorkflow(String sessionId);

	/**
	 * Returns the page with the given pageId.
	 *
	 * @param sessionId
	 *            The identifier of the session
	 * @param pageId
	 *            The identifier of the page
	 * @return An optional containing the page found, or an empty optional
	 */
	Optional<PageDto> getPage(String sessionId, String pageId);
}
