/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.api.permission;

/**
 * Enum to defined all possibles states of EObject according to locks.
 * 
 * @author edugueperoux
 * @since 0.9.0
 */
public enum LockStatus {

    /**
     * Status to indicate that a EObject is locked by the current Session.
     */
    LOCKED_BY_ME,

    /**
     * Status to indicate that a EObject is locked by another Session of the
     * current Session.
     */
    LOCKED_BY_OTHER,

    /**
     * Status to indicate that a EObject is not locked by a Session.
     */
    NOT_LOCKED

}
