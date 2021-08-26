/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
    void setTarget(EObject target);

    /**
     * Get the target.
     * 
     * @return The target.
     */
    EObject getTarget();

}
