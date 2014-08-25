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

import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.junit.After;
import org.junit.Before;

/**
 * Junit4 helper to handle the consistency of common preferences during a set of
 * test case.
 */
public class CommonPreferencesHelper {

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
        this.defaultIsGroupEnable = SiriusTransPlugin.getPlugin().getPreferenceStore().getDefaultBoolean(CommonPreferencesConstants.PREF_GROUP_ENABLE);
        this.isGroupEnable = SiriusTransPlugin.getPlugin().getPreferenceStore().getBoolean(CommonPreferencesConstants.PREF_GROUP_ENABLE);

        this.defaultIsGroupByContainingFeature = SiriusTransPlugin.getPlugin().getPreferenceStore().getDefaultBoolean(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE);
        this.isGroupByContainingFeature = SiriusTransPlugin.getPlugin().getPreferenceStore().getBoolean(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE);

        this.defaultGroupSize = SiriusTransPlugin.getPlugin().getPreferenceStore().getDefaultInt(CommonPreferencesConstants.PREF_GROUP_SIZE);
        this.groupSize = SiriusTransPlugin.getPlugin().getPreferenceStore().getInt(CommonPreferencesConstants.PREF_GROUP_SIZE);

        this.defaultGroupTrigger = SiriusTransPlugin.getPlugin().getPreferenceStore().getDefaultInt(CommonPreferencesConstants.PREF_GROUP_TRIGGER);
        this.groupTrigger = SiriusTransPlugin.getPlugin().getPreferenceStore().getInt(CommonPreferencesConstants.PREF_GROUP_TRIGGER);
    }

    @After
    public void tearDown() {
        // Restore pref values.
        setPrefGroupEnable(this.defaultIsGroupEnable, this.isGroupEnable);
        setPrefGroupByContainingFeature(this.defaultIsGroupByContainingFeature, this.isGroupByContainingFeature);
        setPrefGroupSize(this.defaultGroupSize, this.groupSize);
        setPrefGroupTrigger(this.defaultGroupTrigger, this.groupTrigger);
    }

    public void setPrefGroupEnable(boolean value) {
        setPrefGroupEnable(value, value);
    }

    public void setPrefGroupEnable(boolean defaultValue, boolean value) {
        SiriusTransPlugin.getPlugin().getPreferenceStore().setDefault(CommonPreferencesConstants.PREF_GROUP_ENABLE, defaultValue);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_ENABLE, value);
    }

    public void setPrefGroupByContainingFeature(boolean value) {
        setPrefGroupByContainingFeature(value, value);
    }

    public void setPrefGroupByContainingFeature(boolean defaultValue, boolean value) {
        SiriusTransPlugin.getPlugin().getPreferenceStore().setDefault(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, defaultValue);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, value);
    }

    public void setPrefGroupSize(int value) {
        setPrefGroupSize(value, value);
    }

    public void setPrefGroupSize(int defaultValue, int value) {
        SiriusTransPlugin.getPlugin().getPreferenceStore().setDefault(CommonPreferencesConstants.PREF_GROUP_SIZE, defaultValue);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_SIZE, value);
    }

    public void setPrefGroupTrigger(int value) {
        setPrefGroupTrigger(value, value);
    }

    public void setPrefGroupTrigger(int defaultValue, int value) {
        SiriusTransPlugin.getPlugin().getPreferenceStore().setDefault(CommonPreferencesConstants.PREF_GROUP_TRIGGER, defaultValue);
        SiriusTransPlugin.getPlugin().getPreferenceStore().setValue(CommonPreferencesConstants.PREF_GROUP_TRIGGER, value);
    }
}
