/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.server.backend.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.eclipse.sirius.server.backend.internal.SiriusServerMatchResult;
import org.eclipse.sirius.server.backend.internal.SiriusServerPathMatcher;
import org.junit.Test;

/**
 * Unit tests for the {@link SiriusServerPathMatcher}.
 *
 * @author sbegaudeau
 */
@SuppressWarnings({ "checkstyle:javadocmethod" })
public class SiriusServerPathMatcherTests {

	/** The path of the API. */
	private static final String API_PATH = "/api"; //$NON-NLS-1$

	/** The path of the projects API. */
	private static final String PROJECTS_PATH = "/api/projects"; //$NON-NLS-1$

	/** The path of the project API. */
	private static final String PROJECT_PATH = "/api/projects/{projectName}"; //$NON-NLS-1$

	/** The path of the representations API. */
	private static final String REPRESENTATIONS_PATH = "/api/projects/{projectName}/representations"; //$NON-NLS-1$

	/** The path of the representation API. */
	private static final String REPRESENTATION_PATH = "/api/projects/{projectName}/representations/{representationName}"; //$NON-NLS-1$

	/** The name of the variable used to capture the project name. */
	private static final String PROJECT_NAME_VARIABLE = "projectName"; //$NON-NLS-1$

	/** The name of the variable used to capture the representation name. */
	private static final String REPRESENTATION_NAME_VARIABLE = "representationName"; //$NON-NLS-1$

	/** The name of the test project. */
	private static final String SAMPLE = "sample"; //$NON-NLS-1$

	/** The name of the test representation. */
	private static final String CLASS_DIAGRAM = "classDiagram"; //$NON-NLS-1$

	@Test
	public void testAPIPathValidRequest() {
		SiriusServerPathMatcher pathMatcher = new SiriusServerPathMatcher(API_PATH);
		SiriusServerMatchResult matchResult = pathMatcher.match("/api"); //$NON-NLS-1$
		assertTrue(matchResult.hasMatched());
	}

	@Test
	public void testAPIPathInvalidRequest() {
		SiriusServerPathMatcher pathMatcher = new SiriusServerPathMatcher(API_PATH);
		SiriusServerMatchResult matchResult = pathMatcher.match("/invalid"); //$NON-NLS-1$
		assertFalse(matchResult.hasMatched());
	}

	@Test
	public void testProjectsPathValidRequest() {
		SiriusServerPathMatcher pathMatcher = new SiriusServerPathMatcher(PROJECTS_PATH);
		SiriusServerMatchResult matchResult = pathMatcher.match("/api/projects"); //$NON-NLS-1$
		assertTrue(matchResult.hasMatched());
	}

	@Test
	public void testProjectPathValidRequest() {
		SiriusServerPathMatcher pathMatcher = new SiriusServerPathMatcher(PROJECT_PATH);
		SiriusServerMatchResult matchResult = pathMatcher.match("/api/projects/sample"); //$NON-NLS-1$
		assertTrue(matchResult.hasMatched());

		Map<String, String> variables = matchResult.getVariables();
		assertEquals(variables.size(), 1);
		assertEquals(variables.get(PROJECT_NAME_VARIABLE), SAMPLE);
	}

	@Test
	public void testProjectPathValidRequestWithRemainingPart() {
		SiriusServerPathMatcher pathMatcher = new SiriusServerPathMatcher(PROJECT_PATH);
		SiriusServerMatchResult matchResult = pathMatcher.match("/api/projects/sample/documentation"); //$NON-NLS-1$
		assertTrue(matchResult.hasMatched());

		Map<String, String> variables = matchResult.getVariables();
		assertEquals(variables.size(), 1);
		assertEquals(variables.get(PROJECT_NAME_VARIABLE), SAMPLE);

		assertEquals("documentation", matchResult.getRemainingPart()); //$NON-NLS-1$
	}

	@Test
	public void testRepresentationsPathValidRequest() {
		SiriusServerPathMatcher pathMatcher = new SiriusServerPathMatcher(REPRESENTATIONS_PATH);
		SiriusServerMatchResult matchResult = pathMatcher.match("/api/projects/sample/representations"); //$NON-NLS-1$
		assertTrue(matchResult.hasMatched());

		Map<String, String> variables = matchResult.getVariables();
		assertEquals(variables.size(), 1);
		assertEquals(variables.get(PROJECT_NAME_VARIABLE), SAMPLE);
	}

	@Test
	public void testRepresentationPathValidRequest() {
		SiriusServerPathMatcher pathMatcher = new SiriusServerPathMatcher(REPRESENTATION_PATH);
		SiriusServerMatchResult matchResult = pathMatcher.match("/api/projects/sample/representations/classDiagram"); //$NON-NLS-1$
		assertTrue(matchResult.hasMatched());

		Map<String, String> variables = matchResult.getVariables();
		assertEquals(variables.size(), 2);
		assertEquals(variables.get(PROJECT_NAME_VARIABLE), SAMPLE);
		assertEquals(variables.get(REPRESENTATION_NAME_VARIABLE), CLASS_DIAGRAM);
	}
}
