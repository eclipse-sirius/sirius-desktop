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
import org.eclipse.sirius.editor.properties.internal.SiriusEditorPropertiesPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * This action allows the creation of a command which will create a
 * {@link org.eclipse.sirius.properties.WidgetDescription} for all the
 * structural fetaures of the selected domain class.
 * 
 * @author sbegaudeau
 * @author arichard
 */
public class CreateWidgetForAllFeaturesAction extends CreateChildAction {

    /**
     * The constructor.
     * 
     * @param editorPart
     *            The editor part
     * @param selection
     *            The current selection
     * @param descriptor
     *            The descriptor
     */
    public CreateWidgetForAllFeaturesAction(IEditorPart editorPart, ISelection selection, CreateWidgetForAllFeaturesDescriptor descriptor) {
        super(editorPart, selection, descriptor);
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
        return AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditorPropertiesPlugin.PLUGIN_ID, SiriusEditorPropertiesPlugin.Implementation.CREATE_WIDGET_FOR_ALL_FEATURES_ICON_PATH);
    }

    @Override
    protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection) {
        if (this.descriptor instanceof CreateWidgetForAllFeaturesDescriptor) {
            return new CreateWidgetForAllFeaturesCommand(editingDomain.getResourceSet(), (CreateWidgetForAllFeaturesDescriptor) this.descriptor);
        }
        return null;
    }

}
