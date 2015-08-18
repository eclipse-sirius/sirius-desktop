/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.business.internal.migration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.ui.business.internal.diagramtype.SequenceCollapseUpdater;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Migration contribution for sequence diagram part of representations file.
 * 
 * @author mporhel
 */
public class SequenceDiagramRepresentationsFileMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Sequence Message flag reset migration version.
     */
    public static final Version MESSAGE_FLAG_MIGRATION_VERSION = new Version("8.1.1"); //$NON-NLS-1$

    /**
     * Sequence GMF bounds migration version.
     */
    public static final Version GMF_BOUNDS_MIGRATION_VERSION = new Version("6.7.0.201302181200"); //$NON-NLS-1$

    private static final Version GMF_BOUNDS_ALREADY_MIGRATED_VERSION = new Version("6.5.3"); //$NON-NLS-1$

    private static final Version GMF_BOUNDS_NOT_MIGRATED_VERSION = new Version("6.6.0"); //$NON-NLS-1$

    /**
     * The latest VP version for this participant.
     */
    private static final Version MIGRATION_VERSION = MESSAGE_FLAG_MIGRATION_VERSION;

    private Predicate<Node> isNode = new IsNode();

    private Predicate<Node> isCollapsedNode = new IsCollapsedNode();

    private Predicate<Node> isDirectlyCollapsedNode = new IsDirectlyCollapsedNode();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.migration.IMigrationParticipant#getMigrationVersion()
     */
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    public void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        super.postLoad(dAnalysis, loadedVersion);

        if (loadedVersion.compareTo(GMF_BOUNDS_MIGRATION_VERSION) < 0) {
            // The 6.5.3 maintenance version already contains the migration,
            // migration should be done for versions in
            // [0.0.0, 6.5.3[ U [6.6.0, 6.7.0[.
            if (loadedVersion.compareTo(GMF_BOUNDS_ALREADY_MIGRATED_VERSION) < 0 || loadedVersion.compareTo(GMF_BOUNDS_NOT_MIGRATED_VERSION) >= 0) {
                List<Diagram> sequenceDiagrams = getGMFSequenceDiagrams(dAnalysis);
                if (!sequenceDiagrams.isEmpty()) {
                    migrateGMFBoundsOfCollapsedBorderedNode(sequenceDiagrams);
                }
            }
        }

        if (loadedVersion.compareTo(MESSAGE_FLAG_MIGRATION_VERSION) < 0) {
            List<SequenceDDiagram> sequenceDDiagrams = getSequenceDDiagrams(dAnalysis);
            if (!sequenceDDiagrams.isEmpty()) {
                for (SequenceDDiagram diagram : sequenceDDiagrams) {
                    for (DEdge edge : diagram.getEdges()) {
                        for (AbsoluteBoundsFilter flag : Iterables.filter(edge.getGraphicalFilters(), AbsoluteBoundsFilter.class)) {
                            // Reset default values, x and width are not used
                            // for Message standard layout.
                            // Specific flags set by commands and refresh
                            // extensions (LayoutConstants.TOOL_CREATION_FLAG,
                            // LayoutConstants.TOOL_CREATION_FLAG_FROM_SEMANTIC,
                            // LayoutConstants.EXTERNAL_CHANGE_FLAG) to detect
                            // unsafe situations have been used by the
                            // SequenceLayout triggered by those commands. But
                            // the reset was not done for Message on previous
                            // versions.
                            flag.setX((Integer) DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_X().getDefaultValue());
                            flag.setWidth((Integer) DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Width().getDefaultValue());
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieve all GMF diagrams of the {@link DAnalysis}.
     * 
     * @param dAnalysis
     *            The analysis of the resource to migrate
     * @return Sequence GMF diagrams of this {@link DAnalysis}
     */
    protected List<Diagram> getGMFSequenceDiagrams(DAnalysis dAnalysis) {
        List<Diagram> diagrams = new ArrayList<Diagram>();
        for (SequenceDDiagram diagram : getSequenceDDiagrams(dAnalysis)) {
            DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(diagram);
            Option<Diagram> option = query.getAssociatedGMFDiagram();
            if (option.some()) {
                diagrams.add(option.get());
            }
        }
        return diagrams;
    }

    /**
     * Retrieve all {@link SequenceDDiagram} of the {@link DAnalysis}.
     * 
     * @param dAnalysis
     *            The analysis of the resource to migrate
     * @return {@link SequenceDDiagram} diagrams of this {@link DAnalysis}
     */
    protected List<SequenceDDiagram> getSequenceDDiagrams(DAnalysis dAnalysis) {
        List<SequenceDDiagram> diagrams = new ArrayList<SequenceDDiagram>();
        for (DView view : dAnalysis.getOwnedViews()) {
            Iterables.addAll(diagrams, Iterables.filter(view.getOwnedRepresentations(), SequenceDDiagram.class));
        }
        return diagrams;
    }

    private void migrateGMFBoundsOfCollapsedBorderedNode(List<Diagram> sequenceDiagrams) {
        for (Diagram diagram : sequenceDiagrams) {
            // 1-Add new IndirectlyCollapseFilter
            migrateChildrenOfCollapsedNode(diagram);
            // 2-Set width and height of graphical filters of collapsed nodes
            // (directly or not) with GMF size and set GMF bounds.
            Iterator<Node> viewIterator = Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class), Predicates.and(isNode, isCollapsedNode));
            while (viewIterator.hasNext()) {
                Node node = viewIterator.next();
                DNode dNode = (DNode) node.getElement();

                LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                if (layoutConstraint instanceof Bounds) {
                    Bounds bounds = (Bounds) layoutConstraint;
                    // The GMF node size must be stored in collapse filter (to
                    // can
                    // set this size when this node is expanded).
                    for (GraphicalFilter graphicalFilter : dNode.getGraphicalFilters()) {
                        if (graphicalFilter instanceof CollapseFilter) {
                            ((CollapseFilter) graphicalFilter).setWidth(bounds.getWidth());
                            ((CollapseFilter) graphicalFilter).setHeight(bounds.getHeight());
                        }
                    }
                    // Set new collapsed GMF bounds
                    SequenceCollapseUpdater scbu = new SequenceCollapseUpdater();
                    scbu.collapseBounds(node, dNode);
                }
            }
        }
    }

    /**
     * Add a {@link IndirectlyCollapsedFilter} to the children of CollapsedNode
     * (to retrieve the same behavior as before). The migration of GMF bounds of
     * this indirectly collapsed nodes, if they are bordered nodes, are deal
     * later in method {{@link #migrateGMFBoundsOfBorderedNodes(List)}.
     * 
     * @param diagram
     *            GMF Diagram to migrate.
     */
    private void migrateChildrenOfCollapsedNode(Diagram diagram) {
        List<DDiagramElement> indirectlyCollaspedDDEs = Lists.newArrayList();
        Iterator<Node> viewIterator = Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class), isDirectlyCollapsedNode);
        while (viewIterator.hasNext()) {
            final Node node = viewIterator.next();
            if (node.getElement() instanceof AbstractDNode) {
                AbstractDNode abstractDNode = (AbstractDNode) node.getElement();
                indirectlyCollaspedDDEs.addAll(abstractDNode.getOwnedBorderedNodes());
                if (abstractDNode instanceof DNodeContainer) {
                    DNodeContainer dDiagramElementContainer = (DNodeContainer) abstractDNode;
                    indirectlyCollaspedDDEs.addAll(dDiagramElementContainer.getOwnedDiagramElements());
                } else if (abstractDNode instanceof DNodeList) {
                    DNodeList dNodeList = (DNodeList) abstractDNode;
                    indirectlyCollaspedDDEs.addAll(dNodeList.getOwnedElements());
                }
            }
        }
        for (DDiagramElement indirectlyCollaspedDDE : indirectlyCollaspedDDEs) {
            if (!Iterables.any(indirectlyCollaspedDDE.getGraphicalFilters(), Predicates.instanceOf(IndirectlyCollapseFilter.class))) {
                IndirectlyCollapseFilter indirectlyCollapseFilter = DiagramFactory.eINSTANCE.createIndirectlyCollapseFilter();
                indirectlyCollaspedDDE.getGraphicalFilters().add(indirectlyCollapseFilter);
            }
        }
    }

    /**
     * Predicate that checks that :
     * <UL>
     * <LI>The input is a GMF Node,</LI>
     * <LI>and this Node is a viewpoint node.</LI>
     * </UL>
     */
    private static class IsNode implements Predicate<Node> {
        public boolean apply(Node input) {
            int type = SiriusVisualIDRegistry.getVisualID(input.getType());
            return type == DNodeEditPart.VISUAL_ID || type == DNode2EditPart.VISUAL_ID || type == DNode3EditPart.VISUAL_ID || type == DNode4EditPart.VISUAL_ID;
        }
    }

    /**
     * Predicate that checks that this GMF Node is collapsed (directly or not).
     * 
     * No check is done on the border position of a node because we need to
     * handle ObservationPoints.
     */
    private static class IsCollapsedNode implements Predicate<Node> {
        public boolean apply(Node input) {
            return new NodeQuery(input).isCollapsed();
        }
    }

    /**
     * Predicate that checks that this GMF Node is directly collapsed.
     * 
     * No check is done on the border position of a node because we need to
     * handle ObservationPoints.
     */
    private static class IsDirectlyCollapsedNode implements Predicate<Node> {
        public boolean apply(Node input) {
            boolean apply = false;

            int type = SiriusVisualIDRegistry.getVisualID(input.getType());
            boolean result = type == DNode2EditPart.VISUAL_ID || type == DNode4EditPart.VISUAL_ID;
            result = result || type == DNodeEditPart.VISUAL_ID || type == DNode3EditPart.VISUAL_ID;
            result = result || type == DNodeContainerEditPart.VISUAL_ID || type == DNodeContainer2EditPart.VISUAL_ID;
            result = result || type == DNodeListEditPart.VISUAL_ID || type == DNodeList2EditPart.VISUAL_ID;

            if (result) {
                return new NodeQuery(input).isDirectlyCollapsed();
            }
            return apply;
        }
    }
}
