/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.collect.Lists;

/**
 * Tests Pin/Unpin, Show/Hide and Copy/Paste Layout actions are disabled on
 * Sequence messages described by an EdgeMappingImport.
 * 
 * Test VP-3754.
 * 
 * 
 * @author mporhel
 */
public class ActionDisabledOnExtendedMessagesTest extends AbstractActionDisabledOnSequenceDiagramTest {

    private static final String PATH = DATA_UNIT_DIR + "message_extension/";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getTypesSemanticModel() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();

        // Copy the extension VSM.
        copyFileToTestProject(Activator.PLUGIN_ID, getPath(), "message-extension.odesign");
    }

    /**
     * {@inheritDoc}
     * 
     * @return an extended message.
     */
    protected Collection<SWTBotGefEditPart> getEditPartsToCheckDisabledActionsOn() {
        Collection<SWTBotGefEditPart> partsToTest = new ArrayList<>();
        // Retrieve an extended message.
        partsToTest.add(editor.getEditPart("m1_extended").parent());
        return partsToTest;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<List<String>> getElementPathsToCheckNoEffectInWizard() {
        Collection<List<String>> pathsToTest = new ArrayList<>();
        // Retrieve the same extended message than the other method. The label
        // is different because the label provider in the wizard is the semantic
        // label provider.
        pathsToTest.add(Lists.newArrayList("Call Message m1"));
        return pathsToTest;
    }
}
