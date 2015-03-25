/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.internal.query.DModelElementInternalQuery;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramSpecOperations;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.impl.DDiagramImpl;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DAnnotation;

/**
 * Implementation of DDiagramImpl.java.
 * 
 * @author cbrun mchauvin ymortier
 */
public class DDiagramSpec extends DDiagramImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getOwnedRepresentationElements()
     */
    @Override
    public EList<DRepresentationElement> getOwnedRepresentationElements() {
        final Collection<DDiagramElement> result = getOwnedDiagramElements();
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDRepresentation_OwnedRepresentationElements(), result.size(),
                result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getRepresentationElements()
     */
    @Override
    public EList<DRepresentationElement> getRepresentationElements() {
        final Collection<DDiagramElement> result = getDiagramElements();
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), ViewpointPackage.eINSTANCE.getDRepresentation_RepresentationElements(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getDiagramElements()
     */
    @Override
    public EList<DDiagramElement> getDiagramElements() {
        final Collection<DDiagramElement> result = new DDiagramInternalQuery(this).getDiagramElements();
        return new EcoreEList.UnmodifiableEList<DDiagramElement>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_DiagramElements(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getEdges()
     */
    @Override
    public EList<DEdge> getEdges() {
        final Collection<DEdge> result = new DDiagramInternalQuery(this).getEdges();
        return new EcoreEList.UnmodifiableEList<DEdge>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Edges(), result.size(), result.toArray());

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getNodes()
     */
    @Override
    public EList<DNode> getNodes() {
        final Collection<DNode> result = new DDiagramInternalQuery(this).getNodes();
        return new EcoreEList.UnmodifiableEList<DNode>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Nodes(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getNodeListElements()
     */
    @Override
    public EList<DNodeListElement> getNodeListElements() {
        final Collection<DNodeListElement> result = new DDiagramInternalQuery(this).getNodeListElements();
        return new EcoreEList.UnmodifiableEList<DNodeListElement>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_NodeListElements(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getContainers()
     */
    @Override
    public EList<DDiagramElementContainer> getContainers() {
        final Collection<DDiagramElementContainer> result = new DDiagramInternalQuery(this).getContainers();
        return new EcoreEList.UnmodifiableEList<DDiagramElementContainer>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Containers(), result.size(), result.toArray());

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getNodesFromMapping(org.eclipse.sirius.viewpoint.description.NodeMapping)
     */
    @Override
    public EList<DNode> getNodesFromMapping(final NodeMapping mapping) {
        return DDiagramSpecOperations.getNodesFromMapping(this, mapping);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getEdgesFromMapping(org.eclipse.sirius.viewpoint.description.EdgeMapping)
     */
    @Override
    public EList<DEdge> getEdgesFromMapping(final EdgeMapping mapping) {
        return DDiagramSpecOperations.getEdgesFromMapping(this, mapping);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getContainersFromMapping(org.eclipse.sirius.viewpoint.description.ContainerMapping)
     */
    @Override
    public EList<DDiagramElementContainer> getContainersFromMapping(final ContainerMapping mapping) {
        return DDiagramSpecOperations.getContainersFromMapping(this, mapping);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#refresh()
     */
    @Override
    public void refresh() {
        DialectManager.INSTANCE.refresh(this, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getAllFilters()
     */
    @Override
    public EList<FilterDescription> getAllFilters() {
        final Collection<FilterDescription> result = new DDiagramInternalQuery(this).getAllFilters();
        return new EcoreEList.UnmodifiableEList<FilterDescription>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_AllFilters(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#setCurrentConcern(org.eclipse.sirius.viewpoint.description.concern.ConcernDescription)
     */
    @Override
    public void setCurrentConcern(final ConcernDescription newCurrentConcern) {
        super.setCurrentConcern(newCurrentConcern);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramImpl#getDragAndDropDescription()
     */
    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return new DDiagramInternalQuery(this).getDragAndDropDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DRepresentationImpl#getDAnnotation(String)
     */
    @Override
    public DAnnotation getDAnnotation(String source) {
        return new DModelElementInternalQuery(this).getDAnnotation(source);
    }

}
