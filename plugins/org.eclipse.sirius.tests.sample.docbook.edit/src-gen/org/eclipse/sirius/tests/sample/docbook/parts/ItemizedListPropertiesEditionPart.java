/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.tests.sample.docbook.parts;

// Start of user code for imports
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.ViewerFilter;

// End of user code

/**
 *
 *
 */
public interface ItemizedListPropertiesEditionPart {

    /**
     * @return the mark
     *
     */
    public String getMark();

    /**
     * Defines a new mark
     *
     * @param newValue
     *            the new mark to set
     *
     */
    public void setMark(String newValue);

    /**
     * Init the listitem
     *
     * @param current
     *            the current value
     * @param containgFeature
     *            the feature where to navigate if necessary
     * @param feature
     *            the feature to manage
     */
    public void initListitem(ReferencesTableSettings settings);

    /**
     * Update the listitem
     *
     * @param newValue
     *            the listitem to update
     *
     */
    public void updateListitem();

    /**
     * Adds the given filter to the listitem edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addFilterToListitem(ViewerFilter filter);

    /**
     * Adds the given filter to the listitem edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addBusinessFilterToListitem(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the listitem table
     *
     */
    public boolean isContainedInListitemTable(EObject element);

    /**
     * Returns the internationalized title text.
     *
     * @return the internationalized title text.
     *
     */
    public String getTitle();

    // Start of user code for additional methods

    // End of user code

}
