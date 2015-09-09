/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
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

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLContentHandlerImpl.XMI;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.Messages;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * An event handler to detect xmi model files.
 * 
 * It will inspect the first element to look for XMI namespace.
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
            throw new XMIModelFileSaxParserNormalAbortException(Messages.XMIModelFileHandler_parsingStopedMsg);
        }

        firstElementRead = true;
        boolean isXMI = lookForXMI(attributes);
        canLoad = isXMI;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (firstElementRead) {
            throw new XMIModelFileSaxParserNormalAbortException(Messages.XMIModelFileHandler_parsingStopedMsg);
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
        throw new XMIModelFileSaxParserNormalAbortException(Messages.XMIModelFileHandler_stopTheParsingMsg);
    }

    private boolean lookForXMI(Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            String attributeName = attributes.getQName(i);

            if (attributeName.startsWith(XMLResource.XML_NS)) {
                String value = attributes.getValue(i);

                if (StringUtil.isEmpty(value)) {
                    continue;
                }

                if (XMI.isXMINamespace(value)) {
                    return true;
                }
            }
        }
        return false;
    }

}
