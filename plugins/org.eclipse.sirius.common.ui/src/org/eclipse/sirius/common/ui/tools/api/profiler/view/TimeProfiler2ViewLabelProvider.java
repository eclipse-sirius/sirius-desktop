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

import java.util.Map;

import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler2.CompositeTask;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.ImageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Maps;

/**
 * Label provider for the
 * {@link org.eclipse.sirius.common.tools.api.profiler.TimeProfiler2}.
 * 
 * @author ymortier
 */
public class TimeProfiler2ViewLabelProvider extends LabelProvider implements ITableLabelProvider, IFontProvider {

    /** The index of the category column. */
    public static final int CATEGORY_COL = 0;

    /** The index of the task name column. */
    public static final int TASK_NAME_COL = 1;

    /** The index of the time column. */
    public static final int TIME_COL_MS = 2;

    /** The index of the occurences column. */
    public static final int OCCURENCES_COL = 3;

    /** The index of the minimum time. */
    private static final int MINIMUM = 4;

    /** The index of the maximum time. */
    private static final int MAXIMUM = 5;

    /** The index of the average time. */
    private static final int AVERAGE = 6;

    private Map<Boolean, Font> fontCache = Maps.newHashMap();

    @Override
    public Image getColumnImage(final Object element, final int columnIndex) {
        Image image = null;
        if (element instanceof CompositeTask) {
            final CompositeTask item = (CompositeTask) element;
            switch (columnIndex) {
            case CATEGORY_COL:
                if (item.getProfilerTask().getCategoryImage() != null) {
                    image = ImageProvider.getImageFromPath(item.getProfilerTask().getCategoryImage());
                }
                break;
            case TASK_NAME_COL:
                if (item.getProfilerTask().getTaskImage() != null) {
                    image = ImageProvider.getImageFromPath(item.getProfilerTask().getTaskImage());
                }
                break;
            case TIME_COL_MS:
                break;
            case OCCURENCES_COL:
                break;
            default:
                break;
            }
        }
        return image;
    }

    @Override
    public String getColumnText(final Object element, final int columnIndex) {
        String text = StringUtil.EMPTY_STRING;
        if (element instanceof CompositeTask) {
            final CompositeTask item = (CompositeTask) element;
            switch (columnIndex) {
            case CATEGORY_COL:
                text = item.getProfilerTask().getCategory();
                break;
            case TASK_NAME_COL:
                text = item.getProfilerTask().getName();
                break;
            case TIME_COL_MS:
                text = Long.valueOf(item.getEllapsedTime()).toString();
                break;
            case OCCURENCES_COL:
                text = Integer.valueOf(item.getOccurences()).toString();
                break;
            case MINIMUM:
                text = Long.valueOf(item.getMin()).toString();
                break;
            case MAXIMUM:
                text = Long.valueOf(item.getMax()).toString();
                break;
            case AVERAGE:
                text = Long.valueOf((long) ((double) item.getEllapsedTime() / (double) item.getOccurences())).toString();
                break;
            default:
                break;
            }
        }
        return text;
    }

    @Override
    public Font getFont(final Object element) {
        if (element instanceof CompositeTask) {
            final CompositeTask task = (CompositeTask) element;
            return getFontFromValue(task.getParent() == null);
        }
        return null;
    }
    
    /**
     * This method helps avoiding memory leaks by keeping track of the already
     * built fonts.
     * 
     * @param bold
     *            : bold of the font
     * @return the default font with the given bold value.
     */
    private Font getFontFromValue(final boolean bold) {
        if (!fontCache.containsKey(Boolean.valueOf(bold))) {
            fontCache.put(Boolean.valueOf(bold), new Font(Display.getDefault(), "ARIAL", 8, !bold ? SWT.NORMAL : SWT.BOLD)); //$NON-NLS-1$
        }
        return fontCache.get(Boolean.valueOf(bold));
    }
}
