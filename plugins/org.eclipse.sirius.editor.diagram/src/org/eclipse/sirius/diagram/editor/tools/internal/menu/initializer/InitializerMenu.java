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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Sets;

/**
 * Menu of the Initializer.
 * 
 * @author Joao Martins
 * 
 */
public class InitializerMenu extends AbstractMenuBuilder {

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

        IStructuredSelection sselection = (IStructuredSelection) selection;
        List<?> list = sselection.toList();
        Collection<Object> collection = new ArrayList<Object>(list);

        final Collection<EObject> selected = new ArrayList<EObject>();
        for (final Object object : collection) {
            if (object instanceof EObject) {
                selected.add((EObject) object);
            }
        }
        if (!selected.isEmpty() && selected.iterator().next() instanceof Viewpoint) {
            Viewpoint viewpoint = (Viewpoint) selected.iterator().next();
            advancedChildActions = generateInitializerActions(viewpoint, editor);
        }
    }

    private Collection generateInitializerActions(final Viewpoint viewpoint, final IEditorPart editor) {

        // We first build all candidate Actions
        Set<Action> allActions = Sets.newLinkedHashSet();
        allActions.add(new InitializerOpenWizardAction(editor, viewpoint));
        return allActions;
    }

    @Override
    protected boolean isMine(CommandParameter object) {
        return false;
    }

}
