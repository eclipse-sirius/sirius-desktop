/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.WorkspaceImageQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.osgi.framework.Version;

/**
 * This migration participant resizes nodes with Workspace Image style description. The size of some GMF nodes was
 * inconsistent with the size of their draw2D representations. See bug 576423.
 * 
 * @author Glenn Plouhinec
 *
 */
public class WorkspaceImageGMFBoundsMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("15.0.0.202201261500"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            StringBuilder sb = new StringBuilder(Messages.WorkspaceImageGMFBoundsMigrationParticipant_title);
            EList<DView> ownedViews = dAnalysis.getOwnedViews();
            boolean migrationOccurred = false;
            for (DView view : ownedViews) {
                List<DRepresentationDescriptor> loadedRepresentationsDescriptors = new DViewQuery(view).getLoadedRepresentationsDescriptors();
                for (DRepresentationDescriptor descriptor : loadedRepresentationsDescriptors) {
                    if (descriptor.getRepresentation() instanceof DDiagram) {
                        DDiagram dDiagram = (DDiagram) descriptor.getRepresentation();
                        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
                        Option<Diagram> gmfDiagram = query.getAssociatedGMFDiagram();
                        if (gmfDiagram.some()) {
                            boolean migrationOccurredInCurrentDiag = false;
                            String representationName = StringUtil.EMPTY_STRING;
                            for (Object child : gmfDiagram.get().getChildren()) {
                                if (resizeWorkspaceImageGMFBounds(child)) {
                                    migrationOccurred = true;
                                    migrationOccurredInCurrentDiag = true;
                                    representationName = dDiagram.getName();
                                }
                            }
                            if (migrationOccurredInCurrentDiag) {
                                sb.append(MessageFormat.format(Messages.WorkspaceImageGMFBoundsMigrationParticipant_GMFBoundsResized, representationName));
                            }
                        }
                    }
                }
            }
            if (migrationOccurred) {
                DiagramPlugin.getDefault().logInfo(sb.toString());
                migrationOccurred = false;
            }
        }
    }

    private boolean resizeWorkspaceImageGMFBounds(Object child) {
        boolean resized = false;
        if (child instanceof Node && ((Node) child).getLayoutConstraint() instanceof Size) {
            Node node = (Node) child;
            Size size = (Size) node.getLayoutConstraint();
            if (node.getElement() instanceof DNode) {
                DNode dnode = (DNode) node.getElement();
                if (dnode.getStyle() != null) {
                    StyleDescription description = dnode.getStyle().getDescription();
                    resized = resizeGMFNode(size, description, dnode.getWidth(), dnode.getHeight());
                }
            } else if (node.getElement() instanceof DDiagramElementContainer) {
                DDiagramElementContainer dDiagramElementContainer = (DDiagramElementContainer) node.getElement();
                if (dDiagramElementContainer.getStyle() != null) {
                    StyleDescription description = dDiagramElementContainer.getStyle().getDescription();
                    resized = resizeGMFNode(size, description, dDiagramElementContainer.getWidth(), dDiagramElementContainer.getHeight());
                    for (Object o : node.getChildren()) {
                        resized = resizeWorkspaceImageGMFBounds(o);
                    }
                }
            }
        }
        return resized;
    }

    private boolean resizeGMFNode(Size size, StyleDescription description, Integer diagramElementWidth, Integer diagramElementHeight) {
        boolean resized = false;
        if (description instanceof WorkspaceImageDescription && size != null) {
            WorkspaceImageDescription workspaceImageDescription = (WorkspaceImageDescription) description;
            WorkspaceImageQuery workspaceImageQuery = new WorkspaceImageQuery(workspaceImageDescription);
            Integer width = null;
            Integer height = null;
            String sizeComputationExpression = workspaceImageDescription.getSizeComputationExpression();
            if (ResizeKind.NONE == workspaceImageDescription.getResizeKind().getValue()) {
                // If the resize is not authorized, reset the GMF node size according to DNode size or image size if
                // auto-sized.
                if (!StringUtil.isEmpty(sizeComputationExpression) && "-1".equals(sizeComputationExpression.trim()) && workspaceImageQuery.doesImageExist()) { //$NON-NLS-1$
                    // In this case, ie auto-size, use the real size of the image
                    Dimension imageSize = workspaceImageQuery.getDefaultDimension();
                    width = imageSize.width;
                    height = imageSize.height;
                } else if (diagramElementWidth != null && diagramElementHeight != null) {
                    // Otherwise, use the DNode size
                    width = diagramElementWidth * LayoutUtils.SCALE;
                    height = (int) (diagramElementWidth / workspaceImageQuery.getRatio() * LayoutUtils.SCALE);
                }
            } else {
                // If the resize is authorized, this migration changes the GMF node size only if the current GMF node
                // size is the image size (the problem caused by the bug).
                if (!StringUtil.isEmpty(sizeComputationExpression) && !("-1".equals(sizeComputationExpression.trim()) && workspaceImageQuery.doesImageExist())) { //$NON-NLS-1$
                    Dimension imageSize = workspaceImageQuery.getDefaultDimension();
                    if (size.getWidth() == imageSize.width && size.getHeight() == imageSize.height) {
                        if (diagramElementWidth != null && diagramElementHeight != null) {
                            width = diagramElementWidth * LayoutUtils.SCALE;
                            height = (int) (diagramElementWidth / workspaceImageQuery.getRatio() * LayoutUtils.SCALE);
                        }
                    }
                }
            }
            if (width != null && height != null && (size.getHeight() != height || size.getWidth() != width)) {
                size.setWidth(width);
                size.setHeight(height);
                resized = true;
            }
        }
        return resized;
    }
}
