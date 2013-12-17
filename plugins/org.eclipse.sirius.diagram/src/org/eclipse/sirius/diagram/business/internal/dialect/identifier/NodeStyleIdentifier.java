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
package org.eclipse.sirius.diagram.business.internal.dialect.identifier;

import org.eclipse.sirius.business.api.dialect.identifier.AbstractRepresentationElementIdentifier;
import org.eclipse.sirius.diagram.NodeStyle;

/**
 * An identifier for node style.
 * 
 * @author mchauvin
 */
public class NodeStyleIdentifier extends AbstractRepresentationElementIdentifier {

    private NodeIdentifier parent;

    /**
     * Construct a new instance.
     * 
     * @param style
     *            the style from which to create an identifier
     * @param parent
     *            the parent node.
     */
    public NodeStyleIdentifier(final NodeStyle style, final NodeIdentifier parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.thalesgroup.mde.dae.advance.specific.diagram.ThalesElementState.StateIdentifier#uniqueID()
     */
    @Override
    public int uniqueID() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        return result;
    }

    // CHECKSTYLE:OFF
    /**
     * {@inheritDoc}
     * 
     * @see com.thalesgroup.mde.dae.advance.specific.diagram.ThalesElementState.StateIdentifier#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodeStyleIdentifier other = (NodeStyleIdentifier) obj;
        return parent.equals(other.parent);
    }
    // CHECKSTYLE:ON
}
