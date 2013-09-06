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

import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.description.Sirius;

/**
 * Represents viewpoint folder item in session view.
 * 
 * @author mchauvin
 */
public class SiriussFolderItemImpl extends AbstractFolderItem implements org.eclipse.sirius.ui.tools.api.views.common.item.SiriussFolderItem {
    private static final String FOLDER_LABEL = "Representations per category";

    /**
     * Constructor.
     * 
     * @param session
     *            Session
     * @param parent
     *            Parent tree item.
     */
    public SiriussFolderItemImpl(final Session session, final Object parent) {
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
        final List<SiriusItemImpl> all = Lists.newArrayList();
        for (final Sirius viewpoint : session.getSelectedSiriuss(false)) {
            all.add(new SiriusItemImpl(session, viewpoint, this));
        }
        Collections.sort(all);
        return all;
    }
}
