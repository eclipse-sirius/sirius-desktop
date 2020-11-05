/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This class represents the tree node associated to a viewpoint which contains only invalid representations.
 * 
 * @author lfasani
 */
public class ViewpointInvalidItemImpl extends ViewpointItemImpl {

    private Map<RepresentationDescription, List<DRepresentationDescriptor>> descriptionToDescriptors;

    /**
     * Constructor.
     * 
     * @param session
     *            Session
     * @param viewpoint
     *            Sirius
     * @param parent
     *            Parent tree item
     * @param descriptionToDescriptors
     *            the representation descriptors to display
     * @see #ViewpointItem(Session, Viewpoint, Object)
     */
    public ViewpointInvalidItemImpl(final Session session, final Viewpoint viewpoint, final Object parent, Map<RepresentationDescription, List<DRepresentationDescriptor>> descriptionToDescriptors) {
        super(session, viewpoint, null, parent);
        this.descriptionToDescriptors = descriptionToDescriptors;
    }

    @Override
    public Collection<?> getChildren() {
        final List<RepresentationDescriptionItemImpl> all = new ArrayList<RepresentationDescriptionItemImpl>();
        if (isSafeViewpoint()) {
            Set<RepresentationDescription> descriptions = descriptionToDescriptors.keySet();
            for (final RepresentationDescription representationDescription : new ViewpointQuery(getViewpoint()).getAllRepresentationDescriptions()) {
                if (descriptions.contains(representationDescription)) {
                    all.add(new RepresentationDescriptionInvalidItemImpl(getSession().get(), representationDescription, this, descriptionToDescriptors.get(representationDescription)));
                }
            }
            Collections.sort(all);
        }
        return all;
    }
}
