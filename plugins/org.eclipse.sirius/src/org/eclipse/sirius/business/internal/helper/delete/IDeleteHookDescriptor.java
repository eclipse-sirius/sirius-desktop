/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.delete;

import org.eclipse.sirius.business.api.delete.IDeleteHook;

/**
 * Describes a {@link IDeleteHook} contribution.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public interface IDeleteHookDescriptor {

    /**
     * Id of the deleteHook extension point's tag "id" attribute.
     */
    String DELETE_HOOK_ID_ATTRIBUTE = "id"; //$NON-NLS-1$

    /**
     * Name of the deleteHook extension point's tag "class" attribute.
     */
    String DELETE_HOOK_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * The unique identifier of the extension {@link IDeleteHook} extension.
     * 
     * @return the unique identifier of the extension {@link IDeleteHook}
     *         extension
     */
    String getId();

    /**
     * The concrete implementation (i.e. IDeleteHook) of the extension.
     * 
     * @return the concrete implementation (i.e. IDeleteHook) of the extension
     */
    IDeleteHook getIDeleteHook();

}
