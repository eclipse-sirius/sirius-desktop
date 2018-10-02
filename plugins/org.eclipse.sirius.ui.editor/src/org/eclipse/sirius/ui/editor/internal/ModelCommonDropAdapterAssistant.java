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
package org.eclipse.sirius.ui.editor.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategyRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.SessionEditorPlugin;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;

/**
 * A {@link CommonDropAdapterAssistant} to support drop of model elements into
 * semantic tree viewer.
 * 
 * @author jmallet
 */
public class ModelCommonDropAdapterAssistant extends CommonDropAdapterAssistant {

    @Override
    public IStatus validateDrop(Object target, int operation, TransferData transferType) {
        if (DND.DROP_MOVE == operation) {
            Optional<Session> session = findCurrentSession();
            // Drag and drop of models is only authorized if existing semantic
            // resources are all local
            if (session.isPresent() && session.get().getSemanticResources().stream().allMatch(res -> res.getURI().isPlatformResource())) {
                Collection<IFile> droppedModelFiles = collectDroppedSemanticModelCandidates(LocalSelectionTransfer.getTransfer().getSelection(), session.get());
                if (!droppedModelFiles.isEmpty()) {
                    // Found at least one potential semantic model in the
                    // selection.
                    return Status.OK_STATUS;
                }
            }
        }
        return Status.CANCEL_STATUS;
    }

    @Override
    public IStatus handleDrop(CommonDropAdapter aDropAdapter, DropTargetEvent aDropTargetEvent, Object aTarget) {
        IStatus status = Status.CANCEL_STATUS;
        Optional<Session> session = findCurrentSession();
        if (session.isPresent()) {
            Collection<IFile> droppedModelFiles = collectDroppedSemanticModelCandidates(aDropTargetEvent.data, session.get());
            if (!droppedModelFiles.isEmpty()) {
                try {
                    new ProgressMonitorDialog(getShell()).run(false, false, monitor -> {
                        TransactionalEditingDomain ted = session.get().getTransactionalEditingDomain();
                        ted.getCommandStack().execute(new RecordingCommand(ted) {
                            @Override
                            protected void doExecute() {
                                droppedModelFiles.forEach(file -> session.get().addSemanticResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), monitor));
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

    private Optional<Session> findCurrentSession() {
        final IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
        if (activeEditor instanceof SessionEditor) {
            return Optional.ofNullable(((SessionEditor) activeEditor).getSession());
        } else {
            return Optional.empty();
        }
    }

    /**
     * This extracts a collection of dropped source file from the given object
     * retrieved from the transfer agent. This default implementation converts a
     * structured selection into a collection of elements.
     * 
     * @param object
     *            the object representing the selection in drag
     * @param session
     *            the session into which the candidate files dropped should be
     *            added as semantic models.
     * @return the dropped files which look like potential semantic models the
     *         could be added to the session.
     */
    private Collection<IFile> collectDroppedSemanticModelCandidates(Object object, Session session) {
        if (object instanceof IStructuredSelection) {
            List<?> list = ((IStructuredSelection) object).toList();
            return list.stream().filter(IFile.class::isInstance).map(IFile.class::cast).filter(f -> isLoadableSemanticModel(f, session)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    private boolean isLoadableSemanticModel(IFile file, Session session) {
        URI candidateURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        ResourceStrategyRegistry rsr = ResourceStrategyRegistry.getInstance();
        return rsr.isPotentialSemanticResource(candidateURI) && rsr.isLoadableModel(candidateURI, session);
    }

}
