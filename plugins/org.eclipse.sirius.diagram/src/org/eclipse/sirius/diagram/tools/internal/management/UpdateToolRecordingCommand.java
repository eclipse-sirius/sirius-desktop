/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.management;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.management.ToolManagement;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.internal.Messages;

/**
 * Recording command updating tools available for a diagram and their filter listeners.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class UpdateToolRecordingCommand extends RecordingCommand {

    private DDiagram diagram;

    private boolean updateFilters;

    /**
     * Initialize the command.
     * 
     * @param editingDomain
     *            the editing domain used.
     * @param diagram
     *            the diagram from which tools are updated.
     * @param updateFilters
     *            true if filters should be updated.
     */
    public UpdateToolRecordingCommand(TransactionalEditingDomain editingDomain, DDiagram diagram, boolean updateFilters) {
        super(editingDomain);
        this.diagram = diagram;
        this.updateFilters = updateFilters;
    }

    @Override
    public String getLabel() {
        return Messages.ToolManagment_ToolChange_Command_Label;
    }

    @Override
    protected void doExecute() {
        ToolManagement toolManagement = DiagramPlugin.getPlugin().getToolManagement(diagram);
        if (toolManagement != null) {
            toolManagement.updateTools(updateFilters);
        }
    }

}
