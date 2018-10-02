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
package org.eclipse.sirius.tests.swtbot.support.api.editor;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.waits.WaitForEditor;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.ui.IEditorReference;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * SWTBot VSM helper.
 * 
 * @author amartin
 */
public final class SWTBotVSMHelper {

    /**
     * SWTWorkbenchBot
     */
    private static SWTWorkbenchBot bot = new SWTWorkbenchBot();

    private SWTBotVSMHelper() {

    }

    /**
     * get the odesign editor.
     * 
     * @param partialFileName
     *            the name of the edited odesign
     * @return the odesign editor.
     * @throws WidgetNotFoundException
     *             if it fails.
     */
    public static SWTBotVSMEditor getVSMEditorContainingName(final String partialFileName) throws WidgetNotFoundException {
        final Matcher<IEditorReference> withPartName = WidgetMatcherFactory.withPartName(Matchers.containsString(partialFileName));
        return SWTBotVSMHelper.getVSMEditor(withPartName, 0);
    }

    /**
     * Retrieve the Odesign Editor with the name.
     * 
     * @param fileName
     *            the file name of the odesign edited.
     * @param index
     *            the index.
     * @return the new editor
     * @throws WidgetNotFoundException
     *             if it fails.
     */
    public static SWTBotVSMEditor getVSMEditor(final String fileName, final int index) throws WidgetNotFoundException {
        final Matcher<IEditorReference> withPartName = WidgetMatcherFactory.withPartName(fileName);
        return SWTBotVSMHelper.getVSMEditor(withPartName, index);
    }

    private static SWTBotVSMEditor getVSMEditor(final Matcher<IEditorReference> withPartName, final int index) throws WidgetNotFoundException {
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotVSMHelper.bot.waitUntilWidgetAppears(waitForEditor);
        return new SWTBotVSMEditor(waitForEditor.get(index), SWTBotVSMHelper.bot);
    }

}
