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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.StyleUpdater;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Intersection Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getDirectEdit
 * <em>Direct Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getCanEdit
 * <em>Can Edit</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getDefaultForeground
 * <em>Default Foreground</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getForegroundConditionalStyle
 * <em>Foreground Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getDefaultBackground
 * <em>Default Background</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getBackgroundConditionalStyle
 * <em>Background Conditional Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getLineMapping
 * <em>Line Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getColumnMapping
 * <em>Column Mapping</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getLabelExpression
 * <em>Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#isUseDomainClass
 * <em>Use Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getColumnFinderExpression
 * <em>Column Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getLineFinderExpression
 * <em>Line Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getSemanticCandidatesExpression
 * <em>Semantic Candidates Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl#getCreate
 * <em>Create</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntersectionMappingImpl extends TableMappingImpl implements IntersectionMapping {
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
    protected String canEdit = IntersectionMappingImpl.CAN_EDIT_EDEFAULT;

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
     * The cached value of the '{@link #getLineMapping() <em>Line Mapping</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLineMapping()
     * @generated
     * @ordered
     */
    protected EList<LineMapping> lineMapping;

    /**
     * The cached value of the '{@link #getColumnMapping()
     * <em>Column Mapping</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getColumnMapping()
     * @generated
     * @ordered
     */
    protected ColumnMapping columnMapping;

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
    protected String labelExpression = IntersectionMappingImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isUseDomainClass()
     * <em>Use Domain Class</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isUseDomainClass()
     * @generated
     * @ordered
     */
    protected static final boolean USE_DOMAIN_CLASS_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUseDomainClass()
     * <em>Use Domain Class</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isUseDomainClass()
     * @generated
     * @ordered
     */
    protected boolean useDomainClass = IntersectionMappingImpl.USE_DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getColumnFinderExpression()
     * <em>Column Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getColumnFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String COLUMN_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getColumnFinderExpression()
     * <em>Column Finder Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getColumnFinderExpression()
     * @generated
     * @ordered
     */
    protected String columnFinderExpression = IntersectionMappingImpl.COLUMN_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getLineFinderExpression()
     * <em>Line Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLineFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String LINE_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLineFinderExpression()
     * <em>Line Finder Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLineFinderExpression()
     * @generated
     * @ordered
     */
    protected String lineFinderExpression = IntersectionMappingImpl.LINE_FINDER_EXPRESSION_EDEFAULT;

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
    protected String semanticCandidatesExpression = IntersectionMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT;

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
    protected String domainClass = IntersectionMappingImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The default value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = IntersectionMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getCreate() <em>Create</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCreate()
     * @generated
     * @ordered
     */
    protected CreateCellTool create;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected IntersectionMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.INTERSECTION_MAPPING;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT, oldDirectEdit, newDirectEdit);
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
                msgs = ((InternalEObject) directEdit).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT, null, msgs);
            }
            if (newDirectEdit != null) {
                msgs = ((InternalEObject) newDirectEdit).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT, null, msgs);
            }
            msgs = basicSetDirectEdit(newDirectEdit, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT, newDirectEdit, newDirectEdit));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT, oldCanEdit, canEdit));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND, oldDefaultForeground, newDefaultForeground);
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
                msgs = ((InternalEObject) defaultForeground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            if (newDefaultForeground != null) {
                msgs = ((InternalEObject) newDefaultForeground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND, null, msgs);
            }
            msgs = basicSetDefaultForeground(newDefaultForeground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND, newDefaultForeground, newDefaultForeground));
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
                    DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND, oldDefaultBackground, newDefaultBackground);
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
                msgs = ((InternalEObject) defaultBackground).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            if (newDefaultBackground != null) {
                msgs = ((InternalEObject) newDefaultBackground).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND, null, msgs);
            }
            msgs = basicSetDefaultBackground(newDefaultBackground, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND, newDefaultBackground, newDefaultBackground));
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
                    DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE);
        }
        return backgroundConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LineMapping> getLineMapping() {
        if (lineMapping == null) {
            lineMapping = new EObjectResolvingEList<LineMapping>(LineMapping.class, this, DescriptionPackage.INTERSECTION_MAPPING__LINE_MAPPING);
        }
        return lineMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColumnMapping getColumnMapping() {
        if (columnMapping != null && columnMapping.eIsProxy()) {
            InternalEObject oldColumnMapping = (InternalEObject) columnMapping;
            columnMapping = (ColumnMapping) eResolveProxy(oldColumnMapping);
            if (columnMapping != oldColumnMapping) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.INTERSECTION_MAPPING__COLUMN_MAPPING, oldColumnMapping, columnMapping));
                }
            }
        }
        return columnMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColumnMapping basicGetColumnMapping() {
        return columnMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setColumnMapping(ColumnMapping newColumnMapping) {
        ColumnMapping oldColumnMapping = columnMapping;
        columnMapping = newColumnMapping;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__COLUMN_MAPPING, oldColumnMapping, columnMapping));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isUseDomainClass() {
        return useDomainClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setUseDomainClass(boolean newUseDomainClass) {
        boolean oldUseDomainClass = useDomainClass;
        useDomainClass = newUseDomainClass;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__USE_DOMAIN_CLASS, oldUseDomainClass, useDomainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getColumnFinderExpression() {
        return columnFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setColumnFinderExpression(String newColumnFinderExpression) {
        String oldColumnFinderExpression = columnFinderExpression;
        columnFinderExpression = newColumnFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION, oldColumnFinderExpression, columnFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLineFinderExpression() {
        return lineFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLineFinderExpression(String newLineFinderExpression) {
        String oldLineFinderExpression = lineFinderExpression;
        lineFinderExpression = newLineFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION, oldLineFinderExpression, lineFinderExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION, oldSemanticCandidatesExpression,
                    semanticCandidatesExpression));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPreconditionExpression() {
        return preconditionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPreconditionExpression(String newPreconditionExpression) {
        String oldPreconditionExpression = preconditionExpression;
        preconditionExpression = newPreconditionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CreateCellTool getCreate() {
        return create;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCreate(CreateCellTool newCreate, NotificationChain msgs) {
        CreateCellTool oldCreate = create;
        create = newCreate;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__CREATE, oldCreate, newCreate);
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
    public void setCreate(CreateCellTool newCreate) {
        if (newCreate != create) {
            NotificationChain msgs = null;
            if (create != null) {
                msgs = ((InternalEObject) create).eInverseRemove(this, DescriptionPackage.CREATE_CELL_TOOL__MAPPING, CreateCellTool.class, msgs);
            }
            if (newCreate != null) {
                msgs = ((InternalEObject) newCreate).eInverseAdd(this, DescriptionPackage.CREATE_CELL_TOOL__MAPPING, CreateCellTool.class, msgs);
            }
            msgs = basicSetCreate(newCreate, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.INTERSECTION_MAPPING__CREATE, newCreate, newCreate));
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.INTERSECTION_MAPPING__CREATE:
            if (create != null) {
                msgs = ((InternalEObject) create).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.INTERSECTION_MAPPING__CREATE, null, msgs);
            }
            return basicSetCreate((CreateCellTool) otherEnd, msgs);
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
        case DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT:
            return basicSetDirectEdit(null, msgs);
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND:
            return basicSetDefaultForeground(null, msgs);
        case DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getForegroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND:
            return basicSetDefaultBackground(null, msgs);
        case DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return ((InternalEList<?>) getBackgroundConditionalStyle()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.INTERSECTION_MAPPING__CREATE:
            return basicSetCreate(null, msgs);
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
        case DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT:
            return getDirectEdit();
        case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
            return getCanEdit();
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND:
            return getDefaultForeground();
        case DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return getForegroundConditionalStyle();
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND:
            return getDefaultBackground();
        case DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return getBackgroundConditionalStyle();
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_MAPPING:
            return getLineMapping();
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_MAPPING:
            if (resolve) {
                return getColumnMapping();
            }
            return basicGetColumnMapping();
        case DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION:
            return getLabelExpression();
        case DescriptionPackage.INTERSECTION_MAPPING__USE_DOMAIN_CLASS:
            return isUseDomainClass();
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION:
            return getColumnFinderExpression();
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION:
            return getLineFinderExpression();
        case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return getSemanticCandidatesExpression();
        case DescriptionPackage.INTERSECTION_MAPPING__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.INTERSECTION_MAPPING__CREATE:
            return getCreate();
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
        case DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT:
            setDirectEdit((LabelEditTool) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
            setCanEdit((String) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            getForegroundConditionalStyle().addAll((Collection<? extends ForegroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            getBackgroundConditionalStyle().addAll((Collection<? extends BackgroundConditionalStyle>) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_MAPPING:
            getLineMapping().clear();
            getLineMapping().addAll((Collection<? extends LineMapping>) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_MAPPING:
            setColumnMapping((ColumnMapping) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__USE_DOMAIN_CLASS:
            setUseDomainClass((Boolean) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION:
            setColumnFinderExpression((String) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION:
            setLineFinderExpression((String) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression((String) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__CREATE:
            setCreate((CreateCellTool) newValue);
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
        case DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT:
            setDirectEdit((LabelEditTool) null);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
            setCanEdit(IntersectionMappingImpl.CAN_EDIT_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND:
            setDefaultForeground((ForegroundStyleDescription) null);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            getForegroundConditionalStyle().clear();
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND:
            setDefaultBackground((BackgroundStyleDescription) null);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            getBackgroundConditionalStyle().clear();
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_MAPPING:
            getLineMapping().clear();
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_MAPPING:
            setColumnMapping((ColumnMapping) null);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION:
            setLabelExpression(IntersectionMappingImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__USE_DOMAIN_CLASS:
            setUseDomainClass(IntersectionMappingImpl.USE_DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION:
            setColumnFinderExpression(IntersectionMappingImpl.COLUMN_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION:
            setLineFinderExpression(IntersectionMappingImpl.LINE_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            setSemanticCandidatesExpression(IntersectionMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__DOMAIN_CLASS:
            setDomainClass(IntersectionMappingImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION:
            setPreconditionExpression(IntersectionMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.INTERSECTION_MAPPING__CREATE:
            setCreate((CreateCellTool) null);
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
        case DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT:
            return directEdit != null;
        case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
            return IntersectionMappingImpl.CAN_EDIT_EDEFAULT == null ? canEdit != null : !IntersectionMappingImpl.CAN_EDIT_EDEFAULT.equals(canEdit);
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND:
            return defaultForeground != null;
        case DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
            return foregroundConditionalStyle != null && !foregroundConditionalStyle.isEmpty();
        case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND:
            return defaultBackground != null;
        case DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
            return backgroundConditionalStyle != null && !backgroundConditionalStyle.isEmpty();
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_MAPPING:
            return lineMapping != null && !lineMapping.isEmpty();
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_MAPPING:
            return columnMapping != null;
        case DescriptionPackage.INTERSECTION_MAPPING__LABEL_EXPRESSION:
            return IntersectionMappingImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !IntersectionMappingImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case DescriptionPackage.INTERSECTION_MAPPING__USE_DOMAIN_CLASS:
            return useDomainClass != IntersectionMappingImpl.USE_DOMAIN_CLASS_EDEFAULT;
        case DescriptionPackage.INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION:
            return IntersectionMappingImpl.COLUMN_FINDER_EXPRESSION_EDEFAULT == null ? columnFinderExpression != null : !IntersectionMappingImpl.COLUMN_FINDER_EXPRESSION_EDEFAULT
                    .equals(columnFinderExpression);
        case DescriptionPackage.INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION:
            return IntersectionMappingImpl.LINE_FINDER_EXPRESSION_EDEFAULT == null ? lineFinderExpression != null : !IntersectionMappingImpl.LINE_FINDER_EXPRESSION_EDEFAULT
                    .equals(lineFinderExpression);
        case DescriptionPackage.INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION:
            return IntersectionMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT == null ? semanticCandidatesExpression != null : !IntersectionMappingImpl.SEMANTIC_CANDIDATES_EXPRESSION_EDEFAULT
                    .equals(semanticCandidatesExpression);
        case DescriptionPackage.INTERSECTION_MAPPING__DOMAIN_CLASS:
            return IntersectionMappingImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !IntersectionMappingImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.INTERSECTION_MAPPING__PRECONDITION_EXPRESSION:
            return IntersectionMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null : !IntersectionMappingImpl.PRECONDITION_EXPRESSION_EDEFAULT
                    .equals(preconditionExpression);
        case DescriptionPackage.INTERSECTION_MAPPING__CREATE:
            return create != null;
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
            case DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT:
                return DescriptionPackage.CELL_UPDATER__DIRECT_EDIT;
            case DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT:
                return DescriptionPackage.CELL_UPDATER__CAN_EDIT;
            default:
                return -1;
            }
        }
        if (baseClass == StyleUpdater.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND;
            case DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND:
                return DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND;
            case DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE:
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
                return DescriptionPackage.INTERSECTION_MAPPING__DIRECT_EDIT;
            case DescriptionPackage.CELL_UPDATER__CAN_EDIT:
                return DescriptionPackage.INTERSECTION_MAPPING__CAN_EDIT;
            default:
                return -1;
            }
        }
        if (baseClass == StyleUpdater.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_FOREGROUND:
                return DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_FOREGROUND;
            case DescriptionPackage.STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE;
            case DescriptionPackage.STYLE_UPDATER__DEFAULT_BACKGROUND:
                return DescriptionPackage.INTERSECTION_MAPPING__DEFAULT_BACKGROUND;
            case DescriptionPackage.STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE:
                return DescriptionPackage.INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE;
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
        result.append(", labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(", useDomainClass: "); //$NON-NLS-1$
        result.append(useDomainClass);
        result.append(", columnFinderExpression: "); //$NON-NLS-1$
        result.append(columnFinderExpression);
        result.append(", lineFinderExpression: "); //$NON-NLS-1$
        result.append(lineFinderExpression);
        result.append(", semanticCandidatesExpression: "); //$NON-NLS-1$
        result.append(semanticCandidatesExpression);
        result.append(", domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(')');
        return result.toString();
    }

} // IntersectionMappingImpl
