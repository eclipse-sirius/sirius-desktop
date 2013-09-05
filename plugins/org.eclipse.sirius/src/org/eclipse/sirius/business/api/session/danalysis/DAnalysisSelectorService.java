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

import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.SiriusPlugin;

/**
 * Service to get analysis selectors.
 * 
 * @author ymortier
 */
public final class DAnalysisSelectorService {

    /**
     * analysis selector extension point ID.
     */
    public static final String ID = "org.eclipse.sirius.analysisSelectorProvider";

    /**
     * Extension point attribute for the analysis selector provider class.
     */
    public static final String CLASS_ATTRIBUTE = "providerClass";

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
     * Returns the Sirius default provider (that is used if there is no
     * customer provider).
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
        // Load the default provider
        Predicate<String> isDefaultSiriusProvider = Predicates.equalTo("org.eclipse.sirius.analysisSelectorProvider.default");
        for (DAnalysisSelectorProvider potentialDefaultProvider : EclipseUtil.getExtensionPlugins(DAnalysisSelectorProvider.class, ID, CLASS_ATTRIBUTE, "id", isDefaultSiriusProvider)) {
            if (defaultSiriusProvider == null) {
                defaultSiriusProvider = potentialDefaultProvider;
            } else {
                SiriusPlugin.getDefault().error(
                        "Sirius must provide one (and only one) analysis selector provider (through extension point \"org.eclipse.sirius.analysisSelectorProvider\").", null);
            }
        }

        // Load customers providers
        customerProviders = EclipseUtil.getExtensionPlugins(DAnalysisSelectorProvider.class, ID, CLASS_ATTRIBUTE, "id", Predicates.not(isDefaultSiriusProvider));

        if (defaultSiriusProvider == null) {
            SiriusPlugin.getDefault().error("Sirius must provide one analysis selector provider (through extension point \"org.eclipse.sirius.analysisSelectorProvider\").", null);
        }
    }
}
