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

import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.graphics.Color;

/**
 * This label provider enhanced {@link SiriusRepresentationLabelProvider} by graying out viewpoint items if they are not
 * loaded in the session associated in the {@link ViewpointItemImpl}. It also add a disabled suffix to it.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 */
public class SiriusRepresentationWithInactiveStatusLabelProvider extends SiriusRepresentationLabelProvider {

    @Override
    public Color getForeground(Object element) {
        // we show a gray out the item if it is a viewpoint item not enabled in
        // the session of the current opened editor.
        Color result = null;
        if (element instanceof ViewpointItemImpl) {

            ViewpointItemImpl viewpointItem = (ViewpointItemImpl) element;

            if (!viewpointItem.isViewpointEnabledInSession()) {
                result = VisualBindingManager.getDefault().getColorFromName("gray"); //$NON-NLS-1$
            }
        }
        if (result == null) {
            result = super.getForeground(element);
        }
        return result;
    }

    @Override
    public String getText(Object element) {
        // We add a disabled label to the viewpoint item label if it is not
        // enabled in the session of the current opened editor.
        String text = super.getText(element);
        if (element instanceof ViewpointItemImpl) {
            ViewpointItemImpl viewpointItem = (ViewpointItemImpl) element;

            if (!viewpointItem.isViewpointEnabledInSession()) {
                text += " (" + Messages.GraphicalRepresentationHandler_disabledViewpoint_label + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        return text;
    }
}
