/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.control;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.control.SiriusUncontrolCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Sets;

/**
 * Tests ensuring that Hierarchical Control/Uncontrol operations works as
 * expected.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class HierarchicalControlTest extends AbstractHierarchicalControlTest {
    private static final String[] NON_CONTROLED_RESOURCES = new String[] { "/doremi-2436/My.ecore", "/doremi-2436/My.aird" };

    private static final String[] CONTROLED_RESOURCES = new String[] { "/doremi-2436/My_controlled.ecore", "/doremi-2436/My_controlled.aird", "/doremi-2436/My_B.aird", "/doremi-2436/My_B.ecore",
            "/doremi-2436/My_C.ecore", "/doremi-2436/My_C.aird" };

    private EObject rootA;

    private EObject rootB;

    private EObject rootC;

    private DAnalysis mainAird;

    private DRepresentation representationB;

    private DRepresentation representationC;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        // We modify the Sirius preferences to make sure that aird are
        // created during a control operation, even if there is no
        // representation to associate to it.
        changeSiriusPreference(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);

        super.setUp();
    }

    /**
     * Ensures that the following use case works as expected :
     * <ul>
     * <li>We have a DAnalysis My.aird referencing a semantic resource with some
     * hierarchy (A <>- B <>- C) and containing 3 representations (one on A, one
     * on B, one on C);</li>
     * <li>control B with representationB => test references and representation
     * moving ;</li>
     * <li>control C with representation C => test references and representation
     * moving.</li>
     * </ul>
     * 
     * @throws Exception
     * 
     */
    public void testControlBAndThenCWithTheirRepresentations() throws Exception {
        // Step 1 : opening a session on My.aird (referecing My.ecore)
        openNonControlledSession();

        // Step 2 : we make sur that files My.ecore and My.aird exists
        // and that the aird has not be controled yet
        assertFilesExist("/doremi-2436/My.ecore", "/doremi-2436/My.aird");
        assertFilesDoNotExist("/doremi-2436/My.aird/My_B.ecore", "/doremi-2436/My_B.aird");
        assertFilesDoNotExist("/doremi-2436/My.aird/My_C.ecore", "/doremi-2436/My_C.aird");

        // Step 3 : controlling B with its representation
        DAnalysis bAird = controlB();

        // Step 4 : controlling C with its representation
        final URI controlledModelUriC = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_C.ecore", true);
        final URI controlledAirdUriC = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_C.aird", true);
        siriusControl(rootC, controlledModelUriC, Collections.singleton(representationC), controlledAirdUriC);

        assertFilesExist("/doremi-2436/My.ecore", "/doremi-2436/My.aird", "/doremi-2436/My_B.ecore", "/doremi-2436/My_B.aird", "/doremi-2436/My_C.ecore", "/doremi-2436/My_C.aird");
        final DAnalysis cAird = (DAnalysis) session.getTransactionalEditingDomain().getResourceSet().getResource(controlledAirdUriC, true).getContents().iterator().next();

        // Step 5 : ensure that this control operation did what we expected
        // Ensure that the semantic resource has correctly been controlled
        Resource bResource = ((BasicEObjectImpl) rootB).eDirectResource();
        Resource cResource = ((BasicEObjectImpl) rootC).eDirectResource();
        assertNotNull(cResource);
        assertNotSame(bResource, cResource);
        assertTrue(AdapterFactoryEditingDomain.isControlled(rootC));

        EObject semanticContainerForC = new EObjectQuery(rootC.eContents().iterator().next()).getResourceContainer();
        EObject semanticHighLevelContainerForC = EcoreUtil.getRootContainer(rootC);
        assertEquals(rootC, semanticContainerForC);
        assertEquals(rootA, semanticHighLevelContainerForC);

        // Ensure that the main Aird now references the controled aird
        // An so does bAird
        mainAird.getReferencedAnalysis().contains(cAird);
        bAird.getReferencedAnalysis().contains(cAird);

        // Ensure that Representation C has correctly been moved
        assertEquals(cAird, representationC.eContainer().eContainer());

        // Ensure that the controlled Aird is its own container
        EObject rootDAnalysis = new EObjectQuery(cAird).getResourceContainer();
        assertEquals(cAird, rootDAnalysis);
    }

    /**
     * Ensures that the following use case works as expected :
     * <ul>
     * <li>We have a DAnalysis My.aird referencing a semantic resource with some
     * hierarchy (A <>- B <>- C) and containing 3 representations (one on A, one
     * on B, one on C);</li>
     * <li>control B with representationB => test references and representation
     * moving ;</li>
     * <li>control C without its representation C => test references.</li>
     * </ul>
     * 
     * @throws Exception
     * 
     */
    public void testControlBWithItsRepresentationAndThenControlC() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * Can cause a timeout.
             */
            return;
        }
        // Step 1 : opening a session on My.aird (referecing My.ecore)
        openNonControlledSession();

        // Step 2 : we make sur that files My.ecore and My.aird exists
        // and that the aird has not be controled yet
        assertFilesExist("/doremi-2436/My.ecore", "/doremi-2436/My.aird");
        assertFilesDoNotExist("/doremi-2436/My.aird/My_B.ecore", "/doremi-2436/My_B.aird");
        assertFilesDoNotExist("/doremi-2436/My.aird/My_C.ecore", "/doremi-2436/My_C.aird");

        // Step 3 : controlling B with its representation
        DAnalysis bAird = controlB();

        // Step 4 : controlling C without its representation
        final URI controlledModelUriC = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_C.ecore", true);
        final URI controlledAirdUriC = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_C.aird", true);
        siriusControl(rootC, controlledModelUriC, new HashSet<DRepresentation>(), controlledAirdUriC);

        assertFilesExist("/doremi-2436/My.ecore", "/doremi-2436/My.aird", "/doremi-2436/My_B.ecore", "/doremi-2436/My_B.aird", "/doremi-2436/My_C.ecore", "/doremi-2436/My_C.aird");
        final DAnalysis cAird = (DAnalysis) session.getTransactionalEditingDomain().getResourceSet().getResource(controlledAirdUriC, true).getContents().iterator().next();

        // Ensure that the semantic resource has correctly been controlled
        Resource bResource = ((BasicEObjectImpl) rootB).eDirectResource();
        Resource cResource = ((BasicEObjectImpl) rootC).eDirectResource();
        assertNotSame(bResource, cResource);
        assertTrue(AdapterFactoryEditingDomain.isControlled(rootC));

        EObject semanticContainerForC = new EObjectQuery(rootC.eContents().iterator().next()).getResourceContainer();
        EObject semanticHighLevelContainerForC = EcoreUtil.getRootContainer(rootC);
        assertEquals(rootC, semanticContainerForC);
        assertEquals(rootA, semanticHighLevelContainerForC);

        // Ensure that the main Aird now references the controled aird
        // And so does bAird
        mainAird.getReferencedAnalysis().contains(cAird);
        bAird.getReferencedAnalysis().contains(cAird);

        // Ensure that Representation C has not been moved (stays in B)
        assertEquals(bAird, representationC.eContainer().eContainer());

        // Ensure that the controlled Aird is its own container
        EObject rootDAnalysis = new EObjectQuery(cAird).getResourceContainer();
        assertEquals(cAird, rootDAnalysis);
    }

    /**
     * From a set of controlled representations (My_controlled refencing A, My_B
     * referencing B and My_C referencing C) :
     * <ul>
     * <li>Uncontrols C</li>
     * <li>Ensures that representations created on C have been moved to B</li>
     * <li>Ensures that the main Aird has not been modified (resource).</li>
     * </ul>
     * 
     * @throws Exception
     * 
     */
    public void testUncontrolC() throws Exception {
        // Step 1 : load the controlled airds and semantic resources
        copyFilesToTestProject(CONTROLED_RESOURCES);

        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_controlled.ecore"), Collections.<String> emptySet(), TEMPORARY_PROJECT_NAME + "/doremi-2436/My_controlled.aird");

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final Resource modelResource = session.getSemanticResources().iterator().next();
        final Resource diagramResource = session.getSessionResource();

        rootA = modelResource.getContents().get(0);
        rootB = findPackageNamed("B", modelResource.getContents().get(0));
        rootC = findPackageNamed("C", modelResource.getContents().get(0));
        assertFilesExist("/doremi-2436/My_B.ecore", "/doremi-2436/My_B.aird", "/doremi-2436/My_C.ecore", "/doremi-2436/My_C.aird");

        // Step 2 : uncontrol C
        final EObject packageC = findPackageNamed("C", modelResource.getContents().get(0));
        final URI controlledAirdUriC = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_C.aird", true);
        DAnalysis cAird = (DAnalysis) domain.getResourceSet().getResource(controlledAirdUriC, true).getContents().iterator().next();
        representationC = new DViewQuery(cAird.getOwnedViews().iterator().next()).getLoadedRepresentations().iterator().next();

        boolean isControlledC = AdapterFactoryEditingDomain.isControlled(packageC);
        assertTrue(isControlledC);
        Command vuc = new SiriusUncontrolCommand(packageC, true, false, new NullProgressMonitor());
        assertTrue(vuc.canExecute());
        domain.getCommandStack().execute(vuc);
        session.save(new NullProgressMonitor());
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

        // Step 3 : ensures that this uncontrol operation has been
        // successfully realized
        // Step 3.1 : My_C.ecore & My_C.aird should have been deleted
        assertFilesExist("/doremi-2436/My_B.ecore", "/doremi-2436/My_B.aird");
        assertFilesDoNotExist("/doremi-2436/My_C.ecore", "/doremi-2436/My_C.aird");

        // Step 3.2 : C should not be controlled any more
        Resource cDirectsource = ((BasicEObjectImpl) rootC).eDirectResource();
        assertEquals(null, cDirectsource);
        assertFalse(AdapterFactoryEditingDomain.isControlled(rootC));

        EObject semanticContainerForC = new EObjectQuery(rootC.eContents().iterator().next()).getResourceContainer();
        EObject semanticHighLevelContainerForC = EcoreUtil.getRootContainer(rootC);
        assertEquals(rootB, semanticContainerForC);
        assertEquals(rootA, semanticHighLevelContainerForC);

        // Step 3.3 : representationC should now be held by bAird.
        final URI controlledAirdUriB = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_B.aird", true);
        final DAnalysis bAird = (DAnalysis) domain.getResourceSet().getResource(controlledAirdUriB, true).getContents().iterator().next();
        assertEquals(bAird, representationC.eContainer().eContainer());

        // Step 3.4 : bAird should not reference any Analysis
        assertTrue(bAird.getReferencedAnalysis().isEmpty());

        // Step 3.5 : ensure that main Aird has not been modified
        assertFalse("The main representations file should not be modified.", diagramResource.isModified());
    }

    /**
     * Opens a non controlled session (using My.ecore and My.aird).
     * 
     * @throws Exception
     */
    @Override
    protected void openNonControlledSession() throws Exception {
        copyFilesToTestProject(NON_CONTROLED_RESOURCES);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/doremi-2436/My.ecore"), Collections.<String> emptySet(), TEMPORARY_PROJECT_NAME + "/doremi-2436/My.aird");
        final Resource modelResource = session.getSemanticResources().iterator().next();
        rootA = modelResource.getContents().get(0);
        rootB = findPackageNamed("B", rootA);
        rootC = findPackageNamed("C", rootA);
        mainAird = (DAnalysis) session.getSessionResource().getContents().get(0);
    }

    /**
     * Controls the "B" semantic element and moves representationB inside the
     * created My_B.aird.
     * 
     * @return the created DAnalysis containing representationB and referencing
     *         B
     */
    private DAnalysis controlB() throws Exception {
        List<DRepresentation> loadedRepresentations = new DViewQuery(session.getOwnedViews().iterator().next()).getLoadedRepresentations();
        representationB = loadedRepresentations.get(1);
        representationC = loadedRepresentations.get(0);

        URI controlledModelUriB = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_B.ecore", true);
        URI controlledAirdUriB = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/doremi-2436/My_B.aird", true);
        siriusControl(rootB, controlledModelUriB, Sets.newHashSet(representationB, representationC), controlledAirdUriB);

        DAnalysis bAird = (DAnalysis) session.getTransactionalEditingDomain().getResourceSet().getResource(controlledAirdUriB, true).getContents().get(0);
        assertFilesExist("/doremi-2436/My.ecore", "/doremi-2436/My.aird", "/doremi-2436/My_B.ecore", "/doremi-2436/My_B.aird");
        assertFilesDoNotExist("/doremi-2436/My_C.ecore", "/doremi-2436/My_C.aird");

        // Ensure that the semantic resource has correctly been controlled
        Resource aResource = rootA.eResource();
        Resource bResource = ((BasicEObjectImpl) rootB).eDirectResource();
        Resource cResource = rootC.eResource();
        assertNotNull(aResource);
        assertNotNull(bResource);
        assertNotNull(cResource);
        assertNotSame(bResource, aResource);
        assertEquals(bResource, cResource);

        EObject semanticContainer = new EObjectQuery(rootB.eContents().iterator().next()).getResourceContainer();
        EObject semanticHighLevelContainerForB = EcoreUtil.getRootContainer(rootB);
        assertEquals(rootB, semanticContainer);
        assertEquals(rootA, semanticHighLevelContainerForB);
        assertTrue(AdapterFactoryEditingDomain.isControlled(rootB));

        EObject semanticContainerForC = new EObjectQuery(rootC).getResourceContainer();
        EObject semanticHighLevelContainerForC = EcoreUtil.getRootContainer(rootC);
        assertEquals(rootB, semanticContainerForC);
        assertEquals(rootA, semanticHighLevelContainerForC);
        assertFalse(AdapterFactoryEditingDomain.isControlled(rootC));

        // Ensure that the main Aird now references the controled aird
        mainAird.getReferencedAnalysis().contains(bAird);

        // Ensure that Representation B has correctly been moved
        assertEquals(bAird, representationB.eContainer().eContainer());
        assertEquals(bAird, representationC.eContainer().eContainer());

        // Ensure that the controlled Aird is its own container
        EObject rootDAnalysis = new EObjectQuery(bAird).getResourceContainer();
        assertEquals(bAird, rootDAnalysis);
        return bAird;
    }

    /**
     * Copies the files located at the given paths into the test project.
     * 
     * @param filePaths
     *            the paths of the files to copy
     */
    @Override
    protected void copyFilesToTestProject(String... filePaths) {
        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);
        for (final String path : filePaths) {
            final String pluginPath = "/data/unit/control" + path;
            final String wksPath = TEMPORARY_PROJECT_NAME + path;
            EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginPath, wksPath);
        }
    }
}
