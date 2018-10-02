/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import java.text.MessageFormat;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;

/**
 * Change filter activation.
 *
 * @author mchauvin
 */
public class ChangeFilterActivation extends AbstractChangeActivation {

    private final FilterDescription filter;

    /**
     * Constructor.
     *
     * @see AbstractChangeActivation constructor
     *
     * @param part
     *            the diagram workbench part
     * @param diagram
     *            Diagram
     * @param filter
     *            the filter to change status
     * @param activate
     *            Activation of the layer
     */
    public ChangeFilterActivation(final IDiagramWorkbenchPart part, final DDiagram diagram, final FilterDescription filter, final boolean activate) {
        super(part, diagram, activate);
        this.filter = filter;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
        final DCommand command = new SiriusCommand(domain, null);
        command.getTasks().add(new FilterUpdateTask(diagram, filter, activate));

        // This DDiagramSynchronizer.refesh is useless
        // command.getTasks().add(new RefreshDElementTask((DDiagram)
        // designerElement));
        command.setLabel(activate ? MessageFormat.format(Messages.ChangeFilterActivation_activateFilter, filter.getName())
                : MessageFormat.format(Messages.ChangeFilterActivation_deactivateFilter, filter.getName()));

        domain.getCommandStack().execute(command);
    }

}
