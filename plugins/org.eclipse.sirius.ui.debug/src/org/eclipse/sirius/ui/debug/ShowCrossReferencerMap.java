/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.collect.Lists;

/**
 * Show the content of the internal inverse cross reference map.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
final class ShowCrossReferencerMap implements Runnable {

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
                Option<Object> inverseCrossReferencer = ReflectionHelper.getFieldValueWithoutException(crossReferencer, "inverseCrossReferencer");
                if (inverseCrossReferencer.some()) {
                    dump((Map<EObject, Collection<EStructuralFeature.Setting>>) inverseCrossReferencer.get());
                }
            }
        }
    }

    /**
     * Dump the cross reference map.
     * 
     * @param crossReferenceMap
     *            the cross reference map
     */
    private void dump(Map<EObject, Collection<EStructuralFeature.Setting>> crossReferenceMap) {
        int numberOfSetting = 0;
        int numberOfSettingNotEList = 0;

        LinkedHashMap<EClass, Map<EStructuralFeature, Integer>> featuresMap = new LinkedHashMap<EClass, Map<EStructuralFeature, Integer>>();

        for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : crossReferenceMap.entrySet()) {
            EObject eObject = entry.getKey();
            EClass eClass = eObject.eClass();

            Map<EStructuralFeature, Integer> features = featuresMap.get(eClass);

            Collection<EStructuralFeature.Setting> collection = entry.getValue();
            numberOfSetting += collection.size();

            Iterator<EStructuralFeature.Setting> j = collection.iterator();
            while (j.hasNext()) {
                EStructuralFeature.Setting setting = j.next();

                if (!(setting instanceof EList)) {
                    // An EList is contains by an EObject
                    numberOfSettingNotEList++;
                }

                EStructuralFeature feature = setting.getEStructuralFeature();

                if (features == null) {
                    features = new HashMap<EStructuralFeature, Integer>();
                    featuresMap.put(eClass, features);
                }

                Integer instances = features.get(feature);
                if (instances == null) {
                    instances = 0;
                }

                features.put(feature, instances + 1);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Number of entry: ");
        result.append(crossReferenceMap.size());
        result.append("\r\nNumber of Setting: ");
        result.append(numberOfSetting);
        result.append("\r\nNumber of Setting not EList: ");
        result.append(numberOfSettingNotEList);
        result.append("\r\n---------------------------\r\n\r\n");

        List<Entry<EClass, Map<EStructuralFeature, Integer>>> entries = Lists.newArrayList(featuresMap.entrySet());
        Collections.sort(entries, new Comparator<Entry<EClass, Map<EStructuralFeature, Integer>>>() {

            @Override
            public int compare(Entry<EClass, Map<EStructuralFeature, Integer>> left, Entry<EClass, Map<EStructuralFeature, Integer>> right) {
                int leftTotal = 0;
                for (Integer l : left.getValue().values()) {
                    leftTotal += l;
                }

                int rightTotal = 0;
                for (Integer r : right.getValue().values()) {
                    rightTotal += r;
                }

                return rightTotal - leftTotal;
            }

        });

        for (Map.Entry<EClass, Map<EStructuralFeature, Integer>> entry : entries) {
            EClass eClass = entry.getKey();
            String eClassName = eClass.getInstanceClassName();

            result.append(eClassName);

            Map<EStructuralFeature, Integer> collection = entry.getValue();
            numberOfSetting += collection.size();

            result.append(" <-\r\n    ");
            int totalInstances = 0;
            Iterator<Entry<EStructuralFeature, Integer>> j = collection.entrySet().iterator();
            while (j.hasNext()) {
                Entry<EStructuralFeature, Integer> featureInstance = j.next();
                EStructuralFeature feature = featureInstance.getKey();
                int instances = featureInstance.getValue();
                totalInstances += instances;

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
                    if (totalInstances != instances) {
                        result.append("\r\n    ");
                        result.append("[");
                        result.append(totalInstances);
                        result.append(" total refs]");
                    }
                    result.append("\r\n\r\n");
                }
            }
        }

        view.setText(result.toString());
    }
}
