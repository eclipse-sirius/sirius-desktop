/**
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES
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
 * SWTBot condition to check that the last operation on the command stack has
 * been undone.
 * 
 * @author edugueperoux
 */
public class OperationUndoneCondition extends AbstractOperationCondition {

    /**
     * Default constructor.
     */
    public OperationUndoneCondition() {
        super(OperationHistoryEvent.UNDONE);
    }

    @Override
    public String getFailureMessage() {
        super.getFailureMessage();
        return "Undo of operation failed";
    }

}
