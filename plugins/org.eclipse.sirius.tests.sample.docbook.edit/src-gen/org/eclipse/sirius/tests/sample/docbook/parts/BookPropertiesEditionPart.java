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
public interface BookPropertiesEditionPart {

    /**
     * Init the chapter
     *
     * @param current
     *            the current value
     * @param containgFeature
     *            the feature where to navigate if necessary
     * @param feature
     *            the feature to manage
     */
    public void initChapter(ReferencesTableSettings settings);

    /**
     * Update the chapter
     *
     * @param newValue
     *            the chapter to update
     *
     */
    public void updateChapter();

    /**
     * Adds the given filter to the chapter edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addFilterToChapter(ViewerFilter filter);

    /**
     * Adds the given filter to the chapter edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addBusinessFilterToChapter(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the chapter table
     *
     */
    public boolean isContainedInChapterTable(EObject element);

    /**
     * @return the id
     *
     */
    public String getId();

    /**
     * Defines a new id
     *
     * @param newValue
     *            the new id to set
     *
     */
    public void setId(String newValue);

    /**
     * @return the lang
     *
     */
    public String getLang();

    /**
     * Defines a new lang
     *
     * @param newValue
     *            the new lang to set
     *
     */
    public void setLang(String newValue);

    /**
     * @return the version
     *
     */
    public String getVersion();

    /**
     * Defines a new version
     *
     * @param newValue
     *            the new version to set
     *
     */
    public void setVersion(String newValue);

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
