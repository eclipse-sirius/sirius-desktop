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

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

/**
 * An action that allows to execute a command that creates a widget description
 * initialized from a specific structural feature.
 * 
 * @author sbegaudeau
 * @author arichard
 */
public class CreateWidgetForFeatureAction extends CreateChildAction {

    /**
     * Create a new action.
     * 
     * @param editor
     *            The current editor.
     * @param selection
     *            The current selection.
     * @param descriptor
     *            The descriptor
     */
    public CreateWidgetForFeatureAction(IEditorPart editor, ISelection selection, CreateWidgetForFeatureDescriptor descriptor) {
        super(editor, selection, descriptor);
        this.configureAction(selection);
    }

    @Override
    public String getText() {
        if (this.command != null) {
            return this.command.getLabel();
        }
        return super.getText();
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        if (this.descriptor instanceof CreateWidgetForFeatureDescriptor) {
            CreateWidgetForFeatureDescriptor widgetForFeatureDescriptor = (CreateWidgetForFeatureDescriptor) this.descriptor;
            return widgetForFeatureDescriptor.getImageDescriptor();
        }
        return null;
    }

    @Override
    protected Command createActionCommand(EditingDomain editingDomain, Collection<?> selection) {
        if (this.descriptor instanceof CreateWidgetForFeatureDescriptor) {
            return new CreateWidgetForFeatureCommand(editingDomain.getResourceSet(), (CreateWidgetForFeatureDescriptor) this.descriptor);
        }
        return null;
    }

}
