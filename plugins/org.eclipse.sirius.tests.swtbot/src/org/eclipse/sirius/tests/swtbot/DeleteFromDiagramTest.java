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
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

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

    private static final String VIEWPOINT_NAME = DESC_NAME;

    private static final String REPRESENTATION_NAME = "new " + DESC_NAME;

    private UIDiagramRepresentation diagram;

    private SWTBotGefEditPart node1Bot;

    private int nbStatusInErrorLogBefore;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL_PATH, AIRD_MODEL_PATH, MODELER_PATH);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", AIRD_MODEL_PATH);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(DESC_NAME)
                .selectRepresentationInstance(REPRESENTATION_NAME, UIDiagramRepresentation.class).open();

        initEditor();

        node1Bot = editor.getEditPart("EClass1").parent();

        nbStatusInErrorLogBefore = getNbStatusInErrorLog();
    }

    private void initEditor() {
        if (diagram != null) {
            editor = diagram.getEditor();

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
        // Checks that not new Status has appeared in error log
        Assert.assertEquals(nbStatusInErrorLogBefore, getNbStatusInErrorLog());
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        node1Bot = null;
        super.tearDown();
    }

}
