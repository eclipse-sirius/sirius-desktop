/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.accessor.IExtenderProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.IMetamodelExtender;

/**
 * Provider for the Ecore intrinsic data.
 * 
 * @author cbrun
 * 
 */
public class EcoreExtenderProvider implements IExtenderProvider {
    /**
     * {@inheritDoc}
     */
    public IMetamodelExtender getExtender(final ResourceSet set) {
        return new EcoreIntrinsicExtender();
    }

    /**
     * {@inheritDoc}
     */
    public boolean provides(final ResourceSet set) {
        return true;
    }

}
