/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
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
package org.eclipse.sirius.ui.properties.internal.dialog;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EEFPage;
import org.eclipse.eef.core.api.EditingContextAdapter;
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
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.ui.properties.internal.EditingContextAdapterWrapper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
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
     * The EEF Tab.
     */
    private EEFTab eefTab;

    /**
     * The consumer used to refresh the dialog when a change is performed.
     */
    private Consumer<IStatus> consumer;

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

        this.eefTab = new EEFTab(this.eefPage);
        IEEFFormContainer container = new DialogFormContainer(this.getShell(), managedForm.getForm().getForm());
        Composite composite = managedForm.getForm().getForm().getBody();
        composite.setLayout(new FillLayout());
        int style = GridData.FILL_HORIZONTAL;
        GridData data = new GridData(style);
        composite.setLayoutData(data);

        eefTab.createControls(composite, container);
        eefTab.aboutToBeShown();
        eefTab.refresh();
        managedForm.getForm().reflow(true);

        this.configureModelChangeRefresh();
    }

    /**
     * Configures a consumer executed when {@link EditingContextAdapter#performModelChange(Runnable)} is called in order
     * to refresh the dialog.
     */
    private void configureModelChangeRefresh() {
        EditingContextAdapter editingContextAdapter = this.eefPage.getView().getContextAdapter();
        if (editingContextAdapter instanceof EditingContextAdapterWrapper) {
            EditingContextAdapterWrapper wrapper = (EditingContextAdapterWrapper) editingContextAdapter;
            this.consumer = (status) -> this.refreshDialog();

            wrapper.addPerformedModelChangeConsumer(this.consumer);
        }
    }

    private void refreshDialog() {
        this.eefTab.refresh();

        List<DialogButton> buttons = this.dialogModelOperation.getButtons();
        IntStream.range(0, buttons.size()).forEach(index -> {
            this.refreshButton(this.getButton(index), buttons.get(index));
        });
    }

    /**
     * Refreshes the given button using its description.
     * 
     * @param button
     *            The button to refresh
     * @param dialogButton
     *            The description of the button
     */
    private void refreshButton(Button button, DialogButton dialogButton) {
        String isEnabledExpression = Optional.ofNullable(dialogButton.getIsEnabledExpression()).orElse(""); //$NON-NLS-1$
        if (!isEnabledExpression.isEmpty()) {
            IEvaluationResult evaluationResult = this.interpreter.evaluateExpression(this.variableManager.getVariables(), isEnabledExpression);
            if (Diagnostic.OK == evaluationResult.getDiagnostic().getSeverity() && evaluationResult.getValue() instanceof Boolean) {
                button.setEnabled(((Boolean) evaluationResult.getValue()).booleanValue());
            }
        }
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        List<DialogButton> buttons = this.dialogModelOperation.getButtons();
        if (buttons.size() == 0) {
            super.createButtonsForButtonBar(parent);
        } else {
            IntStream.range(0, buttons.size()).forEach(index -> this.createButton(parent, buttons.get(index), index));

            // We have finished the creation of the content, we can set the initial state of the buttons
            this.refreshDialog();
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

    @Override
    public boolean close() {
        boolean result = super.close();

        EditingContextAdapter editingContextAdapter = this.eefPage.getView().getContextAdapter();
        if (editingContextAdapter instanceof EditingContextAdapterWrapper) {
            EditingContextAdapterWrapper wrapper = (EditingContextAdapterWrapper) editingContextAdapter;
            wrapper.removePerformedModelChangeConsumer(this.consumer);
        }

        this.eefTab.aboutToBeHidden();
        this.eefTab.dispose();

        return result;
    }
}
