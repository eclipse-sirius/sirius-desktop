/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import org.eclipse.swt.graphics.Image;
import org.junit.Assert;

/**
 * Assert that 2 image are equals.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ImageEqualityAsserter {

    private final Image image1;

    private final Image image2;

    /**
     * Test the equality of two {@link Image} according to their
     * {@link Image#getImageData()#data}.
     * 
     * @param image1
     *            first {@link Image}
     * @param image2
     *            second {@link Image}
     */

    public ImageEqualityAsserter(Image image1, Image image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    /**
     * Test the equality of two {@link Image} according to their
     * {@link Image#getImageData()#data}.
     */
    public void assertImagesEquals() {
        Assert.assertEquals("Both image should have the same bounds", image1.getBounds(), image2.getBounds());
        byte[] expectedData = image1.getImageData().data;
        byte[] currentData = image2.getImageData().data;
        Assert.assertEquals("Both image should have the same data size", expectedData.length, currentData.length);
        for (int i = 0; i < expectedData.length; i++) {
            Assert.assertEquals("Both image should have the same byte at index " + i, expectedData[i], currentData[i]);
        }
    }
}
