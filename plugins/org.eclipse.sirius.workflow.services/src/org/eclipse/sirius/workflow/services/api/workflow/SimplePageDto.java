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

/**
 * A simple version of the page.
 *
 * @author sbegaudeau
 */
public class SimplePageDto {
	/**
	 * The identifier of the page.
	 */
	private String pageId;

	/**
	 * The label.
	 */
	private String label;

	/**
	 * The description.
	 */
	private String description;

	/**
	 * The constructor.
	 *
	 * @param pageId
	 *            The identifier of the page
	 * @param label
	 *            The label
	 * @param description
	 *            The description
	 */
	public SimplePageDto(String pageId, String label, String description) {
		this.pageId = pageId;
		this.label = label;
		this.description = description;
	}

	/**
	 * Return the pageId.
	 *
	 * @return the pageId
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * Return the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Return the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
}
