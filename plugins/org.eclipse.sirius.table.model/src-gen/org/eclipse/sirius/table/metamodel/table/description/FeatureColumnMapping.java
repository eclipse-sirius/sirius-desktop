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
package org.eclipse.sirius.table.metamodel.table.description;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Feature Column Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureName <em>Feature
 * Name</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getLabelExpression <em>Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureParentExpression
 * <em>Feature Parent Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getFeatureColumnMapping()
 * @model
 * @generated
 */
public interface FeatureColumnMapping extends ColumnMapping, CellUpdater, StyleUpdater {
    /**
     * Returns the value of the '<em><b>Feature Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Feature Name</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Feature Name</em>' attribute.
     * @see #setFeatureName(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getFeatureColumnMapping_FeatureName()
     * @model required="true"
     * @generated
     */
    String getFeatureName();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureName <em>Feature
     * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Feature Name</em>' attribute.
     * @see #getFeatureName()
     * @generated
     */
    void setFeatureName(String value);

    /**
     * Returns the value of the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Expression</em>' attribute.
     * @see #setLabelExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getFeatureColumnMapping_LabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables root='ecore.EObject | semantic
     *        target of the current DTable.' line='table.DLine | DLine of the current DCell.'
     *        lineSemantic='ecore.EObject | semantic target of $line' container='ecore.EObject | semantic target of
     *        $line.' column='table.DColumn | DColumn of the current DCell.' columnSemantic='ecore.EObject | semantic
     *        target of $column'"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getLabelExpression <em>Label
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Feature Parent Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> An expression to get the parent of the feature. By default, the parent
     * of the feature is the semantic element of the line. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Feature Parent Expression</em>' attribute.
     * @see #setFeatureParentExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getFeatureColumnMapping_FeatureParentExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression"
     * @generated
     */
    String getFeatureParentExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureParentExpression
     * <em>Feature Parent Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Feature Parent Expression</em>' attribute.
     * @see #getFeatureParentExpression()
     * @generated
     */
    void setFeatureParentExpression(String value);

} // FeatureColumnMapping
