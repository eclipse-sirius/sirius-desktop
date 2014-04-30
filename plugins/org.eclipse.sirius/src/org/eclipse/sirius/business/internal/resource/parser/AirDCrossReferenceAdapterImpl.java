/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource.parser;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;

/**
 * This class overrides CrossReferenceAdapter to have it installed only on the
 * AirDResource.
 * 
 * @author smonnier
 * 
 */
public class AirDCrossReferenceAdapterImpl extends CrossReferenceAdapter implements AirDCrossReferenceAdapter {
    boolean resolve = true;

    /**
     * This method has been overridden to filter only the AirDResource and its
     * elements.
     * 
     * {@inheritDoc}
     */
    @Override
    public void selfAdapt(Notification notification) {
        if (!notification.isTouch()
                && (notification.getNotifier() instanceof AirDResourceImpl || (notification.getNotifier() instanceof EObject && ((EObject) notification.getNotifier()).eResource() instanceof AirDResourceImpl))) {
            super.selfAdapt(notification);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter#resolve()
     */
    @Override
    protected boolean resolve() {
        if (resolve) {
            return super.resolve();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter#disableResolve()
     */
    public void disableResolve() {
        resolve = false;

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter#enableResolve()
     */
    public void enableResolve() {
        resolve = true;
    }
}
