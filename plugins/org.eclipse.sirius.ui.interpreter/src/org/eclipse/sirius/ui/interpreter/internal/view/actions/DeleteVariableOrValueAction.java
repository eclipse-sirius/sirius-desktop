/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.ui.interpreter.internal.IInterpreterConstants;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterImages;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.sirius.ui.interpreter.internal.view.Variable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * This action can be used in order to delete a variable (or one of its values) from the evaluation context.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class DeleteVariableOrValueAction extends Action {
    /** Keeps a reference to the variable viewer. */
    private final TreeViewer variableViewer;

    /**
     * Instantiates the "delete variable" action given the variable viewer.
     * 
     * @param viewer
     *            The variable viewer.
     */
    public DeleteVariableOrValueAction(TreeViewer viewer) {
        super(InterpreterMessages.getString("interpreter.action.deletevariable.name")); //$NON-NLS-1$
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.DELETE_ACTION_ICON));
        setDisabledImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.DELETE_ACTION_DISABLED_ICON));
        this.variableViewer = viewer;
    }

    /**
     * Returns the Tree this action will affect.
     * 
     * @return The Tree this action will affect.
     */
    public Tree getTree() {
        if (variableViewer == null) {
            return null;
        }
        return variableViewer.getTree();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        if (getTree() != null && !getTree().isDisposed()) {
            TreeItem[] selectedtems = getTree().getSelection();
            if (selectedtems != null && selectedtems.length > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        if (getTree() == null || getTree().isDisposed()) {
            return;
        }

        Object input = variableViewer.getInput();

        TreeItem[] selectedtems = getTree().getSelection();
        if (selectedtems == null || selectedtems.length == 0) {
            return;
        }

        for (int i = 0; i < selectedtems.length; i++) {
            TreeItem item = selectedtems[i];
            if (item.getData() instanceof Variable) {
                ((List<Variable>) input).remove(item.getData());
            } else if (item.getParentItem() != null && item.getParentItem().getData() instanceof Variable) {
                Variable variable = (Variable) item.getParentItem().getData();
                Object variableValue = variable.getValue();
                if (item.getData().equals(variableValue)) {
                    ((List<Variable>) input).remove(variable);
                } else {
                    List<Object> value = (List<Object>) variableValue;
                    value.remove(item.getData());
                    if (value.size() == 1) {
                        variable.setValue(value.get(0));
                    }
                }
            }
        }

        variableViewer.refresh();
    }

    /**
     * Returns the currently selected Variable(s).
     * 
     * @return The currently selected Variable(s).
     */
    protected List<Variable> getCurrentVariables() {
        List<Variable> result = new ArrayList<Variable>();
        if (getTree() != null && !getTree().isDisposed()) {
            TreeItem[] selectedtems = getTree().getSelection();
            if (selectedtems != null && selectedtems.length > 0) {
                for (int i = 0; i < selectedtems.length; i++) {
                    TreeItem item = selectedtems[i];
                    if (item.getData() instanceof Variable) {
                        result.add((Variable) item.getData());
                    }
                }
            }
        }
        return result;
    }
}
