/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
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

/**
 * Test the equality of two {@link Image}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class ImageEquality {

    private ImageEquality() {
    }

    /**
     * Test the equality of two {@link Image} according to their
     * {@link Image#getImageData()#data}.
     * 
     * @param image1
     *            first {@link Image}
     * @param image2
     *            second {@link Image}
     * @return true if <code>image1</code> is equal to <code>image2</code>
     */
    public static boolean areEqualImages(Image image1, Image image2) {
        byte[] expectedData = image1.getImageData().data;
        byte[] currentData = image2.getImageData().data;
        boolean areSameImages = expectedData.length == currentData.length;
        if (areSameImages) {
            for (int i = 0; i < expectedData.length && areSameImages; i++) {
                areSameImages = areSameImages && expectedData[i] == currentData[i];
            }
        }
        return areSameImages;
    }

}
