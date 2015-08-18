/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.util;

import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.common.ui.tools.api.dialog.SiriusMessageDialogWithToggle;
import org.eclipse.sirius.common.ui.tools.internal.util.ISaveDialogExtensionDescriptor;
import org.eclipse.sirius.common.ui.tools.internal.util.ISaveDialogExtensionRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.part.PageBook;

import com.google.common.collect.Maps;

/**
 * Utility class to avoid verbose SWT code.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public final class SWTUtil {

    private static final String SAVE_CHANGES_QUESTION = "{0} have been modified{1}. Save changes{2}?";

    private static final String OPEN_ELSEWHERE_MESSAGE = ", but are still open elsewhere with identical changes. Closing this will not lose those changes";

    private static final String OPEN_ELSEWHERE_QUESTION = " now anyway";

    /**
     * avoid instantiation
     */
    private SWTUtil() {
    }

    /**
     * utility method to create a {@link Label} widget.
     * 
     * @param parent
     *            the parent widget
     * @param image
     *            the image for the label
     * @param style
     *            see {@link SWT}
     * @return the new label widget
     */
    public static Label createLabel(final Composite parent, final Image image, final int style) {
        final Label label = new Label(parent, style);
        label.setImage(image);
        return label;
    }

    /**
     * utility method to create a {@link Label} widget.
     * 
     * @param parent
     *            the parent widget
     * @param text
     *            the text for the label
     * @param style
     *            see {@link SWT}
     * @return the new label widget
     */
    public static Label createLabel(final Composite parent, final String text, final int style) {
        final Label label = new Label(parent, style);
        label.setText(text);
        return label;
    }

    /**
     * Utility method to create a {@link Label} widget with the SWT.NONE style.
     * 
     * @param parent
     *            the parent widget
     * @param text
     *            the text for the label
     * @return the new label widget
     */
    public static Label createLabel(final Composite parent, final String text) {
        return SWTUtil.createLabel(parent, text, SWT.NONE);
    }

    /**
     * Utility method to create a {@link Label} widget with the SWT.NONE style.
     * 
     * @param parent
     *            the parent widget
     * @param image
     *            the image for the label
     * @return the new label widget
     */
    public static Label createLabel(final Composite parent, final Image image) {
        return SWTUtil.createLabel(parent, image, SWT.NONE);
    }

    /**
     * Utility method to create a {@link PageBook} widget dispose it when the
     * parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @param style
     *            see {@link SWT}
     * @return the new pagebook widget
     */
    public static PageBook createPageBook(final Composite parent, final int style) {
        final PageBook pageBook = new PageBook(parent, style);

        // add a dispose listener
        SWTUtil.addDisposeListener(parent, pageBook);

        return pageBook;
    }

    /**
     * Utility method to create a {@link PageBook} widget with the SWT.NONE
     * style and dispose it when the parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @return the new pagebook widget
     */
    public static PageBook createPageBook(final Composite parent) {
        return SWTUtil.createPageBook(parent, SWT.NONE);
    }

    /**
     * Utility method to create a {@link FilteredTree} widget and dispose it
     * when the parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @param style
     *            see {@link SWT}
     * @param filter
     *            see {@link PatternFilter}
     * @return the new filtered tree widget
     */
    public static FilteredTree createFilteredTree(final Composite parent, final int style, final PatternFilter filter) {
        final FilteredTree filteredTree = new FilteredTree(parent, style, filter, true);

        // add a dispose listener
        SWTUtil.addDisposeListener(parent, filteredTree);

        return filteredTree;

    }

    /**
     * Utility method to create a composite widget which fill horizontally and
     * dispose it when the parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @param columns
     *            the number of columns in the grid layout for the new
     *            composite.
     * @param equalColumns
     *            make columns equals width
     * @return the new composite widget
     */
    public static Composite createCompositeHorizontalFill(final Composite parent, final int columns, final boolean equalColumns) {
        return SWTUtil.createCompositeFill(parent, columns, equalColumns, true, false);
    }

    /**
     * Utility method to create a composite widget which fill vertically and
     * dispose it when the parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @param columns
     *            the number of columns in the grid layout for the new
     *            composite.
     * @param equalColumns
     *            make columns equals width
     * @return the new composite widget
     */
    public static Composite createCompositeVerticalFill(final Composite parent, final int columns, final boolean equalColumns) {
        return SWTUtil.createCompositeFill(parent, columns, equalColumns, false, true);
    }

    /**
     * Utility method to create a composite widget which fill both horizontally
     * and vertically, and dispose it when the parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @param columns
     *            the number of columns in the grid layout for the new
     *            composite.
     * @param equalColumns
     *            make columns equals width
     * @return the new composite widget
     */
    public static Composite createCompositeBothFill(final Composite parent, final int columns, final boolean equalColumns) {
        return SWTUtil.createCompositeFill(parent, columns, equalColumns, true, true);
    }

    /**
     * Utility method to create a composite widget and dispose it when the
     * parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @param columns
     *            the number of columns in the grid layout for the new
     *            composite.
     * @param equalColumns
     *            make columns equals width
     * @return the new composite widget
     */
    private static Composite createCompositeFill(final Composite parent, final int columns, final boolean equalColumns, final boolean horizontalFill, final boolean verticalFill) {
        final Composite composite = new Composite(parent, SWT.NONE);

        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = columns;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        gridLayout.makeColumnsEqualWidth = equalColumns;

        if (horizontalFill && !verticalFill) {
            composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        } else if (!horizontalFill && verticalFill) {
            composite.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, true));
        } else if (!horizontalFill && !verticalFill) {
            composite.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        } else {
            composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        }

        composite.setLayout(gridLayout);

        // add a dispose listener
        SWTUtil.addDisposeListener(parent, composite);

        return composite;
    }

    /**
     * Utility method to create a composite widget and dispose it when the
     * parent will be disposed.
     * 
     * @param parent
     *            the parent widget
     * @param columns
     *            the number of columns in the grid layout for the new
     *            composite.
     * @param equalColumns
     *            make columns equals width
     * @param layoutData
     *            the grid data
     * @return the new composite widget
     */
    public static Composite createComposite(final Composite parent, final int columns, final boolean equalColumns, final GridData layoutData) {
        final Composite composite = new Composite(parent, SWT.NONE);

        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = columns;
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        gridLayout.makeColumnsEqualWidth = equalColumns;

        composite.setLayoutData(layoutData);
        composite.setLayout(gridLayout);

        // add a dispose listener
        SWTUtil.addDisposeListener(parent, composite);

        return composite;
    }

    /**
     * Dispose the child when the parent is disposed.
     * 
     * @param parent
     *            the parent composite
     * @param child
     *            the child composite
     */
    public static void addDisposeListener(final Composite parent, final Composite child) {
        parent.addDisposeListener(new DefaultDisposeListener(child));
    }

    /**
     * Show a dialog to ask the user to save or not the content of the Session.
     * The window's return codes can be ISaveablePart2.CANCEL,
     * ISaveablePart2.YES or ISaveablePart2.NO.
     * 
     * @param objectToSave
     *            the object to save
     * @param label
     *            the name of the element to save or any other label
     * @param canCancel
     *            <code>true</code> if the save can be cancel,
     *            <code>false</code> otherwise
     * @return the return code
     */
    public static int showSaveDialog(final Object objectToSave, final String label, final boolean canCancel) {
        return showSaveDialog(objectToSave, label, canCancel, false, false);
    }

    /**
     * Show a dialog to ask the user to save or not the content of the Session.
     * The window's return codes can be ISaveablePart2.CANCEL,
     * ISaveablePart2.YES or ISaveablePart2.NO.
     * 
     * @param objectToSave
     *            the object to save
     * @param label
     *            the name of the element to save or any other label
     * @param canCancel
     *            <code>true</code> if the save can be cancel,
     *            <code>false</code> otherwise
     * @param stillOpenElsewhere
     *            <code>true</code> the object to save is open elsewhere, prompt
     *            if IWorkbenchPreferenceConstants.
     *            PROMPT_WHEN_SAVEABLE_STILL_OPEN, <code>false</code> otherwise
     * @param onlyIfCustomFound
     *            force to return ISaveablePart2.DEFAULT if no
     *            saveDialogExtension was found.
     * 
     * @return the return code
     */
    public static int showSaveDialog(final Object objectToSave, final String label, final boolean canCancel, final boolean stillOpenElsewhere, final boolean onlyIfCustomFound) {

        final RunnableWithResult runnable = new RunnableWithResult() {
            private int result = ISaveablePart2.DEFAULT;

            public void run() {
                // Case 1 : an ISaveDialogExtension has been contributed
                boolean customSaveDialogProvided = false;
                for (ISaveDialogExtensionDescriptor saveDialogExtensionDescriptor : ISaveDialogExtensionRegistry.getRegisteredExtensions()) {
                    ISaveDialogExtension saveDialogExtension = saveDialogExtensionDescriptor.getSaveDialogExtension();
                    if (saveDialogExtension.isSaveDialogFor(objectToSave)) {
                        // we use it to build the save dialog
                        result = showProvidedSaveDialog(saveDialogExtension, objectToSave, label, canCancel, stillOpenElsewhere);
                        customSaveDialogProvided = true;
                    }
                }

                // Case 2 : no custom save dialog is provided
                // we build a default save dialog
                if (!customSaveDialogProvided && !onlyIfCustomFound) {
                    result = showStandardSaveDialog(label, canCancel, stillOpenElsewhere);
                }
            }

            public int getResult() {
                return result;
            }
        };

        Display.getDefault().syncExec(runnable);
        return runnable.getResult();
    }

    /**
     * Shows a dialog to ask the user to save or not the content of the Session,
     * using the given saveDialogExtension, that may define new buttons and
     * additional behavior. The window's return codes can be
     * ISaveablePart2.CANCEL, ISaveablePart2.YES or ISaveablePart2.NO.
     * 
     * 
     * @param saveDialogExtension
     *            the {@link ISaveDialogExtension} to use for showing this save
     *            window
     * @param objectToSave
     *            the object to save
     * @param label
     *            the label of the window
     * @param canCancel
     *            indicates whether user can cancel this save action or not
     * @param stillOpenElsewhere
     *            <code>true</code> the object to save is open elsewhere,
     *            <code>false</code> otherwise
     * @return the return code
     */
    private static int showProvidedSaveDialog(ISaveDialogExtension saveDialogExtension, Object objectToSave, String label, final boolean canCancel, boolean stillOpenElsewhere) {
        // Step 1: getting the save buttons
        Map<String, Integer> buttons = saveDialogExtension.getButtons(stillOpenElsewhere);

        // Step 2 :opening window
        int temporaryResult = openSaveDialog(label, canCancel, buttons, stillOpenElsewhere);

        // Step 3 : using save contribution to compute additional actions
        // according to the selected choice
        // an
        return saveDialogExtension.reactToValue(objectToSave, temporaryResult, stillOpenElsewhere);
    }

    /**
     * Show a dialog to ask the user to save or not the content of the Session.
     * The window's return codes can be ISaveablePart2.CANCEL,
     * ISaveablePart2.YES or ISaveablePart2.NO.
     * 
     * @param label
     *            the name of the element to save or any other label
     * @param canCancel
     *            <code>true</code> if the save can be cancel,
     *            <code>false</code> otherwise
     * @param stillOpenElsewhere
     *            <code>true</code> the object to save is open elsewhere,
     *            <code>false</code> otherwise
     * @return the return code
     */
    private static int showStandardSaveDialog(final String label, final boolean canCancel, boolean stillOpenElsewhere) {
        // Step 1: getting the save buttons
        Map<String, Integer> buttons = Maps.newLinkedHashMap();
        buttons.put(IDialogConstants.YES_LABEL, IDialogConstants.YES_ID);
        buttons.put(IDialogConstants.NO_LABEL, IDialogConstants.NO_ID);

        // Step 2 :opening window
        int temporaryResult = openSaveDialog(label, canCancel, buttons, stillOpenElsewhere);

        return temporaryResult;
    }

    /**
     * @see org.eclipse.internal.SaveablesList#promptForSaving(List,
     *      org.eclipse.jface.window.IShellProvider,
     *      org.eclipse.jface.operation.IRunnableContext, boolean, boolean)
     */
    private static int openSaveDialog(String label, final boolean canCancel, Map<String, Integer> buttons, boolean stillOpenElsewhere) {
        int choice = ISaveablePart2.YES;

        // Get user preference for still open beahvior
        IPreferenceStore platformUIPrefStore = PlatformUI.getPreferenceStore();
        boolean dontPrompt = stillOpenElsewhere && !platformUIPrefStore.getBoolean(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN);
        if (dontPrompt) {
            choice = ISaveablePart2.NO;
        } else {
            final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window != null && buttons != null) {
                final MessageDialog dialog = createSaveDialog(label, canCancel, buttons, stillOpenElsewhere, window);

                choice = dialog.open();

                // User has pressed "Escape" or has closed the dialog
                if (choice == SWT.DEFAULT) {
                    choice = IDialogConstants.CANCEL_ID;
                }

                // React to the use preference choice.
                // map value of choice back to ISaveablePart2 values
                switch (choice) {
                case IDialogConstants.YES_ID:
                    choice = ISaveablePart2.YES;
                    break;
                case IDialogConstants.NO_ID:
                    choice = ISaveablePart2.NO;
                    break;
                case IDialogConstants.CANCEL_ID:
                    choice = ISaveablePart2.CANCEL;
                    break;
                default:
                    break;
                }

                if (stillOpenElsewhere) {
                    MessageDialogWithToggle dialogWithToggle = (MessageDialogWithToggle) dialog;
                    if (choice != ISaveablePart2.CANCEL && dialogWithToggle.getToggleState()) {
                        // change the rpeference
                        platformUIPrefStore.setValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN, false);
                    }
                }
            }
        }

        return choice;
    }

    private static MessageDialog createSaveDialog(String label, final boolean canCancel, Map<String, Integer> buttons, boolean stillOpenElsewhere, final IWorkbenchWindow window) {
        final MessageDialog dialog;
        if (canCancel) {
            buttons.put(IDialogConstants.CANCEL_LABEL, IDialogConstants.CANCEL_ID);
        }

        // Provide a dialog allowing the user to change the
        // preference if several editors are opened
        Object[] bindings = null;
        if (stillOpenElsewhere) {
            bindings = new Object[] { label, OPEN_ELSEWHERE_MESSAGE, OPEN_ELSEWHERE_QUESTION };
        } else {
            bindings = new Object[] { label, "", "" };
        }
        final String message = NLS.bind(SAVE_CHANGES_QUESTION, bindings);
        dialog = new SiriusMessageDialogWithToggle(window.getShell(), "Save", null, message, MessageDialog.QUESTION, buttons, 0, WorkbenchMessages.EditorManager_closeWithoutPromptingOption, false,
                stillOpenElsewhere) {
            protected int getShellStyle() {
                return getSaveDialogStyle(canCancel);
            }
        };
        return dialog;
    }

    private static int getSaveDialogStyle(final boolean canCancel) {
        int style = canCancel ? SWT.CLOSE : SWT.NONE;
        style = style | SWT.TITLE | SWT.BORDER;
        style = style | SWT.APPLICATION_MODAL | Window.getDefaultOrientation();
        return style;
    }

    /**
     * A runnable which should returns a integer data type.
     * 
     * @author mchauvin
     */
    private interface RunnableWithResult extends Runnable {

        /**
         * get the result.
         * 
         * @return the result
         */
        int getResult();

    }

    /**
     * Utility class to dispose a composite on a dispose event
     * 
     * @author Mariot Chauvin (mchauvin)
     */
    private static class DefaultDisposeListener implements DisposeListener {

        private Composite composite;

        /**
         * Constructor
         * 
         * @param composite
         */
        public DefaultDisposeListener(final Composite composite) {
            this.composite = composite;
        }

        public void widgetDisposed(final DisposeEvent e) {
            if (composite != null && !composite.isDisposed()) {
                composite.dispose();
            }
        }

    }

    /**
     * Get the number of lines needed to display the <code>text</code> according
     * to the default font and the specified width.
     * 
     * @param text
     *            The text to display
     * @param width
     *            The width available to display the control
     * @return The number of lines needed.
     */
    public static int getNbLines(final String text, final int width) {
        if (text != null && text.length() > 0) {
            RunnableWithResult runnable = new RunnableWithResult() {
                int nbLines = 1;

                public void run() {
                    Shell shell = new Shell(Display.getCurrent());
                    Label label = new Label(shell, SWT.NONE);
                    nbLines = getNbLines(label, text, width);
                    label.dispose();
                    shell.dispose();
                }

                public int getResult() {
                    return nbLines;
                }
            };
            Display.getDefault().syncExec(runnable);
            return runnable.getResult();
        }
        return 1;
    }

    /**
     * Get the height, in pixel, of text with default System font.
     * 
     * @return The height of a line of text.
     */
    public static int getDefaultFontTextHeight() {
        RunnableWithResult runnable = new RunnableWithResult() {
            int height;

            public void run() {
                Shell shell = new Shell(Display.getCurrent());
                Label label = new Label(shell, SWT.NONE);
                GC gc = new GC(label);
                height = gc.textExtent("Some text").y; //$NON-NLS-1$
                gc.dispose();
                label.dispose();
                shell.dispose();
            }

            public int getResult() {
                return height;
            }
        };
        Display.getDefault().syncExec(runnable);
        return runnable.getResult();
    }

    /**
     * Get the number of lines needed to display the <code>text</code> according
     * to the default font and the specified width.
     * 
     * @param control
     *            The control used to compute the text size
     * @param text
     *            The text to display
     * @param width
     *            The width available to display the control
     * @return The number of lines needed.
     */
    public static int getNbLines(Control control, final String text, final int width) {
        int nbLines = 1;
        if (text != null && text.length() > 0) {
            GC gc = new GC(control);
            int maxExtent = gc.textExtent(text).x;
            nbLines = maxExtent / width;
            if (maxExtent % width > 0) {
                // Some characters need another line to display
                nbLines++;
            }
            gc.dispose();
        }
        return nbLines;
    }

    /**
     * Compute a new text to display, correctly truncated and with "..." at end
     * if needed.
     * 
     * @param textValue
     *            The text to display
     * @param control
     *            The control used to display text. The parent of this control
     *            is used to get the maximum width and height available to
     *            display text.
     * @param heightOfOneLine
     *            The height in pixel of one line
     * @return the new text to display.
     */
    public static String shortenText(String textValue, Control control, int heightOfOneLine) {
        String result = null;
        if (textValue != null && textValue.length() > 0) {
            GC gc = new GC(control);
            try {
                int maxWidth = control.getParent().getBounds().width;
                int maxHeight = control.getParent().getBounds().height;
                int nbLinesAvailable = maxHeight / heightOfOneLine;
                if (nbLinesAvailable == 0) {
                    nbLinesAvailable = 1;
                }
                int currentWidth = gc.textExtent(textValue).x;
                if (currentWidth / nbLinesAvailable > maxWidth) {
                    int length = textValue.length() - 4;
                    while (length > 0 && result == null) {
                        String newText = textValue.substring(0, length) + "..."; //$NON-NLS-1$
                        currentWidth = gc.textExtent(newText).x;
                        // We remove 1 to the maxWidth because there is
                        // sometimes characters that takes less or more place.
                        if ((currentWidth / nbLinesAvailable) < (maxWidth - 1)) {
                            result = newText;
                        }
                        length--;
                    }
                }
            } finally {
                gc.dispose();
            }
        }
        if (result == null) {
            result = textValue;
        }
        return result;
    }
}
