/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.synchronizer.internal.IntegerAdapter;

/**
 * This class is responsible for providing an Integer ID and to attach it to an
 * EObject instance.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public final class IntegerProvider {

    private static Integer lastInteger = 0;

    /*
     * you should only use this class through static accesses.
     */
    private IntegerProvider() {

    }

    /**
     * Looks through the EObject adapters looking for an integer value. Will
     * create a new one if it is not found.
     * 
     * @param eObject
     *            any EObject.
     * @return the existing Integer if it got associated to the eObject, a new
     *         one otherwise.
     */
    public static Integer getInteger(final EObject eObject) {
        Integer id = null;
        for (final Adapter adapter : eObject.eAdapters()) {
            if (adapter instanceof IntegerAdapter)
                return ((IntegerAdapter) adapter).getIntegerValue();
        }
        if (id == null) {
            id = createANewInteger(eObject);
        }
        return id;
    }

    private static Integer createANewInteger(final EObject eObject) {
        Integer id = lastInteger++;
        IntegerAdapter integerValue = new IntegerAdapter(id);
        eObject.eAdapters().add(integerValue);
        return id;
    }

}
