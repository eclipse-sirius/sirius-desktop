/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.style;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Basic Label Style Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> The style of a label. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelSize <em>Label
 * Size</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelFormat <em>Label
 * Format</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#isShowIcon <em>Show
 * Icon</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelExpression <em>Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelColor <em>Label
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getIconPath <em>Icon
 * Path</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getBasicLabelStyleDescription()
 * @model
 * @generated
 */
public interface BasicLabelStyleDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Label Size</b></em>' attribute. The default value is <code>"8"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The font size. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Size</em>' attribute.
     * @see #setLabelSize(int)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getBasicLabelStyleDescription_LabelSize()
     * @model default="8"
     * @generated
     */
    int getLabelSize();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelSize <em>Label
     * Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Size</em>' attribute.
     * @see #getLabelSize()
     * @generated
     */
    void setLabelSize(int value);

    /**
     * Returns the value of the '<em><b>Label Format</b></em>' attribute. The default value is <code>"normal"</code>.
     * The literals are from the enumeration {@link org.eclipse.sirius.viewpoint.FontFormat}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The font format. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Format</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @see #setLabelFormat(FontFormat)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getBasicLabelStyleDescription_LabelFormat()
     * @model default="normal"
     * @generated
     */
    EList<FontFormat> getLabelFormat();

    /**
     * Returns the value of the '<em><b>Show Icon</b></em>' attribute. The default value is <code>"true"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> True, if the icon shoud be dispayed on the
     * element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Show Icon</em>' attribute.
     * @see #setShowIcon(boolean)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getBasicLabelStyleDescription_ShowIcon()
     * @model default="true"
     * @generated
     */
    boolean isShowIcon();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#isShowIcon <em>Show Icon</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Show Icon</em>' attribute.
     * @see #isShowIcon()
     * @generated
     */
    void setShowIcon(boolean value);

    /**
     * Returns the value of the '<em><b>Label Expression</b></em>' attribute. The default value is
     * <code>"feature:name"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Expression
     * that computes the name of a node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Expression</em>' attribute.
     * @see #setLabelExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getBasicLabelStyleDescription_LabelExpression()
     * @model default="feature:name" dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation = "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables diagram='diagram.DSemanticDiagram | the
     *        current DSemanticDiagram.' view='diagram.DDiagramElement | the current view for which the label is
     *        calculated.'"
     * @generated
     */
    String getLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelExpression <em>Label
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Expression</em>' attribute.
     * @see #getLabelExpression()
     * @generated
     */
    void setLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Label Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Color</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Color</em>' reference.
     * @see #setLabelColor(ColorDescription)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getBasicLabelStyleDescription_LabelColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getLabelColor();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelColor <em>Label
     * Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Color</em>' reference.
     * @see #getLabelColor()
     * @generated
     */
    void setLabelColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The path of the icon to display on the element.
     * If unset, the icon corresponding to the semantic element will be displayed. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getBasicLabelStyleDescription_IconPath()
     * @model default=""
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getIconPath <em>Icon
     * Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Icon Path</em>' attribute.
     * @see #getIconPath()
     * @generated
     */
    void setIconPath(String value);

} // BasicLabelStyleDescription
