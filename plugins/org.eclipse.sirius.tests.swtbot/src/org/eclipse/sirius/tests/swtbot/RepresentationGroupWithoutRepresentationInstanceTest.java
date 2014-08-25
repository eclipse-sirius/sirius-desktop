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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILSCategoryBrowser;

/**
 * 
 * Test that representation instance does not contains representation and it
 * filtered is not present in Model explorer View. Test VP-2331.
 * 
 * @author jdupont
 */
public class RepresentationGroupWithoutRepresentationInstanceTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "Entities";

    private static final String REPRESENTATION_NAME = "p1 package entities";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String VIEWPOINT_NAME_2 = "Documentation";

    private static final String VIEWPOINT_NAME_3 = "Review";

    private static final String MODEL = "vp-2331.ecore";

    private static final String SESSION_FILE = "vp-2331.aird";

    private static final String VSM_FILE = "ecore.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/representationGroup/vp-2331/";

    private static final String FILE_DIR = "/";

    /**
     * Test that the representation with no instance are not visible. By default
     * the customize view checked
     * "The representation description without instance".
     */
    public void testRepresentationWithNoInstanceIsNotVisible() {
        UILSCategoryBrowser perCategory = localSession.getLocalSessionBrowser().perCategory();
        assertTrue("The representation descrtiption Entities must have one instance", perCategory.selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_INSTANCE_NAME).getTreeItem()
                .getItems().length == 1);
        assertTrue("the instance representation must naming \"" + REPRESENTATION_NAME + "\"",
                REPRESENTATION_NAME.equals(perCategory.selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_INSTANCE_NAME).getTreeItem().getItems()[0].getText()));
        assertTrue("The representation description\"Classes\" should not be visible", perCategory.selectViewpoint(VIEWPOINT_NAME).getTreeItem().getItems().length == 1);
        assertTrue("No representation description should not be visible for Documentation viewpoint", perCategory.selectViewpoint(VIEWPOINT_NAME_2).getTreeItem().getItems().length == 0);
        assertTrue("No representation description should not be visible for Review viewpoint", perCategory.selectViewpoint(VIEWPOINT_NAME_3).getTreeItem().getItems().length == 0);
    }

    /**
     * Return files used in the current test.
     */
    String[] getFilesUsedForTest() {
        return new String[] { MODEL, SESSION_FILE, VSM_FILE };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, getFilesUsedForTest());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

}
