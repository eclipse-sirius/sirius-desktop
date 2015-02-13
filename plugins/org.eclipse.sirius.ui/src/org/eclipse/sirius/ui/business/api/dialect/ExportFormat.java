/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.dialect;

import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;

/**
 * A class to store the format for representations export.
 * 
 * @author mchv
 * @since 0.9.0
 */
public class ExportFormat {

    ExportDocumentFormat documentFormat;

    ImageFileFormat imageFomat;

    /**
     * Create a new export format.
     * 
     * @param documentFormat
     *            the document export format
     * @param imageFormat
     *            the image export format
     */
    public ExportFormat(final ExportDocumentFormat documentFormat, ImageFileFormat imageFormat) {
        this.documentFormat = documentFormat;
        this.imageFomat = imageFormat;
    }

    /**
     * Get image export format.
     * 
     * @return the image export format
     */
    public ImageFileFormat getImageFormat() {
        return this.imageFomat;
    }

    /**
     * Get document export format.
     * 
     * @return the document export format
     */
    public ExportDocumentFormat getDocumentFormat() {
        return this.documentFormat;
    }

    /**
     * Export document format:
     * <UL>
     * <LI>HTML (only using 3.5 platform) for all kind of diagrams,</LI>
     * <LI>CSV for tables export is supported.</LI>
     * </UL>
     * 
     * @author mchauvin
     */
    public enum ExportDocumentFormat {
        /** HTML export format. */
        HTML,
        /** CSV export format. */
        CSV,
        /** Used when we only export images. */
        NONE
    }

}
