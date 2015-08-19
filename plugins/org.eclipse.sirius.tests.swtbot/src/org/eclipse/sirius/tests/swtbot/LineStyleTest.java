/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.Shape;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test the border line style.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class LineStyleTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "borderLineStyle.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/borderLineStyle/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "new Diagram";

    private static final String REPRESENTATION_NAME = "Diagram";

    private static final String PROPERTIES = "Properties";

    private static final String STYLE = "Style";

    private static final String APPEARANCE = "Appearance";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Ensure that the border line style of nodes and containers is correctly
     * updated from the property section.
     */
    public void testContainerBorderLineStyleCustomization() {
        for (DDiagramElementContainer container : ((DDiagram) editor.getDRepresentation()).getContainers()) {
            doTestLineStyleCustomizationFromPropertySection(container.getName(), false);
        }
    }

    /**
     * Ensure that the border line style of nodes and containers is correctly
     * updated from the property section.
     */
    public void testNodeBorderLineStyleCustomization() {
        for (DNode node : ((DDiagram) editor.getDRepresentation()).getNodes()) {
            doTestLineStyleCustomizationFromPropertySection(node.getName(), false);
        }
    }

    /**
     * Ensure that the line style of edge is correctly updated from the property
     * section.
     */
    public void testEdgeLineStyleCustomization() {
        for (DEdge edge : ((DDiagram) editor.getDRepresentation()).getEdges()) {
            doTestLineStyleCustomizationFromPropertySection(edge.getName(), true);
        }

    }

    private void doTestLineStyleCustomizationFromPropertySection(String label, boolean edge) {
        SWTBotGefEditPart editPart = selectAndCheckEditPart(label);
        checkLineStyle(editPart, LineStyle.SOLID_LITERAL, false);
        changeLineStyle(LineStyle.DASH_LITERAL, edge);
        checkLineStyle(editPart, LineStyle.DASH_LITERAL, true);
        changeLineStyle(LineStyle.DOT_LITERAL, edge);
        checkLineStyle(editPart, LineStyle.DOT_LITERAL, true);
        changeLineStyle(LineStyle.DASH_DOT_LITERAL, edge);
        checkLineStyle(editPart, LineStyle.DASH_DOT_LITERAL, true);

        ICondition done = new OperationDoneCondition();
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        bot.waitUntil(done);
        checkLineStyle(editPart, LineStyle.SOLID_LITERAL, false);
    }

    /**
     * Ensure that the border line style of nodes and containers and the line
     * style of edges are correctly updated from a description change
     * (activation of layer triggering a style customization).
     */
    public void testLineStyleUpdateFromDescriptionChange() {
        // Activate Dot layer
        doTestLineStyleUpdateAfterLayerActivation("Dot", LineStyle.DOT_LITERAL);
        // Activate Dash_Dot layer
        doTestLineStyleUpdateAfterLayerActivation("Dash_Dot", LineStyle.DASH_DOT_LITERAL);
        // Activate Dash layer
        doTestLineStyleUpdateAfterLayerActivation("Dash", LineStyle.DASH_LITERAL);
        // Disable Dash layer
        doTestLineStyleUpdateAfterLayerActivation("Dash", LineStyle.DASH_DOT_LITERAL);
        // Disable Dash_Dot layer
        doTestLineStyleUpdateAfterLayerActivation("Dash_Dot", LineStyle.DOT_LITERAL);
        // Disable Dot layer
        doTestLineStyleUpdateAfterLayerActivation("Dot", LineStyle.SOLID_LITERAL);

    }

    private void doTestLineStyleUpdateAfterLayerActivation(String layerName, LineStyle expectedLineStyle) {
        ICondition done = new OperationDoneCondition();
        editor.click(0, 0);
        editor.changeLayerActivation(layerName);
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();

        for (DDiagramElement dde : ((DDiagram) editor.getDRepresentation()).getOwnedDiagramElements()) {
            SWTBotGefEditPart editPart = selectAndCheckEditPart(dde.getName());
            checkLineStyle(editPart, expectedLineStyle, false);
        }
    }

    private void checkLineStyle(SWTBotGefEditPart editPart, LineStyle expectedBorderLineStyle, boolean customFeature) {
        IDiagramElementEditPart part = (IDiagramElementEditPart) editPart.part();
        DDiagramElement diagramElement = DiagramElementEditPartOperation.resolveDiagramElement(part);
        String eltId = diagramElement.getName() + "' (" + diagramElement.getMapping().getName() + ")";

        // Check the style
        LineStyle lineStyle = null;
        Style style = diagramElement.getStyle();
        if (diagramElement instanceof DEdge) {
            assertTrue("Wrong style type for " + eltId, style instanceof EdgeStyle);
            EdgeStyle edgeStyle = (EdgeStyle) diagramElement.getStyle();
            lineStyle = edgeStyle.getLineStyle();
        } else {
            assertTrue("Wrong style type for " + eltId, style instanceof BorderedStyle);
            BorderedStyle borderedStyle = (BorderedStyle) diagramElement.getStyle();
            lineStyle = borderedStyle.getBorderLineStyle();
        }

        assertEquals("Wrong border line style for " + eltId, expectedBorderLineStyle, lineStyle);
        assertTrue(style instanceof Customizable);
        // The style has been customized by the user bot, check the custom
        // features
        assertEquals("Wrong style.customFeatures for " + eltId, customFeature, ((Customizable) style).getCustomFeatures().contains(DiagramPackage.Literals.BORDERED_STYLE__BORDER_LINE_STYLE.getName())
                || ((Customizable) style).getCustomFeatures().contains(DiagramPackage.Literals.EDGE_STYLE__LINE_STYLE.getName()));

        // Check the figure
        IFigure figure = null;
        Border border = null;
        if (part instanceof IDiagramNodeEditPart || part instanceof IDiagramBorderNodeEditPart) {
            IStyleEditPart styleEditPart = part.getStyleEditPart();
            assertNotNull(styleEditPart);

            figure = (IFigure) styleEditPart.getFigure().getChildren().get(0);
            border = figure.getBorder();
        } else if (part instanceof IDiagramContainerEditPart) {
            figure = ((AbstractDiagramElementContainerEditPart) part).getPrimaryShape();
            border = figure.getBorder();
        } else if (part instanceof IDiagramEdgeEditPart) {
            figure = part.getFigure();
        }

        if (border instanceof LineBorder) {
            int borderStyle = ((LineBorder) border).getStyle();
            checkLineStyle(eltId, expectedBorderLineStyle, borderStyle, false);
        }

        if (figure instanceof Shape) {
            int figureLineStyle = ((Shape) figure).getLineStyle();
            checkLineStyle(eltId, expectedBorderLineStyle, figureLineStyle, part instanceof IDiagramEdgeEditPart);
        } else if (figure instanceof NodeFigure) {
            int figureLineStyle = ((NodeFigure) figure).getLineStyle();
            checkLineStyle(eltId, expectedBorderLineStyle, figureLineStyle, false);
        }
    }

    private void checkLineStyle(String eltId, LineStyle expectedBorderLineStyle, int borderStyle, boolean edge) {
        switch (expectedBorderLineStyle.getValue()) {
        case LineStyle.SOLID:
            assertEquals("Wrong line style for " + eltId, SWT.LINE_SOLID, borderStyle);
            break;
        case LineStyle.DOT:
            assertEquals("Wrong line style for " + eltId, SWT.LINE_DOT, borderStyle);
            break;
        case LineStyle.DASH:
            assertEquals("Wrong line style for " + eltId, edge ? SWT.LINE_CUSTOM : SWT.LINE_DASH, borderStyle);
            break;
        case LineStyle.DASH_DOT:
            assertEquals("Wrong line style for " + eltId, SWT.LINE_DASHDOT, borderStyle);
            break;
        default:
            fail("Complete the test for the expected border line style " + expectedBorderLineStyle.getName());
            break;
        }
    }

    private void changeLineStyle(LineStyle lineStyle, boolean edge) {
        ICondition done = new OperationDoneCondition();
        bot.viewByTitle(PROPERTIES).setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(STYLE);
        SWTBotTreeItem style;
        SWTBotTree propertiesTree = bot.viewByTitle(PROPERTIES).bot().tree(0);
        if (edge) {
            style = propertiesTree.getTreeItem("General").expand().getNode("Line Style");
        } else {
            style = propertiesTree.getTreeItem("Border").expand().getNode("Border Line Style");
        }
        style.click();
        SWTBotCCombo lineStyleCombo = bot.viewByTitle(PROPERTIES).bot().ccomboBox();
        lineStyleCombo.setFocus();
        lineStyleCombo.setSelection(lineStyle.getName());
        // apply change with change focus
        SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE);
        bot.waitUntil(done);
        editor.setFocus();
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name) {
        SWTBotGefEditPart botPart = editor.getEditPart(name).parent();
        assertNotNull("The requested edit part should not be null", botPart);
        ICondition selected = new CheckSelectedCondition(editor, botPart.part());
        botPart.select();
        bot.waitUntil(selected);
        return botPart;
    }

}
