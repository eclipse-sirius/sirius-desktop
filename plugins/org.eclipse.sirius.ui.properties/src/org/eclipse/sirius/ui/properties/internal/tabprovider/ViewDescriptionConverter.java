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
package org.eclipse.sirius.ui.properties.internal.tabprovider;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
import org.eclipse.sirius.properties.core.api.SiriusInputDescriptor;
import org.eclipse.sirius.ui.properties.api.DescriptionCache;
import org.eclipse.sirius.ui.properties.api.IDescriptionConverter;
import org.eclipse.sirius.ui.properties.api.IDescriptionLinkResolver;
import org.eclipse.sirius.ui.properties.internal.SiriusUIPropertiesPlugin;

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

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(IDescriptionConverter.INPUT, input);
        parameters.put(IDescriptionConverter.VIEW, view);

        DescriptionCache cache = new DescriptionCache();

        // Starts the conversion
        for (PageDescription pageDescription : pageDescriptions) {
            IDescriptionConverter converter = SiriusUIPropertiesPlugin.getPlugin().getDescriptionConverter(pageDescription);
            EObject page = converter.convert(pageDescription, parameters, cache);
            if (page instanceof EEFPageDescription) {
                view.getPages().add((EEFPageDescription) page);

                for (GroupDescription groupDescription : pageDescription.getGroups()) {
                    if (!cache.getAllSiriusDescriptions().contains(groupDescription)) {
                        IDescriptionConverter groupConverter = SiriusUIPropertiesPlugin.getPlugin().getDescriptionConverter(groupDescription);
                        EObject group = groupConverter.convert(groupDescription, parameters, cache);
                        if (group instanceof EEFGroupDescription) {
                            view.getGroups().add((EEFGroupDescription) group);
                            ((EEFPageDescription) page).getGroups().add((EEFGroupDescription) group);
                        }
                    } else {
                        ((EEFPageDescription) page).getGroups().add((EEFGroupDescription) cache.getEEFDescription(groupDescription));
                    }
                }
            }
        }

        // Starts the resolution of the links
        List<IDescriptionLinkResolver> linkResolvers = SiriusUIPropertiesPlugin.getPlugin().getDescriptionLinkResolvers();
        for (IDescriptionLinkResolver linkResolver : linkResolvers) {
            linkResolver.resolve(view, cache);
        }

        return view;
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
