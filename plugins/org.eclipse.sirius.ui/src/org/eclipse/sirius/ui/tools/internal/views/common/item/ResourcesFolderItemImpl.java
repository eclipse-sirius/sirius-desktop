/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * Represents resource folder item in session view.
 *
 * @author mchauvin
 */
public class ResourcesFolderItemImpl extends AbstractFolderItem implements org.eclipse.sirius.ui.tools.api.views.common.item.ResourcesFolderItem {

    /**
     * Constructor.
     *
     * @param session
     *            Session
     * @param parent
     *            Parent
     */
    public ResourcesFolderItemImpl(final Session session, final Object parent) {
        super(session, parent);
    }

    @Override
    public String getText() {
        return Messages.ResourcesFolderItemImpl_text;
    }

    @Override
    public Collection<?> getChildren() {
        final Collection<AnalysisResourceItem> all = new ArrayList<>();
        final Collection<Resource> resources = session.getAllSessionResources();
        for (final Resource resource : resources) {
            all.add(new AnalysisResourceItemImpl(session, resource, this));
        }
        return all;
    }
}
