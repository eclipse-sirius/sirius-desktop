/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.operations;

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
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
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
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * Implementation of the EdgeMapping interface. This class is more or less a
 * wrapper for another EdgeMapping, it helps in reusing mappings from
 * EdgeMapping.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class EdgeMappingImportWrapper extends EObjectImpl implements EdgeMapping, EdgeMappingImport {

    /**
     * The default value of the '{@link #isSynchronizationLock()
     * <em>Synchronization Lock</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#isSynchronizationLock()
     * @generated
     * @ordered
     */
    protected static final boolean SYNCHRONIZATION_LOCK_EDEFAULT = false;

    private static Map<EdgeMappingImport, EdgeMappingImportWrapper> wrappers = new HashMap<EdgeMappingImport, EdgeMappingImportWrapper>();

    /**
     * The cached value of the '{@link #isSynchronizationLock()
     * <em>Synchronization Lock</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
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
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#createEdge(org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.emf.ecore.EObject)
     */
    public DEdge createEdge(final EdgeTarget source, final EdgeTarget target, final EObject semanticTarget) {
        return createEdge(source, target, null, semanticTarget);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#createEdge(org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    public DEdge createEdge(final EdgeTarget source, final EdgeTarget target, final EObject container, final EObject semanticTarget) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticTarget);
        return new EdgeMappingHelper(interpreter).createEdge(this, source, target, container, semanticTarget);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getBestStyle(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    public EdgeStyle getBestStyle(final EObject modelElement, final EObject viewVariable, final EObject containerVariable) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        EdgeStyle result = (EdgeStyle) new MappingHelper(interpreter).getBestStyle(this, modelElement, viewVariable, containerVariable, new EObjectQuery(viewVariable).getParentDiagram().get());
        if (result == null && edgeMappingImport.getImportedMapping() != null && edgeMappingImport.getImportedMapping() != this) {
            result = edgeMappingImport.getImportedMapping().getBestStyle(modelElement, viewVariable, containerVariable);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getConditionnalStyles()
     */
    public EList<ConditionalEdgeStyleDescription> getConditionnalStyles() {
        return edgeMappingImport.getConditionnalStyles();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getDomainClass()
     */
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
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getEdgeSourceCandidates(EObject,
     *      DDiagram)
     */
    public EList<EObject> getEdgeSourceCandidates(final EObject semanticOrigin, final DDiagram viewPoint) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getEdgeSourceCandidates(semanticOrigin, viewPoint);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getEdgeTargetCandidates(EObject,
     *      DDiagram)
     */
    public EList<EObject> getEdgeTargetCandidates(final EObject semanticOrigin, final DDiagram viewPoint) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getEdgeTargetCandidates(semanticOrigin, viewPoint);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getEdgeTargetCandidates(EObject,
     *      EObject, EObject)
     */
    public EList<EObject> getEdgeTargetCandidates(final EObject semanticOrigin, final EObject container, final EObject containerView) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getEdgeTargetCandidates(semanticOrigin, container, containerView);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#getPathExpression()
     */
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
    public void setStyle(final EdgeStyleDescription value) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#setTargetExpression(java.lang.String)
     */
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
    public void setUseDomainElement(final boolean value) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            edgeMapping.setUseDomainElement(value);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMapping#updateEdge(org.eclipse.sirius.diagram.DEdge)
     */
    public void updateEdge(final DEdge viewEdge) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(viewEdge.getTarget());
        new EdgeMappingHelper(interpreter).updateEdge(this, viewEdge);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#checkPrecondition(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    public boolean checkPrecondition(final EObject modelElement, final EObject container, final EObject containerView) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return SiriusElementMappingSpecOperations.checkPrecondition(edgeMapping, modelElement, container, containerView);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getAllMappings()
     */
    public EList<DiagramElementMapping> getAllMappings() {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.getAllMappings();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDeletionDescription()
     */
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
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#isTypeOf(org.eclipse.sirius.viewpoint.DMappingBased)
     */
    public boolean isFrom(final DMappingBased element) {
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return new DiagramElementMappingQuery(edgeMapping).isTypeOf(element);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#setCreateElements(boolean)
     */
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
    public String getName() {
        return edgeMappingImport.getName();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#getNavigationDescriptions()
     */
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
     * @see org.eclipse.emf.ecore.EObject#eGet(org.eclipse.emf.ecore.EStructuralFeature,
     *      boolean)
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
     * @return true if the feature is one of the EdgeMappingImport, false
     *         otherwise
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
     * @see org.eclipse.emf.ecore.EObject#eSet(org.eclipse.emf.ecore.EStructuralFeature,
     *      java.lang.Object)
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
        final EdgeMapping edgeMapping = MappingHelper.getEdgeMapping(edgeMappingImport);
        if (edgeMapping != null) {
            return edgeMapping.eAdapters();
        }
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
    public String getDocumentation() {
        return edgeMappingImport.getDocumentation();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement#setDocumentation(java.lang.String)
     */
    public void setDocumentation(final String value) {
        edgeMappingImport.setDocumentation(value);
    }

    /**
     * Return The imported mapping of the wrapped EdgeMappingImport.
     * 
     * @return The imported mapping
     */
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
    public void setImportedMapping(final IEdgeMapping value) {
        this.edgeMappingImport.setImportedMapping(value);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramElementMapping#getDoubleClickDescription()
     */
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
    public boolean isInheritsAncestorFilters() {
        return edgeMappingImport.isInheritsAncestorFilters();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.EdgeMappingImport#setInheritsAncestorFilters(org.eclipse.sirius.viewpoint.description.IEdgeMapping)
     */
    public void setInheritsAncestorFilters(boolean newInheritsAncestorFilters) {
        this.edgeMappingImport.setInheritsAncestorFilters(newInheritsAncestorFilters);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#isSynchronizationLock()
     */
    public boolean isSynchronizationLock() {
        return synchronizationLock;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#setSynchronizationLock(boolean
     *      newSynchronizationLock)
     */
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
    public String getLabel() {
        return edgeMappingImport.getLabel();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping#setName(java.lang.String)
     */
    public void setLabel(final String value) {
        edgeMappingImport.setLabel(value);
    }
}
