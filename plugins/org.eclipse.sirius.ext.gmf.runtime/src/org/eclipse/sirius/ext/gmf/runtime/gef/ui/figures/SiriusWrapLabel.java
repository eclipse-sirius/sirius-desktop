/******************************************************************************
 * Copyright (c) 2002, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Obeo - Duplication to keep the same behavior as GMF 2.0.1
 ****************************************************************************/
//CHECKSTYLE:OFF
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures;

import java.lang.ref.WeakReference;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.internal.mapmode.IMapModeHolder;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;

/**
 * An extended label that has the following extra features:
 * 
 * 1- It is capable of showing selection and focus feedback (primary or
 * secondary) 2- It is capable of optionally underlining the label's text 3- It
 * is capable of wrapping the label's text at a given width with a given
 * alignment 4- It is capable of supporting multiple label icons (temporary
 * feature)
 * 
 * This class was originally deriving off Draw2d's <code>Label</code> class but
 * with the introduction of the auto-wrapping feature, a copy had to be made
 * overriding was not straightforward. Hopefully, this extended version can be
 * pushed to opensource
 * 
 * <p>
 * Code taken from Eclipse reference bugzilla #98820
 * 
 * <p>
 * Since GMF 2.1, there is a lot of refactoring and regresion (See
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=274859). So to keep
 * compatibility we duplicate the class
 * org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel into SiriusWrapLabel to
 * keep the same behavior as GMF 2.0.1 There is only a little change in
 * paintText figure (see javadoc for more details).
 * 
 * @author melaasar
 */
@SuppressWarnings({ "rawtypes", "unchecked", "restriction" })
public class SiriusWrapLabel extends Figure implements PositionConstants {

    private static final String _ellipse = "..."; //$NON-NLS-1$

    private static final Dimension EMPTY_DIMENSION = new Dimension(0, 0);

    private static final Map mapModeConstantsMap = new WeakHashMap();

    private static class MapModeConstants {

        private static final int MAX_IMAGE_INFO = 12;

        public final WeakReference mapModeRef;

        public final int nDPtoLP_3;

        public final int nDPtoLP_2;

        public final int nDPtoLP_0;

        public final Dimension dimension_nDPtoLP_0;

        public final WeakHashMap fontToEllipseTextSize = new WeakHashMap();

        public final SingleIconInfo[] singleIconInfos = new SingleIconInfo[MAX_IMAGE_INFO];

        public MapModeConstants(IMapMode mapMode) {
            this.mapModeRef = new WeakReference(mapMode);
            nDPtoLP_2 = mapMode.DPtoLP(2);
            nDPtoLP_3 = mapMode.DPtoLP(3);
            nDPtoLP_0 = mapMode.DPtoLP(0);
            dimension_nDPtoLP_0 = new Dimension(nDPtoLP_0, nDPtoLP_0);
        }

        public Dimension getEllipseTextSize(Font f) {
            Dimension d = (Dimension) fontToEllipseTextSize.get(f);
            if (d == null) {
                d = FigureUtilities.getTextExtents(_ellipse, f);
                d.setHeight(FigureUtilities.getFontMetrics(f).getHeight());
                IMapMode mapMode = (IMapMode) mapModeRef.get();
                if (mapMode != null) {
                    d = new Dimension(mapMode.DPtoLP(d.width), mapMode.DPtoLP(d.height));
                }
                fontToEllipseTextSize.put(f, d);
            }
            return d;
        }

        public SingleIconInfo getSingleIconInfo(Image image) {
            if (image == null) {
                return SingleIconInfo.NULL_INFO;
            }
            SingleIconInfo info;
            for (int i = 0; i < MAX_IMAGE_INFO; ++i) {
                info = singleIconInfos[i];
                if (info == null) {
                    info = new SingleIconInfo(image);
                    singleIconInfos[i] = info;
                    return info;
                }
                if (info.icon == image) {
                    return info;
                }
            }
            int index = SingleIconInfo.count % MAX_IMAGE_INFO;
            info = new SingleIconInfo(image);
            singleIconInfos[index] = info;
            return info;
        }
    }

    // reserve 1 bit
    private static int FLAG_SELECTED = MAX_FLAG << 1;

    private static int FLAG_HASFOCUS = MAX_FLAG << 2;

    private static int FLAG_UNDERLINED = MAX_FLAG << 3;

    private static int FLAG_STRIKEDTHROUGH = MAX_FLAG << 4;

    private static int FLAG_WRAP = MAX_FLAG << 5;

    // reserve 3 bits
    private static int FLAG_TEXT_ALIGN = MAX_FLAG << 6;

    private static int FLAG_WRAP_ALIGN = MAX_FLAG << 9;

    private static int FLAG_ICON_ALIGN = MAX_FLAG << 12;

    private static int FLAG_LABEL_ALIGN = MAX_FLAG << 15;

    private static int FLAG_TEXT_PLACEMENT = MAX_FLAG << 18;

    private MapModeConstants mapModeConstants;

    /** the original label's text */
    private String text;

    /** the label's text used in painting after applying required styles */
    private String subStringText;

    /** the size of text */
    private Dimension textSize;

    private Dimension ellipseTextSize;

    /** the location of text */
    private Point textLocation;

    /** the cached hint used to calculate text size */
    private int cachedPrefSizeHint_width;

    private int cachedPrefSizeHint_height;

    /** the icon location */
    private Point iconLocation;

    private static abstract class IconInfo {
        /**
         * Gets the icon at the index location.
         * 
         * @param i
         *            the index to retrieve the icon of
         * @return <code>Image</code> that corresponds to the given index.
         */
        public abstract Image getIcon(int i);

        /**
         * Gets the icon size of the icon at the given index.
         * 
         * @param i
         * @return the <code>Dimension</code> that is the size of the icon at
         *         the given index.
         */
        public abstract Dimension getIconSize(IMapMode mapMode, int i);

        /**
         * @return the number of icons
         */
        public abstract int getNumberofIcons();

        /**
         * @return the <code>Dimension</code> that is the total size of all the
         *         icons.
         */
        public abstract Dimension getTotalIconSize(IMapMode mapMode);

        public abstract void invalidate();

        /**
         * Sets the icon at the index location.
         * 
         * @param icon
         * @param i
         */
        public abstract void setIcon(Image icon, int i);

        /**
         * 
         */
        public abstract int getMaxIcons();

    }

    private static class SingleIconInfo extends IconInfo {

        static int count;

        public static final SingleIconInfo NULL_INFO = new SingleIconInfo() {
            @Override
            public int getNumberofIcons() {
                return 0;
            }
        };

        final Image icon;

        /** total icon size */
        private Dimension totalIconSize;

        private SingleIconInfo() {
            icon = null;// don't increment count, used only for NULL_INFO
        }

        public SingleIconInfo(Image icon) {
            this.icon = icon;
            ++count;
        }

        @Override
        public final int getMaxIcons() {
            return 1;
        }

        @Override
        public Image getIcon(int i) {
            if (i == 0) {
                return icon;
            } else if (i > 0) {
                return null;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override
        public void setIcon(Image img, int i) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Dimension getIconSize(IMapMode mapMode, int i) {
            if (i == 0) {
                return getTotalIconSize(mapMode);
            }

            throw new IndexOutOfBoundsException();
        }

        @Override
        public int getNumberofIcons() {
            return 1;
        }

        @Override
        public Dimension getTotalIconSize(IMapMode mapMode) {
            if (totalIconSize != null)
                return totalIconSize;

            if (icon != null && !icon.isDisposed()) {
                org.eclipse.swt.graphics.Rectangle imgBounds = icon.getBounds();
                totalIconSize = new Dimension(mapMode.DPtoLP(imgBounds.width), mapMode.DPtoLP(imgBounds.height));
            } else {
                totalIconSize = EMPTY_DIMENSION;
            }

            return totalIconSize;
        }

        @Override
        public void invalidate() {
            totalIconSize = null;
        }

    }

    private static class MultiIconInfo extends IconInfo {

        /** the label icons */
        private ArrayList icons = new ArrayList(2);

        /** total icon size */
        private Dimension totalIconSize;

        public MultiIconInfo() {
            super();
        }

        @Override
        public int getMaxIcons() {
            return -1;
        }

        /**
         * Gets the icon at the index location.
         * 
         * @param i
         *            the index to retrieve the icon of
         * @return <code>Image</code> that corresponds to the given index.
         */
        @Override
        public Image getIcon(int i) {
            if (i >= icons.size())
                return null;

            return (Image) icons.get(i);
        }

        /**
         * Sets the icon at the index location.
         * 
         * @param icon
         * @param i
         */
        @Override
        public void setIcon(Image icon, int i) {
            int size = icons.size();
            if (i >= size) {
                for (int j = size; j < i; j++)
                    icons.add(null);
                icons.add(icon);
                icons.trimToSize();
            } else
                icons.set(i, icon);
        }

        /**
         * Gets the icon size of the icon at the given index.
         * 
         * @param i
         * @return the <code>Dimension</code> that is the size of the icon at
         *         the given index.
         */
        @Override
        public Dimension getIconSize(IMapMode mapMode, int i) {
            Image img = getIcon(i);
            if (img != null && !img.isDisposed()) {
                org.eclipse.swt.graphics.Rectangle imgBounds = img.getBounds();
                return new Dimension(mapMode.DPtoLP(imgBounds.width), mapMode.DPtoLP(imgBounds.height));
            }
            return EMPTY_DIMENSION;
        }

        /**
         * @return the number of icons
         */
        @Override
        public int getNumberofIcons() {
            return icons.size();
        }

        /**
         * @return the <code>Dimension</code> that is the total size of all the
         *         icons.
         */
        @Override
        public Dimension getTotalIconSize(IMapMode mapMode) {
            if (totalIconSize != null)
                return totalIconSize;
            int iconNum = getNumberofIcons();
            if (iconNum == 0) {
                return totalIconSize = EMPTY_DIMENSION;
            }

            totalIconSize = new Dimension();
            for (int i = 0; i < iconNum; i++) {
                Dimension iconSize = getIconSize(mapMode, i);
                totalIconSize.setWidth(totalIconSize.width + iconSize.width);
                if (iconSize.height > totalIconSize.height)
                    totalIconSize.setHeight(iconSize.height);
            }

            return totalIconSize;
        }

        /**
         * 
         */
        @Override
        public void invalidate() {
            totalIconSize = null;
        }
    }

    private IconInfo iconInfo;

    /** the cached hint used to calculate text size */
    private int cachedTextSizeHint_width;

    private int cachedTextSizeHint_height;

    /**
     * Construct an empty Label.
     * 
     * @since 0.9.0
     */
    public SiriusWrapLabel() {
        text = "";//$NON-NLS-1$
        // set defaults
        setAlignmentFlags(CENTER, FLAG_TEXT_ALIGN);
        setAlignmentFlags(CENTER, FLAG_ICON_ALIGN);
        setAlignmentFlags(CENTER, FLAG_LABEL_ALIGN);
        setAlignmentFlags(LEFT, FLAG_WRAP_ALIGN);
        setPlacementFlags(EAST, FLAG_TEXT_PLACEMENT);
    }

    /**
     * Construct a Label with passed String as its text.
     * 
     * @param s
     *            the label text
     * @since 0.9.0
     */
    public SiriusWrapLabel(String s) {
        if (s != null) {
            text = s;
        } else {
            text = "";//$NON-NLS-1$
        }
        // setBorder(new LineBorderEx(ColorConstants.red,3));
    }

    /**
     * Construct a Label with passed Image as its icon.
     * 
     * @param i
     *            the label image
     * @since 0.9.0
     */
    public SiriusWrapLabel(Image i) {
        text = "";//$NON-NLS-1$
        iconInfo = new SingleIconInfo(i);
    }

    /**
     * Construct a Label with passed String as text and passed Image as its
     * icon.
     * 
     * @param s
     *            the label text
     * @param i
     *            the label image
     * @since 0.9.0
     */
    public SiriusWrapLabel(String s, Image i) {
        if (s != null) {
            text = s;
        } else {
            text = "";//$NON-NLS-1$
        }
        iconInfo = new SingleIconInfo(i);
    }

    /**
     * @return <code>IMapMode</code> used by this figure. <code>IMapMode</code>
     *         that allows for the coordinate mapping from device to logical
     *         units.
     */
    private IMapMode getFigureMapMode() {
        return (IMapMode) getMapModeConstants().mapModeRef.get();
    }

    private MapModeConstants getMapModeConstants() {
        if (mapModeConstants == null) {
            IMapMode mapMode = MapModeUtil.getMapMode(this);
            while (mapMode instanceof IMapModeHolder) {
                mapMode = ((IMapModeHolder) mapMode).getMapMode();
            }
            mapModeConstants = (MapModeConstants) mapModeConstantsMap.get(mapMode);
            if (mapModeConstants == null) {
                mapModeConstants = new MapModeConstants(mapMode);
                mapModeConstantsMap.put(mapMode, mapModeConstants);
            }
        }
        return mapModeConstants;
    }

    private void alignOnHeight(Point loc, Dimension size, int alignment) {
        switch (alignment) {
        case TOP:
            loc.setY(getInsets().top);
            break;
        case BOTTOM:
            loc.setY(bounds.height - size.height - getInsets().bottom);
            break;
        default:
            loc.setY((bounds.height - size.height) / 2);
        }
    }

    private void alignOnWidth(Point loc, Dimension size, int alignment) {
        switch (alignment) {
        case LEFT:
            loc.setX(getInsets().left);
            break;
        case RIGHT:
            loc.setX(bounds.width - size.width - getInsets().right);
            break;
        default:
            loc.setX((bounds.width - size.width) / 2);
        }
    }

    private void calculateAlignment(Dimension iconSize, int textPlacement) {
        switch (textPlacement) {
        case EAST:
        case WEST:
            alignOnHeight(textLocation, getTextSize(), getTextAlignment());
            alignOnHeight(getIconLocation(), iconSize, getIconAlignment());
            break;
        case NORTH:
        case SOUTH:
            alignOnWidth(textLocation, getSubStringTextSize(), getTextAlignment());
            alignOnWidth(getIconLocation(), iconSize, getIconAlignment());
            break;
        default:
            break;
        }
    }

    /**
     * Calculates the size of the Label using the passed Dimension as the size
     * of the Label's text.
     * 
     * @param txtSize
     *            the precalculated size of the label's text
     * @return the label's size
     * @since 0.9.0
     */
    protected Dimension calculateLabelSize(Dimension txtSize) {
        Dimension iconSize = getTotalIconSize();
        boolean isEmpty = (iconSize.width == 0 && iconSize.height == 0);
        int len = getText().length();
        if (len == 0 && isEmpty) {
            return new Dimension(txtSize.width, txtSize.height);
        }
        int gap = (len == 0 || isEmpty) ? 0 : getIconTextGap();
        int placement = getTextPlacement();
        if (placement == WEST || placement == EAST) {
            return new Dimension(iconSize.width + gap + txtSize.width, Math.max(iconSize.height, txtSize.height));
        } else {
            return new Dimension(Math.max(iconSize.width, txtSize.width), iconSize.height + gap + txtSize.height);
        }
    }

    private void calculateLocations() {
        textLocation = new Point();
        iconLocation = new Point();
        Dimension iconSize = getTotalIconSize();
        int textPlacement = getTextPlacement();
        calculatePlacement(iconSize, textPlacement);
        calculateAlignment(iconSize, textPlacement);
        Rectangle r = getBounds();
        Dimension ps = getPreferredSize(r.width, r.height);
        int w = (r.width - ps.width) + (getTextSize().width - getSubStringTextSize().width);
        int h = r.height - ps.height;
        if (w == 0 && h == 0) {
            return;
        }

        Dimension offset = new Dimension(w, h);
        switch (getLabelAlignment()) {
        case LEFT:
            offset.scale(0.0f);
            break;
        case RIGHT:
            offset.scale(1.0f);
            break;
        case TOP:
            offset.setHeight(0);
            offset.scale(0.5f);
            break;
        case BOTTOM:
            offset.setHeight(offset.height * 2);
            offset.scale(0.5f);
            break;
        case CENTER:
        default:
            offset.scale(0.5f);
            break;
        }

        switch (textPlacement) {
        case EAST:
        case WEST:
            offset.setHeight(0);
            break;
        case NORTH:
        case SOUTH:
            offset.setWidth(0);
            break;
        default:
            break;
        }

        textLocation.translate(offset);
        iconLocation.translate(offset);
    }

    private void calculatePlacement(Dimension iconSize, int textPlacement) {
        int gap = (getText().length() == 0 || (iconSize.width == 0 && iconSize.height == 0)) ? 0 : getIconTextGap();
        Insets insets = getInsets();
        switch (textPlacement) {
        case EAST:
            iconLocation.setX(insets.left);
            textLocation.setX(iconSize.width + gap + insets.left);
            break;
        case WEST:
            textLocation.setX(insets.left);
            iconLocation.setX(getSubStringTextSize().width + gap + insets.left);
            break;
        case NORTH:
            textLocation.setY(insets.top);
            iconLocation.setY(getTextSize().height + gap + insets.top);
            break;
        case SOUTH:
            textLocation.setY(iconSize.height + gap + insets.top);
            iconLocation.setY(insets.top);
        default:
            break;
        }
    }

    /**
     * Calculates the size of the Label's text size. The text size calculated
     * takes into consideration if the Label's text is currently truncated. If
     * text size without considering current truncation is desired, use
     * {@link #calculateTextSize(int, int)}.
     * 
     * @return the size of the label's text, taking into account truncation
     * @since 0.9.0
     */
    protected Dimension calculateSubStringTextSize() {
        Font f = getFont();
        return getTextExtents(getSubStringText(), f, getFigureMapMode().DPtoLP(FigureUtilities.getFontMetrics(f).getHeight()));
    }

    /**
     * Calculates and returns the size of the Label's text. Note that this
     * Dimension is calculated using the Label's full text, regardless of
     * whether or not its text is currently truncated. If text size considering
     * current truncation is desired, use {@link #calculateSubStringTextSize()}.
     * 
     * @param wHint
     *            a width hint
     * @param hHint
     *            a height hint
     * @return the size of the label's text, ignoring truncation
     * @since 0.9.0
     */
    protected Dimension calculateTextSize(int wHint, int hHint) {
        Font f = getFont();
        return getTextExtents(getWrappedText(wHint, hHint), f, getFigureMapMode().DPtoLP(FigureUtilities.getFontMetrics(f).getHeight()));
    }

    private void clearLocations() {
        iconLocation = textLocation = null;
    }

    /**
     * Returns the Label's icon.
     * 
     * @return the label icon
     * @since 0.9.0
     */
    public Image getIcon() {
        return getIcon(0);
    }

    /**
     * Gets the label's icon at the given index
     * 
     * @param index
     *            The icon index
     * @return the <code>Image</code> that is the icon for the given index.
     */
    public Image getIcon(int index) {
        if (iconInfo == null)
            return null;
        return iconInfo.getIcon(index);
    }

    /**
     * Determines if there is any icons by checking if icon size is zeros.
     * 
     * @return true if icons are present, false otherwise
     */
    protected boolean hasIcons() {
        return (getNumberofIcons() > 0);
    }

    /**
     * Returns the current alignment of the Label's icon. The default is
     * {@link PositionConstants#CENTER}.
     * 
     * @return the icon alignment
     * @since 0.9.0
     */
    public int getIconAlignment() {
        return getAlignment(FLAG_ICON_ALIGN);
    }

    /**
     * Returns the bounds of the Label's icon.
     * 
     * @return the icon's bounds
     * @since 0.9.0
     */
    public Rectangle getIconBounds() {
        return new Rectangle(getBounds().getLocation().translate(getIconLocation()), getTotalIconSize());
    }

    /**
     * Returns the location of the Label's icon relative to the Label.
     * 
     * @return the icon's location
     * @since 0.9.0
     */
    protected Point getIconLocation() {
        if (iconLocation == null)
            calculateLocations();
        return iconLocation;
    }

    /**
     * Returns the gap in pixels between the Label's icon and its text.
     * 
     * @return the gap
     * @since 0.9.0
     */
    public int getIconTextGap() {
        return getMapModeConstants().nDPtoLP_3;
    }

    /**
     * @see IFigure#getMinimumSize(int, int)
     */
    @Override
    public Dimension getMinimumSize(int w, int h) {
        if (minSize != null)
            return minSize;
        minSize = new Dimension();
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null)
            minSize.setSize(layoutManager.getMinimumSize(this, w, h));
        Font f = getFont();
        Dimension d = getEllipseTextSize().getIntersected(getTextExtents(getText(), f, getFigureMapMode().DPtoLP(FigureUtilities.getFontMetrics(f).getHeight())));

        Dimension labelSize = calculateLabelSize(d);
        Insets insets = getInsets();
        labelSize.expand(insets.getWidth(), insets.getHeight());
        minSize.union(labelSize);
        return minSize;
    }

    @Override
    public Dimension getPreferredSize(int wHint, int hHint) {
        if (prefSize == null || wHint != cachedPrefSizeHint_width || hHint != cachedPrefSizeHint_height) {
            prefSize = calculateLabelSize(getTextSize(wHint, hHint));
            Insets insets = getInsets();
            prefSize.expand(insets.getWidth(), insets.getHeight());
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager != null) {
                prefSize.union(layoutManager.getPreferredSize(this, wHint, hHint));
            }
            prefSize.union(getMinimumSize(wHint, hHint));
            cachedPrefSizeHint_width = wHint;
            cachedPrefSizeHint_height = hHint;
        }
        return prefSize;
    }

    @Override
    public Dimension getMaximumSize() {
        // this assumes that getPreferredSize(wHint, hHint) is called before
        return prefSize;
    }

    /**
     * Calculates the amount of the Label's current text will fit in the Label,
     * including an elipsis "..." if truncation is required.
     * 
     * @return the substring
     * @since 0.9.0
     */
    public String getSubStringText() {
        if (subStringText != null)
            return subStringText;

        String theText = getText();
        int textLen = theText.length();
        if (textLen == 0) {
            return subStringText = "";//$NON-NLS-1$
        }
        Dimension size = getSize();
        Dimension shrink = getPreferredSize(size.width, size.height).getShrinked(size);
        Dimension effectiveSize = getTextSize().getExpanded(-shrink.width, -shrink.height);

        if (effectiveSize.height == 0) {
            return subStringText = "";//$NON-NLS-1$
        }

        Font f = getFont();
        FontMetrics metrics = FigureUtilities.getFontMetrics(f);
        IMapMode mm = getFigureMapMode();
        int fontHeight = mm.DPtoLP(metrics.getHeight());
        int charAverageWidth = mm.DPtoLP(metrics.getAverageCharWidth());
        int maxLines = (int) (effectiveSize.height / (double) fontHeight);
        if (maxLines == 0) {
            return subStringText = "";//$NON-NLS-1$
        }

        StringBuffer accumlatedText = new StringBuffer();
        StringBuffer remainingText = new StringBuffer(theText);

        int effectiveSizeWidth = effectiveSize.width;
        int widthHint = Math.max(effectiveSizeWidth - getEllipseTextSize().width, 0);
        int i = 0, j = 0;
        while (remainingText.length() > 0 && j++ < maxLines) {
            i = getLineWrapPosition(remainingText.toString(), f, effectiveSizeWidth, fontHeight);

            if (accumlatedText.length() > 0)
                accumlatedText.append('\n');

            if (i == 0 || (remainingText.length() > i && j == maxLines)) {
                i = getLargestSubstringConfinedTo(remainingText.toString(), f, widthHint, fontHeight, charAverageWidth);
                accumlatedText.append(remainingText.substring(0, i));
                accumlatedText.append(getEllipse());
            } else
                accumlatedText.append(remainingText.substring(0, i));
            remainingText.delete(0, i);
        }
        return subStringText = accumlatedText.toString();
    }

    /**
     * Creates an equivalent text to that of the label's but with "\n"(s)
     * inserted at the wrapping positions. This method assumes unlimited
     * bounding box and is used by <code>calculateTextSize()</code> to calculate
     * the perfect size of the text with wrapping
     * 
     * @return the wrapped text
     */
    private String getWrappedText(int wHint, int hHint) {
        String theText = getText();
        if (wHint == -1 || theText.length() == 0 || !isTextWrapped())
            return theText;

        Dimension iconSize = getTotalIconSize();
        if (!(iconSize.width == 0 && iconSize.height == 0)) {
            switch (getTextPlacement()) {
            case EAST:
            case WEST:
                wHint -= iconSize.width + getIconTextGap();
                break;
            case NORTH:
            case SOUTH:
                if (hHint != -1)
                    hHint -= iconSize.height + getIconTextGap();
                break;
            default:
                break;
            }
        }

        if ((hHint == 0) || (wHint == 0)) {
            return "";//$NON-NLS-1$
        }

        Font f = getFont();
        int fontHeight = getFigureMapMode().DPtoLP(FigureUtilities.getFontMetrics(f).getHeight());
        int maxLines = Integer.MAX_VALUE;
        if (hHint != -1) {
            maxLines = (int) (hHint / (double) fontHeight);
            if (maxLines == 0) {
                return "";//$NON-NLS-1$
            }
        }

        StringBuffer accumlatedText = new StringBuffer();
        StringBuffer remainingText = new StringBuffer(theText);
        int i = 0, j = 0;

        while (remainingText.length() > 0 && j++ < maxLines) {
            if ((i = getLineWrapPosition(remainingText.toString(), f, wHint, fontHeight)) == 0)
                break;

            if (accumlatedText.length() > 0)
                accumlatedText.append('\n');
            accumlatedText.append(remainingText.substring(0, i));
            remainingText.delete(0, i);
        }
        return accumlatedText.toString();
    }

    /**
     * Returns the size of the Label's current text. If the text is currently
     * truncated, the truncated text with its ellipsis is used to calculate the
     * size.
     * 
     * @return the size of this label's text, taking into account truncation
     * @since 0.9.0
     */
    protected Dimension getSubStringTextSize() {
        return calculateSubStringTextSize();
    }

    /**
     * Returns the size of the String constant "..." the ellipse based on the
     * currently used Map mode size.
     * 
     * @return the size of ellipse text
     * 
     */
    private Dimension getEllipseTextSize() {
        if (ellipseTextSize == null) {
            ellipseTextSize = getMapModeConstants().getEllipseTextSize(getFont());
        }
        return ellipseTextSize;
    }

    /**
     * Returns the text of the label. Note that this is the complete text of the
     * label, regardless of whether it is currently being truncated. Call
     * {@link #getSubStringText()}to return the label's current text contents
     * with truncation considered.
     * 
     * @return the complete text of this label
     * @since 0.9.0
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the current alignment of the Label's text. The default text
     * alignment is {@link PositionConstants#CENTER}.
     * 
     * @return the text alignment
     */
    public int getTextAlignment() {
        return getAlignment(FLAG_TEXT_ALIGN);
    }

    /**
     * Returns the current alignment of the entire Label. The default label
     * alignment is {@link PositionConstants#LEFT}.
     * 
     * @return the label alignment
     */
    private int getLabelAlignment() {
        return getAlignment(FLAG_LABEL_ALIGN);
    }

    /**
     * Returns the bounds of the label's text. Note that the bounds are
     * calculated using the label's complete text regardless of whether the
     * label's text is currently truncated.
     * 
     * @return the bounds of this label's complete text
     * @since 0.9.0
     */
    public Rectangle getTextBounds() {
        return new Rectangle(getBounds().getLocation().translate(getTextLocation()), getTextSize());
    }

    /**
     * Returns the location of the label's text relative to the label.
     * 
     * @return the text location
     * @since 0.9.0
     */
    protected Point getTextLocation() {
        if (textLocation != null)
            return textLocation;
        calculateLocations();
        return textLocation;
    }

    /**
     * Returns the current placement of the label's text relative to its icon.
     * The default text placement is {@link PositionConstants#EAST}.
     * 
     * @return the text placement
     * @since 0.9.0
     */
    public int getTextPlacement() {
        return getPlacement(FLAG_TEXT_PLACEMENT);
    }

    /**
     * Returns the size of the label's complete text. Note that the text used to
     * make this calculation is the label's full text, regardless of whether the
     * label's text is currently being truncated and is displaying an ellipsis.
     * If the size considering current truncation is desired, call
     * {@link #getSubStringTextSize()}.
     * 
     * @param wHint
     *            a width hint
     * @param hHint
     *            a height hint
     * @return the size of this label's complete text
     * @since 0.9.0
     */
    protected Dimension getTextSize(int wHint, int hHint) {
        if (textSize == null || wHint != cachedTextSizeHint_width || hHint != cachedTextSizeHint_height) {
            textSize = calculateTextSize(wHint, hHint);
            cachedTextSizeHint_width = wHint;
            cachedTextSizeHint_height = hHint;
        }
        return textSize;
    }

    /**
     * Gets the text size given the current size as a width hint
     */
    private final Dimension getTextSize() {
        Rectangle r = getBounds();
        return getTextSize(r.width, r.height);
    }

    /**
     * @see IFigure#invalidate()
     */
    @Override
    public void invalidate() {
        prefSize = null;
        minSize = null;
        clearLocations();
        ellipseTextSize = null;
        textSize = null;
        subStringText = null;
        if (iconInfo != null)
            iconInfo.invalidate();
        super.invalidate();
    }

    /**
     * Returns <code>true</code> if the label's text is currently truncated and
     * is displaying an ellipsis, <code>false</code> otherwise.
     * 
     * @return <code>true</code> if the label's text is truncated
     * @since 0.9.0
     */
    public boolean isTextTruncated() {
        return !getSubStringTextSize().equals(getTextSize());
    }

    /**
     * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    public void paintFigure(Graphics graphics) {
        if (isSelected()) {
            graphics.pushState();
            graphics.setBackgroundColor(ColorConstants.menuBackgroundSelected);
            graphics.fillRectangle(getSelectionRectangle());
            graphics.popState();
            graphics.setForegroundColor(ColorConstants.white);
        }
        if (hasFocus()) {
            graphics.pushState();
            graphics.setXORMode(true);
            graphics.setForegroundColor(ColorConstants.menuBackgroundSelected);
            graphics.setBackgroundColor(ColorConstants.white);
            graphics.drawFocus(getSelectionRectangle().resize(-1, -1));
            graphics.popState();
        }
        if (isOpaque())
            super.paintFigure(graphics);
        Rectangle figBounds = getBounds();

        graphics.translate(figBounds.x, figBounds.y);
        if (hasIcons())
            paintIcons(graphics);

        String subString = getSubStringText();
        if (subString.length() > 0) {
            if (!isEnabled()) {
                graphics.translate(1, 1);
                graphics.setForegroundColor(ColorConstants.buttonLightest);
                paintText(graphics, subString);
                graphics.translate(-1, -1);
                graphics.setForegroundColor(ColorConstants.buttonDarker);
            } else {
                paintText(graphics, subString);
            }
        }
        graphics.translate(-figBounds.x, -figBounds.y);
    }

    /**
     * Paints the text and optionally underlines it. <BR>
     * The offset added to avoid truncating at the top make a truncating at the
     * bottom so to avoid the bottom truncating problem we remove this fix.
     * 
     * @param graphics
     *            The graphics context
     * @param subString
     *            The string to draw
     */
    private void paintText(Graphics graphics, String subString) {
        StringTokenizer tokenizer = new StringTokenizer(subString, "\n"); //$NON-NLS-1$
        Font f = getFont();
        FontMetrics fontMetrics = FigureUtilities.getFontMetrics(f);
        int fontHeight = getFigureMapMode().DPtoLP(fontMetrics.getHeight());
        int fontHeightHalf = fontHeight / 2;
        int textWidth = getTextExtents(subString, f, fontHeight).width;
        Point p = getTextLocation();
        int y = p.y;
        int x = p.x;
        final int wrapAlignment = getTextWrapAlignment();
        boolean isUnderlined = isTextUnderlined();
        boolean isStrikedThrough = isTextStrikedThrough();
        Rectangle clipRect = new Rectangle();
        graphics.getClip(clipRect);
        int clipRectTopRight_x = clipRect.getTopRight().x;
        // If the font's leading area is 0 then we need to add an offset to
        // avoid truncating at the top (e.g. Korean fonts)
        // if (0 == fontMetrics.getLeading()) {
        // y += getMapModeConstants().nDPtoLP_2; // 2 is the leading area for
        // default English
        // }

        while (tokenizer.hasMoreTokens()) {
            x = p.x;
            String token = tokenizer.nextToken();
            int tokenWidth = getTextExtents(token, f, fontHeight).width;

            switch (wrapAlignment) {
            case CENTER:
                x += (textWidth - tokenWidth) / 2;
                break;
            case RIGHT:
                x += textWidth - tokenWidth;
                break;
            default:
                break;
            }

            // increase the clipping rectangle by a small amount to account for
            // font overhang
            // from italic / irregular characters etc.

            if (tokenWidth + x <= clipRectTopRight_x) {
                Rectangle newClipRect = new Rectangle(clipRect);
                newClipRect.setWidth(newClipRect.width + (tokenWidth / token.length()) / 2);
                graphics.setClip(newClipRect);
            }

            graphics.drawText(token, x, y);
            graphics.setClip(clipRect);

            y += fontHeight;

            if (isUnderlined) {
                graphics.setLineWidth(1);
                graphics.drawLine(x, y - 1, x + tokenWidth, y - 1);
            }
            if (isStrikedThrough) {
                graphics.setLineWidth(1);
                graphics.drawLine(x, y - fontHeightHalf - 1, x + tokenWidth, y - fontHeightHalf - 1);
            }
        }
    }

    /**
     * Paints the icon(s)
     * 
     * @param graphics
     *            The graphics context
     */
    private void paintIcons(Graphics graphics) {
        Point p = Point.SINGLETON;
        p.setLocation(getIconLocation());

        int num = getNumberofIcons();
        for (int i = 0; i < num; i++) {
            Image icon = getIcon(i);
            if (icon != null) {
                graphics.drawImage(icon, p);
                p.setX(p.x + getIconSize(i).width);
            }
        }
    }

    /**
     * Sets the label's icon to the passed image.
     * 
     * @param image
     *            the new label image
     * @since 0.9.0
     */
    public void setIcon(Image image) {
        setIcon(image, 0);
    }

    /**
     * Sets the label's icon at given index
     * 
     * @param image
     *            The icon image or null to remove the icon
     * @param index
     *            The icon index
     */
    public void setIcon(Image image, int index) {
        if (iconInfo == null) {
            if (index == 0) {
                iconInfo = getMapModeConstants().getSingleIconInfo(image);
            } else {
                iconInfo = new MultiIconInfo();
                iconInfo.setIcon(image, index);
            }
            revalidate();
            repaint();// Call repaint, in case the image dimensions are the
                      // same.
        } else if (iconInfo.getIcon(index) != image) {
            if (iconInfo.getMaxIcons() == 1) {
                if (index == 0) {
                    iconInfo = getMapModeConstants().getSingleIconInfo(image);
                    revalidate();
                    repaint();// Call repaint, in case the image dimensions are
                              // the same.
                    return;
                }
                IconInfo oldIconInfo = iconInfo;
                iconInfo = new MultiIconInfo();
                iconInfo.setIcon(oldIconInfo.getIcon(0), 0);
            }
            iconInfo.setIcon(image, index);
            revalidate();
            repaint();// Call repaint, in case the image dimensions are the
                      // same.
        }
    }

    /**
     * Sets the icon alignment relative to the .abel's alignment to the passed
     * value. The default is {@link PositionConstants#CENTER}. Other possible
     * values are {@link PositionConstants#TOP},
     * {@link PositionConstants#BOTTOM},{@link PositionConstants#LEFT}and
     * {@link PositionConstants#RIGHT}.
     * 
     * @param align
     *            the icon alignment
     * @since 0.9.0
     */
    public void setIconAlignment(int align) {
        if (getIconAlignment() == align)
            return;
        setAlignmentFlags(align, FLAG_ICON_ALIGN);
        clearLocations();
        repaint();
    }

    /**
     * getIconSize
     * 
     * @param index
     *            of icon to retrieve size of.
     * @return Dimension representing the icon size.
     */
    protected Dimension getIconSize(int index) {
        if (iconInfo == null)
            return EMPTY_DIMENSION;
        return iconInfo.getIconSize(getFigureMapMode(), index);
    }

    /**
     * getIconNumber
     * 
     * @return int number of icons in the wrap label
     */
    protected int getNumberofIcons() {
        if (iconInfo == null)
            return 0;
        return iconInfo.getNumberofIcons();
    }

    /**
     * getTotalIconSize Calculates the total union of icon sizes
     * 
     * @return Dimension that is the union of icon sizes
     */
    protected Dimension getTotalIconSize() {
        if (iconInfo == null)
            return EMPTY_DIMENSION;
        return iconInfo.getTotalIconSize(getFigureMapMode());
    }

    /**
     * Sets the Label's alignment to the passed value. The default is
     * {@link PositionConstants#CENTER}. Other possible values are
     * {@link PositionConstants#TOP},{@link PositionConstants#BOTTOM},
     * {@link PositionConstants#LEFT}and {@link PositionConstants#RIGHT}.
     * 
     * @param align
     *            label alignment
     */
    public void setLabelAlignment(int align) {
        if (getLabelAlignment() == align)
            return;
        setAlignmentFlags(align, FLAG_LABEL_ALIGN);
        clearLocations();
        repaint();
    }

    /**
     * Return the ellipse string.
     * 
     * @return the <code>String</code> that represents the fact that the text
     *         has been truncated and that more text is available but hidden.
     *         Usually this is represented by "...".
     */
    protected String getEllipse() {
        return _ellipse;
    }

    /**
     * Sets the label's text.
     * 
     * @param s
     *            the new label text
     * @since 0.9.0
     */
    public void setText(String s) {
        // "text" will never be null.
        if (s == null)
            s = "";//$NON-NLS-1$
        if (text.equals(s))
            return;
        text = s;
        revalidate();
        repaint(); // If the new text does not cause a new size, we still need
        // to paint.
    }

    /**
     * Sets the text alignment of the Label relative to the label alignment. The
     * default is {@link PositionConstants#CENTER}. Other possible values are
     * {@link PositionConstants#TOP},{@link PositionConstants#BOTTOM},
     * {@link PositionConstants#LEFT}and {@link PositionConstants#RIGHT}.
     * 
     * @param align
     *            the text alignment
     * @since 0.9.0
     */
    public void setTextAlignment(int align) {
        if (getTextAlignment() == align)
            return;
        setAlignmentFlags(align, FLAG_TEXT_ALIGN);
        clearLocations();
        repaint();
    }

    /**
     * Sets the text placement of the label relative to its icon. The default is
     * {@link PositionConstants#EAST}. Other possible values are
     * {@link PositionConstants#NORTH},{@link PositionConstants#SOUTH}and
     * {@link PositionConstants#WEST}.
     * 
     * @param where
     *            the text placement
     * @since 0.9.0
     */
    public void setTextPlacement(int where) {
        if (getTextPlacement() == where)
            return;
        setPlacementFlags(where, FLAG_TEXT_PLACEMENT);
        revalidate();
        repaint();
    }

    /**
     * Sets whether the label text should be underlined
     * 
     * @param b
     *            Whether the label text should be underlined
     */
    public void setTextUnderline(boolean b) {
        if (isTextUnderlined() == b)
            return;
        setFlag(FLAG_UNDERLINED, b);
        repaint();
    }

    /**
     * @return whether the label text is underlined
     */
    public boolean isTextUnderlined() {
        return (flags & FLAG_UNDERLINED) != 0;
    }

    /**
     * Sets whether the label text should be striked-through
     * 
     * @param b
     *            Whether the label text should be stricked-through
     */
    public void setTextStrikeThrough(boolean b) {
        if (isTextStrikedThrough() == b)
            return;
        setFlag(FLAG_STRIKEDTHROUGH, b);
        repaint();
    }

    /**
     * @return whether the label text is stricked-through
     */
    public boolean isTextStrikedThrough() {
        return (flags & FLAG_STRIKEDTHROUGH) != 0;
    }

    /**
     * Sets whether the label text should wrap
     * 
     * @param b
     *            whether the label text should wrap
     */
    public void setTextWrap(boolean b) {
        if (isTextWrapped() == b)
            return;
        setFlag(FLAG_WRAP, b);
        revalidate();
        repaint();
    }

    /**
     * @return whether the label text wrap is on
     */
    public boolean isTextWrapped() {
        return (flags & FLAG_WRAP) != 0;
    }

    /**
     * Sets the wrapping width of the label text. This is only valid if text
     * wrapping is turned on
     * 
     * @param i
     *            The label text wrapping width
     */
    public void setTextWrapWidth(int i) {
        /*
         * if (this.wrapWidth == i) return; this.wrapWidth = i; revalidate();
         * repaint();
         */
    }

    /**
     * Sets the wrapping width of the label text. This is only valid if text
     * wrapping is turned on
     * 
     * @param i
     *            The label text wrapping width
     */
    public void setTextWrapAlignment(int i) {
        if (getTextWrapAlignment() == i)
            return;

        setAlignmentFlags(i, FLAG_WRAP_ALIGN);
        repaint();
    }

    /**
     * @return the label text wrapping width
     */
    public int getTextWrapAlignment() {
        return getAlignment(FLAG_WRAP_ALIGN);
    }

    /**
     * setPlacementFlags
     * 
     * @param align
     * @param flagOffset
     */
    private void setPlacementFlags(int align, int flagOffset) {
        flags &= ~(0x7 * flagOffset);
        switch (align) {
        case EAST:
            flags |= 0x1 * flagOffset;
            break;
        case WEST:
            flags |= 0x2 * flagOffset;
            break;
        case NORTH:
            flags |= 0x3 * flagOffset;
            break;
        case SOUTH:
            flags |= 0x4 * flagOffset;
            break;
        default:
            break;
        }
    }

    /**
     * getPlacement
     * 
     * @param flagOffset
     * @return PositionConstant representing the placement
     */
    private int getPlacement(int flagOffset) {
        int wrapValue = flags & (0x7 * flagOffset);
        if (wrapValue == 0x1 * flagOffset)
            return EAST;
        else if (wrapValue == 0x2 * flagOffset)
            return WEST;
        else if (wrapValue == 0x3 * flagOffset)
            return NORTH;
        else if (wrapValue == 0x4 * flagOffset)
            return SOUTH;

        return EAST;
    }

    /**
     * setAlignmentFlags
     * 
     * @param align
     * @param flagOffset
     */
    private void setAlignmentFlags(int align, int flagOffset) {
        flags &= ~(0x7 * flagOffset);
        switch (align) {
        case CENTER:
            flags |= 0x1 * flagOffset;
            break;
        case TOP:
            flags |= 0x2 * flagOffset;
            break;
        case LEFT:
            flags |= 0x3 * flagOffset;
            break;
        case RIGHT:
            flags |= 0x4 * flagOffset;
            break;
        case BOTTOM:
            flags |= 0x5 * flagOffset;
            break;
        default:
            break;
        }
    }

    /**
     * Retrieves the alignment value from the flags member.
     * 
     * @param flagOffset
     *            that is the bitwise value representing the offset.
     * @return PositionConstant representing the alignment
     */
    private int getAlignment(int flagOffset) {
        int wrapValue = flags & (0x7 * flagOffset);
        if (wrapValue == 0x1 * flagOffset)
            return CENTER;
        else if (wrapValue == 0x2 * flagOffset)
            return TOP;
        else if (wrapValue == 0x3 * flagOffset)
            return LEFT;
        else if (wrapValue == 0x4 * flagOffset)
            return RIGHT;
        else if (wrapValue == 0x5 * flagOffset)
            return BOTTOM;

        return CENTER;
    }

    /**
     * Sets the selection state of this label
     * 
     * @param b
     *            true will cause the label to appear selected
     */
    public void setSelected(boolean b) {
        if (isSelected() == b)
            return;
        setFlag(FLAG_SELECTED, b);
        repaint();
    }

    /**
     * @return the selection state of this label
     */
    public boolean isSelected() {
        return (flags & FLAG_SELECTED) != 0;
    }

    /**
     * Sets the focus state of this label
     * 
     * @param b
     *            true will cause a focus rectangle to be drawn around the text
     *            of the Label
     */
    public void setFocus(boolean b) {
        if (hasFocus() == b)
            return;
        setFlag(FLAG_HASFOCUS, b);
        repaint();
    }

    /**
     * @return the focus state of this label
     */
    @Override
    public boolean hasFocus() {
        return (flags & FLAG_HASFOCUS) != 0;
    }

    /**
     * Returns the bounds of the text selection
     * 
     * @return The bounds of the text selection
     */
    private Rectangle getSelectionRectangle() {
        Rectangle figBounds = getTextBounds();
        int expansion = getMapModeConstants().nDPtoLP_2;
        figBounds.resize(expansion, expansion);
        translateToParent(figBounds);
        figBounds.intersect(getBounds());
        return figBounds;
    }

    /**
     * returns the position of last character within the supplied text that will
     * fit within the supplied width.
     * 
     * @param s
     *            a text string
     * @param f
     *            font used to draw the text string
     * @param w
     *            width in pixles.
     * @param fontHeight
     *            int <b>mapped already to logical units</b>.
     */
    private int getLineWrapPosition(String s, Font f, int w, int fontHeight) {
        if (getTextExtents(s, f, fontHeight).width <= w) {
            return s.length();
        }
        // create an iterator for line breaking positions
        BreakIterator iter = BreakIterator.getLineInstance();
        iter.setText(s);
        int start = iter.first();
        int end = iter.next();

        // if the first line segment does not fit in the width,
        // determine the position within it where we need to cut
        if (getTextExtents(s.substring(start, end), f, fontHeight).width > w) {
            iter = BreakIterator.getCharacterInstance();
            iter.setText(s);
            start = iter.first();
        }

        // keep iterating as long as width permits
        do
            end = iter.next();
        while (end != BreakIterator.DONE && getTextExtents(s.substring(start, end), f, fontHeight).width <= w);
        return (end == BreakIterator.DONE) ? iter.last() : iter.previous();
    }

    /**
     * Returns the largest substring of <i>s </i> in Font <i>f </i> that can be
     * confined to the number of pixels in <i>availableWidth <i>.
     * 
     * @param s
     *            the original string
     * @param f
     *            the font
     * @param w
     *            the available width
     * @param fontHeight
     *            int <b>mapped already to logical units</b>.
     * @param charAverageWidth
     *            int <b>mapped already to logical units</b>.
     * @return the largest substring that fits in the given width
     * @since 0.9.0
     */
    private int getLargestSubstringConfinedTo(String s, Font f, int w, int fontHeight, int charAverageWidth) {
        float avg = charAverageWidth;
        int min = 0;
        int max = s.length() + 1;

        // The size of the current guess
        int guess = 0, guessSize = 0;
        while ((max - min) > 1) {
            // Pick a new guess size
            // New guess is the last guess plus the missing width in pixels
            // divided by the average character size in pixels
            guess = guess + (int) ((w - guessSize) / avg);

            if (guess >= max)
                guess = max - 1;
            if (guess <= min)
                guess = min + 1;

            // Measure the current guess
            guessSize = getTextExtents(s.substring(0, guess), f, fontHeight).width;

            if (guessSize < w)
                // We did not use the available width
                min = guess;
            else
                // We exceeded the available width
                max = guess;
        }
        return min;
    }

    /**
     * Gets the tex extent scaled to the mapping mode
     */
    private Dimension getTextExtents(String s, Font f, int fontHeight) {
        if (s.length() == 0) {
            return getMapModeConstants().dimension_nDPtoLP_0;
        } else {
            // height should be set using the font height and the number of
            // lines in the string
            Dimension d = FigureUtilities.getTextExtents(s, f);
            IMapMode mapMode = getFigureMapMode();
            d.setWidth(mapMode.DPtoLP(d.width));
            d.setHeight(fontHeight * new StringTokenizer(s, "\n").countTokens());//$NON-NLS-1$
            return d;
        }
    }

    /**
     * Returns the current alignment of the entire Label. The default label
     * alignment is {@link PositionConstants#LEFT}.
     * 
     * @return the label alignment
     */
    public int getLabelAlignment2() {
        return getLabelAlignment();
    }

    // CHECKSTYLE:ON

    /**
     * {@inheritDoc}
     * 
     * Return false when this label part is not visible. (some edge labels
     * intercepts the selection when they are hidden or empty and non visible)
     */
    @Override
    public boolean containsPoint(int x, int y) {
        if (isVisible()) {
            return super.containsPoint(x, y);
        }
        return false;
    }
}
