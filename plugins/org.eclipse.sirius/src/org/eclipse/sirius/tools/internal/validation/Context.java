/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.internal.validation;

import org.eclipse.emf.validation.model.IClientSelector;

/**
 * Selects constraints for the constraint binding when the
 * {@link org.eclipse.emf.validation.examples.general.actions.BatchValidationDelegate}
 * or the {@link LiveValidationContentAdapter} was the entry point into
 * validation.
 * 
 * @author cbrun
 */
public class Context implements IClientSelector {

    /**
     * {@inheritDoc}
     */
    public boolean selects(final Object object) {
        return true;
    }
}
