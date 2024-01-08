/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.tests.unit.common.migration;

import java.io.IOException;

import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.data.LayoutOptionData;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.elk.migration.description.ELK090MigrationParticipant;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * Ensures correct migration from FilterVariable to SelectModelElementVariable and from FilterVariableValue to
 * EObjectVariableValue.
 *
 * @author lredor
 */
public class ELK090VSMMigrationTest extends SiriusTestCase {

    private static final String MODELER_FILE_PATH = "/data/unit/migration/do_not_migrate/fromELK0.7.1To0.9.0/ELK0.7.1-0.9.0-changes.odesign"; //$NON-NLS-1$

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated. It allows to check the effect of the migration in the other test.
     */
    public void testVSMMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + MODELER_FILE_PATH, true), true);

        // Check that the migration is needed.
        Version migration = ELK090MigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0); //$NON-NLS-1$
    }

    /**
     * Check that the instances of FilterVariableHistory.ownedValues are
     * correctly converted into EObjectVariableValue
     * 
     * @throws IOException
     */
    public void testVSMLoadedWithoutException() throws IOException {
        ResourceSet set = new ResourceSetImpl();
        Group group = null;
        try {
            group = (Group) ModelUtils.load(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + MODELER_FILE_PATH, true), set);
        } catch (IOException e) {
            fail("Check the test data, we should not fail here."); //$NON-NLS-1$
        }
        if (doesAnErrorOccurs()) {
            fail("An error occured during the loading of the VSM."); //$NON-NLS-1$
        }
        StringBuffer errors = new StringBuffer();
        group.getOwnedViewpoints().stream().forEach(v -> v.getOwnedRepresentations().stream().filter(DiagramDescription.class::isInstance).map(DiagramDescription.class::cast).forEach(d -> {
            if (d.getLayout() instanceof CustomLayoutConfiguration) {
                CustomLayoutConfiguration customLayoutConfiguration = (CustomLayoutConfiguration) d.getLayout();
                for (LayoutOption layoutOption : customLayoutConfiguration.getLayoutOptions()) {
                    // Check that all options used are available on ELK side
                    LayoutOptionData layoutProperty = LayoutMetaDataService.getInstance().getOptionData(layoutOption.getId());
                    if (layoutProperty == null) {
                        errors.append("The option \"" + layoutOption.getId() + "\" is not available on ELK side. It should be migrated.\n"); //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }));
        if (!errors.isEmpty()) {
            fail(errors.toString());
        }
    }
}
