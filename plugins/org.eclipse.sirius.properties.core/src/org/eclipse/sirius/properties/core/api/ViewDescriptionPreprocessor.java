/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

/**
 * Preprocesses the property views description defined in a Sirius VSM which can
 * contain extends and overrides into a "flat" resolved Sirius property views
 * description.
 * 
 * @author flatombe
 * @author mbats
 */
public class ViewDescriptionPreprocessor {

    /**
     * The original view extension description.
     */
    private ViewExtensionDescription originalDescription;

    /**
     * The constructor.
     * 
     * @param viewExtensionDescription
     *            The description of the view extension to preprocess
     */
    public ViewDescriptionPreprocessor(ViewExtensionDescription viewExtensionDescription) {
        this.originalDescription = viewExtensionDescription;
    }

    /**
     * Use the description provided in order to unfold the extends and overrides
     * relations.
     * 
     * @return The {@link ViewExtensionDescription} computed
     */
    public Optional<ViewExtensionDescription> convert() {
        TransformationCache cache = new TransformationCache();

        // Starts the conversion
        Optional<IDescriptionPreprocessor> vedPreprocessor = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessor(originalDescription);
        if (vedPreprocessor.isPresent()) {
            EObject viewExtensionDescription = vedPreprocessor.get().convert(originalDescription, cache);

            // Starts the resolution of the links
            List<IDescriptionLinkResolver> linkResolvers = SiriusPropertiesCorePlugin.getPlugin().getDescriptionPreprocessorLinkResolvers();
            linkResolvers.forEach(linkResolver -> linkResolver.resolve(viewExtensionDescription, cache));

            return Optional.ofNullable((ViewExtensionDescription) viewExtensionDescription);
        }
        return Optional.empty();
    }
}
