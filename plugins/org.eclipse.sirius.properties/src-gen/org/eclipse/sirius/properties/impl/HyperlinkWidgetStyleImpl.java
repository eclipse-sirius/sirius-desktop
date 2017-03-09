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
package org.eclipse.sirius.properties.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Hyperlink Widget Style</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl#getFontNameExpression <em>Font Name
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl#getFontSizeExpression <em>Font Size
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl#getBackgroundColor <em>Background
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl#getFontFormat <em>Font Format</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HyperlinkWidgetStyleImpl extends WidgetStyleImpl implements HyperlinkWidgetStyle {
    /**
     * The default value of the '{@link #getFontNameExpression() <em>Font Name Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFontNameExpression()
     * @generated
     * @ordered
     */
    protected static final String FONT_NAME_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFontNameExpression() <em>Font Name Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFontNameExpression()
     * @generated
     * @ordered
     */
    protected String fontNameExpression = HyperlinkWidgetStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFontSizeExpression() <em>Font Size Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFontSizeExpression()
     * @generated
     * @ordered
     */
    protected static final String FONT_SIZE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFontSizeExpression() <em>Font Size Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFontSizeExpression()
     * @generated
     * @ordered
     */
    protected String fontSizeExpression = HyperlinkWidgetStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getBackgroundColor() <em>Background Color</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected ColorDescription backgroundColor;

    /**
     * The cached value of the '{@link #getFontFormat() <em>Font Format</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getFontFormat()
     * @generated
     * @ordered
     */
    protected EList<FontFormat> fontFormat;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected HyperlinkWidgetStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.HYPERLINK_WIDGET_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFontNameExpression() {
        return fontNameExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFontNameExpression(String newFontNameExpression) {
        String oldFontNameExpression = fontNameExpression;
        fontNameExpression = newFontNameExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION, oldFontNameExpression, fontNameExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFontSizeExpression() {
        return fontSizeExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFontSizeExpression(String newFontSizeExpression) {
        String oldFontSizeExpression = fontSizeExpression;
        fontSizeExpression = newFontSizeExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION, oldFontSizeExpression, fontSizeExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (ColorDescription) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
                }
            }
        }
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundColor(ColorDescription newBackgroundColor) {
        ColorDescription oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FontFormat> getFontFormat() {
        if (fontFormat == null) {
            fontFormat = new EDataTypeUniqueEList<FontFormat>(FontFormat.class, this, PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_FORMAT);
        }
        return fontFormat;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION:
            return getFontNameExpression();
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION:
            return getFontSizeExpression();
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR:
            if (resolve) {
                return getBackgroundColor();
            }
            return basicGetBackgroundColor();
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_FORMAT:
            return getFontFormat();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION:
            setFontNameExpression((String) newValue);
            return;
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION:
            setFontSizeExpression((String) newValue);
            return;
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) newValue);
            return;
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_FORMAT:
            getFontFormat().clear();
            getFontFormat().addAll((Collection<? extends FontFormat>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION:
            setFontNameExpression(HyperlinkWidgetStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION:
            setFontSizeExpression(HyperlinkWidgetStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) null);
            return;
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_FORMAT:
            getFontFormat().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION:
            return HyperlinkWidgetStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT == null ? fontNameExpression != null : !HyperlinkWidgetStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT.equals(fontNameExpression);
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION:
            return HyperlinkWidgetStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT == null ? fontSizeExpression != null : !HyperlinkWidgetStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT.equals(fontSizeExpression);
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR:
            return backgroundColor != null;
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE__FONT_FORMAT:
            return fontFormat != null && !fontFormat.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (fontNameExpression: "); //$NON-NLS-1$
        result.append(fontNameExpression);
        result.append(", fontSizeExpression: "); //$NON-NLS-1$
        result.append(fontSizeExpression);
        result.append(", fontFormat: "); //$NON-NLS-1$
        result.append(fontFormat);
        result.append(')');
        return result.toString();
    }

} // HyperlinkWidgetStyleImpl
