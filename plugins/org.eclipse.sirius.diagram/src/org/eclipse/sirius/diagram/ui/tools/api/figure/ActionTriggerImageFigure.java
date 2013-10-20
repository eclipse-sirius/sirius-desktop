/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.swt.graphics.Image;

import org.eclipse.sirius.common.ui.tools.api.util.ISimpleAction;

/**
 * A figure that triggers actions when the user clicks on the image.
 * 
 * 
 * @author ymortier
 */
public class ActionTriggerImageFigure extends MouseAwareImageFigure {

    /** The image that is shown when the user clicks on the figure. */
    protected Image clickedImage;

    /**
     * Actions to triggers when the image is clicked (type of
     * {@link ISimpleAction}.
     */
    private List actions;

    /**
     * the action trigger mandatory to simulate click
     */
    private ActionTrigger actionTrigger;

    /**
     * Create a new {@link ActionTriggerImageFigure}.
     */
    public ActionTriggerImageFigure() {
        super();
        this.init();
    }

    /**
     * Create a new {@link ActionTriggerImageFigure}.
     * 
     * @param imageWOFocus
     *            the image that is shown when the mouse is not on the figure.
     * @param imageWFocus
     *            the image that is shown when the mouse is on the figure.
     */
    public ActionTriggerImageFigure(final Image imageWOFocus, final Image imageWFocus) {
        super(imageWOFocus, imageWFocus);
        this.init();
    }

    /**
     * Create a new {@link ActionTriggerImageFigure}.
     * 
     * @param imageWOFocus
     *            the image that is shown when the mouse is not on the figure.
     */
    public ActionTriggerImageFigure(final Image imageWOFocus) {
        super(imageWOFocus);
        this.init();
    }

    /**
     * Initialize the figure.
     */
    private void init() {
        this.actions = new LinkedList();
        this.actionTrigger = new ActionTrigger();
        this.addMouseListener(this.actionTrigger);
    }

    /**
     * This class has the responsability to trigger actions when the figure is
     * clicked.
     * 
     * @author ymortier
     */
    private class ActionTrigger implements MouseListener {

        /**
         * @see MouseListener#mouseDoubleClicked(MouseEvent)
         */
        public void mouseDoubleClicked(final MouseEvent me) {
            // do nothing.
        }

        /**
         * @see MouseListener#mousePressed(MouseEvent)
         */
        public void mousePressed(final MouseEvent me) {
            if (clickedImage != null) {
                ActionTriggerImageFigure.this.setImage(clickedImage);
            }
            trigger();
            me.consume();
        }

        /**
         * @see MouseListener#mouseReleased(MouseEvent)
         */
        public void mouseReleased(final MouseEvent me) {
            if (getImage() == clickedImage) {
                setImage(imageWFocus);
            }
        }

    }

    /**
     * Trigger all actions.
     */
    public void trigger() {
        final Iterator iterActions = this.iterActions();
        while (iterActions.hasNext()) {
            final ISimpleAction action = (ISimpleAction) iterActions.next();
            action.executeAction();
        }
    }

    /**
     * Add an action.
     * 
     * @param simpleAction
     *            the action to add.
     */
    public void addAction(final ISimpleAction simpleAction) {
        this.actions.add(simpleAction);
    }

    /**
     * Add an action at the specified index.
     * 
     * @param simpleAction
     *            the action to add.
     * @param index
     *            index at which the specified element is to be inserted
     */
    public void addAction(final ISimpleAction simpleAction, final int index) {
        this.actions.add(index, simpleAction);
    }

    /**
     * Remove all actions.
     */
    public void clearActions() {
        this.actions.clear();
    }

    /**
     * Remove an action.
     * 
     * @param simpleAction
     *            the action to remove.
     */
    public void removeAction(final ISimpleAction simpleAction) {
        this.actions.remove(simpleAction);
    }

    /**
     * Return an iterator that iterates on actions.
     * 
     * @return an iterator that iterates on actions.
     */
    public Iterator iterActions() {
        return this.actions.iterator();
    }

    /**
     * Define the image that is shown when the user clicks on the figure.
     * 
     * @param clickedImage
     *            the image that is shown when the user clicks on the figure.
     */
    public void setClickedImage(final Image clickedImage) {
        this.clickedImage = clickedImage;
    }

}
