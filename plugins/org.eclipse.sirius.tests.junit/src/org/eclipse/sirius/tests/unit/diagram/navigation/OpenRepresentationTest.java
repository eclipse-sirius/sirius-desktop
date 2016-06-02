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
package org.eclipse.sirius.tests.unit.diagram.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.Package;

/**
 * @author mporhel
 * 
 *         Test Open representation menu.
 */
public class OpenRepresentationTest extends GenericTestCase {

    private static final String PLUGIN = "/" + SiriusTestsPlugin.PLUGIN_ID;

    private static final String SEMANTIC_MODEL_PATH = PLUGIN + "/data/unit/navigation/testNavigation.uml";

    private static final String MODELER_PATH = PLUGIN + "/data/unit/navigation/testNavigation.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        final Viewpoint vp = viewpoints.iterator().next();
        activateViewpoint(vp.getName());
    }

    private DSemanticDiagram getDiagramFromDescriptionName(final String name) {
        for (final DView dView : session.getOwnedViews()) {
            for (final Iterator<DRepresentation> iterator = new DViewQuery(dView).getLoadedRepresentations().iterator(); iterator.hasNext();) {
                final DSemanticDiagram rep = (DSemanticDiagram) iterator.next();
                if (name.equals(rep.getDescription().getName())) {
                    return rep;
                }
            }
        }
        return null;
    }

    private List<DSemanticDiagram> getDiagramsFromDescriptionName(final String name) {
        List<DSemanticDiagram> result = new ArrayList<DSemanticDiagram>();
        for (final DView dView : session.getOwnedViews()) {
            for (final Iterator<DRepresentation> iterator = new DViewQuery(dView).getLoadedRepresentations().iterator(); iterator.hasNext();) {
                final DSemanticDiagram rep = (DSemanticDiagram) iterator.next();
                if (name.equals(rep.getDescription().getName())) {
                    result.add(rep);
                }
            }
        }
        return result;
    }

    public void testCandidatesForOpenRepresentation() {
        final DiagramDescription pkgDiagDesc = findDiagramDescription("Package Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, pkgDiagDesc);

        final DiagramDescription classDiagDesc = findDiagramDescription("Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiagDesc);

        final DSemanticDiagram pkgDiag = getDiagramFromDescriptionName("Package Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, pkgDiag);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, "Package Diagram", pkgDiag.getDescription().getName());

        final List<DSemanticDiagram> classDiagrams = getDiagramsFromDescriptionName("Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiagrams);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 2, classDiagrams.size());

        /* set refresh on representation opening */
        boolean refreshOn = SiriusEditPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name());
        SiriusEditPlugin.getPlugin().getPreferenceStore().setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

        DialectUIManager.INSTANCE.openEditor(session, pkgDiag, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        /* restore refresh parameter */
        SiriusEditPlugin.getPlugin().getPreferenceStore().setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), refreshOn);

        IEditorPart editor = EclipseUIUtil.getActiveEditor();
        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        assertNotNull("We should have an activa page", page);
        IWorkbenchPart workbenchPart = page.getActivePart();

        IMenuManager popupMenu = new MenuManager();
        popupMenu.add(new MenuManager("additions", "additions"));//$NON-NLS-1$ //$NON-NLS-2$
        popupMenu.add(new MenuManager("popup.open", "popup.open"));//$NON-NLS-1$

        // Set the focus to Package 1
        assertTrue("We should have a DiagramDocumentEditor", editor instanceof DiagramDocumentEditor);

        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;

        // Get the editPart for the first package
        GraphicalEditPart elementEditPart = null;
        for (Iterator<?> iterator = diagramEditor.getDiagramEditPart().getChildren().iterator(); iterator.hasNext() && elementEditPart == null;) {
            GraphicalEditPart editPart = (GraphicalEditPart) iterator.next();
            final DNode element = (DNode) editPart.resolveSemanticElement();
            if ("1".equals(element.getName())) {
                elementEditPart = editPart;
                assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, element.getTarget() instanceof Package);
            }

        }

        diagramEditor.getDiagramGraphicalViewer().setFocus(elementEditPart);

        DNode focusedElement = (DNode) ((GraphicalEditPart) diagramEditor.getDiagramGraphicalViewer().getFocusEditPart()).resolveSemanticElement();
        assertTrue("This is not the good focused element", focusedElement.getTarget() instanceof Package);
        assertEquals("This is not the good focused element", "1", ((Package) focusedElement.getTarget()).getName());

        ContributionItemService.getInstance().contributeToPopupMenu(popupMenu, workbenchPart);

        // Check the open menu.
        IMenuManager openMenu = (IMenuManager) popupMenu.find("popup.open");
        IContributionItem[] items = openMenu.getItems();

        boolean inOpenToGroup = false;

        ActionContributionItem actionContribution = null;

        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof Separator) {
                Separator sep = (Separator) items[i];
                if ("openRepresentationGroup".equals(sep.getId())) {
                    inOpenToGroup = true;
                } else {
                    inOpenToGroup = false;
                }
            }
            if (inOpenToGroup && items[i] instanceof ActionContributionItem) {
                assertNull("There should be only one ActionContributionItem", actionContribution);
                actionContribution = (ActionContributionItem) items[i];
            }
        }

        assertNotNull("There should be one ActionContributionItem", actionContribution);
        final IAction action = actionContribution.getAction();
        // This action label is provided by GMF in
        // org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService.contributeToPopupMenu(IMenuManager,
        // IWorkbenchPart)
        assertEquals("Action has not the correct text", "Navigate to owned packages : Class Diagram", action.getText());

        action.run();

        IEditorPart editor2 = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertTrue("We should have a DiagramDocumentEditor", editor2 instanceof DiagramDocumentEditor);

        final DiagramDocumentEditor diagramEditor2 = (DiagramDocumentEditor) editor2;
        GraphicalEditPart diagramPart = diagramEditor2.getDiagramEditPart();
        final DSemanticDiagram element2 = (DSemanticDiagram) diagramPart.resolveSemanticElement();

        assertEquals("The opened diagram is not valid", element2.getTarget(), focusedElement.getTarget());
        assertEquals("The opened diagram is not valid", element2.getDescription().getName(), "Class Diagram");

        diagramEditor.close(false);
        diagramEditor2.close(false);

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        DialectUIManager.INSTANCE.closeEditor(diagramEditor2, false);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
