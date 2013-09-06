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

import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.views.common.item.AnalysisResourceItem;

/**
 * Represents resource folder item in session view.
 * 
 * @author mchauvin
 */
public class ResourcesFolderItemImpl extends AbstractFolderItem implements org.eclipse.sirius.ui.tools.api.views.common.item.ResourcesFolderItem {

    private static final String FOLDER_LABEL = "Representations per resource";

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
        final Collection<AnalysisResourceItem> all = Lists.newArrayList();
        final Collection<Resource> resources = session.getAllSessionResources();
        for (final Resource resource : resources) {
            all.add(new AnalysisResourceItemImpl(session, resource, this));
        }
        return all;
    }
}
