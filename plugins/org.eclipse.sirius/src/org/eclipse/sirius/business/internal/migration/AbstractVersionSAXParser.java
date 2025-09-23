/*******************************************************************************
 * Copyright (c) 2012, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.migration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.sirius.business.internal.session.parser.SiriusSaxParserNormalAbortException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Parser to get the current version.
 * 
 * @author fbarbin
 * 
 */
public abstract class AbstractVersionSAXParser {

    URI resourceUri;

    /**
     * Constructor.
     * 
     * @param resourceUri
     *            the resource to parse version.
     */
    public AbstractVersionSAXParser(URI resourceUri) {
        this.resourceUri = resourceUri;
    }

    /**
     * Get the qualified name of element from which the version should be extracted.
     * 
     * @return the versioned element to detect.
     */
    protected abstract String getVersionedElementQualifiedName();

    /**
     * Parse meta-model version.
     * 
     * @param monitor
     *            A progress monitor
     * 
     * @return the version
     */
    public String getVersion(final IProgressMonitor monitor) {
        monitor.beginTask(Messages.AbstractVersionSAXParser_getVersionMsg, 1);
        VersionHandler versionHandler = new VersionHandler();
        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();

        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(resourceUri);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, versionHandler);
        } catch (final SiriusSaxParserNormalAbortException e) {
            // That is the normal exit for the parsing.
            return versionHandler.getVersion();
        } catch (FileNotFoundException e) {
            // do nothing
        } catch (ParserConfigurationException e) {
            // do nothing
        } catch (SAXException e) {
            // do nothing
        } catch (IOException e) {
            // do nothing
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    SiriusPlugin.getDefault().error(e.getMessage(), e);
                }
            }
            monitor.done();
        }
        return null;
    }

    /**
     * Handles version attribute value.
     * 
     * @author fbarbin
     * 
     */
    private final class VersionHandler extends DefaultHandler {
        private String version;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals(getVersionedElementQualifiedName())) {
                version = attributes.getValue("version"); //$NON-NLS-1$
                throw new SiriusSaxParserNormalAbortException(Messages.AbstractVersionSAXParser_stopParsingMsg);
            }
        }

        public String getVersion() {
            return version;
        }
    }
}
