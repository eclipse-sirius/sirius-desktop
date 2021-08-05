/*******************************************************************************
 * Copyright (c) 2019, 2021 THALES GLOBAL SERVICES
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
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.business.internal.migration.ActivatedFilterSortingMigrationParticipant;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
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
 * A {@link AbstractRepresentationsFileMigrationParticipant} that makes sure that the labels on border remain visually
 * fixed by the end-user (by moving its GMF coordinates).
 * 
 * @author Laurent Redor
 */
public class LabelOnBorderMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added initially (in 6.1.4).
     */
    public static final Version MIGRATION_VERSION_6_1_4 = new Version("14.1.4.201908131800"); //$NON-NLS-1$

    /**
     * The VP version for which this migration is added initially (in 6.3.0) or at least the last version of migration
     * participant added in 6.3.0.
     */
    public static final Version MIGRATION_VERSION_6_3_0 = ActivatedFilterSortingMigrationParticipant.MIGRATION_VERSION_FILTER_SORTING;

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("14.3.1.202003261200"); //$NON-NLS-1$

    private boolean migrationOccured;

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        // If the previous migration participant has been done (only in branch 6.1.4 or 6.3.0) and the current migration
        // participant has not been done, we do a migration.
        if ((loadedVersion.compareTo(MIGRATION_VERSION_6_1_4) == 0 || loadedVersion.compareTo(MIGRATION_VERSION_6_3_0) >= 0) && loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
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
                            updateChangeId(dAnalysis, dDiagram);
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
     * Check that the GMF coordinates is "synchronized" with what is supposed to be before the previous migration, ie:
     * <UL>
     * <LI>For North or South
     * <UL>
     * <LI>If the label is larger than the Node, centered (but for this the x coordinate for GMF is equal to 0)</LI>
     * <LI>Otherwise, x between 0 and "width" of node, label is not authorized beyond these limits</LI>
     * </UL>
     * </LI>
     * <LI>For East or West, y between 0 and "height" of node, label is not authorized beyond these limits</LI>
     * </UL>
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
                        Location currentNodeLocation = (Location) nodeLayoutConstraint;
                        if (currentLabelLocation.getY() == -labelSize.height() + IBorderItemOffsets.NO_OFFSET.height()
                                || currentLabelLocation.getY() == nodeSize.getHeight() - IBorderItemOffsets.NO_OFFSET.height()) {
                            // North or South side
                            isLabelMoved = computeNewLocationForNorthOrSouthSide(labelSize, nodeSize, currentLabelLocation, currentNodeLocation) || isLabelMoved;
                        } else {
                            // East or West side
                            isLabelMoved = computeNewLocationForEastOrWestSide(labelSize, nodeSize, currentLabelLocation, currentNodeLocation) || isLabelMoved;
                        }
                    }
                }
            }
        }
        return isLabelMoved;
    }

    private boolean computeNewLocationForNorthOrSouthSide(Dimension labelSize, Size nodeSize, Location currentLabelLocation, Location currentNodeLocation) {
        boolean isLabelMoved = false;
        if (labelSize.width() > nodeSize.getWidth()) {
            // If label is larger than the node, the label will be displayed centered but the GMF coordinate is 0 in
            // this case.
            if (currentLabelLocation.getX() != 0) {
                isLabelMoved = true;
                currentLabelLocation.setX(0);
            }
        } else if (currentLabelLocation.getX() < 0) {
            // Set x to 0 (to be in the node bounds)
            isLabelMoved = true;
            currentLabelLocation.setX(0);
        } else if (currentLabelLocation.getX() + labelSize.width() > nodeSize.getWidth()) {
            // Set x according to the max right location (to be in the node bounds)
            isLabelMoved = true;
            currentLabelLocation.setX(nodeSize.getWidth() - labelSize.width());
        }
        return isLabelMoved;
    }

    private boolean computeNewLocationForEastOrWestSide(Dimension labelSize, Size nodeSize, Location currentLabelLocation, Location currentNodeLocation) {
        boolean isLabelMoved = false;
        if (currentLabelLocation.getY() < 0) {
            // Set y to 0 (to be in the node bounds)
            isLabelMoved = true;
            currentLabelLocation.setY(0);
        } else if (currentLabelLocation.getY() + labelSize.height() > nodeSize.getHeight()) {
            // Set y according to the max right location (to be in the node bounds)
            isLabelMoved = true;
            currentLabelLocation.setY(nodeSize.getHeight() - labelSize.height());
        }
        return isLabelMoved;
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
