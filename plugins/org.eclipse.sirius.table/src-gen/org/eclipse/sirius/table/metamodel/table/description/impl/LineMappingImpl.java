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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Line Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getDefaultForeground
 * <em>Default Foreground</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getForegroundConditionalStyle
 * <em>Foreground Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getDefaultBackground
 * <em>Default Background</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getBackgroundConditionalStyle
 * <em>Background Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getOwnedSubLines
 * <em>Owned Sub Lines</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getReusedSubLines
 * <em>Reused Sub Lines</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getAllSubLines
 * <em>All Sub Lines</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getReusedInMappings
 * <em>Reused In Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getCreate
 * <em>Create</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getDelete
 * <em>Delete</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl#getHeaderLabelExpression
 * <em>Header Label Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LineMappingImpl extends TableMappingImpl implements LineMapping {
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
     * The cached value of the '{@link #getOwnedSubLines()
     * <em>Owned Sub Lines</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedSubLines()
     * @generated
     * @ordered
     */
    protected EList<LineMapping> ownedSubLines;

    /**
     * The cached value of the '{@link #getReusedSubLines()
     * <em>Reused Sub Lines</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getReusedSubLines()
     * @generated
     * @ordered
     */
    protected EList<LineMapping> reusedSubLines;

    /**
     * The cached value of the '{@link #getReusedInMappings()
     * <em>Reused In Mappings</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getReusedInMappings()
     * @generated
     * @ordered
     */
    protected EList<LineMapping> reusedInMappings;

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
    protected String domainClass = LineMappingImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The cached value of the '{@link #getCreate() <em>Create</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCreate()
     * @generated
     * @ordered
     */
    protected EList<CreateLineTool> create;

    /**
     * The cached value of the '{@link #getDelete() <em>Delete</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDelete()
     * @generated
     * @ordered
     */
    protected DeleteLineTool delete;

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
    protected String semanticCandidatesExpression = LineMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getHeaderLabelExpression()
     * <em>Header Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHeaderLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String HEADER_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHeaderLabelExpression()
     * <em>Header Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHeaderLabelExpression()
     * @generated
     * @ordered
     */
    protected String headerLabelExpression = LineMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected LineMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.LINE_MAPPING;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND, oldDefaultForeground, newDefaultForeground);
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
                msgs = ((InternalEObject) defaultForeground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            if (newDefaultForeground != null) {
                msgs = ((InternalEObject) newDefaultForeground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            msgs = basicSetDefaultForeground(newDefaultForeground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND, newDefaultForeground, newDefaultForeground));
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
            foregroundConditionalStyle = new EObjectContainmentEList<ForegroundConditionalStyle>(ForegroundConditionalStyle.class, this, DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND, oldDefaultBackground, newDefaultBackground);
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
                msgs = ((InternalEObject) defaultBackground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            if (newDefaultBackground != null) {
                msgs = ((InternalEObject) newDefaultBackground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            msgs = basicSetDefaultBackground(newDefaultBackground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND, newDefaultBackground, newDefaultBackground));
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
            backgroundConditionalStyle = new EObjectContainmentEList<BackgroundConditionalStyle>(BackgroundConditionalStyle.class, this, DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE);
        }
        return backgroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LineMapping> getOwnedSubLines() {
        if (ownedSubLines == null) {
            ownedSubLines = new EObjectContainmentEList<LineMapping>(LineMapping.class, this, DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES);
        }
        return ownedSubLines;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LineMapping> getReusedSubLines() {
        if (reusedSubLines == null) {
            reusedSubLines = new EObjectWithInverseResolvingEList.ManyInverse<LineMapping>(LineMapping.class, this, DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES,
                    DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS);
        }
        return reusedSubLines;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<CreateLineTool> getCreate() {
        if (create == null) {
            create = new EObjectContainmentEList<CreateLineTool>(CreateLineTool.class, this, DescriptionPackage.LINE_MAPPING__CREATE);
        }
        return create;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DeleteLineTool getDelete() {
        return delete;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetDelete(DeleteLineTool newDelete, NotificationChain msgs) {
        DeleteLineTool oldDelete = delete;
        delete = newDelete;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__DELETE, oldDelete, newDelete);
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
    public void setDelete(DeleteLineTool newDelete) {
        if (newDelete != delete) {
            NotificationChain msgs = null;
            if (delete != null) {
                msgs = ((InternalEObject) delete).eInverseRemove(this, DescriptionPackage.DELETE_LINE_TOOL__MAPPING, DeleteLineTool.class, msgs);
            }
            if (newDelete != null) {
                msgs = ((InternalEObject) newDelete).eInverseAdd(this, DescriptionPackage.DELETE_LINE_TOOL__MAPPING, DeleteLineTool.class, msgs);
            }
            msgs = basicSetDelete(newDelete, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__DELETE, newDelete, newDelete));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, oldSemanticCandidatesExpression, semanticCandidatesExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getHeaderLabelExpression() {
        return headerLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setHeaderLabelExpression(String newHeaderLabelExpression) {
        String oldHeaderLabelExpression = headerLabelExpression;
        headerLabelExpression = newHeaderLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION, oldHeaderLabelExpression, headerLabelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LineMapping> getAllSubLines() {
        // TODO: implement this method to return the 'All Sub Lines' reference
        // list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LineMapping> getReusedInMappings() {
        if (reusedInMappings == null) {
            reusedInMappings = new EObjectWithInverseResolvingEList.ManyInverse<LineMapping>(LineMapping.class, this, DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS,
                    DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES);
        }
        return reusedInMappings;
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
        case DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getReusedSubLines()).basicAdd(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getReusedInMappings()).basicAdd(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__DELETE:
            if (delete != null) {
                msgs = ((InternalEObject) delete).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LINE_MAPPING__DELETE, null, msgs);
            }
            return basicSetDelete((DeleteLineTool) otherEnd, msgs);
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
        case DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND:
            return basicSetDefaultForeground(null, msgs);
        case DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getForegroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND:
            return basicSetDefaultBackground(null, msgs);
        case DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getBackgroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES:
            return ((InternalEList<?>) getOwnedSubLines()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES:
            return ((InternalEList<?>) getReusedSubLines()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS:
            return ((InternalEList<?>) getReusedInMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__CREATE:
            return ((InternalEList<?>) getCreate()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LINE_MAPPING__DELETE:
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
        case DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND:
            return getDefaultForeground();
        case DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return getForegroundConditionalStyle();
        case DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND:
            return getDefaultBackground();
        case DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return getBackgroundConditionalStyle();
        case DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES:
            return getOwnedSubLines();
        case DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES:
            return getReusedSubLines();
        case DescriptionPackage.LINE_MAPPING__ALL_SUB_LINES:
            return getAllSubLines();
        case DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS:
            return getReusedInMappings();
        case DescriptionPackage.LINE_MAPPING__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.LINE_MAPPING__CREATE:
            return getCreate();
        case DescriptionPackage.LINE_MAPPING__DELETE:
            return getDelete();
        case DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return getSemanticCandidatesExpression();
        case DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION:
            return getHeaderLabelExpression();
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
        case DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            getForegroundConditionalStyle().addAll((Collection<? extends ForegroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            getBackgroundConditionalStyle().addAll((Collection<? extends BackgroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES:
            getOwnedSubLines().clear();
            getOwnedSubLines().addAll((Collection<? extends LineMapping>) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES:
            getReusedSubLines().clear();
            getReusedSubLines().addAll((Collection<? extends LineMapping>) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS:
            getReusedInMappings().clear();
            getReusedInMappings().addAll((Collection<? extends LineMapping>) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__CREATE:
            getCreate().clear();
            getCreate().addAll((Collection<? extends CreateLineTool>) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__DELETE:
            setDelete((DeleteLineTool) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression((String) newValue);
            return;
        case DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION:
            setHeaderLabelExpression((String) newValue);
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
        case DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) null);
            return;
        case DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            return;
        case DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) null);
            return;
        case DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            return;
        case DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES:
            getOwnedSubLines().clear();
            return;
        case DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES:
            getReusedSubLines().clear();
            return;
        case DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS:
            getReusedInMappings().clear();
            return;
        case DescriptionPackage.LINE_MAPPING__DOMAIN_CLASS:
            setDomainClass(LineMappingImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.LINE_MAPPING__CREATE:
            getCreate().clear();
            return;
        case DescriptionPackage.LINE_MAPPING__DELETE:
            setDelete((DeleteLineTool) null);
            return;
        case DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression(LineMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION:
            setHeaderLabelExpression(LineMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND:
            return defaultForeground != null;
        case DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return foregroundConditionalStyle != null && !foregroundConditionalStyle.isEmpty();
        case DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND:
            return defaultBackground != null;
        case DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return backgroundConditionalStyle != null && !backgroundConditionalStyle.isEmpty();
        case DescriptionPackage.LINE_MAPPING__OWNED_SUB_LINES:
            return ownedSubLines != null && !ownedSubLines.isEmpty();
        case DescriptionPackage.LINE_MAPPING__REUSED_SUB_LINES:
            return reusedSubLines != null && !reusedSubLines.isEmpty();
        case DescriptionPackage.LINE_MAPPING__ALL_SUB_LINES:
            return !getAllSubLines().isEmpty();
        case DescriptionPackage.LINE_MAPPING__REUSED_IN_MAPPINGS:
            return reusedInMappings != null && !reusedInMappings.isEmpty();
        case DescriptionPackage.LINE_MAPPING__DOMAIN_CLASS:
            return LineMappingImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !LineMappingImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.LINE_MAPPING__CREATE:
            return create != null && !create.isEmpty();
        case DescriptionPackage.LINE_MAPPING__DELETE:
            return delete != null;
        case DescriptionPackage.LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return LineMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT == null ? semanticCandidatesExpression != null : !LineMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT
                    .equals(semanticCandidatesExpression);
        case DescriptionPackage.LINE_MAPPING__HEADER_LABEL_EXPRESSION:
            return LineMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT == null ? headerLabelExpression != null : !LineMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT.equals(headerLabelExpression);
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
            case DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND;
            case DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND;
            case DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
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
                return DescriptionPackage.LINE_MAPPING__DEFAULT_FOREGROUND;
            case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
                return DescriptionPackage.LINE_MAPPING__DEFAULT_BACKGROUND;
            case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE;
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
        result.append(", headerLabelExpression: "); //$NON-NLS-1$
        result.append(headerLabelExpression);
        result.append(')');
        return result.toString();
    }

} // LineMappingImpl
