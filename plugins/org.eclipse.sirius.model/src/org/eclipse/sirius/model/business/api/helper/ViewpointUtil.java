/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.model.business.api.helper;

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
