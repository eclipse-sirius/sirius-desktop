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
package org.eclipse.sirius.tests.unit.api.semantic;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.scxml.DocumentRoot;
import org.eclipse.sirius.tests.sample.scxml.ScxmlScxmlType;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Tests with a semantic resource that conforms to a MM generated with an XSD.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class XSDSemanticResourceTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/semantic/xsd/";

    private String SEMANTIC_RESOURCE_NAME = "test.scxml";

    private String AIRD_RESOURCE_NAME = "representation.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        assertEquals("The created session should not have semantic resources", 0, session.getSemanticResources().size());

        // Set the preference PREF_SAVE_WHEN_NO_EDITOR to false in order to
        // verify that the session is dirty after semantic modification without
        // opened editor
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
    }

    /**
     * Test the semantic resource addition in a non modeling project.
     */
    public void testSemanticResourceAdditionInNonModelingProject() {
        addSemanticResourceInNonModelingProject();
        doTestSemanticResource(session.getSessionResource().getURI());
    }

    /**
     * Test the semantic resource addition in a modeling project
     * 
     * @throws Exception
     */
    public void testSemanticResourceDetectionInModelingProject() throws Exception {
        addSemanticResourceInModelingProject();
        doTestSemanticResource(session.getSessionResource().getURI());
    }

    /**
     * Test the Aird resource addition referencing the semantic resource.
     */
    public void testAirResourceAddition() {
        Resource airdResource = addAirdResource();
        doTestSemanticResource(airdResource.getURI());
    }

    /**
     * Do the test.
     * 
     * @param airdURI
     *            Aird resource uri containing the semantic model to test
     */
    private void doTestSemanticResource(URI airdURI) {
        // Check semantic modification
        checkSemanticModel(airdURI);

        // Save, close and reload the session
        saveCloseAndReloadSession();

        // Check semantic modification
        checkSemanticModel(airdURI);

        // Remove the semantic resource
        removeSemanticResource();
        assertEquals("The session should have 0 semantic resource", 0, session.getSemanticResources().size());
    }

    /**
     * Check the semantic model.
     * 
     * @param airdURI
     *            the Aird resource URI containing the semantic model to test
     */
    private void checkSemanticModel(URI airdURI) {
        waitUntilOneSemanticResource();
        checkCrossReferencer();

        Resource airdResource = session.getTransactionalEditingDomain().getResourceSet().getResource(airdURI, false);

        // Step 1: Check the DAnalysis content
        DAnalysis mainDAnalysis = (DAnalysis) airdResource.getContents().get(0);
        assertEquals("The analysis should have 1 semantic element", 1, mainDAnalysis.getModels().size());

        // Step 2: Check the type of the semantic element
        EObject semanticElement = mainDAnalysis.getModels().get(0);
        assertTrue("The semantic element should be an instance of ScxmlScxmlType", semanticElement instanceof ScxmlScxmlType);
        assertTrue("The container should be an instance of DocumentRoot", semanticElement.eContainer() instanceof DocumentRoot);

        // Step 3: Rename the semantic element and ensure that the session is
        // dirty
        renameSemanticElement((ScxmlScxmlType) semanticElement);
        assertTrue("The session should be dirty", session.getStatus() == SessionStatus.DIRTY);

        // Step 4: Save and ensure that the session is sync
        session.save(new NullProgressMonitor());
        assertTrue("The session should be dirty", session.getStatus() == SessionStatus.SYNC);
    }

    /**
     * Add an Aird resource.
     * 
     * @return the added Aird resource
     */
    private Resource addAirdResource() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, AIRD_RESOURCE_NAME);

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        URI airdResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + AIRD_RESOURCE_NAME, true);

        Resource airdResource = domain.getResourceSet().getResource(airdResourceURI, true);
        final DAnalysis dAnalysis = (DAnalysis) airdResource.getContents().get(0);

        Command addDAnalysisCmd = new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                ((DAnalysisSession) session).addReferencedAnalysis(dAnalysis);
            }
        };

        domain.getCommandStack().execute(addDAnalysisCmd);

        return airdResource;
    }

    /**
     * Add the semantic resource in a non modeling project
     */
    private void addSemanticResourceInNonModelingProject() {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        URI semanticResource4URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, true);
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResource4URI, new NullProgressMonitor());
        domain.getCommandStack().execute(addSemanticResourceCmd);
    }

    /**
     * Add the semantic resource a modeling project
     * 
     * @throws CoreException
     */
    private void addSemanticResourceInModelingProject() throws CoreException {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        ModelingProjectManager.INSTANCE.convertToModelingProject(project, new NullProgressMonitor());
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
    }

    /**
     * Remove the semantic resource.
     */
    private void removeSemanticResource() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Resource addedSemanticResource = session.getSemanticResources().iterator().next();
        Command removeSemanticResourceCmd = new RemoveSemanticResourceCommand(session, addedSemanticResource, new NullProgressMonitor(), true);
        domain.getCommandStack().execute(removeSemanticResourceCmd);
    }

    /**
     * Rename the semantic element.
     * 
     * @param semanticElement
     *            semantic element to rename
     */
    private void renameSemanticElement(final ScxmlScxmlType semanticElement) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                semanticElement.setName(semanticElement.getName() + "_renamed");
            }
        });
    }

    /**
     * Ensure that the cross referencer is on all semantic resources and their
     * content.
     */
    private void checkCrossReferencer() {
        ECrossReferenceAdapter crossReferencer = session.getSemanticCrossReferencer();

        // Call the method {@link ECrossReferenceAdapter.getTarget()} only to
        // force the initialization of the {@link LazyCrossReferencer}
        crossReferencer.getTarget();

        for (Resource semanticResource : session.getSemanticResources()) {
            // Check the resource
            assertTrue("The semantic resource '" + semanticResource.getURI() + "' should have the cross referencer", semanticResource.eAdapters().contains(crossReferencer));

            // Check the content
            Iterator<EObject> semanticElementIterator = semanticResource.getAllContents();
            while (semanticElementIterator.hasNext()) {
                EObject semanticElement = semanticElementIterator.next();
                assertTrue("The semantic element '" + EcoreUtil.getURI(semanticElement) + "' should have the cross referencer", semanticElement.eAdapters().contains(crossReferencer));
            }
        }
    }

    /**
     * Save, Close and reload the session.
     */
    private void saveCloseAndReloadSession() {
        // Save
        assertTrue("The session must be opened", session.isOpen());
        session.save(new NullProgressMonitor());
        assertEquals("The session should be sync", SessionStatus.SYNC, session.getStatus());

        // Close
        URI mainAnalysisURI = session.getSessionResource().getURI();
        session.close(new NullProgressMonitor());
        assertFalse("The session should be closed", session.isOpen());

        // Open
        session = SessionManager.INSTANCE.getSession(mainAnalysisURI, new NullProgressMonitor());
        assertFalse("The session should not be opened", session.isOpen());
        session.open(new NullProgressMonitor());
        assertTrue("The session should be opened", session.isOpen());
    }

    /**
     * Wait until there is one semantic resource.
     */
    private void waitUntilOneSemanticResource() {
        TestsUtil.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return session.getSemanticResources().size() == 1;
            }

            @Override
            public String getFailureMessage() {
                return "The session should have only one semantic resource";
            }
        });
    }
}
