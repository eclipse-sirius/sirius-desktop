/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.business.internal.metamodel.helper.ContainerMappingHelper;
import org.eclipse.sirius.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.sequence.description.impl.OperandMappingImpl;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.ContainerMapping;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.NodeMapping;

/**
 * Implementation of OperandMapping.
 * 
 * @author pcdavid
 */
public class OperandMappingSpec extends OperandMappingImpl implements IContainerMappingExt {

    private final Map<EObject, EList<DSemanticDecorator>> viewContainerDone = new HashMap<EObject, EList<DSemanticDecorator>>();

    private final Map<EObjectCouple, EList<EObject>> candidatesCache = new WeakHashMap<EObjectCouple, EList<EObject>>();

    /**
     * {@inheritDoc}
     */
    public Map<EObject, EList<DSemanticDecorator>> getViewContainerDone() {
        return viewContainerDone;
    }

    /**
     * {@inheritDoc}
     */
    public Map<EObjectCouple, EList<EObject>> getCandidatesCache() {
        return candidatesCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<NodeMapping> getAllNodeMappings() {
        final Collection<NodeMapping> result = ContainerMappingHelper.getAllNodeMappings(this);
        return new EcoreEList.UnmodifiableEList<NodeMapping>(eInternalContainer(), DescriptionPackage.eINSTANCE.getContainerMapping_AllNodeMappings(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<ContainerMapping> getAllContainerMappings() {
        final Collection<ContainerMapping> result = ContainerMappingHelper.getAllContainerMappings(this);
        return new EcoreEList.UnmodifiableEList<ContainerMapping>(eInternalContainer(), DescriptionPackage.eINSTANCE.getContainerMapping_AllContainerMappings(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<EObject> getNodesCandidates(final EObject semanticOrigin, final EObject container, final EObject containerView) {
        return ContainerMappingHelper.getNodesCandidates(this, semanticOrigin, container, containerView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearDNodesDone() {
        ContainerMappingHelper.clearDNodesDone(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DDiagramElement> findDNodeFromEObject(final EObject object) {
        return ContainerMappingHelper.findDNodeFromEObject(this, object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DDiagramElementContainer createContainer(final EObject modelElement, final EObject container, final DDiagram viewPoint) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);
        return new ContainerMappingHelper(interpreter).createContainer(this, modelElement, container, viewPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateContainer(final DDiagramElementContainer container) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);
        new ContainerMappingHelper(interpreter).updateContainer(this, container);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDoneNode(final DSemanticDecorator node) {
        ContainerMappingHelper.addDoneNode(this, node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerStyle getBestStyle(final EObject modelElement, final EObject viewVariable, final EObject containerVariable) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        return (ContainerStyle) new MappingHelper(interpreter).getBestStyle(this, modelElement, viewVariable, containerVariable, new EObjectQuery(containerVariable).getParentDiagram().get());
    }

    /*
     * Here we add the behavior we should inherit from AbstractNodeMapping
     */

    /**
     * {@inheritDoc}
     */
    public void createBorderingNodes(final EObject modelElement, final DDiagramElement vpElement, @SuppressWarnings("rawtypes")
    final Collection filterSemantic, final DDiagram viewPoint) {
        AbstractNodeMappingSpecOperations.createBorderingNodes(this, modelElement, vpElement, filterSemantic, viewPoint);
    }

    /*
     * Behavior inherited from DiagramElementMapping
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkPrecondition(final EObject modelElement, final EObject container, final EObject containerView) {
        return SiriusElementMappingSpecOperations.checkPrecondition(this, modelElement, container, containerView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DiagramElementMapping> getAllMappings() {
        return ContainerMappingHelper.getAllMappings(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFrom(final DMappingBased element) {
        return SiriusElementMappingSpecOperations.isFrom(this, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<NodeMapping> getAllBorderedNodeMappings() {
        return AbstractNodeMappingSpecOperations.getAllBorderedNodeMappings(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DDiagramElement> getDNodesDone() {
        return ContainerMappingHelper.getDNodesDone(this);
    }

}
