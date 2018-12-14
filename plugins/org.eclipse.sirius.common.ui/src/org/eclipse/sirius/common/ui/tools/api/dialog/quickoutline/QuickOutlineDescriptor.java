/*******************************************************************************
 * Copyright (c) 2011, 2019 Obeo.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.base.Preconditions;

/**
 * Describes a quick outline.
 * 
 * @author ymortier
 */
public class QuickOutlineDescriptor {

    /**
     * All pages of the quick outline.
     */
    private List<QuickOutlinePageDescriptor> pages;

    /**
     * Creates a new {@link QuickOutlineDescriptor}.
     */
    public QuickOutlineDescriptor() {
        this.pages = new ArrayList<>();
    }

    /**
     * Adds the given page to this quick outline. The <code>page</code> argument
     * is mandatory.
     * 
     * @param page
     *            the page to add.
     */
    public void addPage(QuickOutlinePageDescriptor page) {
        Preconditions.checkArgument(page != null);

        this.pages.add(page);
    }

    /**
     * Returns all pages of this quick outline. The returned list is not
     * modifiable.
     * 
     * @return all pages of this quick outline.
     */
    public List<QuickOutlinePageDescriptor> getPages() {
        return Collections.<QuickOutlinePageDescriptor> unmodifiableList(this.pages);
    }

    /**
     * Returns the first page of this quick outline or <code>null</code> if this
     * outline has no page.
     * 
     * @return the first page of this quick outline or <code>null</code> if this
     *         outline has no page.
     */
    public Optional<QuickOutlinePageDescriptor> getFirstPage() {
        QuickOutlinePageDescriptor firstPage = null;
        if (!this.pages.isEmpty()) {
            firstPage = this.pages.get(0);
        }
        return Optional.ofNullable(firstPage);
    }

    /**
     * Returns the next page of <code>page</code>. Returns the first page if
     * <code>page</code> is <code>null</code> or if page is the last page.
     * 
     * @param page
     *            the previous page.
     * @return the next page of <code>page</code> or the first page if
     *         <code>page</code> is <code>null</code>.
     */
    public Optional<QuickOutlinePageDescriptor> getNextPage(QuickOutlinePageDescriptor page) {
        Optional<QuickOutlinePageDescriptor> result;
        if (page == null) {
            result = getFirstPage();
        } else {
            int indexNextPage = (this.pages.indexOf(page) + 1) % this.pages.size();
            result = Optional.ofNullable(this.pages.get(indexNextPage));
        }
        return result;
    }
}
