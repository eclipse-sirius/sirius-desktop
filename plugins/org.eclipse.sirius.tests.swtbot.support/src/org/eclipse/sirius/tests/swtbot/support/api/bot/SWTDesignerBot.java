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
package org.eclipse.sirius.tests.swtbot.support.api.bot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.hamcrest.Matcher;

/**
 * Inner bot.
 * 
 * @author dlecan
 */
public class SWTDesignerBot extends SWTWorkbenchBot {

    /**
     * Get GMF drop down button with SWT.SEPARATOR style instead of
     * SWT.DROP_DOWN style !
     * 
     * @param tooltip
     *            the tooltip on the widget.
     * @return a ToolItem with the specified <code>tooltip</code>.
     */
    @SuppressWarnings("unchecked")
    public ToolItem toolbarSpecialDropDownButtonWithTooltip(final String tooltip) {
        final Matcher matcher = WidgetMatcherFactory.allOf(WidgetMatcherFactory.widgetOfType(ToolItem.class), WidgetMatcherFactory.withTooltip(tooltip),
                WidgetMatcherFactory.withStyle(SWT.SEPARATOR, "SWT.SEPARATOR"));
        return (ToolItem) widget(matcher, 0);
    }
}
