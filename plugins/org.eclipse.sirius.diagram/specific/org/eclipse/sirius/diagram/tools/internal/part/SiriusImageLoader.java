/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.part;

import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.internal.image.FileFormat;

/**
 * Override ImageLoader to change the encodingQFactor of the JPEGFileFormat.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SiriusImageLoader extends ImageLoader {

    /**
     * Saves the image data in this ImageLoader to the specified stream. The
     * format parameter can have one of the following values:
     * <dl>
     * <dt><code>IMAGE_BMP</code></dt>
     * <dd>Windows BMP file format, no compression</dd>
     * <dt><code>IMAGE_BMP_RLE</code></dt>
     * <dd>Windows BMP file format, RLE compression if appropriate</dd>
     * <dt><code>IMAGE_GIF</code></dt>
     * <dd>GIF file format</dd>
     * <dt><code>IMAGE_ICO</code></dt>
     * <dd>Windows ICO file format</dd>
     * <dt><code>IMAGE_JPEG</code></dt>
     * <dd>JPEG file format</dd>
     * <dt><code>IMAGE_PNG</code></dt>
     * <dd>PNG file format</dd>
     * </dl>
     * 
     * @param stream
     *            the output stream to write the images to
     * @param format
     *            the format to write the images in
     * 
     * @exception IllegalArgumentException
     *                <ul>
     *                <li>ERROR_NULL_ARGUMENT - if the stream is null</li>
     *                </ul>
     * @exception SWTException
     *                <ul>
     *                <li>ERROR_IO - if an IO error occurs while writing to the
     *                stream</li>
     *                <li>ERROR_INVALID_IMAGE - if the image data contains
     *                invalid data</li>
     *                <li>ERROR_UNSUPPORTED_FORMAT - if the image data cannot be
     *                saved to the requested format</li>
     *                </ul>
     */
    public void save(OutputStream stream, int format) throws IllegalArgumentException, SWTException {
        if (stream == null)
            SWT.error(SWT.ERROR_NULL_ARGUMENT);
        if (format == SWT.IMAGE_JPEG) {
            SiriusFileFormat.save(stream, format, this);
        } else {
            FileFormat.save(stream, format, this);
        }

    }
}
