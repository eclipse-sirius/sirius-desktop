/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
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

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;

/**
 * 
 * This class helps to wait that a item is enabled.
 * 
 * @author amartin
 * 
 * 
 */
public class ItemEnabledCondition extends DefaultCondition {

    private final AbstractSWTBot<? extends Widget> item;

    /**
     * Constructor.
     * 
     * @param item
     *            The item to wait to be enabled
     */
    public ItemEnabledCondition(final AbstractSWTBot<? extends Widget> item) {
        this.item = item;
    }

    @Override
    public String getFailureMessage() {
        return "item with text " + item.getText() + " is not enabled";
    }

    /**
     * Test if the item is enabled.
     * 
     * @throws Exception
     *             if the test fail.
     * @return if the item is enabled
     */
    @Override
    public boolean test() throws Exception {
        return item.isEnabled();
    }
}
