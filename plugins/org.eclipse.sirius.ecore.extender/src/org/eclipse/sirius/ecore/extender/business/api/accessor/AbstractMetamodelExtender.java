/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import java.util.Collection;

/**
 * Basic implementation of an extender. It implements:
 * <ul>
 * <li> {@link IMetamodelExtender#activate()}</li>
 * <li> {@link IMetamodelExtender#deactivate()}</li>
 * <li> {@link IMetamodelExtender#isActive()}</li>
 * </ul>
 * 
 * @author ymortier
 */
public abstract class AbstractMetamodelExtender implements IMetamodelExtender {

    /**
     * <code>true</code> if the extender is active.
     */
    private boolean active;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.accessor.IMetamodelExtender#isActive()
     */
    public boolean isActive() {
        return active;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void activate() {
        this.active = true;

    }

    /**
     * {@inheritDoc}
     */
    public void updateMetamodels(Collection<? extends MetamodelDescriptor> metamodelDescriptors) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.accessor.IMetamodelExtender#deactivate()
     */
    public void deactivate() {
        this.active = false;
    }

}
