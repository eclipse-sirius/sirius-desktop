/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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

import java.util.Optional;

import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.IModelOperationManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * The model operation manager in charge of the {@link WizardModelOperation}.
 * 
 * @author sbegaudeau
 */
public class WizardModelOperationManager implements IModelOperationManager {

    @Override
    public Optional<ICommandTask> createTask(ModelOperation modelOperation, ModelAccessor modelAccessor, UICallBack uiCallback, Session session, IInterpreter interpreter, CommandContext context) {
        if (modelOperation instanceof WizardModelOperation) {
            WizardModelOperation wizardModelOperation = (WizardModelOperation) modelOperation;
            return Optional.of(new WizardTask(context, modelAccessor, interpreter, session, wizardModelOperation));
        }
        return Optional.empty();
    }

}
