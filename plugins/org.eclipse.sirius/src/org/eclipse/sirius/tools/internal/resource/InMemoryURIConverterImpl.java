/*******************************************************************************
 * Copyright (c) 2011, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tools.internal.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.sirius.business.api.query.URIQuery;

/**
 * {@link URIConverter} used for in memory Resource.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class InMemoryURIConverterImpl extends ExtensibleURIConverterImpl implements URIConverter {

    private URIHandler inMemoryURIHandler;

    /**
     * Default constructor.
     */
    public InMemoryURIConverterImpl() {
        super();
    }

    /**
     * Overridden to manage {@link URI} with scheme of type memory.
     * 
     * {@inheritDoc}
     */
    @Override
    public OutputStream createOutputStream(URI uri) throws IOException {
        OutputStream outputStream = null;
        if (URIQuery.INMEMORY_URI_SCHEME.equals(uri.scheme())) {
            outputStream = new ByteArrayOutputStream();
        } else {
            outputStream = super.createOutputStream(uri);
        }
        return outputStream;
    }

    /**
     * Create {@link InputStream} from in memory {@link URI}.
     * 
     * {@inheritDoc}
     */
    @Override
    public InputStream createInputStream(URI uri) throws IOException {
        InputStream inputStream = null;
        byte[] buffer = InMemoryResourceFactoryImpl.IN_MEMORY_BUFFERS.get(uri);
        if (buffer == null || buffer.length == 0) {
            buffer = new byte[InMemoryResourceFactoryImpl.BUFFER_SIZE];
            InMemoryResourceFactoryImpl.IN_MEMORY_BUFFERS.put(uri, buffer);
        }
        inputStream = new ByteArrayInputStream(buffer);
        return inputStream;
    }

    /**
     * Overridden to return a {@link InMemoryURIHandlerImpl} for
     * {@link InMemoryResourceImpl}.
     * 
     * {@inheritDoc}
     */
    @Override
    public URIHandler getURIHandler(URI uri) {
        URIHandler uriHandler = null;
        if (URIQuery.INMEMORY_URI_SCHEME.equals(uri.scheme())) {
            return getInMemoryURIHandler();
        } else {
            uriHandler = super.getURIHandler(uri);
        }
        return uriHandler;
    }

    private URIHandler getInMemoryURIHandler() {
        if (inMemoryURIHandler == null) {
            inMemoryURIHandler = new InMemoryURIHandlerImpl();
        }
        return inMemoryURIHandler;
    }

    /**
     * Overridden to test if the specified {@link URI} has a existing
     * corresponding Resource contents.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean exists(URI uri, Map<?, ?> options) {
        return InMemoryResourceFactoryImpl.IN_MEMORY_BUFFERS.containsKey(uri);
    }
}
