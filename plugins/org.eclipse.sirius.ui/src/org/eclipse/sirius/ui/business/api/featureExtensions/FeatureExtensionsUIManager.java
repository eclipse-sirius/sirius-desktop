/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.business.api.featureExtensions;

import org.eclipse.sirius.ui.business.internal.featureExtensions.FeatureExtensionsUIManagerImpl;

/**
 * Instance managing the feature extensions.
 * 
 * @author mchauvin
 */
public interface FeatureExtensionsUIManager extends FeatureExtensionUIServices {

    /**
     * Singleton instance of the feature extensions manager.
     */
    FeatureExtensionsUIManager INSTANCE = FeatureExtensionsUIManagerImpl.init();

    /**
     * FeatureExtension manager extension point ID.
     */
    String ID = "org.eclipse.sirius.ui.featureExtensionsui"; //$NON-NLS-1$

    /**
     * Extension point attribute for the feature extension ui class.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Enable a new feature extension.
     * 
     * @param featureExtension
     *            feature extension to enable.
     */
    void enableAspectUI(FeatureExtensionUI featureExtension);

    /**
     * Disable an feature extension. If it was not enabled : do nothing.
     * 
     * @param featureExtension
     *            feature extension to disable.
     */
    void disableAspectUI(FeatureExtensionUI featureExtension);

}
