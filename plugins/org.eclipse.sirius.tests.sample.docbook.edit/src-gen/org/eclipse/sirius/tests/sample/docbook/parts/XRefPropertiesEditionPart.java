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
public interface XRefPropertiesEditionPart {

    /**
     * @return the linkend
     *
     */
    public String getLinkend();

    /**
     * Defines a new linkend
     *
     * @param newValue
     *            the new linkend to set
     *
     */
    public void setLinkend(String newValue);

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
