/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.statusline;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

public class MouseLocationContribution extends WorkbenchWindowControlContribution {

    /**
     * Control manager to get last created control.
     * 
     * @author dlecan
     */
    public static class MouseLocationControlManager {

        private MouseLocationControl mouseLocationControl;

        /**
         * Returns the mouseLocationControl.
         * 
         * @return The mouseLocationControl.
         */
        public MouseLocationControl getMouseLocationControl() {
            return mouseLocationControl;
        }

        /**
         * Sets the value of mouseLocationControl to mouseLocationControl.
         * 
         * @param mouseLocationControl
         *            The mouseLocationControl to set.
         */
        public void setMouseLocationControl(final MouseLocationControl mouseLocationControl) {
            this.mouseLocationControl = mouseLocationControl;
        }

    }

    /**
     * Singleton for control manager.
     */
    public static final MouseLocationControlManager CONTROL_MANAGER = new MouseLocationControlManager();

    @Override
    protected Control createControl(final Composite parent) {
        final MouseLocationControl mouseLocationControl = new MouseLocationControl(parent, getOrientation());
        CONTROL_MANAGER.setMouseLocationControl(mouseLocationControl);
        return mouseLocationControl;
    }

}
