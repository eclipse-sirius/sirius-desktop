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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.AbstractDeleteHideSeveralElements;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Test delete and hide several elements on table. Test VP-2272.
 * 
 * @author jdupont
 */
public class DeleteSeveralElementOnTree extends AbstractDeleteHideSeveralElements {

    private static final String REPRESENTATION_TREE_NAME = "Tree";

    private static final String REPRESENTATION_TREE_INSTANCE_NAME = "new Tree";

    private static final String CLASS1 = "new EClass 1";

    private static final String CLASS2 = "Class2";

    private static final String CLASS3 = "Class3";

    /**
     * Current tree.
     */
    protected UITreeRepresentation tree;

    /**
     * Test delete several elements on tree.
     * <P>
     * Step 1 : open tree representation
     * <p>
     * Step 2 : Select 3 elements of tree
     * <p>
     * Step 3 : Click on context menu and chose delete element. Verify that the
     * 3 selected elements are deleted.
     */
    public void testDeleteSeveralElements() {
        // Open tree representation
        openTreeRepresentation();
        // Check that the 3 elements to delete are present.
        checkElementArePresent(CLASS1, CLASS2, CLASS3);
        // Select the 3 elements
        selectElementAndDelete(CLASS1, CLASS2, CLASS3);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected void checkElementArePresent(String... elementToVerifyArePresent) {
        for (String element : elementToVerifyArePresent) {
            assertEquals("The element \" " + element + " \" must be present in tree", element, tree.getTree().getTreeItem(element).getText());
        }
    }

    @Override
    protected void selectElementAndDelete(String... elementsToSelect) {
        tree.getTree().select(elementsToSelect);
        SWTBotUtils.clickContextMenu(tree.getTree().getTreeItem(CLASS3), "Delete tree items");
        tree.getTree().setFocus();

        // Reduce timeout
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 500;

            for (String element : elementsToSelect) {
                try {
                    tree.getTree().getTreeItem(element);
                    fail("The element \" " + element + " \" must be delete");
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
    private void openTreeRepresentation() {
        tree = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_TREE_NAME)
                .selectRepresentationInstance(REPRESENTATION_TREE_INSTANCE_NAME, UITreeRepresentation.class).open();
    }
}
