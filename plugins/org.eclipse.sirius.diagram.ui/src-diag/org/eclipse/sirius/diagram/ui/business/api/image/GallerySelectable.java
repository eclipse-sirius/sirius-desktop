/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.api.image;

import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery.Gallery;
import org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery.GalleryItem;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

/**
 * This class is used to create a {@link Gallery} with a simpler {@link GalleryItem} selection and to notify listeners.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class GallerySelectable extends Gallery {

    /**
     * Create a new instance of {@link GallerySelectable}.
     * 
     * @param parent
     *            the parent composite
     * @param style
     *            the SWT style to apply
     */
    public GallerySelectable(Composite parent, int style) {
        super(parent, style);
    }

    /**
     * Select an item in the gallery and notify the listeners if specified.
     * 
     * @param item
     *            the item to select
     * @param notifyListeners
     *            notify the listeners if specified
     */
    public void selectItem(GalleryItem item, boolean notifyListeners) {
        setSelection(new GalleryItem[] { item }, notifyListeners);
    }

    /**
     * Select items specified in the gallery.
     */
    @Override
    public void setSelection(GalleryItem[] items) {
        setSelection(items, false);
    }

    /**
     * The old implementation of {@link Gallery#setSelection(GalleryItem[])} has been moved in this method to allow to
     * notify the listeners if needed.
     * 
     * @param items
     *            the items to select
     * @param notifyListeners
     *            notify the listeners if specified
     */
    public void setSelection(GalleryItem[] items, boolean notifyListeners) {
        checkWidget();
        _deselectAll(false);
        for (int i = 0; i < items.length; i++) {
            this.setSelected(items[i], true, notifyListeners);

            // Ensure item is visible
            showItem(items[i]);

            // Simulate mouse click to enable keyboard navigation
            lastSingleClick = items[i];
        }
        redraw();
    }

    @Override
    public void showItem(GalleryItem item) {
        if (item != null) {
            int y;
            int height;
            Rectangle rect = getGroupRenderer().getSize(item);
            if (rect == null) {
                return;
            }
            if (isVertical()) {
                y = rect.y;
                height = rect.height;
                if (y < translate) {
                    translate = y;
                } else if (translate + this.getClientArea().height < y + height) {
                    translate = y + height - this.getClientArea().height;
                }
            } else {
                y = rect.x;
                height = rect.width;

                if (y < translate) {
                    translate = y;
                } else if (translate + this.getClientArea().width < y + height) {
                    translate = y + height - this.getClientArea().width;
                }
            }
            this.updateScrollBarsProperties();
            redraw();
        }
    }
}
