/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.wizards;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.ui.IWorkbench;

/**
 * This is a simple wizard for creating a new model file.
 */
public class DescriptionModelWizardSpec extends DescriptionModelWizard {

    /**
     * {@inheritDoc}
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
        setWindowTitle(SiriusEditorPlugin.INSTANCE.getString("_UI_Wizard_label"));
        setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(SiriusEditorPlugin.INSTANCE.getImage("full/wizban/banner_odesign")));
    }
}