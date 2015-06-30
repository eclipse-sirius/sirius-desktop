/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
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
import org.eclipse.sirius.diagram.tools.api.command.view.CreateDiagramWithInitialOperation;
import org.eclipse.sirius.diagram.tools.api.command.view.HideDDiagramElement;
import org.eclipse.sirius.diagram.tools.api.command.view.HideDDiagramElementLabel;
import org.eclipse.sirius.diagram.tools.api.command.view.RefreshSiriusElement;
import org.eclipse.sirius.diagram.tools.api.command.view.RevealAllElementsCommand;
import org.eclipse.sirius.diagram.tools.api.command.view.RevealDDiagramElements;
import org.eclipse.sirius.diagram.tools.api.command.view.RevealDDiagramElementsLabel;
import org.eclipse.sirius.diagram.tools.internal.command.builders.ContainerCreationCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.DeletionCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.DirectEditCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.DoubleClickCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.DragAndDropCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.EdgeCreationCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.GenericToolCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.NodeCreationCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.PaneBasedSelectionWizardCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.PasteCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.ReconnectionCommandBuilder;
import org.eclipse.sirius.diagram.tools.internal.command.builders.SelectionWizardCommandBuilder;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.AbstractCommandFactory;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.command.view.JavaActionFromToolCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.tools.internal.command.builders.CommandBuilder;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;

/**
 * A command factory that creates commands that can be undone.
 * 
 * @author mchauvin
 */
public class UndoRedoCapableEMFCommandFactory extends AbstractCommandFactory implements IDiagramCommandFactory {

    private TaskHelper commandTaskHelper;

    /**
     * Create a new Factory. the autoRefresh is by default deactivated
     * 
     * @param domain
     *            current editing domain.
     */
    public UndoRedoCapableEMFCommandFactory(final TransactionalEditingDomain domain) {
        super(domain);
        commandTaskHelper = new TaskHelper(modelAccessor, uiCallBack);
    }

    private IPermissionAuthority getPermissionAuthority() {
        return modelAccessor.getPermissionAuthority();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildLaunchRuleCommandFromTool(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.sirius.viewpoint.description.tool.BehaviorTool, boolean,
     *      boolean)
     */
    @Override
    public Command buildLaunchRuleCommandFromTool(final DSemanticDecorator rootObject, final BehaviorTool tool, final boolean executeFromRootContainer, final boolean deepProcess) {

        EObject root = rootObject.getTarget();

        if (root != null) {
            if (executeFromRootContainer) {
                // Let's launch the operation for the entire model.
                root = EcoreUtil.getRootContainer(root);
            }
            final Option<DRepresentation> representation = new EObjectQuery(rootObject).getRepresentation();
            final DCommand result = new SiriusCommand(domain, tool.getName());
            //
            // Current selection.
            if (representation.some() && tool.getDomainClass() == null || StringUtil.isEmpty(tool.getDomainClass().trim()) || this.modelAccessor.eInstanceOf(root, tool.getDomainClass())) {
                if (this.commandTaskHelper.checkPrecondition(root, tool) && tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                    //
                    // We append a new task.
                    result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(representation.get(), root, tool.getInitialOperation().getFirstModelOperations()));
                }
            }
            if (representation.some() && deepProcess) {
                final Iterator<EObject> iterContents = root.eAllContents();
                while (iterContents.hasNext()) {
                    final EObject current = iterContents.next();
                    if (tool.getDomainClass() == null || StringUtil.isEmpty(tool.getDomainClass().trim()) || this.modelAccessor.eInstanceOf(current, tool.getDomainClass())) {
                        if (this.commandTaskHelper.checkPrecondition(current, tool)) {
                            //
                            // We append a new task.
                            result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(representation.get(), current, tool.getInitialOperation().getFirstModelOperations()));
                        }
                    }
                }
            }
            addRefreshTask(rootObject, result, tool);
            result.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(rootObject), rootObject.getTarget(), representation.get()));
            return result;
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildCreateNodeCommandFromTool(DDiagramElementContainer,
     *      NodeCreationDescription)
     */
    @Override
    public Command buildCreateNodeCommandFromTool(final DDiagramElementContainer container, final NodeCreationDescription tool) {
        final CommandBuilder builder = new NodeCreationCommandBuilder(tool, container);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildCreateNodeCommandFromTool(DNode,
     *      NodeCreationDescription)
     */
    @Override
    public Command buildCreateNodeCommandFromTool(final DNode node, final NodeCreationDescription tool) {
        final CommandBuilder builder = new NodeCreationCommandBuilder(tool, node);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildCreateNodeCommandFromTool(DDiagram,
     *      NodeCreationDescription)
     */
    @Override
    public Command buildCreateNodeCommandFromTool(final DDiagram diagram, final NodeCreationDescription tool) {
        final CommandBuilder builder = new NodeCreationCommandBuilder(tool, diagram);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildCreateContainerCommandFromTool(DDiagram,
     *      ContainerCreationDescription)
     */
    @Override
    public Command buildCreateContainerCommandFromTool(final DDiagram diagram, final ContainerCreationDescription tool) {
        final CommandBuilder builder = new ContainerCreationCommandBuilder(tool, diagram);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildCreateContainerCommandFromTool(DDiagramElementContainer,
     *      ContainerCreationDescription)
     */
    @Override
    public Command buildCreateContainerCommandFromTool(final DDiagramElementContainer nodeContainer, final ContainerCreationDescription tool) {
        final CommandBuilder builder = new ContainerCreationCommandBuilder(tool, nodeContainer);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildSelectionWizardCommandFromTool(SelectionWizardDescription,
     *      DSemanticDecorator, Collection)
     */
    @Override
    public Command buildSelectionWizardCommandFromTool(final SelectionWizardDescription tool, final DSemanticDecorator containerView, final Collection<EObject> selectedElement) {
        final CommandBuilder builder = new SelectionWizardCommandBuilder(tool, containerView, selectedElement);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildPaneBasedSelectionWizardCommandFromTool(PaneBasedSelectionWizardDescription,
     *      DSemanticDecorator, Collection)
     */
    @Override
    public Command buildPaneBasedSelectionWizardCommandFromTool(final PaneBasedSelectionWizardDescription tool, final DSemanticDecorator containerView, final Collection<EObject> selectedElement) {
        final CommandBuilder builder = new PaneBasedSelectionWizardCommandBuilder(tool, containerView, selectedElement);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildJavaActionFromTool(ExternalJavaAction,
     *      Collection, IExternalJavaAction)
     */
    @Override
    public Command buildJavaActionFromTool(final ExternalJavaAction tool, final Collection<DSemanticDecorator> containerViews, final IExternalJavaAction javaAction) {
        final EObject anySemantic = containerViews.iterator().next().getTarget();
        final CompoundCommand compoundCommand = new CompoundCommand();
        final DCommand dCommand = new SiriusCommand(this.domain, tool.getName());

        final Command command = buildJavaActionFromTool(tool, anySemantic, containerViews, javaAction);
        compoundCommand.append(command);
        compoundCommand.append(dCommand);

        for (final DSemanticDecorator containerView : containerViews) {
            addRefreshTask(containerView, dCommand, tool);
            Option<DDiagram> parentDiagram = new EObjectQuery(containerView).getParentDiagram();
            dCommand.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(containerView), anySemantic, parentDiagram.get()));
        }
        return compoundCommand;
    }

    private Command buildJavaActionFromTool(final ExternalJavaAction tool, final EObject container, final Collection<DSemanticDecorator> containerViews, final IExternalJavaAction javaAction) {
        return new JavaActionFromToolCommand(this.domain, javaAction, tool, containerViews);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildOperationActionFromTool(OperationAction,
     *      Collection)
     */
    @Override
    public Command buildOperationActionFromTool(final OperationAction tool, final Collection<DSemanticDecorator> containerViews) {
        final EObject anySemantic = containerViews.iterator().next().getTarget();
        final DCommand command = buildOperationActionFromTool(tool, anySemantic, containerViews);
        for (final DSemanticDecorator containerView : containerViews) {
            addRefreshTask(containerView, command, tool);
            Option<DDiagram> parentDiagram = new EObjectQuery(containerView).getParentDiagram();
            command.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(containerView), anySemantic, parentDiagram.get()));
        }
        return command;
    }

    private DCommand buildOperationActionFromTool(final OperationAction tool, final EObject container, final Collection<DSemanticDecorator> containerViews) {
        final DCommand result = new SiriusCommand(domain, tool.getName());
        final IInterpreter interpreter = InterpreterUtil.getInterpreter(container);
        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        variables.put(tool.getView(), containerViews);
        result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallBack));

        DSemanticDecorator firstContainerView = null;
        if (containerViews != null && containerViews.size() > 0) {
            firstContainerView = containerViews.iterator().next();
            addDiagramVariable(result, firstContainerView, interpreter);
        }

        Option<DRepresentation> representation = new EObjectQuery(firstContainerView).getRepresentation();
        if (representation.some() && tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
            result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(representation.get(), container, tool.getInitialOperation().getFirstModelOperations()));
        }
        return result;
    }

    private void addDiagramVariable(final DCommand command, final EObject containerView, final IInterpreter interpreter) {
        final Option<DDiagram> diag = new EObjectQuery(containerView).getParentDiagram();
        if (diag.some()) {
            command.getTasks().add(new AbstractCommandTask() {
                @Override
                public String getLabel() {
                    return "Add diagram variable";
                }

                @Override
                public void execute() {
                    interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diag.get());
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildCreateEdgeCommandFromTool(org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.viewpoint.description.tool.EdgeCreationDescription)
     */
    @Override
    public Command buildCreateEdgeCommandFromTool(final EdgeTarget source, final EdgeTarget target, final EdgeCreationDescription tool) {
        final CommandBuilder builder = new EdgeCreationCommandBuilder(tool, source, target);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildReconnectEdgeCommandFromTool(org.eclipse.sirius.viewpoint.description.tool.ReconnectEdgeDescription,
     *      org.eclipse.sirius.diagram.DEdge,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget)
     */
    @Override
    public Command buildReconnectEdgeCommandFromTool(final ReconnectEdgeDescription tool, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        final CommandBuilder builder = new ReconnectionCommandBuilder(tool, edge, source, target);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDropInContainerCommandFromTool(DragAndDropTarget,
     *      DDiagramElement, ContainerDropDescription)
     */
    @Override
    public Command buildDropInContainerCommandFromTool(final DragAndDropTarget container, final DDiagramElement element, final ContainerDropDescription tool) {
        final CommandBuilder builder = new DragAndDropCommandBuilder(tool, container, element);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDropInContainerCommandFromTool(DragAndDropTarget,
     *      EObject, ContainerDropDescription)
     */
    @Override
    public Command buildDropInContainerCommandFromTool(final DragAndDropTarget container, final EObject droppedElement, final ContainerDropDescription tool) {
        final CommandBuilder builder = new DragAndDropCommandBuilder(tool, container, droppedElement);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDoubleClickOnElementCommandFromTool(DDiagramElement,
     *      DoubleClickOnElementDescription)
     */
    @Override
    public Command buildDoubleClickOnElementCommandFromTool(DDiagramElement dDiagramElement, DoubleClickDescription tool) {
        final CommandBuilder builder = new DoubleClickCommandBuilder(tool, dDiagramElement);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDoExecuteDetailsOperation(org.eclipse.sirius.viewpoint.DSemanticDecorator,
     *      org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription,
     *      java.lang.String)
     */
    @Override
    public Command buildDoExecuteDetailsOperation(final DSemanticDecorator target, final RepresentationCreationDescription desc, final String newRepresentationName) {
        final DCommand cmd = new SiriusCommand(domain, "Create new representation");
        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        variables.put(desc.getContainerViewVariable(), target);
        final Map<AbstractVariable, String> stringVariables = new HashMap<AbstractVariable, String>();
        stringVariables.put(desc.getRepresentationNameVariable(), newRepresentationName);
        final ICommandTask initInterpreterVariables = new InitInterpreterVariablesTask(variables, stringVariables, InterpreterUtil.getInterpreter(target), uiCallBack);
        cmd.getTasks().add(initInterpreterVariables);

        Option<DRepresentation> representation = new EObjectQuery(target).getRepresentation();
        if (representation.some() && desc.getInitialOperation() != null && desc.getInitialOperation().getFirstModelOperations() != null) {
            cmd.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(representation.get(), target.getTarget(), desc.getInitialOperation().getFirstModelOperations()));
        }
        return cmd;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDeleteDiagram(DDiagram)
     */
    @Override
    public Command buildDeleteDiagram(final DDiagram dDiagram) {
        final CommandBuilder builder = new DeletionCommandBuilder(dDiagram);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDeleteFromDiagramCommand(DDiagramElement)
     */
    @Override
    public Command buildDeleteFromDiagramCommand(final DDiagramElement element) {
        final CommandBuilder builder = new DeletionCommandBuilder(element, true);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDeleteDiagramElement(DDiagramElement)
     */
    @Override
    public Command buildDeleteDiagramElement(final DDiagramElement element) {
        final CommandBuilder builder = new DeletionCommandBuilder(element, false);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildDirectEditLabelFromTool(DRepresentationElement,
     *      DirectEditLabel, String)
     */
    @Override
    public Command buildDirectEditLabelFromTool(final DRepresentationElement repElement, final DirectEditLabel directEditTool, final String newValue) {
        final CommandBuilder builder = new DirectEditCommandBuilder(repElement, directEditTool, newValue);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildRefreshCommand(org.eclipse.sirius.viewpoint.DRefreshable)
     */
    @Override
    public Command buildRefreshCommand(final DRefreshable vpElem) {
        if (getPermissionAuthority().canEditInstance(vpElem)) {
            return new RefreshSiriusElement(domain, vpElem);
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildHideCommand(java.util.Set)
     */
    @Override
    public Command buildHideCommand(final Set<EObject> elementsToHide) {
        final Set<EObject> filteredSet = new HashSet<EObject>();
        final Iterator<EObject> it = elementsToHide.iterator();
        while (it.hasNext()) {
            final EObject eObj = it.next();
            if (getPermissionAuthority().canEditInstance(eObj)) {
                filteredSet.add(eObj);
            }
        }
        if (filteredSet.size() > 0) {
            return new HideDDiagramElement(domain, filteredSet);
        }

        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildHideLabelCommand(java.util.Set)
     */
    @Override
    public Command buildHideLabelCommand(Set<EObject> elementsToHide) {
        final Set<EObject> filteredSet = new HashSet<EObject>();
        final Iterator<EObject> it = elementsToHide.iterator();
        while (it.hasNext()) {
            final EObject eObj = it.next();
            if (getPermissionAuthority().canEditInstance(eObj)) {
                filteredSet.add(eObj);
            }
        }
        boolean canHideLabel = true;
        for (Object selectedElement : filteredSet) {
            if (selectedElement instanceof DDiagramElement) {
                canHideLabel = canHideLabel & new DDiagramElementQuery((DDiagramElement) selectedElement).canHideLabel();
            }
        }
        if (canHideLabel) {
            return new HideDDiagramElementLabel(domain, filteredSet);
        }

        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildRevealCommand(DDiagramElement)
     */
    @Override
    public Command buildRevealCommand(final DDiagramElement vpe) {
        if (getPermissionAuthority().canEditInstance(vpe)) {
            return new RevealDDiagramElements(domain, Collections.singleton(vpe));
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory#buildRevealLabelCommand(DDiagramElement)
     */
    @Override
    public Command buildRevealLabelCommand(DDiagramElement diagramElement) {
        if (getPermissionAuthority().canEditInstance(diagramElement)) {
            return new RevealDDiagramElementsLabel(domain, Collections.singleton(diagramElement));
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildRevealElementsCommand(final DDiagram viewpoint) {
        if (getPermissionAuthority().canEditInstance(viewpoint)) {
            return new RevealAllElementsCommand(domain, viewpoint);
        }

        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildRevealElementsCommand(final Set<DDiagramElement> elementsToReveal) {
        final Set<DDiagramElement> filteredSet = new HashSet<DDiagramElement>();
        if (elementsToReveal != null) {
            for (final DDiagramElement diagramElement : elementsToReveal) {
                if (getPermissionAuthority().canEditInstance(diagramElement)) {
                    filteredSet.add(diagramElement);
                }
            }
            if (filteredSet.size() > 0) {
                return new RevealDDiagramElements(domain, filteredSet);
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DCommand buildCreateDiagramFromDescription(final DiagramDescription description, final EObject semanticElement, IProgressMonitor monitor) {
        final DCommand command = new SiriusCommand(domain, "Create new diagram") {
            /**
             * creation of a diagram must not be undoable ! {@inheritDoc}
             */
            @Override
            public boolean canUndo() {
                return false;
            }
        };

        command.getTasks().add(new CreateDiagramWithInitialOperation(description, semanticElement, uiCallBack, monitor));

        return command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserInterfaceCallBack(final UICallBack newCB) {
        this.uiCallBack = newCB;
        commandTaskHelper = new TaskHelper(modelAccessor, uiCallBack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildQuickFixOperation(final ValidationFix fix, final EObject fixTarget, final DDiagram diagram) {
        if (fix.getInitialOperation() != null && fix.getInitialOperation().getFirstModelOperations() != null) {
            final DCommand result = new SiriusCommand(domain, "Quick fix");
            result.getTasks().add(commandTaskHelper.buildTaskFromModelOperation(diagram, fixTarget, fix.getInitialOperation().getFirstModelOperations()));
            return result;
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildGenericToolCommandFromTool(final EObject containerView, final ToolDescription tool) {
        final CommandBuilder builder = new GenericToolCommandBuilder(tool, containerView);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildPasteCommandFromTool(DSemanticDecorator dContainer, DSemanticDecorator element, PasteDescription tool) {
        final CommandBuilder builder = new PasteCommandBuilder(tool, dContainer, element);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command buildPasteCommandFromTool(DSemanticDecorator dContainer, EObject droppedElement, PasteDescription tool) {
        final CommandBuilder builder = new PasteCommandBuilder(tool, dContainer, droppedElement);
        builder.init(modelAccessor, domain, uiCallBack);
        return builder.buildCommand();
    }
}
