/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.resource;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Predicate;

/**
 * A Predicate checking for a given file extension.
 * 
 * @author cbrun
 */
public class ResourceFileExtensionPredicate implements Predicate<Resource> {

    private final String fileExtension;

    private final boolean shouldBePlatformResource;

    /**
     * Default constructor.
     * 
     * @param fileExtension
     *            The extension of the file to check.
     */
    public ResourceFileExtensionPredicate(final String fileExtension) {
        this(fileExtension, true);
    }

    /**
     * Default constructor.
     * 
     * @param fileExtension
     *            The extension of the file to check.
     * @param shouldBePlatformResource
     *            true if we must check that this resource is a platform
     *            resource, false otherwise
     */
    public ResourceFileExtensionPredicate(final String fileExtension, final boolean shouldBePlatformResource) {
        this.fileExtension = fileExtension;
        this.shouldBePlatformResource = shouldBePlatformResource;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.google.common.base.Predicate#apply(java.lang.Object)
     */
    public boolean apply(Resource input) {
        boolean result = true;
        if (shouldBePlatformResource) {
            result = input.getURI().isPlatformResource();
        }
        return result && input.getURI() != null && input.getURI().fileExtension() != null && input.getURI().fileExtension().equals(fileExtension);
    }

}
