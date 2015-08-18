/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.business.api.diagramtype;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.description.DefaultInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.diagramtype.HeaderData;
import org.eclipse.sirius.diagram.business.api.diagramtype.ICollapseUpdater;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.query.InstanceRoleQuery;
import org.eclipse.sirius.diagram.sequence.description.DescriptionFactory;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.SequenceDiagramToolDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.sequence.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.sequence.ordering.provider.OrderingItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.sequence.provider.SequenceItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.sequence.template.provider.TemplateItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.sequence.ui.business.internal.diagramtype.SequenceCollapseUpdater;
import org.eclipse.sirius.diagram.sequence.ui.business.internal.diagramtype.SequenceToolInterpretedExpressionSwitch;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Provides diagram description for Sequence diagram.
 * 
 * @author ymortier, pcdavid
 */
public class SequenceDiagramTypeProvider implements IDiagramDescriptionProvider {

    private final Predicate<DSemanticDecorator> isSequenceSemanticDecorator = new Predicate<DSemanticDecorator>() {
        public boolean apply(DSemanticDecorator input) {
            boolean result = false;
            if (input instanceof DDiagram) {
                result = checkSequenceDescriptionPackage(((DDiagram) input).getDescription().eClass());
            } else if (input instanceof DDiagramElement) {
                result = isSequenceDDiagramElement.apply((DDiagramElement) input);
            }
            return result;
        }
    };

    private final Predicate<DDiagramElement> isSequenceDDiagramElement = new Predicate<DDiagramElement>() {
        public boolean apply(DDiagramElement input) {
            // check that input has a Sequence mapping or is a simple node
            // connected to a sequence message : a lost end.
            DiagramElementMapping mappingToCheck = new DiagramElementMappingQuery(input.getDiagramElementMapping()).getRootMapping();
            return checkSequenceDescriptionPackage(mappingToCheck.eClass()) || LostMessageEnd.viewpointElementPredicate().apply(input);
        }
    };

    private boolean checkSequenceDescriptionPackage(EClass eClass) {
        return DescriptionPackage.eINSTANCE.equals(eClass.getEPackage());
    }

    /**
     * {@inheritDoc}
     */
    public DiagramDescription createDiagramDescription() {
        return DescriptionFactory.eINSTANCE.createSequenceDiagramDescription();
    }

    /**
     * {@inheritDoc}
     */
    public Collection<? extends CommandParameter> collectToolCommands(EObject context) {
        Collection<CommandParameter> result = Lists.newArrayList();
        Collection<EReference> refs = Arrays.asList(ToolPackage.Literals.TOOL_SECTION__OWNED_TOOLS, ToolPackage.Literals.TOOL_GROUP__TOOLS);
        for (EReference ref : refs) {
            if (ref.getEContainingClass().equals(context.eClass())) {
                collectInstanceRoleCreation(result, ref);
                collectExecutionCreation(result, ref);
                collectStateCreation(result, ref);
                collectMessageCreation(result, ref);
                collectInteractionUse(result, ref);
                collectCombinedFragmentCreation(result, ref);
                collectOperandCreation(result, ref);
                collectReorderCreations(result, ref);
                collectObservationPointCreation(result, ref);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<? extends CommandParameter> collectMappingsCommands() {
        Collection<CommandParameter> result = Lists.newArrayList();
        // Nodes
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createInstanceRoleMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createExecutionMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createStateMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createEndOfLifeMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__NODE_MAPPINGS, DescriptionFactory.eINSTANCE.createObservationPointMapping()));
        // Containers
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createInteractionUseMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE
                .createCombinedFragmentMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__CONTAINER_MAPPINGS, DescriptionFactory.eINSTANCE.createOperandMapping()));
        // Edges
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createBasicMessageMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createReturnMessageMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createCreationMessageMapping()));
        result.add(new CommandParameter(null, org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.LAYER__EDGE_MAPPINGS, DescriptionFactory.eINSTANCE.createDestructionMessageMapping()));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public AdapterFactory getAdapterFactory() {
        ComposedAdapterFactory composed = new ComposedAdapterFactory();
        composed.addAdapterFactory(new SequenceItemProviderAdapterFactory());
        composed.addAdapterFactory(new TemplateItemProviderAdapterFactory());
        composed.addAdapterFactory(new DescriptionItemProviderAdapterFactory());
        composed.addAdapterFactory(new OrderingItemProviderAdapterFactory());
        composed.addAdapterFactory(new ToolItemProviderAdapterFactory());
        return composed;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider#handles(org.eclipse.emf.ecore.EPackage)
     */
    public boolean handles(EPackage ePackage) {
        return DescriptionPackage.eINSTANCE.getNsURI().equals(ePackage.getNsURI()) || org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage.eINSTANCE.getNsURI().equals(ePackage.getNsURI());
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider#createInterpretedExpressionSwitch(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    public IInterpretedExpressionTargetSwitch createInterpretedExpressionSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch parentSwitch) {
        return new SequenceGlobalInterpretedTargetSwitch(feature, parentSwitch);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider#allowsLayoutingModeActivation()
     */
    public boolean allowsLayoutingModeActivation() {
        return false;
    }

    @Override
    public boolean allowsPinUnpin(DDiagramElement element) {
        return !isSequenceDDiagramElement.apply(element);
    }

    @Override
    public boolean allowsHideReveal(DDiagramElement element) {
        return !isSequenceDDiagramElement.apply(element);
    }

    @Override
    public boolean allowsCopyPasteLayout(DSemanticDecorator element) {
        return !isSequenceSemanticDecorator.apply(element);
    }

    private void collectInstanceRoleCreation(Collection<CommandParameter> result, EReference ref) {
        InstanceRoleCreationTool instanceRoleCreationTool = ToolFactory.eINSTANCE.createInstanceRoleCreationTool();
        addVariables(instanceRoleCreationTool);
        result.add(new CommandParameter(null, ref, instanceRoleCreationTool));
    }

    private void collectExecutionCreation(Collection<CommandParameter> result, EReference ref) {
        ExecutionCreationTool executionCreationTool = ToolFactory.eINSTANCE.createExecutionCreationTool();
        addVariables(executionCreationTool);
        result.add(new CommandParameter(null, ref, executionCreationTool));
    }

    private void collectStateCreation(Collection<CommandParameter> result, EReference ref) {
        StateCreationTool stateCreationTool = ToolFactory.eINSTANCE.createStateCreationTool();
        addVariables(stateCreationTool);
        result.add(new CommandParameter(null, ref, stateCreationTool));
    }

    private void collectMessageCreation(Collection<CommandParameter> result, EReference ref) {
        MessageCreationTool messageCreationTool = ToolFactory.eINSTANCE.createMessageCreationTool();
        addVariables(messageCreationTool);
        result.add(new CommandParameter(null, ref, messageCreationTool));
    }

    private void collectCombinedFragmentCreation(Collection<CommandParameter> result, EReference ref) {
        CombinedFragmentCreationTool combinedFragmentCreationTool = ToolFactory.eINSTANCE.createCombinedFragmentCreationTool();
        addVariables(combinedFragmentCreationTool);
        result.add(new CommandParameter(null, ref, combinedFragmentCreationTool));
    }

    private void collectOperandCreation(Collection<CommandParameter> result, EReference ref) {
        OperandCreationTool operandCreationTool = ToolFactory.eINSTANCE.createOperandCreationTool();
        addVariables(operandCreationTool);
        result.add(new CommandParameter(null, ref, operandCreationTool));
    }

    private void collectReorderCreations(Collection<CommandParameter> result, EReference ref) {
        ReorderTool reorderCreationTool = ToolFactory.eINSTANCE.createReorderTool();
        addVariables(reorderCreationTool);
        result.add(new CommandParameter(null, ref, reorderCreationTool));

        InstanceRoleReorderTool irReorderCreationTool = ToolFactory.eINSTANCE.createInstanceRoleReorderTool();
        addVariables(irReorderCreationTool);
        result.add(new CommandParameter(null, ref, irReorderCreationTool));
    }

    private void collectInteractionUse(Collection<CommandParameter> result, EReference ref) {
        InteractionUseCreationTool interactionUseCreationTool = ToolFactory.eINSTANCE.createInteractionUseCreationTool();
        addVariables(interactionUseCreationTool);
        result.add(new CommandParameter(null, ref, interactionUseCreationTool));
    }

    private void collectObservationPointCreation(Collection<CommandParameter> result, EReference ref) {
        ObservationPointCreationTool obsPointCreationTool = ToolFactory.eINSTANCE.createObservationPointCreationTool();
        addVariables(obsPointCreationTool);
        result.add(new CommandParameter(null, ref, obsPointCreationTool));
    }

    private void addVariables(SequenceDiagramToolDescription sequenceDiagramTool) {
        for (EReference ref : sequenceDiagramTool.eClass().getEAllReferences()) {
            if (ref.isContainment() && ref.getEType() instanceof EClass) {
                EClass k = (EClass) ref.getEType();
                EClass variable = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.getAbstractVariable();
                if (variable.isSuperTypeOf(k)) {
                    AbstractVariable var = (AbstractVariable) k.getEPackage().getEFactoryInstance().create(k);
                    EAnnotation annotation = ref.getEAnnotation("toolVariable"); //$NON-NLS-1$
                    if (annotation != null) {
                        var.setName(annotation.getDetails().get("name")); //$NON-NLS-1$
                    } else {
                        var.setName(ref.getName());
                    }
                    sequenceDiagramTool.eSet(ref, var);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider#supportHeader()
     */
    public boolean supportHeader() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider#getHeaderData()
     */
    public LinkedList<HeaderData> getHeaderData(DDiagram diagram) {
        LinkedList<HeaderData> result = Lists.newLinkedList();
        if (diagram instanceof SequenceDDiagram) {
            Diagram gmfDiagram = getGmfDiagram(diagram);

            if (gmfDiagram != null) {
                Option<SequenceDiagram> optionalSequenceDiagram = ISequenceElementAccessor.getSequenceDiagram(gmfDiagram);
                if (optionalSequenceDiagram.some()) {
                    Collection<InstanceRole> instanceRoles = optionalSequenceDiagram.get().getSortedInstanceRole();
                    for (InstanceRole instanceRole : instanceRoles) {
                        result.add(new InstanceRoleQuery(instanceRole).getHeaderData());
                    }
                }

            }
        }
        return result;
    }

    private Diagram getGmfDiagram(DDiagram diagram) {
        Diagram gmfDiagram = null;
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) diagram;
        Option<Diagram> optionDiagram = new DDiagramGraphicalQuery(sequenceDDiagram).getAssociatedGMFDiagram();
        if (optionDiagram.some()) {
            gmfDiagram = optionDiagram.get();
        } else {
            Resource eResource = diagram.eResource();
            if (eResource != null) {
                for (Diagram diag : Iterables.filter(eResource.getContents(), Diagram.class)) {
                    if (diagram.equals(diag.getElement())) {
                        gmfDiagram = diag;
                        break;
                    }
                }
            }
        }
        return gmfDiagram;
    }

    /**
     * An {@link IInterpretedExpressionTargetSwitch} that delegates to the
     * defaultSwitch or the diagram specific switch, according to the package of
     * the considered element.
     * 
     * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
     * 
     */
    private class SequenceGlobalInterpretedTargetSwitch implements IInterpretedExpressionTargetSwitch {

        private final DefaultInterpretedExpressionTargetSwitch defaultSwitch;

        private final SequenceDiagramInterpretedExpressionSwitch sequenceSwitch;

        private final SequenceToolInterpretedExpressionSwitch toolSwitch;

        public SequenceGlobalInterpretedTargetSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch parentSwitch) {
            defaultSwitch = new DefaultInterpretedExpressionTargetSwitch(feature, parentSwitch);
            sequenceSwitch = new SequenceDiagramInterpretedExpressionSwitch(feature, parentSwitch);
            toolSwitch = new SequenceToolInterpretedExpressionSwitch(feature, parentSwitch);
        }

        /**
         * 
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
         */
        public Option<Collection<String>> doSwitch(EObject target, boolean considerFeature) {
            Collection<String> targetTypes = Sets.newLinkedHashSet();
            Option<Collection<String>> expressionTarget = Options.newSome(targetTypes);
            if (target != null) {
                // Step 1 : apply the sequence diagram specific switch
                sequenceSwitch.setConsiderFeature(considerFeature);
                expressionTarget = sequenceSwitch.doSwitch(target);

                // If no result has been found
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    // Step 2 : apply the sequence tool specific switch
                    toolSwitch.setConsiderFeature(considerFeature);
                    expressionTarget = toolSwitch.doSwitch(target);
                }

                // If no result has been found
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    // Step 3 : we use the default switch
                    expressionTarget = defaultSwitch.doSwitch(target, considerFeature);
                }
            }
            return expressionTarget;
        }

        @Override
        public EObject getFirstRelevantContainer(EObject obj) {
            // Can be null only during default switch initialization.
            return defaultSwitch != null ? defaultSwitch.getFirstRelevantContainer(obj) : null;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramDescriptionProvider
     *      #getCollapseUpdater(DDiagram)
     */
    public Option<? extends ICollapseUpdater> getCollapseUpdater(DDiagram diagram) {
        if (diagram != null && diagram.getDescription() != null && handles(diagram.getDescription().eClass().getEPackage())) {
            return Options.newSome(new SequenceCollapseUpdater());
        }
        return Options.newNone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject) {
        return completeToolTipText(toolTipText, eObject, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject, EStructuralFeature feature) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtil.isEmpty(toolTipText)) {
            sb.append(toolTipText + "\n"); //$NON-NLS-1$
            sb.append("\n"); //$NON-NLS-1$
        }

        sb.append("Additional available variable for Sequence Diagram:");
        sb.append("\n . ");
        sb.append("endBefore"); //$NON-NLS-1$
        sb.append(": "); //$NON-NLS-1$
        sb.append("an EventEnd referencing the semantic event end ");

        return sb.toString();
    }
}
