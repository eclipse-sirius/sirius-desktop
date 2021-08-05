/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.commands.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * .
 * 
 * @author mchauvin
 */
public abstract class AbstractSelectionWizardCommand extends RecordingCommand {

    /**
     * Construct a new instance
     * 
     * @param domain
     *            the editing domain
     */
    AbstractSelectionWizardCommand(final TransactionalEditingDomain domain) {
        super(domain);
    }

    /**
     * Check if a new {@link PaneBasedSelectionWizardCommand} can be created.
     * 
     * @param tool
     *            the selection wizard description tool.
     * @param containerView
     *            the view of the container
     * @param input
     *            the candidates objects to select in the wizard.
     * @return <code>True</code> if the command can be created, <code>False</code> otherwise.
     */
    public static boolean canCreateCommand(final AbstractToolDescription tool, final EObject containerView, final TreeItemWrapper input) {
        EObject container;
        if (containerView instanceof DSemanticDecorator) {
            container = ((DSemanticDecorator) containerView).getTarget();
            return AbstractSelectionWizardCommand.checkPrecondition(tool, containerView, container);
        }
        return false;
    }

    /**
     * .
     * 
     * @param description
     *            .
     * @param containerView
     *            .
     * @param container
     *            .
     * @return .
     */
    protected static boolean checkPrecondition(final AbstractToolDescription description, final EObject containerView, final EObject container) {
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);

        boolean preconditionResult = false;

        if (description.getPrecondition() == null || StringUtil.isEmpty(description.getPrecondition())) {
            preconditionResult = true;
        } else {
            try {
                preconditionResult = false;
                Option<DDiagram> diagram = new EObjectQuery(containerView).getParentDiagram();
                if (diagram.some()) {
                    interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());
                } else {
                    interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, null);
                }
                interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
                interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
                preconditionResult = interpreter.evaluateBoolean(container, description.getPrecondition());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(description, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
                interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
                interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            }
        }
        return preconditionResult;
    }

}
