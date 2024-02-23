/*******************************************************************************
 * Copyright (c) 2022, 2024 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.outline;

import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DiagramOutlinePage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.uml2.uml.Package;

/**
 * @author etraisnel
 * 
 *         Test Outline contextual menu
 */
public class OutlineContextualMenuTest extends GenericTestCase {

    private static final String PLUGIN = "/" + SiriusTestsPlugin.PLUGIN_ID; //$NON-NLS-1$

    private static final String SEMANTIC_MODEL_PATH = PLUGIN + "/data/unit/navigation/testNavigation.uml"; //$NON-NLS-1$

    private static final String MODELER_PATH = PLUGIN + "/data/unit/navigation/testNavigation.odesign"; //$NON-NLS-1$

    private static final String OUTLINE_VIEW = "org.eclipse.ui.views.ContentOutline"; //$NON-NLS-1$
    
    private static final String SHOW_OUTLINE_ACTION = "showOutlineAction"; //$NON-NLS-1$
    
    private static final String TEST_SEPARATOR = "org.eclipse.sirius.tests.junit.separator1"; //$NON-NLS-1$
    @Override
    protected void setUp() throws Exception {
        super.setUp();        
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        final Viewpoint vp = viewpoints.iterator().next();
        assertTrue("The SiriusLayoutDataManager should not contain data before viewpoint selection.", SiriusLayoutDataManager.INSTANCE.getCreatedViewForInitPositionLayout().isEmpty());
        activateViewpoint(vp.getName());
        assertTrue("The SiriusLayoutDataManager should not contain data after viewpoint selection.", SiriusLayoutDataManager.INSTANCE.getCreatedViewForInitPositionLayout().isEmpty());
    }

    /**
     * This test ensures that elements contributed to the group "additions" are properly contributed to the outline contextual menu
     */
    public void testOutlineContextualMenu() {
        //Open a diagram editor
        final DSemanticDiagram pkgDiag = (DSemanticDiagram) getRepresentations("Package Diagram").stream().findFirst().get(); //$NON-NLS-1$
        DialectUIManager.INSTANCE.openEditor(session, pkgDiag, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        IEditorPart editor = EclipseUIUtil.getActiveEditor();
        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        assertNotNull("We should have an active page", page); //$NON-NLS-1$
        
        //open outline view
        IViewPart outlineView = null;
        try {
            outlineView = page.showView(OUTLINE_VIEW);           
        } catch (PartInitException e) {
            assertNotNull("Could not find outline view", outlineView); //$NON-NLS-1$
            e.printStackTrace();
        }        
        
        // Set the focus to Package 1
        assertTrue("We should have a DiagramDocumentEditor", editor instanceof DiagramDocumentEditor); //$NON-NLS-1$

        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editor;

        // Get the editPart for the first package
        GraphicalEditPart elementEditPart = null;
        for (Iterator<?> iterator = diagramEditor.getDiagramEditPart().getChildren().iterator(); iterator.hasNext() && elementEditPart == null;) {
            GraphicalEditPart editPart = (GraphicalEditPart) iterator.next();
            final DNode element = (DNode) editPart.resolveSemanticElement();
            if ("1".equals(element.getName())) { //$NON-NLS-1$
                elementEditPart = editPart;
                assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, element.getTarget() instanceof Package);
            }

        }
        //Ensure that outline is initialized by focusing an element in the diagram editor
        diagramEditor.getDiagramGraphicalViewer().setFocus(elementEditPart);
        
        ContentOutline contentOutline = (ContentOutline) outlineView;   
        DiagramOutlinePage diagramOutlinePage = (DiagramOutlinePage) contentOutline.getCurrentPage();    
        
        //ensure tree view is opened
        switchToTreeMode(diagramOutlinePage);
        
        MenuManager popupMenu = new MenuManager();
        popupMenu.setRemoveAllWhenShown(true);
        popupMenu.markDirty();  
        
        //Create and register the menu
        TreeViewer outlineTreeViewer = (TreeViewer) ReflectionHelper.getFieldValueWithoutException(diagramOutlinePage, "outlineViewer").get();
        Tree tree = outlineTreeViewer.getTree();   
        Menu menu = popupMenu.createContextMenu(tree);
        tree.setMenu(menu);
        diagramOutlinePage.getSite().registerContextMenu(OUTLINE_VIEW, popupMenu, diagramOutlinePage); //$NON-NLS-1$
       
        //Open the menu
        menu.notifyListeners(SWT.Show, null);       
            
        IContributionItem[] items = popupMenu.getItems();

        boolean foundTestSeparator = false;

        //Test that the junit plugin contribution that contributes to the group additions is here
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof GroupMarker) {
                GroupMarker sep = (GroupMarker) items[i];
                if (TEST_SEPARATOR.equals(sep.getId())) {
                    foundTestSeparator = true;
                    break;
                } else {
                    foundTestSeparator = false;
                }
            }
        }
        
        assertTrue(foundTestSeparator);

        diagramEditor.close(false);
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                IViewPart outlineView = page.findView(OUTLINE_VIEW);
                if (outlineView != null) {
                    page.hideView(outlineView);
                }
            }
        });
    }
    
    //Tree mode action
    private void switchToTreeMode(DiagramOutlinePage diagramOutlinePage) {
        Action action;
        action = (Action) ReflectionHelper.getFieldValueWithoutException(diagramOutlinePage, SHOW_OUTLINE_ACTION).get();
        action.run();       
        TestsUtil.synchronizationWithUIThread();
    }
        
   
}
