/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.commands;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.tools.internal.Messages;

import com.google.common.collect.Lists;

/**
 * A command to mark a collection of diagram elements as "pinned" so that they
 * are not moved/resized during automatic layout operations.
 * 
 * @author pcdavid
 */
public class PinElementsCommand extends RecordingCommand {
    /**
     * The elements to mark as pinned.
     */
    private final Collection<DDiagramElement> targetElements;

    /**
     * Creates a new {@link PinElementsCommand}.
     * 
     * @param targetElements
     *            the elements which must be marked as pinned.
     */
    public PinElementsCommand(final Collection<? extends DDiagramElement> targetElements) {
        super(TransactionUtil.getEditingDomain(targetElements.iterator().next()), Messages.PinElementsCommand_commandLabel);
        this.targetElements = Lists.newArrayList(targetElements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        PinHelper pinHelper = new PinHelper();
        for (DDiagramElement element : targetElements) {
            pinHelper.markAsPinned(element);
        }
    }
}
