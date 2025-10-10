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
package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.ui.interpreter.internal.view.Variable;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.NewVariableAction;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

/**
 * This listener will be registered against the "Variables" TreeViewer in order to allow drop operations on that viewer
 * from workspace selections.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class VariableDropListener extends DropTargetAdapter {
    /** Keeps a reference towards the viewer against which this listener is registered. */
    private TreeViewer viewer;

    /**
     * Creates a new drop listener for the given <code>viewer</code>.
     * 
     * @param viewer
     *            The viewer against which this drop listener is registered.
     */
    public VariableDropListener(TreeViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.dnd.DropTargetAdapter#dragEnter(org.eclipse.swt.dnd.DropTargetEvent)
     */
    @Override
    public void dragEnter(DropTargetEvent event) {
        if (!LocalSelectionTransfer.getTransfer().isSupportedType(event.currentDataType)) {
            return;
        }

        event.detail = DND.DROP_COPY;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.dnd.DropTargetAdapter#drop(org.eclipse.swt.dnd.DropTargetEvent)
     */
    @Override
    public void drop(DropTargetEvent event) {
        Collection<?> selection = getSelection(event);
        if (!selection.isEmpty()) {
            Widget targetItem = event.item;

            if (targetItem instanceof TreeItem) {
                Object targetVariable = targetItem.getData();
                while (targetItem != null && !(targetVariable instanceof Variable)) {
                    targetItem = ((TreeItem) targetItem).getParentItem();
                    if (targetItem != null) {
                        targetVariable = targetItem.getData();
                    }
                }
                if (targetVariable instanceof Variable) {
                    addToVariable((Variable) targetVariable, selection);
                } else {
                    createNewVariable(selection);
                }
            } else {
                createNewVariable(selection);
            }
        }
    }

    /**
     * Adds the given selection to the values of <code>targetVariable</code>.
     * 
     * @param targetVariable
     *            Variable into which we are to add values.
     * @param selection
     *            The values that are to be added to <code>targetVariable</code>.
     */
    @SuppressWarnings("unchecked")
    private void addToVariable(Variable targetVariable, Collection<?> selection) {
        Collection<Object> newValue;

        final Object currentValue = targetVariable.getValue();
        if (currentValue instanceof Collection<?>) {
            newValue = (Collection<Object>) currentValue;
        } else {
            newValue = new ArrayList<Object>();
            if (currentValue != null) {
                newValue.add(currentValue);
            }
        }

        newValue.addAll(selection);

        // if there is but a single value, simply use it
        if (newValue.size() == 1) {
            targetVariable.setValue(newValue.iterator().next());
        } else {
            targetVariable.setValue(newValue);
        }

        viewer.refresh();
    }

    /**
     * Creates a new variable to hold the given selection.
     * 
     * @param selection
     *            The selection for which to create a Variable.
     */
    private void createNewVariable(Collection<?> selection) {
        final Object variableValue;
        if (selection.size() == 1) {
            variableValue = selection.iterator().next();
        } else {
            variableValue = selection;
        }

        NewVariableAction action = new NewVariableAction(viewer, variableValue);
        action.run();
    }

    /**
     * Tries and extract the selected objects from the drop event.
     * 
     * @param event
     *            The drop event from which to extract a selection.
     * @return The list containing the event's selection, an empty list if none.
     */
    private Collection<?> getSelection(DropTargetEvent event) {
        if (event.data instanceof IStructuredSelection) {
            @SuppressWarnings("unchecked")
            List<Object> objectList = ((IStructuredSelection) event.data).toList();

            List<Object> variablesValues = new ArrayList<Object>();
            for (Object object : objectList) {
                EObject variableEObject = null;
                if (object instanceof EObject) {
                    variableEObject = (EObject) object;
                } else if (object instanceof IAdaptable) {
                    variableEObject = (EObject) ((IAdaptable) object).getAdapter(EObject.class);
                } else {
                    variableEObject = (EObject) Platform.getAdapterManager().getAdapter(object, EObject.class);
                }

                if (variableEObject != null) {
                    variablesValues.add(variableEObject);
                } else {
                    variablesValues.add(object);
                }
            }
            return variablesValues;
        }
        return Collections.emptyList();
    }
}
