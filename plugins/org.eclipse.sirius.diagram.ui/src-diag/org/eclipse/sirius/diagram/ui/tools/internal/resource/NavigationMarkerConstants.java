/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
     * The key for record the diagram info for retrieve the diagram on marker
     * opening.<BR>
     * The URI is like this :
     * platform:/resource/projectName/folderPath/fileName#id
     */
    public static final String DIAGRAM_URI = "DIAGRAM_URI"; //$NON-NLS-1$

    /**
     * The key for record the semantic element URI.<BR>
     * The URI is like this :
     * platform:/resource/projectName/folderPath/fileName#id
     */
    public static final String SEMANTIC_URI = "SEMANTIC_URI"; //$NON-NLS-1$

    /**
     * Default constructor.
     */
    private NavigationMarkerConstants() {

    }
}
