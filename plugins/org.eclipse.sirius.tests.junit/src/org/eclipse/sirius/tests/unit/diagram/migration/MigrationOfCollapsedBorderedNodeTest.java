/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV670;
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
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Test if the migration of VP-3833 is correctly launched. This migration
 * changes the GMF layout constraint of collapsed bordered nodes. Before this
 * migration, the size of the bordered node is as if it is not collapsed and the
 * {@link CollapseFilter} associated to the DNode does not contained the size of
 * the node before the collapse.<BR>
 * See
 * {@link org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV670#migrateGMFBoundsOfCollapsedBorderedNode(List)}
 * for more details.
 * 
 * @author lredor
 */
public class MigrationOfCollapsedBorderedNodeTest extends SiriusDiagramTestCase {

    private String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/node/noderefresh.odesign";

    private String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-3833/My.uml";

    private String REPRESENTATIONS_FILE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/do_not_migrate/VP-3833/My.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, REPRESENTATIONS_FILE_PATH);
    }

    /**
     * Predicate that checks that :
     * <UL>
     * <LI>The input corresponds to something that can be collapsed,</LI>
     * <LI>and the input is collapsed.</LI>
     * </UL>
     */
    private Predicate<Node> isCollapsedNode = new Predicate<Node>() {
        @Override
        public boolean apply(Node input) {
            boolean apply = false;

            int type = SiriusVisualIDRegistry.getVisualID(input.getType());
            boolean result = type == DNode2EditPart.VISUAL_ID || type == DNode4EditPart.VISUAL_ID;
            result = result || type == DNodeEditPart.VISUAL_ID || type == DNode3EditPart.VISUAL_ID;
            result = result || type == DNodeContainerEditPart.VISUAL_ID || type == DNodeContainer2EditPart.VISUAL_ID;
            result = result || type == DNodeListEditPart.VISUAL_ID || type == DNodeList2EditPart.VISUAL_ID;

            if (result) {
                if (new NodeQuery(input).isCollapsed()) {
                    // This node is collapsed.
                    apply = true;
                }
            }
            return apply;
        }
    };

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(REPRESENTATIONS_FILE_PATH, true), true);

        // Check that the migration is needed.
        Version migration = DiagramRepresentationsFileMigrationParticipantV670.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the size is 1x1 for the collapsed bordered nodes and that the
     * width and height of the collapse filter contains in the corresponding
     * DNode are set (only if the size is different than the default size
     * defined in the VSM).
     */
    public void testMigration() {

        List<Diagram> diagrams = new ArrayList<Diagram>();
        // Retrieve all gmf diagram
        for (DView view : session.getOwnedViews()) {
            for (DRepresentation representation : new DViewQuery(view).getLoadedRepresentations()) {
                if (representation instanceof DDiagram) {
                    DDiagramGraphicalQuery query = new DDiagramGraphicalQuery((DDiagram) representation);
                    Option<Diagram> option = query.getAssociatedGMFDiagram();
                    if (option.some()) {
                        diagrams.add(option.get());
                    }
                }
            }
        }

        for (Diagram diagram : diagrams) {
            checkMigrationForEachCollapsedNode(diagram);
        }

    }

    /**
     * A bordered node that is collapsed should:
     * <UL>
     * <LI>have a width and height equals to 1.</LI>
     * <LI>have a corresponding DDiagramElement with at least one graphical
     * filter of kind {@link CollapseFilter}.</LI>
     * </UL>
     * A node (that is not) a bordered node, that is collapsed should have a
     * corresponding DDiagramElement:
     * <UL>
     * <LI>with graphical filter of kind {@link CollapseFilter},</LI>
     * <LI>with children having at least one graphical filter of kind
     * {@link IndirectlyCollapseFilter}</LI>
     * </UL>
     * <UL>
     * <LI></LI>
     * <LI></LI>
     * </UL>
     * 
     * @param diagram
     */
    private void checkMigrationForEachCollapsedNode(Diagram diagram) {
        // Get an iterator of nodes that probably move
        Iterator<Node> collapsedNodesIterator = Iterators.filter(Iterators.filter(diagram.eAllContents(), Node.class), isCollapsedNode);
        while (collapsedNodesIterator.hasNext()) {
            Node node = collapsedNodesIterator.next();
            NodeQuery nodeQuery = new NodeQuery(node);
            DDiagramElement dde = (DDiagramElement) node.getElement();
            DDiagramElementQuery query = new DDiagramElementQuery(dde);
            if (query.isCollapsed()) {
                if (nodeQuery.isBorderedNode()) {
                    Dimension defaultCollapsedSize = nodeQuery.getCollapsedSize();
                    assertTrue("The layout constraint should be a Bounds.", node.getLayoutConstraint() instanceof Bounds);
                    assertEquals("The GMF height of the collapsed bordered node should be as expected.", defaultCollapsedSize.height, ((Bounds) node.getLayoutConstraint()).getHeight());
                    assertEquals("The GMF width of the collapsed bordered node should be as expected.", defaultCollapsedSize.width, ((Bounds) node.getLayoutConstraint()).getWidth());
                    assertTrue("The element of the GMF Node should be a DNode.", node.getElement() instanceof DNode);
                    for (GraphicalFilter graphicalFilter : dde.getGraphicalFilters()) {
                        if (graphicalFilter instanceof CollapseFilter) {
                            CollapseFilter collapseFilter = ((CollapseFilter) graphicalFilter);
                            assertTrue("The collapse filter height and width should be set",
                                    collapseFilter.eIsSet(DiagramPackage.eINSTANCE.getCollapseFilter_Height()) && collapseFilter.eIsSet(DiagramPackage.eINSTANCE.getCollapseFilter_Width()));
                        }
                    }
                } else {
                    assertTrue("A CollapseFilter should be in the list of graphical filters.", Iterators.any(dde.getGraphicalFilters().iterator(), Predicates.instanceOf(CollapseFilter.class)));
                    List<DDiagramElement> children = Lists.newArrayList();
                    if (dde instanceof AbstractDNode) {
                        AbstractDNode abstractDNode = (AbstractDNode) dde;
                        children.addAll(abstractDNode.getOwnedBorderedNodes());
                        if (abstractDNode instanceof DNodeContainer) {
                            DNodeContainer dDiagramElementContainer = (DNodeContainer) abstractDNode;
                            children.addAll(dDiagramElementContainer.getOwnedDiagramElements());
                        } else if (abstractDNode instanceof DNodeList) {
                            DNodeList dNodeList = (DNodeList) abstractDNode;
                            children.addAll(dNodeList.getOwnedElements());
                        }
                    }
                    for (DDiagramElement child : children) {
                        assertTrue("An IndirectlyCollapseFilter should be in the list of graphical filters.",
                                Iterators.any(child.getGraphicalFilters().iterator(), Predicates.instanceOf(IndirectlyCollapseFilter.class)));
                    }
                }
            }
        }
    }
}
