/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;

/**
 * Common behavior for arrange all tests.
 * 
 * @author dlecan
 */
public abstract class AbstractArrangeAllTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Open a transaction to pin the element.
     * 
     * @param diagramElement
     *            the {@link DDiagramElement} to pin
     */
    protected void pinDiagramElement(final DDiagramElement diagramElement) {
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagramElement);

        final List<DDiagramElement> selection = new ArrayList<DDiagramElement>(1);
        selection.add(diagramElement);
        editingDomain.getCommandStack().execute(new PinElementsCommand(selection));
    }

    /**
     * Open a transaction to unpin the element.
     * 
     * @param diagramElement
     *            the {@link DDiagramElement} to pin
     */
    protected void unpinDiagramElement(final DDiagramElement diagramElement) {
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagramElement);

        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {
                new PinHelper().markAsUnpinned(diagramElement);
            }
        });
    }

}
