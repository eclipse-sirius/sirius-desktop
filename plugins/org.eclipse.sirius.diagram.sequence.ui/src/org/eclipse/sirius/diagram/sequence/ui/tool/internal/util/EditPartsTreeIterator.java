/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.util;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

import com.google.common.collect.Iterables;

/**
 * A tree iterator to iterate on hierarchies of GMF edit parts.
 * 
 * @author pcdavid
 */
class EditPartsTreeIterator extends AbstractTreeIterator<IGraphicalEditPart> {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 3234398995856675133L;

    /**
     * Creates a new tree iterator on the specified edit part and all its
     * descendant edit parts.
     * 
     * @param root
     *            the root edit part of the iteration.
     */
    EditPartsTreeIterator(IGraphicalEditPart root) {
        super(root);
    }

    /**
     * Creates a new tree iterator on the descendants of the specified edit
     * part.
     * 
     * @param root
     *            the root edit part of the iteration.
     * @param includeRoot
     *            if <code>true</code>, the iterator includes the root element.
     */
    EditPartsTreeIterator(IGraphicalEditPart root, boolean includeRoot) {
        super(root, includeRoot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Iterator<? extends IGraphicalEditPart> getChildren(Object object) {
        if (object instanceof IGraphicalEditPart) {
            Iterable<IGraphicalEditPart> children = Iterables.filter(((IGraphicalEditPart) object).getChildren(), IGraphicalEditPart.class);
            return children.iterator();
        } else {
            return Collections.emptyIterator();
        }
    }

}
