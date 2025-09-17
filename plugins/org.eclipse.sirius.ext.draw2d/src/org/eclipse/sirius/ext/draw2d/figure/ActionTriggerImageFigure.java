/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.draw2d.figure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.swt.graphics.Image;
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
     * Actions to triggers when the image is clicked.
     */
    private List<Runnable> actions;

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
        this.actions = new LinkedList<Runnable>();
        this.actionTrigger = new ActionTrigger();
        this.addMouseListener(this.actionTrigger);
    }

    /**
     * This class has the responsability to trigger actions when the figure is
     * clicked.
     * 
     * @author ymortier
     */
    private final class ActionTrigger implements MouseListener {

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
        for (Runnable action : actions) {
            action.run();
        }
    }

    /**
     * Add an action.
     * 
     * @param simpleAction
     *            the action to add.
     */
    public void addAction(Runnable simpleAction) {
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
    public void addAction(Runnable simpleAction, final int index) {
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
    public void removeAction(Runnable simpleAction) {
        this.actions.remove(simpleAction);
    }

    /**
     * Return an iterator that iterates on actions.
     * 
     * @return an iterator that iterates on actions.
     */
    public Iterator<Runnable> iterActions() {
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
