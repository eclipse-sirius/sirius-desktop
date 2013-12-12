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
package org.eclipse.sirius.business.internal.resource.parser;

import java.io.IOException;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLContentHandlerImpl.XMI;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Sets;

/**
 * An event handler to detect xmi model files.
 * 
 * It will inspect the first element to look for XMI namespace and check that
 * other namespace attributes reference knwon meta models.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public class XMIModelFileHandler extends DefaultHandler {
    /**
     * True if at least one analysis models has been parse.
     */
    private boolean firstElementRead;

    private boolean canLoad;

    public boolean isLoadable() {
        return canLoad;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (firstElementRead) {
            throw new XMIModelFileSaxParserNormalAbortException("All needed informations have been reached. Stop the parsing.");
        }

        firstElementRead = true;
        Set<String> nsValues = Sets.newHashSet();
        boolean isXMI = lookForXMIAndCollectNamespaces(attributes, nsValues);
        canLoad = isXMI && allNamespacesAreKnownMetaModels(nsValues);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (firstElementRead) {
            throw new XMIModelFileSaxParserNormalAbortException("All needed informations have been reached. Stop the parsing.");
        }

        super.endElement(uri, localName, qName);
    }

    /**
     * Trying to access external element, stop resolution.
     * 
     * {@inheritDoc}
     */
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws IOException, SAXException {
        throw new XMIModelFileSaxParserNormalAbortException("We try to access external elements. Stop the parsing.");
    }

    private boolean lookForXMIAndCollectNamespaces(Attributes attributes, Set<String> nsValues) {
        boolean isXMI = false;
        for (int i = 0; i < attributes.getLength(); i++) {
            String attributeName = attributes.getQName(i);

            if (attributeName.startsWith(XMLResource.XML_NS)) {
                String value = attributes.getValue(i);

                if (StringUtil.isEmpty(value)) {
                    continue;
                }

                boolean xmiNamespace = XMI.isXMINamespace(value);
                isXMI = isXMI || xmiNamespace;

                if (!value.startsWith(XMLResource.XML_SCHEMA_URI) && !xmiNamespace) {
                    nsValues.add(value);
                }
            }
        }
        return isXMI;
    }

    private boolean allNamespacesAreKnownMetaModels(Set<String> nsValues) {
        boolean allKnwonMM = true;
        if (!nsValues.isEmpty()) {
            Registry workspaceEPackageRegistry = DslCommonPlugin.getDefault().getWorkspaceEPackageRegistry();
            for (String val : nsValues) {
                allKnwonMM = allKnwonMM && workspaceEPackageRegistry.get(val) != null;
            }
        }
        return allKnwonMM;
    }
}
