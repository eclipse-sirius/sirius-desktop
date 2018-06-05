/*******************************************************************************
 * Copyright (c) 2017, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.EEFPage;
import org.eclipse.eef.core.api.EEFView;
import org.eclipse.eef.core.api.EEFViewFactory;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.common.interpreter.api.VariableManagerFactory;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.core.api.DialogModelOperationPreprocessor;
import org.eclipse.sirius.properties.core.api.OverridesProvider;
import org.eclipse.sirius.properties.core.api.SiriusDomainClassTester;
import org.eclipse.sirius.properties.core.api.SiriusInputDescriptor;
import org.eclipse.sirius.properties.core.api.SiriusInterpreter;
import org.eclipse.sirius.properties.core.api.ViewDescriptionConverter;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.ui.properties.internal.EditingContextAdapterWrapper;
import org.eclipse.sirius.ui.properties.internal.Messages;
import org.eclipse.sirius.ui.properties.internal.SiriusUIPropertiesPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.FormDialog;

/**
 * The task used to open a dialog.
 * 
 * @author sbegaudeau
 */
public class DialogTask extends AbstractOperationTask {

    /**
     * The session.
     */
    private Session session;

    /**
     * The dialog model operation.
     */
    private DialogModelOperation dialogModelOperation;

    /**
     * The constructor.
     * 
     * @param context
     *            The context
     * @param modelAccessor
     *            The model accessor
     * @param interpreter
     *            The interpreter
     * @param session
     *            The session
     * @param dialogModelOperation
     *            The dialog model operation
     */
    public DialogTask(CommandContext context, ModelAccessor modelAccessor, IInterpreter interpreter, Session session, DialogModelOperation dialogModelOperation) {
        super(context, modelAccessor, interpreter);
        this.session = session;
        this.dialogModelOperation = dialogModelOperation;
    }

    @Override
    public String getLabel() {
        return Messages.DialogTask_label;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        SiriusInputDescriptor input = new SiriusInputDescriptor(this.context.getCurrentTarget());

        IVariableManager variableManager = new VariableManagerFactory().createVariableManager();
        variableManager.put(EEFExpressionUtils.SELF, input.getSemanticElement());
        variableManager.put(EEFExpressionUtils.INPUT, input);
        SiriusInterpreter siriusInterpreter = new SiriusInterpreter(this.session);

        DialogModelOperationPreprocessor preprocessor = new DialogModelOperationPreprocessor(this.dialogModelOperation, siriusInterpreter, variableManager, new OverridesProvider(this.session));

        Optional<DialogModelOperation> optionalDialogModelOperation = preprocessor.convert();
        optionalDialogModelOperation.ifPresent(convertedDialogModelOperation -> {
            PageDescription pageDescription = convertedDialogModelOperation.getPage();
            List<PageDescription> pageDescriptions = new ArrayList<>();
            pageDescriptions.add(pageDescription);

            ViewDescriptionConverter viewDescriptionConverter = new ViewDescriptionConverter(pageDescriptions);
            EEFViewDescription eefViewDescription = viewDescriptionConverter.convert(input);

            EditingContextAdapter editingContextAdapter = SiriusUIPropertiesPlugin.getPlugin().getEditingContextAdapter(session);
            EditingContextAdapterWrapper wrapper = new EditingContextAdapterWrapper(editingContextAdapter);

            EEFViewFactory eefViewFactory = new EEFViewFactory();
            EEFView eefView = eefViewFactory.createEEFView(eefViewDescription, variableManager, siriusInterpreter, wrapper, new SiriusDomainClassTester(session), input);

            if (eefView.getPages().size() == 1) {
                EEFPage eefPage = eefView.getPages().get(0);

                Runnable runnable = () -> {
                    Shell shell = Display.getCurrent().getActiveShell();
                    FormDialog formDialog = new PropertiesFormDialog(shell, this.extPackage, this.context, this.session, siriusInterpreter, variableManager, convertedDialogModelOperation, eefPage);
                    formDialog.create();
                    formDialog.getShell().setMinimumSize(600, 200);

                    int returnCode = formDialog.open();

                    if (Window.CANCEL == returnCode) {
                        throw new OperationCanceledException();
                    }
                };

                boolean isInUiThread = Display.getCurrent() != null && Display.getCurrent().getActiveShell() != null;
                if (isInUiThread) {
                    runnable.run();
                } else {
                    Display.getDefault().syncExec(runnable);
                }
            }
        });
    }

}
