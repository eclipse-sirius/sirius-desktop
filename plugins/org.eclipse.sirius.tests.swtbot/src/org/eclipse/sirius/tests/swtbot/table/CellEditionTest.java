/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.table;

import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests for table cell edition.
 * 
 * @author pcdavid
 */
public class CellEditionTest extends AbstractSiriusSwtBotGefTestCase {

    /** Odesign. */
    private static final String MODEL = "different_parent_expression.odesign";

    /** Test repository. */
    private static final String DATA_UNIT_DIR = "data/unit/vp-2683/";

    /** Session file. */
    private static final String SESSION_FILE = "fixture.aird";

    /** UML File. */
    private static final String ECORE_FILE = "fixture.ecore";

    /** File directory. */
    private static final String FILE_DIR = "/";

    /** Local Session. */
    private UILocalSession localSession;

    /** Session. */
    private UIResource sessionAirdResource;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        String[] fileNames = { MODEL, SESSION_FILE, ECORE_FILE };
        for (final String fileName : fileNames) {
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + fileName, getProjectName() + "/" + fileName);
        }
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Tests that editing a cell on a column which uses the same featureName as
     * another column edits the right cell. This is a regression test for
     * VP-2683.
     * 
     * @throws Exception
     *             if an error occurs.
     */
    public void testEditBooleanCellWithOtherColumnOnSameFeatureName() throws Exception {
        final UITableRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("different_parent_expression").selectRepresentation("different_parent_expression")
                .selectRepresentationInstance("new different_parent_expression", UITableRepresentation.class).open();
        SWTBotTreeItem[] items = table.getTable().getAllItems();

        // Check the values before.
        assertEquals("A0", items[0].cell(0));
        assertEquals("true", items[0].cell(1));
        assertEquals("", items[0].cell(2));

        assertEquals("B0", items[1].cell(0));
        assertEquals("false", items[1].cell(1));
        assertEquals("true", items[1].cell(2));

        // Toggle the cell on line "B0" and column "IsAbstract".
        items[1].select();
        items[1].click(1);
        SWTBotUtils.pressKeyboardKey(table.getTable().widget, SWT.SPACE, SWT.SPACE);

        bot.toolbarButtonWithTooltip("Force a refresh of the table").click();
        SWTBotUtils.waitAllUiEvents();
        table.getTable().display.syncExec(new Runnable() {
            @Override
            public void run() {
                table.getTable().widget.update();
            }
        });
        SWTBotUtils.waitAllUiEvents();

        // Check that the edited cell has changed, and only that cell.
        assertEquals("A0", items[0].cell(0));
        assertEquals("true", items[0].cell(1));
        assertEquals("", items[0].cell(2));

        assertEquals("B0", items[1].cell(0));
        assertEquals("true", items[1].cell(1));
        assertEquals("true", items[1].cell(2));
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        super.tearDown();
    }
}
