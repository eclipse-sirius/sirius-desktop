/**
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.business;

import java.text.MessageFormat;

import org.eclipse.sirius.tests.swtbot.support.api.business.UINewRepresentationBuilderFlow.EndFlow;
import org.eclipse.sirius.tests.swtbot.support.api.business.UINewRepresentationBuilderFlow.ItemChoice;
import org.eclipse.sirius.tests.swtbot.support.api.business.UINewRepresentationBuilderFlow.NameChoice;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.support.utils.business.UIRepresentationUtils;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Builder tool to create "New representations".
 * 
 * @author dlecan
 * @param <R>
 *            Type of representation.
 */
public class UINewRepresentationBuilder<R extends AbstractUIRepresentation<?>> implements ItemChoice<R>, NameChoice<R>, EndFlow<R> {

    private final String representationNameToClick;

    private final String representationDescriptionLabel;

    private String newRepresentationName;

    private final SWTWorkbenchBot mainBot;

    private SWTBotShell shell;

    private SWTBot shellBot;

    private final Class<R> representationType;

    /**
     * Constructor.
     * 
     * @param clickedRepresentationName
     *            Representation name clicked in contextual menu
     * @param representationDescriptionLabel
     *            The label of the representation description corresponding to <code>clickedRepresentationName</code>.
     * @param representationType
     *            Java type of representation.
     */
    public UINewRepresentationBuilder(final String clickedRepresentationName, final String representationDescriptionLabel, final Class<R> representationType) {
        this.mainBot = new SWTWorkbenchBot();
        this.representationNameToClick = clickedRepresentationName;
        this.representationType = representationType;
        this.representationDescriptionLabel = representationDescriptionLabel;
    }

    /**
     * On method.
     * 
     * @param treeItem
     *            Tree item on which you want to create new representation.
     * @return Current {@link UINewRepresentationBuilder}
     */
    @Override
    public NameChoice<R> on(final SWTBotTreeItem treeItem) {
        treeItem.select();
        SWTBotUtils.clickContextMenu(treeItem, representationNameToClick);

        shell = mainBot.shell(MessageFormat.format(Messages.createRepresentationInputDialog_Title, representationDescriptionLabel));
        shell.activate();

        shellBot = shell.bot();

        return this;
    }

    /**
     * withName method.
     * 
     * @param name
     *            name of the new representation.
     * @return Current {@link UINewRepresentationBuilder}
     */
    @Override
    public EndFlow<R> withName(final String name) {
        newRepresentationName = name;
        final SWTBotText text = shellBot.text();
        text.setText(newRepresentationName);
        return this;
    }

    /**
     * New representation with default name.
     * 
     * @return Current {@link UINewRepresentationBuilder}
     */
    @Override
    public EndFlow<R> withDefaultName() {
        final SWTBotText text = shellBot.text();
        return withName(text.getText());
    }

    /**
     * Finish creation with click on "ok".
     * 
     * @return Representation.
     */
    @Override
    public R ok() {
        return ok(false);
    }

    /**
     * Finish creation with click on "ok".
     * 
     * @param disableSnapToGridOnThisEditor
     *            true if the snapToGrid must be disable for this editor, false otherwise
     * @return Representation.
     */
    @Override
    public R ok(boolean disableSnapToGridOnThisEditor) {
        final SWTBotButton ok = shellBot.button("OK");
        shellBot.waitUntilWidgetAppears(new ItemEnabledCondition(ok));
        ok.click();
        SWTBotUtils.waitProgressMonitorClose("Progress Information");
        finish();

        // Get the corresponding SWtBotEditor
        SWTBotEditor swtBotEditor = SWTBotSiriusHelper.getSiriusEditor(newRepresentationName);
        if (disableSnapToGridOnThisEditor) {
            swtBotEditor = SWTBotSiriusHelper.getSiriusDiagramEditor(newRepresentationName);
        } else {
            swtBotEditor = SWTBotSiriusHelper.getSiriusEditor(newRepresentationName);
        }
        if (swtBotEditor != null && disableSnapToGridOnThisEditor) {
            ((SWTBotSiriusDiagramEditor) swtBotEditor).setSnapToGrid(false);
        }
        return UIRepresentationUtils.buildRepresentation(null, getRealNewRepresentationName(), getRepresentationType());
    }

    /**
     * Finish creation with click on "cancel".
     */
    @Override
    public void cancel() {
        final SWTBotButton cancel = shellBot.button("Cancel");
        shellBot.waitUntilWidgetAppears(new ItemEnabledCondition(cancel));
        cancel.click();
        finish();
    }

    private void finish() {
        mainBot.waitUntil(Conditions.shellCloses(shell));
        mainBot.sleep(500);
    }

    private String getRealNewRepresentationName() {
        String result;
        if (newRepresentationName == null) {
            result = representationNameToClick;
        } else {
            result = newRepresentationName;
        }
        return result;
    }

    private Class<R> getRepresentationType() {
        return representationType;
    }
}
