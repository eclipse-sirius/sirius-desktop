/*******************************************************************************
 * Copyright (c) 2016, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.services;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.ConnectorStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.Style;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * An {@link IExternalJavaAction} used to test a specific layout.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ExternalJavaActionSpecificLayout implements IExternalJavaAction {
    private static final String DDIAGRAM_PARAM = "ddiagram";

    private Node getGMFNode(DDiagramElement dDiagramElement, ECrossReferenceAdapter xref) {
        for (EStructuralFeature.Setting setting : Iterables.filter(xref.getInverseReferences(dDiagramElement), new Predicate<EStructuralFeature.Setting>() {
            @Override
            public boolean apply(Setting input) {
                return input != null && input.getEStructuralFeature().getName().equals("element");
            }
        })) {
            return (Node) setting.getEObject();
        }
        return null;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        Diagram self = (Diagram) selections.iterator().next();
        Session sess = SessionManager.INSTANCE.getSession(self);
        if (sess != null) {
            ECrossReferenceAdapter xref = sess.getSemanticCrossReferencer();
            Object dDiagramObjectParam = parameters.get(DDIAGRAM_PARAM);
            if (dDiagramObjectParam instanceof DDiagram) {
                DDiagram diagram = (DDiagram) dDiagramObjectParam;

                for (DNode dNode : diagram.getNodes()) {
                    Node node = getGMFNode(dNode, xref);
                    if (node != null) {
                        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                        if (layoutConstraint instanceof Location) {
                            if (dNode.getTarget() instanceof Bordered) {
                                if ("Interface11".equals(((Bordered) dNode.getTarget()).getId())) {
                                    // Change routing style of edge from
                                    // Interface11 to Interface12
                                    Style style = dNode.getOutgoingEdges().get(0).getStyle();
                                    ((EdgeStyle) style).setRoutingStyle(EdgeRouting.MANHATTAN_LITERAL);
                                    ((EdgeStyle) style).getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName());
                                    final ConnectorStyle connectorStyle = (ConnectorStyle) ((Edge) node.getSourceEdges().get(0)).getStyle(NotationPackage.eINSTANCE.getConnectorStyle());
                                    if (connectorStyle != null) {
                                        connectorStyle.setRouting(Routing.RECTILINEAR_LITERAL);
                                    }
                                }
                            } else if ("Class1".equals(((org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node) dNode.getTarget()).getId())) {
                                // Move Class1
                                ((Location) layoutConstraint).setX(50);
                                ((Location) layoutConstraint).setY(90);
                                // Move edge from Class1 to Class11
                                RelativeBendpoints result = NotationFactory.eINSTANCE.createRelativeBendpoints();
                                List<RelativeBendpoint> relativeBendpoints = new LinkedList<RelativeBendpoint>();
                                relativeBendpoints.add(new RelativeBendpoint(51, 0, -159, 0));
                                relativeBendpoints.add(new RelativeBendpoint(130, 0, -80, 0));
                                result.setPoints(relativeBendpoints);
                                Bendpoints oldBendpoints = ((Edge) node.getSourceEdges().get(0)).getBendpoints();
                                if (oldBendpoints instanceof RelativeBendpoints && result instanceof RelativeBendpoints) {
                                    // Use this method to allow correct notification handle in
                                    // org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart.handleNotificationEvent(Notification)
                                    // and induce a refresh of the edge figure.
                                    ((RelativeBendpoints) oldBendpoints).setPoints(((RelativeBendpoints) result).getPoints());
                                } else {
                                    // Fallback but seems not necessary
                                    ((Edge) node.getSourceEdges().get(0)).setBendpoints(result);
                                }
                                // Change its routing style
                                Style style = dNode.getOutgoingEdges().get(0).getStyle();
                                ((EdgeStyle) style).setRoutingStyle(EdgeRouting.MANHATTAN_LITERAL);
                                ((EdgeStyle) style).getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName());
                                final ConnectorStyle connectorStyle = (ConnectorStyle) ((Edge) node.getSourceEdges().get(0)).getStyle(NotationPackage.eINSTANCE.getConnectorStyle());
                                if (connectorStyle != null) {
                                    connectorStyle.setRouting(Routing.RECTILINEAR_LITERAL);
                                }
                            } else if ("Class11".equals(((org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node) dNode.getTarget()).getId())) {
                                // Move Class11
                                ((Location) layoutConstraint).setX(17);
                                ((Location) layoutConstraint).setY(24);
                            }
                        }
                    }
                }
                for (DDiagramElementContainer dContainer : diagram.getContainers()) {
                    Node node = getGMFNode(dContainer, xref);
                    if (node != null) {
                        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                        // Move and resize P1
                        if ("P1".equals(((Container) dContainer.getTarget()).getId())) {
                            if (layoutConstraint instanceof Location) {
                                ((Location) layoutConstraint).setX(200);
                                ((Location) layoutConstraint).setY(20);
                            }
                            if (layoutConstraint instanceof Size) {
                                ((Size) layoutConstraint).setHeight(300);
                            }
                        }
                    }
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.sirius.tools.api.ui.IExternalJavaAction#canExecute(java.util.
     * Collection)
     */
    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return selections.size() == 1 && selections.iterator().next() instanceof Diagram;
    }
}
