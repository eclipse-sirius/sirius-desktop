/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.editors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.EditorNameAdapter;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Tests on editing domain based on entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class EditorNameAdapterTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();

        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME).toArray()[0];
        TestsUtil.synchronizationWithUIThread();

    }

    public void testRegister() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        EditorNameAdapter adapter = new EditorNameAdapter(SessionUIManager.INSTANCE.getUISession(session));
        adapter.registerEditor((DialectEditor) editor);
        assertTrue(diagram.eAdapters().contains(adapter));
        adapter.unregisterEditor((DialectEditor) editor);
        assertFalse(diagram.eAdapters().contains(adapter));
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testRename() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final String originalTitle = editor.getTitle();

        executeCommand(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                new DRepresentationQuery(diagram).getRepresentationDescriptor().setName("new name");
            }
        });

        TestsUtil.synchronizationWithUIThread();

        final String newTitle = editor.getTitle();
        assertFalse(originalTitle.equals(newTitle));
        assertTrue(newTitle.contains("new name"));
        assertTrue(editor.isDirty());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        diagram = null;
    }

}
