/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.description.spec;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl;

/**
 * Implementation of SiriusImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public class ViewpointSpec extends ViewpointImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.ViewpointImpl#initView(org.eclipse.sirius.viewpoint.DView,
     *      org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void initView(final EObject model) {
        DialectManager.INSTANCE.initRepresentations(this, model, new NullProgressMonitor());
    }
}
