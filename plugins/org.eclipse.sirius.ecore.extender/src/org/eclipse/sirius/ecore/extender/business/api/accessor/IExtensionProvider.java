/*******************************************************************************
 * Copyright (c) 2007 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

/**
 * Provides a factory and a package to manipulate an extension.
 * 
 * @author mchauvin
 */
public interface IExtensionProvider {

    /**
     * return a model accessor to get/set values from a model.
     * 
     * @return a model accessor to get/set values from a model.
     */
    ModelAccessor getModelAccessor();
}
