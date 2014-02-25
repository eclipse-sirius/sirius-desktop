/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;

/**
 * A {@link RecordingCommand} to execute
 * {@link ViewPropertiesSynchronizer#synchronizeViewProperties(org.eclipse.gmf.runtime.notation.View)}
 * on a specifed {@link View}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SynchronizeGMFViewPropertiesCommand extends RecordingCommand {

    private View view;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which execute this
     *            command
     * @param view
     *            the {@link View} from which update properties according to
     *            DDiagramElement's style properties
     */
    public SynchronizeGMFViewPropertiesCommand(TransactionalEditingDomain domain, View view) {
        super(domain);
        this.view = view;
    }

    @Override
    protected void doExecute() {
        new ViewPropertiesSynchronizer().synchronizeViewProperties(view);
    }

}
