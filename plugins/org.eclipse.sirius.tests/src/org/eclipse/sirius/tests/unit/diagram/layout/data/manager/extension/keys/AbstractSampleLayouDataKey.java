/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.layout.data.manager.extension.keys;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey;

/**
 * Specific key allowing to know the semantic {@link EObject}.
 * 
 * @author mporhel
 */
public abstract class AbstractSampleLayouDataKey implements LayoutDataKey {

    /**
     * The semantic element of this LayoutDataKey
     */
    private EObject semantic;

    /**
     * Default constructor.
     * 
     * @param key
     *            The key
     */
    public AbstractSampleLayouDataKey(final EObject key) {
        this.semantic = key;
    }

    public EObject getSemantic() {
        return semantic;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutDataKey#getId()
     */
    public String getId() {
        return EcoreUtil.getURI(getSemantic()).fragment();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((semantic == null) ? 0 : semantic.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractSampleLayouDataKey other = (AbstractSampleLayouDataKey) obj;
        if (semantic == null) {
            if (other.semantic != null)
                return false;
        } else if (!semantic.equals(other.semantic))
            return false;
        return true;
    }
}
