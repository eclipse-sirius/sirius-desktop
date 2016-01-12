/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

public class SiriusToolServices {
    public void run(EObject eObject, Object selection, String initialCommandUri) {
        Session session = new EObjectQuery(eObject).getSession();
        ModelAccessor modelAccessor = session.getModelAccessor();
        TaskHelper taskHelper = new TaskHelper(modelAccessor, new NoUICallback());

        ModelOperation modelOperation = null;

        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
        for (Viewpoint viewpoint : selectedViewpoints) {
            Resource eResource = viewpoint.eResource();
            if (eResource != null && URI.createURI(initialCommandUri).trimFragment().equals(eResource.getURI())) {
                EObject modelOperationEObject = eResource.getEObject(URI.createURI(initialCommandUri).fragment());
                if (modelOperationEObject instanceof InitialOperation) {
                    modelOperation = ((InitialOperation) modelOperationEObject).getFirstModelOperations();
                }
            }
        }

        if (modelOperation != null) {
            ICommandTask task = taskHelper.buildTaskFromModelOperation(eObject, modelOperation);
            SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain(), "SiriusToolServices#run");
            command.getTasks().add(task);
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
    }
}
