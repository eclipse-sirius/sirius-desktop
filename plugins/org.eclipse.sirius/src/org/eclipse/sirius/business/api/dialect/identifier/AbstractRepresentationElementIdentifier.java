/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect.identifier;

/**
 * Default implementation of {@link RepresentationElementIdentifier}.
 * 
 * @author ymortier
 */
public abstract class AbstractRepresentationElementIdentifier implements RepresentationElementIdentifier {

    private Integer cachedUniqueId;

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        if (cachedUniqueId == null) {
            cachedUniqueId = Integer.valueOf(this.uniqueID());
        }
        return cachedUniqueId.intValue();
    }

    /**
     * Returns an unique id for this identifier.
     * 
     * @return an unique id for this identifier.
     */
    public abstract int uniqueID();

}
