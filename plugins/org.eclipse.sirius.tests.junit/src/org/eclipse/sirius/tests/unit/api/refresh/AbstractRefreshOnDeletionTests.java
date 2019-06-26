/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.action.DeleteLinesAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.api.command.TreeCommandFactoryService;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.DeleteTreeItemsAction;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * Abstract class for {@link RefreshOnDeletionInManualRefreshTests} and
 * {@link RefreshOnDeletionInAutoRefreshTests}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractRefreshOnDeletionTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/VP-2649/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-2649.ecore";

    private static final String MODELER_RESOURCE_NAME = "VP-2649.odesign";

    private static final String SESSION_RESOURCE_NAME = "VP-2649.aird";

    private static final String OPENED_DTREE_REP = "TreeOpenedRep";

    private static final String CLOSED_DTREE_REP = "TreeClosedRep";

    private static final String OPENED_DTABLE_REP = "TableOpenedRep";

    private static final String CLOSED_DTABLE_REP = "TableClosedRep";

    private static final String OPENED_DDIAGRAM_REP = "DiagramOpenedRep";

    private static final String CLOSED_DDIAGRAM_REP = "DiagramClosedRep";

    protected EPackage rootEPackage;

    protected EClass eClass1;

    protected EClass eClass2;

    protected EPackage ePackage1;

    protected DDiagram openedDDiagram;

    protected DDiagram closedDDiagram;

    protected DTable openedDTable;

    protected DTable closedDTable;

    protected DTree openedDTree;

    protected DTree closedDTree;

    protected DTreeEditor openedDTreeEditor;

    protected DTableEditor openedDTableEditor;

    protected DDiagramEditor openedDDiagramEditor;

    protected DTreeItem dTreeItemOfEClass1InOpenedDTree;

    protected DTreeItem dTreeItemOfEClass1InClosedDTree;

    protected DTreeItem dTreeItemOfEPackage1InOpenedDTree;

    protected DTreeItem dTreeItemOfEPackage1InClosedDTree;

    protected DLine dLineOfEClass1InOpenedDTable;

    protected DLine dLineOfEClass1InClosedDTable;

    protected DLine dLineOfEPackage1InOpenedDTable;

    protected DLine dLineOfEPackage1InClosedDTable;

    protected DDiagramElement dDiagramElementOfEClass1InOpenedDDiagram;

    protected DDiagramElement dDiagramElementOfEClass1InClosedDDiagram;

    protected DDiagramElement dDiagramElementOfEPackage1InOpenedDDiagram;

    protected DDiagramElement dDiagramElementOfEPackage1InClosedDDiagram;

    protected ChangedDRepresentationsGetter changedDRepresentationsGetter;

    protected boolean autoRefreshMode;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        genericSetUp(PATH + SEMANTIC_RESOURCE_NAME, PATH + MODELER_RESOURCE_NAME, PATH + SESSION_RESOURCE_NAME);

        Resource semanticResource = session.getSemanticResources().iterator().next();
        rootEPackage = (EPackage) semanticResource.getContents().get(0);
        eClass1 = (EClass) rootEPackage.getEClassifier("EClass1");
        eClass2 = (EClass) rootEPackage.getEClassifier("EClass2");
        ePackage1 = rootEPackage.getESubpackages().get(0);

        final Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
            if (OPENED_DTREE_REP.equals(representationDescriptor.getName())) {
                openedDTree = (DTree) representationDescriptor.getRepresentation();
            } else if (OPENED_DTABLE_REP.equals(representationDescriptor.getName())) {
                openedDTable = (DTable) representationDescriptor.getRepresentation();
            } else if (OPENED_DDIAGRAM_REP.equals(representationDescriptor.getName())) {
                openedDDiagram = (DDiagram) representationDescriptor.getRepresentation();
            } else if (CLOSED_DTREE_REP.equals(representationDescriptor.getName())) {
                closedDTree = (DTree) representationDescriptor.getRepresentation();
            } else if (CLOSED_DTABLE_REP.equals(representationDescriptor.getName())) {
                closedDTable = (DTable) representationDescriptor.getRepresentation();
            } else if (CLOSED_DDIAGRAM_REP.equals(representationDescriptor.getName())) {
                closedDDiagram = (DDiagram) representationDescriptor.getRepresentation();
            }
        }
        openedDTreeEditor = (DTreeEditor) DialectUIManager.INSTANCE.openEditor(session, openedDTree, new NullProgressMonitor());
        openedDTableEditor = (DTableEditor) DialectUIManager.INSTANCE.openEditor(session, openedDTable, new NullProgressMonitor());
        openedDDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, openedDDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        dTreeItemOfEClass1InOpenedDTree = openedDTree.getOwnedTreeItems().get(0);
        dTreeItemOfEPackage1InOpenedDTree = openedDTree.getOwnedTreeItems().get(2);
        dTreeItemOfEClass1InClosedDTree = closedDTree.getOwnedTreeItems().get(0);
        dTreeItemOfEPackage1InClosedDTree = closedDTree.getOwnedTreeItems().get(2);

        dLineOfEClass1InOpenedDTable = openedDTable.getLines().get(0);
        dLineOfEPackage1InOpenedDTable = openedDTable.getLines().get(2);
        dLineOfEClass1InClosedDTable = closedDTable.getLines().get(0);
        dLineOfEPackage1InClosedDTable = closedDTable.getLines().get(2);

        dDiagramElementOfEClass1InOpenedDDiagram = openedDDiagram.getOwnedDiagramElements().get(0);
        dDiagramElementOfEPackage1InOpenedDDiagram = openedDDiagram.getOwnedDiagramElements().get(2);
        dDiagramElementOfEClass1InClosedDDiagram = closedDDiagram.getOwnedDiagramElements().get(0);
        dDiagramElementOfEPackage1InClosedDDiagram = closedDDiagram.getOwnedDiagramElements().get(2);

        changedDRepresentationsGetter = new ChangedDRepresentationsGetter();
        session.getTransactionalEditingDomain().addResourceSetListener(changedDRepresentationsGetter);
    }

    /**
     * A {@link ResourceSetListener} to get changed {@link DRepresentation}
     * following a deletion in a {@link DRepresentation}.
     * 
     * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban
     *         Dugueperoux</a>
     */
    protected class ChangedDRepresentationsGetter extends ResourceSetListenerImpl implements ResourceSetListener {

        private Set<DRepresentation> changedDRepresentations;

        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {
            changedDRepresentations = new HashSet<DRepresentation>();
            List<Notification> notifications = event.getNotifications();
            for (Notification notification : notifications) {
                Object notifier = notification.getNotifier();
                if (notifier instanceof EObject) {
                    EObject eObject = (EObject) notifier;
                    EObjectQuery eObjectQuery = new EObjectQuery(eObject);
                    Option<DRepresentation> dRepresentationOption = eObjectQuery.getRepresentation();
                    if (dRepresentationOption.some()) {
                        changedDRepresentations.add(dRepresentationOption.get());
                    }
                }
            }
        }

        /**
         * Get changed {@link DRepresentation}.
         * 
         * @return the changed {@link DRepresentation}
         */
        public Set<DRepresentation> getChangedDRepresentations() {
            return changedDRepresentations != null ? changedDRepresentations : Collections.<DRepresentation> emptySet();
        }

    }

    @Override
    public void tearDown() throws Exception {
        session.getTransactionalEditingDomain().removeResourceSetListener(changedDRepresentationsGetter);
        changedDRepresentationsGetter = null;
        dTreeItemOfEClass1InOpenedDTree = null;
        dTreeItemOfEClass1InClosedDTree = null;
        dTreeItemOfEPackage1InOpenedDTree = null;
        dTreeItemOfEPackage1InClosedDTree = null;
        dLineOfEClass1InOpenedDTable = null;
        dLineOfEClass1InClosedDTable = null;
        dLineOfEPackage1InOpenedDTable = null;
        dLineOfEPackage1InClosedDTable = null;
        dDiagramElementOfEClass1InOpenedDDiagram = null;
        dDiagramElementOfEClass1InClosedDDiagram = null;
        dDiagramElementOfEPackage1InOpenedDDiagram = null;
        dDiagramElementOfEPackage1InClosedDDiagram = null;
        DialectUIManager.INSTANCE.closeEditor(openedDTreeEditor, false);
        DialectUIManager.INSTANCE.closeEditor(openedDTableEditor, false);
        DialectUIManager.INSTANCE.closeEditor(openedDDiagramEditor, false);
        openedDTreeEditor = null;
        openedDTableEditor = null;
        openedDDiagramEditor = null;
        openedDTree = null;
        openedDTable = null;
        openedDDiagram = null;
        closedDTree = null;
        closedDTable = null;
        closedDDiagram = null;
        eClass1 = null;
        eClass2 = null;
        ePackage1 = null;
        rootEPackage = null;
        super.tearDown();
    }

    protected void checkChangedRepresentations(String errorMessage, DRepresentation... expectedChangedRepresentations) {
        Set<DRepresentation> effectiveChangedDRepresentations = changedDRepresentationsGetter.getChangedDRepresentations();

        assertEquals(errorMessage, expectedChangedRepresentations.length, effectiveChangedDRepresentations.size());

        for (DRepresentation expectedChangedRep : expectedChangedRepresentations) {
            assertTrue(errorMessage, effectiveChangedDRepresentations.contains(expectedChangedRep));
        }
    }

    protected void delete(DTreeItem item) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        ITreeCommandFactory treeCommandFactory = TreeCommandFactoryService.getInstance().getNewProvider().getCommandFactory(domain);
        treeCommandFactory.setModelAccessor(session.getModelAccessor());
        DeleteTreeItemsAction deleteTreeItemsAction = new DeleteTreeItemsAction(domain, treeCommandFactory);
        deleteTreeItemsAction.setItems(Collections.singletonList(item));
        deleteTreeItemsAction.run();
    }

    protected void delete(DLine line) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        ITableCommandFactory tableCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(domain);
        tableCommandFactory.setModelAccessor(session.getModelAccessor());
        DeleteLinesAction deleteTreeItemsAction = new DeleteLinesAction(domain, tableCommandFactory);
        deleteTreeItemsAction.setLines(Collections.singletonList(line));
        deleteTreeItemsAction.run();
    }

    /*
     * Check DSemanticDecorator.getTarget not changed by
     * DanglingRefRemovalTrigger.
     */
    protected void checkTargetReferencesNotUnsettedByDanglingRefRemovalTrigger(String errorMessage) {
        assertNotNull(errorMessage, dTreeItemOfEPackage1InOpenedDTree.getTarget());
        assertNotNull(errorMessage, dTreeItemOfEPackage1InClosedDTree.getTarget());
        assertNotNull(errorMessage, dLineOfEPackage1InOpenedDTable.getTarget());
        assertNotNull(errorMessage, dLineOfEPackage1InClosedDTable.getTarget());
        assertNotNull(errorMessage, dDiagramElementOfEPackage1InOpenedDDiagram.getTarget());
        assertNotNull(errorMessage, dDiagramElementOfEPackage1InClosedDDiagram.getTarget());

        assertNotNull(errorMessage, dTreeItemOfEClass1InOpenedDTree.getTarget());
        assertNotNull(errorMessage, dTreeItemOfEClass1InClosedDTree.getTarget());
        assertNotNull(errorMessage, dLineOfEClass1InOpenedDTable.getTarget());
        assertNotNull(errorMessage, dLineOfEClass1InClosedDTable.getTarget());
        assertNotNull(errorMessage, dDiagramElementOfEClass1InOpenedDDiagram.getTarget());
        assertNotNull(errorMessage, dDiagramElementOfEClass1InClosedDDiagram.getTarget());
    }
}
