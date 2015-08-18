/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Parser to get representationsFile current version.
 * 
 * @author fbarbin
 * 
 */
public class RepresentationsFileVersionSAXParser extends AbstractVersionSAXParser {

    /* In Representations files, the DAnalysis element is versioned. */
    private static final String VERSIONED_ELEMENT_QUALIFIED_NAME = ViewpointPackage.eINSTANCE.getNsPrefix() + ":" + ViewpointPackage.eINSTANCE.getDAnalysis().getName(); //$NON-NLS-1$

    /**
     * Constructor.
     * 
     * @param airdResourceUri
     *            the representation resource to parse version.
     */
    public RepresentationsFileVersionSAXParser(URI airdResourceUri) {
        super(airdResourceUri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getVersionedElementQualifiedName() {
        return VERSIONED_ELEMENT_QUALIFIED_NAME;
    }
}
