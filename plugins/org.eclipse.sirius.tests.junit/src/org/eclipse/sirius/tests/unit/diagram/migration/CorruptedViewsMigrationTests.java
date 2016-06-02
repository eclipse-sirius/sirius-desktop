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
package org.eclipse.sirius.tests.unit.diagram.migration;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.ui.business.internal.migration.SetGMFViewsToNillMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.unit.common.migration.AbstractMigrationTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * A test ensuring that if we have an aird containing corrupted {@link View}s
 * (e.g. views which should have a 'null' element but have the {@link DDiagram}
 * ). This can occur when manipulating diagrams with collaborative mode, the
 * result of the import does dot contain the expected '
 * <element xsi:nil="true"/>' tags.
 * 
 * <p>
 * For further information, please refer to
 * {@link org.eclipse.sirius.diagram.business.internal.migration.SetGMFViewsToNillMigrationParticipant}
 * javadoc.
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class CorruptedViewsMigrationTests extends AbstractMigrationTestCase {

    private static final String TEST_FOLDER_PATH = "/data/unit/migration/do_not_migrate/vp-4371";

    private static final String SESSION_RESOURCE_FILENAME = "representations.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "My.ecore";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.ViewpointTestCase#setUp()
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_FOLDER_PATH + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, TEST_FOLDER_PATH + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
    }

    /**
     * Ensures that the data used in the test requires migration.
     */
    public void testMigrationIsNeededOnData() {
        Version versionOfDAnalysis = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + TEST_FOLDER_PATH + "/" + SESSION_RESOURCE_FILENAME, true), true);

        // Check that the migration is needed.
        Version migration = SetGMFViewsToNillMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", versionOfDAnalysis == null || migration.compareTo(versionOfDAnalysis) > 0);
    }

    /**
     * Ensures that if we have an aird containing corrupted {@link View}s (e.g.
     * views which should have a 'null' element but have the {@link DDiagram}).
     * This can occur when manipulating diagrams with collaborative mode, the
     * result of the import does dot contain the expected '
     * <element xsi:nil="true"/>' tags.
     * 
     * @throws Exception
     *             if unexpected issue occur while performing this test
     */
    @Test
    public void testSessionDoesNotContainViewWithUnsettedElements() throws Exception {
        // Step 1: Migration should have been called
        doCheckSessionDoesNotContainViewWithUnsettedElements();

        // Step 2: save and reopen the session
        // migration is not called anymore, but it should have caused an update
        // of the XMI (xsi:nil="true" tags added)
        // so now data should be correct and not need migration
        URI sessionURI = session.getSessionResource().getURI();
        session.save(new NullProgressMonitor());
        session.close(new NullProgressMonitor());
        session = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        doCheckSessionDoesNotContainViewWithUnsettedElements();
        session.close(new NullProgressMonitor());
    }

    /**
     * Check that for all GMF {@link Diagram} contained in the current session,
     * all {@link View}s (including ones which have a null 'element') are set
     * and no view except for the Diagram itself has the DSemanticDiagram as
     * element.
     */
    private void doCheckSessionDoesNotContainViewWithUnsettedElements() {
        EReference elementReference = NotationPackage.eINSTANCE.getView_Element();
        for (Resource sessionResource : session.getAllSessionResources()) {
            for (DView view : ((DAnalysis) sessionResource.getContents().iterator().next()).getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(new DViewQuery(view).getLoadedRepresentations(), DDiagram.class)) {
                    DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
                    assertTrue(diagramCreationUtil.findAssociatedGMFDiagram());
                    Diagram gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
                    assertNotNull(gmfDiagram);
                    TreeIterator<EObject> allDiagramContent = gmfDiagram.eAllContents();
                    while (allDiagramContent.hasNext()) {
                        EObject diagramElement = allDiagramContent.next();
                        // We should not have any View referencing the semantic
                        // diagram except the Diagram itself
                        if (diagramElement instanceof View && !(diagramElement instanceof Diagram)) {
                            assertTrue("This " + diagramElement.getClass().getSimpleName() + " 'element' feature should be set", diagramElement.eIsSet(elementReference));
                            assertNotSame("This " + diagramElement.getClass().getSimpleName() + " should not have the DSemanticDiagram as 'element'", dDiagram, ((View) diagramElement).getElement());
                        }
                    }
                }
            }
        }
    }

}
