/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test multiple representations.
 * 
 * @author mchauvin
 */
public class OpenMultipleRepresentationsTest extends AbstractSiriusSwtBotGefTestCase implements EcoreModeler {

    private static final String MODEL = "blank.ecore";

    private static final String DATA_UNIT_DIR = "data/unit/blankEcore/";

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {

        UIResource semanticModel = new UIResource(designerProject, "/", MODEL);
        localSession = designerPerspective.openSessionCreationWizardFromSemanticResource(semanticModel).fromAlreadySelectedSemanticResource().withDefaultSessionName().finish()
                .selectViewpoints(DESIGN_VIEWPOINT_NAME);
    }

    public void testOpenDiagramRepresentation() throws Exception {
        SWTBotTreeItem modelElementItem = localSession.getLocalSessionBrowser().perSemantic().expandNode("root").click();
        final UIDiagramRepresentation diagramRepresentation = localSession.newDiagramRepresentation("root" + " package entities", "Entities").on(modelElementItem).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor editor = diagramRepresentation.open().getEditor();
        assertEditorIsNotError("editor was an error one", editor);
    }

    public void testOpenTableRepresentation() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.widgets.TimeoutException: Timeout
             * after: 10000 ms.: tree item with text is not selected at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(
             * SWTBotFactory.java:407) at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(
             * SWTBotFactory.java:381) at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(
             * SWTBotFactory.java:369) at
             * org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.
             * getUIItemFromResource(UIProject.java:157) at
             * org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.
             * selectResource(UIProject.java:122) at
             * org.eclipse.sirius.tests.swtbot.support.api.business.
             * UIPerspective.openSessionCreationWizardFromSemanticResource(
             * UIPerspective.java:188) at
             * org.eclipse.sirius.tests.swtbot.OpenMultipleRepresentationsTest.
             * onSetUpAfterOpeningDesignerPerspective(
             * OpenMultipleRepresentationsTest.java:61)
             */
            return;
        }
        SWTBotTreeItem modelElementItem = localSession.getLocalSessionBrowser().perSemantic().expandNode("root").click();
        final UITableRepresentation tableRepresentation = localSession.newTableRepresentation("Classes in root package", "Classes").on(modelElementItem).withDefaultName().ok();
        final SWTBotEditor editor = tableRepresentation.open().getEditor();
        assertEditorIsNotError("editor was an error one", editor);
    }

    public void testOpenDiagramAndTableRepresentations() throws Exception {

        SWTBotTreeItem modelElementItem = localSession.getLocalSessionBrowser().perSemantic().expandNode("root").click();
        final UIDiagramRepresentation diagramRepresentation = localSession.newDiagramRepresentation("root" + " package entities", "Entities").on(modelElementItem).withDefaultName().ok();
        final SWTBotSiriusDiagramEditor diagramEditor = diagramRepresentation.open().getEditor();

        SWTBotTreeItem modelElementItem2 = localSession.getLocalSessionBrowser().perSemantic().expandNode("root").click();
        final UITableRepresentation tableRepresentation = localSession.newTableRepresentation("Classes in root package", "Classes").on(modelElementItem2).withDefaultName().ok();
        final SWTBotEditor tableEditor = tableRepresentation.open().getEditor();
        assertEditorIsNotError("editor was an error one", tableEditor);
        assertEditorIsNotError("editor was an error one", diagramEditor);
    }

}
