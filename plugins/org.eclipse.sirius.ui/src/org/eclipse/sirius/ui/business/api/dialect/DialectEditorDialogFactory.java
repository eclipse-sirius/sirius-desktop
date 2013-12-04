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
package org.eclipse.sirius.ui.business.api.dialect;

import org.eclipse.swt.widgets.Shell;

/**
 * Dialog factory for any editor.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface DialectEditorDialogFactory {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.MessageDialog.openInformation
     */
    void editorWillBeClosedInformationDialog(Shell parent);

    /**
     * Informs the end-user wit the given message, with the given severity.
     * 
     * 
     * @param severity
     *            the severity (one of
     *            {@link org.eclipse.core.runtime.IStatus#OK} ,
     *            {@link org.eclipse.core.runtime.IStatus#ERROR},
     *            {@link org.eclipse.core.runtime.IStatus#WARNING}).
     * @param message
     *            the message to show to the end-user
     * @since 0.9.0
     */
    void informUserOfEvent(int severity, String message);

}
