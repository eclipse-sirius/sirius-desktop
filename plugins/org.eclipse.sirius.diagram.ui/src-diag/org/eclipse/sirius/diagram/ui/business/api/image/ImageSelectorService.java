/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.api.image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.diagram.ui.business.internal.image.ImageSelectorDescriptor;
import org.eclipse.sirius.diagram.ui.business.internal.image.ImageSelectorDescriptorRegistry;

/**
 * Service class to get the most priority {@link ImageSelector}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ImageSelectorService {

    /**
     * Singleton of {@link ImageSelectorService}.
     */
    public static final ImageSelectorService INSTANCE = new ImageSelectorService();

    /**
     * Get a default {@link ImageSelector} to select. Compute the first contribution which is not overridden by another
     * extension
     * 
     * @return a default ImageSelector
     */
    public ImageSelector getImageSelector() {
        ImageSelector result = null;
        List<ImageSelectorDescriptor> imageSelectorDescriptors = ImageSelectorDescriptorRegistry.getRegisteredExtensions();
        if (!imageSelectorDescriptors.isEmpty()) {
            ImageSelectorDescriptor imageSelectorDescriptor = getFirstMostOverrider(imageSelectorDescriptors);
            if (imageSelectorDescriptor != null) {
                result = imageSelectorDescriptor.getImageSelector();
            }
        }
        if (result == null) {
            result = new WorkspaceImageSelector();
        }
        return result;
    }

    /**
     * Get the first {@link ImageSelectorDescriptor} of extension list or the the most overriding extension.
     * 
     * @param imageSelectorDescriptors
     * 
     * @return
     */
    private ImageSelectorDescriptor getFirstMostOverrider(List<ImageSelectorDescriptor> imageSelectorDescriptors) {
        List<String> overriddenImageSelectorIDs = new ArrayList<String>();
        ImageSelectorDescriptor firstMostOverridingImageSelectorDescriptor = null;
        Iterator<ImageSelectorDescriptor> descriptorsIterator = imageSelectorDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Recovers all overridden ImageSelectorDescriptor
            firstMostOverridingImageSelectorDescriptor = descriptorsIterator.next();
            String overrideValue = firstMostOverridingImageSelectorDescriptor.getOverrideValue();
            if (overrideValue != null) {
                overriddenImageSelectorIDs.add(overrideValue);
            }
        }
        descriptorsIterator = imageSelectorDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Find the first ImageSelectorDescriptor that is not
            // overridden
            firstMostOverridingImageSelectorDescriptor = descriptorsIterator.next();
            if (!overriddenImageSelectorIDs.contains(firstMostOverridingImageSelectorDescriptor.getId())) {
                return firstMostOverridingImageSelectorDescriptor;
            }
        }

        return firstMostOverridingImageSelectorDescriptor;
    }
}
