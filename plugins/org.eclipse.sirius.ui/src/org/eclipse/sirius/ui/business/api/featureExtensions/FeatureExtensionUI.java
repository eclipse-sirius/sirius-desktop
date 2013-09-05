/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.featureExtensions;

/**
 * FeatureExtension UI provider interface.
 * 
 * @author mchauvin
 */
public interface FeatureExtensionUI {

    /**
     * Identifier for the dialect type.
     * 
     * @return the dialect name.
     */
    String getName();

    /**
     * return the provided dialect services.
     * 
     * @return the provided dialect services.
     */
    FeatureExtensionUIServices getServices();
}
