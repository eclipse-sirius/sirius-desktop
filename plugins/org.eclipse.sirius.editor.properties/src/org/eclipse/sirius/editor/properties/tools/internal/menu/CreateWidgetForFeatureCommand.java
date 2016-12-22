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

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;

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
        this.descriptor.getGroupDescription().getControls().add(this.descriptor.getDefaultWidgetDescription().getWidgetDescription());
    }

    @Override
    protected String getText() {
        return this.descriptor.getDefaultWidgetDescription().getLabel();
    }
}
