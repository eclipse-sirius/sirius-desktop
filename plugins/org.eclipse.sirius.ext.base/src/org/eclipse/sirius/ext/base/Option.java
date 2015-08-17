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
package org.eclipse.sirius.ext.base;

import java.text.MessageFormat;

/**
 * This class means a value which might be here, or not depending on the "some"
 * method result. This class is useful to indicate to API's clients that a given
 * method my return nothing avoiding the dreaded "return null" pattern.
 * 
 * @author cbrun
 * 
 * @param <T>
 *            the type of the element to return.
 */
public final class Option<T> {

    private T value;

    /*
     * You should use the Options type to create new instances.
     */
    Option(T val) {
        this.value = val;
    }

    /**
     * return true if there is any value to return.
     * 
     * @return true if there is any value to return
     */
    public boolean some() {
        return this.value != null;
    }

    /**
     * return the value.
     * 
     * @return the value
     */
    public T get() {
        return value;
    }

    /**
     * Validates that obj is an Option and that it has the same value or none as
     * well.
     * 
     * @param obj
     *            an object
     * @return that obj is an Option and that it has the same value or none as
     *         well.
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Option<?>) {
            if (!this.some()) {
                result = !((Option<?>) obj).some();
            } else {
                result = ((Option<?>) obj).some() && this.get().equals(((Option<?>) obj).get());
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        if (this.some()) {
            return this.get().hashCode();
        } else {
            return 17;
        }
    }
    
    @Override
    public String toString() {
        if (this.some()) {
            return MessageFormat.format(Messages.Option_present, String.valueOf(this.get()));
        } else {
            return Messages.Option_absent;
        }
    }
}
