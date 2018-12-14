/*******************************************************************************
 * Copyright (c) 2015-2019 Obeo.
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
package org.eclipse.sirius.ui.debug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Show the content of the internal inverse cross reference map.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
final class ShowCrossReferencerMap implements Runnable {

    /**
     * Store inverse references.
     */
    private static class InverseReferences {
        private Map<EStructuralFeature, Integer> features = new HashMap<EStructuralFeature, Integer>();

        private int totalReferences;

        public void addInstance(EStructuralFeature feature) {
            Integer instances = features.get(feature);
            if (instances == null) {
                instances = 0;
            }

            features.put(feature, instances + 1);
            totalReferences++;
        }

        public int getTotalReferences() {
            return totalReferences;
        }

        public Set<Entry<EStructuralFeature, Integer>> getFeatures() {
            return features.entrySet();
        }
    }

    /**
     * Sort map entry by value.
     */
    private static class InverseReferencesComparator implements Comparator<Entry<EClass, InverseReferences>> {

        @Override
        public int compare(Entry<EClass, InverseReferences> left, Entry<EClass, InverseReferences> right) {
            return right.getValue().getTotalReferences() - left.getValue().getTotalReferences();
        }
    }

    /** Sirius debug view */
    private final SiriusDebugView view;

    /**
     * @param view
     */
    ShowCrossReferencerMap(SiriusDebugView view) {
        this.view = view;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        EObject current = view.getCurrentEObject();
        if (current != null) {
            Session session = SessionManager.INSTANCE.getSession(current);
            if (session == null && current instanceof DSemanticDecorator) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) current).getTarget());
            }

            if (session != null) {
                ECrossReferenceAdapter crossReferencer = session.getSemanticCrossReferencer();
                Collection<Resource> semanticResources = session.getSemanticResources();
                Optional<Object> inverseCrossReferencer = ReflectionHelper.getFieldValueWithoutException(crossReferencer, "inverseCrossReferencer");
                if (inverseCrossReferencer.isPresent()) {
                    dumpCrossReferencer(semanticResources, (Map<EObject, Collection<EStructuralFeature.Setting>>) inverseCrossReferencer.get());
                }
            }
        }
    }

    /**
     * Dump the cross reference map.
     * 
     * @param semanticResources
     *            list of semantic resource
     * 
     * @param crossReferenceMap
     *            the cross reference map
     */
    private void dumpCrossReferencer(Collection<Resource> semanticResources, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferenceMap) {
        int totalInverseReferences = 0;
        int totalInversReferencesNotEList = 0;

        Map<EClass, InverseReferences> referencesToSemantic = new HashMap<EClass, InverseReferences>();
        Map<EClass, InverseReferences> referencesToNonSemantic = new HashMap<EClass, InverseReferences>();

        for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : crossReferenceMap.entrySet()) {
            EObject eObject = entry.getKey();
            Resource resource = eObject.eResource();

            Map<EClass, InverseReferences> inverseReferences = semanticResources.contains(resource) ? referencesToSemantic : referencesToNonSemantic;

            EClass eClass = eObject.eClass();
            InverseReferences features = inverseReferences.get(eClass);

            Collection<EStructuralFeature.Setting> collection = entry.getValue();
            totalInverseReferences += collection.size();

            Iterator<EStructuralFeature.Setting> j = collection.iterator();
            while (j.hasNext()) {
                EStructuralFeature.Setting setting = j.next();

                if (!(setting instanceof EList)) {
                    // An EList is contains by an EObject
                    totalInversReferencesNotEList++;
                }

                EStructuralFeature feature = setting.getEStructuralFeature();

                if (features == null) {
                    features = new InverseReferences();
                    inverseReferences.put(eClass, features);
                }

                features.addInstance(feature);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Number of entry: ");
        result.append(crossReferenceMap.size());
        result.append("\r\nNumber of inverse reference: ");
        result.append(totalInverseReferences);
        result.append("\r\nNumber of inverse reference not EList: ");
        result.append(totalInversReferencesNotEList);
        result.append("\r\n\r\n");

        result.append("-------------------------------------\r\n");
        result.append("- References to non semantic element:\r\n");
        result.append("-------------------------------------\r\n\r\n");

        dumpInverseReferences(result, referencesToNonSemantic);

        result.append("---------------------------------\r\n");
        result.append("- References to semantic element:\r\n");
        result.append("---------------------------------\r\n\r\n");
        dumpInverseReferences(result, referencesToSemantic);

        view.setText(result.toString());
    }

    /**
     * Dump some inverses reference.
     * 
     * @param result
     *            result to fill
     * 
     * @param inverseReferences
     *            inverse references to dump
     */
    private void dumpInverseReferences(StringBuilder result, Map<EClass, InverseReferences> inverseRerefences) {
        List<Entry<EClass, InverseReferences>> entries = new ArrayList<Entry<EClass, InverseReferences>>(inverseRerefences.entrySet());
        Collections.sort(entries, new InverseReferencesComparator());

        for (Map.Entry<EClass, InverseReferences> entry : entries) {
            EClass eClass = entry.getKey();
            String eClassName = eClass.getInstanceClassName();

            result.append(eClassName);

            InverseReferences collection = entry.getValue();

            result.append(" <-\r\n    ");
            Iterator<Entry<EStructuralFeature, Integer>> j = collection.getFeatures().iterator();
            while (j.hasNext()) {
                Entry<EStructuralFeature, Integer> featureInstance = j.next();
                EStructuralFeature feature = featureInstance.getKey();
                int instances = featureInstance.getValue();

                result.append(feature.getEContainingClass().getInstanceClassName());
                result.append(".");
                result.append(feature.getName());

                if (feature.isMany()) {
                    result.append("*");
                }

                result.append(" [");
                result.append(instances);
                result.append(" refs]");

                if (j.hasNext()) {
                    result.append("\r\n    ");
                } else {
                    if (collection.getTotalReferences() != instances) {
                        result.append("\r\n    ");
                        result.append("[");
                        result.append(collection.getTotalReferences());
                        result.append(" total refs]");
                    }
                    result.append("\r\n\r\n");
                }
            }
        }

    }

}
