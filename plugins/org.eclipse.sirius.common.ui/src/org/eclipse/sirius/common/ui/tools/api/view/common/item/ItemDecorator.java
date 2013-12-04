/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.view.common.item;

import org.eclipse.swt.graphics.Image;

/**
 * A interface to decorate objects.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface ItemDecorator {
    /**
     * Get a text description.
     * 
     * @return a text description
     */
    String getText();

    /**
     * Get an image description.
     * 
     * @return an image description
     */
    Image getImage();
}
