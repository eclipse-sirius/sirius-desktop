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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Element Column Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getDefaultForeground
 * <em>Default Foreground</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getForegroundConditionalStyle
 * <em>Foreground Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getDefaultBackground
 * <em>Default Background</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getBackgroundConditionalStyle
 * <em>Background Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getCreate
 * <em>Create</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl#getDelete
 * <em>Delete</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementColumnMappingImpl extends ColumnMappingImpl implements ElementColumnMapping {
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
     * The default value of the '{@link #getDomainClass() <em>Domain Class</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected static final String DOMAIN_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDomainClass() <em>Domain Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDomainClass()
     * @generated
     * @ordered
     */
    protected String domainClass = ElementColumnMappingImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getSemanticCandidatesExpression()
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSemanticCandidatesExpression()
     * @generated
     * @ordered
     */
    protected static final String SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSemanticCandidatesExpression()
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSemanticCandidatesExpression()
     * @generated
     * @ordered
     */
    protected String semanticCandidatesExpression = ElementColumnMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getCreate() <em>Create</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCreate()
     * @generated
     * @ordered
     */
    protected EList<CreateColumnTool> create;

    /**
     * The cached value of the '{@link #getDelete() <em>Delete</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDelete()
     * @generated
     * @ordered
     */
    protected DeleteColumnTool delete;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ElementColumnMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.ELEMENT_COLUMN_MAPPING;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND, oldDefaultForeground, newDefaultForeground);
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
                msgs = ((InternalEObject) defaultForeground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            if (newDefaultForeground != null) {
                msgs = ((InternalEObject) newDefaultForeground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            msgs = basicSetDefaultForeground(newDefaultForeground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND, newDefaultForeground, newDefaultForeground));
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
                    DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND, oldDefaultBackground, newDefaultBackground);
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
                msgs = ((InternalEObject) defaultBackground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            if (newDefaultBackground != null) {
                msgs = ((InternalEObject) newDefaultBackground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            msgs = basicSetDefaultBackground(newDefaultBackground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND, newDefaultBackground, newDefaultBackground));
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
                    DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE);
        }
        return backgroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDomainClass() {
        return domainClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDomainClass(String newDomainClass) {
        String oldDomainClass = domainClass;
        domainClass = newDomainClass;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSemanticCandidatesExpression() {
        return semanticCandidatesExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSemanticCandidatesExpression(String newSemanticCandidatesExpression) {
        String oldSemanticCandidatesExpression = semanticCandidatesExpression;
        semanticCandidatesExpression = newSemanticCandidatesExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, oldSemanticCandidatesExpression,
                    semanticCandidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<CreateColumnTool> getCreate() {
        if (create == null) {
            create = new EObjectContainmentWithInverseEList<CreateColumnTool>(CreateColumnTool.class, this, DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE,
                    DescriptionPackage.CREATE_COLUMN_TOOL__MAPPING);
        }
        return create;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DeleteColumnTool getDelete() {
        return delete;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDelete(DeleteColumnTool newDelete, NotificationChain msgs) {
        DeleteColumnTool oldDelete = delete;
        delete = newDelete;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE, oldDelete, newDelete);
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
    public void setDelete(DeleteColumnTool newDelete) {
        if (newDelete != delete) {
            NotificationChain msgs = null;
            if (delete != null) {
                msgs = ((InternalEObject) delete).eInverseRemove(this, DescriptionPackage.DELETE_COLUMN_TOOL__MAPPING, DeleteColumnTool.class, msgs);
            }
            if (newDelete != null) {
                msgs = ((InternalEObject) newDelete).eInverseAdd(this, DescriptionPackage.DELETE_COLUMN_TOOL__MAPPING, DeleteColumnTool.class, msgs);
            }
            msgs = basicSetDelete(newDelete, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE, newDelete, newDelete));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getCreate()).basicAdd(otherEnd, msgs);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE:
            if (delete != null) {
                msgs = ((InternalEObject) delete).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE, null, msgs);
            }
            return basicSetDelete((DeleteColumnTool) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            return basicSetDefaultForeground(null, msgs);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getForegroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            return basicSetDefaultBackground(null, msgs);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getBackgroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE:
            return ((InternalEList<?>) getCreate()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE:
            return basicSetDelete(null, msgs);
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
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            return getDefaultForeground();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return getForegroundConditionalStyle();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            return getDefaultBackground();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return getBackgroundConditionalStyle();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return getSemanticCandidatesExpression();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE:
            return getCreate();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE:
            return getDelete();
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
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) newValue);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            getForegroundConditionalStyle().addAll((Collection<? extends ForegroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) newValue);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            getBackgroundConditionalStyle().addAll((Collection<? extends BackgroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression((String) newValue);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE:
            getCreate().clear();
            getCreate().addAll((Collection<? extends CreateColumnTool>) newValue);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE:
            setDelete((DeleteColumnTool) newValue);
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
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) null);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) null);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS:
            setDomainClass(ElementColumnMappingImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression(ElementColumnMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE:
            getCreate().clear();
            return;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE:
            setDelete((DeleteColumnTool) null);
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
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND:
            return defaultForeground != null;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return foregroundConditionalStyle != null && !foregroundConditionalStyle.isEmpty();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND:
            return defaultBackground != null;
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return backgroundConditionalStyle != null && !backgroundConditionalStyle.isEmpty();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS:
            return ElementColumnMappingImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !ElementColumnMappingImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return ElementColumnMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT == null ? semanticCandidatesExpression != null : !ElementColumnMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT
                    .equals(semanticCandidatesExpression);
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__CREATE:
            return create != null && !create.isEmpty();
        case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DELETE:
            return delete != null;
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
        if (baseClass == StyleUpdater.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND;
            case DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND;
            case DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
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
        if (baseClass == StyleUpdater.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
                return DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND;
            case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
                return DescriptionPackage.ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND;
            case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE;
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
        result.append(" (domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", semanticCandidatesExpression: "); //$NON-NLS-1$
        result.append(semanticCandidatesExpression);
        result.append(')');
        return result.toString();
    }

} // ElementColumnMappingImpl
