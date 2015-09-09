/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.ui.property;

import org.eclipse.sirius.viewpoint.Messages;

/**
 * Properties provider.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public interface IPropertiesProvider {
    /**
     * Key for auto_refresh.
     */
    int KEY_AUTO_REFRESH = 1;

    /**
     * Exception when no property is found.
     */
    IllegalArgumentException EXCEPTION_PROPERTY_NOT_FOUND = new IllegalArgumentException(Messages.IPropertiesProvider_unfoundPropertyErrorMsg);

    /**
     * get the enablement of a property.
     * 
     * @param key
     *            a property key in the following list :
     * 
     *            {@link IPropertiesProvider#KEY_AUTO_REFRESH}
     * 
     * @return true if the property is enabled, false otherwise
     * @throws IllegalArgumentException
     *             if the property is not available or not supported
     */
    boolean getProperty(int key) throws IllegalArgumentException;
}
