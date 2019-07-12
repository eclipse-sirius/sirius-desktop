/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.refresh;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;

/**
 * Check that the diagram refresh algorithm produces a deterministic result.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class RefreshStabilityTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/stability/";

    private static final String SEMANTIC_MODEL_FILENAME = "My.ecore";

    private static final String MODELER_FILENAME = "My.odesign";

    private static final String AIRD_MODEL_FILENAME = "representations.aird";

    /**
     * Test that the refresh algorithm will process the semantic elements in a deterministic order when the semantic
     * candidate expression is empty.
     * 
     * @throws Exception
     */
    public void testEmptySemanticCandidatesExpression() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, AIRD_MODEL_FILENAME, SEMANTIC_MODEL_FILENAME, MODELER_FILENAME);

        // If there was a bug it could be random because of the usage of Set instead of List. So we launch it 10 times.
        for (int i = 0; i < 10; i++) {
            doTestEmptySemanticCandidatesExpression();
        }
    }

    private void doTestEmptySemanticCandidatesExpression() throws CoreException {
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + AIRD_MODEL_FILENAME, true);
        session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DRepresentation dRepresentation = getRepresentationsByName("new MyDiagram").get(0);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation, new NullProgressMonitor());

        assertEquals("Bad session status", SessionStatus.SYNC, session.getStatus());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        closeSession(session);
        TestsUtil.synchronizationWithUIThread();
    }

}
