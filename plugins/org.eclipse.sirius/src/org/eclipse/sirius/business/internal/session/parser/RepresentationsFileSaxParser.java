/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.xml.sax.SAXException;

import com.google.common.collect.Sets;

/**
 * A parser to collect shortly some informations in representations file.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RepresentationsFileSaxParser {

    /**
     * This will hold a reference to representations file to analyze.
     */
    private final IFile representationsFile;

    private final URI representationsFileURI;

    private Set<URI> referencedAnalysis = Sets.newLinkedHashSet();

    /**
     * Constructor.
     * 
     * @param representationsFile
     *            The file to parse
     */
    public RepresentationsFileSaxParser(IFile representationsFile) {
        this.representationsFile = representationsFile;
        this.representationsFileURI = URI.createPlatformResourceURI(representationsFile.getFullPath().toString(), true);
    }

    /**
     * Analyze this representations file.
     * 
     * @param monitor
     *            A progress monitor
     */
    public void analyze(final IProgressMonitor monitor) {
        monitor.beginTask("", 1); //$NON-NLS-1$
        RepresentationsFileHandler sessionHandler = new RepresentationsFileHandler(getRepresentationsFileURI());
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(representationsFile.getRawLocation().toOSString()));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, sessionHandler);
        } catch (final SiriusSaxParserNormalAbortException e) {
            // That is the normal exit for the parsing.
            referencedAnalysis = sessionHandler.getReferencedAnalysis();
        } catch (final SAXException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (final FileNotFoundException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (ParserConfigurationException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
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
    }

    /**
     * Get the representationsFile URI corresponding the parsed representations
     * file.
     * 
     * @return the representationsFileURI
     */
    public URI getRepresentationsFileURI() {
        return representationsFileURI;
    }

    /**
     * Get the analysis referenced by this analysis.
     * 
     * @return the referencedAnalysis
     */
    public Set<URI> getReferencedAnalysis() {
        return referencedAnalysis;
    }
}
