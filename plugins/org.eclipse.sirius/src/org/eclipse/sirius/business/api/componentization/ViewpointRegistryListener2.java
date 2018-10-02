/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.componentization;

/**
 * A listener interface to listen events in the ViewpointRegistry.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface ViewpointRegistryListener2 {

    /**
     * Called when one or several modeler description files have been loaded or
     * reloaded.
     */
    void modelerDesciptionFilesLoaded();

}
