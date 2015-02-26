/*******************************************************************************
 * Copyright (c) 2010-2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.session;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Tests for VP-3818 to check contract of
 * {@link Session#addSemanticResource(org.eclipse.emf.common.util.URI, IProgressMonitor)}
 * and
 * {@link Session#removeSemanticResource(org.eclipse.emf.ecore.resource.Resource, IProgressMonitor)}
 * .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SessionSemanticResourceTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/semantic/VP-3818";

    // resources dependencies schema:
    // ..R
    // ./ \
    // R2 R3
    // ....|
    // ....R4
    // R resource has an elementA with a reference to ElementB of resource
    // R2 and ElementC of resource R3.
    // R3 has a reference from ElementC to ElementD of resource R4.

    private String SEMANTIC_RESOURCE_1_NAME = "r.ecore";

    private String SEMANTIC_RESOURCE_2_NAME = "r2.ecore";

    private String SEMANTIC_RESOURCE_3_NAME = "r3.ecore";

    private String SEMANTIC_RESOURCE_4_NAME = "r4.ecore";

    private String SEMANTIC_RESOURCE_5_NAME = "r5.ecore";

    private String SEMANTIC_RESOURCE_6_NAME = "r6.ecore";

    private String SEMANTIC_RESOURCE_7_NAME = "r7.ecore";

    private String SEMANTIC_RESOURCE_8_NAME = "r8.ecore";

    private String SEMANTIC_RESOURCE_9_NAME = "r9.ecore";

    private String AIRD_RESOURCE_NAME = "representation.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_1_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_1_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_2_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_2_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_3_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_3_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_4_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_4_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_5_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_5_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_6_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_6_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_7_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_7_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_8_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_8_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_9_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_9_NAME);
        genericSetUp();

        assertEquals("The created session should not have semantic resources", 0, session.getSemanticResources().size());

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
    }

    @Override
    protected void tearDown() throws Exception {
        // Ensure that for each test, the cross referencer is on all semantic
        // resources.
        checkCrossReferencer();

        super.tearDown();
    }

    /**
     * Test the semantic resource addition of a single resource without other
     * referenced resources.
     */
    public void testSimpleSemanticResourceAddition() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        URI semanticResource4URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_4_NAME, true);
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResource4URI, new NullProgressMonitor());
        domain.getCommandStack().execute(addSemanticResourceCmd);

        assertEquals("The session should have only one semantic resource", 1, session.getSemanticResources().size());
        Resource semanticResource = session.getSemanticResources().iterator().next();
        assertEquals("The session should have only one semantic resource and the good one", semanticResource4URI, semanticResource.getURI());
    }

    /**
     * Test the semantic resource addition of a resource referencing other
     * semantic resources.
     */
    public void testTransitiveSemanticResourceAddition() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        URI semanticResource1URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_1_NAME, true);
        URI semanticResource2URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_2_NAME, true);
        URI semanticResource3URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_3_NAME, true);
        URI semanticResource4URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_4_NAME, true);
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResource1URI, new NullProgressMonitor());
        domain.getCommandStack().execute(addSemanticResourceCmd);

        assertEquals("The session should have 4 semantic resources", 4, session.getSemanticResources().size());
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource1URI));
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource2URI));
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource3URI));
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource4URI));
    }

    /**
     * Test the semantic resource addition of a resource referencing other
     * semantic resources with references to the initial added resource.
     */
    public void testTransitiveSemanticResourceAdditionWithLoop() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        URI semanticResource5URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_5_NAME, true);
        URI semanticResource6URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_6_NAME, true);
        URI semanticResource7URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_7_NAME, true);
        URI semanticResource8URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_8_NAME, true);
        URI semanticResource9URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_9_NAME, true);
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResource5URI, new NullProgressMonitor());
        domain.getCommandStack().execute(addSemanticResourceCmd);

        assertEquals("The session should have 5 semantic resources", 5, session.getSemanticResources().size());
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource5URI));
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource6URI));
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource7URI));
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource8URI));
        assertNotNull("The session should have this semantic resource", getSemanticResourceFromSession(semanticResource9URI));
    }

    /**
     * Test the semantic resource removal of a single resource without resources
     * in the session referencing this removed resource.
     */
    public void testSimpleSemanticResourceRemoval() {
        testSimpleSemanticResourceAddition();
        Resource addedSemanticResource = session.getSemanticResources().iterator().next();

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command removeSemanticResourceCmd = new RemoveSemanticResourceCommand(session, addedSemanticResource, new NullProgressMonitor(), true);
        domain.getCommandStack().execute(removeSemanticResourceCmd);

        assertEquals("The session shouldn't have semantic resource now", 0, session.getSemanticResources().size());
    }

    /**
     * Test the semantic resource removal of a single resource with resources in
     * the session referencing this removed resource.
     */
    public void testTransitiveSemanticResourceRemoval() {
        testTransitiveSemanticResourceAddition();
        URI semanticResource2URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_2_NAME, true);
        URI semanticResource3URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_3_NAME, true);
        URI semanticResource4URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_4_NAME, true);
        Resource semanticResource3 = getSemanticResourceFromSession(semanticResource3URI);

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command removeSemanticResourceCmd = new RemoveSemanticResourceCommand(session, semanticResource3, new NullProgressMonitor(), true);
        domain.getCommandStack().execute(removeSemanticResourceCmd);

        assertEquals("The session should have now only 2 semantic resources", 2, session.getSemanticResources().size());
        assertNotNull("The session should have the semantic resource r2.ecore", getSemanticResourceFromSession(semanticResource2URI));
        assertNotNull("The session should have the semantic resource r4.ecore", getSemanticResourceFromSession(semanticResource4URI));
    }

    /**
     * Test the semantic resource removal of a single resource with resources in
     * the session referencing this removed resource with references to the
     * initial removed resource.
     */
    public void testTransitiveSemanticResourceRemovalWithLoop() {
        testTransitiveSemanticResourceAdditionWithLoop();

        URI semanticResource5URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_5_NAME, true);
        Resource semanticResource5 = getSemanticResourceFromSession(semanticResource5URI);

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command removeSemanticResourceCmd = new RemoveSemanticResourceCommand(session, semanticResource5, new NullProgressMonitor(), true);
        domain.getCommandStack().execute(removeSemanticResourceCmd);

        assertEquals("The session shouldn't have semantic resource now", 0, session.getSemanticResources().size());
    }

    /**
     * Test the semantic resource removal of a single resource with resources in
     * the session referencing this removed resource after that the resource
     * dependencies graph has been changed with for example the removal of a
     * dependency from r6.ecore to r7.ecore to not have r6.ecore removed
     * transitively.
     */
    public void testTransitiveSemanticResourceRemovalAfterGraphResourceDependenciesRemoval() {
        testTransitiveSemanticResourceAdditionWithLoop();

        // Remove the resource dependency from r6.ecore to r7.ecore
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        URI semanticResource6URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_6_NAME, true);
        Resource semanticResource6 = getSemanticResourceFromSession(semanticResource6URI);
        EPackage r6EPackage = (EPackage) semanticResource6.getContents().get(0);
        EClass e6 = (EClass) r6EPackage.getEClassifiers().get(0);
        Command removeR6DependencyToR7Cmd = SetCommand.create(domain, e6.getEReferences().get(0), EcorePackage.Literals.ETYPED_ELEMENT__ETYPE, e6);
        domain.getCommandStack().execute(removeR6DependencyToR7Cmd);

        // Test the removal of r5.ecore
        URI semanticResource5URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_5_NAME, true);
        Resource semanticResource5 = getSemanticResourceFromSession(semanticResource5URI);
        Command removeSemanticResourceCmd = new RemoveSemanticResourceCommand(session, semanticResource5, new NullProgressMonitor(), true);
        domain.getCommandStack().execute(removeSemanticResourceCmd);

        assertEquals("The session should have only 1 semantic resource now", 1, session.getSemanticResources().size());
        assertNotNull("The session should have the semantic resource r6.ecore", getSemanticResourceFromSession(semanticResource6URI));
    }

    /**
     * Test the semantic resource removal of a single resource with resources in
     * the session referencing this removed resource after that the resource
     * dependencies graph has been changed with for example the addition of a
     * dependency from r2.ecore to r4.ecore to have r2.ecore removed
     * transitively.
     */
    public void testTransitiveSemanticResourceRemovalAfterGraphResourceDependenciesAddition() {
        testTransitiveSemanticResourceAddition();

        // Add a resource dependency from r2.ecore to r4.ecore
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        URI semanticResource2URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_2_NAME, true);
        URI semanticResource4URI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_4_NAME, true);
        Resource semanticResource2 = getSemanticResourceFromSession(semanticResource2URI);
        Resource semanticResource4 = getSemanticResourceFromSession(semanticResource4URI);
        EPackage r2EPackage = (EPackage) semanticResource2.getContents().get(0);
        EPackage r4EPackage = (EPackage) semanticResource4.getContents().get(0);
        EClass elementB = (EClass) r2EPackage.getEClassifiers().get(0);
        EClass elementD = (EClass) r4EPackage.getEClassifiers().get(0);
        Command addR2DependencyToR4Cmd = AddCommand.create(domain, elementB, EcorePackage.Literals.ECLASS__ESUPER_TYPES, elementD);
        domain.getCommandStack().execute(addR2DependencyToR4Cmd);

        // Test the removal of r4.ecore
        Command removeSemanticResourceCmd = new RemoveSemanticResourceCommand(session, semanticResource4, new NullProgressMonitor(), true);
        domain.getCommandStack().execute(removeSemanticResourceCmd);

        assertEquals("The session shouldn't have semantic resource now", 0, session.getSemanticResources().size());
    }

    /**
     * Test the DAnalysis addition referencing all other semantic resources.
     */
    public void testReferencedAnalysisAddition() {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + AIRD_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + AIRD_RESOURCE_NAME);

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

        assertEquals("The session should have 9 semantic resources", 9, session.getSemanticResources().size());
    }

    private Resource getSemanticResourceFromSession(URI semanticResourceURI) {
        Resource semanticResourceFromSession = null;
        for (Resource semanticResource : session.getSemanticResources()) {
            if (semanticResourceURI.equals(semanticResource.getURI())) {
                semanticResourceFromSession = semanticResource;
                break;
            }
        }
        return semanticResourceFromSession;
    }

    /**
     * Ensure that the cross referencer is on all semantic resources.
     */
    private void checkCrossReferencer() {
        ECrossReferenceAdapter crossReferencer = session.getSemanticCrossReferencer();
        for (Resource semanticResource : session.getSemanticResources()) {
            assertTrue("The resource '" + semanticResource.getURI() + "' should have the cross referencer", semanticResource.eAdapters().contains(crossReferencer));
        }
    }
}
