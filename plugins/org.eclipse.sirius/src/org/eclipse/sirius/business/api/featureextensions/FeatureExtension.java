/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.featureextensions;

/**
 * FeatureExtension interface.
 * 
 * @author mchauvin
 */
public interface FeatureExtension {
    /**
     * Identifier for the aspect type.
     * 
     * @return the dialect name.
     */
    String getName();

    /**
     * return the provided aspect services.
     * 
     * @return the provided aspect services.
     */
    FeatureExtensionServices getServices();

}
