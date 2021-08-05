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
package org.eclipse.sirius.ui.properties.internal.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.eef.core.api.EEFPage;
import org.eclipse.eef.core.api.EEFView;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.ui.properties.internal.EditingContextAdapterWrapper;
import org.eclipse.sirius.ui.properties.internal.SiriusUIPropertiesPlugin;

/**
 * The wizard parameterized by the Sirius wizard description.
 * 
 * @author sbegaudeau
 */
public class PropertiesWizard extends Wizard {

    /**
     * The wizard model operation.
     */
    private WizardModelOperation wizardModelOperation;

    /**
     * The EEF View.
     */
    private EEFView eefView;

    /**
     * The interpreter.
     */
    private IInterpreter interpreter;

    /**
     * The variable manager.
     */
    private IVariableManager variableManager;

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
     * The consumer used to refresh the wizard when a change is performed.
     */
    private Consumer<IStatus> consumer;

    /**
     * The constructor.
     * 
     * @param wizardModelOperation
     *            The wizard model operation
     * @param eefView
     *            The EEF View
     * @param interpreter
     *            The interpreter
     * @param variableManager
     *            The variable manager
     * @param modelAccessor
     *            The model accessor
     * @param context
     *            The command context
     * @param session
     *            The Sirius session
     */
    public PropertiesWizard(WizardModelOperation wizardModelOperation, EEFView eefView, IInterpreter interpreter, IVariableManager variableManager, ModelAccessor modelAccessor, CommandContext context,
            Session session) {
        this.wizardModelOperation = wizardModelOperation;
        this.eefView = eefView;
        this.interpreter = interpreter;
        this.variableManager = variableManager;
        this.modelAccessor = modelAccessor;
        this.context = context;
        this.session = session;

        this.setNeedsProgressMonitor(false);
        this.setHelpAvailable(false);

        this.evaluateExpression(this.variableManager.getVariables(), this.wizardModelOperation.getWindowTitleExpression(), String.class).ifPresent(this::setWindowTitle);
        this.configureModelChangeRefresh();
    }

    /**
     * Configures a consumer executed when {@link EditingContextAdapter#performModelChange(Runnable)} is called in order
     * to refresh the wizard.
     */
    private void configureModelChangeRefresh() {
        EditingContextAdapter editingContextAdapter = this.eefView.getContextAdapter();
        if (editingContextAdapter instanceof EditingContextAdapterWrapper) {
            EditingContextAdapterWrapper wrapper = (EditingContextAdapterWrapper) editingContextAdapter;
            this.consumer = (status) -> {
                // @formatter:off
                Optional.ofNullable(this.getContainer().getCurrentPage())
                    .filter(PropertiesWizardPage.class::isInstance)
                    .map(PropertiesWizardPage.class::cast)
                    .ifPresent(this::refreshWizard);
                // @formatter:on
            };

            wrapper.addPerformedModelChangeConsumer(this.consumer);
        }
    }

    /**
     * Refreshes the current page and the state of the wizard.
     * 
     * @param wizardPage
     *            The current page to refresh
     */
    private void refreshWizard(PropertiesWizardPage wizardPage) {
        wizardPage.refresh();
        this.getContainer().updateButtons();
    }

    @Override
    public void addPages() {
        this.eefView.getPages().forEach(this::createPage);
    }

    /**
     * Creates the {@link PropertiesWizardPage} for the given {@link EEFPage}.
     * 
     * @param eefPage
     *            The EEF Page to use
     */
    private void createPage(EEFPage eefPage) {
        String title = this.evaluateExpression(this.variableManager.getVariables(), this.wizardModelOperation.getTitleExpression(), String.class).orElse(""); //$NON-NLS-1$
        String description = this.evaluateExpression(this.variableManager.getVariables(), this.wizardModelOperation.getDescriptionExpression(), String.class).orElse(""); //$NON-NLS-1$

        ImageDescriptor imageDescriptor = null; // SBE Use the same code as MEB for the images
        this.addPage(new PropertiesWizardPage(this.wizardModelOperation.toString(), title, description, imageDescriptor, this.wizardModelOperation, eefPage));
    }

    /**
     * Evaluates the given expression.
     * 
     * @param variables
     *            The variables
     * @param expression
     *            The expression
     * @param clazz
     *            The expected return type
     * @return The computed value or an empty optional if the value is null or if it does not match the expected type
     */
    private <T> Optional<T> evaluateExpression(Map<String, Object> variables, String expression, Class<T> clazz) {
        // @formatter:off
        return Optional.ofNullable(expression)
                    .filter(exp -> !exp.isEmpty())
                    .map(exp -> this.interpreter.evaluateExpression(this.variableManager.getVariables(), exp))
                    .filter(IEvaluationResult::success)
                    .map(IEvaluationResult::getValue)
                    .filter(clazz::isInstance)
                    .map(clazz::cast);
        // @formatter:on
    }

    @Override
    public boolean performCancel() {
        this.aboutToBeClosed();
        return super.performCancel();
    }

    /**
     * This methods is used to notify the current page that it is about to be hidden because the wizard will be closed.
     */
    private void aboutToBeClosed() {
        // @formatter:off
        Optional.ofNullable(this.getContainer().getCurrentPage())
            .filter(PropertiesWizardPage.class::isInstance)
            .map(PropertiesWizardPage.class::cast)
            .ifPresent(PropertiesWizardPage::aboutToBeHidden);
        // @formatter:on
    }

    @Override
    public boolean performFinish() {
        // Trigger the listener before launching the initial operation
        this.aboutToBeClosed();

        EditingContextAdapter editingContextAdapter = this.eefView.getContextAdapter();
        if (editingContextAdapter instanceof EditingContextAdapterWrapper) {
            EditingContextAdapterWrapper wrapper = (EditingContextAdapterWrapper) editingContextAdapter;
            wrapper.removePerformedModelChangeConsumer(this.consumer);
        }

        try {
            this.getContainer().run(false, false, (monitor) -> {
                Optional.ofNullable(this.wizardModelOperation.getInitialOperation()).flatMap(initialOperation -> Optional.ofNullable(initialOperation.getFirstModelOperations()))
                        .ifPresent(modelOperation -> {
                            ICommandTask task = new TaskHelper(this.modelAccessor, SiriusPlugin.getDefault().getUiCallback()).buildTaskFromModelOperation(this.context.getCurrentTarget(),
                                    modelOperation);
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
            });
        } catch (InvocationTargetException | InterruptedException e) {
            SiriusUIPropertiesPlugin.getPlugin().error(e.getMessage(), e);
        }
        return true;
    }

}
