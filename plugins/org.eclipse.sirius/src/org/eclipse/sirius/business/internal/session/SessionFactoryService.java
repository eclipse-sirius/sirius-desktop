/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.session.factory.SessionFactoryDescriptor;
import org.eclipse.sirius.business.internal.session.factory.SessionFactoryRegistry;

/**
 * Service class to get the most priority {@link SessionFactory}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class SessionFactoryService {

    /**
     * Singleton of {@link SessionFactoryService}.
     */
    public static final SessionFactoryService INSTANCE = new SessionFactoryService();

    /**
     * Get a default {@link SessionFactory} to create Session. Compute the first
     * contribution which is not overridden by another extension
     * 
     * @return a default SessionFactory
     */
    public SessionFactory getSessionFactory() {
        SessionFactory result = null;
        List<SessionFactoryDescriptor> uiSessionFactoryDescriptors = SessionFactoryRegistry.getRegisteredExtensions();
        if (!uiSessionFactoryDescriptors.isEmpty()) {
            SessionFactoryDescriptor sessionFactoryDescriptor = getFirstMostOverrider(uiSessionFactoryDescriptors);
            if (sessionFactoryDescriptor != null) {
                result = sessionFactoryDescriptor.getSessionFactory();
            }
        }
        return result;
    }

    /**
     * Get the first {@link SessionFactoryDescriptor} of extension list or the
     * the most overriding extension.
     * 
     * @param sessionFactoryDescriptors
     * 
     * @return
     */
    private SessionFactoryDescriptor getFirstMostOverrider(List<SessionFactoryDescriptor> sessionFactoryDescriptors) {
        List<String> overriddenSessionFactoryIDs = new ArrayList<String>();
        SessionFactoryDescriptor firstMostOverridingSessionFactoryDescriptor = null;
        Iterator<SessionFactoryDescriptor> descriptorsIterator = sessionFactoryDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Recovers all overridden EditingDomainFactoryDescriptor
            firstMostOverridingSessionFactoryDescriptor = descriptorsIterator.next();
            String overrideValue = firstMostOverridingSessionFactoryDescriptor.getOverrideValue();
            if (overrideValue != null) {
                overriddenSessionFactoryIDs.add(overrideValue);
            }
        }
        descriptorsIterator = sessionFactoryDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Find the first SessionFactoryDescriptor that is not
            // overridden
            firstMostOverridingSessionFactoryDescriptor = descriptorsIterator.next();
            if (!overriddenSessionFactoryIDs.contains(firstMostOverridingSessionFactoryDescriptor.getId())) {
                return firstMostOverridingSessionFactoryDescriptor;
            }
        }

        return firstMostOverridingSessionFactoryDescriptor;
    }
}
