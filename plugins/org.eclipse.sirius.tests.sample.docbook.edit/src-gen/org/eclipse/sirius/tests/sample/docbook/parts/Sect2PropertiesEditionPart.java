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
public interface Sect2PropertiesEditionPart {

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
     * Init the sect3
     *
     * @param current
     *            the current value
     * @param containgFeature
     *            the feature where to navigate if necessary
     * @param feature
     *            the feature to manage
     */
    public void initSect3(ReferencesTableSettings settings);

    /**
     * Update the sect3
     *
     * @param newValue
     *            the sect3 to update
     *
     */
    public void updateSect3();

    /**
     * Adds the given filter to the sect3 edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addFilterToSect3(ViewerFilter filter);

    /**
     * Adds the given filter to the sect3 edition editor.
     *
     * @param filter
     *            a viewer filter
     * @see org.eclipse.jface.viewers.StructuredViewer#addFilter(ViewerFilter)
     *
     */
    public void addBusinessFilterToSect3(ViewerFilter filter);

    /**
     * @return true if the given element is contained inside the sect3 table
     *
     */
    public boolean isContainedInSect3Table(EObject element);

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
