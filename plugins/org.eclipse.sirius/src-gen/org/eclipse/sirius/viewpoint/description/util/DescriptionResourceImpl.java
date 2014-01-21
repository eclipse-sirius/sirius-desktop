/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.viewpoint.description.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMXMIHelper;
import org.eclipse.sirius.ext.base.Option;

/**
 * <!-- begin-user-doc --> The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.util.DescriptionResourceFactoryImpl
 * @not-generated
 */
public class DescriptionResourceImpl extends XMIResourceImpl {

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param uri
     *            the URI of the new resource.
     * @not-generated
     */
    protected DescriptionResourceImpl(URI uri) {
        super(uri);
    }

    @Override
    protected XMLHelper createXMLHelper() {
        return new VSMXMIHelper(this);
    }

    /**
     * Override to migrate fragment if necessary (when a reference has been
     * renamed) before getting the EObject.
     */
    @Override
    public EObject getEObject(String uriFragment) {
        Option<String> optionalRewrittenFragment = VSMMigrationService.getInstance().getNewFragment(uriFragment);
        if (optionalRewrittenFragment.some()) {
            return getEObject(optionalRewrittenFragment.get());
        } else {
            return super.getEObject(uriFragment);
        }
    }

} // DescriptionResourceImpl
