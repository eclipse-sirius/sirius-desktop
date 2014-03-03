/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
     * the edit part to wait for its selection.
     */
    private final IGraphicalEditPart editPartToWaitForSelection;

    /**
     * the class of the edit part to wait for its selection.
     */
    private Class<? extends IGraphicalEditPart> editPartClass;

    /**
     * The expected bounds in absolute coordinates.
     */
    private final Rectangle expectedBounds;

    /**
     * Constructor.
     * 
     * @param editPartToWaitForSelection
     *            the edit part to wait for its bounds.
     * @param expectedAbsoluteBounds
     *            expected bounds in absolute coordinate
     */
    public CheckBoundsCondition(IGraphicalEditPart editPartToWaitForSelection, Rectangle expectedAbsoluteBounds) {
        this.editPartToWaitForSelection = editPartToWaitForSelection;
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
        this.editPartToWaitForSelection = editPartToWaitForSelection;
        this.expectedBounds = expectedAbsoluteBounds;
        this.checkWidth = checkWidth;
        this.checkHeight = checkHeight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        boolean result = false;
        if (editPartToWaitForSelection != null) {
            if (checkHeight && checkWidth) {
                result = getCurrentAbsoluteBounds().equals(expectedBounds);
            } else if (checkWidth) {
                result = getCurrentAbsoluteBounds().width == expectedBounds.width;
            } else if (checkHeight) {
                result = getCurrentAbsoluteBounds().height == expectedBounds.height;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        String result = null;
        if (checkHeight && checkWidth) {
            result = new ComparisonFailure("The expected bounds is not reached.", expectedBounds.toString(), getCurrentAbsoluteBounds().toString()).getMessage();
        } else if (checkWidth) {
            result = new ComparisonFailure("The expected width is not reached.", Integer.toString(expectedBounds.width), Integer.toString(getCurrentAbsoluteBounds().width)).getMessage();
        } else if (checkHeight) {
            result = new ComparisonFailure("The expected width is not reached.", Integer.toString(expectedBounds.height), Integer.toString(getCurrentAbsoluteBounds().height)).getMessage();
        }
        return result;
    }

    /**
     * Return the absolute bounds of the edit part.
     * 
     * @return The absolute bounds.
     */
    protected Rectangle getCurrentAbsoluteBounds() {
        Rectangle bounds = editPartToWaitForSelection.getFigure().getBounds().getCopy();
        editPartToWaitForSelection.getFigure().translateToAbsolute(bounds);
        return bounds;
    }
}
