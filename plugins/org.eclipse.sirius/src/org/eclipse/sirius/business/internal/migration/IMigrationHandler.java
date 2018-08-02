/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.ext.base.Option;

/**
 * The interface used to call migration process.
 * 
 * @author jmallet
 */
public interface IMigrationHandler {

    /** The extension point id. */
    String ID = "org.eclipse.sirius.migrationHandler"; //$NON-NLS-1$

    /** The class attribute. */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Handle migration options and return an error diagnostic in case of migration version mismatch.
     * 
     * @param uri
     *            URI of the resource.
     * @param defaultLoadOptions
     *            option map used to to control load behavior.
     * @param defaultSaveOptions
     *            option map used to control save behavior.
     * 
     * @return migration Mismatch Diagnostic.
     */
    Diagnostic handleMigrationOptions(URI uri, Map<Object, Object> defaultLoadOptions, Map<Object, Object> defaultSaveOptions);

    /**
     * Create XML configuration that XMLResource uses to load resource.
     * 
     * @param options
     *            the load options.
     * @param resource
     *            the {@link XMLResource} use to load and save XML documents.
     * 
     * @return the XML configuration that XMLResource uses to load resource.
     */
    XMLLoad createXMLLoad(Map<?, ?> options, XMLResource resource);

    /**
     * Get map of option used to migrate fragment.
     * 
     * @param uriFragment
     *            the current fragment.
     * 
     * @return map of option used to migrate fragment.
     */
    Option<String> getOptionalRewrittenFragment(String uriFragment);

    /**
     * Create XML configuration.
     * 
     * @param resource
     *            the {@link XMLResource} use to load and save XML documents.
     * 
     * @return XML configuration.
     */
    XMLHelper createXMLHelper(XMLResource resource);

    /**
     * Add migration options on resource.
     * 
     * @param uri
     *            URI of the resource.
     * @param loadOptions
     *            The map of options to complete that is used to control load behavior.
     * @param saveOptions
     *            The map of options to complete that is used to control save behavior.
     */
    void addMigrationOptionIfNeeded(URI uri, Map<Object, Object> loadOptions, Map<Object, Object> saveOptions);

}
