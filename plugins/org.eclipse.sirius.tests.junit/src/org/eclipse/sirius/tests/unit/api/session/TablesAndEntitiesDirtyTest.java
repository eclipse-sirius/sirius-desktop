/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPreferenceConstants;

import com.google.common.collect.Iterables;

/**
 * Test if session and/or table diagrams are dirty
 * 
 * @author lchituc
 */
public class TablesAndEntitiesDirtyTest extends SiriusDiagramTestCase implements EcoreModeler {

    private EPackage rootEPackage;

    private EPackage childEPackage;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Restore the default preference values of Sirius (not a customer
        // specific one)
        changePlatformUIPreference(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);

        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        SessionUIManager.INSTANCE.createUISession(session);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());

        rootEPackage = (EPackage) semanticModel;
        childEPackage = rootEPackage.getESubpackages().get(0);
    }

    @Override
    protected void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

    /**
     * Tests if already existing table ("Classes" diagram) is dirty when open, save, reopen the session and the diagram
     * itself
     * 
     * @throws Exception
     */
    public void testOpenTableRepresentationInEditor() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        assertsSessionIsSyncAndReload(session);
        loadModeler(URI.createPlatformPluginURI(MODELER_PATH, true), session.getTransactionalEditingDomain());

        rootEPackage = (EPackage) semanticModel;
        childEPackage = rootEPackage.getESubpackages().get(0);
        DTable table = (DTable) createRepresentation(TABLES_DESC_NAME, childEPackage);
        IEditorPart editor = openRepresentation(table, SessionStatus.DIRTY);
        TestsUtil.synchronizationWithUIThread();

        // Save the session and close the representation
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // Reopen the representation
        openRepresentation(table, SessionStatus.SYNC);

        // Close and reopen the session in saving mode
        assertSessionIsSyncAfterCloseReopen();
        TestsUtil.synchronizationWithUIThread();
        rootEPackage = (EPackage) semanticModel;
        childEPackage = rootEPackage.getESubpackages().get(0);

        // Open the table
        table = getTableWithSemanticElementName(TABLES_DESC_NAME);
        editor = openRepresentation(table, SessionStatus.SYNC);

        // Close the representation
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests if after the creation of a table ("Classes" diagram) is dirty when open, save, reopen the session and the
     * diagram itself.
     * 
     * @throws Exception
     */
    public void testCreateAndOpenTableRepresentationInEditor() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        assertsSessionIsSyncAndReload(session);
        loadModeler(URI.createPlatformPluginURI(MODELER_PATH, true), session.getTransactionalEditingDomain());

        rootEPackage = (EPackage) semanticModel;
        childEPackage = rootEPackage.getESubpackages().get(0);
        String newTableName = "testCreateAndOpenTableRepresentationInEditor";
        DTable table = (DTable) createRepresentation(TABLES_DESC_NAME, newTableName, semanticModel);
        IEditorPart editor = openRepresentation(table, SessionStatus.DIRTY);

        // Close the representation
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // Reopen the representation
        openRepresentation(table, SessionStatus.DIRTY);

        // Close and reopen the session in saving mode
        assertSessionIsSyncAfterCloseReopen();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests if a cross table ("Documentation" diagram) is dirty when open, save, reopen the session and the diagram
     * itself.
     * 
     * @throws Exception
     */
    public void testOpenCrossTableRepresentationInEditor() throws Exception {
        initViewpoint(REVIEW_VIEWPOINT_NAME);
        assertNotNull("Representation \"" + CROSS_TABLES_DESC_NAME + "\" has not been created.", createRepresentation(CROSS_TABLES_DESC_NAME, childEPackage));

        // Waiting for auto-save (see preferences in setup).
        Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertsSessionIsSyncAndReload(session);

        rootEPackage = (EPackage) semanticModel;
        childEPackage = rootEPackage.getESubpackages().get(0);

        DTable table = getTableWithSemanticElementName(CROSS_TABLES_DESC_NAME);
        IEditorPart editor = openRepresentation(table, SessionStatus.DIRTY);

        // Save the session and close the representation
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        // Reopen the representation
        openRepresentation(table, SessionStatus.SYNC);

        // Close and reopen the session in saving mode
        assertSessionIsSyncAfterCloseReopen();
        TestsUtil.synchronizationWithUIThread();

        rootEPackage = (EPackage) semanticModel;
        childEPackage = rootEPackage.getESubpackages().get(0);

        // Open the table
        table = getTableWithSemanticElementName(CROSS_TABLES_DESC_NAME);
        editor = openRepresentation(table, SessionStatus.SYNC);

        // Close the representation
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

    }

    /**
     * Tests if an "Entities" diagram is dirty when open, save, reopen the session and the diagram itself.
     * 
     * @throws Exception
     */
    public void testOpenEntitiesRepresentationInEditor() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        Collection<DRepresentation> diagrams = getRepresentations(ENTITIES_DESC_NAME);
        for (DRepresentation dRepresentation : diagrams) {
            DialectUIManager.INSTANCE.openEditor(session, dRepresentation, new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
        }

        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return SessionStatus.DIRTY.equals(session.getStatus());
            }

            @Override
            public String getFailureMessage() {
                return "The session is not in the expected state: expected:<" + SessionStatus.DIRTY + "> but was:<" + session.getStatus() + ">";
            }
        });
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertsSessionIsSyncAndReload(session);

        DDiagram diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        IEditorPart editor = openRepresentation(diagram, SessionStatus.SYNC);
        TestsUtil.synchronizationWithUIThread();

        // Save the session and close the representation
        session.save(new NullProgressMonitor());
        DialectUIManager.INSTANCE.closeEditor(editor, true);
        TestsUtil.synchronizationWithUIThread();

        // Reopen the diagram
        openRepresentation(diagram, SessionStatus.SYNC);
        TestsUtil.synchronizationWithUIThread();

        // Close and reopen the session in saving mode
        assertSessionIsSyncAfterCloseReopen();
        TestsUtil.synchronizationWithUIThread();

        // Open the diagram
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        editor = openRepresentation(diagram, SessionStatus.SYNC);
        TestsUtil.synchronizationWithUIThread();

        // Close the diagram
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Asserts that a session is not dirty when close and then reopen
     * 
     * @throws Exception
     */
    public void assertSessionIsSyncAfterCloseReopen() throws Exception {
        closeAndReloadSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Opens the editor, assert that the status is like expected.
     * 
     * @param representation
     *            The current representation
     * @param expectedStatus
     *            The expected status
     * @return The opened representation
     */
    public IEditorPart openRepresentation(DRepresentation representation, final SessionStatus expectedStatus) {
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return expectedStatus.equals(session.getStatus());
            }

            @Override
            public String getFailureMessage() {
                return "The session is not in the expected state: expected:<" + expectedStatus + "> but was:<" + session.getStatus() + ">";
            }
        });
        assertNotNull(editor);
        assertEquals(editor.isDirty(), expectedStatus != SessionStatus.SYNC);
        return editor;
    }

    /**
     * Asserts that a session is not dirty after save, close and reopen
     * 
     * @param session
     *            Current session
     * @throws Exception
     */
    public void assertsSessionIsSyncAndReload(Session session) throws Exception {
        assertEquals(SessionStatus.SYNC, session.getStatus());
        closeAndReloadSession();
        TestsUtil.synchronizationWithUIThread();
        assertEquals(SessionStatus.SYNC, session.getStatus());
    }

    /**
     * Find a table by the name of it's semantic element root name..
     * 
     * @param representationDescriptionName
     *            the representation description name
     * @return the found table
     */
    private DTable getTableWithSemanticElementName(String representationDescriptionName) {
        for (DTable table : Iterables.filter(getRepresentations(representationDescriptionName), DTable.class)) {
            if (table.getTarget() == childEPackage) {
                return table;
            }
        }
        fail("No table of type \"" + representationDescriptionName + "\" on semantic element \"" + childEPackage + "\" has been found.");
        return null;
    }
}
