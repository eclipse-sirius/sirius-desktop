/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.sirius.table.metamodel.table.description.TableMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DCell Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCellStyle#getForegroundStyleOrigin <em>Foreground Style
 * Origin</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCellStyle#getBackgroundStyleOrigin <em>Background Style
 * Origin</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCellStyle()
 * @model
 * @generated
 */
public interface DCellStyle extends DTableElementStyle {
    /**
     * Returns the value of the '<em><b>Foreground Style Origin</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Needed to know the origin of the foreground part of this DCellStyle to
     * respect the style priority rules between Cell, Line and Column. This TableMapping can be only an
     * IntersectionMapping or a ColumnMapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Foreground Style Origin</em>' reference.
     * @see #setForegroundStyleOrigin(TableMapping)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCellStyle_ForegroundStyleOrigin()
     * @model
     * @generated
     */
    TableMapping getForegroundStyleOrigin();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCellStyle#getForegroundStyleOrigin
     * <em>Foreground Style Origin</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Foreground Style Origin</em>' reference.
     * @see #getForegroundStyleOrigin()
     * @generated
     */
    void setForegroundStyleOrigin(TableMapping value);

    /**
     * Returns the value of the '<em><b>Background Style Origin</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Needed to know the origin of the background part of this DCellStyle to
     * respect the style priority rules between Cell, Line and Column. This TableMapping can be only an
     * IntersectionMapping or a ColumnMapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Background Style Origin</em>' reference.
     * @see #setBackgroundStyleOrigin(TableMapping)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCellStyle_BackgroundStyleOrigin()
     * @model
     * @generated
     */
    TableMapping getBackgroundStyleOrigin();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCellStyle#getBackgroundStyleOrigin
     * <em>Background Style Origin</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Style Origin</em>' reference.
     * @see #getBackgroundStyleOrigin()
     * @generated
     */
    void setBackgroundStyleOrigin(TableMapping value);

} // DCellStyle
