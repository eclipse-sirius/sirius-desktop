/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.tools.internal.ecore.IndexedSettingsEList;

/**
 * Specific {@link ECrossReferenceAdapter} which resolve proxy ability can be
 * disabled. All {@link ECrossReferenceAdapter} used for Sirius should extend
 * this adapter in place of {@link ECrossReferenceAdapter}
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusCrossReferenceAdapterImpl extends ECrossReferenceAdapter implements SiriusCrossReferenceAdapter {

    /**
     * Tell if the resolution of the proxy is enabled or not.
     */
    private boolean resolveProxyEnabled = true;

    /**
     * Disable the resolution of the proxy.
     */
    @Override
    public void disableResolveProxy() {
        resolveProxyEnabled = false;
    }

    /**
     * Enable the resolution of the proxy.
     */
    @Override
    public void enableResolveProxy() {
        resolveProxyEnabled = true;
    }

    @Override
    protected boolean resolve() {
        if (resolveProxyEnabled) {
            return super.resolve();
        }
        return false;
    }
    
    @Override
    protected InverseCrossReferencer createInverseCrossReferencer() {
        return new InverseCrossReferencer() {
            @Override
            protected Collection<Setting> newCollection() {
                return new IndexedSettingsEList();
            }
        };
    }
}
