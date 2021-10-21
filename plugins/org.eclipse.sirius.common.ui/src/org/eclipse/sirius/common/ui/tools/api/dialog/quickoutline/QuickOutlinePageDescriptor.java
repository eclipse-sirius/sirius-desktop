/*******************************************************************************
 * Copyright (c) 2011, 2015 Obeo.
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
package org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline;

import java.util.Objects;

import org.eclipse.jface.viewers.IContentProvider;

/**
 * Descriptor of a quick outline page.
 * 
 * @author ymortier
 */
public class QuickOutlinePageDescriptor {

    /**
     * The callback to invoke when an object is selected.
     */
    private final IQuickOutlineCallback quickOutlineCallback;

    /**
     * The label provider of the tree.
     */
    private final IQuickOutlineLabelProvider labelProvider;

    /**
     * The content provider of the tree.
     */
    private final IContentProvider contentProvider;

    /**
     * Little description of the page.
     */
    private final String pageDescription;

    /**
     * Creates a new descriptor. All parameters are mandatory.
     * 
     * @param quickOutlineCallback
     *            callback to invoke when an object is selected.
     * @param labelProvider
     *            The label provider of the tree.
     * @param contentProvider
     *            The content provider of the tree.
     * @param pageDescription
     *            little description of the page.
     */
    public QuickOutlinePageDescriptor(final IQuickOutlineCallback quickOutlineCallback, final IQuickOutlineLabelProvider labelProvider, final IContentProvider contentProvider, String pageDescription) {
        this.quickOutlineCallback = Objects.requireNonNull(quickOutlineCallback);
        this.labelProvider = Objects.requireNonNull(labelProvider);
        this.contentProvider = Objects.requireNonNull(contentProvider);
        this.pageDescription = Objects.requireNonNull(pageDescription);
    }

    /**
     * Returns the callback to invoke when an object is selected.
     * 
     * @return the callback to invoke when an object is selected.
     */
    public IQuickOutlineCallback getQuickOutlineCallback() {
        return quickOutlineCallback;
    }

    /**
     * Returns the label provider of the tree.
     * 
     * @return the label provider of the tree.
     */
    public IQuickOutlineLabelProvider getLabelProvider() {
        return labelProvider;
    }

    /**
     * Returns the content provider of the tree.
     * 
     * @return the content provider of the tree.
     */
    public IContentProvider getContentProvider() {
        return contentProvider;
    }

    /**
     * Returns a little description of the page.
     * 
     * @return a little description of the page.
     */
    public String getPageDescription() {
        return pageDescription;
    }
}
