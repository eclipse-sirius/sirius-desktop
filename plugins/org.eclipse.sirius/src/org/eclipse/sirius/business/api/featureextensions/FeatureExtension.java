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
