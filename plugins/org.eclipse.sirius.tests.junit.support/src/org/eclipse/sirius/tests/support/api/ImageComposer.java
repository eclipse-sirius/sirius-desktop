/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.support.api;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.ComposedImage.Point;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * Compose different images in a single one.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ImageComposer {

    /**
     * Get the Image provided by the specified {@link AdapterFactory} for the
     * specified modelElement.
     * 
     * @param adapterFactory
     *            the specified {@link AdapterFactory}
     * @param modelElement
     *            the specified modelElement
     * @return the {@link Image} provided by the specified
     *         {@link AdapterFactory}
     */
    public Image getImageOfEditPlugin(AdapterFactory adapterFactory, EObject modelElement) {
        Image imageOfEditPlugin = null;
        AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
        imageOfEditPlugin = adapterFactoryLabelProvider.getImage(modelElement);
        return imageOfEditPlugin;
    }

    /**
     * Get image for specified key, and/or composition.<br>
     * If no image exists for specified key, a new one is created from specified
     * composition.
     * 
     * @param images
     *            images involved in the composed image.
     * @param positions
     *            positions of images involved in the composed image.
     * @return a composed image, should be not <code>null</code>.
     */
    protected Image getComposedImage(List<Object> images, final List<Point> positions) {
        ComposedImage result = new ComposedImage(images) {
            @Override
            public List<Point> getDrawPoints(Size size) {
                return positions;
            }
        };
        return ExtendedImageRegistry.getInstance().getImage(result);
    }

}
