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
package org.eclipse.sirius.tests.swtbot.table;

import org.eclipse.sirius.tests.swtbot.AbstractDeleteHideSeveralElements;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Test delete and hide several lines on table. Test VP-2272.
 * 
 * @author jdupont
 */
public class DeleteHideSeveralLineOnTable extends AbstractDeleteHideSeveralElements {

    private static final String REPRESENTATION_TABLE_NAME = "Table";

    private static final String REPRESENTATION_TABLE_INSTANCE_NAME = "new Table";

    private static final String CLASS1 = "new EClass 1";

    private static final String CLASS2 = "Class2";

    private static final String CLASS3 = "Class3 -> new EClass 1";

    private static final String REFRESH_TABLE = "Force a refresh of the table";

    /**
     * Current table.
     */
    protected UITableRepresentation table;

    /**
     * Test delete several lines on table.
     * <P>
     * Step 1 : open table representation
     * <p>
     * Step 2 : Select 3 lines of table
     * <p>
     * Step 3 : Click on context menu and chose delete line. Verify that the 3
     * selected lines are deleted.
     */
    public void testDeleteSeveralLines() {
        // Open table representation
        openTableRepresentation();
        // Check that the 3 lines to delete are present.
        checkElementArePresent(CLASS1, CLASS2, CLASS3);
        // Select the 3 lines
        selectElementAndDelete(CLASS1, CLASS2, CLASS3);
    }

    /**
     * Test hide several lines on table.
     * <P>
     * Step 1 : open table representation
     * <p>
     * Step 2 : Select 3 lines of table
     * <p>
     * Step 3 : Click on context menu and chose hide line. Verify that the 3
     * selected lines are hidden.
     */
    public void testHideSeveralLines() {
        // Open table representation
        openTableRepresentation();
        // Check that the 3 lines to hide are present.
        checkElementArePresent(CLASS1, CLASS2, CLASS3);
        // Select the 3 lines
        selectLinesAndHide(CLASS1, CLASS2, CLASS3);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void checkElementArePresent(String... elementToVerifyArePresent) {
        for (String element : elementToVerifyArePresent) {
            assertEquals("The element \" " + element + " \" must be present in table", element, table.getTable().getTreeItem(element).getText());
        }
    }

    @Override
    protected void selectElementAndDelete(String... elementsToSelect) {
        table.getTable().select(elementsToSelect);
        SWTBotUtils.clickContextMenu(table.getTable().getTreeItem(CLASS3), "Delete lines");
        table.getTable().setFocus();
        manualRefreshTable();

        // Reduce timeout
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 500;

            for (String element : elementsToSelect) {
                try {
                    table.getTable().getTreeItem(element);
                    fail("The line \" " + element + " \" must be delete");
                } catch (WidgetNotFoundException wnfe) {
                    // DO NOTHING, it's the good behavior
                }
            }

        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Request an explicit refresh of the current table.
     */
    private void manualRefreshTable() {
        bot.toolbarButtonWithTooltip(REFRESH_TABLE).click();
    }

    private void selectLinesAndHide(String... linesToSelect) {
        table.getTable().select(linesToSelect);
        SWTBotUtils.clickContextMenu(table.getTable(), "Hide lines");

        // Reduce timeout
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 500;

            for (String line : linesToSelect) {
                try {
                    table.getTable().getTreeItem(line);
                    fail("The line \" " + line + " \"must be hidden");
                } catch (WidgetNotFoundException wnfe) {
                    // DO NOTHING, it's the good behavior
                }
            }
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

    /**
     * Open the table editor
     */
    private void openTableRepresentation() {
        table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_TABLE_NAME)
                .selectRepresentationInstance(REPRESENTATION_TABLE_INSTANCE_NAME, UITableRepresentation.class).open();
    }
}
