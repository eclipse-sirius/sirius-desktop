/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Feature Column Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getDirectEdit
 * <em>Direct Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getCanEdit
 * <em>Can Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getDefaultForeground
 * <em>Default Foreground</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getForegroundConditionalStyle
 * <em>Foreground Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getDefaultBackground
 * <em>Default Background</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getBackgroundConditionalStyle
 * <em>Background Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getFeatureName
 * <em>Feature Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getLabelExpression
 * <em>Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl#getFeatureParentExpression
 * <em>Feature Parent Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureColumnMappingImpl extends ColumnMappingImpl implements FeatureColumnMapping {
    /**
     * The cached value of the '{@link #getDirectEdit() <em>Direct Edit</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDirectEdit()
     * @generated
     * @ordered
     */
    protected LabelEditTool directEdit;

    /**
     * The default value of the '{@link #getCanEdit() <em>Can Edit</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCanEdit()
     * @generated
     * @ordered
     */
    protected static final String CAN_EDIT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCanEdit() <em>Can Edit</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCanEdit()
     * @generated
     * @ordered
     */
    protected String canEdit = FeatureColumnMappingImpl.CAN_EDIT_EDEFAULT;

    /**
     * The cached value of the '{@link #getDefaultForeground()
     * <em>Default Foreground</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getDefaultForeground()
     * @generated
     * @ordered
     */
    protected ForegroundStyleDescription defaultForeground;

    /**
     * The cached value of the '{@link #getForegroundConditionalStyle()
     * <em>Foreground Conditional Style</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getForegroundConditionalStyle()
     * @generated
     * @ordered
     */
    protected EList<ForegroundConditionalStyle> foregroundConditionalStyle;

    /**
     * The cached value of the '{@link #getDefaultBackground()
     * <em>Default Background</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getDefaultBackground()
     * @generated
     * @ordered
     */
    protected BackgroundStyleDescription defaultBackground;

    /**
     * The cached value of the '{@link #getBackgroundConditionalStyle()
     * <em>Background Conditional Style</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBackgroundConditionalStyle()
     * @generated
     * @ordered
     */
    protected EList<BackgroundConditionalStyle> backgroundConditionalStyle;

    /**
     * The default value of the '{@link #getFeatureName() <em>Feature Name</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected static final String FEATURE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFeatureName() <em>Feature Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected String featureName = FeatureColumnMappingImpl.FEATURE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = FeatureColumnMappingImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFeatureParentExpression()
     * <em>Feature Parent Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getFeatureParentExpression()
     * @generated
     * @ordered
     */
    protected static final String FEATURE_PARENT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFeatureParentExpression()
     * <em>Feature Parent Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getFeatureParentExpression()
     * @generated
     * @ordered
     */
    protected String featureParentExpression = FeatureColumnMappingImpl.FEATURE_PARENT_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FeatureColumnMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.FEATURE_COLUMN_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LabelEditTool getDirectEdit() {
        return directEdit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDirectEdit(LabelEditTool newDirectEdit, NotificationChain msgs) {
        LabelEditTool oldDirectEdit = directEdit;
        directEdit = newDirectEdit;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT, oldDirectEdit, newDirectEdit);
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
    public void setDirectEdit(LabelEditTool newDirectEdit) {
        if (newDirectEdit != directEdit) {
            NotificationChain msgs = null;
            if (directEdit != null) {
                msgs = ((InternalEObject) directEdit).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT, null, msgs);
            }
            if (newDirectEdit != null) {
                msgs = ((InternalEObject) newDirectEdit).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT, null, msgs);
            }
            msgs = basicSetDirectEdit(newDirectEdit, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT, newDirectEdit, newDirectEdit));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getCanEdit() {
        return canEdit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCanEdit(String newCanEdit) {
        String oldCanEdit = canEdit;
        canEdit = newCanEdit;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT, oldCanEdit, canEdit));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ForegroundStyleDescription getDefaultForeground() {
        return defaultForeground;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDefaultForeground(ForegroundStyleDescription newDefaultForeground, NotificationChain msgs) {
        ForegroundStyleDescription oldDefaultForeground = defaultForeground;
        defaultForeground = newDefaultForeground;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND, oldDefaultForeground, newDefaultForeground);
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
    public void setDefaultForeground(ForegroundStyleDescription newDefaultForeground) {
        if (newDefaultForeground != defaultForeground) {
            NotificationChain msgs = null;
            if (defaultForeground != null) {
                msgs = ((InternalEObject) defaultForeground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            if (newDefaultForeground != null) {
                msgs = ((InternalEObject) newDefaultForeground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            msgs = basicSetDefaultForeground(newDefaultForeground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND, newDefaultForeground, newDefaultForeground));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ForegroundConditionalStyle> getForegroundConditionalStyle() {
        if (foregroundConditionalStyle == null) {
            foregroundConditionalStyle = new EObjectContainmentEList<ForegroundConditionalStyle>(ForegroundConditionalStyle.class, this,
                    DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE);
        }
        return foregroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BackgroundStyleDescription getDefaultBackground() {
        return defaultBackground;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDefaultBackground(BackgroundStyleDescription newDefaultBackground, NotificationChain msgs) {
        BackgroundStyleDescription oldDefaultBackground = defaultBackground;
        defaultBackground = newDefaultBackground;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND, oldDefaultBackground, newDefaultBackground);
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
    public void setDefaultBackground(BackgroundStyleDescription newDefaultBackground) {
        if (newDefaultBackground != defaultBackground) {
            NotificationChain msgs = null;
            if (defaultBackground != null) {
                msgs = ((InternalEObject) defaultBackground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            if (newDefaultBackground != null) {
                msgs = ((InternalEObject) newDefaultBackground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            msgs = basicSetDefaultBackground(newDefaultBackground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND, newDefaultBackground, newDefaultBackground));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<BackgroundConditionalStyle> getBackgroundConditionalStyle() {
        if (backgroundConditionalStyle == null) {
            backgroundConditionalStyle = new EObjectContainmentEList<BackgroundConditionalStyle>(BackgroundConditionalStyle.class, this,
                    DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE);
        }
        return backgroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFeatureName(String newFeatureName) {
        String oldFeatureName = featureName;
        featureName = newFeatureName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_NAME, oldFeatureName, featureName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFeatureParentExpression() {
        return featureParentExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFeatureParentExpression(String newFeatureParentExpression) {
        String oldFeatureParentExpression = featureParentExpression;
        featureParentExpression = newFeatureParentExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION, oldFeatureParentExpression, featureParentExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabelComputationExpression() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CreateCellTool getCreateCell() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT:
            return basicSetDirectEdit(null, msgs);
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            return basicSetDefaultForeground(null, msgs);
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getForegroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            return basicSetDefaultBackground(null, msgs);
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getBackgroundConditionalStyle()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT:
            return getDirectEdit();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT:
            return getCanEdit();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            return getDefaultForeground();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return getForegroundConditionalStyle();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            return getDefaultBackground();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return getBackgroundConditionalStyle();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_NAME:
            return getFeatureName();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION:
            return getLabelExpression();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION:
            return getFeatureParentExpression();
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
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT:
            setDirectEdit((LabelEditTool) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT:
            setCanEdit((String) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            getForegroundConditionalStyle().addAll((Collection<? extends ForegroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            getBackgroundConditionalStyle().addAll((Collection<? extends BackgroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_NAME:
            setFeatureName((String) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION:
            setFeatureParentExpression((String) newValue);
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
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT:
            setDirectEdit((LabelEditTool) null);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT:
            setCanEdit(FeatureColumnMappingImpl.CAN_EDIT_EDEFAULT);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) null);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) null);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_NAME:
            setFeatureName(FeatureColumnMappingImpl.FEATURE_NAME_EDEFAULT);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION:
            setLabelExpression(FeatureColumnMappingImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION:
            setFeatureParentExpression(FeatureColumnMappingImpl.FEATURE_PARENT_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT:
            return directEdit != null;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT:
            return FeatureColumnMappingImpl.CAN_EDIT_EDEFAULT == null ? canEdit != null : !FeatureColumnMappingImpl.CAN_EDIT_EDEFAULT.equals(canEdit);
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            return defaultForeground != null;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return foregroundConditionalStyle != null && !foregroundConditionalStyle.isEmpty();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            return defaultBackground != null;
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return backgroundConditionalStyle != null && !backgroundConditionalStyle.isEmpty();
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_NAME:
            return FeatureColumnMappingImpl.FEATURE_NAME_EDEFAULT == null ? featureName != null : !FeatureColumnMappingImpl.FEATURE_NAME_EDEFAULT.equals(featureName);
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION:
            return FeatureColumnMappingImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !FeatureColumnMappingImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case DescriptionPackage.FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION:
            return FeatureColumnMappingImpl.FEATURE_PARENT_EXPRESSION_EDEFAULT == null ? featureParentExpression != null : !FeatureColumnMappingImpl.FEATURE_PARENT_EXPRESSION_EDEFAULT
                    .equals(featureParentExpression);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == CellUpdater.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT:
                return DescriptionPackage.CELL_UPDATER__DIRECT_EDIT;
            case DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT:
                return DescriptionPackage.CELL_UPDATER__CAN_EDIT;
            default:
                return -1;
            }
        }
        if (baseClass == StyleUpdater.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND;
            case DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND;
            case DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == CellUpdater.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.CELL_UPDATER__DIRECT_EDIT:
                return DescriptionPackage.FEATURE_COLUMN_MAPPING__DIRECT_EDIT;
            case DescriptionPackage.CELL_UPDATER__CAN_EDIT:
                return DescriptionPackage.FEATURE_COLUMN_MAPPING__CAN_EDIT;
            default:
                return -1;
            }
        }
        if (baseClass == StyleUpdater.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
                return DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND;
            case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
                return DescriptionPackage.FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND;
            case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (canEdit: "); //$NON-NLS-1$
        result.append(canEdit);
        result.append(", featureName: "); //$NON-NLS-1$
        result.append(featureName);
        result.append(", labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(", featureParentExpression: "); //$NON-NLS-1$
        result.append(featureParentExpression);
        result.append(')');
        return result.toString();
    }

} // FeatureColumnMappingImpl
