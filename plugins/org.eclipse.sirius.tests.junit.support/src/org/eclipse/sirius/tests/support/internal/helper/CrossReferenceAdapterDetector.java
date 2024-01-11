/*******************************************************************************
 * Copyright (c) 2015, 2024 Obeo.
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
package org.eclipse.sirius.tests.support.internal.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.junit.Assert;

/**
 * Sirius specific test checks.
 * 
 * @author mporhel
 */
public final class CrossReferenceAdapterDetector {
    /**
     * The name of the system property which can be used to control whether or
     * not to enable the check for the presence of CrossReferenceAdapters. Tha
     * system property should be "true" or "false".
     */
    public static final String SKIP_CROSS_REFERENCE_ADAPTER_DETECTION = "org.eclipse.sirius.tests.skipCrossReferenceAdapterDetection";

    private final Collection<URI> resourcesWithDetectedCrossReferenceAdapter = new ArrayList<>();

    /**
     * Check that there is no session whose resources have a
     * CrossReferencerAdatper.
     */
    public void checkNoCrossReferenceAdapter() {
        for (Session session : SessionManager.INSTANCE.getSessions()) {
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            if (domain != null) {
                checkNoCrossReferenceAdapter(domain.getResourceSet());
            }
        }
    }

    /**
     * Check that the given resource set its resources do not have a
     * CrossReferencerAdatper.
     * 
     * @param resourceSet
     *            the resource set to check.
     */
    public void checkNoCrossReferenceAdapter(ResourceSet resourceSet) {
        if (hasCrossReferenceAdapter(resourceSet)) {
            // Add AirdResources
            // @formatter:off
            List<AirdResource> airdResources = resourceSet.getResources().stream()
                                                          .filter(AirdResource.class::isInstance)
                                                          .map(AirdResource.class::cast)
                                                          .collect(Collectors.toList());
            // @formatter:on
            if (!airdResources.isEmpty()) {
                for (AirdResource airdResource : airdResources) {
                    resourcesWithDetectedCrossReferenceAdapter.add(airdResource.getURI());
                }
            } else {
                resourcesWithDetectedCrossReferenceAdapter.add(resourceSet.getResources().get(0).getURI());
            }

        } else {
            for (Resource resource : resourceSet.getResources()) {
                if (hasCrossReferenceAdapter(resource)) {
                    resourcesWithDetectedCrossReferenceAdapter.add(resource.getURI());
                }
            }
        }
    }

    /**
     * Check that the given Notifier does not have a CrossReferencerAdatper
     * CrossReferencerAdatper.
     * 
     * @param notifier
     *            the notifier to check.
     * @return true if a CrossReferencerAdapter has been found.
     */
    public static boolean hasCrossReferenceAdapter(Notifier notifier) {
        return notifier.eAdapters().stream().anyMatch(org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter.class::isInstance);
    }

    /**
     * Tests whether the environment is configured to skip the
     * CrossReferenceAdapter detection reporting.
     * 
     * @return <code>true</code> if the environment is setup to skip the
     *         CrossReferenceAdapter detection reporting.
     */
    public boolean shouldSkipCrossReferenceAdapterDetectionReport() {
        return "true".equals(System.getProperty(CrossReferenceAdapterDetector.SKIP_CROSS_REFERENCE_ADAPTER_DETECTION));
    }

    /**
     * Asserts that no
     * {@link org.eclipse.gmf.runtime.emf.core.util.CrossReferenceAdapter} has
     * been found.
     * 
     * Will do nothing if {@link shouldSkipCrossReferenceAdapterDetectionReport}
     * return true.
     */
    public void assertNoCrossReferenceAdapterFound() {
        if (!shouldSkipCrossReferenceAdapterDetectionReport() && !resourcesWithDetectedCrossReferenceAdapter.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (URI uri : resourcesWithDetectedCrossReferenceAdapter) {
                sb.append("This resource should not have a CrossReferenceAdapter: ").append(uri.path()).append("\n");
            }
            Assert.fail(sb.toString());
        }
    }
}
