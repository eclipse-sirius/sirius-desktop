/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES and others.
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

    private final ExportDocumentFormat documentFormat;

    private final ImageFileFormat imageFomat;

    private final ScalingPolicy scalingPolicy;

    /**
     * Scaling level from 0 to 100 by step of 10. It is used only if scaling policy is set to WORKSPACE_DEFAULT,
     * AUTO_SCALING or AUTO_SCALING_IF_LARGER. A scaling level set to 0 corresponds to the NO_SCALING scaling policy
     * (even with other policies).
     */
    private final Integer scalingLevel;

    /**
     * Create a new export format.
     * 
     * @param documentFormat
     *            the document export format
     * @param imageFormat
     *            the image export format
     * @param scalingPolicy
     *            indicates if and how exported diagram should be scaled
     */
    public ExportFormat(final ExportDocumentFormat documentFormat, ImageFileFormat imageFormat, ScalingPolicy scalingPolicy) {
        this.documentFormat = documentFormat;
        this.imageFomat = imageFormat;
        this.scalingPolicy = scalingPolicy;
        this.scalingLevel = null;
    }

    /**
     * Create a new export format.
     * 
     * @param documentFormat
     *            the document export format
     * @param imageFormat
     *            the image export format
     * @param scalingPolicy
     *            indicates if and how exported diagram should be scaled
     * @param scalingLevel
     *            indicates level of scaling when diagrams are exported (value from 0 to 100 by step of 10).
     */
    public ExportFormat(final ExportDocumentFormat documentFormat, ImageFileFormat imageFormat, ScalingPolicy scalingPolicy, Integer scalingLevel) {
        this.documentFormat = documentFormat;
        this.imageFomat = imageFormat;
        this.scalingPolicy = scalingPolicy;
        this.scalingLevel = scalingLevel;
    }

    /**
     * Create a new export format. Uses the value of the workspace preference for the scaling policy.
     * 
     * @param documentFormat
     *            the document export format
     * @param imageFormat
     *            the image export format
     */
    public ExportFormat(final ExportDocumentFormat documentFormat, ImageFileFormat imageFormat) {
        this(documentFormat, imageFormat, ScalingPolicy.WORKSPACE_DEFAULT, null);
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
     * Get the diagram scaling policy.
     * 
     * @return the diagram scaling policy.
     */
    public ScalingPolicy getScalingPolicy() {
        return scalingPolicy;
    }

    /**
     * Get the diagram scaling level.
     * 
     * @return the diagram scaling level.
     */
    public Integer getScalingLevel() {
        return scalingLevel;
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

    /**
     * Used to indicate if and how exported diagram should be scaled.
     * 
     * @author pcdavid
     */
    public enum ScalingPolicy {
        /**
         * Use the current value set in the workspace preference
         * ({@code SiriusDiagramUiPreferencesKeys.PREF_SCALE_DIAGRAMS_ON_EXPORT}).
         */
        WORKSPACE_DEFAULT,
        /**
         * Automatically scale the image (up or down) to the maximum size that fits the max buffer size (while keeping
         * the image ratio).
         */
        AUTO_SCALING,
        /**
         * Do not scale the diagram, export it at 100% zoom level.
         */
        NO_SCALING,
        /**
         * Same as AUTO_SCALING, but only if this would produce a higher resolution image, i.e. zoom level higher than
         * 100%. Otherwise export at 100% (same as NO_SCALING).
         */
        AUTO_SCALING_IF_LARGER
    }

}
