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
package org.eclipse.sirius.tests.unit.api.resource;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.internal.helper.AirdFileParser;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Ensures that DAnalysis.semanticResources is
 * <ul>
 * <li>correctly serialized with good relative/absolute path</li>
 * <li>correctly deserialized to work with absolute URI</li>
 * </ul>
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SemanticResourceURIInAirdTests extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/resource/";

    private static final String REPRESENTATIONS_FILE_NAME = "representationsWithSemanticResourceTag.aird";

    private static final String MODEL_FILE_NAME = "SemRes1.ecore";

    private static final String MODEL_FILE_NAME2 = "SemRes2.ecore";

    private static final String MODEL_FILE_NAME_LIB = "LibrarySemRes1.ecore";

    private static final String MODEL_FILE_NAME_LIB2 = "LibrarySemRes2.ecore";

    private static final String OTHER_PROJECT_IN_WS = "SiriusLibrary";

    private static final String RESOURCE_URI_IN_OTHER_PROJECT = "platform:/resource/" + OTHER_PROJECT_IN_WS + "/" + MODEL_FILE_NAME_LIB;

    private static final String RESOURCE_URI_IN_OTHER_PROJECT2 = "platform:/resource/" + OTHER_PROJECT_IN_WS + "/" + MODEL_FILE_NAME_LIB2;

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME, MODEL_FILE_NAME2);
        copyFiles(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, OTHER_PROJECT_IN_WS + File.separator, MODEL_FILE_NAME_LIB, MODEL_FILE_NAME_LIB2);

        genericSetUp(Collections.<URI> emptyList(), Collections.<URI> emptyList(), true, toURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, ResourceURIType.RESOURCE_PLATFORM_URI));
    }

    /**
     * Check that the DAnalysis.semanticResources is correctly initialized from
     * DAnalysis.models
     * 
     * @throws Exception
     */
    /**
     * @throws Exception
     */
    public void testSemanticResourcesTagInAird() throws Exception {
        // Check that representationsWithSemanticResourceTag.aird content is
        // correct after open
        List<String> semanticResourcesTagContent = AirdFileParser.parseSemanticResourcesTag(TEMPORARY_PROJECT_NAME);
        assertEquals("Bad semantic resource tag number in aird file", 2, semanticResourcesTagContent.size());
        assertEquals("Bad semantic resource tag in aird file", MODEL_FILE_NAME, semanticResourcesTagContent.get(0));
        assertEquals("Bad semantic resource tag in aird file", RESOURCE_URI_IN_OTHER_PROJECT, semanticResourcesTagContent.get(1));

        // Add two other semantic resources : MODEL_FILE_NAME2(local) and
        // MODEL_FILE_NAME_LIB2(in other project than aird)
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, toURI(TEMPORARY_PROJECT_NAME + "/" + MODEL_FILE_NAME2, ResourceURIType.RESOURCE_PLATFORM_URI),
                new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        addSemanticResourceCmd = new AddSemanticResourceCommand(session, toURI(OTHER_PROJECT_IN_WS + "/" + MODEL_FILE_NAME_LIB2, ResourceURIType.RESOURCE_PLATFORM_URI), new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        session.save(new NullProgressMonitor());

        // Check that the serialization is properly done
        semanticResourcesTagContent = AirdFileParser.parseSemanticResourcesTag(TEMPORARY_PROJECT_NAME);
        assertEquals("Bad semantic resource tag number in aird file", 4, semanticResourcesTagContent.size());
        assertEquals("Bad semantic resource tag in aird file", MODEL_FILE_NAME, semanticResourcesTagContent.get(0));
        assertEquals("Bad semantic resource tag in aird file", RESOURCE_URI_IN_OTHER_PROJECT, semanticResourcesTagContent.get(1));
        assertEquals("Bad semantic resource tag in aird file", MODEL_FILE_NAME2, semanticResourcesTagContent.get(2));
        assertEquals("Bad semantic resource tag in aird file", RESOURCE_URI_IN_OTHER_PROJECT2, semanticResourcesTagContent.get(3));

        // Check that all ResourceDescriptor URI are in absolute path
        DAnalysis dAnalysis = new AirDResouceQuery((AirdResource) session.getSessionResource()).getDAnalysis().get();
        for (ResourceDescriptor rd : dAnalysis.getSemanticResources()) {
            assertTrue("Resource descriptor " + rd + " does not contain a absolute URI", rd.getResourceURI().isPlatformResource());
        }
    }
}
