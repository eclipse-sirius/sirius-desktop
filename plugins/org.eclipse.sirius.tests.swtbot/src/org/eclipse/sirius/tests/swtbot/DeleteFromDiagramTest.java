/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * An exception was raised in some case when performing a delete from diagram.
 * The exception occurs when deleting a container with 2 edges with style
 * customization (here the label). See VP-4380 for more details.
 * 
 * @author fbarbin
 */
public class DeleteFromDiagramTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/delete/VP-4380/";

    private static final String MODELER_PATH = "VP-4380.odesign";

    private static final String SEMANTIC_MODEL_PATH = "VP-4380.ecore";

    private static final String AIRD_MODEL_PATH = "VP-4380.aird";

    private static final String DESC_NAME = "deleteFromDiagram";

    private static final String REPRESENTATION_NAME = "new " + DESC_NAME;

    private SWTBotGefEditPart node1Bot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL_PATH, AIRD_MODEL_PATH, MODELER_PATH);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", AIRD_MODEL_PATH);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DESC_NAME, REPRESENTATION_NAME, DDiagram.class);

        initEditor();

        node1Bot = editor.getEditPart("EClass1").parent();

        // Activate error and warning catch mechanisms (if it is not already done) and reset the previous messages
        // caught
        setErrorCatchActive(true);
        setWarningCatchActive(true);
        errors.clear();
        warnings.clear();
    }

    private void initEditor() {
        if (editor != null) {
            editor.setSnapToGrid(false);

            editor.setFocus();
        }
    }

    /**
     * Test that the delete from diagram do not raise exceptions.
     */
    public void testDeleteFromDiagramAction() {

        editor.click(0, 0);

        node1Bot.select();
        deleteFromDiagram();
        // Checks that no new Status has appeared in error log
        assertFalse("At least one warning occurs.", doesAWarningOccurs());
        assertFalse("At least one errors occurs.", doesAnErrorOccurs());
    }

    @Override
    protected void tearDown() throws Exception {
        node1Bot = null;
        super.tearDown();
    }

}
