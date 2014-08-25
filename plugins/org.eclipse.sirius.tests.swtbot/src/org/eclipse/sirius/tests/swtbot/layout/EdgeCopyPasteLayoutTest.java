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
package org.eclipse.sirius.tests.swtbot.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import org.eclipse.sirius.tests.swtbot.Activator;

/**
 * Copy Paste Layout manage specific custom style (Jump Links,
 * Smoothness). This test, test copy layout from representation with edge with
 * JumpLinks with 'Above' status and with 'Chamfered' Type, with smoothness set
 * to normal and paste this layout on representation with no custom style. Check
 * that the custom style was applied to new representation.
 * 
 * Copy Paste Layout should not set a Location during paste
 * layout for edge labels with copied (-1,-1) default size: resize label will be
 * forbidden.
 * 
 * @author jdupont
 */
public class EdgeCopyPasteLayoutTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_NAME1 = "root package entities";

    private static final String REPRESENTATION_NAME2 = "root package entities sans layout";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/layout/edgeCopyPasteLayout/";

    private static final String FILE_DIR = "/";

    private static final String REFERENCE1 = "[0..1] newEReference1";

    private static final String REFERENCE2 = "[0..1] newEReference2";

    /**
     * Diagram on first representation.
     */
    private UIDiagramRepresentation diagram1;

    /**
     * Diagram on second representation.
     */
    private UIDiagramRepresentation diagram2;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        // Open the 2 representations
        diagram1 = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_DESCRIPTION_NAME)
                .selectRepresentationInstance(REPRESENTATION_NAME1, UIDiagramRepresentation.class).open();
        diagram2 = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_DESCRIPTION_NAME)
                .selectRepresentationInstance(REPRESENTATION_NAME2, UIDiagramRepresentation.class).open();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        diagram1 = null;
        diagram2 = null;
        super.tearDown();
    }

    /**
     * Test that the paste layout affect custom style for edges.
     */
    public void testEdgeCopyPasteLayout() {

        // Check style of 2 representations. The fist should be have custom
        // style and not the second
        editor = diagram1.getEditor();
        SWTBotGefEditPart botGef = selectAndCheckEditPart(REFERENCE1, DEdgeEditPart.class);
        checkRoutingStyle(botGef, "Above", "Chamfered", "None", true);
        SWTBotGefEditPart label = editor.getEditPart(REFERENCE1, DEdgeNameEditPart.class);
        checkLayoutConstraint(label);
        botGef = selectAndCheckEditPart(REFERENCE2, DEdgeEditPart.class);
        checkRoutingStyle(botGef, "None", "Semicircle", "Normal", false);
        label = editor.getEditPart(REFERENCE1, DEdgeNameEditPart.class);
        checkLayoutConstraint(label);
        editor = diagram2.getEditor();
        botGef = selectAndCheckEditPart(REFERENCE1, DEdgeEditPart.class);
        checkRoutingStyle(botGef, "None", "Semicircle", "None", false);
        label = editor.getEditPart(REFERENCE1, DEdgeNameEditPart.class);
        checkLayoutConstraint(label);
        botGef = selectAndCheckEditPart(REFERENCE2, DEdgeEditPart.class);
        checkRoutingStyle(botGef, "None", "Semicircle", "None", false);
        label = editor.getEditPart(REFERENCE1, DEdgeNameEditPart.class);
        checkLayoutConstraint(label);
        // Select all elements on first representation and copy layout.
        editor = diagram1.getEditor();
        editor.show();
        editor.click(new Point(1, 1));
        editor.clickContextMenu("Copy layout");
        // Paste layout on second representation
        editor = diagram2.getEditor();
        editor.show();
        editor.click(new Point(500, 250));
        editor.clickContextMenu("Paste layout");
        // Check style on second representation (should be have custom style)
        botGef = selectAndCheckEditPart(REFERENCE1, DEdgeEditPart.class);
        checkRoutingStyle(botGef, "Above", "Chamfered", "None", true);
        label = editor.getEditPart(REFERENCE1, DEdgeNameEditPart.class);
        checkLayoutConstraint(label);
        botGef = selectAndCheckEditPart(REFERENCE2, DEdgeEditPart.class);
        checkRoutingStyle(botGef, "None", "Semicircle", "Normal", false);
        label = editor.getEditPart(REFERENCE1, DEdgeNameEditPart.class);
        checkLayoutConstraint(label);
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);
        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();
        bot.waitUntil(new CheckSelectedCondition(editor, name));
        return botPart;
    }

    private void checkRoutingStyle(SWTBotGefEditPart bot, String jumpLinkStatus, String jumpLinkType, String smoothness, boolean reverseJumpLink) {
        RoutingStyle routingStyle = (RoutingStyle) ((org.eclipse.gmf.runtime.notation.Edge) bot.part().getModel()).getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        assertEquals("The Jump Link Status should be equals to '" + jumpLinkStatus + "'", jumpLinkStatus, routingStyle.getJumpLinkStatus().getLiteral());
        assertEquals("The Jump Link Type should be equals to '" + jumpLinkType + "'", jumpLinkType, routingStyle.getJumpLinkType().getLiteral());
        assertEquals("The Smoothness should be equals to '" + smoothness + "'", smoothness, routingStyle.getSmoothness().getLiteral());
        if (reverseJumpLink) {
            assertEquals("The reverse Jump Link should be checked", reverseJumpLink, routingStyle.isJumpLinksReverse());
        } else {
            assertEquals("The reverse Jump Link should not be checked", reverseJumpLink, routingStyle.isJumpLinksReverse());
        }
    }

    /**
     * @param label
     */
    private void checkLayoutConstraint(SWTBotGefEditPart label) {
        LayoutConstraint constraint = ((org.eclipse.gmf.runtime.notation.Node) label.part().getModel()).getLayoutConstraint();
        assertTrue("The layout constraint should be a org.eclipse.gmf.runtime.notation.Bounds)", constraint instanceof Bounds);

        Size size = (Size) constraint;
        assertEquals("Default width value (-1) expected.", -1, size.getWidth());
        assertEquals("Default height value (-1) expected.", -1, size.getHeight());
    }

}
