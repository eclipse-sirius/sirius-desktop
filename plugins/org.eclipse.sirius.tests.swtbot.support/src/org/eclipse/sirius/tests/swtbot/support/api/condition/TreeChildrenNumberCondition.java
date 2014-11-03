/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * A {@link DefaultCondition} to test {@link SWTBotTree} children number.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TreeChildrenNumberCondition extends DefaultCondition {

    private SWTBotTree swtBotTree;

    private int childrenNumber;

    /**
     * Default constructor.
     * 
     * @param swtBotTree
     *            the {@link SWTBotTree} to test
     * @param childrenNumber
     *            the number of expected chidlren
     */
    public TreeChildrenNumberCondition(SWTBotTree swtBotTree, int childrenNumber) {
        this.swtBotTree = swtBotTree;
        this.childrenNumber = childrenNumber;
    }

    @Override
    public boolean test() throws Exception {
        return swtBotTree.rowCount() == childrenNumber;
    }

    @Override
    public String getFailureMessage() {
        return childrenNumber + " expected children but was " + swtBotTree.rowCount();
    }

}
