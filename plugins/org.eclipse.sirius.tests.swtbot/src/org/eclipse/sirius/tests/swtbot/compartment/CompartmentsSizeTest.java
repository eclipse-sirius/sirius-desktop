/*******************************************************************************
 * Copyright (c) 2018, 2023 Obeo.
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
package org.eclipse.sirius.tests.swtbot.compartment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckBoundsCondition;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;

/**
 * Test the Make Same Size and Auto-size actions on compartments.
 * 
 * @author fbarbin
 *
 */
public class CompartmentsSizeTest extends AbstractCompartmentTest {

    /** Tooltip of Make same size action. */
    private static final String MAKE_SAME_SIZE_ACTION_TOOLTIP = "Make height and width same size"; //$NON-NLS-1$

    /** Tooltip of Auto size action. */
    private static final String AUTO_SIZE_ACTION_TOOLTIP = "Auto Size"; //$NON-NLS-1$

    /** Message to display when the session is dirty. */
    private static final String SESSION_SHOULD_NOT_BE_DIRTY_MSG = "Session should not be dirty."; //$NON-NLS-1$

    /** Message to display when the width is not the expected one after the auto size action. */
    private static final String AUTO_SIZE_WRONG_WIDTH_ERROR_MSG = "with the auto-size, the new region width should be smaller than the previous one."; //$NON-NLS-1$

    private static final Rectangle REGION_V_P1_BOUNDS_GMF = new Rectangle(64, 16, -1, -1);

    private static final Rectangle REGION_P1_BOUNDS_DRAW2D_VSTACK = new Rectangle(64, 16, 141, 414);

    /**
     * Here the data contains an "unexpected bounds" for the "az" regions container, the target (height is equals to the
     * real height on not to -1). But it could exist in old model. This is why the parameter is adapted.
     */
    private static final Rectangle REGION_V_AZ_BOUNDS_GMF = new Rectangle(338, 66, 166, -1);

    private static final Rectangle REGION_AZ_BOUNDS_DRAW2D_VSTACK = new Rectangle(338, 66, 166, 198);

    private static final Rectangle REGION_H_P1_BOUNDS_GMF = new Rectangle(0, 0, -1, -1);

    private static final Rectangle REGION_P1_BOUNDS_DRAW2D_HSTACK = new Rectangle(0, 0, 831, 247);

    /**
     * Here the data contains an "unexpected bounds" for the "az" regions container, the target (height is equals to the
     * real height on not to -1). But it could exist in old model. This is why the parameter is adapted.
     */
    private static final Rectangle REGION_H_AZ_BOUNDS_GMF = new Rectangle(940, 125, -1, 258);

    private static final Rectangle REGION_AZ_BOUNDS_DRAW2D_HSTACK = new Rectangle(940, 125, 233, 258);

    private static final Rectangle FREE_FORM_CONTAINER_H_BOUNDS = new Rectangle(700, 400, 743, 207);

    private static final Rectangle FREE_FORM_CONTAINER_V_BOUNDS = new Rectangle(535, 30, 128, 338);

    private String oldFont;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        oldFont = changeDefaultFontName("Comic Sans MS"); //$NON-NLS-1$
    }

    @Override
    protected void tearDown() throws Exception {
        if (editor != null) {
            editor.close();
        }
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
        super.tearDown();
    }

    /**
     * This test makes sure the make same size on compartments works properly on vertical stack even with a different
     * number of regions between them.
     */
    public void testMakeSameSizeVStack_betweenTwoRegionsContainer() {
        // Here the data contains an "unexpected bounds" for the "az" regions container, the target (height is equals to
        // the real height and not to -1). But it could exist in old model. This is why the parameter is adapted.
        testMakeSameSizeVStack(FIRST_REGION_CONTAINER_NAME, REGION_V_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_VSTACK, SECOND_REGION_CONTAINER_NAME, REGION_V_AZ_BOUNDS_GMF,
                REGION_AZ_BOUNDS_DRAW2D_VSTACK);
    }

    /**
     * This test makes sure the "make same size" with a vertical stack compartment as source and a standard container as
     * target works properly.
     */
    public void testMakeSameSizeVStack_betweenARegionsContainerAndAStandardContainer() {
        testMakeSameSizeVStack(FIRST_REGION_CONTAINER_NAME, REGION_V_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_VSTACK, FREE_FORM_CONTAINER_NAME, FREE_FORM_CONTAINER_V_BOUNDS,
                FREE_FORM_CONTAINER_V_BOUNDS);
    }

    /**
     * This test makes sure the "make same size" with standard container as source and a vertical stack compartment as
     * target works properly.
     */
    public void testMakeSameSizeVStack_betweenAStandardContainerAndARegionsContainer() {
        testMakeSameSizeVStack(FREE_FORM_CONTAINER_NAME, FREE_FORM_CONTAINER_V_BOUNDS, FREE_FORM_CONTAINER_V_BOUNDS, SECOND_REGION_CONTAINER_NAME, REGION_V_AZ_BOUNDS_GMF,
                REGION_AZ_BOUNDS_DRAW2D_VSTACK);
    }

    /**
     * This test makes sure the "make same size" on compartments works properly on horizontal stack, with between two
     * regions container, even with a different number of regions between them.
     */
    public void testMakeSameSizeHStack_betweenTwoRegionsContainer() {
        testMakeSameSizeHStack(FIRST_REGION_CONTAINER_NAME, REGION_H_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_HSTACK, SECOND_REGION_CONTAINER_NAME, REGION_H_AZ_BOUNDS_GMF,
                REGION_AZ_BOUNDS_DRAW2D_HSTACK);
    }

    /**
     * This test makes sure the "make same size" with an horizontal stack compartment as source and a standard container
     * as target works properly.
     */
    public void testMakeSameSizeHStack_betweenARegionsContainerAndAStandardContainer() {
        testMakeSameSizeHStack(FIRST_REGION_CONTAINER_NAME, REGION_H_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_HSTACK, FREE_FORM_CONTAINER_NAME, FREE_FORM_CONTAINER_H_BOUNDS,
                FREE_FORM_CONTAINER_H_BOUNDS);
    }

    /**
     * This test makes sure the "make same size" with a standard container as source and an horizontal stack compartment
     * as target works properly.
     */
    public void testMakeSameSizeHStack_betweenAStandardContainerAndARegionsContainer() {
        testMakeSameSizeHStack(FREE_FORM_CONTAINER_NAME, FREE_FORM_CONTAINER_H_BOUNDS, FREE_FORM_CONTAINER_H_BOUNDS, SECOND_REGION_CONTAINER_NAME, REGION_H_AZ_BOUNDS_GMF,
                REGION_AZ_BOUNDS_DRAW2D_HSTACK);
    }

    /**
     * Execute a makeSameSizeTest on HStackDiag.
     * 
     * @param sourceOfMakeSameSizeName
     *            The edit part name which is used as source of the make same size action.
     * @param expectedSourceGmfBounds
     *            The initial expected GMF bounds of the source.
     * @param expectedSourceDraw2dBounds
     *            The initial expected Draw2D bounds of the source.
     * @param targetOfMakeSameSizeName
     *            The edit part name which is used as target of the make same size action.
     * @param expectedTargetGmfBounds
     *            The initial expected GMF bounds of the target.
     * @param expectedTargetDraw2dBounds
     *            The initial expected Draw2D bounds of the target.
     */
    private void testMakeSameSizeHStack(String sourceOfMakeSameSizeName, Rectangle expectedSourceGmfBounds, Rectangle expectedSourceDraw2dBounds, String targetOfMakeSameSizeName,
            Rectangle expectedTargetGmfBounds, Rectangle expectedTargetDraw2dBounds) {
        testMakeSameSize(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, sourceOfMakeSameSizeName, expectedSourceGmfBounds, expectedSourceDraw2dBounds,
                targetOfMakeSameSizeName, expectedTargetGmfBounds, expectedTargetDraw2dBounds);
    }

    /**
     * Execute a makeSameSizeTest on VStackDiag.
     * 
     * @param sourceOfMakeSameSizeName
     *            The edit part name which is used as source of the make same size action.
     * @param expectedSourceGmfBounds
     *            The initial expected GMF bounds of the source.
     * @param expectedSourceDraw2dBounds
     *            The initial expected Draw2D bounds of the source.
     * @param targetOfMakeSameSizeName
     *            The edit part name which is used as target of the make same size action.
     * @param expectedTargetGmfBounds
     *            The initial expected GMF bounds of the target.
     * @param expectedTargetDraw2dBounds
     *            The initial expected Draw2D bounds of the target.
     */
    private void testMakeSameSizeVStack(String sourceOfMakeSameSizeName, Rectangle expectedSourceGmfBounds, Rectangle expectedSourceDraw2dBounds, String targetOfMakeSameSizeName,
            Rectangle expectedTargetGmfBounds, Rectangle expectedTargetDraw2dBounds) {
        testMakeSameSize(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, sourceOfMakeSameSizeName, expectedSourceGmfBounds, expectedSourceDraw2dBounds,
                targetOfMakeSameSizeName, expectedTargetGmfBounds, expectedTargetDraw2dBounds);
    }

    /**
     * Execute a makeSameSize test.
     * 
     * @param representationDescriptionName
     *            The name of the representation description to open.
     * @param representationInstanceName
     *            The name of the representation to open.
     * @param sourceOfMakeSameSizeName
     *            The edit part name which is used as source of the make same size action.
     * @param expectedSourceGmfBounds
     *            The initial expected GMF bounds of the source.
     * @param expectedSourceDraw2dBounds
     *            The initial expected Draw2D bounds of the source.
     * @param targetOfMakeSameSizeName
     *            The edit part name which is used as target of the make same size action.
     * @param expectedTargetGmfBounds
     *            The initial expected GMF bounds of the target.
     * @param expectedTargetDraw2dBounds
     *            The initial expected Draw2D bounds of the target.
     */
    private void testMakeSameSize(String representationDescriptionName, String representationInstanceName, String sourceOfMakeSameSizeName, Rectangle expectedSourceGmfBounds,
            Rectangle expectedSourceDraw2dBounds, String targetOfMakeSameSizeName, Rectangle expectedTargetGmfBounds, Rectangle expectedTargetDraw2dBounds) {
        openRepresentation(representationDescriptionName, representationInstanceName);

        assertEquals(SESSION_SHOULD_NOT_BE_DIRTY_MSG, SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check the source initial bounds
        checkBounds(sourceOfMakeSameSizeName, expectedSourceGmfBounds, expectedSourceDraw2dBounds, false, 0, 0);
        // Check the target initial bounds
        checkBounds(targetOfMakeSameSizeName, expectedTargetGmfBounds, expectedTargetDraw2dBounds, false, 0, 0);

        SWTBotGefEditPart part = editor.getEditPart(targetOfMakeSameSizeName, AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart part2 = editor.getEditPart(sourceOfMakeSameSizeName, AbstractDiagramContainerEditPart.class);

        Collection<SWTBotGefEditPart> parts = new ArrayList<>();
        parts.add(part);
        parts.add(part2);
        editor.select(parts);

        // We perform the make same size. The last selected part (sourceOfMakeSameSizeName) is the reference to apply
        // the size.
        SWTBotToolbarButton button = bot.toolbarButtonWithTooltip(MAKE_SAME_SIZE_ACTION_TOOLTIP);
        button.click();

        // Check that the size of the target is the same than the source
        bot.waitUntil(new CheckBoundsCondition((IGraphicalEditPart) part.part(), expectedSourceDraw2dBounds.getCopy().setLocation(expectedTargetDraw2dBounds.getLocation())));

        DDiagramElement ddiagramElement = ((AbstractDiagramContainerEditPart) part.part()).resolveDiagramElement();
        if (ddiagramElement instanceof DNodeContainer) {
            DNodeContainer ddec = (DNodeContainer) ddiagramElement;
            DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery(ddec);
            if (query.isRegionContainer()) {
                var children = ((AbstractDNodeContainerCompartmentEditPart) ((AbstractDiagramContainerEditPart) part.part()).getChildren().get(1)).getChildren();
                Rectangle regionsContainerBounds = ((AbstractDiagramContainerEditPart) part.part()).getFigure().getBounds();
                Rectangle lastRegionBounds = ((GraphicalEditPart) children.get(children.size() - 1)).getFigure().getBounds().getCopy();
                ((GraphicalEditPart) children.get(children.size() - 1)).getFigure().translateToAbsolute(lastRegionBounds);
                int approximativeBorderWidth = 6;
                if (query.isHorizontaltackContainer()) {
                    // Check that the height of a region is the same of the regions container (with the delta of the
                    // title)
                    int approximativeTitleHeight = 36;
                    assertEquals("The height of the region should fit into the height of the regions container", regionsContainerBounds.height() - approximativeTitleHeight, lastRegionBounds.height(), //$NON-NLS-1$
                            2);
                    // Check that there is no white space (last region right coordinate is the same than right
                    // coordinate of the regions container (with the delta of the border)
                    assertEquals("The right of the last region should be aligned to the right of the regions container.", regionsContainerBounds.right() - approximativeBorderWidth, //$NON-NLS-1$
                            lastRegionBounds.right(), 2);
                } else if (query.isVerticalStackContainer()) {
                    // Check that the width of a region is the same of the regions container (with the delta of the
                    // borders)
                    assertEquals("The width of the region should fit into the width of the regions container", regionsContainerBounds.width() - (approximativeBorderWidth * 2), //$NON-NLS-1$
                            lastRegionBounds.width(),
                            2);
                    // Check that there is no white space (last region bottom coordinate is the same than bottom
                    // coordinate of the regions container (with the delta of the border)
                    assertEquals("The bottom of the last region should be aligned to the bottom of the regions container.", regionsContainerBounds.bottom() - approximativeBorderWidth, //$NON-NLS-1$
                            lastRegionBounds.bottom(), 2);

                }
            }
        }
    }

    /**
     * Tests that auto-size works as expected on horizontal region container.
     */
    public void testAutoSizeHStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals(SESSION_SHOULD_NOT_BE_DIRTY_MSG, SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check the regions initial bounds
        checkBounds(FIRST_REGION_CONTAINER_NAME, REGION_H_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_HSTACK, false, 0, 0);

        final SWTBotGefEditPart part = editor.getEditPart(FIRST_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part);
        SWTBotToolbarButton button = bot.toolbarButtonWithTooltip(AUTO_SIZE_ACTION_TOOLTIP);
        button.click();

        // Check the regions after auto-size
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                Rectangle newBounds = ((DNodeContainerEditPart) part.part()).getFigure().getBounds();
                return newBounds.preciseWidth() < REGION_P1_BOUNDS_DRAW2D_HSTACK.width;
            }

            @Override
            public String getFailureMessage() {
                return AUTO_SIZE_WRONG_WIDTH_ERROR_MSG;
            }
        });

        undo();

        // Test applying auto-size on a region
        final SWTBotGefEditPart part2 = editor.getEditPart(CENTER_CLASS_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part2);
        button = bot.toolbarButtonWithTooltip(AUTO_SIZE_ACTION_TOOLTIP);
        button.click();

        // Check the regions after auto-size
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                Rectangle newBounds = ((DNodeContainerEditPart) part.part()).getFigure().getBounds();
                return newBounds.preciseWidth() < REGION_P1_BOUNDS_DRAW2D_HSTACK.width;
            }

            @Override
            public String getFailureMessage() {
                return AUTO_SIZE_WRONG_WIDTH_ERROR_MSG;
            }
        });
    }

    /**
     * Tests that auto-size works as expected on vertical region container.
     */
    public void testAutoSizeVStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals(SESSION_SHOULD_NOT_BE_DIRTY_MSG, SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check the regions initial bounds
        checkBounds(SECOND_REGION_CONTAINER_NAME, REGION_V_AZ_BOUNDS_GMF, REGION_AZ_BOUNDS_DRAW2D_VSTACK, false, 0, 0);

        final SWTBotGefEditPart part = editor.getEditPart(SECOND_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part);
        SWTBotToolbarButton button = bot.toolbarButtonWithTooltip(AUTO_SIZE_ACTION_TOOLTIP);
        button.click();

        // Check the regions after auto-size
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                Rectangle newBounds = ((DNodeContainerEditPart) part.part()).getFigure().getBounds();
                return newBounds.preciseWidth() < REGION_AZ_BOUNDS_DRAW2D_VSTACK.width;
            }

            @Override
            public String getFailureMessage() {
                return AUTO_SIZE_WRONG_WIDTH_ERROR_MSG;
            }
        });

        undo();

        // Test applying auto-size on a region
        final SWTBotGefEditPart part2 = editor.getEditPart(SECOND_REGION_CONTAINER_FIRST_PKG_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part2);
        button = bot.toolbarButtonWithTooltip(AUTO_SIZE_ACTION_TOOLTIP);
        button.click();

        // Check the regions after auto-size
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                Rectangle newBounds = ((DNodeContainerEditPart) part.part()).getFigure().getBounds();
                return newBounds.preciseWidth() < REGION_AZ_BOUNDS_DRAW2D_VSTACK.width;
            }

            @Override
            public String getFailureMessage() {
                return AUTO_SIZE_WRONG_WIDTH_ERROR_MSG;
            }
        });
    }

}
