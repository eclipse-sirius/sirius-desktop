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
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;

/**
 * Interface to implement to provide context menu actions. This interface is not
 * API.
 * 
 * @author mchauvin
 */
public interface IContextMenuActionProvider {

    /**
     * Get the actions to add to context menu for the selection.
     * 
     * @param selection
     *            the element on which context menu will be displayed
     * @return the actions to add, an empty collection if none.
     */
    Iterable<IAction> getContextMenuActions(ISelection selection);

    /**
     * Get the actions to add to context menu for the selection.
     * 
     * @param selection
     *            the element on which context menu will be displayed
     * @return the actions to add, an empty collection if none.
     */
    Iterable<IContributionItem> getContextualMenuContributionItems(ISelection selection);

}
