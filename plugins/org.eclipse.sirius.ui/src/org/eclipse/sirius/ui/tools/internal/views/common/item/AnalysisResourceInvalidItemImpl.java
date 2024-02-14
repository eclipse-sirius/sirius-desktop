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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * This class represents the tree node associated to a resource which contains only invalid representations.
 * 
 * @author lfasani
 */
public class AnalysisResourceInvalidItemImpl extends AnalysisResourceItemImpl {

    private Collection<DRepresentationDescriptor> repDescriptors;

    /**
     * Constructor.
     * 
     * @param session
     *            session
     * @param resource
     *            resource
     * @param parent
     *            parent
     * @param repDescriptors
     *            the representation descriptors to display
     */
    public AnalysisResourceInvalidItemImpl(final Session session, final Resource resource, final Object parent, Collection<DRepresentationDescriptor> repDescriptors) {
        super(session, resource, parent);
        this.repDescriptors = repDescriptors;
    }

    @Override
    public Collection<?> getChildren() {

        Map<RepresentationDescription, List<DRepresentationDescriptor>> descriptionToDescriptors = new LinkedHashMap<RepresentationDescription, List<DRepresentationDescriptor>>();
        List<DRepresentationDescriptor> repDescriptorsWithoutDescription = new ArrayList<>();
        for (DRepresentationDescriptor repDesc : repDescriptors) {
            RepresentationDescription description = repDesc.getDescription();
            if (description != null) {
                if (descriptionToDescriptors.containsKey(description)) {
                    descriptionToDescriptors.get(description).add(repDesc);
                } else {
                    List<DRepresentationDescriptor> repDescs = new ArrayList<DRepresentationDescriptor>();
                    repDescs.add(repDesc);
                    descriptionToDescriptors.put(description, repDescs);
                }
            } else {
                repDescriptorsWithoutDescription.add(repDesc);
            }
        }

        Set<RepresentationDescription> descriptions = descriptionToDescriptors.keySet();

        //@formatter:off
        List<ViewpointItemImpl> all = getSession().get().getSelectedViewpoints(false).stream()
            .filter(vp -> isVPToBeDisplayed(vp, descriptions))
            .map(vp -> new ViewpointInvalidItemImpl(getSession().get(), vp, this, descriptionToDescriptors))
            .collect(Collectors.toList());
        //@formatter:on
        Collections.sort(all);

        List<RepresentationDescription> repDescriptionsInOtherViewpoints = descriptions.stream().filter(repDescription -> repDescription.eContainer() == null).toList();
        if (!repDescriptionsInOtherViewpoints.isEmpty() || !repDescriptorsWithoutDescription.isEmpty()) {
            all.add(new OtherViewpointInvalidItemImpl(getSession().get(), this, repDescriptionsInOtherViewpoints, descriptionToDescriptors, repDescriptorsWithoutDescription));
        }

        return all;
    }

    /**
     * This method returns true if there is at least one invalid representation which type is among the
     * {@link RepresentationDescription} list of the given {@link Viewpoint}.
     */
    private boolean isVPToBeDisplayed(Viewpoint vp, Set<RepresentationDescription> descriptions) {
        for (RepresentationDescription descriptionInVp : new ViewpointQuery(vp).getAllRepresentationDescriptions()) {
            for (RepresentationDescription representationDescription : descriptions) {
                if (EqualityHelper.areEquals(descriptionInVp, representationDescription)) {
                    return true;
                }
            }
        }
        return false;
    }
}
