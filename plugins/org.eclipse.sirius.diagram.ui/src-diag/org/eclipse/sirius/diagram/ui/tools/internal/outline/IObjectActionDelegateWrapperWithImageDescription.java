/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.outline;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.util.IObjectActionDelegateWrapper;
import org.eclipse.ui.IObjectActionDelegate;

/**
 * 
 * {@link IObjectActionDelegateWrapper} created to refactor code and have less code lines in DDiagramEditorImpl.
 * 
 * @author <a href="mailto:steve.monnier@obeosoft.ca">Steve Monnier</a>
 *
 */
public class IObjectActionDelegateWrapperWithImageDescription extends IObjectActionDelegateWrapper {

    /**
     * Constructor.
     * 
     * 
     * @param action
     *            the delegated action to wrap
     * 
     * @param text
     *            the string used as the text for the action, or <code>null</code> if there is no text
     */
    public IObjectActionDelegateWrapperWithImageDescription(IObjectActionDelegate action, String text) {
        super(action, text);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return ((IAction) getWrappedAction()).getImageDescriptor();
    }

}
