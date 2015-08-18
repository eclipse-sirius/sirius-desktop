/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.xml.sax.SAXException;

/**
 * A parser to check shortly some informations in a model file: is it XMI ?
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public class XMIModelFileSaxParser {

    /**
     * This will hold a reference to model file to analyze.
     */
    private final IFile modelFile;

    private boolean canLoad;

    /**
     * Constructor.
     * 
     * @param modelFile
     *            The file to parse
     */
    public XMIModelFileSaxParser(IFile modelFile) {
        this.modelFile = modelFile;
    }

    public boolean isLoadable() {
        return canLoad;
    }

    /**
     * Analyze this model file.
     * 
     * @param monitor
     *            A progress monitor
     */
    public void analyze(final IProgressMonitor monitor) {
        monitor.beginTask("", 1); //$NON-NLS-1$
        XMIModelFileHandler xmiModelFileHandler = new XMIModelFileHandler();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(modelFile.getRawLocation().toOSString()));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, xmiModelFileHandler);
        } catch (final XMIModelFileSaxParserNormalAbortException e) {
            // That is the normal exit for the parsing.
            canLoad = xmiModelFileHandler.isLoadable();
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
            monitor.done();
        }
    }
}
