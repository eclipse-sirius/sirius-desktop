/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource;

/**
 * This class overrides CrossReferenceAdapter to have it installed only on the
 * AirDResource.
 * 
 * @author smonnier
 * 
 */
public interface AirDCrossReferenceAdapter {

    /**
     * Disable the resolution of the proxy.
     */
    void disableResolve();

    /**
     * Enable the resolution of the proxy.
     */
    void enableResolve();
}
