/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.AbstractDNodeSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementContainerSpecOperations;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.impl.DNodeListImpl;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.Style;

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

/**
 * Implementation of {@link org.eclipse.sirius.viewpoint.DNodeList}.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public class DNodeListSpec extends DNodeListImpl {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getElements()
     */
    @Override
    public EList<DDiagramElement> getElements() {
        final EList<DDiagramElement> result = new BasicEList<DDiagramElement>();
        result.addAll(getOwnedBorderedNodes());
        result.addAll(getOwnedElements());
        return new EcoreEList.UnmodifiableEList<DDiagramElement>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagramElementContainer_Elements(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#refresh()
     */
    @Override
    public void refresh() {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(this);
        new ContainerMappingHelper(interpreter).updateContainer((IContainerMappingExt) getActualMapping(), this);
        for (DNodeListElement iterElement : this.getOwnedElements()) {
            iterElement.refresh();
        }

        AbstractDNodeSpecOperations.refreshBorderNodes(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getMapping()
     */
    @Override
    public DiagramElementMapping getMapping() {
        return getActualMapping();
    }

    /*
     * Behavior that should come thanks to viewpointelementcontainer.
     */

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl#getParentDiagram()
     */
    @Override
    public DDiagram getParentDiagram() {
        return DDiagramElementContainerSpecOperations.getParentDiagram(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getNodes()
     */
    @Override
    public EList<DNode> getNodes() {
        final Collection<AbstractDNode> result = DDiagramElementContainerSpecOperations.getNodes(this);
        Collection<DNode> dNodeResult = new ArrayList<DNode>();
        for (AbstractDNode dNode : Collections2.filter(result, Predicates.instanceOf(DNode.class))) {
            dNodeResult.add((DNode) dNode);
        }
        return new EcoreEList.UnmodifiableEList<DNode>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagramElementContainer_Nodes(), dNodeResult.size(), dNodeResult.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getContainers()
     */
    @Override
    public EList<DDiagramElementContainer> getContainers() {
        final Collection<DDiagramElementContainer> result = DDiagramElementContainerSpecOperations.getContainers(this);
        return new EcoreEList.UnmodifiableEList<DDiagramElementContainer>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Containers(), result.size(), result.toArray());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getContainersFromMapping(org.eclipse.sirius.viewpoint.description.ContainerMapping)
     */
    @Override
    public EList<DDiagramElementContainer> getContainersFromMapping(final ContainerMapping mapping) {
        return DDiagramElementContainerSpecOperations.getContainersFromMapping(this, mapping);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getNodesFromMapping(org.eclipse.sirius.viewpoint.description.NodeMapping)
     */
    @Override
    public EList<DNode> getNodesFromMapping(final NodeMapping mapping) {
        return DDiagramElementContainerSpecOperations.getNodesFromMapping(this, mapping);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getStyle()
     */
    @Override
    public Style getStyle() {
        return getOwnedStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementContainerImpl#getDragAndDropDescription()
     */
    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return DDiagramElementContainerSpecOperations.getDragAndDropDescription(this);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "NodeList " + getName(); //$NON-NLS-1$
    }
}
