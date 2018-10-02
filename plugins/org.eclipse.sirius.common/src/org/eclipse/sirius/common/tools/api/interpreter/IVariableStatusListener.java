/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Map;

/**
 * This listener may be used with an {@link IInterpreter} to be notified when
 * the variable status change.
 * 
 * @author cbrun
 * 
 */
public interface IVariableStatusListener {
    /**
     * Called when a change concerning the variables appears.
     * 
     * @param variables
     *            : the new variables status
     */
    void notifyChanged(Map<?, ?> variables);
}
