/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.sirius.business.api.query.URIQuery;

/**
 * A {@link URIHandler} for InMemoryResource.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class InMemoryURIHandlerImpl extends URIHandlerImpl implements URIHandler {

    /**
     * Overridden to tell we can handle only
     * {@link InMemoryURIQuery#INMEMORY_URI_SCHEME}.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canHandle(URI uri) {
        return URIQuery.INMEMORY_URI_SCHEME.equals(uri.scheme());
    }

    /**
     * Overridden to manage {@link URI} with scheme of type memory.
     * 
     * {@inheritDoc}
     */
    @Override
    public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
        OutputStream outputStream = null;
        if (URIQuery.INMEMORY_URI_SCHEME.equals(uri.scheme())) {
            outputStream = new ByteArrayOutputStream();
        } else {
            outputStream = super.createOutputStream(uri, options);
        }
        return outputStream;
    }

    /**
     * Create {@link InputStream} from in memory {@link URI}.
     * 
     * {@inheritDoc}
     */
    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
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
