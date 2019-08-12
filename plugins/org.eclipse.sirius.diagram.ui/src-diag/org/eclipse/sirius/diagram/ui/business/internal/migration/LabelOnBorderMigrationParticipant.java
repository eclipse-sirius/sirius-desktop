/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Version;

/**
 * A {@link AbstractRepresentationsFileMigrationParticipant} that makes sure that the centered label on border remain
 * visually fixed by the end-user (by moving its GMF coordinates). This migration participant is enabled through the VM
 * arg "org.eclipse.sirius.experimental.labelOnBorderImprovement" (as its associated feature). Otherwise, it has no
 * effect and the version of the aird file is not changed.
 * 
 * @author Laurent Redor
 * 
 */
public class LabelOnBorderMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("14.3.0.201908231800"); //$NON-NLS-1$

    private boolean migrationOccured;

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            super.postLoad(dAnalysis, loadedVersion);
            StringBuilder sb = new StringBuilder(Messages.LabelOnBorderMigrationParticipant_title);
            dAnalysis.getOwnedViews().stream().flatMap(view -> new DViewQuery(view).getLoadedRepresentations().stream()).filter(rep -> rep instanceof DDiagram).map(DDiagram.class::cast)
                    .forEach(dDiagram -> {
                        boolean isLabelMoved = false;
                        Collection<Node> nodesWithLabelOnBorder = getNodesWithLabelOnBorder(dDiagram);
                        for (Node node : nodesWithLabelOnBorder) {
                            isLabelMoved = checkAndFixGMFCoordinatesOfLabelOnBorder(node) || isLabelMoved;
                        }
                        if (isLabelMoved) {
                            migrationOccured = true;
                            sb.append(MessageFormat.format(Messages.LabelOnBorderMigrationParticipant_labelsModified, dDiagram.getName()));
                        }
                    });
            if (migrationOccured) {
                DiagramPlugin.getDefault().logInfo(sb.toString());
                migrationOccured = false;
            }
        }
    }

    /**
     * Check that the GMF coordinates is "synchronized" with what is supposed to be displayed.
     * 
     * @param node
     *            a node with a label on border
     * @return true if the label has been fixed, false otherwise
     */
    private boolean checkAndFixGMFCoordinatesOfLabelOnBorder(Node node) {
        boolean isLabelMoved = false;
        for (Object child : node.getChildren()) {
            if (child instanceof Node) {
                Node childNode = (Node) child;
                int id = SiriusVisualIDRegistry.getVisualID(childNode.getType());
                if (id == NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID || id == NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID || id == NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID
                        || id == NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID) {
                    LayoutConstraint labelLayoutConstraint = childNode.getLayoutConstraint();
                    LayoutConstraint nodeLayoutConstraint = node.getLayoutConstraint();
                    if (labelLayoutConstraint instanceof Location && nodeLayoutConstraint instanceof Size) {
                        Dimension labelSize;
                        if (Display.getCurrent() != null) {
                            labelSize = GMFHelper.getLabelDimension(node, new Dimension(50, 20));
                        } else {
                            // We call the method GMFHelper.getLabelDimension in UI Thread to have "real dimension".
                            RunnableWithResult<Dimension> getLabelDimension = new RunnableWithResult.Impl<Dimension>() {
                                @Override
                                public void run() {
                                    setResult(GMFHelper.getLabelDimension(node, new Dimension(50, 20)));
                                }
                            };
                            EclipseUIUtil.displaySyncExec(getLabelDimension);
                            labelSize = getLabelDimension.getResult();
                        }
                        Size nodeSize = (Size) nodeLayoutConstraint;
                        Location currentLabelLocation = (Location) labelLayoutConstraint;
                        if (labelSize.width() > nodeSize.getWidth() && (currentLabelLocation.getY() < 0 || currentLabelLocation.getY() > nodeSize.getHeight())) {
                            // We have to compute new "centered location" for label larger that its node and on the top
                            // or bottom border
                            isLabelMoved = computeNewLocation(labelSize, nodeSize, currentLabelLocation) || isLabelMoved;
                        }
                    }
                }
            }
        }
        return isLabelMoved;
    }

    private boolean computeNewLocation(Dimension labelSize, Size nodeSize, Location currentLabelLocation) {
        boolean isSameLocation = true;
        int newX = -(labelSize.width() - nodeSize.getWidth()) / 2;
        if (newX != currentLabelLocation.getX()) {
            isSameLocation = false;
            currentLabelLocation.setX(-(labelSize.width() - nodeSize.getWidth()) / 2);
        }
        int newY;
        if (currentLabelLocation.getY() < 0) {
            newY = IBorderItemOffsets.NO_OFFSET.height() - labelSize.height();
        } else {
            newY = nodeSize.getHeight() - IBorderItemOffsets.NO_OFFSET.height();
        }
        if (newY != currentLabelLocation.getY()) {
            isSameLocation = false;
            currentLabelLocation.setY(newY);
        }
        return !isSameLocation;
    }

    /**
     * Get the {@link Node}s with a label on border in the given {@link DDiagram}.
     * 
     * @param dDiagram
     *            the given {@link DDiagram}.
     * @return the {@link Node}s with a label on border in the given {@link DDiagram}.
     */
    private Collection<Node> getNodesWithLabelOnBorder(DDiagram dDiagram) {
        Collection<Node> nodes = new HashSet<>();
        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
        Option<Diagram> gmfDiagram = query.getAssociatedGMFDiagram();
        if (gmfDiagram.some()) {
            gmfDiagram.get().eAllContents().forEachRemaining(eObject -> {
                if (eObject instanceof Node) {
                    Node node = (Node) eObject;
                    if (isNodeWithLabelOnBorder(node)) {
                        nodes.add(node);
                    }
                }
            });
        }
        return nodes;
    }

    /**
     * Check if the label of the Node is on border (and not inside the node).
     * 
     * @param node
     *            The node to check.
     * 
     * @return true if the label of this <code>node</code> is on border, false otherwise.
     */
    private boolean isNodeWithLabelOnBorder(Node node) {
        int id = SiriusVisualIDRegistry.getVisualID(node.getType());
        if (id == DNodeEditPart.VISUAL_ID || id == DNode2EditPart.VISUAL_ID || id == DNode3EditPart.VISUAL_ID || id == DNode4EditPart.VISUAL_ID) {
            EObject element = node.getElement();
            return element instanceof DNode && ((DNode) element).getLabelPosition().equals(LabelPosition.BORDER_LITERAL);
        }
        return false;
    }
}
