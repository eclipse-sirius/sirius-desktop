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
package org.eclipse.sirius.tests.unit.common.mock;

import static org.junit.Assert.assertEquals;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ImageFigureWithAlpha;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.util.OpaquePixelFinder;
import org.junit.Test;

/**
 * Automated tests for the
 * {@link OpaquePixelFinder#searchFirstOpaquePoint(Point, int, Point, Point, ImageFigureWithAlpha, Rectangle)}
 * method.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class OpaquePixelFinderTest {

    @Test
    public void imageWithOneOpaquePixelInTopLeftCorner() {
        imageWithZeroOrOneOpaquePixel(new Point(0, 0));
    }

    @Test
    public void imageWithOneOpaquePixelInTopRightCorner() {
        imageWithZeroOrOneOpaquePixel(new Point(8, 0));
    }

    @Test
    public void imageWithOneOpaquePixelInBottomLeftCorner() {
        imageWithZeroOrOneOpaquePixel(new Point(0, 8));
    }

    @Test
    public void imageWithOneOpaquePixelInBottomRightCorner() {
        imageWithZeroOrOneOpaquePixel(new Point(8, 8));
    }

    @Test
    public void imageWithOneOpaquePixelInCenterCorner() {
        imageWithZeroOrOneOpaquePixel(new Point(4, 4));
    }

    @Test
    public void imageWithoutOpaquePixel() {
        imageWithZeroOrOneOpaquePixel(null);
    }

    @Test
    public void onePixelImageTransparent() {
        imageWithZeroOrOneOpaquePixel(null, new Dimension(1, 1));
    }

    @Test
    public void onePixelImageOpaque() {
        imageWithZeroOrOneOpaquePixel(new Point(0, 0), new Dimension(1, 1));
    }

    @Test
    public void imageWithTwoOpaquePixelsTopBottomMiddle() {
        Point firstOpaquePoint = new Point(4, 0);
        Point secondOpaquePoint = new Point(4, 8);
        ImageFigureWithAlpha image = getImageWithTwoOpaquePixels(firstOpaquePoint, secondOpaquePoint);

        // Check from left
        validateFirstOpaquePoint(PositionConstants.HORIZONTAL, new Point(0, 0), image, firstOpaquePoint);
        // Check from right
        validateFirstOpaquePoint(PositionConstants.HORIZONTAL, new Point(image.getImageWidth() - 1, 0), image, firstOpaquePoint);
        // Check from top
        validateFirstOpaquePoint(PositionConstants.VERTICAL, new Point(0, 0), image, firstOpaquePoint);
        // Check from bottom
        validateFirstOpaquePoint(PositionConstants.VERTICAL, new Point(0, image.getImageHeight() - 1), image, secondOpaquePoint);
    }

    @Test
    public void imageWithTwoOpaquePixelsLeftRightCenter() {
        Point firstOpaquePoint = new Point(0, 4);
        Point secondOpaquePoint = new Point(8, 4);
        ImageFigureWithAlpha image = getImageWithTwoOpaquePixels(firstOpaquePoint, secondOpaquePoint);

        // Check from left
        validateFirstOpaquePoint(PositionConstants.HORIZONTAL, new Point(0, 0), image, firstOpaquePoint);
        // Check from right
        validateFirstOpaquePoint(PositionConstants.HORIZONTAL, new Point(image.getImageWidth() - 1, 0), image, secondOpaquePoint);
        // Check from top
        validateFirstOpaquePoint(PositionConstants.VERTICAL, new Point(0, 0), image, firstOpaquePoint);
        // Check from bottom
        validateFirstOpaquePoint(PositionConstants.VERTICAL, new Point(0, image.getImageHeight() - 1), image, firstOpaquePoint);
    }

    /**
     * Check that the found opaque pixel is the <code>expectedOpaquePoint</code>
     * . If the <code>expectedOpaquePoint</code> is null (case without opaque
     * point), the initial cursor location should be found.<BR>
     * The image used has a size of 9x9.
     * 
     * @param expectedOpaquePoint
     *            The expected opaque point, can be null if any
     */
    private void imageWithZeroOrOneOpaquePixel(final Point expectedOpaquePoint) {
        imageWithZeroOrOneOpaquePixel(expectedOpaquePoint, new Dimension(9, 9));
    }

    /**
     * Check that the found opaque pixel is the <code>expectedOpaquePoint</code>
     * . If the <code>expectedOpaquePoint</code> is null (case without opaque
     * point), the initial cursor location should be found.<BR>
     * 
     * @param expectedOpaquePoint
     *            The expected opaque point, can be null if any
     * @param size
     *            The image size
     */
    private void imageWithZeroOrOneOpaquePixel(final Point expectedOpaquePoint, final Dimension size) {
        ImageFigureWithAlpha zeroOrOneOpaquePixelImage = new ImageFigureWithAlpha() {
            @Override
            public int getImageWidth() {
                return size.width();
            }

            @Override
            public int getImageHeight() {
                return size.height();
            }

            @Override
            public int getImageAlphaValue(int x, int y) {
                if (expectedOpaquePoint != null && x == expectedOpaquePoint.x && y == expectedOpaquePoint.y) {
                    return 255;
                }
                return 0;
            }
        };
        imageWithZeroOrOneOpaquePixel(zeroOrOneOpaquePixelImage, expectedOpaquePoint);
    }

    /**
     * Check that the found opaque pixel is the <code>expectedOpaquePoint</code>
     * . If the <code>expectedOpaquePoint</code> is null (case without opaque
     * point), the initial cursor location should be found.<BR>
     * 
     * @param image
     *            The image in which to search the first opaque point
     * @param expectedOpaquePoint
     *            The expected opaque point, can be null if any
     */
    private void imageWithZeroOrOneOpaquePixel(ImageFigureWithAlpha image, Point expectedOpaquePoint) {
        // Check from left
        validateFirstOpaquePoint(PositionConstants.HORIZONTAL, new Point(0, 0), image, expectedOpaquePoint);
        // Check from right
        validateFirstOpaquePoint(PositionConstants.HORIZONTAL, new Point(image.getImageWidth() - 1, 0), image, expectedOpaquePoint);
        // Check from top
        validateFirstOpaquePoint(PositionConstants.VERTICAL, new Point(0, 0), image, expectedOpaquePoint);
        // Check from bottom
        validateFirstOpaquePoint(PositionConstants.VERTICAL, new Point(0, image.getImageHeight() - 1), image, expectedOpaquePoint);
    }

    /**
     * Create a new image with these 2 opaque points. The image used has a size
     * of 9x9.
     * 
     * @param firstOpaquePoint
     *            The first opaque point of the image
     * @param secondOpaquePoint
     *            The second opaque point of the image
     */
    private ImageFigureWithAlpha getImageWithTwoOpaquePixels(final Point firstOpaquePoint, final Point secondOpaquePoint) {
        ImageFigureWithAlpha twoOpaquePixelsImage = new ImageFigureWithAlpha() {
            @Override
            public int getImageWidth() {
                return 9;
            }

            @Override
            public int getImageHeight() {
                return 9;
            }

            @Override
            public int getImageAlphaValue(int x, int y) {
                if ((x == firstOpaquePoint.x && y == firstOpaquePoint.y) || (x == secondOpaquePoint.x && y == secondOpaquePoint.y)) {
                    return 255;
                }
                return 0;
            }
        };
        return twoOpaquePixelsImage;
    }

    /**
     * @param orientation
     *            an orientation ({@link PositionConstants#HORIZONTAL} or
     *            {@link PositionConstants#VERTICAL}) the method will consider
     *            to start its search of an anchor position.
     * @param initialCursor
     *            The initial cursor location
     * @param image
     *            The image in which to search the first opaque point
     * @param expectedOpaquePoint
     *            The expected opaque point, can be null if any
     */
    private void validateFirstOpaquePoint(int orientation, Point initialCursor, ImageFigureWithAlpha image, Point expectedOpaquePoint) {
        Point result = new OpaquePixelFinder(0).searchFirstOpaquePoint(orientation, initialCursor, image, new Rectangle(0, 0, image.getImageWidth(), image.getImageHeight()));
        if (expectedOpaquePoint == null) {
            assertEquals("The first opaque point should be the initial point if there is no opaque point.", initialCursor, result);
        } else {
            assertEquals("The first opaque point is not the expected one.", expectedOpaquePoint, result);
        }
    }
}
