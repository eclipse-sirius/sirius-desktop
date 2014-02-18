/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

    /**
     * Default constructor.
     */
    public OperationDoneCondition() {
        super(OperationHistoryEvent.DONE);
    }

    @Override
    public String getFailureMessage() {
        return "operation execution failed";
    }

}
