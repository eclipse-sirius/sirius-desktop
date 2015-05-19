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

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.internal.helper.AirdFileParser;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;

import com.google.common.collect.Lists;

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

    private static final String OTHER_FOLDER_IN_WS = "SiriusLibrary";

    private static final String RESOURCE_URI_IN_OTHER_PROJECT = "platform:/resource/" + OTHER_FOLDER_IN_WS + "/" + MODEL_FILE_NAME_LIB;

    private static final String RESOURCE_URI_IN_OTHER_PROJECT2 = "platform:/resource/" + OTHER_FOLDER_IN_WS + "/" + MODEL_FILE_NAME_LIB2;

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), true);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), true);
    }

    /**
     * Check that the DAnalysis.semanticResources is correctly initialized from
     * DAnalysis.models
     * 
     * @throws Exception
     */
    public void testSemanticResourcesTagInAird() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME, MODEL_FILE_NAME2);
        copyFiles(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, OTHER_FOLDER_IN_WS + File.separator, MODEL_FILE_NAME_LIB, MODEL_FILE_NAME_LIB2);

        List<URI> semanticModelURIs = Lists.newArrayList(toURI(TEMPORARY_PROJECT_NAME + "/" + MODEL_FILE_NAME2, ResourceURIType.RESOURCE_PLATFORM_URI),
                toURI(OTHER_FOLDER_IN_WS + "/" + MODEL_FILE_NAME_LIB2, ResourceURIType.RESOURCE_PLATFORM_URI));
        // The representationsWithSemanticResourceTag.aird already contains
        // resourceDescriptor to MODEL_FILE_NAME(local) and
        // MODEL_FILE_NAME_LIB(in other project than aird)
        // The genericSetUp method will add the two other semantic resources
        // MODEL_FILE_NAME2(local) and MODEL_FILE_NAME_LIB2(in other project
        // than aird)
        genericSetUp(semanticModelURIs, Collections.<URI> emptyList(), true, toURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, ResourceURIType.RESOURCE_PLATFORM_URI));

        // Check that the serialization is properly done
        List<String> semanticResourcesTagContent = AirdFileParser.parseSemanticResourcesTag(TEMPORARY_PROJECT_NAME);
        assertEquals("Bad semantic resource tag number in aird file", 4, semanticResourcesTagContent.size());
        assertEquals("Bad semantic resource tag in aird file", MODEL_FILE_NAME, semanticResourcesTagContent.get(0));
        assertEquals("Bad semantic resource tag in aird file", RESOURCE_URI_IN_OTHER_PROJECT, semanticResourcesTagContent.get(1));
        assertEquals("Bad semantic resource tag in aird file", MODEL_FILE_NAME2, semanticResourcesTagContent.get(2));
        assertEquals("Bad semantic resource tag in aird file", RESOURCE_URI_IN_OTHER_PROJECT2, semanticResourcesTagContent.get(3));
    }
}
