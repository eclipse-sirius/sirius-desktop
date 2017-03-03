/*******************************************************************************
 * Copyright (c) 2015, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.eef.EEFGroupDescription;
import org.eclipse.eef.EEFPageDescription;
import org.eclipse.eef.EEFViewDescription;
import org.eclipse.eef.EefFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.internal.SiriusPropertiesCorePlugin;

/**
 * Interprets the high-level property views description defined in a Sirius VSM
 * into a lower-level EEFViewDescription suitable for the EEF runtime.
 * 
 * @author pcdavid
 */
public class ViewDescriptionConverter {

    private final List<PageDescription> pageDescriptions;

    /**
     * The constructor.
     * 
     * @param pageDescriptions
     *            The description of the pages to convert
     */
    public ViewDescriptionConverter(List<PageDescription> pageDescriptions) {
        this.pageDescriptions = pageDescriptions;
    }

    /**
     * Use the description of the pages provided in order to create an
     * {@link EEFViewDescription}.
     * 
     * @param input
     *            Semantic element
     * 
     * @return The {@link EEFViewDescription} computed
     */
    public EEFViewDescription convert(SiriusInputDescriptor input) {
        EEFViewDescription view = this.createView();
        TransformationCache cache = new TransformationCache();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(IDescriptionConverter.INPUT, input);
        parameters.put(IDescriptionConverter.VIEW, view);

        // Starts the conversion
        pageDescriptions.forEach(pageDescription -> convertPage(input, view, cache, pageDescription, parameters));

        // Starts the resolution of the links
        List<IDescriptionLinkResolver> linkResolvers = SiriusPropertiesCorePlugin.getPlugin().getDescriptionConverterLinkResolvers();
        linkResolvers.forEach(linkResolver -> linkResolver.resolve(view, cache));

        return view;
    }

    private void convertPage(SiriusInputDescriptor input, EEFViewDescription view, TransformationCache cache, PageDescription pageDescription, Map<String, Object> parameters) {
        Optional<IDescriptionConverter> converter = SiriusPropertiesCorePlugin.getPlugin().getDescriptionConverter(pageDescription);
        if (converter.isPresent()) {
            EObject eObject = converter.get().convert(pageDescription, parameters, cache);
            if (eObject instanceof EEFPageDescription) {
                EEFPageDescription convertedPageDescription = (EEFPageDescription) eObject;
                view.getPages().add(convertedPageDescription);

                for (GroupDescription groupDescription : pageDescription.getGroups()) {
                    convertGroup(view, cache, parameters, convertedPageDescription, groupDescription);
                }
            }
        }
    }

    private void convertGroup(EEFViewDescription view, TransformationCache cache, Map<String, Object> parameters, EEFPageDescription convertedPageDescription, GroupDescription groupDescription) {
        if (!cache.getAllInputs().contains(groupDescription)) {
            Optional<IDescriptionConverter> groupConverter = SiriusPropertiesCorePlugin.getPlugin().getDescriptionConverter(groupDescription);
            if (groupConverter.isPresent()) {
                EObject group = groupConverter.get().convert(groupDescription, parameters, cache);
                if (group instanceof EEFGroupDescription) {
                    view.getGroups().add((EEFGroupDescription) group);
                    convertedPageDescription.getGroups().add((EEFGroupDescription) group);
                }
            }
        } else {
            Optional<Object> output = cache.getOutput(groupDescription);
            output.filter(EEFGroupDescription.class::isInstance).map(EEFGroupDescription.class::cast).ifPresent(eefGroupDescription -> {
                convertedPageDescription.getGroups().add(eefGroupDescription);
            });
        }
    }

    /**
     * Creates the EEF view.
     * 
     * @return The created EEF View
     */
    private EEFViewDescription createView() {
        EEFViewDescription view = EefFactory.eINSTANCE.createEEFViewDescription();

        view.setLabelExpression("aql:input.emfEditServices(self).getText()"); //$NON-NLS-1$
        view.setImageExpression("aql:input.emfEditServices(self).getImage()"); //$NON-NLS-1$

        Set<EPackage> ePackages = new LinkedHashSet<>();
        for (PageDescription pageDescription : pageDescriptions) {
            EObject eContainer = pageDescription.eContainer();
            if (eContainer instanceof ViewExtensionDescription) {
                ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) eContainer;
                ePackages.addAll(viewExtensionDescription.getMetamodels());
            }
        }

        view.getEPackages().addAll(ePackages);
        return view;
    }
}
