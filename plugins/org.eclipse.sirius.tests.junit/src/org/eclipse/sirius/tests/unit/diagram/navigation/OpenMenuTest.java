/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class OpenMenuTest extends SiriusDiagramTestCase {

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

    public void testAllViewpointsEnabled() throws Exception {
        activateViewpoint("Design");
        activateViewpoint("Documentation");
        activateViewpoint("Quality");
        activateViewpoint("Review");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getOpenContributions();
        assertEquals(1, items.size());
        IAction action = items.get(0).getAction();
        assertEquals("packageWithExistingDiagram package entities", action.getText());

        selectPackage("packageWithExistingTable");
        items = getOpenContributions();
        assertEquals(1, items.size());
        action = items.get(0).getAction();
        assertEquals("new Documentation", action.getText());

        selectPackage("packageWithNoRepresentation");
        items = getOpenContributions();
        assertEquals(0, items.size());
    }

    public void testReviewViewpointDisabled() throws Exception {
        activateViewpoint("Design");
        activateViewpoint("Documentation");
        activateViewpoint("Quality");
        deactivateViewpoint("Review");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getOpenContributions();
        items = getOpenContributions();
        assertEquals(1, items.size());
        final IAction action = items.get(0).getAction();
        assertEquals("packageWithExistingDiagram package entities", action.getText());

        selectPackage("packageWithExistingTable");
        items = getOpenContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithNoRepresentation");
        items = getOpenContributions();
        assertEquals(0, items.size());
    }

    public void testDesignViewpointDisabled() throws Exception {
        activateViewpoint("Documentation");
        activateViewpoint("Quality");
        activateViewpoint("Review");
        deactivateViewpoint("Design");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getOpenContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithExistingTable");
        items = getOpenContributions();
        assertEquals(1, items.size());
        final IAction action = items.get(0).getAction();
        assertEquals("new Documentation", action.getText());

        selectPackage("packageWithNoRepresentation");
        items = getOpenContributions();
        assertEquals(0, items.size());
    }

    public void testDesignAndReviewViewpointsDisabled() throws Exception {
        activateViewpoint("Documentation");
        activateViewpoint("Quality");
        deactivateViewpoint("Design");
        deactivateViewpoint("Review");

        selectPackage("packageWithExistingDiagram");
        List<ActionContributionItem> items = getOpenContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithExistingTable");
        items = getOpenContributions();
        assertEquals(0, items.size());

        selectPackage("packageWithNoRepresentation");
        items = getOpenContributions();
        assertEquals(0, items.size());
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

    private List<ActionContributionItem> getOpenContributions() {
        // The "popupMenu" created below is just a mock of the real menu
        // structure we would have at runtime. It is created so that we can
        // ContributionItemService.getInstance().contributeToPopupMenu() and
        // then check the entries added to the open menu.
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
        popupMenu.add(new MenuManager("popup.open", "popup.open") {//$NON-NLS-1$ //$NON-NLS-2$
                    @Override
                    public boolean isGroupMarker() {
                        return true;
                    }
                });
        ContributionItemService.getInstance().contributeToPopupMenu(popupMenu, EclipseUIUtil.getActivePage().getActivePart());
        final IMenuManager openMenu = (IMenuManager) popupMenu.find("popup.open");
        final IContributionItem[] items = openMenu.getItems();

        final List<ActionContributionItem> actionContributions = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof ActionContributionItem) {
                actionContributions.add((ActionContributionItem) items[i]);
            }
        }
        return actionContributions;
    }

    private IEditorPart openRepresentation(final String name) {
        for (final DRepresentationDescriptor representationDescriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
            if (name.equals(representationDescriptor.getName())) {
                IEditorPart openEditor = DialectUIManager.INSTANCE.openEditor(session, representationDescriptor.getRepresentation(), new NullProgressMonitor());
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
