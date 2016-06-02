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
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.control.SiriusUncontrolCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.collect.Sets;

/**
 * Tests ensuring that if Control/Uncontrol grandson when the grand father is in
 * read only, there is no pop up Read-only File Encountered was opened. This
 * test check the grandson has no link to grandfather.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 */
public class HierarchicalControlWithRootElementReadOnlyTest extends AbstractHierarchicalControlTest {
    private static final String[] NON_CONTROLED_RESOURCES_ON_TEST = new String[] { "/vp-2067/2067.ecore", "/vp-2067/2067.aird", "/vp-2067/2067_p1p1.ecore", "/vp-2067/2067_p1p1.aird" };

    private EObject rootP1P1P1;

    private DRepresentation representationP1;

    private URI controlledModelUrip1p1p1;

    private URI controlledAirdUrip1p1p1;

    class ExplicitFileModificationValidator implements IResourceChangeListener {

        Set<IPath> readOnlyfullPaths = Sets.newLinkedHashSet();

        public void setReadOnly(IFile file) {
            readOnlyfullPaths.add(file.getFullPath());
        }

        @Override
        public void resourceChanged(IResourceChangeEvent event) {

            IResourceDelta delta = event.getDelta();

            if (delta != null) {
                IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {

                    @Override
                    public boolean visit(IResourceDelta delta) {

                        boolean continueToVisit = true;
                        IResource resource = delta.getResource();
                        if (resource.getFullPath() != null && readOnlyfullPaths.contains(resource.getFullPath())) {
                            throw new RuntimeException("File :" + resource.getFullPath() + " has been changed whereas it was explicitely marked as not to be changed");
                        }

                        return true;
                    }

                };
                try {
                    delta.accept(visitor);
                } catch (CoreException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

    private ExplicitFileModificationValidator readonlyValidator = new ExplicitFileModificationValidator();

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // We modify the Sirius preferences to make sure that aird are
        // created during a control operation, even if there is no
        // representation to associate to it.
        changeSiriusPreference(SiriusPreferencesKeys.PREF_EMPTY_AIRD_FRAGMENT_ON_CONTROL.name(), true);

        // Step 1 : opening a session on 2067.aird (referencing 2067.ecore)
        openNonControlledSession();
        controlledModelUrip1p1p1 = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/vp-2067/2067_p1p1_p1p1p1.ecore", true);
        controlledAirdUrip1p1p1 = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/vp-2067/2067_p1p1_p1p1p1.aird", true);
        if (new DViewQuery((DView) session.getOwnedViews().toArray()[0]).getLoadedRepresentations().size() == 0) {
            representationP1 = new DViewQuery((DView) session.getOwnedViews().toArray()[1]).getLoadedRepresentations().get(0);
        } else {
            representationP1 = new DViewQuery((DView) session.getOwnedViews().toArray()[0]).getLoadedRepresentations().get(0);
        }

        ResourcesPlugin.getWorkspace().addResourceChangeListener(readonlyValidator);
    }

    /**
     * Ensures that the following use case works as expected :
     * <ul>
     * <li>We have a DAnalysis 2067.aird referencing a semantic resource with
     * some hierarchy (p1 <>- p1p1 <>- p1p1p1) and containing 1 representations
     * (one on p1p1);</li>
     * <li>control p1p1p1 (GrandSon) with 2067.ecore and 2067.aird on read only
     * (GrandFather) => test no popup opening ;</li>
     * </ul>
     * 
     * @throws Exception
     * 
     */
    public void testControlGrandSonWhenGrandFatherIsReadOnly() throws Exception {
        // Step 2 : we make sur that files 2067.ecore, 2067.aird,
        // 2067_p1p1.ecore and 2067_p1p1.aird exists
        // and that p1p1p1 has not controlled yet.
        assertFilesExist("/vp-2067/2067.ecore", "/vp-2067/2067.aird", "/vp-2067/2067_p1p1.ecore", "/vp-2067/2067_p1p1.aird");
        assertFilesDoNotExist("/vp-2067/2067.aird/2067_p1p1_p1p1p1.ecore", "/vp-2067/2067_p1p1_p1p1p1.aird");

        // Step 3 : passed 2067.ecore and 2067.aird on read only
        IFile semanticModelFile = getFile("vp-2067/2067.ecore");
        readonlyValidator.setReadOnly(semanticModelFile);
        IFile sessionFile = getFile("vp-2067/2067.aird");
        readonlyValidator.setReadOnly(sessionFile);

        // Step 4 control p1p1p1 (grandSon)
        final SiriusControlCommand vccp1p1p1 = new SiriusControlCommand(rootP1P1P1, controlledModelUrip1p1p1, Collections.singleton(representationP1), controlledAirdUrip1p1p1, true,
                new NullProgressMonitor());
        assertTrue(executeCommand(vccp1p1p1));
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

        // Step 5 : we make sure that p1p1p1 has controlled.
        assertFilesExist("/vp-2067/2067.ecore", "/vp-2067/2067.aird", "/vp-2067/2067_p1p1.ecore", "/vp-2067/2067_p1p1.aird", "/vp-2067/2067_p1p1_p1p1p1.ecore", "/vp-2067/2067_p1p1_p1p1p1.aird");
    }

    /**
     * From a set of controlled representations (p1, p1p1 and p1p1p1)
     * <ul>
     * <li>Uncontrols p1p1p1</li>
     * <li>Ensure that no pop up refer read only grand father status opened</li>
     * </ul>
     * 
     * @throws Exception
     * 
     */
    public void testControlUncontrolp1p1p1() throws Exception {
        assertFilesExist("/vp-2067/2067.ecore", "/vp-2067/2067.aird", "/vp-2067/2067_p1p1.ecore", "/vp-2067/2067_p1p1.aird");

        // Step 2 : control p1p1p1
        Command vccp1p1p1 = new SiriusControlCommand(rootP1P1P1, controlledModelUrip1p1p1, Collections.singleton(representationP1), controlledAirdUrip1p1p1, true, new NullProgressMonitor());
        assertTrue(executeCommand(vccp1p1p1));
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

        assertFilesExist("/vp-2067/2067.ecore", "/vp-2067/2067.aird", "/vp-2067/2067_p1p1.ecore", "/vp-2067/2067_p1p1.aird", "/vp-2067/2067_p1p1_p1p1p1.ecore", "/vp-2067/2067_p1p1_p1p1p1.aird");

        // Step 3 : passed 2067.ecore and 2067.aird on read only
        IFile semanticModelFile = getFile("vp-2067/2067.ecore");
        readonlyValidator.setReadOnly(semanticModelFile);
        IFile sessionFile = getFile("vp-2067/2067.aird");
        readonlyValidator.setReadOnly(sessionFile);

        // Step 3 : uncontrol p1p1p1
        boolean isControlledP1p1p1 = AdapterFactoryEditingDomain.isControlled(rootP1P1P1);
        assertTrue(isControlledP1p1p1);
        Command vuc = new SiriusUncontrolCommand(rootP1P1P1, true, new NullProgressMonitor());
        assertTrue(executeCommand(vuc));
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

        // Step 3 : ensures that this uncontrol operation has been
        // successfully realized
        // Step 3.1 : 2067_p1p1_p1p1p1.ecore & 2067_p1p1_p1p1p1.aird should
        // have been deleted
        assertFilesExist("/vp-2067/2067.ecore", "/vp-2067/2067.aird", "/vp-2067/2067_p1p1.ecore", "/vp-2067/2067_p1p1.aird");
        assertFilesDoNotExist("/vp-2067/2067.aird/2067_p1p1_p1p1p1.ecore", "/vp-2067/2067_p1p1_p1p1p1.aird");
    }

    /**
     * Opens a non controlled session (using 2067.ecore and 2067.aird).
     * 
     * @throws Exception
     */
    @Override
    protected void openNonControlledSession() throws Exception {
        copyFilesToTestProject(NON_CONTROLED_RESOURCES_ON_TEST);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/vp-2067/2067.ecore"), Collections.<String> emptySet(), TEMPORARY_PROJECT_NAME + "/vp-2067/2067.aird");
        final Resource modelResource = session.getSemanticResources().iterator().next();
        rootP1P1P1 = findPackageNamed("p1p1p1", modelResource.getContents().get(0));
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(readonlyValidator);
        // Setting the preference to its old value
        if (session != null) {
            session.close(new NullProgressMonitor());
        }
        EclipseTestsSupportHelper.INSTANCE.deleteProject(TEMPORARY_PROJECT_NAME);
        TestsUtil.synchronizationWithUIThread();
        rootP1P1P1 = null;
        representationP1 = null;
        super.tearDown();
    }
}
