/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.ui.interpreter.internal.view.Variable;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.ClearVariableViewerAction;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.DeleteVariableOrValueAction;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.NewVariableWizardAction;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.RenameVariableAction;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * This class will be used in order to populate the right-click menu of the Variable viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class VariableMenuListener implements IMenuListener {
    /** The viewer on which this menu listener operates. */
    private TreeViewer variableViewer;

    /**
     * Creates this menu listener given the viewer on which it operates.
     * 
     * @param variableViewer
     *            The viewer on which this menu listener operates.
     */
    public VariableMenuListener(TreeViewer variableViewer) {
        this.variableViewer = variableViewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    public void menuAboutToShow(IMenuManager manager) {
        final Variable variable = getCurrentVariable();
        manager.add(new NewVariableWizardAction(variableViewer, variable));
        manager.add(new ClearVariableViewerAction(variableViewer));
        manager.add(new Separator());
        manager.add(new DeleteVariableOrValueAction(variableViewer));
        manager.add(new RenameVariableAction(variableViewer));
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    /**
     * Returns the first of the currently selected Variable(s).
     * 
     * @return The first of the currently selected Variable(s)
     */
    private Variable getCurrentVariable() {
        if (variableViewer == null || variableViewer.getTree() == null || variableViewer.getTree().isDisposed()) {
            return null;
        }
        Tree tree = variableViewer.getTree();

        TreeItem[] selectedItems = tree.getSelection();
        Variable selectedVariable = null;
        if (selectedItems != null && selectedItems.length > 0) {
            for (int i = 0; i < selectedItems.length && selectedVariable == null; i++) {
                TreeItem item = selectedItems[i];
                if (item.getData() instanceof Variable) {
                    selectedVariable = (Variable) item.getData();
                }
            }
            for (int i = 0; i < selectedItems.length && selectedVariable == null; i++) {
                TreeItem item = selectedItems[i].getParentItem();
                while (item != null && selectedVariable == null) {
                    if (item.getData() instanceof Variable) {
                        selectedVariable = (Variable) item.getData();
                    }
                    item = item.getParentItem();
                }
            }
        }
        return selectedVariable;
    }
}
