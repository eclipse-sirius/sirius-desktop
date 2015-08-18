/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.session.danalysis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.collect.Lists;

/**
 * Service to get analysis selectors.
 * 
 * @author ymortier
 */
public final class DAnalysisSelectorService {

    /**
     * analysis selector extension point ID.
     */
    public static final String ID = "org.eclipse.sirius.analysisSelectorProvider"; //$NON-NLS-1$

    /**
     * Extension point attribute for the analysis selector provider class.
     */
    public static final String CLASS_ATTRIBUTE = "providerClass"; //$NON-NLS-1$

    private static final String DEFAULT_PROVIDER_ID = "org.eclipse.sirius.analysisSelectorProvider.default"; //$NON-NLS-1$

    private static DAnalysisSelectorProvider defaultSiriusProvider;

    private static List<DAnalysisSelectorProvider> customerProviders;

    /**
     * Avoid instantiation.
     */
    private DAnalysisSelectorService() {
        // empty.
    }

    /**
     * Returns the analysis selector to use for the given session or
     * <code>null</code> if none is provided.
     * 
     * @param session
     *            the analysis session.
     * @return the analysis selector to use for the given session or
     *         <code>null</code> if none is provided.
     */
    public static DAnalysisSelector getSelector(final DAnalysisSession session) {
        for (final DAnalysisSelectorProvider provider : DAnalysisSelectorService.getCustomerProviders()) {
            if (provider.provides(session)) {
                return provider.getSelector(session);
            }
        }
        return DAnalysisSelectorService.getDefaultProvider().getSelector(session);
    }

    /**
     * Returns the Sirius default provider (that is used if there is no customer
     * provider).
     * 
     * @return the Sirius default provider
     */
    private static DAnalysisSelectorProvider getDefaultProvider() {
        if (defaultSiriusProvider == null) {
            initializeProviders();
        }
        return defaultSiriusProvider;
    }

    /**
     * Returns all customer providers.
     * 
     * @return all customer providers.
     */
    private static List<DAnalysisSelectorProvider> getCustomerProviders() {
        if (customerProviders == null) {
            initializeProviders();
        }
        return customerProviders;
    }

    /**
     * Initialize the customer providers and the Sirius default provider.
     */
    private static void initializeProviders() {
        Map<String, Collection<DAnalysisSelectorProvider>> providers = EclipseUtil.getExtensionPluginsByKey(DAnalysisSelectorProvider.class, ID, CLASS_ATTRIBUTE, "id"); //$NON-NLS-1$
        Collection<DAnalysisSelectorProvider> defaults = providers.get(DAnalysisSelectorService.DEFAULT_PROVIDER_ID);
        if (defaults == null || defaults.isEmpty()) {
            SiriusPlugin.getDefault().warning(String.format("No default analysis selector provider found at extension point \"%s\", using the DefaultAnalysisSelectorProvider instead.", ID), null);
            defaultSiriusProvider = new DefaultAnalysisSelectorProvider();
        } else if (defaultSiriusProvider == null) {
            defaultSiriusProvider = defaults.iterator().next();
        }
        if (defaults != null && defaults.size() > 1) {
            SiriusPlugin.getDefault().error(String.format("Multiple default analysis selector providers found at extension point \"%s\": took only the first found.", ID), null);
        }
        customerProviders = Lists.newArrayList();
        for (Entry<String, Collection<DAnalysisSelectorProvider>> entry : providers.entrySet()) {
            if (!DAnalysisSelectorService.DEFAULT_PROVIDER_ID.equals(entry.getKey())) {
                customerProviders.addAll(entry.getValue());
            }
        }
    }
}
