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
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.business.api.query.DCellQuery;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.internal.editor.DefaultFontStyler;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for all the DTable column (except the line header column).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableColumnLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {
    /** The key for the image which represents a checkbox checked. */
    public static final String CHECKED_IMAGE = "table/checked"; //$NON-NLS-1$

    /** The key for the image which represents a checkbox unchecked. */
    public static final String UNCHECKED_IMAGE = "table/unchecked"; //$NON-NLS-1$

    // The imageRegistry for the checkbox images
    private static ImageRegistry imageRegistry = new ImageRegistry();

    static {
        imageRegistry.put(CHECKED_IMAGE, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(CHECKED_IMAGE)));
        imageRegistry.put(UNCHECKED_IMAGE, ImageDescriptor.createFromURL((URL) TableUIPlugin.INSTANCE.getImage(UNCHECKED_IMAGE)));
    }

    /**
     * The index of the column (0 corresponding to the second Column, the first
     * being the line header)
     */
    // int columnIndex = -1;
    DColumn column;

    /**
     * Default constructor.
     * 
     * @param column
     *            The column of this provider
     */
    public DTableColumnLabelProvider(final DColumn column) {
        super();
        // this.columnIndex = columnIndex;
        this.column = column;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
     */
    @Override
    public Color getBackground(final Object element) {
        Color result = null;
        if (column != null) {
            Option<DCell> optionalCell = getDCell(element);
            Option<DTableElementStyle> styleToApply = null;
            if (optionalCell.some()) {
                styleToApply = new DCellQuery(optionalCell.get()).getBackgroundStyleToApply();
            } else if (element instanceof DLine) {
                styleToApply = TableHelper.getBackgroundStyleToApply((DLine) element, column);
            }
            if (styleToApply != null && styleToApply.some()) {
                final RGBValues rgb = styleToApply.get().getBackgroundColor();
                if (rgb != null) {
                    result = VisualBindingManager.getDefault().getColorFromRGBValues(rgb);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getFont(java.lang.Object)
     */
    @Override
    public Font getFont(final Object element) {
        if (column != null) {
            Option<DCell> optionalCell = getDCell(element);
            Option<DTableElementStyle> styleToApply = null;
            if (optionalCell.some()) {
                styleToApply = new DCellQuery(optionalCell.get()).getForegroundStyleToApply();
            } else if (element instanceof DLine) {
                styleToApply = TableHelper.getForegroundStyleToApply((DLine) element, column);
            }
            if (styleToApply != null && styleToApply.some()) {
                final int size = styleToApply.get().getLabelSize();
                List<FontFormat> labelFormat = new ArrayList<FontFormat>();
                if (styleToApply.get().getLabelFormat() != null) {
                    labelFormat = styleToApply.get().getLabelFormat();
                } else {
                    labelFormat.clear();
                }
                return VisualBindingManager.getDefault().getFontFromLabelFormatAndSize(labelFormat, size);
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(final Object element) {
        if (column != null) {
            Option<DCell> optionalCell = getDCell(element);
            Option<DTableElementStyle> styleToApply = null;
            if (optionalCell.some()) {
                styleToApply = new DCellQuery(optionalCell.get()).getForegroundStyleToApply();
            } else if (element instanceof DLine) {
                styleToApply = TableHelper.getForegroundStyleToApply((DLine) element, column);
            }
            if (styleToApply != null && styleToApply.some()) {
                final RGBValues rgb = styleToApply.get().getForegroundColor();
                if (rgb != null) {
                    return VisualBindingManager.getDefault().getColorFromRGBValues(rgb);
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        if (column != null) {
            // Test if the type of this column is a Boolean
            DLine line = null;
            if (element instanceof DCell) {
                line = ((DCell) element).getLine();
            } else if (element instanceof DLine) {
                line = (DLine) element;
            }
            final EClassifier eClassifier = TableHelper.getEClassifier(line, column);
            if (eClassifier instanceof EDataType && ("Boolean".equals(((EDataType) eClassifier).getName()) || "EBoolean".equals(((EDataType) eClassifier).getName()))) { //$NON-NLS-1$ //$NON-NLS-2$
                Option<DCell> optionalCell = TableHelper.getCell(line, column);
                if (optionalCell.some()) {
                    return getImage(Boolean.parseBoolean(optionalCell.get().getLabel()));
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        String result = StringUtil.EMPTY_STRING;
        Option<DCell> optionalCell = getDCell(element);
        if (optionalCell.some()) {
            result = optionalCell.get().getLabel();
        }
        return result;
    }

    /**
     * Get the cell.
     * 
     * @param element
     *            the DLine or DCell in which to look.
     * @return an optional cell
     */
    protected Option<DCell> getDCell(final Object element) {
        Option<DCell> optionalCell = Options.newNone();
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            optionalCell = TableHelper.getCell(line, column);
        } else if (element instanceof DCell) {
            optionalCell = Options.newSome((DCell) element);
        }
        return optionalCell;
    }

    /**
     * Returns the image with the given key, or <code>null</code> if not found.
     */
    private Image getImage(final boolean isSelected) {
        final String key = isSelected ? CHECKED_IMAGE : UNCHECKED_IMAGE;
        return imageRegistry.get(key);
    }

    /**
     * Test if the currentColumn corresponds to the column of this
     * LabelProvider.
     * 
     * @param currentColumn
     *            The column to test
     * @return true is the currentColumn corresponds to the column of this
     *         LabelProvider, false otherwise.
     */
    public boolean isProvideColumn(final DColumn currentColumn) {
        return this.column.equals(currentColumn);
    }

    @Override
    public StyledString getStyledText(Object element) {
        String text = getText(element);
        DefaultFontStyler styler = new DefaultFontStyler(getFont(element), getForeground(element), getBackground(element), getUnderline(element), getStrikeout(element));
        if (text == null) {
            text = ""; //$NON-NLS-1$
        }
        StyledString styledString = new StyledString(text, styler);

        return styledString;
    }

    private boolean getStrikeout(Object element) {
        if (column != null) {
            Option<DCell> optionalCell = getDCell(element);
            Option<DTableElementStyle> styleToApply = null;
            if (optionalCell.some()) {
                styleToApply = new DCellQuery(optionalCell.get()).getForegroundStyleToApply();
            } else if (element instanceof DLine) {
                styleToApply = TableHelper.getForegroundStyleToApply((DLine) element, column);
            }
            if (styleToApply != null && styleToApply.some()) {
                List<FontFormat> labelFormat = styleToApply.get().getLabelFormat();
                if (labelFormat != null) {
                    return labelFormat.contains(FontFormat.STRIKE_THROUGH_LITERAL);
                }
            }
        }
        return false;
    }

    private boolean getUnderline(Object element) {
        if (column != null) {
            Option<DCell> optionalCell = getDCell(element);
            Option<DTableElementStyle> styleToApply = null;
            if (optionalCell.some()) {
                styleToApply = new DCellQuery(optionalCell.get()).getForegroundStyleToApply();
            } else if (element instanceof DLine) {
                styleToApply = TableHelper.getForegroundStyleToApply((DLine) element, column);
            }
            if (styleToApply != null && styleToApply.some()) {
                List<FontFormat> labelFormat = styleToApply.get().getLabelFormat();
                if (labelFormat != null) {
                    return labelFormat.contains(FontFormat.UNDERLINE_LITERAL);
                }
            }
        }
        return false;
    }
}
