/*******************************************************************************
 * Copyright (c) 2009, 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.item;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;

/**
 * Represents viewpoint folder item in session view.
 * 
 * @author mchauvin
 */
public class ViewpointsFolderItemImpl extends AbstractFolderItem implements org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointsFolderItem {
    private static final String FOLDER_LABEL = "Representations per category";

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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator#getText()
     */
    public String getText() {
        return FOLDER_LABEL;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getChildren()
     */
    public Collection<?> getChildren() {
        final List<ViewpointItemImpl> all = Lists.newArrayList();
        for (final Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
            all.add(new ViewpointItemImpl(session, viewpoint, this));
        }
        Collections.sort(all);
        return all;
    }
}
