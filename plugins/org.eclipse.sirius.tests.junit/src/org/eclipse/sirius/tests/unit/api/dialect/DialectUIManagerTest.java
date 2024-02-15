/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.dialect;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.impl.DRepresentationImpl;
import org.eclipse.ui.IEditorPart;

/**
 * Tests for for the dialect UI manager.
 * 
 * @author mchauvin
 */
public class DialectUIManagerTest extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
    }

    public void testCanHandleRepresentation() {
        final boolean canHandle = DialectUIManager.INSTANCE.canHandle(diagram);
        assertTrue(canHandle);
        final DRepresentation newRepresentation = new DRepresentationImpl() {};
        final boolean canHandleNR = DialectUIManager.INSTANCE.canHandle(newRepresentation);
        assertFalse(canHandleNR);
    }

    public void testOpenEditor() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testCloseProjectWithOpenedEditor() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);

        try {
            // Close the session by closing the project
            ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).close(new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
        } catch (CoreException e) {
            fail("Unable to close the modeling project");
        }
        if (platformProblemsListener.doesAnErrorOccurs()) {
            fail("An error occured while closing the sesssion");
        }
    }

    public void testManagement() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue(DialectUIManager.INSTANCE.isRepresentationManagedByEditor(diagram, editor));
        assertFalse(DialectUIManager.INSTANCE.isRepresentationManagedByEditor(DiagramFactory.eINSTANCE.createDDiagram(), editor));
        assertTrue(DialectUIManager.INSTANCE.isRepresentationDescriptionManagedByEditor(diagram.getDescription(), editor));
        assertFalse(DialectUIManager.INSTANCE.isRepresentationDescriptionManagedByEditor(DescriptionFactory.eINSTANCE.createDiagramDescription(), editor));
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
