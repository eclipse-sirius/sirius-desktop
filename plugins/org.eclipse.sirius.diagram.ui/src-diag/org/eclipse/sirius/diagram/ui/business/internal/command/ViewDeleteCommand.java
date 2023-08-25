/*******************************************************************************
 * Copyright (c) 2015, 2023 Obeo.
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
package org.eclipse.sirius.diagram.ui.business.internal.command;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;

/**
 * Extends the GMF {@link DeleteCommand} to avoid creating
 * {@link org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter}.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
public class ViewDeleteCommand extends DeleteCommand {

    /**
     * Creates a new Delete command.
     * 
     * @param editingDomain
     *            the editing domain through which model changes are made
     * @param view
     *            view to delete
     */
    public ViewDeleteCommand(TransactionalEditingDomain editingDomain, View view) {
        super(editingDomain, view);
    }

    /**
     * Creates a new Delete command.
     * 
     * @param view
     *            view to delete
     */
    public ViewDeleteCommand(View view) {
        super(view);
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
        // Prevents GMF to install its CrossReferencerAdapter by not calling
        // ViewUtil.destroy(View)

        boolean prefRemoveAttachedPGE = DiagramUIPlugin.getPlugin().getPreferenceStore()
                .getBoolean(SiriusDiagramUiInternalPreferencesKeys.PREF_REMOVE_HIDE_NOTE_WHEN_ANNOTED_ELEMENT_HIDDEN_OR_REMOVE.name());

        // Remove incoming or outgoing NoteAttachment links
        List<Edge> attachedEdge = GMFHelper.getAttachedEdgesRecursively(Collections.singleton(getView()));

        // Get PGE attached to all element that are about to be deleted
        List<Node> attachedPGE = null;
        if (prefRemoveAttachedPGE) {
            attachedPGE = Stream.concat(GMFHelper.getAttachedPGE(getView()).stream(), GMFHelper.getAttachedPGE(attachedEdge).stream()).toList();
        }
        // Delete all elements
        for (Edge edge : attachedEdge) {
            if (ViewType.NOTEATTACHMENT.equals(edge.getType())) {
                EcoreUtil.remove(edge);
            }
        }
        EcoreUtil.remove(getView());

        if (prefRemoveAttachedPGE) {
            // attachedPGE can't be null here
            GMFHelper.deleteDetachedPGE(attachedPGE);
        }

        return CommandResult.newOKCommandResult();
    }

}
