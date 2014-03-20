/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.image;

import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelector;


/**
 * A descriptor of contributed {@link ImageSelector}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface ImageSelectorDescriptor {

    /**
     * Id of the imageSelector extension point's tag "id" attribute.
     */
    String IMAGE_SELECTOR_ID_ATTRIBUTE = "id"; //$NON-NLS-1$

    /**
     * Name of the imageSelector extension point's tag "class" attribute.
     */
    String IMAGE_SELECTOR_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Name of the imageSelector extension point's tag "override" attribute.
     */
    String IMAGE_SELECTOR_OVERRIDE_ATTRIBUTE = "override"; //$NON-NLS-1$

    /**
     * The unique identifier of the extension {@link ImageSelector} extension.
     * 
     * @return the unique identifier of the extension {@link ImageSelector}
     *         extension
     */
    String getId();

    /**
     * The concrete implementation (i.e. ImageSelector) of the extension.
     * 
     * @return the concrete implementation (i.e. ImageSelector) of the extension
     */
    ImageSelector getImageSelector();

    /**
     * Get the override attribute value of this contribution.
     * 
     * @return the override attribute value of this contribution
     */
    String getOverrideValue();
}
