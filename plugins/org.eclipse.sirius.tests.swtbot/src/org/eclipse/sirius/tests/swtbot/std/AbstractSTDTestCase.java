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
package org.eclipse.sirius.tests.swtbot.std;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;

/**
 * 
 * @author amartin
 */
public abstract class AbstractSTDTestCase extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/std/";

    protected static final String FILE_DIR = "/";

    private static String STD_DIRECTORY;

    abstract String[] getFilesUsedForTest();

    abstract String getSTDDiretory();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR + getSTDDiretory(), getFilesUsedForTest());
    }

}
