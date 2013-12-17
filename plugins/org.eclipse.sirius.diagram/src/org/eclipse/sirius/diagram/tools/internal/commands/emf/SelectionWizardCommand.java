/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.commands.emf;

import java.util.Collection;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.helper.SelectionDescriptionHelper;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.selection.EObjectSelectionWizard;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.swt.widgets.Shell;

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
        final Shell shell = new Shell();
        final EObjectSelectionWizard wizard = new EObjectSelectionWizard(this.tool.getWindowTitle(), this.tool.getMessage(), getImage(), input, SiriusDiagramEditorPlugin.getInstance()
                .getItemProvidersAdapterFactory());
        wizard.setMany(tool.isMultiple());
        final WizardDialog dlg = new WizardDialog(shell, wizard);
        final int result = dlg.open();
        if (result == Window.OK) {
            final Collection<EObject> selectedElements = wizard.getSelectedEObjects();
            final org.eclipse.emf.common.command.Command command = factory.buildSelectionWizardCommandFromTool(tool, containerView, selectedElements);
            command.execute();
            shell.dispose();
        } else {
            if (containerView instanceof AbstractDNode) {
                SiriusLayoutDataManager.INSTANCE.getData((AbstractDNode) containerView);
            }
            shell.dispose();
            throw new OperationCanceledException("User cancel operation");
        }
    }

    private void computeInput() {
        EObject container;
        container = containerView.getTarget();
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);

        if (AbstractSelectionWizardCommand.checkPrecondition(tool, containerView, container)) {

            // variables
            interpreter.setVariable(tool.getContainerView().getName(), containerView);
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, SiriusUtil.findDiagram(containerView));
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
            return SiriusDiagramEditorPlugin.findImageDescriptor(tool.getWindowImagePath());
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
