/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;

/**
 * Represents viewpoint folder item in session view.
 *
 * @author mchauvin
 */
public class ViewpointsFolderItemImpl extends AbstractFolderItem implements org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointsFolderItem {
    /**
     * Constructor.
     *
     * @param session
     *            Session
     * @param parent
     *            Parent tree item.
     */
    public ViewpointsFolderItemImpl(final Session session, final Object parent) {
        super(session, parent);
    }

    @Override
    public String getText() {
        return Messages.ViewpointsFolderItemImpl_representationsPerCategory_title;
    }

    @Override
    public Collection<?> getChildren() {
        final List<ViewpointItemImpl> all = new ArrayList<>();
        for (final Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
            all.add(new ViewpointItemImpl(session, viewpoint, this));
        }
        Collections.sort(all);
        return all;
    }
}
