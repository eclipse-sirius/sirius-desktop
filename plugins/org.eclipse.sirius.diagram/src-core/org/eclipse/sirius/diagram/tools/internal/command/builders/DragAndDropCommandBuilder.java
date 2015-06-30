/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.resource.WorkspaceDragAndDropSupport;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.helper.task.DnDTasksOperations;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.api.ui.resource.ISiriusMessages;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;

/**
 * .
 * 
 * @author mchauvin
 */
public class DragAndDropCommandBuilder extends AbstractDiagramCommandBuilder {

    private ContainerDropDescription tool;

    private DragAndDropTarget container;

    private DDiagramElement diagramElement;

    private EObject droppedElement;

    private boolean dragSemantic;

    /**
     * .
     * 
     * @param tool
     *            the dnd tool
     * @param container
     *            the diagram container
     * @param diagramElement
     *            the diagram element to drop
     */
    public DragAndDropCommandBuilder(final ContainerDropDescription tool, final DragAndDropTarget container, final DDiagramElement diagramElement) {
        this.tool = tool;
        this.container = container;
        this.diagramElement = diagramElement;
        this.dragSemantic = false;
    }

    /**
     * .
     * 
     * @param tool
     *            the dnd tool
     * @param container
     *            the diagram container
     * @param droppedElement
     *            the semantic element to drop
     */
    public DragAndDropCommandBuilder(final ContainerDropDescription tool, final DragAndDropTarget container, final EObject droppedElement) {
        this.tool = tool;
        this.container = container;
        this.droppedElement = droppedElement;
        this.dragSemantic = true;
    }

    private DSemanticDecorator getOldContainer() {
        EObject semanticOldContainer = null;
        DSemanticDecorator oldContainer = null;

        EObject currentOldContainer = diagramElement.eContainer();
        while (currentOldContainer != null && semanticOldContainer == null) {
            if (currentOldContainer instanceof DSemanticDecorator) {
                oldContainer = (DSemanticDecorator) currentOldContainer;
                semanticOldContainer = oldContainer.getTarget();
            }
            currentOldContainer = currentOldContainer.eContainer();
        }
        return oldContainer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    @Override
    public Command buildCommand() {
        if (permissionAuthority.canEditInstance(container) && permissionAuthority.canEditInstance(dragSemantic ? droppedElement : diagramElement)
        // Layouting mode on diagrams
        // if the ddiagram is in LayoutingMode, we do not allow DnD
                && !isInLayoutingModeDiagram(diagramElement)) {
            if (container instanceof DSemanticDecorator) {
                final EObject semanticContainer = ((DSemanticDecorator) container).getTarget();

                if (!dragSemantic) {
                    droppedElement = diagramElement.getTarget();
                }

                final DSemanticDecorator oldContainer = dragSemantic ? null : getOldContainer();
                final EObject semanticOldContainer = dragSemantic ? null : oldContainer.getTarget();

                /* Check the precondition of the tool */
                if (checkDragAndDropPrecondition(semanticOldContainer, semanticContainer)) {
                    final DiagramElementMapping mapping = tool.getBestMapping(container, droppedElement);
                    if (mapping != null || dragSemantic) {
                        // Get the command which contains all the tasks describe
                        // in the model operation of the VSM (viewpoint
                        // specification model)
                        final DCommand cmd = buildDropInContainerCommandFromTool(semanticContainer, droppedElement, container, semanticOldContainer);
                        // Add new DNode in the new container
                        fillDropinCommand(cmd, mapping, semanticContainer);
                        // Delete the old DNode from the previous container
                        cleanAfterDropinCommand(cmd, oldContainer);
                        // Launch a refresh to build the GMF elements according
                        // to the DDiagram modifications
                        final DDiagram diagram = getDDiagram().get();
                        this.addRefreshTask(diagram, cmd, tool);
                        cmd.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(semanticContainer), semanticContainer, diagram));
                        return cmd;
                    }
                }
            } else {
                SiriusPlugin.getDefault().error(ISiriusMessages.IS_NOT_A_DECORATE_SEMANTIC_ELEMENT, new RuntimeException());
            }
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Check the precondition of the tool.
     * 
     * @param oldContainer
     *            the old container, may be <code>null</code>.
     * @param newContainer
     *            the new container..
     * @return <code>true</code> if the precondition is OK, false otherwise.
     */
    private boolean checkDragAndDropPrecondition(final EObject oldContainer, final EObject newContainer) {
        return DnDTasksOperations.checkDragAndDropPrecondition(tool, droppedElement, oldContainer, newContainer, container);
    }

    private void fillDropinCommand(final DCommand cmd, final DiagramElementMapping mapping, final EObject semanticContainer) {

        ICommandTask createElement = null;
        final boolean moveEdges = tool.isMoveEdges();

        if (mapping instanceof NodeMapping) {
            createElement = DnDTasksOperations.createDropinForNodeTask(container, (NodeMapping) mapping, diagramElement, droppedElement, semanticContainer, moveEdges);
        } else if (mapping instanceof EdgeMapping) {
            /* we do not handle edge mapping yet */
        } else if (mapping instanceof ContainerMapping) {
            createElement = DnDTasksOperations.createDropinForContainerTask(container, (ContainerMapping) mapping, diagramElement, droppedElement, semanticContainer, tool);
        }

        if (createElement != null) {
            cmd.getTasks().add(createElement);
        }
    }

    private void cleanAfterDropinCommand(final DCommand cmd, final DSemanticDecorator oldContainer) {
        if (!dragSemantic) {
            cmd.getTasks().add(DnDTasksOperations.createRemoveAfterDropInTask(oldContainer, diagramElement));
        }
    }

    private DCommand buildDropInContainerCommandFromTool(final EObject newContainer, final EObject element, final EObject dContainer, final EObject oldContainer) {

        final DCommand result = createEnclosingCommand();
        if (permissionAuthority.canEditInstance(newContainer) && permissionAuthority.canEditInstance(element)) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(newContainer);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            variables.put(tool.getElement(), element);
            variables.put(tool.getNewContainer(), newContainer);
            variables.put(tool.getNewViewContainer(), dContainer);
            if (oldContainer != null) {
                variables.put(tool.getOldContainer(), oldContainer);
            }
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback) {

                @Override
                public void execute() {
                    if (element instanceof DModel) {
                        setElementVariableWithModelContent((DModel) element);
                    }
                    super.execute();
                }

                private void setElementVariableWithModelContent(final DModel model) {
                    final WorkspaceDragAndDropSupport support = new WorkspaceDragAndDropSupport();
                    final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(newContainer);
                    final IFile file = support.getWorkspaceFile(model);
                    final Resource loadedResource = uiCallback.loadResource(domain, file);
                    if (loadedResource != null) {
                        final Session session = SessionManager.INSTANCE.getSession(model);
                        support.addSemanticResourceToSession(loadedResource, session);
                        if (!loadedResource.getContents().isEmpty()) {
                            final EObject newElement = loadedResource.getContents().get(0);
                            if (newElement != null) {
                                variables.put(tool.getElement(), newElement);
                            }
                        }
                    }
                }

            });
            Option<DRepresentation> representation = new EObjectQuery(diagramElement).getRepresentation();
            if (tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                result.getTasks().add(taskHelper.buildTaskFromModelOperation(representation.get(), newContainer, tool.getInitialOperation().getFirstModelOperations()));
            }
        } else {
            result.getTasks().add(UnexecutableTask.INSTANCE);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEnclosingCommandLabel() {
        return new IdentifiedElementQuery(tool).getLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<DDiagram> getDDiagram() {
        DDiagram diag = null;
        if (container instanceof DDiagram) {
            diag = (DDiagram) container;
        } else {
            diag = ((DDiagramElement) container).getParentDiagram();
        }
        return Options.newSome(diag);
    }
}
