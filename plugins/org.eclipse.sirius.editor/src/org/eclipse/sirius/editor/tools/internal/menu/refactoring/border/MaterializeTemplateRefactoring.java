/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.menu.refactoring.border;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.editor.tools.api.menu.AbstractEObjectRefactoringAction;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;

/**
 * Action changing a representation template to classical representations.
 * 
 * @author cbrun
 * 
 */
public class MaterializeTemplateRefactoring extends AbstractEObjectRefactoringAction {
    private static final String MATERIALIZE_TEMPLATE_COMMAND_LABEL = "Transform Template to Representations";

    /**
     * Create the action.
     * 
     * @param editor
     *            the current editor.
     * @param selection
     *            the current selection.
     */
    public MaterializeTemplateRefactoring(final IEditorPart editor, final ISelection selection) {
        super(editor, selection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command buildActionCommand(final EditingDomain arg0, final Collection<EObject> selection) {
        Command result = UnexecutableCommand.INSTANCE;
        setSelectionValid(false);
        if (selection.size() == 1) {
            final EObject elementToMove = selection.iterator().next();
            if (elementToMove instanceof RepresentationTemplate && elementToMove.eContainer() instanceof Viewpoint) {
                setSelectionValid(true);
                setTextIfDisable(MATERIALIZE_TEMPLATE_COMMAND_LABEL);
                final RepresentationTemplate template = (RepresentationTemplate) elementToMove;
                final Viewpoint containingSirius = (Viewpoint) template.eContainer();
                result = new AbstractUndoRecordingCommand(arg0.getResourceSet()) {

                    @Override
                    protected void doExecute() {
                        containingSirius.getOwnedRepresentations().addAll(template.getOwnedRepresentations());
                        EcoreUtil.delete(template);
                    }

                    @Override
                    protected String getText() {
                        return MATERIALIZE_TEMPLATE_COMMAND_LABEL;
                    }

                };
            }
        }
        return result;
    }
}
