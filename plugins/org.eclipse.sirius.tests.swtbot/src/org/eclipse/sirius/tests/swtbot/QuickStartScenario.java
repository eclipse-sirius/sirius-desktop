/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class QuickStartScenario extends AbstractScenarioTestCase {

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testQuickStart() throws Exception {
        // Open Design perspective
        designerPerspectives.openModelingPerspective();

        // Open Plug-ins view
        final SWTBotView plugInsView = designerViews.openViewByAPI("org.eclipse.pde.ui.PluginsView", "Plug-ins");

        plugInsView.setFocus();

        final SWTBot plugInsViewBot = plugInsView.bot();

        plugInsViewBot.tree();

        plugInsView.close();
    }
}
