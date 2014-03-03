/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.LineContainer;

/**
 * The provider for the content of the table.
 * 
 * @author lredor
 */
public class DTableContentProvider implements ITreeContentProvider {

    /**
     * Returns the elements to display in the viewer (only the visible one).
     * 
     * @param inputElement
     *            the input element
     * @return the array of elements to display in the viewer
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof DTable) {
            final List<DLine> visibleLines = getVisibleLines((DTable) inputElement);
            return visibleLines.toArray();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#inputChanged(Viewer,
     *      Object, Object)
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(final Object parentElement) {
        Object[] result = null;
        if (parentElement instanceof DTable) {
            final List<DLine> visibleLines = getVisibleLines((DTable) parentElement);
            result = visibleLines.toArray();
        } else if (parentElement instanceof DLine) {
            final List<DLine> visibleLines = getVisibleLines((DLine) parentElement);
            result = visibleLines.toArray();
        }
        return result;
    }

    /**
     * Returns the visible lines of the given <@link LineContainer
     * lineContainer>.
     * <p>
     * 
     * @param lineContainer
     *            the line container
     * @return a list of visible lines
     */
    private List<DLine> getVisibleLines(final LineContainer lineContainer) {
        final List<DLine> visibleLines = new ArrayList<DLine>();
        for (DLine line : lineContainer.getLines()) {
            if (line.isVisible()) {
                visibleLines.add(line);
            }
        }
        return visibleLines;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(final Object element) {
        if (element instanceof DLine) {
            final DLine line = (DLine) element;
            return line.eContainer();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(final Object element) {
        final Object[] children = getChildren(element);
        return children != null && children.length > 0;
    }

    /**
     * Disposes of this content provider.
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
    }
}
