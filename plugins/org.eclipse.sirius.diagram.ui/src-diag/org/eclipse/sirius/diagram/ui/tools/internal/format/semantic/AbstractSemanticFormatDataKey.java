/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.format.semantic;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;

import com.google.common.base.Objects;

/**
 * Common behavior for Semantic*FormatDataKey classes.
 * 
 * @author dlecan
 */
public abstract class AbstractSemanticFormatDataKey implements FormatDataKey, Comparable<AbstractSemanticFormatDataKey> {

    /** URI Fragment of the element's target semantic element. */
    private final String semanticElementURIFragment;

    /**
     * Constructor.
     * 
     * @param semanticElement
     *            The element the create the key.
     */
    public AbstractSemanticFormatDataKey(final EObject semanticElement) {
        this.semanticElementURIFragment = EcoreUtil.getURI(semanticElement).fragment();
    }

    /**
     * Constructor.
     * 
     * @param uriFragment
     *            String to use to create the new key.
     */
    public AbstractSemanticFormatDataKey(final String uriFragment) {
        semanticElementURIFragment = uriFragment;
    }

    protected String getSemanticElementURIFragment() {
        return semanticElementURIFragment;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(semanticElementURIFragment);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj != null && getClass().isAssignableFrom(obj.getClass()) && getSemanticElementURIFragment() != null) {
            result = getId().equals(((FormatDataKey) obj).getId());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Key ID: " + getId(); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return getSemanticElementURIFragment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final AbstractSemanticFormatDataKey o) {
        return getId().compareTo(o.getId());
    }

}
