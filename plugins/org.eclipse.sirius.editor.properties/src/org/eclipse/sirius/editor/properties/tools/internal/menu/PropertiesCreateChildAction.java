/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.tools.internal.menu;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * A {@link CreateChildAction} with a specific label and priority.
 * 
 * @author sbegaudeau
 */
public class PropertiesCreateChildAction extends CreateChildAction {

    /**
     * The label.
     */
    private String label;

    /**
     * The priority.
     */
    private int priority;

    /**
     * The constructor.
     * 
     * @param editorPart
     *            The editor part
     * @param selection
     *            The selection
     * @param commandParameter
     *            The command parameter
     * @param label
     *            The label
     * @param priority
     *            The priority
     */
    public PropertiesCreateChildAction(IEditorPart editorPart, ISelection selection, CommandParameter commandParameter, String label, int priority) {
        super(editorPart, selection, commandParameter);
        this.label = label;
        this.priority = priority;
    }

    /**
     * Returns the priority.
     * 
     * @return The priority
     */
    public int getPriority() {
        return this.priority;
    }

    @Override
    public String getText() {
        return this.label;
    }
}
