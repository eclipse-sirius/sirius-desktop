/**
 * Copyright (c) 2015 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * A Condition to test if a view is closed.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ViewIsClosedCondition extends DefaultCondition {

    private final SWTBotView view;

    /**
     * Default Constructor.
     * 
     * @param view
     *            the view to test
     */
    public ViewIsClosedCondition(SWTBotView view) {
        this.view = view;
    }

    @Override
    public boolean test() throws Exception {
        return null == view.getViewReference().getPart(false);
    }

    @Override
    public String getFailureMessage() {
        return "The view " + view.getTitle() + " is not closed.";
    }
}
