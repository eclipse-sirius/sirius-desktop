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
package org.eclipse.sirius.business.api.helper.task;

import java.util.Optional;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * Interface used to handle a {@link ModelOperation}.
 * 
 * @author sbegaudeau
 */
public interface IModelOperationManager {

    /**
     * Creates a task to run the given model operation.
     * 
     * @param modelOperation
     *            The model operation to execute
     * @param modelAccessor
     *            The model accessor
     * @param uiCallback
     *            The UiCallback
     * @param session
     *            The Sirius session
     * @param interpreter
     *            The interpreter
     * @param context
     *            The context
     * @return An optional with the task to execute or an empty optional if it could not be created
     */
    Optional<ICommandTask> createTask(ModelOperation modelOperation, ModelAccessor modelAccessor, UICallBack uiCallback, Session session, IInterpreter interpreter, CommandContext context);

}
