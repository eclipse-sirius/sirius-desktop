/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.session;

import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;

/**
 * Test for session interpreter.
 * 
 * @author mchauvin
 */
public class SessionInterpreterTests extends SiriusDiagramTestCase implements SessionInterpreterBug1411Modeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH );
        initViewpoint(TEST_VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
    }

    public void testCrossReferencerIsInitialized() throws Exception {
        assertEquals(1, getRepresentations(TEST_REPRESENTATION_NAME).size());
    }
}
