/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This class test moving ports simultaneously.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class MoveBorderNodeTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String SEMANTIC_MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "borderNode.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/moveBorderNodes/";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "BorderNodeDiagram", "new BorderNodeDiagram", DDiagram.class, true);
    }

    /**
     * Check if after a move in a corner, the border nodes have not the same GMF
     * locations.
     */
    public void testMoveSeveralBorderNodes() {
        // Select the border nodes
        final List<SWTBotGefEditPart> partsToSelect = new ArrayList<>();
        partsToSelect.add(editor.getEditPart("A", AbstractDiagramBorderNodeEditPart.class));
        partsToSelect.add(editor.getEditPart("B", AbstractDiagramBorderNodeEditPart.class));
        partsToSelect.add(editor.getEditPart("C", AbstractDiagramBorderNodeEditPart.class));
        editor.select(partsToSelect);
        // Get the center coordinates of the first border node
        final Point pointToDrag = editor.getAbsoluteCenter((GraphicalEditPart) partsToSelect.get(0).part());

        // Compute the drop destination
        final Point endpoint = pointToDrag.getTranslated(2000, 2000);
        // Drag'and'drop with the mouse
        editor.drag(pointToDrag.x, pointToDrag.y, endpoint.x, endpoint.y);
        // Wait that border nodes have been moved
        bot.waitUntil(new OperationDoneCondition());
        // Get the new GMF location of each border nodes and compare with each
        // other
        for (int i = 0; i < partsToSelect.size() - 1; i++) {
            Point firstPoint = getGMFLocation(partsToSelect.get(i).part());
            for (int j = i + 1; j < partsToSelect.size(); j++) {
                Point secondPoint = getGMFLocation(partsToSelect.get(j).part());
                assertFalse("The GMF locations of moved border nodes should be different.", firstPoint.equals(secondPoint));
            }
        }
    }

    /**
     * Get the GMF location of specified edit part.
     * 
     * @param part
     *            The edit part
     * @return GMF location of part.
     */
    protected Point getGMFLocation(final EditPart part) {
        if (part.getModel() instanceof View) {
            Node view = (Node) part.getModel();
            if (view.getLayoutConstraint() instanceof Location) {
                return new Point(((Location) view.getLayoutConstraint()).getX(), ((Location) view.getLayoutConstraint()).getY());
            }
        }
        fail("Unable to determine GMF location.");
        return null;
    }
}
