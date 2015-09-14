/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.NotificationFigure;
import org.eclipse.sirius.ui.business.api.dialect.DefaultDialectEditorDialogFactory;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * Dialog factory for
 * {@link org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DiagramDialectEditorDialogFactory extends DefaultDialectEditorDialogFactory {

    private DDiagramEditorImpl editor;

    /**
     * Default constructor.
     * 
     * @param dDiagramEditorImpl
     *            the editor
     */
    public DiagramDialectEditorDialogFactory(DDiagramEditorImpl dDiagramEditorImpl) {
        this.editor = dDiagramEditorImpl;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DefaultDialectEditorDialogFactory#informUserOfEvent(int,
     *      java.lang.String)
     */
    @Override
    public void informUserOfEvent(int severity, String message) {
        boolean loggedAsNotificationFigure = false;

        // Step 1 : if the preference says that messages
        // should not be displayed using pop ups
        if (!DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiPreferencesKeys.PREF_DIAGRAM_SHOULD_DISPLAY_MESSAGES_USING_POP_UPS.name())) {
            // We try to log it as a notification figure
            if (this.editor.getDiagramEditPart() != null) {
                RootEditPart rootEditPart = this.editor.getDiagramEditPart().getRoot();
                if (rootEditPart instanceof DiagramRootEditPart) {
                    NotificationFigure.createNotification((DiagramRootEditPart) rootEditPart, message);
                    loggedAsNotificationFigure = true;
                }
            }
        }

        // Step 2 : if the preference says that messages should be displayed as
        // pop-ups
        // or if the notification figure created failed
        if (!loggedAsNotificationFigure) {
            // We open a message dialog displaying the given message
            IWorkbenchPartSite site = editor.getSite();
            if (site != null && site.getShell() != null) {
                MessageDialog.openInformation(site.getShell(), Messages.DiagramDialectEditorDialogFactory_forbiddenOperation, message);
            }
        }
    }
}
