/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.List;

/**
 * A service which correspond exactly to a single Java method.
 * 
 * @author fbarbin
 * 
 */
public interface IMonomorphicService extends IService {

    /**
     * Provides the list of parameters types.
     * 
     * @return a list of {@link String}.
     */
    List<String> getParametersTypes();
}
