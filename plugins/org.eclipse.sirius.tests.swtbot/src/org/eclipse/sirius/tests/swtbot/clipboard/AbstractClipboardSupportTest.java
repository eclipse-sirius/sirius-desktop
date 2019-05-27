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
package org.eclipse.sirius.tests.swtbot.clipboard;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.clipboard.SiriusClipboardManager;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.widget.ContextualMenuItemGetter;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;

import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;

/**
 * 
 * @author jdupont
 */
public abstract class AbstractClipboardSupportTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * The unchaught exceptions handler.
     */
    private UncaughtExceptionHandler exceptionHandler;

    /**
     * The platform error listener.
     */
    private ILogListener logListener;

    /**
     * The reported errors.
     */
    private LinkedHashMultimap<String, IStatus> errors;

    /**
     * Generic representation instance name.
     */
    protected static final String REPRESENTATION_WITH_GENERIC_PASTE = "GenericClipboard";

    /**
     * Custom representation instance name.
     */
    protected static final String REPRESENTATION_WITH_CUSTOM_PASTE = "CustomClipboard";

    /**
     * Generic representation instance name bis.
     */
    protected static final String REPRESENTATION_WITH_GENERIC_PASTE_BIS = "GenericClipboardBis";

    /**
     * Custom representation instance name bis.
     */
    protected static final String REPRESENTATION_WITH_CUSTOM_PASTE_BIS = "CustomClipboardBis";

    /**
     * Semantic model.
     */
    protected static final String MODEL = "1894.ecore";

    /**
     * Semantic model bis.
     */
    protected static final String MODEL_BIS = "1894Bis.ecore";

    /**
     * Sirius name.
     */
    protected static final String GENERIC_VIEWPOINT_NAME = "Design";

    /**
     * Sirius name.
     */
    protected static final String CUSTOM_VIEWPOINT_NAME = "vp-1894";

    /**
     * Sirius name.
     */
    protected static final String GENERIC_DESCRIPTION = "Entities";

    /**
     * Sirius name.
     */
    protected static final String CUSTOM_DESCRIPTION = "diagramWithCustomClipboard";

    /**
     * Sirius specific model.
     */
    protected static final String VSM_FILE = "vp-1894.odesign";

    /**
     * Session file.
     */
    protected static final String SESSION_FILE = "1894.aird";

    /**
     * Session file.
     */
    protected static final String SESSION_FILE_BIS = "1894Bis.aird";

    /**
     * Repository for data test.
     */
    protected static final String DATA_UNIT_DIR = "data/unit/copyPaste/";

    /**
     * Separator file.
     */
    protected static final String FILE_DIR = "/";

    /**
     * The local session.
     */
    protected UILocalSession localSession;

    /**
     * The local session bis.
     */
    protected UILocalSession localSessionBis;

    /**
     * The Session Resource wrapper bis.
     */
    protected UIResource sessionAirdResourceBis;

    /**
     * Dialect editor.
     */
    protected SWTBotSiriusDiagramEditor editor2;

    private boolean errorCatchActive;

    @SuppressWarnings("unused")
    private String errorMessage;

    private UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private void initErrorLoggers() {
        errors = LinkedHashMultimap.create();

        logListener = new ILogListener() {

            @Override
            public void logging(IStatus status, String plugin) {
                if (status.getSeverity() == IStatus.ERROR) {
                    errorOccurs(status, plugin);
                }
            }

        };
        Platform.addLogListener(logListener);

        exceptionHandler = new UncaughtExceptionHandler() {
            private String sourcePlugin = "Uncaught exception";

            @Override
            public void uncaughtException(Thread t, Throwable e) {

                IStatus status = new Status(IStatus.ERROR, sourcePlugin, sourcePlugin, e);
                errorOccurs(status, sourcePlugin);
            }
        };

        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    }

    private void disposeErrorLoggers() {
        if (logListener != null) {
            Platform.removeLogListener(logListener);
        }

        if (defaultUncaughtExceptionHandler != null) {
            Thread.setDefaultUncaughtExceptionHandler(defaultUncaughtExceptionHandler);
        }

        clearErrors();
    }

    /**
     * @param errorCatchActive
     *            error catch active
     */
    @Override
    protected synchronized void setErrorCatchActive(boolean errorCatchActive) {
        this.errorCatchActive = errorCatchActive;
        this.errorMessage = "";
    }

    private synchronized void errorOccurs(IStatus status, String sourcePlugin) {
        if (errorCatchActive) {
            errors.put(sourcePlugin, status);
        }
    }

    /**
     * check if an error occurs.
     * 
     * @return true if an error occurs.
     */
    @Override
    protected synchronized boolean doesAnErrorOccurs() {
        if (errors != null) {
            return errors.values().size() != 0;
        }
        return false;
    }

    /**
     * Clear errors.
     */
    protected void clearErrors() {
        if (errors != null) {
            errors.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean getAutoRefreshMode() {
        return true;
    }

    /**
     * ! Use with caution ! Will have side effect for cut and paste.
     * 
     * 
     * Check copy/cut/paste menu status from toolbar edit menu and contextual menu.
     * 
     * SWTbot cannot just check contextual menu items, it must try to click it.
     * 
     * Contextual menu is not well updated, and the only action to test is to click. This test is here to test
     * contextual menu sync with global "Edit" menu.
     * 
     * Other tests can only check edit menu.
     * 
     * @param editor
     *            the editor to check.
     * @param copyState
     *            expected copy menu status.
     * @param cutState
     *            expected cut menu status.
     * @param pasteState
     *            expected paste menu status.
     * 
     */
    protected void sideEffectAssertCopyCutPasteActivation(SWTBotSiriusDiagramEditor editor, boolean copyState, boolean cutState, boolean pasteState) {
        assertCopyCutPasteToolBarActivation(copyState, cutState, pasteState);

        // now tests context menu

        /* Init error log and uncauht exception handlers */
        initErrorLoggers();

        // Check Contextual Menu
        setErrorCatchActive(true);

        editor.clickContextMenu("Paste");
        assertEquals(pasteState, !doesAnErrorOccurs());

        setErrorCatchActive(false);
        clearErrors();
        setErrorCatchActive(true);

        editor.clickContextMenu("Copy");
        assertEquals(copyState, !doesAnErrorOccurs());

        setErrorCatchActive(false);
        clearErrors();
        setErrorCatchActive(true);

        editor.clickContextMenu("Cut");
        assertEquals(cutState, !doesAnErrorOccurs());

        setErrorCatchActive(false);
        clearErrors();
        setErrorCatchActive(true);

        // Disable error catching
        setErrorCatchActive(false);
        disposeErrorLoggers();
    }

    /**
     * Check copy/cut menu status from toolbar edit menu and contextual menu.
     * 
     * @param copyState
     *            expected copy menu enablement
     * @param cutState
     *            expected cut menu enablement
     */
    protected void assertCopyCutToolBarActivation(boolean copyState, boolean cutState) {
        SWTBotMenu copyMenu = editor.bot().menu("Edit").menu("Copy");
        assertEquals(true, copyMenu.isVisible());
        assertEquals("Copy action enabled ?", copyState, copyMenu.isEnabled());

        SWTBotMenu cutMenu = editor.bot().menu("Edit").menu("Cut");
        assertEquals(true, cutMenu.isVisible());
        assertEquals("Cut action enabled ?", cutState, cutMenu.isEnabled());
    }

    /**
     * Check copy/cut/paste menu status from toolbar edit menu and contextual menu.
     * 
     * @param copyState
     *            expected copy menu enablement
     * @param cutState
     *            expected cut menu enablement
     * @param pasteState
     *            expected delete menu enablement
     */
    protected void assertCopyCutPasteToolBarActivation(boolean copyState, boolean cutState, boolean pasteState) {
        assertCopyCutToolBarActivation(copyState, cutState);

        SWTBotMenu pasteMenu = bot.menu("Edit").menu("Paste");
        assertEquals(true, pasteMenu.isVisible());
        assertEquals("Paste action enabled ?", pasteState, pasteMenu.isEnabled());
    }

    /**
     * Assert on availability of copy/cut/paste menu item on contextual menu.
     * 
     * @param expectedCopyState
     *            expected copy menu enablement
     * @param expectedCutState
     *            expected cut menu enablement
     * @param expectedPasteState
     *            expected delete menu enablement
     */
    protected void assertCopyCutPasteMenuItemAvailabilityOnContextualMenu(boolean expectedCopyState, boolean expectedCutState, boolean expectedPasteState) {
        // Copy
        final Control control = editor.rootEditPart().part().getViewer().getControl();

        Result<MenuItem> menuItemGetter = new ContextualMenuItemGetter(control, new String[] { "&Edit", "Copy" });
        final MenuItem copyMenuItem = UIThreadRunnable.syncExec(menuItemGetter);

        boolean currentCopyState = copyMenuItem != null;
        if (currentCopyState) {
            currentCopyState = UIThreadRunnable.syncExec(new Result<Boolean>() {

                @Override
                public Boolean run() {
                    return copyMenuItem.isEnabled();
                }

            });
        }
        assertEquals("The Copy contextual menuItem was expected " + (expectedCopyState ? "available" : "unavailable") + " but was " + (currentCopyState ? "available" : "unavailable"),
                expectedCopyState, currentCopyState);

        // Cut
        menuItemGetter = new ContextualMenuItemGetter(control, new String[] { "&Edit", "Cut" });
        final MenuItem cutMenuItem = UIThreadRunnable.syncExec(menuItemGetter);
        boolean currentCutState = cutMenuItem != null;
        if (currentCutState) {
            currentCutState = UIThreadRunnable.syncExec(new Result<Boolean>() {

                @Override
                public Boolean run() {
                    return cutMenuItem.isEnabled();
                }

            });
        }
        assertEquals("The Cut contextual menuItem was expected " + (expectedCutState ? "available" : "unavailable") + " but was " + (currentCutState ? "available" : "unavailable"), expectedCutState,
                currentCutState);

        // Paste
        menuItemGetter = new ContextualMenuItemGetter(control, new String[] { "&Edit", "Paste" });
        final MenuItem pasteMenuItem = UIThreadRunnable.syncExec(menuItemGetter);
        boolean currentPasteState = pasteMenuItem != null;
        if (currentPasteState) {
            currentPasteState = UIThreadRunnable.syncExec(new Result<Boolean>() {

                @Override
                public Boolean run() {
                    return pasteMenuItem.isEnabled();
                }

            });
        }
        assertEquals("The Paste contextual menuItem was expected " + (expectedPasteState ? "available" : "unavailable") + " but was " + (currentPasteState ? "available" : "unavailable"),
                expectedPasteState, currentPasteState);
    }

    /**
     * clear viewpoint clipboard.
     */
    protected void clearSiriusClipboard() {
        // reinit viewpoint clipboard.
        SiriusClipboardManager.getInstance().dispose();
    }

    /**
     * Check that copy/paste on edit menu works well.
     * 
     * Check that element on menu are available or not.
     * 
     * Test that paste after a copy had paste element in semantic model and on the graphical view.
     * 
     * @param copyEditor
     *            the editor where copy action is do.
     * @param pasteEditor
     *            the editor where paste action is do.
     * @param expectedCanPaste
     *            boolean to know if paste is disabled (a "no paste" tool is defined or generic paste can be executed).
     * @param elemenToCopyName
     *            the element to copy. If null element to copy is the diagram.
     * @param pasteTarget
     *            the element to paste. If null element to paste is the diagram.
     * @param pastedName
     *            expected name of the pasted element.
     * @param numberElementCopy
     *            number of element corresponding to element paste name.
     */
    protected void checkCopyPaste(SWTBotSiriusDiagramEditor copyEditor, SWTBotGefEditPart elemenToCopyName, SWTBotSiriusDiagramEditor pasteEditor, boolean expectedCanPaste,
            SWTBotGefEditPart pasteTarget, String pastedName, int numberElementCopy) {

        // Empty viewpoint clipboard
        clearSiriusClipboard();

        // Init bot to copy
        copyEditor.show();
        copyEditor.setFocus();
        SWTBotGefEditPart partToCopyBot = copyEditor.rootEditPart().children().iterator().next();
        // if (!StringUtil.isEmpty(elemenToCopyName)) {
        // partToCopyBot = copyEditor.getEditPart(elemenToCopyName).parent();
        // }
        if (elemenToCopyName != null) {
            partToCopyBot = elemenToCopyName;
        }

        // Init paste target bot
        pasteEditor.show();
        pasteEditor.setFocus();
        SWTBotGefEditPart pasteDiagramBot = pasteEditor.rootEditPart().children().iterator().next();
        SWTBotGefEditPart pasteTargetBot = pasteDiagramBot;
        if (pasteTarget != null) {
            pasteTargetBot = pasteTarget;
        }
        // if (!StringUtil.isEmpty(pasteTarget)) {
        // pasteTargetBot = pasteEditor.getEditPart(pasteTarget).parent();
        // }

        // Select copy source
        copyEditor.show();
        copyEditor.setFocus();

        ICondition checkSelected = new CheckSelectedCondition(copyEditor, partToCopyBot.part());
        partToCopyBot.select();
        bot.waitUntil(checkSelected);

        // Checks that menu copy is accessible. Paste must be not
        // accessible
        assertCopyCutPasteToolBarActivation(true, !(partToCopyBot.part() instanceof DDiagramEditPart), false);

        copySelection();

        // Select paste target
        if (pasteEditor != copyEditor) {
            pasteEditor.show();
            pasteEditor.setFocus();
            pasteDiagramBot.select();
        }
        checkSelected = new CheckSelectedCondition(pasteEditor, pasteTargetBot.part());
        pasteTargetBot.select();
        bot.waitUntil(checkSelected);

        assertCopyCutPasteToolBarActivation(true, !(pasteTargetBot.part() instanceof DDiagramEditPart), expectedCanPaste);

        // "No paste" tool -> do not try to paste
        if (!expectedCanPaste) {
            return;
        }

        pasteInSelection();

        // check IdentifiedElement.uid unicity
        checkUidUnicity((DDiagramEditPart) copyEditor.mainEditPart().part(), (DDiagramEditPart) pasteDiagramBot.part());
        // Check DNode element is paste
        checkNumberOfDElementWithName(pasteDiagramBot, pastedName, numberElementCopy);
        // Check DNode element is paste
        checkGMFCopy(pasteEditor, pastedName, numberElementCopy);

        // TODO check new edit part : pasted name shoudl be different to use the
        // following commented code, check expected part type number befiore and
        // after paste
        // SWTBotGefEditPart pastedPartBot =
        // pasteEditor.getEditPart(pastedName).parent();
        // assertNotNull(pastedPartBot);

        // Test undo
        bot.menu("Edit").menu("Undo Paste").click();
        // Check DNode element is paste
        checkNumberOfDElementWithName(pasteDiagramBot, pastedName, numberElementCopy - 1);
        // Check DNode element is paste
        checkGMFCopy(pasteEditor, pastedName, numberElementCopy - 1);

        // Test Redo
        bot.menu("Edit").menu("Redo Paste").click();
        // check IdentifiedElement.uid unicity
        checkUidUnicity((DDiagramEditPart) copyEditor.mainEditPart().part(), (DDiagramEditPart) pasteDiagramBot.part());
        // Check DNode element is paste
        checkNumberOfDElementWithName(pasteDiagramBot, pastedName, numberElementCopy);
        // Check DNode element is paste
        checkGMFCopy(pasteEditor, pastedName, numberElementCopy);

    }

    /**
     * Check that copy/paste on edit menu works well.
     * 
     * Check that element on menu are available or not.
     * 
     * Test that paste after a copy had paste element in semantic model and on the graphical view.
     * 
     * @param copyEditor
     *            the editor where copy action is do.
     * @param pasteEditor
     *            the editor where paste action is do.
     * @param expectedCanPaste
     *            boolean to know if paste is disabled (a "no paste" tool is defined or generic paste can be executed).
     * @param listElementToCopy
     *            list of elements to copy.
     * @param pasteTarget
     *            the element to paste. If null element to paste is the diagram.
     * @param pastedName
     *            expected name of the pasted element. *
     */
    protected void checkCopyPaste(final SWTBotSiriusDiagramEditor copyEditor, final List<SWTBotGefEditPart> listElementToCopy, SWTBotSiriusDiagramEditor pasteEditor, boolean expectedCanPaste,
            SWTBotGefEditPart pasteTarget, Map<String, Integer> pastedName) {
        // Empty viewpoint clipboard
        clearSiriusClipboard();

        // Init bot to copy
        copyEditor.show();
        copyEditor.setFocus();
        copyEditor.select(listElementToCopy);

        SWTBotGefEditPart pasteDiagramBot = pasteEditor.rootEditPart().children().iterator().next();
        SWTBotGefEditPart pasteTargetBot = pasteDiagramBot;
        if (pasteTarget != null) {
            pasteTargetBot = pasteTarget;
        }

        // Init paste target bot
        pasteEditor.show();
        pasteEditor.setFocus();

        // Checks that menu copy is accessible. Paste must be not
        // accessible
        assertCopyCutPasteToolBarActivation(true, true, false);

        copySelection();

        // Select paste target
        if (pasteEditor != copyEditor) {
            pasteEditor.show();
            pasteEditor.setFocus();
            pasteDiagramBot.select();
        }
        ICondition checkSelected = new CheckSelectedCondition(pasteEditor, pasteTargetBot.part());
        pasteTargetBot.select();
        bot.waitUntil(checkSelected);

        assertCopyCutPasteToolBarActivation(true, !(pasteTargetBot.part() instanceof DDiagramEditPart), expectedCanPaste);

        // "No paste" tool -> do not try to paste
        if (!expectedCanPaste) {
            return;
        }

        pasteInSelection();

        // check IdentifiedElement.uid unicity
        checkUidUnicity((DDiagramEditPart) copyEditor.mainEditPart().part(), (DDiagramEditPart) pasteDiagramBot.part());

        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteDiagramBot, "attributee1", pastedName.get("attributee1"));
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, "attributee1", pastedName.get("attributee1"));
        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteDiagramBot, "attributee2", pastedName.get("attributee2"));
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, "attributee2", pastedName.get("attributee2"));

        // Test undo
        bot.menu("Edit").menu("Undo Paste").click();

        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteDiagramBot, "attributee1", pastedName.get("attributee1") - 1);
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, "attributee1", pastedName.get("attributee1") - 1);
        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteDiagramBot, "attributee2", pastedName.get("attributee2") - 1);
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, "attributee2", pastedName.get("attributee2") - 1);

        // Test Redo
        bot.menu("Edit").menu("Redo Paste").click();

        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteDiagramBot, "attributee1", pastedName.get("attributee1"));
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, "attributee1", pastedName.get("attributee1"));
        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteDiagramBot, "attributee2", pastedName.get("attributee2"));
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, "attributee2", pastedName.get("attributee2"));
    }

    /**
     * Check that copy/paste on edit menu works well.
     * 
     * Check that element on menu are available or not.
     * 
     * Test that paste after a copy had paste element in semantic model and on the graphical view.
     * 
     * @param copyEditor
     *            the editor where copy action is do.
     * @param pasteEditor
     *            the editor where paste action is do.
     * @param expectedCanPaste
     *            boolean to know if paste is disabled (a "no paste" tool is defined or generic paste can be executed).
     * @param elementToCopy
     *            element to copy.
     * @param listPasteTarget
     *            list of elements to paste.
     * @param pastedName
     *            expected name of the pasted element.
     * @param numberElementCopy
     *            number of element corresponding to element paste name.
     */
    protected void checkCopyPaste(final SWTBotSiriusDiagramEditor copyEditor, final SWTBotGefEditPart elementToCopy, SWTBotSiriusDiagramEditor pasteEditor, boolean expectedCanPaste,
            List<SWTBotGefEditPart> listPasteTarget, String pastedName, int numberElementCopy) {
        // Empty viewpoint clipboard
        clearSiriusClipboard();

        // Init bot to copy
        copyEditor.show();
        copyEditor.setFocus();
        SWTBotGefEditPart partToCopyBot = copyEditor.rootEditPart().children().iterator().next();
        // if (!StringUtil.isEmpty(elemenToCopyName)) {
        // partToCopyBot = copyEditor.getEditPart(elemenToCopyName).parent();
        // }
        if (elementToCopy != null) {
            partToCopyBot = elementToCopy;
        }

        // Init paste target bot
        pasteEditor.show();
        pasteEditor.setFocus();

        // Select copy source
        copyEditor.show();
        copyEditor.setFocus();

        ICondition checkSelected = new CheckSelectedCondition(copyEditor, partToCopyBot.part());
        partToCopyBot.select();
        bot.waitUntil(checkSelected);

        // Checks that menu copy is accessible. Paste must be not
        // accessible
        assertCopyCutPasteToolBarActivation(true, !(partToCopyBot.part() instanceof DDiagramEditPart), false);

        copySelection();

        // "No paste" tool -> do not try to paste
        if (!expectedCanPaste) {
            return;
        }

        copyEditor.select(listPasteTarget);

        SWTBotGefEditPart pasteDiagramBot = pasteEditor.rootEditPart().children().iterator().next();
        SWTBotGefEditPart pasteTargetBot = pasteDiagramBot;

        pasteInSelection();

        // check IdentifiedElement.uid unicity
        checkUidUnicity(elementToCopy.part(), (DDiagramEditPart) pasteDiagramBot.part());

        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteTargetBot, pastedName, numberElementCopy);
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, pastedName, numberElementCopy);

        // Test undo
        bot.menu("Edit").menu("Undo Paste").click();
        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteTargetBot, pastedName, numberElementCopy - 3);
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, pastedName, numberElementCopy - 3);

        // Test Redo
        bot.menu("Edit").menu("Redo Paste").click();
        // check IdentifiedElement.uid unicity
        checkUidUnicity(elementToCopy.part(), (DDiagramEditPart) pasteDiagramBot.part());
        // Check DNode element is paste
        checkNumberOfDChildrenElementWithName(pasteTargetBot, pastedName, numberElementCopy);
        // Check DNode element is paste
        checkGMFChildrenCopy(pasteEditor, pastedName, numberElementCopy);
    }

    /**
     * Check that the elements have a different uid and check that EObjects contained in targetDiagramEditPart have a
     * different uid than those in diagram containing sourceEditPart.
     * 
     * @param sourceEditPart
     *            Source edit part to check (source of the copy/paste action)
     * @param targetDiagramEditPart
     *            Target diagram edit part to check
     */
    private void checkUidUnicity(final EditPart sourceEditPart, DDiagramEditPart targetDiagramEditPart) {
        if (sourceEditPart instanceof DDiagramEditPart) {
            checkUidUnicity((DDiagramEditPart) sourceEditPart, targetDiagramEditPart);
        } else {
            checkUidUnicityAmongEObject(sourceEditPart);
            checkUidUnicity((DDiagramEditPart) sourceEditPart.getRoot().getChildren().iterator().next(), targetDiagramEditPart);
        }
    }

    /**
     * Check that the elements have a different uid and check that EObjects contained in targetDiagramEditPart have a
     * different uid than those in sourceDiagramEditPart.
     * 
     * @param sourceDiagramEditPart
     *            Source diagram edit part to check
     * @param targetDiagramEditPart
     *            Target diagram edit part to check
     */
    private void checkUidUnicity(final DDiagramEditPart sourceDiagramEditPart, DDiagramEditPart targetDiagramEditPart) {
        checkUidUnicityAmongEObject(sourceDiagramEditPart);

        if (sourceDiagramEditPart != targetDiagramEditPart) {
            EObject diagramSource = ((View) sourceDiagramEditPart.getModel()).getElement();
            Iterable<EObject> it = () -> diagramSource.eAllContents();
            Stream<String> diagramSourceUids = StreamSupport.stream(it.spliterator(), false).filter(IdentifiedElement.class::isInstance).map(IdentifiedElement.class::cast)
                    .map(IdentifiedElement::getUid);
            EObject diagramTarget = ((View) targetDiagramEditPart.getModel()).getElement();
            Iterable<EObject> it2 = () -> diagramTarget.eAllContents();
            List<String> diagramTargetUids = StreamSupport.stream(it2.spliterator(), false).filter(IdentifiedElement.class::isInstance).map(IdentifiedElement.class::cast)
                    .map(IdentifiedElement::getUid).collect(Collectors.toList());
            assertFalse("The element in the copied diagram should have different uid than in source diagram", diagramSourceUids.anyMatch(uid -> diagramTargetUids.contains(uid)));

            checkUidUnicityAmongEObject(targetDiagramEditPart);
        }
    }

    /**
     * Check that the elements have a different uid.
     * 
     * @param swtBotEditPart
     *            Edit part to check.
     */
    private void checkUidUnicityAmongEObject(final EditPart editPart) {
        EObject object = ((View) editPart.getModel()).getElement();
        Iterable<EObject> it = () -> object.eAllContents();
        long nbIdentifiedElement = StreamSupport.stream(it.spliterator(), false).filter(IdentifiedElement.class::isInstance).count();
        long nbDifferentUid = StreamSupport.stream(it.spliterator(), false).filter(IdentifiedElement.class::isInstance).map(IdentifiedElement.class::cast).map(IdentifiedElement::getUid).distinct()
                .count();
        assertEquals("The elements should all have a different uid.", nbIdentifiedElement, nbDifferentUid);
    }

    /**
     * Check that cut from edit menu, delete semantic elements and graphical elements on view.
     * 
     * @param cutEditor
     *            the editor where cut is do.
     * @param elementToCutName
     *            name of the element to cut (should be unique)
     * @param expectedCanDelete
     *            to know if a delete tool is defined.
     */
    protected void checkCutPaste(SWTBotSiriusDiagramEditor cutEditor, String elementToCutName, boolean expectedCanDelete) {
        // Empty viewpoint clipboard
        clearSiriusClipboard();

        // Init bot to copy
        cutEditor.show();
        cutEditor.setFocus();
        SWTBotGefEditPart diagramCutBot = cutEditor.rootEditPart().children().iterator().next();
        SWTBotGefEditPart partToCutBot = diagramCutBot;
        if (!StringUtil.isEmpty(elementToCutName)) {
            partToCutBot = cutEditor.getEditPart(elementToCutName).parent();
        }

        ICondition checkSelected = new CheckSelectedCondition(cutEditor, partToCutBot.part());
        partToCutBot.select();
        bot.waitUntil(checkSelected);

        // Checks that menu copy is accessible. Paste must be not
        // accessible
        assertCopyCutPasteToolBarActivation(true, expectedCanDelete, false);

        checkUidUnicityAmongEObject(diagramCutBot.part());

        if (expectedCanDelete) {
            cutSelection();

            // Check DNode element was delete
            boolean cutDeleteAll;
            try {
                cutEditor.getEditPart(elementToCutName);
                cutDeleteAll = false;
            } catch (WidgetNotFoundException wnfe) {
                cutDeleteAll = true;
            }
            assertEquals(expectedCanDelete, cutDeleteAll);

            checkNumberOfDElementWithName(diagramCutBot, elementToCutName, 0);

            // Select diagram
            checkSelected = new CheckSelectedCondition(cutEditor, diagramCutBot.part());
            diagramCutBot.select();
            bot.waitUntil(checkSelected);
            assertCopyCutPasteToolBarActivation(true, false, true);

            pasteInSelection();
            // check IdentifiedElement.uid unicity
            checkUidUnicityAmongEObject(diagramCutBot.part());

            checkNumberOfDElementWithName(diagramCutBot, elementToCutName, 1);

            checkGMFCopy(cutEditor, elementToCutName, 1);
        }
    }

    private void checkNumberOfDElementWithName(SWTBotGefEditPart diagramBot, String elementName, int expectedNumber) {
        // Check semantic element is paste
        DSemanticDiagram semanticDiagram = (DSemanticDiagram) ((Diagram) diagramBot.part().getModel()).getElement();
        List<DDiagramElement> diagramElements = semanticDiagram.getOwnedDiagramElements();
        int nbDDE = 0;
        for (DDiagramElement element : diagramElements) {
            if (elementName.equals(element.getName())) {
                nbDDE++;
            }
        }
        assertEquals(expectedNumber, nbDDE);
    }

    private void checkNumberOfDChildrenElementWithName(SWTBotGefEditPart diagramBot, String elementName, int expectedNumber) {
        // Check semantic element is paste
        DSemanticDiagram semanticDiagram = (DSemanticDiagram) ((Diagram) diagramBot.part().getModel()).getElement();
        List<DDiagramElement> diagramElements = semanticDiagram.getOwnedDiagramElements();
        int nbDDE = 0;
        for (DDiagramElement element : diagramElements) {
            if (element instanceof DNodeList && ((DNodeList) element).getOwnedElements().size() > 0)
                for (DDiagramElement diagramElement : ((DNodeList) element).getOwnedElements()) {
                    if (elementName.equals(diagramElement.getName())) {
                        nbDDE++;
                    }
                }
        }
        assertEquals(expectedNumber, nbDDE);
    }

    /**
     * Check that graphical elements are copy.
     * 
     * @param swtBotEditor
     *            the editor where copy is do.
     * @param pastedName
     *            pasted name.
     * @param numberElementCopy
     *            number of element parts copy.
     */
    private void checkGMFCopy(SWTBotSiriusDiagramEditor swtBotEditor, String pastedName, int numberElementCopy) {
        DDiagramEditPart dDiagramEditPart = (DDiagramEditPart) swtBotEditor.rootEditPart().children().iterator().next().part();
        // Check gmf node is present
        Diagram diagramGmf = (Diagram) dDiagramEditPart.getModel();
        int nbNodeGmfCopy = 0;
        for (Node gmfNode : Iterables.filter(diagramGmf.getPersistedChildren(), Node.class)) {
            if (gmfNode.getElement() instanceof DNodeList) {
                DNodeList dde = (DNodeList) gmfNode.getElement();
                if (pastedName.equals(dde.getName())) {
                    nbNodeGmfCopy++;
                    // Check that sub elements are copy too
                    assertEquals(1, dde.getOwnedElements().size());
                    assertEquals("attributee2", dde.getOwnedElements().iterator().next().getName());
                }
            }
        }
        for (Edge gmfEdge : Iterables.filter(diagramGmf.getEdges(), Edge.class)) {
            if (gmfEdge.getElement() instanceof DEdge) {
                DEdge deg = (DEdge) gmfEdge.getElement();
                if (pastedName.equals(deg.getName())) {
                    nbNodeGmfCopy++;
                }
            }
        }
        assertEquals(numberElementCopy, nbNodeGmfCopy);
    }

    /**
     * Check that graphical elements children are copy.
     * 
     * @param swtBotEditor
     *            the editor where copy is do.
     * @param pastedName
     *            pasted name.
     * @param numberElementCopy
     *            number of element parts copy.
     */
    private void checkGMFChildrenCopy(SWTBotSiriusDiagramEditor swtBotEditor, String pastedName, int numberElementCopy) {
        DDiagramEditPart dDiagramEditPart = (DDiagramEditPart) swtBotEditor.rootEditPart().children().iterator().next().part();
        // Check gmf node is present
        Diagram diagramGmf = (Diagram) dDiagramEditPart.getModel();
        int nbNodeGmfCopy = 0;
        for (Node gmfNode : Iterables.filter(diagramGmf.getPersistedChildren(), Node.class)) {
            DNodeList dde = (DNodeList) gmfNode.getElement();
            for (DDiagramElement element : dde.getOwnedElements()) {
                if (pastedName.equals(element.getName())) {
                    nbNodeGmfCopy++;
                }
            }
        }
        assertEquals(numberElementCopy, nbNodeGmfCopy);
    }

    /**
     * Paste actions.
     */
    protected void pasteInSelection() {
        SWTBotMenu menu = bot.menu("Edit").menu("Paste");
        assertEquals("Paste menu should be enable", true, menu.isEnabled());
        menu.click();
    }

    /**
     * Copy action
     */
    protected void copySelection() {
        SWTBotMenu menu = bot.menu("Edit").menu("Copy");
        assertEquals("Copy menu should be enable", true, menu.isEnabled());
        menu.click();
    }

    /**
     * Cut action.
     */
    protected void cutSelection() {
        SWTBotMenu menu = bot.menu("Edit").menu("Cut");
        assertEquals("Cut menu should be enable", true, menu.isEnabled());
        menu.click();
    }

    /**
     * Check viewpoint clipboard : semantic elements and DdiagramElements. Cannot be used to check notes.
     * 
     * @param selection
     *            selected bots.
     */
    protected void checkSiriusClipboardContent(SWTBotGefEditPart... selection) {
        assertEquals("Clipboard is not", selection != null && selection.length != 0, SiriusClipboardManager.getInstance().hasPasteData());

        Session session = localSession.getOpenedSession();
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.setClipboard(null);

        // Get viewpoint clipboard.
        SiriusClipboardManager.getInstance().setDomainClipboard(domain);

        assertEquals("Wrong clipboard size", selection == null ? 0 : selection.length,
                domain.getClipboard() == null ? 0 : Iterables.size(Iterables.filter(domain.getClipboard(), DSemanticDecorator.class)));

        for (SWTBotGefEditPart selectedBot : selection) {
            IGraphicalEditPart part = (IGraphicalEditPart) selectedBot.part();
            EObject element = ((View) part.getModel()).getElement();
            assertTrue("Type not supported by viewpoint clipboard", element instanceof DSemanticDecorator);
            DSemanticDecorator semDec = (DSemanticDecorator) element;
            EObject sem = semDec.getTarget();

            DSemanticDecorator copySemDec = (DSemanticDecorator) checkCopyInClipboard(domain, semDec);
            EObject copySem = checkCopyInClipboard(domain, sem);

            assertEquals("Target link was not copied", copySem, copySemDec.getTarget());
        }
    }

    private EObject checkCopyInClipboard(TransactionalEditingDomain domain, EObject source) {
        for (EObject clip : Iterables.filter(Iterables.filter(domain.getClipboard(), source.getClass()), EObject.class)) {
            assertEquals(source.eClass(), clip.eClass());
            boolean copy = false;

            for (EAttribute attr : source.eClass().getEAllAttributes()) {
                copy = copy && source.eGet(attr) == clip.eGet(attr);
            }

            if (copy = true) {
                return clip;
            }
        }
        fail("Sirius clipboard do not contain a copy of " + source);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        clearSiriusClipboard();
        disposeErrorLoggers();

        if (editor != null) {
            editor.close();
        }

        if (editor2 != null) {
            editor2.close();
        }

        localSession = null;
        editor = null;
        editor2 = null;

        exceptionHandler = null;
        logListener = null;

        if (errors != null) {
            errors.clear();
        }
        errors = null;

        super.tearDown();
    }

}
