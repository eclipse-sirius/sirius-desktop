/******************************************************************************
 * Copyright (c) 2002, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.sirius.common.tools.api.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * An enumeration of image formats supported by the copy diagram to image file
 * utility class <code>CopyToImageUtil</code>.
 * <p>
 * We use this enumeration rather than int as in SWT.IMAGE_GIF to enforce strict
 * supported format type checking.
 * </p>
 * 
 * @author Anthony Hunter <a
 *         href="mailto:ahunter@rational.com">ahunter@rational.com</a>
 */
public final class ImageFileFormat {

    /**
     * supported format Graphics Interchange Format (GIF).
     */
    public static final ImageFileFormat GIF = new ImageFileFormat("GIF"); //$NON-NLS-1$

    /**
     * supported format Joint Photographic Experts Group format (JPEG).
     */
    public static final ImageFileFormat JPEG = new ImageFileFormat("JPEG"); //$NON-NLS-1$

    /**
     * supported format Joint Photographic Experts Group format (JPG).
     */
    public static final ImageFileFormat JPG = new ImageFileFormat("JPG"); //$NON-NLS-1$

    /**
     * supported format Windows Bitmap format (BMP).
     */
    public static final ImageFileFormat BMP = new ImageFileFormat("BMP"); //$NON-NLS-1$

    /**
     * supported format Scalable Vector Graphics (SVG).
     */
    public static final ImageFileFormat SVG = new ImageFileFormat("SVG"); //$NON-NLS-1$

    /**
     * supported format Scalable Vector Graphics compressed (SVGZ).
     */
    public static final ImageFileFormat SVGZ = new ImageFileFormat("SVGZ"); //$NON-NLS-1$

    /**
     * supported format PNG.
     */
    public static final ImageFileFormat PNG = new ImageFileFormat("PNG"); //$NON-NLS-1$

    /**
     * The list of values for this enumerated type.
     */
    public static final ImageFileFormat[] VALUES = { GIF, BMP, JPEG, JPG, SVG, SVGZ, PNG };

    private String name;

    /**
     * Constructs a new type with the specified name and ordinal.
     * 
     * @param name
     *            The name of the new type.
     */
    private ImageFileFormat(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Retrieves the list of constants for this enumerated type.
     * 
     * @return The list of constants for this enumerated type.
     * 
     * @see org.eclipse.gmf.runtime.common.core.util.EnumeratedType#getValues()
     */
    protected List<ImageFileFormat> getValues() {
        return Collections.unmodifiableList(Arrays.asList(VALUES));
    }

    /**
     * Retrieves the default image format.
     * 
     * @return the default image format.
     */
    public static ImageFileFormat getDefaultImageFormat() {
        return GIF;
    }

    /**
     * resolve the selected image format to an enumerated type.
     * 
     * @param ordinal
     *            the selected format in the pulldown
     * @return the image format enumerated type
     */
    public static ImageFileFormat resolveImageFormat(final int ordinal) {
        return ImageFileFormat.VALUES[ordinal];
    }

    /**
     * Resolve the selected image format to an enumerated type.
     * 
     * @param imageFormat
     *            the selected format.
     * @return the image format enumerated type
     */
    public static ImageFileFormat resolveImageFormat(final String imageFormat) {
        for (ImageFileFormat element : ImageFileFormat.VALUES) {
            if (element.getName().toLowerCase().equals(imageFormat.toLowerCase())) {
                return element;
            }
        }

        return ImageFileFormat.getDefaultImageFormat();
    }

}
