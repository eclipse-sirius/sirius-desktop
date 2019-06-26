/*******************************************************************************
 * Copyright (c) 2016, 2019 Obeo.
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
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.diagram.FilterVariableHistory;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.diagram.business.internal.migration.FilterVariableValueMigrationParticipant;
import org.eclipse.sirius.diagram.business.internal.migration.VariableMigrationParticipant;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.osgi.framework.Version;

/**
 * Ensures correct migration from FilterVariable to SelectModelElementVariable
 * and from FilterVariableValue to EObjectVariableValue.
 *
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class VariableMigrationTest extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/migration/do_not_migrate/variableFilter/";

    private static final String REPRESENTATIONS_FILE_NAME = "filterMigration.aird";

    private static final String MODEL_FILE_NAME = "filterMigration.ecore";

    private static final String MODELER_FILE_NAME = "filterMigration.odesign";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated. It allows to check the effect of
     * the migration in the other test.
     */
    public void testRepMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = FilterVariableValueMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that the data were not migrated. It allows to check the effect of
     * the migration in the other test.
     */
    public void testVSMMigrationIsNeededOnData() {
        Version loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + MODELER_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = VariableMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the instances of FilterVariableHistory.ownedValues are
     * correctly converted into EObjectVariableValue
     * 
     * @throws IOException
     */
    public void testFilterVariableValueToEObjectVariableValueMigration() throws IOException {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME, MODELER_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        Resource resourceAird = set.getResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), true);
        analysis = (DAnalysis) resourceAird.getContents().get(0);

        assertNotNull("Analysis is not retrieved.", analysis);

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        resourceAird.save(Collections.emptyMap());

        // save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We can now check the migration effect to be sure that the migration
        // is effective.
        checkRepMigrationEffect(analysis);

    }

    private void checkRepMigrationEffect(DAnalysis analysis) {
        DRepresentationDescriptor representationDescriptor = new DViewQuery(analysis.getOwnedViews().get(0)).getLoadedRepresentationsDescriptors().get(0);
        DRepresentation representation = representationDescriptor.getRepresentation();
        assertTrue("DRepresentation " + representationDescriptor.getName() + " is not a DDiagram", representation instanceof DDiagram);
        FilterVariableHistory filterVariableHistory = ((DDiagram) representation).getFilterVariableHistory();
        EList<VariableValue> ownedValues = filterVariableHistory.getOwnedValues();
        VariableValue variableValue = ownedValues.get(0);

        assertTrue("FilterVariableHistory.ownedValues should be of type EObjectVariableValue", variableValue instanceof EObjectVariableValue);
        EObjectVariableValue objectValue = (EObjectVariableValue) variableValue;
        EObject modelElement = objectValue.getModelElement();
        assertNotNull("EObjectVariableValue.modelElement is not set", modelElement);
        SelectModelElementVariable variableDefinition = objectValue.getVariableDefinition();
        assertNotNull("EObjectVariableValue.variableDefinition is not set", variableDefinition);
    }

    /**
     * Check that the instances of FilterVariableHistory.ownedValues are
     * correctly converted into EObjectVariableValue
     * 
     * @throws IOException
     */
    public void testFilterVariableToSelectModelElementVariableMigration() throws IOException {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, MODELER_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();
        Group group = null;
        Resource resourceVSM = set.getResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME, true), true);
        group = (Group) resourceVSM.getContents().get(0);

        assertNotNull("Group is not retrieved.", group);

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = group.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        resourceVSM.save(Collections.emptyMap());

        // save should update the version.
        version = group.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We can now check the migration effect to be sure that the migration
        // is effective.
        checkVSMMigrationEffect(group);

    }

    private void checkVSMMigrationEffect(Group group) {
        RepresentationDescription representationDescription = group.getOwnedViewpoints().get(0).getOwnedRepresentations().get(0);
        assertTrue("RepresentationDescription " + representationDescription.getName() + " is not a DiagramDescription", representationDescription instanceof DiagramDescription);
        DiagramDescription diagramDesc = (DiagramDescription) representationDescription;
        InteractiveVariableDescription interactiveVariableDescription = ((VariableFilter) ((CompositeFilterDescription) diagramDesc.getFilters().get(0)).getFilters().get(0)).getOwnedVariables()
                .get(0);

        assertTrue("VariableFilter.ownedVariables should be of type SelectModelElementVariable", interactiveVariableDescription instanceof SelectModelElementVariable);

        SelectModelElementVariable variable = (SelectModelElementVariable) interactiveVariableDescription;
        assertEquals("Bad SelectModelElementVariable.candidatesExpression", "aql:self.eContents()", variable.getCandidatesExpression());

        assertEquals("Bad SelectModelElementVariable.name", "MyVariable", variable.getName());
        assertEquals("Bad SelectModelElementVariable.message", "message", variable.getMessage());
    }
}
