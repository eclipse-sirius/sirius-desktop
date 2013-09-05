/*******************************************************************************
 * Copyright (c) 2007 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
