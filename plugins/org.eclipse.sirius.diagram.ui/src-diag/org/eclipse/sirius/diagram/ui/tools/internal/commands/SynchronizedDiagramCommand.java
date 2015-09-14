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
    public void updateElement(final UIElement element, @SuppressWarnings("rawtypes") final Map parameters) {
        final IWorkbenchWindow window = (IWorkbenchWindow) element.getServiceLocator().getService(IWorkbenchWindow.class);
        if (window == null) {
            return;
        }

        final IWorkbenchPage activePage = window.getActivePage();
        final IEditorPart activeEditor = activePage.getActiveEditor();
        if (activeEditor instanceof DDiagramEditor) {
            final DDiagramEditor ddiagramEditor = (DDiagramEditor) activeEditor;
            if (ddiagramEditor.getRepresentation() instanceof DDiagram) {
                final DDiagram diagram = (DDiagram) ddiagramEditor.getRepresentation();
                element.setChecked(!diagram.isSynchronized());
                element.setText(Messages.SynchronizedDiagramCommand_unsynchronizedLabel);
            }
        }
    }
}
