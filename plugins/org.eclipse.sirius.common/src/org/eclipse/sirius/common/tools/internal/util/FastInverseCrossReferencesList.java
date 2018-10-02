/**
 * Copyright (c) 2005-2017 IBM Corporation, CEA, and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *   Christian W. Damus (CEA) - bug 433027
 *   Obeo - extracted internal BasicEList subclass
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

// CHECKSTYLE:OFF
/**
 * A {@link BasicEList} optimized for use in inverse cross-referencers. It switches from a plain list to a map for
 * storing inverse references when the number of settings increases beyond a threshold. Using a map allows for very fast
 * containment testing, which can bring spectacular performance improvements when cross-referencing models where some
 * elements are referenced a lot.
 * <p>
 * Extracted from org.eclipse.emf.ecore.util.ECrossReferenceAdapter.InverseCrossReferencer.newCollection() added in EMF
 * 2.13 by http://git.eclipse.org/c/emf/org.eclipse.emf.git/commit/?id=0448d995991f08fd5764f1efe134f3a5395593cc.
 */
public final class FastInverseCrossReferencesList extends BasicEList<EStructuralFeature.Setting> {
    private static final long serialVersionUID = 1L;

    private static final int THRESHOLD = 100;

    private Map<EObject, Object> map;

    private BooleanSupplier checkUnique;

    public FastInverseCrossReferencesList(BooleanSupplier checkUnique) {
        this.checkUnique = checkUnique;
    }

    @Override
    protected Object[] newData(int capacity) {
        return new EStructuralFeature.Setting[capacity];
    }

    @Override
    protected void didAdd(int index, EStructuralFeature.Setting setting) {
        if (map != null) {
            EObject eObject = setting.getEObject();
            EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
            Object object = map.get(eObject);
            if (object == null) {
                map.put(eObject, eStructuralFeature);
            } else if (object instanceof Object[]) {
                Object[] oldFeatures = (Object[]) object;
                Object[] newFeatures = new Object[oldFeatures.length + 1];
                System.arraycopy(oldFeatures, 0, newFeatures, 0, oldFeatures.length);
                newFeatures[oldFeatures.length] = eStructuralFeature;
                map.put(eObject, newFeatures);
            } else {
                Object[] newFeatures = new Object[2];
                newFeatures[0] = object;
                newFeatures[1] = eStructuralFeature;
                map.put(eObject, newFeatures);
            }
        }
    }

    @Override
    protected void didRemove(int index, EStructuralFeature.Setting setting) {
        if (map != null) {
            if (size < THRESHOLD / 2) {
                map = null;
            } else {
                EObject eObject = setting.getEObject();
                Object object = map.get(eObject);
                if (object instanceof Object[]) {
                    Object[] oldFeatures = (Object[]) object;
                    EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
                    if (oldFeatures.length == 2) {
                        map.put(eObject, oldFeatures[0] == eStructuralFeature ? oldFeatures[1] : oldFeatures[0]);
                    } else {
                        Object[] newFeatures = new Object[oldFeatures.length - 1];
                        for (int i = 0; i < oldFeatures.length; ++i) {
                            Object oldFeature = oldFeatures[i];
                            if (oldFeature == eStructuralFeature) {
                                System.arraycopy(oldFeatures, i + 1, newFeatures, i, oldFeatures.length - i - 1);
                                break;
                            } else {
                                newFeatures[i] = oldFeatures[i];
                            }
                        }
                        map.put(eObject, newFeatures);
                    }
                } else {
                    map.remove(eObject);
                }
            }
        }
    }

    @Override
    public boolean add(EStructuralFeature.Setting setting) {
        if (size > 0 && checkUnique.getAsBoolean()) {
            EObject eObject = setting.getEObject();
            if (size > THRESHOLD) {
                if (map == null) {
                    map = new HashMap<EObject, Object>();
                    EStructuralFeature.Setting[] settingData = (EStructuralFeature.Setting[]) data;
                    for (int i = 0; i < size; ++i) {
                        didAdd(i, settingData[i]);
                    }
                }

                Object object = map.get(eObject);
                if (object != null) {
                    EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
                    if (object == eStructuralFeature) {
                        return false;
                    } else if (object instanceof Object[]) {
                        Object[] features = (Object[]) object;
                        for (int i = 0; i < features.length; ++i) {
                            if (features[i] == eStructuralFeature) {
                                return false;
                            }
                        }
                    }
                }
            } else {
                EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
                EStructuralFeature.Setting[] settingData = (EStructuralFeature.Setting[]) data;
                for (int i = 0; i < size; ++i) {
                    EStructuralFeature.Setting containedSetting = settingData[i];
                    if (containedSetting.getEObject() == eObject && containedSetting.getEStructuralFeature() == eStructuralFeature) {
                        return false;
                    }
                }
            }
        }
        addUnique(setting);
        return true;
    }
}
// CHECKSTYLE:ON
