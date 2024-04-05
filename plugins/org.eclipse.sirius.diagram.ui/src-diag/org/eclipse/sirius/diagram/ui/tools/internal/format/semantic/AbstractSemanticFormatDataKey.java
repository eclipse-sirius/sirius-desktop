/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.format.semantic;

import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.ui.tools.api.format.FormatDataKey;

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

    @Override
    public int hashCode() {
        return Objects.hash(semanticElementURIFragment);
    }

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

    @Override
    public String toString() {
        return "Key ID: " + getId(); //$NON-NLS-1$
    }

    @Override
    public String getId() {
        return getSemanticElementURIFragment();
    }

    @Override
    public int compareTo(final AbstractSemanticFormatDataKey o) {
        return getId().compareTo(o.getId());
    }

}
