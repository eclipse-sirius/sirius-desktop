/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.control.SiriusControlCommand;
import org.eclipse.sirius.business.api.control.SiriusUncontrolCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.danalysis.SimpleAnalysisSelector;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Test for ticket 2015 : delete representations in controlled diagram should
 * not change the parent diagram ( no use of DDiagramSet)
 * 
 * @author nlepine
 * 
 */
public class SiriusControlAndDeleteRepresentationTest extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String PACKAGE2 = "package2";

    private static final String PATH = "/data/unit/control/2015/";

    private static final String MAIN_SESSION_MODEL_FILENAME = "2015.aird";

    private static final String MAIN_SEMANTIC_MODEL_FILENAME = "2015.ecore";

    private static final String CONTROLLED_SEMANTIC_MODEL_FILENAME = "2015_package2.ecore";

    private static final String CONTROLLED_SESSION_MODEL_FILENAME = "2015_package2.aird";

    private URI mainSemanticResourceURI;

    private URI mainSessionResourceURI;

    private URI controlledSemanticResourceURI;

    private URI controlledSessionResourceURI;

    private Resource modelResource;

    private Resource diagramResource;

    private EObject package2;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MAIN_SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MAIN_SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MAIN_SESSION_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MAIN_SESSION_MODEL_FILENAME);

        mainSemanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + MAIN_SEMANTIC_MODEL_FILENAME, true);
        mainSessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + MAIN_SESSION_MODEL_FILENAME, true);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MAIN_SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MAIN_SEMANTIC_MODEL_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MAIN_SESSION_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MAIN_SESSION_MODEL_FILENAME);

        controlledSemanticResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + CONTROLLED_SEMANTIC_MODEL_FILENAME, true);
        controlledSessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + CONTROLLED_SESSION_MODEL_FILENAME, true);

        EclipseTestsSupportHelper.INSTANCE.copyFile("org.eclipse.sirius.sample.ecore.design", "/description/ecore.odesign", "/" + TEMPORARY_PROJECT_NAME + "/" + "modeler.odesign");

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + MAIN_SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + "modeler.odesign", TEMPORARY_PROJECT_NAME + "/" + MAIN_SESSION_MODEL_FILENAME);

        // set a SimpleAnalysisSelector on Session to avoid popup
        DAnalysisSession dAnalysisSession = (DAnalysisSession) session;
        dAnalysisSession.setAnalysisSelector(new SimpleAnalysisSelector((DAnalysis) session.getSessionResource().getContents().get(0)));

        TestsUtil.emptyEventsFromUIThread();

        initViewpoint(DESIGN_VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();

        modelResource = session.getTransactionalEditingDomain().getResourceSet().getResource(mainSemanticResourceURI, true);
        diagramResource = session.getTransactionalEditingDomain().getResourceSet().getResource(mainSessionResourceURI, true);
        TestsUtil.synchronizationWithUIThread();
        assertFilesExist(MAIN_SEMANTIC_MODEL_FILENAME, MAIN_SESSION_MODEL_FILENAME);
        assertFilesDoNotExist(CONTROLLED_SEMANTIC_MODEL_FILENAME, CONTROLLED_SESSION_MODEL_FILENAME);
        TestsUtil.synchronizationWithUIThread();
        package2 = findPackageNamed(PACKAGE2, modelResource.getContents().get(0));
        createRepresentation(TABLES_DESC_NAME, semanticModel);
        createRepresentation(TABLES_DESC_NAME, package2);
        TestsUtil.synchronizationWithUIThread();
    }

    public void testControlAndDeleteRepresentation() throws Exception {
        controlRepresentation();

        // Reload
        modelResource = session.getTransactionalEditingDomain().getResourceSet().getResource(mainSemanticResourceURI, true);
        package2 = findPackageNamed(PACKAGE2, modelResource.getContents().get(0));

        Resource diagramControlResource = session.getTransactionalEditingDomain().getResourceSet().getResource(controlledSessionResourceURI, true);
        ByteArrayOutputStream outdiagramControlResource = new ByteArrayOutputStream();
        saveInOutputStreamNoSideEffect(diagramControlResource, outdiagramControlResource);

        diagramResource = session.getTransactionalEditingDomain().getResourceSet().getResource(mainSessionResourceURI, true);
        ByteArrayOutputStream outdiagramResource = new ByteArrayOutputStream();
        saveInOutputStreamNoSideEffect(diagramResource, outdiagramResource);

        final DRepresentationDescriptor existingRepresentation = Iterables.find(getRepresentationDescriptors(ENTITIES_DESC_NAME, session), new Predicate<DRepresentationDescriptor>() {
            @Override
            public boolean apply(DRepresentationDescriptor input) {
                return input.getName().equals("package2 package entities");
            }
        });
        final DRepresentation newRepresentation = createRepresentation(ENTITIES_DESC_NAME, package2);
        final DRepresentationDescriptor newrepresentationDescriptor = new DRepresentationQuery(newRepresentation).getRepresentationDescriptor();
        session.save(new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();
        // TestsUtil.synchronizationWithUIThread();

        assertResourcesAreEquals(mainSessionResourceURI, session.getTransactionalEditingDomain(), controlledSessionResourceURI, outdiagramControlResource, outdiagramResource);

        executeCommand(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.deleteRepresentation(existingRepresentation, session);
            }
        });
        session.save(new NullProgressMonitor());
        assertResourcesAreEquals(mainSessionResourceURI, session.getTransactionalEditingDomain(), controlledSessionResourceURI, outdiagramControlResource, outdiagramResource);

        executeCommand(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.deleteRepresentation(newrepresentationDescriptor, session);
            }
        });
        session.save(new NullProgressMonitor());
        assertResourcesAreEquals(mainSessionResourceURI, session.getTransactionalEditingDomain(), controlledSessionResourceURI, outdiagramControlResource, outdiagramResource);

        SiriusUncontrolCommand vuc = new SiriusUncontrolCommand(package2, true, true, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(vuc);
        session.save(new NullProgressMonitor());
    }

    protected void saveInOutputStreamNoSideEffect(Resource resource, ByteArrayOutputStream outputStream) throws IOException {
        /*
         * Saving a resource, even to an outputstream, has side effects on the
         * resource state itself : notification of state changes and update of
         * isModified state
         */
        /*
         * Here we want to avoid the side effects. We have no guarantee a
         * specific resource will not have other side effects but at least we
         * can anihilate the ResourceImpl ones.
         */

        // no notifications during the save
        resource.eSetDeliver(false);

        boolean isModified = resource.isModified();
        long timestamp = resource.getTimeStamp();
        resource.save(outputStream, null);
        resource.setModified(isModified);
        resource.setTimeStamp(timestamp);

        // notifications are back
        resource.eSetDeliver(true);
    }

    public void testControlAndDeleteRepresentationClosingSession() throws Exception {

        if (TestsUtil.shouldSkipUnreliableTests()) {
            // junit.framework.ComparisonFailure:
            // expected:<...nMIH8EeK7nPLrycVk9A"[ version="6.6.1.201301141057"]>
            // <models xmi:type...> but was:<...nMIH8EeK7nPLrycVk9A"[]>
            // <models xmi:type...>
            // at junit.framework.Assert.assertEquals(Assert.java:85)
            // at junit.framework.Assert.assertEquals(Assert.java:91)
            // at
            // org.eclipse.sirius.tests.unit.api.tools.SiriusControlAndDeleteRepresentationTest.assertResourcesAreEquals(SiriusControlAndDeleteRepresentationTest.java:305)
            // at
            // org.eclipse.sirius.tests.unit.api.tools.SiriusControlAndDeleteRepresentationTest.testControlAndDeleteRepresentationClosingSession(SiriusControlAndDeleteRepresentationTest.java:208)
            return;
        }

        controlRepresentation();

        // Reload
        modelResource = session.getTransactionalEditingDomain().getResourceSet().getResource(mainSemanticResourceURI, true);
        package2 = findPackageNamed(PACKAGE2, modelResource.getContents().get(0));

        // Create a new diagram on package2 with a distinct name
        final DRepresentation newPackage2Diagram = createRepresentation(ENTITIES_DESC_NAME, package2);
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                new DRepresentationQuery(newPackage2Diagram).getRepresentationDescriptor().setName("New package2 diagram");
            }
        });

        // Save the session and keep the contents of some of the resources
        // around for reference
        Resource diagramControlResource = session.getTransactionalEditingDomain().getResourceSet().getResource(controlledSessionResourceURI, true);

        diagramResource = session.getTransactionalEditingDomain().getResourceSet().getResource(mainSessionResourceURI, true);
        ByteArrayOutputStream outdiagramResource = new ByteArrayOutputStream();
        saveInOutputStreamNoSideEffect(diagramResource, outdiagramResource);
        session.save(new NullProgressMonitor());
        Session session1 = session;

        // Open a second session
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + MAIN_SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + "modeler.odesign", TEMPORARY_PROJECT_NAME + "/" + MAIN_SESSION_MODEL_FILENAME);
        ByteArrayOutputStream outdiagramControlResource = new ByteArrayOutputStream();
        saveInOutputStreamNoSideEffect(diagramControlResource, outdiagramControlResource);
        Session session2 = session;
        modelResource = session2.getTransactionalEditingDomain().getResourceSet().getResource(mainSemanticResourceURI, true);
        package2 = findPackageNamed(PACKAGE2, modelResource.getContents().get(0));
        // Save it after no change; make sure the resources correspond to the
        // reference version kept earlier.
        session2.save(new NullProgressMonitor());
        assertResourcesAreEquals(mainSessionResourceURI, session2.getTransactionalEditingDomain(), controlledSessionResourceURI, outdiagramControlResource, outdiagramResource);

        // Open a third session
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + MAIN_SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + "modeler.odesign", TEMPORARY_PROJECT_NAME + "/" + MAIN_SESSION_MODEL_FILENAME);
        final Session session3 = session;
        TestsUtil.synchronizationWithUIThread();
        // Delete the diagram created earlier.
        final DRepresentationDescriptor repDescriptor2 = Iterables.find(getRepresentationDescriptors(ENTITIES_DESC_NAME, session), new Predicate<DRepresentationDescriptor>() {
            @Override
            public boolean apply(DRepresentationDescriptor input) {
                return input.getName().equals("New package2 diagram");
            }
        });
        Command cmd = new RecordingCommand(session3.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.deleteRepresentation(repDescriptor2, session3);
            }
        };
        executeCommand(cmd);
        // It should have been saved in the main (non-controlled) aird, so
        // removing at should have no impact on the controlled resources
        // content.
        session3.save(new NullProgressMonitor());
        assertResourcesAreEquals(mainSessionResourceURI, session3.getTransactionalEditingDomain(), controlledSessionResourceURI, outdiagramControlResource, outdiagramResource);

        session1.close(new NullProgressMonitor());
        IEditingSession uiSession1 = SessionUIManager.INSTANCE.getUISession(session1);
        if (uiSession1 != null) {
            uiSession1.close();
            TestsUtil.emptyEventsFromUIThread();
        }
        session2.close(new NullProgressMonitor());
        IEditingSession uiSession2 = SessionUIManager.INSTANCE.getUISession(session2);
        if (uiSession2 != null) {
            uiSession2.close();
            TestsUtil.emptyEventsFromUIThread();
        }
        session3.close(new NullProgressMonitor());
        IEditingSession uiSession3 = SessionUIManager.INSTANCE.getUISession(session3);
        if (uiSession3 != null) {
            uiSession3.close();
            TestsUtil.emptyEventsFromUIThread();
        }

        // Same as before, but remove a representation which was here already at
        // the beginning, and also not controlled.
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + MAIN_SEMANTIC_MODEL_FILENAME, TEMPORARY_PROJECT_NAME + "/" + "modeler.odesign", TEMPORARY_PROJECT_NAME + "/" + MAIN_SESSION_MODEL_FILENAME);
        final DRepresentationDescriptor repDescriptor3 = Iterables.find(getRepresentationDescriptors(ENTITIES_DESC_NAME, session), new Predicate<DRepresentationDescriptor>() {
            @Override
            public boolean apply(DRepresentationDescriptor input) {
                return input.eResource().getURI().equals(mainSessionResourceURI);
            }
        });
        cmd = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.deleteRepresentation(repDescriptor3, session);
            }
        };
        executeCommand(cmd);
        session.save(new NullProgressMonitor());
        assertResourcesAreEquals(mainSessionResourceURI, session.getTransactionalEditingDomain(), controlledSessionResourceURI, outdiagramControlResource, outdiagramResource);
    }

    private DRepresentation controlRepresentation() throws Exception {
        // Initially we have a two packages in the same ecore, and two diagrams
        // (one for each package) in the same aird.
        DView dView = Iterables.find(session.getOwnedViews(), new Predicate<DView>() {
            @Override
            public boolean apply(DView v) {
                return new DViewQuery(v).getLoadedRepresentations().size() == 4;
            }
        });
        List<DRepresentation> allRepresentations = new DViewQuery(dView).getLoadedRepresentations();
        assertEquals("package1 package entities", new DRepresentationQuery(allRepresentations.get(0)).getRepresentationDescriptor().getName());
        assertEquals("package2 package entities", new DRepresentationQuery(allRepresentations.get(1)).getRepresentationDescriptor().getName());

        // We control package2 and "package1 package entities" into a separate
        // ecore and (resp.) aird
        DRepresentation representation = allRepresentations.get(0);
        DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation).getRepresentationDescriptor();
        SiriusControlCommand vcc = new SiriusControlCommand(package2, controlledSemanticResourceURI, Collections.singleton(representationDescriptor), controlledSessionResourceURI, true,
                new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(vcc);
        ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

        // Check that the semantic element for package2 is still accessible from
        // the main semantic model, but is actually stored in a different ecore.
        assertSame(package2, findPackageNamed(PACKAGE2, modelResource.getContents().get(0)));
        assertEquals(controlledSemanticResourceURI, package2.eResource().getURI());
        // Also check that the controled representation is in the new resource
        assertEquals(controlledSessionResourceURI, representation.eResource().getURI());
        // and that the files have been created physically
        assertFilesExist(MAIN_SEMANTIC_MODEL_FILENAME, MAIN_SESSION_MODEL_FILENAME, CONTROLLED_SEMANTIC_MODEL_FILENAME, CONTROLLED_SESSION_MODEL_FILENAME);
        return representation;
    }

    private void assertResourcesAreEquals(final URI parentAirdUri, final TransactionalEditingDomain domain, final URI controlledAirdUri, ByteArrayOutputStream outdiagramControlResource,
            ByteArrayOutputStream outdiagramResource) throws IOException {
        Resource diagramResourceAfterCreateRepresentation = domain.getResourceSet().getResource(parentAirdUri, true);
        Resource diagramControlResourceAfterCreateRepresentation = domain.getResourceSet().getResource(controlledAirdUri, true);

        ByteArrayOutputStream outdiagramResourceAfterCreateRepresentation = new ByteArrayOutputStream();
        saveInOutputStreamNoSideEffect(diagramResourceAfterCreateRepresentation, outdiagramResourceAfterCreateRepresentation);
        ByteArrayOutputStream outdiagramControlResourceAfterCreateRepresentation = new ByteArrayOutputStream();
        saveInOutputStreamNoSideEffect(diagramControlResourceAfterCreateRepresentation, outdiagramControlResourceAfterCreateRepresentation);
        assertEquals(outdiagramControlResourceAfterCreateRepresentation.toString(), outdiagramControlResource.toString());
    }

    private EObject findPackageNamed(final String name, final EObject root) {
        final TreeIterator<EObject> iter = root.eAllContents();
        while (iter.hasNext()) {
            final EObject current = iter.next();
            if (current instanceof EPackage && ((EPackage) current).getName().equals(name)) {
                return current;
            }
        }
        return null;
    }

    private void assertFilesDoNotExist(final String... wksPaths) {
        for (String path : wksPaths) {
            path = TEMPORARY_PROJECT_NAME + path;
            assertTrue("Workspace file " + path + " should not exist.", fileDoesNotExist(wksPaths));
        }
    }

    private void assertFilesExist(final String... wksPaths) {
        for (String path : wksPaths) {
            path = TEMPORARY_PROJECT_NAME + "/" + path;
            assertTrue("Workspace file " + path + " should exist.", ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).exists());
        }
    }

    private boolean fileDoesNotExist(final String... wksPaths) {
        long startTime = System.currentTimeMillis();
        long delay = 5000;
        while (System.currentTimeMillis() - startTime < delay) {
            boolean fileDoesNotExists = true;
            for (String path : wksPaths) {
                path = TEMPORARY_PROJECT_NAME + "/" + path;
                fileDoesNotExists = fileDoesNotExists && !ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path)).exists();
            }
            if (fileDoesNotExists) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void tearDown() throws Exception {
        mainSemanticResourceURI = null;
        mainSessionResourceURI = null;
        controlledSemanticResourceURI = null;
        controlledSessionResourceURI = null;
        modelResource = null;
        diagramResource = null;
        super.tearDown();
    }
}
