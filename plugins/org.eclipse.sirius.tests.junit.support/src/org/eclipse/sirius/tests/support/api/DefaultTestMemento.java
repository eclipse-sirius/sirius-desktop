/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.support.api;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.tests.support.internal.SiriusTestsSupportPlugin;
import org.eclipse.ui.IMemento;

/**
 * Default {@link IMemento} implementation to be used as mock.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DefaultTestMemento implements IMemento {

    private Map<String, String> values = new HashMap<String, String>();

    @Override
    public IMemento createChild(String type) {
        return null;
    }

    @Override
    public IMemento createChild(String type, String id) {
        return null;
    }

    @Override
    public IMemento getChild(String type) {
        return null;
    }

    @Override
    public IMemento[] getChildren() {
        return null;
    }

    @Override
    public IMemento[] getChildren(String type) {
        return null;
    }

    @Override
    public Float getFloat(String key) {
        Float result = null;
        String strValue = values.get(key);
        if (strValue == null) {
            return null;
        }
        try {
            result = new Float(strValue);
        } catch (NumberFormatException e) {
            SiriusTestsSupportPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusTestsSupportPlugin.PLUGIN_ID, "Memento problem - invalid float for key: " + key //$NON-NLS-1$
                    + " value: " + strValue, e)); //$NON-NLS-1$
        }
        return result;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getID() {
        return values.get(TAG_ID);
    }

    @Override
    public Integer getInteger(String key) {
        Integer integer = null;
        String strValue = values.get(key);
        if (strValue == null) {
            return null;
        }
        try {
            integer = new Integer(strValue);
        } catch (NumberFormatException e) {
            SiriusTestsSupportPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, SiriusTestsSupportPlugin.PLUGIN_ID, "Memento problem - invalid integer for key: " + key //$NON-NLS-1$
                    + " value: " + strValue, e)); //$NON-NLS-1$
        }
        return integer;
    }

    @Override
    public String getString(String key) {
        return values.get(key);
    }

    @Override
    public Boolean getBoolean(String key) {
        String attr = values.get(key);
        if (attr == null) {
            return null;
        }
        return Boolean.valueOf(attr);
    }

    @Override
    public String getTextData() {
        return null;
    }

    @Override
    public String[] getAttributeKeys() {
        return values.keySet().toArray(new String[values.size()]);
    }

    @Override
    public void putFloat(String key, float value) {
        values.put(key, String.valueOf(value));
    }

    @Override
    public void putInteger(String key, int value) {
        values.put(key, String.valueOf(value));
    }

    @Override
    public void putMemento(IMemento memento) {
    }

    @Override
    public void putString(String key, String value) {
        values.put(key, value);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        values.put(key, Boolean.toString(value));
    }

    @Override
    public void putTextData(String data) {

    }

}
