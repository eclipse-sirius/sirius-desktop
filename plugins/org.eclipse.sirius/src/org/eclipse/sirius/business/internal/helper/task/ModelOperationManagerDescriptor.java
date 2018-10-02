/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.business.internal.helper.task;

import org.eclipse.sirius.business.api.helper.task.IModelOperationManager;

/**
 * Descriptor for the contribution.
 * 
 * @author sbegaudeau
 */
public class ModelOperationManagerDescriptor {

    /**
     * Name of the task extension point's tag "class" attribute.
     */
    public static final String MODEL_OPERATION_MANAGER_ID_ATTRIBUTE = "id"; //$NON-NLS-1$

    /**
     * Name of the task extension point's tag "class" attribute.
     */
    public static final String MODEL_OPERATION_MANAGER_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * The identifier of the manager.
     */
    private String id;

    /**
     * The manager.
     */
    private IModelOperationManager modelOperationManager;

    /**
     * The constructor.
     * 
     * @param id
     *            Theidentifier
     * @param modelOperationManager
     *            The manager
     */
    public ModelOperationManagerDescriptor(String id, IModelOperationManager modelOperationManager) {
        this.id = id;
        this.modelOperationManager = modelOperationManager;
    }

    public String getId() {
        return this.id;
    }

    public IModelOperationManager getModelOperationManager() {
        return this.modelOperationManager;
    }
}
