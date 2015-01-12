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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

/**
 * {@link ECrossReferenceAdapter} that provides the capability to resolve all
 * proxy cross reference to a given resource.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ECrossReferenceAdapterWithUnproxyCapability extends ECrossReferenceAdapter {
    /**
     * InverseCrossReferencer to allow access to {@link #removeProxies(URI)} in
     * '@link InternalCrossReferencer}.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private class LocalInverseCrossReferencer extends InverseCrossReferencer {
        private static final long serialVersionUID = 1L;

        @Override
        protected List<EObject> removeProxies(URI uri) {
            return super.removeProxies(uri);
        }
    }

    /**
     * Default constructor that uses its own {@link InverseCrossReferencer}.
     */
    public ECrossReferenceAdapterWithUnproxyCapability() {
        inverseCrossReferencer = new LocalInverseCrossReferencer();
    }

    /**
     * Look at all EObjects of the specified resource and resolve proxy cross
     * reference that reference these EObjects.<BR>
     * A part of {@link #resolveAll(EObject)} has been duplicated to avoid the
     * time consumption of accessing to resourceURI for each objects of the same
     * resource.
     * 
     * @param resource
     *            Each cross reference pointing to a proxy of this
     *            <code>resource</code> will be resolved.
     */
    public void resolveProxyCrossReferences(Resource resource) {
        if (resource != null) {
            URI resourceURI = resource.getURI();
            if (resourceURI != null) {
                ResourceSet resourceSet = resource.getResourceSet();
                if (resourceSet != null) {
                    resourceURI = resourceSet.getURIConverter().normalize(resourceURI);
                }
            }
            final Iterator<EObject> it = resource.getAllContents();
            while (it.hasNext()) {
                EObject eObject = it.next();
                URI eObjectURI;
                if (resourceURI != null) {
                    eObjectURI = resourceURI.appendFragment(resource.getURIFragment(eObject));
                } else {
                    eObjectURI = URI.createHierarchicalURI(null, null, resource.getURIFragment(eObject));
                }
                List<EObject> proxies = ((LocalInverseCrossReferencer) inverseCrossReferencer).removeProxies(eObjectURI);
                if (proxies != null) {
                    for (int i = 0, size = proxies.size(); i < size; ++i) {
                        EObject proxy = proxies.get(i);
                        for (EStructuralFeature.Setting setting : getInverseReferences(proxy, false)) {
                            resolveProxy(resource, eObject, proxy, setting);
                        }
                    }
                }
            }
        }
    }
}
