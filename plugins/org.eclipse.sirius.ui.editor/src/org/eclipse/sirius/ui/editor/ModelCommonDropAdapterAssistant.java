/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;

/**
 * A {@link CommonDropAdapterAssistant} to support drop of model elements into
 * semantic tree viewer.
 * 
 * @author jmallet
 */
@SuppressWarnings("restriction")
public class ModelCommonDropAdapterAssistant extends CommonDropAdapterAssistant {

    @Override
    public IStatus validateDrop(Object target, int operation, TransferData transferType) {
        IStatus status = Status.CANCEL_STATUS;
        if (DND.DROP_MOVE == operation) {
            Collection<File> extractDragSource = extractDragSource(LocalSelectionTransfer.getTransfer().getSelection());
            if (!extractDragSource.isEmpty()) {
                status = Status.OK_STATUS;
            }
        }
        return status;
    }

    @Override
    public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object aTarget) {
        IStatus status = Status.CANCEL_STATUS;
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        final IWorkbenchPage activePage = window.getActivePage();
        final IEditorPart activeEditor = activePage.getActiveEditor();
        if (activeEditor instanceof SessionEditor) {
            Session session = ((SessionEditor) activeEditor).getSession();
            Collection<File> droppedEObjects = extractDragSource(aDropTargetEvent.data);
            if (!droppedEObjects.isEmpty()) {
                try {
                    new ProgressMonitorDialog(window.getShell()).run(false, false, monitor -> {
                        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                            @Override
                            protected void doExecute() {
                                for (Iterator<File> iterator = droppedEObjects.iterator(); iterator.hasNext(); ) {
                                    File file = iterator.next();
                                    session.addSemanticResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), monitor);
                                }
                            }
                        });
                    });
                    status = Status.OK_STATUS;
                } catch (InvocationTargetException | InterruptedException e) {
                    SessionEditorPlugin.getPlugin().error(e.getMessage(), e);
                }
            }
        }
        return status;
    }

    /**
     * This extracts a collection of dragged source file from the given object
     * retrieved from the transfer agent. This default implementation converts a
     * structured selection into a collection of elements.
     * 
     * @param object
     *            the object representing the selection in drag
     * @return the drag selection in form of collection
     */
    protected Collection<File> extractDragSource(Object object) {
        Collection<File> droppedEObjects = new ArrayList<File>();
        if (object instanceof IStructuredSelection) {
            List<?> list = ((IStructuredSelection) object).toList();
            for (Object obj : list) {
                if (obj instanceof File) {
                    File file = (File) obj;
                    droppedEObjects.add(file);
                }
            }
        }
        return droppedEObjects;
    }

}
