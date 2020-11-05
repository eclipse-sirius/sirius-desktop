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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * This class represents the folder node which contains only invalid representations.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class ViewpointsFolderInvalidItemImpl extends AbstractFolderItem implements org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointsFolderItem {

    private Collection<DRepresentationDescriptor> repDescriptors;

    /**
     * Constructor.
     *
     * @param session
     *            Session
     * @param parent
     *            Parent tree item.
     * @param repDescriptors
     *            the representation descriptors to display
     */
    public ViewpointsFolderInvalidItemImpl(final Session session, final Object parent, Collection<DRepresentationDescriptor> repDescriptors) {
        super(session, parent);
        this.repDescriptors = repDescriptors;
    }

    @Override
    public String getText() {
        return Messages.ViewpointsFolderInvalidItemImpl_invalidRepresentations_title;
    }

    @Override
    public Collection<?> getChildren() {
        final Collection<AnalysisResourceItem> all = new ArrayList<>();

        Map<Resource, List<DRepresentationDescriptor>> resourceToDescriptors = new LinkedHashMap<Resource, List<DRepresentationDescriptor>>();
        for (DRepresentationDescriptor repDesc : repDescriptors) {
            Resource representationResource = repDesc.eResource();
            if (representationResource != null) {
                if (resourceToDescriptors.containsKey(representationResource)) {
                    resourceToDescriptors.get(representationResource).add(repDesc);
                } else {
                    List<DRepresentationDescriptor> repDescs = new ArrayList<DRepresentationDescriptor>();
                    repDescs.add(repDesc);
                    resourceToDescriptors.put(representationResource, repDescs);
                }
            }
        }

        for (final Resource resource : resourceToDescriptors.keySet()) {
            all.add(new AnalysisResourceInvalidItemImpl(session, resource, this, resourceToDescriptors.get(resource)));
        }
        return all;
    }
}
