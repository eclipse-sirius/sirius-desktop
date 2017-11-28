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
package org.eclipse.sirius.common.ui.tools.api.profiler.view;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * The label provider of a
 * {@link org.eclipse.sirius.common.tools.api.profiler.ProfilerTask}.
 * 
 * @author ymortier
 */
public class TimeProfilerTreeViewLabelProvider extends LabelProvider implements ITableLabelProvider, IColorProvider {

    /** The index of the category column. */
    public static final int CATEGORY_COL = 0;

    /** The index of the task name column. */
    public static final int TASK_NAME_COL = 1;

    /** The index of the time column. */
    public static final int TIME_COL = 2;

    /** The index of the occurences column. */
    public static final int OCCURENCES_COL = 3;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(final Object element, final int columnIndex) {

        Image image = null;

        if (element instanceof TimeProfilerViewItem) {
            final TimeProfilerViewItem item = (TimeProfilerViewItem) element;
            switch (columnIndex) {
            case CATEGORY_COL:
                image = item.getCategoryImage();
                break;
            case TASK_NAME_COL:
                image = item.getTaskImage();
                break;
            case TIME_COL:
                break;
            case OCCURENCES_COL:
                break;
            default:
                break;
            }
        }
        return image;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(final Object element, final int columnIndex) {

        String text = StringUtil.EMPTY_STRING;

        if (element instanceof TimeProfilerViewItem) {
            final TimeProfilerViewItem item = (TimeProfilerViewItem) element;
            switch (columnIndex) {
            case CATEGORY_COL:
                text = item.getTask().getCategory();
                break;
            case TASK_NAME_COL:
                text = item.getTask().getName();
                break;
            case TIME_COL:
                text = item.getTime().toString();
                break;
            case OCCURENCES_COL:
                text = item.getOccurences().toString();
                break;
            default:
                break;
            }
        }
        return text;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    public Color getBackground(final Object element) {
        return getColor(SWT.COLOR_LIST_BACKGROUND);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    public Color getForeground(final Object element) {
        return getColor(SWT.COLOR_LIST_FOREGROUND);
    }
    
    
    private static Color getColor(final int which) {
        Display display = Display.getCurrent();
        if (display != null)
            return display.getSystemColor(which);
        display = Display.getDefault();
        final Color[] result = new Color[1];
        display.syncExec(new Runnable() {
            public void run() {
                synchronized (result) {
                    result[0] = Display.getCurrent().getSystemColor(which);                 
                }
            }
        });
        synchronized (result) {
            return result[0];
        }
    }
}
