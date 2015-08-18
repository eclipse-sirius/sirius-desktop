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

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.sequence.description.impl.DestructionMessageMappingImpl;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Implementation of <code>DestructionMessageMapping</code>.
 * 
 * @author pcdavid
 */
public class DestructionMessageMappingSpec extends DestructionMessageMappingImpl {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EdgeMappingImpl#createEdge(org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.emf.ecore.EObject)
     */
    @Override
    public DEdge createEdge(final EdgeTarget source, final EdgeTarget target, final EObject semanticTarget) {
        return createEdge(source, target, null, semanticTarget);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EdgeMappingImpl#createEdge(org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.sirius.diagram.EdgeTarget,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
    public DEdge createEdge(final EdgeTarget source, final EdgeTarget target, final EObject container, final EObject semanticTarget) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);
        return new EdgeMappingHelper(interpreter).createEdge(this, source, target, container, semanticTarget);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EdgeMappingImpl#getBestStyle(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
    public EdgeStyle getBestStyle(final EObject modelElement, final EObject viewVariable, final EObject containerVariable) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        return (EdgeStyle) new MappingHelper(interpreter).getBestStyle(this, modelElement, viewVariable, containerVariable, new EObjectQuery(viewVariable).getParentDiagram().get());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EdgeMappingImpl#updateEdge(org.eclipse.sirius.diagram.DEdge)
     */
    @Override
    public void updateEdge(final DEdge dEdge) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(dEdge);
        new EdgeMappingHelper(interpreter).updateEdge(this, dEdge);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getEdgeTargetCandidates(EObject,
     *      DDiagram)
     */
    @Override
    public EList<EObject> getEdgeTargetCandidates(final EObject semanticOrigin, final DDiagram diagram) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticOrigin);
        return new EdgeMappingHelper(interpreter).getEdgeTargetCandidates(this, semanticOrigin, diagram);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl#getEdgeSourceCandidates(EObject,
     *      DDiagram)
     */
    @Override
    public EList<EObject> getEdgeSourceCandidates(final EObject semanticOrigin, final DDiagram diagram) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticOrigin);
        return new EdgeMappingHelper(interpreter).getEdgeSourceCandidates(this, semanticOrigin, diagram);
    }

    /*
     * Behavior inherited from DiagramElementMapping
     */

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#checkPrecondition(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean checkPrecondition(final EObject modelElement, final EObject container, final EObject containerView) {
        return SiriusElementMappingSpecOperations.checkPrecondition(this, modelElement, container, containerView);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#getAllMappings()
     */
    @Override
    public EList<DiagramElementMapping> getAllMappings() {
        return new BasicEList.UnmodifiableEList<DiagramElementMapping>(0, new Object[0]);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramElementMappingImpl#isFrom(org.eclipse.sirius.viewpoint.DMappingBased)
     */
    @Override
    public boolean isFrom(final DMappingBased element) {
        return SiriusElementMappingSpecOperations.isFrom(this, element);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.EdgeMappingImpl#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + " " + getName(); //$NON-NLS-1$
    }

}
