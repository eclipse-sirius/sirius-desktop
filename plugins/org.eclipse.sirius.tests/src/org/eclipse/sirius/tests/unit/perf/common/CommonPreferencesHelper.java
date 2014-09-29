/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.perf.common;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.junit.After;
import org.junit.Before;

/**
 * Junit4 helper to handle the consistency of common preferences during a set of
 * test case.
 */
public class CommonPreferencesHelper {
    protected final IPreferenceStore store = SiriusTransPlugin.getPlugin().getPreferenceStore();

    protected boolean defaultIsGroupEnable;

    protected boolean isGroupEnable;

    protected boolean defaultIsGroupByContainingFeature;

    protected boolean isGroupByContainingFeature;

    protected int defaultGroupSize;

    protected int groupSize;

    protected int defaultGroupTrigger;

    protected int groupTrigger;

    @Before
    public void setUp() {

        // store pref values
        this.defaultIsGroupEnable = store.getDefaultBoolean(CommonPreferencesConstants.PREF_GROUP_ENABLE);
        this.isGroupEnable = store.getBoolean(CommonPreferencesConstants.PREF_GROUP_ENABLE);

        this.defaultIsGroupByContainingFeature = store.getDefaultBoolean(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE);
        this.isGroupByContainingFeature = store.getBoolean(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE);

        this.defaultGroupSize = store.getDefaultInt(CommonPreferencesConstants.PREF_GROUP_SIZE);
        this.groupSize = store.getInt(CommonPreferencesConstants.PREF_GROUP_SIZE);

        this.defaultGroupTrigger = store.getDefaultInt(CommonPreferencesConstants.PREF_GROUP_TRIGGER);
        this.groupTrigger = store.getInt(CommonPreferencesConstants.PREF_GROUP_TRIGGER);
    }

    @After
    public void tearDown() {
        // Restore pref values.
        setPrefGroupEnable(this.isGroupEnable);
        setPrefGroupByContainingFeature(this.isGroupByContainingFeature);
        setPrefGroupSize(this.groupSize);
        setPrefGroupTrigger(this.groupTrigger);
    }

    public void setPrefGroupEnable(boolean value) {
        store.setValue(CommonPreferencesConstants.PREF_GROUP_ENABLE, value);
    }

    public void setPrefGroupByContainingFeature(boolean value) {
        store.setValue(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, value);
    }

    public void setPrefGroupSize(int value) {
        store.setValue(CommonPreferencesConstants.PREF_GROUP_SIZE, value);
    }

    public void setPrefGroupTrigger(int value) {
        store.setValue(CommonPreferencesConstants.PREF_GROUP_TRIGGER, value);
    }
}
