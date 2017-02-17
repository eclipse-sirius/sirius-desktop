/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.dialect;

import static org.easymock.EasyMock.replay;

import java.lang.reflect.Method;
import java.util.Collection;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SaveAsImageFileAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.migration.AbstractRepairMigrateTest;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.internal.resource.InMemoryResourceImpl;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.tools.internal.actions.export.AbstractExportRepresentationsAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;

/**
 * Tests the export of diagrams as image after the migration of the session
 * 
 * @author lchituc
 */
public class ExportAsImageTest extends AbstractRepairMigrateTest {

    private static final String SEMANTIC_MODEL_FILE_NAME = "My.ecore";

    private static final String SEMANTIC_MODEL_RELATIVE_PATH = "/data/unit/ExportAsImage/" + SEMANTIC_MODEL_FILE_NAME;

    private static final String SEMANTIC_MODEL_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILE_NAME;

    private static final String MODELER_FILE_NAME = "My.odesign";

    private static final String MODELER_RELATIVE_PATH = "/data/unit/ExportAsImage/" + MODELER_FILE_NAME;

    private static final String MODELER_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME;

    private static final String AIRD_FILE_NAME = "My.aird";

    private static final String AIRD_RELATIVE_PATH = "/data/unit/ExportAsImage/" + AIRD_FILE_NAME;

    private static final String AIRD_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + AIRD_FILE_NAME;

    private static final String SESSION_MODEL_FILENAME = "My.aird";

    private static final String IMAGE_FILE_NAME = "new diagExportAsImage.PNG";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, MODELER_RELATIVE_PATH, MODELER_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SEMANTIC_MODEL_RELATIVE_PATH, SEMANTIC_MODEL_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, AIRD_RELATIVE_PATH, AIRD_WORKSPACE_PATH);

        genericSetUp(SEMANTIC_MODEL_WORKSPACE_PATH, MODELER_WORKSPACE_PATH, AIRD_WORKSPACE_PATH);

        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Tests the export of diagrams as image after the migration of the session
     * 
     * @throws Exception
     */
    public void testExportAsImage() throws Exception {
        // The session must be close before launching the migration (see
        // RepresentationFilesMigration.run and
        // RepresentationFilesMigrationValidator);
        closeSession(session);

        // Migrates the session
        runRepairProcess(SESSION_MODEL_FILENAME);

        // Reopen the session( because after migration the session is closed)
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        DRepresentation representation = (DRepresentation) allRepresentations.toArray()[0];

        IPath absoluteImagePath = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getLocation().append(IMAGE_FILE_NAME);

        // Export the image
        DialectUIManager.INSTANCE.export(representation, session, absoluteImagePath, new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG), new NullProgressMonitor(), false);

        // Asserts that the image had been created
        SiriusAssert.assertFileExists("/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_FILE_NAME);
    }

    /**
     * Tests the export of diagrams as image after the migration of the session
     * (but in other session as it can be the case).
     * 
     * @throws Exception
     */
    public void testExportAsImageFromAnotherSession() throws Exception {

        // The session must be close before launching the migration (see
        // RepresentationFilesMigration.run and
        // RepresentationFilesMigrationValidator);
        closeSession(session);

        // Migrates the session
        runRepairProcess(SESSION_MODEL_FILENAME);

        // Reopen the session( because after migration the session is closed)
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true);
        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        // Get the Root of the given aird file (i.e. a DAnalysis)
        Session newSession;
        IFile airdFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME));
        assertTrue("The file " + "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME + " should exist in the workspace.", airdFile.exists());
        // Load the resource in a new TransactionalEditingDomain

        newSession = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        if (!newSession.isOpen()) {
            newSession.open(new NullProgressMonitor());
        }

        final IInterpreter interpreter = newSession.getInterpreter();
        InterpreterRegistry.prepareImportsFromSession(interpreter, newSession);

        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(newSession);
        assertTrue("It should be at least one representation in the session.", allRepresentations.size() > 0);
        DRepresentation representation = (DRepresentation) allRepresentations.toArray()[0];

        // Export the image
        IPath absoluteImagePath = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getLocation().append(IMAGE_FILE_NAME);
        DialectUIManager.INSTANCE.export(representation, newSession, absoluteImagePath, new ExportFormat(ExportDocumentFormat.NONE, ImageFileFormat.PNG), new NullProgressMonitor(), false);

        // Asserts that the image had been created
        SiriusAssert.assertFileExists("/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_FILE_NAME);
    }

    /**
     * Test that no NPE occurs for InMemory uri with no opaque part and thaht
     * the returned path corresponds to the Platform location.
     * 
     * @throws Exception
     */
    public void testInMemoryResourceExportPathComputation() throws Exception {
        URI uri = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
        InMemoryResourceImpl res = new InMemoryResourceImpl(uri);
        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        res.getContents().add(diag);

        // Start recording
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getSessionResource()).andReturn(res);
        replay(session);
        // stop recording

        Object path = null;
        SaveAsImageFileAction saveAsImageFileAction = new SaveAsImageFileAction();
        try {
            DRepresentationDescriptor createDRepresentationDescriptor = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
            createDRepresentationDescriptor.setRepresentation(diag);
            Method method = AbstractExportRepresentationsAction.class.getDeclaredMethod("getExportPath", DRepresentationDescriptor.class, Session.class);
            method.setAccessible(true);
            path = method.invoke(saveAsImageFileAction, createDRepresentationDescriptor, session);
        } catch (Exception e) {
            fail("The test should be checked, the tested method could no more exist.");
        }
        assertEquals("The returned path should correspond to the platform location for the InMemory test aird without real semantic file.", Platform.getLocation(), path);
    }
}
