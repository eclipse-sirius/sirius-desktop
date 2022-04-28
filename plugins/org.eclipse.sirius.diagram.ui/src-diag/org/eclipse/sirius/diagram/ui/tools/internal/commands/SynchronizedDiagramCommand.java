/*******************************************************************************
 * Copyright (c) 2010, 2015, 2022 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    Vincent LORENZO (CEA LIST) - vincent.lorenzo@cea.fr - Bug 579780
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Map;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SynchronizedDiagramAction;
import org.eclipse.sirius.ui.tools.internal.commands.AbstractActionWrapperHandler;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.menus.UIElement;

/**
 * Toggle between synchronized and unsynchronized diagram modes.
 *
 * @author smonnier
 */
public class SynchronizedDiagramCommand extends AbstractActionWrapperHandler implements IElementUpdater {

    /**
     * Construct a new instance.
     */
    public SynchronizedDiagramCommand() {
        super(new SynchronizedDiagramAction());
    }

    /**
     * Update the synchronization contribution menu. {@inheritDoc}
     *
     * @see org.eclipse.ui.commands.IElementUpdater#updateElement(org.eclipse.ui.menus.UIElement,
     *      java.util.Map)
     */
    @Override
    public void updateElement(final UIElement element, final Map parameters) {
        final IWorkbenchWindow window = element.getServiceLocator().getService(IWorkbenchWindow.class);
        if (window == null) {
            return;
        }

        final IWorkbenchPage activePage = window.getActivePage();
        final IEditorPart activeEditor = activePage.getActiveEditor();
        DDiagramEditor ddiagramEditor = null;
        if (activeEditor instanceof DDiagramEditor) {
            ddiagramEditor = (DDiagramEditor) activeEditor;
        } else if (activeEditor != null) {
        	//required when the DDiagramEditor is embedded in a multipage editor
            ddiagramEditor = activeEditor.getAdapter(DDiagramEditor.class);
        }
        if (ddiagramEditor != null) {
            if (ddiagramEditor.getRepresentation() instanceof DDiagram) {
                final DDiagram diagram = (DDiagram) ddiagramEditor.getRepresentation();
                element.setChecked(!diagram.isSynchronized());
                element.setText(Messages.SynchronizedDiagramCommand_unsynchronizedLabel);
            }
        }
    }
}
