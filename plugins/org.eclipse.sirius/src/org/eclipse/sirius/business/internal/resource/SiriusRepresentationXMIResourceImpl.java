/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * A specific implementation for *.srm. Sirius Representation Model contains one or several representations referenced
 * by a DRepresentationDescriptor. That allows to serialize representations in separate files than the main *.aird. That
 * will make the lazy loading possible.
 * 
 * @author Florian Barbin
 *
 */
public class SiriusRepresentationXMIResourceImpl extends XMIResourceImpl {

    private static final String PLATFORM_SCHEME = "platform"; //$NON-NLS-1$

    private static boolean activateTrace;
    static {
        activateTrace = Boolean.getBoolean(System.getProperty("activate_trace_getLoadedRepresentations", Boolean.FALSE.toString())); //$NON-NLS-1$
    }

    /**
     * Default constructor.
     * 
     * @param uri
     *            the resource URI.
     */
    public SiriusRepresentationXMIResourceImpl(URI uri) {
        super(uri);
    }

    @Override
    protected boolean useUUIDs() {
        return true;
    }

    @Override
    protected boolean useIDAttributes() {
        return false;
    }

    @Override
    public void load(Map<?, ?> options) throws IOException {
        if (activateTrace) {
            Thread.dumpStack();
        } else {
            super.load(options);
        }
    }

    @Override
    protected XMLHelper createXMLHelper() {
        return new XMIHelperImpl(this) {

            /**
             * Extracted from org.eclipse.gmf.runtime.emf.core.resources.GMFHelper.deresolve(URI).
             * 
             * {@inheritDoc}
             * 
             * @see org.eclipse.gmf.runtime.emf.core.resources.GMFHelper.deresolve(URI)
             * @see org.eclipse.emf.ecore.xmi.XMLHelper#deresolve(org.eclipse.emf.common.util.URI)
             */
            @Override
            public URI deresolve(URI uri) {

                // if this both target and container are within a platform resource and
                // projects or plugins are different then do not deresolve.
                // CHECKSTYLE:OFF
                if (((PLATFORM_SCHEME.equals(uri.scheme())) && (PLATFORM_SCHEME.equals(resourceURI.scheme()))) && ((uri.segmentCount() > 2) && (resourceURI.segmentCount() > 2))
                        && ((!uri.segments()[0].equals(resourceURI.segments()[0])) || (!uri.segments()[1].equals(resourceURI.segments()[1])))) {
                    // CHECKSTYLE:ON
                    return uri;
                }
                return super.deresolve(uri);
            }
        };
    }

}
