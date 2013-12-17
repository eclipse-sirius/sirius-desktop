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

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.internal.image.FileFormat;

/**
 * A specific FileFormat class to modify the encoderQFactor of the
 * {@link org.eclipse.swt.internal.image.JPEGFileFormat} to 100 to have a better
 * quality for JPG file.
 * 
 * @author laurent.redor@obeo.fr
 * 
 */
public final class SiriusFileFormat {
    private static final int NEW_ENCODER_Q_FACTOR = 100;

    /**
     * Default constructor to avoid instantiation.
     */
    private SiriusFileFormat() {

    }

    /**
     * Write the device independent image array stored in the specified loader
     * to the specified output stream using the specified file format.
     * 
     * @param os
     *            The current OutputStream
     * @param format
     *            The chosen format
     * @param loader
     *            The loader used for this image
     */
    public static void save(OutputStream os, int format, ImageLoader loader) {

        if (format != SWT.IMAGE_JPEG)
            SWT.error(SWT.ERROR_UNSUPPORTED_FORMAT);
        if (loader.data == null || loader.data.length < 1)
            SWT.error(SWT.ERROR_INVALID_ARGUMENT);

        // Use the reflection to launch the save of JPG file with an
        // encoderQFactor of 99. It the introspection failed, the default save
        // is used.
        boolean reflectionCallOK = false;

        Option<Object> streamOption = ReflectionHelper.instantiateWithoutException("org.eclipse.swt.internal.image.LEDataOutputStream", new Class[] { OutputStream.class }, new Object[] { os });
        if (streamOption.some()) {
            Option<Object> fileFormatOption = ReflectionHelper.instantiateWithoutException("org.eclipse.swt.internal.image.JPEGFileFormat", new Class[0], new Object[0]);
            if (fileFormatOption.some()) {
                if (ReflectionHelper.setFieldValueWithoutException(fileFormatOption.get(), "encoderQFactor", NEW_ENCODER_Q_FACTOR)) {
                    reflectionCallOK = ReflectionHelper.invokeMethodWithoutException(fileFormatOption.get(), "unloadIntoStream", new Class[] { ImageLoader.class, streamOption.get().getClass() },
                            new Object[] { loader, streamOption.get() });
                }
            }
        }
        if (!reflectionCallOK) {
            // The reflection way failed, do it with the normal way with default
            // (0.75) quality...
            FileFormat.save(os, format, loader);
        }
    }
}
