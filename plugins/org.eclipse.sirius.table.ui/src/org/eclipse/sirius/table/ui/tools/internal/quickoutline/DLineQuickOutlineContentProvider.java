/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.quickoutline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;

/**
 * A Table Quick Outline specific {@link ITreeContentProvider} for
 * {@link org.eclipse.sirius.table.metamodel.table.DLine}.
 * 
 * @author ymortier
 */
public class DLineQuickOutlineContentProvider implements ITreeContentProvider {

    private static final Object[] EMPTY = {};

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof DTable) {
            DTable table = (DTable) inputElement;
            return table.getLines().toArray();
        }
        return EMPTY;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof LineContainer) {
            return ((LineContainer) parentElement).getLines().toArray();
        }
        return EMPTY;
    }

    @Override
    public Object getParent(Object element) {
        Object result;
        if (element instanceof DTable) {
            result = null;
        } else if (element instanceof EObject) {
            result = ((EObject) element).eContainer();
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public boolean hasChildren(Object element) {
        boolean result = false;
        if (element instanceof LineContainer) {
            LineContainer lineContainer = (LineContainer) element;
            result = !lineContainer.getLines().isEmpty();
        }
        return result;
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // nothing to do.
    }

    @Override
    public void dispose() {
        // empty.
    }
}
