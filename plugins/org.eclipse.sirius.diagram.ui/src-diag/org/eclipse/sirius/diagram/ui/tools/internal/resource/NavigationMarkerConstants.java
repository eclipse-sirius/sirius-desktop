/*******************************************************************************
 * Copyright (c) 2009, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.resource;

/**
 * Constants for specific navigation markers.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class NavigationMarkerConstants {

    /**
     * The key for record the diagram descriptor info for retrieve the diagram on marker opening.<BR>
     * The URI is like this : platform:/resource/projectName/folderPath/fileName#id
     */
    public static final String DIAGRAM_DESCRIPTOR_URI = "DIAGRAM_DESCRIPTOR_URI"; //$NON-NLS-1$

    /**
     * The key for record the semantic element URI.<BR>
     * The URI is like this : platform:/resource/projectName/folderPath/fileName#id
     */
    public static final String SEMANTIC_URI = "SEMANTIC_URI"; //$NON-NLS-1$

    /**
     * Used to set a marker as a marker used for image path.
     */
    public static final String MARKER_IMAGE = "MARKER_IMAGE"; //$NON-NLS-1$

    /**
     * The key to retrieve the image path.
     */
    public static final String IMAGE_PATH_KEY = "IMAGE_PATH"; //$NON-NLS-1$

    /**
     * The key to retrieve the ImagePathTarget litteral.
     */
    public static final String IMAGE_PATH_TARGET_KEY = "IMAGE_PATH_TARGET_KEY"; //$NON-NLS-1$

    /**
     * The key to retrieve the feature containing path to image.
     */
    public static final String IMAGE_PATH_FEATURE_NAME = "IMAGE_PATH_FEATURE"; //$NON-NLS-1$

    /**
     * Default constructor.
     */
    private NavigationMarkerConstants() {

    }
}
