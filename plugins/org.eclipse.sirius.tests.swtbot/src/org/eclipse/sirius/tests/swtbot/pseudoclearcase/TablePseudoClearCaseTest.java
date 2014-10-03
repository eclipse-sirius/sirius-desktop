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
package org.eclipse.sirius.tests.swtbot.pseudoclearcase;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILSRepresentationBrowser;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Pseudo clear-case tests on tables.
 * 
 * @author dlecan
 */
public class TablePseudoClearCaseTest extends AbstractPseudoClearCaseTest<SWTBotEditor> {

    private UITableRepresentation representationInstance;

    private SWTBotTree treeTable;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationInstanceName() {
        return "new Classes";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationName() {
        return "Classes";
    }

    @Override
    protected SWTBotEditor openAndGetEditor(Session session, String representationDescriptionName, String representationName) {
        final UILSRepresentationBrowser selectedRepresentation = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(representationName);
        representationInstance = selectedRepresentation.selectRepresentationInstance(getRepresentationInstanceName(), UITableRepresentation.class);
        // Should be run synchronously, but modification dialog pops up
        // Remove asynchronous call and waitUntil... when table opening won't
        // make session dirty.
        UIThreadRunnable.asyncExec(new VoidResult() {
            public void run() {
                representationInstance.open();
            }
        });
        waitUntilReadOnlyPopupDialogOpens();

        treeTable = representationInstance.getTable();
        treeTable.setFocus();

        return representationInstance.getEditor();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDeleteRow() throws Exception {
        final SWTBotTreeItem treeItem = treeTable.getTreeItem("sp2").select();
        SWTBotUtils.clickContextMenu(treeItem, "Delete line");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRevealRow() throws Exception {
        treeTable.contextMenu("Show hidden lines").click();
    }
}
