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
package org.eclipse.sirius.tests.unit.diagram.navigation;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

public class NavigationMenuTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/navigation/tc1562.ecore";

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/navigation/tc1562.aird";

    private IEditorPart editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        editor = openRepresentation("new Dependencies");
    }

    public void testAllSiriussEnabled() throws Exception {
        enableSiriuss("Design", "Documentation", "Quality", "Review");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getNavigationContributions();
        assertEquals(1, items.size());
        IAction action = items.get(0).getAction();
        assertEquals("Open packageWithExistingDiagram package entities", action.getText());

        selectPackage("packageWithExistingTable");
        items = getNavigationContributions();
        assertEquals(1, items.size());
        action = items.get(0).getAction();
        assertEquals("Open new Documentation", action.getText());

        selectPackage("packageWithNoRepresentation");
        items = getNavigationContributions();
        assertEquals(0, items.size());
    }

    public void testReviewSiriusDisabled() throws Exception {
        enableSiriuss("Design", "Documentation", "Quality");
        disableSiriuss("Review");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getNavigationContributions();
        items = getNavigationContributions();
        assertEquals(1, items.size());
        final IAction action = items.get(0).getAction();
        assertEquals("Open packageWithExistingDiagram package entities", action.getText());

        selectPackage("packageWithExistingTable");
        items = getNavigationContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithNoRepresentation");
        items = getNavigationContributions();
        assertEquals(0, items.size());
    }

    public void testDesignSiriusDisabled() throws Exception {
        enableSiriuss("Documentation", "Quality", "Review");
        disableSiriuss("Design");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getNavigationContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithExistingTable");
        items = getNavigationContributions();
        assertEquals(1, items.size());
        final IAction action = items.get(0).getAction();
        assertEquals("Open new Documentation", action.getText());

        selectPackage("packageWithNoRepresentation");
        items = getNavigationContributions();
        assertEquals(0, items.size());
    }

    public void testDesignAndReviewSiriussDisabled() throws Exception {
        enableSiriuss("Documentation", "Quality");
        disableSiriuss("Design", "Review");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getNavigationContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithExistingTable");
        items = getNavigationContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithNoRepresentation");
        items = getNavigationContributions();
        assertEquals(0, items.size());
    }

    private void enableSiriuss(final String... names) {
        for (final Viewpoint vp : viewpoints) {
            for (final String name : names) {
                if (vp.getName().equals(name)) {
                    session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                        @Override
                        protected void doExecute() {
                            new ViewpointSelectionCallback().selectViewpoint(vp, session, new NullProgressMonitor());
                        }
                    });
                }
            }
        }
    }

    private void disableSiriuss(final String... names) {
        for (final Viewpoint vp : viewpoints) {
            for (final String name : names) {
                if (vp.getName().equals(name)) {
                    session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                        @Override
                        protected void doExecute() {
                            new ViewpointSelectionCallback().deselectViewpoint(vp, session, new NullProgressMonitor());
                        }
                    });
                }
            }
        }
    }

    private DDiagramElement selectPackage(final String name) {
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        final IWorkbenchPage page = EclipseUIUtil.getActivePage();
        assertNotNull("We should have an active page", page);
        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;

        GraphicalEditPart elementEditPart = null;
        for (final Iterator<?> iterator = diagramEditor.getDiagramEditPart().getChildren().iterator(); iterator.hasNext();) {
            final GraphicalEditPart editPart = (GraphicalEditPart) iterator.next();
            final DDiagramElement element = (DDiagramElement) editPart.resolveSemanticElement();
            final EObject target = element.getTarget();
            if (target instanceof EPackage && ((EPackage) target).getName().equals(name)) {
                elementEditPart = editPart;
                break;
            }
        }

        diagramEditor.getDiagramGraphicalViewer().setFocus(elementEditPart);
        return (DDiagramElement) ((GraphicalEditPart) diagramEditor.getDiagramGraphicalViewer().getFocusEditPart()).resolveSemanticElement();
    }

    private List<ActionContributionItem> getNavigationContributions() {
        // The "popupMenu" created below is just a mock of the real menu
        // structure we would have at runtime. It is created so that we can
        // ContributionItemService.getInstance().contributeToPopupMenu() and
        // then check the entries added to the navigation menu.
        MenuManager popupMenu = new MenuManager();
        // We must override isGroupMarker() so that the two following menus
        // support the addition of elements, which may be defined in the VSM and
        // will be added by contributeToPopupMenu().
        popupMenu.add(new MenuManager("additions", "additions") {//$NON-NLS-1$ //$NON-NLS-2$
                    @Override
                    public boolean isGroupMarker() {
                        return true;
                    }
                });
        popupMenu.add(new MenuManager("navigateMenu", "navigateMenu") {//$NON-NLS-1$ //$NON-NLS-2$
                    @Override
                    public boolean isGroupMarker() {
                        return true;
                    }
                });
        ContributionItemService.getInstance().contributeToPopupMenu(popupMenu, EclipseUIUtil.getActivePage().getActivePart());
        final IMenuManager navigateMenu = (IMenuManager) popupMenu.find("navigateMenu");
        final IContributionItem[] items = navigateMenu.getItems();

        final List<ActionContributionItem> actionContributions = Lists.newArrayList();
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof ActionContributionItem) {
                actionContributions.add((ActionContributionItem) items[i]);
            }
        }
        return actionContributions;
    }

    private IEditorPart openRepresentation(final String name) {
        for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
            if (name.equals(representation.getName())) {
                IEditorPart openEditor = DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
                return openEditor;
            }
        }
        return null;
    }

    private void closeEditor() {
        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    private void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
        }
    }

    private void emptyEventsFromUIThread() {
        try {
            synchronizationWithUIThread();
        } catch (final Exception e) {
            emptyEventsFromUIThread();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        closeEditor();
        emptyEventsFromUIThread();
        super.tearDown();
    }
}
