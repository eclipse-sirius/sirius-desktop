/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.tests.support.internal.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.internal.query.ModelingProjectQuery;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Helper to get specific informations in aird file without EMF loading.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class AirdFileParser {

    private AirdFileParser() {
    }

    /**
     * Retrieve DAnalysis.semanticResources informations.
     * 
     * @param projectName
     *            Name of the project containing the aird file.
     * @return list of semantic resources string
     */
    public static List<String> parseSemanticResourcesTag(String projectName) {
        List<String> semanticResourcesTagContent = new ArrayList<>();

        // get the workspace
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IProject project = workspace.getRoot().getProject(projectName);

        // get the file
        ModelingProject modelingProject = new ModelingProject();
        modelingProject.setProject(project);
        IFile airdFile = new ModelingProjectQuery(modelingProject).getRepresentationFiles().get(0);

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(airdFile.getRawLocation().toOSString()));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            TestHandler testHandler = new TestHandler();
            saxParser.parse(inputStream, testHandler);
            semanticResourcesTagContent = testHandler.getSemanticResourcesTagContent();
        } catch (final SAXException e) {
            // An error occurs, the file cannot be loaded.
        } catch (final FileNotFoundException e) {
            // An error occurs, the file cannot be loaded.
        } catch (final IOException e) {
            // An error occurs, the file cannot be loaded.
        } catch (ParserConfigurationException e) {
            // An error occurs, the file cannot be loaded.
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    SiriusPlugin.getDefault().error(e.getMessage(), e);
                }
            }
        }
        return semanticResourcesTagContent;
    }

    private static class TestHandler extends DefaultHandler {
        private final String dAnalysisSemanticResourcesTag = ViewpointPackage.eINSTANCE.getDAnalysis_SemanticResources().getName();

        private List<String> semanticResourcesTagContent = new ArrayList<>();

        private String currentTagContent;

        public List<String> getSemanticResourcesTagContent() {
            return semanticResourcesTagContent;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals(dAnalysisSemanticResourcesTag)) {
                currentTagContent = dAnalysisSemanticResourcesTag;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            currentTagContent = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (currentTagContent != null) {
                if (currentTagContent.equals(dAnalysisSemanticResourcesTag)) {
                    semanticResourcesTagContent.add(new String(ch, start, length));
                }
            }
        }
    };

}
