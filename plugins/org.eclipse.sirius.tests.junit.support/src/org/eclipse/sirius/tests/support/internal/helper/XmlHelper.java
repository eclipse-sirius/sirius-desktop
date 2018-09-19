/**
 * Copyright (c) 2018 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.internal.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import junit.framework.TestCase;

/**
 * Helper to specific operations helper.
 * 
 * @author mporhel
 */
public final class XmlHelper {

    /**
     * Prevent instantiation.
     */
    private XmlHelper() {

    }

    /**
     * Retrieve the value of the corresponding qNAme in the resource with the given URI.
     * 
     * @param uri
     *            the uri of the resource to analyse
     * @param xmlQName
     *            the XML qName to look for.
     * @return the list of found values for the given qName.
     */
    public static Collection<String> getXmlAttributes(URI uri, final String xmlQName) {
        List<String> xmlValues = new ArrayList<>();

        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(uri);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    String value = attributes.getValue(xmlQName);
                    if (!StringUtil.isEmpty(value)) {
                        xmlValues.add(value);
                    }
                }
            });
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            failCheckData();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    failCheckData();
                }
            }
        }

        return xmlValues;
    }

    private static void failCheckData() {
        TestCase.fail("Check the test data, we should not fail here.");
    }

}
