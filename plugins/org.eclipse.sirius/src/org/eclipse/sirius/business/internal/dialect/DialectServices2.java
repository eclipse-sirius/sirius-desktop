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
package org.eclipse.sirius.business.internal.dialect;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.sirius.business.api.dialect.DialectServices;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * The service provided by this interface should be quickly added in the API
 * {@link DialectServices} but only after the implementation by all the
 * dialects.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public interface DialectServices2 extends DialectServices {
    /**
     * Performs a refresh only for elements impacted by the given notifications
     * list.
     * 
     * @param representation
     *            representation to refresh.
     * @param notifications
     *            the notifications that triggered this refresh.
     * @param monitor
     *            to monitor the refresh operation.
     */
    void refreshImpactedElements(DRepresentation representation, Collection<Notification> notifications, IProgressMonitor monitor);
}
