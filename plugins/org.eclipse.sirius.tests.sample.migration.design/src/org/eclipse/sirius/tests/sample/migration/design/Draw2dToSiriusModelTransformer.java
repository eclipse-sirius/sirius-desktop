/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.sample.migration.design;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.FanRouter;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.DForestRouter;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerFactory;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Class to get a view model equivalent the draw2d model to check that migration preserve the draw2d infos.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class Draw2dToSiriusModelTransformer {

    private DDiagramEditPart dDiagramEditPart;

    /**
     * Default constructor.
     * 
     * @param dDiagramEditPart
     *            the {@link DDiagramEditPart} of a diagram editor for which get a Sirius model of what is displayed
     */
    public Draw2dToSiriusModelTransformer(DDiagramEditPart dDiagramEditPart) {
        this.dDiagramEditPart = dDiagramEditPart;
    }

    public Diagram getMigrationModel() {
        Diagram migrationDiagram = MigrationmodelerFactory.eINSTANCE.createDiagram();
        DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) dDiagramEditPart.resolveDDiagram().get();
        migrationDiagram.setName(new DRepresentationQuery(dSemanticDiagram).getRepresentationDescriptor().getName());
        org.eclipse.gmf.runtime.notation.Diagram diagramView = dDiagramEditPart.getDiagramView();
        Map<?, ?> editPartRegistry = dDiagramEditPart.getViewer().getEditPartRegistry();
        Map<Node, Node> semanticNodeToMigrationNodeMap = new HashMap<Node, Node>();
        Map<Container, Container> semanticContainerToMigrationContainerMap = new HashMap<Container, Container>();
        List<?> children = diagramView.getChildren();
        for (Object childObj : children) {
            Object value = editPartRegistry.get(childObj);
            if (childObj instanceof org.eclipse.gmf.runtime.notation.Node && value instanceof IDiagramElementEditPart) {
                IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) value;
                if (diagramElementEditPart instanceof IDiagramContainerEditPart) {
                    IDiagramContainerEditPart diagramContainerEditPart = (IDiagramContainerEditPart) diagramElementEditPart;
                    Container semanticContainer = (Container) diagramContainerEditPart.resolveTargetSemanticElement();
                    Container migrationContainer = semanticContainerToMigrationContainerMap.get(semanticContainer);
                    if (migrationContainer == null) {
                        migrationContainer = MigrationmodelerFactory.eINSTANCE.createContainer();
                        migrationDiagram.getContainers().add(migrationContainer);
                        semanticContainerToMigrationContainerMap.put(semanticContainer, migrationContainer);
                    }
                    ContainerRepresentation containerRepresentation = getMigrationContainerRepresentation(diagramContainerEditPart);
                    migrationContainer.getContainerRepresentations().add(containerRepresentation);
                } else if (diagramElementEditPart instanceof IDiagramListEditPart) {
                    IDiagramListEditPart diagramListEditPart = (IDiagramListEditPart) diagramElementEditPart;
                    Container semanticContainer = (Container) diagramElementEditPart.resolveTargetSemanticElement();
                    Container migrationContainer = semanticContainerToMigrationContainerMap.get(semanticContainer);
                    if (migrationContainer == null) {
                        migrationContainer = MigrationmodelerFactory.eINSTANCE.createContainer();
                        migrationDiagram.getContainers().add(migrationContainer);
                        semanticContainerToMigrationContainerMap.put(semanticContainer, migrationContainer);
                    }
                    ContainerRepresentation containerRepresentation = getMigrationContainerRepresentation(diagramListEditPart);
                    migrationContainer.getContainerRepresentations().add(containerRepresentation);
                } else if (diagramElementEditPart instanceof IDiagramNodeEditPart) {
                    IDiagramNodeEditPart diagramNodeEditPart = (IDiagramNodeEditPart) diagramElementEditPart;
                    Node semanticNode = (Node) diagramNodeEditPart.resolveTargetSemanticElement();
                    Node migrationNode = semanticNodeToMigrationNodeMap.get(semanticNode);
                    if (migrationNode == null) {
                        migrationNode = MigrationmodelerFactory.eINSTANCE.createNode();
                        migrationDiagram.getNodes().add(migrationNode);
                        semanticNodeToMigrationNodeMap.put(semanticNode, migrationNode);
                    }
                    NodeRepresentation nodeRepresentation = getMigrationNodeRepresentation(diagramNodeEditPart);
                    migrationNode.getNodeRepresentations().add(nodeRepresentation);
                }
            }
        }
        List<?> edges = diagramView.getEdges();
        Map<Edge, Edge> semanticEdgeToMigrationEdgeMap = new HashMap<Edge, Edge>();
        for (Object obj : edges) {
            Object value = editPartRegistry.get(obj);
            if (obj instanceof org.eclipse.gmf.runtime.notation.Edge && value instanceof AbstractDiagramEdgeEditPart) {
                AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) value;
                Edge semanticEdge = (Edge) abstractDiagramEdgeEditPart.resolveTargetSemanticElement();
                Edge migrationEdge = semanticEdgeToMigrationEdgeMap.get(semanticEdge);
                if (migrationEdge == null) {
                    migrationEdge = MigrationmodelerFactory.eINSTANCE.createEdge();
                    migrationDiagram.getEdges().add(migrationEdge);
                    semanticEdgeToMigrationEdgeMap.put(semanticEdge, migrationEdge);
                }
                EdgeRepresentation migrationEdgeRepresentation = getMigrationEdgeRepresentation(abstractDiagramEdgeEditPart);
                migrationEdge.getEdgeRepresentations().add(migrationEdgeRepresentation);
                // Set source
                EditPart sourceEditPart = abstractDiagramEdgeEditPart.getSource();
                if (sourceEditPart instanceof IDiagramContainerEditPart || sourceEditPart instanceof IDiagramListEditPart) {
                    Container semanticContainer = (Container) ((IDiagramElementEditPart) sourceEditPart).resolveTargetSemanticElement();
                    Container migrationContainer = semanticContainerToMigrationContainerMap.get(semanticContainer);
                    migrationEdge.setSource(migrationContainer);
                } else if (sourceEditPart instanceof IDiagramNodeEditPart) {
                    Node semanticNode = (Node) ((IDiagramElementEditPart) sourceEditPart).resolveTargetSemanticElement();
                    Node migrationNode = semanticNodeToMigrationNodeMap.get(semanticNode);
                    migrationEdge.setSource(migrationNode);
                }
                // Set target
                EditPart targetEditPart = abstractDiagramEdgeEditPart.getTarget();
                if (targetEditPart instanceof IDiagramContainerEditPart || targetEditPart instanceof IDiagramListEditPart) {
                    Container semanticContainer = (Container) ((IDiagramElementEditPart) targetEditPart).resolveTargetSemanticElement();
                    Container migrationContainer = semanticContainerToMigrationContainerMap.get(semanticContainer);
                    migrationEdge.setTarget(migrationContainer);
                } else if (targetEditPart instanceof IDiagramNodeEditPart) {
                    Node semanticNode = (Node) ((IDiagramElementEditPart) targetEditPart).resolveTargetSemanticElement();
                    Node migrationNode = semanticNodeToMigrationNodeMap.get(semanticNode);
                    migrationEdge.setTarget(migrationNode);
                }
            }
        }

        setFilters(dSemanticDiagram, migrationDiagram);
        setLayers(dSemanticDiagram, migrationDiagram);

        Iterator<EObject> it = migrationDiagram.eAllContents();
        while (it.hasNext()) {
            EObject eObject = it.next();
            if (eObject instanceof AbstractRepresentation) {
                AbstractRepresentation element = (AbstractRepresentation) eObject;
                if (!isPresentInDiagram(element, dSemanticDiagram)) {
                    element.setDisplayed(false);
                }
            }
        }
        return migrationDiagram;
    }

    public void updateMigrationModel(Diagram migrationDiagram) {
        clearRepresentations(migrationDiagram);
        DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) dDiagramEditPart.resolveDDiagram().get();
        migrationDiagram.setName(new DRepresentationQuery(dSemanticDiagram).getRepresentationDescriptor().getName());
        org.eclipse.gmf.runtime.notation.Diagram diagramView = dDiagramEditPart.getDiagramView();
        Map<?, ?> editPartRegistry = dDiagramEditPart.getViewer().getEditPartRegistry();
        List<?> children = diagramView.getChildren();
        for (Object childObj : children) {
            Object value = editPartRegistry.get(childObj);
            if (childObj instanceof org.eclipse.gmf.runtime.notation.Node && value instanceof IDiagramElementEditPart) {
                IDiagramElementEditPart diagramElementEditPart = (IDiagramElementEditPart) value;
                if (diagramElementEditPart instanceof IDiagramContainerEditPart) {
                    IDiagramContainerEditPart diagramContainerEditPart = (IDiagramContainerEditPart) diagramElementEditPart;
                    Container container = (Container) diagramContainerEditPart.resolveTargetSemanticElement();
                    ContainerRepresentation containerRepresentation = getMigrationContainerRepresentation(diagramContainerEditPart);
                    container.getContainerRepresentations().add(containerRepresentation);
                } else if (diagramElementEditPart instanceof IDiagramListEditPart) {
                    IDiagramListEditPart diagramListEditPart = (IDiagramListEditPart) diagramElementEditPart;
                    Container container = (Container) diagramElementEditPart.resolveTargetSemanticElement();
                    ContainerRepresentation containerRepresentation = getMigrationContainerRepresentation(diagramListEditPart);
                    container.getContainerRepresentations().add(containerRepresentation);
                } else if (diagramElementEditPart instanceof IDiagramNodeEditPart) {
                    IDiagramNodeEditPart diagramNodeEditPart = (IDiagramNodeEditPart) diagramElementEditPart;
                    Node node = (Node) diagramNodeEditPart.resolveTargetSemanticElement();
                    NodeRepresentation nodeRepresentation = getMigrationNodeRepresentation(diagramNodeEditPart);
                    node.getNodeRepresentations().add(nodeRepresentation);
                }
            }
        }
        List<?> edges = diagramView.getEdges();
        for (Object obj : edges) {
            Object value = editPartRegistry.get(obj);
            if (obj instanceof org.eclipse.gmf.runtime.notation.Edge && value instanceof AbstractDiagramEdgeEditPart) {
                AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart = (AbstractDiagramEdgeEditPart) value;
                Edge edge = (Edge) abstractDiagramEdgeEditPart.resolveTargetSemanticElement();
                EdgeRepresentation migrationEdgeRepresentation = getMigrationEdgeRepresentation(abstractDiagramEdgeEditPart);
                edge.getEdgeRepresentations().add(migrationEdgeRepresentation);
            }
        }

        setFilters(dSemanticDiagram, migrationDiagram);
        setLayers(dSemanticDiagram, migrationDiagram);

        Iterator<EObject> it = migrationDiagram.eAllContents();
        while (it.hasNext()) {
            EObject eObject = it.next();
            if (eObject instanceof AbstractRepresentation) {
                AbstractRepresentation element = (AbstractRepresentation) eObject;
                if (!isPresentInDiagram(element, dSemanticDiagram)) {
                    element.setDisplayed(false);
                }
            }
        }
    }

    private void clearRepresentations(Diagram migrationDiagram) {
        for (Node node : migrationDiagram.getNodes()) {
            node.getNodeRepresentations().clear();
        }
        for (Container container : migrationDiagram.getContainers()) {
            container.getContainerRepresentations().clear();
            clearContainerElementRepresentations(container);
        }
        for (Edge edge : migrationDiagram.getEdges()) {
            edge.getEdgeRepresentations().clear();
        }
    }

    private void clearContainerElementRepresentations(Container container) {
        for (GraphicalElement graphicalElement : container.getElements()) {
            if (graphicalElement instanceof Node) {
                Node node = (Node) graphicalElement;
                node.getNodeRepresentations().clear();
            } else if (graphicalElement instanceof Bordered) {
                Bordered bordered = (Bordered) graphicalElement;
                bordered.getBorderedRepresentations().clear();
            } else if (graphicalElement instanceof Container) {
                Container subContainer = (Container) graphicalElement;
                subContainer.getContainerRepresentations().clear();
                clearContainerElementRepresentations(subContainer);
            }
        }
    }

    private ContainerRepresentation getMigrationContainerRepresentation(IDiagramListEditPart diagramListEditPart) {
        ContainerRepresentation containerRepresentation = MigrationmodelerFactory.eINSTANCE.createContainerRepresentation();
        containerRepresentation.setMappingId(getMappingId(diagramListEditPart));
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle containerStyle = MigrationmodelerFactory.eINSTANCE.createContainerStyle();
        containerRepresentation.setOwnedStyle(containerStyle);
        updateLabelStyle(containerStyle, diagramListEditPart);
        updateBorderedStyle(containerStyle, diagramListEditPart);
        Object model = diagramListEditPart.getModel();
        if (model instanceof org.eclipse.gmf.runtime.notation.Node) {
            org.eclipse.gmf.runtime.notation.Node node = (org.eclipse.gmf.runtime.notation.Node) model;
            if (node.getLayoutConstraint() instanceof Size) {
                Size size = (Size) node.getLayoutConstraint();
                if (size.getWidth() == -1 || size.getHeight() == -1) {
                    containerRepresentation.setAutoSized(true);
                }
            }
        }
        updateLayout(containerRepresentation, diagramListEditPart.getFigure());

        List<?> children = new ArrayList<Object>(diagramListEditPart.getChildren());
        Iterator<ResizableCompartmentEditPart> compart = diagramListEditPart.getChildren().stream().filter(ResizableCompartmentEditPart.class::isInstance).map(ResizableCompartmentEditPart.class::cast)
                .iterator();
        if (compart.hasNext()) {
            ResizableCompartmentEditPart compartmentEditPart = compart.next();
            children.addAll(compartmentEditPart.getChildren());
        }
        Iterable<IAbstractDiagramNodeEditPart> filter = () -> children.stream().filter(IAbstractDiagramNodeEditPart.class::isInstance).map(IAbstractDiagramNodeEditPart.class::cast).iterator();
        for (IAbstractDiagramNodeEditPart childEditPart : filter) {
            EObject targetSemanticElement = childEditPart.resolveTargetSemanticElement();
            if (targetSemanticElement instanceof Node && childEditPart instanceof IDiagramNodeEditPart) {
                Node subNode = (Node) targetSemanticElement;
                IDiagramNodeEditPart childNodeEditPart = (IDiagramNodeEditPart) childEditPart;
                NodeRepresentation nodeRepresentation = getMigrationNodeRepresentation(childNodeEditPart);
                subNode.getNodeRepresentations().add(nodeRepresentation);
            } else if (targetSemanticElement instanceof Bordered) {
                Bordered subBordered = (Bordered) targetSemanticElement;
                BorderedRepresentation borderedRepresentation = getMigrationBorderedRepresentation(childEditPart);
                subBordered.getBorderedRepresentations().add(borderedRepresentation);
            } else if (targetSemanticElement instanceof Container) {
                Container subContainer = (Container) targetSemanticElement;
                ContainerRepresentation subContainerRepresentation = null;
                if (childEditPart instanceof IDiagramContainerEditPart) {
                    subContainerRepresentation = getMigrationContainerRepresentation((IDiagramContainerEditPart) childEditPart);
                } else if (childEditPart instanceof IDiagramListEditPart) {
                    subContainerRepresentation = getMigrationContainerRepresentation((IDiagramListEditPart) childEditPart);
                }
                if (subContainerRepresentation != null) {
                    subContainer.getContainerRepresentations().add(subContainerRepresentation);
                }
            }
        }

        return containerRepresentation;
    }

    private ContainerRepresentation getMigrationContainerRepresentation(IDiagramContainerEditPart diagramContainerEditPart) {
        ContainerRepresentation containerRepresentation = MigrationmodelerFactory.eINSTANCE.createContainerRepresentation();
        containerRepresentation.setMappingId(getMappingId(diagramContainerEditPart));
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle containerStyle = MigrationmodelerFactory.eINSTANCE.createContainerStyle();
        containerRepresentation.setOwnedStyle(containerStyle);
        updateLabelStyle(containerStyle, diagramContainerEditPart);
        updateBorderedStyle(containerStyle, diagramContainerEditPart);
        Object model = diagramContainerEditPart.getModel();
        if (model instanceof org.eclipse.gmf.runtime.notation.Node) {
            org.eclipse.gmf.runtime.notation.Node node = (org.eclipse.gmf.runtime.notation.Node) model;
            if (node.getLayoutConstraint() instanceof Size) {
                Size size = (Size) node.getLayoutConstraint();
                if (size.getWidth() == -1 || size.getHeight() == -1) {
                    containerRepresentation.setAutoSized(true);
                }
            }
        }
        updateLayout(containerRepresentation, diagramContainerEditPart.getFigure());
        List<?> children = new ArrayList<Object>(diagramContainerEditPart.getChildren());
        Iterator<ResizableCompartmentEditPart> compart = Iterators.filter(diagramContainerEditPart.getChildren().iterator(), ResizableCompartmentEditPart.class);
        if (compart.hasNext()) {
            ResizableCompartmentEditPart compartmentEditPart = compart.next();
            children.addAll(compartmentEditPart.getChildren());
        }
        Iterable<IAbstractDiagramNodeEditPart> filter = Iterables.filter(children, IAbstractDiagramNodeEditPart.class);
        for (IAbstractDiagramNodeEditPart childEditPart : filter) {
            EObject targetSemanticElement = childEditPart.resolveTargetSemanticElement();
            if (targetSemanticElement instanceof Node && childEditPart instanceof IDiagramNodeEditPart) {
                Node subNode = (Node) targetSemanticElement;
                IDiagramNodeEditPart childNodeEditPart = (IDiagramNodeEditPart) childEditPart;
                NodeRepresentation nodeRepresentation = getMigrationNodeRepresentation(childNodeEditPart);
                subNode.getNodeRepresentations().add(nodeRepresentation);
            } else if (targetSemanticElement instanceof Bordered) {
                Bordered subBordered = (Bordered) targetSemanticElement;
                BorderedRepresentation borderedRepresentation = getMigrationBorderedRepresentation(childEditPart);
                subBordered.getBorderedRepresentations().add(borderedRepresentation);
            } else if (targetSemanticElement instanceof Container) {
                Container subContainer = (Container) targetSemanticElement;
                ContainerRepresentation subContainerRepresentation = null;
                if (childEditPart instanceof IDiagramContainerEditPart) {
                    subContainerRepresentation = getMigrationContainerRepresentation((IDiagramContainerEditPart) childEditPart);
                } else if (childEditPart instanceof IDiagramListEditPart) {
                    subContainerRepresentation = getMigrationContainerRepresentation((IDiagramListEditPart) childEditPart);
                }
                if (subContainerRepresentation != null) {
                    subContainer.getContainerRepresentations().add(subContainerRepresentation);
                }
            }
        }
        return containerRepresentation;
    }

    private BorderedRepresentation getMigrationBorderedRepresentation(IAbstractDiagramNodeEditPart borderedEditPart) {
        BorderedRepresentation borderedRepresentation = MigrationmodelerFactory.eINSTANCE.createBorderedRepresentation();
        borderedRepresentation.setMappingId(getMappingId(borderedEditPart));
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle nodeStyle = MigrationmodelerFactory.eINSTANCE.createNodeStyle();
        borderedRepresentation.setOwnedStyle(nodeStyle);
        updateNodeStyle(nodeStyle, borderedEditPart);
        updateLayout(borderedRepresentation, borderedEditPart.getFigure());
        return borderedRepresentation;
    }

    private NodeRepresentation getMigrationNodeRepresentation(IDiagramNodeEditPart diagramNodeEditPart) {
        NodeRepresentation nodeRepresentation = MigrationmodelerFactory.eINSTANCE.createNodeRepresentation();
        nodeRepresentation.setMappingId(getMappingId(diagramNodeEditPart));
        IFigure figure = diagramNodeEditPart.getFigure();

        nodeRepresentation.setDisplayed(true);
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle nodeStyle = MigrationmodelerFactory.eINSTANCE.createNodeStyle();
        nodeRepresentation.setOwnedStyle(nodeStyle);

        updateNodeStyle(nodeStyle, diagramNodeEditPart);
        updateLayout(nodeRepresentation, figure);

        return nodeRepresentation;
    }

    private EdgeRepresentation getMigrationEdgeRepresentation(AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart) {
        EdgeRepresentation edgeRepresentation = MigrationmodelerFactory.eINSTANCE.createEdgeRepresentation();
        edgeRepresentation.setMappingId(getMappingId(abstractDiagramEdgeEditPart));
        edgeRepresentation.setDisplayed(true);
        DEdge dEdge = (DEdge) abstractDiagramEdgeEditPart.resolveDiagramElement();
        Connection connectionFigure = abstractDiagramEdgeEditPart.getConnectionFigure();
        PointList points = abstractDiagramEdgeEditPart.getConnectionFigure().getPoints();
        if (points != null) {
            for (int i = 0; i < points.size(); i++) {
                Point point = points.getPoint(i);
                org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point migrationPoint = MigrationmodelerFactory.eINSTANCE.createPoint();
                migrationPoint.setX(point.x);
                migrationPoint.setY(point.y);
                edgeRepresentation.getBendpoints().add(migrationPoint);
            }
        }
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle edgeStyle = MigrationmodelerFactory.eINSTANCE.createEdgeStyle();
        edgeRepresentation.setOwnedStyle(edgeStyle);

        RoutingStyle routingStyle = getRoutingStyle(connectionFigure);
        if (routingStyle != null) {
            edgeStyle.setRoutingStyle(routingStyle);
        }
        Color foregroundColor = connectionFigure.getForegroundColor();
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color color = MigrationmodelerFactory.eINSTANCE.createColor();
        color.setRed(foregroundColor.getRed());
        color.setGreen(foregroundColor.getGreen());
        color.setBlue(foregroundColor.getBlue());
        edgeStyle.setColor(color);
        DEdgeBeginNameEditPart beginDEdgeNameEditPart = getDEdgeBeginNameEditPart(abstractDiagramEdgeEditPart);
        if (beginDEdgeNameEditPart != null && dEdge.getOwnedStyle().getBeginLabelStyle() != null) {
            org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle migrationBeginLabelStyle = getMigrationBasicLabelStyle(dEdge.getOwnedStyle().getBeginLabelStyle(),
                    beginDEdgeNameEditPart);
            edgeStyle.setBeginLabelStyle(migrationBeginLabelStyle);
        }
        DEdgeNameEditPart centerDEdgeNameEditPart = getDEdgeCenterNameEditPart(abstractDiagramEdgeEditPart);
        if (centerDEdgeNameEditPart != null && dEdge.getOwnedStyle().getCenterLabelStyle() != null) {
            org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle migrationCenterLabelStyle = getMigrationBasicLabelStyle(dEdge.getOwnedStyle().getCenterLabelStyle(),
                    centerDEdgeNameEditPart);
            edgeStyle.setCenterLabelStyle(migrationCenterLabelStyle);
        }
        DEdgeEndNameEditPart endDEdgeNameEditPart = getDEdgeEndNameEditPart(abstractDiagramEdgeEditPart);
        if (endDEdgeNameEditPart != null && dEdge.getOwnedStyle().getEndLabelStyle() != null) {
            org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle migrationEndLabelStyle = getMigrationBasicLabelStyle(dEdge.getOwnedStyle().getEndLabelStyle(),
                    endDEdgeNameEditPart);
            edgeStyle.setEndLabelStyle(migrationEndLabelStyle);
        }
        return edgeRepresentation;
    }

    private RoutingStyle getRoutingStyle(Connection connectionFigure) {
        RoutingStyle routingStyle = null;
        ConnectionRouter connectionRouter = connectionFigure.getConnectionRouter();
        if (connectionRouter instanceof RectilinearRouter) {
            routingStyle = RoutingStyle.MANHATTAN;
        } else if (connectionRouter instanceof FanRouter) {
            routingStyle = RoutingStyle.STRAIGHT;
        } else if (connectionRouter instanceof DForestRouter) {
            routingStyle = RoutingStyle.TREE;
        }
        return routingStyle;
    }

    private String getMappingId(IDiagramElementEditPart diagramListEditPart) {
        String mappingId = null;
        RepresentationElementMapping mapping = diagramListEditPart.resolveDiagramElement().getMapping();
        XMLResource xmlResource = (XMLResource) mapping.eResource();
        mappingId = xmlResource.getURIFragment(mapping);
        return mappingId;
    }

    private void updateNodeStyle(org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle nodeStyle, IDiagramElementEditPart abstractGraphicalEditPart) {
        Iterator<DNodeNameEditPart> dNodeNameEditParts = Iterators.filter(abstractGraphicalEditPart.getChildren().iterator(), DNodeNameEditPart.class);
        if (dNodeNameEditParts.hasNext()) {
            DNodeNameEditPart dNodeNameEditPart = dNodeNameEditParts.next();
            if (dNodeNameEditPart.getFigure().isVisible()) {
                nodeStyle.setLabelPosition(LabelPosition.BORDER);
            } else {
                nodeStyle.setLabelPosition(LabelPosition.NODE);
            }
        }
        updateLabelStyle(nodeStyle, abstractGraphicalEditPart);
        updateBorderedStyle(nodeStyle, abstractGraphicalEditPart);
    }

    private void updateLabelStyle(org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle labelStyle, IDiagramElementEditPart abstractGraphicalEditPart) {
        if (abstractGraphicalEditPart.getFigure() instanceof SiriusWrapLabel) {
            SiriusWrapLabel viewpointWrapLabel = (SiriusWrapLabel) abstractGraphicalEditPart.getFigure();
            LabelAlignment labelAlignment = null;
            switch (viewpointWrapLabel.getLabelAlignment2()) {
            case PositionConstants.CENTER:
                labelAlignment = LabelAlignment.CENTER;
                break;
            case PositionConstants.LEFT:
                labelAlignment = LabelAlignment.LEFT;
                break;
            case PositionConstants.RIGHT:
                labelAlignment = LabelAlignment.RIGHT;
                break;
            default:
                labelAlignment = LabelAlignment.CENTER;
                break;
            }
            labelStyle.setLabelAlignment(labelAlignment);
        }
        Iterator<org.eclipse.sirius.viewpoint.BasicLabelStyle> filter = Iterators.filter(abstractGraphicalEditPart.resolveDiagramElement().eAllContents(),
                org.eclipse.sirius.viewpoint.BasicLabelStyle.class);
        if (filter.hasNext()) {
            org.eclipse.sirius.viewpoint.BasicLabelStyle onlyElement = filter.next();
            updateBasicLabelStyle(labelStyle, onlyElement, (AbstractGraphicalEditPart) abstractGraphicalEditPart);
        }
    }

    private void updateBorderedStyle(org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle borderedStyle, IDiagramElementEditPart abstractGraphicalEditPart) {
        IFigure figure = abstractGraphicalEditPart.getFigure();
        int borderSize = 0;
        Color borderColor = null;
        if (figure.getBorder() instanceof LineBorder) {
            LineBorder lineBorder = (LineBorder) figure.getBorder();
            borderSize = lineBorder.getWidth();
            borderColor = lineBorder.getColor();
        }
        borderedStyle.setBorderSize(borderSize);
        if (borderColor != null) {
            org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color color = MigrationmodelerFactory.eINSTANCE.createColor();
            color.setRed(borderColor.getRed());
            color.setGreen(borderColor.getGreen());
            color.setBlue(borderColor.getBlue());
            borderedStyle.setBorderColor(color);
        }
    }

    private void updateLayout(AbstractRepresentation abstractRepresentation, IFigure figure) {
        Layout layout = MigrationmodelerFactory.eINSTANCE.createLayout();
        abstractRepresentation.setLayout(layout);
        Rectangle bounds = figure.getBounds();
        layout.setX(bounds.x);
        layout.setY(bounds.y);
        layout.setWidth(bounds.width);
        layout.setHeight(bounds.height);
    }

    private org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle getMigrationBasicLabelStyle(org.eclipse.sirius.viewpoint.BasicLabelStyle viewpointBasicLabelStyle,
            AbstractDEdgeNameEditPart abstractDEdgeNameEditPart) {
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle migrationBeginLabelStyle = MigrationmodelerFactory.eINSTANCE.createBasicLabelStyle();
        updateBasicLabelStyle(migrationBeginLabelStyle, viewpointBasicLabelStyle, abstractDEdgeNameEditPart);
        return migrationBeginLabelStyle;
    }

    private void updateBasicLabelStyle(BasicLabelStyle migrationBeginLabelStyle, org.eclipse.sirius.viewpoint.BasicLabelStyle viewpointBasicLabelStyle,
            AbstractGraphicalEditPart abstractGraphicalEditPart) {
        IFigure figure = abstractGraphicalEditPart.getFigure();
        Font font = figure.getFont();
        FontData fontData = font.getFontData()[0];
        int fontHeight = fontData.getHeight();
        migrationBeginLabelStyle.setLabelSize(fontHeight);
        boolean isBold = (fontData.getStyle() & SWT.BOLD) != 0;
        boolean isItalic = (fontData.getStyle() & SWT.ITALIC) != 0;
        if (isBold && isItalic) {
            migrationBeginLabelStyle.setLabelFormat(FontFormat.BOLD_ITALIC);
        } else if (isBold) {
            migrationBeginLabelStyle.setLabelFormat(FontFormat.BOLD);
        } else if (isItalic) {
            migrationBeginLabelStyle.setLabelFormat(FontFormat.ITALIC);
        } else {
            migrationBeginLabelStyle.setLabelFormat(FontFormat.NORMAL);
        }
        Color labelForegroundColor = figure.getForegroundColor();
        org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color migrationLabelForegroundColor = MigrationmodelerFactory.eINSTANCE.createColor();
        migrationLabelForegroundColor.setRed(labelForegroundColor.getRed());
        migrationLabelForegroundColor.setGreen(labelForegroundColor.getGreen());
        migrationLabelForegroundColor.setBlue(labelForegroundColor.getBlue());
        migrationBeginLabelStyle.setLabelColor(migrationLabelForegroundColor);
        AbstractDiagramNameEditPart abstractDiagramNameEditPart = getAbstractDiagramNameEditPart(abstractGraphicalEditPart);
        if (abstractDiagramNameEditPart != null) {
            SiriusWrapLabel viewpointWrapLabel = (SiriusWrapLabel) abstractDiagramNameEditPart.getFigure();
            Image icon = viewpointWrapLabel.getIcon();
            migrationBeginLabelStyle.setShowIcon(icon != null);
            if (icon == null) {
                migrationBeginLabelStyle.setIconPath(viewpointBasicLabelStyle.getIconPath());
            } else {
                migrationBeginLabelStyle.setIconPath(getIconPath(viewpointBasicLabelStyle, abstractDiagramNameEditPart));
            }
        }
    }

    private AbstractDiagramNameEditPart getAbstractDiagramNameEditPart(AbstractGraphicalEditPart abstractGraphicalEditPart) {
        AbstractDiagramNameEditPart abstractDiagramNameEditPart = null;
        if (abstractGraphicalEditPart instanceof AbstractDiagramNameEditPart) {
            abstractDiagramNameEditPart = (AbstractDiagramNameEditPart) abstractGraphicalEditPart;
        } else {
            for (Object obj : abstractGraphicalEditPart.getChildren()) {
                if (obj instanceof AbstractDiagramNameEditPart) {
                    abstractDiagramNameEditPart = (AbstractDiagramNameEditPart) obj;
                    break;
                }
            }
        }
        return abstractDiagramNameEditPart;
    }

    private String getIconPath(org.eclipse.sirius.viewpoint.BasicLabelStyle viewpointBasicLabelStyle, AbstractDiagramNameEditPart abstractDiagramNameEditPart) {
        String iconPath = "";
        String modelIconPath = viewpointBasicLabelStyle.getIconPath();
        IFigure figure = abstractDiagramNameEditPart.getFigure();
        if (figure instanceof SiriusWrapLabel) {
            SiriusWrapLabel viewpointWrapLabel = (SiriusWrapLabel) figure;
            Image icon = viewpointWrapLabel.getIcon();
            if (modelIconPath != null && modelIconPath.length() > 0) {
                File imageFile = FileProvider.getDefault().getFile(new Path(modelIconPath));
                if (imageFile != null) {
                    try {
                        ImageDescriptor imageDescriptor = DiagramUIPlugin.Implementation.findImageDescriptor(imageFile.toURI().toURL());
                        if (imageDescriptor == null) {
                            imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
                        }
                        Image image = DiagramUIPlugin.getPlugin().getImage(imageDescriptor);
                        if (areEqualImages(image, icon)) {
                            iconPath = modelIconPath;
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return iconPath;
    }

    /**
     * Test the equality of two {@link Image} according to their {@link Image#getImageData()#data}.
     * 
     * @param image1
     *            first {@link Image}
     * @param image2
     *            second {@link Image}
     * @return true if <code>image1</code> is equal to <code>image2</code>
     */
    public static boolean areEqualImages(Image image1, Image image2) {
        byte[] expectedData = image1.getImageData().data;
        byte[] currentData = image2.getImageData().data;
        boolean areSameImages = expectedData.length == currentData.length;
        if (areSameImages) {
            for (int i = 0; i < expectedData.length && areSameImages; i++) {
                areSameImages = areSameImages && expectedData[i] == currentData[i];
            }
        }
        return areSameImages;
    }

    private DEdgeBeginNameEditPart getDEdgeBeginNameEditPart(AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart) {
        DEdgeBeginNameEditPart dEdgeBeginNameEditPart = null;
        for (Object child : abstractDiagramEdgeEditPart.getChildren()) {
            if (child instanceof DEdgeBeginNameEditPart) {
                dEdgeBeginNameEditPart = (DEdgeBeginNameEditPart) child;
                break;
            }
        }
        return dEdgeBeginNameEditPart;
    }

    private DEdgeNameEditPart getDEdgeCenterNameEditPart(AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart) {
        DEdgeNameEditPart dEdgeCenterNameEditPart = null;
        for (Object child : abstractDiagramEdgeEditPart.getChildren()) {
            if (child instanceof DEdgeNameEditPart) {
                dEdgeCenterNameEditPart = (DEdgeNameEditPart) child;
                break;
            }
        }
        return dEdgeCenterNameEditPart;
    }

    private DEdgeEndNameEditPart getDEdgeEndNameEditPart(AbstractDiagramEdgeEditPart abstractDiagramEdgeEditPart) {
        DEdgeEndNameEditPart dEdgeEndNameEditPart = null;
        for (Object child : abstractDiagramEdgeEditPart.getChildren()) {
            if (child instanceof DEdgeEndNameEditPart) {
                dEdgeEndNameEditPart = (DEdgeEndNameEditPart) child;
                break;
            }
        }
        return dEdgeEndNameEditPart;
    }

    private void setLayers(DSemanticDiagram dSemanticDiagram, Diagram migrationDiagram) {
        List<Layer> activatedLayers = dSemanticDiagram.getActivatedLayers();

        // Remove layers from migration diagram
        migrationDiagram.getLayers().clear();

        for (Layer layer : activatedLayers) {
            org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer migrationLayer = MigrationmodelerFactory.eINSTANCE.createLayer();
            migrationLayer.setId(layer.getName());
            migrationLayer.setActivated(true);
            migrationDiagram.getLayers().add(migrationLayer);
        }

        List<Layer> allLayers = new ArrayList<Layer>(LayerHelper.getAllLayers(dSemanticDiagram.getDescription()));
        allLayers.removeAll(activatedLayers);

        for (Layer layer : allLayers) {
            org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer migrationLayer = MigrationmodelerFactory.eINSTANCE.createLayer();
            migrationLayer.setId(layer.getName());
            migrationLayer.setActivated(false);
            migrationDiagram.getLayers().add(migrationLayer);
        }
    }

    private void setFilters(DSemanticDiagram dSemanticDiagram, Diagram migrationDiagram) {
        List<FilterDescription> activatedFilters = dSemanticDiagram.getActivatedFilters();

        // Remove filters from migration diagram
        migrationDiagram.getFilters().clear();

        for (FilterDescription filterDescription : activatedFilters) {
            Filter migrationFilter = MigrationmodelerFactory.eINSTANCE.createFilter();
            migrationFilter.setId(filterDescription.getName());
            migrationFilter.setActivated(true);
            migrationDiagram.getFilters().add(migrationFilter);
        }

        List<FilterDescription> allFilters = new ArrayList<FilterDescription>(dSemanticDiagram.getDescription().getFilters());
        allFilters.removeAll(activatedFilters);

        for (FilterDescription filterDescription : allFilters) {
            Filter migrationFilter = MigrationmodelerFactory.eINSTANCE.createFilter();
            migrationFilter.setId(filterDescription.getName());
            migrationFilter.setActivated(false);
            migrationDiagram.getFilters().add(migrationFilter);
        }
    }

    private boolean isPresentInDiagram(AbstractRepresentation element, DSemanticDiagram diagram) {
        for (DDiagramElement diagramElement : diagram.getDiagramElements()) {
            if (diagramElement.getTarget().equals(element)) {
                return true;
            }
        }
        return false;
    }

}
