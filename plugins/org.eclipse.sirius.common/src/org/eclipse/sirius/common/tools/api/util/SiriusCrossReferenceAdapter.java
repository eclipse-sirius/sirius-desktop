/*******************************************************************************
 * Copyright (c) 2015, 2019 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.internal.util.FastInverseCrossReferencesList;

/**
 * Specific {@link ECrossReferenceAdapter} whose resolve proxy ability can be disabled. It also provides the capability
 * to resolve all proxy cross references for a given resource. All {@link ECrossReferenceAdapter} used for Sirius
 * should extend this adapter in place of {@link ECrossReferenceAdapter}
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusCrossReferenceAdapter extends ECrossReferenceAdapter {
    /**
     * to keep track if the setting targets have been captured yet.
     * 
     */
    protected boolean isSettingTargets;

    /**
     * Tell if the resolution of the proxy is enabled or not.
     */
    protected boolean resolveProxyEnabled = true;

    /**
     * This is a white list of features that must be cross referenced.
     */
    private Collection<EReference> featureToBeCrossReferencedWhiteList = new HashSet<EReference>();

    /**
     * InverseCrossReferencer to allow access to {@link #removeProxies(URI)} in '@link InternalCrossReferencer}.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    protected class SiriusInverseCrossReferencer extends InverseCrossReferencer {
        private static final long serialVersionUID = 1L;

        /**
         * Check if the proxyMap is null or not. Since change in ECrossReferenceAdapter (bugzilla 400891), the proxyMap
         * is no longer used if the resolve() method returns true. In this case, we must iterate on all crossReferences
         * to retrieve corresponding proxies.
         * 
         * @return true if the proxy map is null.
         */
        public boolean isNullMapProxy() {
            return proxyMap == null;
        }

        /**
         * Get all proxy {@link EObject EObjects} that have URI corresponding to the <code>resourceURI</code>. Warning:
         * this map is computed at each call and can be costly.
         * 
         * @param resourceURI
         *            The URI of the resource for which we want to get the proxy EObjects.
         * @return map of proxies with the URI as key.
         */
        public Map<URI, List<EObject>> getProxiesOf(URI resourceURI) {
            Map<URI, List<EObject>> result = new HashMap<>();
            if (resourceURI != null) {
                for (Iterator<EObject> iterator = keySet().iterator(); iterator.hasNext(); /* */) {
                    EObject eObject = iterator.next();
                    if (eObject.eIsProxy()) {
                        URI keyURI = EcoreUtil.getURI(eObject);
                        if (resourceURI.equals(keyURI.trimFragment())) {
                            List<EObject> correspondingEObjects = result.get(keyURI);
                            if (correspondingEObjects == null) {
                                correspondingEObjects = new ArrayList<>();
                            }
                            correspondingEObjects.add(eObject);
                            result.put(keyURI, correspondingEObjects);
                        }
                    }
                }
            }
            return result;
        }

        @Override
        protected Collection<EStructuralFeature.Setting> newCollection() {
            return new FastInverseCrossReferencesList(() -> !SiriusCrossReferenceAdapter.this.settingTargets || SiriusCrossReferenceAdapter.this.resolve());
        }

        @Override
        protected List<EObject> removeProxies(URI uri) {
            return super.removeProxies(uri);
        }
    }

    /**
     * Look at all EObjects of the specified resource and resolve proxy cross reference that reference these
     * EObjects.<BR>
     * A part of {@link #resolveAll(EObject)} has been duplicated to avoid the time consumption of accessing to
     * resourceURI for each objects of the same resource.
     * 
     * @param resource
     *            Each cross reference pointing to a proxy of this <code>resource</code> will be resolved.
     */
    public void resolveProxyCrossReferences(Resource resource) {
        if (resource != null) {
            URI resourceURI = resource.getURI();
            if (resourceURI != null) {
                ResourceSet theResourceSet = resource.getResourceSet();
                if (theResourceSet != null) {
                    resourceURI = theResourceSet.getURIConverter().normalize(resourceURI);
                }
            }
            final Iterator<EObject> it = resource.getAllContents();
            // Since change in ECrossReferenceAdapter (bugzilla 400891),
            // the proxyMap is no longer used if the resolve() method
            // returns true. In this case, we must iterate on all
            // crossReferences to retrieve corresponding proxies.
            if (((SiriusInverseCrossReferencer) inverseCrossReferencer).isNullMapProxy()) {
                // Get proxies of this resource
                Map<URI, List<EObject>> proxies = ((SiriusInverseCrossReferencer) inverseCrossReferencer).getProxiesOf(resourceURI);
                // Iterate on all EObject of the resource to find proxy cross
                // references and resolve them.
                while (it.hasNext()) {
                    EObject eObject = it.next();
                    URI eObjectURI = getURI(eObject, resource, resourceURI);
                    resolveProxyCrossReferences(eObject, resource, proxies.get(eObjectURI));
                }
            } else {
                // Code used for Juno
                while (it.hasNext()) {
                    EObject eObject = it.next();
                    URI eObjectURI = getURI(eObject, resource, resourceURI);
                    List<EObject> proxies = ((SiriusInverseCrossReferencer) inverseCrossReferencer).removeProxies(eObjectURI);
                    resolveProxyCrossReferences(eObject, resource, proxies);
                }
            }
        }
    }

    /**
     * Get URI of this <code>eObject</code>.
     * 
     * @param eObject
     *            Concerned {@link EObject}
     * @param resource
     *            Resource containing the <code>eObject</code>
     * @param resourceURI
     *            URI of <code>resource</code>
     * 
     * @return the URI of this <code>eObject</code>.
     */
    private URI getURI(EObject eObject, Resource resource, URI resourceURI) {
        URI eObjectURI;
        if (resourceURI != null) {
            eObjectURI = resourceURI.appendFragment(resource.getURIFragment(eObject));
        } else {
            eObjectURI = URI.createHierarchicalURI(null, null, resource.getURIFragment(eObject));
        }
        return eObjectURI;
    }

    /**
     * Resolve the proxy cross references of the current <code>eObject</code>.
     * 
     * @param eObject
     *            Current EObject (not a proxy)
     * @param resource
     *            Resource containing the <code>eObject</code>
     * @param proxies
     *            Corresponding proxies of eObject
     */
    private void resolveProxyCrossReferences(EObject eObject, Resource resource, List<EObject> proxies) {
        if (proxies != null) {
            for (int i = 0, size = proxies.size(); i < size; ++i) {
                EObject proxy = proxies.get(i);
                for (EStructuralFeature.Setting setting : getInverseReferences(proxy, false)) {
                    resolveProxy(resource, eObject, proxy, setting);
                }
            }
        }
    }

    /**
     * Disable the resolution of the proxy.
     */
    public void disableResolveProxy() {
        resolveProxyEnabled = false;
    }

    /**
     * Enable the resolution of the proxy.
     */
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
        return new SiriusInverseCrossReferencer();
    }

    @Override
    protected void addAdapter(Notifier notifier) {
        List<Adapter> eAdapters = notifier.eAdapters();
        if (!eAdapters.contains(this)) {
            boolean oldSettingTargets = isSettingTargets;
            try {
                isSettingTargets = true;
                eAdapters.add(this);
            } finally {
                isSettingTargets = oldSettingTargets;
            }
        }
    }

    /**
     * Set a white list of features that must be cross referenced.
     * 
     * @param featureToBeCrossReferencedWhiteList
     *            The feature list that must not be null.
     */
    public void setFeatureToBeCrossReferencedWhiteList(Collection<EReference> featureToBeCrossReferencedWhiteList) {
        this.featureToBeCrossReferencedWhiteList = featureToBeCrossReferencedWhiteList;
    }

    /**
     * This override allows to crossReference a white list of EReference.
     */
    @Override
    protected boolean isIncluded(EReference eReference) {
        return (eReference.getEOpposite() == null && !eReference.isDerived()) || featureToBeCrossReferencedWhiteList.contains(eReference);
    }

    public boolean isResolveProxyEnabled() {
        return resolveProxyEnabled;
    }
}
