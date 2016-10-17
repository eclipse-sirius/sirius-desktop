/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts.refresh;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractNotSelectableShapeNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Icon refresher on semantic changes to manage when
 * XXXItemProvider.getImage()/getText() is customized to return different result
 * on semantic change
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LabelAndIconRefresher implements NotificationListener {

    private IGraphicalEditPart graphicalEditPart;

    public LabelAndIconRefresher(IGraphicalEditPart graphicalEditPart) {
        this.graphicalEditPart = graphicalEditPart;
        DiagramEventBroker broker = DiagramEventBroker.getInstance(graphicalEditPart.getEditingDomain());
        DDiagramElement dDiagramElement = getDDiagramElement(graphicalEditPart);
        if (broker != null && dDiagramElement != null) {
            for (EObject semanticElement : dDiagramElement.getSemanticElements()) {
                broker.addNotificationListener(semanticElement, this);
            }
            broker.addNotificationListener(dDiagramElement, ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS, this);
        }
    }

    private DDiagramElement getDDiagramElement(IGraphicalEditPart graphicalEditPart) {
        DDiagramElement dDiagramElement = null;
        if (graphicalEditPart instanceof IDiagramElementEditPart) {
            dDiagramElement = ((IDiagramElementEditPart) graphicalEditPart).resolveDiagramElement();
        } else if (graphicalEditPart.getParent() instanceof IDiagramElementEditPart) {
            dDiagramElement = ((IDiagramElementEditPart) graphicalEditPart.getParent()).resolveDiagramElement();
        }
        return dDiagramElement;
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (graphicalEditPart != null && graphicalEditPart.isActive()) {
            DDiagramElement dDiagramElement = getDDiagramElement(graphicalEditPart);
            final IPermissionAuthority auth = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(graphicalEditPart.getEditingDomain().getResourceSet());
            if (dDiagramElement != null && dDiagramElement.getSemanticElements().contains(notification.getNotifier()) && auth.canEditInstance(dDiagramElement)) {
                refreshLabelAndIcon(dDiagramElement);
            } else if (ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS == notification.getFeature()) {
                // Manage change on DRepresentationElement.semanticElements
                // reference
                DiagramEventBroker broker = DiagramEventBroker.getInstance(graphicalEditPart.getEditingDomain());
                if (notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.ADD_MANY) {
                    Object newValue = notification.getNewValue();
                    if (newValue instanceof Collection<?>) {
                        for (Object newVal : (Collection<?>) newValue) {
                            if (newVal instanceof EObject) {
                                broker.addNotificationListener((EObject) newVal, this);
                            }
                        }
                    } else if (newValue instanceof EObject) {
                        broker.addNotificationListener((EObject) newValue, this);
                    }
                } else if (notification.getEventType() == Notification.REMOVE || notification.getEventType() == Notification.REMOVE_MANY) {
                    Object oldValue = notification.getOldValue();
                    if (oldValue instanceof Collection<?>) {
                        for (Object oldVal : (Collection<?>) oldValue) {
                            if (oldVal instanceof EObject) {
                                broker.removeNotificationListener((EObject) oldVal, this);
                            }
                        }
                    } else if (oldValue instanceof EObject) {
                        broker.removeNotificationListener((EObject) oldValue, this);
                    }
                }
            }
        }
    }

    private void refreshLabelAndIcon(DDiagramElement dDiagramElement) {
        SiriusWrapLabel siriusWrapLabel = null;
        IDiagramElementEditPart diagramElementEditPart = null;
        if (graphicalEditPart instanceof AbstractDiagramNameEditPart) {
            if (graphicalEditPart.getFigure() instanceof SiriusWrapLabel) {
                siriusWrapLabel = (SiriusWrapLabel) graphicalEditPart.getFigure();
                diagramElementEditPart = (AbstractDiagramNameEditPart) graphicalEditPart;
            }
        } else if (graphicalEditPart instanceof AbstractNotSelectableShapeNodeEditPart) {
            EditPart parent = graphicalEditPart.getParent();
            if (parent instanceof IAbstractDiagramNodeEditPart) {
                diagramElementEditPart = (IAbstractDiagramNodeEditPart) parent;
                siriusWrapLabel = ((IAbstractDiagramNodeEditPart) diagramElementEditPart).getNodeLabel();
            }
        }
        if (diagramElementEditPart != null) {
            DiagramElementEditPartOperation.refreshLabelAndIcon(diagramElementEditPart, dDiagramElement, siriusWrapLabel);
        }
    }

    public void dispose() {
        DiagramEventBroker broker = DiagramEventBroker.getInstance(graphicalEditPart.getEditingDomain());
        if (broker != null) {
            DDiagramElement dDiagramElement = getDDiagramElement(graphicalEditPart);
            if (dDiagramElement != null) {
                broker.removeNotificationListener(dDiagramElement, ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS, this);
                EList<EObject> semanticElements = dDiagramElement.getSemanticElements();
                if (semanticElements != null) {
                    for (EObject semanticElement : semanticElements) {
                        broker.removeNotificationListener(semanticElement, this);
                    }
                }
            }

        }
        graphicalEditPart = null;
    }

}
