/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.Layout;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.concern.ConcernSet;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Diagram Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getDocumentation
 * <em>Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getEndUserDocumentation
 * <em>End User Documentation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getTitleExpression
 * <em>Title Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#isInitialisation
 * <em>Initialisation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getMetamodel
 * <em>Metamodel</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#isShowOnStartup
 * <em>Show On Startup</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getPasteDescriptions
 * <em>Paste Descriptions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getAllEdgeMappings
 * <em>All Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getAllNodeMappings
 * <em>All Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getAllContainerMappings
 * <em>All Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getValidationSet
 * <em>Validation Set</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getConcerns
 * <em>Concerns</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getAllTools
 * <em>All Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getDomainClass
 * <em>Domain Class</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getPreconditionExpression
 * <em>Precondition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getDefaultConcern
 * <em>Default Concern</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getRootExpression
 * <em>Root Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getInit
 * <em>Init</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getLayout
 * <em>Layout</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getDiagramInitialisation
 * <em>Diagram Initialisation</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getDefaultLayer
 * <em>Default Layer</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getAdditionalLayers
 * <em>Additional Layers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getAllLayers
 * <em>All Layers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getAllActivatedTools
 * <em>All Activated Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getNodeMappings
 * <em>Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getEdgeMappings
 * <em>Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getEdgeMappingImports
 * <em>Edge Mapping Imports</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getContainerMappings
 * <em>Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getReusedMappings
 * <em>Reused Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getToolSection
 * <em>Tool Section</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#getReusedTools
 * <em>Reused Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl#isEnablePopupBars
 * <em>Enable Popup Bars</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramDescriptionImpl extends DragAndDropTargetDescriptionImpl implements DiagramDescription {
    /**
     * The default value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation()
     * <em>Documentation</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = DiagramDescriptionImpl.DOCUMENTATION_EDEFAULT;

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
    protected String endUserDocumentation = DiagramDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT;

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
    protected String name = DiagramDescriptionImpl.NAME_EDEFAULT;

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
    protected String label = DiagramDescriptionImpl.LABEL_EDEFAULT;

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
    protected String titleExpression = DiagramDescriptionImpl.TITLE_EXPRESSION_EDEFAULT;

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
    protected boolean initialisation = DiagramDescriptionImpl.INITIALISATION_EDEFAULT;

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
    protected boolean showOnStartup = DiagramDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT;

    /**
     * The cached value of the '{@link #getPasteDescriptions()
     * <em>Paste Descriptions</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getPasteDescriptions()
     * @generated
     * @ordered
     */
    protected EList<PasteDescription> pasteDescriptions;

    /**
     * The cached value of the '{@link #getFilters() <em>Filters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilters()
     * @generated
     * @ordered
     */
    protected EList<FilterDescription> filters;

    /**
     * The cached value of the '{@link #getValidationSet()
     * <em>Validation Set</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getValidationSet()
     * @generated
     * @ordered
     */
    protected ValidationSet validationSet;

    /**
     * The cached value of the '{@link #getConcerns() <em>Concerns</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConcerns()
     * @generated
     * @ordered
     */
    protected ConcernSet concerns;

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
    protected String domainClass = DiagramDescriptionImpl.DOMAIN_CLASS_EDEFAULT;

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
    protected String preconditionExpression = DiagramDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getDefaultConcern()
     * <em>Default Concern</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getDefaultConcern()
     * @generated
     * @ordered
     */
    protected ConcernDescription defaultConcern;

    /**
     * The default value of the '{@link #getRootExpression()
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRootExpression()
     * @generated
     * @ordered
     */
    protected static final String ROOT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRootExpression()
     * <em>Root Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getRootExpression()
     * @generated
     * @ordered
     */
    protected String rootExpression = DiagramDescriptionImpl.ROOT_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getInit() <em>Init</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInit()
     * @generated
     * @ordered
     */
    protected RepresentationCreationDescription init;

    /**
     * The cached value of the '{@link #getLayout() <em>Layout</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getLayout()
     * @generated
     * @ordered
     */
    protected Layout layout;

    /**
     * The cached value of the '{@link #getDiagramInitialisation()
     * <em>Diagram Initialisation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDiagramInitialisation()
     * @generated
     * @ordered
     */
    protected InitialOperation diagramInitialisation;

    /**
     * The cached value of the '{@link #getDefaultLayer() <em>Default Layer</em>
     * }' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDefaultLayer()
     * @generated
     * @ordered
     */
    protected Layer defaultLayer;

    /**
     * The cached value of the '{@link #getAdditionalLayers()
     * <em>Additional Layers</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAdditionalLayers()
     * @generated
     * @ordered
     */
    protected EList<AdditionalLayer> additionalLayers;

    /**
     * The cached value of the '{@link #getNodeMappings() <em>Node Mappings</em>
     * }' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getNodeMappings()
     * @generated
     * @ordered
     */
    protected EList<NodeMapping> nodeMappings;

    /**
     * The cached value of the '{@link #getEdgeMappings() <em>Edge Mappings</em>
     * }' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getEdgeMappings()
     * @generated
     * @ordered
     */
    protected EList<EdgeMapping> edgeMappings;

    /**
     * The cached value of the '{@link #getEdgeMappingImports()
     * <em>Edge Mapping Imports</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEdgeMappingImports()
     * @generated
     * @ordered
     */
    protected EList<EdgeMappingImport> edgeMappingImports;

    /**
     * The cached value of the '{@link #getContainerMappings()
     * <em>Container Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContainerMappings()
     * @generated
     * @ordered
     */
    protected EList<ContainerMapping> containerMappings;

    /**
     * The cached value of the '{@link #getReusedMappings()
     * <em>Reused Mappings</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getReusedMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> reusedMappings;

    /**
     * The cached value of the '{@link #getToolSection() <em>Tool Section</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getToolSection()
     * @generated
     * @ordered
     */
    protected ToolSection toolSection;

    /**
     * The cached value of the '{@link #getReusedTools() <em>Reused Tools</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getReusedTools()
     * @generated
     * @ordered
     */
    protected EList<AbstractToolDescription> reusedTools;

    /**
     * The default value of the '{@link #isEnablePopupBars()
     * <em>Enable Popup Bars</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isEnablePopupBars()
     * @generated
     * @ordered
     */
    protected static final boolean ENABLE_POPUP_BARS_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isEnablePopupBars()
     * <em>Enable Popup Bars</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isEnablePopupBars()
     * @generated
     * @ordered
     */
    protected boolean enablePopupBars = DiagramDescriptionImpl.ENABLE_POPUP_BARS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DiagramDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DIAGRAM_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION, oldDocumentation, documentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION, oldEndUserDocumentation, endUserDocumentation));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL, oldLabel, label));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION, oldTitleExpression, titleExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION, oldInitialisation, initialisation));
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
            metamodel = new EObjectResolvingEList<EPackage>(EPackage.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL);
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP, oldShowOnStartup, showOnStartup));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PasteDescription> getPasteDescriptions() {
        if (pasteDescriptions == null) {
            pasteDescriptions = new EObjectResolvingEList<PasteDescription>(PasteDescription.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS);
        }
        return pasteDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<FilterDescription> getFilters() {
        if (filters == null) {
            filters = new EObjectContainmentEList.Resolving<FilterDescription>(FilterDescription.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS);
        }
        return filters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeMapping> getAllEdgeMappings() {
        // TODO: implement this method to return the 'All Edge Mappings'
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
    public EList<NodeMapping> getAllNodeMappings() {
        // TODO: implement this method to return the 'All Node Mappings'
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
    public EList<ContainerMapping> getAllContainerMappings() {
        // TODO: implement this method to return the 'All Container Mappings'
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
    public ValidationSet getValidationSet() {
        if (validationSet != null && validationSet.eIsProxy()) {
            InternalEObject oldValidationSet = (InternalEObject) validationSet;
            validationSet = (ValidationSet) eResolveProxy(oldValidationSet);
            if (validationSet != oldValidationSet) {
                InternalEObject newValidationSet = (InternalEObject) validationSet;
                NotificationChain msgs = oldValidationSet.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET, null, null);
                if (newValidationSet.eInternalContainer() == null) {
                    msgs = newValidationSet.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET, oldValidationSet, validationSet));
                }
            }
        }
        return validationSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ValidationSet basicGetValidationSet() {
        return validationSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetValidationSet(ValidationSet newValidationSet, NotificationChain msgs) {
        ValidationSet oldValidationSet = validationSet;
        validationSet = newValidationSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET, oldValidationSet, newValidationSet);
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
    public void setValidationSet(ValidationSet newValidationSet) {
        if (newValidationSet != validationSet) {
            NotificationChain msgs = null;
            if (validationSet != null) {
                msgs = ((InternalEObject) validationSet).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            if (newValidationSet != null) {
                msgs = ((InternalEObject) newValidationSet).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET, null, msgs);
            }
            msgs = basicSetValidationSet(newValidationSet, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET, newValidationSet, newValidationSet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ConcernSet getConcerns() {
        if (concerns != null && concerns.eIsProxy()) {
            InternalEObject oldConcerns = (InternalEObject) concerns;
            concerns = (ConcernSet) eResolveProxy(oldConcerns);
            if (concerns != oldConcerns) {
                InternalEObject newConcerns = (InternalEObject) concerns;
                NotificationChain msgs = oldConcerns.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS, null, null);
                if (newConcerns.eInternalContainer() == null) {
                    msgs = newConcerns.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS, oldConcerns, concerns));
                }
            }
        }
        return concerns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ConcernSet basicGetConcerns() {
        return concerns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetConcerns(ConcernSet newConcerns, NotificationChain msgs) {
        ConcernSet oldConcerns = concerns;
        concerns = newConcerns;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS, oldConcerns, newConcerns);
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
    public void setConcerns(ConcernSet newConcerns) {
        if (newConcerns != concerns) {
            NotificationChain msgs = null;
            if (concerns != null) {
                msgs = ((InternalEObject) concerns).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS, null, msgs);
            }
            if (newConcerns != null) {
                msgs = ((InternalEObject) newConcerns).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS, null, msgs);
            }
            msgs = basicSetConcerns(newConcerns, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS, newConcerns, newConcerns));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractToolDescription> getAllTools() {
        // TODO: implement this method to return the 'All Tools' reference list
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS, oldDomainClass, domainClass));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION, oldPreconditionExpression, preconditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ConcernDescription getDefaultConcern() {
        if (defaultConcern != null && defaultConcern.eIsProxy()) {
            InternalEObject oldDefaultConcern = (InternalEObject) defaultConcern;
            defaultConcern = (ConcernDescription) eResolveProxy(oldDefaultConcern);
            if (defaultConcern != oldDefaultConcern) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN, oldDefaultConcern, defaultConcern));
                }
            }
        }
        return defaultConcern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ConcernDescription basicGetDefaultConcern() {
        return defaultConcern;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDefaultConcern(ConcernDescription newDefaultConcern) {
        ConcernDescription oldDefaultConcern = defaultConcern;
        defaultConcern = newDefaultConcern;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN, oldDefaultConcern, defaultConcern));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getRootExpression() {
        return rootExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRootExpression(String newRootExpression) {
        String oldRootExpression = rootExpression;
        rootExpression = newRootExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION, oldRootExpression, rootExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RepresentationCreationDescription getInit() {
        if (init != null && init.eIsProxy()) {
            InternalEObject oldInit = (InternalEObject) init;
            init = (RepresentationCreationDescription) eResolveProxy(oldInit);
            if (init != oldInit) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__INIT, oldInit, init));
                }
            }
        }
        return init;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RepresentationCreationDescription basicGetInit() {
        return init;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInit(RepresentationCreationDescription newInit) {
        RepresentationCreationDescription oldInit = init;
        init = newInit;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__INIT, oldInit, init));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Layout getLayout() {
        if (layout != null && layout.eIsProxy()) {
            InternalEObject oldLayout = (InternalEObject) layout;
            layout = (Layout) eResolveProxy(oldLayout);
            if (layout != oldLayout) {
                InternalEObject newLayout = (InternalEObject) layout;
                NotificationChain msgs = oldLayout.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT, null, null);
                if (newLayout.eInternalContainer() == null) {
                    msgs = newLayout.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT, oldLayout, layout));
                }
            }
        }
        return layout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Layout basicGetLayout() {
        return layout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetLayout(Layout newLayout, NotificationChain msgs) {
        Layout oldLayout = layout;
        layout = newLayout;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT, oldLayout, newLayout);
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
    public void setLayout(Layout newLayout) {
        if (newLayout != layout) {
            NotificationChain msgs = null;
            if (layout != null) {
                msgs = ((InternalEObject) layout).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT, null, msgs);
            }
            if (newLayout != null) {
                msgs = ((InternalEObject) newLayout).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT, null, msgs);
            }
            msgs = basicSetLayout(newLayout, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT, newLayout, newLayout));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getDiagramInitialisation() {
        if (diagramInitialisation != null && diagramInitialisation.eIsProxy()) {
            InternalEObject oldDiagramInitialisation = (InternalEObject) diagramInitialisation;
            diagramInitialisation = (InitialOperation) eResolveProxy(oldDiagramInitialisation);
            if (diagramInitialisation != oldDiagramInitialisation) {
                InternalEObject newDiagramInitialisation = (InternalEObject) diagramInitialisation;
                NotificationChain msgs = oldDiagramInitialisation.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, null,
                        null);
                if (newDiagramInitialisation.eInternalContainer() == null) {
                    msgs = newDiagramInitialisation.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, oldDiagramInitialisation, diagramInitialisation));
                }
            }
        }
        return diagramInitialisation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InitialOperation basicGetDiagramInitialisation() {
        return diagramInitialisation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDiagramInitialisation(InitialOperation newDiagramInitialisation, NotificationChain msgs) {
        InitialOperation oldDiagramInitialisation = diagramInitialisation;
        diagramInitialisation = newDiagramInitialisation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, oldDiagramInitialisation,
                    newDiagramInitialisation);
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
    public void setDiagramInitialisation(InitialOperation newDiagramInitialisation) {
        if (newDiagramInitialisation != diagramInitialisation) {
            NotificationChain msgs = null;
            if (diagramInitialisation != null) {
                msgs = ((InternalEObject) diagramInitialisation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, null,
                        msgs);
            }
            if (newDiagramInitialisation != null) {
                msgs = ((InternalEObject) newDiagramInitialisation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, null,
                        msgs);
            }
            msgs = basicSetDiagramInitialisation(newDiagramInitialisation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION, newDiagramInitialisation, newDiagramInitialisation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Layer getDefaultLayer() {
        if (defaultLayer != null && defaultLayer.eIsProxy()) {
            InternalEObject oldDefaultLayer = (InternalEObject) defaultLayer;
            defaultLayer = (Layer) eResolveProxy(oldDefaultLayer);
            if (defaultLayer != oldDefaultLayer) {
                InternalEObject newDefaultLayer = (InternalEObject) defaultLayer;
                NotificationChain msgs = oldDefaultLayer.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, null, null);
                if (newDefaultLayer.eInternalContainer() == null) {
                    msgs = newDefaultLayer.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, oldDefaultLayer, defaultLayer));
                }
            }
        }
        return defaultLayer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Layer basicGetDefaultLayer() {
        return defaultLayer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDefaultLayer(Layer newDefaultLayer, NotificationChain msgs) {
        Layer oldDefaultLayer = defaultLayer;
        defaultLayer = newDefaultLayer;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, oldDefaultLayer, newDefaultLayer);
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
    public void setDefaultLayer(Layer newDefaultLayer) {
        if (newDefaultLayer != defaultLayer) {
            NotificationChain msgs = null;
            if (defaultLayer != null) {
                msgs = ((InternalEObject) defaultLayer).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, null, msgs);
            }
            if (newDefaultLayer != null) {
                msgs = ((InternalEObject) newDefaultLayer).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, null, msgs);
            }
            msgs = basicSetDefaultLayer(newDefaultLayer, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER, newDefaultLayer, newDefaultLayer));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AdditionalLayer> getAdditionalLayers() {
        if (additionalLayers == null) {
            additionalLayers = new EObjectContainmentEList.Resolving<AdditionalLayer>(AdditionalLayer.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS);
        }
        return additionalLayers;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Layer> getAllLayers() {
        // TODO: implement this method to return the 'All Layers' reference list
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
    public EList<AbstractToolDescription> getAllActivatedTools() {
        // TODO: implement this method to return the 'All Activated Tools'
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
    public EList<NodeMapping> getNodeMappings() {
        if (nodeMappings == null) {
            nodeMappings = new EObjectContainmentEList.Resolving<NodeMapping>(NodeMapping.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS);
        }
        return nodeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeMapping> getEdgeMappings() {
        if (edgeMappings == null) {
            edgeMappings = new EObjectContainmentEList.Resolving<EdgeMapping>(EdgeMapping.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS);
        }
        return edgeMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EdgeMappingImport> getEdgeMappingImports() {
        if (edgeMappingImports == null) {
            edgeMappingImports = new EObjectContainmentEList.Resolving<EdgeMappingImport>(EdgeMappingImport.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS);
        }
        return edgeMappingImports;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ContainerMapping> getContainerMappings() {
        if (containerMappings == null) {
            containerMappings = new EObjectContainmentEList.Resolving<ContainerMapping>(ContainerMapping.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS);
        }
        return containerMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getReusedMappings() {
        if (reusedMappings == null) {
            reusedMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS);
        }
        return reusedMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolSection getToolSection() {
        if (toolSection != null && toolSection.eIsProxy()) {
            InternalEObject oldToolSection = (InternalEObject) toolSection;
            toolSection = (ToolSection) eResolveProxy(oldToolSection);
            if (toolSection != oldToolSection) {
                InternalEObject newToolSection = (InternalEObject) toolSection;
                NotificationChain msgs = oldToolSection.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION, null, null);
                if (newToolSection.eInternalContainer() == null) {
                    msgs = newToolSection.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION, oldToolSection, toolSection));
                }
            }
        }
        return toolSection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolSection basicGetToolSection() {
        return toolSection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetToolSection(ToolSection newToolSection, NotificationChain msgs) {
        ToolSection oldToolSection = toolSection;
        toolSection = newToolSection;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION, oldToolSection, newToolSection);
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
    public void setToolSection(ToolSection newToolSection) {
        if (newToolSection != toolSection) {
            NotificationChain msgs = null;
            if (toolSection != null) {
                msgs = ((InternalEObject) toolSection).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION, null, msgs);
            }
            if (newToolSection != null) {
                msgs = ((InternalEObject) newToolSection).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION, null, msgs);
            }
            msgs = basicSetToolSection(newToolSection, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION, newToolSection, newToolSection));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractToolDescription> getReusedTools() {
        if (reusedTools == null) {
            reusedTools = new EObjectResolvingEList<AbstractToolDescription>(AbstractToolDescription.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_TOOLS);
        }
        return reusedTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isEnablePopupBars() {
        return enablePopupBars;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEnablePopupBars(boolean newEnablePopupBars) {
        boolean oldEnablePopupBars = enablePopupBars;
        enablePopupBars = newEnablePopupBars;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS, oldEnablePopupBars, enablePopupBars));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DSemanticDiagram createDiagram() {
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
        case DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS:
            return ((InternalEList<?>) getFilters()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET:
            return basicSetValidationSet(null, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS:
            return basicSetConcerns(null, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT:
            return basicSetLayout(null, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION:
            return basicSetDiagramInitialisation(null, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER:
            return basicSetDefaultLayer(null, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS:
            return ((InternalEList<?>) getAdditionalLayers()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS:
            return ((InternalEList<?>) getNodeMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS:
            return ((InternalEList<?>) getEdgeMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS:
            return ((InternalEList<?>) getEdgeMappingImports()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS:
            return ((InternalEList<?>) getContainerMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION:
            return basicSetToolSection(null, msgs);
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
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION:
            return getDocumentation();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION:
            return getEndUserDocumentation();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NAME:
            return getName();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL:
            return getLabel();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION:
            return getTitleExpression();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION:
            return isInitialisation();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL:
            return getMetamodel();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP:
            return isShowOnStartup();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS:
            return getPasteDescriptions();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS:
            return getFilters();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS:
            return getAllEdgeMappings();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS:
            return getAllNodeMappings();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS:
            return getAllContainerMappings();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET:
            if (resolve) {
                return getValidationSet();
            }
            return basicGetValidationSet();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS:
            if (resolve) {
                return getConcerns();
            }
            return basicGetConcerns();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_TOOLS:
            return getAllTools();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS:
            return getDomainClass();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION:
            return getPreconditionExpression();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN:
            if (resolve) {
                return getDefaultConcern();
            }
            return basicGetDefaultConcern();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION:
            return getRootExpression();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INIT:
            if (resolve) {
                return getInit();
            }
            return basicGetInit();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT:
            if (resolve) {
                return getLayout();
            }
            return basicGetLayout();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION:
            if (resolve) {
                return getDiagramInitialisation();
            }
            return basicGetDiagramInitialisation();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER:
            if (resolve) {
                return getDefaultLayer();
            }
            return basicGetDefaultLayer();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS:
            return getAdditionalLayers();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_LAYERS:
            return getAllLayers();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS:
            return getAllActivatedTools();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS:
            return getNodeMappings();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS:
            return getEdgeMappings();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS:
            return getEdgeMappingImports();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS:
            return getContainerMappings();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS:
            return getReusedMappings();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION:
            if (resolve) {
                return getToolSection();
            }
            return basicGetToolSection();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_TOOLS:
            return getReusedTools();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS:
            return isEnablePopupBars();
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
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION:
            setDocumentation((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION:
            setEndUserDocumentation((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION:
            setInitialisation((Boolean) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            getMetamodel().addAll((Collection<? extends EPackage>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP:
            setShowOnStartup((Boolean) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS:
            getPasteDescriptions().clear();
            getPasteDescriptions().addAll((Collection<? extends PasteDescription>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS:
            getFilters().clear();
            getFilters().addAll((Collection<? extends FilterDescription>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET:
            setValidationSet((ValidationSet) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS:
            setConcerns((ConcernSet) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN:
            setDefaultConcern((ConcernDescription) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION:
            setRootExpression((String) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INIT:
            setInit((RepresentationCreationDescription) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT:
            setLayout((Layout) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION:
            setDiagramInitialisation((InitialOperation) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER:
            setDefaultLayer((Layer) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS:
            getAdditionalLayers().clear();
            getAdditionalLayers().addAll((Collection<? extends AdditionalLayer>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_LAYERS:
            getAllLayers().clear();
            getAllLayers().addAll((Collection<? extends Layer>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS:
            getAllActivatedTools().clear();
            getAllActivatedTools().addAll((Collection<? extends AbstractToolDescription>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS:
            getNodeMappings().clear();
            getNodeMappings().addAll((Collection<? extends NodeMapping>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            getEdgeMappings().addAll((Collection<? extends EdgeMapping>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS:
            getEdgeMappingImports().clear();
            getEdgeMappingImports().addAll((Collection<? extends EdgeMappingImport>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS:
            getContainerMappings().clear();
            getContainerMappings().addAll((Collection<? extends ContainerMapping>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS:
            getReusedMappings().clear();
            getReusedMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION:
            setToolSection((ToolSection) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_TOOLS:
            getReusedTools().clear();
            getReusedTools().addAll((Collection<? extends AbstractToolDescription>) newValue);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS:
            setEnablePopupBars((Boolean) newValue);
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
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION:
            setDocumentation(DiagramDescriptionImpl.DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION:
            setEndUserDocumentation(DiagramDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NAME:
            setName(DiagramDescriptionImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL:
            setLabel(DiagramDescriptionImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION:
            setTitleExpression(DiagramDescriptionImpl.TITLE_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION:
            setInitialisation(DiagramDescriptionImpl.INITIALISATION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL:
            getMetamodel().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP:
            setShowOnStartup(DiagramDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS:
            getPasteDescriptions().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS:
            getFilters().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET:
            setValidationSet((ValidationSet) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS:
            setConcerns((ConcernSet) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS:
            setDomainClass(DiagramDescriptionImpl.DOMAIN_CLASS_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION:
            setPreconditionExpression(DiagramDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN:
            setDefaultConcern((ConcernDescription) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION:
            setRootExpression(DiagramDescriptionImpl.ROOT_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INIT:
            setInit((RepresentationCreationDescription) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT:
            setLayout((Layout) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION:
            setDiagramInitialisation((InitialOperation) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER:
            setDefaultLayer((Layer) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS:
            getAdditionalLayers().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_LAYERS:
            getAllLayers().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS:
            getAllActivatedTools().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS:
            getNodeMappings().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS:
            getEdgeMappingImports().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS:
            getContainerMappings().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS:
            getReusedMappings().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION:
            setToolSection((ToolSection) null);
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_TOOLS:
            getReusedTools().clear();
            return;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS:
            setEnablePopupBars(DiagramDescriptionImpl.ENABLE_POPUP_BARS_EDEFAULT);
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
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION:
            return DiagramDescriptionImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !DiagramDescriptionImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION:
            return DiagramDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT == null ? endUserDocumentation != null : !DiagramDescriptionImpl.END_USER_DOCUMENTATION_EDEFAULT.equals(endUserDocumentation);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NAME:
            return DiagramDescriptionImpl.NAME_EDEFAULT == null ? name != null : !DiagramDescriptionImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL:
            return DiagramDescriptionImpl.LABEL_EDEFAULT == null ? label != null : !DiagramDescriptionImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION:
            return DiagramDescriptionImpl.TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null : !DiagramDescriptionImpl.TITLE_EXPRESSION_EDEFAULT.equals(titleExpression);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION:
            return initialisation != DiagramDescriptionImpl.INITIALISATION_EDEFAULT;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL:
            return metamodel != null && !metamodel.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP:
            return showOnStartup != DiagramDescriptionImpl.SHOW_ON_STARTUP_EDEFAULT;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS:
            return pasteDescriptions != null && !pasteDescriptions.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS:
            return filters != null && !filters.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS:
            return !getAllEdgeMappings().isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS:
            return !getAllNodeMappings().isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS:
            return !getAllContainerMappings().isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET:
            return validationSet != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS:
            return concerns != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_TOOLS:
            return !getAllTools().isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS:
            return DiagramDescriptionImpl.DOMAIN_CLASS_EDEFAULT == null ? domainClass != null : !DiagramDescriptionImpl.DOMAIN_CLASS_EDEFAULT.equals(domainClass);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION:
            return DiagramDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT == null ? preconditionExpression != null
                    : !DiagramDescriptionImpl.PRECONDITION_EXPRESSION_EDEFAULT.equals(preconditionExpression);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN:
            return defaultConcern != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION:
            return DiagramDescriptionImpl.ROOT_EXPRESSION_EDEFAULT == null ? rootExpression != null : !DiagramDescriptionImpl.ROOT_EXPRESSION_EDEFAULT.equals(rootExpression);
        case DescriptionPackage.DIAGRAM_DESCRIPTION__INIT:
            return init != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT:
            return layout != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION:
            return diagramInitialisation != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER:
            return defaultLayer != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS:
            return additionalLayers != null && !additionalLayers.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_LAYERS:
            return !getAllLayers().isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS:
            return !getAllActivatedTools().isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS:
            return nodeMappings != null && !nodeMappings.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS:
            return edgeMappings != null && !edgeMappings.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS:
            return edgeMappingImports != null && !edgeMappingImports.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS:
            return containerMappings != null && !containerMappings.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS:
            return reusedMappings != null && !reusedMappings.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION:
            return toolSection != null;
        case DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_TOOLS:
            return reusedTools != null && !reusedTools.isEmpty();
        case DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS:
            return enablePopupBars != DiagramDescriptionImpl.ENABLE_POPUP_BARS_EDEFAULT;
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
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == EndUserDocumentedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.DIAGRAM_DESCRIPTION__NAME:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == RepresentationDescription.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION;
            case DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__INITIALISATION;
            case DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__METAMODEL;
            case DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP;
            default:
                return -1;
            }
        }
        if (baseClass == PasteTargetDescription.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS;
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
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == EndUserDocumentedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__NAME;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL;
            default:
                return -1;
            }
        }
        if (baseClass == RepresentationDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__INITIALISATION:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__METAMODEL:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP;
            default:
                return -1;
            }
        }
        if (baseClass == PasteTargetDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.PASTE_TARGET_DESCRIPTION__PASTE_DESCRIPTIONS:
                return DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS;
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", endUserDocumentation: "); //$NON-NLS-1$
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
        result.append(", domainClass: "); //$NON-NLS-1$
        result.append(domainClass);
        result.append(", preconditionExpression: "); //$NON-NLS-1$
        result.append(preconditionExpression);
        result.append(", rootExpression: "); //$NON-NLS-1$
        result.append(rootExpression);
        result.append(", enablePopupBars: "); //$NON-NLS-1$
        result.append(enablePopupBars);
        result.append(')');
        return result.toString();
    }

} // DiagramDescriptionImpl
