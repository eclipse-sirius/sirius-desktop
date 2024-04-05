/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.editor.tools.internal.menu.refactoring;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.editor.tools.internal.menu.refactoring.border.BorderRefactoringAction;
import org.eclipse.sirius.editor.tools.api.menu.AbstractEObjectRefactoringAction;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.refactoring.RefactoringMenu;
import org.eclipse.ui.IEditorPart;

/**
 * The menu for the refactoring actions.
 * 
 * @author cbrun
 */
public class DiagramRefactoringMenu extends AbstractMenuBuilder {
    @Override
    public String getLabel() {
        return RefactoringMenu.REFACTORING_MENU_LABEL;
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.REFACTOR;
    }

    @Override
    public void update(final Collection newChildDescriptors, final ISelection selection, final IEditorPart editor) {
        depopulate();
        advancedChildActions = generateRefactoringActions(selection, editor);
    }

    private Collection generateRefactoringActions(final ISelection selection, final IEditorPart editor) {

        // We first build all candidate Actions
        Set<AbstractEObjectRefactoringAction> allActions = new LinkedHashSet<>();
        allActions.add(new BorderRefactoringAction(editor, selection));
        allActions.add(new EdgeMappingRefactoringAction(editor, selection));

        // We only add to the menu the actions that have a valid selection
        return allActions.stream().filter(AbstractEObjectRefactoringAction::isSelectionValid).collect(Collectors.toSet());
    }

    @Override
    protected boolean isMine(final CommandParameter object) {
        // not relevant here
        return false;
    }
}
