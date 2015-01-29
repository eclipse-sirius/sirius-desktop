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
package org.eclipse.sirius.tests.unit.diagram.action;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * Test bordered node deletion on list container.
 * 
 * @author ymortier
 */
public class DeleteBorderedNodeFromModelTest extends SiriusDiagramTestCase {

    private static final String PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/semantic/VP-3380/";//$NON-NLS-1$ $NON-NLS-2$

    private static final String MODELER_PATH = PATH + "vp-3380.odesign"; //$NON-NLS-1$ $NON-NLS-2$

    private static final String SEMANTIC_MODEL_PATH = PATH + "vp-3380.ecore"; //$NON-NLS-1$ $NON-NLS-2$

    private static final String AIRD_MODEL_PATH = PATH + "vp-3380.aird"; //$NON-NLS-1$ $NON-NLS-2$

    private static final String DESC_NAME = "vp-3380";//$NON-NLS-1$

    private DDiagram diagram;

    private DDiagramEditor editor;

    private EPackage ePackage;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, AIRD_MODEL_PATH);

        ePackage = (EPackage) semanticModel;
        diagram = (DDiagram) getRepresentations(DESC_NAME, ePackage).iterator().next();
        editor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    public void testBorderedNodeDeletionOnListContainer() {
        DNode borderedNodeOp1 = this.getDiagramElementsFromLabel(diagram, "op1", DNode.class).iterator().next();
        IGraphicalEditPart graphicalEditPart = getEditPart(borderedNodeOp1, editor);
        delete(graphicalEditPart);
        DNodeList container = this.getDiagramElementsFromLabel(diagram, "VP-3380", DNodeList.class).iterator().next();
        assertEquals(container.getOwnedBorderedNodes().size(), 1);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
