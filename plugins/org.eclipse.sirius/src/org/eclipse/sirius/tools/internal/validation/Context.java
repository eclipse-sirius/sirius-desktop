/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
