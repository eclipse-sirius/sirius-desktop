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

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Represents common behaviors for *FolderItem classes.
 * 
 * @author dlecan
 */
public abstract class AbstractFolderItem implements CommonSessionItem, ItemDecorator, InternalCommonItem {

    /**
     * Current session.
     */
    protected Session session;

    /**
     * This item's parent.
     */
    protected Object parent;

    /**
     * Constructor.
     * 
     * @param session
     *            Session
     * @param parent
     *            Parent
     */
    public AbstractFolderItem(final Session session, final Object parent) {
        this.session = session;
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator#getImage()
     */
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        boolean result = false;

        if (this == obj) {
            result = true;
        } else {
            if (obj instanceof AbstractFolderItem) {
                final AbstractFolderItem otherObj = (AbstractFolderItem) obj;
                result = otherObj.getText().equals(getText()) && otherObj.parent.equals(parent);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return getText().hashCode() + parent.hashCode();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getSession()
     */
    public Option<Session> getSession() {
        return Options.newSome(session);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.views.common.item.CommonSessionItem#getParent()
     */
    public Object getParent() {
        return parent;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public void setParent(Object newParent) {
        this.parent = newParent;
    }
}
