/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
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

import java.util.Collection;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.helper.SelectionDescriptionHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.common.ui.tools.api.selection.WizardDialogClosableByWizard;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A command to display a selection wizard.
 *
 * @author mchauvin
 */
public class SelectionWizardCommand extends AbstractSelectionWizardCommand {

    private final IDiagramCommandFactory factory;

    private final SelectionWizardDescription tool;

    private final TreeItemWrapper input;

    private final DSemanticDecorator containerView;

    /**
     * Default constructor.
     *
     * @param factory
     *            the command factory.
     * @param tool
     *            the wizard description tool reference.
     * @param input
     *            the candidates objects to select in the wizard.
     * @param containerView
     *            the view of the container.
     */
    public SelectionWizardCommand(final IDiagramCommandFactory factory, final SelectionWizardDescription tool, final TreeItemWrapper input, final DSemanticDecorator containerView) {
        super(TransactionUtil.getEditingDomain(containerView));
        this.factory = factory;
        this.tool = tool;
        this.input = input;
        this.containerView = containerView;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    public void doExecute() {
        computeInput();
        Shell shell = null;
        boolean createdShell = false;
        if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        }
        if (shell == null) {
            shell = new Shell();
            createdShell = true;
        }
        String windowTitleLocalized = MessageTranslator.INSTANCE.getMessage(this.tool, this.tool.getWindowTitle());
        String messageLocalized = MessageTranslator.INSTANCE.getMessage(this.tool, this.tool.getMessage());
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(windowTitleLocalized, messageLocalized, getImage(), input, DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
        wizard.setMany(tool.isMultiple());
        final WizardDialogClosableByWizard dlg = new WizardDialogClosableByWizard(shell, wizard);
        wizard.setDialog(dlg);
        final int result = dlg.open();
        if (result == Window.OK) {
            final Collection<EObject> selectedElements = wizard.getSelectedEObjects();
            final org.eclipse.emf.common.command.Command command = factory.buildSelectionWizardCommandFromTool(tool, containerView, selectedElements);
            command.execute();
            if (createdShell) {
                shell.dispose();
            }
        } else {
            if (containerView instanceof AbstractDNode) {
                SiriusLayoutDataManager.INSTANCE.getData((AbstractDNode) containerView);
            }
            if (createdShell) {
                shell.dispose();
            }
            throw new OperationCanceledException(Messages.SelectionWizardCommand_cancelExceptionMsg);
        }
    }

    private void computeInput() {
        EObject container;
        container = containerView.getTarget();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);

        if (AbstractSelectionWizardCommand.checkPrecondition(tool, containerView, container)) {

            // variables
            interpreter.setVariable(tool.getContainerView().getName(), containerView);
            Option<DDiagram> diagram = new EObjectQuery(containerView).getParentDiagram();
            if (diagram.some()) {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());
            } else {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, null);
            }
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);

            SelectionDescriptionHelper.computeInput(tool, container, interpreter, input);

            interpreter.unSetVariable(tool.getContainerView().getName());
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        }
    }

    private ImageDescriptor getImage() {
        if (StringUtil.isEmpty(tool.getWindowImagePath())) {
            return null;
        } else {
            return DiagramUIPlugin.Implementation.findImageDescriptor(tool.getWindowImagePath());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.common.command.AbstractCommand#getLabel()
     */
    @Override
    public String getLabel() {
        return this.tool.getName();
    }
}
