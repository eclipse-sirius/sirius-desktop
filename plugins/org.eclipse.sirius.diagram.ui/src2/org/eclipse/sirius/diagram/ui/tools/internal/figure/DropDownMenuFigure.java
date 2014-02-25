/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.swt.graphics.Image;

/**
 * A figure which behaves like a drop down menu.
 * 
 * @author mchauvin
 */
public class DropDownMenuFigure extends Clickable {

    private static final int DEFAULT_MENU_ELEMENT_HEIGHT = 20;

    private List<IFigure> elements = new ArrayList<IFigure>();

    private RoundedRectangle subMenuFigure;

    private IFigure parent;

    private Collection<MouseMotionListener> listeners;

    /**
     * Construct a new figure which behaves like a drop down menu.
     * 
     * @param image
     *            the image to display for the figure
     * @param parent
     *            the parent figure
     */
    public DropDownMenuFigure(final ImageFigure image, final IFigure parent) {
        super(image);

        this.parent = parent;
        this.listeners = new ArrayList<MouseMotionListener>();

        final ButtonModel buttonModel = createDefaultModel();
        buttonModel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                final boolean visibility = subMenuFigure.isVisible();
                subMenuFigure.setVisible(!visibility);
                for (final IFigure elem : elements) {
                    elem.setVisible(!visibility);
                }
            }
        });
        setModel(buttonModel);

    }

    /**
     * Add a menu element.
     * 
     * @param entry
     *            the menu element to add
     */
    public void addToMenu(final IFigure entry) {
        elements.add(entry);
    }

    /**
     * Add a menu element.
     * 
     * @param image
     *            the element image
     * @param label
     *            the element label
     * @param runnable
     *            the handler to run when clicked
     */
    public void addToMenu(final Image image, final String label, final Runnable runnable) {
        elements.add(createActionFigure(image, label, runnable));
    }

    private IFigure createActionFigure(final Image image, final String label, final Runnable runnable) {
        final IFigure imageFigure = new Label(label, image);
        imageFigure.setFont(VisualBindingManager.getDefault().getFontFromValue(9));
        imageFigure.addMouseListener(new MouseListener.Stub() {
            @Override
            public void mousePressed(final MouseEvent me) {
                runnable.run();
            }
        });
        return imageFigure;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#addMouseMotionListener(org.eclipse.draw2d.MouseMotionListener)
     */
    @Override
    public void addMouseMotionListener(final MouseMotionListener listener) {
        super.addMouseMotionListener(listener);
        if (listeners != null) {
            this.listeners.add(listener);
        }
    }

    /**
     * Update the figure based on the added elements.
     */
    public void updateFigure() {
        createSubMenuFigure();
        for (IFigure elem : elements) {
            final Dimension d = elem.getPreferredSize();
            elem.setSize(d);
            elem.setLocation(new Point(this.getBounds().getTopLeft().x, parent.getBounds().getTopLeft().y + DEFAULT_MENU_ELEMENT_HEIGHT + 10 + elem.getSize().height * elements.indexOf(elem)));
            elem.setVisible(false);
            parent.add(elem);
        }
        subMenuFigure.setSize(getMaxWidth(), DEFAULT_MENU_ELEMENT_HEIGHT * elements.size());

        for (final MouseMotionListener listener : listeners) {
            this.subMenuFigure.addMouseMotionListener(listener);
        }
        this.listeners.clear();
    }

    private void createSubMenuFigure() {
        if (subMenuFigure == null) {
            subMenuFigure = new RoundedRectangle();
            subMenuFigure.setCornerDimensions(new Dimension(0, 0));
            subMenuFigure.setLocation(new Point(this.getBounds().getTopLeft().x, parent.getBounds().getCopy().getTopLeft().y + DEFAULT_MENU_ELEMENT_HEIGHT + 10));
            subMenuFigure.setVisible(false);
            parent.add(subMenuFigure);
        }

    }

    private int getMaxWidth() {
        int maxWidth = 0;
        for (IFigure elem : elements) {
            if (elem.getSize().width > maxWidth) {
                maxWidth = elem.getSize().width;
            }
        }
        return maxWidth;
    }

}
