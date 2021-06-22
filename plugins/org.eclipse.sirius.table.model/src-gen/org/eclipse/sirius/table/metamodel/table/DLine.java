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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DLine</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#getOriginMapping <em>Origin Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#isVisible <em>Visible</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#isCollapsed <em>Collapsed</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#getCells <em>Cells</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#getContainer <em>Container</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#getOrderedCells <em>Ordered Cells</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DLine#getCurrentStyle <em>Current Style</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine()
 * @model
 * @generated
 */
public interface DLine extends LineContainer, DTableElement {
    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DLine#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Origin Mapping</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Origin Mapping</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Origin Mapping</em>' reference.
     * @see #setOriginMapping(LineMapping)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_OriginMapping()
     * @model required="true"
     * @generated
     */
    LineMapping getOriginMapping();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DLine#getOriginMapping <em>Origin
     * Mapping</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Origin Mapping</em>' reference.
     * @see #getOriginMapping()
     * @generated
     */
    void setOriginMapping(LineMapping value);

    /**
     * Returns the value of the '<em><b>Visible</b></em>' attribute. The default value is <code>"true"</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Visible</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Visible</em>' attribute.
     * @see #setVisible(boolean)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_Visible()
     * @model default="true" required="true"
     * @generated
     */
    boolean isVisible();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DLine#isVisible <em>Visible</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Visible</em>' attribute.
     * @see #isVisible()
     * @generated
     */
    void setVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Collapsed</b></em>' attribute. The default value is <code>"false"</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Collapsed</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Collapsed</em>' attribute.
     * @see #setCollapsed(boolean)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_Collapsed()
     * @model default="false" required="true"
     * @generated
     */
    boolean isCollapsed();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DLine#isCollapsed <em>Collapsed</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Collapsed</em>' attribute.
     * @see #isCollapsed()
     * @generated
     */
    void setCollapsed(boolean value);

    /**
     * Returns the value of the '<em><b>Cells</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.DCell}. It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getLine <em>Line</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cells</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> List of cells of this line. This list does not necessarily have as
     * many cells as there are columns. Indeed, the ?blank cells? are not created. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Cells</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_Cells()
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getLine
     * @model opposite="line" containment="true"
     * @generated
     */
    EList<DCell> getCells();

    /**
     * Returns the value of the '<em><b>Container</b></em>' container reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.LineContainer#getLines <em>Lines</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Container</em>' container reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Container</em>' container reference.
     * @see #setContainer(LineContainer)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_Container()
     * @see org.eclipse.sirius.table.metamodel.table.LineContainer#getLines
     * @model opposite="lines" transient="false"
     * @generated
     */
    LineContainer getContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DLine#getContainer <em>Container</em>}'
     * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Container</em>' container reference.
     * @see #getContainer()
     * @generated
     */
    void setContainer(LineContainer value);

    /**
     * Returns the value of the '<em><b>Ordered Cells</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.DCell}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ordered Cells</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Same list as "cells" but sorted according to the order of columns.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Ordered Cells</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_OrderedCells()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DCell> getOrderedCells();

    /**
     * Returns the value of the '<em><b>Current Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Stores the best style of the LineMapping style updater : * The first
     * conditional foreground style (with predicate expression that returns true), otherwise the default foreground
     * style. * The first conditional background style (with predicate expression that returns true), otherwise the
     * default background style. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Current Style</em>' containment reference.
     * @see #setCurrentStyle(DTableElementStyle)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDLine_CurrentStyle()
     * @model containment="true"
     * @generated
     */
    DTableElementStyle getCurrentStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DLine#getCurrentStyle <em>Current
     * Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Current Style</em>' containment reference.
     * @see #getCurrentStyle()
     * @generated
     */
    void setCurrentStyle(DTableElementStyle value);

} // DLine
