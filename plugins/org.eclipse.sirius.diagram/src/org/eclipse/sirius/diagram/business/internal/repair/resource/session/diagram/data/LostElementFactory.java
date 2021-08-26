/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Factory of all lost elements.
 * 
 * @author dlecan
 */
public final class LostElementFactory {

    private LostElementFactory() {
        // Nothing.
    }

    /**
     * Create lost node data from diagram element.
     * 
     * @param diagramElement
     *            Diagram element.
     * @return Lost node data.
     */
    public static LostNodeData createLostNodeData(final DDiagramElement diagramElement) {
        Option<? extends RepresentationElementMapping> mapping = new DDiagramElementQuery(diagramElement).getMapping();

        LostNodeData data = new LostNodeData();
        data.setTarget(diagramElement.getTarget());
        data.setMapping(mapping.get());
        data.setParentData(LostElementFactory.createLostNodeParentData(diagramElement));
        return data;
    }

    /**
     * Create lost edge data from diagram element.
     * 
     * @param diagramElement
     *            Diagram element.
     * @return Lost edge data.
     */
    public static LostEdgeData createLostEdgeData(final DDiagramElement diagramElement) {
        Option<? extends RepresentationElementMapping> mapping = new DDiagramElementQuery(diagramElement).getMapping();

        final LostEdgeData data = new LostEdgeData();
        data.setTarget(diagramElement.getTarget());
        data.setMapping(mapping.get());

        final EdgeTarget sourceNode = ((DEdge) diagramElement).getSourceNode();
        final EdgeTarget targetNode = ((DEdge) diagramElement).getTargetNode();
        if (sourceNode instanceof DDiagramElement && LostElementFactory.isCompleteDiagramElement((DDiagramElement) sourceNode)) {
            final LostNodeData sourceData = LostElementFactory.createLostNodeData((DDiagramElement) sourceNode);
            data.setSourceData(sourceData);

            if (targetNode instanceof DDiagramElement && LostElementFactory.isCompleteDiagramElement((DDiagramElement) targetNode)) {
                final LostNodeData targetData = LostElementFactory.createLostNodeData((DDiagramElement) targetNode);
                data.setTargetData(targetData);
            }
        }

        return data;
    }

    /**
     * Check if diagram element is complete.
     * 
     * @param diagElement
     *            Diagram element to check. .
     * @return <code>true</code> if diagram element and mapping and target are
     *         not <code>null</code>.
     */
    public static boolean isCompleteDiagramElement(final DDiagramElement diagElement) {
        return diagElement != null && diagElement.getDiagramElementMapping() != null && diagElement.getTarget() != null && !diagElement.getTarget().eIsProxy();
    }

    private static ILostElementDataContainer createLostNodeParentData(final DDiagramElement diagElement) {
        final EObject eContainer = diagElement.eContainer();

        ILostElementDataContainer parent = null;

        if (eContainer != null) {
            if (eContainer instanceof DDiagram) {
                final LostDiagramData parentData = new LostDiagramData();
                if (eContainer instanceof DSemanticDecorator) {
                    parentData.setTarget(((DSemanticDecorator) eContainer).getTarget());
                }
                parent = parentData;

            } else if (eContainer instanceof DDiagramElement) {
                final DDiagramElement containerDiagramElement = (DDiagramElement) eContainer;

                if (LostElementFactory.isCompleteDiagramElement(containerDiagramElement)) {
                    parent = LostElementFactory.createLostNodeData(containerDiagramElement);
                }
            }
        }
        return parent;
    }
}
