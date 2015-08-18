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
 * Element data which stores only a target.
 * 
 * @author dlecan
 */
public abstract class AbstractLostElementDataWithTarget extends AbstractLostElementData implements ILostElementDataWithTarget {

    /** Separator for toString. */
    protected static final String SEPARATOR = " |--| "; //$NON-NLS-1$

    private EObject target;

    /**
     * {@inheritDoc}
     */
    public void setTarget(final EObject target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    public EObject getTarget() {
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + SEPARATOR + "Target: " + target; //$NON-NLS-1$
    }
}
