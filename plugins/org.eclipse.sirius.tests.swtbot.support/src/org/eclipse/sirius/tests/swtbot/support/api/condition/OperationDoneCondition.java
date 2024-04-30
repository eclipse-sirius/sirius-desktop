/**
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.core.commands.operations.OperationHistoryEvent;

/**
 * SWTBot condition to check that a operation on the command stack has been
 * done.
 * 
 * @author edugueperoux
 */
public class OperationDoneCondition extends AbstractOperationCondition {

    String operationName;

    /**
     * Default constructor.
     */
    public OperationDoneCondition() {
        super(OperationHistoryEvent.DONE);
    }

    /**
     * Default constructor with an operation name.
     * 
     * @param operationName
     *            The name of the operation to be done
     */
    public OperationDoneCondition(String operationName) {
        super(OperationHistoryEvent.DONE);
        this.operationName = operationName;
    }

    @Override
    public String getFailureMessage() {
        super.getFailureMessage();
        String failureMessage = "Operation execution failed";
        if (operationName != null) {
            failureMessage += " for \"" + operationName + "\"";
        }
        return failureMessage;
    }

}
