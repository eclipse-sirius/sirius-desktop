/*******************************************************************************
 * Copyright (c) 2016 Obeo.
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.editor.properties.Messages;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.ui.properties.internal.tabprovider.SiriusTabDescriptorProvider;
import org.eclipse.sirius.viewpoint.description.Group;

/**
 * Command inlining the default properties view description.
 * 
 * @author mbats
 * 
 */
public class ImportingDefaultPropertiesViewDescriptionCommand extends AbstractUndoRecordingCommand {

    private final Group group;

    /**
     * Create a new command.
     * 
     * @param set
     *            the current resourceSet.
     * @param group
     *            the group element in which the rules must be imported.
     */
    public ImportingDefaultPropertiesViewDescriptionCommand(final ResourceSet set, final Group group) {
        super(set);
        this.group = group;
    }

    @Override
    protected void doExecute() {
        // Load the default rules
        ViewExtensionDescription defaults = SiriusTabDescriptorProvider.getDefaultRules();

        // Copy the default rules into the selected group
        ViewExtensionDescription ved = EcoreUtil.copy(defaults);
        group.getExtensions().add(ved);

    }

    @Override
    protected String getText() {
        return Messages.ImportingDefaultPropertiesViewDescriptionCommand_text;
    }
}
