/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.policies;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.NonResizableEditPolicyEx;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;


/**
 * @was-generated
 */
public class SiriusTextNonResizableEditPolicy extends NonResizableEditPolicyEx {

    /**
     * @was-generated
     */
    private IFigure selectionFeedbackFigure;

    /**
     * @was-generated
     */
    private IFigure focusFeedbackFigure;

    /**
     * @not-generated
     */
    @Override
    protected void showPrimarySelection() {
        if (getHostFigure() instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) getHostFigure()).setSelected(true);
            ((SiriusWrapLabel) getHostFigure()).setFocus(true);
        } else {
            showSelection();
            showFocus();
        }
    }

    /**
     * @not-generated
     */
    @Override
    protected void showSelection() {
        if (getHostFigure() instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) getHostFigure()).setSelected(true);
            ((SiriusWrapLabel) getHostFigure()).setFocus(false);
        } else {
            hideSelection();
            addFeedback(selectionFeedbackFigure = createSelectionFeedbackFigure());
            refreshSelectionFeedback();
            hideFocus();
        }
    }

    /**
     * @not-generated
     */
    @Override
    protected void hideSelection() {
        if (getHostFigure() instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) getHostFigure()).setSelected(false);
            ((SiriusWrapLabel) getHostFigure()).setFocus(false);
        } else {
            if (selectionFeedbackFigure != null) {
                removeFeedback(selectionFeedbackFigure);
                selectionFeedbackFigure = null;
            }
            hideFocus();
        }
    }

    /**
     * @not-generated
     */
    @Override
    protected void showFocus() {
        if (getHostFigure() instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) getHostFigure()).setFocus(true);
        } else {
            hideFocus();
            addFeedback(focusFeedbackFigure = createFocusFeedbackFigure());
            refreshFocusFeedback();
        }
    }

    /**
     * @not-generated
     */
    @Override
    protected void hideFocus() {
        if (getHostFigure() instanceof SiriusWrapLabel) {
            ((SiriusWrapLabel) getHostFigure()).setFocus(false);
        } else {
            if (focusFeedbackFigure != null) {
                removeFeedback(focusFeedbackFigure);
                focusFeedbackFigure = null;
            }
        }
    }

    /**
     * @was-generated
     */
    protected Rectangle getFeedbackBounds() {
        Rectangle bounds;
        if (getHostFigure() instanceof Label) {
            bounds = ((Label) getHostFigure()).getTextBounds();
            bounds.intersect(getHostFigure().getBounds());
        } else {
            bounds = new Rectangle(getHostFigure().getBounds());
        }
        getHostFigure().getParent().translateToAbsolute(bounds);
        getFeedbackLayer().translateToRelative(bounds);
        return bounds;
    }

    /**
     * @was-generated
     */
    protected IFigure createSelectionFeedbackFigure() {
        if (getHostFigure() instanceof Label) {
            final Label feedbackFigure = new Label();
            feedbackFigure.setOpaque(true);
            feedbackFigure.setBackgroundColor(ColorConstants.menuBackgroundSelected);
            feedbackFigure.setForegroundColor(ColorConstants.menuForegroundSelected);
            return feedbackFigure;
        } else {
            final RectangleFigure feedbackFigure = new RectangleFigure();
            feedbackFigure.setFill(false);
            return feedbackFigure;
        }
    }

    /**
     * @was-generated
     */
    protected IFigure createFocusFeedbackFigure() {
        return new Figure() {

            @Override
            protected void paintFigure(Graphics graphics) {
                graphics.drawFocus(getBounds().getResized(-1, -1));
            }
        };
    }

    /**
     * @was-generated
     */
    protected void updateLabel(Label target) {
        final Label source = (Label) getHostFigure();
        target.setText(source.getText());
        target.setTextAlignment(source.getTextAlignment());
        target.setFont(source.getFont());
    }

    /**
     * @was-generated
     */
    protected void refreshSelectionFeedback() {
        if (selectionFeedbackFigure != null) {
            if (selectionFeedbackFigure instanceof Label) {
                updateLabel((Label) selectionFeedbackFigure);
                selectionFeedbackFigure.setBounds(getFeedbackBounds());
            } else {
                selectionFeedbackFigure.setBounds(getFeedbackBounds().expand(5, 5));
            }
        }
    }

    /**
     * @was-generated
     */
    protected void refreshFocusFeedback() {
        if (focusFeedbackFigure != null) {
            focusFeedbackFigure.setBounds(getFeedbackBounds());
        }
    }

    /**
     * @was-generated
     */
    public void refreshFeedback() {
        refreshSelectionFeedback();
        refreshFocusFeedback();
    }

    /**
     * @was-generated
     */
    @Override
    protected List createSelectionHandles() {
        final MoveHandle moveHandle = new MoveHandle((GraphicalEditPart) getHost());
        moveHandle.setBorder(null);
        moveHandle.setDragTracker(new DragEditPartsTrackerEx(getHost()));
        return Collections.singletonList(moveHandle);
    }
}
