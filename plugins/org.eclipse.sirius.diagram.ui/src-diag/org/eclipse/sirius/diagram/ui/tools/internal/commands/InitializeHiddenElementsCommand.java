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

import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * Specific command to add hidden elements to transient reference
 * hiddenElements.
 *
 * @author mporhel
 */
public final class InitializeHiddenElementsCommand extends RecordingCommand {

    private final DDiagram dDiagram;

    private final List<DDiagramElement> hiddenElements;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain.
     * @param hiddenElements
     *            the hiddens elements
     * @param dDiagram
     *            the DDiagram to update.
     */
    public InitializeHiddenElementsCommand(TransactionalEditingDomain domain, DDiagram dDiagram, List<DDiagramElement> hiddenElements) {
        super(domain, Messages.InitializeHiddenElementsCommand_label);
        this.dDiagram = dDiagram;
        this.hiddenElements = hiddenElements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (dDiagram == null || hiddenElements == null || hiddenElements.isEmpty()) {
            return;
        }

        dDiagram.getHiddenElements().addAll(hiddenElements);
    }
}
