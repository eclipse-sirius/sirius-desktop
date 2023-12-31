/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.logger;

import org.eclipse.sirius.business.internal.logger.RuntimeLoggerManagerImpl;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;

/**
 * Interface to create a Runtime Logger Manager.
 * 
 * @author smonnier
 * 
 */
public interface RuntimeLoggerManager extends RuntimeLogger {

    /**
     * Singleton instance of the logger manager.
     */
    RuntimeLoggerManager INSTANCE = RuntimeLoggerManagerImpl.init();

    /**
     * Extension point ID.
     */
    String ID = "org.eclipse.sirius.runtimeLogger"; //$NON-NLS-1$

    /**
     * What need to be extended.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Decorate an existing interpreter to provide safe (without exceptions)
     * evaluation methods.
     * 
     * @param interpreter
     *            the interpreter to decorate
     * @return the safe interpreter
     */
    RuntimeLoggerInterpreter decorate(IInterpreter interpreter);
}
