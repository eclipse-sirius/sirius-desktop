/*******************************************************************************
 * Copyright (c) 2021, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs.widgets.gallery;

import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Static methods used to layout the gallery and add tooltips to {@link GalleryItem}.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public final class GalleryRendererUtils {

    /**
     * Thumbnails image width as shown in the UI.
     */
    static final int THUMBNAIL_IMAGE_WIDTH = 80;

    /**
     * Thumbnails image height as shown in the UI.
     */
    static final int THUMBNAIL_IMAGE_HEIGHT = 80;

    private static final int LIST_ITEM_HEIGHT = 32;

    /**
     * Constructor.
     */
    private GalleryRendererUtils() {
    }

    /**
     * Set up the layout of a standard gallery.
     * 
     * @param parent
     *            the parent <code>Composite</code>
     * @param gallery
     *            the gallery to layout
     */
    public static void setStandardGallery(Composite parent, Gallery gallery) {
        // Renderers
        DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer() {

            @Override
            protected String getGroupTitle(GalleryItem group) {
                return new String();
            }

            @Override
            protected int getGroupHeight(GalleryItem group) {
                return 0;
            }
        };
        gr.setMinMargin(2);
        gr.setItemHeight(GalleryRendererUtils.THUMBNAIL_IMAGE_HEIGHT);
        gr.setItemWidth(GalleryRendererUtils.THUMBNAIL_IMAGE_WIDTH);
        gr.setAutoMargin(true);
        gr.setAlwaysExpanded(true);
        gallery.setGroupRenderer(gr);

        DefaultGalleryItemRenderer itemRenderer = new DefaultGalleryItemRenderer();
        itemRenderer.setShowLabels(true);
        gallery.setItemRenderer(itemRenderer);

        configureTooltips(gallery);
    }

    /**
     * Set up the layout of a list gallery.
     * 
     * @param parent
     *            the parent <code>Composite</code>
     * @param gallery
     *            the gallery to layout
     */
    public static void setListGallery(Composite parent, Gallery gallery) {
        // Renderers
        DefaultGalleryGroupRenderer gr = new DefaultGalleryGroupRenderer() {

            @Override
            protected String getGroupTitle(GalleryItem group) {
                return new String();
            }

            @Override
            protected int getGroupHeight(GalleryItem group) {
                return 0;
            }

            @Override
            public void preLayout(GC gc) {
                itemWidth = gallery.getClientArea().width - margin;
                super.preLayout(gc);
            }
        };
        gr.setItemHeight(LIST_ITEM_HEIGHT);
        gr.setMinMargin(0);
        gr.setAutoMargin(false);
        gr.setAlwaysExpanded(true);
        gallery.setGroupRenderer(gr);
        ListItemRenderer itemRenderer = new ListItemRenderer();
        itemRenderer.setShowLabels(true);
        gallery.setItemRenderer(itemRenderer);

        configureTooltips(gallery);
    }

    /**
     * Add tooltips to the gallery which natively doesn't support them.
     * 
     * @param gallery
     *            the gallery
     */
    private static void configureTooltips(Gallery gallery) {
        // Disable native tooltip
        gallery.setToolTipText(StringUtil.EMPTY_STRING);

        Listener tableListener = new TooltipMouseListener(gallery);
        gallery.addListener(SWT.Dispose, tableListener);
        gallery.addListener(SWT.KeyDown, tableListener);
        gallery.addListener(SWT.MouseMove, tableListener);
        gallery.addListener(SWT.MouseHover, tableListener);
    }

    /**
     * Displays a tooltip when the mouse hovers over a GalleryItem.
     * 
     * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
     *
     */
    private static class TooltipMouseListener implements Listener {
        Shell tip;

        Label label;

        private Gallery gallery;

        TooltipMouseListener(Gallery gallery) {
            this.gallery = gallery;

        }

        @Override
        public void handleEvent(Event event) {
            switch (event.type) {
            case SWT.Dispose:
            case SWT.KeyDown:
            case SWT.MouseMove:
                if (tip == null) {
                    break;
                }
                tip.dispose();
                tip = null;
                label = null;
                break;
            case SWT.MouseHover:
                GalleryItem item = gallery.getItem(new Point(event.x, event.y));
                if (item != null) {
                    if (tip != null && !tip.isDisposed()) {
                        tip.dispose();
                    }

                    Shell shell = gallery.getShell();
                    Display display = shell.getDisplay();
                    tip = new Shell(shell, SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
                    tip.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                    FillLayout layout = new FillLayout();
                    layout.marginWidth = 2;
                    tip.setLayout(layout);
                    label = new Label(tip, SWT.NONE);
                    label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                    label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                    label.setData("_TABLEITEM", item); //$NON-NLS-1$
                    label.setText(item.getText());
                    Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);

                    Point pt = gallery.toDisplay(event.x, event.y + 16);
                    tip.setBounds(pt.x, pt.y, size.x, size.y);
                    tip.setVisible(true);
                }
                break;
            default:
                break;
            }
        }
    }
}
