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

/**
 * Test delete and hide several lines on table. Test VP-2272.
 * 
 * @author jdupont
 */
public abstract class AbstractDeleteHideSeveralElements extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Sirius used in tests.
     */
    protected static final String VIEWPOINT_NAME = "2272";

    private static final String VSM_FILE = "2272.odesign";

    private static final String SESSION_FILE = "2272.aird";

    private static final String ECORE_FILE = "2272.ecore";

    private static final String FILE_DIR = "/";

    private static final String DATA_UNIT_DIR = "data/unit/table/deleteSeveralLines/vp-2272/";

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
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Check that element passed in parameters are present.
     * 
     * @param elementToVerifyArePresent
     *            element presence or not
     */
    protected abstract void checkElementArePresent(String... elementToVerifyArePresent);

    /**
     * Select several elements corresponding to parameters
     * 
     * @param elementsToSelect
     *            the elements to select
     */
    protected abstract void selectElementAndDelete(String... elementsToSelect);

    /**
     * Return files used in the current test.
     * 
     * @return files used for test
     */
    protected String[] getFilesUsedForTest() {
        return new String[] { VSM_FILE, ECORE_FILE, SESSION_FILE };
    }

}
