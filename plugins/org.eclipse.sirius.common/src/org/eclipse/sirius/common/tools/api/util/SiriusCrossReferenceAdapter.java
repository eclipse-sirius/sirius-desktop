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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.common.tools.internal.util.FastInverseCrossReferencesList;

/**
 * Specific {@link ECrossReferenceAdapter} which resolve proxy ability can be disabled. All
 * {@link ECrossReferenceAdapter} used for Sirius should extend this adapter in place of {@link ECrossReferenceAdapter}
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
        return new InverseCrossReferencer() {
            private static final long serialVersionUID = 1L;

            @Override
            protected Collection<EStructuralFeature.Setting> newCollection() {
                return new FastInverseCrossReferencesList(() -> !SiriusCrossReferenceAdapter.this.settingTargets || SiriusCrossReferenceAdapter.this.resolve());
            }
        };
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
