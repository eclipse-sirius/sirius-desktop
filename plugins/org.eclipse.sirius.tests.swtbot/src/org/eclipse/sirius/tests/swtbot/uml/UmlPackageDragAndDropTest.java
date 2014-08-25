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
package org.eclipse.sirius.tests.swtbot.uml;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;

/**
 * Test class for drag an drop on diagram.
 * 
 * @author dlecan
 */
public class UmlPackageDragAndDropTest extends AbstractUmlDragAndDropTest {

    private static final String DIAGRAM_NAME_PACKAGE = "DnD Package Diagram";

    private static final String REPRESENTATION_NAME = "Package Diagram";

    private static final String PACKAGE_TO_DND1 = "ContainerPackageToDrop";

    private static final String PACKAGE_TO_DND2 = "CanvasPackageToDrop1";

    private static final String PACKAGE_TO_DND3 = "CanvasPackageToDrop2";

    private static final Point POINT_IN_CONTAINER_TO_DROP = new Point(50, 70);

    private static final Point POINT_IN_CANVAS = new Point(60, 200);

    private static final Point POINT_IN_ROOT_PACKAGE2 = new Point(390, 50);

    private static final Point POINT_IN_CANVAS_PACKAGE_TO_DROP1 = new Point(390, 205);

    private static final Rectangle RECT_DRAG_AROUND = new Rectangle(380, 190, 280, 70);

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationDescriptionName() {
        return REPRESENTATION_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationNameToOpen() {
        return DIAGRAM_NAME_PACKAGE;
    }

    /**
     * Test drag and drop of uml package from another package to canvas.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDnDPackageFromContainerToCanvas() throws Exception {
        final Point mouseDragPosition = POINT_IN_CONTAINER_TO_DROP;
        final Point mouseDropPosition = POINT_IN_CANVAS;
        final String elementToDnDName = PACKAGE_TO_DND1;

        testSimpleDnD(mouseDragPosition, mouseDropPosition, elementToDnDName);
    }

    /**
     * Validates that drag and dropping a node, from container to canvas, with
     * snap to grid as false and scroll, will drop the node at the proper
     * location.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDnDPackageFromContainerToCanvasWithScroll() throws Exception {
        // Reuse the following test to move the container to the right container
        testDndPackageFromContainerToContainer();

        // Save full screen status.
        final boolean fullScreenStatus = UIThreadRunnable.syncExec(new Result<Boolean>() {
            public Boolean run() {
                return PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell().getFullScreen();
            }
        });
        try {
            // Deactivate full screen to have scroll bars on diagram
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

                public void run() {
                    PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell().setFullScreen(false);
                }
            });

            editor.setSnapToGrid(false);

            // Reveal element to have some scroll
            editor.reveal(PACKAGE_TO_DND3);
            editor.reveal(PACKAGE_TO_DND1);

            Rectangle containerPackageToDropBounds = getEditPartBounds(PACKAGE_TO_DND1);
            Rectangle canvasPackageToDrop2Bounds = getEditPartBounds(PACKAGE_TO_DND3);

            // Drop Interface 1 on the right of its container
            Point dropLocation = canvasPackageToDrop2Bounds.getRight().getTranslated(10, 0).getCopy();
            editor.drag(containerPackageToDropBounds.getTopLeft(), dropLocation);

            // Validate drop location
            Assert.assertEquals("The dropped element is not at the expected position", dropLocation, getEditPartBounds(PACKAGE_TO_DND1).getTopLeft());

        } finally {
            // Restore full screen as it was before this test case.
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

                public void run() {
                    PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell().setFullScreen(fullScreenStatus);
                }
            });
        }
    }

    /**
     * Test drag and drop of uml package from canvas to another package.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDnDPackageFromCanvasToContainer() throws Exception {
        final Point mouseDragPosition = POINT_IN_CANVAS_PACKAGE_TO_DROP1;
        final Point mouseDropPosition = POINT_IN_ROOT_PACKAGE2;
        final String elementToDnDName = PACKAGE_TO_DND2;

        testSimpleDnD(mouseDragPosition, mouseDropPosition, elementToDnDName);
    }

    /**
     * Test drag and drop of uml package from package to another package.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDndPackageFromContainerToContainer() throws Exception {
        final Point mouseDragPosition = POINT_IN_CONTAINER_TO_DROP;
        final Point mouseDropPosition = POINT_IN_ROOT_PACKAGE2;
        final String elementToDnDName = PACKAGE_TO_DND1;

        testSimpleDnD(mouseDragPosition, mouseDropPosition, elementToDnDName);
    }

    /**
     * Test drag and drop of uml several uml packages from cancas to another
     * package.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDndMultiplePackagesFromCanvasToContainer() throws Exception {
        final Rectangle packDnD2OriginalBounds = getEditPartBounds(PACKAGE_TO_DND2);
        final Rectangle packDnD3OriginalBounds = getEditPartBounds(PACKAGE_TO_DND3);

        // Select the 2 packages together
        editor.drag(RECT_DRAG_AROUND.getTopLeft().x, RECT_DRAG_AROUND.getTopLeft().y, RECT_DRAG_AROUND.getBottomRight().x, RECT_DRAG_AROUND.getBottomRight().y);

        editor.drag(POINT_IN_CANVAS_PACKAGE_TO_DROP1.x, POINT_IN_CANVAS_PACKAGE_TO_DROP1.y, POINT_IN_ROOT_PACKAGE2.x, POINT_IN_ROOT_PACKAGE2.y);
        final Dimension translation = POINT_IN_ROOT_PACKAGE2.getDifference(POINT_IN_CANVAS_PACKAGE_TO_DROP1);

        final Rectangle packDnD2NewBounds = getEditPartBounds(PACKAGE_TO_DND2);
        final Rectangle packDnD3NewBounds = getEditPartBounds(PACKAGE_TO_DND3);

        checkNewCoordinates(PACKAGE_TO_DND2, packDnD2OriginalBounds, packDnD2NewBounds, translation);

        checkNewCoordinates(PACKAGE_TO_DND3, packDnD3OriginalBounds, packDnD3NewBounds, translation);

        checkConstantGapBetween(PACKAGE_TO_DND2, packDnD2OriginalBounds.getTopLeft(), packDnD2NewBounds.getTopLeft(), PACKAGE_TO_DND3, packDnD3OriginalBounds.getTopLeft(),
                packDnD3NewBounds.getTopLeft());
    }

    private void testSimpleDnD(final Point mouseDragPosition, final Point mouseDropPosition, final String elementNameToDnD) {
        final Rectangle originalBounds = getEditPartBounds(elementNameToDnD);

        // Move ContainerPackageToDrop out of RootPackage1
        // It is not possible to use package label to pick container, because
        // package label click takes title box instead of the whole container.
        editor.drag(mouseDragPosition.x, mouseDragPosition.y, mouseDropPosition.x, mouseDropPosition.y);
        final Dimension translation = mouseDropPosition.getDifference(mouseDragPosition);

        final Rectangle newBounds = getEditPartBounds(elementNameToDnD);

        checkNewCoordinates(elementNameToDnD, originalBounds, newBounds, translation);
    }
}
