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
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Test for the VP-2649, for which now in auto refresh a deletion impact all
 * concerned representations by the deletion to remove dangling references.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshOnDeletionInAutoRefreshTests extends AbstractRefreshOnDeletionTests {

    private static final String FAILURE_MESSAGE = "In automatic refresh all DRepresentations impacted by the deletion should be changed, and for opened one the DRepresentationElement having the deleted element has target should be deleted";

    @Override
    public void setUp() throws Exception {
        super.setUp();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    /**
     * Test that in auto refresh delete a {@link DTreeItem} without tool with
     * its semantic target change all concerned {@link DRepresentation} by the
     * deletion.
     */
    public void testDeletionOnDTreeWithoutToolInAutoRefresh() {
        openedDTreeEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dTreeItemOfEClass1InOpenedDTree);

        // Checks
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree, openedDTable, openedDDiagram);

        assertNull(FAILURE_MESSAGE, eClass1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEClass1InOpenedDTree.eContainer());
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
     * Test that in auto refresh delete a {@link DTreeItem} without tool with
     * its semantic target change all concerned {@link DRepresentation} by the
     * deletion.
     */
    public void testDeletionOnDTableWithoutToolInAutoRefresh() {
        openedDTableEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dLineOfEClass1InOpenedDTable);

        // Checks
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree, openedDTable, openedDDiagram);

        assertNull(FAILURE_MESSAGE, eClass1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEClass1InOpenedDTree.eContainer());
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
     * Test that in auto refresh delete a {@link DDiagramElement} without tool
     * with its semantic target change all concerned {@link DRepresentation}s by
     * the deletion is done and semantic elements .
     */
    public void testDeletionOnDDiagramWithoutToolInAutoRefresh() {
        openedDDiagramEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        IGraphicalEditPart editPartOfEClass1InOpenedDDiagram = getEditPart(dDiagramElementOfEClass1InOpenedDDiagram);
        delete(editPartOfEClass1InOpenedDDiagram);
        TestsUtil.synchronizationWithUIThread();

        // Checks
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree, openedDTable, openedDDiagram);

        assertNull(FAILURE_MESSAGE, eClass1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEClass1InOpenedDTree.eContainer());
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
     * Test that in auto refresh delete a {@link DTreeItem} with tool with its
     * semantic target change all concerned {@link DRepresentation} by the
     * deletion.
     */
    public void testDeletionOnDTreeWithToolInAutoRefresh() {
        openedDTreeEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dTreeItemOfEPackage1InOpenedDTree);

        // Checks
        //Here we should have only the three opened editor: see OD-834
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree, openedDTable, openedDDiagram, closedDTree, closedDTable, closedDDiagram);

        assertNull(FAILURE_MESSAGE, ePackage1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InOpenedDTree.eContainer());
        assertNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InClosedDTree.eContainer());
        assertNull(FAILURE_MESSAGE, dLineOfEPackage1InOpenedDTable.eContainer());
        assertNull(FAILURE_MESSAGE, dLineOfEPackage1InClosedDTable.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InOpenedDDiagram.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }

    /**
     * Test that in auto refresh delete a {@link DTreeItem} with tool with its
     * semantic target change all concerned {@link DRepresentation} by the
     * deletion.
     */
    public void testDeletionOnDTableWithToolInAutoRefresh() {
        openedDTableEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        delete(dLineOfEPackage1InOpenedDTable);

        // Checks
        //Here we should have only the three opened editor: see OD-834
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree, openedDTable, openedDDiagram, closedDTree, closedDTable, closedDDiagram);

        assertNull(FAILURE_MESSAGE, ePackage1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InOpenedDTree.eContainer());
        assertNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InClosedDTree.eContainer());
        assertNull(FAILURE_MESSAGE, dLineOfEPackage1InOpenedDTable.eContainer());
        assertNull(FAILURE_MESSAGE, dLineOfEPackage1InClosedDTable.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InOpenedDDiagram.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }

    /**
     * Test that in auto refresh delete a {@link DDiagramElement} with tool with
     * its semantic target change all concerned {@link DRepresentation}s by the
     * deletion is done and semantic elements .
     */
    public void testDeletionOnDDiagramWithToolInAutoRefresh() {
        openedDDiagramEditor.setFocus();
        TestsUtil.synchronizationWithUIThread();

        // Delete
        IGraphicalEditPart editPartOfEPackage1InOpenedDDiagram = getEditPart(dDiagramElementOfEPackage1InOpenedDDiagram);
        delete(editPartOfEPackage1InOpenedDDiagram);
        TestsUtil.synchronizationWithUIThread();

        // Checks
        //Here we should have only the three opened editor: see OD-834
        checkChangedRepresentations(FAILURE_MESSAGE, openedDTree, openedDTable, openedDDiagram, closedDTree, closedDTable, closedDDiagram);

        assertNull(FAILURE_MESSAGE, ePackage1.eContainer());

        assertNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InOpenedDTree.eContainer());
        assertNull(FAILURE_MESSAGE, dTreeItemOfEPackage1InClosedDTree.eContainer());
        assertNull(FAILURE_MESSAGE, dLineOfEPackage1InOpenedDTable.eContainer());
        assertNull(FAILURE_MESSAGE, dLineOfEPackage1InClosedDTable.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InOpenedDDiagram.eContainer());
        assertNull(FAILURE_MESSAGE, dDiagramElementOfEPackage1InClosedDDiagram.eContainer());

        // Check DSemanticDecorator.getTarget not changed by
        // DanglingRefRemovalTrigger.
        checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(FAILURE_MESSAGE);
    }
}
