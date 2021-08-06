/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.business.api.resource;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Preconditions;

/**
 * Represents a resource identified by its URI.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class ResourceDescriptor {

    private final URI resourceURI;

    /**
     * This constructor is used by EMF to deserialized the instances of this
     * class.
     * 
     * @param serializedString
     *            used to initialize the fields of the class. This string has
     *            been previously serialized with <code>toString()</code>.
     */
    public ResourceDescriptor(String serializedString) {
        this(URI.createURI(serializedString, true, URI.FRAGMENT_FIRST_SEPARATOR));
    }

    /**
     * This constructor allows initializing the characterizing fields of this
     * class.
     * 
     * @param uri
     *            uri of the resource
     */
    public ResourceDescriptor(URI uri) {
        resourceURI = Preconditions.checkNotNull(uri);
    }

    /**
     * Return the URI of the resource.
     * 
     * @return the uri of the resource
     */
    public URI getResourceURI() {
        return resourceURI;
    }

    @Override
    public String toString() {
        return resourceURI.toString();
    }

    @Override
    public int hashCode() {
        return resourceURI.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // URI does NOT overload equals method but the instance of URI is
        // unique so we can use the equals method.
        return obj instanceof ResourceDescriptor && this.resourceURI.equals(((ResourceDescriptor) obj).resourceURI);
    }
}
