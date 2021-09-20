/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.image;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;

/**
 * This registry is intended to provide a set of {@link EAttribute} that contains html description and needs to be
 * identified by Sirius to transform base64 image into path to a file.
 * 
 * @author lfasani
 */
public final class RichTextAttributeRegistry {
    /**
     * Unique instance of the registry.
     */
    public static final RichTextAttributeRegistry INSTANCE = new RichTextAttributeRegistry();

    private Set<EAttribute> attributesContainingHtml = new LinkedHashSet<>();

    private RichTextAttributeRegistry() {
    }

    /**
     * Add the {@link EAttribute} in the registry.
     * 
     * @param attribute
     *            the attribute t.o add
     */
    public void add(EAttribute attribute) {
        attributesContainingHtml.add(attribute);
    }

    /**
     * Remove the {@link EAttribute} in the registry.
     * 
     * @param attribute
     *            the attribute t.o add
     */
    public void remove(EAttribute attribute) {
        attributesContainingHtml.add(attribute);
    }

    /**
     * Get the set of {@link EAttribute}.
     * 
     * @return the set of {@link EAttribute}.
     */
    public Set<EAttribute> getEAttributes() {
        return attributesContainingHtml;
    }
}
