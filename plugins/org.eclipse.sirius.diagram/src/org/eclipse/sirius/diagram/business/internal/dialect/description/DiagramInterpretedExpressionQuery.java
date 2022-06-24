/*******************************************************************************
 * Copyright (c) 2011, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.dialect.description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.dialect.description.AbstractInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.DefaultInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.api.dialect.description.MultiLanguagesValidator;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.OrderedTreeLayout;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.CreateEdgeView;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.tools.api.interpreter.IInterpreterSiriusDiagramVariables;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Query allowing to get the target domain classes and available packages for a given Interpreted expression. This
 * diagram query will treat all generic description elements and those related to the diagram concept.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DiagramInterpretedExpressionQuery extends AbstractInterpretedExpressionQuery implements IInterpretedExpressionQuery {

    private static final String DIAGRAM_D_DIAGRAM_ELEMENT_CONTAINER = "diagram.DDiagramElementContainer"; //$NON-NLS-1$

    private static final String DIAGRAM_D_SEMANTIC_DIAGRAM = "diagram.DSemanticDiagram"; //$NON-NLS-1$

    private static final String DIAGRAM_D_NODE = "diagram.DNode"; //$NON-NLS-1$

    private static final String DIAGRAM_D_NODE_LIST = "diagram.DNodeList"; //$NON-NLS-1$

    private static final String DIAGRAM_D_NODE_CONTAINER = "diagram.DNodeContainer"; //$NON-NLS-1$

    private static final String DIAGRAM_D_EDGE_TYPE = "diagram.DEdge"; //$NON-NLS-1$

    private static final VariableType EDGE_TARGET_POSSIBILITIES = VariableType.fromStrings(new HashSet<String>(Arrays.asList(DIAGRAM_D_EDGE_TYPE, DIAGRAM_D_NODE, DIAGRAM_D_DIAGRAM_ELEMENT_CONTAINER)));

    /**
     * Default constructor.
     * 
     * @param target
     *            the target containing the InterpretedExpression (NodeMapping, ModelOperation...)
     * @param feature
     *            the feature corresponding to the InterpretedExpression to evaluate (
     *            NodeMapping.semanticCandidatesExpression...)
     */
    public DiagramInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        super(target, feature);
    }

    @Override
    public Collection<EPackage> getPackagesToImport() {
        Collection<EPackage> superResult = super.getPackagesToImport();
        superResult.add(DiagramPackage.eINSTANCE);
        superResult.add(DescriptionPackage.eINSTANCE);
        superResult.add(StylePackage.eINSTANCE);
        superResult.add(org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE);
        superResult.add(FilterPackage.eINSTANCE);
        superResult.add(ConcernPackage.eINSTANCE);
        return superResult;
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
        // Add CreateView and CreateEdgeView Variable Name to available
        // variables
        if (context instanceof CreateView) {
            availableVariables.put(((CreateView) context).getVariableName(), VariableType.ANY_EOBJECT);
        }

    }

    @Override
    protected void collectContextualVariableForOperation(EObject current, Map<String, Collection<VariableType>> definitions, EObject leaf) {
        super.collectContextualVariableForOperation(current, definitions, leaf);
        if (current instanceof CreateView) {
            CreateView op = (CreateView) current;
            DiagramElementMapping mapping = op.getMapping();
            if (mapping instanceof NodeMapping) {
                changeSelfType(VariableType.fromString(DIAGRAM_D_NODE));
            } else if (mapping instanceof ContainerMapping) {
                if (((ContainerMapping) mapping).getChildrenPresentation() == ContainerLayout.LIST) {
                    changeSelfType(VariableType.fromString(DIAGRAM_D_NODE_LIST));
                } else {
                    changeSelfType(VariableType.fromString(DIAGRAM_D_NODE_CONTAINER));
                }
            } else if (mapping instanceof EdgeMapping) {
                changeSelfType(VariableType.fromString(DIAGRAM_D_EDGE_TYPE));
            }
        }
    }

    @Override
    public Map<String, VariableType> getAvailableVariables() {

        Map<String, VariableType> availableVariables = super.getAvailableVariables();

        /*
         * [428757] tool variables are not displayed in autocompletion. This patch adds hard coded variables and hence
         * is a temporary solution. The good way would be to put those metadata on the EdgeCreationDescription EClass in
         * the diagram.ecore metamodel and to complete the AbstractInterpretedExpressionQuery to make it able to find
         * specific variables for concrete types.
         */
        if (target instanceof EdgeCreationDescription) {
            EdgeCreationDescription tool = (EdgeCreationDescription) target;
            collectEdgeCreationDescriptionVariableTypes(availableVariables, tool);
        } else if (target instanceof ConditionalNodeStyleDescription || target instanceof NodeStyleDescription) {
            availableVariables.put(IInterpreterSiriusVariables.VIEW, VariableType.fromString(DIAGRAM_D_NODE));
        } else if (target instanceof ConditionalContainerStyleDescription || target instanceof ContainerStyleDescription) {
            availableVariables.put(IInterpreterSiriusVariables.VIEW, VariableType.fromString(DIAGRAM_D_NODE_CONTAINER));
        } else if (target instanceof ConditionalEdgeStyleDescription || target instanceof EdgeStyleDescription || target instanceof BasicLabelStyleDescription) {
            availableVariables.put(IInterpreterSiriusVariables.VIEW, VariableType.fromString(DIAGRAM_D_EDGE_TYPE));
        } else if (target instanceof DiagramElementMapping) {
            if (this.feature == DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION) {
                Collection<String> possibleContainerViewTypes = new LinkedHashSet<>();
                Collection<String> possibleContainerTypes = new LinkedHashSet<>();
                collectPotentialContainerTypes(possibleContainerTypes, possibleContainerViewTypes, ImmutableList.of((DiagramElementMapping) this.target));
                availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleContainerTypes));
                availableVariables.put(IInterpreterSiriusVariables.CONTAINER_VIEW, VariableType.fromStrings(possibleContainerViewTypes));
            }
        }
        if (target instanceof EdgeMapping && this.feature == DescriptionPackage.Literals.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION) {
            EdgeMapping edge = (EdgeMapping) target;
            Set<String> sourceSemanticType = new LinkedHashSet<>();
            Set<String> sourceViewType = new LinkedHashSet<>();
            for (DiagramElementMapping m : edge.getSourceMapping()) {
                collectTypes(sourceSemanticType, sourceViewType, m);
            }
            availableVariables.put(IInterpreterSiriusVariables.SOURCE, VariableType.fromStrings(sourceSemanticType));
            availableVariables.put(IInterpreterSiriusVariables.SOURCE_VIEW, VariableType.fromStrings(sourceViewType));
            Set<String> targetSemanticType = new LinkedHashSet<>();
            Set<String> targetViewType = new LinkedHashSet<>();
            for (DiagramElementMapping m : edge.getTargetMapping()) {
                collectTypes(targetSemanticType, targetViewType, m);
            }
            availableVariables.put(IInterpreterSiriusVariables.TARGET, VariableType.fromStrings(targetSemanticType));
            availableVariables.put(IInterpreterSiriusVariables.TARGET_VIEW, VariableType.fromStrings(targetViewType));

        }
        if (target instanceof ConditionalStyleDescription && this.feature == org.eclipse.sirius.viewpoint.description.DescriptionPackage.Literals.CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION) {
            /*
             * We don't use the Ecore annotation to define the 'diagram' variable as it is only available for subclasses
             * of ConditionalStyleDescription which are defined in the diagram.description EPackage.
             */
            availableVariables.put(IInterpreterSiriusVariables.DIAGRAM, VariableType.fromString(DIAGRAM_D_SEMANTIC_DIAGRAM));
        }
        if ((target instanceof NodeCreationDescription || target instanceof ContainerCreationDescription) && this.feature == ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION) {
            Collection<String> possibleContainerViewTypes = new LinkedHashSet<>();
            Collection<String> possibleContainerTypes = new LinkedHashSet<>();
            Collection<DiagramElementMapping> mappings = new LinkedHashSet<>();
            if (target instanceof NodeCreationDescription) {
                mappings.addAll(((NodeCreationDescription) target).getNodeMappings());
            }
            if (target instanceof ContainerCreationDescription) {
                mappings.addAll(((ContainerCreationDescription) target).getContainerMappings());
            }
            collectPotentialContainerTypes(possibleContainerTypes, possibleContainerViewTypes, mappings);
            if (target instanceof ContainerCreationDescription) {
                addExtraMappings(possibleContainerTypes, ((ContainerCreationDescription) target).getExtraMappings());
            }
            if (target instanceof NodeCreationDescription) {
                addExtraMappings(possibleContainerTypes, ((NodeCreationDescription) target).getExtraMappings());
            }
            availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleContainerTypes));
            availableVariables.put(IInterpreterSiriusVariables.CONTAINER_VIEW, VariableType.fromStrings(possibleContainerViewTypes));
        }
        if (target instanceof OrderedTreeLayout && this.feature == DescriptionPackage.Literals.ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION) {
            Collection<String> possibleSemanticTypes = new LinkedHashSet<>();
            for (AbstractNodeMapping n : ((OrderedTreeLayout) target).getNodeMapping()) {
                collectTypes(possibleSemanticTypes, Sets.<String> newLinkedHashSet(), n);
            }
            refineVariableType(availableVariables, SELF, possibleSemanticTypes);
        }
        if (target instanceof ReconnectEdgeDescription && this.feature == ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION) {
            collectReconnectEdgeDescriptionVariableTypes(availableVariables);
        }
        if (target instanceof RepresentationCreationDescription) {
            typeVariablesForDiagramCreationRepresentation((RepresentationCreationDescription) target, availableVariables);
        }
        return availableVariables;

    }

    private void addExtraMappings(Collection<String> possibleContainerTypes, Collection<AbstractNodeMapping> extra) {
        for (AbstractNodeMapping np : extra) {
            String domainClass = np.getDomainClass();
            if (!StringUtil.isEmpty(domainClass)) {
                possibleContainerTypes.add(domainClass);
            }
        }
    }

    private void typeVariablesForDiagramCreationRepresentation(RepresentationCreationDescription desc, Map<String, VariableType> availableVariables) {
        Collection<String> possibleSemanticTypes = new LinkedHashSet<>();
        Collection<String> possibleViewTypes = new LinkedHashSet<>();
        Collection<String> possibleContainerViewTypes = new LinkedHashSet<>();
        Collection<String> possibleContainerTypes = new LinkedHashSet<>();
        for (DiagramElementMapping n : Iterables.filter(desc.getMappings(), DiagramElementMapping.class)) {
            collectTypes(possibleSemanticTypes, possibleViewTypes, n);
            collectPotentialContainerTypes(possibleContainerTypes, possibleContainerViewTypes, n);
        }
        refineVariableType(availableVariables, SELF, possibleSemanticTypes);
        if (this.feature == ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION) {
            /*
             * then we are validating the attribute expressions of the tool.
             */
            availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleContainerTypes));
            availableVariables.put(IInterpreterSiriusVariables.CONTAINER_VIEW, VariableType.fromStrings(possibleContainerViewTypes));
        } else if (this.feature != ToolPackage.Literals.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION) {
            /*
             * we are in the model operations
             */
            availableVariables.put(IInterpreterSiriusVariables.CONTAINER_VIEW, VariableType.fromStrings(possibleContainerViewTypes));
            if (desc.getRepresentationNameVariable() != null && !StringUtil.isEmpty(desc.getRepresentationNameVariable().getName())) {
                availableVariables.put(desc.getRepresentationNameVariable().getName(), VariableType.fromJavaClass(java.lang.String.class)); // $NON-NLS-1$
            }

        }
    }

    private void collectEdgeCreationDescriptionVariableTypes(Map<String, VariableType> availableVariables, EdgeCreationDescription tool) {
        Collection<String> possibleSemanticSources = new LinkedHashSet<>();
        Collection<String> possibleViewSources = new LinkedHashSet<>();
        Collection<String> possibleSemanticTargets = new LinkedHashSet<>();
        Collection<String> possibleViewTargets = new LinkedHashSet<>();
        Collection<String> possibleContainerTypes = new LinkedHashSet<>();
        collectPotentialContainerTypes(possibleContainerTypes, Sets.<String> newLinkedHashSet(), tool.getEdgeMappings());
        for (EdgeMapping eMapping : tool.getEdgeMappings()) {
            for (DiagramElementMapping endMapping : eMapping.getSourceMapping()) {
                collectTypes(possibleSemanticSources, possibleViewSources, endMapping);
            }
            for (DiagramElementMapping endMapping : eMapping.getTargetMapping()) {
                collectTypes(possibleSemanticTargets, possibleViewTargets, endMapping);
            }
        }
        for (DiagramElementMapping extraSource : tool.getExtraSourceMappings()) {
            collectTypes(possibleSemanticSources, possibleViewSources, extraSource);
        }
        for (DiagramElementMapping extraTarget : tool.getExtraTargetMappings()) {
            collectTypes(possibleSemanticTargets, possibleViewTargets, extraTarget);
        }
        if (ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION.equals(feature)) {
            availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleContainerTypes));
            availableVariables.put(IInterpreterSiriusVariables.DIAGRAM, VariableType.fromString(DIAGRAM_D_SEMANTIC_DIAGRAM));
            availableVariables.put(IInterpreterSiriusVariables.SOURCE_PRE, VariableType.fromStrings(possibleSemanticSources));
            availableVariables.put(IInterpreterSiriusVariables.SOURCE_VIEW_PRE, VariableType.fromStrings(possibleViewSources));
            availableVariables.put(IInterpreterSiriusVariables.TARGET_PRE, VariableType.fromStrings(possibleSemanticTargets));
            availableVariables.put(IInterpreterSiriusVariables.TARGET_VIEW_PRE, VariableType.fromStrings(possibleViewTargets));
        }
        if (this.feature == org.eclipse.sirius.diagram.description.tool.ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION) {
            availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleContainerTypes));
            availableVariables.put(IInterpreterSiriusVariables.DIAGRAM, VariableType.fromString(DIAGRAM_D_SEMANTIC_DIAGRAM));
            availableVariables.put(IInterpreterSiriusVariables.SOURCE_PRE, VariableType.fromStrings(possibleSemanticSources));
            availableVariables.put(IInterpreterSiriusVariables.SOURCE_VIEW_PRE, VariableType.fromStrings(possibleViewSources));
        }
    }

    @Override
    protected void addVariableFromCreateOperation(ModelOperation modelOperation) {
        super.addVariableFromCreateOperation(modelOperation);

        if (modelOperation instanceof CreateEdgeView) {
            availableVariables.put(((CreateEdgeView) modelOperation).getVariableName(), VariableType.fromString(DIAGRAM_D_EDGE_TYPE));
        } else if (modelOperation instanceof CreateView) {
            availableVariables.put(((CreateView) modelOperation).getVariableName(), VariableType.fromString("viewpoint.DView")); //$NON-NLS-1$
        }
    }

    @Override
    protected void addVariablesFromToolContext(EObject toolContext) {
        if (toolContext != null) {
            addVariablesForCreationTools(toolContext);

            if (toolContext instanceof DeleteElementDescription) {
                DeleteElementDescription tool = (DeleteElementDescription) toolContext;
                Collection<String> possibleSemanticTypes = new LinkedHashSet<>();
                Collection<String> possibleViewTypes = new LinkedHashSet<>();
                Collection<String> possibleContainerViewTypes = new LinkedHashSet<>();
                for (DiagramElementMapping mapping : tool.getMappings()) {
                    collectTypes(possibleSemanticTypes, possibleViewTypes, mapping);
                    collectPotentialContainerTypes(Sets.<String> newLinkedHashSet(), possibleContainerViewTypes, mapping);
                }
                refineVariableType(availableVariables, IInterpreterSiriusVariables.ELEMENT, possibleSemanticTypes);
                refineVariableType(availableVariables, "elementView", possibleViewTypes); //$NON-NLS-1$
                refineVariableType(availableVariables, "containerView", possibleContainerViewTypes); //$NON-NLS-1$
            }

            if (toolContext instanceof OperationAction) {
                OperationAction tool = (OperationAction) toolContext;
                if (new EObjectQuery(tool).getFirstAncestorOfType(DescriptionPackage.Literals.DIAGRAM_DESCRIPTION).some()) {
                    availableVariables.put(IInterpreterSiriusVariables.DIAGRAM, VariableType.fromString(DIAGRAM_D_SEMANTIC_DIAGRAM));
                }
                availableVariables.put("views", VariableType.fromString("viewpoint.DSemanticDecorator")); //$NON-NLS-1$ //$NON-NLS-2$
            }
            if (toolContext instanceof ToolDescription) {
                ToolDescription tool = (ToolDescription) toolContext;
                if (!StringUtil.isEmpty(tool.getPrecondition())) {
                    IInterpreterContext iContext = SiriusInterpreterContextFactory.createInterpreterContext(tool, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION);
                    ValidationResult res = MultiLanguagesValidator.getInstance().validateExpression(iContext, tool.getPrecondition());
                    Map<String, VariableType> inferedTypes = res.getInferredVariableTypes(Boolean.TRUE);
                    for (Entry<String, VariableType> infered : inferedTypes.entrySet()) {
                        if (SELF.equals(infered.getKey())) {
                            changeSelfType(infered.getValue());
                        } else if (infered.getValue().getPossibleTypes().size() > 0) {
                            availableVariables.put(infered.getKey(), infered.getValue());
                        }
                    }
                    availableVariables.put(IInterpreterSiriusVariables.DIAGRAM, VariableType.fromString(DIAGRAM_D_SEMANTIC_DIAGRAM));
                }
            }
            if (toolContext instanceof DiagramNavigationDescription) {
                DiagramNavigationDescription tool = (DiagramNavigationDescription) toolContext;
                Collection<String> possibleContainerSemanticTypes = new LinkedHashSet<>();
                Collection<String> possibleContainerViewTypes = new LinkedHashSet<>();
                collectPotentialContainerTypes(possibleContainerSemanticTypes, possibleContainerViewTypes, Lists.newArrayList(Iterables.filter(tool.getMappings(), DiagramElementMapping.class)));
                refineVariableType(availableVariables, tool.getContainerVariable().getName(), possibleContainerSemanticTypes);
                refineVariableType(availableVariables, tool.getContainerViewVariable().getName(), possibleContainerViewTypes);
            }
            if (toolContext instanceof DoubleClickDescription) {
                DoubleClickDescription tool = (DoubleClickDescription) toolContext;
                Collection<String> possibleSemanticTypes = new LinkedHashSet<>();
                Collection<String> possibleViewTypes = new LinkedHashSet<>();
                for (DiagramElementMapping m : tool.getMappings()) {
                    collectTypes(possibleSemanticTypes, possibleViewTypes, m);
                }
                refineVariableType(availableVariables, tool.getElement().getName(), possibleSemanticTypes);
                refineVariableType(availableVariables, tool.getElementView().getName(), possibleViewTypes);
            }
            if (toolContext instanceof RepresentationCreationDescription) {
                typeVariablesForDiagramCreationRepresentation((RepresentationCreationDescription) toolContext, availableVariables);
            }

        }
    }

    private void addVariablesForCreationTools(EObject toolContext) {
        if (toolContext instanceof EdgeCreationDescription) {
            EdgeCreationDescription tool = (EdgeCreationDescription) toolContext;
            declareEdgeSourceTargets(availableVariables, tool.getEdgeMappings(), tool.getExtraSourceMappings(), tool.getExtraTargetMappings());
        }
        if (toolContext instanceof ReconnectEdgeDescription) {
            ReconnectEdgeDescription tool = (ReconnectEdgeDescription) toolContext;
            declareEdgeSourceTargets(availableVariables, tool.getMappings(), Collections.<DiagramElementMapping> emptyList(), Collections.<DiagramElementMapping> emptyList());
            availableVariables.put(IInterpreterSiriusDiagramVariables.OTHER_END_VARIABLE_NAME, EDGE_TARGET_POSSIBILITIES);
            availableVariables.put(IInterpreterSiriusDiagramVariables.EDGE_VIEW_VARIABLE_NAME, VariableType.fromString(DIAGRAM_D_EDGE_TYPE));

            Collection<String> possibleSources = new ArrayList<>();
            for (EdgeMapping eMapping : tool.getMappings()) {
                collectSemanticElementType(possibleSources, eMapping);
            }
            refineVariableType(availableVariables, IInterpreterSiriusVariables.ELEMENT, possibleSources);

        }
        if (toolContext instanceof NodeCreationDescription) {
            NodeCreationDescription tool = (NodeCreationDescription) toolContext;
            Collection<String> possibleSemanticTypes = new LinkedHashSet<>();
            /*
             * gather types for the "container" variable.
             */
            for (AbstractNodeMapping np : tool.getExtraMappings()) {
                String domainClass = np.getDomainClass();
                if (!StringUtil.isEmpty(domainClass)) {
                    possibleSemanticTypes.add(domainClass);
                }
            }
            Collection<String> possibleViewTypes = new LinkedHashSet<>();
            collectPotentialContainerTypes(possibleSemanticTypes, possibleViewTypes, tool.getNodeMappings());

            changeSelfType(VariableType.fromStrings(possibleSemanticTypes));
            refineVariableType(availableVariables, IInterpreterSiriusVariables.CONTAINER, possibleSemanticTypes);
            refineVariableType(availableVariables, IInterpreterSiriusVariables.CONTAINER_VIEW, possibleViewTypes);
        }

        if (toolContext instanceof ContainerCreationDescription) {
            ContainerCreationDescription tool = (ContainerCreationDescription) toolContext;
            Collection<String> possibleTypes = new LinkedHashSet<>();
            /*
             * gather types for the "container" variable.
             */
            for (AbstractNodeMapping np : tool.getExtraMappings()) {
                String domainClass = np.getDomainClass();
                if (!StringUtil.isEmpty(domainClass)) {
                    possibleTypes.add(domainClass);
                }
            }
            Collection<String> possibleViewTypes = new LinkedHashSet<>();
            collectPotentialContainerTypes(possibleTypes, possibleViewTypes, tool.getContainerMappings());
            changeSelfType(VariableType.fromStrings(possibleTypes));
            refineVariableType(availableVariables, IInterpreterSiriusVariables.CONTAINER_VIEW, possibleViewTypes);

            refineVariableType(availableVariables, IInterpreterSiriusVariables.CONTAINER, possibleTypes);
        }
    }

    private void refineVariableType(Map<String, VariableType> availableVariables, String variableName, Collection<String> foundTypes) {
        if (foundTypes.size() > 0) {
            availableVariables.put(variableName, VariableType.fromStrings(foundTypes));
        }

    }

    private void collectPotentialContainerTypes(Collection<String> possibleSemanticTypes, Collection<String> possibleViewTypes, Collection<? extends DiagramElementMapping> toolMappings) {
        for (DiagramElementMapping anyMapping : toolMappings) {
            collectPotentialContainerTypes(possibleSemanticTypes, possibleViewTypes, anyMapping);

        }
    }

    private void collectPotentialContainerTypes(Collection<String> possibleSemanticTypes, Collection<String> possibleViewTypes, DiagramElementMapping mapping) {
        /*
         * A mapping is "used" by its container.
         */
        EObject container = mapping.eContainer();
        if (container instanceof Layer) {
            /*
             * Layer is a no-op from a type perspective.
             */
            container = container.eContainer();
        }
        if (container instanceof AbstractNodeMapping) {
            String domainClass = ((AbstractNodeMapping) container).getDomainClass();
            if (!StringUtil.isEmpty(domainClass)) {
                possibleSemanticTypes.add(domainClass);
            }
            collectViewTypes(possibleViewTypes, (AbstractNodeMapping) container);
        } else if (container instanceof DiagramDescription) {
            String domainClass = ((DiagramDescription) container).getDomainClass();
            if (!StringUtil.isEmpty(domainClass)) {
                possibleSemanticTypes.add(domainClass);
            }
            possibleViewTypes.add(DIAGRAM_D_SEMANTIC_DIAGRAM);
        }
        /*
         * besides the container a mapping can be re-used by another one or by a diagram description.
         */
        ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(mapping);
        if (crossReferencer != null) {
            for (Setting xRef : crossReferencer.getInverseReferences(mapping)) {
                EStructuralFeature eStructuralFeature = xRef.getEStructuralFeature();
                EObject referencingObject = xRef.getEObject();
                if (eStructuralFeature == DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS && referencingObject instanceof DiagramDescription) {
                    String domainClass = ((DiagramDescription) referencingObject).getDomainClass();
                    if (!StringUtil.isEmpty(domainClass)) {
                        possibleSemanticTypes.add(domainClass);
                    }
                } else if (eStructuralFeature == DescriptionPackage.Literals.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS && referencingObject instanceof AbstractNodeMapping) {
                    String domainClass = ((AbstractNodeMapping) referencingObject).getDomainClass();
                    if (!StringUtil.isEmpty(domainClass)) {
                        possibleSemanticTypes.add(domainClass);
                    }

                } else if ((eStructuralFeature == DescriptionPackage.Literals.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS
                        || eStructuralFeature == DescriptionPackage.Literals.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS) && referencingObject instanceof ContainerMapping) {
                    String domainClass = ((ContainerMapping) referencingObject).getDomainClass();
                    if (!StringUtil.isEmpty(domainClass)) {
                        possibleSemanticTypes.add(domainClass);
                    }
                }
            }
        }
    }

    private void declareEdgeSourceTargets(Map<String, VariableType> availableVariables, Collection<EdgeMapping> eMappings, Collection<DiagramElementMapping> extraSourceMappings,
            Collection<DiagramElementMapping> extraTargetMappings) {
        Collection<String> possibleSemanticSources = new LinkedHashSet<>();
        Collection<String> possibleViewSources = new LinkedHashSet<>();
        Collection<String> possibleSemanticTargets = new LinkedHashSet<>();
        Collection<String> possibleViewTargets = new LinkedHashSet<>();
        for (EdgeMapping eMapping : eMappings) {
            for (DiagramElementMapping endMapping : eMapping.getSourceMapping()) {
                collectTypes(possibleSemanticSources, possibleViewSources, endMapping);
            }
            for (DiagramElementMapping endMapping : eMapping.getTargetMapping()) {
                collectTypes(possibleSemanticTargets, possibleViewTargets, endMapping);
            }
        }
        for (DiagramElementMapping extraSource : extraSourceMappings) {
            collectTypes(possibleSemanticSources, possibleViewSources, extraSource);
        }
        for (DiagramElementMapping extraTarget : extraTargetMappings) {
            collectTypes(possibleSemanticTargets, possibleViewTargets, extraTarget);
        }
        refineVariableType(availableVariables, IInterpreterSiriusVariables.SOURCE_VIEW, possibleViewSources);
        refineVariableType(availableVariables, IInterpreterSiriusVariables.SOURCE_VIEW_PRE, possibleViewSources);
        refineVariableType(availableVariables, IInterpreterSiriusVariables.TARGET_VIEW, possibleViewTargets);
        refineVariableType(availableVariables, IInterpreterSiriusVariables.SOURCE_PRE, possibleSemanticSources);
        refineVariableType(availableVariables, IInterpreterSiriusVariables.SOURCE, possibleSemanticSources);

        if (feature != org.eclipse.sirius.diagram.description.tool.ToolPackage.Literals.EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION) {
            refineVariableType(availableVariables, IInterpreterSiriusVariables.TARGET_PRE, possibleSemanticTargets);
            refineVariableType(availableVariables, IInterpreterSiriusVariables.TARGET, possibleSemanticTargets);
            refineVariableType(availableVariables, IInterpreterSiriusVariables.TARGET_VIEW_PRE, possibleViewTargets);
        }
    }

    private void collectTypes(Collection<String> possibleSemanticTypes, Collection<String> possibleViewTypes, DiagramElementMapping endMapping) {
        collectViewTypes(possibleViewTypes, endMapping);
        if (endMapping instanceof AbstractNodeMapping) {
            String domainClass = ((AbstractNodeMapping) endMapping).getDomainClass();
            if (!StringUtil.isEmpty(domainClass)) {
                possibleSemanticTypes.add(domainClass);
            }
        } else if (endMapping instanceof EdgeMapping) {
            EdgeMapping edgeMapping = (EdgeMapping) endMapping;
            collectSemanticElementType(possibleSemanticTypes, edgeMapping);
        }
    }

    private void collectViewTypes(Collection<String> possibleViewTypes, DiagramElementMapping endMapping) {
        if (endMapping instanceof ContainerMapping) {
            if (((ContainerMapping) endMapping).getChildrenPresentation() == ContainerLayout.LIST) {
                possibleViewTypes.add(DIAGRAM_D_NODE_LIST);
            } else {
                possibleViewTypes.add(DIAGRAM_D_NODE_CONTAINER);
            }
        } else if (endMapping instanceof NodeMapping) {
            possibleViewTypes.add(DIAGRAM_D_NODE);
        } else if (endMapping instanceof EdgeMapping) {
            possibleViewTypes.add(DIAGRAM_D_EDGE_TYPE);
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

    private void collectReconnectEdgeDescriptionVariableTypes(Map<String, VariableType> availableVariables) {
        Collection<String> possibleContainerTypes = new LinkedHashSet<>();
        EList<EdgeMapping> mappings = ((ReconnectEdgeDescription) target).getMappings();
        collectPotentialContainerTypes(possibleContainerTypes, Sets.<String> newLinkedHashSet(), mappings);
        availableVariables.put(IInterpreterSiriusVariables.CONTAINER, VariableType.fromStrings(possibleContainerTypes));

        Set<String> sourceSemanticType = new LinkedHashSet<>();
        Set<String> sourceViewType = new LinkedHashSet<>();
        Set<String> targetSemanticType = new LinkedHashSet<>();
        Set<String> targetViewType = new LinkedHashSet<>();
        for (EdgeMapping edgeMapping : mappings) {
            for (DiagramElementMapping m : edgeMapping.getSourceMapping()) {
                collectTypes(sourceSemanticType, sourceViewType, m);
            }
            for (DiagramElementMapping m : edgeMapping.getTargetMapping()) {
                collectTypes(targetSemanticType, targetViewType, m);
            }
        }
        availableVariables.put(IInterpreterSiriusVariables.SOURCE, VariableType.fromStrings(sourceSemanticType));
        availableVariables.put(IInterpreterSiriusVariables.SOURCE_VIEW, VariableType.fromStrings(sourceViewType));
        availableVariables.put(IInterpreterSiriusVariables.TARGET, VariableType.fromStrings(targetSemanticType));
        availableVariables.put(IInterpreterSiriusVariables.TARGET_VIEW, VariableType.fromStrings(targetViewType));
        availableVariables.put(IInterpreterSiriusVariables.DIAGRAM, VariableType.fromString(DIAGRAM_D_SEMANTIC_DIAGRAM));
    }

    /**
     * An {@link IInterpretedExpressionTargetSwitch} that delegates to the defaultSwitch or the diagram specific switch,
     * according to the package of the considered element.
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
            Collection<String> targetTypes = new LinkedHashSet<>();
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
