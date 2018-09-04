/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.backend.internal.services.workflow;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * This class contains various services provided by the Sirius UI Properties
 * bundle to the interpreter.
 *
 * @author sbegaudeau
 */
public class SiriusToolServices {

    /**
     * Executes the operation with the given URI.
     *
     * @param eObject
     *            The EObject to use as the operation's context
     * @param initialCommandUri
     *            the URI of the operation to execute
     * @return the model element on which the tool was executed.
     */
    public EObject executeOperation(EObject eObject, String initialCommandUri) {
        if (!eObject.eIsProxy()) {
            Session session = new EObjectQuery(eObject).getSession();
            if (session != null) {
                ModelOperation modelOperation = findModelOperation(initialCommandUri, session);
                if (modelOperation != null) {
                    ModelAccessor modelAccessor = session.getModelAccessor();
                    ICommandTask task = new TaskHelper(modelAccessor, SiriusPlugin.getDefault().getUiCallback()).buildTaskFromModelOperation(eObject, modelOperation);
                    SiriusCommand command = new SiriusCommand(session.getTransactionalEditingDomain(), "SiriusToolServices#executeOperation"); //$NON-NLS-1$
                    command.getTasks().add(task);
                    try {
                        if (command.canExecute()) {
                            command.execute();
                        }
                    } catch (@SuppressWarnings("unused") OperationCanceledException cancel) {
                        // A tool (possibly via a Java service) can request the
                        // cancellation of its execution. This is
                        // handled as a rollback by EMF Transaction, but the
                        // exception should not propagate above this
                        // point: it's a signal for requesting
                        // cancellation/rollback, which at this point is done,
                        // but
                        // not an error that the caller should be aware of.
                    } finally {
                        command.dispose();
                    }
                }
            }
        }
        return eObject;
    }

    /**
     * Resolves the actual {@link ModelOperation} to execute given its URI.
     *
     * @param initialCommandUri
     *            the URI of the operation to search for.
     * @param session
     *            the Sirius session which determines the scope to search into.
     * @return the {@link ModelOperation} instance found at the specified URI,
     *         either in one of the VSMs for which at least one Viewpoint is
     *         currently enabled in the session, or from the default ruleset, or
     *         <code>null</code> if no matching operation could be located.
     */
    private ModelOperation findModelOperation(String initialCommandUri, Session session) {
        URI commandResourceURI = URI.createURI(initialCommandUri).trimFragment();
        for (Resource res : getResourcesInScope(session)) {
            if (commandResourceURI.equals(res.getURI())) {
                EObject modelOperationEObject = res.getEObject(URI.createURI(initialCommandUri).fragment());
                if (modelOperationEObject instanceof InitialOperation) {
                    return ((InitialOperation) modelOperationEObject).getFirstModelOperations();
                }
            }
        }
        return null;
    }

    /**
     * Returns all the (VSM-like) resources in which to search for the
     * {@link ModelOperation} to execute.
     *
     * @param session
     *            the Sirius session.
     * @return all the resources in which to look for the ModelOperation, in
     *         order of preference.
     */
    private Set<Resource> getResourcesInScope(Session session) {
        Set<Resource> result = new LinkedHashSet<>();
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
        for (Viewpoint viewpoint : selectedViewpoints) {
            Resource eResource = viewpoint.eResource();
            if (eResource != null) {
                result.add(eResource);
            }
        }
        return result;
    }

}
