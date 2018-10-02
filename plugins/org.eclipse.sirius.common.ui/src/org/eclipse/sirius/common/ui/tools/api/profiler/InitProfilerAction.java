/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.ui.tools.api.profiler;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.common.tools.DslCommonPlugin;

/**
 * This action reinits the profiler.
 * 
 * @author ymortier
 */
public class InitProfilerAction extends Action {

    final TreeViewer viewer;

    /**
     * Default constructor.
     * 
     * @param viewer
     *            The treeViewer representing the profiler
     */
    public InitProfilerAction(final TreeViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        DslCommonPlugin.PROFILER.init();
        viewer.refresh();
    }

}
