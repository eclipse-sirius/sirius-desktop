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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

/**
 * This class represents the tree node associated to a representation description which contains only invalid
 * representations.
 * 
 * @author lfasani
 *
 */
public class RepresentationDescriptionInvalidItemImpl extends RepresentationDescriptionItemImpl {

    private List<DRepresentationDescriptor> repDescriptors;

    /**
     * Create a new {@link RepresentationDescriptionInvalidItemImpl}.
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
    public RepresentationDescriptionInvalidItemImpl(final Session session, final RepresentationDescription representationDescription, final Object parent,
            List<DRepresentationDescriptor> repDescriptors) {
        super(session, representationDescription, parent, null);
        this.repDescriptors = repDescriptors;
    }

    @Override
    public Collection<?> getChildren() {
        //@formatter:off
        List<RepresentationInvalidItemImpl> repDescriptorsInvalid = repDescriptors.stream()
                .map(repDesc -> new RepresentationInvalidItemImpl(repDesc, this))
                .collect(Collectors.toList());
      //@formatter:on

        Collections.sort(repDescriptorsInvalid, Ordering.natural().onResultOf(new Function<RepresentationInvalidItemImpl, String>() {
            @Override
            public String apply(RepresentationInvalidItemImpl from) {
                return from.getDRepresentationDescriptor().getName();
            }
        }));
        return repDescriptorsInvalid;
    }
}
