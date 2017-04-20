/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.text.MessageFormat;

import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider;
import org.eclipse.sirius.viewpoint.provider.Messages;
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
    public String getText(Object element) {
        String text = super.getText(element);
        if (element instanceof RepresentationDescriptionItemImpl) {
            RepresentationDescriptionItemImpl descriptionItem = (RepresentationDescriptionItemImpl) element;
            text += MessageFormat.format(Messages.GraphicalRepresentationHandler_representationNumber_label, descriptionItem.getChildren().size()); // $NON-NLS-1$
        }
        return text;
    }

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
