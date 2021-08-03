/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.provider;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * A image descriptor wrapper with a dimension field.
 *
 * @author mchauvin
 */
public interface ImageWithDimensionDescriptor {

    /**
     * should be used when no resized is asked.
     */
    Dimension NO_RESIZE = new Dimension(0, 0);

    /**
     * Get the wrapped image descriptor
     *
     * @return
     */
    ImageDescriptor getImageDescriptor();

    /**
     * Get the dimension
     *
     * @return dimension
     */
    Dimension getDimension();
}

class ImageWithDimensionDescriptorImpl implements ImageWithDimensionDescriptor {

    private Dimension dimension;

    private ImageDescriptor descriptor;

    public ImageWithDimensionDescriptorImpl(final ImageDescriptor descriptor) {
        this.dimension = ImageWithDimensionDescriptor.NO_RESIZE;
        this.descriptor = descriptor;
    }

    public ImageWithDimensionDescriptorImpl(final ImageDescriptor descriptor, final Dimension dimension) {
        this.dimension = dimension;
        this.descriptor = descriptor;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.provider.ImageWithDimensionDescriptor#getDimension()
     */
    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.diagram.ui.provider.ImageWithDimensionDescriptor#getImageDescriptor()
     */
    @Override
    public ImageDescriptor getImageDescriptor() {
        return this.descriptor;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descriptor == null) ? 0 : descriptor.hashCode());
        result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        ImageWithDimensionDescriptorImpl other = (ImageWithDimensionDescriptorImpl) obj;
        if (descriptor == null) {
            if (other.descriptor != null) {
                return false;
            }
        } else if (!descriptor.equals(other.descriptor)) {
            return false;
        }
        if (dimension == null) {
            if (other.dimension != null) {
                return false;
            }
        } else if (!dimension.equals(other.dimension)) {
            return false;
        }
        return true;
    }
}
