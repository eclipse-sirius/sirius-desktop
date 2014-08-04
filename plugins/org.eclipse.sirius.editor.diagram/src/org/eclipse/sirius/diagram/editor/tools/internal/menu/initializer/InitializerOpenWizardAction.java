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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;

/**
 * Initializer Action, only work when the specifier do a right click on
 * Viewpoint.
 * 
 * @author Joao Martins.
 * 
 */
public class InitializerOpenWizardAction extends Action {

    /**
     * Label on menu.
     */
    public static final String TEXT = "Initializer";

    private final Viewpoint viewpoint;

    private EditingDomain editingDomain;

    /**
     * Constructor.
     * 
     * @param set
     *            Editing Domain.
     * @param viewpoint
     *            selection.
     */
    public InitializerOpenWizardAction(final IEditorPart set, final Viewpoint viewpoint) {
        super();
        this.viewpoint = viewpoint;
        editingDomain = ((IEditingDomainProvider) set).getEditingDomain();
    }

    @Override
    public void run() {
        InitializerWizard wizard = new InitializerWizard();
        WizardDialog dialog = new WizardDialog(null, wizard);
        dialog.create();
        if (dialog.open() == Window.OK) {
            IPatternProvider patternProvider = wizard.getPattern();
            if (patternProvider != null) {
                Command cmd = patternProvider.getPatternCreationCommand(editingDomain.getResourceSet(), viewpoint);
                if (cmd != null && cmd.canExecute()) {
                    editingDomain.getCommandStack().execute(cmd);
                }
            }
        }

    }

    public String getText() {
        return TEXT;
    }

}
