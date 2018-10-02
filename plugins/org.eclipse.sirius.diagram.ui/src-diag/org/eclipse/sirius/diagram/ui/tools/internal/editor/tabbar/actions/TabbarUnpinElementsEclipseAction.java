/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.pinning.UnpinElementsEclipseAction;

/**
 * A tabbar pin action which reference an opposite action.
 * 
 * @author mchauvin
 */
public class TabbarUnpinElementsEclipseAction extends UnpinElementsEclipseAction {

    private IAction otherPinAction;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.actions.pinning.AbstractPinUnpinElementsEclipseAction#run()
     */
    @Override
    public void run() {
        super.run();
        this.setEnabled(isEnabled());
        otherPinAction.setEnabled(otherPinAction.isEnabled());
    }

    /**
     * Set the opposite pin action.
     * 
     * @param action
     *            the opposite pin action
     */
    public void setOppositePinAction(IAction action) {
        otherPinAction = action;
    }
}
