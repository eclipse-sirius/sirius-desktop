/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Implementation of DDiagramImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public final class DDiagramSpecOperations {

    /**
     * Avoid instantiation.
     */
    private DDiagramSpecOperations() {

    }

    /**
     * Return the analysis of the specified viewpoint.
     * 
     * @param diagram
     *            the viewpoint.
     * @return the analysis of the specified viewpoint.
     */
    public static DView basicGetView(final DDiagram diagram) {
        EObject cur = diagram.eContainer();
        DView result = null;
        while (cur != null && result == null) {
            if (cur instanceof DView) {
                result = (DView) cur;
            }
            cur = cur.eContainer();
        }
        return result;
    }

    /**
     * Cleans the specified viewpoint.
     * 
     * @param vp
     *            the viewpoint to clean.
     */
    public static void clean(final DDiagram vp) {
        // redefine if needed
    }

    /**
     * Returns all nodes that are in the specified viewpoint and that have been
     * created from the specified mapping.
     * 
     * @param vp
     *            the viewpoint.
     * @param mapping
     *            the mapping.
     * @return all nodes that are in the specified viewpoint and that have been
     *         created from the specified mapping.
     */
    public static EList<DNode> getNodesFromMapping(final DDiagram vp, final NodeMapping mapping) {
        final EList<DNode> result = new BasicEList<DNode>();
        final Iterator<DNode> it = vp.getNodes().iterator();
        while (it.hasNext()) {
            final DNode node = it.next();
            if (node.getActualMapping() == mapping) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * Returns all edges that are in the specified viewpoint and that have been
     * created from the specified mapping or by an imported mapping of the
     * specified mapping or by a mapping which import the specified mapping.
     * 
     * @param vp
     *            the viewpoint.
     * @param mapping
     *            the mapping.
     * @return all edges that are in the specified viewpoint and that have been
     *         created from the specified mapping.
     */
    public static EList<DEdge> getEdgesFromMapping(final DDiagram vp, final EdgeMapping mapping) {
        final EList<DEdge> result = new BasicEList<DEdge>();
        IEdgeMapping mappingToCompare = mapping;
        if (mapping instanceof EdgeMappingImportWrapper) {
            mappingToCompare = ((EdgeMappingImportWrapper) mapping).getWrappedEdgeMappingImport();
        }
        final Iterator<DEdge> it = vp.getEdges().iterator();
        while (it.hasNext()) {
            final DEdge edge = it.next();
            boolean importedOrImport = false;

            IEdgeMapping edgeMapping = edge.getActualMapping();
            if (edgeMapping instanceof EdgeMappingImportWrapper) {
                edgeMapping = ((EdgeMappingImportWrapper) edgeMapping).getWrappedEdgeMappingImport();
            }

            if (mappingToCompare instanceof EdgeMappingImport) {
                importedOrImport = LayerHelper.isImported(edgeMapping, (EdgeMappingImport) mappingToCompare);
            }
            if (!importedOrImport && edgeMapping instanceof EdgeMappingImport) {
                importedOrImport = LayerHelper.isImported(mappingToCompare, (EdgeMappingImport) edgeMapping);
            }
            if (edgeMapping == mappingToCompare || importedOrImport) {
                result.add(edge);
            }
        }
        return result;
    }

    /**
     * Returns all containers that are in the specified viewpoint and that have
     * been created from the specified mapping.
     * 
     * @param vp
     *            the viewpoint.
     * @param mapping
     *            the mapping.
     * @return all containers that are in the specified viewpoint and that have
     *         been created from the specified mapping.
     */
    public static EList<DDiagramElementContainer> getContainersFromMapping(final DDiagram vp, final ContainerMapping mapping) {
        final EList<DDiagramElementContainer> result = new BasicEList<DDiagramElementContainer>();
        final Iterator<DDiagramElementContainer> it = vp.getContainers().iterator();
        while (it.hasNext()) {

            final DDiagramElementContainer container = it.next();
            if (container.getActualMapping() == mapping) {
                result.add(container);
            }
        }
        return result;
    }

    /**
     * Validates the specified diagram.
     * 
     * @param diagram
     *            the diagram to validate.
     * @return <code>true</code> if the diagram is valid.
     */
    public static boolean validate(final DDiagram diagram) {
        throw new UnsupportedOperationException("Should no more be called");
    }

    // /**
    // * Defines the current concern of the specified diagram. Updates filters,
    // * behaviors and rules of the diagram.
    // *
    // * @param diagram
    // * the diagram.
    // * @param newCurrentConcern
    // * the concern of the viewpoint.
    // */
    // public static void setCurrentConcern(final DDiagram diagram, final
    // ConcernDescription newCurrentConcern) {
    // diagram.setCurrentConcern(newCurrentConcern);
    //
    // diagram.getActivatedFilters().clear();
    // diagram.getActivatedRules().clear();
    // diagram.getActivateBehaviors().clear();
    // if (newCurrentConcern != null) {
    // /*
    // * We want to activate the filters associated with the concern...
    // */
    // diagram.getActivatedFilters().addAll(newCurrentConcern.getFilters());
    // /*
    // * We want to activate the validation rules associated with the
    // * concern...
    // */
    // diagram.getActivatedRules().addAll(newCurrentConcern.getRules());
    // /*
    // * We want to activate the behaviors associated with the concern...
    // */
    // diagram.getActivateBehaviors().addAll(newCurrentConcern.getBehaviors());
    // }
    //
    // }
    //
    // /**
    // * Reset the current concern of the specified diagram. Keep filters,
    // * behaviors and rules of the diagram.
    // *
    // * @param diagram
    // * the diagram.
    // * @param newCurrentConcern
    // * the concern of the viewpoint.
    // */
    // public static void resetCurrentConcern(final DDiagram diagram, final
    // ConcernDescription newCurrentConcern) {
    // diagram.setCurrentConcern(newCurrentConcern);
    // }

    /**
     * Finds all view point elements that have the specified semantic element
     * has target and that are instances of the specified type.
     * 
     * @param diagram
     *            the diagram.
     * @param semanticElement
     *            the semantic element.
     * @param type
     *            the type.
     * @return all view point elements that have the specified semantic element
     *         has target and that are instances of the specified type.
     */
    public static EList<DDiagramElement> findDiagramElements(final DDiagram diagram, final EObject semanticElement, final EClass type) {
        List<EObject> tmpResult = Collections.emptyList();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(diagram);
        final String evaluation = "<%eAllContents(\"" + type.getName() + "\").filter(\"DSemanticDecorator\")[target == $semantic]%>";
        interpreter.setVariable("semantic", semanticElement);
        try {
            tmpResult = new ArrayList<EObject>(interpreter.evaluateCollection(diagram, evaluation));
        } catch (final EvaluationException e) {
            // do not log anything in this case
        }
        interpreter.unSetVariable("semantic");
        final EList<DDiagramElement> result = new BasicEList<DDiagramElement>(tmpResult.size());
        final Iterator<EObject> iterTmpResult = tmpResult.iterator();
        while (iterTmpResult.hasNext()) {
            final EObject next = iterTmpResult.next();
            if (next instanceof DDiagramElement) {
                result.add((DDiagramElement) next);
            }
        }
        return result;
    }

}
