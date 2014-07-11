/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com>  - initial API and implementation
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 438074, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.diagram.editor.tools.internal.menu.initializer;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractEObjectRefactoringAction;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

/**
 * Menu of the Initializer.
 * 
 * @author Joao Martins
 * 
 */
public class DiagramInitializerMenu extends AbstractMenuBuilder {

    /**
     * Initializer menu label.
     */
    public static final String INITIALIZER_MENU_LABEL = "Initializer";

    @Override
    public String getLabel() {
        return INITIALIZER_MENU_LABEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Collection newChildDescriptors, final ISelection selection, final IEditorPart editor) {
        depopulate();
        advancedChildActions = generateInitializerActions(selection, editor);
    }

    private Collection generateInitializerActions(final ISelection selection, final IEditorPart editor) {

        // We first build all candidate Actions
        Set<AbstractEObjectRefactoringAction> allActions = Sets.newLinkedHashSet();
        allActions.add(new InitializerAction(editor, selection));

        // We only add to the menu the actions that have a valid selection
        return Sets.filter(allActions, new Predicate<AbstractEObjectRefactoringAction>() {

            public boolean apply(AbstractEObjectRefactoringAction candidateAction) {
                return candidateAction.isSelectionValid();
            }
        });
    }

    @Override
    protected boolean isMine(CommandParameter object) {
        return false;
    }

}
