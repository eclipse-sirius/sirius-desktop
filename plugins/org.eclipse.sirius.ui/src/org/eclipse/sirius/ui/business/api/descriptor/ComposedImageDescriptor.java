/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ExtendedImageRegistry.java,v 1.7 2010/02/04 18:59:30 emerks Exp $
 */

package org.eclipse.sirius.ui.business.api.descriptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public class ComposedImageDescriptor extends CompositeImageDescriptor {
    /**
     * ComposedImage.
     */
    protected ComposedImage composedImage;

    /**
     * Image data.
     */
    protected List<ImageData> imageDatas;

    /**
     * Constructor.
     * 
     * @param composedImage
     *            ComposedImage
     */
    public ComposedImageDescriptor(ComposedImage composedImage) {
        this.composedImage = composedImage;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.resource.CompositeImageDescriptor#drawCompositeImage(int,
     *      int)
     */
    public void drawCompositeImage(int width, int height) {
        ComposedImage.Size size = new ComposedImage.Size();
        size.width = width;
        size.height = height;
        Iterator<ImageData> images = imageDatas.iterator();
        for (ComposedImage.Point point : composedImage.getDrawPoints(size)) {
            drawImage(images.next(), point.x, point.y);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.resource.CompositeImageDescriptor#getSize()
     */
    public Point getSize() {
        List<Object> images = composedImage.getImages();
        imageDatas = new ArrayList<ImageData>(images.size());
        List<ComposedImage.Size> sizes = new ArrayList<ComposedImage.Size>(images.size());
        for (Object object : images) {
            Image image = ExtendedImageRegistry.getInstance().getImage(object);
            ImageData imageData = image.getImageData();
            imageDatas.add(imageData);

            ComposedImage.Size size = new ComposedImage.Size();
            size.width = imageData.width;
            size.height = imageData.height;
            sizes.add(size);
        }

        ComposedImage.Size result = composedImage.getSize(sizes);
        return new Point(result.width, result.height);
    }
}
