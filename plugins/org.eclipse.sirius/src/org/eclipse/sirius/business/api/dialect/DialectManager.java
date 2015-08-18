/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect;

import org.eclipse.sirius.business.internal.dialect.DialectManagerImpl;

/**
 * Instance managing the dialects.
 * 
 * @author cbrun
 * 
 */
public interface DialectManager extends DialectServices {
    /**
     * Singleton instance of the dialect manager.
     */
    DialectManager INSTANCE = DialectManagerImpl.init();

    /**
     * Dialect manager extension point ID.
     */
    String ID = "org.eclipse.sirius.dialect"; //$NON-NLS-1$

    /**
     * Extension point attribute for the dialect class.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Enable a new dialect.
     * 
     * @param dialect
     *            dialect to enable.
     */
    void enableDialect(Dialect dialect);

    /**
     * Disable a dialect. If it was not enabled : do nothing.
     * 
     * @param dialect
     *            dialect to disable.
     */
    void disableDialect(Dialect dialect);
}
