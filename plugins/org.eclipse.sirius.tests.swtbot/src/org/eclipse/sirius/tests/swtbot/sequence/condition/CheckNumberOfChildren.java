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

import java.util.List;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.hamcrest.Matcher;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Class to check the number of descendants of a type.
 * 
 * @author smonnier
 */
public class CheckNumberOfChildren extends DefaultCondition {
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
    public CheckNumberOfChildren(SWTBotGefEditPart gefBot, Class<? extends GraphicalEditPart> editPartClass, int expectedEditPartNumber) {
        this.gefBot = gefBot;
        this.editPartClass = editPartClass;
        this.expectedEditPartNumber = expectedEditPartNumber;
    }

    public boolean test() throws Exception {
        List<SWTBotGefEditPart> children = gefBot.children();
        final Matcher<? extends GraphicalEditPart> instanceOf = IsInstanceOf.instanceOf(editPartClass);
        Predicate<SWTBotGefEditPart> requested = new Predicate<SWTBotGefEditPart>() {
            /**
             * {@inheritDoc}
             */
            public boolean apply(SWTBotGefEditPart input) {
                return instanceOf.matches(input);
            }
        };

        return Iterables.size(Iterables.filter(children, requested)) == expectedEditPartNumber;

    }

    public String getFailureMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
