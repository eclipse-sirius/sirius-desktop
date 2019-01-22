/*******************************************************************************
* Copyright (c) 2011, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.refresh;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DiagramPackage;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

/**
 * Pridicate to filter {@link Notification} concerning only the DSemanticDiagram & DDiagramElement.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DSemanticDiagramScopePredicate implements Predicate<Notification> {

    private Set<EStructuralFeature> featureToContainDDiagramElements = Sets.newLinkedHashSet();

    /**
     * Create the predicate.
     */
    public DSemanticDiagramScopePredicate() {
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDNodeList_OwnedElements());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDEdge_SourceNode());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDEdge_TargetNode());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDNodeContainer_ChildrenPresentation());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDDiagramElementContainer_Containers());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDNode_OwnedStyle());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDEdge_OwnedStyle());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDNodeListElement_OwnedStyle());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getEdgeTarget_IncomingEdges());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getEdgeTarget_OutgoingEdges());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDDiagram_ActivatedFilters());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDDiagram_ActivatedLayers());
        featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters());
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
    @Override
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
