/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect;

import org.eclipse.sirius.business.api.dialect.Dialect;
import org.eclipse.sirius.business.api.dialect.DialectServices;

/**
 * The definition of the "Tree" dialect for Sirius.
 * 
 * @author pcdavid
 */
public class TreeDialect implements Dialect {
    private TreeDialectServices services;

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "tree"; //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    public synchronized DialectServices getServices() {
        if (services == null) {
            services = new TreeDialectServices();
        }
        return services;
    }
}
