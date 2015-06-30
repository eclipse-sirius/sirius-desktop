/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Specific builder for paste tool operations.
 * 
 * @author mporhel
 */
public class PasteCommandBuilder extends AbstractDiagramCommandBuilder {

    private PasteDescription tool;

    private DSemanticDecorator pasteTarget;

    private DSemanticDecorator droppedDiagramElement;

    private EObject droppedElement;

    /**
     * .
     * 
     * @param tool
     *            the paste tool
     * @param container
     *            the diagram container
     * @param diagramElement
     *            the diagram element targeting the semantic element to paste
     */
    public PasteCommandBuilder(final PasteDescription tool, final DSemanticDecorator container, final DSemanticDecorator diagramElement) {
        this.tool = tool;
        this.pasteTarget = container;
        this.droppedDiagramElement = diagramElement;
        this.droppedElement = diagramElement.getTarget();
    }

    /**
     * .
     * 
     * @param tool
     *            the paste tool
     * @param container
     *            the diagram container
     * @param droppedElement
     *            the semantic element to paste
     */
    public PasteCommandBuilder(final PasteDescription tool, final DSemanticDecorator container, final EObject droppedElement) {
        this.tool = tool;
        this.pasteTarget = container;
        this.droppedElement = droppedElement;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    @Override
    public Command buildCommand() {
        if (permissionAuthority.canEditInstance(pasteTarget) && permissionAuthority.canEditInstance(droppedElement)) {
            /* Check the precondition of the tool */
            if (checkPastePrecondition()) {
                // Get the command which contains all the tasks describe
                // in the model operation of the VSM (viewpoint
                // specification model)
                final DCommand cmd = buildPasteCommandFromTool();

                // Launch a refresh to build the GMF elements according
                // to the DDiagram modifications
                final DDiagram diagram = getDDiagram().get();
                this.addRefreshTask(diagram, cmd, tool);
                cmd.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(pasteTarget.getTarget()), pasteTarget.getTarget(), diagram));
                return cmd;
            }

        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Check the precondition of the tool.
     * 
     * @return <code>true</code> if the precondition is OK, false otherwise.
     */
    private boolean checkPastePrecondition() {
        return checkPastePrecondition(tool, droppedElement, pasteTarget.getTarget(), droppedDiagramElement, pasteTarget);
    }

    /**
     * Check the precondition of the tool. Context for evaluation is the
     * semantic receiver.
     * 
     * @param description
     *            the tool.
     * @param semanticDroppedElement
     *            the semantic element to paste.
     * @param viewDroppedElement
     *            the view targeting the semantic element to paste, can be
     *            <code>null</code>.
     * @param semanticPasteTarget
     *            the semantic container.
     * @param pasteTargetView
     *            the view container.
     * 
     * @return <code>true</code> if the precondition is OK, false otherwise.
     */
    public static boolean checkPastePrecondition(final PasteDescription description, final EObject semanticDroppedElement, final EObject semanticPasteTarget, final EObject pasteTargetView,
            final EObject viewDroppedElement) {
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticPasteTarget);

        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, semanticPasteTarget);
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, pasteTargetView);
        interpreter.setVariable(IInterpreterSiriusVariables.COPIED_ELEMENT, semanticDroppedElement);
        interpreter.setVariable(IInterpreterSiriusVariables.COPIED_VIEW, viewDroppedElement);

        boolean check = true;
        final String precondition = description.getPrecondition();
        if (precondition != null && !StringUtil.isEmpty(precondition)) {
            check = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semanticPasteTarget, description, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
        }

        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.COPIED_ELEMENT);
        interpreter.unSetVariable(IInterpreterSiriusVariables.COPIED_VIEW);

        return check;
    }

    private DCommand buildPasteCommandFromTool() {
        final DCommand result = createEnclosingCommand();
        EObject semanticPasteTarget = pasteTarget.getTarget();
        if (permissionAuthority.canEditInstance(semanticPasteTarget) && permissionAuthority.canEditInstance(droppedElement)) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticPasteTarget);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            variables.put(tool.getContainer(), semanticPasteTarget);
            variables.put(tool.getContainerView(), pasteTarget);
            variables.put(tool.getCopiedElement(), droppedElement);
            variables.put(tool.getCopiedView(), droppedDiagramElement);

            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            Option<DRepresentation> representation = new EObjectQuery(pasteTarget).getRepresentation();
            if (tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                result.getTasks().add(taskHelper.buildTaskFromModelOperation(representation.get(), semanticPasteTarget, tool.getInitialOperation().getFirstModelOperations()));
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
        return tool != null ? new IdentifiedElementQuery(tool).getLabel() : "Paste";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<DDiagram> getDDiagram() {
        DDiagram diagram = null;
        if (pasteTarget instanceof DDiagram) {
            diagram = (DDiagram) pasteTarget;
        } else {
            diagram = ((DDiagramElement) pasteTarget).getParentDiagram();
        }
        return Options.newSome(diagram);
    }
}
