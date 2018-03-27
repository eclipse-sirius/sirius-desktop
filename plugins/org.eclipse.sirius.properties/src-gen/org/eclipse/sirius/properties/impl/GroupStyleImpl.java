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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.properties.GroupStyle;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.TitleBarStyle;
import org.eclipse.sirius.properties.ToggleStyle;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupStyleImpl#getBackgroundColor <em>Background Color</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupStyleImpl#getForegroundColor <em>Foreground Color</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupStyleImpl#getFontNameExpression <em>Font Name
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupStyleImpl#getFontSizeExpression <em>Font Size
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupStyleImpl#getBarStyle <em>Bar Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupStyleImpl#getToggleStyle <em>Toggle Style</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupStyleImpl#isExpandedByDefault <em>Expanded By Default</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupStyleImpl extends MinimalEObjectImpl.Container implements GroupStyle {
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
     * The cached value of the '{@link #getForegroundColor() <em>Foreground Color</em>}' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected ColorDescription foregroundColor;

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
    protected String fontNameExpression = GroupStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT;

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
    protected String fontSizeExpression = GroupStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getBarStyle() <em>Bar Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBarStyle()
     * @generated
     * @ordered
     */
    protected static final TitleBarStyle BAR_STYLE_EDEFAULT = TitleBarStyle.TITLE_BAR;

    /**
     * The cached value of the '{@link #getBarStyle() <em>Bar Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBarStyle()
     * @generated
     * @ordered
     */
    protected TitleBarStyle barStyle = GroupStyleImpl.BAR_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getToggleStyle() <em>Toggle Style</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getToggleStyle()
     * @generated
     * @ordered
     */
    protected static final ToggleStyle TOGGLE_STYLE_EDEFAULT = ToggleStyle.TWISTIE;

    /**
     * The cached value of the '{@link #getToggleStyle() <em>Toggle Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getToggleStyle()
     * @generated
     * @ordered
     */
    protected ToggleStyle toggleStyle = GroupStyleImpl.TOGGLE_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #isExpandedByDefault() <em>Expanded By Default</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isExpandedByDefault()
     * @generated
     * @ordered
     */
    protected static final boolean EXPANDED_BY_DEFAULT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isExpandedByDefault() <em>Expanded By Default</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isExpandedByDefault()
     * @generated
     * @ordered
     */
    protected boolean expandedByDefault = GroupStyleImpl.EXPANDED_BY_DEFAULT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GroupStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.GROUP_STYLE;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.GROUP_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getForegroundColor() {
        if (foregroundColor != null && foregroundColor.eIsProxy()) {
            InternalEObject oldForegroundColor = (InternalEObject) foregroundColor;
            foregroundColor = (ColorDescription) eResolveProxy(oldForegroundColor);
            if (foregroundColor != oldForegroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.GROUP_STYLE__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
                }
            }
        }
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetForegroundColor() {
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForegroundColor(ColorDescription newForegroundColor) {
        ColorDescription oldForegroundColor = foregroundColor;
        foregroundColor = newForegroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_STYLE__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_STYLE__FONT_NAME_EXPRESSION, oldFontNameExpression, fontNameExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_STYLE__FONT_SIZE_EXPRESSION, oldFontSizeExpression, fontSizeExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TitleBarStyle getBarStyle() {
        return barStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBarStyle(TitleBarStyle newBarStyle) {
        TitleBarStyle oldBarStyle = barStyle;
        barStyle = newBarStyle == null ? GroupStyleImpl.BAR_STYLE_EDEFAULT : newBarStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_STYLE__BAR_STYLE, oldBarStyle, barStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToggleStyle getToggleStyle() {
        return toggleStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setToggleStyle(ToggleStyle newToggleStyle) {
        ToggleStyle oldToggleStyle = toggleStyle;
        toggleStyle = newToggleStyle == null ? GroupStyleImpl.TOGGLE_STYLE_EDEFAULT : newToggleStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_STYLE__TOGGLE_STYLE, oldToggleStyle, toggleStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isExpandedByDefault() {
        return expandedByDefault;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setExpandedByDefault(boolean newExpandedByDefault) {
        boolean oldExpandedByDefault = expandedByDefault;
        expandedByDefault = newExpandedByDefault;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_STYLE__EXPANDED_BY_DEFAULT, oldExpandedByDefault, expandedByDefault));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiesPackage.GROUP_STYLE__BACKGROUND_COLOR:
            if (resolve) {
                return getBackgroundColor();
            }
            return basicGetBackgroundColor();
        case PropertiesPackage.GROUP_STYLE__FOREGROUND_COLOR:
            if (resolve) {
                return getForegroundColor();
            }
            return basicGetForegroundColor();
        case PropertiesPackage.GROUP_STYLE__FONT_NAME_EXPRESSION:
            return getFontNameExpression();
        case PropertiesPackage.GROUP_STYLE__FONT_SIZE_EXPRESSION:
            return getFontSizeExpression();
        case PropertiesPackage.GROUP_STYLE__BAR_STYLE:
            return getBarStyle();
        case PropertiesPackage.GROUP_STYLE__TOGGLE_STYLE:
            return getToggleStyle();
        case PropertiesPackage.GROUP_STYLE__EXPANDED_BY_DEFAULT:
            return isExpandedByDefault();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PropertiesPackage.GROUP_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) newValue);
            return;
        case PropertiesPackage.GROUP_STYLE__FOREGROUND_COLOR:
            setForegroundColor((ColorDescription) newValue);
            return;
        case PropertiesPackage.GROUP_STYLE__FONT_NAME_EXPRESSION:
            setFontNameExpression((String) newValue);
            return;
        case PropertiesPackage.GROUP_STYLE__FONT_SIZE_EXPRESSION:
            setFontSizeExpression((String) newValue);
            return;
        case PropertiesPackage.GROUP_STYLE__BAR_STYLE:
            setBarStyle((TitleBarStyle) newValue);
            return;
        case PropertiesPackage.GROUP_STYLE__TOGGLE_STYLE:
            setToggleStyle((ToggleStyle) newValue);
            return;
        case PropertiesPackage.GROUP_STYLE__EXPANDED_BY_DEFAULT:
            setExpandedByDefault((Boolean) newValue);
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
        case PropertiesPackage.GROUP_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) null);
            return;
        case PropertiesPackage.GROUP_STYLE__FOREGROUND_COLOR:
            setForegroundColor((ColorDescription) null);
            return;
        case PropertiesPackage.GROUP_STYLE__FONT_NAME_EXPRESSION:
            setFontNameExpression(GroupStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_STYLE__FONT_SIZE_EXPRESSION:
            setFontSizeExpression(GroupStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_STYLE__BAR_STYLE:
            setBarStyle(GroupStyleImpl.BAR_STYLE_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_STYLE__TOGGLE_STYLE:
            setToggleStyle(GroupStyleImpl.TOGGLE_STYLE_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_STYLE__EXPANDED_BY_DEFAULT:
            setExpandedByDefault(GroupStyleImpl.EXPANDED_BY_DEFAULT_EDEFAULT);
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
        case PropertiesPackage.GROUP_STYLE__BACKGROUND_COLOR:
            return backgroundColor != null;
        case PropertiesPackage.GROUP_STYLE__FOREGROUND_COLOR:
            return foregroundColor != null;
        case PropertiesPackage.GROUP_STYLE__FONT_NAME_EXPRESSION:
            return GroupStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT == null ? fontNameExpression != null : !GroupStyleImpl.FONT_NAME_EXPRESSION_EDEFAULT.equals(fontNameExpression);
        case PropertiesPackage.GROUP_STYLE__FONT_SIZE_EXPRESSION:
            return GroupStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT == null ? fontSizeExpression != null : !GroupStyleImpl.FONT_SIZE_EXPRESSION_EDEFAULT.equals(fontSizeExpression);
        case PropertiesPackage.GROUP_STYLE__BAR_STYLE:
            return barStyle != GroupStyleImpl.BAR_STYLE_EDEFAULT;
        case PropertiesPackage.GROUP_STYLE__TOGGLE_STYLE:
            return toggleStyle != GroupStyleImpl.TOGGLE_STYLE_EDEFAULT;
        case PropertiesPackage.GROUP_STYLE__EXPANDED_BY_DEFAULT:
            return expandedByDefault != GroupStyleImpl.EXPANDED_BY_DEFAULT_EDEFAULT;
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
        result.append(", barStyle: "); //$NON-NLS-1$
        result.append(barStyle);
        result.append(", toggleStyle: "); //$NON-NLS-1$
        result.append(toggleStyle);
        result.append(", expandedByDefault: "); //$NON-NLS-1$
        result.append(expandedByDefault);
        result.append(')');
        return result.toString();
    }

} // GroupStyleImpl
