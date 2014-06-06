/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com> - initial API and implementation 
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 434698, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.diagram.editor.tools.internal.menu.refactoring.grouping;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.ui.IEditorPart;

/**
 * This action allows the user to group several tools (AbstractToolDescription)
 * into a new ToolGroup.
 * 
 * @author Joao Martins
 * 
 */
public class GroupIntoToolGroupAction extends AbstractToolGroupIntoAction {

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * @param selection
     *            the current selection.
     */
    public GroupIntoToolGroupAction(final IEditorPart editor, final ISelection selection) {
        super(editor, selection);
        setTextIfDisable(GroupIntoToolGroupCommand.TEXT);
    }

    @Override
    protected Command createGroupIntoCommand(EditingDomain arg0, ToolSection parent, Collection<EObject> selection) {
        return new GroupIntoToolGroupCommand(arg0.getResourceSet(), parent, selection);
    }
}
