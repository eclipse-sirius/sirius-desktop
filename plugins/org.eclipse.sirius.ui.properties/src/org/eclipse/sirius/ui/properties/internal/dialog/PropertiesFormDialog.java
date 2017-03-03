/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.dialog;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EEFPage;
import org.eclipse.eef.ide.ui.api.EEFTab;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.properties.DialogButton;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;

/**
 * The form dialog parameterized by the Sirius Dialog description.
 * 
 * @author sbegaudeau
 */
public class PropertiesFormDialog extends FormDialog {

    /**
     * The model accessor.
     */
    private ModelAccessor modelAccessor;

    /**
     * The command context.
     */
    private CommandContext context;

    /**
     * The Sirius session.
     */
    private Session session;

    /**
     * The interpreter.
     */
    private IInterpreter interpreter;

    /**
     * The variable manager.
     */
    private IVariableManager variableManager;

    /**
     * The EEF Page.
     */
    private EEFPage eefPage;

    /**
     * The description of the dialog.
     */
    private DialogModelOperation dialogModelOperation;

    /**
     * The constructor.
     * 
     * @param shell
     *            The shell
     * @param modelAccessor
     *            The model accessor
     * @param context
     *            The command context
     * @param session
     *            The Sirius session
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param dialogModelOperation
     *            The description of the dialog
     * @param eefPage
     *            The EEF Page
     */
    @SuppressWarnings("checkstyle:parameternumber")
    public PropertiesFormDialog(Shell shell, ModelAccessor modelAccessor, CommandContext context, Session session, IInterpreter interpreter, IVariableManager variableManager,
            DialogModelOperation dialogModelOperation, EEFPage eefPage) {
        super(shell);
        this.modelAccessor = modelAccessor;
        this.context = context;
        this.session = session;
        this.interpreter = interpreter;
        this.variableManager = variableManager;
        this.dialogModelOperation = dialogModelOperation;
        this.eefPage = eefPage;
    }

    @Override
    protected void createFormContent(IManagedForm managedForm) {
        Optional.ofNullable(this.dialogModelOperation.getTitleExpression()).ifPresent(titleExpression -> {
            IEvaluationResult evaluationResult = this.interpreter.evaluateExpression(variableManager.getVariables(), titleExpression);
            if (Diagnostic.OK == evaluationResult.getDiagnostic().getSeverity() && evaluationResult.getValue() instanceof String) {
                String title = (String) evaluationResult.getValue();
                this.getShell().setText(title);
            }
        });

        Optional.ofNullable(this.eefPage.getDescription().getLabelExpression()).ifPresent(labelExpression -> {
            IEvaluationResult evaluationResult = this.interpreter.evaluateExpression(this.variableManager.getVariables(), labelExpression);
            if (Diagnostic.OK == evaluationResult.getDiagnostic().getSeverity() && evaluationResult.getValue() instanceof String) {
                String title = (String) evaluationResult.getValue();
                managedForm.getForm().setText(title);
            }
        });

        managedForm.getMessageManager().setDecorationPosition(SWT.TOP | SWT.LEFT);

        EEFTab eefTab = new EEFTab(this.eefPage);
        IEEFFormContainer container = new DialogFormContainer(this.getShell(), managedForm.getForm().getForm());
        Composite composite = managedForm.getForm().getForm().getBody();
        composite.setLayout(new FillLayout());
        int style = GridData.FILL_HORIZONTAL;
        GridData data = new GridData(style);
        composite.setLayoutData(data);

        eefTab.createControls(composite, container);
        eefTab.aboutToBeShown();
        eefTab.refresh();
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        List<DialogButton> buttons = this.dialogModelOperation.getButtons();
        if (buttons.size() == 0) {
            super.createButtonsForButtonBar(parent);
        } else {
            IntStream.range(0, buttons.size()).forEach(index -> this.createButton(parent, buttons.get(index), index));
        }
    }

    /**
     * Creates a button from the given dialog button description.
     * 
     * @param parent
     *            The parent composite
     * @param dialogButton
     *            The dialog button
     * @param index
     *            The index of the button
     */
    private void createButton(Composite parent, DialogButton dialogButton, int index) {
        IEvaluationResult evaluationResult = this.interpreter.evaluateExpression(this.variableManager.getVariables(), dialogButton.getLabelExpression());
        if (Diagnostic.OK == evaluationResult.getDiagnostic().getSeverity() && evaluationResult.getValue() instanceof String) {
            String label = (String) evaluationResult.getValue();
            this.createButton(parent, index, label, dialogButton.isDefault());
        }
    }

    @Override
    protected void buttonPressed(int buttonId) {
        if (this.dialogModelOperation.getButtons().isEmpty()) {
            super.buttonPressed(buttonId);
        }

        if (this.dialogModelOperation.getButtons().size() > buttonId) {
            DialogButton dialogButton = this.dialogModelOperation.getButtons().get(buttonId);

            Optional.ofNullable(dialogButton.getInitialOperation()).flatMap(initialOperation -> Optional.ofNullable(initialOperation.getFirstModelOperations())).ifPresent(modelOperation -> {
                ICommandTask task = new TaskHelper(this.modelAccessor, SiriusPlugin.getDefault().getUiCallback()).buildTaskFromModelOperation(this.context.getCurrentTarget(), modelOperation);
                SiriusCommand command = new SiriusCommand(this.session.getTransactionalEditingDomain(), "SiriusToolServices#executeOperation"); //$NON-NLS-1$
                command.getTasks().add(task);
                try {
                    if (command.canExecute()) {
                        command.execute();
                    }
                } finally {
                    command.dispose();
                }
            });

            // Make sure that the user interface loses the focus to trigger any
            // potential text widget focus lost listener
            Optional.ofNullable(this.getDialogArea()).ifPresent(Control::setFocus);
            if (dialogButton.isCloseDialogOnClick()) {
                if (dialogButton.isRollbackChangesOnClose()) {
                    this.setReturnCode(CANCEL);
                } else {
                    this.setReturnCode(OK);
                }

                this.close();
            }
        }
    }
}
