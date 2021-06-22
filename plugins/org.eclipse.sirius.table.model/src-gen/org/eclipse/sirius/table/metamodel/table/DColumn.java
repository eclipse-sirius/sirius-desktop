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
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DColumn</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#getCells <em>Cells</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#getOriginMapping <em>Origin Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#getTable <em>Table</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#getOrderedCells <em>Ordered Cells</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#isVisible <em>Visible</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#getWidth <em>Width</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DColumn#getCurrentStyle <em>Current Style</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn()
 * @model abstract="true"
 * @generated
 */
public interface DColumn extends DTableElement {
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
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DColumn#getLabel <em>Label</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Cells</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.DCell}. It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getColumn <em>Column</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cells</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> List of cells of this column. This list does not necessarily have
     * as many cells as there are lines. Indeed, the ?blank cells? are not created. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Cells</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_Cells()
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getColumn
     * @model opposite="column"
     * @generated
     */
    EList<DCell> getCells();

    /**
     * Returns the value of the '<em><b>Origin Mapping</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Origin Mapping</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Origin Mapping</em>' reference.
     * @see #setOriginMapping(ColumnMapping)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_OriginMapping()
     * @model required="true"
     * @generated
     */
    ColumnMapping getOriginMapping();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DColumn#getOriginMapping <em>Origin
     * Mapping</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Origin Mapping</em>' reference.
     * @see #getOriginMapping()
     * @generated
     */
    void setOriginMapping(ColumnMapping value);

    /**
     * Returns the value of the '<em><b>Table</b></em>' container reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.DTable#getColumns <em>Columns</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table</em>' container reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Table</em>' container reference.
     * @see #setTable(DTable)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_Table()
     * @see org.eclipse.sirius.table.metamodel.table.DTable#getColumns
     * @model opposite="columns" transient="false"
     * @generated
     */
    DTable getTable();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DColumn#getTable <em>Table</em>}'
     * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Table</em>' container reference.
     * @see #getTable()
     * @generated
     */
    void setTable(DTable value);

    /**
     * Returns the value of the '<em><b>Ordered Cells</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.DCell}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ordered Cells</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Same list as a"cells" but sorted according to the order of lines.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Ordered Cells</em>' reference list.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_OrderedCells()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<DCell> getOrderedCells();

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
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_Visible()
     * @model default="true" required="true"
     * @generated
     */
    boolean isVisible();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DColumn#isVisible <em>Visible</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Visible</em>' attribute.
     * @see #isVisible()
     * @generated
     */
    void setVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(int)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DColumn#getWidth <em>Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Returns the value of the '<em><b>Current Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Stores the best style of the ColumnMapping style updater : * The best
     * style is only the default foreground style and the default background style (and only if the color use for it is
     * not with variable parts: ComputedColor or InterpolatedColor). * We can not store the result of the conditional
     * styles (foreground and background) because the predicateExpression depend on the semantic element of each line.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Current Style</em>' containment reference.
     * @see #setCurrentStyle(DTableElementStyle)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDColumn_CurrentStyle()
     * @model containment="true"
     * @generated
     */
    DTableElementStyle getCurrentStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DColumn#getCurrentStyle <em>Current
     * Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Current Style</em>' containment reference.
     * @see #getCurrentStyle()
     * @generated
     */
    void setCurrentStyle(DTableElementStyle value);

} // DColumn
