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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;

/**
 * User-interface centric services for dialects. All services must be stateless.
 * 
 * @author mchauvin
 */
public interface FeatureExtensionUIServices {

    /**
     * Return the list of new child descriptors provided on the Sirius type.
     * 
     * @return the list of new child descriptors provided on the Sirius type.
     */
    Collection<CommandParameter> provideNewChildDescriptors();

    /**
     * Creates a new {@link AdapterFactory} used in the description editor for
     * the provided types.
     * 
     * @return a new {@link AdapterFactory} used in the description editor for
     *         the provided types.
     */
    AdapterFactory createAdapterFactory();

}
