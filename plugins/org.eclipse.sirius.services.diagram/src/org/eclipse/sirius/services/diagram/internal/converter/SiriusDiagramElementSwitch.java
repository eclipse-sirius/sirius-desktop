/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.services.diagram.internal.converter;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Switch used to compute the converter of a given diagram element.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramElementSwitch {
    /**
     * Returns the default value of the switch.
     *
     * @return The default value
     */
    protected ISiriusDiagramElementConverter getDefaultValue() {
        return null;
    }

    /**
     * Returns the converter for the given diagram element.
     *
     * @param dDiagramElement
     *            The diagram element to convert
     * @return The converter used to handle the given diagram element
     */
    public ISiriusDiagramElementConverter doSwitch(DDiagramElement dDiagramElement) {
        ISiriusDiagramElementConverter result = this.getDefaultValue();

        switch (dDiagramElement.eClass().getClassifierID()) {
        case DiagramPackage.DNODE:
            if (dDiagramElement instanceof DNode) {
                result = this.caseDNode((DNode) dDiagramElement);
            }
            break;
        case DiagramPackage.DNODE_CONTAINER:
            if (dDiagramElement instanceof DNodeContainer) {
                result = this.caseDNodeContainer((DNodeContainer) dDiagramElement);
            }
            break;
        case DiagramPackage.DNODE_LIST:
            if (dDiagramElement instanceof DNodeList) {
                result = this.caseDNodeList((DNodeList) dDiagramElement);
            }
            break;
        case DiagramPackage.DNODE_LIST_ELEMENT:
            if (dDiagramElement instanceof DNodeListElement) {
                result = this.caseDNodeListElement((DNodeListElement) dDiagramElement);
            }
            break;
        case DiagramPackage.DEDGE:
            if (dDiagramElement instanceof DEdge) {
                result = this.caseDEdge((DEdge) dDiagramElement);
            }
            break;
        default:
            result = this.getDefaultValue();
            break;
        }

        return result;
    }

    /**
     * Returns the converter for a DNode.
     *
     * @param dNode
     *            The DNode
     * @return The converter for a DNode
     */
    public ISiriusDiagramElementConverter caseDNode(DNode dNode) {
        Style style = dNode.getStyle();
        if (style instanceof WorkspaceImage) {
            return new SiriusDiagramImageNodeConverter(dNode);
        } else if (style instanceof Square) {
            return new SiriusDiagramSquareNodeConverter(dNode);
        }
        throw new IllegalArgumentException(); // Not supported yet :)
    }

    /**
     * Returns the converter for a DNodeContainer.
     *
     * @param dNodeContainer
     *            The DNodeContainer
     * @return The converter for a DNodeContainer
     */
    public ISiriusDiagramElementConverter caseDNodeContainer(DNodeContainer dNodeContainer) {
        Style style = dNodeContainer.getStyle();
        if (style instanceof FlatContainerStyle) {
            return new SiriusDiagramGradientNodeConverter(dNodeContainer);
        }
        throw new IllegalArgumentException(); // Not supported yet :)
    }

    /**
     * Returns the converter for a DNodeList.
     *
     * @param dNodeList
     *            The DNodeList
     * @return The converter for a DNodeList
     */
    public ISiriusDiagramElementConverter caseDNodeList(DNodeList dNodeList) {
        return new SiriusDiagramListNodeConverter(dNodeList);
    }

    /**
     * Returns the converter for a DNodeListElement.
     *
     * @param dNodeListElement
     *            The DNodeListElement
     * @return The converter for a DNodeListelement
     */
    public ISiriusDiagramElementConverter caseDNodeListElement(DNodeListElement dNodeListElement) {
        return new SiriusDiagramListElementNodeConverter(dNodeListElement);
    }

    /**
     * Returns the converter for a DEdge.
     *
     * @param dEdge
     *            The DEdge
     * @return The converter for a DEdge
     */
    public ISiriusDiagramElementConverter caseDEdge(DEdge dEdge) {
        return new SiriusDiagramEdgeConverter(dEdge);
    }
}
