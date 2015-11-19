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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.ui.tools.api.editor.tabbar.ITabbarContributor;

/**
 * 
 * A ITabbarContributorProvider allows providing {@link ITabbarContributor}.
 * 
 * @author Florian Barbin
 *
 */
public interface ITabbarContributorProvider {

    /**
     * Returns whether tabbar contributors are available.
     * 
     * @return true if at least one contributor has been provided, otherwise
     *         false.
     */
    boolean hasContributor();

    /**
     * Provides the first {@link ITabbarContributor} accepting an empty
     * selection.
     * 
     * @return the first {@link ITabbarContributor} if found, otherwise null.
     */
    ITabbarContributor getContributor();

    /**
     * Provides the first {@link ITabbarContributor} accepting the given
     * selection.
     * 
     * @param selection
     *            the current selection.
     * @return the first {@link ITabbarContributor} if found, otherwise null.
     */
    ITabbarContributor getContributor(ISelection selection);

}
