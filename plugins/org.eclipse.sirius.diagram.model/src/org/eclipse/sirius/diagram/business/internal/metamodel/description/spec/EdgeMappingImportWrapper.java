/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.spec;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.tool.DeleteElementDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * Implementation of the EdgeMapping interface. This class is more or less a wrapper for another EdgeMapping, it helps
 * in reusing mappings from EdgeMapping.<BR>
 * It must not be used to set the actual mapping. This class must only be used for {@link DEdge#getMapping()} and then
 * the result of {@link #getWrappedEdgeMappingImport()} must be used to set actual mapping:
 * {@link DEdge#setActualMapping(IEdgeMapping)}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class EdgeMappingImportWrapper extends EObjectImpl implements EdgeMapping, EdgeMappingImport {

    /**
     * The default value of the '{@link #isSynchronizationLock() <em>Synchronization Lock</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#isSynchronizationLock()
     * @generated
     * @ordered
     */
    protected static final boolean SYNCHRONIZATION_LOCK_EDEFAULT = false;

    private static Map<EdgeMappingImport, EdgeMappingImportWrapper> wrappers = new HashMap<EdgeMappingImport, EdgeMappingImportWrapper>();

    /**
     * The cached value of the '{@link #isSynchronizationLock() <em>Synchronization Lock</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#isSynchronizationLock()
     * @generated
     * @ordered
     */
    protected boolean synchronizationLock = SYNCHRONIZATION_LOCK_EDEFAULT;

    private EdgeMappingImport edgeMappingImport;

    /**
     * Default constructor.
     * 
     * @param edgeMappingImport
     *            The edgeMappingImport to wrap
     */
    private EdgeMappingImportWrapper(final EdgeMappingImport edgeMappingImport) {
        this.edgeMappingImport = edgeMappingImport;
    }

    /**
     * Return the wrapper corresponding to the edgeMappingImport.
     * 
     * @param edgeMappingImport
     *            The edgeMappingImport to wrap
     * @return the corresponding wrapper
     */
    public static EdgeMappingImportWrapper getWrapper(final EdgeMappingImport edgeMappingImport) {
        if (!wrappers.containsKey(edgeMappingImport)) {
            wrappers.put(edgeMappingImport, new EdgeMappingImportWrapper(edgeMappingImport));
        }
        return wrappers.get(edgeMappingImport);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getConditionnalStyles()
     */
    @Override
    public EList<ConditionalEdgeStyleDescription> getConditionnalStyles() {
        return edgeMappingImport.getConditionnalStyles();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getDomainClass()
     */
    @Override
    public String getDomainClass() {
        String result = null;
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            result = edgeMapping.getDomainClass();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getPathExpression()
     */
    @Override
    public String getPathExpression() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getPathExpression();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getPathNodeMapping()
     */
    @Override
    public EList<AbstractNodeMapping> getPathNodeMapping() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getPathNodeMapping();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getReconnections()
     */
    @Override
    public EList<ReconnectEdgeDescription> getReconnections() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getReconnections();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getSourceFinderExpression()
     */
    @Override
    public String getSourceFinderExpression() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getSourceFinderExpression();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getSourceMapping()
     */
    @Override
    public EList<DiagramElementMapping> getSourceMapping() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getSourceMapping();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getStyle()
     */
    @Override
    public EdgeStyleDescription getStyle() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getStyle();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getTargetExpression()
     */
    @Override
    public String getTargetExpression() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getTargetExpression();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getTargetFinderExpression()
     */
    @Override
    public String getTargetFinderExpression() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getTargetFinderExpression();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getTargetMapping()
     */
    @Override
    public EList<DiagramElementMapping> getTargetMapping() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getTargetMapping();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#isUseDomainElement()
     */
    @Override
    public boolean isUseDomainElement() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.isUseDomainElement();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setDomainClass(java.lang.String)
     */
    @Override
    public void setDomainClass(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setDomainClass(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setPathExpression(java.lang.String)
     */
    @Override
    public void setPathExpression(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setPathExpression(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setSourceFinderExpression(java.lang.String)
     */
    @Override
    public void setSourceFinderExpression(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setSourceFinderExpression(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setStyle(org.eclipse.sirius.diagram.description.style.EdgeStyleDescription)
     */
    @Override
    public void setStyle(final EdgeStyleDescription value) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setTargetExpression(java.lang.String)
     */
    @Override
    public void setTargetExpression(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setTargetExpression(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setTargetFinderExpression(java.lang.String)
     */
    @Override
    public void setTargetFinderExpression(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setTargetFinderExpression(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setUseDomainElement(boolean)
     */
    @Override
    public void setUseDomainElement(final boolean value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setUseDomainElement(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDeletionDescription()
     */
    @Override
    public DeleteElementDescription getDeletionDescription() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getDeletionDescription();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.PasteTargetDescription#getPasteDescriptions()
     */
    @Override
    public EList<PasteDescription> getPasteDescriptions() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getPasteDescriptions();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getLabelDirectEdit()
     */
    @Override
    public DirectEditLabel getLabelDirectEdit() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getLabelDirectEdit();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getPreconditionExpression()
     */
    @Override
    public String getPreconditionExpression() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getPreconditionExpression();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getSemanticCandidatesExpression()
     */
    @Override
    public String getSemanticCandidatesExpression() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getSemanticCandidatesExpression();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getSemanticElements()
     */
    @Override
    public String getSemanticElements() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getSemanticElements();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#isCreateElements()
     */
    @Override
    public boolean isCreateElements() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.isCreateElements();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setCreateElements(boolean)
     */
    @Override
    public void setCreateElements(final boolean value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setCreateElements(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setDeletionDescription(org.eclipse.sirius.viewpoint.description.tool.DeleteElementDescription)
     */
    @Override
    public void setDeletionDescription(final DeleteElementDescription value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setDeletionDescription(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setLabelDirectEdit(org.eclipse.sirius.viewpoint.description.tool.DirectEditLabel)
     */
    @Override
    public void setLabelDirectEdit(final DirectEditLabel value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setLabelDirectEdit(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setPreconditionExpression(java.lang.String)
     */
    @Override
    public void setPreconditionExpression(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setPreconditionExpression(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setSemanticCandidatesExpression(java.lang.String)
     */
    @Override
    public void setSemanticCandidatesExpression(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setSemanticCandidatesExpression(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setSemanticElements(java.lang.String)
     */
    @Override
    public void setSemanticElements(final String value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setSemanticElements(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getDetailDescriptions()
     */
    @Override
    public EList<RepresentationCreationDescription> getDetailDescriptions() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getDetailDescriptions();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getName()
     */
    @Override
    public String getName() {
        return edgeMappingImport.getName();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getNavigationDescriptions()
     */
    @Override
    public EList<RepresentationNavigationDescription> getNavigationDescriptions() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getNavigationDescriptions();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#setName(java.lang.String)
     */
    @Override
    public void setName(final String value) {
        edgeMappingImport.setName(value);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eAllContents()
     */
    @Override
    public TreeIterator<EObject> eAllContents() {
        return edgeMappingImport.eAllContents();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eClass()
     */
    @Override
    public EClass eClass() {
        return edgeMappingImport.eClass();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eContainer()
     */
    @Override
    public EObject eContainer() {
        return edgeMappingImport.eContainer();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eContainingFeature()
     */
    @Override
    public EStructuralFeature eContainingFeature() {
        return edgeMappingImport.eContainingFeature();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eContainmentFeature()
     */
    @Override
    public EReference eContainmentFeature() {
        return edgeMappingImport.eContainmentFeature();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.impl.EObjectImpl#eContainerFeatureID()
     */
    @Override
    public int eContainerFeatureID() {
        return ((InternalEObject) edgeMappingImport).eContainerFeatureID();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eContents()
     */
    @Override
    public EList<EObject> eContents() {
        return edgeMappingImport.eContents();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eCrossReferences()
     */
    @Override
    public EList<EObject> eCrossReferences() {
        return edgeMappingImport.eCrossReferences();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public Object eGet(final EStructuralFeature feature) {
        Object result = null;
        if (isEdgeMappingImportFeature(feature)) {
            result = edgeMappingImport.eGet(feature);
        } else {
            final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
            if (edgeMapping != null) {
                result = edgeMapping.eGet(feature);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature, boolean)
     */
    @Override
    public Object eGet(final EStructuralFeature feature, final boolean resolve) {
        Object result = null;
        if (isEdgeMappingImportFeature(feature)) {
            result = edgeMappingImport.eGet(feature, resolve);
        } else {
            final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
            if (edgeMapping != null) {
                result = edgeMapping.eGet(feature, resolve);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public boolean eIsSet(final EStructuralFeature feature) {
        boolean result = false;
        if (isEdgeMappingImportFeature(feature)) {
            result = edgeMappingImport.eIsSet(feature);
        } else {
            final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
            if (edgeMapping != null) {
                result = edgeMapping.eIsSet(feature);
            }
        }
        return result;
    }

    /**
     * @param feature
     *            the structural feature to check
     * @return true if the feature is one of the EdgeMappingImport, false otherwise
     */
    private boolean isEdgeMappingImportFeature(final EStructuralFeature feature) {
        return feature.equals(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getIdentifiedElement_Name())
                || feature.equals(DescriptionPackage.eINSTANCE.getEdgeMappingImport_ImportedMapping()) || feature.equals(DescriptionPackage.eINSTANCE.getEdgeMappingImport_ConditionnalStyles());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.impl.EObjectImpl#eInternalContainer()
     */
    @Override
    public InternalEObject eInternalContainer() {
        return ((InternalEObject) edgeMappingImport).eInternalContainer();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eDirectResource()
     */
    @Override
    public Internal eDirectResource() {
        return ((InternalEObject) edgeMappingImport).eDirectResource();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eInternalResource()
     */
    @Override
    public Internal eInternalResource() {
        return ((InternalEObject) edgeMappingImport).eInternalResource();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eResource()
     */
    @Override
    public Resource eResource() {
        return edgeMappingImport.eResource();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eIsProxy()
     */
    @Override
    public boolean eIsProxy() {
        return edgeMappingImport.eIsProxy();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.impl.EObjectImpl#eSetProxyURI(org.eclipse.emf.common.util.URI)
     */
    @Override
    public void eSetProxyURI(URI uri) {
        ((InternalEObject) edgeMappingImport).eSetProxyURI(uri);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eProxyURI()
     */
    @Override
    public URI eProxyURI() {
        return ((InternalEObject) edgeMappingImport).eProxyURI();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    @Override
    public void eSet(final EStructuralFeature feature, final Object newValue) {
        if (isEdgeMappingImportFeature(feature)) {
            edgeMappingImport.eSet(feature, newValue);
        } else {
            final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
            if (edgeMapping != null) {
                edgeMapping.eSet(feature, newValue);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.EObject#eUnset(org.eclipse.emf.ecore.EStructuralFeature)
     */
    @Override
    public void eUnset(final EStructuralFeature feature) {
        if (isEdgeMappingImportFeature(feature)) {
            edgeMappingImport.eUnset(feature);
        } else {
            final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
            if (edgeMapping != null) {
                edgeMapping.eUnset(feature);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.notify.Notifier#eAdapters()
     */
    @Override
    public EList<Adapter> eAdapters() {
        return edgeMappingImport.eAdapters();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.notify.Notifier#eDeliver()
     */
    @Override
    public boolean eDeliver() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.eDeliver();
        }
        return edgeMappingImport.eDeliver();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.notify.Notifier#eNotify(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void eNotify(final Notification notification) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.eNotify(notification);
        }
        edgeMappingImport.eNotify(notification);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement#getDocumentation()
     */
    @Override
    public String getDocumentation() {
        return edgeMappingImport.getDocumentation();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement#setDocumentation(java.lang.String)
     */
    @Override
    public void setDocumentation(final String value) {
        edgeMappingImport.setDocumentation(value);
    }

    /**
     * Return The imported mapping of the wrapped EdgeMappingImport.
     * 
     * @return The imported mapping
     */
    @Override
    public IEdgeMapping getImportedMapping() {
        return edgeMappingImport.getImportedMapping();
    }

    /**
     * Return The EdgeMappingImport.
     * 
     * @return the edgeMappingImport
     */
    public EdgeMappingImport getWrappedEdgeMappingImport() {
        return edgeMappingImport;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMappingImport#setImportedMapping(org.eclipse.sirius.viewpoint.description.IEdgeMapping)
     */
    @Override
    public void setImportedMapping(final IEdgeMapping value) {
        this.edgeMappingImport.setImportedMapping(value);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDoubleClickDescription()
     */
    @Override
    public DoubleClickDescription getDoubleClickDescription() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getDoubleClickDescription();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setDoubleClickDescription(DoubleClickOnElementDescription)
     */
    @Override
    public void setDoubleClickDescription(DoubleClickDescription value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setDoubleClickDescription(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMappingImport#isInheritsAncestorFilters(org.eclipse.sirius.viewpoint.description.IEdgeMapping)
     */
    @Override
    public boolean isInheritsAncestorFilters() {
        return edgeMappingImport.isInheritsAncestorFilters();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMappingImport#setInheritsAncestorFilters(org.eclipse.sirius.viewpoint.description.IEdgeMapping)
     */
    @Override
    public void setInheritsAncestorFilters(boolean newInheritsAncestorFilters) {
        this.edgeMappingImport.setInheritsAncestorFilters(newInheritsAncestorFilters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#isSynchronizationLock()
     */
    @Override
    public boolean isSynchronizationLock() {
        return synchronizationLock;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#setSynchronizationLock(boolean
     *      newSynchronizationLock)
     */
    @Override
    public void setSynchronizationLock(boolean newSynchronizationLock) {
        boolean oldSynchronizationLock = synchronizationLock;
        synchronizationLock = newSynchronizationLock;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK, oldSynchronizationLock, synchronizationLock));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getLabel()
     */
    @Override
    public String getLabel() {
        return edgeMappingImport.getLabel();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#setName(java.lang.String)
     */
    @Override
    public void setLabel(final String value) {
        edgeMappingImport.setLabel(value);
    }

}
