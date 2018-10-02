/**
 * Copyright (c) 2016 THALES GLOBAL SERVICES
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
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;

/**
 * Condition validating a checkbox status (checked or unchecked).
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class CheckboxStatusCondition extends DefaultCondition {

    private SWTBotCheckBox checkBox;

    private boolean expectedToBeChecked;

    /**
     * Constructor.
     * 
     * @param checkBox
     *            the checkbox to validate the status.
     * 
     * @param expectedToBeChecked
     *            true if expected to be checked, false otherwise.
     */
    public CheckboxStatusCondition(SWTBotCheckBox checkBox, Boolean expectedToBeChecked) {
        this.checkBox = checkBox;
        this.expectedToBeChecked = expectedToBeChecked;
    }

    @Override
    public boolean test() throws Exception {
        return this.checkBox.isChecked() == this.expectedToBeChecked;
    }

    @Override
    public String getFailureMessage() {
        if (expectedToBeChecked) {
            return "The checkbox was expected to be checked but it was unchecked";
        } else {
            return "The checkbox was expected to be unchecked but was checked";
        }
    }
}
