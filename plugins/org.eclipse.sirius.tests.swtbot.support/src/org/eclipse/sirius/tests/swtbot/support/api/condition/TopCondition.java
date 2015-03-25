/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * A specific condition to wait that the top of the edit part is on the expected
 * axis.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class TopCondition extends DefaultCondition {
    /** Element to move. */
    private final GraphicalEditPart part;

    /** The expected y location */
    private int expectedYLocation;

    /**
     * A delta of one pixel can be tolerated, in case of zoom different from
     * 100% for example.
     */
    private boolean addOnePixelDelta;

    /** Failure message when a test fails */
    private String failureMessage = "";

    private int actual;

    /**
     * Default constructor.
     * 
     * @param part
     *            Part to check
     * @param expectedYLocation
     *            The expected y location
     * @param failureMessage
     *            Failure message when a test fails
     * @param addOnePixelDelta
     *            A delta of one pixel can be tolerated, in case of zoom
     *            different from 100% for example
     */
    public TopCondition(GraphicalEditPart part, int expectedYLocation, String failureMessage, boolean addOnePixelDelta) {
        this.expectedYLocation = expectedYLocation;
        this.part = part;
        this.failureMessage = failureMessage;
        this.addOnePixelDelta = addOnePixelDelta;
    }

    @Override
    public boolean test() throws Exception {
        Rectangle newBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(part);
        actual = newBounds.getTop().y;
        if (addOnePixelDelta) {
            return (expectedYLocation - 1) <= actual && actual <= (expectedYLocation + 1);
        } else {
            return expectedYLocation == actual;
        }
    }

    @Override
    public String getFailureMessage() {
        return failureMessage + ", expected:<" + expectedYLocation + "> but was:<" + actual + ">";
    }
}
