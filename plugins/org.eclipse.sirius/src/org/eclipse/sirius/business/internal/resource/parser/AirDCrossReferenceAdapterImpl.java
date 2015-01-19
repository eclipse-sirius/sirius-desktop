/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource.parser;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;

/**
 * This class overrides CrossReferenceAdapter to have it installed only on the
 * AirDResource.
 * 
 * @author smonnier
 */
public class AirDCrossReferenceAdapterImpl extends CrossReferenceAdapter implements SiriusCrossReferenceAdapter, AirDCrossReferenceAdapter {
    boolean resolveProxy = true;

    /**
     * Overridden to have this {@link SiriusCrossReferenceAdapter} installed
     * only on aird resource.
     * 
     * @param notifier
     *            a model element of the ResourceSet
     */
    @Override
    protected void addAdapter(Notifier notifier) {
        if (notifier instanceof Resource) {
            Resource resource = (Resource) notifier;
            if (!new ResourceQuery(resource).isRepresentationsResource()) {
                return;
            }
        }
        super.addAdapter(notifier);
    }

    @Override
    protected boolean resolve() {
        if (resolveProxy) {
            return super.resolve();
        }
        return false;
    }

    @Override
    public void disableResolveProxy() {
        resolveProxy = false;
    }

    @Override
    public void enableResolveProxy() {
        resolveProxy = true;
    }
}
