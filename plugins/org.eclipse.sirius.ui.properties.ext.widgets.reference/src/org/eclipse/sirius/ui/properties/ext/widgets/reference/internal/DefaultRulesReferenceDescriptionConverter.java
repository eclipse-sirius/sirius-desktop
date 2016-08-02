/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.properties.ext.widgets.reference.internal;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EEFExtReferenceDescription;
import org.eclipse.eef.ext.widgets.reference.eefextwidgetsreference.EefExtWidgetsReferenceFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.ui.properties.api.DescriptionCache;
import org.eclipse.sirius.ui.properties.api.IDescriptionConverter;

/**
 * This {@link IDescriptionConverter} is used to replace the widget used by the
 * default rules for the references by the extension reference widget.
 * 
 * @author sbegaudeau
 */
public class DefaultRulesReferenceDescriptionConverter implements IDescriptionConverter {
    /**
     * The identifier of the widget description for mono-valued containment
     * references.
     */
    private static final String MONO_CONTAINMENT_REFERENCE_ID = "sirius_default_rules_mono_containment_reference"; //$NON-NLS-1$

    /**
     * The identifier of the widget description for mono-valued non-containment
     * references.
     */
    private static final String MONO_NONCONTAINMENT_REFERENCE_ID = "sirius_default_rules_mono_noncontainment_reference"; //$NON-NLS-1$

    /**
     * The identifier of the widget description if for multi-valued containment
     * references.
     */
    private static final String MULTI_CONTAINMENT_REFERENCE_ID = "sirius_default_rules_multi_containment_reference"; //$NON-NLS-1$

    /**
     * The identifier of the widget description if for multi-valued
     * non-containment references.
     */
    private static final String MULTI_NONCONTAINMENT_REFERENCE_ID = "sirius_default_rules_multi_noncontainment_reference"; //$NON-NLS-1$

    /**
     * The identifier of the descriptions supported by this
     * {@link IDescriptionConverter}.
     */
    private static final String[] SUPPORTED_IDS = new String[] { MONO_CONTAINMENT_REFERENCE_ID, MONO_NONCONTAINMENT_REFERENCE_ID, MULTI_CONTAINMENT_REFERENCE_ID, MULTI_NONCONTAINMENT_REFERENCE_ID };

    /**
     * The URI of the default rules resource.
     */
    private static final String SUPPORTED_URI = "platform:/plugin/org.eclipse.sirius.ui.properties/model/properties.xmi"; //$NON-NLS-1$

    @Override
    public boolean canHandle(EObject description) {
        if (description.eResource() != null && SUPPORTED_URI.equals(description.eResource().getURI().toString())) {
            if (description instanceof WidgetDescription && ((WidgetDescription) description).eContainer() instanceof DynamicMappingIf) {
                WidgetDescription widgetDescription = (WidgetDescription) description;
                return Arrays.asList(SUPPORTED_IDS).contains(widgetDescription.getIdentifier());
            }
        }
        return false;
    }

    @Override
    public EObject convert(EObject description, Map<String, Object> parameters, DescriptionCache cache) {
        if (description instanceof WidgetDescription && ((WidgetDescription) description).eContainer() instanceof DynamicMappingIf) {
            WidgetDescription widgetDescription = (WidgetDescription) description;

            if (Arrays.asList(SUPPORTED_IDS).contains(widgetDescription.getIdentifier())) {
                EEFExtReferenceDescription eefExtReferenceDescription = EefExtWidgetsReferenceFactory.eINSTANCE.createEEFExtReferenceDescription();
                eefExtReferenceDescription.setIdentifier(widgetDescription.getIdentifier());
                eefExtReferenceDescription.setReferenceNameExpression("aql:eStructuralFeature.name"); //$NON-NLS-1$
                cache.put(widgetDescription, eefExtReferenceDescription);
                return eefExtReferenceDescription;
            }
        }
        return null;
    }
}
