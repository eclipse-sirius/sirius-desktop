/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect;

/**
 * Represents a Sirius dialect, i.e. a type of representation supported by
 * Sirius. For example, diagrams and tables are two different dialects.
 * Dialects are registered using the extension point
 * <code>org.eclipse.sirius.dialect</code>.
 * <p>
 * This API is for dialect operations which may depend on the state of the
 * dialect. State-less operations are accessible through the associated
 * {@link DialectServices}.
 * 
 * @author cbrun
 */
public interface Dialect {
    /**
     * Identifier for the dialect type.
     * 
     * @return the dialect name.
     */
    String getName();

    /**
     * return the provided dialect services.
     * 
     * @return the provided dialect services.
     */
    DialectServices getServices();
}
