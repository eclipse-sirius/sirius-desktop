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
package org.eclipse.sirius.business.api.logger;

/**
 * Logger for runtime markers.
 * 
 * @author smonnier
 * @since 0.9.0
 * 
 */
// CHECKSTYLE:OFF
public interface MarkerRuntimeLogger {
    /**
     * Defines the type of the marker for Sirius.
     */
    String MARKER_TYPE = "org.eclipse.sirius.viewpointMarker"; //$NON-NLS-1$

    /**
     * Defines the URI of the marker attribute.
     */
    String URI_MARKER_ATTRIBUTE = "viewpointURIMarker"; //$NON-NLS-1$
}
