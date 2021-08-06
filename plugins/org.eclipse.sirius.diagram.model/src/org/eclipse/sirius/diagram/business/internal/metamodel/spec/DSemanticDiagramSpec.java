/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.internal.query.model.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl;
import org.eclipse.sirius.model.business.internal.query.DModelElementInternalQuery;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;

/**
 * Operations of semantic viewpoints.
 *
 * @author mchauvin, cbrun, ymortier
 */
public class DSemanticDiagramSpec extends DSemanticDiagramImpl {
    @Override
    public EList<DRepresentationElement> getOwnedRepresentationElements() {
        final Collection<DDiagramElement> result = getOwnedDiagramElements();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDRepresentation_OwnedRepresentationElements(), result.size(),
                result.toArray());
    }

    @Override
    public EList<DRepresentationElement> getRepresentationElements() {
        final Collection<DDiagramElement> result = getDiagramElements();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDRepresentation_RepresentationElements(), result.size(), result.toArray());
    }

    @Override
    public EList<DDiagramElement> getDiagramElements() {
        final Collection<DDiagramElement> result = new DDiagramInternalQuery(this).getDiagramElements();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_DiagramElements(), result.size(), result.toArray());
    }

    /*
     * Behavior inherited from DDiagramSpec
     */

    @Override
    public EList<DEdge> getEdges() {
        final Collection<DEdge> result = new DDiagramInternalQuery(this).getEdges();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Edges(), result.size(), result.toArray());
    }

    @Override
    public EList<DNode> getNodes() {
        final Collection<DNode> result = new DDiagramInternalQuery(this).getNodes();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Nodes(), result.size(), result.toArray());
    }

    @Override
    public EList<DNodeListElement> getNodeListElements() {
        final Collection<DNodeListElement> result = new DDiagramInternalQuery(this).getNodeListElements();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_NodeListElements(), result.size(), result.toArray());
    }

    @Override
    public EList<DDiagramElementContainer> getContainers() {
        final Collection<DDiagramElementContainer> result = new DDiagramInternalQuery(this).getContainers();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Containers(), result.size(), result.toArray());

    }

    @Override
    public DAnnotation getDAnnotation(String source) {
        return new DModelElementInternalQuery(this).getDAnnotation(source);
    }

    @Override
    public EList<FilterDescription> getAllFilters() {
        final Collection<FilterDescription> result = new DDiagramInternalQuery(this).getAllFilters();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_AllFilters(), result.size(), result.toArray());
    }

    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return new DDiagramInternalQuery(this).getDragAndDropDescription();
    }
}
