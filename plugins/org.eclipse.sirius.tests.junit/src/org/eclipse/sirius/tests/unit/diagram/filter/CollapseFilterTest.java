/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.filter;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Tests to check the behavior of graphical collapse filters computation. See
 * VP-3833.
 * 
 * @author jdupont
 * 
 */
public class CollapseFilterTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/collapsefilter/My.ecore";

    private static final String SESSION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/collapsefilter/My.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/collapsefilter/collapse.odesign";

    private static final String REPRESENTATION_DESC_NAME = "Node Class and Package Diagram with Ports";

    private static final String REPRESENTATION_DESC_NAME_CONTAINER = "Container Class and Package Diagram with Ports";

    private static final String COLLAPSE_PROPERTY_STARTING_WITH_A = "FilterPropertyStartingWithA";

    private static final String COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A = "FilterClassAndPackageStartingWithA";

    private static final String COLLAPSE_CLASS_STARTING_WITH_A = "FilterClassStartingWithA";

    private static final String PROP2 = "prop2";

    private static final String APROP4 = "aProp4";

    private DDiagram diagram;

    private DiagramEditor editor;

    private DNode prop2;

    private DNode aProp4;

    private LayoutConstraint gmfProp2;

    private LayoutConstraint gmfAProp4;

    private Rectangle rectProp2;

    private Rectangle rectAProp4;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        TestsUtil.synchronizationWithUIThread();
        prop2 = (DNode) getDiagramElementsFromLabel(diagram, PROP2).get(0);
        aProp4 = (DNode) getDiagramElementsFromLabel(diagram, APROP4).get(0);
        gmfProp2 = getGmfNode(prop2).getLayoutConstraint();
        gmfAProp4 = getGmfNode(aProp4).getLayoutConstraint();
        rectProp2 = new Rectangle(getEditPart(prop2).getFigure().getBounds());
        rectAProp4 = new Rectangle(getEditPart(aProp4).getFigure().getBounds());
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        prop2 = null;
        aProp4 = null;
        gmfProp2 = null;
        gmfAProp4 = null;
        rectProp2 = null;
        rectAProp4 = null;
        super.tearDown();
    }

    /**
     * Collapse Bordered Node on Node that starting with A and check that
     * collapsed elements are OK.
     */
    public void testCollapseBorderedNodeOnNode() {
        checkNoCollapseApplicationForDiagramElements(2, 7);
        activateFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 7, getFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A));
    }

    /**
     * Collapse Node that starting with A and check that collapsed elements are
     * OK. Check That Bordered Node of collapsed Node is also collapsed.
     */
    public void testCollapseNode() {
        checkNoCollapseApplicationForDiagramElements(2, 7);
        activateFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A));
        checkIndirectlyCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 7, getFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A));
    }

    /**
     * Collapse Node and Bordered Node that starting with A and check that
     * collapsed elements are OK.
     */
    public void testCollapseNodeAndBorderedNode() {
        checkNoCollapseApplicationForDiagramElements(2, 7);
        activateFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A);
        activateFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 7, getFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A));
        checkIndirectlyCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 7, getFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A));
    }

    /**
     * Collapse and expand Node that starting with A and check that collapsed
     * elements are OK. Check That Bordered Node of collapsed Node is also
     * collapsed. Check that the size of Bordered Node is always the same after
     * expand.
     */
    public void testExpandNode() {
        testCollapseNode();
        deactivateFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 7);
        checkFigureHaveSameBoundsAfterExpand();
    }

    /**
     * Collapse and expand Bordered Node on Node that starting with A and check
     * that collapsed elements are OK. Check that the size of Bordered Node is
     * always the same after expand.
     */
    public void testExpandBorderedNode() {
        testCollapseBorderedNodeOnNode();
        deactivateFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 7);
        checkFigureHaveSameBoundsAfterExpand();
    }

    /**
     * Collapse and expand Node and Bordered Node that starting with A and check
     * that collapsed elements are OK. Check that the size of Bordered Node is
     * always the same after expand.
     */
    public void testExpandAllElementsCollapsed() {
        testCollapseBorderedNodeOnNode();
        activateFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A));
        checkIndirectlyCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 7, getFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A));
        deactivateFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A);
        deactivateFilter(diagram, COLLAPSE_CLASS_AND_PACKAGE_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 7);
        checkFigureHaveSameBoundsAfterExpand();

    }

    /**
     * Collapse Bordered Node on Container and Node that starting with A and
     * check that collapsed elements are OK.
     */
    public void testCollapseBorderedNodeHierarchy() {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME_CONTAINER).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 21);
        activateFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 21, getFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A));
    }

    /**
     * Collapse Container that starting with A and check that the children on
     * first level are collapsed and not the others.
     */
    public void testCollapseNodeHierarchy() {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME_CONTAINER).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 21);
        activateFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A));
        checkIndirectlyCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 21, getFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A));
    }

    /**
     * Collapse Container, node and bordered node starting with A. Check that
     * it's the good elements that were collapsed.
     */
    public void testCollapseBorderedNodeAndNodeHierarchy() {
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME_CONTAINER).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
        TestsUtil.synchronizationWithUIThread();
        testCollapseBorderedNodeHierarchy();
        activateFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A));
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 21, getFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A));
        checkIndirectlyCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 21, getFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A));

    }

    /**
     * Collapse Container starting with A and expand them. Check that the size
     * of Bordered Node is always the same.
     */
    public void testExpandNodeHierarchy() {
        testCollapseNodeHierarchy();
        deactivateFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 21);
    }

    /**
     * Collapse Bordered Node on Container and Node starting with A and expand
     * them. Check that the size of Bordered Node is always the same after
     * expand.
     */
    public void testExpandBorderedNodeHierarchy() {
        testCollapseBorderedNodeHierarchy();
        deactivateFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 21);
    }

    /**
     * Collapse Container, node and bordered node starting with A starting with
     * A and expand them. Check that the size of Bordered Node is always the
     * same.
     */
    public void testExpandAllElementsCollapsedHierarchy() {
        testCollapseBorderedNodeHierarchy();
        activateFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), 2, getFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A));
        checkIndirectlyCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), 21, getFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A));
        deactivateFilter(diagram, COLLAPSE_PROPERTY_STARTING_WITH_A);
        deactivateFilter(diagram, COLLAPSE_CLASS_STARTING_WITH_A);
        TestsUtil.synchronizationWithUIThread();
        checkNoCollapseApplicationForDiagramElements(2, 21);

    }

    /**
     * Checks that the elements correspond to type are correctly collapsed.
     * Checks that the GMF bounds correspond to Draw2d bounds. Checks that for
     * the bordered node the size (GMF and Draw2D) for height and weight equals
     * to 1.
     * 
     * @param type
     *            type of element to analysis
     * @param expectedNumberOfElements
     *            number of elements correspond to the type
     * @param filters
     *            Filter applied
     */
    private void checkCollapseApplication(EClass type, int expectedNumberOfElements, FilterDescription... filters) {
        List<DDiagramElement> elementsFromType = getDiagramElements(type);

        assertEquals(expectedNumberOfElements, elementsFromType.size());

        for (DDiagramElement element : elementsFromType) {
            if (element.getName().startsWith("a")) {
                // One CollapseApplication per DDiagramElement
                assertEquals(filters.length == 0 ? 0 : 1,
                        Iterables.size(Iterables.filter(Iterables.filter(element.getGraphicalFilters(), CollapseFilter.class), Predicates.not(Predicates.instanceOf(IndirectlyCollapseFilter.class)))));

                DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);

                assertEquals(filters.length != 0, elementQuery.isCollapsed());

                Node gmfNode = getGmfNode(element);
                Size sizeGmfNode = (Size) gmfNode.getLayoutConstraint();
                IFigure figure = getEditPart(element).getFigure();

                if (filters.length == 0) {
                    if (!(element instanceof DDiagramElementContainer)) {
                        // Check that the GMF bounds and figure bounds are
                        // correct
                        assertEquals("The figure Draw2D should be have the same size", figure.getBounds().height, sizeGmfNode.getHeight());
                        assertEquals("The figure Draw2D should be have the same size", figure.getBounds().width, sizeGmfNode.getWidth());
                    }
                    return;
                }

                for (FilterDescription filter : filters) {
                    assertTrue(diagram.getActivatedFilters().contains(filter));
                }

                // Check that the size of collapse element is 1*1 if it's a
                // borderedNode
                NodeQuery nodeQuery = new NodeQuery(gmfNode);
                if (nodeQuery.isBorderedNode()) {
                    assertEquals("The height of borderedNode collapsed should be 1", 1, figure.getBounds().height);
                    assertEquals("The height of borderedNode collapsed should be 1", 1, sizeGmfNode.getHeight());
                    assertEquals("The width of borderedNode collapsed should be 1", 1, figure.getBounds().width);
                    assertEquals("The width of borderedNode collapsed should be 1", 1, sizeGmfNode.getWidth());
                }

            }
        }
    }

    /**
     * Checks that the child's elements correspond to type are indirectly
     * collapsed. Checks that for the bordered node the size (GMF and Draw2D)
     * for height and weight equals to 1. Checks that the indirectly collapse is
     * only applied on children and not after
     * 
     * @param type
     *            type of element to analysis
     * @param expectedNumberOfElements
     *            number of elements correspond to the type
     * @param filters
     *            Filter applied
     */
    private void checkIndirectlyCollapseApplication(EClass type, int expectedNumberOfElements, FilterDescription... filters) {
        List<DDiagramElement> elementsFromType = getDiagramElements(type);

        assertEquals(expectedNumberOfElements, elementsFromType.size());

        for (DDiagramElement element : elementsFromType) {
            // One CollapseApplication per DDiagramElement
            if (((DDiagramElement) element.eContainer()).getName().startsWith("a")) {
                assertEquals(filters.length == 0 ? 0 : 1, Iterables.size(Iterables.filter(element.getGraphicalFilters(), IndirectlyCollapseFilter.class)));
                DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);

                assertEquals(filters.length != 0, elementQuery.isIndirectlyCollapsed());

                for (FilterDescription filter : filters) {
                    assertTrue(diagram.getActivatedFilters().contains(filter));
                }

                // Check that the size of collapse element is 1*1 if it's a
                // borderedNode
                Node gmfNode = getGmfNode(element);
                NodeQuery nodeQuery = new NodeQuery(gmfNode);
                Size sizeGmfNode = (Size) gmfNode.getLayoutConstraint();
                if (nodeQuery.isBorderedNode()) {
                    assertEquals("The height of borderedNode collapsed should be 1", 1, getEditPart(element).getFigure().getBounds().height);
                    assertEquals("The height of borderedNode collapsed should be 1", 1, sizeGmfNode.getHeight());
                    assertEquals("The width of borderedNode collapsed should be 1", 1, getEditPart(element).getFigure().getBounds().width);
                    assertEquals("The height of borderedNode collapsed should be 1", 1, sizeGmfNode.getWidth());
                }
                if (filters.length == 0)
                    return;
            }
            // Check that the indirectly collapse is only applied on children
            // and
            // not after
            if (element.eContainer().eContainer() instanceof DDiagramElement) {
                if ("aClass".equals(((DDiagramElement) element.eContainer().eContainer()).getName())) {
                    assertEquals(0, Iterables.size(Iterables.filter(element.getGraphicalFilters(), IndirectlyCollapseFilter.class)));
                }
            }
        }
    }

    /**
     * Checks that there is no elements collapsed on diagram.
     * 
     * @param nbECLass
     *            number of eEClass on diagram
     * @param nbAttr
     *            number of EAttribute on diagram
     */
    private void checkNoCollapseApplicationForDiagramElements(int nbECLass, int nbAttr) {
        checkCollapseApplication(EcorePackage.eINSTANCE.getEClass(), nbECLass);
        checkCollapseApplication(EcorePackage.eINSTANCE.getEAttribute(), nbAttr);
    }

    /**
     * Checks that the figures have the same bounds after expand. Check GMF and
     * Draw2D bounds.
     */
    private void checkFigureHaveSameBoundsAfterExpand() {
        prop2 = (DNode) getDiagramElementsFromLabel(diagram, PROP2).get(0);
        aProp4 = (DNode) getDiagramElementsFromLabel(diagram, APROP4).get(0);

        int gmfProp2X = ((Bounds) gmfProp2).getX();
        int gmfProp2Y = ((Bounds) gmfProp2).getY();
        int gmfProp2Height = ((Bounds) gmfProp2).getHeight();
        int gmfProp2Width = ((Bounds) gmfProp2).getWidth();

        int gmfAProp4X = ((Bounds) gmfAProp4).getX();
        int gmfAProp4Y = ((Bounds) gmfAProp4).getY();
        int gmfAProp4Height = ((Bounds) gmfAProp4).getHeight();
        int gmfAProp4Width = ((Bounds) gmfAProp4).getWidth();

        // Check Gmf bounds are always the same
        assertEquals("After Collapse, the X position should be same for 'prop2'", gmfProp2X, ((Location) getGmfNode(prop2).getLayoutConstraint()).getX());
        assertEquals("After Collapse, the Y position should be same for 'prop2'", gmfProp2Y, ((Location) getGmfNode(prop2).getLayoutConstraint()).getY());
        assertEquals("After Collapse, the Height should be same for 'prop2'", gmfProp2Height, ((Size) getGmfNode(prop2).getLayoutConstraint()).getHeight());
        assertEquals("After Collapse, the Width should be same for 'prop2'", gmfProp2Width, ((Size) getGmfNode(prop2).getLayoutConstraint()).getWidth());

        assertEquals("After Collapse, the X position should be same for 'aProp4'", gmfAProp4X, ((Location) getGmfNode(aProp4).getLayoutConstraint()).getX());
        assertEquals("After Collapse, the Y position should be same for 'aProp4'", gmfAProp4Y, ((Location) getGmfNode(aProp4).getLayoutConstraint()).getY());
        assertEquals("After Collapse, the Height should be same for 'aProp4'", gmfAProp4Height, ((Size) getGmfNode(aProp4).getLayoutConstraint()).getHeight());
        assertEquals("After Collapse, the Width should be same for 'aProp4'", gmfAProp4Width, ((Size) getGmfNode(aProp4).getLayoutConstraint()).getWidth());

        // Check Draw2D bounds are always the same
        assertEquals("After Collapse, the bounds(Draw2D) should be same for 'prop2'", rectProp2, getEditPart(prop2).getFigure().getBounds());
        assertEquals("After Collapse, the bounds(Draw2D) should be same for 'aProp4'", rectAProp4, getEditPart(aProp4).getFigure().getBounds());
    }

    private List<DDiagramElement> getDiagramElements(final EClass type) {
        Predicate<DDiagramElement> expectedType = new Predicate<DDiagramElement>() {
            @Override
            public boolean apply(DDiagramElement input) {
                return type.isInstance(input.getTarget());
            }
        };
        return Lists.newArrayList(Iterables.filter(diagram.getDiagramElements(), expectedType));
    }

}
