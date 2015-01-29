/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.refresh;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * Test for differents cases that in manual refresh local refresh is done when
 * changing the semantic model. See VP-2793.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TableManuelRefreshTests extends SiriusTestCase {

    private static final String PATH = "/data/table/unit/refresh/manual/VP-2793/";

    private static final String SESSION_RESOURCE_FILENAME = "VP-2793.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-2793.ecore";

    private static final String MODELER_RESOURCE_FILENAME = "VP-2793.odesign";

    private DTable dTable;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);

        dTable = (DTable) DialectManager.INSTANCE.getAllRepresentations(session).iterator().next();

        DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
    }

    public void testLocalRefreshAfterAEClassNameChange() {
        DLine dLineOfNewEClass = dTable.getLines().get(0);
        EClass newEClass = (EClass) dLineOfNewEClass.getTarget();
        Command changeNewEClassNameCmd = SetCommand.create(session.getTransactionalEditingDomain(), newEClass, EcorePackage.Literals.ENAMED_ELEMENT__NAME, "new" + newEClass.getName());
        session.getTransactionalEditingDomain().getCommandStack().execute(changeNewEClassNameCmd);
        TestsUtil.synchronizationWithUIThread();

        assertEquals("in manual refresh the DCell.label should be refreshed", newEClass.getName(), dLineOfNewEClass.getLabel());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        dTable = null;
        super.tearDown();
    }

}
