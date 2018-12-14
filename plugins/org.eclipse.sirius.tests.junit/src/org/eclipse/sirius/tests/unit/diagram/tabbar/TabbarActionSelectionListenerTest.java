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
package org.eclipse.sirius.tests.unit.diagram.tabbar;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartListenerList;
import org.eclipse.ui.internal.PartService;

/**
 * This test ensures that selection changed listeners are correctly disposed
 * when the editors are disposed.(see VP-3632)
 * 
 * @author fbarbin
 * 
 */
public class TabbarActionSelectionListenerTest extends SiriusDiagramTestCase implements EcoreModeler {
    private static final String PATH = "/data/unit/tabbar/vp-3632/";

    private static final String SEMANTIC_MODEL_FILENAME = "vp-3632.ecore";

    private static final String SESSION_MODEL_FILENAME = "vp-3632.aird";

    private static final String REPRESENTATION_NAME_PATTERN = "newPackage%d package entities";

    private ArrayList<DDiagramEditor> editors;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_MODEL_FILENAME);
        String pathModel = "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME;
        String pathAird = "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME;
        genericSetUp(pathModel, MODELER_PATH, pathAird);
    }

    /**
     * Steps executed during the test running:
     * <ul>
     * <li>Get the listeners list before opening any editor.</li>
     * <li>Open all editors.</li>
     * <li>Close all editors.</li>
     * <li>Check that the selection listeners list did not grew up during the
     * open/close first cycle, except for additional PagePartSelectionTracker</li>
     * <li>Open all editors.</li>
     * <li>Close all editors.</li>
     * <li>Check that the selection listeners list length is the same than after
     * the first open/close cycle.</li>
     * <li>Open one editor</li>
     * <li>Open a second editor</li>
     * <li>Close the second editor</li>
     * <li>Check that listener list length is the same as after having opened
     * one editor.</li>
     * </ul>
     */
    public void testNumberOfListenerIsCorrectAfterOpenCloseDiagrams() {
        Object[] pageSelectionListeners = getPageSelectionListeners();
        Object[] partServiceListeners = getPartServiceListeners();
        Object[] workbenchWindowSelectionListeners = getWorkbenchWindowSelectionListeners();

        assertNotNull("Review the test, no found page selection listener.", pageSelectionListeners);
        assertNotNull("Review the test, no found part service listener.", partServiceListeners);
        assertNotNull("Review the test no found workbench window selection listener.", workbenchWindowSelectionListeners);

        // we get the listeners list before opening editors.
        int expectedPageSelectionListener = pageSelectionListeners.length;
        int expectedPartServiceListeners = partServiceListeners.length;
        int expectedWindowSelectionListener = workbenchWindowSelectionListeners.length;

        openAllEditors();

        assertTrue("Review this test: opening editor should add page selection listeners.", expectedPageSelectionListener < getPageSelectionListeners().length);
        assertTrue("Review this test: opening editor should add part service listeners.", expectedPartServiceListeners < getPartServiceListeners().length);
        assertTrue("Review this test: opening editor should add workbench window selection listeners.", expectedWindowSelectionListener < getWorkbenchWindowSelectionListeners().length);

        closeAllEditors();

        if (!TestsUtil.isEclipse4xPlatform()) {
            // +3 comes from the new PagePartSelectionTracker listener created
            // for viewpoint editor and undo/redo handlers.
            expectedPartServiceListeners = expectedPartServiceListeners + 3;
        }

        // We check that after having opened and closed editors, the
        // selection listener list length is as before.
        assertTrue("Too many page selection listeners.", expectedPageSelectionListener >= getPageSelectionListeners().length);
        assertTrue("Too many part service listeners.", expectedPartServiceListeners >= getPartServiceListeners().length);
        assertTrue("Too many workbench window listeners.", expectedWindowSelectionListener >= getWorkbenchWindowSelectionListeners().length);

        openAllEditors();

        assertTrue("Review this test: opening editor should add selection listeners.", expectedPageSelectionListener < getPageSelectionListeners().length);
        assertTrue("Review this test: opening editor should add part service listeners.", expectedPartServiceListeners < getPartServiceListeners().length);
        assertTrue("Review this test: opening editor should add selection listeners.", expectedWindowSelectionListener < getWorkbenchWindowSelectionListeners().length);

        closeAllEditors();

        // ... so we check that after having reopened and closed editors
        // again, the listener list size doesn't grow up again.
        assertTrue("Too many page selection listeners.", expectedPageSelectionListener >= getPageSelectionListeners().length);
        assertTrue("Too many part service listeners.", expectedPartServiceListeners >= getPartServiceListeners().length);
        assertTrue("Too many workbench window listeners.", expectedWindowSelectionListener >= getWorkbenchWindowSelectionListeners().length);

        // Check for one editor:
        openEditor(1);
        expectedPageSelectionListener = getPageSelectionListeners().length;
        expectedPartServiceListeners = getPartServiceListeners().length;
        expectedWindowSelectionListener = getWorkbenchWindowSelectionListeners().length;
        DDiagramEditor editor2 = openEditor(2);
        assertTrue("Review this test: opening editor should add selection listeners.", expectedPageSelectionListener < getPageSelectionListeners().length);
        assertTrue("Review this test: opening editor should add part service listeners.", expectedPartServiceListeners < getPartServiceListeners().length);
        assertTrue("Review this test: opening editor should add selection listeners.", expectedWindowSelectionListener < getWorkbenchWindowSelectionListeners().length);

        // close the second editor
        closeEditor(editor2);
        assertTrue("Too many page selection listeners.", expectedPageSelectionListener >= getPageSelectionListeners().length);
        assertTrue("Too many part service listeners.", expectedPartServiceListeners >= getPartServiceListeners().length);
        assertTrue("Too many workbench window listeners.", expectedWindowSelectionListener >= getWorkbenchWindowSelectionListeners().length);
    }

    private void closeEditor(DDiagramEditor editor) {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

    }

    private DDiagramEditor openEditor(int i) {
        Iterator<DRepresentation> iterator = getRepresentations(ENTITIES_DESC_NAME).iterator();

        while (iterator.hasNext()) {
            DRepresentation next = iterator.next();

            if (next.getName().equals(String.format(REPRESENTATION_NAME_PATTERN, i))) {
                DDiagram diag = (DDiagram) next;
                DDiagramEditor editor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diag, new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
                return editor;
            }

        }
        return null;

    }

    private void closeAllEditors() {
        for (DDiagramEditor editor : editors) {
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    private void openAllEditors() {
        Iterator<DRepresentation> iterator = getRepresentations(ENTITIES_DESC_NAME).iterator();
        editors = new ArrayList<DDiagramEditor>();
        while (iterator.hasNext()) {
            DRepresentation next = iterator.next();
            for (int i = 1; i <= 6; i++) {
                if (next.getName().equals(String.format(REPRESENTATION_NAME_PATTERN, i))) {
                    DDiagram diag = (DDiagram) next;
                    editors.add((DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diag, new NullProgressMonitor()));
                    TestsUtil.synchronizationWithUIThread();
                }
            }
        }
    }

    /**
     * Retrieves part service listener list by introspection.
     * 
     * @return all selection listeners
     */
    private Object[] getWorkbenchWindowSelectionListeners() {
        ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();

        Optional<Object> option = ReflectionHelper.getFieldValueWithoutException(selectionService, "listeners");
        if (option.isPresent()) {
            return ((ListenerList) option.get()).getListeners();
        }

        return null;
    }

    /**
     * Retrieves part service listener list by introspection.
     * 
     * @return all selection listeners
     */
    private Object[] getPageSelectionListeners() {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

        Object[] listeners = null;
        if (page instanceof IPartService) {
            Optional<Object> option = ReflectionHelper.getFieldValueWithoutException(page, "selectionListeners");
            if (option.isPresent()) {
                listeners = ((List) option.get()).toArray();
            }
        }

        if (listeners == null) {
            Optional<Object> option = ReflectionHelper.getFieldValueWithoutException(page, "selectionService");
            if (option.isPresent()) {
                ISelectionService pageSelectionService = (ISelectionService) option.get();
                Optional<Object> listenerListOption = ReflectionHelper.getFieldValueWithoutException(pageSelectionService, "listeners");
                if (listenerListOption.isPresent()) {
                    listeners = ((ListenerList) listenerListOption.get()).getListeners();
                }
            }
        }
        return listeners;
    }

    /**
     * Retrieves part service listener list by introspection.
     * 
     * @return all selection listeners
     */
    private Object[] getPartServiceListeners() {
        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

        Object[] listeners = null;
        if (page instanceof IPartService) {
            Optional<Object> option = ReflectionHelper.getFieldValueWithoutException(page, "partListenerList");
            if (option.isPresent()) {
                ListenerList list = (ListenerList) option.get();
                listeners = list.getListeners();
            }
        }

        if (listeners == null) {
            PartService partService = (PartService) ReflectionHelper.invokeMethodWithoutExceptionWithReturn(page, "getPartService", new Class[] {}, new Object[] {});
            if (partService != null) {
                Optional<Object> option = ReflectionHelper.getFieldValueWithoutException(partService, "listeners");
                PartListenerList listenerList = (PartListenerList) option.get();
                Optional<Object> listenerListOption = getFieldValueWithoutException(listenerList.getClass().getSuperclass(), listenerList, "listenerList");
                ListenerList list = (ListenerList) listenerListOption.get();
                listeners = list.getListeners();
            }
        }
        return listeners;
    }

    private Optional<Object> getFieldValueWithoutException(Class<?> clazz, Object instance, String fieldName) {
        Optional<Field> field = ReflectionHelper.setFieldVisibleWithoutException(clazz, fieldName);
        if (field.isPresent()) {
            try {
                return Optional.ofNullable(field.get().get(instance));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
        return Optional.empty();
    }
}
