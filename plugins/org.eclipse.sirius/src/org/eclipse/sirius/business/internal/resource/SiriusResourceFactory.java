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

import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import com.google.common.collect.Maps;

/**
 * An abstract class that bring common options between Aird Resource Factory and Sirius Representation File Resource
 * Factory.
 * 
 * @author florian barbin
 *
 */
public abstract class SiriusResourceFactory extends XMIResourceFactoryImpl {

    /**
     * XMI encoding.
     */
    protected static final String XMI_ENCODING = "UTF-8"; //$NON-NLS-1$

    /**
     * default load options.
     */
    protected static final Map<Object, Object> DEFAULT_LOAD_OPTIONS = Maps.newHashMap();

    /**
     * default save options.
     */
    protected static final Map<Object, Object> DEFAULT_SAVE_OPTIONS = Maps.newHashMap();
    static {

        XMIResource resource = new XMIResourceImpl();

        // default load options.
        DEFAULT_LOAD_OPTIONS.putAll(resource.getDefaultLoadOptions());
        DEFAULT_LOAD_OPTIONS.put(XMIResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);

        // default save options.
        DEFAULT_SAVE_OPTIONS.putAll(resource.getDefaultSaveOptions());
        DEFAULT_SAVE_OPTIONS.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
        DEFAULT_SAVE_OPTIONS.put(XMIResource.OPTION_PROCESS_DANGLING_HREF, XMIResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
        DEFAULT_SAVE_OPTIONS.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
        DEFAULT_SAVE_OPTIONS.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
        DEFAULT_SAVE_OPTIONS.put(XMIResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE);
        DEFAULT_SAVE_OPTIONS.put(XMIResource.OPTION_SKIP_ESCAPE_URI, Boolean.FALSE);
        DEFAULT_SAVE_OPTIONS.put(XMIResource.OPTION_ENCODING, XMI_ENCODING);
    }

    /**
     * Get default load options.
     * 
     * @return the default load options
     */
    public static Map<Object, Object> getDefaultLoadOptions() {
        return DEFAULT_LOAD_OPTIONS;
    }

    /**
     * Get default save options.
     * 
     * @return the default save options
     */
    public static Map<Object, Object> getDefaultSaveOptions() {
        return DEFAULT_SAVE_OPTIONS;
    }
}
