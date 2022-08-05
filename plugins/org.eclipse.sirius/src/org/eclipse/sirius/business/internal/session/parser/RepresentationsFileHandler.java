/*******************************************************************************
 * Copyright (c) 2011, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session.parser;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.internal.image.ImageDependenciesAnnotationHelper;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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

    private Set<URI> usedModels = new LinkedHashSet<>();

    private Set<URI> referencedAnalysis = new LinkedHashSet<>();

    private Set<URI> semanticElements = new LinkedHashSet<>();

    private Set<String> imageProjectDependencies = new LinkedHashSet<>();

    private boolean inSemanticElement;

    private boolean inImageAnnotation;

    private boolean inAnnotationDetail;

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
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String,
     *      org.xml.sax.Attributes)
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
        } else if (qName.equals(ViewpointPackage.eINSTANCE.getDAnalysis_EAnnotations().getName())) {
            String hrefValue = attributes.getValue("source"); //$NON-NLS-1$
            if (ImageDependenciesAnnotationHelper.IMAGES_DEPENDENCIES_ANNOTATION_SOURCE_NAME.equals(hrefValue)) {
                inImageAnnotation = true;
            }
        } else if (qName.equals(ViewpointPackage.eINSTANCE.getDAnalysis_SemanticResources().getName())) {
            inSemanticElement = true;
        } else if (qName.equals(DescriptionPackage.eINSTANCE.getDAnnotationEntry_Details().getName())) {
            inAnnotationDetail = true;
        } else if (qName.equals(ViewpointPackage.eINSTANCE.getDAnalysis_OwnedViews().getName()) && (dAnalysisReferencedAnalysis || dAnalysisModels)) {
            throw new SiriusSaxParserNormalAbortException(Messages.XMIModelFileHandler_parsingStopedMsg);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (inSemanticElement) {
            String value = new String(ch, start, length);
            if (value != null) {
                semanticElements.add(URI.createURI(value).resolve(sessionURI).trimFragment());
            }
            inSemanticElement = false;
        } else if (inAnnotationDetail && inImageAnnotation) {
            String value = new String(ch, start, length);
            if (value != null) {
                String[] detailsSplit = value.split(ImageDependenciesAnnotationHelper.SEPARATOR);
                if (detailsSplit.length == 2) {
                    imageProjectDependencies.add(detailsSplit[1]);
                }
            }
        }
        inAnnotationDetail = false;
    }

    @Override
    public void endDocument() throws SAXException {
        throw new SiriusSaxParserNormalAbortException(Messages.XMIModelFileHandler_parsingStopedMsg);
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

    /**
     * Get the semantic elements.
     * 
     * @return
     */
    public Set<URI> getSemanticElements() {
        return semanticElements;
    }

    /**
     * Get the project containing images used by this analysis.
     * 
     * @return
     */
    public Set<String> getImageProjectDependencies() {
        return imageProjectDependencies;
    }
}
