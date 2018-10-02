/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.editor.properties.tools.internal.menu;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.GroupDescription;

/**
 * Creates a widget description with values from a specific structural feature..
 * 
 * @author arichard
 */
public class CreateWidgetForFeatureCommand extends AbstractUndoRecordingCommand {

    /**
     * The descriptor.
     */
    private CreateWidgetForFeatureDescriptor descriptor;

    /**
     * Create a new command.
     * 
     * @param resourceSet
     *            The current resourceSet.
     * @param descriptor
     *            The descriptor.
     */
    public CreateWidgetForFeatureCommand(ResourceSet resourceSet, CreateWidgetForFeatureDescriptor descriptor) {
        super(resourceSet);
        this.descriptor = descriptor;
    }

    @Override
    protected void doExecute() {
        EObject controlsContainerDescription = this.descriptor.getControlsContainerDescription();
        if (controlsContainerDescription instanceof GroupDescription) {
            ((GroupDescription) controlsContainerDescription).getControls().add(this.descriptor.getDefaultWidgetDescription().getWidgetDescription());
        } else if (controlsContainerDescription instanceof ContainerDescription) {
            ((ContainerDescription) controlsContainerDescription).getControls().add(this.descriptor.getDefaultWidgetDescription().getWidgetDescription());
        }
    }

    @Override
    protected String getText() {
        return this.descriptor.getDefaultWidgetDescription().getLabel();
    }
}
