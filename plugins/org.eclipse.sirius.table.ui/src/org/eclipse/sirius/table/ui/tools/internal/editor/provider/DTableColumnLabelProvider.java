/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
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
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

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

    /** Default background color: constant as VSM only supports white background. */
    private static final RGB DEFAULT_BG_COLOR = new RGB(255, 255, 255);
    
    /**
     * The index of the column (0 corresponding to the second Column, the first
     * being the line header)
     */
    DColumn column;
    
    private FontData defaultFont;

    /**
     * Default constructor.
     * 
     * @param column
     *            The column of this provider
     */
    public DTableColumnLabelProvider(final DColumn column) {
        this.column = column;
    }

    private DTableElementStyle getFgStyleToApply(Object element) {
        Optional<DCell> cell = getDCell(element);
        DTableElementStyle result = null;
        
        if (cell.isPresent()) {
            result = new DCellQuery(cell.get()).getForegroundStyleToApply().get();
        } else if (element instanceof DLine) {
            result = TableHelper.getForegroundStyleToApply((DLine) element, column).get();
        }
        return result;
    }
    
    private DTableElementStyle getBgStyleToApply(Object element) {
        Optional<DCell> cell = getDCell(element);
        DTableElementStyle result = null;
        
        if (cell.isPresent()) {
            result = new DCellQuery(cell.get()).getBackgroundStyleToApply().get();
        } else if (element instanceof DLine) {
            result = TableHelper.getBackgroundStyleToApply((DLine) element, column).get();
        }
        return result;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getBackground(java.lang.Object)
     */
    @Override
    public Color getBackground(final Object element) {
        Color result = null;
        
        DTableElementStyle styleToApply = getBgStyleToApply(element);
        if (styleToApply != null) {
            final RGBValues rgb = styleToApply.getBackgroundColor();
            if (rgb != null) {
                result = VisualBindingManager.getDefault().getColorFromRGBValues(rgb);
            }
        }
        // When provided, background color of Label Provider preempts selection highlight color.
        // When background color match default widget color,
        // display is disturbing for user as selection highlight color is missing.
        if (result != null && DEFAULT_BG_COLOR.equals(result.getRGB())) {
            result = null;
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
        Font result = null;
        DTableElementStyle styleToApply = getFgStyleToApply(element);
        if (styleToApply != null) {
            
            List<FontFormat> labelFormat = getFontFormat(styleToApply);
            int size = getFontSize(styleToApply);
            
            if (defaultFont == null) {
                result = VisualBindingManager.getDefault()
                        .getFontFromLabelFormatAndSize(labelFormat, size);
            } else {
                result = VisualBindingManager.getDefault()
                        .getFontFromLabelFormatAndSize(labelFormat, size, defaultFont.getName());
            }
        }
        
        return result;
    }

    private static List<FontFormat> getFontFormat(DTableElementStyle styleToApply) {
        List<FontFormat> labelFormat = Collections.emptyList();
        if (styleToApply.getLabelFormat() != null) {
            labelFormat = styleToApply.getLabelFormat();
        }
        return labelFormat;
    }

    private int getFontSize(DTableElementStyle styleToApply) {
        int size = styleToApply.getLabelSize();
        if (size == -1) {
            if (defaultFont != null) {
                size = defaultFont.getHeight();
            } else {
                // Legacy value. Usually default font is provided.
                size = 8;
            }
        }
        return size;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getForeground(java.lang.Object)
     */
    @Override
    public Color getForeground(final Object element) {
        DTableElementStyle styleToApply = getFgStyleToApply(element);
        if (styleToApply != null) {
            final RGBValues rgb = styleToApply.getForegroundColor();
            if (rgb != null) {
                return VisualBindingManager.getDefault().getColorFromRGBValues(rgb);
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
        // Test if the type of this column is a Boolean
        DLine line = null;
        if (element instanceof DCell) {
            line = ((DCell) element).getLine();
        } else if (element instanceof DLine) {
            line = (DLine) element;
        }
        if (isBooleanColumn(line)) {
            Optional<DCell> cell = getDCell(line);
            if (cell.isPresent()) {
                return getBooleanImage(Boolean.parseBoolean(cell.get().getLabel()));
            }
        }
        
        return null;
    }

    private boolean isBooleanColumn(DLine line) {
        EStructuralFeature feature = TableHelper.getEStructuralFeature(line, column);
        final EClassifier eClassifier = TableHelper.getEClassifier(line, column);
        // We do not display the check box for multi-valued feature.
        boolean isNotMany = feature != null && !feature.isMany();
        
        return isNotMany && eClassifier instanceof EDataType // Only pure value
                && ("Boolean".equals(eClassifier.getName()) //$NON-NLS-1$
                        || "EBoolean".equals(eClassifier.getName())); //$NON-NLS-1$
    }
    
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        String result = StringUtil.EMPTY_STRING;
        Optional<DCell> cell = getDCell(element);
        if (cell.isPresent()) {
            result = cell.get().getLabel();
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
    protected Optional<DCell> getDCell(final Object element) {
        DCell result = null;
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            result = TableHelper.getCell(line, column).get();
        } else if (element instanceof DCell) {
            result = (DCell) element;
        }
        return Optional.ofNullable(result);
    }

    /**
     * Returns the image for a boolean according provided value.
     * 
     * @param isSelected value of cell
     * @return image or checked or un-checked
     */
    private Image getBooleanImage(final boolean isSelected) {
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
        if (text == null) {
            text = ""; //$NON-NLS-1$
        }
        
        DTableElementStyle styleToApply = getFgStyleToApply(element);
        DefaultFontStyler styler = new DefaultFontStyler(getFont(element), 
                getForeground(element), getBackground(element),
                isFormat(styleToApply, FontFormat.UNDERLINE_LITERAL),
                isFormat(styleToApply, FontFormat.STRIKE_THROUGH_LITERAL));

        return new StyledString(text, styler);
    }

    
    private boolean isFormat(DTableElementStyle styleToApply, FontFormat format) {
        return styleToApply != null
                && styleToApply.getLabelFormat() != null
                && styleToApply.getLabelFormat().contains(format);
    }

    
    /**
     * Sets the default font of the style provider.
     * <p>
     * Table description does not provide font in style.
     * </p>
     * 
     * @param defaultFont of column
     */
    public void setDefaultFont(Font defaultFont) {
        if (defaultFont != null) {            
            this.defaultFont = defaultFont.getFontData()[0];
        } else {
            this.defaultFont = null;
        }
    }
}
