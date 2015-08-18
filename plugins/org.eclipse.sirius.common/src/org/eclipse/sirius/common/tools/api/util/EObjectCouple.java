/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import org.eclipse.emf.ecore.EObject;

/**
 * Class useful to use as a key for a cache needing two objects.
 * 
 * @author cbrun
 * 
 */
public class EObjectCouple {

    /** The first EObject. */
    private EObject obj1;

    /**
     * The second {@link EObject}.
     */
    private EObject obj2;

    /** The cached hash string. */
    private Integer int1;

    private Integer int2;

    /**
     * Create a new {@link EObjectCouple}.
     * 
     * @param obj1
     *            the first {@link EObject}.
     * @param obj2
     *            the second {@link EObject}.
     * @param ids
     *            the holder of refresh ids.
     */
    public EObjectCouple(final EObject obj1, final EObject obj2, RefreshIdsHolder ids) {
        if (obj1 == null) {
            throw new IllegalArgumentException("the first eObject is null");
        }
        if (obj2 == null) {
            throw new IllegalArgumentException("the second eObject is null");
        }
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.int1 = ids.getOrCreateID(obj1);
        this.int2 = ids.getOrCreateID(obj2);
    }

    @Override
    public int hashCode() {
        return int1 + int2;
    }

    @Override
    public boolean equals(final Object obj) {

        boolean areEquals = false;

        if (this == obj) {

            areEquals = true;

        } else if (obj != null && getClass() == obj.getClass()) {

            final EObjectCouple other = (EObjectCouple) obj;

            areEquals = this.int1.equals(other.int1) && this.int2.equals(other.int2);

        }

        return areEquals;

    }

    public EObject getObj1() {
        return this.obj1;
    }

    public EObject getObj2() {
        return this.obj2;
    }
}
