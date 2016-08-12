/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.keys;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;

/**
 * Specific key allowing to know the semantic {@link EObject}.
 * 
 * @author mporhel
 */
public abstract class AbstractSampleFormatDataKey implements FormatDataKey {

    /**
     * The semantic element of this FormatDataKey
     */
    private EObject semantic;

    /**
     * Default constructor.
     * 
     * @param key
     *            The key
     */
    public AbstractSampleFormatDataKey(final EObject key) {
        this.semantic = key;
    }

    public EObject getSemantic() {
        return semantic;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey#getId()
     */
    @Override
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
        AbstractSampleFormatDataKey other = (AbstractSampleFormatDataKey) obj;
        if (semantic == null) {
            if (other.semantic != null)
                return false;
        } else if (!semantic.equals(other.semantic))
            return false;
        return true;
    }
}
