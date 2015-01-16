/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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

import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXWrapper;
import org.eclipse.gmf.runtime.emf.core.resources.GMFHandler;
import org.eclipse.gmf.runtime.emf.core.resources.GMFLoad;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A specialization of {@link GMFLoad} to enable hooking into the XML parsing
 * for modeling migration.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 *
 */
public class AirdResourceXMILoad extends GMFLoad {

    private String loadedVersion;

    /**
     * Create a new {@link AirdResourceXMILoad}, suitable for on the fly
     * migration of .aird files.
     * 
     * @param loadedVersion
     *            the original version of the .aird file which is currently
     *            being loaded.
     * @param helper
     *            the xml helper to use during the load.
     */
    public AirdResourceXMILoad(String loadedVersion, XMLHelper helper) {
        super(helper);
        this.loadedVersion = loadedVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DefaultHandler makeDefaultHandler() {
        return new SAXWrapper(new AirdHandler(resource, helper, options));
    }

    /**
     * A specialization of the GMF handler to delegate to the file migration
     * service.
     * 
     * @author cedric
     *
     */
    class AirdHandler extends GMFHandler {

        public AirdHandler(XMLResource xmiResource, XMLHelper helper, Map options) {
            super(xmiResource, helper, options);
        }

        @Override
        public void endElement(String uri, String localName, String name) {
            super.endElement(uri, localName, name);
            RepresentationsFileMigrationService.getInstance().postXMLEndElement(objects.peek(), attribs, uri, localName, name, loadedVersion);
        }
    }
}
