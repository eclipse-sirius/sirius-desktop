/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

/**
 * Contract for {@link ECrossReferenceAdapter} which resolve proxy ability can
 * be disabled.
 * 
 * @see {@link SiriusCrossReferenceAdapterImpl}
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public interface SiriusCrossReferenceAdapter {

    /**
     * Disable the resolution of the proxy.
     */
    void disableResolveProxy();

    /**
     * Enable the resolution of the proxy.
     */
    void enableResolveProxy();
}
