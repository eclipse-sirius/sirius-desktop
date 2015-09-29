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
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

/**
 * Specific {@link ECrossReferenceAdapter} which resolve proxy ability can be
 * disabled. All {@link ECrossReferenceAdapter} used for Sirius should extend
 * this adapter in place of {@link ECrossReferenceAdapter}
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SiriusCrossReferenceAdapterImpl extends ECrossReferenceAdapter implements SiriusCrossReferenceAdapter {
    /**
     * to keep track if the setting targets have been captured yet.
     * 
     */
    protected boolean isSettingTargets;

    /**
     * Tell if the resolution of the proxy is enabled or not.
     */
    private boolean resolveProxyEnabled = true;

    /**
     * Disable the resolution of the proxy.
     */
    @Override
    public void disableResolveProxy() {
        resolveProxyEnabled = false;
    }

    /**
     * Enable the resolution of the proxy.
     */
    @Override
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
        return new InverseCrossReferencer() {
            private static final long serialVersionUID = 1L;

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
        };
    }

    /**
     * {@inheritDoc}
     */
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
}
