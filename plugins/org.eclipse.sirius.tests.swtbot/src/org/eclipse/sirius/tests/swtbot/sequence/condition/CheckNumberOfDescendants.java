/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Class to check the number of descendants of a type.
 * 
 * @author smonnier
 */
public class CheckNumberOfDescendants extends DefaultCondition {
    private SWTBotGefEditPart gefBot;

    private Class<? extends GraphicalEditPart> editPartClass;

    private int expectedEditPartNumber;

    /**
     * Constructor.
     * 
     * @param gefBot
     *            the bot we want to count the descendant of class
     *            editPartClass.
     * 
     * @param editPartClass
     *            class of edit part we are counting.
     * @param expectedEditPartNumber
     *            expected descendant
     */
    public CheckNumberOfDescendants(SWTBotGefEditPart gefBot, Class<? extends GraphicalEditPart> editPartClass, int expectedEditPartNumber) {
        this.gefBot = gefBot;
        this.editPartClass = editPartClass;
        this.expectedEditPartNumber = expectedEditPartNumber;
    }

    public boolean test() throws Exception {
        return gefBot.descendants(IsInstanceOf.instanceOf(editPartClass)).size() == expectedEditPartNumber;
    }

    public String getFailureMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
