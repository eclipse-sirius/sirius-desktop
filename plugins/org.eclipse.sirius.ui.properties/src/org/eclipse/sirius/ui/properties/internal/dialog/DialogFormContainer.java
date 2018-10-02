/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.ui.properties.internal.dialog;

import org.eclipse.eef.common.ui.api.EEFWidgetFactory;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.Form;

/**
 * The {@link IEEFFormContainer} for the form dialog.
 * 
 * @author sbegaudeau
 */
public class DialogFormContainer implements IEEFFormContainer {

    /**
     * The shell.
     */
    private Shell shell;

    /**
     * The form.
     */
    private Form form;

    /**
     * The constructor.
     * 
     * @param shell
     *            The shell
     * @param form
     *            The form
     */
    public DialogFormContainer(Shell shell, Form form) {
        this.shell = shell;
        this.form = form;
    }

    @Override
    public Form getForm() {
        return this.form;
    }

    @Override
    public Shell getShell() {
        return this.shell;
    }

    @Override
    public EEFWidgetFactory getWidgetFactory() {
        return new EEFWidgetFactory();
    }

    @Override
    public boolean isRenderingInProgress() {
        return false;
    }

    @Override
    public void refreshPage() {
        // do nothing for now :)
    }

}
