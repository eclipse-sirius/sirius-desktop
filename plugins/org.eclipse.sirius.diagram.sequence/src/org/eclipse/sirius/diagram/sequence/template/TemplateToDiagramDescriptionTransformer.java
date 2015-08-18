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
package org.eclipse.sirius.diagram.sequence.template;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.FoldingStyle;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;
import org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.tools.api.command.semantic.RemoveDanglingReferences;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Class responsible for refreshing the computed representations based on the
 * given sequence diagram template.
 * 
 * @author cbrun
 * 
 */
public class TemplateToDiagramDescriptionTransformer {

    private TSequenceDiagram template;

    private Collection<Runnable> posts;

    private ModelGeneratedMaker marker = new ModelGeneratedMaker();

    private Template2SequenceDiag template2SequenceDiag = new Template2SequenceDiag(marker);

    /**
     * A TSequenceDiagram produces a SequenceDiagramDescription element with the
     * same name, domainClass and endsOrdering. The lifelineMappings and
     * messageMappings produce concrete mappings inside the
     * SequenceDiagramDescription as specified below.
     * 
     * @author cbrun
     * 
     */
    private class Template2SequenceDiag extends AbstractRule<TSequenceDiagram, SequenceDiagramDescription> {

        public Template2SequenceDiag(ModelGeneratedMaker marker) {
            super(marker);
        }

        public SequenceDiagramDescription apply(TSequenceDiagram from) {
            SequenceDiagramDescription to = getOrCreate(from, DescriptionPackage.eINSTANCE.getSequenceDiagramDescription());
            to.setName(from.getName());
            to.setEndsOrdering(from.getEndsOrdering());
            to.setDomainClass(from.getDomainClass());
            marker.clearGenerateds(to.getNodeMappings());
            to.getNodeMappings().addAll(AbstractRule.transform(from.getLifelineMappings(), lifeline2InstanceRoleMapping));
            marker.clearGenerateds(to.getEdgeMappings());
            to.getEdgeMappings().addAll(AbstractRule.transform(Lists.newArrayList(Iterables.filter(from.getMessageMappings(), TBasicMessageMapping.class)), basicMessageToEdgeMapping));
            to.getEdgeMappings().addAll(AbstractRule.transform(Lists.newArrayList(Iterables.filter(from.getMessageMappings(), TCreationMessageMapping.class)), creationMessageToEdgeMapping));
            to.getEdgeMappings().addAll(AbstractRule.transform(Lists.newArrayList(Iterables.filter(from.getMessageMappings(), TDestructionMessageMapping.class)), destructionMessageToEdgeMapping));
            to.getEdgeMappings().addAll(AbstractRule.transform(Lists.newArrayList(Iterables.filter(from.getMessageMappings(), TReturnMessageMapping.class)), returnMessageToEdgeMapping));
            return to;
        }

    }

    private Lifeline2InstanceRoleMapping lifeline2InstanceRoleMapping = new Lifeline2InstanceRoleMapping(marker);

    private class Lifeline2InstanceRoleMapping extends AbstractRule<TLifelineMapping, InstanceRoleMapping> {

        public Lifeline2InstanceRoleMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public InstanceRoleMapping apply(TLifelineMapping from) {
            InstanceRoleMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getInstanceRoleMapping());
            to.setName(from.getName() + " Instance Role");
            to.setDomainClass(from.getDomainClass());
            to.setSemanticCandidatesExpression(from.getSemanticCandidatesExpression());
            marker.clearGenerateds(to.getBorderedNodeMappings());
            to.getBorderedNodeMappings().add(lifeline2ExecutionMapping.apply(from));

            to.setCreateElements(true);
            to.setStyle((NodeStyleDescription) copy(from.getInstanceRoleStyle()));
            if (to.getStyle() != null) {
                to.getStyle().setResizeKind(ResizeKind.NSEW_LITERAL);
            }
            return to;
        }

    }

    private Lifeline2ExecutionMapping lifeline2ExecutionMapping = new Lifeline2ExecutionMapping(marker);

    private class Lifeline2ExecutionMapping extends AbstractRule<TLifelineMapping, ExecutionMapping> {

        private static final String SELF = "var:self"; //$NON-NLS-1$

        public Lifeline2ExecutionMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public ExecutionMapping apply(TLifelineMapping from) {
            ExecutionMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getExecutionMapping());
            to.setName(from.getName() + " Execution");
            to.setDomainClass(from.getDomainClass());
            to.setSemanticCandidatesExpression(SELF);
            to.setStartingEndFinderExpression(SELF);
            to.setFinishingEndFinderExpression(SELF);

            if (from.getLifelineStyle() != null) {
                to.setStyle(lifelineStyleToNodeStyle.apply(from.getLifelineStyle()));
            }
            marker.clearGenerateds(to.getConditionnalStyles());
            to.getConditionnalStyles().addAll(AbstractRule.transform(from.getConditionalLifeLineStyles(), conditionalLifelineStyleToNodeStyle));
            to.setCreateElements(true);
            marker.clearGenerateds(to.getBorderedNodeMappings());
            to.getBorderedNodeMappings().addAll(AbstractRule.transform(from.getExecutionMappings(), execution2ExecutionMaping));
            to.getBorderedNodeMappings().add(lifeline2EndOfLineMapping.apply(from));
            return to;
        }

    }

    private Lifeline2EndOfLifeMapping lifeline2EndOfLineMapping = new Lifeline2EndOfLifeMapping(marker);

    private class Lifeline2EndOfLifeMapping extends AbstractRule<TLifelineMapping, EndOfLifeMapping> {

        private static final String SELF = "var:self"; //$NON-NLS-1$

        public Lifeline2EndOfLifeMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public EndOfLifeMapping apply(TLifelineMapping from) {
            EndOfLifeMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getEndOfLifeMapping());
            to.setName(from.getName() + " EOL"); //$NON-NLS-1$
            to.setSemanticCandidatesExpression(SELF);
            to.setSemanticElements(SELF);
            to.setDomainClass(from.getDomainClass());
            to.setCreateElements(true);
            to.setPreconditionExpression(from.getEolVisibleExpression());
            to.setStyle((NodeStyleDescription) copy(from.getEndOfLifeStyle()));
            return to;
        }

    }

    private Execution2ExecutionMapping execution2ExecutionMaping = new Execution2ExecutionMapping(marker);

    private class Execution2ExecutionMapping extends AbstractRule<TExecutionMapping, ExecutionMapping> {

        public Execution2ExecutionMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public ExecutionMapping apply(TExecutionMapping from) {
            ExecutionMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getExecutionMapping());
            to.setName(from.getName());
            to.setDomainClass(from.getDomainClass());
            to.setSemanticCandidatesExpression(from.getSemanticCandidatesExpression());
            to.setFinishingEndFinderExpression(from.getFinishingEndFinderExpression());
            to.setStartingEndFinderExpression(from.getStartingEndFinderExpression());
            to.getReusedBorderedNodeMappings().clear();
            if (from.isRecursive()) {
                to.getReusedBorderedNodeMappings().add(to);
            }
            if (from.getStyle() != null) {
                to.setStyle(executionStyleToNodeStyle.apply(from.getStyle()));
            }
            marker.clearGenerateds(to.getConditionnalStyles());
            to.getConditionnalStyles().addAll(AbstractRule.transform(from.getConditionalStyles(), conditionalExecutionStyleToNodeStyle));
            to.getBorderedNodeMappings().addAll(AbstractRule.transform(from.getExecutionMappings(), execution2ExecutionMaping));
            return to;
        }

    }

    private TBasicMessage2EdgeMapping basicMessageToEdgeMapping = new TBasicMessage2EdgeMapping(marker);

    private class TBasicMessage2EdgeMapping extends AbstractRule<TBasicMessageMapping, BasicMessageMapping> {

        public TBasicMessage2EdgeMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public BasicMessageMapping apply(final TBasicMessageMapping from) {
            final BasicMessageMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getBasicMessageMapping());

            copyMappingData(from, to);
            postOp(new Runnable() {

                public void run() {
                    Collection<EObject> sourceOutputs = AbstractRule.collectGeneratedElements(execution2ExecutionMaping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getSource());
                    sourceOutputs.addAll(AbstractRule.collectGeneratedElements(lifeline2ExecutionMapping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getSource()));
                    to.getSourceMapping().clear();
                    to.getSourceMapping().addAll(Lists.newArrayList(Iterables.filter(sourceOutputs, AbstractNodeMapping.class)));

                    Collection<EObject> targetOutputs = AbstractRule.collectGeneratedElements(execution2ExecutionMaping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getTarget());
                    targetOutputs.addAll(AbstractRule.collectGeneratedElements(lifeline2ExecutionMapping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getTarget()));
                    to.getTargetMapping().clear();
                    to.getTargetMapping().addAll(Lists.newArrayList(Iterables.filter(targetOutputs, AbstractNodeMapping.class)));
                }

            });

            return to;
        }

    }

    private TCreationMessage2EdgeMapping creationMessageToEdgeMapping = new TCreationMessage2EdgeMapping(marker);

    private class TCreationMessage2EdgeMapping extends AbstractRule<TCreationMessageMapping, CreationMessageMapping> {

        public TCreationMessage2EdgeMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public CreationMessageMapping apply(final TCreationMessageMapping from) {
            final CreationMessageMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getCreationMessageMapping());

            copyMappingData(from, to);
            postOp(new Runnable() {

                public void run() {
                    Collection<EObject> sourceOutputs = AbstractRule.collectGeneratedElements(execution2ExecutionMaping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getSource());
                    sourceOutputs.addAll(AbstractRule.collectGeneratedElements(lifeline2ExecutionMapping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getSource()));
                    to.getSourceMapping().clear();
                    to.getSourceMapping().addAll(Lists.newArrayList(Iterables.filter(sourceOutputs, AbstractNodeMapping.class)));

                    Collection<EObject> targetOutputs = Lists.newArrayList();
                    targetOutputs.addAll(AbstractRule.collectGeneratedElements(lifeline2InstanceRoleMapping, DescriptionPackage.eINSTANCE.getInstanceRoleMapping(), from.getTarget()));
                    to.getTargetMapping().clear();
                    to.getTargetMapping().addAll(Lists.newArrayList(Iterables.filter(targetOutputs, AbstractNodeMapping.class)));

                }

            });

            return to;
        }

    }

    private TDestructionMessage2EdgeMapping destructionMessageToEdgeMapping = new TDestructionMessage2EdgeMapping(marker);

    private class TDestructionMessage2EdgeMapping extends AbstractRule<TDestructionMessageMapping, DestructionMessageMapping> {

        public TDestructionMessage2EdgeMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public DestructionMessageMapping apply(final TDestructionMessageMapping from) {
            final DestructionMessageMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getDestructionMessageMapping());

            copyMappingData(from, to);
            postOp(new Runnable() {

                public void run() {
                    Collection<EObject> sourceOutputs = AbstractRule.collectGeneratedElements(execution2ExecutionMaping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getSource());
                    sourceOutputs.addAll(AbstractRule.collectGeneratedElements(lifeline2ExecutionMapping, DescriptionPackage.eINSTANCE.getExecutionMapping(), from.getSource()));
                    to.getSourceMapping().clear();
                    to.getSourceMapping().addAll(Lists.newArrayList(Iterables.filter(sourceOutputs, AbstractNodeMapping.class)));

                    Collection<EObject> targetOutputs = Lists.newArrayList();
                    targetOutputs.addAll(AbstractRule.collectGeneratedElements(lifeline2EndOfLineMapping, DescriptionPackage.eINSTANCE.getEndOfLifeMapping(), from.getTarget()));
                    to.getTargetMapping().clear();
                    to.getTargetMapping().addAll(Lists.newArrayList(Iterables.filter(targetOutputs, AbstractNodeMapping.class)));

                }

            });

            return to;
        }
    }

    private TReturnMessage2EdgeMapping returnMessageToEdgeMapping = new TReturnMessage2EdgeMapping(marker);

    private class TReturnMessage2EdgeMapping extends AbstractRule<TReturnMessageMapping, ReturnMessageMapping> {

        public TReturnMessage2EdgeMapping(ModelGeneratedMaker marker) {
            super(marker);
        }

        public ReturnMessageMapping apply(TReturnMessageMapping from) {
            ReturnMessageMapping to = getOrCreate(from, DescriptionPackage.eINSTANCE.getReturnMessageMapping());
            to.setName(from.getName());
            to.setDomainClass(from.getDomainClass());
            to.setSemanticCandidatesExpression(from.getSemanticCandidatesExpression());
            to.setSendingEndFinderExpression(from.getSendingEndFinderExpression());
            to.setReceivingEndFinderExpression(from.getReceivingEndFinderExpression());
            if (from.getStyle() != null) {
                to.setStyle(messageStyleToEdgeStyle.apply(from.getStyle()));
            }
            marker.clearGenerateds(to.getConditionnalStyles());
            to.getConditionnalStyles().addAll(AbstractRule.transform(from.getConditionalStyle(), conditionalMessageStyleToConditional));
            to.setInvocationMessageFinderExpression(from.getInvocationMessageFinderExpression());
            return to;
        }

    }

    private TMessageStyle2EdgeStyle messageStyleToEdgeStyle = new TMessageStyle2EdgeStyle(marker);

    private static class TMessageStyle2EdgeStyle extends AbstractRule<TMessageStyle, EdgeStyleDescription> {

        public TMessageStyle2EdgeStyle(ModelGeneratedMaker marker) {
            super(marker);
        }

        public EdgeStyleDescription apply(TMessageStyle from) {
            EdgeStyleDescription to = getOrCreate(from, StylePackage.eINSTANCE.getEdgeStyleDescription());
            to.setFoldingStyle(FoldingStyle.NONE_LITERAL);
            to.getCenterLabelStyleDescription().setLabelExpression(from.getLabelExpression());
            to.setLineStyle(from.getLineStyle());
            to.setSourceArrow(from.getSourceArrow());
            to.setStrokeColor(from.getStrokeColor());
            to.setSizeComputationExpression("2"); //$NON-NLS-1$
            to.setTargetArrow(from.getTargetArrow());
            return to;
        }

    }

    private TConditionalMessageStyle2ConditionalEdgeStyle conditionalMessageStyleToConditional = new TConditionalMessageStyle2ConditionalEdgeStyle(marker);

    private class TConditionalMessageStyle2ConditionalEdgeStyle extends AbstractRule<TConditionalMessageStyle, ConditionalEdgeStyleDescription> {

        public TConditionalMessageStyle2ConditionalEdgeStyle(ModelGeneratedMaker marker) {
            super(marker);
        }

        public ConditionalEdgeStyleDescription apply(TConditionalMessageStyle from) {
            ConditionalEdgeStyleDescription to = getOrCreate(from, org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getConditionalEdgeStyleDescription());
            to.setPredicateExpression(from.getPredicateExpression());
            if (from.getStyle() != null) {
                to.setStyle(messageStyleToEdgeStyle.apply(from.getStyle()));
            }
            return to;
        }
    }

    private TLifelineStyle2NodeStyle lifelineStyleToNodeStyle = new TLifelineStyle2NodeStyle(marker);

    private static class TLifelineStyle2NodeStyle extends AbstractRule<TLifelineStyle, SquareDescription> {

        public TLifelineStyle2NodeStyle(ModelGeneratedMaker marker) {
            super(marker);
        }

        public SquareDescription apply(TLifelineStyle from) {
            SquareDescription to = getOrCreate(from, StylePackage.eINSTANCE.getSquareDescription());
            to.setBorderColor(from.getLifelineColor());
            to.setBorderSizeComputationExpression(from.getLifelineWidthComputationExpression());
            to.setLabelPosition(LabelPosition.NODE_LITERAL);
            to.setResizeKind(ResizeKind.NSEW_LITERAL);
            to.setWidth(1);
            to.setHeight(40);
            to.setShowIcon(false);
            return to;
        }

    }

    private TConditionalLifelineStyle2ConditionalNodeStyle conditionalLifelineStyleToNodeStyle = new TConditionalLifelineStyle2ConditionalNodeStyle(marker);

    private class TConditionalLifelineStyle2ConditionalNodeStyle extends AbstractRule<TConditionalLifelineStyle, ConditionalNodeStyleDescription> {

        public TConditionalLifelineStyle2ConditionalNodeStyle(ModelGeneratedMaker marker) {
            super(marker);
        }

        public ConditionalNodeStyleDescription apply(TConditionalLifelineStyle from) {
            ConditionalNodeStyleDescription to = getOrCreate(from, org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getConditionalNodeStyleDescription());
            to.setPredicateExpression(from.getPredicateExpression());
            if (from.getStyle() != null) {
                to.setStyle(lifelineStyleToNodeStyle.apply(from.getStyle()));
            }
            return to;
        }

    }

    private TExecutionStyle2NodeStyle executionStyleToNodeStyle = new TExecutionStyle2NodeStyle(marker);

    private static class TExecutionStyle2NodeStyle extends AbstractRule<TExecutionStyle, SquareDescription> {

        public TExecutionStyle2NodeStyle(ModelGeneratedMaker marker) {
            super(marker);
        }

        public SquareDescription apply(TExecutionStyle from) {
            SquareDescription to = getOrCreate(from, StylePackage.eINSTANCE.getSquareDescription());
            if (from.getBackgroundColor() != null) {
                to.setColor(from.getBackgroundColor());
            }
            if (from.getBorderColor() != null) {
                to.setBorderColor(from.getBorderColor());
            }
            to.setBorderSizeComputationExpression(from.getBorderSizeComputationExpression());
            to.setResizeKind(ResizeKind.NORTH_SOUTH_LITERAL);
            to.setShowIcon(false);
            to.setLabelExpression("[''/]"); //$NON-NLS-1$
            to.setWidth(2);
            to.setHeight(3);
            return to;
        }
    }

    private TConditionalExecutionStyle2NodeStyle conditionalExecutionStyleToNodeStyle = new TConditionalExecutionStyle2NodeStyle(marker);

    private class TConditionalExecutionStyle2NodeStyle extends AbstractRule<TConditionalExecutionStyle, ConditionalNodeStyleDescription> {

        public TConditionalExecutionStyle2NodeStyle(ModelGeneratedMaker marker) {
            super(marker);
        }

        public ConditionalNodeStyleDescription apply(TConditionalExecutionStyle from) {
            ConditionalNodeStyleDescription to = getOrCreate(from, org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getConditionalNodeStyleDescription());
            to.setPredicateExpression(from.getPredicateExpression());
            if (from.getStyle() != null) {
                to.setStyle(executionStyleToNodeStyle.apply(from.getStyle()));
            }
            return to;
        }

    }

    /**
     * Create a new transformer.
     * 
     * @param template
     *            the template to update.
     */
    public TemplateToDiagramDescriptionTransformer(TSequenceDiagram template) {
        this.template = template;
        posts = Lists.newArrayList();
    }

    private void copyMappingData(final TSourceTargetMessageMapping from, final MessageMapping to) {
        to.setName(from.getName());
        to.setDomainClass(from.getDomainClass());
        to.setSemanticCandidatesExpression(from.getSemanticCandidatesExpression());
        to.setSourceFinderExpression(from.getSourceFinderExpression());
        to.setTargetFinderExpression(from.getTargetFinderExpression());
        to.setSendingEndFinderExpression(from.getSendingEndFinderExpression());
        to.setReceivingEndFinderExpression(from.getReceivingEndFinderExpression());
        to.setUseDomainElement(from.isUseDomainElement());
        if (from.getStyle() != null) {
            to.setStyle(messageStyleToEdgeStyle.apply(from.getStyle()));
        }
        marker.clearGenerateds(to.getConditionnalStyles());
        to.getConditionnalStyles().addAll(AbstractRule.transform(from.getConditionalStyle(), conditionalMessageStyleToConditional));

    }

    /**
     * refresh the representation based on the template.
     * 
     * @return the created Sequence Diagram.
     */
    public SequenceDiagramDescription refresh() {
        SequenceDiagramDescription description = template2SequenceDiag.apply(template);
        template.getOwnedRepresentations().add(description);
        postProcess();
        cleanDanglingReferences();
        return description;
    }

    private void cleanDanglingReferences() {
        RemoveDanglingReferences.removeDanglingReferences(template);
    }

    private void postProcess() {
        for (Runnable postProcess : posts) {
            postProcess.run();
        }
    }

    private void postOp(Runnable runnable) {
        this.posts.add(runnable);
    }

    /**
     * Return true if the transformation is going to override the given feature
     * in the given EObject.
     * 
     * @param eObj
     *            eObject to update.
     * @param feature
     *            feature to check.
     * @return true if the transformation is going to override the given feature
     *         in the given EObject.
     * 
     */
    public boolean isOverriding(EObject eObj, EStructuralFeature feature) {
        Collection<EStructuralFeature> overriden = Sets.newLinkedHashSet();
        overriden.add(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getIdentifiedElement_Name());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramDescription_DomainClass());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramDescription_NodeMappings());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getAbstractNodeMapping_DomainClass());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping_DomainClass());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping_SourceMapping());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping_TargetMapping());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping_SourceFinderExpression());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping_TargetFinderExpression());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getEdgeMapping_UseDomainElement());
        overriden.add(org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression());
        overriden.add(org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE.getBasicLabelStyleDescription_ShowIcon());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramElementMapping_SemanticCandidatesExpression());
        overriden.add(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramElementMapping_CreateElements());
        overriden.add(DescriptionPackage.eINSTANCE.getSequenceDiagramDescription_EndsOrdering());
        overriden.add(DescriptionPackage.eINSTANCE.getDelimitedEventMapping_FinishingEndFinderExpression());
        overriden.add(DescriptionPackage.eINSTANCE.getDelimitedEventMapping_StartingEndFinderExpression());
        overriden.add(DescriptionPackage.eINSTANCE.getReturnMessageMapping_InvocationMessageFinderExpression());
        overriden.add(DescriptionPackage.eINSTANCE.getMessageMapping_ReceivingEndFinderExpression());
        overriden.add(DescriptionPackage.eINSTANCE.getMessageMapping_SendingEndFinderExpression());

        return overriden.contains(feature);
    }

    private EObject copy(EObject src) {
        if (src != null) {
            return EcoreUtil.copy(src);
        }
        return null;
    }
}
