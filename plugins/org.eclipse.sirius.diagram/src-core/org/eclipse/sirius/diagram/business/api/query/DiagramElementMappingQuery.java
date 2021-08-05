/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.base.collect.MultipleCollection;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;

import com.google.common.collect.Iterators;

/**
 * A class aggregating all the queries (read-only!) having a
 * DiagramElementMapping as a starting point.
 * 
 * @author cbrun
 * 
 */
public class DiagramElementMappingQuery {

    private DiagramElementMapping mapping;

    /** A cache for semantic candidates. */
    private Collection<EObject> semantics;

    /**
     * Create a new query.
     * 
     * @param mapping
     *            the element to query.
     */
    public DiagramElementMappingQuery(DiagramElementMapping mapping) {
        this.mapping = mapping;
    }

    /**
     * Create a new query.
     * 
     * @param mapping
     *            the element to query.
     */
    public DiagramElementMappingQuery(EdgeMappingImport mapping) {
        this.mapping = new IEdgeMappingQuery(mapping).getEdgeMapping().get();
    }

    /**
     * return true if the given element is described by the current mapping. See
     * isInstanceOf to check for the whole mapping hiearchy.
     * 
     * @param element
     *            any mapping based element.
     * @return true if the given element is described by the current mapping.
     */
    public boolean isTypeOf(DMappingBased element) {
        DiagramElementMapping otherMapping = (DiagramElementMapping) element.getMapping();
        if (EqualityHelper.areEquals(mapping, otherMapping)) {
            return true;
        }
        return false;

    }

    /**
     * Return the drop tools in the mapping hierarchy.
     * 
     * @return the drop tools in the mapping hierarchy.
     */
    public Collection<ContainerDropDescription> getDropToolsInHierarchy() {
        Collection<ContainerDropDescription> descs = new ArrayList<ContainerDropDescription>();
        for (final DiagramElementMapping mappingInHier : hierachy()) {
            if (mappingInHier instanceof DragAndDropTargetDescription) {
                descs.addAll(((DragAndDropTargetDescription) mappingInHier).getDropDescriptions());
            }
        }
        if (mapping instanceof DragAndDropTargetDescription) {
            descs.addAll(((DragAndDropTargetDescription) mapping).getDropDescriptions());
        }
        return descs;
    }

    /**
     * Return the drops tools of the mapping.
     * 
     * @return the drop tools of the mapping.
     */
    public Collection<ContainerDropDescription> getDropTools() {
        Collection<ContainerDropDescription> dropTools = new ArrayList<ContainerDropDescription>();
        if (mapping instanceof AbstractMappingImport) {
            if (getDropToolsInHierarchy().isEmpty()) {
                if (mapping instanceof NodeMappingImport) {
                    dropTools.addAll(new DiagramElementMappingQuery(((NodeMappingImport) mapping).getImportedMapping()).getDropTools());
                } else if (mapping instanceof ContainerMappingImport) {
                    dropTools.addAll(new DiagramElementMappingQuery(((ContainerMappingImport) mapping).getImportedMapping()).getDropTools());
                }
            } else {
                dropTools.addAll(getDropToolsInHierarchy());
            }
        } else {
            dropTools.addAll(getDropToolsInHierarchy());
        }
        return dropTools;
    }

    /**
     * Return the paste tools in the mapping hierarchy.
     * 
     * @return the paste tools in the mapping hierarchy.
     */
    private Collection<PasteDescription> getPasteToolsInHierarchy() {
        Collection<PasteDescription> descs = new ArrayList<>();
        for (final DiagramElementMapping mappingInHier : hierachy()) {
            if (mappingInHier instanceof DragAndDropTargetDescription) {
                descs.addAll(((PasteTargetDescription) mappingInHier).getPasteDescriptions());
            }
        }
        descs.addAll(mapping.getPasteDescriptions());
        return descs;
    }

    /**
     * Return the paste tools targeting the the current mapping ( or its import
     * hierarchy).
     * 
     * @return the paste tools of the mapping.
     */
    public Collection<PasteDescription> getAllPasteTools() {
        Collection<PasteDescription> pasteTools = new ArrayList<>();
        Collection<PasteDescription> pasteToolsInHierarchy = getPasteToolsInHierarchy();

        if (mapping instanceof AbstractMappingImport) {
            if (pasteToolsInHierarchy.isEmpty()) {
                if (mapping instanceof NodeMappingImport) {
                    pasteTools.addAll(new DiagramElementMappingQuery(((NodeMappingImport) mapping).getImportedMapping()).getAllPasteTools());
                } else if (mapping instanceof ContainerMappingImport) {
                    pasteTools.addAll(new DiagramElementMappingQuery(((ContainerMappingImport) mapping).getImportedMapping()).getAllPasteTools());
                }
            } else {
                pasteTools.addAll(pasteToolsInHierarchy);
            }
        } else {
            pasteTools.addAll(pasteToolsInHierarchy);
        }
        return pasteTools;
    }

    /**
     * return true if the given element is described by the current mapping or
     * one of its super mappings. See isTypeOf to check only for one mapping.
     * 
     * @param element
     *            any mapping based element.
     * @return true if the given element is described by the current mapping or
     *         one of its super mappings.
     */
    public boolean isInstanceOf(DMappingBased element) {
        DiagramElementMapping otherMapping = (DiagramElementMapping) element.getMapping();
        return isSuperTypeOf(otherMapping);
    }

    /**
     * return true if this mapping (the query one) is the same as, or a super
     * type of, some other mapping.
     * 
     * @param otherMapping
     *            any other mapping.
     * @return true if the given mapping is a subtype of the query one.
     */
    public boolean isSuperTypeOf(DiagramElementMapping otherMapping) {
        boolean isSuperTypeOf = EqualityHelper.areEquals(mapping, otherMapping);
        Iterator<DiagramElementMapping> it = new DiagramElementMappingQuery(otherMapping).superTypes();
        while (it.hasNext() && !isSuperTypeOf) {
            if (EqualityHelper.areEquals(mapping, it.next())) {
                isSuperTypeOf = true;
            }
        }
        return isSuperTypeOf;
    }

    /**
     * Check if the {@code superType} is a super type of the current mapping.
     * 
     * @param superType
     *            The superType to check.
     * @return true if the {@code mapping} imports the superType, false
     *         otherwise.
     */
    public boolean isSubTypeOf(DiagramElementMapping superType) {
        boolean isSubTypeOf = false;
        for (Iterator<DiagramElementMapping> superTypes = superTypes(); superTypes.hasNext() && !isSubTypeOf; /* */) {
            DiagramElementMapping diagramElementMapping = superTypes.next();
            isSubTypeOf = diagramElementMapping.equals(superType);

        }
        return isSubTypeOf;
    }

    /**
     * browse the mappings from the most specific to the most general.
     * 
     * @return a collection to browse the mappings from the most specific to the
     *         most general.
     */
    public Iterable<DiagramElementMapping> hierachy() {
        return new SuperTypes(mapping);
    }

    /**
     * return an iterator browsing the mappings from the most specific to the
     * most general.
     * 
     * @return an iterator browsing the mappings from the most specific to the
     *         most general.
     */
    public Iterator<DiagramElementMapping> superTypes() {
        return new SuperTypesIterator(mapping);
    }

    private static class SuperTypes implements Iterable<DiagramElementMapping> {

        private DiagramElementMapping mapping;

        SuperTypes(final DiagramElementMapping mapping) {
            this.mapping = mapping;
        }

        @Override
        public Iterator<DiagramElementMapping> iterator() {
            return new SuperTypesIterator(mapping);
        }

    }

    /**
     * Iterator to browse the mapping's hierarchy.
     * 
     * @author cbrun
     * 
     */
    private static class SuperTypesIterator implements Iterator<DiagramElementMapping> {
        private DiagramElementMapping cur;

        SuperTypesIterator(DiagramElementMapping map) {
            this.cur = map;
        }

        @Override
        public boolean hasNext() {
            return doGetNext() != null;
        }

        @Override
        public DiagramElementMapping next() {
            cur = doGetNext();
            if (cur == null) {
                throw new NoSuchElementException();
            }
            return cur;
        }

        private DiagramElementMapping doGetNext() {
            DiagramElementMapping next = null;
            if (cur instanceof NodeMappingImport) {
                next = ((NodeMappingImport) cur).getImportedMapping();
            } else if (cur instanceof EdgeMappingImport) {
                IEdgeMappingQuery query = new IEdgeMappingQuery(((EdgeMappingImport) cur).getImportedMapping());
                next = query.getEdgeMapping().get();
            } else if (cur instanceof ContainerMappingImport) {
                next = ((ContainerMappingImport) cur).getImportedMapping();
            }
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * return true if the mappings are sharing the same most generic type.
     * 
     * @param other
     *            any other mapping.
     * @return true if the mappings are sharing the same most generic type.
     */
    public boolean areInSameHiearchy(DiagramElementMapping other) {

        boolean isInSameHiearchy = false;

        if (mapping == other) {
            isInSameHiearchy = true;
        } else {
            DiagramElementMapping rootMapping1 = getRootMapping(mapping);
            DiagramElementMapping rootMapping2 = getRootMapping(other);

            /* compare the two roots */
            if (EqualityHelper.areEquals(rootMapping1, rootMapping2)) {
                isInSameHiearchy = true;
            }
        }
        return isInSameHiearchy;
    }

    /**
     * Get the root mapping (the highest mapping in the hierarchy of imported
     * mappings) of this mapping if it is a mapping import, self otherwise.
     * 
     * @return the root mapping
     */
    public DiagramElementMapping getRootMapping() {
        return getRootMapping(mapping);
    }

    /**
     * Get the root mapping (the highest mapping in the hierarchy of imported
     * mappings) of this mapping if it is a mapping import, self otherwise.
     * 
     * @param current
     *            the mapping
     * @return the root mapping
     */
    private DiagramElementMapping getRootMapping(DiagramElementMapping current) {
        Iterator<DiagramElementMapping> superTypes = new DiagramElementMappingQuery(current).superTypes();
        if (superTypes.hasNext()) {
            return Iterators.getLast(superTypes);
        } else {
            return current;
        }
    }

    /**
     * Get the domain class of the current mapping.
     * 
     * @return the domain class
     */
    public Option<String> getDomainClass() {
        String domainType = null;
        if (mapping instanceof AbstractNodeMapping) {
            domainType = ((AbstractNodeMapping) mapping).getDomainClass();
        } else if (mapping instanceof EdgeMapping && ((EdgeMapping) mapping).isUseDomainElement()) {
            domainType = ((EdgeMapping) mapping).getDomainClass();
        }
        return Options.newSome(domainType);
    }

    /**
     * Check if both "synchronized" attribute of the {@link DDiagram} and
     * "createElements" attribute of the {@link DiagramElementMapping} are true.
     * 
     * @param dDiagram
     *            the {@link DDiagram} to check
     * @return true if both true.
     */
    public boolean isSynchronizedAndCreateElement(DDiagram dDiagram) {
        boolean result = dDiagram != null && mapping != null;
        result = result && mapping.isCreateElements() && (dDiagram.isSynchronized() || mapping.isSynchronizationLock());
        return result;
    }

    /**
     * Check if both "synchronized" attribute of the parent {@link DDiagram} of
     * the {@link DDiagramElement} and "createElements" attribute of the
     * {@link DiagramElementMapping} are true.
     * 
     * @param dDiagramElement
     *            the {@link DDiagramElement} to check its parent
     *            {@link DDiagram}
     * @return true if both true.
     */
    public boolean isSynchronizedAndCreateElement(DDiagramElement dDiagramElement) {
        return dDiagramElement != null && isSynchronizedAndCreateElement(dDiagramElement.getParentDiagram());
    }

    /**
     * return true if the mapping defines a semantic candidates expression.
     * 
     * @return true if the mapping defines a semantic candidates expression.
     */
    public boolean hasCandidatesExpression() {
        return mapping.getSemanticCandidatesExpression() != null && !StringUtil.isEmpty(mapping.getSemanticCandidatesExpression());
    }

    /**
     * 
     * Evaluate the semantic candidates for the given context.
     * 
     * @param diagram
     *            the {@link DSemanticDiagram}
     * @param interpreter
     *            the {@link IInterpreter} to evaluate candidates expression
     * @param containerView
     *            the container of the potential views.
     * @return a collection of semantic candidates.
     */
    public Collection<EObject> evaluateCandidateExpression(DSemanticDiagram diagram, IInterpreter interpreter, DragAndDropTarget containerView) {
        EObject rootContent = getRootContent(diagram, interpreter, containerView);
        final Collection<EObject> semanticCandidatesEvaluation = new MultipleCollection<EObject>();
        if (rootContent != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);

            Collection<EObject> candidates = null;
            try {
                candidates = interpreter.evaluateCollection(rootContent, mapping.getSemanticCandidatesExpression());
            } catch (final EvaluationException e) {
                SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.DiagramElementMappingQuery_mappingCandidateExpressionEvaluationErrorMsg, mapping.getSemanticCandidatesExpression()), e);
            }
            if (candidates != null && !candidates.isEmpty()) {
                semanticCandidatesEvaluation.addAll(candidates);
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
        }
        return semanticCandidatesEvaluation;
    }

    private EObject getRootContent(DSemanticDiagram diagram, IInterpreter interpreter, DragAndDropTarget containerView) {
        EObject rootContent = ((DSemanticDecorator) containerView).getTarget();
        /*
         * FIXME : we should do that but the old behavior was buggy :S final
         * String rootContentExpression =
         * diagram.getDescription().getRootExpression(); if
         * (!StringUtil.isEmpty(rootContentExpression)) {
         * this.interpreter.setVariable
         * (IAcceleoInterpreterSiriusVariables.VIEWPOINT, this.diagram); try {
         * rootContent =
         * ENodeHelper.getAsEObject(ENodeHelper.evaluate(this.interpreter,
         * rootContent, rootContentExpression)); } catch (EvaluationException e)
         * {SiriusPlugin.getDefault().error(IAcceleoInterpreterMessages.
         * EVALUATION_ERROR_ON_SEMANTIC_ITERATOR_RETRIEVING, e); }
         * this.interpreter.unSetVariable(IAcceleoInterpreterSiriusVariables.
         * VIEWPOINT); }
         */
        // The rootExpression evaluation was disabled for EdgeMapping in
        // DEdgeSynchronizedHelper, don't know why
        if (containerView == diagram && !(mapping instanceof EdgeMapping)) {
            final String rootContentExpression = ((DDiagram) containerView).getDescription().getRootExpression();
            if (!StringUtil.isEmpty(rootContentExpression)) {
                interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
                EObject computedRoot = null;
                try {
                    computedRoot = interpreter.evaluateEObject(rootContent, diagram.getDescription().getRootExpression());
                } catch (final EvaluationException e) {
                    SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.DiagramElementMappingQuery_diagramRootExpressionEvaluationErrorMsg, diagram.getDescription().getRootExpression()),
                            e);
                }
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
                if (computedRoot != null) {
                    rootContent = computedRoot;
                }
            }
        }
        return rootContent;
    }

    /**
     * Get all candidates corresponding to the the given mapping in the current
     * session.
     * 
     * @param session
     *            the {@link Session} to use
     * @param modelAccessor
     *            the {@link ModelAccessor} to use
     * 
     * @return candidates
     */
    public Collection<EObject> getAllCandidates(Session session, ModelAccessor modelAccessor) {
        Option<String> domainClass = getDomainClass();
        if (semantics == null) {
            semantics = new ArrayList<EObject>();
            if (domainClass.some()) {
                for (final Resource resource : session.getSemanticResources()) {
                    for (EObject root : resource.getContents()) {
                        semantics.addAll(modelAccessor.eAllContents(root, domainClass.get()));
                    }
                }
            }
        }
        return semantics;
    }

}
