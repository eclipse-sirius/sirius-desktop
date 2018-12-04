/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.services.common.api;

import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * A set of services to handle Sirius representation I/O.
 * 
 * @author hmarchadour
 */
public class RepresentationServices {

    /**
     * Returns an optional containing the representation description computed from the given viewpoint identifier and
     * representation identifier.
     * 
     * @param object
     *            The identifier of the viewpoint as an Object
     * @return An optional with the viewpoint found or an empty optional
     */
    public static Optional<RepresentationDescription> representationDescriptionFromIdentifiers(Session session, String viewpointIdentifier, String representationIdentifier) {
        return ViewpointServices.viewpointFromIdentifier(session, viewpointIdentifier).flatMap(
                viewpoint -> viewpoint.getOwnedRepresentations().stream().filter(representationDescription -> representationIdentifier.equals(representationDescription.getName())).findFirst());
    }

    /**
     * Creates the representation using a recording command.
     * 
     * @param session
     *            The session
     * @param representationDescription
     *            The description of the representation
     * @param name
     *            The name of the representation
     * @param eObject
     *            The EObject used as the root of the representation
     */
    public static void createRepresentation(Session session, RepresentationDescription representationDescription, String name, EObject eObject) {
        RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.createRepresentation(name, eObject, representationDescription, session, new NullProgressMonitor());
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
    }
}
