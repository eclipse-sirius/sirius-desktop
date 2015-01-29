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
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.test.AbstractScenarioTestCase;

/**
 * 
 * @author amartin
 */
public class STD049 extends AbstractScenarioTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/std/";

    private static final String FILE_DIR = "/";

    private static final String VIEWPOINT_NAME_049 = "Review";

    private static final String REPRESENTATION_NAME_TABLE_049 = "Documentation";

    private static final String REPRESENTATION_INSTANCE_NAME_TABLE_049 = "new Documentation";

    private static final String SESSION_FILE_049 = "STD-TEST-049.aird";

    private static final String MODEL_049 = "STD-TEST-049.ecore";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_049, SESSION_FILE_049);
    }

    /**
     * @throws Exception
     *             Test error.
     */
    public void testSTD049() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_049);

        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_049).selectRepresentation(REPRESENTATION_NAME_TABLE_049)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_TABLE_049, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-049]:Error the table couldn't be opened!", table);

        // table.getEditor().getEditPart()
        localSession.close(false);
        // ENHANCE THIS we should manipule the table...
    }

}
