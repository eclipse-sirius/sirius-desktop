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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test auto refresh on conditional style.
 * 
 * @author fbarbin
 */
public class ConditionalStyleRefreshTest extends SiriusDiagramTestCase {

    private static final int EXPECTED_ELEMENTS = 3;

    private static final String EXPECTED_LABEL = "condStyler2";

    private static final String SEMANTIC_RESOURCE_NAME = "VP978.ecore";

    private static final String SEMANTIC_MODEL_PATH = "/data/unit/refresh/VP978/" + SEMANTIC_RESOURCE_NAME;

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/VP978/VP978.odesign";

    private static final String VIEWPOINT_NAME = "EdgeLabelRefreshPb";

    private static final String REPRESENTATION_NAME = "EdgeLabelRefreshPb";

    private DDiagram diagram;

    private IEditorPart editor;

    public void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SEMANTIC_MODEL_PATH, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);

        diagram = (DDiagram) createRepresentation(REPRESENTATION_NAME, semanticModel);
    }

    /**
     * ticket VP-978. Test conditional label refresh after changed reference
     * name from "r1" to "r2".
     */
    public void testLabelRefresh() {
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(EXPECTED_ELEMENTS, diagram.getOwnedDiagramElements().size());

        DNode dNode = (DNode) diagram.getOwnedDiagramElements().get(0);
        changeReferenceName(dNode);
        TestsUtil.synchronizationWithUIThread();

        DNode dNode2 = (DNode) diagram.getOwnedDiagramElements().get(1);
        DEdge dEdge = (DEdge) diagram.getOwnedDiagramElements().get(2);

        assertEquals(EXPECTED_LABEL, dNode.getName());
        assertEquals(EXPECTED_LABEL, dNode2.getName());
        assertEquals(EXPECTED_LABEL, dEdge.getName());
    }

    /**
     * Test conditionalStyleDescription referencing container variable.
     * 
     * See VP-3604.
     */
    public void testContainerVariableReference() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command changeRootEPackageNameCmd = SetCommand.create(domain, semanticModel, EcorePackage.Literals.ENAMED_ELEMENT__NAME, "plop");
        domain.getCommandStack().execute(changeRootEPackageNameCmd);

        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals(EXPECTED_ELEMENTS, diagram.getOwnedDiagramElements().size());

        DNode dNode1 = (DNode) diagram.getOwnedDiagramElements().get(0);
        DNode dNode2 = (DNode) diagram.getOwnedDiagramElements().get(1);
        DEdge dEdge = (DEdge) diagram.getOwnedDiagramElements().get(2);

        assertTrue("the first conditionalStyle should apply a Lozenge style", dNode1.getStyle() instanceof Lozenge);
        assertTrue("the first conditionalStyle should apply a Lozenge style", dNode2.getStyle() instanceof Lozenge);
        assertTrue("the first conditionalStyle should apply a BracketEdgeStyle", dEdge.getStyle() instanceof BracketEdgeStyle);
    }

    /**
     * Change reference name to "r2" in dNode semantic target.
     * 
     * @param dNode
     */
    private void changeReferenceName(DNode dNode) {
        EClass eClass = (EClass) dNode.getTarget();
        final EReference eReference = (EReference) eClass.getEStructuralFeature("r1");
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command changeReferenceNameCmd = SetCommand.create(domain, eReference, EcorePackage.Literals.ENAMED_ELEMENT__NAME, "r2");
        domain.getCommandStack().execute(changeReferenceNameCmd);
    }

    public void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }
}
