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
package org.eclipse.sirius.tests.unit.api.diagramintegrity;

import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.unit.common.DocbookTestCase;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;

/**
 * Diagram integrity tests base class.
 * 
 * @author mchauvin
 */
public class DiagramIntegrityTestCase extends DocbookTestCase {

    @Override
    protected void setUp() throws Exception {

        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DOCKBOOK_VIEWPOINT_NAME, session, false);
        sessionModel = session.getSessionResource().getContents().get(0);

        INTERPRETER = session.getInterpreter();
        InterpreterRegistry.prepareImportsFromSession(INTERPRETER, session);
    }

}
