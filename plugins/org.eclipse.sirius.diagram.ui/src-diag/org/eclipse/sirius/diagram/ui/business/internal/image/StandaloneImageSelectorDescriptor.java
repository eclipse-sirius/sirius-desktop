/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.image;

import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelector;

/**
 * A {@link ImageSelectorDescriptor} for standalone use.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StandaloneImageSelectorDescriptor extends AbstractImageSelectorDescriptor implements ImageSelectorDescriptor {

    /**
     * Default constructor.
     * 
     * @param id
     *            id of this contribution
     * @param imageSelector
     *            {@link ImageSelector} of this contribution
     * @param overrideValue
     *            override attribute value
     * 
     */
    public StandaloneImageSelectorDescriptor(String id, ImageSelector imageSelector, String overrideValue) {
        super();
        this.id = id;
        this.imageSelector = imageSelector;
        this.overrideValue = overrideValue;
    }
}
