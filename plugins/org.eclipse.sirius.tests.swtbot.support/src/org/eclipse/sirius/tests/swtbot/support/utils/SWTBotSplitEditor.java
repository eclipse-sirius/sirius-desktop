/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.eclipse.sirius.tests.swtbot.support.internal.DesignerSWTBotTestsSupportPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.SWTBotTestCase;
import org.eclipse.swtbot.swt.finder.utils.ClassUtils;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
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
public final class SWTBotSplitEditor extends SWTBotGefTestCase {

    /**
     * To test that the editor can be splits. With eclipse 4.2 this code not
     * work so the variable value equals false.
     */
    private static boolean editorSplit = true;

    /** Counts the screenshots to determine if maximum number is reached. */
    private static int screenshotCounter;

    /** The logger. */
    private static Logger log = Logger.getLogger(SWTBotTestCase.class);

    /**
     * Avoid instantiation.
     */
    private SWTBotSplitEditor() {

    }

    /**
     * Split the editor area if there is at least two editors in it.
     */
    public static void splitEditorArea() {
        Display.getDefault().syncExec(new Runnable() {
            @Override
            public void run() {
                doSplitEditorArea();
            }
        });
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
        } catch (IllegalAccessException iae) {
            SWTBotSplitEditor.setEditor_split(false);
        } catch (NoSuchMethodException nsme) {
            SWTBotSplitEditor.setEditor_split(false);
        } catch (InvocationTargetException iae) {
            SWTBotSplitEditor.setEditor_split(false);
        } catch (ClassNotFoundException cnfe) {
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
        } catch (IllegalAccessException iae) {
            return newWorkbook;
        } catch (NoSuchMethodException nsme) {
            return newWorkbook;
        } catch (InvocationTargetException iae) {
            return newWorkbook;
        } catch (ClassNotFoundException cnfe) {
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

    // CHECKSTYLE:OFF
    /**
     * Method override to use a specific captureScreenshot() that uses the
     * constant SWTBotPreferences.SCREENSHOTS_DIR instead of a hard coded folder
     * name.
     */
    @Override
    public void runBare() throws Throwable {
        Throwable exception = null;
        try {
            super.runBare();
        } catch (Throwable running) {
            exception = running;
            captureScreenshot();
        }
        if (exception != null)
            throw exception;
    }

    /**
     * Helper used by {@link #runBare()}. Duplicate from
     * {@link SWTBotTestCase#captureScreenshot()} to use the constant
     * SWTBotPreferences.SCREENSHOTS_DIR instead of a hard coded folder.
     * 
     * @see #runBare()
     */
    private void captureScreenshot() {
        try {
            int maximumScreenshots = SWTBotPreferences.MAX_ERROR_SCREENSHOT_COUNT;
            String fileName = SWTBotPreferences.SCREENSHOTS_DIR + "/screenshot-" + ClassUtils.simpleClassName(getClass()) + "." + getName() + "." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    + SWTBotPreferences.SCREENSHOT_FORMAT.toLowerCase();
            if (++screenshotCounter <= maximumScreenshots) {
                new File(SWTBotPreferences.SCREENSHOTS_DIR).mkdirs(); //$NON-NLS-1$
                SWTUtils.captureScreenshot(fileName);
            } else {
                log.info("No screenshot captured for '" + ClassUtils.simpleClassName(getClass()) + "." + getName() //$NON-NLS-1$ //$NON-NLS-2$
                        + "' because maximum number of screenshots reached: " + maximumScreenshots); //$NON-NLS-1$
            }
        } catch (Exception e) {
            log.warn("Could not capture screenshot", e); //$NON-NLS-1$
        }
    }
    // CHECKSTYLE:ON
}
