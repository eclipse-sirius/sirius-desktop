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

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.editor.properties.Messages;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

/**
 * The menu for the import actions.
 * 
 * @author mbats
 */
public class ImportingMenu extends AbstractMenuBuilder {

    /**
     * Import menu label.
     */
    public static final String IMPORTING_MENU_LABEL = Messages.ImportingMenu_label;

    @Override
    public String getLabel() {
        return IMPORTING_MENU_LABEL;
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.OTHERS;
    }

    @Override
    public void update(final Collection newChildDescriptors, final ISelection selection, final IEditorPart editor) {
        depopulate();
        advancedChildActions = generateImportingActions(selection, editor);
    }

    private Collection<?> generateImportingActions(final ISelection selection, final IEditorPart editor) {
        // We first build all candidate Actions
        Set<ImportingDefaultPropertiesViewDescriptionAction> allActions = Sets.newLinkedHashSet();
        allActions.add(new ImportingDefaultPropertiesViewDescriptionAction(editor, selection));

        // We only add to the menu the actions that have a valid selection
        return Sets.filter(allActions, new Predicate<ImportingDefaultPropertiesViewDescriptionAction>() {

            public boolean apply(ImportingDefaultPropertiesViewDescriptionAction candidateAction) {
                return candidateAction.isSelectionValid();
            }
        });
    }

    @Override
    protected boolean isMine(final CommandParameter object) {
        // not relevant here
        return false;
    }
}
