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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * {@link ECrossReferenceAdapter} that provides the capability to resolve all
 * proxy cross reference to a given resource.
 * 
 * @noextend This class is not intended to be subclassed by clients.
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ECrossReferenceAdapterWithUnproxyCapability extends SiriusCrossReferenceAdapterImpl {
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

        /**
         * Check if the proxyMap is null or not. Since change in
         * ECrossReferenceAdapter (bugzilla 400891), the proxyMap is no longer
         * used if the resolve() method returns true. In this case, we must
         * iterate on all crossReferences to retrieve corresponding proxies.
         * 
         * @return
         */
        public boolean isNullMapProxy() {
            return proxyMap == null;
        }

        /**
         * Get all proxy {@link EObject EObjects} that have URI corresponding to
         * the <code>resourceURI</code>. Warning: this map is computed at each
         * call and can be costly.
         * 
         * @param resourceURI
         *            The URI of the resource for which we want to get the proxy
         *            EObjects.
         * @return map of proxies with the URI as key.
         */
        public Map<URI, List<EObject>> getProxiesOf(URI resourceURI) {
            Map<URI, List<EObject>> result = Maps.newHashMap();
            if (resourceURI != null) {
                for (Iterator<EObject> iterator = keySet().iterator(); iterator.hasNext(); /* */) {
                    EObject eObject = iterator.next();
                    if (eObject.eIsProxy()) {
                        URI keyURI = EcoreUtil.getURI(eObject);
                        if (resourceURI.equals(keyURI.trimFragment())) {
                            List<EObject> correspondingEObjects = result.get(keyURI);
                            if (correspondingEObjects == null) {
                                correspondingEObjects = Lists.newArrayList();
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
            return new BasicEList<EStructuralFeature.Setting>() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Object[] newData(int capacity) {
                    return new EStructuralFeature.Setting[capacity];
                }

                @Override
                public boolean add(EStructuralFeature.Setting setting) {
                    if (!isSettingTargets) {
                        EObject eObject = setting.getEObject();
                        EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
                        EStructuralFeature.Setting[] settingData = (EStructuralFeature.Setting[]) data;
                        for (int i = 0; i < size; ++i) {
                            EStructuralFeature.Setting containedSetting = settingData[i];
                            if (containedSetting.getEObject() == eObject && containedSetting.getEStructuralFeature() == eStructuralFeature) {
                                return false;
                            }
                        }
                    }
                    addUnique(setting);
                    return true;
                }
            };
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
            // Since change in ECrossReferenceAdapter (bugzilla 400891),
            // the proxyMap is no longer used if the resolve() method
            // returns true. In this case, we must iterate on all
            // crossReferences to retrieve corresponding proxies.
            if (((LocalInverseCrossReferencer) inverseCrossReferencer).isNullMapProxy()) {
                // Get proxies of this resource
                Map<URI, List<EObject>> proxies = ((LocalInverseCrossReferencer) inverseCrossReferencer).getProxiesOf(resourceURI);
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
                    List<EObject> proxies = ((LocalInverseCrossReferencer) inverseCrossReferencer).removeProxies(eObjectURI);
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

}
