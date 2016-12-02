/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.properties.WidgetDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Ext
 * Reference Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceNameExpression
 * <em>Reference Name Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceOwnerExpression
 * <em>Reference Owner Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getStyle
 * <em>Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getConditionalStyles
 * <em>Conditional Styles</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage#getExtReferenceDescription()
 * @model
 * @generated
 */
public interface ExtReferenceDescription extends WidgetDescription {
    /**
     * Returns the value of the '<em><b>Reference Name Expression</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference Name Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reference Name Expression</em>' attribute.
     * @see #setReferenceNameExpression(String)
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage#getExtReferenceDescription_ReferenceNameExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true"
     * @generated
     */
    String getReferenceNameExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceNameExpression
     * <em>Reference Name Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Reference Name Expression</em>'
     *            attribute.
     * @see #getReferenceNameExpression()
     * @generated
     */
    void setReferenceNameExpression(String value);

    /**
     * Returns the value of the '<em><b>Reference Owner Expression</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference Owner Expression</em>' attribute
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reference Owner Expression</em>' attribute.
     * @see #setReferenceOwnerExpression(String)
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage#getExtReferenceDescription_ReferenceOwnerExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getReferenceOwnerExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceOwnerExpression
     * <em>Reference Owner Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Reference Owner Expression</em>'
     *            attribute.
     * @see #getReferenceOwnerExpression()
     * @generated
     */
    void setReferenceOwnerExpression(String value);

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(ExtReferenceWidgetStyle)
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage#getExtReferenceDescription_Style()
     * @model containment="true"
     * @generated
     */
    ExtReferenceWidgetStyle getStyle();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(ExtReferenceWidgetStyle value);

    /**
     * Returns the value of the '<em><b>Conditional Styles</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Conditional Styles</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Conditional Styles</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage#getExtReferenceDescription_ConditionalStyles()
     * @model containment="true"
     * @generated
     */
    EList<ExtReferenceWidgetConditionalStyle> getConditionalStyles();

} // ExtReferenceDescription
