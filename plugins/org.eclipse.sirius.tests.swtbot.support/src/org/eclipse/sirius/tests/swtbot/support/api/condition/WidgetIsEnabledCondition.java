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
 * A Condition to test if a widget is enable.
 * 
 * @author hmarchadour
 */
public class WidgetIsEnabledCondition extends DefaultCondition {

    private final AbstractSWTBot<? extends Widget> widget;

    /**
     * Default Constructor.
     * 
     * @param widget
     *            the widget to test
     */
    public WidgetIsEnabledCondition(AbstractSWTBot<? extends Widget> widget) {
        this.widget = widget;
    }

    @Override
    public boolean test() throws Exception {
        return widget.isEnabled();
    }

    @Override
    public String getFailureMessage() {
        return "The widget " + widget + " was not enabled.";
    }

}
