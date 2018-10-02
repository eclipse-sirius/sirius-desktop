/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.business;

import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * SWTBot bot for the wizard to add a local semantic resource to the current
 * session.
 * 
 * @author edugueperoux
 */
public class UIAddLocalSemanticResourceWizardUIWrapper {

    /** The wizard. */
    protected SWTBotShell localSemanitcResourceWizardUIWrapper;

    private final UILocalSession uiLocalSession;

    /**
     * Default constructor.
     * 
     * @param uiLocalSession
     *            the UI wrapper of the session to use
     */
    public UIAddLocalSemanticResourceWizardUIWrapper(UILocalSession uiLocalSession) {
        this.uiLocalSession = uiLocalSession;
        uiLocalSession.getRootSessionTreeItem().contextMenu("Add Model").click();
        localSemanitcResourceWizardUIWrapper = uiLocalSession.bot.activeShell();
    }

    /**
     * Get a UI wrapper for the WizardPage of creation from scratch of a
     * semantic resource to add to the current session.
     * 
     * @return SemanticResourceFromSratchWizardUIWrapper UI wrapper for the
     *         WizardPage of creation from scratch of a semantic resource to add
     *         to the current session
     */
    public SemanticResourceFromSratchWizardUIWrapper createNewSemanticResourceFromSratchWizardUIWrapper() {
        SWTBot bot = localSemanitcResourceWizardUIWrapper.bot();
        SWTBotRadio radio = bot.radio(0);
        new WrappedSWTBotRadio(radio).click();
        SWTBotButton button = bot.button("Next >");
        bot.waitUntil(new ItemEnabledCondition(button));
        button.click();
        return new SemanticResourceFromSratchWizardUIWrapper(uiLocalSession, bot.activeShell());
    }

    /**
     * Get the UI wrapper for the WizardPage of selection of a existing semantic
     * resource to the current session.
     * 
     * @return a ExistingSemanticResourceWizardUIWrapper to use for add of
     *         existing semantic resource to the current session
     */
    public ExistingSemanticResourceWizardUIWrapper addExistingSemanticResourceWizardUIWrapper() {
        SWTBot bot = localSemanitcResourceWizardUIWrapper.bot();
        bot.buttonWithLabel("Add existing resource").setFocus();
        bot.buttonWithLabel("Finish").click();
        return new ExistingSemanticResourceWizardUIWrapper(bot.activeShell());
    }

}
