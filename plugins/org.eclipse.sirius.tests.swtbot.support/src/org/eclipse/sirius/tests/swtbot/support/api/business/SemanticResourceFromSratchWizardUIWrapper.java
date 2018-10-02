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

import java.util.Collections;
import java.util.Set;

import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ViewpointSelectionDialog;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

/**
 * UI wrapper for the WizardPage of creation from scratch of a semantic resource
 * to add to the current session.
 * 
 * @author edugueperoux
 */
public class SemanticResourceFromSratchWizardUIWrapper {

    private final SWTBotShell newPage;

    private final ViewpointSelectionDialog selectionDialog;

    /**
     * Default constructor.
     * 
     * @param uiLocalSession
     *            the current {@link UILocalSession}
     * 
     * @param newPage
     *            UI wrapper to a session.
     */
    public SemanticResourceFromSratchWizardUIWrapper(UILocalSession uiLocalSession, SWTBotShell newPage) {
        this.newPage = newPage;
        this.selectionDialog = new ViewpointSelectionDialog(uiLocalSession.bot);
    }

    /**
     * Set in the "Metamodel URI" text field the nsURI of the metamodel for
     * which to create a model instance.
     * 
     * @param nsURI
     *            the nsURI of the EPackage on which we create the model
     *            instance.
     * 
     * @return this
     */
    public SemanticResourceFromSratchWizardUIWrapper selectDirecltyMetamodelURI(String nsURI) {
        SWTBot bot = newPage.bot();
        SWTBotCombo comboBox = bot.comboBox();
        comboBox.setFocus();
        comboBox.setSelection(nsURI);
        return this;
    }

    /**
     * Set in the "Metamodel URI" text field the nsURI of the metamodel for
     * which to create a model instance.
     * 
     * @param rootModelElementName
     *            the name of the metaclass to use as type for the root element
     *            of the semantic resource to create.
     * 
     * @return this
     */
    public SemanticResourceFromSratchWizardUIWrapper selectRootModelElementToUse(String rootModelElementName) {
        SWTBot bot = newPage.bot();
        SWTBotCombo swtBotCombo = bot.comboBox(1);
        swtBotCombo.setFocus();
        swtBotCombo.setText(rootModelElementName);
        return this;
    }

    /**
     * Set in the "Metamodel URI" text field the nsURI of the metamodel for
     * which to create a model instance.
     * 
     * @param semanticResourceFileName
     *            the semantic resource filename to create
     * 
     * @return this
     */
    public SemanticResourceFromSratchWizardUIWrapper setSemanticResourceFileName(String semanticResourceFileName) {
        SWTBot bot = newPage.bot();
        SWTBotText textBot = bot.text(0);
        textBot.setFocus();
        textBot.setText(semanticResourceFileName);
        return this;
    }

    /**
     * Click on the Finish button.
     * 
     * @param viewpointsToSelect
     *            set of viewpoint to select
     */
    public void finishWithViewpointSelection(Set<String> viewpointsToSelect) {
        SWTBot bot = newPage.bot();
        SWTBotButton button = bot.button("Finish");
        bot.waitUntil(new ItemEnabledCondition(button));
        button.click();

        selectionDialog.selectViewpoint(viewpointsToSelect, Collections.<String> emptySet());
        bot.waitUntil(Conditions.shellCloses(newPage));
    }

}
