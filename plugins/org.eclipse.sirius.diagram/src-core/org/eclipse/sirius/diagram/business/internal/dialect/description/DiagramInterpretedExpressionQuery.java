/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.dialect.description;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.AbstractInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.DefaultInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Query allowing to get the target domain classes and available packages for a
 * given Interpreted expression. This diagram query will treat all generic
 * description elements and those related to the diagram concept.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DiagramInterpretedExpressionQuery extends AbstractInterpretedExpressionQuery implements IInterpretedExpressionQuery {

    private static final String DIAGRAM_D_EDGE_TYPE = "diagram.DEdge";

    private static final String DIAGRAM_EDGE_TARGET_TYPE = "diagram.EdgeTarget";

    /**
     * Default constructor.
     * 
     * @param target
     *            the target containing the InterpretedExpression (NodeMapping,
     *            ModelOperation...)
     * @param feature
     *            the feature corresponding to the InterpretedExpression to
     *            evaluate ( NodeMapping.semanticCandidatesExpression...)
     */
    public DiagramInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        super(target, feature);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.description.AbstractInterpretedExpressionQuery#initializeTargetSwitch()
     */
    @Override
    protected void initializeTargetSwitch() {
        targetSwitch = new DiagramGlobalInterpretedTargetSwitch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void appendAllLocalVariableDefinitions(Map<String, Collection<VariableType>> definitions, EObject context) {
        super.appendAllLocalVariableDefinitions(definitions, context);
        // Direct edit defines numbered variables based on their mask.
        if (context instanceof DirectEditLabel && ((DirectEditLabel) context).getMask() != null) {
            EditMaskVariables emv = ((DirectEditLabel) context).getMask();
            appendEditMaskVariables(emv, definitions);
        }
        // Add CreateViewn and CreateEdgeView Variable Name to available
        // variables
        if (context instanceof CreateView) {
            availableVariables.put(((CreateView) context).getVariableName(), VariableType.ANY_EOBJECT);
        }

    }

    @Override
    public Map<String, VariableType> getAvailableVariables() {

        Map<String, VariableType> availableVariables = super.getAvailableVariables();

        if (getToolContext().some()) {
            EObject operationContext = getToolContext().get();
            if (operationContext instanceof EdgeCreationDescription) {
                EdgeCreationDescription tool = (EdgeCreationDescription) operationContext;
                declareEdgeSourceTargets(availableVariables, tool.getEdgeMappings(), tool.getExtraSourceMappings(), tool.getExtraTargetMappings());
            }
            if (operationContext instanceof ReconnectEdgeDescription) {
                ReconnectEdgeDescription tool = (ReconnectEdgeDescription) operationContext;
                declareEdgeSourceTargets(availableVariables, tool.getMappings(), Collections.<DiagramElementMapping> emptyList(), Collections.<DiagramElementMapping> emptyList());
                availableVariables.put("otherEnd", VariableType.fromString(DIAGRAM_EDGE_TARGET_TYPE));
                availableVariables.put("edgeView", VariableType.fromString(DIAGRAM_D_EDGE_TYPE));

                Collection<String> possibleSources = Lists.newArrayList();
                for (EdgeMapping eMapping : tool.getMappings()) {
                    collectSemanticElementType(possibleSources, eMapping);
                }
                if (possibleSources.size() > 0) {
                    VariableType sourceTypes = VariableType.fromStrings(possibleSources);
                    availableVariables.put(IInterpreterSiriusVariables.ELEMENT, sourceTypes);
                }

            }
            if (operationContext instanceof NodeCreationDescription) {
                NodeCreationDescription tool = (NodeCreationDescription) operationContext;
                Collection<String> possibleTypes = Sets.newLinkedHashSet();
                /*
                 * gather types for the "container" variable.
                 */
                for (AbstractNodeMapping np : tool.getExtraMappings()) {
                    String domainClass = np.getDomainClass();
                    if (!StringUtil.isEmpty(domainClass)) {
                        possibleTypes.add(domainClass);
                    }
                }
                availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleTypes));
            }

            if (operationContext instanceof ContainerCreationDescription) {
                ContainerCreationDescription tool = (ContainerCreationDescription) operationContext;
                Collection<String> possibleTypes = Sets.newLinkedHashSet();
                /*
                 * gather types for the "container" variable.
                 */
                for (AbstractNodeMapping np : tool.getExtraMappings()) {
                    String domainClass = np.getDomainClass();
                    if (!StringUtil.isEmpty(domainClass)) {
                        possibleTypes.add(domainClass);
                    }
                }
                availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleTypes));
            }

        }
        /*
         * [428757] tool variables and not displayed in autocompletion This
         * patch adds hard coded variables and hence is a temporary solution.
         * The good way would be to put those metadata on the
         * EdgeCreationDescription EClass in the diagram.ecore metamodel and to
         * complete the AbstractInterpretedExpressionQuery to make it able to
         * find specific variables for concrete types.
         */
        if (target instanceof EdgeCreationDescription && ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION.equals(feature)) {
            availableVariables.put("diagram", VariableType.fromString("diagram.DDiagram"));
            availableVariables.put("preSource", VariableType.fromString("ecore.EObject"));
            availableVariables.put("preSourceView", VariableType.fromString(DIAGRAM_EDGE_TARGET_TYPE));
            availableVariables.put("preTarget", VariableType.fromString("ecore.EObject"));
            availableVariables.put("preTargetView", VariableType.fromString(DIAGRAM_EDGE_TARGET_TYPE));

        }

        return availableVariables;
    }

    private void declareEdgeSourceTargets(Map<String, VariableType> availableVariables, Collection<EdgeMapping> eMappings, Collection<DiagramElementMapping> extraSourceMappings,
            Collection<DiagramElementMapping> extraTargetMappings) {
        Collection<String> possibleSources = Sets.newLinkedHashSet();
        Collection<String> possibleTargets = Sets.newLinkedHashSet();
        for (EdgeMapping eMapping : eMappings) {
            for (DiagramElementMapping endMapping : eMapping.getSourceMapping()) {
                collectTypes(possibleSources, endMapping);
            }
            for (DiagramElementMapping endMapping : eMapping.getTargetMapping()) {
                collectTypes(possibleTargets, endMapping);
            }
        }
        for (DiagramElementMapping extraSource : extraSourceMappings) {
            collectTypes(possibleSources, extraSource);
        }
        for (DiagramElementMapping extraTarget : extraTargetMappings) {
            collectTypes(possibleTargets, extraTarget);
        }
        if (possibleSources.size() > 0) {
            VariableType sourceTypes = VariableType.fromStrings(possibleSources);
            availableVariables.put(IInterpreterSiriusVariables.SOURCE_PRE, sourceTypes);
            availableVariables.put(IInterpreterSiriusVariables.SOURCE, sourceTypes);
        }
        if (possibleTargets.size() > 0 && feature != org.eclipse.sirius.diagram.description.tool.ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION) {
            VariableType targetTypes = VariableType.fromStrings(possibleTargets);
            availableVariables.put(IInterpreterSiriusVariables.TARGET_PRE, targetTypes);
            availableVariables.put(IInterpreterSiriusVariables.TARGET, targetTypes);
        }
    }

    private void collectTypes(Collection<String> possibleSources, DiagramElementMapping endMapping) {
        if (endMapping instanceof AbstractNodeMapping) {
            String domainClass = ((AbstractNodeMapping) endMapping).getDomainClass();
            if (!StringUtil.isEmpty(domainClass)) {
                possibleSources.add(domainClass);
            }
        } else if (endMapping instanceof EdgeMapping) {
            EdgeMapping edgeMapping = (EdgeMapping) endMapping;
            collectSemanticElementType(possibleSources, edgeMapping);
        }
    }

    private void collectSemanticElementType(Collection<String> possibleSources, EdgeMapping edgeMapping) {
        if (edgeMapping.isUseDomainElement()) {
            String domainClass = edgeMapping.getDomainClass();
            if (!StringUtil.isEmpty(domainClass)) {
                possibleSources.add(domainClass);
            }
        } else {
            for (AbstractNodeMapping nMapping : Iterables.filter(edgeMapping.getSourceMapping(), AbstractNodeMapping.class)) {
                String domainClass = nMapping.getDomainClass();
                if (!StringUtil.isEmpty(domainClass)) {
                    possibleSources.add(domainClass);
                }
            }
        }
    }

    /**
     * An {@link IInterpretedExpressionTargetSwitch} that delegates to the
     * defaultSwitch or the diagram specific switch, according to the package of
     * the considered element.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    private class DiagramGlobalInterpretedTargetSwitch implements IInterpretedExpressionTargetSwitch {

        private DefaultInterpretedExpressionTargetSwitch defaultSwitch = new DefaultInterpretedExpressionTargetSwitch(feature, this) {
            @Override
            public EObject getFirstRelevantContainer(EObject obj) {
                return DiagramGlobalInterpretedTargetSwitch.this.getFirstRelevantContainer(obj);
            }
        };

        private DiagramInterpretedExpressionTargetSwitch diagramDescriptionSwitch = new DiagramInterpretedExpressionTargetSwitch(feature, this);

        private DiagramStyleInterpretedExpressionTargetSwitch diagramStyleSwitch = new DiagramStyleInterpretedExpressionTargetSwitch(feature, this);

        private DiagramToolInterpretedExpressionTargetSwitch diagramToolSwitch = new DiagramToolInterpretedExpressionTargetSwitch(feature, this);

        private FilterInterpretedExpressionTargetSwitch diagramFilterSwitch = new FilterInterpretedExpressionTargetSwitch(feature, this);

        /**
         * 
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
         */
        @Override
        public Option<Collection<String>> doSwitch(EObject target, boolean considerFeature) {
            Collection<String> targetTypes = Sets.newLinkedHashSet();
            Option<Collection<String>> expressionTarget = Options.newSome(targetTypes);
            if (target != null) {
                // Step 1: trying to apply any contributed switch that matches
                // the given target's EPackage
                for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
                    if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(target.eClass().getEPackage())) {
                        IInterpretedExpressionTargetSwitch contributedSwitch = diagramTypeDescriptor.getDiagramDescriptionProvider().createInterpretedExpressionSwitch(feature, this);
                        if (contributedSwitch != null) {
                            expressionTarget = contributedSwitch.doSwitch(target, considerFeature);
                        }
                    }
                }
                // If no result has been found
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    // Step 2: apply the Diagram description specific switch
                    diagramDescriptionSwitch.setConsiderFeature(considerFeature);
                    expressionTarget = diagramDescriptionSwitch.doSwitch(target);
                }

                // If no result has been found
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    // Step 3: apply the Diagram style specific switch
                    diagramStyleSwitch.setConsiderFeature(considerFeature);
                    expressionTarget = diagramStyleSwitch.doSwitch(target);
                }

                // If no result has been found
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    // Step 4: apply the Diagram tool specific switch
                    diagramToolSwitch.setConsiderFeature(considerFeature);
                    expressionTarget = diagramToolSwitch.doSwitch(target);
                }

                // If no result has been found
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    // Step 5: apply the Diagram filter specific switch
                    diagramFilterSwitch.setConsiderFeature(considerFeature);
                    expressionTarget = diagramFilterSwitch.doSwitch(target);
                }

                // If no result has been found
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    // Step 7 : we use the default switch
                    expressionTarget = defaultSwitch.doSwitch(target, considerFeature);
                }
            }
            return expressionTarget;
        }

        @Override
        public EObject getFirstRelevantContainer(EObject obj) {
            if (obj != null) {
                EObject container = obj.eContainer();
                while (container != null && !isRelevant(container)) {
                    container = container.eContainer();
                }
                return container;
            } else {
                return null;
            }
        }

        private boolean isRelevant(EObject container) {
            return container instanceof RepresentationDescription || container instanceof RepresentationElementMapping || container instanceof EdgeMappingImport
                    || container instanceof DiagramExtensionDescription;
        }
    }
}
