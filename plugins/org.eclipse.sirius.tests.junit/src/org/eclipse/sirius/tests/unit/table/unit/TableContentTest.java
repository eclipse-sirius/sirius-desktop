/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.business.internal.dialect.TableDialectServices;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

public class TableContentTest extends SiriusTestCase {
    public static final String PATH = "/data/table/unit/contents/";

    private static final String SEMANTIC_MODEL_FILENAME = "t.ecore";

    private static final String MODELER_MODEL_FILENAME = "t.odesign";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_MODEL_FILENAME;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH);
        activateViewpoint("T");
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterCreation_128() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(128, false);
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterCreation_256() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(256, false);
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterCreation_1024() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(1024, false);
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterCreation_2048() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(2048, false);
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterRefresh_128() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(128, true);
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterRefresh_256() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(256, true);
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterRefresh_1024() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(1024, true);
    }

    public void testTableContainsAsManyLinesAndManyColumnsAsEClassesAfterRefresh_2048() throws Exception {
        createNewTableAndCheckNbLinesAndNbColumns(2048, true);
    }

    /**
     * Creates a synchronizer.
     * 
     * @param descr of table
     * @return synchronizer
     */
    protected DTableSynchronizer createTableSynchronizer(TableDescription descr) {
    	return new TableDialectServices().createTableSynchronizer(descr, accessor, interpreter);
    }
    
    private void createNewTableAndCheckNbLinesAndNbColumns(final int nbClasses, boolean secondRefresh) {
        TransactionalEditingDomain ted = (TransactionalEditingDomain) session.getTransactionalEditingDomain();
        ted.getCommandStack().execute(new RecordingCommand(ted) {
            @Override
            protected void doExecute() {
                EPackage pkg = (EPackage) semanticModel;
                for (int i = pkg.getEClassifiers().size(); i < nbClasses; i++) {
                    EClass k = EcoreFactory.eINSTANCE.createEClass();
                    k.setName("A" + i);
                    pkg.getEClassifiers().add(k);
                }
            }
        });

        Viewpoint viewpoint = session.getSelectedViewpoints(false).iterator().next();
        CrossTableDescription desc = (CrossTableDescription) viewpoint.getOwnedRepresentations().iterator().next();
        DTableSynchronizer sync = createTableSynchronizer(desc);
        DTable newTable = TableFactory.eINSTANCE.createDTable();
        newTable.setDescription(desc);
        newTable.setTarget(semanticModel);
        sync.setTable(newTable);
        sync.refresh(new NullProgressMonitor());
        assertEquals("Invalid number of lines in the table.", semanticModel.eContents().size(), newTable.getLines().size());
        assertEquals("Invalid number of columns in the table.", semanticModel.eContents().size(), newTable.getColumns().size());

        if (secondRefresh) {
            sync = createTableSynchronizer(desc);
            sync.setTable(newTable);
            sync.refresh(new NullProgressMonitor());

            assertEquals("Invalid number of lines in the table.", semanticModel.eContents().size(), newTable.getLines().size());
            assertEquals("Invalid number of columns in the table.", semanticModel.eContents().size(), newTable.getColumns().size());
        }
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
