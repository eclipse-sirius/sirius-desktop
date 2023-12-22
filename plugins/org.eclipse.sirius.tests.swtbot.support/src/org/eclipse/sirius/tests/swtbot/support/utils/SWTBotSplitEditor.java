/**
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.sirius.tests.swtbot.support.internal.DesignerSWTBotTestsSupportPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartPane;
import org.eclipse.ui.internal.PartSite;
import org.eclipse.ui.internal.WorkbenchPage;

/**
 * Utilities to split an eclipse editor. Use reflexivity to compile with eclipse
 * 4.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
@SuppressWarnings("restriction")
public final class SWTBotSplitEditor {

    /**
     * To test that the editor can be splits. With eclipse 4.2 this code not
     * work so the variable value equals false.
     */
    private static boolean editorSplit = true;

    /**
     * Avoid instantiation.
     */
    private SWTBotSplitEditor() {
    }

    /**
     * Split the editor area if there is at least two editors in it.
     */
    public static void splitEditorArea() {
        Display.getDefault().syncExec(SWTBotSplitEditor::doSplitEditorArea);
    }

    private static void doSplitEditorArea() {
        IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IWorkbenchPart part = workbenchPage.getActivePart();
        try {
            PartSite partSite = (PartSite) part.getSite();
            Method getPane = partSite.getClass().getMethod("getPane", new Class[0]);
            PartPane partPane = (PartPane) getPane.invoke(partSite);
            Class layoutPartClass = DesignerSWTBotTestsSupportPlugin.getDefault().getBundle().loadClass("org.eclipse.ui.internal.LayoutPart");
            IEditorReference[] editorReferences = workbenchPage.getEditorReferences();
            Method getPart = layoutPartClass.getMethod("getPart", new Class[0]);
            Object layoutPart = getPart.invoke(partPane);
            // Do it only if we have more that one editor
            if (editorReferences.length > 1) {
                // Get PartPane that correspond to the active editor
                getPane = ((PartSite) workbenchPage.getActiveEditor().getSite()).getClass().getMethod("getPane", new Class[0]);
                PartPane currentEditorPartPane = (PartPane) getPane.invoke(workbenchPage.getActiveEditor().getSite());
                Object editorSashContainer = null;
                Class editorSashContainerClass = DesignerSWTBotTestsSupportPlugin.getDefault().getBundle().loadClass("org.eclipse.ui.internal.EditorSashContainer");
                Class iLayoutContainerClass = DesignerSWTBotTestsSupportPlugin.getDefault().getBundle().loadClass("org.eclipse.ui.internal.ILayoutContainer");
                Method getContainer = layoutPart.getClass().getMethod("getContainer", new Class[0]);
                Object rootLayoutContainer = getContainer.invoke(layoutPart);
                if (layoutPartClass.isInstance(rootLayoutContainer)) {
                    Object editorSashLayoutContainer = getContainer.invoke(layoutPartClass.cast(rootLayoutContainer));
                    if (editorSashContainerClass.isInstance(editorSashLayoutContainer)) {
                        editorSashContainer = editorSashContainerClass.cast(editorSashLayoutContainer);
                    }
                }
                /*
                 * Create a new part stack (i.e. a workbook) to home the
                 * currentEditorPartPane which hold the active editor
                 */
                Class partStackClass = DesignerSWTBotTestsSupportPlugin.getDefault().getBundle().loadClass("org.eclipse.ui.internal.PartStack");
                Object newPart = SWTBotSplitEditor.createStack(editorSashContainer);
                if (newPart != null) {
                    partStackClass.cast(newPart);
                    Class[] paramTypes = new Class[2];
                    paramTypes[0] = layoutPartClass;
                    paramTypes[1] = iLayoutContainerClass;
                    Method stack = editorSashContainerClass.getMethod("stack", paramTypes);
                    stack.invoke(editorSashContainer, currentEditorPartPane, newPart);
                    if (layoutPartClass.isInstance(rootLayoutContainer)) {
                        Object cont = getContainer.invoke(layoutPartClass.cast(rootLayoutContainer));
                        Class partSashContainerClass = DesignerSWTBotTestsSupportPlugin.getDefault().getBundle().loadClass("org.eclipse.ui.internal.PartSashContainer");
                        if (partSashContainerClass.isInstance(cont)) {
                            // "Split" the editor area by adding the new
                            // part
                            partSashContainerClass.cast(cont);
                            paramTypes = new Class[1];
                            paramTypes[0] = layoutPartClass;
                            Method add = partSashContainerClass.getMethod("add", paramTypes);
                            add.invoke(cont, newPart);
                        }
                    }
                } else {
                    SWTBotSplitEditor.setEditor_split(false);
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException iae) {
            SWTBotSplitEditor.setEditor_split(false);
        }
    }

    // CHECKSTYLE:OFF
    /**
     * A method to create a part stack container (a new workbook).
     * 
     * @param editorSashContainer
     *            the <code>EditorSashContainer</code> to set for the returned
     *            <code>PartStack</code>
     * @return a new part stack container
     */
    public static Object createStack(Object editorSashContainer) {
        WorkbenchPage workbenchPage = (WorkbenchPage) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        Object newWorkbook = null;
        try {
            Class editorStackClass = DesignerSWTBotTestsSupportPlugin.getDefault().getBundle().loadClass("org.eclipse.ui.internal.EditorStack");

            Class[] paramTypes = new Class[2];
            paramTypes[0] = editorSashContainer.getClass();
            paramTypes[1] = workbenchPage.getClass();
            Method newEditorWorkbook = editorStackClass.getMethod("newEditorWorkbook", paramTypes);
            newWorkbook = newEditorWorkbook.invoke(null, editorSashContainer, workbenchPage);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | ClassNotFoundException iae) {
            return newWorkbook;
        }
        return newWorkbook;
    }

    /**
     * @return the editor_split
     */
    public static boolean isEditor_split() {
        return SWTBotSplitEditor.editorSplit;
    }

    /**
     * @param editor_split
     *            the editor_split to set
     */
    public static void setEditor_split(boolean editor_split) {
        SWTBotSplitEditor.editorSplit = editor_split;
    }
}