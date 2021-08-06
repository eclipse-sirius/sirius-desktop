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
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.SytemColorsPalette;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Environment</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.EnvironmentImpl#getSystemColors <em>System Colors</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.EnvironmentImpl#getDefaultTools <em>Default Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.EnvironmentImpl#getLabelBorderStyles <em>Label Border
 * Styles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnvironmentImpl extends MinimalEObjectImpl.Container implements Environment {
    /**
     * The cached value of the '{@link #getSystemColors() <em>System Colors</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSystemColors()
     * @generated
     * @ordered
     */
    protected SytemColorsPalette systemColors;

    /**
     * The cached value of the '{@link #getDefaultTools() <em>Default Tools</em> }' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultTools()
     * @generated
     * @ordered
     */
    protected EList<ToolEntry> defaultTools;

    /**
     * The cached value of the '{@link #getLabelBorderStyles() <em>Label Border Styles</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLabelBorderStyles()
     * @generated
     * @ordered
     */
    protected LabelBorderStyles labelBorderStyles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EnvironmentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.ENVIRONMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SytemColorsPalette getSystemColors() {
        if (systemColors != null && systemColors.eIsProxy()) {
            InternalEObject oldSystemColors = (InternalEObject) systemColors;
            systemColors = (SytemColorsPalette) eResolveProxy(oldSystemColors);
            if (systemColors != oldSystemColors) {
                InternalEObject newSystemColors = (InternalEObject) systemColors;
                NotificationChain msgs = oldSystemColors.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS, null, null);
                if (newSystemColors.eInternalContainer() == null) {
                    msgs = newSystemColors.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS, oldSystemColors, systemColors));
                }
            }
        }
        return systemColors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SytemColorsPalette basicGetSystemColors() {
        return systemColors;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetSystemColors(SytemColorsPalette newSystemColors, NotificationChain msgs) {
        SytemColorsPalette oldSystemColors = systemColors;
        systemColors = newSystemColors;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS, oldSystemColors, newSystemColors);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSystemColors(SytemColorsPalette newSystemColors) {
        if (newSystemColors != systemColors) {
            NotificationChain msgs = null;
            if (systemColors != null) {
                msgs = ((InternalEObject) systemColors).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS, null, msgs);
            }
            if (newSystemColors != null) {
                msgs = ((InternalEObject) newSystemColors).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS, null, msgs);
            }
            msgs = basicSetSystemColors(newSystemColors, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS, newSystemColors, newSystemColors));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ToolEntry> getDefaultTools() {
        if (defaultTools == null) {
            defaultTools = new EObjectContainmentEList.Resolving<ToolEntry>(ToolEntry.class, this, DescriptionPackage.ENVIRONMENT__DEFAULT_TOOLS);
        }
        return defaultTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelBorderStyles getLabelBorderStyles() {
        if (labelBorderStyles != null && labelBorderStyles.eIsProxy()) {
            InternalEObject oldLabelBorderStyles = (InternalEObject) labelBorderStyles;
            labelBorderStyles = (LabelBorderStyles) eResolveProxy(oldLabelBorderStyles);
            if (labelBorderStyles != oldLabelBorderStyles) {
                InternalEObject newLabelBorderStyles = (InternalEObject) labelBorderStyles;
                NotificationChain msgs = oldLabelBorderStyles.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES, null, null);
                if (newLabelBorderStyles.eInternalContainer() == null) {
                    msgs = newLabelBorderStyles.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES, oldLabelBorderStyles, labelBorderStyles));
                }
            }
        }
        return labelBorderStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public LabelBorderStyles basicGetLabelBorderStyles() {
        return labelBorderStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLabelBorderStyles(LabelBorderStyles newLabelBorderStyles, NotificationChain msgs) {
        LabelBorderStyles oldLabelBorderStyles = labelBorderStyles;
        labelBorderStyles = newLabelBorderStyles;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES, oldLabelBorderStyles, newLabelBorderStyles);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelBorderStyles(LabelBorderStyles newLabelBorderStyles) {
        if (newLabelBorderStyles != labelBorderStyles) {
            NotificationChain msgs = null;
            if (labelBorderStyles != null) {
                msgs = ((InternalEObject) labelBorderStyles).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES, null, msgs);
            }
            if (newLabelBorderStyles != null) {
                msgs = ((InternalEObject) newLabelBorderStyles).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES, null, msgs);
            }
            msgs = basicSetLabelBorderStyles(newLabelBorderStyles, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES, newLabelBorderStyles, newLabelBorderStyles));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS:
            return basicSetSystemColors(null, msgs);
        case DescriptionPackage.ENVIRONMENT__DEFAULT_TOOLS:
            return ((InternalEList<?>) getDefaultTools()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES:
            return basicSetLabelBorderStyles(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS:
            if (resolve) {
                return getSystemColors();
            }
            return basicGetSystemColors();
        case DescriptionPackage.ENVIRONMENT__DEFAULT_TOOLS:
            return getDefaultTools();
        case DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES:
            if (resolve) {
                return getLabelBorderStyles();
            }
            return basicGetLabelBorderStyles();
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
        case DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS:
            setSystemColors((SytemColorsPalette) newValue);
            return;
        case DescriptionPackage.ENVIRONMENT__DEFAULT_TOOLS:
            getDefaultTools().clear();
            getDefaultTools().addAll((Collection<? extends ToolEntry>) newValue);
            return;
        case DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES:
            setLabelBorderStyles((LabelBorderStyles) newValue);
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
        case DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS:
            setSystemColors((SytemColorsPalette) null);
            return;
        case DescriptionPackage.ENVIRONMENT__DEFAULT_TOOLS:
            getDefaultTools().clear();
            return;
        case DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES:
            setLabelBorderStyles((LabelBorderStyles) null);
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
        case DescriptionPackage.ENVIRONMENT__SYSTEM_COLORS:
            return systemColors != null;
        case DescriptionPackage.ENVIRONMENT__DEFAULT_TOOLS:
            return defaultTools != null && !defaultTools.isEmpty();
        case DescriptionPackage.ENVIRONMENT__LABEL_BORDER_STYLES:
            return labelBorderStyles != null;
        }
        return super.eIsSet(featureID);
    }

} // EnvironmentImpl
