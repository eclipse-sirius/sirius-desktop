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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Layer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getEndUserDocumentation
 * <em>End User Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getName
 * <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getNodeMappings
 * <em>Node Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getEdgeMappings
 * <em>Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getEdgeMappingImports
 * <em>Edge Mapping Imports</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getContainerMappings
 * <em>Container Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getReusedMappings
 * <em>Reused Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getAllTools
 * <em>All Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getToolSections
 * <em>Tool Sections</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getReusedTools
 * <em>Reused Tools</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getDecorationDescriptionsSet
 * <em>Decoration Descriptions Set</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getIcon
 * <em>Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getAllEdgeMappings
 * <em>All Edge Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.LayerImpl#getCustomization
 * <em>Customization</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LayerImpl extends DocumentedElementImpl implements Layer {
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
    protected String endUserDocumentation = LayerImpl.END_USER_DOCUMENTATION_EDEFAULT;

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
    protected String name = LayerImpl.NAME_EDEFAULT;

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
    protected String label = LayerImpl.LABEL_EDEFAULT;

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
     * The cached value of the '{@link #getToolSections() <em>Tool Sections</em>
     * }' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getToolSections()
     * @generated
     * @ordered
     */
    protected EList<ToolSection> toolSections;

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
     * The cached value of the '{@link #getDecorationDescriptionsSet()
     * <em>Decoration Descriptions Set</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDecorationDescriptionsSet()
     * @generated
     * @ordered
     */
    protected DecorationDescriptionsSet decorationDescriptionsSet;

    /**
     * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected static final String ICON_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIcon()
     * @generated
     * @ordered
     */
    protected String icon = LayerImpl.ICON_EDEFAULT;

    /**
     * The cached value of the '{@link #getCustomization()
     * <em>Customization</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getCustomization()
     * @generated
     * @ordered
     */
    protected Customization customization;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected LayerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.LAYER;
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__END_USER_DOCUMENTATION, oldEndUserDocumentation, endUserDocumentation));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__NAME, oldName, name));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<NodeMapping> getNodeMappings() {
        if (nodeMappings == null) {
            nodeMappings = new EObjectContainmentEList.Resolving<NodeMapping>(NodeMapping.class, this, DescriptionPackage.LAYER__NODE_MAPPINGS);
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
            edgeMappings = new EObjectContainmentEList.Resolving<EdgeMapping>(EdgeMapping.class, this, DescriptionPackage.LAYER__EDGE_MAPPINGS);
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
            edgeMappingImports = new EObjectContainmentEList.Resolving<EdgeMappingImport>(EdgeMappingImport.class, this, DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS);
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
            containerMappings = new EObjectContainmentEList.Resolving<ContainerMapping>(ContainerMapping.class, this, DescriptionPackage.LAYER__CONTAINER_MAPPINGS);
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
            reusedMappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, DescriptionPackage.LAYER__REUSED_MAPPINGS);
        }
        return reusedMappings;
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
    public EList<ToolSection> getToolSections() {
        if (toolSections == null) {
            toolSections = new EObjectContainmentEList.Resolving<ToolSection>(ToolSection.class, this, DescriptionPackage.LAYER__TOOL_SECTIONS);
        }
        return toolSections;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AbstractToolDescription> getReusedTools() {
        if (reusedTools == null) {
            reusedTools = new EObjectResolvingEList<AbstractToolDescription>(AbstractToolDescription.class, this, DescriptionPackage.LAYER__REUSED_TOOLS);
        }
        return reusedTools;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DecorationDescriptionsSet getDecorationDescriptionsSet() {
        if (decorationDescriptionsSet != null && decorationDescriptionsSet.eIsProxy()) {
            InternalEObject oldDecorationDescriptionsSet = (InternalEObject) decorationDescriptionsSet;
            decorationDescriptionsSet = (DecorationDescriptionsSet) eResolveProxy(oldDecorationDescriptionsSet);
            if (decorationDescriptionsSet != oldDecorationDescriptionsSet) {
                InternalEObject newDecorationDescriptionsSet = (InternalEObject) decorationDescriptionsSet;
                NotificationChain msgs = oldDecorationDescriptionsSet.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET, null, null);
                if (newDecorationDescriptionsSet.eInternalContainer() == null) {
                    msgs = newDecorationDescriptionsSet.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET, oldDecorationDescriptionsSet, decorationDescriptionsSet));
                }
            }
        }
        return decorationDescriptionsSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DecorationDescriptionsSet basicGetDecorationDescriptionsSet() {
        return decorationDescriptionsSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDecorationDescriptionsSet(DecorationDescriptionsSet newDecorationDescriptionsSet, NotificationChain msgs) {
        DecorationDescriptionsSet oldDecorationDescriptionsSet = decorationDescriptionsSet;
        decorationDescriptionsSet = newDecorationDescriptionsSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET, oldDecorationDescriptionsSet,
                    newDecorationDescriptionsSet);
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
    public void setDecorationDescriptionsSet(DecorationDescriptionsSet newDecorationDescriptionsSet) {
        if (newDecorationDescriptionsSet != decorationDescriptionsSet) {
            NotificationChain msgs = null;
            if (decorationDescriptionsSet != null) {
                msgs = ((InternalEObject) decorationDescriptionsSet).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET, null, msgs);
            }
            if (newDecorationDescriptionsSet != null) {
                msgs = ((InternalEObject) newDecorationDescriptionsSet).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET, null, msgs);
            }
            msgs = basicSetDecorationDescriptionsSet(newDecorationDescriptionsSet, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET, newDecorationDescriptionsSet, newDecorationDescriptionsSet));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIcon() {
        return icon;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIcon(String newIcon) {
        String oldIcon = icon;
        icon = newIcon;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__ICON, oldIcon, icon));
        }
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
    public Customization getCustomization() {
        if (customization != null && customization.eIsProxy()) {
            InternalEObject oldCustomization = (InternalEObject) customization;
            customization = (Customization) eResolveProxy(oldCustomization);
            if (customization != oldCustomization) {
                InternalEObject newCustomization = (InternalEObject) customization;
                NotificationChain msgs = oldCustomization.eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__CUSTOMIZATION, null, null);
                if (newCustomization.eInternalContainer() == null) {
                    msgs = newCustomization.eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__CUSTOMIZATION, null, msgs);
                }
                if (msgs != null) {
                    msgs.dispatch();
                }
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.LAYER__CUSTOMIZATION, oldCustomization, customization));
                }
            }
        }
        return customization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Customization basicGetCustomization() {
        return customization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCustomization(Customization newCustomization, NotificationChain msgs) {
        Customization oldCustomization = customization;
        customization = newCustomization;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__CUSTOMIZATION, oldCustomization, newCustomization);
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
    public void setCustomization(Customization newCustomization) {
        if (newCustomization != customization) {
            NotificationChain msgs = null;
            if (customization != null) {
                msgs = ((InternalEObject) customization).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__CUSTOMIZATION, null, msgs);
            }
            if (newCustomization != null) {
                msgs = ((InternalEObject) newCustomization).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.LAYER__CUSTOMIZATION, null, msgs);
            }
            msgs = basicSetCustomization(newCustomization, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.LAYER__CUSTOMIZATION, newCustomization, newCustomization));
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
        case DescriptionPackage.LAYER__NODE_MAPPINGS:
            return ((InternalEList<?>) getNodeMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LAYER__EDGE_MAPPINGS:
            return ((InternalEList<?>) getEdgeMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS:
            return ((InternalEList<?>) getEdgeMappingImports()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LAYER__CONTAINER_MAPPINGS:
            return ((InternalEList<?>) getContainerMappings()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LAYER__TOOL_SECTIONS:
            return ((InternalEList<?>) getToolSections()).basicRemove(otherEnd, msgs);
        case DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET:
            return basicSetDecorationDescriptionsSet(null, msgs);
        case DescriptionPackage.LAYER__CUSTOMIZATION:
            return basicSetCustomization(null, msgs);
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
        case DescriptionPackage.LAYER__END_USER_DOCUMENTATION:
            return getEndUserDocumentation();
        case DescriptionPackage.LAYER__NAME:
            return getName();
        case DescriptionPackage.LAYER__LABEL:
            return getLabel();
        case DescriptionPackage.LAYER__NODE_MAPPINGS:
            return getNodeMappings();
        case DescriptionPackage.LAYER__EDGE_MAPPINGS:
            return getEdgeMappings();
        case DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS:
            return getEdgeMappingImports();
        case DescriptionPackage.LAYER__CONTAINER_MAPPINGS:
            return getContainerMappings();
        case DescriptionPackage.LAYER__REUSED_MAPPINGS:
            return getReusedMappings();
        case DescriptionPackage.LAYER__ALL_TOOLS:
            return getAllTools();
        case DescriptionPackage.LAYER__TOOL_SECTIONS:
            return getToolSections();
        case DescriptionPackage.LAYER__REUSED_TOOLS:
            return getReusedTools();
        case DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET:
            if (resolve) {
                return getDecorationDescriptionsSet();
            }
            return basicGetDecorationDescriptionsSet();
        case DescriptionPackage.LAYER__ICON:
            return getIcon();
        case DescriptionPackage.LAYER__ALL_EDGE_MAPPINGS:
            return getAllEdgeMappings();
        case DescriptionPackage.LAYER__CUSTOMIZATION:
            if (resolve) {
                return getCustomization();
            }
            return basicGetCustomization();
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
        case DescriptionPackage.LAYER__END_USER_DOCUMENTATION:
            setEndUserDocumentation((String) newValue);
            return;
        case DescriptionPackage.LAYER__NAME:
            setName((String) newValue);
            return;
        case DescriptionPackage.LAYER__LABEL:
            setLabel((String) newValue);
            return;
        case DescriptionPackage.LAYER__NODE_MAPPINGS:
            getNodeMappings().clear();
            getNodeMappings().addAll((Collection<? extends NodeMapping>) newValue);
            return;
        case DescriptionPackage.LAYER__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            getEdgeMappings().addAll((Collection<? extends EdgeMapping>) newValue);
            return;
        case DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS:
            getEdgeMappingImports().clear();
            getEdgeMappingImports().addAll((Collection<? extends EdgeMappingImport>) newValue);
            return;
        case DescriptionPackage.LAYER__CONTAINER_MAPPINGS:
            getContainerMappings().clear();
            getContainerMappings().addAll((Collection<? extends ContainerMapping>) newValue);
            return;
        case DescriptionPackage.LAYER__REUSED_MAPPINGS:
            getReusedMappings().clear();
            getReusedMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case DescriptionPackage.LAYER__TOOL_SECTIONS:
            getToolSections().clear();
            getToolSections().addAll((Collection<? extends ToolSection>) newValue);
            return;
        case DescriptionPackage.LAYER__REUSED_TOOLS:
            getReusedTools().clear();
            getReusedTools().addAll((Collection<? extends AbstractToolDescription>) newValue);
            return;
        case DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET:
            setDecorationDescriptionsSet((DecorationDescriptionsSet) newValue);
            return;
        case DescriptionPackage.LAYER__ICON:
            setIcon((String) newValue);
            return;
        case DescriptionPackage.LAYER__CUSTOMIZATION:
            setCustomization((Customization) newValue);
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
        case DescriptionPackage.LAYER__END_USER_DOCUMENTATION:
            setEndUserDocumentation(LayerImpl.END_USER_DOCUMENTATION_EDEFAULT);
            return;
        case DescriptionPackage.LAYER__NAME:
            setName(LayerImpl.NAME_EDEFAULT);
            return;
        case DescriptionPackage.LAYER__LABEL:
            setLabel(LayerImpl.LABEL_EDEFAULT);
            return;
        case DescriptionPackage.LAYER__NODE_MAPPINGS:
            getNodeMappings().clear();
            return;
        case DescriptionPackage.LAYER__EDGE_MAPPINGS:
            getEdgeMappings().clear();
            return;
        case DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS:
            getEdgeMappingImports().clear();
            return;
        case DescriptionPackage.LAYER__CONTAINER_MAPPINGS:
            getContainerMappings().clear();
            return;
        case DescriptionPackage.LAYER__REUSED_MAPPINGS:
            getReusedMappings().clear();
            return;
        case DescriptionPackage.LAYER__TOOL_SECTIONS:
            getToolSections().clear();
            return;
        case DescriptionPackage.LAYER__REUSED_TOOLS:
            getReusedTools().clear();
            return;
        case DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET:
            setDecorationDescriptionsSet((DecorationDescriptionsSet) null);
            return;
        case DescriptionPackage.LAYER__ICON:
            setIcon(LayerImpl.ICON_EDEFAULT);
            return;
        case DescriptionPackage.LAYER__CUSTOMIZATION:
            setCustomization((Customization) null);
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
        case DescriptionPackage.LAYER__END_USER_DOCUMENTATION:
            return LayerImpl.END_USER_DOCUMENTATION_EDEFAULT == null ? endUserDocumentation != null : !LayerImpl.END_USER_DOCUMENTATION_EDEFAULT.equals(endUserDocumentation);
        case DescriptionPackage.LAYER__NAME:
            return LayerImpl.NAME_EDEFAULT == null ? name != null : !LayerImpl.NAME_EDEFAULT.equals(name);
        case DescriptionPackage.LAYER__LABEL:
            return LayerImpl.LABEL_EDEFAULT == null ? label != null : !LayerImpl.LABEL_EDEFAULT.equals(label);
        case DescriptionPackage.LAYER__NODE_MAPPINGS:
            return nodeMappings != null && !nodeMappings.isEmpty();
        case DescriptionPackage.LAYER__EDGE_MAPPINGS:
            return edgeMappings != null && !edgeMappings.isEmpty();
        case DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS:
            return edgeMappingImports != null && !edgeMappingImports.isEmpty();
        case DescriptionPackage.LAYER__CONTAINER_MAPPINGS:
            return containerMappings != null && !containerMappings.isEmpty();
        case DescriptionPackage.LAYER__REUSED_MAPPINGS:
            return reusedMappings != null && !reusedMappings.isEmpty();
        case DescriptionPackage.LAYER__ALL_TOOLS:
            return !getAllTools().isEmpty();
        case DescriptionPackage.LAYER__TOOL_SECTIONS:
            return toolSections != null && !toolSections.isEmpty();
        case DescriptionPackage.LAYER__REUSED_TOOLS:
            return reusedTools != null && !reusedTools.isEmpty();
        case DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET:
            return decorationDescriptionsSet != null;
        case DescriptionPackage.LAYER__ICON:
            return LayerImpl.ICON_EDEFAULT == null ? icon != null : !LayerImpl.ICON_EDEFAULT.equals(icon);
        case DescriptionPackage.LAYER__ALL_EDGE_MAPPINGS:
            return !getAllEdgeMappings().isEmpty();
        case DescriptionPackage.LAYER__CUSTOMIZATION:
            return customization != null;
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
            case DescriptionPackage.LAYER__END_USER_DOCUMENTATION:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.LAYER__NAME:
                return org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case DescriptionPackage.LAYER__LABEL:
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
                return DescriptionPackage.LAYER__END_USER_DOCUMENTATION;
            default:
                return -1;
            }
        }
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return DescriptionPackage.LAYER__NAME;
            case org.eclipse.sirius.viewpoint.description.DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return DescriptionPackage.LAYER__LABEL;
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
        result.append(", icon: "); //$NON-NLS-1$
        result.append(icon);
        result.append(')');
        return result.toString();
    }

} // LayerImpl
