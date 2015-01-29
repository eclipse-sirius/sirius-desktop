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

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests for a "read only" column setup using the 'canEdit' property of
 * FeatureColumnMapping.
 * 
 * @author pcdavid
 */
public class ReadOnlyColumnTest extends AbstractSiriusSwtBotGefTestCase {
    /**
     * Odesign.
     */
    private static final String MODEL = "read_only_column.odesign";

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/vp-2674/";

    /**
     * Session file.
     */
    private static final String SESSION_FILE = "fixture.aird";

    /**
     * UML File.
     */
    private static final String ECORE_FILE = "fixture.ecore";

    /**
     * File directory.
     */
    private static final String FILE_DIR = "/";

    /**
     * Local Session.
     */
    private UILocalSession localSession;

    /**
     * Session.
     */
    private UIResource sessionAirdResource;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        String[] fileNames = { MODEL, SESSION_FILE, ECORE_FILE };
        for (final String fileName : fileNames) {
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + fileName, getProjectName() + "/" + fileName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Tests that the 'canEdit' proerty of a column mapping is properly handled,
     * even if several columns have the same featureName. This is a regression
     * test for VP-2674.
     * 
     * @throws Exception
     *             if an error occurs.
     */
    public void testEditBooleanCellWithOtherColumnOnSameFeatureName() throws Exception {
        final UITableRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("read_only_column").selectRepresentation("read_only_column")
                .selectRepresentationInstance("new read_only_column", UITableRepresentation.class).open();
        // Get the second line, which is actually the first sub-line of the first top-level line.
        SWTBotTreeItem[] items = table.getTable().getAllItems()[0].getItems();

        // Check the values before.
        assertEquals("newEReference1 : B0", items[0].cell(0));
        assertEquals("false", items[0].cell(1));
        assertEquals("false", items[0].cell(2));

        pressKey(table.getTable(), "arrow_down");
        pressKey(table.getTable(), "arrow_down");
        pressKey(table.getTable(), "arrow_right");
        pressKey(table.getTable(), "space");

        table.getTable().display.syncExec(new Runnable() {
            public void run() {
                table.getTable().widget.update();
            }
        });
        
        // Check the values after: no change expected, the column through which we tried to do the edition has canEdit = <%false%>
        assertEquals("newEReference1 : B0", items[0].cell(0));
        assertEquals("false", items[0].cell(1));
        assertEquals("false", items[0].cell(2));

    }

    private void pressKey(AbstractSWTBot<?> bot, String key) throws ParseException {
        String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        bot.pressShortcut(KeyStroke.getInstance(key));
        SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;
    }
}
