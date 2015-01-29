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
package org.eclipse.sirius.tests.unit.diagram.filter;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.collect.Iterables;

/**
 * Tests that the activation and deactivation of a collapse filter restore the
 * correct expanded GMF Bounds.
 * 
 * @author fbarbin
 * 
 */
public class CollapseExpandFilterTest extends SiriusDiagramTestCase {
    private static final String AIRD = "testCollaspeExpand.aird";

    private static final String REPRESENTATION_NAME = "testCollapse";

    private static final String PATH = "/data/unit/filter/collapseExpand";

    private static final String ODESIGN = "testCollaspeExpand.odesign";

    private static final String SEMANTIC = "testCollaspeExpand.ecore";

    private static final String FILTER_NAME = "collapseContainer";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + ODESIGN, "/" + TEMPORARY_PROJECT_NAME + "/" + ODESIGN);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + AIRD, "/" + TEMPORARY_PROJECT_NAME + "/" + AIRD);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC, TEMPORARY_PROJECT_NAME + "/" + ODESIGN, TEMPORARY_PROJECT_NAME + "/" + AIRD);
    }

    /**
     * Tests that after collapse/expand, the GMF node bounds are still corrects.
     * <ul>
     * <li>Open the diagram</li>
     * <li>Activate collapse filter</li>
     * <li>Check GMF bounds after collapse</li>
     * <li>Deactivate collapse filter</li>
     * <li>Check GMF bounds after expand</li>
     * </ul>
     */
    public void testBorderedNodeBoundsAfterHavingCollapseAndExpandThem() {
        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME).toArray()[0]);
        DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        activateFilter(dDiagram, "collapseContainer");
        TestsUtil.synchronizationWithUIThread();

        checkBoundsForGivenNode("op1", new Rectangle(146, 32, 1, 1), dDiagram);
        checkBoundsForGivenNode("op2", new Rectangle(1, 45, 1, 1), dDiagram);
        checkBoundsForGivenNode("op3", new Rectangle(71, 66, 1, 1), dDiagram);
        checkBoundsForGivenNode("att1", new Rectangle(96, 66, 1, 1), dDiagram);
        checkBoundsForGivenNode("att2", new Rectangle(65, 1, 1, 1), dDiagram);
        checkBoundsForGivenNode("att3", new Rectangle(77, 1, 1, 1), dDiagram);

        deactivateFilter(dDiagram, FILTER_NAME);
        TestsUtil.synchronizationWithUIThread();

        checkBoundsForGivenNode("op1", new Rectangle(140, 28, 10, 10), dDiagram);
        checkBoundsForGivenNode("op2", new Rectangle(-2, 41, 10, 10), dDiagram);
        checkBoundsForGivenNode("op3", new Rectangle(61, 60, 22, 24), dDiagram);
        // TODO FBA: activate this check when VP-3886 will be fixed
        // checkBoundsForGivenNode("op4", new Rectangle(0, 60, 10, 10),
        // dDiagram);

        checkBoundsForGivenNode("att1", new Rectangle(73, 60, 48, 10), dDiagram);
        checkBoundsForGivenNode("att2", new Rectangle(61, -2, 10, 10), dDiagram);
        checkBoundsForGivenNode("att3", new Rectangle(73, -2, 10, 10), dDiagram);

        checkBoundsForGivenNode("att4", new Rectangle(138, 60, 10, 10), dDiagram);

    }

    private void checkBoundsForGivenNode(String name, Rectangle expected, DDiagram dDiagram) {
        List<DDiagramElement> diagramElements = getDiagramElementsFromLabel(dDiagram, name);

        EObjectQuery eObjectQuery = new EObjectQuery(diagramElements.get(0));
        Collection<EObject> objects = eObjectQuery.getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
        Node node = (Node) Iterables.get(objects, 0);
        Bounds bounds = (Bounds) node.getLayoutConstraint();
        assertEquals("Wrong expected GMF Node Bounds for " + node, expected, new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()));
    }
}
