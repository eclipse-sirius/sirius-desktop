/*******************************************************************************
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.ecore;

import java.util.BitSet;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;

import com.google.common.collect.Maps;

/**
 * A specialization of BasicEList<Setting> which speed up the
 * addition in the list by using a index to quickly determine if addUnique() can
 * be used.
 * 
 * @author cedric
 *
 */
public class IndexedSettingsEList extends BasicEList<Setting> {

    private static final long serialVersionUID = 1L;

    /*
     * Number of Setting beyond which we trigger the creation
     * of the knowns bitsets to speed up lookups when adding a new setting.
     */
    private static final int KNOWNS_MAP_THRESHOLD = 500;

    /*
     * Map of bitset used to speed up containment checks, for a given EObject
     * and EStructuralFeature ID (used as the bitset key) we know if the feature
     * is already known or not faster instead of having to iterate over all the
     * Settings in the data field. This map is only created
     * when the size of the list hits a fixed threshold as to avoid a
     * prohibitive heap memory usage for a small speedup.
     */
    private Map<EObject, BitSet> knowns;

    @Override
    protected Object[] newData(int capacity) {
        return new Setting[capacity];
    }

    @Override
    public boolean add(Setting setting) {
        EObject eObject = setting.getEObject();
        if (knowns != null) {
            /*
             * if knowns is not null then the map can be used for lookups.
             */
            boolean isKnownFeature = isKnown(setting);
            boolean toBeAdded = true;
            if (isKnownFeature) {
                EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
                Setting[] settingData = (Setting[]) data;
                for (int i = 0; i < size; ++i) {
                    Setting containedSetting = settingData[i];
                    if (containedSetting.getEObject() == eObject && containedSetting.getEStructuralFeature() == eStructuralFeature) {
                        toBeAdded = false;
                        break;
                    }
                }
            }
            if (toBeAdded) {
                addUnique(setting);
            }
            return toBeAdded;
        } else {
            return super.add(setting);
        }
    }

    private boolean isKnown(Setting setting) {
        BitSet knownFeaturesForThisEObject = knowns.get(setting.getEObject());
        if (knownFeaturesForThisEObject != null) {
            return knownFeaturesForThisEObject.get(setting.getEStructuralFeature().getFeatureID());
        }
        return false;
    }

    private void markAsKnown(Setting setting) {
        BitSet knownFeaturesForThisEObject = knowns.get(setting.getEObject());
        if (knownFeaturesForThisEObject == null) {
            knownFeaturesForThisEObject = new BitSet();
            knowns.put(setting.getEObject(), knownFeaturesForThisEObject);
        }
        knownFeaturesForThisEObject.set(setting.getEStructuralFeature().getFeatureID());
    }

    @Override
    protected Setting assign(int index, Setting setting) {
        if (knowns == null && size >= KNOWNS_MAP_THRESHOLD) {
            /*
             * init the knowns maps with the settings we already hold.
             */
            knowns = Maps.newHashMapWithExpectedSize(size);
            Setting[] settingData = (Setting[]) data;
            for (int i = 0; i < size; ++i) {
                Setting containedSetting = settingData[i];
                markAsKnown(containedSetting);
            }
        }
        if (setting != null && knowns != null && setting.getEObject() != null && setting.getEStructuralFeature() != null) {
            markAsKnown(setting);
        }
        return super.assign(index, setting);
    }

    @Override
    public void clear() {
        super.clear();
        if (knowns != null) {
            knowns.clear();
        }
    }

}
