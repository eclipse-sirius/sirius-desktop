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
public interface ChapterPropertiesEditionPart {

    /**
     * Init the para
     *
     * @param current
     *            the current value
     * @param containgFeature
     *            the feature where to navigate if necessary
     * @param feature
     *            the feature to manage
     */
    public void initPara(ReferencesTableSettings settings);

    /**
     * Update the para
     *
     * @param newValue
     *            the para to update
     *
     */
    public void updatePara();

    /**
     * Adds the given filter to the para edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addFilterToPara(ViewerFilter filter);

    /**
     * Adds the given filter to the para edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addBusinessFilterToPara(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the para table
     *
     */
    public boolean isContainedInParaTable(EObject element);

    /**
     * Init the sect1
     *
     * @param current
     *            the current value
     * @param containgFeature
     *            the feature where to navigate if necessary
     * @param feature
     *            the feature to manage
     */
    public void initSect1(ReferencesTableSettings settings);

    /**
     * Update the sect1
     *
     * @param newValue
     *            the sect1 to update
     *
     */
    public void updateSect1();

    /**
     * Adds the given filter to the sect1 edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addFilterToSect1(ViewerFilter filter);

    /**
     * Adds the given filter to the sect1 edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addBusinessFilterToSect1(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the sect1 table
     *
     */
    public boolean isContainedInSect1Table(EObject element);

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
     * Returns the internationalized title text.
     *
     * @return the internationalized title text.
     *
     */
    public String getTitle();

    // Start of user code for additional methods

    // End of user code

}
