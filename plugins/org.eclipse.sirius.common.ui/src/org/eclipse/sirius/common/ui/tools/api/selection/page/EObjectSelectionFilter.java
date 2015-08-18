/*******************************************************************************
 * Copyright (c) 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.selection.page;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.eclipse.sirius.common.tools.api.util.StringMatcher;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.CommonItem;

/**
 * A selection filter.
 * 
 * @author mchauvin
 */
public class EObjectSelectionFilter extends ViewerFilter {

    /**
     * The regular expression (for example '?a?' or 'abc' or '*c') ;
     * <code>null</code> or empty regular expression will be replaced by '*'
     */
    private String regExp;

    private TreeViewer treeViewer;

    /**
     * Set a prefix to the filter.
     * 
     * @param newRegExp
     *            the new prefix to set
     */
    public void setPrefix(final String newRegExp) {
        this.regExp = newRegExp;
        if (treeViewer != null) {
            treeViewer.refresh();
        }
    }

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {

        boolean selected = false;
        Object element2 = null;

        if (element instanceof TreeItemWrapper) {

            /* select parent if child should be selected */
            for (final TreeItemWrapper childItem : ((TreeItemWrapper) element).getChildren()) {
                if (select(viewer, element, childItem)) {
                    return true;
                }
            }

            element2 = ((TreeItemWrapper) element).getWrappedObject();

        } else {
            element2 = element;
        }

        if (element2 instanceof EObject || element2 instanceof CommonItem) {
            if (regExp == null || regExp.length() == 0) {
                selected = true;
            }

            if (treeViewer != null) {
                final String text = ((ILabelProvider) treeViewer.getLabelProvider()).getText(element2);
                if (!selected && text != null && getStringMatcher().match(text)) {
                    selected = true;
                }
            }
        }

        return selected;
    }

    public void setTreeViewer(final TreeViewer treeViewer) {
        this.treeViewer = treeViewer;
    }

    /**
     * Creates a new StringMatcher corresponding to the regExp.
     * 
     * @return a new StringMatcher
     */
    private StringMatcher getStringMatcher() {
        String computedRegExp = regExp;
        if (regExp == null) {
            computedRegExp = ""; //$NON-NLS-1$
        }
        // If the regular expression ends with a space, we have to use the exact
        // value of the given expreg
        if (computedRegExp.endsWith(" ")) { //$NON-NLS-1$
            computedRegExp = computedRegExp.substring(0, computedRegExp.lastIndexOf(' '));
        }
        // At the end we add a star to make 'XYZ' recognized by the 'X'
        // expreg (as in quick outline for example)
        computedRegExp = computedRegExp + "*"; //$NON-NLS-1$

        return new StringMatcher(computedRegExp, true, false);
    }
}
