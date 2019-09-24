/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.tests.swtbot.support.utils;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.finders.ControlFinder;
import org.eclipse.ui.PlatformUI;

/**
 * A control finder falling back to current shell if no shell with focus is found.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class ControlFinderWithDefaultShell extends ControlFinder {
    @Override
    public Shell activeShell() {
        Shell activeShell = super.activeShell();
        if (activeShell == null) {
            return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        }
        return activeShell;
    }
}
