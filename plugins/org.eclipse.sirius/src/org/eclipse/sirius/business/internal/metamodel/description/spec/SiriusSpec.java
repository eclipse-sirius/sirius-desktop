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
import org.eclipse.sirius.description.impl.SiriusImpl;

/**
 * Implementation of SiriusImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public class SiriusSpec extends SiriusImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.description.impl.SiriusImpl#initView(org.eclipse.sirius.DView,
     *      org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void initView(final EObject model) {
        DialectManager.INSTANCE.initRepresentations(this, model, new NullProgressMonitor());
    }

}
