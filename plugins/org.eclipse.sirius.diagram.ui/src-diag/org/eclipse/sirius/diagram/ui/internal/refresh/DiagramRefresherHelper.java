/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh.RefreshRunnableWithProgress;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * This class contains utility methods used to refresh table editors.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class DiagramRefresherHelper {

    /**
     * Private constructor for utility class.
     */
    private DiagramRefresherHelper() {
        super();
    }

    /**
     * Refreshes the tree item of the given selection if such element exists. If not refreshes the all editor. Should be
     * call in an EMF {@link Command} because some semantics modifications can happen in this context.
     * 
     * @param diagram
     *            the diagram from which edit parts will be refreshed.
     * @param editParts
     *            the edit parts to refresh.
     */
    public static void refreshEditParts(DDiagram diagram, final Collection<EditPart> editParts) {
        if (diagram != null) {
            RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(diagram);
        }

        final Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
        try {
            monitorDialog.run(true, false, new RefreshRunnableWithProgress(editParts));
        } catch (final InvocationTargetException e) {
            MessageDialog.openError(activeShell, Messages.RefreshDiagramAction_error, e.getTargetException().getMessage());
            SiriusPlugin.getDefault().error(Messages.RefreshDiagramAction_refreshDiagramError, e);
        } catch (final InterruptedException e) {
            MessageDialog.openInformation(activeShell, Messages.RefreshDiagramAction_cancelled, e.getMessage());
        }
    }
}
