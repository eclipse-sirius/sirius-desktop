/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.tests.sample.docbook.parts;

// Start of user code for imports

// End of user code

/**
 *
 *
 */
public interface AuthorPropertiesEditionPart {

    /**
     * @return the email
     *
     */
    public String getEmail();

    /**
     * Defines a new email
     *
     * @param newValue
     *            the new email to set
     *
     */
    public void setEmail(String newValue);

    /**
     * @return the personname
     *
     */
    public String getPersonname();

    /**
     * Defines a new personname
     *
     * @param newValue
     *            the new personname to set
     *
     */
    public void setPersonname(String newValue);

    /**
     * @return the address
     *
     */
    public String getAddress();

    /**
     * Defines a new address
     *
     * @param newValue
     *            the new address to set
     *
     */
    public void setAddress(String newValue);

    /**
     * Returns the internationalized title text.
     *
     * @return the internationalized title text.
     *
     */
    public String getTitle();

    // Start of user code for additional methods

    // End of user code

}
