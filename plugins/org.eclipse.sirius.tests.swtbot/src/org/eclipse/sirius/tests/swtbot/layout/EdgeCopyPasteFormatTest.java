/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.model.business.internal.spec.DEdgeSpec;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Copy Paste Layout manage specific custom style (Jump Links, Smoothness). This
 * test, test copy layout and style from representation with edge with JumpLinks
 * with 'Above' status and with 'Chamfered' Type, with smoothness set to normal
 * and paste this layout on representation with no custom style. Check that the
 * custom style was applied to new representation. Copy Paste Layout should not
 * set a Location during paste layout for edge labels with copied (-1,-1)
 * default size: resize label will be forbidden.
 * 
 * @author jdupont
 */
public class EdgeCopyPasteFormatTest extends AbstractSiriusSwtBotGefTestCase {

    /** Orange color. */
    private static final Color ORANGE = new Color(null, 252, 175, 62, 255);

    /** Gray color. */
    private static final Color GRAY = new Color(null, 136, 136, 136, 255);

    /** Point used to copy Layout. */
    private static final Point FIRST_POINT = new Point(1, 1);

    /** Point used to paste layout which do not reference any edit part. */
    private static final Point SECOND_POINT = new Point(500, 250);

    private static final String NONE_SMOOTHNESS = "None";

    private static final String NORMAL_SMOOTHNESS = "Normal";

    private static final String ABOVE_LINK_STATUS = "Above";

    private static final String BELOW_LINK_STATUS = "Below";

    private static final String NONE_LINK_STATUS = NONE_SMOOTHNESS;

    private static final String CHAMFERED_LINK_TYPE = "Chamfered";

    private static final String SEMICIRCLE_LINK_TYPE = "Semicircle";

    private static final String SQUARE_LINK_TYPE = "Square";

    private static final String REPRESENTATION_NAME1 = "root package entities";

    private static final String REPRESENTATION_NAME2 = "root package entities sans layout";

    private static final String REPRESENTATION_NAME3 = "rootPackageEntitiesAdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_NAME4 = "rootPackageEntitiesSansLayoutAdaptedForCopyPasteFormatTest";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/layout/edgeCopyPasteLayout/";

    private static final String FILE_DIR = "/";

    private static final String REFERENCE1 = "[0..1] newEReference1";

    private static final String REFERENCE2 = "[0..1] newEReference2";

    private static final String ECLASS_1_NAME = "NewEClass1";

    /**
     * Diagram on first representation.
     */
    private SWTBotSiriusDiagramEditor diagram1;

    /**
     * Diagram on second representation.
     */
    private SWTBotSiriusDiagramEditor diagram2;

    /**
     * Diagram on third representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ).
     */
    private SWTBotSiriusDiagramEditor diagram3;

    /**
     * Diagram on fourth representation using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ).
     */
    private SWTBotSiriusDiagramEditor diagram4;

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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        diagram1 = null;
        diagram2 = null;
        diagram3 = null;
        diagram4 = null;
        clearFormatDataManager();
        super.tearDown();
    }

    /**
     * Test that the paste layout affect custom style for edges.
     */
    public void testEdgeCopyPasteLayout() {
        // Open the 2 required representations
        diagram2 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME2, DDiagram.class);
        diagram1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
        // Check style of the first representation : it should have custom style
        // and not the second
        checkLayoutStyleReferences(diagram1, REFERENCE1, ABOVE_LINK_STATUS, CHAMFERED_LINK_TYPE, NONE_SMOOTHNESS, true);
        checkLayoutStyleReferences(diagram1, REFERENCE2, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NORMAL_SMOOTHNESS, false);
        // Check style of the second representation : it should have no custom
        // style
        checkLayoutStyleReferences(diagram2, REFERENCE1, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkLayoutStyleReferences(diagram2, REFERENCE2, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        // Select all elements on first representation and copy layout.
        diagram1.click(FIRST_POINT);
        diagram1.clickContextMenu(Messages.CopyFormatAction_text);
        // Paste layout on second representation
        diagram2.show();
        diagram2.click(SECOND_POINT);
        diagram2.clickContextMenu(Messages.PasteLayoutAction_text);
        // Check Routing style and layout constraints on second representation
        // (should have custom style)
        checkLayoutStyleReferences(diagram2, REFERENCE1, ABOVE_LINK_STATUS, CHAMFERED_LINK_TYPE, NONE_SMOOTHNESS, true);
        checkLayoutStyleReferences(diagram2, REFERENCE2, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NORMAL_SMOOTHNESS, false);
    }

    /**
     * Just check that the copy layout is OK, ie without exception, when the selection contains a Node and an edge's
     * label.
     */
    public void testNodeAndEdgeLabelCopyLayout() {
        boolean oldIsErrorCatchActive = isErrorCatchActive();
        setErrorCatchActive(true);
        try {
            // Open the required representation
            diagram1 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME1, DDiagram.class);
            // Select a node and an edge's label
            SWTBotGefEditPart ege1LabelEditPart = diagram1.getEditPart(REFERENCE1, AbstractDiagramNameEditPart.class);
            SWTBotGefEditPart nodeEditPart = diagram1.getEditPart(ECLASS_1_NAME, DNodeListEditPart.class);
            diagram1.select(nodeEditPart, ege1LabelEditPart);
            diagram1.clickContextMenu(Messages.CopyFormatAction_text);
            SWTBotUtils.waitAllUiEvents();
        } finally {
            setErrorCatchActive(oldIsErrorCatchActive);
            assertFalse("The copy format action fails." + getErrorLoggersMessage(), doesAnErrorOccurs());
        }
    }

    /**
     * Test that the paste layout affect custom style for edges from and to diagrams using a specific copy/paste
     * extension ( {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider} ).
     */
    public void testEdgeCopyPasteLayoutUsingDiagramsWithExtension() {
        // Open the 2 required representations
        diagram4 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME4, DDiagram.class);
        diagram3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);
        // Check style of the first representation : it should have custom style
        // and not the second
        checkLayoutStyleReferences(diagram3, REFERENCE1, ABOVE_LINK_STATUS, CHAMFERED_LINK_TYPE, NONE_SMOOTHNESS, true);
        checkLayoutStyleReferences(diagram3, REFERENCE2, BELOW_LINK_STATUS, SQUARE_LINK_TYPE, NORMAL_SMOOTHNESS, true);
        checkEdgeColor(diagram3, REFERENCE2, ORANGE, "Orange");
        // Check style of the second representation : it should have no custom
        // style
        checkLayoutStyleReferences(diagram4, REFERENCE1, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkLayoutStyleReferences(diagram4, REFERENCE2, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkEdgeColor(diagram4, REFERENCE1, GRAY, "Gray");
        // Select all elements on first representation and copy layout.
        diagram3.click(FIRST_POINT);
        diagram3.clickContextMenu(Messages.CopyFormatAction_text);
        // Paste layout on second representation
        diagram4.show();
        diagram4.click(SECOND_POINT);
        diagram4.clickContextMenu(Messages.PasteLayoutAction_text);
        // Check Routing style and layout constraints on second representation
        checkLayoutStyleReferences(diagram4, REFERENCE1, BELOW_LINK_STATUS, SQUARE_LINK_TYPE, NORMAL_SMOOTHNESS, true);
        // Check style on second representation has not been changed
        checkEdgeColor(diagram4, REFERENCE1, GRAY, "Gray");
    }

    /**
     * Test that the paste style affect custom style for edges from and to
     * diagrams using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ).
     */
    public void testEdgeCopyPasteStyleUsingDiagramsWithExtension() {
        // Open the 2 required representations
        diagram4 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME4, DDiagram.class);
        diagram3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);
        // Check style of the first representation : it should have custom style
        // and not the second
        checkLayoutStyleReferences(diagram3, REFERENCE1, ABOVE_LINK_STATUS, CHAMFERED_LINK_TYPE, NONE_SMOOTHNESS, true);
        checkLayoutStyleReferences(diagram3, REFERENCE2, BELOW_LINK_STATUS, SQUARE_LINK_TYPE, NORMAL_SMOOTHNESS, true);
        checkEdgeColor(diagram3, REFERENCE2, ORANGE, "Orange");
        // Check style of the second representation : it should have no custom
        // style
        checkLayoutStyleReferences(diagram4, REFERENCE1, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkLayoutStyleReferences(diagram4, REFERENCE2, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkEdgeColor(diagram4, REFERENCE1, GRAY, "Gray");
        checkEdgeColor(diagram4, REFERENCE2, GRAY, "Gray");
        // Select all elements on first representation and copy layout.
        diagram3.click(FIRST_POINT);
        diagram3.clickContextMenu(Messages.CopyFormatAction_text);
        // Paste layout on second representation
        diagram4.show();
        diagram4.click(SECOND_POINT);
        diagram4.clickContextMenu(Messages.PasteStyleAction_text);
        // Check Routing style and layout constraints on second representation
        // does not change
        checkLayoutStyleReferences(diagram4, REFERENCE1, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        // Check style on second representation change in orange
        checkEdgeColor(diagram4, REFERENCE1, ORANGE, "Orange");
    }

    /**
     * Test that the paste format affect custom style for edges from and to
     * diagrams using a specific copy/paste extension (
     * {@link org.eclipse.sirius.tests.unit.diagram.format.data.manager.extension.SampleNameDataProvider}
     * ).
     */
    public void testEdgeCopyPasteFormatUsingDiagramsWithExtension() {
        // Open the 2 required representations
        diagram4 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME4, DDiagram.class);
        diagram3 = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME3, DDiagram.class);
        // Check style of the first representation : it should have custom style
        // and not the second
        checkLayoutStyleReferences(diagram3, REFERENCE1, ABOVE_LINK_STATUS, CHAMFERED_LINK_TYPE, NONE_SMOOTHNESS, true);
        checkLayoutStyleReferences(diagram3, REFERENCE2, BELOW_LINK_STATUS, SQUARE_LINK_TYPE, NORMAL_SMOOTHNESS, true);
        checkEdgeColor(diagram3, REFERENCE2, ORANGE, "Orange");
        // Check style of the second representation : it should have no custom
        // style
        checkLayoutStyleReferences(diagram4, REFERENCE1, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkLayoutStyleReferences(diagram4, REFERENCE2, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkEdgeColor(diagram4, REFERENCE1, GRAY, "Gray");
        checkEdgeColor(diagram4, REFERENCE2, GRAY, "Gray");
        // Select all elements on first representation and copy layout.
        diagram3.click(FIRST_POINT);
        diagram3.clickContextMenu(Messages.CopyFormatAction_text);
        // Paste layout on second representation
        diagram4.show();
        diagram4.click(SECOND_POINT);
        diagram4.clickContextMenu(Messages.PasteFormatAction_text);
        // Check Routing style and layout constraints on second representation
        // does not change on REFERENCE2 but change on REFERENCE1 (target ref1
        // is map
        // to source ref2)
        checkLayoutStyleReferences(diagram4, REFERENCE2, NONE_LINK_STATUS, SEMICIRCLE_LINK_TYPE, NONE_SMOOTHNESS, false);
        checkLayoutStyleReferences(diagram4, REFERENCE1, BELOW_LINK_STATUS, SQUARE_LINK_TYPE, NORMAL_SMOOTHNESS, true);
        // Check style on second representation change in orange for ref1
        checkEdgeColor(diagram4, REFERENCE2, GRAY, "Gray");
        checkEdgeColor(diagram4, REFERENCE1, ORANGE, "Orange");
    }

    /**
     * Check layout style references layout.
     * 
     * @param diagramEditor
     *            diagram representation
     * @param reference
     *            name of the reference to check
     * @param jumpLinkStatus
     *            jump link status expected for the reference
     * @param jumpLinkType
     *            jump link type expected for the reference
     * @param smoothness
     *            smoothness expected for the reference
     * @param reverseJumpLink
     *            reverse jump link expected for the reference
     */
    private void checkLayoutStyleReferences(SWTBotSiriusDiagramEditor diagramEditor, String reference, String jumpLinkStatus, String jumpLinkType, String smoothness, boolean reverseJumpLink) {
        SWTBotGefEditPart botGef = selectEditPart(diagramEditor, reference, DEdgeEditPart.class);
        checkRoutingStyle(botGef, jumpLinkStatus, jumpLinkType, smoothness, reverseJumpLink);
        SWTBotGefEditPart label = diagramEditor.getEditPart(reference, DEdgeNameEditPart.class);
        checkLayoutConstraint(label);
    }

    /**
     * Check routing style for a given reference.
     * 
     * @param bot
     *            reference edit part to check
     * @param jumpLinkStatus
     *            jump link status expected for the reference
     * @param jumpLinkType
     *            jump link type expected for the reference
     * @param smoothness
     *            smoothness expected for the reference
     * @param reverseJumpLink
     *            reverse jump link expected for the reference
     */
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
     * Check layout Constraint for a given reference.
     * 
     * @param label
     *            The label of the edit part to check
     */
    private void checkLayoutConstraint(SWTBotGefEditPart label) {
        LayoutConstraint constraint = ((org.eclipse.gmf.runtime.notation.Node) label.part().getModel()).getLayoutConstraint();
        assertTrue("The layout constraint should be a org.eclipse.gmf.runtime.notation.Bounds)", constraint instanceof Bounds);

        Size size = (Size) constraint;
        assertEquals("Default width value (-1) expected.", -1, size.getWidth());
        assertEquals("Default height value (-1) expected.", -1, size.getHeight());
    }

    /**
     * Select an edit part from a diagram editor.
     * 
     * @param diagramEditor
     *            diagram representation
     * @param name
     *            name of the edit part to select
     * @param type
     *            type of the edit Part to select
     * @return the edit part selected
     */
    private SWTBotGefEditPart selectEditPart(SWTBotSiriusDiagramEditor diagramEditor, String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = diagramEditor.getEditPart(name, type);
        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();
        bot.waitUntil(new CheckSelectedCondition(diagramEditor, name));
        return botPart;
    }

    /**
     * Check edge color for a given reference.
     * 
     * @param diagramEditor
     *            diagram representation
     * @param reference
     *            name of the reference to check
     * @param color
     *            expected color
     * @param colorName
     *            expected color name
     */
    private void checkEdgeColor(SWTBotSiriusDiagramEditor diagramEditor, String reference, Color color, String colorName) {
        for (DRepresentationElement representationElement : diagramEditor.getDRepresentation().getRepresentationElements()) {
            if (representationElement instanceof DEdgeSpec) {
                DEdgeSpec edge = (DEdgeSpec) representationElement;
                if (reference.equals(edge.getName())) {
                    assertTrue("The color of edge " + reference + " must be " + colorName, isSameColor(((EdgeStyle) edge.getStyle()).getStrokeColor(), color));
                    return;
                }
            }
        }
    }

    /**
     * Compare two colors.
     * 
     * @param color
     *            the first color to compare
     * @param colorRef
     *            the second color to compare
     * @return true if color are the same, false otherwise.
     */
    private boolean isSameColor(RGBValues color, Color colorRef) {
        return color.getBlue() == colorRef.getBlue() && color.getGreen() == colorRef.getGreen() && color.getRed() == colorRef.getRed();
    }

}
