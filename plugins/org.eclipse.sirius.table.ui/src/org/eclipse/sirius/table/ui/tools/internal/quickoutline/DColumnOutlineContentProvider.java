/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
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
package org.eclipse.sirius.table.ui.tools.internal.quickoutline;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.table.metamodel.table.DTable;

/**
 * A Table specific {@link ITreeContentProvider} for
 * {@link org.eclipse.sirius.table.metamodel.table.DColumn}.
 * 
 * @author ymortier
 */
public class DColumnOutlineContentProvider implements ITreeContentProvider {

    private static final Object[] EMPTY = {};

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof DTable) {
            return ((DTable) inputElement).getColumns().toArray();
        }
        return EMPTY;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        return EMPTY;
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return false;
    }

    @Override
    public void dispose() {
        // empty.
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // empty.
    }

}
