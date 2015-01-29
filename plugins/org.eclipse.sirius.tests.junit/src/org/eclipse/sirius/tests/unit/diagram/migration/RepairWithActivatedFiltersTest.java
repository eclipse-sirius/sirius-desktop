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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * This Junit tests:
 * <ul>
 * <li>that the repair action restore all graphicalFilters of type
 * CollapseFilter with a correct width and height</li>
 * <li>that GMF bounds of collapse bordered node are still correct</li>
 * </ul>
 * 
 * @author fbarbin
 * 
 */
public class RepairWithActivatedFiltersTest extends AbstractRepairMigrateTest {

    private static final String AIRD = "My.aird";

    private static final String REPRESENTATION_NAME = "testCollapse";

    private static final String PATH = "/data/unit/repair/testFilterActivated";

    private static final String ODESIGN = "My.odesign";

    private static final String SEMANTIC = "My.ecore";

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + ODESIGN, "/" + TEMPORARY_PROJECT_NAME + "/" + ODESIGN);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + AIRD, "/" + TEMPORARY_PROJECT_NAME + "/" + AIRD);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC, TEMPORARY_PROJECT_NAME + "/" + ODESIGN, TEMPORARY_PROJECT_NAME + "/" + AIRD);
        session.close(new NullProgressMonitor());
        runRepairProcess(AIRD);
    }

    /**
     * Tests that after repair, all collapse filter are recreated correctly with
     * the same expected width and height.
     */
    public void testFiltersAreCorrectlyRestoredAfterRunningRepair() {

        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + AIRD, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        checkGraphicalFilter(dDiagram);
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests that after repair all collapsed node bounds are correct.
     */
    public void testBorderedBoundsAreCorrectAfterRunningRepair() {
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + AIRD, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME).toArray()[0]);
        checkBoundsForGivenNode("op1", new Rectangle(1, 54, 1, 1), dDiagram);
        checkBoundsForGivenNode("op2", new Rectangle(146, 40, 1, 1), dDiagram);
        checkBoundsForGivenNode("op3", new Rectangle(77, 66, 1, 1), dDiagram);
        checkBoundsForGivenNode("att1", new Rectangle(1, 16, 1, 1), dDiagram);
        checkBoundsForGivenNode("att2", new Rectangle(89, 1, 1, 1), dDiagram);
        checkBoundsForGivenNode("att3", new Rectangle(65, 1, 1, 1), dDiagram);

    }

    private void checkBoundsForGivenNode(String name, Rectangle expected, DDiagram dDiagram) {
        List<DDiagramElement> diagramElements = getDiagramElementsFromLabel(dDiagram, name);

        EObjectQuery eObjectQuery = new EObjectQuery(diagramElements.get(0));
        Collection<EObject> objects = eObjectQuery.getInverseReferences(NotationPackage.eINSTANCE.getView_Element());
        Node node = (Node) Iterables.get(objects, 0);
        Bounds bounds = (Bounds) node.getLayoutConstraint();
        assertEquals("Wrong expected GMF Node Bounds for " + node, expected, new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()));
    }

    private void checkGraphicalFilter(DDiagram dDiagram) {
        List<DRepresentationElement> diagramElements = dDiagram.getOwnedRepresentationElements();
        assertEquals("There should be one container root element", 1, diagramElements.size());
        DRepresentationElement diagramElement = diagramElements.get(0);
        assertTrue("wrong expected type", diagramElement instanceof DNodeContainer);
        Predicate<Object> predicate = new Predicate<Object>() {

            public boolean apply(Object input) {
                if (input instanceof DNodeContainer) {
                    String name = ((DNodeContainer) input).getName();
                    if (name.equals("Test") || name.equals("Test2") || name.equals("testPackage")) {
                        return true;
                    }
                }
                return false;
            }
        };
        Iterable<DDiagramElement> iterable = Iterables.filter(((DNodeContainer) diagramElement).getElements(), predicate);
        assertEquals("Wrong expected number of DNode container. There should have 3 container named Test, Test2 and testPackage", 3, Iterables.size(iterable));
        for (DDiagramElement dDiagramElement : iterable) {
            Iterable<CollapseFilter> collapseFilters = Iterables.filter(dDiagramElement.getGraphicalFilters(), CollapseFilter.class);
            assertEquals(dDiagramElement + " should contain one CollapseFilter", 1, Iterables.size(collapseFilters));
            CollapseFilter collapseFilter = Iterables.get(collapseFilters, 0);
            assertEquals("wrong expected height value for " + collapseFilter, -1, collapseFilter.getHeight());
            assertEquals("wrong expected width value for " + collapseFilter, -1, collapseFilter.getWidth());
            if (dDiagramElement.getName().equals("Test")) {
                checkBorderedContainerContent((DNodeContainer) dDiagramElement, 5);
            } else if (dDiagramElement.getName().equals("Test2")) {

                checkBorderedContainerContent((DNodeContainer) dDiagramElement, 1);

            } else if (dDiagramElement.getName().equals("testPackage")) {

                checkThirdContainerContent((DNodeContainer) dDiagramElement);
            }
        }

    }

    private void checkThirdContainerContent(DNodeContainer dNodeContainer) {
        for (DDiagramElement node : dNodeContainer.getOwnedDiagramElements()) {
            Iterable<CollapseFilter> iterableCollapseFilter = Iterables.filter(node.getGraphicalFilters(), CollapseFilter.class);
            assertEquals("There should be only one CollapseFilter in " + node, 1, Iterables.size(iterableCollapseFilter));
            CollapseFilter collapseFilter = Iterables.get(iterableCollapseFilter, 0);
            assertEquals("wrong expected height value for " + collapseFilter, 30, collapseFilter.getHeight());
            assertEquals("wrong expected width value for " + collapseFilter, 30, collapseFilter.getWidth());
        }

    }

    private void checkBorderedContainerContent(DNodeContainer dNodeContainer, int expectedNumberOfBorderedNode) {
        assertEquals("Wrong number of bordered node in " + dNodeContainer, expectedNumberOfBorderedNode, Iterables.size(dNodeContainer.getOwnedBorderedNodes()));
        for (DNode node : dNodeContainer.getOwnedBorderedNodes()) {
            Iterable<IndirectlyCollapseFilter> iterableIndirectlyCollapseFilter = Iterables.filter(node.getGraphicalFilters(), IndirectlyCollapseFilter.class);
            assertEquals("There should be one IndirectlyCollapseFilter in " + node, 1, Iterables.size(iterableIndirectlyCollapseFilter));
            IndirectlyCollapseFilter indirectlyCollapseFilter = Iterables.get(iterableIndirectlyCollapseFilter, 0);
            assertEquals("wrong expected height value for " + indirectlyCollapseFilter, 10, indirectlyCollapseFilter.getHeight());
            assertEquals("wrong expected width value for " + indirectlyCollapseFilter, 10, indirectlyCollapseFilter.getWidth());

            if (node.getTarget() instanceof EOperation) {
                Iterable<GraphicalFilter> onlyCollapseFilter = Iterables.filter(node.getGraphicalFilters(),
                        Predicates.and(Predicates.instanceOf(CollapseFilter.class), Predicates.not(Predicates.instanceOf(IndirectlyCollapseFilter.class))));
                assertEquals("There should be one CollapseFilter in " + node, 1, Iterables.size(onlyCollapseFilter));
                CollapseFilter collapseFilter = (CollapseFilter) Iterables.get(onlyCollapseFilter, 0);
                assertEquals("wrong expected height value for " + collapseFilter, 10, collapseFilter.getHeight());
                assertEquals("wrong expected width value for " + collapseFilter, 10, collapseFilter.getWidth());
            }

            else {
                Iterable<CollapseFilter> iterableCollapseFilter = Iterables.filter(node.getGraphicalFilters(), CollapseFilter.class);
                assertEquals("There should be only one CollapseFilter in " + node, 1, Iterables.size(iterableCollapseFilter));
            }
        }
    }
}
