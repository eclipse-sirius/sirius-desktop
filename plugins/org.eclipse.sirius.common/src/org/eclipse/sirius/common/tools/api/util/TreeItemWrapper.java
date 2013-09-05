/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A tree item wrapper.
 * 
 * @author mchauvin
 */
public class TreeItemWrapper {

    /**
     * The virtual root item.
     */
    public static final TreeItemWrapper ROOT_ITEM = new TreeItemWrapper(null, null);

    private Object wrappedObject;

    private List<TreeItemWrapper> children;

    private TreeItemWrapper parent;

    /**
     * Default constructor.
     * 
     * @param wrappedObect
     *            the wrapped object
     * @param parent
     *            the parent tree item wrapper
     */
    public TreeItemWrapper(final Object wrappedObect, final TreeItemWrapper parent) {
        this.wrappedObject = wrappedObect;
        this.parent = parent;
        children = new ArrayList<TreeItemWrapper>(5);
    }

    /**
     * Get the wrapped object.
     * 
     * @return the wrapped object
     */
    public Object getWrappedObject() {
        return this.wrappedObject;
    }

    /**
     * Get the children.
     * 
     * @return the children of the tree item wrapper
     */
    public List<TreeItemWrapper> getChildren() {
        return this.children;
    }

    /**
     * Get the parent.
     * 
     * @return the parent of the tree item wrapper
     */
    public TreeItemWrapper getParent() {
        return this.parent;
    }

    /**
     * Avoid a cycle in this branch.
     * 
     * @param o
     *            the tested object
     * 
     * @return true if the tested object is not a parent.
     */
    public boolean knownThisAsAncestor(final Object o) {
        boolean result = false;
        if (wrappedObject == null) {
            result = false;
        } else if (wrappedObject.equals(o)) {
            result = true;
        } else {
            result = parent.knownThisAsAncestor(o);
        }
        return result;

    }

}
