/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.Set;

/**
 * Represent a polymorphic service.
 * 
 * @author fbarbin
 * 
 */
public interface IPolymorphicService extends IService {

    /**
     * Provides the set of implementers of this Polymorphic service.
     * 
     * @return the set of {@link IMonomorphicService}
     */
    Set<IMonomorphicService> getImplementers();
}