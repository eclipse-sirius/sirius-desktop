/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.dialect;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Shell;

/**
 * Default dialog factory.
 *
 * @author mchauvin
 * @since 0.9.0
 */
public class DefaultDialectEditorDialogFactory implements DialectEditorDialogFactory {
    /** Deleted diagram dialog title. */
    public static final String ELEMENT_HAS_BEEN_DELETED_TITLE = Messages.DefaultDialectEditorDialogFactory_title;

    /**
     * Deleted diagram dialog message.
     */
    private static final String ELEMENT_HAS_BEEN_DELETED = Messages.DefaultDialectEditorDialogFactory_message;

    @Override
    public void editorWillBeClosedInformationDialog(Shell parent) {
        MessageDialog.openInformation(parent, ELEMENT_HAS_BEEN_DELETED_TITLE, ELEMENT_HAS_BEEN_DELETED);
    }

    @Override
    public void informUserOfEvent(int severity, String message) {
        // Default implementation is to do nothing
    }
}
