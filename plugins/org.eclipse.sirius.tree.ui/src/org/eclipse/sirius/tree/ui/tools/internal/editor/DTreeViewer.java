/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tree.ui.tools.internal.editor.listeners.DTreeItemExpansionChecker;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeViewer;

/**
 * Sirius {@link AbstractDTreeViewer} for DTree model.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTreeViewer extends AbstractDTreeViewer {

    private DTreeItemExpansionChecker dTreeItemExpansionChecker;

    /**
     * Creates a tree viewer on the given tree control. The viewer has no input,
     * no content provider, a default label provider, no sorter, and no filters.
     * 
     * @param parent
     *            the parent control
     * @param style
     *            the SWT style bits used to create the tree.
     * @param session
     *            the {@link Session} used by the underlying model
     */
    public DTreeViewer(final Composite parent, final int style, final Session session) {
        super(parent, style);
        this.dTreeItemExpansionChecker.setPermissionAuthority(session.getModelAccessor().getPermissionAuthority());
    }

    /**
     * Overridden to add the {@link DTreeItemExpansionChecker}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void hookControl(Control control) {
        dTreeItemExpansionChecker = new DTreeItemExpansionChecker(control);
        super.hookControl(control);
    }

    /**
     * Overridden to fix a behavior difference.
     * 
     * {@inheritDoc}
     */
    @Override
    public void expandToLevel(Object elementOrTreePath, int level) {
        int newLevel = level;
        if (level == ALL_LEVELS) {
            /*
             * here we're making sure we'll have the same behavior as in Linux
             * even on other platforms. We don't want the user to ends up in an
             * infinite loop because he stroke the "*" key on windows and this
             * tree is infinite.
             */
            newLevel = getCurrentExpandedLevel() + 1;
        }
        super.expandToLevel(elementOrTreePath, newLevel);
    }

    private int getCurrentExpandedLevel() {
        int maxLevel = 0;
        for (TreeItem child : getTree().getItems()) {
            int childValue = doBrowse(child, 1);
            if (childValue > maxLevel) {
                maxLevel = childValue;
            }
        }
        return maxLevel;
    };

    private int doBrowse(TreeItem cur, int currentLevel) {
        int maxLevel = currentLevel;
        for (TreeItem child : cur.getItems()) {
            int childValue = doBrowse(child, currentLevel + 1);
            if (childValue > maxLevel) {
                maxLevel = childValue;
            }
        }
        return maxLevel;
    }

    /**
     * Overridden to remove the {@link DTreeItemExpansionChecker}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void handleDispose(DisposeEvent event) {
        dTreeItemExpansionChecker.dispose();
        super.handleDispose(event);
    }

}
