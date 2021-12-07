/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;

/**
 * An implementation of <code>IDiagramCommandFactory</code> which delegates to
 * another one. Useful to customize only some of the methods when one does not
 * control the instantiation of the factory to customize.
 * 
 * @author pcdavid
 */
public class DelegatingDiagramCommandFactory implements IDiagramCommandFactory {
    private final IDiagramCommandFactory baseFactory;

    /**
     * Constructor.
     * 
     * @param baseFactory
     *            the factory to delegate to.
     */
    public DelegatingDiagramCommandFactory(IDiagramCommandFactory baseFactory) {
        this.baseFactory = Objects.requireNonNull(baseFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildCreateNodeCommandFromTool(DNode node, NodeCreationDescription tool) {
        return baseFactory.buildCreateNodeCommandFromTool(node, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildCreateContainerCommandFromTool(DDiagram diagram, ContainerCreationDescription tool) {
        return baseFactory.buildCreateContainerCommandFromTool(diagram, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildCreateContainerCommandFromTool(DDiagramElementContainer nodeContainer, ContainerCreationDescription tool) {
        return baseFactory.buildCreateContainerCommandFromTool(nodeContainer, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DCommand buildCreateDiagramFromDescription(DiagramDescription description, EObject semanticElement, IProgressMonitor monitor) {
        return baseFactory.buildCreateDiagramFromDescription(description, semanticElement, monitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildCreateEdgeCommandFromTool(EdgeTarget source, EdgeTarget target, EdgeCreationDescription tool) {
        return baseFactory.buildCreateEdgeCommandFromTool(source, target, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildCreateNodeCommandFromTool(DDiagram diagram, NodeCreationDescription tool) {
        return baseFactory.buildCreateNodeCommandFromTool(diagram, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildCreateNodeCommandFromTool(DDiagramElementContainer container, NodeCreationDescription tool) {
        return baseFactory.buildCreateNodeCommandFromTool(container, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateRepresentationCommand buildCreateRepresentationFromDescription(RepresentationCreationDescription desc, DRepresentationElement element, String newRepresentationName) {
        return baseFactory.buildCreateRepresentationFromDescription(desc, element, newRepresentationName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDeleteDiagram(DDiagram vp) {
        return baseFactory.buildDeleteDiagram(vp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDeleteDiagramElement(DDiagramElement element) {
        return baseFactory.buildDeleteDiagramElement(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDeleteFromDiagramCommand(DDiagramElement element) {
        return baseFactory.buildDeleteFromDiagramCommand(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDirectEditLabelFromTool(DRepresentationElement repElement, DirectEditLabel directEditTool, String newValue) {
        return baseFactory.buildDirectEditLabelFromTool(repElement, directEditTool, newValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDoExecuteDetailsOperation(DSemanticDecorator target, RepresentationCreationDescription desc, String newRepresentationName) {
        return baseFactory.buildDoExecuteDetailsOperation(target, desc, newRepresentationName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDoubleClickOnElementCommandFromTool(DDiagramElement dDiagramElement, DoubleClickDescription tool) {
        return baseFactory.buildDoubleClickOnElementCommandFromTool(dDiagramElement, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDropInContainerCommandFromTool(DragAndDropTarget dContainer, DDiagramElement element, ContainerDropDescription tool) {
        return baseFactory.buildDropInContainerCommandFromTool(dContainer, element, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildDropInContainerCommandFromTool(DragAndDropTarget dContainer, EObject droppedElement, ContainerDropDescription tool) {
        return baseFactory.buildDropInContainerCommandFromTool(dContainer, droppedElement, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildGenericToolCommandFromTool(EObject containerView, ToolDescription toolDesc) {
        return baseFactory.buildGenericToolCommandFromTool(containerView, toolDesc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildHideCommand(Set<EObject> elementsToHide) {
        return baseFactory.buildHideCommand(elementsToHide);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Command buildHideLabelCommand(Set<EObject> elementsToHide) {
        return baseFactory.buildHideLabelCommand(elementsToHide);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Command buildHideLabelSelectionCommand(Set<EObject> elementsToHide, Map<EObject, List<Integer>> selectedLabelVisualIds) {
        return baseFactory.buildHideLabelSelectionCommand(elementsToHide, selectedLabelVisualIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildJavaActionFromTool(ExternalJavaAction tool, Collection<DSemanticDecorator> selectedViews, IExternalJavaAction javaAction) {
        return baseFactory.buildJavaActionFromTool(tool, selectedViews, javaAction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildLaunchRuleCommandFromTool(DSemanticDecorator rootObject, BehaviorTool tool, boolean executeFromRootContainer, boolean deepProcess) {
        return baseFactory.buildLaunchRuleCommandFromTool(rootObject, tool, executeFromRootContainer, deepProcess);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildOperationActionFromTool(OperationAction tool, Collection<DSemanticDecorator> selectedViews) {
        return baseFactory.buildOperationActionFromTool(tool, selectedViews);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildPaneBasedSelectionWizardCommandFromTool(PaneBasedSelectionWizardDescription tool, DSemanticDecorator dContainer,
            Collection<EObject> selectedElement) {
        return baseFactory.buildPaneBasedSelectionWizardCommandFromTool(tool, dContainer, selectedElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildQuickFixOperation(ValidationFix fix, EObject fixTarget, DDiagram diagram) {
        return baseFactory.buildQuickFixOperation(fix, fixTarget, diagram);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildReconnectEdgeCommandFromTool(ReconnectEdgeDescription tool, DEdge edge, EdgeTarget source, EdgeTarget target) {
        return baseFactory.buildReconnectEdgeCommandFromTool(tool, edge, source, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildRefreshCommand(DRefreshable refreshableElement) {
        return baseFactory.buildRefreshCommand(refreshableElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildRevealCommand(DDiagramElement diagramElement) {
        return baseFactory.buildRevealCommand(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildRevealElementsCommand(DDiagram diagram) {
        return baseFactory.buildRevealElementsCommand(diagram);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildRevealLabelCommand(DDiagramElement diagramElement) {
        return baseFactory.buildRevealLabelCommand(diagramElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildRevealLabelSelectionCommand(DDiagramElement diagramElement, Map<EObject, List<Integer>> selectedLabelVisualIds) {
        return baseFactory.buildRevealLabelSelectionCommand(diagramElement, selectedLabelVisualIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildRevealElementsCommand(Set<DDiagramElement> elementsToReveal) {
        return baseFactory.buildRevealElementsCommand(elementsToReveal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public org.eclipse.emf.common.command.Command buildSelectionWizardCommandFromTool(SelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement) {
        return baseFactory.buildSelectionWizardCommandFromTool(tool, dContainer, selectedElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserInterfaceCallBack(UICallBack newCB) {
        baseFactory.setUserInterfaceCallBack(newCB);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildPasteCommandFromTool(DSemanticDecorator dContainer, DSemanticDecorator element, PasteDescription tool) {
        return baseFactory.buildPasteCommandFromTool(dContainer, element, tool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildPasteCommandFromTool(DSemanticDecorator dContainer, EObject droppedElement, PasteDescription tool) {
        return baseFactory.buildPasteCommandFromTool(dContainer, droppedElement, tool);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.ICommandFactory#getUserInterfaceCallBack()
     */
    @Override
    public UICallBack getUserInterfaceCallBack() {
        return baseFactory.getUserInterfaceCallBack();
    }

    @Override
    public Command buildInsertOrRemoveVerticalBlankSpaceCommand(DDiagram diagram, int startY, int spaceToInsert) {
        return baseFactory.buildInsertOrRemoveVerticalBlankSpaceCommand(diagram, startY, spaceToInsert);
    }

    @Override
    public Command buildBringToFrontCommand(List<? extends View> elementsToBringToFront) {
        return baseFactory.buildBringToFrontCommand(elementsToBringToFront);
    }

    @Override
    public Command buildSendToBackCommand(List<? extends View> elementsToSendToBack) {
        return baseFactory.buildSendToBackCommand(elementsToSendToBack);
    }

    @Override
    public Command buildBringForwardCommand(List<? extends View> elementsToBringForward, int index) {
        return baseFactory.buildBringForwardCommand(elementsToBringForward, index);
    }

    @Override
    public Command buildSendBackwardCommand(List<? extends View> elementsToSendBackward, int index) {
        return baseFactory.buildSendBackwardCommand(elementsToSendBackward, index);
    }
}
