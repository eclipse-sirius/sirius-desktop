/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.refresh;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;

/**
 * This interface allows to tell when time stamp of the representation must NOT be updated.
 * 
 * @author lfasani
 *
 */
public interface RepresentationTimeStampInformationSupplier {

    /**
     * Decide if the time is updated or not according to changes done in the transaction.<br/>
     * Implementors should analyze the whole notifications to decide if the time stamp will be left unchanged.
     * 
     * @param notifications
     *            notifications received by the core Sirius precommit listener that updates the time stamp
     *            (DRepresentationDescriptor.changeId)
     * @return true if the time must NOT be updated.
     */
    boolean preventTimeStampFromBeingUpdated(List<Notification> notifications);

}
