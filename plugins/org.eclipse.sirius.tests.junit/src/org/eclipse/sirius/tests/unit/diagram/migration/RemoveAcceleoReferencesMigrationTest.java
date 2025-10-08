/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.ui.business.internal.migration.description.AcceleoExpressionsMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.osgi.framework.Version;

/**
 * Test migration participant to update object values in VSM before loading this
 * objects from XML file.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class RemoveAcceleoReferencesMigrationTest extends SiriusTestCase {

    private static final String PATH = "data/unit/migration/do_not_migrate/RemoveAcceleo3References/";

    private static final String VSM_RESOURCE_NAME = "RemoveAcceleoReferences.odesign";

    private static final String SESSION_RESOURCE_NAME = "RemoveAcceleoReferences.aird";

    private Group group;

    /**
     * {@inheritDoc}
     */
    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, VSM_RESOURCE_NAME, SESSION_RESOURCE_NAME);
        URI vsmResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + VSM_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource vsmResource = resourceSet.getResource(vsmResourceURI, true);
        group = (Group) vsmResource.getContents().get(0);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version acceleoExpressionMigrationVersion = new AcceleoExpressionsMigrationParticipant().getMigrationVersion();

        // Check that the vsm migration is needed.
        Version loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + VSM_RESOURCE_NAME, true), true);
        assertTrue("The list migration must be required on test data.", acceleoExpressionMigrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Ensure that the editor should not be dirty after the diagram opening.
     */
    public void testRemoveAcceleoReferencesMigration() {
        session = SessionManager.INSTANCE.getSession(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + SESSION_RESOURCE_NAME, true), new NullProgressMonitor());
        if (!session.isOpen()) {
            session.open(new NullProgressMonitor());
        }
        assertFalse("The editor should not be dirty after the diagram opening.", session.getStatus().equals(SessionStatus.DIRTY));
    }

    /**
     * Check Color Value Computation Expression values after migration.
     */
    public void testValuesAfterMigration() {
        InterpolatedColor interpolatedColor1 = (InterpolatedColor) group.getUserColorsPalettes().get(0).getEntries().get(0);
        InterpolatedColor interpolatedColor2 = (InterpolatedColor) group.getUserColorsPalettes().get(0).getEntries().get(1);
        assertEquals("Setted Color Value Computation Expression values in VSM should not change after migration", interpolatedColor1.getColorValueComputationExpression(), "1");
        assertEquals("Default Color Value Computation Expression values in VSM must be set to aql:self.eContents()->size()", interpolatedColor2.getColorValueComputationExpression(),
                "aql:self.eContents()->size()");
    }

}
