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
package org.eclipse.sirius.tests.unit.common.migration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.sample.migration.design.LayoutHelper;
import org.eclipse.sirius.tests.sample.migration.design.MigrationModeler;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IEditorPart;
import org.junit.After;

/**
 * This class contains services used by the migration tests.
 */
public abstract class AbstractMigrationTestCase extends SiriusDiagramTestCase {

    protected static final String MODELER_PATH = "/org.eclipse.sirius.tests.sample.migration.design/description/migration.odesign";

    protected DDiagram currentdRepresentation;

    /**
     * Open an editor on the specified <code>diagramName</code> in the
     * <code>diagram</code> viewpoint.
     * 
     * @param diagramName
     *            the diagram to open
     */
    protected void openEditorOnDiagram(String diagramName) {
        openEditorOnDiagram(diagramName, MigrationModeler.DIAGRAM_VIEWPOINT_NAME);
    }

    /**
     * Open an editor on the specified <code>diagramName</code> in the
     * <code>diagram</code> viewpoint.
     * 
     * @param diagramName
     *            the diagram to open
     * 
     * @param viewpointName
     *            the viewpoint name to which the specified diagram to open
     *            owned
     */
    protected void openEditorOnDiagram(String diagramName, String viewpointName) {
        for (DRepresentationDescriptor representationDescriptor : getRepresentationDescriptors(viewpointName)) {
            if (representationDescriptor.getName().equals(diagramName) && representationDescriptor.getRepresentation() instanceof DDiagram) {
                currentdRepresentation = (DDiagram) representationDescriptor.getRepresentation();
                IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, currentdRepresentation, new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
                if (editorPart instanceof IDiagramWorkbenchPart) {
                    ((DiagramGraphicalViewer) ((IDiagramWorkbenchPart) editorPart).getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.SNAPTOGRID,
                            false);
                }
                break;
            }
        }
    }

    /**
     * This method checks if graphical elements present before a migration are
     * always present after.
     * 
     * @param representation
     *            the representation containing the graphical element to check
     */
    protected void checkAllDiagramElementPresent(Representation representation) {
        for (Iterator<EObject> iterator = representation.eAllContents(); iterator.hasNext();) {
            EObject element = iterator.next();
            if (element instanceof AbstractRepresentation) {
                AbstractRepresentation abstractRepresentation = (AbstractRepresentation) element;
                String mappingId = abstractRepresentation.getMappingId();
                XMLResource odesignResource = getOdesignResource();
                DiagramElementMapping diagramElementMapping = (DiagramElementMapping) odesignResource.getEObject(mappingId);
                DDiagramElement diagramElement = getFirstDiagramElement(currentdRepresentation, element.eContainer(), diagramElementMapping);
                if (((AbstractRepresentation) element).isDisplayed()) {
                    assertNotNull("DDiagram Element is missing for " + ((GraphicalElement) element.eContainer()).getId(), diagramElement);
                    assertNotNull("EditPart is missing for " + ((GraphicalElement) element.eContainer()).getId(), getEditPart(diagramElement));
                } else {
                    assertNull("Diagram element should not be displayed", diagramElement);
                }
            }
        }
    }

    private XMLResource getOdesignResource() {
        XMLResource odesignResource = (XMLResource) session.getTransactionalEditingDomain().getResourceSet().getResource(URI.createPlatformPluginURI(MODELER_PATH, true), false);
        return odesignResource;
    }

    /**
     * This method checks if the coordinates of a graphical elements are the
     * same before and after a migration.
     * 
     * @param abstractRepresentation
     *            the {@link AbstractRepresentation} to check
     */
    protected void checkBounds(AbstractRepresentation abstractRepresentation) {
        if (abstractRepresentation.isDisplayed()) {
            DDiagramElement dDiagramElement = getFirstDiagramElement(currentdRepresentation, abstractRepresentation.eContainer());
            Layout layout = abstractRepresentation.getLayout();
            Rectangle expected = new Rectangle(layout.getX(), layout.getY(), layout.getWidth(), layout.getHeight());
            checkBounds(((GraphicalElement) abstractRepresentation.eContainer()).getId(), getDraw2DBounds(dDiagramElement), expected);
        }

    }

    /**
     * Return the draw2D bounds for a diagram element.
     * 
     * @param diagramElement
     *            the diagrame element
     * @return the draw2D bounds
     */
    protected Rectangle getDraw2DBounds(DDiagramElement diagramElement) {
        return LayoutHelper.getDraw2DBounds(diagramElement);
    }

    /**
     * Check if the layout (the bounds) of nodes and containers are the same
     * before and after a migration.
     * 
     * @param representation
     *            the representation containing the nodes and the containers to
     *            check
     */
    protected void checkNodeLayout(Representation representation) {
        if (representation instanceof Diagram) {
            List<Node> nodes = ((Diagram) representation).getNodes();
            List<Container> containers = ((Diagram) representation).getContainers();
            for (Node currentNode : nodes) {
                for (NodeRepresentation nodeRepresentation : currentNode.getNodeRepresentations()) {
                    checkBounds(nodeRepresentation);
                }
            }

            for (Container currentContainer : containers) {
                for (ContainerRepresentation containerRepresentation : currentContainer.getContainerRepresentations()) {
                    checkBounds(containerRepresentation);
                }
            }
        }
    }

    /**
     * This method tests if the bounds of two rectangles are equals.
     * 
     * @param elementName
     *            name of element to check
     * @param actual
     *            the rectangle to compare
     * @param expected
     *            the expected rectangle
     */
    protected void checkBounds(String elementName, Rectangle actual, Rectangle expected) {
        assertEquals("Wrong figure bounds for " + elementName, expected, actual);
    }
    /**
     * Check if the layout (the bounds) of an edge is same before and after a
     * migration.
     * 
     * @param representation
     *            the representation containing the edges to check
     */
    protected void checkEdgeLayout(Representation representation) {
        this.checkEdgeLayout(representation, 0);
    }
    
    /**
     * Check if the layout (the bounds) of an edge is same before and after a migration.
     * 
     * @param representation
     *            the representation containing the edges to check
     * @param delta
     *            how many pixels of difference between expected and actual coordinates is tolerated.
     */
    protected void checkEdgeLayout(Representation representation, int delta) {
        if (representation instanceof Diagram) {
            List<Edge> edges = ((Diagram) representation).getEdges();
            for (Edge currentEdge : edges) {
                // The bendpoints are not checked if the source or the target of
                // the edge is autoSized.
                if (!isAutoSized(currentEdge.getSource()) && !isAutoSized(currentEdge.getTarget())) {
                    for (EdgeRepresentation edgeRepresentation : currentEdge.getEdgeRepresentations()) {
                        checkBendpoints(edgeRepresentation, delta);
                    }
                }
            }
        }
    }

    private boolean isAutoSized(GraphicalElement graphicalElement) {
        if (graphicalElement instanceof Container) {
            Container container = ((Container) graphicalElement);
            if (container.getContainerRepresentations().size() == 1) {
                if (container.getContainerRepresentations().get(0).isAutoSized()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method tests if the bendpoints of an edge are the same before and after a migration.
     * 
     * @param edgeRepresentation
     *            the {@link EdgeRepresentation} to check
     * @param delta
     *            how many pixels of difference between expected and actual coordinates is tolerated.
     */
    protected void checkBendpoints(EdgeRepresentation edgeRepresentation, int delta) {
        GraphicalElement graphicalEdge = (GraphicalElement) edgeRepresentation.eContainer();
        DDiagramElement dDiagramElement = getFirstDiagramElement(currentdRepresentation, graphicalEdge);
        assertNotNull("Edge " + graphicalEdge.getId() + " is missing from representation", dDiagramElement);
        List<Point> expectedBendpoints = edgeRepresentation.getBendpoints();
        assertNotNull("Edge expected bendpoints list is null for edge " + graphicalEdge.getId(), expectedBendpoints);
        PointList actualPointList = getDraw2DBendpoints((DEdge) dDiagramElement);
        assertNotNull("Cannot retrieve edge bendpoint for " + graphicalEdge.getId(), actualPointList);
        assertEquals("wrong bendpoint number for " + graphicalEdge.getId(), expectedBendpoints.size(), actualPointList.size());
        for (int i = 0; i < expectedBendpoints.size(); i++) {
            Point expectedPoint = expectedBendpoints.get(i);
            org.eclipse.draw2d.geometry.Point actualPoint = actualPointList.getPoint(i);
            assertTrue("Edge " + graphicalEdge.getId() + ": wrong x bendpoint position for point " + i, Math.abs(expectedPoint.getX() - actualPoint.x) <= delta);
            assertTrue("Edge " + graphicalEdge.getId() + ": wrong y bendpoint position for point " + i, Math.abs(expectedPoint.getY() - actualPoint.y) <= delta);
        }
    }

    /**
     * Return the bendpoints of an edge.
     * 
     * @param dEdge
     *            the edge
     * @return the bendpoints of the edge
     */
    protected PointList getDraw2DBendpoints(DEdge dEdge) {
        return LayoutHelper.getBendpoints(dEdge);

    }

    /**
     * Check if the style of nodes is the same before and after a migration.
     * 
     * @param representation
     *            the representation containing the nodes to check
     */
    protected void checkNodesStyle(Representation representation) {
        for (Iterator<EObject> iterator = representation.eAllContents(); iterator.hasNext();) {
            EObject element = iterator.next();
            if (element instanceof AbstractRepresentation) {
                checkElementStyle((AbstractRepresentation) element);
            }

        }
    }

    /**
     * Check if the color of a graphical element is the same after and before a
     * migration.
     * 
     * @param abstractRepresentation
     *            {@link AbstractRepresentation} to test
     */
    protected void checkElementStyle(AbstractRepresentation abstractRepresentation) {
        DDiagramElement dDiagramElement = getFirstDiagramElement(currentdRepresentation, abstractRepresentation);

        // check color
        if (abstractRepresentation.isDisplayed() && abstractRepresentation instanceof AbstractNodeRepresentation) {
            Color actualBgColor = LayoutHelper.getBackgroundColor(dDiagramElement);
            Square nodeStyle = (Square) ((AbstractNodeRepresentation) abstractRepresentation).getOwnedStyle();
            RGBValues nodeColor = nodeStyle.getColor();
            assertEquals("Wrong node color: " + abstractRepresentation.getMappingId(), nodeColor.getRed(), actualBgColor.getRed());
            assertEquals("Wrong node color: " + abstractRepresentation.getMappingId(), nodeColor.getGreen(), actualBgColor.getGreen());
            assertEquals("Wrong node color: " + abstractRepresentation.getMappingId(), nodeColor.getBlue(), actualBgColor.getBlue());

        }
    }

    /**
     * Check for each edge contained in a representation, if the color is the
     * same after and before a migration.
     * 
     * @param representation
     *            the representation
     */
    protected void checkEdgesStyle(Representation representation) {
        for (Edge edge : getEdgeList((Diagram) representation)) {
            for (EdgeRepresentation edgeRepresentation : edge.getEdgeRepresentations()) {
                checkColor(edgeRepresentation);
            }
        }
    }

    /**
     * Return a list of the edges contained in a representation.
     * 
     * @param representation
     *            the representation
     * @return a list of edges
     */
    protected List<Edge> getEdgeList(Diagram representation) {
        List<Edge> edges = new ArrayList<Edge>();
        for (Iterator<EObject> iterator = representation.eAllContents(); iterator.hasNext();) {
            EObject element = iterator.next();
            if (element instanceof Edge) {
                edges.add((Edge) element);
            }
        }
        return edges;
    }

    /**
     * Check if the color of an edge is the same after and before a migration.
     * 
     * @param edge
     *            the {@link EdgeRepresentation} to test
     */
    protected void checkColor(EdgeRepresentation edgeRepresentation) {
        DDiagramElement dDiagramElement = getFirstDiagramElement(currentdRepresentation, edgeRepresentation.eContainer());
        assertNotNull("Impossible to retrieve edge " + edgeRepresentation.getMappingId() + " ddiagram element", dDiagramElement);
        Color actualColor = LayoutHelper.getForegroundColor(dDiagramElement);
        EdgeStyle edgeStyle = edgeRepresentation.getOwnedStyle();
        assertNotNull("Expected edge style is null", edgeStyle);
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color expectedEdgeColor = edgeStyle.getColor();
        assertEquals("Wrong edge color: " + edgeRepresentation.getMappingId(), expectedEdgeColor.getRed(), actualColor.getRed());
        assertEquals("Wrong edge color: " + edgeRepresentation.getMappingId(), expectedEdgeColor.getGreen(), actualColor.getGreen());
        assertEquals("Wrong edge color: " + edgeRepresentation.getMappingId(), expectedEdgeColor.getBlue(), actualColor.getBlue());

    }

    /**
     * Check the functionality show/hide for each graphical element of a
     * representation :
     * <UL>
     * <LI>A graphical element hide before a migration must be hide after a
     * migration</LI>
     * <LI>A graphical element which is not hide (show) before a migration must
     * be not hide after a migration</LI>
     * </UL>
     * 
     * @param representation
     *            the representation that containing the graphical elements to
     *            test
     */
    protected void checkHidden(Representation representation) {
        for (Iterator<EObject> iterator = representation.eAllContents(); iterator.hasNext();) {
            EObject element = iterator.next();
            if (element instanceof AbstractRepresentation) {
                checkHidden((AbstractRepresentation) element);
            }

        }
    }

    /**
     * Check the functionality pin/unpin for each graphical element of a
     * representation :
     * <UL>
     * <LI>A graphical element pin before a migration must be pin after a
     * migration</LI>
     * <LI>A graphical element unpin before a migration must be unpin after a
     * migration.</LI>
     * </UL>
     * 
     * @param representation
     *            the representation that containing the graphical element to
     *            test
     */
    protected void checkPinUnpin(Representation representation) {
        for (Iterator<EObject> iterator = representation.eAllContents(); iterator.hasNext();) {
            EObject element = iterator.next();
            if (element instanceof AbstractRepresentation) {
                checkPinUnpin((AbstractRepresentation) element);
            }

        }
    }

    /**
     * Check if the activated filters before a migration are always activated
     * after a migration.
     * 
     * @param representation
     *            the representation that containing the filters
     */
    protected void checkFilters(Representation representation) {
        if (representation instanceof Diagram) {
            Diagram diagram = (Diagram) representation;
            List<FilterDescription> activatedFilters = currentdRepresentation.getActivatedFilters();
            for (Filter filter : diagram.getFilters()) {
                if (filter.isActivated()) {
                    assertTrue("", findFilter(filter, activatedFilters));
                } else {
                    assertFalse("", findFilter(filter, activatedFilters));
                }
            }
        }
    }

    private boolean findFilter(Filter filter, List<FilterDescription> activatedFilters) {
        for (FilterDescription filterDescription : activatedFilters) {
            if (filterDescription.getName().equals(filter.getId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the activated layers before a migration are always activated
     * after a migration.
     * 
     * @param representation
     *            the representation that containing the layers
     */
    protected void checkLayers(Representation representation) {
        if (representation instanceof Diagram) {
            Diagram diagram = (Diagram) representation;
            List<Layer> activatedLayers = currentdRepresentation.getActivatedLayers();
            for (org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer layer : diagram.getLayers()) {
                if (layer.isActivated()) {
                    assertTrue("", findLayer(layer, activatedLayers));
                } else {
                    assertFalse("", findLayer(layer, activatedLayers));
                }
            }
        }
    }

    private boolean findLayer(org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer layer, List<Layer> activatedLayers) {
        for (Layer layerDescription : activatedLayers) {
            if (layerDescription.getName().equals(layer.getId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check the functionality pin/unpin on a graphical element :
     * <UL>
     * <LI>A graphical element pin before a migration must be pin after a
     * migration</LI>
     * <LI>A graphical element unpin before a migration must be unpin after a
     * migration</LI>
     * </UL>
     * 
     * @param abstractRepresentation
     *            the {@link AbstractRepresentation} to test
     */
    protected void checkPinUnpin(AbstractRepresentation abstractRepresentation) {
        if (abstractRepresentation.isHidden()) {
            DDiagramElement dDiagramElement = getFirstDiagramElement(currentdRepresentation, abstractRepresentation);
            if (abstractRepresentation.isPinned()) {
                PinHelper helper = new PinHelper();
                assertTrue(abstractRepresentation.getMappingId() + " should be pinned", helper.isPinned(dDiagramElement));
            } else {
                PinHelper helper = new PinHelper();
                assertFalse(abstractRepresentation.getMappingId() + " should not be pinned", helper.isPinned(dDiagramElement));
            }
        }
    }

    /**
     * Check the functionality show/hide on a graphical element :
     * <UL>
     * <LI>A graphical element hide before a migration must be hide after a
     * migration</LI>
     * <LI>A graphical element which is not hide (show) before a migration must
     * be displayed after a migration</LI>
     * </UL>
     * 
     * @param abstractRepresentation
     *            the {@link AbstractRepresentation} to test
     */
    protected void checkHidden(AbstractRepresentation abstractRepresentation) {
        if (abstractRepresentation.isHidden()) {
            DDiagramElement dDiagramElement = getFirstDiagramElement(currentdRepresentation, abstractRepresentation);
            DDiagramElementQuery elementQuery = new DDiagramElementQuery(dDiagramElement);
            assertTrue(abstractRepresentation.getMappingId() + " should be hidden", elementQuery.isHidden());
            assertNull(abstractRepresentation.getMappingId() + " should not has an edit part", LayoutHelper.getEditPart(dDiagramElement));
        }
    }

    protected void assertEqualsMigrationDiagrams(Diagram expectedDiagram, Diagram migratedDiagram) {
        assertEquals("The migrated diagram name should be : " + expectedDiagram.getName(), expectedDiagram.getName(), migratedDiagram.getName());
        assertEqualsNodes(expectedDiagram, migratedDiagram);
        assertEqualsContainers(expectedDiagram, migratedDiagram);
        assertEqualsEdges(expectedDiagram, migratedDiagram);
    }

    protected void assertEqualsNodes(Diagram expectedDiagram, Diagram migratedDiagram) {
        assertEquals("The migrated diagram hasn't the expect top level nodes number", expectedDiagram.getNodes().size(), migratedDiagram.getNodes().size());
        for (int i = 0; i < expectedDiagram.getNodes().size(); i++) {
            Node expectedNode = expectedDiagram.getNodes().get(i);
            Node migratedNode = migratedDiagram.getNodes().get(i);
            assertEqualsNode(expectedNode, migratedNode);
        }
    }

    protected void assertEqualsContainers(Diagram expectedDiagram, Diagram migratedDiagram) {
        assertEquals("The migrated diagram hasn't the expect top level containers number", expectedDiagram.getContainers().size(), migratedDiagram.getContainers().size());
        for (int i = 0; i < expectedDiagram.getContainers().size(); i++) {
            Container expectedContainer = expectedDiagram.getContainers().get(i);
            Container migratedContainer = migratedDiagram.getContainers().get(i);
            assertEqualsContainer(expectedContainer, migratedContainer);
            assertEquals("The migrated container " + migratedContainer.getId() + " hasn't the expect contained elements number", expectedContainer.getElements().size(),
                    migratedContainer.getElements().size());
            for (int j = 0; j < expectedContainer.getElements().size(); j++) {
                GraphicalElement expectedGraphicalElement = expectedContainer.getElements().get(j);
                GraphicalElement migratedGraphicalElement = migratedContainer.getElements().get(j);
                assertEquals("The migrated sub element n°" + j + "contained by Container " + migratedContainer.getId() + " isn't of the same type of the expected one",
                        expectedGraphicalElement.eClass(), migratedGraphicalElement.eClass());
                if (migratedGraphicalElement instanceof Node) {
                    Node expectedSubNode = (Node) expectedGraphicalElement;
                    Node migratedSubNode = (Node) migratedGraphicalElement;
                    assertEqualsNode(expectedSubNode, migratedSubNode);
                } else if (migratedGraphicalElement instanceof Bordered) {
                    Bordered expectedSubBordered = (Bordered) expectedGraphicalElement;
                    Bordered migratedSubBordered = (Bordered) migratedGraphicalElement;
                    assertEqualsBordered(expectedSubBordered, migratedSubBordered);
                } else if (migratedGraphicalElement instanceof Container) {
                    Container expectedSubContainer = (Container) expectedGraphicalElement;
                    Container migratedSubContainer = (Container) migratedGraphicalElement;
                    assertEqualsContainer(expectedSubContainer, migratedSubContainer);
                }
            }
        }
    }

    protected void assertEqualsEdges(Diagram expectedDiagram, Diagram migratedDiagram) {
        assertEquals("The migrated diagram hasn't the expect top level edges number", expectedDiagram.getEdges().size(), migratedDiagram.getEdges().size());
        for (int i = 0; i < expectedDiagram.getEdges().size(); i++) {
            Edge expectedEdge = expectedDiagram.getEdges().get(i);
            Edge migratedEdge = migratedDiagram.getEdges().get(i);
            assertEquals("The migrated edge " + migratedEdge.getId() + " hasn't the expect edge representations number", expectedEdge.getEdgeRepresentations().size(),
                    migratedEdge.getEdgeRepresentations().size());
            for (int j = 0; j < expectedEdge.getEdgeRepresentations().size(); j++) {
                EdgeRepresentation expectedEdgeRepresentation = expectedEdge.getEdgeRepresentations().get(j);
                EdgeRepresentation migratedEdgeRepresentation = migratedEdge.getEdgeRepresentations().get(j);
                // The bendpoints are not checked if the source or the target of
                // the edge is autoSized.
                if (isAutoSized(expectedEdge.getSource()) || isAutoSized(expectedEdge.getTarget())) {
                    assertEqualsBendpoints(expectedEdgeRepresentation.getMappingId(), expectedEdgeRepresentation.getBendpoints(), migratedEdgeRepresentation.getBendpoints());
                }

                EdgeStyle expectedEdgeStyle = expectedEdgeRepresentation.getOwnedStyle();
                EdgeStyle migratedEdgeStyle = migratedEdgeRepresentation.getOwnedStyle();
                assertEqualsEdgeStyle(expectedEdgeRepresentation.getMappingId(), expectedEdgeStyle, migratedEdgeStyle);
            }
        }
    }

    protected void assertEqualsEdgeStyle(String representationElementId, EdgeStyle expectedEdgeStyle, EdgeStyle migratedEdgeStyle) {
        assertEquals("The migrated edge representation " + representationElementId + " routing style isn't the expected one", expectedEdgeStyle.getRoutingStyle(), migratedEdgeStyle.getRoutingStyle());
        boolean anyHasColor = expectedEdgeStyle.getColor() == null && migratedEdgeStyle.getColor() == null;
        boolean bothHasColor = expectedEdgeStyle.getColor() != null && migratedEdgeStyle.getColor() != null;
        assertTrue(anyHasColor || bothHasColor);
        if (bothHasColor) {
            assertEquals("The migrated edge representation " + representationElementId + " color red part isn't the expected one", expectedEdgeStyle.getColor().getRed(),
                    migratedEdgeStyle.getColor().getRed());
            assertEquals("The migrated edge representation " + representationElementId + " color green part isn't the expected one", expectedEdgeStyle.getColor().getGreen(),
                    migratedEdgeStyle.getColor().getGreen());
            assertEquals("The migrated edge representation " + representationElementId + " color blue part isn't the expected one", expectedEdgeStyle.getColor().getBlue(),
                    migratedEdgeStyle.getColor().getBlue());
        }
        boolean anyHasBeginLabelStyle = expectedEdgeStyle.getBeginLabelStyle() == null && migratedEdgeStyle.getBeginLabelStyle() == null;
        boolean bothHasBeginLabelStyle = expectedEdgeStyle.getBeginLabelStyle() != null && migratedEdgeStyle.getBeginLabelStyle() != null;
        assertTrue(anyHasBeginLabelStyle || bothHasBeginLabelStyle);
        if (bothHasBeginLabelStyle) {
            assertEqualsBasicLabelStyle(representationElementId, expectedEdgeStyle.getBeginLabelStyle(), migratedEdgeStyle.getBeginLabelStyle());
        }
        boolean anyHasCenterLabelStyle = expectedEdgeStyle.getCenterLabelStyle() == null && migratedEdgeStyle.getCenterLabelStyle() == null;
        boolean bothHasCenterLabelStyle = expectedEdgeStyle.getCenterLabelStyle() != null && migratedEdgeStyle.getCenterLabelStyle() != null;
        assertTrue(anyHasCenterLabelStyle || bothHasCenterLabelStyle);
        if (bothHasCenterLabelStyle) {
            assertEqualsBasicLabelStyle(representationElementId, expectedEdgeStyle.getCenterLabelStyle(), migratedEdgeStyle.getCenterLabelStyle());
        }
        boolean anyHasEndLabelStyle = expectedEdgeStyle.getEndLabelStyle() == null && migratedEdgeStyle.getEndLabelStyle() == null;
        boolean bothHasEndLabelStyle = expectedEdgeStyle.getEndLabelStyle() != null && migratedEdgeStyle.getEndLabelStyle() != null;
        assertTrue(anyHasEndLabelStyle || bothHasEndLabelStyle);
        if (bothHasEndLabelStyle) {
            assertEqualsBasicLabelStyle(representationElementId, expectedEdgeStyle.getEndLabelStyle(), migratedEdgeStyle.getEndLabelStyle());
        }
    }

    protected void assertEqualsNode(Node expectedNode, Node migratedNode) {
        assertEquals("The migrated node " + migratedNode.getId() + " hasn't the expect node representations number", expectedNode.getNodeRepresentations().size(),
                migratedNode.getNodeRepresentations().size());
        for (int j = 0; j < expectedNode.getNodeRepresentations().size(); j++) {
            NodeRepresentation expectedNodeRepresentation = expectedNode.getNodeRepresentations().get(j);
            NodeRepresentation migratedNodeRepresentation = migratedNode.getNodeRepresentations().get(j);
            assertEqualsLayout(expectedNodeRepresentation.getMappingId(), expectedNodeRepresentation.getLayout(), migratedNodeRepresentation.getLayout(), false);
            NodeStyle expectedNodeStyle = expectedNodeRepresentation.getOwnedStyle();
            NodeStyle migratedNodeStyle = migratedNodeRepresentation.getOwnedStyle();
            assertEqualsNodeStyle(expectedNodeRepresentation.getMappingId(), expectedNodeStyle, migratedNodeStyle);
            assertEquals("The migrated node representation style " + expectedNodeStyle + " label position isn't the expect one", expectedNodeStyle.getLabelPosition(),
                    migratedNodeStyle.getLabelPosition());

        }
    }

    protected void assertEqualsBordered(Bordered expectedBordered, Bordered migratedBordered) {
        assertEquals("The migrated bordered " + migratedBordered.getId() + " hasn't the expect bordered representations number", expectedBordered.getBorderedRepresentations().size(),
                migratedBordered.getBorderedRepresentations().size());
        for (int j = 0; j < expectedBordered.getBorderedRepresentations().size(); j++) {
            BorderedRepresentation expectedBorderedRepresentation = expectedBordered.getBorderedRepresentations().get(j);
            BorderedRepresentation migratedBorderedRepresentation = migratedBordered.getBorderedRepresentations().get(j);
            assertEqualsLayout(expectedBorderedRepresentation.getMappingId(), expectedBorderedRepresentation.getLayout(), migratedBorderedRepresentation.getLayout(), false);
            NodeStyle expectedNodeStyle = expectedBorderedRepresentation.getOwnedStyle();
            NodeStyle migratedNodeStyle = migratedBorderedRepresentation.getOwnedStyle();
            assertEqualsLabelStyle(expectedBorderedRepresentation.getMappingId(), expectedNodeStyle, migratedNodeStyle);
            assertEqualsBorderedStyle(expectedBorderedRepresentation.getMappingId(), expectedNodeStyle, migratedNodeStyle);
            assertEquals("The migrated node representation style " + expectedNodeStyle + " label position isn't the expect one", expectedNodeStyle.getLabelPosition(),
                    migratedNodeStyle.getLabelPosition());

        }
    }

    protected void assertEqualsContainer(Container expectedContainer, Container migratedContainer) {
        assertEquals("The migrated container " + migratedContainer.getId() + " hasn't the expect container representations number", expectedContainer.getContainerRepresentations().size(),
                migratedContainer.getContainerRepresentations().size());
        for (int j = 0; j < expectedContainer.getContainerRepresentations().size(); j++) {
            ContainerRepresentation expectedContainerRepresentation = expectedContainer.getContainerRepresentations().get(j);
            ContainerRepresentation migratedContainerRepresentation = migratedContainer.getContainerRepresentations().get(j);
            assertEqualsLayout(expectedContainerRepresentation.getMappingId(), expectedContainerRepresentation.getLayout(), migratedContainerRepresentation.getLayout(),
                    migratedContainerRepresentation.isAutoSized());

            ContainerStyle expectedContainerStyle = expectedContainerRepresentation.getOwnedStyle();
            ContainerStyle migratedContainerStyle = migratedContainerRepresentation.getOwnedStyle();
            assertEqualsLabelStyle(expectedContainerRepresentation.getMappingId(), expectedContainerStyle, migratedContainerStyle);
            assertEqualsBorderedStyle(expectedContainerRepresentation.getMappingId(), expectedContainerStyle, migratedContainerStyle);
        }
    }

    protected void assertEqualsBendpoints(String representationElementId, List<Point> expectedBendpoints, List<Point> migratedBendpoints) {
        assertEquals("The migrated edge representation " + representationElementId + " points number isn't the expected one", expectedBendpoints.size(), migratedBendpoints.size());
        int tolerance = 5;
        for (int i = 0; i < expectedBendpoints.size(); i++) {
            Point expectedPoint = expectedBendpoints.get(i);
            Point migratedPoint = migratedBendpoints.get(i);
            assertTrue("The migrated edge representation " + representationElementId + " point #" + (i + 1) + " x coordinate isn't the expected one, expected " + expectedPoint.getX() + " was "
                    + migratedPoint.getX(), expectedPoint.getX() - tolerance < migratedPoint.getX() && expectedPoint.getX() + tolerance > migratedPoint.getX());
            assertTrue("The migrated edge representation " + representationElementId + " point #" + (i + 1) + " y coordinate isn't the expected one, expected " + expectedPoint.getY() + " was "
                    + migratedPoint.getY(), expectedPoint.getY() - tolerance < migratedPoint.getY() && expectedPoint.getY() + tolerance > migratedPoint.getY());
        }
    }

    protected void assertEqualsLayout(String representationElementId, Layout expectedLayout, Layout migratedLayout, boolean isAutoSized) {
        assertEquals("The migrated representation element " + representationElementId + " x coordinate isn't the expected one, expected " + expectedLayout.getX() + " was " + migratedLayout.getX(),
                expectedLayout.getX(), migratedLayout.getX());
        assertEquals("The migrated representation element " + representationElementId + " y coordinate isn't the expected one, expected " + expectedLayout.getY() + " was " + migratedLayout.getY(),
                expectedLayout.getY(), migratedLayout.getY());
        if (!isAutoSized) {
            int tolerance = 10;
            assertTrue("The migrated representation element " + representationElementId + " width isn't the expected one, expected " + expectedLayout.getWidth() + " was " + migratedLayout.getWidth(),
                    expectedLayout.getWidth() - tolerance < migratedLayout.getWidth() && expectedLayout.getWidth() + tolerance > migratedLayout.getWidth());
            assertTrue(
                    "The migrated representation element " + representationElementId + " height isn't the expected one, expected " + expectedLayout.getHeight() + " was " + migratedLayout.getHeight(),
                    expectedLayout.getHeight() - tolerance < migratedLayout.getHeight() && expectedLayout.getHeight() + tolerance > migratedLayout.getHeight());
        }
    }

    protected void assertEqualsNodeStyle(String representationElementId, NodeStyle expectedNodeStyle, NodeStyle migratedNodeStyle) {
        assertEquals("The migrated node representation style " + representationElementId + " label position isn't the expected one", expectedNodeStyle.getLabelPosition(),
                migratedNodeStyle.getLabelPosition());
        assertEqualsLabelStyle(representationElementId, expectedNodeStyle, migratedNodeStyle);
        assertEqualsBorderedStyle(representationElementId, expectedNodeStyle, migratedNodeStyle);
    }

    protected void assertEqualsLabelStyle(String representationElementId, LabelStyle expectedLabelStyle, LabelStyle migratedLabelStyle) {
        assertEquals("The migrated node representation style " + representationElementId + " label alignment isn't the expected one", expectedLabelStyle.getLabelAlignment(),
                migratedLabelStyle.getLabelAlignment());
        assertEqualsBasicLabelStyle(representationElementId, expectedLabelStyle, migratedLabelStyle);
    }

    protected void assertEqualsBorderedStyle(String representationElementId, BorderedStyle expectedBorderedStyle, BorderedStyle migratedBorderedStyle) {
        assertEquals("The migrated node representation style " + representationElementId + " border size isn't the expected one", expectedBorderedStyle.getBorderSize(),
                migratedBorderedStyle.getBorderSize());
        boolean anyHasBorderColor = expectedBorderedStyle.getBorderColor() == null && migratedBorderedStyle.getBorderColor() == null;
        boolean bothHasBorderColor = expectedBorderedStyle.getBorderColor() != null && migratedBorderedStyle.getBorderColor() != null;
        assertTrue(anyHasBorderColor || bothHasBorderColor);
        if (bothHasBorderColor) {
            assertEquals("The migrated node representation style " + representationElementId + " border color red part isn't the expected one", expectedBorderedStyle.getBorderColor().getRed(),
                    migratedBorderedStyle.getBorderColor().getRed());
            assertEquals("The migrated node representation style " + representationElementId + " border color green part isn't the expected one", expectedBorderedStyle.getBorderColor().getGreen(),
                    migratedBorderedStyle.getBorderColor().getGreen());
            assertEquals("The migrated node representation style " + representationElementId + " border color blue part isn't the expected one", expectedBorderedStyle.getBorderColor().getBlue(),
                    migratedBorderedStyle.getBorderColor().getBlue());
        }
    }

    protected void assertEqualsBasicLabelStyle(String representationElementId, BasicLabelStyle expectedBasicLabelStyle, BasicLabelStyle migratedBasicLabelStyle) {
        assertEquals("The migrated node representation style " + representationElementId + " icon path isn't the expected one", expectedBasicLabelStyle.getIconPath(),
                migratedBasicLabelStyle.getIconPath());
        assertEquals("The migrated node representation style " + representationElementId + " label size isn't the expected one", expectedBasicLabelStyle.getLabelSize(),
                migratedBasicLabelStyle.getLabelSize());
        boolean anyHasLabelColor = expectedBasicLabelStyle.getLabelColor() == null && migratedBasicLabelStyle.getLabelColor() == null;
        boolean bothHasLabelColor = expectedBasicLabelStyle.getLabelColor() != null && migratedBasicLabelStyle.getLabelColor() != null;
        assertTrue(anyHasLabelColor || bothHasLabelColor);
        if (bothHasLabelColor) {
            assertEquals("The migrated node representation style " + representationElementId + " label color red part isn't the expected one", expectedBasicLabelStyle.getLabelColor().getRed(),
                    migratedBasicLabelStyle.getLabelColor().getRed());
            assertEquals("The migrated node representation style " + representationElementId + " label color green part isn't the expected one", expectedBasicLabelStyle.getLabelColor().getGreen(),
                    migratedBasicLabelStyle.getLabelColor().getGreen());
            assertEquals("The migrated node representation style " + representationElementId + " label color blue part isn't the expected one", expectedBasicLabelStyle.getLabelColor().getBlue(),
                    migratedBasicLabelStyle.getLabelColor().getBlue());
        }
        assertEquals("The migrated node representation style " + representationElementId + " label format isn't the expected one", expectedBasicLabelStyle.getLabelFormat(),
                migratedBasicLabelStyle.getLabelFormat());
        assertEquals("The migrated node representation style " + representationElementId + " showIcon value isn't the expected one", expectedBasicLabelStyle.isShowIcon(),
                migratedBasicLabelStyle.isShowIcon());
    }

    protected DDiagramEditPart getDDiagramEditPart() {
        DDiagramEditPart dDiagramEditPart = null;
        IEditorPart editor = EclipseUIUtil.getActiveEditor();
        if (editor instanceof DiagramEditor) {
            DiagramEditor diagramEditor = (DiagramEditor) editor;
            DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
            if (diagramEditPart instanceof DDiagramEditPart) {
                dDiagramEditPart = (DDiagramEditPart) diagramEditPart;
            }
        }
        return dDiagramEditPart;
    }

    @Override
    @After
    public void tearDown() throws Exception {
        this.currentdRepresentation = null;
        super.tearDown();
    }
}
