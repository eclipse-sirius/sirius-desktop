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
package org.eclipse.sirius.business.api.componentization;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This interface represent the capability to extract Viewpoints from a given
 * EObject.
 * 
 * @since 0.9.0
 * @author cbrun
 * 
 */
public interface ViewpointFileCollector {
    /**
     * return true if the given model is valid and the collector handles this
     * kind of model.
     * 
     * @param root
     *            any model
     * @return true if the given model is valid and the collector handles this
     *         kind of model.
     */
    boolean isValid(EObject root);

    /**
     * return a list of collected viewpoints from this model.
     * 
     * @param root
     *            any model
     * @return a list of collected viewpoints from this model.
     */
    Collection<Viewpoint> collect(EObject root);
}
