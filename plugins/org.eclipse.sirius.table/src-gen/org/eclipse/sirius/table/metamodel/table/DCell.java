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

import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DCell</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCell#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCell#getLine <em>Line</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCell#getColumn <em>Column</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCell#getCurrentStyle <em>Current Style</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCell#getUpdater <em>Updater</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DCell#getIntersectionMapping <em>Intersection Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCell()
 * @model
 * @generated
 */
public interface DCell extends DSemanticDecorator, DTableElement {
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
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCell_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCell#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Line</b></em>' container reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.DLine#getCells <em>Cells</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line</em>' container reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Line</em>' container reference.
     * @see #setLine(DLine)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCell_Line()
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getCells
     * @model opposite="cells" transient="false"
     * @generated
     */
    DLine getLine();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCell#getLine <em>Line</em>}' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Line</em>' container reference.
     * @see #getLine()
     * @generated
     */
    void setLine(DLine value);

    /**
     * Returns the value of the '<em><b>Column</b></em>' reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.DColumn#getCells <em>Cells</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Column</em>' reference.
     * @see #setColumn(DColumn)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCell_Column()
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getCells
     * @model opposite="cells"
     * @generated
     */
    DColumn getColumn();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCell#getColumn <em>Column</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Column</em>' reference.
     * @see #getColumn()
     * @generated
     */
    void setColumn(DColumn value);

    /**
     * Returns the value of the '<em><b>Current Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Current Style</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Stores the best style of the IntersectionMapping style updater and
     * ColumnMapping style updater : * Foreground ** The first conditional foreground style of the cell (with predicate
     * expression that returns true). In this case the foregroundStyleOrigin references the intersection mapping and the
     * defaultForegroundStyle is equal false. ** Otherwise the first conditional foreground style of the column (with
     * predicate expression that returns true). In this case the foregroundStyleOrigin references the column mapping and
     * the defaultForegroundStyle is equal false. ** Otherwise, if it exists, the default foreground style of the cell.
     * In this case the foregroundStyleOrigin references the intersection mapping and the defaultForegroundStyle is
     * equal true. ** Otherwise, if the default foreground style of the column uses variable color, the default
     * foreground style of the column. In this case the foregroundStyleOrigin references the column mapping and the
     * defaultForegroundStyle is equal true. * Background ** The first conditional background style of the cell (with
     * predicate expression that returns true). In this case the backgroundStyleOrigin references the intersection
     * mapping and the defaultBackgroundStyle is equal false. ** Otherwise the first conditional background style of the
     * column (with predicate expression that returns true). In this case the backgroundStyleOrigin references the
     * column mapping and the defaultBackgroundStyle is equal false. ** Otherwise, if it exists, the default background
     * style of the cell. In this case the backgroundStyleOrigin references the intersection mapping and the
     * defaultBackgroundStyle is equal true. ** Otherwise, if the default background style of the column uses variable
     * color, the default background style of the column. In this case the backgroundStyleOrigin references the column
     * mapping and the defaultBackgroundStyle is equal true. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Current Style</em>' containment reference.
     * @see #setCurrentStyle(DCellStyle)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCell_CurrentStyle()
     * @model containment="true"
     * @generated
     */
    DCellStyle getCurrentStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCell#getCurrentStyle <em>Current
     * Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Current Style</em>' containment reference.
     * @see #getCurrentStyle()
     * @generated
     */
    void setCurrentStyle(DCellStyle value);

    /**
     * Returns the value of the '<em><b>Updater</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Updater</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Updater</em>' reference.
     * @see #setUpdater(CellUpdater)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCell_Updater()
     * @model transient="true" volatile="true" derived="true"
     * @generated
     */
    CellUpdater getUpdater();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCell#getUpdater <em>Updater</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Updater</em>' reference.
     * @see #getUpdater()
     * @generated
     */
    void setUpdater(CellUpdater value);

    /**
     * Returns the value of the '<em><b>Intersection Mapping</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Intersection Mapping</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Intersection Mapping</em>' reference.
     * @see #setIntersectionMapping(IntersectionMapping)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDCell_IntersectionMapping()
     * @model
     * @generated
     */
    IntersectionMapping getIntersectionMapping();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DCell#getIntersectionMapping
     * <em>Intersection Mapping</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Intersection Mapping</em>' reference.
     * @see #getIntersectionMapping()
     * @generated
     */
    void setIntersectionMapping(IntersectionMapping value);

} // DCell
