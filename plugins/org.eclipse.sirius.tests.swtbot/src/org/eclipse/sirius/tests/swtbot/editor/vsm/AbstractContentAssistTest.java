/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SiriusSWTBotTable;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.hamcrest.Matcher;

/**
 * Abstract class to handle completion.
 * 
 * @author mporhel
 */
public abstract class AbstractContentAssistTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Add the content assist proposal on index indexOfContentAssistProposal to SWTBotText textToActivateContentAssit.
     * 
     * @param text
     *            text of a property section
     * @param cursorPosition
     *            the cursor position to set before calling completion.
     * @param indexOfContentAssistProposal
     *            the index of content assist proposal to add.
     * @return the selected proposal.
     */
    protected String selectContentAssistProposal(final SWTBotText text, final int cursorPosition, final int indexOfContentAssistProposal) {

        setCursorPosition(text, cursorPosition);

        return UIThreadRunnable.syncExec(new Result<String>() {
            @Override
            public String run() {
                SWTBotShell contentAssistShell = openContentAssist(text);

                SWTBot swtBot = contentAssistShell.bot();
                Matcher matcher = allOf(widgetOfType(Table.class));
                SiriusSWTBotTable contentAssistTable = new SiriusSWTBotTable((Table) swtBot.widget(matcher, 0), matcher);

                String contentAssistProposalText = contentAssistTable.getTableItem(indexOfContentAssistProposal).getText();

                // Simulate user selection by double click
                contentAssistTable.doubleClick(indexOfContentAssistProposal, 0);

                return contentAssistProposalText;
            }
        });
    }

    /**
     * Get the content assist proposal on cursor position.
     * 
     * @param text
     *            text of a property section
     * @param cursorPosition
     *            the cursor position to set before calling completion.
     * @return the displayed content proposals.
     */
    protected Collection<String> getContentAssistProposal(final SWTBotText text, final int cursorPosition) {

        setCursorPosition(text, cursorPosition);

        return UIThreadRunnable.syncExec(new Result<Collection<String>>() {
            @SuppressWarnings("finally")
            @Override
            public Collection<String> run() {

                SWTBotShell contentAssistShell = openContentAssist(text);

                // Get the displayed proposals.
                List<String> proposals = new ArrayList<>();
                try {
                    SWTBotTable contentAssistTable = contentAssistShell.bot().table();
                    for (TableItem item : contentAssistTable.widget.getItems()) {
                        proposals.add(item.getText());
                    }

                    closeContentAssist(text);
                } catch (WidgetNotFoundException e) {
                    // Display this exception in the console otherwise it is not visible. Only a
                    // "java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0" is visible for method
                    // "AbstractContentAssistTest.getContentAssistProposal(AbstractContentAssistTest.java:97)"
                    System.out.println("The table of the content assist shell has not been found.");
                } finally {
                    return proposals;
                }
            }
        });
    }

    /**
     * Must not be run in UI Thread.
     */
    private void setCursorPosition(final SWTBotText text, final int cursorPosition) {
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                text.widget.setSelection(cursorPosition);
            }
        });
    }

    /**
     * Must be run in UI Thread.
     */
    private SWTBotShell openContentAssist(final SWTBotText text) {
        final int shellsNumberBeforeContentAssist = bot.shells().length;

        IBindingService bindingService = PlatformUI.getWorkbench().getService(IBindingService.class);
        String binding = bindingService.getActiveBindingsFor("org.eclipse.ui.edit.text.contentAssist.proposals")[0].format();
        KeyStroke keyStroke = null;
        try {
            keyStroke = KeyStroke.getInstance(binding.toLowerCase());
        } catch (ParseException e) {
            fail(ParseException.class + e.getMessage());
        }

        Event event = new Event();
        event.stateMask = keyStroke.getModifierKeys();
        event.keyCode = keyStroke.getNaturalKey();

        text.widget.notifyListeners(SWT.KeyDown, event);
        text.widget.notifyListeners(SWT.KeyUp, event);

        // Wait the display of the content proposal shell.
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return bot.shells().length == shellsNumberBeforeContentAssist + 1;
            }

            @Override
            public void init(SWTBot bot) {
                // Nothing
            }

            @Override
            public String getFailureMessage() {
                return "There is not the expected number of shells";
            }
        });

        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                int openShells = 0;
                int visibleShells = 0;
                for (SWTBotShell shell : bot.shells()) {
                    if (shell.isOpen()) {
                        openShells++;
                    }
                    if (shell.isVisible()) {
                        visibleShells++;
                    }
                }
                return openShells >= 2 && visibleShells >= 2;
            }

            @Override
            public void init(SWTBot bot) {
                // Nothing
            }

            @Override
            public String getFailureMessage() {
                return "There is not the expected number of shells";
            }
        });

        // Look for the content proposal shell.
        // It is not always at the end of the array.
        // Look for the first visible and open shell which is not the
        // eclipse one (with Sirius perspective).
        SWTBotShell contentAssistShell = null;
        for (int i = shellsNumberBeforeContentAssist; i >= 0; i--) {
            SWTBotShell swtBotShell = bot.shells()[i];
            boolean open = swtBotShell.isOpen();
            boolean visible = swtBotShell.isVisible();
            if (open && visible && !swtBotShell.getText().contains("Sirius")) {
                contentAssistShell = swtBotShell;
                break;
            }
        }
        assertNotNull("Content proposal shell not found.", contentAssistShell);
        contentAssistShell.setFocus();
        return contentAssistShell;
    }

    /**
     * Must be run in UI Thread.
     */
    private void closeContentAssist(final SWTBotText text) {
        // Quit the completion.
        Event event = new Event();
        event.keyCode = SWT.ESC;

        text.widget.notifyListeners(SWT.KeyDown, event);
        text.widget.notifyListeners(SWT.KeyUp, event);
    }
}
