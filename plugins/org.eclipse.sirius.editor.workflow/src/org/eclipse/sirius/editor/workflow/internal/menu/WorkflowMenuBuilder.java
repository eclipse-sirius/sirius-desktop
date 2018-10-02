/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.editor.workflow.internal.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.editor.workflow.internal.Messages;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.workflow.WorkflowDescription;
import org.eclipse.sirius.workflow.WorkflowPackage;
import org.eclipse.ui.IEditorPart;

/**
 * Menu builder used to create a new workflow.
 * 
 * @author sbegaudeau
 */
public class WorkflowMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * The constructor.
     */
    public WorkflowMenuBuilder() {
        this.addValidType(WorkflowPackage.Literals.WORKFLOW_DESCRIPTION);
    }

    @Override
    public String getLabel() {
        return Messages.WorkflowMenuBuilder_label;
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.EXTENSION;
    }

    @Override
    public void update(Collection<?> newChildDescriptors, ISelection selection, IEditorPart editor) {
        this.depopulate();
        this.advancedChildActions = new ArrayList<>();

        Optional<CommandParameter> optionalNewWorkflowCommandParameter = newChildDescriptors.stream().filter(CommandParameter.class::isInstance).map(CommandParameter.class::cast)
                .filter(parameter -> DescriptionPackage.Literals.GROUP__EXTENSIONS.equals(parameter.getEStructuralFeature()) && parameter.getEValue() instanceof WorkflowDescription).findFirst();

        if (selection instanceof IStructuredSelection && optionalNewWorkflowCommandParameter.isPresent()) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;

            // Add the parameter to our descriptor to prevent its use somewhere
            // else which would create another "New" menu with this entry
            CommandParameter newWorkflowCommandParameter = optionalNewWorkflowCommandParameter.get();
            descriptors.add(newWorkflowCommandParameter);

            Optional<Group> optionalGroup = Arrays.stream(structuredSelection.toArray()).filter(Group.class::isInstance).map(Group.class::cast).findFirst();
            optionalGroup.ifPresent(group -> {
                newWorkflowCommandParameter.setOwner(group);

                Collection<CreateChildAction> actions = new ArrayList<>();
                actions.add(new WorkflowCreateChildAction(editor, structuredSelection, newWorkflowCommandParameter, Messages.WorkflowMenuBuilder_NewWorkflow_label, 1000));

                this.advancedChildActions = actions;
            });
        }
    }
}
