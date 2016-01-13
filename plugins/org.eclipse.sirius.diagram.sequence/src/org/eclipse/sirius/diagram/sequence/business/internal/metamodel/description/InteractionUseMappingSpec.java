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
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.SiriusElementMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.sequence.description.impl.InteractionUseMappingImpl;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Implementation of InteractionUseMapping.
 * 
 * @author pcdavid
 */
public class InteractionUseMappingSpec extends InteractionUseMappingImpl implements IContainerMappingExt {

    private final Map<EObject, EList<DSemanticDecorator>> viewContainerDone = new HashMap<EObject, EList<DSemanticDecorator>>();

    private final Map<EObjectCouple, EList<EObject>> candidatesCache = new WeakHashMap<EObjectCouple, EList<EObject>>();

    @Override
    public Map<EObject, EList<DSemanticDecorator>> getViewContainerDone() {
        return viewContainerDone;
    }

    @Override
    public Map<EObjectCouple, EList<EObject>> getCandidatesCache() {
        return candidatesCache;
    }

    @Override
    public EList<NodeMapping> getAllNodeMappings() {
        final Collection<NodeMapping> result = ContainerMappingHelper.getAllNodeMappings(this);
        return new EcoreEList.UnmodifiableEList<NodeMapping>(eInternalContainer(), DescriptionPackage.eINSTANCE.getContainerMapping_AllNodeMappings(), result.size(), result.toArray());
    }

    @Override
    public EList<ContainerMapping> getAllContainerMappings() {
        final Collection<ContainerMapping> result = ContainerMappingHelper.getAllContainerMappings(this);
        return new EcoreEList.UnmodifiableEList<ContainerMapping>(eInternalContainer(), DescriptionPackage.eINSTANCE.getContainerMapping_AllContainerMappings(), result.size(), result.toArray());
    }

    @Override
    public void clearDNodesDone() {
        ContainerMappingHelper.clearDNodesDone(this);
    }

    @Override
    public EList<DDiagramElement> findDNodeFromEObject(final EObject object) {
        return ContainerMappingHelper.findDNodeFromEObject(this, object);
    }

    @Override
    public void addDoneNode(final DSemanticDecorator node) {
        ContainerMappingHelper.addDoneNode(this, node);
    }

    @Override
    public ContainerStyle getBestStyle(final EObject modelElement, final EObject viewVariable, final EObject containerVariable) {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement);
        return (ContainerStyle) new MappingHelper(interpreter).getBestStyle(this, modelElement, viewVariable, containerVariable, new EObjectQuery(viewVariable).getParentDiagram().get());
    }

    /*
     * Here we add the behavior we should inherit from AbstractNodeMapping
     */

    @Override
    public void createBorderingNodes(EObject modelElement, DDiagramElement vpElement, Collection filterSemantic, DDiagram viewPoint) {
        AbstractNodeMappingSpecOperations.createBorderingNodes(this, modelElement, vpElement, filterSemantic, viewPoint);
    }

    /*
     * Behavior inherited from DiagramElementMapping
     */

    @Override
    public boolean checkPrecondition(final EObject modelElement, final EObject container, final EObject containerView) {
        return SiriusElementMappingSpecOperations.checkPrecondition(this, modelElement, container, containerView);
    }

    @Override
    public EList<DiagramElementMapping> getAllMappings() {
        return ContainerMappingHelper.getAllMappings(this);
    }

    @Override
    public boolean isFrom(final DMappingBased element) {
        return SiriusElementMappingSpecOperations.isFrom(this, element);
    }

    @Override
    public String toString() {
        return new StringBuffer(getClass().getName()).append(" ").append(getName()).toString(); //$NON-NLS-1$
    }

    @Override
    public EList<NodeMapping> getAllBorderedNodeMappings() {
        return AbstractNodeMappingSpecOperations.getAllBorderedNodeMappings(this);
    }
}
