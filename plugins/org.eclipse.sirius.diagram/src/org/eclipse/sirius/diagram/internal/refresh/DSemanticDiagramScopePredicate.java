/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.refresh;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.NotationPackage;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Pridicate to filter {@link Notification} concerning only the DSemanticDiagram
 * & DDiagramElement.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DSemanticDiagramScopePredicate implements Predicate<Notification> {

    private Set<EReference> featureToContainDDiagramElements = Sets.newLinkedHashSet();

    /**
     * Create the predicate.
     */
    public DSemanticDiagramScopePredicate() {
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDDiagram_OwnedDiagramElements());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDNodeList_OwnedElements());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDEdge_SourceNode());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDEdge_TargetNode());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDDiagramElementContainer_Containers());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDNode_OwnedStyle());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDEdge_OwnedStyle());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDNodeListElement_OwnedStyle());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getEdgeTarget_IncomingEdges());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getEdgeTarget_OutgoingEdges());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDDiagram_ActivatedFilters());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDDiagram_ActivatedLayers());
        featureToContainDDiagramElements.add(ViewpointPackage.eINSTANCE.getDDiagramElement_GraphicalFilters());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getEdge_Source());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getEdge_Target());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getView_PersistedChildren());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getView_TransientChildren());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getView_SourceEdges());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getView_TargetEdges());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getDiagram_PersistedEdges());
        featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getDiagram_TransientEdges());
    }

    /**
     * {@inheritDoc}
     */
    public boolean apply(Notification input) {
        if (!input.isTouch()) {
            switch (input.getEventType()) {
            case Notification.SET:
            case Notification.UNSET:
            case Notification.ADD:
            case Notification.ADD_MANY:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
            case Notification.MOVE:
                return featureToContainDDiagramElements.contains(input.getFeature());
            default:
                break;
            }
        }
        return false;
    }
}
