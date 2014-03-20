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
 * A abstract {@link ImageSelectorDescriptor} with id and
 * {@link ImageSelectorDescriptor} attribute.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractImageSelectorDescriptor implements ImageSelectorDescriptor {

    /** Id of this contribution. */
    protected String id;

    /** {@link ImageSelector} of this contribution. */
    protected ImageSelector imageSelector;

    /** The override attribute value . */
    protected String overrideValue;

    public String getId() {
        return id;
    }

    public ImageSelector getImageSelector() {
        return imageSelector;
    }

    public String getOverrideValue() {
        return overrideValue;
    }

}
