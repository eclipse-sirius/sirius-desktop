/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.migration.resource;

/**
 * A class to manage a couple of old and new value (and replacement of it).
 * 
 * @author mchauvin
 */
public class StringCouple {

    /** The old String. */
    private final String oldString;

    /** The new String. */
    private final String newString;

    /**
     * Constructor.
     * 
     * @param oldString
     *            The old value
     * @param newString
     *            The new value
     */
    public StringCouple(final String oldString, final String newString) {
        this.oldString = oldString;
        this.newString = newString;
    }

    /**
     * Transform the value by replacing the old value by the new one.
     * 
     * @param value
     *            The string to transform.
     * @return The transformed string.
     */
    public String transform(final String value) {
        return value.replaceAll(oldString, newString);
    }

    /**
     * Return the oldString.
     * 
     * @return the oldString
     */
    public String getOldString() {
        return oldString;
    }

    /**
     * Return the newString.
     * 
     * @return the newString
     */
    public String getNewString() {
        return newString;
    }
}
