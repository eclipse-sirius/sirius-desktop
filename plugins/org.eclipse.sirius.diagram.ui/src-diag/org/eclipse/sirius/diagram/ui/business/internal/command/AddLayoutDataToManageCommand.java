/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.command;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.AbstractLayoutData;

/**
 * Command to add a {@link AbstractLayoutData} to the
 * {@link SiriusLayoutDataManager}.
 * 
 * @author edugueperoux
 */
public class AddLayoutDataToManageCommand extends AbstractCommand {

    private AbstractLayoutData abstractLayoutData;

    /**
     * Default constructor.
     * 
     * @param abstractLayoutData
     *            {@link AbstractLayoutData} to store in
     *            {@link SiriusLayoutDataManager}
     */
    public AddLayoutDataToManageCommand(AbstractLayoutData abstractLayoutData) {
        this.abstractLayoutData = abstractLayoutData;
    }

    /**
     * This command can always been executed.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        SiriusLayoutDataManager.INSTANCE.addData(abstractLayoutData);
    }

    /**
     * Overridden to not nothing.
     */
    public void redo() {

    }

}
