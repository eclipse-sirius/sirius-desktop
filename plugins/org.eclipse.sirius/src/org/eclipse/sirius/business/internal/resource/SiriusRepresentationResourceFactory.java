/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;

import com.google.common.collect.Maps;

/**
 * A new Factory to create a specific resource for *.srm files. A srm file contains one or several Sirius
 * representations contained in its own resource.
 * 
 * @author Florian Barbin
 *
 */
public class SiriusRepresentationResourceFactory extends SiriusResourceFactory {

    @Override
    public Resource createResource(final URI uri) {
        final XMIResource resource = doCreateResource(uri);
        setOptions(resource);

        if (!resource.getEncoding().equals(XMI_ENCODING)) {
            resource.setEncoding(XMI_ENCODING);
        }

        return resource;
    }

    /**
     * Returns the implementation of the RepFileXMIResourceImpl to use.
     * 
     * @param uri
     *            the uri of the Resource
     * @return the implementation of the RepFileXMIResourceImpl to use
     */
    protected XMIResource doCreateResource(URI uri) {
        return new SiriusRepresentationXMIResourceImpl(uri);
    }

    /**
     * Sets the options to associate to the AirDResource.
     * 
     * @param resource
     *            the resource being loaded
     */
    private void setOptions(XMIResource resource) {

        final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
        final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
        /* default load options. */
        loadOptions.putAll(getDefaultLoadOptions());
        /* default save options. */
        saveOptions.putAll(getDefaultSaveOptions());

        loadOptions.put(XMLResource.OPTION_DEFER_ATTACHMENT, true);
        loadOptions.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
        loadOptions.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, false);
        loadOptions.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(true));
        loadOptions.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, Maps.newHashMap());

        loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        saveOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

        resource.getDefaultSaveOptions().putAll(saveOptions);
        resource.getDefaultLoadOptions().putAll(loadOptions);
    }
}
