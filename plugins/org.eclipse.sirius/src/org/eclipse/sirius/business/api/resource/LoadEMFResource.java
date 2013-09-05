/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;

import org.eclipse.sirius.business.internal.resource.parser.XMIModelFileSaxParser;

/**
 * Runnable to load an EMF resource.
 * 
 * @author mchauvin
 * @since 2.9
 */
public class LoadEMFResource implements Runnable {

    private final IFile file;

    private Resource res;

    private ResourceSet set;

    /**
     * Constructor.
     * 
     * @param set
     *            the resource set in which to load the file
     * @param file
     *            the file containing the resource to load.
     */
    public LoadEMFResource(final ResourceSet set, final IFile file) {
        this.file = file;
        this.set = set;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {

        try {
            final URI uri = URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
            if (shouldBeAbleToLoad()) {
                res = set.getResource(uri, true);
                if (!res.getErrors().isEmpty()) {
                    error();
                }
                res.load(getOptions());
            }
        } catch (final WrappedException e) {
            error();
            // CHECKSTYLE:OFF
        } catch (final RuntimeException e) {
            error();
            // CHECKSTYLE:ON
        } catch (final IOException e) {
            error();
        }
    }

    private Map<?, ?> getOptions() {
        /* options to load fast an xml model */
        final Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
        options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(true));
        return options;
    }

    private void error() {
        if (res != null) {
            res.unload();
            set.getResources().remove(res);
            res = null;
        }
    }

    /**
     * Returns the loaded resource.
     * 
     * @return the loaded resource, or <code>null</code> if it could not be
     *         loaded
     */
    public Resource getLoadedResource() {
        return res;
    }

    private boolean shouldBeAbleToLoad() {
        final URI uri = URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
        boolean ableToLoad = true;
        // Is there any specific resource factori for the given uri ?
        if (set.getResourceFactoryRegistry().getFactory(uri, null).getClass() == XMIResourceFactoryImpl.class) {
            // The default XMI resource factory will be used, check we have XMI
            // in the file and all namespace metamodels are known.
            XMIModelFileSaxParser modelFileSaxParser = new XMIModelFileSaxParser(file);
            modelFileSaxParser.analyze(new NullProgressMonitor());
            ableToLoad = modelFileSaxParser.isLoadable();
        }
        return ableToLoad;
    }
}
