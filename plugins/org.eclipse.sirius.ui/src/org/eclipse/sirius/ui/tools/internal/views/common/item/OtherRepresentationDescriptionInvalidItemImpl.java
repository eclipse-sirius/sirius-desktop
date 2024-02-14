/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * This class represents the tree node containing invalid representations that have no representation description.
 * 
 * @author lfasani
 *
 */
public class OtherRepresentationDescriptionInvalidItemImpl extends RepresentationDescriptionInvalidItemImpl implements ItemDecorator {

    /**
     * Create a new {@link OtherRepresentationDescriptionInvalidItemImpl}.
     * 
     * @param session
     *            the current session.
     * @param representationDescription
     *            the representation description.
     * @param parent
     *            Parent tree item.
     * @param repDescriptors
     *            the representation descriptors to display
     */
    public OtherRepresentationDescriptionInvalidItemImpl(final Session session, final RepresentationDescription representationDescription, final Object parent,
            List<DRepresentationDescriptor> repDescriptors) {
        super(session, representationDescription, parent, repDescriptors);
    }

    @Override
    public String getText() {
        return Messages.OtherRepresentationDescriptionInvalidItemImpl; // $NON-NLS-1$
    }

    @Override
    public Image getImage() {
        ImageDescriptor bundledImageDescriptor = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/obj16/diagram-type.gif"); //$NON-NLS-1$
        return SiriusEditPlugin.getPlugin().getImage(bundledImageDescriptor);
    }
}
