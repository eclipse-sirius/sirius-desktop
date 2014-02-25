/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.pinning.PinElementsEclipseAction;

/**
 * A tabbar pin action which reference an opposite action.
 * 
 * @author mchauvin
 */
public class TabbarPinElementsEclipseAction extends PinElementsEclipseAction {

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
