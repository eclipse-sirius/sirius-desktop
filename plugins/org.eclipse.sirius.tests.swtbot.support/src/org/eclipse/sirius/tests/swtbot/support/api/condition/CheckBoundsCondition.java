/**
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.junit.ComparisonFailure;

/**
 * Class to check if the edit part has the expected bounds.
 * 
 * @author lredor
 */
public class CheckBoundsCondition extends DefaultCondition {

    /**
     * True if the width of the bounds must be checked.
     */
    private boolean checkWidth = true;

    /**
     * True if the height of the bounds must be checked.
     */
    private boolean checkHeight = true;

    /**
     * the edit part to wait for its expected bounds.
     */
    private final IGraphicalEditPart editPartToWaitFor;

    /**
     * The expected bounds in absolute coordinates.
     */
    private final Rectangle expectedBounds;

    /**
     * Constructor.
     * 
     * @param editPartToWaitFor
     *            the edit part to wait for its bounds.
     * @param expectedAbsoluteBounds
     *            expected bounds in absolute coordinate
     */
    public CheckBoundsCondition(IGraphicalEditPart editPartToWaitFor, Rectangle expectedAbsoluteBounds) {
        this.editPartToWaitFor = editPartToWaitFor;
        this.expectedBounds = expectedAbsoluteBounds;
    }

    /**
     * Constructor.
     * 
     * @param editPartToWaitForSelection
     *            the edit part to wait for its bounds.
     * @param expectedAbsoluteBounds
     *            expected bounds in absolute coordinate
     * @param checkWidth
     *            True if the width of the bounds must be checked.
     * @param checkHeight
     *            True if the height of the bounds must be checked.
     */
    public CheckBoundsCondition(IGraphicalEditPart editPartToWaitForSelection, Rectangle expectedAbsoluteBounds, boolean checkWidth, boolean checkHeight) {
        this.editPartToWaitFor = editPartToWaitForSelection;
        this.expectedBounds = expectedAbsoluteBounds;
        this.checkWidth = checkWidth;
        this.checkHeight = checkHeight;
    }

    @Override
    public boolean test() throws Exception {
        boolean result = false;
        if (editPartToWaitFor != null) {
            if (checkHeight && checkWidth) {
                result = getCurrentAbsoluteBounds().equals(expectedBounds);
            } else {
                Rectangle currentBounds = getCurrentAbsoluteBounds();
                if (checkWidth) {
                    result = currentBounds.width == expectedBounds.width;
                } else if (checkHeight) {
                    result = currentBounds.height == expectedBounds.height;
                } else {
                    result = true;
                }
                result = result && currentBounds.getLocation().equals(expectedBounds.getLocation());
            }
        }
        return result;
    }

    @Override
    public String getFailureMessage() {
        String result = null;
        if (checkHeight && checkWidth) {
            result = new ComparisonFailure("The expected bounds is not reached.", expectedBounds.toString(), getCurrentAbsoluteBounds().toString()).getMessage();
        } else if (checkWidth) {
            result = new ComparisonFailure("The expected width is not reached.", Integer.toString(expectedBounds.width), Integer.toString(getCurrentAbsoluteBounds().width)).getMessage();
        } else if (checkHeight) {
            result = new ComparisonFailure("The expected height is not reached.", Integer.toString(expectedBounds.height), Integer.toString(getCurrentAbsoluteBounds().height)).getMessage();
        } else {
            result = new ComparisonFailure("The expected location is not reached.", expectedBounds.getLocation().toString(), getCurrentAbsoluteBounds().getLocation().toString()).getMessage();
        }
        return result;
    }

    /**
     * Return the absolute bounds of the edit part.
     * 
     * @return The absolute bounds.
     */
    protected Rectangle getCurrentAbsoluteBounds() {
        Rectangle bounds = editPartToWaitFor.getFigure().getBounds().getCopy();
        editPartToWaitFor.getFigure().translateToAbsolute(bounds);
        return bounds;
    }
}
