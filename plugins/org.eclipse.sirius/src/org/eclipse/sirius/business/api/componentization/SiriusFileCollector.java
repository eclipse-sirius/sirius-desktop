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
package org.eclipse.sirius.business.api.componentization;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.description.Sirius;

/**
 * This interface represent the capability to extract Siriuss from a given
 * EObject.
 * 
 * @since 2.7
 * @author cbrun
 * 
 */
public interface SiriusFileCollector {
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
    Collection<Sirius> collect(EObject root);
}
