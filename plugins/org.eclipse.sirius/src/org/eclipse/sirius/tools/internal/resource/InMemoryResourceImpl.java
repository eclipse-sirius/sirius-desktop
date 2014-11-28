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
package org.eclipse.sirius.tools.internal.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

/**
 * {@link Resource} used for in memory Resource.
 * 
 * @author edugueperoux
 */
public class InMemoryResourceImpl extends BinaryResourceImpl implements Resource {

    private URIConverter uriConverter;

    /**
     * Default constructor.
     * 
     * @param uri
     *            the URI
     */
    public InMemoryResourceImpl(URI uri) {
        super(uri);
    }

    /**
     * Overridden to add the {@link InMemoryURIHandlerImpl}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
        addInMemoryURIHandler();
        super.doLoad(inputStream, options);
    }

    /**
     * Overridden to add the {@link InMemoryURIHandlerImpl}.
     * </br></br>
     * 
     * Note: use latest version, i.e. {@link Version#VERSION_1_1} defined in
     * through
     * {@link org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl#OPTION_VERSION}
     * option to have better of BinaryResourceImpl.
     * 
     * {@inheritDoc}
     */
    @Override
    public void save(Map<?, ?> options) throws IOException {
        addInMemoryURIHandler();
        super.save(options);
    }

    /**
     * Overridden to return a {@link InMemoryURIConverterImpl}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected URIConverter getURIConverter() {
        if (uriConverter == null) {
            uriConverter = new InMemoryURIConverterImpl();
        }
        return uriConverter;
    }

    /**
     * Overridden to save <code>outputstream</code> in
     * InMemoryResourceFactoryImpl.IN_MEMORY_BUFFERS.
     * 
     * {@inheritDoc}
     */
    @Override
    public void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
        super.doSave(outputStream, options);
        if (outputStream instanceof ByteArrayOutputStream) {
            ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
            byte[] buffer = byteArrayOutputStream.toByteArray();
            URI uri = getURI();
            InMemoryResourceFactoryImpl.IN_MEMORY_BUFFERS.put(uri, buffer);
        }
    }

    /**
     * Overridden to clear buffer entry in
     * InMemoryResourceFactoryImpl.IN_MEMORY_BUFFERS.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doUnload() {
        super.doUnload();
        URI uri = getURI();
        InMemoryResourceFactoryImpl.IN_MEMORY_BUFFERS.remove(uri);
    }

    /**
     * Add the InMemoryURIHandlerImpl to the list of default one to the
     * ExtensibleURIConverterImpl to have ResourceSet.getURIConverter().exists()
     * use the InMemoryURIHandlerImpl.
     */
    private void addInMemoryURIHandler() {
        List<URIHandler> uriHandlers = getResourceSet().getURIConverter().getURIHandlers();
        URIHandler uriHandler = getURIConverter().getURIHandler(getURI());
        if (!uriHandlers.contains(uriHandler)) {
            uriHandlers.add(0, uriHandler);
        }
    }
}
