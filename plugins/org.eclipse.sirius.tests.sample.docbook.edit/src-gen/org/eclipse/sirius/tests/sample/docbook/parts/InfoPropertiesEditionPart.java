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
public interface InfoPropertiesEditionPart {

    /**
     * Init the author
     *
     * @param current
     *            the current value
     * @param containgFeature
     *            the feature where to navigate if necessary
     * @param feature
     *            the feature to manage
     */
    public void initAuthor(ReferencesTableSettings settings);

    /**
     * Update the author
     *
     * @param newValue
     *            the author to update
     *
     */
    public void updateAuthor();

    /**
     * Adds the given filter to the author edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addFilterToAuthor(ViewerFilter filter);

    /**
     * Adds the given filter to the author edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addBusinessFilterToAuthor(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the author table
     *
     */
    public boolean isContainedInAuthorTable(EObject element);

    /**
     * @return the date
     *
     */
    public String getDate();

    /**
     * Defines a new date
     *
     * @param newValue
     *            the new date to set
     *
     */
    public void setDate(String newValue);

    /**
     * @return the pubdate
     *
     */
    public String getPubdate();

    /**
     * Defines a new pubdate
     *
     * @param newValue
     *            the new pubdate to set
     *
     */
    public void setPubdate(String newValue);

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
