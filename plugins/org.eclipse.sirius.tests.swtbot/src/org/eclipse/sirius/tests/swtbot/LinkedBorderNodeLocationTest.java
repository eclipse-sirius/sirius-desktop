/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This class tests the location of linked border nodes.
 * 
 * @author gplouhinec
 *
 */
public class LinkedBorderNodeLocationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final int OFFSET_BORDER_NORTH = 8;

    private static final int OFFSET_BORDER_WEST = 8;

    private static final int OFFSET_BORDER_SOUTH = 10;

    private static final int SAFETY_MARGIN = 2;

    private static final int OFFSET_BORDER_EAST = 10;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private static final String DATA_UNIT_DIR = "data/unit/borderNodeSide/github-issue-145/"; //$NON-NLS-1$

    private static final String MODEL = "github-issue-145.ecore"; //$NON-NLS-1$

    private static final String SESSION_FILE = "github-issue-145.aird"; //$NON-NLS-1$

    private static final String VSM_FILE = "github-issue-145.odesign"; //$NON-NLS-1$

    private static final String FILE_DIR = "/"; //$NON-NLS-1$

    private static final String REPRESENTATION_DESCRIPTION_NAME = "github-issue-145"; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME = REPRESENTATION_DESCRIPTION_NAME;

    private static final String BORDER_NODE_1 = "EClass1_1"; //$NON-NLS-1$

    private static final String BORDER_NODE_2 = "EClass1_2"; //$NON-NLS-1$

    private static final String BORDER_NODE_3 = "EClass1_3"; //$NON-NLS-1$

    private static final String BORDER_NODE_4 = "EClass1_4"; //$NON-NLS-1$

    private static final String SUB_CONTAINER = "SubEPackage1"; //$NON-NLS-1$

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
    }

    /**
     * Drag and drop of an element with unpinned linked border nodes should arrange these linked border nodes according
     * to the other border nodes to which they are linked.
     */
    public void testDnDSubContainerWithLinkedBorderNodes() {
        SWTBotGefEditPart subPackageEP = editor.getEditPart(SUB_CONTAINER, DNodeContainer2EditPart.class);
        editor.clickCentered(subPackageEP);
        bot.waitUntil(new CheckSelectedCondition(editor, SUB_CONTAINER, DNodeContainer2EditPart.class));

        dragAndDropSubContainer("EPackage1"); //$NON-NLS-1$
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_1), PositionConstants.NORTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_2), PositionConstants.EAST);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_3), PositionConstants.SOUTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_4), PositionConstants.WEST);

        dragAndDropSubContainer("EPackage6"); //$NON-NLS-1$
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_1), PositionConstants.WEST);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_2), PositionConstants.SOUTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_3), PositionConstants.SOUTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_4), PositionConstants.WEST);

        dragAndDropSubContainer("EPackage7"); //$NON-NLS-1$
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_1), PositionConstants.NORTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_2), PositionConstants.NORTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_3), PositionConstants.WEST);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_4), PositionConstants.WEST);

        dragAndDropSubContainer("EPackage8"); //$NON-NLS-1$
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_1), PositionConstants.NORTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_2), PositionConstants.EAST);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_3), PositionConstants.EAST);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_4), PositionConstants.NORTH);

        dragAndDropSubContainer("EPackage9"); //$NON-NLS-1$
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_1), PositionConstants.EAST);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_2), PositionConstants.EAST);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_3), PositionConstants.SOUTH);
        checkBorderNodePosition(getSubContainerBounds(SUB_CONTAINER), getBorderNodeBounds(BORDER_NODE_4), PositionConstants.SOUTH);
    }

    private void dragAndDropSubContainer(String targetName) {
        Point subEPackage1Center = getSubContainerBounds(SUB_CONTAINER).getCenter().getCopy();
        Point targetCenter = getContainerBounds(targetName).getCenter().getCopy();
        CheckEditPartMoved movedCondition = new CheckEditPartMoved(editor, SUB_CONTAINER, DNodeContainer2EditPart.class, getSubContainerBounds(SUB_CONTAINER).getLocation());
        editor.drag(subEPackage1Center, targetCenter);
        bot.waitUntil(movedCondition);
    }

    private Rectangle getBorderNodeBounds(String borderNodeName) {
        return editor.getBounds(editor.getEditPart(borderNodeName, AbstractDiagramBorderNodeEditPart.class));
    }

    private Rectangle getSubContainerBounds(String containerName) {
        return editor.getBounds(editor.getEditPart(containerName, DNodeContainer2EditPart.class));
    }

    private Rectangle getContainerBounds(String containerName) {
        return editor.getBounds(editor.getEditPart(containerName, DNodeContainerEditPart.class));
    }

    /**
     * Checks that a border node is on the correct side of its bordered node. Magic numbers are used here, corresponding
     * to the delta between the border of the bordered node and the closest border of the border node.
     * 
     * @param borderedNodeBounds
     *            the bordered node rectangle
     * @param borderNodeBounds
     *            the border node rectangle
     * @param expectedSide
     *            the side where the border node is expected among {@code PositionConstants.NORTH},
     *            {@code PositionConstants.EAST}, {@code PositionConstants.SOUTH}, {@code PositionConstants.WEST}.
     */
    private static void checkBorderNodePosition(Rectangle borderedNodeBounds, Rectangle borderNodeBounds, int expectedSide) {
        switch (expectedSide) {
        case PositionConstants.NORTH:
            assertEquals(borderedNodeBounds.getTop().y, borderNodeBounds.getBottom().y - OFFSET_BORDER_NORTH, SAFETY_MARGIN);
            break;
        case PositionConstants.EAST:
            assertEquals(borderedNodeBounds.getRight().x, borderNodeBounds.getLeft().x + OFFSET_BORDER_EAST, SAFETY_MARGIN);
            break;
        case PositionConstants.SOUTH:
            assertEquals(borderedNodeBounds.getBottom().y, borderNodeBounds.getTop().y + OFFSET_BORDER_SOUTH, SAFETY_MARGIN);
            break;
        case PositionConstants.WEST:
            assertEquals(borderedNodeBounds.getLeft().x, borderNodeBounds.getRight().x - OFFSET_BORDER_WEST, SAFETY_MARGIN);
            break;
        }

    }
}
