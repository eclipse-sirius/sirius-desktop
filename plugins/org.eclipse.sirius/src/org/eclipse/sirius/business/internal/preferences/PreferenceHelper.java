/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.preferences;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

/**
 * Helper class for Sirius preferences used at the session level.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public final class PreferenceHelper {
    private PreferenceHelper() {
    }

    /**
     * Utility method to get the preference associated to a session.
     * 
     * @param <T>
     *            the type of the expected preference value
     * @param projectScope
     *            the projectScope that may be null
     * @param qualifier
     *            the qualifier
     * @param sessionId
     *            an unique id identifying the session
     * @param preferenceKey
     *            the preference key
     * @param preferenceType
     *            the type of the expected preference value
     * @return the preference value
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPreference(IScopeContext projectScope, String qualifier, String sessionId, String preferenceKey, Class<T> preferenceType) {
        IScopeContext[] contexts;
        contexts = new IScopeContext[] { projectScope, InstanceScope.INSTANCE, ConfigurationScope.INSTANCE, DefaultScope.INSTANCE };

        String value = null;

        // The qualifier of the ProjectScope is suffixed with the session uid.
        if (contexts[0] != null) {
            value = getValueFromScope(contexts[0], qualifier + sessionId, preferenceKey);
        }
        if (value == null) {
            for (int i = 1; i < contexts.length; ++i) {
                value = getValueFromScope(contexts[i], qualifier, preferenceKey);
                if (value != null) {
                    break;
                }
            }
        }

        if (value != null) {
            if (preferenceType.equals(Boolean.class)) {
                return (T) Boolean.valueOf(value);
            }
        }
        return null;
    }

    private static String getValueFromScope(IScopeContext scope, String qualifier, String preferenceKey) {
        String value = scope.getNode(qualifier).get(preferenceKey, null);
        if (value != null) {
            value = value.trim();
            if (!value.isEmpty()) {
                return value;
            }
        }
        return null;
    }
}
