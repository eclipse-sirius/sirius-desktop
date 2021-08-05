/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.helper.task.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.helper.task.IDeletionTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.tool.DeleteView;
import org.eclipse.sirius.viewpoint.description.tool.RemoveElement;

/**
 * This task remove an element and unset all the references an element may have DRepresentationElement on this one.
 *
 * @author Cedric Brun (cbrun)
 *
 */
public class RemoveElementTask extends AbstractOperationTask implements IDeletionTask {

    private EObject toBeRemoved;

    private boolean deleteView;

    /**
     * Default constructor.
     *
     * @param context
     *            the command context
     * @param extPackage
     *            the extended package
     * @param op
     *            the operation
     * @param interpreter
     *            the interpreter to use.
     */
    public RemoveElementTask(final CommandContext context, final ModelAccessor extPackage, final RemoveElement op, IInterpreter interpreter) {
        super(context, extPackage, interpreter);
    }

    /**
     * Default constructor.
     *
     * @param extPackage
     *            the extended package
     * @param context
     *            the command context
     * @param op
     *            the operation
     * @param interpreter
     *            the interpreter to use.
     */
    public RemoveElementTask(final ModelAccessor extPackage, final CommandContext context, final DeleteView op, IInterpreter interpreter) {
        super(context, extPackage, interpreter);
        deleteView = true;
    }

    @Override
    public void execute() {
        this.toBeRemoved = context.getCurrentTarget();
        if (deleteView) {
            if (!(toBeRemoved instanceof DRepresentationElement)) {
                SiriusPlugin.getDefault().warning(Messages.RemoveElementTask_notAViewErrorMsg, new IllegalArgumentException());
                return;
            }
        }
        if (toBeRemoved != null && this.toBeRemoved.eContainingFeature() != null) {
            SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(toBeRemoved).eRemove(toBeRemoved);
        }
    }

    @Override
    public String getLabel() {
        return Messages.RemoveElementTask_label;
    }

    @Override
    public Collection<EObject> getDeletedElements() {
        final Collection<EObject> result = new ArrayList<EObject>();
        if (this.toBeRemoved != null) {
            result.add(this.toBeRemoved);
            final Iterator<EObject> it = this.toBeRemoved.eAllContents();
            while (it.hasNext()) {
                result.add(it.next());
            }
        }
        return result;
    }
}
