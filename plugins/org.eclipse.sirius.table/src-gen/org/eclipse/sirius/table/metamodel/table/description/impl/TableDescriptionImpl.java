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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Table Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getEndUserDocumentation
 * <em>End User Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getTitleExpression
 * <em>Title Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#isInitialisation
 * <em>Initialisation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getMetamodel
 * <em>Metamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#isShowOnStartup
 * <em>Show On Startup</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getOwnedRepresentationCreationDescriptions
 * <em>Owned Representation Creation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getReusedRepresentationCreationDescriptions
 * <em>Reused Representation Creation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getAllRepresentationCreationDescriptions
 * <em>All Representation Creation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getOwnedRepresentationNavigationDescriptions
 * <em>Owned Representation Navigation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getReusedRepresentationNavigationDescriptions
 * <em>Reused Representation Navigation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getAllRepresentationNavigationDescriptions
 * <em>All Representation Navigation Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getOwnedLineMappings
 * <em>Owned Line Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getReusedLineMappings
 * <em>Reused Line Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getAllLineMappings
 * <em>All Line Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getOwnedCreateLine
 * <em>Owned Create Line</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getReusedCreateLine
 * <em>Reused Create Line</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getAllCreateLine
 * <em>All Create Line</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getInitialHeaderColumnWidth
 * <em>Initial Header Column Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl#getImportedElements
 * <em>Imported Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TableDescriptionImpl extends DocumentedElementImpl implements TableDescription {
    /**
     * The default value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected static final String END_USER_DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getEndUserDocumentation()
     * <em>End User Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getEndUserDocumentation()
     * @generated
     * @ordered
     */
    protected String endUserDocumentation = TableDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = TableDescriptionImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = TableDescriptionImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getTitleExpression()
     * <em>Title Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getTitleExpression()
     * <em>Title Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTitleExpression()
     * @generated
     * @ordered
     */
    protected String titleExpression = TableDescriptionImpl.TITLE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isInitialisation()
     * <em>Initialisation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isInitialisation()
     * @generated
     * @ordered
     */
    protected static final boolean INITIALISATION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInitialisation()
     * <em>Initialisation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isInitialisation()
     * @generated
     * @ordered
     */
    protected boolean initialisation = TableDescriptionImpl.INITIALISATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getMetamodel() <em>Metamodel</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMetamodel()
     * @generated
     * @ordered
     */
    protected EList<EPackage> metamodel;

    /**
     * The default value of the '{@link #isShowOnStartup()
     * <em>Show On Startup</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isShowOnStartup()
     * @generated
     * @ordered
     */
    protected static final boolean SHOW_ON_STARTUP_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isShowOnStartup()
     * <em>Show On Startup</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isShowOnStartup()
     * @generated
     * @ordered
     */
    protected boolean showOnStartup = TableDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT;

    /**
     * The default value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getPreconditionExpression()
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPreconditionExpression()
     * @generated
     * @ordered
     */
    protected String preconditionExpression = TableDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT;

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
    protected String domainClass = TableDescriptionImpl.DOMAIN_CLASS_EDEFAULT;

    /**
     * The cached value of the '
     * {@link #getOwnedRepresentationCreationDescriptions()
     * <em>Owned Representation Creation Descriptions</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedRepresentationCreationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationCreationDescription> ownedRepresentationCreationDescriptions;

    /**
     * The cached value of the '
     * {@link #getReusedRepresentationCreationDescriptions()
     * <em>Reused Representation Creation Descriptions</em>}' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReusedRepresentationCreationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationCreationDescription> reusedRepresentationCreationDescriptions;

    /**
     * The cached value of the '
     * {@link #getOwnedRepresentationNavigationDescriptions()
     * <em>Owned Representation Navigation Descriptions</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedRepresentationNavigationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationNavigationDescription> ownedRepresentationNavigationDescriptions;

    /**
     * The cached value of the '
     * {@link #getReusedRepresentationNavigationDescriptions()
     * <em>Reused Representation Navigation Descriptions</em>}' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReusedRepresentationNavigationDescriptions()
     * @generated
     * @ordered
     */
    protected EList<RepresentationNavigationDescription> reusedRepresentationNavigationDescriptions;

    /**
     * The cached value of the '{@link #getOwnedLineMappings()
     * <em>Owned Line Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedLineMappings()
     * @generated
     * @ordered
     */
    protected EList<LineMapping> ownedLineMappings;

    /**
     * The cached value of the '{@link #getReusedLineMappings()
     * <em>Reused Line Mappings</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getReusedLineMappings()
     * @generated
     * @ordered
     */
    protected EList<LineMapping> reusedLineMappings;

    /**
     * The cached value of the '{@link #getOwnedCreateLine()
     * <em>Owned Create Line</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedCreateLine()
     * @generated
     * @ordered
     */
    protected EList<CreateLineTool> ownedCreateLine;

    /**
     * The cached value of the '{@link #getReusedCreateLine()
     * <em>Reused Create Line</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getReusedCreateLine()
     * @generated
     * @ordered
     */
    protected EList<CreateLineTool> reusedCreateLine;

    /**
     * The default value of the '{@link #getInitialHeaderColumnWidth()
     * <em>Initial Header Column Width</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getInitialHeaderColumnWidth()
     * @generated
     * @ordered
     */
    protected static final int INITIAL_HEADER_COLUMN_WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getInitialHeaderColumnWidth()
     * <em>Initial Header Column Width</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getInitialHeaderColumnWidth()
     * @generated
     * @ordered
     */
    protected int initialHeaderColumnWidth = TableDescriptionImpl.INITIAL_HEADER_COLUMN_WIDTH_EDEFAULT;

    /**
     * The cached value of the '{@link #getImportedElements()
     * <em>Imported Elements</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getImportedElements()
     * @generated
     * @ordered
     */
    protected EList<EObject> importedElements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TableDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TABLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTitleExpression() {
        return titleExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTitleExpression(String newTitleExpression) {
        String oldTitleExpression = titleExpression;
        titleExpression = newTitleExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION, oldTitleExpression, titleExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isInitialisation() {
        return initialisation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInitialisation(boolean newInitialisation) {
        boolean oldInitialisation = initialisation;
        initialisation = newInitialisation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION, oldInitialisation, initialisation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EPackage> getMetamodel() {
        if (metamodel == null) {
            metamodel = new EObjectResolvingEList<EPackage>(EPackage.class, this, DescriptionPackage.TABLE_DESCRIPTION__METAMODEL);
        }
        return metamodel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isShowOnStartup() {
        return showOnStartup;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setShowOnStartup(boolean newShowOnStartup) {
        boolean oldShowOnStartup = showOnStartup;
        showOnStartup = newShowOnStartup;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP, oldShowOnStartup, showOnStartup));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getEndUserDocumentation() {
        return endUserDocumentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setEndUserDocumentation(String newEndUserDocumentation) {
        String oldEndUserDocumentation = endUserDocumentation;
        endUserDocumentation = newEndUserDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION, oldEndUserDocumentation, endUserDocumentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS, oldDomainClass, domainClass));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationCreationDescription> getOwnedRepresentationCreationDescriptions() {
        if (ownedRepresentationCreationDescriptions == null) {
            ownedRepresentationCreationDescriptions = new EObjectContainmentEList<RepresentationCreationDescription>(RepresentationCreationDescription.class, this,
                    DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS);
        }
        return ownedRepresentationCreationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationCreationDescription> getReusedRepresentationCreationDescriptions() {
        if (reusedRepresentationCreationDescriptions == null) {
            reusedRepresentationCreationDescriptions = new EObjectResolvingEList<RepresentationCreationDescription>(RepresentationCreationDescription.class, this,
                    DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS);
        }
        return reusedRepresentationCreationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationCreationDescription> getAllRepresentationCreationDescriptions() {
        // TODO: implement this method to return the 'All Representation
        // Creation Descriptions' reference list
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
    public EList<RepresentationNavigationDescription> getOwnedRepresentationNavigationDescriptions() {
        if (ownedRepresentationNavigationDescriptions == null) {
            ownedRepresentationNavigationDescriptions = new EObjectContainmentEList<RepresentationNavigationDescription>(RepresentationNavigationDescription.class, this,
                    DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        }
        return ownedRepresentationNavigationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationNavigationDescription> getReusedRepresentationNavigationDescriptions() {
        if (reusedRepresentationNavigationDescriptions == null) {
            reusedRepresentationNavigationDescriptions = new EObjectResolvingEList<RepresentationNavigationDescription>(RepresentationNavigationDescription.class, this,
                    DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS);
        }
        return reusedRepresentationNavigationDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RepresentationNavigationDescription> getAllRepresentationNavigationDescriptions() {
        // TODO: implement this method to return the 'All Representation
        // Navigation Descriptions' reference list
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
    public EList<LineMapping> getOwnedLineMappings() {
        if (ownedLineMappings == null) {
            ownedLineMappings = new EObjectContainmentEList<LineMapping>(LineMapping.class, this, DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS);
        }
        return ownedLineMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LineMapping> getReusedLineMappings() {
        if (reusedLineMappings == null) {
            reusedLineMappings = new EObjectResolvingEList<LineMapping>(LineMapping.class, this, DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS);
        }
        return reusedLineMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<LineMapping> getAllLineMappings() {
        // TODO: implement this method to return the 'All Line Mappings'
        // reference list
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
    public EList<CreateLineTool> getOwnedCreateLine() {
        if (ownedCreateLine == null) {
            ownedCreateLine = new EObjectContainmentEList<CreateLineTool>(CreateLineTool.class, this, DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE);
        }
        return ownedCreateLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<CreateLineTool> getReusedCreateLine() {
        if (reusedCreateLine == null) {
            reusedCreateLine = new EObjectResolvingEList<CreateLineTool>(CreateLineTool.class, this, DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE);
        }
        return reusedCreateLine;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<CreateLineTool> getAllCreateLine() {
        // TODO: implement this method to return the 'All Create Line' reference
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
    public int getInitialHeaderColumnWidth() {
        return initialHeaderColumnWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInitialHeaderColumnWidth(int newInitialHeaderColumnWidth) {
        int oldInitialHeaderColumnWidth = initialHeaderColumnWidth;
        initialHeaderColumnWidth = newInitialHeaderColumnWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH, oldInitialHeaderColumnWidth, initialHeaderColumnWidth));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EObject> getImportedElements() {
        if (importedElements == null) {
            importedElements = new EObjectContainmentEList<EObject>(EObject.class, this, DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS);
        }
        return importedElements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return ((InternalEList<?>) getOwnedRepresentationCreationDescriptions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return ((InternalEList<?>) getOwnedRepresentationNavigationDescriptions()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS:
            return ((InternalEList<?>) getOwnedLineMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE:
            return ((InternalEList<?>) getOwnedCreateLine()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS:
            return ((InternalEList<?>) getImportedElements()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION:
            return getEndUserDocumentation();
        case DescriptionPackage.TABLE_DESCRIPTION__NAME:
            return getName();
        case DescriptionPackage.TABLE_DESCRIPTION__LABEL:
            return getLabel();
        case DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION:
            return getTitleExpression();
        case DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION:
            return isInitialisation();
        case DescriptionPackage.TABLE_DESCRIPTION__METAMODEL:
            return getMetamodel();
        case DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP:
            return isShowOnStartup();
        case DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return getOwnedRepresentationCreationDescriptions();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return getReusedRepresentationCreationDescriptions();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS:
            return getAllRepresentationCreationDescriptions();
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return getOwnedRepresentationNavigationDescriptions();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return getReusedRepresentationNavigationDescriptions();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return getAllRepresentationNavigationDescriptions();
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS:
            return getOwnedLineMappings();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS:
            return getReusedLineMappings();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_LINE_MAPPINGS:
            return getAllLineMappings();
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE:
            return getOwnedCreateLine();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE:
            return getReusedCreateLine();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_CREATE_LINE:
            return getAllCreateLine();
        case DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH:
            return getInitialHeaderColumnWidth();
        case DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS:
            return getImportedElements();
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
        case DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION:
            setEndUserDocumentation((String) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression((String) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION:
            setInitialisation((Boolean) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            getMetamodel().addAll((Collection<? extends EPackage>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP:
            setShowOnStartup((Boolean) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            getOwnedRepresentationCreationDescriptions().clear();
            getOwnedRepresentationCreationDescriptions().addAll((Collection<? extends RepresentationCreationDescription>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS:
            getReusedRepresentationCreationDescriptions().clear();
            getReusedRepresentationCreationDescriptions().addAll((Collection<? extends RepresentationCreationDescription>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            getOwnedRepresentationNavigationDescriptions().clear();
            getOwnedRepresentationNavigationDescriptions().addAll((Collection<? extends RepresentationNavigationDescription>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            getReusedRepresentationNavigationDescriptions().clear();
            getReusedRepresentationNavigationDescriptions().addAll((Collection<? extends RepresentationNavigationDescription>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS:
            getOwnedLineMappings().clear();
            getOwnedLineMappings().addAll((Collection<? extends LineMapping>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS:
            getReusedLineMappings().clear();
            getReusedLineMappings().addAll((Collection<? extends LineMapping>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE:
            getOwnedCreateLine().clear();
            getOwnedCreateLine().addAll((Collection<? extends CreateLineTool>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE:
            getReusedCreateLine().clear();
            getReusedCreateLine().addAll((Collection<? extends CreateLineTool>) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH:
            setInitialHeaderColumnWidth((Integer) newValue);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS:
            getImportedElements().clear();
            getImportedElements().addAll((Collection<? extends EObject>) newValue);
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
        case DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION:
            setEndUserDocumentation(TableDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__NAME:
            setName(TableDescriptionImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__LABEL:
            setLabel(TableDescriptionImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression(TableDescriptionImpl.TITLE_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION:
            setInitialisation(TableDescriptionImpl.INITIALISATION_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP:
            setShowOnStartup(TableDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(TableDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass(TableDescriptionImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            getOwnedRepresentationCreationDescriptions().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS:
            getReusedRepresentationCreationDescriptions().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            getOwnedRepresentationNavigationDescriptions().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            getReusedRepresentationNavigationDescriptions().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS:
            getOwnedLineMappings().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS:
            getReusedLineMappings().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE:
            getOwnedCreateLine().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE:
            getReusedCreateLine().clear();
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH:
            setInitialHeaderColumnWidth(TableDescriptionImpl.INITIAL_HEADER_COLUMN_WIDTH_EDEFAULT);
            return;
        case DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS:
            getImportedElements().clear();
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
        case DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION:
            return TableDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT == null ? endUserDocumentation != null : !TableDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT.equals(endUserDocumentation);
        case DescriptionPackage.TABLE_DESCRIPTION__NAME:
            return TableDescriptionImpl.NAME_EDEFAULT == null ? name != null : !TableDescriptionImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.TABLE_DESCRIPTION__LABEL:
            return TableDescriptionImpl.LABEL_EDEFAULT == null ? label != null : !TableDescriptionImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION:
            return TableDescriptionImpl.TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null : !TableDescriptionImpl.TITLE_EXPRESSION_EDEFAULT.equals(titleExpression);
        case DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION:
            return initialisation != TableDescriptionImpl.INITIALISATION_EDEFAULT;
        case DescriptionPackage.TABLE_DESCRIPTION__METAMODEL:
            return metamodel != null && !metamodel.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP:
            return showOnStartup != TableDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT;
        case DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION:
            return TableDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null : !TableDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT
                    .equals(preconditionExpression);
        case DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS:
            return TableDescriptionImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !TableDescriptionImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return ownedRepresentationCreationDescriptions != null && !ownedRepresentationCreationDescriptions.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS:
            return reusedRepresentationCreationDescriptions != null && !reusedRepresentationCreationDescriptions.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS:
            return !getAllRepresentationCreationDescriptions().isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return ownedRepresentationNavigationDescriptions != null && !ownedRepresentationNavigationDescriptions.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return reusedRepresentationNavigationDescriptions != null && !reusedRepresentationNavigationDescriptions.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS:
            return !getAllRepresentationNavigationDescriptions().isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS:
            return ownedLineMappings != null && !ownedLineMappings.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS:
            return reusedLineMappings != null && !reusedLineMappings.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_LINE_MAPPINGS:
            return !getAllLineMappings().isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE:
            return ownedCreateLine != null && !ownedCreateLine.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE:
            return reusedCreateLine != null && !reusedCreateLine.isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__ALL_CREATE_LINE:
            return !getAllCreateLine().isEmpty();
        case DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH:
            return initialHeaderColumnWidth != TableDescriptionImpl.INITIAL_HEADER_COLUMN_WIDTH_EDEFAULT;
        case DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS:
            return importedElements != null && !importedElements.isEmpty();
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
        if (baseClass == EndUserDocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TABLE_DESCRIPTION__NAME:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case DescriptionPackage.TABLE_DESCRIPTION__LABEL:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
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
        if (baseClass == EndUserDocumentedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
                return DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return DescriptionPackage.TABLE_DESCRIPTION__NAME;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return DescriptionPackage.TABLE_DESCRIPTION__LABEL;
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
        result.append(" (endUserDocumentation: "); //$NON-NLS-1$
        result.append(endUserDocumentation);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", titleExpression: "); //$NON-NLS-1$
        result.append(titleExpression);
        result.append(", initialisation: "); //$NON-NLS-1$
        result.append(initialisation);
        result.append(", showOnStartup: "); //$NON-NLS-1$
        result.append(showOnStartup);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(", domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", initialHeaderColumnWidth: "); //$NON-NLS-1$
        result.append(initialHeaderColumnWidth);
        result.append(')');
        return result.toString();
    }

} // TableDescriptionImpl
