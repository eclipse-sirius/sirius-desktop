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

import java.util.List;

/**
 * The workflow.
 *
 * @author sbegaudeau
 */
public class WorkflowDto {
	/**
	 * The pages of the workflow.
	 */
	private List<SimplePageDto> pages;

	/**
	 * The constructor.
	 *
	 * @param pages
	 *            The pages of the workflow
	 */
	public WorkflowDto(List<SimplePageDto> pages) {
		this.pages = pages;
	}

	/**
	 * Return the pages of the workflow.
	 *
	 * @return the pages of the workflow
	 */
	public List<SimplePageDto> getPages() {
		return this.pages;
	}
}
