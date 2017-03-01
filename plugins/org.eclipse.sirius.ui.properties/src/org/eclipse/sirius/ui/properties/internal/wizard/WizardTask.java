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
package org.eclipse.sirius.ui.properties.internal.wizard;

import java.util.Optional;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.EEFView;
import org.eclipse.eef.core.api.EEFViewFactory;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.sirius.common.interpreter.api.VariableManagerFactory;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.properties.core.api.SiriusDomainClassTester;
import org.eclipse.sirius.properties.core.api.SiriusInputDescriptor;
import org.eclipse.sirius.properties.core.api.SiriusInterpreter;
import org.eclipse.sirius.properties.core.api.ViewDescriptionConverter;
import org.eclipse.sirius.properties.core.api.WizardModelOperationPreprocessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.ui.properties.internal.EditingContextAdapterWrapper;
import org.eclipse.sirius.ui.properties.internal.Messages;
import org.eclipse.sirius.ui.properties.internal.SiriusUIPropertiesPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * The task used to open a wizard.
 * 
 * @author sbegaudeau
 */
public class WizardTask extends AbstractOperationTask {

    /**
     * The session.
     */
    private Session session;

    /**
     * The wizard model operation.
     */
    private WizardModelOperation wizardModelOperation;

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
     * @param wizardModelOperation
     *            The wizard model operation
     */
    public WizardTask(CommandContext context, ModelAccessor modelAccessor, IInterpreter interpreter, Session session, WizardModelOperation wizardModelOperation) {
        super(context, modelAccessor, interpreter);
        this.session = session;
        this.wizardModelOperation = wizardModelOperation;
    }

    @Override
    public String getLabel() {
        return Messages.WizardTask_label;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        SiriusInputDescriptor input = new SiriusInputDescriptor(this.context.getCurrentTarget());

        IVariableManager variableManager = new VariableManagerFactory().createVariableManager();
        variableManager.put(EEFExpressionUtils.SELF, input.getSemanticElement());
        variableManager.put(EEFExpressionUtils.INPUT, input);

        SiriusInterpreter siriusInterpreter = new SiriusInterpreter(this.session);

        WizardModelOperationPreprocessor preprocessor = new WizardModelOperationPreprocessor(this.wizardModelOperation, siriusInterpreter, variableManager);
        Optional<WizardModelOperation> optionalWizardModelOperation = preprocessor.convert();
        optionalWizardModelOperation.ifPresent(convertedWizardModelOperation -> {
            ViewDescriptionConverter viewDescriptionConverter = new ViewDescriptionConverter(convertedWizardModelOperation.getPages());
            EEFViewDescription eefViewDescription = viewDescriptionConverter.convert(input);

            EditingContextAdapter editingContextAdapter = SiriusUIPropertiesPlugin.getPlugin().getEditingContextAdapter(session);
            EditingContextAdapterWrapper editingContextAdapterWrapper = new EditingContextAdapterWrapper(editingContextAdapter);

            EEFViewFactory eefViewFactory = new EEFViewFactory();
            EEFView eefView = eefViewFactory.createEEFView(eefViewDescription, variableManager, siriusInterpreter, editingContextAdapterWrapper, new SiriusDomainClassTester(session), input);

            if (!eefView.getPages().isEmpty()) {
                Shell shell = Display.getCurrent().getActiveShell();
                WizardDialog wizardDialog = new PropertiesWizardDialog(shell, this.extPackage, this.context, this.session, siriusInterpreter, variableManager, convertedWizardModelOperation, eefView);

                int returnCode = wizardDialog.open();

                if (Window.CANCEL == returnCode) {
                    throw new OperationCanceledException();
                }
            }
        });
    }

}
