/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper.model;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Utility class to factor customizations for ContainerMapping and related.
 * 
 * @author pcdavid
 */
public final class MappingExtHelper {

    /**
     * Create the helper.
     * 
     */
    private MappingExtHelper() {
    }

    /**
     * Implementation of {@link ContainerMapping#clearDNodesDone()}.
     * 
     * @param self
     *            the container mapping.
     */
    public static void clearDNodesDone(IContainerMappingExt self) {
        self.getViewContainerDone().clear();
    }

    /**
     * Implementation of {@link NodeMapping#clearDNodesDone()}.
     * 
     * @param self
     *            the node mapping.
     */
    public static void clearDNodesDone(INodeMappingExt self) {
        self.getViewNodesDone().clear();
    }

    /**
     * Implementation of {@link ContainerMapping#findDNodeFromEObject(EObject)}.
     * 
     * @param self
     *            the container mapping.
     * @param object
     *            the semantic element.
     * @return the node that has been created by this mapping and the specified EObject as semantic element.
     */
    public static EList<DDiagramElement> findDNodeFromEObject(IContainerMappingExt self, EObject object) {
        EList result = self.getViewContainerDone().get(object);
        if (result == null) {
            result = new BasicEList<DDiagramElement>();
        }
        return result;
    }

    /**
     * Implementation of {@link NodeMapping#findDNodeFromEObject(EObject)}.
     * 
     * @param self
     *            the node mapping.
     * @param object
     *            the semantic element.
     * @return the node found.
     */
    public static EList<DDiagramElement> findDNodeFromEObject(INodeMappingExt self, EObject object) {
        EList result = self.getViewNodesDone().get(object);
        if (result == null) {
            result = new BasicEList<DDiagramElement>();
        }
        return result;
    }

    /**
     * Add a node marked as done in given mapping.
     * 
     * @param nodeMapping
     *            the node mapping to be filled with done node.
     * @param node
     *            the node to mark as done.
     */
    @Deprecated
    public static void addDoneNode(final AbstractNodeMapping nodeMapping, final DSemanticDecorator node) {
        if (node instanceof NodeMapping) {
            addDoneNode((INodeMappingExt) nodeMapping, node);
        } else if (node instanceof ContainerMapping) {
            addDoneNode((IContainerMappingExt) nodeMapping, node);
        } else if (node instanceof NodeMappingImport) {
            addDoneNode((NodeMappingImport) nodeMapping, node);
        } else if (node instanceof ContainerMappingImport) {
            addDoneNode((ContainerMappingImport) nodeMapping, node);
        }
    }

    /**
     * Implementation of {@link ContainerMapping#addDoneNode(DSemanticDecorator)}.
     * 
     * @param self
     *            the container mapping.
     * @param node
     *            the node to add.
     */
    @Deprecated
    private static void addDoneNode(IContainerMappingExt self, DSemanticDecorator node) {
        EList<DSemanticDecorator> list = self.getViewContainerDone().get(node.getTarget());
        if (list == null) {
            list = new BasicEList<DSemanticDecorator>();
            self.getViewContainerDone().put(node.getTarget(), list);
        }
        list.add(node);
    }

    /**
     * Implementation of {@link NodeMapping#addDoneNode(DSemanticDecorator)}.
     * 
     * @param self
     *            the node mapping.
     * @param node
     *            the node to add.
     */
    @Deprecated
    private static void addDoneNode(INodeMappingExt self, DSemanticDecorator node) {
        EList<DSemanticDecorator> list = self.getViewNodesDone().get(node.getTarget());
        if (list == null) {
            list = new BasicEList<DSemanticDecorator>();
            self.getViewNodesDone().put(node.getTarget(), list);
        }
        list.add(node);
    }

    @Deprecated
    private static void addDoneNode(final ContainerMappingImport containerMapping, final DSemanticDecorator node) {
        addDoneNode((IContainerMappingExt) containerMapping, node);
        if (containerMapping.getImportedMapping() != null && containerMapping.getImportedMapping() != containerMapping) {
            addDoneNode(containerMapping.getImportedMapping(), node);
        }
    }

    @Deprecated
    private static void addDoneNode(final NodeMappingImport nodeMapping, final DSemanticDecorator node) {
        addDoneNode((INodeMappingExt) nodeMapping, node);
        if (nodeMapping.getImportedMapping() != null && nodeMapping.getImportedMapping() != nodeMapping) {
            addDoneNode(nodeMapping.getImportedMapping(), node);
        }
    }

}
