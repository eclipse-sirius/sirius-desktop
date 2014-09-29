/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.refresh;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Test for the VP-2649, for which now in manual refresh a deletion impact only
 * representation on which the deletion occurs and in manual refresh impact also
 * other related representation by removing dangling references.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshOnDeletionInManualRefreshTests extends AbstractRefreshOnDeletionTests {

    private static final String FAILURE_MESSAGE = "In manual refresh only the DRepresentation on which the deletion occurs should be changed";

    @Override
    public void setUp() throws Exception {
        super.setUp();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
    }

    /**
     * Test that in manual refresh delete a {@link DTreeItem} without tool with
     * its semantic target change only the {@link DRepresentation} in which
     * deletion is done and semantic elements concerned but not others
     * {@link DRepresentation}s.
     */
    public void testDeletionOnDTreeWithoutToolInManualRefresh() {
        openedDTreeEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dTreeItemOfEClass1InOpenedDTree);

        // Checks
        // For tree dialect without delete tool only semantic elt should be
        // deleted
        // To be consistent with diagram, only the tree should be modified here.
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree, openedDTable, openedDDiagram);

        assertNull(FAILURE_MESSAGE, eClass1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEClass1InOpenedDTree.eContainer());
        // To be consistent with diagram, only the tree element should be
        // modified here.
        assertNull(FAILURE_MESSAGE, dLineOfEClass1InOpenedDTable.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEClass1InOpenedDDiagram.eContainer());

        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEClass1InClosedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEClass1InClosedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEClass1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }

    /**
     * Test that in manual refresh delete a {@link DLine} without tool with its
     * semantic target change only the {@link DRepresentation} in which deletion
     * is done and semantic elements concerned but not others
     * {@link DRepresentation}s.
     */
    public void testDeletionOnDTableWithoutToolInManualRefresh() {
        openedDTableEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dLineOfEClass1InOpenedDTable);

        // Checks
        // For table dialect without delete tool only semantic elt should be
        // deleted
        // To be consistent with diagram, only the table should be modified
        // here.
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTable, openedDTree, openedDDiagram);

        assertNull(FAILURE_MESSAGE, eClass1.eContainer());

        assertNull(FAILURE_MESSAGE, dLineOfEClass1InOpenedDTable.eContainer());
        // To be consistent with diagram, only the table element should be
        // modified here.
        assertNull(FAILURE_MESSAGE, dTreeItemOfEClass1InOpenedDTree.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEClass1InOpenedDDiagram.eContainer());

        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEClass1InClosedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEClass1InClosedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEClass1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }

    /**
     * Test that in manual refresh delete a {@link DDiagramElement} without tool
     * with its semantic target change only the {@link DRepresentation} in which
     * deletion is done and semantic elements concerned but not others
     * {@link DRepresentation}s.
     */
    public void testDeletionOnDDiagramWithoutToolInManualRefresh() {
        openedDDiagramEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        IGraphicalEditPart editPartOfEClass1InOpenedDDiagram = getEditPart(dDiagramElementOfEClass1InOpenedDDiagram);
        delete(editPartOfEClass1InOpenedDDiagram);
        TestsUtil.synchronizationWithUIThread();

        // Checks

        checkChangedRepresentations(FAILURE_MESSAGE, openedDDiagram);

        assertNull(FAILURE_MESSAGE, eClass1.eContainer());

        assertNull(FAILURE_MESSAGE, dDiagramElementOfEClass1InOpenedDDiagram.eContainer());

        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEClass1InOpenedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEClass1InOpenedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEClass1InClosedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEClass1InClosedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEClass1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);

    }

    /**
     * Test that in manual refresh delete a {@link DTreeItem} witht tool with
     * its semantic target change only the {@link DRepresentation} in which
     * deletion is done and semantic elements concerned but not others
     * {@link DRepresentation}s.
     */
    public void testDeletionOnDTreeWithToolInManualRefresh() {
        openedDTreeEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dTreeItemOfEPackage1InOpenedDTree);

        // Checks
        // For tree dialect without delete tool only semantic element is deleted

        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree);

        assertNull(FAILURE_MESSAGE, ePackage1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InOpenedDTree.eContainer());

        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InClosedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEPackage1InOpenedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEPackage1InClosedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InOpenedDDiagram.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }

    /**
     * Test that in manual refresh delete a {@link DLine} with tool with its
     * semantic target change only the {@link DRepresentation} in which deletion
     * is done and semantic elements concerned but not others
     * {@link DRepresentation}s.
     */
    public void testDeletionOnDTableWithToolInManualRefresh() {
        openedDTableEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dLineOfEPackage1InOpenedDTable);

        // Checks
        // For table dialect without delete tool only semantic elt is deleted

        checkChangedRepresentations(FAILURE_MESSAGE, openedDTable);

        assertNull(FAILURE_MESSAGE, ePackage1.eContainer());

        assertNull(FAILURE_MESSAGE, dLineOfEPackage1InOpenedDTable.eContainer());

        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InOpenedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InClosedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEPackage1InClosedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InOpenedDDiagram.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }

    /**
     * Test that in manual refresh delete a {@link DDiagramElement} with tool
     * with its semantic target change only the {@link DRepresentation} in which
     * deletion is done and semantic elements concerned but not others
     * {@link DRepresentation}s.
     */
    public void testDeletionOnDDiagramWithToolInManualRefresh() {
        openedDDiagramEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        IGraphicalEditPart editPartOfEPackage1InOpenedDDiagram = getEditPart(dDiagramElementOfEPackage1InOpenedDDiagram);
        delete(editPartOfEPackage1InOpenedDDiagram);
        TestsUtil.synchronizationWithUIThread();

        // Checks

        checkChangedRepresentations(FAILURE_MESSAGE, openedDDiagram);

        assertNull(FAILURE_MESSAGE, ePackage1.eContainer());

        assertNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InOpenedDDiagram.eContainer());

        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InOpenedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InClosedDTree.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEPackage1InOpenedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dLineOfEPackage1InClosedDTable.eContainer());
        assertNotNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }
}
