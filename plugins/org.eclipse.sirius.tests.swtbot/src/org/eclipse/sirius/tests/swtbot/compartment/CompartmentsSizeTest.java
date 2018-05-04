/*******************************************************************************
 * Copyright (c) 2018-2019 Obeo.
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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
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

    private static final String ERROR_MSG = "with the auto-size, the new region width should be smaller than the previous one.";

    private static final Rectangle REGION_P1_BOUNDS_GMF = new Rectangle(64, 16, -1, -1);

    private static final Rectangle REGION_P1_BOUNDS_DRAW2D_VSTACK = new Rectangle(64, 16, 141, 414);

    private static final Rectangle REGION_AZ_BOUNDS_GMF = new Rectangle(338, 66, -1, -1);

    private static final Rectangle REGION_AZ_BOUNDS_DRAW2D_VSTACK = new Rectangle(338, 66, 166, 198);

    private static final Rectangle REGION_AZ_BOUNDS_DRAW2D_VSTACK_AUTO = new Rectangle(338, 66, 59, 198);

    private static final Rectangle REGION_H_P1_BOUNDS_GMF = new Rectangle(0, 0, -1, -1);

    private static final Rectangle REGION_P1_BOUNDS_DRAW2D_HSTACK = new Rectangle(0, 0, 831, 247);

    private static final Rectangle REGION_P1_BOUNDS_DRAW2D_HSTACK_AUTO = new Rectangle(0, 0, 517, 182);

    private static final Rectangle REGION_H_AZ_BOUNDS_GMF = new Rectangle(940, 125, -1, -1);

    private static final Rectangle REGION_AZ_BOUNDS_DRAW2D_HSTACK = new Rectangle(940, 125, 233, 258);

    private String oldFont;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        oldFont = changeDefaultFontName("Comic Sans MS");
    }

    @Override
    protected void tearDown() throws Exception {
        if (oldFont != null) {
            changeDefaultFontName(oldFont);
        }
        super.tearDown();
    }

    /**
     * This test makes sure the make same size on compartments works properly on vertical stack even with a different
     * number of regions between them.
     */
    public void testMakeSameSizeVStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check the regions initial bounds
        checkBounds(FIRST_REGION_CONTAINER_NAME, REGION_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_VSTACK, false, 0, 0);
        checkBounds(SECOND_REGION_CONTAINER_NAME, REGION_AZ_BOUNDS_GMF, REGION_AZ_BOUNDS_DRAW2D_VSTACK, false, 0, 0);

        SWTBotGefEditPart part = editor.getEditPart(SECOND_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart part2 = editor.getEditPart(FIRST_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);

        Collection<SWTBotGefEditPart> parts = new ArrayList<>();
        parts.add(part);
        parts.add(part2);
        editor.select(parts);

        // We perform the make same size. The last selected part (FIRST_REGION_CONTAINER_NAME) is the reference to apply
        // the size.
        SWTBotToolbarButton button = bot.toolbarButtonWithTooltip("Make height and width same size");
        button.click();

        bot.waitUntil(new CheckBoundsCondition((IGraphicalEditPart) part.part(), REGION_P1_BOUNDS_DRAW2D_VSTACK.getCopy().setLocation(REGION_AZ_BOUNDS_DRAW2D_VSTACK.getLocation())));

    }

    /**
     * This test makes sure the "make same size" on compartments works properly on horizontal stack even with a
     * different number of regions between them.
     */
    public void testMakeSameSizeHStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check the regions initial bounds
        checkBounds(FIRST_REGION_CONTAINER_NAME, REGION_H_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_HSTACK, false, 0, 0);
        checkBounds(SECOND_REGION_CONTAINER_NAME, REGION_H_AZ_BOUNDS_GMF, REGION_AZ_BOUNDS_DRAW2D_HSTACK, false, 0, 0);

        SWTBotGefEditPart part = editor.getEditPart(SECOND_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart part2 = editor.getEditPart(FIRST_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);

        Collection<SWTBotGefEditPart> parts = new ArrayList<>();
        parts.add(part);
        parts.add(part2);
        editor.select(parts);

        // We perform the make same size. The last selected part (FIRST_REGION_CONTAINER_NAME) is the reference to apply
        // the size.
        SWTBotToolbarButton button = bot.toolbarButtonWithTooltip("Make height and width same size");
        button.click();

        bot.waitUntil(new CheckBoundsCondition((IGraphicalEditPart) part.part(), REGION_P1_BOUNDS_DRAW2D_HSTACK.getCopy().setLocation(REGION_AZ_BOUNDS_DRAW2D_HSTACK.getLocation())));

    }

    /**
     * Tests that auto-size works as expected on horizontal region container.
     */
    public void testAutoSizeHStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check the regions initial bounds
        checkBounds(FIRST_REGION_CONTAINER_NAME, REGION_H_P1_BOUNDS_GMF, REGION_P1_BOUNDS_DRAW2D_HSTACK, false, 0, 0);

        final SWTBotGefEditPart part = editor.getEditPart(FIRST_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part);
        SWTBotToolbarButton button = bot.toolbarButtonWithTooltip("Auto Size");
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
                return ERROR_MSG;
            }
        });

        undo();

        // Test applying auto-size on a region
        final SWTBotGefEditPart part2 = editor.getEditPart(CENTER_CLASS_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part2);
        button = bot.toolbarButtonWithTooltip("Auto Size");
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
                return ERROR_MSG;
            }
        });
    }

    /**
     * Tests that auto-size works as expected on vertical region container.
     */
    public void testAutoSizeVStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);

        assertEquals("Session should not be dirty.", SessionStatus.SYNC, localSession.getOpenedSession().getStatus());

        // Check the regions initial bounds
        checkBounds(SECOND_REGION_CONTAINER_NAME, REGION_AZ_BOUNDS_GMF, REGION_AZ_BOUNDS_DRAW2D_VSTACK, false, 0, 0);

        final SWTBotGefEditPart part = editor.getEditPart(SECOND_REGION_CONTAINER_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part);
        SWTBotToolbarButton button = bot.toolbarButtonWithTooltip("Auto Size");
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
                return ERROR_MSG;
            }
        });

        undo();

        // Test applying auto-size on a region
        final SWTBotGefEditPart part2 = editor.getEditPart(SECOND_REGION_CONTAINER_FIRST_PKG_NAME, AbstractDiagramContainerEditPart.class);
        editor.select(part2);
        button = bot.toolbarButtonWithTooltip("Auto Size");
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
                return ERROR_MSG;
            }
        });
    }

}
