/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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

import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.ui.PartInitException;

/**
 * Tests for the VSM editor's properties view.
 * 
 * @author pcdavid
 */
public class VSMEditorPropertiesTest extends AbstractSiriusSwtBotGefTestCase {
    /**
     * The identifier of the "Properties" view.
     */
    private static final String VIEW_ID = "org.eclipse.ui.views.PropertySheet";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(SiriusTestsPlugin.PLUGIN_ID, "data/unit/vsm/", "valideVSM.odesign");
    }

    /**
     * Regression test to check that closing and then re-opening the VSM
     * editor's property sheet does not cause any issue (it used to crash SWT by
     * attempting to reuse an already disposed widget).
     * 
     * @bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=449007
     * @throws PartInitException
     *             if the VSM editor could not be opened.
     */
    public void testCloseAndReeopenVSMEditorProperties() throws PartInitException {
        UIThreadRunnable.asyncExec(() -> EclipseUIUtil.showView(VSMEditorPropertiesTest.VIEW_ID));
        SWTBotUtils.waitAllUiEvents();

        SWTBotVSMEditor vsmEditor = openViewpointSpecificationModel("valideVSM.odesign");
        SWTBotUtils.waitAllUiEvents();

        UIThreadRunnable.asyncExec(() -> EclipseUIUtil.hideView(VSMEditorPropertiesTest.VIEW_ID));
        SWTBotUtils.waitAllUiEvents();

        UIThreadRunnable.asyncExec(() -> EclipseUIUtil.showView(VSMEditorPropertiesTest.VIEW_ID));
        SWTBotUtils.waitAllUiEvents();

        vsmEditor.setFocus();
        SWTBotUtils.waitAllUiEvents();
    }
}
