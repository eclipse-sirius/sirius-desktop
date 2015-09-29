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
package org.eclipse.sirius.tests.unit.api.startup;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionHelper;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

public class StartupRepresentationsRegardingSelectedVpTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/table/unit/vp-2734/";

    private static final String SEMANTIC_MODEL_FILENAME = "vp-2734.ecore";

    private static final String MODELER_FILENAME = "vp-2734.odesign";

    private static final String SESSION_MODEL_FILENAME = "vp-2734.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_MODEL_FILENAME, MODELER_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
        final IEditingSession uiSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
        uiSession.open();
    }

    public void testOpenOnlyStartupsOfSelectedSiriuss() throws Exception {
        // Open startup representation
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check opening the session opens the right representation and not the
        // other (-> representation of selected viewpoints should be opened)
        assertEquals("Only the startup table editor should be opened (check editors opened).", 1, getOpenedEditors().size());
        IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        assertEquals("Only the startup table editor should be opened (check editors associated with editing session).", 1, uiSession.getEditors().size());
        assertTrue("The table should be opened", uiSession.getEditors().iterator().next().getRepresentation() instanceof DTable);

        // Change viewpoint activation VP-2734 > VP-2891
        uiSession.closeEditors(false, uiSession.getEditors());
        TestsUtil.emptyEventsFromUIThread();
        deactivateViewpoint("VP-2734");
        activateViewpoint("VP-2891");

        // Test O editor open.
        TestsUtil.synchronizationWithUIThread();
        assertEquals("Only the startup table editor should be opened (check editors opened).", 0, getOpenedEditors().size());
        assertEquals("Only the startup table editor should be opened (check editors associated with editing session).", 0, uiSession.getEditors().size());

        // Open startup representations
        SessionHelper.openStartupRepresentations(session, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check opening the session opens the right representation and not the
        // other (-> representation of selected viewpoints should be opened)
        assertEquals("Only the startup diagram editor should be opened (check editors opened).", 1, getOpenedEditors().size());
        assertEquals("Only the startup diagram editor should be opened (check editors associated with editing session).", 1, uiSession.getEditors().size());
        assertTrue("The diagram should be opened", uiSession.getEditors().iterator().next().getRepresentation() instanceof DDiagram);
    }

    private Collection<IEditorReference> getOpenedEditors() {
        final IEditorReference[] refs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        if (refs == null) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(refs);
        }
    }
}
