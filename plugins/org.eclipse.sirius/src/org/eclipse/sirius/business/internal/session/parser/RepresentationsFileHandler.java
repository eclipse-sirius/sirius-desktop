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

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Sets;

/**
 * An event handler for representations files.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class RepresentationsFileHandler extends DefaultHandler {
    /**
     * True if at least one analysis models has been parse.
     */
    private boolean dAnalysisModels;

    /**
     * True if at least one analysis models has been parse.
     */
    private boolean dAnalysisReferencedAnalysis;

    private final URI sessionURI;

    private Set<URI> usedModels = Sets.newLinkedHashSet();

    private Set<URI> referencedAnalysis = Sets.newLinkedHashSet();

    /**
     * Constructor.
     * 
     * @param sessionURI
     *            The URI of the current representations file to parse
     */
    public RepresentationsFileHandler(URI sessionURI) {
        this.sessionURI = sessionURI;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(ViewpointPackage.eINSTANCE.getDAnalysis_Models().getName())) {
            dAnalysisModels = true;
            String hrefValue = attributes.getValue("href"); //$NON-NLS-1$
            if (hrefValue != null) {
                usedModels.add(URI.createURI(hrefValue).resolve(sessionURI).trimFragment());
            }
        } else if (qName.equals(ViewpointPackage.eINSTANCE.getDAnalysis_ReferencedAnalysis().getName())) {
            dAnalysisReferencedAnalysis = true;
            String hrefValue = attributes.getValue("href"); //$NON-NLS-1$
            if (hrefValue != null) {
                referencedAnalysis.add(URI.createURI(hrefValue).resolve(sessionURI).trimFragment());
            }
        } else if (qName.equals(ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews().getName()) && (dAnalysisReferencedAnalysis || dAnalysisModels)) {
            throw new SiriusSaxParserNormalAbortException("All needed informations have been reached. Stop the parsing.");
        }
    }

    /**
     * Get the semantic models used by this analysis.
     * 
     * @return the usedModels
     */
    public Set<URI> getUsedModels() {
        return usedModels;
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
