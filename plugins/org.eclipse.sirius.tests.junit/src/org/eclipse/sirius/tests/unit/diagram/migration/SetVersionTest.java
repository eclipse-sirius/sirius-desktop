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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileResourceHandler;
import org.eclipse.sirius.business.internal.migration.description.VSMExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceHandler;
import org.eclipse.sirius.business.internal.resource.AirDResourceImpl;
import org.eclipse.sirius.business.internal.session.SessionFactoryImpl;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl;
import org.osgi.framework.Version;

/**
 * Ensures that version attributes are set during save. Migration occurs during
 * load, not during resource simple creation.
 */
public class SetVersionTest extends SiriusTestCase {

    private static final String PATH = "data/unit/migration/do_not_migrate/setVersion/";

    private static final String NO_VERSION_VSM_RESOURCE_NAME = "noVersion.odesign";

    private static final String NO_VERSION_SESSION_RESOURCE_NAME = "noVersion.aird";

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);
    }

    /**
     * Test that version is set on group on the first save.
     */
    public void testVersionSetOnFirstSaveAfterGroupCreation() {
        URI vsmURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + "sample.odesign", true);
        ResourceSet set = new ResourceSetImpl();
        Resource vsmRes = ModelUtils.createResource(vsmURI, set);

        assertTrue(vsmRes instanceof DescriptionResourceImpl);
        assertTrue(vsmRes.getContents().isEmpty());

        DescriptionResourceImpl descriptionResource = (DescriptionResourceImpl) vsmRes;
        Group group = DescriptionFactory.eINSTANCE.createGroup();
        descriptionResource.getContents().add(group);

        assertNull(group.getVersion());
        assertTrue(descriptionResource.getDefaultLoadOptions().get(XMLResource.OPTION_EXTENDED_META_DATA) instanceof VSMExtendedMetaData);
        assertTrue(descriptionResource.getDefaultLoadOptions().get(XMLResource.OPTION_RESOURCE_HANDLER) instanceof VSMResourceHandler);

        assertTrue(descriptionResource.getDefaultSaveOptions().get(XMLResource.OPTION_EXTENDED_META_DATA) instanceof VSMExtendedMetaData);
        assertTrue(descriptionResource.getDefaultSaveOptions().get(XMLResource.OPTION_RESOURCE_HANDLER) instanceof VSMResourceHandler);

        try {
            vsmRes.save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertNotNull(group.getVersion());
        assertFalse(VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(group.getVersion())));
        checkVsmFileMigrationStatus(vsmURI, false);
    }

    /**
     * Test that version is set on analysis on the first save.
     */
    public void testVersionSetOnFirstSaveAfterDAnalysisCreation() {
        URI airdURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + "sample.aird", true);
        ResourceSet set = new ResourceSetImpl();
        Resource airdRes = ModelUtils.createResource(airdURI, set);
        // Migration service must be active, we have just created the resource.
        AirDResourceImpl airdResource = checkAirdResource(airdRes, true);
        assertEquals(AirDResourceImpl.class, airdResource.getClass());
        assertTrue(airdResource.getContents().isEmpty());
        DAnalysis analysis = createAndCheckDAnalysisBeforeSave(airdResource);

        try {
            airdRes.save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        checkAnalysisAfterSave(airdURI, analysis);
    }

    /**
     * Test that version is set on analysis on the first save after session
     * creation from SessionFactory.INSTANCE.
     */
    public void testVersionSetOnFirstSaveAfterSessionCreationThroughSessionFactoryInstance() {
        testVersionSetAfterSessionCreation(SessionFactory.INSTANCE, false);
    }

    /**
     * Test that version is set on analysis on the first save after default
     * session creation from SessionFactory.INSTANCE.
     */
    public void testVersionSetOnFirstSaveAfterDefaultSessionCreationThroughSessionFactoryInstance() {
        testVersionSetAfterDefaultSessionCreation(SessionFactory.INSTANCE, false);
    }

    /**
     * Test that version is set on analysis on the first save after session
     * creation from SessionFactoryImpl.
     */
    public void testVersionSetOnFirstSaveAfterSessionCreationWithBasicSessionFactoryImpl() {
        SessionFactoryImpl sessionFactoryImpl = createSessionFactoryImpl();

        // Migration service should not be active, the session factory creates
        // the resource in a resource set and then load it from the session
        // resource set.
        Resource airdResource = testVersionSetAfterSessionCreation(sessionFactoryImpl, false);

        assertEquals(AirDResourceImpl.class.getName(), airdResource.getClass().getName());
    }

    private SessionFactoryImpl createSessionFactoryImpl() {
        SessionFactoryImpl sessionFactoryImpl = null;
        Optional<Constructor> osConstructor = ReflectionHelper.setConstructorVisibleWithoutException(SessionFactoryImpl.class, new Class[0]);
        if (osConstructor.isPresent()) {
            try {
                sessionFactoryImpl = (SessionFactoryImpl) osConstructor.get().newInstance(new Object[0]);
            } catch (IllegalArgumentException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                // Do nothing
            }
        }

        assertNotNull("Reflection fails to create a SessionFactoryImpl (constructor is private).", sessionFactoryImpl);
        return sessionFactoryImpl;
    }

    /**
     * Test that version is set on analysis on the first save after default
     * session creation from SessionFactoryImpl.
     */
    public void testVersionSetOnFirstSaveAfterDefaultSessionCreationWithBasicSessionFactoryImpl() {
        SessionFactoryImpl sessionFactoryImpl = createSessionFactoryImpl();

        // Migration service should not be active, the session factory creates
        // the resource in a resource set and then load it from the session
        // resource set.
        Resource airdResource = testVersionSetAfterDefaultSessionCreation(sessionFactoryImpl, false);

        assertEquals(AirDResourceImpl.class.getName(), airdResource.getClass().getName());
    }

    protected Resource testVersionSetAfterSessionCreation(SessionFactory sessionFactory, boolean migrationServiceActive) {
        URI airdURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + "sample.aird", true);

        Session session = null;
        try {
            session = sessionFactory.createSession(airdURI, new NullProgressMonitor());
        } catch (CoreException e) {
            fail(e.getMessage());
        }

        return testVersionSet(session, airdURI, migrationServiceActive);
    }

    protected Resource testVersionSetAfterDefaultSessionCreation(SessionFactory sessionFactory, boolean migrationServiceActive) {
        URI airdURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + "sample.aird", true);

        Session session = null;
        try {
            session = sessionFactory.createDefaultSession(airdURI);
        } catch (CoreException e) {
            fail(e.getMessage());
        }

        return testVersionSet(session, airdURI, migrationServiceActive);
    }

    private Resource testVersionSet(Session session, URI airdUri, boolean migrationServiceActive) {
        Resource sessionResource = session.getSessionResource();
        session.open(new NullProgressMonitor());
        assertEquals(SessionStatus.SYNC, session.getStatus());
        assertFalse(sessionResource.getContents().isEmpty());

        checkAirdResource(sessionResource, migrationServiceActive);
        DAnalysis analysis = (DAnalysis) sessionResource.getContents().get(0);
        checkAnalysisAfterSave(sessionResource.getURI(), analysis);

        return sessionResource;
    }

    protected DAnalysis createAndCheckDAnalysisBeforeSave(Resource airdResource) {
        DAnalysis analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
        airdResource.getContents().add(analysis);

        assertNull(analysis.getVersion());
        return analysis;
    }

    protected void checkAnalysisAfterSave(URI airdURI, DAnalysis analysis) {
        assertNotNull(analysis.getVersion());
        assertFalse(RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(analysis.getVersion())));
        checkRepresentationFileMigrationStatus(airdURI, false);
    }

    protected AirDResourceImpl checkAirdResource(Resource sessionResource, boolean migrationServiceActive) {
        assertTrue(sessionResource instanceof AirDResourceImpl);

        AirDResourceImpl airdResource = (AirDResourceImpl) sessionResource;
        // Check if migration is triggered on load and version set on save.
        assertEquals(migrationServiceActive, airdResource.getDefaultLoadOptions().get(XMLResource.OPTION_EXTENDED_META_DATA) instanceof RepresentationsFileExtendedMetaData);
        assertEquals(migrationServiceActive, airdResource.getDefaultLoadOptions().get(XMLResource.OPTION_RESOURCE_HANDLER) instanceof RepresentationsFileResourceHandler);

        assertEquals(migrationServiceActive, airdResource.getDefaultSaveOptions().get(XMLResource.OPTION_EXTENDED_META_DATA) instanceof RepresentationsFileExtendedMetaData);
        assertEquals(migrationServiceActive, airdResource.getDefaultSaveOptions().get(XMLResource.OPTION_RESOURCE_HANDLER) instanceof RepresentationsFileResourceHandler);
        return airdResource;
    }

    /**
     * Check the behavior of the collapse filter migration for sequence
     * diagrams.
     */
    public void testRepresentationFilesMigrationDoneOnce() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, NO_VERSION_SESSION_RESOURCE_NAME);

        URI copiedSession = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + NO_VERSION_SESSION_RESOURCE_NAME, true);
        checkRepresentationFileMigrationStatus(copiedSession, true);

        // The load must migrate the data.
        try {
            session = SessionFactory.INSTANCE.createSession(copiedSession, new NullProgressMonitor());
            session.open(new NullProgressMonitor());
        } catch (Exception e) {
            fail("An error occur during session creation/opening. " + e.getMessage());
        }

        AirDResouceQuery query = new AirDResouceQuery((AirdResource) session.getSessionResource());
        Option<DAnalysis> dAnalysis = query.getDAnalysis();

        // Check that the version attribute has not been set by the migration,
        // it will be set during save.
        assertTrue(dAnalysis.some());
        String version = dAnalysis.get().getVersion();
        assertTrue("The migration should still be marked as needed for next load.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // Save
        session.save(new NullProgressMonitor());

        // Check that the version attribute is set during save.
        version = dAnalysis.get().getVersion();
        assertFalse("The migration should be done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // Check that the version attribute has been serialized.
        checkRepresentationFileMigrationStatus(session.getSessionResource().getURI(), false);
    }

    /**
     * Check the behavior of the collapse filter migration for sequence
     * diagrams.
     */
    public void testVSMFilesMigrationDoneOnce() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, NO_VERSION_VSM_RESOURCE_NAME);

        URI vsmURI = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + NO_VERSION_VSM_RESOURCE_NAME, true);
        checkVsmFileMigrationStatus(vsmURI, true);

        // The load must migrate the data.
        ResourceSet set = new ResourceSetImpl();
        Group group = null;
        try {
            group = (Group) ModelUtils.load(vsmURI, set);
        } catch (IOException e) {
            fail("An error occur during vsm load. " + e.getMessage());
        }

        // Check that the version attribute has not been set by the migration,
        // it will be set during save.
        assertNotNull(group);
        String version = group.getVersion();
        assertTrue("The migration should still be marked as needed for next load.", VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // Save
        try {
            group.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            fail("An error occur during save. " + e.getMessage());
        }

        // Check that the version attribute is set during save.
        version = group.getVersion();
        assertFalse("The migration should be done.", VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // Check that the version attribute has been serialized.
        checkVsmFileMigrationStatus(group.eResource().getURI(), false);
    }
}
