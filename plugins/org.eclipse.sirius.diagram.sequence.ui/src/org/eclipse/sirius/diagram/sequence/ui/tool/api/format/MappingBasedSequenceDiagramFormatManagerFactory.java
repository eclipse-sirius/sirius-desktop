/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.api.format;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.format.MappingBasedSiriusFormatManagerFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedDiagramContentDuplicationSwitch;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;

/**
 * A {@link MappingBasedSiriusFormatManagerFactory} specialized for the management of sequence diagrams.
 * 
 * @author adieumegard
 */
public class MappingBasedSequenceDiagramFormatManagerFactory extends MappingBasedSiriusFormatManagerFactory {

    /**
     * The singleton INSTANCE.
     */
    protected static final MappingBasedSequenceDiagramFormatManagerFactory INSTANCE = new MappingBasedSequenceDiagramFormatManagerFactory();

    /**
     * gives access to the singleton instance of <code>MappingBasedSiriusFormatManagerFactory</code>.
     * 
     * @return the singleton instance
     */
    public static MappingBasedSequenceDiagramFormatManagerFactory getInstance() {
        return INSTANCE;
    }

    @Override
    protected void populateDiagramFromSourceDiagram(DDiagram sourceDiagram, Map<EObject, EObject> correspondenceMap, Session targetSession, DSemanticDiagram targetDiagram) {
        diagramContentDuplicationSwitch = new MappingBasedDiagramContentDuplicationSwitch(targetDiagram, correspondenceMap, targetSession);
        diagramContentDuplicationSwitch.doSwitch(sourceDiagram);
    }

    @Override
    public DDiagram applyFormatOnDiagram(Session sourceSession, DDiagram sourceDiagram, Map<EObject, EObject> correspondenceMap, Session targetSession, DDiagram targetDiagram, boolean copyNotes) {
        diagramContentDuplicationSwitch = new MappingBasedDiagramContentDuplicationSwitch((DSemanticDiagram) targetDiagram, correspondenceMap, targetSession);
        diagramContentDuplicationSwitch.doSwitch(sourceDiagram);
        return super.applyFormatOnDiagram(sourceSession, sourceDiagram, correspondenceMap, targetSession, targetDiagram, copyNotes);
    }

    @Override
    protected void applyFormatOnDiagram(DiagramEditPart sourceDiagramEditPart, Map<EObject, EObject> correspondenceMap, DiagramEditPart targetDiagramEditPart) {
        formatDataManager = new MappingBasedSiriusFormatDataManager(correspondenceMap);
        formatDataManager.storeFormatData(sourceDiagramEditPart);

        for (Entry<DDiagramElement, DDiagramElement> entry : diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().entrySet()) {
            View sourceGmfView = SiriusGMFHelper.getGmfView(entry.getKey());
            View targetGmfView = SiriusGMFHelper.getGmfView(entry.getValue());
            if (sourceGmfView instanceof Node && targetGmfView instanceof Node) {
                Node sourceNode = (Node) sourceGmfView;
                Node targetNode = (Node) targetGmfView;
                copyNodeLayout(sourceNode, targetNode);
                formatDataManager.copySiriusStyle(entry.getKey(), entry.getValue());
                formatDataManager.copyGMFStyle(sourceNode, targetNode);
            } else if (sourceGmfView instanceof Edge && targetGmfView instanceof Edge) {
                Edge sourceEdge = (Edge) sourceGmfView;
                Edge targetEdge = (Edge) targetGmfView;
                copyEdgeLayout(sourceEdge, targetEdge);
                formatDataManager.copySiriusStyle(entry.getKey(), entry.getValue());
                formatDataManager.copyGMFStyle(sourceEdge, targetEdge);
            }
        }
        // formatDataManager.applyStyle(targetDiagramEditPart);
    }

    private void copyNodeLayout(Node sourceGmfView, Node targetGmfView) {
        LayoutConstraint layoutConstraintCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceGmfView.getLayoutConstraint());
        targetGmfView.setLayoutConstraint(layoutConstraintCopy);
    }

    private void copyEdgeLayout(Edge sourceEdge, Edge targetEdge) {
        // Copy
        Bendpoints bendpointsCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceEdge.getBendpoints());
        Anchor sourceAnchorCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceEdge.getSourceAnchor());
        Anchor targetAnchorCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceEdge.getTargetAnchor());

        // Apply
        targetEdge.setBendpoints(bendpointsCopy);
        targetEdge.setSourceAnchor(sourceAnchorCopy);
        targetEdge.setTargetAnchor(targetAnchorCopy);

        // Copy routing style as it is considered format
        Style routingStyle = sourceEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        Style targetRoutingStyle = targetEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        if (targetRoutingStyle != null) {
            targetEdge.getStyles().remove(targetRoutingStyle);
        }
        targetEdge.getStyles().add(SiriusCopierHelper.copyWithNoUidDuplication(routingStyle));

        copyLabelLayout(sourceEdge, targetEdge);
    }

    private void copyLabelLayout(Edge sourceEdge, Edge targetEdge) {
        final Node sourceLabelNode = SiriusGMFHelper.getLabelNode(sourceEdge);
        final Node targetLabelNode = SiriusGMFHelper.getLabelNode(targetEdge);

        copyNodeLayout(sourceLabelNode, targetLabelNode);
    }
}
