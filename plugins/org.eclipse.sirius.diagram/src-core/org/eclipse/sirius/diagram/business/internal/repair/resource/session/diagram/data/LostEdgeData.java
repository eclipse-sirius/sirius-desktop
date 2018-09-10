/*******************************************************************************
 * Copyright (c) 2013, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.EdgeMappingHelper;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Class to handle lost edge data.
 * 
 * @author dlecan
 */
public class LostEdgeData extends LostElementDataWithMapping {
    private ILostElementDataContainer sourceData;

    private ILostElementDataContainer targetData;

    /**
     * Sets the value of sourceData to sourceData.
     * 
     * @param sourceData
     *            The sourceData to set.
     */
    public void setSourceData(final ILostElementDataContainer sourceData) {
        this.sourceData = sourceData;
    }

    /**
     * Returns the sourceData.
     * 
     * @return The sourceData.
     */
    public ILostElementDataContainer getSourceData() {
        return sourceData;
    }

    /**
     * Sets the value of targetData to targetData.
     * 
     * @param targetData
     *            The targetData to set.
     */
    public void setTargetData(final ILostElementDataContainer targetData) {
        this.targetData = targetData;
    }

    /**
     * Returns the targetData.
     * 
     * @return The targetData.
     */
    public ILostElementDataContainer getTargetData() {
        return targetData;
    }

    /**
     * {@inheritDoc}
     * 
     * @return {@link LostElementDataState#CREATED} if the edge was created.
     */
    @Override
    protected LostElementDataState doRecreateNonExistingLostElement(final DSemanticDiagram designerDiagram) {
        LostElementDataState result = super.doRecreateNonExistingLostElement(designerDiagram);
        if (getSourceData() != null && getTargetData() != null && getMapping() instanceof EdgeMapping) {

            final DDiagramElement sourceElement = LostElementDataUtil.findDesignerDiagramElement(designerDiagram, getSourceData());
            final DDiagramElement targetElement = LostElementDataUtil.findDesignerDiagramElement(designerDiagram, getTargetData());

            if (sourceElement instanceof EdgeTarget && targetElement instanceof EdgeTarget) {

                final EdgeMapping edgeMapping = (EdgeMapping) getMapping();
                IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(designerDiagram);
                final DEdge dEdge = new EdgeMappingHelper(interpreter).createEdge(edgeMapping, (EdgeTarget) sourceElement, (EdgeTarget) targetElement, designerDiagram, getTarget());
                if (designerDiagram.getOwnedDiagramElements().add(dEdge)) {
                    result = LostElementDataState.CREATED;
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doIsSimilarTo(final DDiagramElement diagramElement, final RepresentationElementMapping extractedMapping) {
        boolean result = super.doIsSimilarTo(diagramElement, extractedMapping);

        if (result && diagramElement instanceof DEdge) {
            final EdgeTarget sourceNode = ((DEdge) diagramElement).getSourceNode();
            final EdgeTarget targetNode = ((DEdge) diagramElement).getTargetNode();

            if (getSourceData() != null && getTargetData() != null && sourceNode instanceof DDiagramElement && targetNode instanceof DDiagramElement) {
                result = getSourceData().isSimilarTo((DDiagramElement) sourceNode) && getTargetData().isSimilarTo((DDiagramElement) targetNode);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String result = super.toString() + SEPARATOR;
        if (sourceData == null) {
            result += "No source data"; //$NON-NLS-1$
        } else {
            result += "Source data: " + sourceData.toString(); //$NON-NLS-1$
        }

        if (targetData == null) {
            result += "No target data"; //$NON-NLS-1$
        } else {
            result += "Target data: " + targetData.toString(); //$NON-NLS-1$
        }

        return result;
    }
}
