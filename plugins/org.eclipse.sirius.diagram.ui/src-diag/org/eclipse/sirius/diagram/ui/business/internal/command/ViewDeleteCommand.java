/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.collect.Iterables;

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

        // Remove incoming or outgoing NoteAttachment links
        for (Edge edge : Iterables.filter(Iterables.concat(getView().getSourceEdges(), getView().getTargetEdges()), Edge.class)) {
            if (ViewType.NOTEATTACHMENT.equals(edge.getType())) {
                EcoreUtil.remove(edge);
            }
        }
        EcoreUtil.remove(getView());
        return CommandResult.newOKCommandResult();
    }

}
