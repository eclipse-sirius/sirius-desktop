/*******************************************************************************
 * Copyright (c) 2013, 2021 THALES GLOBAL SERVICES and others.
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

import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.helper.SiriusDiagramHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Class to handle lost node data.
 * 
 * @author dlecan
 */
public class LostNodeData extends LostElementDataWithMapping implements ILostElementDataContainer {
    private ILostElementDataContainer parentData;

    /**
     * Sets the value of parentData to parentData.
     * 
     * @param parentData
     *            The parentData to set.
     */
    public void setParentData(final ILostElementDataContainer parentData) {
        this.parentData = parentData;
    }

    /**
     * Returns the parentData.
     * 
     * @return The parentData.
     */
    public ILostElementDataContainer getParentData() {
        return parentData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LostElementDataState doRecreateNonExistingLostElement(final DSemanticDiagram designerDiagram) {
        LostElementDataState created = super.doRecreateNonExistingLostElement(designerDiagram);

        final DDiagramElement createdElement = SiriusDiagramHelper.createElement(getMapping(), designerDiagram, getTarget());
        if (createdElement != null) {
            if (parentData == null) {
                if (designerDiagram.getOwnedDiagramElements().add(createdElement)) {
                    created = LostElementDataState.CREATED;
                }
            } else {
                created = parentData.addDiagramElementInCorrespondingParentContainer(designerDiagram, createdElement);
            }
        }
        return created;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String result = super.toString() + SEPARATOR;
        if (parentData == null) {
            result += "no parent data"; //$NON-NLS-1$
        } else {
            result += "Parent data: " + parentData.toString(); //$NON-NLS-1$
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doIsSimilarTo(final DDiagramElement diagramElement, final RepresentationElementMapping extractedMapping) {
        boolean result = false;

        if (super.doIsSimilarTo(diagramElement, extractedMapping)) {
            if (parentData == null) {
                result = true;
            } else if (diagramElement.eContainer() instanceof DSemanticDecorator) {
                result = parentData.isSimilarTo((DSemanticDecorator) diagramElement.eContainer());
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LostElementDataState addDiagramElementInCorrespondingParentContainer(final DDiagram designerDiagram, final DDiagramElement createdElement) {
        LostElementDataState result = LostElementDataState.NOT_CREATED;

        final DDiagramElement parentVPContainer = LostElementDataUtil.findDesignerDiagramElement(designerDiagram, this);

        if (parentVPContainer instanceof AbstractDNode) {
            final AbstractDNode otherAbstractNode = (AbstractDNode) parentVPContainer;
            boolean created = false;

            RepresentationElementMapping mapping = otherAbstractNode.getMapping();
            if (createdElement instanceof DNode && mapping instanceof AbstractNodeMapping
                    && MappingHelper.getAllBorderedNodeMappings((AbstractNodeMapping) mapping).contains(createdElement.getMapping())) {

                created = otherAbstractNode.getOwnedBorderedNodes().add((DNode) createdElement);

            } else if (parentVPContainer instanceof DNodeContainer) {

                created = ((DNodeContainer) parentVPContainer).getOwnedDiagramElements().add(createdElement);

            } else if (parentVPContainer instanceof DNodeList) {

                created = ((DNodeList) parentVPContainer).getOwnedElements().add((DNodeListElement) createdElement);
            }

            if (created) {
                result = LostElementDataState.CREATED;
            }
        }

        return result;
    }
}
