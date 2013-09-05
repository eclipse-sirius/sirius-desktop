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

/**
 * This class is a wrapper class for one value. It might contains a value or
 * not, clients have to explicitly check the some() method. This is inspired by
 * the Maybe utility type in haskell.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 * @param <T>
 *            the type of the element to return.
 */
public final class Maybe<T> {

    private T value;

    /*
     * See MaybeFactory
     */
    Maybe(T val) {
        this.value = val;
    }

    public T value() {
        return value;
    }

    public boolean some() {
        return this.value != null;
    }

}
