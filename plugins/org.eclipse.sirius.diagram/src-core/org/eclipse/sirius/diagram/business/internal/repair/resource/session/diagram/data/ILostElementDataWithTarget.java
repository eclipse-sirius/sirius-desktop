/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data;

import org.eclipse.emf.ecore.EObject;

/**
 * Lost element data with target.
 * 
 * @author dlecan
 */
public interface ILostElementDataWithTarget extends ILostElementData {

    /**
     * Set the target.
     * 
     * @param target
     *            the target.
     */
    void setTarget(final EObject target);

    /**
     * Get the target.
     * 
     * @return The target.
     */
    EObject getTarget();

}
