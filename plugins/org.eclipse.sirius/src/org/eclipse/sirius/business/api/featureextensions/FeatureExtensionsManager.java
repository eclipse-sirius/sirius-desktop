/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.featureextensions;

import org.eclipse.sirius.business.internal.featureextensions.FeatureExtensionsManagerImpl;

/**
 * Instance managing the feature extensions.
 * 
 * @author mchauvin
 */
public interface FeatureExtensionsManager extends FeatureExtensionServices {
    /**
     * Singleton instance of the dialect manager.
     */
    FeatureExtensionsManager INSTANCE = FeatureExtensionsManagerImpl.init();

    /**
     * FeatureExtension manager extension point ID.
     */
    String ID = "org.eclipse.sirius.featureExtensions"; //$NON-NLS-1$

    /**
     * Extension point attribute for the feature extension class.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Enable a new feature extension.
     * 
     * @param featureExtension
     *            feature extension to enable.
     */
    void enableFeatureExtension(FeatureExtension featureExtension);

    /**
     * Disable a feature extension. If it was not enabled : do nothing.
     * 
     * @param featureExtension
     *            feature extension to disable.
     */
    void disableFeatureExtension(FeatureExtension featureExtension);
}
