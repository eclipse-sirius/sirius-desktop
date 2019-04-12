/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * A Condition to wait for the expected number of selected elements.
 * 
 * @author fbarbin
 *
 */
public class SelectionCountCondition extends DefaultCondition {

    private SWTBotTree swtBotTree;

    private int expected;

    /**
     * Default constructor.
     * 
     * @param swtBotTree
     *            the {@link SWTBotTree}.
     * @param expected
     *            the expected selection count.
     */
    public SelectionCountCondition(SWTBotTree swtBotTree, int expected) {
        this.swtBotTree = swtBotTree;
        this.expected = expected;
    }

    @Override
    public boolean test() throws Exception {
        return swtBotTree.selectionCount() == expected;
    }

    @Override
    public String getFailureMessage() {
        return "Wrong selection count";
    }

}
