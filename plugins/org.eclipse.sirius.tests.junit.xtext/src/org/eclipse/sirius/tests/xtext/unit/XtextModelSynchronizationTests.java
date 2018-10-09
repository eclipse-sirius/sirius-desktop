/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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

package org.eclipse.sirius.tests.xtext.unit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.xtext.internal.XtextResourceSetFactory;
import org.eclipse.sirius.tests.sample.xtext.statemachine.NamedElement;
import org.eclipse.sirius.tests.sample.xtext.statemachine.State;
import org.eclipse.sirius.tests.sample.xtext.statemachine.Statemachine;
import org.eclipse.sirius.tests.sample.xtext.statemachine.StatemachineFactory;
import org.eclipse.sirius.tests.sample.xtext.statemachine.design.Services;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.xtext.SiriusXtextTestsPlugin;

public class XtextModelSynchronizationTests extends SiriusDiagramTestCase {

    private static final String ORIGINAL_STATE_NAME = "CreatingPizza";

    private static final String ORIGINAL_STATE_DISPLAY_NAME = "Creating a new pizza";

    private static final String CHANGED_STATE_DISPLAY_NAME = "changed display name";

    private static final String PATH = "/data/unit/siriusXtextSync/";

    private static final String SEMANTIC_RESOURCE_NAME = "model.statemachine";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    @Override
    public void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusXtextTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Collections.<String> emptyList(), TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
    }

    /**
     * This test checks that changes made externally to a Sirius session on
     * Xtext file are correctly updated in the session.
     * 
     * @throws IOException
     * @throws CoreException
     * @throws InterruptedException
     */
    public void testXTextResourceInSessionAfterXtextFileChange() throws IOException, CoreException, InterruptedException {
        // Modify the xtext resource outside the session
        URI semanticResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, true);
        Resource semanticResource = new XtextResourceSetFactory().createResourceSet(semanticResourceURI).getResource(semanticResourceURI, true);
        NamedElement element = new Services().getElementByName(semanticResource, ORIGINAL_STATE_NAME);
        assertEquals(ORIGINAL_STATE_DISPLAY_NAME, element.getDisplayname());
        element.setDisplayname(CHANGED_STATE_DISPLAY_NAME);
        semanticResource.save(null);

        // Check the session status
        assertEquals("Bad session status", session.getStatus(), SessionStatus.SYNC);

        // check that the resource is correctly updated in the session
        Resource semanticResourceInSession = session.getSemanticResources().iterator().next();
        NamedElement elementInSession = new Services().getElementByName(semanticResourceInSession, ORIGINAL_STATE_NAME);
        assertEquals(CHANGED_STATE_DISPLAY_NAME, elementInSession.getDisplayname());

        // Modify the xtext resource outside the session using java.nio API
        modifyResourceOutsideOfTheSession(semanticResourceInSession, CHANGED_STATE_DISPLAY_NAME, ORIGINAL_STATE_DISPLAY_NAME);
        // refresh workspace projects
        ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
    }

    /**
     * This test checks that changes made on a Xtext resource in a Sirius
     * session are correctly serialized.
     * 
     * @throws IOException
     */
    public void testXTextFileAfterChangeFromSessionChange() throws IOException {
        // Modify the xtext resource in the session
        Resource semanticResourceInSession = session.getSemanticResources().iterator().next();
        NamedElement elementInSession = new Services().getElementByName(semanticResourceInSession, ORIGINAL_STATE_NAME);
        assertEquals(ORIGINAL_STATE_DISPLAY_NAME, elementInSession.getDisplayname());

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                NamedElement elementInSession = new Services().getElementByName(session.getSemanticResources().iterator().next(), ORIGINAL_STATE_NAME);
                elementInSession.setDisplayname(CHANGED_STATE_DISPLAY_NAME);

                State newState = StatemachineFactory.eINSTANCE.createState();
                newState.setName("newState");
                ((Statemachine) semanticResourceInSession.getContents().get(0)).getStates().add(newState);
            }
        });
        session.save(new org.eclipse.core.runtime.NullProgressMonitor());

        // check that the resource is correctly updated in the XText file with
        // java.nio.file API
        URI semanticResourceURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, true);
        checkFileContainsString(semanticResourceURI, CHANGED_STATE_DISPLAY_NAME);

        // check that the resource is correctly updated in the XText file with
        // EMF API
        Resource semanticResource = new XtextResourceSetFactory().createResourceSet(semanticResourceURI).getResource(semanticResourceURI, true);
        NamedElement element = new Services().getElementByName(semanticResource, ORIGINAL_STATE_NAME);
        assertEquals(CHANGED_STATE_DISPLAY_NAME, element.getDisplayname());
        assertNotNull("The state created in Sirius has not been serialized in XText file", new Services().getElementByName(semanticResource, "newState"));

        // Check the session status
        assertEquals("Bad session status", session.getStatus(), SessionStatus.SYNC);
    }

    private void checkFileContainsString(URI resourceUri, String stringToCheck) throws IOException {
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resourceUri.toPlatformString(true)));
        java.net.URI rawLocationURI = file.getRawLocationURI();
        java.nio.file.Path filePath = Paths.get(rawLocationURI);
        boolean present = false;
        try (Stream<String> lines = Files.lines(filePath)) { // necessary to
                                                             // close the stream
            present = lines.filter(line -> line.contains(stringToCheck)).findFirst().isPresent();
        }
        assertTrue("string " + stringToCheck + " not found in " + resourceUri, present);
    }

    protected void modifyResourceOutsideOfTheSession(Resource semanticResource, String oldValue, String newValue) throws IOException {
        final IFile fileToOpen = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(semanticResource.getURI().toPlatformString(true)));
        java.net.URI rawLocationURI = fileToOpen.getRawLocationURI();
        java.nio.file.Path filePath = Paths.get(rawLocationURI);

        try (Stream<String> lines = Files.lines(filePath)) { // necessary to
                                                             // close the stream
            List<String> replaced = lines.map(line -> line.replaceAll(oldValue, newValue)).collect(Collectors.toList());
            Files.write(filePath, replaced);
        }
    }
}
