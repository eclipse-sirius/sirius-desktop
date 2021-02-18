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
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
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
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * An {@link IExternalJavaAction} used to test a specific vertical layout.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ExternalJavaActionSpecificVerticalLayout implements IExternalJavaAction {
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
                // First iteration to retrieve container data needed after.
                DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
                IDiagramDialectGraphicalViewer viewer = (IDiagramDialectGraphicalViewer) editor.getDiagramGraphicalViewer();
                double xCenterReferenceLocation = 0;
                double width = 0;
                for (DDiagramElementContainer dContainer : diagram.getContainers()) {
                    Container container = (Container) dContainer.getTarget();
                    if ("container2".equals(container.getId())) {
                        for (DNodeContainerEditPart editPart : viewer.findEditPartsForElement(dContainer.getTarget(), DNodeContainerEditPart.class)) {
                            xCenterReferenceLocation = editPart.getFigure().getBounds().getCenter().preciseX();
                        }
                    } else {
                        for (DNodeContainerEditPart editPart : viewer.findEditPartsForElement(dContainer.getTarget(), DNodeContainerEditPart.class)) {
                            width = editPart.getFigure().getBounds().preciseWidth();
                        }
                    }
                }
                for (DDiagramElementContainer dContainer : diagram.getContainers()) {
                    Node node = getGMFNode(dContainer, xref);
                    if (node != null) {
                        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                        if ("container1container1container1container1container1container1".equals(((Container) dContainer.getTarget()).getId())) {
                            // Move it (center on other container)
                            if (layoutConstraint instanceof Location) {
                                // ((Location) layoutConstraint).setX(61);
                                ((Location) layoutConstraint).setX((int) Math.round(xCenterReferenceLocation - (width / 2)));
                            }
                            // Make the incoming edge rectilinear and straight
                            RelativeBendpoints result = NotationFactory.eINSTANCE.createRelativeBendpoints();
                            List<RelativeBendpoint> relativeBendpoints = new LinkedList<RelativeBendpoint>();
                            relativeBendpoints.add(new RelativeBendpoint(0, 34, 0, -186));
                            relativeBendpoints.add(new RelativeBendpoint(0, 186, 0, -34));
                            result.setPoints(relativeBendpoints);
                            Bendpoints oldBendpoints = ((Edge) node.getTargetEdges().get(0)).getBendpoints();
                            if (oldBendpoints instanceof RelativeBendpoints && result instanceof RelativeBendpoints) {
                                // Use this method to allow correct notification handle in
                                // org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart.handleNotificationEvent(Notification)
                                // and induce a refresh of the edge figure.
                                ((RelativeBendpoints) oldBendpoints).setPoints(((RelativeBendpoints) result).getPoints());
                            } else {
                                // Fallback but seems not necessary
                                ((Edge) node.getTargetEdges().get(0)).setBendpoints(result);
                            }
                            // Change its routing style
                            Style style = dContainer.getIncomingEdges().get(0).getStyle();
                            ((EdgeStyle) style).setRoutingStyle(EdgeRouting.MANHATTAN_LITERAL);
                            ((EdgeStyle) style).getCustomFeatures().add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE.getName());
                            final ConnectorStyle connectorStyle = (ConnectorStyle) ((Edge) node.getTargetEdges().get(0)).getStyle(NotationPackage.eINSTANCE.getConnectorStyle());
                            if (connectorStyle != null) {
                                connectorStyle.setRouting(Routing.RECTILINEAR_LITERAL);
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
