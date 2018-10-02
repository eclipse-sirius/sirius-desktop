/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
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

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * A condition for waiting that the tree displays the expected number of
 * elements.
 * 
 * @author lredor
 */
public class CheckNbVisibleElementsInTree extends DefaultCondition {

    private final SWTBotTree tree;

    private final int expected;

    private String errorMessage;

    /**
     * Default Constructor.
     * 
     * @param tree
     *            The tree to check.
     * @param expected
     *            Number of elements that should be display in this tree.
     * 
     */
    public CheckNbVisibleElementsInTree(SWTBotTree tree, int expected) {
        this.tree = tree;
        this.expected = expected;
    }

    /**
     * Constructor with error message.
     * 
     * @param tree
     *            The tree to check.
     * @param expected
     *            Number of elements that should be display in this tree.
     * @param errorMessage
     *            The message to display in case of failure.
     * 
     */
    public CheckNbVisibleElementsInTree(SWTBotTree tree, int expected, String errorMessage) {
        this.tree = tree;
        this.expected = expected;
        this.errorMessage = errorMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return tree.visibleRowCount() == expected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        String suffix = "(expected:<" + expected + "> but was:<" + tree.visibleRowCount() + ">)";
        if (errorMessage == null) {
            return "This tree does not display " + expected + " elements " + suffix + ".";
        } else {
            return errorMessage + suffix;
        }

    }
}
