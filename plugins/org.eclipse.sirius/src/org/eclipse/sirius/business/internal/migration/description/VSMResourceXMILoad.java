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
package org.eclipse.sirius.business.internal.migration.description;

import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMIHandler;
import org.eclipse.emf.ecore.xmi.impl.XMILoadImpl;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A specialization of {@link XMILoadImpl} to enable hooking into the XML
 * parsing for modeling migration.
 * 
 * @author mporhel
 *
 */
public class VSMResourceXMILoad extends XMILoadImpl {

    private String loadedVersion;

    /**
     * Create a new {@link DescriptionResourceXMILoad}, suitable for on the fly
     * migration of .aird files.
     * 
     * @param loadedVersion
     *            the original version of the .aird file which is currently
     *            being loaded.
     * @param helper
     *            the xml helper to use during the load.
     */
    public VSMResourceXMILoad(String loadedVersion, XMLHelper helper) {
        super(helper);
        this.loadedVersion = loadedVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DefaultHandler makeDefaultHandler() {
        return new VSMHandler(resource, helper, options);
    }

    /**
     * A specialization of the handler to delegate to the file migration
     * service.
     * 
     * @author mporhel
     */
    class VSMHandler extends SAXXMIHandler {

        VSMHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
            super(xmiResource, helper, options);
        }

        @Override
        public void endElement(String uri, String localName, String name) {
            super.endElement(uri, localName, name);
            VSMMigrationService.getInstance().postXMLEndElement(objects.peek(), attribs, uri, localName, name, loadedVersion);
        }
    }
}
