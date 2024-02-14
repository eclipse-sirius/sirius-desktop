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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * This class represents the tree node associated to a not selected viewpoint which contains only invalid
 * representations.
 * 
 * @author lfasani
 */
public class OtherViewpointInvalidItemImpl extends ViewpointItemImpl {

    private Map<RepresentationDescription, List<DRepresentationDescriptor>> descriptionToDescriptors;

    private List<RepresentationDescription> repDescriptionsInOtherViewpoints;

    private List<DRepresentationDescriptor> repDescriptorsWithoutDescription;
    
    /**
     * Constructor.
     * 
     * @param parent
     *            Parent tree item
     * @param repDescriptionsInOtherViewpoints
     *            list of representation descriptor that are associated to a not loaded viewpoint
     * @param descriptionToDescriptors
     *            the representation descriptors to display
     * @param repDescriptorWithoutDescription
     *            list of representation descriptor that are associated to a not loaded representation description
     * @see #ViewpointItem(Session, Viewpoint, Object)
     */
    public OtherViewpointInvalidItemImpl(Session session, Object parent, List<RepresentationDescription> repDescriptionsInOtherViewpoints,
            Map<RepresentationDescription, List<DRepresentationDescriptor>> descriptionToDescriptors, List<DRepresentationDescriptor> repDescriptorsWithoutDescription) {
        super(session, null, null, parent);
        this.descriptionToDescriptors = descriptionToDescriptors;
        this.repDescriptionsInOtherViewpoints = repDescriptionsInOtherViewpoints;
        this.repDescriptorsWithoutDescription = repDescriptorsWithoutDescription;
    }

    @Override
    public String getText() {
        return Messages.OtherViewpointInvalidItemImpl_label;
    }

    @Override
    public Image getImage() {
        ImageDescriptor bundledImageDescriptor = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/obj16/Sirius.gif"); //$NON-NLS-1$
        return SiriusEditPlugin.getPlugin().getImage(bundledImageDescriptor);
    }

    @Override
    public Collection<?> getChildren() {
        final List<RepresentationDescriptionItemImpl> all = new ArrayList<RepresentationDescriptionItemImpl>();
        for (RepresentationDescription representationDescription : repDescriptionsInOtherViewpoints) {
            all.add(new RepresentationDescriptionInvalidItemImpl(getSession().get(), representationDescription, this, descriptionToDescriptors.get(representationDescription)));
        }

        if (!repDescriptorsWithoutDescription.isEmpty()) {
            all.add(new OtherRepresentationDescriptionInvalidItemImpl(null, null, this, repDescriptorsWithoutDescription));
        }
        return all;
    }
}
