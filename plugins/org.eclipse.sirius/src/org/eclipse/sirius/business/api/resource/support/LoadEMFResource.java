/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.resource.support;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategyRegistry;
import org.eclipse.sirius.business.internal.resource.parser.XMIModelFileSaxParser;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Runnable to load an EMF resource.
 * 
 * @author mchauvin
 * @since 0.9.0
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
        this.file = Objects.requireNonNull(file);
        this.set = Objects.requireNonNull(set);
    }

    @Override
    public void run() {
        String path = file.getFullPath().toOSString();
        try {
            final URI uri = URI.createPlatformResourceURI(path, true);
            if (shouldBeAbleToLoad()) {
                res = set.getResource(uri, true);
                if (!res.getErrors().isEmpty()) {
                    // The file was previously loaded with errors. It might have
                    // changed, unload it before reload.
                    res.unload();
                }
                res.load(getOptions());
            }
            // CHECKSTYLE:OFF
        } catch (final RuntimeException e) {
            error(path, e);
            // CHECKSTYLE:ON
        } catch (final IOException e) {
            error(path, e);
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

    private void error(String path, Exception e) {
        SiriusPlugin.getDefault().error(MessageFormat.format(Messages.LoadEMFResource_loadingErrorMsg, path), e);
        unload();
    }

    private void unload() {
        if (res != null) {
            ResourceStrategyRegistry.getInstance().unloadAtResourceSetDispose(res, new NullProgressMonitor());
            set.getResources().remove(res);
            res = null;
        }
    }

    /**
     * Returns the loaded resource.
     * 
     * @return the loaded resource, or <code>null</code> if it could not be loaded
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
            // in the file.
            XMIModelFileSaxParser modelFileSaxParser = new XMIModelFileSaxParser(file);
            modelFileSaxParser.analyze(new NullProgressMonitor());
            ableToLoad = modelFileSaxParser.isLoadable();
        }
        return ableToLoad;
    }
}
