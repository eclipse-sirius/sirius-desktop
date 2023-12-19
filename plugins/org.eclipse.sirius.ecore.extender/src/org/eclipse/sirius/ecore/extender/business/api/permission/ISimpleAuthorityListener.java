/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

@FunctionalInterface
public interface ISimpleAuthorityListener extends IAuthorityListener {
    @Override
    default void notifyIsLocked(EObject instance) {
        onChange();
    }

    @Override
    default void notifyIsReleased(EObject instance) {
        onChange();
    }

    @Override
    default void notifyIsLocked(Collection<EObject> instances) {
        onChange();
    }

    @Override
    default void notifyIsReleased(Collection<EObject> instances) {
        onChange();
    }

    /**
     * Called when any element are locked or released.
     */
    void onChange();
}
