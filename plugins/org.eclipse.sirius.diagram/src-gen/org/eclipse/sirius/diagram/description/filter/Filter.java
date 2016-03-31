/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.filter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Filter</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A filter. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.filter.Filter#getFilterKind
 * <em>Filter Kind</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getFilter()
 * @model abstract="true"
 * @generated
 */
public interface Filter extends EObject {
    /**
     * Returns the value of the '<em><b>Filter Kind</b></em>' attribute. The
     * default value is <code>"HIDE"</code>. The literals are from the
     * enumeration
     * {@link org.eclipse.sirius.diagram.description.filter.FilterKind}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> A
     * filter might hide elements or just shrink them. In the case of the
     * shrink, the edges going to this element will be kept. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Filter Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.filter.FilterKind
     * @see #setFilterKind(FilterKind)
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getFilter_FilterKind()
     * @model default="HIDE"
     * @generated
     */
    FilterKind getFilterKind();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.filter.Filter#getFilterKind
     * <em>Filter Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Kind</em>' attribute.
     * @see org.eclipse.sirius.diagram.description.filter.FilterKind
     * @see #getFilterKind()
     * @generated
     */
    void setFilterKind(FilterKind value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Return true if the specified viewpoint element is visible for this
     * filter.
     *
     * @param element
     *            The element to test. <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean isVisible(DDiagramElement element);

} // Filter
