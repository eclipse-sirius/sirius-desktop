/*******************************************************************************
 * Copyright (c) 2016, 2023 Obeo.
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
package org.eclipse.sirius.tests.unit.table.unit;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.business.internal.dialect.TableDialectServices;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.junit.Assert;

/**
 * Test class for table displaying multivalued String attributes.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class TableWithMultivaluedAttributeTest extends SiriusTestCase {

    public static final String PATH = "/data/table/unit/bugzilla-490092/";

    private static final String SEMANTIC_MODEL_FILENAME = "component.component";

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.sample.component.design/description/component.odesign";

    private static final String SESSION_MODEL_FILENAME = "representations.aird";

    private static final String TABLE_DESCRIPTION_NAME = "Aliases";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH, TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
    }

    /**
     * Using the Component metamodel and viewpoint, this scenario create an
     * "Aliases" table and refresh it. As this table displays the multivalued
     * string attribute "aliases", this test validates that no
     * ClassCastException is raised.
     * 
     * @throws Exception
     */
    public void testTableWithMultivaluedAttribute() throws Exception {
        // Look for the TableDescription named "Aliases" in the viewpoint
        Viewpoint viewpoint = session.getSelectedViewpoints(false).iterator().next();
        TableDescription aliasesTableDescription = null;
        for (RepresentationDescription representationDescription : viewpoint.getOwnedRepresentations()) {
            if (representationDescription instanceof TableDescription && TABLE_DESCRIPTION_NAME.equals(representationDescription.getName())) {
                aliasesTableDescription = (TableDescription) representationDescription;
            }
        }
        Assert.assertNotNull("The table description " + TABLE_DESCRIPTION_NAME + " has not been found in the activated viewpoint", aliasesTableDescription);

        // Creation of the table
        DTableSynchronizer sync = new TableDialectServices().createTableSynchronizer(aliasesTableDescription, accessor, interpreter);
        DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(aliasesTableDescription);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        // Refresh the table to force the label evaluation of a cell displaying
        // a multivalued string attribute
        try {
        sync.refresh(new NullProgressMonitor());
        } catch (ClassCastException cce) {
            // Catch the exception to display a better failing message
            fail("Refreshing a table displaying a multivalued String attribute should not cause a ClassCastException anymore");
        }
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
