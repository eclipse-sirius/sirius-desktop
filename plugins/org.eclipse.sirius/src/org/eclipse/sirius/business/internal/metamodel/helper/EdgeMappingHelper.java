/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.helper;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.internal.metamodel.description.operations.EdgeMappingImportWrapper;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.tools.api.ui.resource.ISiriusMessages;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * Common utils between
 * {@link org.eclipse.sirius.business.internal.metamodel.description.spec.EdgeMappingSpec}
 * and {@link EdgeMappingImportWrapper}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class EdgeMappingHelper {

    private IInterpreter interpreter;

    /**
     * Create the helper.
     * 
     * @param interpreter
     *            interpreter used to evaluate expressions.
     */
    public EdgeMappingHelper(IInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * return the semantic element of the {@link EdgeTarget}.
     * 
     * @param edgeTarget
     *            the edge target.
     * @return the semantic element of the {@link EdgeTarget}.
     */
    public EObject getSemanticTarget(final EdgeTarget edgeTarget) {
        if (edgeTarget instanceof DSemanticDecorator) {
            return ((DSemanticDecorator) edgeTarget).getTarget();
        }
        SiriusPlugin.getDefault().warning("The edge target " + String.valueOf(edgeTarget) + ISiriusMessages.IS_NOT_A_DECORATE_SEMANTIC_ELEMENT, null);
        return null;
    }

    /**
     * Finds all the potential candidate elements for the target of an edge.
     * 
     * @param self
     *            the mapping defining the edge.
     * @param semanticOrigin
     *            the semantic element of the origin of the edge.
     * @param diagram
     *            the diagram in which the edge will be created.
     * @return the candidates for the edge's target.
     */
    public EList<EObject> getEdgeTargetCandidates(final EdgeMapping self, final EObject semanticOrigin, final DDiagram diagram) {
        EList<EObject> result = new UniqueEList<EObject>();
        if (self.getTargetFinderExpression() != null && !StringUtil.isEmpty(self.getTargetFinderExpression().trim())) {
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
            final Collection<EObject> candidates = safeInterpreter.evaluateCollection(semanticOrigin, self, DescriptionPackage.eINSTANCE.getEdgeMapping_TargetFinderExpression());
            result = new UniqueEList<EObject>(candidates);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
        }
        return result;
    }

    /**
     * Finds all the potential candidate elements for the source of an edge.
     * 
     * @param self
     *            the mapping defining the edge.
     * @param semanticOrigin
     *            the semantic element of the origin of the edge.
     * @param diagram
     *            the diagram in which the edge will be created.
     * @return the candidates for the edge's target.
     */
    public EList<EObject> getEdgeSourceCandidates(final EdgeMapping self, final EObject semanticOrigin, final DDiagram diagram) {
        EList<EObject> result = new UniqueEList<EObject>();
        if (self.getSourceFinderExpression() != null && !StringUtil.isEmpty(self.getSourceFinderExpression().trim())) {
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            final Collection<EObject> resultList = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(semanticOrigin, self,
                    DescriptionPackage.eINSTANCE.getEdgeMapping_SourceFinderExpression());
            result = new UniqueEList<EObject>(resultList);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
        }
        return result;
    }

    /**
     * Create an new edge with this parameters.
     * 
     * @param edgeMapping
     *            The edge mapping
     * @param source
     *            The source of the new edge
     * @param target
     *            The target of the new edge
     * @param container
     *            The semantic container
     * @param semanticTarget
     *            The semantic element of the new edge
     * @return A new edge
     */
    public DEdge createEdge(final EdgeMapping edgeMapping, final EdgeTarget source, final EdgeTarget target, final EObject container, final EObject semanticTarget) {
        EObject semanticTargetToUseForExpression = semanticTarget;
        if (semanticTargetToUseForExpression == null) {
            semanticTargetToUseForExpression = getSemanticTarget(source);
        }
        final DEdge newEdge = DiagramFactory.eINSTANCE.createDEdge();
        newEdge.setSourceNode(source);
        newEdge.setTargetNode(target);
        if (edgeMapping instanceof EdgeMappingImportWrapper) {
            newEdge.setActualMapping(((EdgeMappingImportWrapper) edgeMapping).getWrappedEdgeMappingImport());
        } else {
            newEdge.setActualMapping(edgeMapping);
        }
        DDiagram diagram = null;
        if (source instanceof DDiagramElement) {
            diagram = ((DDiagramElement) source).getParentDiagram();
        }
        // target :
        newEdge.setTarget(semanticTargetToUseForExpression);

        if (semanticTarget == null && !StringUtil.isEmpty(edgeMapping.getTargetExpression())) {
            final EObject context = newEdge.getTarget();
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT_2, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            final EObject adapterValue = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateEObject(context, edgeMapping, DescriptionPackage.eINSTANCE.getEdgeMapping_TargetExpression());
            if (adapterValue != null) {
                newEdge.setTarget(adapterValue);
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT_2);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }

        /*
         * We'll see if it's proven to be useful
         */
        // Get the best style according to conditionals styles
        final EdgeStyle style = (EdgeStyle) new MappingHelper(interpreter).getBestStyle(edgeMapping, newEdge.getTarget(), diagram, container, diagram);
        EdgeStyleDescription styleDescription = null;
        if (style != null && style.getDescription() instanceof EdgeStyleDescription) {
            styleDescription = (EdgeStyleDescription) style.getDescription();
        }

        DiagramElementMappingHelper.refreshSemanticElements(edgeMapping, newEdge, interpreter);

        // The optional path.
        createPath(edgeMapping, newEdge, diagram);

        if (style != null) {
            newEdge.setOwnedStyle(style);
        }
        if (newEdge.getOwnedStyle() != null) {
            Option<EdgeStyle> noPreviousStyle = Options.newNone();
            new StyleHelper(interpreter).refreshStyle(newEdge.getOwnedStyle(), noPreviousStyle);
        }

        if (styleDescription != null) {
            final String name = computeLabel(newEdge, styleDescription.getCenterLabelStyleDescription(), diagram);
            if (name != null) {
                newEdge.setName(name);
            }
            final String beginName = computeLabel(newEdge, styleDescription.getBeginLabelStyleDescription(), diagram);
            if (beginName != null) {
                newEdge.setBeginLabel(beginName);
            }
            final String endName = computeLabel(newEdge, styleDescription.getEndLabelStyleDescription(), diagram);
            if (endName != null) {
                newEdge.setEndLabel(endName);
            }
        }

        /*
         * Validation does not work here : getParentDiagram is still unknown.
         * 
         * Let's check the fact that we can find back the target from the source
         * using the target finder expression
         */
        // if (!newEdge.validate()) {
        // SiriusPlugin.getDefault().warning("The newly created edge is not
        // valid # Mapping:" + getName(), new Exception());
        // }
        return newEdge;
    }

    private String computeLabel(final DSemanticDecorator view, final EObject descriptionObject, final DDiagram diagram) {
        String result = null;

        if (descriptionObject instanceof BasicLabelStyleDescription) {
            result = IInterpreterMessages.DEFAULT_NAME_ON_FACTORY_EXCEPTION;
            String labelExpression = ((BasicLabelStyleDescription) descriptionObject).getLabelExpression();

            interpreter.setVariable(IInterpreterSiriusVariables.VIEW, view);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);

            try {
                result = interpreter.evaluateString(view.getTarget(), labelExpression);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(descriptionObject, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            }
        }

        return result;
    }

    /**
     * @param edgeMapping
     * @param newEdge
     * @param diagram
     */
    private void createPath(final EdgeMapping edgeMapping, final DEdge edge, final EObject diagram) {
        if (edgeMapping.getPathExpression() != null && !StringUtil.isEmpty(edgeMapping.getPathExpression()) && edgeMapping.getPathNodeMapping() != null) {

            // variables.
            interpreter.setVariable(IInterpreterSiriusVariables.VIEWPOINT, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram);
            interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, edge.getTarget());
            interpreter.setVariable(IInterpreterSiriusVariables.SOURCE, getSemanticTarget(edge.getSourceNode()));
            interpreter.setVariable(IInterpreterSiriusVariables.TARGET, getSemanticTarget(edge.getTargetNode()));

            final Collection<EObject> elements = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(edge.getTarget(), edgeMapping,
                    DescriptionPackage.eINSTANCE.getEdgeMapping_PathExpression());

            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEWPOINT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
            interpreter.unSetVariable(IInterpreterSiriusVariables.SOURCE);
            interpreter.unSetVariable(IInterpreterSiriusVariables.TARGET);

            EList<EdgeTarget> newPath = new BasicEList<EdgeTarget>();

            for (final EObject current : elements) {
                for (final AbstractNodeMapping currentNodeMapping : edgeMapping.getPathNodeMapping()) {
                    final List<DDiagramElement> listView = currentNodeMapping.findDNodeFromEObject(current);
                    if (listView != null) {
                        final Iterator<DDiagramElement> iterViews = listView.iterator();
                        while (iterViews.hasNext()) {
                            final Object vpElement = iterViews.next();
                            if (vpElement instanceof EdgeTarget) {
                                newPath.add((EdgeTarget) vpElement);
                            }
                        }
                    }
                }
            }

            org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper equalityHelper = new org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper();

            // Change path only if new list is different from original one
            if (!equalityHelper.equals((List) edge.getPath(), (List) newPath)) {
                edge.getPath().clear();
                edge.getPath().addAll(newPath);
            }
        }
    }

    /**
     * Update an existing edge.
     * 
     * @param edgeMapping
     *            The edge mapping
     * @param dEdge
     *            The existing edge to update
     */
    public void updateEdge(final EdgeMapping edgeMapping, final DEdge dEdge) {
        if (dEdge.validate()) {

            EObject containerVariable = null;
            if (dEdge.eContainer() instanceof DSemanticDecorator) {
                containerVariable = ((DSemanticDecorator) dEdge.eContainer()).getTarget();
            }
            final EdgeStyleDescription styleDescription = (EdgeStyleDescription) new MappingHelper(interpreter).getBestStyleDescription(edgeMapping, dEdge.getTarget(), dEdge, containerVariable,
                    dEdge.getParentDiagram());

            DDiagram dDiagram = dEdge.getParentDiagram();

            // the optional path.
            createPath(edgeMapping, dEdge, dDiagram);

            // semantic elements
            DiagramElementMappingHelper.refreshSemanticElements(edgeMapping, dEdge, interpreter);

            if (styleDescription != null) {
                final String name = computeLabel(dEdge, styleDescription.getCenterLabelStyleDescription(), dDiagram);
                if (name != null) {
                    dEdge.setName(name);
                }
                final String beginName = computeLabel(dEdge, styleDescription.getBeginLabelStyleDescription(), dDiagram);
                if (beginName != null) {
                    dEdge.setBeginLabel(beginName);
                }
                final String endName = computeLabel(dEdge, styleDescription.getEndLabelStyleDescription(), dDiagram);
                if (endName != null) {
                    dEdge.setEndLabel(endName);
                }
            }

            StyleHelper styleHelper = new StyleHelper(interpreter);
            if (styleDescription != null && dEdge.getStyle() != null && styleDescription != dEdge.getStyle().getDescription()) {
                styleHelper.setAndRefreshStyle(dEdge, dEdge.getOwnedStyle(), styleHelper.createStyle(styleDescription));
            } else if (dEdge.getOwnedStyle() != null) {
                styleHelper.refreshStyle(dEdge.getOwnedStyle());
            }
        }
    }

    /**
     * Check if mapping is imported by edgeMappingImport.
     * 
     * @param mapping
     *            The potentially imported mapping
     * @param edgeMappingImport
     *            The potential importer
     * @return true if mapping is imported by edgeMappingImport or a subMapping,
     *         false otherwise
     */
    public static boolean isImported(final IEdgeMapping mapping, final EdgeMappingImport edgeMappingImport) {
        boolean result = false;
        if (mapping.equals(edgeMappingImport.getImportedMapping())) {
            result = true;
        } else if (edgeMappingImport.getImportedMapping() instanceof EdgeMappingImport) {
            result = isImported(mapping, (EdgeMappingImport) edgeMappingImport.getImportedMapping());
        }
        return result;
    }
}
