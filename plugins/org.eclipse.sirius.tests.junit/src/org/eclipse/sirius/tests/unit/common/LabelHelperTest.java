/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import static org.junit.Assert.assertEquals;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.LabelHelper2;
import org.junit.Test;

/**
 * Test to reveal asymmetrically methods of
 * {@link org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper}
 * problem. This method is not added to a suite (and it is normal). For oblique
 * edge the label is not always stable. This is caused by this problem and this
 * test is a base to fix it.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class LabelHelperTest {

    /**
     * For oblique edges, the LabelHelper has two asymmetrically methods
     * offsetFromRelativeCoordinate and relativeCoordinateFromOffset. This
     * method reveals this problem by simulated a successive calls to these
     * methods. The {@link LabelHelper2} is used instead of {@link LabelHelper2}
     * . The methods are the same but avoid to need a IFigure as entry point.
     */
    @Test
    public void asymmetricallyMethodsInLabelHelper2() {
        Point originalRelativeCoordinate = new Point(657, 159);
        System.out.println("The original label center is " + originalRelativeCoordinate);
        PointList points = new PointList();
        points.addPoint(new Point(735, 142));
        points.addPoint(new Point(581, 168));
        points.addPoint(new Point(405, 350));
        points.addPoint(new Point(228, 188));
        Point ref = new Point(639, 158);
        // We can not use directly
        // LabelHelper.offsetFromRelativeCoordinate(label, bounds, ref) for this
        // test because we have not Figure. But this is equivalent to:
        Point offset = LabelHelper2.offsetFromRelativeCoordinate(originalRelativeCoordinate, points, ref);
        System.out.println("Call to LabelHelper2.offsetFromRelativeCoordinate gives the offset: " + offset);
        // We can not use
        // directlyLabelHelper.relativeCoordinateFromOffset(label, ref,
        // offset) for this test because we have not Figure. But this is
        // equivalent to:
        Point retreiveRelativeCoordinate = LabelHelper2.relativeCenterCoordinateFromOffset(points, ref, offset);
        System.out.println("Call to LabelHelper2.relativeCenterCoordinateFromOffset using the above offset gives the new label center: " + retreiveRelativeCoordinate);
        Point offset2 = LabelHelper2.offsetFromRelativeCoordinate(retreiveRelativeCoordinate, points, ref);
        System.out.println("Call again to LabelHelper.offsetFromRelativeCoordinate with the new label center gives the offset: " + offset2);
        assertEquals("The original point after successive call to offsetFromRelativeCoordinate and relativeCenterCoordinateFromOffset should be the same.", originalRelativeCoordinate,
                retreiveRelativeCoordinate);
    }
}
