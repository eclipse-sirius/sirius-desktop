/*******************************************************************************
 * Copyright (c) 2020, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.action;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.CopyFormatAction;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * This class tests activation of CopyFormat action while changing permission authority on diagram.
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 *
 */
public class CopyFormatActionTests extends SiriusDiagramTestCase implements EcoreModeler {

    private DiagramDocumentEditor diagramEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
    }

    /**
     * Test that CopyFormat action is enable without and with Read Only Permission Authority on diagram.
     */
    public void testActionCopyFormat() {
        final EPackage root = (EPackage) semanticModel;
        final DDiagram rootdiagram = (DDiagram) getRepresentations(ENTITIES_DESC_NAME, root).iterator().next();

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, rootdiagram, new NullProgressMonitor());
        assertTrue("Editor is not of the right type.", editor instanceof DiagramDocumentEditor);
        diagramEditor = (DiagramDocumentEditor) editor;
        diagramEditor.setFocus();

        final IWorkbenchPage page = EclipseUIUtil.getActivePage();
        assertNotNull("We should have an active page.", page);

        CopyFormatAction copyFormatAction = new CopyFormatAction(page);
        copyFormatAction.init();
        copyFormatAction.refresh();
        assertTrue("CopyFormatAction should be enabled when the diagram is selected.", copyFormatAction.isEnabled());
        assertEquals("The tooltip should correspond to the diagram.", Messages.CopyFormatAction_toolTipText_diagram, copyFormatAction.getToolTipText());

        // select one element in diagram
        DEdge dEdge = rootdiagram.getEdges().get(0);
        diagramEditor.getDiagramGraphicalViewer().select(getEditPart(dEdge));

        TestsUtil.synchronizationWithUIThread();

        copyFormatAction = new CopyFormatAction(page);
        copyFormatAction.init();
        copyFormatAction.refresh();
        assertTrue("CopyFormatAction should be enabled when a diagram element is selected.", copyFormatAction.isEnabled());
        assertEquals("The tooltip should correspond to the diagram elements.", Messages.CopyFormatAction_toolTipText_diagramElements, copyFormatAction.getToolTipText());

        ReadOnlyPermissionAuthority permissionAuthority = (ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(rootdiagram);
        permissionAuthority.activate();

        copyFormatAction.refresh();
        assertTrue("CopyFormatAction should be enabled when a diagram element is selected even with read only permission.", copyFormatAction.isEnabled());
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
