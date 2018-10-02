/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This label provider is used to provides labels, images and foreground for items of the block showing representations
 * and viewpoint on the same viewer.
 * 
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SiriusRepresentationLabelProvider extends SiriusCommonLabelProvider {

    @Override
    public Image getImage(Object element) {
        Image image = null;

        if (element instanceof ViewpointItemImpl) {
            image = ViewpointHelper.getImage(((ViewpointItemImpl) element).getViewpoint());
        } else {
            image = super.getImage(element);
        }

        return image;
    }

}
