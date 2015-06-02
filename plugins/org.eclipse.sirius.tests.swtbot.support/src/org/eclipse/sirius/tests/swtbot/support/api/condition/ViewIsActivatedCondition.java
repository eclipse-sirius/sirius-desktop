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
 * A Condition to test if a view is activated.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ViewIsActivatedCondition extends DefaultCondition {

    private final SWTBotView view;

    private final boolean negate;

    /**
     * Default Constructor.
     * 
     * @param view
     *            the view to test
     */
    public ViewIsActivatedCondition(SWTBotView view) {
        this(view, false);
    }

    /**
     * Default Constructor.
     * 
     * @param view
     *            the view to test
     * @param negate
     *            allow to reverse the test (ie check if the view is not
     *            activated).
     */
    public ViewIsActivatedCondition(SWTBotView view, boolean negate) {
        this.view = view;
        this.negate = negate;
    }

    @Override
    public boolean test() throws Exception {
        return negate != view.isActive();
    }

    @Override
    public String getFailureMessage() {
        if (negate) {
            return "The view " + view.getTitle() + " is activated, it should not.";
        } else {
            return "The view " + view.getTitle() + " is not activated, it should.";
        }
    }
}
