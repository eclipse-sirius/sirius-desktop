/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
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
