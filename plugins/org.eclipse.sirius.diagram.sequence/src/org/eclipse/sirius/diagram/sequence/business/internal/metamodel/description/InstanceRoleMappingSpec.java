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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.sequence.description.impl.InstanceRoleMappingImpl;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Implementation of <code>InstanceRoleMapping</code>.
 * 
 * @author pcdavid
 */
public class InstanceRoleMappingSpec extends InstanceRoleMappingImpl implements INodeMappingExt {

    private final Map<EObject, EList<DSemanticDecorator>> viewNodesDone = new HashMap<EObject, EList<DSemanticDecorator>>();

    private final Map<EObjectCouple, EList<EObject>> candidatesCache = new WeakHashMap<EObjectCouple, EList<EObject>>();

    /**
     * {@inheritDoc}
     */
    public Map<EObject, EList<DSemanticDecorator>> getViewNodesDone() {
        return viewNodesDone;
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
    public EList<EObject> getNodesCandidates(final EObject semanticOrigin, final EObject container) {
        return NodeMappingHelper.getNodesCandidates(this, semanticOrigin, container);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<EObject> getNodesCandidates(final EObject semanticOrigin, final EObject container, final EObject containerView) {
        return NodeMappingHelper.getNodesCandidates(this, semanticOrigin, container, containerView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DNode createNode(final EObject modelElement, final EObject container, final DDiagram diagram) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        return new NodeMappingHelper(interpreter).createNode(this, modelElement, container, diagram);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateNode(final DNode node) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(node);
        new NodeMappingHelper(interpreter).updateNode(this, node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateListElement(final DNodeListElement listElement) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(listElement);
        new NodeMappingHelper(interpreter).updateListElement(this, listElement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearDNodesDone() {
        NodeMappingHelper.clearDNodesDone(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<DDiagramElement> findDNodeFromEObject(final EObject object) {
        return NodeMappingHelper.findDNodeFromEObject(this, object);
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
        final BasicEList<DiagramElementMapping> allMappings = new BasicEList<DiagramElementMapping>();
        allMappings.addAll(this.getAllBorderedNodeMappings());
        return new UnmodifiableEList<DiagramElementMapping>(allMappings.size(), allMappings.toArray());
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
    public void addDoneNode(final DSemanticDecorator node) {
        NodeMappingHelper.addDoneNode(this, node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString(); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EList<NodeMapping> getAllBorderedNodeMappings() {
        return AbstractNodeMappingSpecOperations.getAllBorderedNodeMappings(this);
    }

}
