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
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.ViewpointSelector;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A set of services to handle Sirius viewpoint I/O.
 * 
 * @author hmarchadour
 */
public class ViewpointServices {

    /**
     * Returns an optional containing the viewpoint computed from the given object or an empty optional if it could not be
     * found.
     * 
     * @param object
     *            The identifier of the viewpoint as an Object
     * @return An optional with the viewpoint found or an empty optional
     * @deprecated The viewpoint instance returned will not the same as sirius session
     *             https://www.eclipse.org/sirius/doc/developer/Architecture.html#viewpoint_registry. You will need to use
     *             SiriusResourceHelper.getCorrespondingViewpoint to get the expected instance.
     */
    public static Optional<Viewpoint> viewpointFromIdentifier(Object object) {
        // @formatter:off
        return Optional.ofNullable(object)
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .flatMap(viewpointIdentifier -> {
                    return ViewpointRegistry.getInstance().getViewpoints().stream()
                            .filter(viewpoint -> viewpointIdentifier.equals(viewpoint.getName()))
                            .findFirst();
                });
        // @formatter:on
    }

    /**
     * Returns an optional containing the viewpoint computed from the given object or an empty optional if it could not be
     * found.
     * 
     * @param session
     *            the current session
     * @param object
     *            The identifier of the viewpoint as an Object
     * @return An optional with the viewpoint found or an empty optional
     */
    public static Optional<Viewpoint> viewpointFromIdentifier(Session session, Object object) {
        // @formatter:off
        Optional<Viewpoint> viewpointFromIdentifier = viewpointFromIdentifier(object);
        return viewpointFromIdentifier.map(viewpoint -> { 
            Viewpoint rest = SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint);
            return rest;
            });
        // @formatter:on
    }

    public static void activateViewpoint(Session session, Viewpoint viewpoint) {
        RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                new ViewpointSelector(session).selectViewpoint(SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint), false, new NullProgressMonitor());
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
    }
}
