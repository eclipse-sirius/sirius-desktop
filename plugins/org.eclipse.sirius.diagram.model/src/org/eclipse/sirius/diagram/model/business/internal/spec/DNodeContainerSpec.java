/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.model.business.internal.spec;

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.impl.DNodeContainerImpl;
import org.eclipse.sirius.diagram.model.business.internal.operations.DDiagramElementContainerOperations;
import org.eclipse.sirius.diagram.model.business.internal.operations.DDiagramElementSpecOperations;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of DNodeContainerImpl.java.
 *
 * @author cbrun, mchauvin, ymortier
 */
public class DNodeContainerSpec extends DNodeContainerImpl {

    @Override
    public DiagramElementMapping getMapping() {
        return getActualMapping();
    }

    @Override
    public Style getStyle() {
        return getOwnedStyle();
    }

    @Override
    public DDiagram getParentDiagram() {
        return DDiagramElementSpecOperations.getParentDiagram(this);
    }

    @Override
    public EList<DDiagramElement> getElements() {
        final EList<DDiagramElement> result = new BasicEList<>();
        result.addAll(getOwnedBorderedNodes());
        result.addAll(getOwnedDiagramElements());
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagramElementContainer_Elements(), result.size(), result.toArray());
    }

    /*
     * Behavior that should come thanks to viewpointelementcontainer.
     */

    @Override
    public EList<DNode> getNodes() {
        final Collection<AbstractDNode> result = DDiagramElementContainerOperations.getNodes(this);
        Collection<DNode> dNodeResult = result.stream().filter(DNode.class::isInstance).map(DNode.class::cast).toList();
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagramElementContainer_Nodes(), dNodeResult.size(), dNodeResult.toArray());
    }

    @Override
    public EList<DDiagramElementContainer> getContainers() {
        final Collection<DDiagramElementContainer> result = DDiagramElementContainerOperations.getContainers(this);
        return new EcoreEList.UnmodifiableEList<>(eInternalContainer(), DiagramPackage.eINSTANCE.getDDiagram_Containers(), result.size(), result.toArray());
    }

    @Override
    public EList<DDiagramElementContainer> getContainersFromMapping(final ContainerMapping mapping) {
        return DDiagramElementContainerOperations.getContainersFromMapping(this, mapping);

    }

    @Override
    public EList<DNode> getNodesFromMapping(final NodeMapping mapping) {
        return DDiagramElementContainerOperations.getNodesFromMapping(this, mapping);

    }

    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return DDiagramElementContainerOperations.getDragAndDropDescription(this);
    }

    @Override
    public String toString() {
        return getName() != null ? getName() : ""; //$NON-NLS-1$
    }

}
