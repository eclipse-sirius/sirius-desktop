/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.properties.core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.properties.AbstractOverrideDescription;

/**
 * Utility class used to provide the {@link AbstractOverrideDescription}.
 * 
 * @author mbats
 */
public class OverridesProvider {
    /**
     * The Sirius session.
     */
    private Session session;

    /**
     * The constructor.
     * 
     * @param session
     *            The Sirius session
     */
    public OverridesProvider(Session session) {
        this.session = session;
    }

    /**
     * Provides the {@link AbstractOverrideDescription} for the given
     * {@link EObject}.
     * 
     * @param eObject
     *            The sirius description
     * @return The collection of the {@link AbstractOverrideDescription}
     *         overriding the given description
     */
    public List<AbstractOverrideDescription> getOverrideDescriptions(EObject eObject) {
        List<AbstractOverrideDescription> descriptions = new ArrayList<>();

        Optional<ECrossReferenceAdapter> optionalCrossReferencer = Optional.ofNullable(session).map(Session::getSemanticCrossReferencer);
        optionalCrossReferencer.ifPresent(crossReferencer -> {
            // @formatter:off
            crossReferencer.getInverseReferences(eObject, true).stream()
                    .filter(setting -> "overrides".equals(setting.getEStructuralFeature().getName())) //$NON-NLS-1$
                    .map(Setting::getEObject)
                    .filter(AbstractOverrideDescription.class::isInstance)
                    .map(AbstractOverrideDescription.class::cast)
                    .forEach(descriptions::add);
            // @formatter:on
        });

        return descriptions.stream().filter(description -> {
            List<Resource> activatedViewpointResources = session.getSelectedViewpoints(true).stream().map(EObject::eResource).collect(Collectors.toList());
            return activatedViewpointResources.contains(description.eResource());
        }).collect(Collectors.toList());
    }
}
