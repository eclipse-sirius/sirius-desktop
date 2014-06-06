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
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractEObjectRefactoringAction;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.ui.IEditorPart;

/**
 * Useful to the creation of the ToolGroupIntoActions.
 * 
 * @author Joao Martins
 * 
 */
public abstract class AbstractToolGroupIntoAction extends AbstractEObjectRefactoringAction {

    /**
     * Constructor.
     * 
     * @param editor
     *            the current editor.
     * @param selection
     *            the current selection.
     */

    public AbstractToolGroupIntoAction(IEditorPart editor, ISelection selection) {
        super(editor, selection);
    }

    @Override
    protected Command buildActionCommand(EditingDomain arg0, Collection<EObject> selection) {
        Command result = UnexecutableCommand.INSTANCE;
        ToolSection foundParent = findParentSection(selection);
        setSelectionValid(false);
        if (selection.size() > 1) {
            // we only want to display this action when we have several items
            // selected
            if (foundParent != null) {
                setSelectionValid(true);
                result = createGroupIntoCommand(arg0, foundParent, selection);
            }
        }
        return result;

    }

    /**
     * Get the action Command.
     * 
     * @param arg0
     *            Domain.
     * @param parent
     *            ToolSection.
     * @param selection
     *            the current selection.
     * @return the action Command.
     */
    protected abstract Command createGroupIntoCommand(EditingDomain arg0, ToolSection parent, Collection<EObject> selection);

    /**
     * This is useful to find a Parent(ToolSection) to group all selected tools.
     * 
     * @param selection
     *            the current selection.
     * @return Return the parent if can be found otherwise return null.
     */
    protected ToolSection findParentSection(Collection<EObject> selection) {
        Option<EObject> toolsection;
        for (EObject tool : selection) {
            toolsection = new EObjectQuery(tool).getFirstAncestorOfType(ToolPackage.eINSTANCE.getToolSection());
            if (toolsection.some()) {
                return (ToolSection) toolsection.get();
            }
        }
        return null;
    }

}
