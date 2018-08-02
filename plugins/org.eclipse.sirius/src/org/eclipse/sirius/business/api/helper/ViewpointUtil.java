/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper;

/**
 * Useful constants to manipulate a Viewpoint.
 * 
 * @author jmallet
 */
public interface ViewpointUtil {

    /** The "environment:/" uri scheme. */
    String ENVIRONMENT_URI_SCHEME = "environment"; //$NON-NLS-1$

    /** The "environment:/viewpoint" resource uri. */
    String VIEWPOINT_ENVIRONMENT_RESOURCE_URI = ENVIRONMENT_URI_SCHEME + ":/viewpoint"; //$NON-NLS-1$

}
