/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.editor.tabbar;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;

/**
 * A tabbar contributor aims to provide a specific list of
 * {@link IContributionItem} that will be displayed in the tabbar.
 * 
 * @author Florian Barbin
 *
 */
public interface ITabbarContributor {

    /**
     * Provides the {@link IContributionItem} if there is no specific selection
     * (at the diagram opening for instance).
     * 
     * @param part
     *            the current workbench part.
     * @param toolBarManager
     *            the toolbar manager
     * @return the {@link IContributionItem} list.
     */
    List<IContributionItem> getContributionItems(IDiagramWorkbenchPart part, ToolBarManager toolBarManager);

    /**
     * Provides the {@link IContributionItem} according to the current
     * selection. Keep in mind that this method will be called each time the
     * selection changes. To avoid adding too much instances of a same kind of
     * contribution item, you should return the same instance list for each kind
     * of selection.
     * 
     * @param selection
     *            the current selection.
     * @param part
     *            the current workbench part.
     * @param toolBarManager
     *            the toolbar manager
     * @return the {@link IContributionItem} list.
     */
    List<IContributionItem> getContributionItems(ISelection selection, IDiagramWorkbenchPart part, ToolBarManager toolBarManager);

    /**
     * Returns whether this TabbarContributor provides a list of
     * {@link IContributionItem} for this kind of selection.
     * 
     * @param selection
     *            the current selection or null if no selection.
     * @return true if it provides a {@link IContributionItem} list, otherwise
     *         false.
     */
    boolean accept(ISelection selection);
}
