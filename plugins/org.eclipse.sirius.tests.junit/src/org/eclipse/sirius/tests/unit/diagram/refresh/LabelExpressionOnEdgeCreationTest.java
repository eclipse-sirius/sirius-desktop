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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.junit.Assert;

/**
 * Test for VP-1959 : test that label computation expression evaluation have
 * $diagram variable access in REFRESH_AUTO mode at false with Edge when the
 * EdgeMapping is between two BorderedNodeMapping & the Edge creation tool is
 * between the two BorderedNodeMapping parent (NodeMapping) using the extra
 * mapping.
 * 
 * @author lchituc
 */
public class LabelExpressionOnEdgeCreationTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/VP-1958/";

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-1958.ecore";

    private static final String SESSION_RESOURCE_FILENAME = "VP-1958.aird";

    private static final String MODELER_RESOURCE_FILENAME = "VP-1958.odesign";

    private static final String REPRESENTATION_DESC_ID = "VP_1958_Diagram";

    private DDiagram diagram;

    private DiagramEditor editor;

    private EdgeTarget sourceEdgeTarget;

    private EdgeTarget targetEdgeTarget;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_FILENAME, SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_RESOURCE_FILENAME, SiriusTestsPlugin.PLUGIN_ID + PATH
                + SESSION_RESOURCE_FILENAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_ID).toArray()[0];
        Assert.assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        Assert.assertNotNull(editor);

        EPackage rootPkg = (EPackage) semanticModel;
        EPackage ePackage1 = rootPkg.getESubpackages().get(0);
        EPackage ePackage2 = rootPkg.getESubpackages().get(1);

        sourceEdgeTarget = (EdgeTarget) getFirstDiagramElement(diagram, ePackage1);
        targetEdgeTarget = (EdgeTarget) getFirstDiagramElement(diagram, ePackage2);

    }

    /**
     * Test on a example with two EPackages with DNode, with a EdgeCreationTool
     * which create two EClass as BorderedNode & a EReference between these two
     * EClass (BorderedNodes) in REFRESH_AUTO mode at false & check if the label
     * computation expression referencing the $diagram variable is correctly
     * evaluated.
     */
    public void testLabelExpressiononEdgeCreation_P1_P2() {

        applyEdgeCreationTool("EdgeCreationTool", diagram, sourceEdgeTarget, targetEdgeTarget);

        Assert.assertEquals(1, diagram.getEdges().size());

        DEdge dEdge = diagram.getEdges().get(0);

        Assert.assertNotSame(IInterpreterMessages.DEFAULT_NAME_ON_FACTORY_EXCEPTION, dEdge.getName());
        Assert.assertEquals("The label computation expression doesn't seems correctly evaluated, because of reference to the $diagram variable?", "ref Test Case", dEdge.getName());

    }

    @Override
    protected void tearDown() throws Exception {

        diagram = null;
        editor = null;
        sourceEdgeTarget = null;
        targetEdgeTarget = null;

        super.tearDown();
    }

}
