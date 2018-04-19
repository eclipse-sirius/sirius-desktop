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
 * The page.
 *
 * @author sbegaudeau
 */
public class PageDto {
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
	 * The identifier of the previous page.
	 */
	private String previousPageId;

	/**
	 * The identifier of the next page.
	 */
	private String nextPageId;

	/**
	 * The sections.
	 */
	private List<SectionDto> sections;

	/**
	 * The constructor.
	 *
	 * @param pageId
	 *            The identifier of the page
	 * @param label
	 *            The label
	 * @param description
	 *            The description
	 * @param previousPageId
	 *            The identifier of the previous page
	 * @param nextPageId
	 *            The identifier of the next page
	 * @param sections
	 *            The sections
	 */
	public PageDto(String pageId, String label, String description, String previousPageId, String nextPageId, List<SectionDto> sections) {
		this.label = label;
		this.description = description;
		this.previousPageId = previousPageId;
		this.nextPageId = nextPageId;
		this.sections = sections;
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

	/**
	 * Return the previousPageId.
	 *
	 * @return the previousPageId
	 */
	public String getPreviousPageId() {
		return this.previousPageId;
	}

	/**
	 * Return the nextPageId.
	 *
	 * @return the nextPageId
	 */
	public String getNextPageId() {
		return this.nextPageId;
	}

	/**
	 * Return the sections.
	 *
	 * @return the sections
	 */
	public List<SectionDto> getSections() {
		return this.sections;
	}
}
