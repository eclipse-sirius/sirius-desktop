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
package org.eclipse.sirius.business.internal.helper.task.operations;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * An unexecutable AbstractOperationTask.
 * 
 * @author fbarbin
 *
 */
public final class UnexecutableOperationTask extends AbstractOperationTask {

    private static UnexecutableOperationTask instance;

    private UnexecutableOperationTask(CommandContext context, ModelAccessor extPackage, IInterpreter interpreter) {
        super(context, extPackage, interpreter);
    }

    /**
     * Provides the UnexecutableOperationTask unique instance.
     * 
     * @return the UnexecutableOperationTask instance.
     */
    public static UnexecutableOperationTask getInstance() {
        if (instance == null) {
            instance = new UnexecutableOperationTask(null, null, null);
        }
        return instance;
    }

    @Override
    public String getLabel() {
        return Messages.UnexecutableTask_label;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
    }

    @Override
    public boolean canExecute() {
        return false;
    }

}
