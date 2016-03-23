/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.std;

import java.text.MessageFormat;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

public class STD043 extends AbstractSTDTestCase {

    private static final String SESSION_FILE_043 = "STD-TEST-043.aird";

    private static final String MODEL_043 = "STD-TEST-043.ecore";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_043 = "RootPackage package entities";

    private static final String VIEWPOINT_NAME_043 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_043 = "Entities";

    private final static String NEW_ECLASS_CREATED = "new EClass 1";

    private final static String NEW_EPACKAGE_CREATED = "new Package 1";

    private final static String NEW_ECLASS_CREATED_2 = "new EClass 2";

    private final static String NEW_EPACKAGE_CREATED_2 = "new Package 2";

    private final static int X_MARGING_FOR_NOT_CLICKING_LABEL_NODE = 10;

    private final static int Y_MARGING_FOR_NOT_CLICKING_LABEL_NODE = 20;

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { SESSION_FILE_043, MODEL_043 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "043/";
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD043() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_043);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // create functionalTree diagram

        SWTBotTreeItem rootSemantic = localSession.getLocalSessionBrowser().perSemantic();
        SWTBotUtils.clickContextMenu(rootSemantic.expandNode("RootPackage").select(), REPRESENTATION_INSTANCE_NAME_DIAGRAM_043); // Category().selectSirius(VIEWPOINT_NAME_043)
        SWTBotShell confirmBoxDiagram = bot.shell(MessageFormat.format(Messages.createRepresentationInputDialog_Title, REPRESENTATION_NAME_DIAGRAM_043));
        SWTBotButton ok = bot.button("OK");
        bot.waitUntil(new ItemEnabledCondition(ok));
        ok.click();

        UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_043).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_043)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_043, UIDiagramRepresentation.class).open();

        // -feed diagram with functions
        // f =
        // SWTBotDesignerHelper.getDesignerEditor("RootPackage package
        // entities(EPackage Rootpackage)");
        SWTBotSiriusDiagramEditor editordiagram = diagram.getEditor();
        editordiagram.activateTool("Package");
        editordiagram.click(200, 100);

        editordiagram.activateTool("Class");
        editordiagram.click(50, 100);

        editordiagram.drag(NEW_EPACKAGE_CREATED, 250, 250);
        // WARNING: we are obliged to test with +2 for X, as it sems that GMF
        // put +2 in the w of the dragged node!
        // (MCH checked, and it doesn't seem to come from SWTBot)
        assertTrue(checkNewLocation(editordiagram, diagram.getEditor().getEditPart(NEW_EPACKAGE_CREATED), 252, 250));

        // -manipulate the position of model element
        editordiagram.drag( /* NEW_ECLASS_CREATED */50 + X_MARGING_FOR_NOT_CLICKING_LABEL_NODE, 100 + Y_MARGING_FOR_NOT_CLICKING_LABEL_NODE, 150, 150);
        assertNotNull(editordiagram);
        assertNotNull(diagram.getEditor());
        assertNotNull(diagram.getEditor().getEditPart(NEW_ECLASS_CREATED));
        assertTrue(checkNewLocation(editordiagram, diagram.getEditor().getEditPart(NEW_ECLASS_CREATED), 140, 130));

        // -save the diagram
        // -close the diagram and session
        localSession.close(true);

        // -reopen it
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_043).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_043)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_043, UIDiagramRepresentation.class).open();
        editordiagram = diagram.getEditor();

        // -check the position of model element are not changed
        assertTrue(checkNewLocation(editordiagram, diagram.getEditor().getEditPart(NEW_ECLASS_CREATED), 140, 130));
        assertTrue(checkNewLocation(editordiagram, diagram.getEditor().getEditPart(NEW_EPACKAGE_CREATED), 252, 250));
        // -add modelElement in order they overlay
        editordiagram.activateTool("Package");
        editordiagram.click(350, 150);
        editordiagram.drag(NEW_EPACKAGE_CREATED_2, 248, 248);

        editordiagram.activateTool("Class");
        editordiagram.click(50, 50);
        editordiagram.drag(50 + X_MARGING_FOR_NOT_CLICKING_LABEL_NODE, 50 + Y_MARGING_FOR_NOT_CLICKING_LABEL_NODE, 138, 128);

        assertTrue(checkNewLocation(editordiagram, diagram.getEditor().getEditPart(NEW_ECLASS_CREATED_2), 138, 128));
        assertTrue(checkNewLocation(editordiagram, diagram.getEditor().getEditPart(NEW_EPACKAGE_CREATED_2), 248, 248));

        bot.sleep(4000);
        // -use the 'arrange' function

        localSession.close(false);
    }

    private boolean checkNewLocation(final SWTBotSiriusDiagramEditor editordiagram, final SWTBotGefEditPart botPart, final int x, final int y) {
        if (botPart == null) {
            System.out.println("NULL");
        }
        final EditPart rawPart = botPart.part();
        if (rawPart == null) {
            System.out.println("rawPart NULL");
        }
        if (rawPart instanceof IDiagramElementEditPart && !(rawPart instanceof DiagramEditPart)) {
            final IGraphicalEditPart gep = (IGraphicalEditPart) rawPart;
            final Rectangle r = gep.getFigure().getBounds().getCopy();
            System.out.println("REAL");
            System.out.println(r.x);
            System.out.println(r.y);
            System.out.println("EXPECTED");
            System.out.println(x);
            System.out.println(y);

            return (r.x == x) && (r.y == y);
        }
        return false;
    }

}
