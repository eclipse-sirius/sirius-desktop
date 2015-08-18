/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.dialect;

import org.eclipse.sirius.business.api.dialect.Dialect;
import org.eclipse.sirius.business.api.dialect.DialectServices;

/**
 * Table dialect.
 * 
 * @author cbrun
 */
public class TableDialect implements Dialect {
    private DialectServices services;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.Dialect#getName()
     */
    public String getName() {
        return "table"; //$NON-NLS-1$
    }

    /**
     * 
     * {@inheritDoc}
     */
    public DialectServices getServices() {
        if (services == null) {
            services = new TableDialectServices();
        }
        return services;
    }

}
