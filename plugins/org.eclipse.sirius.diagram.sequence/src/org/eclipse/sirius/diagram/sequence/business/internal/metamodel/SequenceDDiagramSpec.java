/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreEList;

import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DDiagramElementContainer;
import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeListElement;
import org.eclipse.sirius.DRepresentationElement;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.internal.metamodel.helper.DSemanticDiagramHelper;
import org.eclipse.sirius.business.internal.metamodel.operations.DDiagramSpecOperations;
import org.eclipse.sirius.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.business.internal.query.DModelElementInternalQuery;
import org.eclipse.sirius.description.ContainerMapping;
import org.eclipse.sirius.description.DAnnotation;
import org.eclipse.sirius.description.DragAndDropTargetDescription;
import org.eclipse.sirius.description.EdgeMapping;
import org.eclipse.sirius.description.NodeMapping;
import org.eclipse.sirius.description.concern.ConcernDescription;
import org.eclipse.sirius.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl;

/**
 * Implementation of <code>SequenceDDiagram</code>.
 * 
 * @author pcdavid
 */
public class SequenceDDiagramSpec extends SequenceDDiagramImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DRepresentationImpl#getOwnedRepresentationElements()
     */
    @Override
    public EList<DRepresentationElement> getOwnedRepresentationElements() {
        final Collection<DDiagramElement> result = getOwnedDiagramElements();
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), SiriusPackage.eINSTANCE.getDRepresentation_OwnedRepresentationElements(), result.size(),
                result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DRepresentationImpl#getRepresentationElements()
     */
    @Override
    public EList<DRepresentationElement> getRepresentationElements() {
        final Collection<DDiagramElement> result = getDiagramElements();
        return new EcoreEList.UnmodifiableEList<DRepresentationElement>(eInternalContainer(), SiriusPackage.eINSTANCE.getDRepresentation_RepresentationElements(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getDiagramElements()
     */
    @Override
    public EList<DDiagramElement> getDiagramElements() {
        final Collection<DDiagramElement> result = new DDiagramInternalQuery(this).getDiagramElements();
        return new EcoreEList.UnmodifiableEList<DDiagramElement>(eInternalContainer(), SiriusPackage.eINSTANCE.getDDiagram_DiagramElements(), result.size(), result.toArray());
    }

    /**
     * Create the contents of the viewpoint.
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#createContents()
     */
    @Override
    public void createContents() {
        refresh();
    }

    /**
     * Create the contents of the viewpoint with the specified root element.
     * 
     * @param rootElement
     *            the root element.
     * @see org.eclipse.sirius.impl.DDiagramImpl#createContents(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void createContents(final EObject rootElement) {
        refresh();
    }

    /**
     * Update the content of the viewpoint.
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#updateContent()
     */
    @Override
    public void updateContent() {
        refresh();
    }

    /**
     * Clean the viewpoint, delete all elements that are obsolete.
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#clean()
     */
    @Override
    public void clean() {
        DSemanticDiagramHelper.clean(this);
    }

    /**
     * Refresh the viewpoint.
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#refresh()
     */
    @Override
    public void refresh() {
        DialectManager.INSTANCE.refresh(this, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DSemanticDiagramImpl#getRootContent()
     */
    @Override
    public EObject getRootContent() {
        return DSemanticDiagramHelper.getRootContent(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DRepresentationImpl#getDAnnotation(String)
     */
    @Override
    public DAnnotation getDAnnotation(String source) {
        return new DModelElementInternalQuery(this).getDAnnotation(source);
    }

    /*
     * Behavior inherited from DDiagramSpec
     */

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getEdges()
     */
    @Override
    public EList<DEdge> getEdges() {
        final Collection<DEdge> result = new DDiagramInternalQuery(this).getEdges();
        return new EcoreEList.UnmodifiableEList<DEdge>(eInternalContainer(), SiriusPackage.eINSTANCE.getDDiagram_Edges(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getNodes()
     */
    @Override
    public EList<DNode> getNodes() {
        final Collection<DNode> result = new DDiagramInternalQuery(this).getNodes();
        return new EcoreEList.UnmodifiableEList<DNode>(eInternalContainer(), SiriusPackage.eINSTANCE.getDDiagram_Nodes(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getNodeListElements()
     */
    @Override
    public EList<DNodeListElement> getNodeListElements() {
        final Collection<DNodeListElement> result = new DDiagramInternalQuery(this).getNodeListElements();
        return new EcoreEList.UnmodifiableEList<DNodeListElement>(eInternalContainer(), SiriusPackage.eINSTANCE.getDDiagram_NodeListElements(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getContainers()
     */
    @Override
    public EList<DDiagramElementContainer> getContainers() {
        final Collection<DDiagramElementContainer> result = new DDiagramInternalQuery(this).getContainers();
        return new EcoreEList.UnmodifiableEList<DDiagramElementContainer>(eInternalContainer(), SiriusPackage.eINSTANCE.getDDiagram_Containers(), result.size(), result.toArray());

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getNodesFromMapping(org.eclipse.sirius.description.NodeMapping)
     */
    @Override
    public EList<DNode> getNodesFromMapping(final NodeMapping mapping) {
        return DDiagramSpecOperations.getNodesFromMapping(this, mapping);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getEdgesFromMapping(org.eclipse.sirius.description.EdgeMapping)
     */
    @Override
    public EList<DEdge> getEdgesFromMapping(final EdgeMapping mapping) {
        return DDiagramSpecOperations.getEdgesFromMapping(this, mapping);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getContainersFromMapping(org.eclipse.sirius.description.ContainerMapping)
     */
    @Override
    public EList<DDiagramElementContainer> getContainersFromMapping(final ContainerMapping mapping) {
        return DDiagramSpecOperations.getContainersFromMapping(this, mapping);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#validate()
     */
    @Override
    public boolean validate() {
        return DDiagramSpecOperations.validate(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getAllFilters()
     */
    @Override
    public EList<FilterDescription> getAllFilters() {
        final Collection<FilterDescription> result = new DDiagramInternalQuery(this).getAllFilters();
        return new EcoreEList.UnmodifiableEList<FilterDescription>(eInternalContainer(), SiriusPackage.eINSTANCE.getDDiagram_AllFilters(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#setCurrentConcern(org.eclipse.sirius.description.concern.ConcernDescription)
     */
    @Override
    public void setCurrentConcern(final ConcernDescription newCurrentConcern) {
        super.setCurrentConcern(newCurrentConcern);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#getDragAndDropDescription()
     */
    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return new DDiagramInternalQuery(this).getDragAndDropDescription();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.impl.DDiagramImpl#findSiriusElements(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EClass)
     */
    @Override
    public EList<DDiagramElement> findDiagramElements(final EObject semanticElement, final EClass type) {
        return DDiagramSpecOperations.findDiagramElements(this, semanticElement, type);
    }

}
