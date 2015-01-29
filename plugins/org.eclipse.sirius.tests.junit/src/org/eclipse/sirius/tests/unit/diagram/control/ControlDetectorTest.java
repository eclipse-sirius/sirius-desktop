/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.control;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;

public class ControlDetectorTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/control/vp-2517";

    private static final String AIRD_MODEL_FILENAME_1 = "/1.aird";

    private static final String SEMANTIC_MODEL_FILENAME_1 = "/1.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_2 = "/2.ecore";

    private static final String SEMANTIC_MODEL_FILENAME_3 = "/3.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, AIRD_MODEL_FILENAME_1, SEMANTIC_MODEL_FILENAME_1, SEMANTIC_MODEL_FILENAME_2, SEMANTIC_MODEL_FILENAME_3);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + SEMANTIC_MODEL_FILENAME_1), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + AIRD_MODEL_FILENAME_1);
    }

    public void testControlDetectionOnSessionOpening() throws IOException {
        assertTrue("Session should be open, test data seems to be corrupted", session.isOpen());
        assertEquals("Fragments should not be returned", 1, session.getSemanticResources().size());
        assertEquals("There is two imbricated fragments", 2, ((DAnalysisSessionEObject) session).getControlledResources().size());
    }
}
