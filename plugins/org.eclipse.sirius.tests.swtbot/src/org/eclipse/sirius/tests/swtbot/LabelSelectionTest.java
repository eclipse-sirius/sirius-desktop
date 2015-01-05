/*******************************************************************************
 * Copyright (c) 2010-2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;

import java.util.List;

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper.EdgeData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.ui.PlatformUI;

/**
 * Test class for label selection.
 * 
 * @author dlecan
 */
public class LabelSelectionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "trac1522 package entities";

    private static final String REPRESENTATION_NAME = "TC 1522";

    private static final String MODEL = "tc1522.ecore";

    private static final String SESSION_FILE = "tc1522.aird";

    private static final String VSM_FILE = "tc1522.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/labelSelection/";

    private static final String FILE_DIR = "/";

    private Color selectionColor;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

            @Override
            public void run() {
                selectionColor = PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);
            }
        });

    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckLabelSelection() throws Exception {
        final SWTBotGefEditPart editPart = editor.getEditPart("Class2").parent();

        final List<EdgeData> edgeData = SWTBotCommonHelper.getEdgeData(editPart, editPart, editor);

        assertThat("Wrong number of edges found", edgeData.size(), equalTo(1));

        final SWTBotGefConnectionEditPart swtBotConnectionEditPart = edgeData.get(0).getSwtBotEditPart();

        final ConnectionEditPart connectionEditPart = swtBotConnectionEditPart.part();

        assertThat(connectionEditPart.getFigure(), instanceOf(ViewEdgeFigure.class));

        final ViewEdgeFigure viewEdgeFigure = (ViewEdgeFigure) connectionEditPart.getFigure();

        // Before selection
        final int previousLineSize = getEdgeLineWidth(viewEdgeFigure);
        final Color previousColor = viewEdgeFigure.getForegroundColor();

        // Select the edge
        editor.select(swtBotConnectionEditPart);

        // After selection
        assertThat(viewEdgeFigure.getForegroundColor(), equalTo(selectionColor));

        final int newLineWidth = getEdgeLineWidth(viewEdgeFigure);
        assertThat("Edge line size did not increase on selection", newLineWidth, greaterThan(previousLineSize));

        // Unselect edge
        editor.select(editor.getEditPart("Class1", AbstractBorderedShapeEditPart.class));

        assertThat("Edge color is not back to previous one", viewEdgeFigure.getForegroundColor(), equalTo(previousColor));

        final int unSelectedLineWidth = getEdgeLineWidth(viewEdgeFigure);
        assertThat("Edge line is not back to normal size", unSelectedLineWidth, equalTo(previousLineSize));
    }

    private int getEdgeLineWidth(final ViewEdgeFigure viewEdgeFigure) {
        int result = 0;
        for (final Object child : viewEdgeFigure.getChildren()) {
            if (child instanceof PolylineDecoration) {
                final PolylineDecoration decoration = (PolylineDecoration) child;
                result = decoration.getLineWidth();
                break;
            }
        }
        return result;
    }
}
