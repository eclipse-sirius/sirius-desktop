/*******************************************************************************    
 * Copyright (c) 2009, 2014 Obeo and others. All rights 
 * reserved. This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License 2.0 which accompanies this    
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0  
 *  
 * Contributors: Obeo - initial API and implementation  
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.swt.widgets.TreeItem;

/**
 * A {@link Runnable} to call {@link TreeItem#setExpanded(boolean)}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ChangeExpandeStateRunnable implements Runnable {

    private TreeItem treeItem;

    private boolean expand;

    /**
     * Default constructor.
     * 
     * @param treeItem
     *            the {@link TreeItem} for which to call
     *            {@link TreeItem#setExpanded(boolean)}
     * @param expand
     *            the value of the parameter
     */
    public ChangeExpandeStateRunnable(TreeItem treeItem, boolean expand) {
        this.treeItem = treeItem;
        this.expand = expand;
    }

    @Override
    public void run() {
        if (treeItem != null && !treeItem.isDisposed()) {
            treeItem.setExpanded(expand);
        }
    }
}
