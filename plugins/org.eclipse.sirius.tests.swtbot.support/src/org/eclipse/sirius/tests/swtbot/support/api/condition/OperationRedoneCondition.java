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
 * SWTBot condition to check that the last operation on the command stack has
 * been redone.
 * 
 * @author edugueperoux
 */
public class OperationRedoneCondition extends AbstractOperationCondition {

    /**
     * Default constructor.
     */
    public OperationRedoneCondition() {
        super(OperationHistoryEvent.REDONE);
    }

    @Override
    public String getFailureMessage() {
        return "Redo of operation failed";
    }

}
