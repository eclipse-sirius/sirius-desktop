/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.query;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.query.model.CustomizationQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.BestStyleDescriptionKey;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

import com.google.common.collect.Iterators;

/**
 * A Query for {@link StyleDescriptionQuery}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class StyleDescriptionQuery {

    private StyleDescription styleDescription;

    /**
     * Default constructor.
     * 
     * @param styleDescription
     *            the {@link StyleDescription} to query
     */
    public StyleDescriptionQuery(StyleDescription styleDescription) {
        this.styleDescription = styleDescription;
    }

    /**
     * Get all {@link EStructuralFeatureCustomization} applied on the current {@link StyleDescription} or a element
     * owned by a {@link StyleDescription} , for example a BasicLabelStyleDescription, a LabelBorderStyleDescription, a
     * GaugeSectionDescription or a ConditionalStyleDescription, which is enabled.
     * 
     * @param bestStyleDescriptionKey
     *            the {@link BestStyleDescriptionKey} identifying the best {@link StyleDescription}
     * @param interpreter
     *            the {@link IInterpreter} used to know if a {@link EStructuralFeatureCustomization} is enabled
     * @return all {@link EStructuralFeatureCustomization} applied on the current {@link StyleDescription} which is
     *         enabled
     */
    public Map<EStructuralFeatureCustomization, Set<EObject>> getEStructuralFeatureCustomizationAppliedOn(BestStyleDescriptionKey bestStyleDescriptionKey, IInterpreter interpreter) {
        Map<EStructuralFeatureCustomization, Set<EObject>> eStructuralFeatureCustomizationsAppliedOn = new LinkedHashMap<EStructuralFeatureCustomization, Set<EObject>>();
        DDiagram dDiagram = bestStyleDescriptionKey.getDDiagram();
        for (Layer layer : dDiagram.getActivatedLayers()) {
            Customization customization = layer.getCustomization();
            if (customization != null) {
                Map<EStructuralFeatureCustomization, Set<EObject>> collectedEStructuralFeatureCustomizationsForCurrentLayer = collectEStructuralFeatureCustomizations(customization);
                for (Entry<EStructuralFeatureCustomization, Set<EObject>> entry : collectedEStructuralFeatureCustomizationsForCurrentLayer.entrySet()) {
                    EStructuralFeatureCustomization featureCustomization = entry.getKey();
                    Set<EObject> value = entry.getValue();
                    Set<EObject> appliedOn = eStructuralFeatureCustomizationsAppliedOn.get(featureCustomization);
                    if (appliedOn == null) {
                        eStructuralFeatureCustomizationsAppliedOn.put(featureCustomization, value);
                    } else {
                        appliedOn.addAll(value);
                    }
                }

            }
        }
        Map<EStructuralFeatureCustomization, Set<EObject>> enaledFeatureCostumizations = getEnabledEStructuralFeatureCustomizations(eStructuralFeatureCustomizationsAppliedOn, bestStyleDescriptionKey,
                interpreter);
        return enaledFeatureCostumizations;
    }

    private Map<EStructuralFeatureCustomization, Set<EObject>> collectEStructuralFeatureCustomizations(Customization customization) {
        Map<EStructuralFeatureCustomization, Set<EObject>> eStructuralFeatureCustomizationsAppliedOn = new LinkedHashMap<EStructuralFeatureCustomization, Set<EObject>>();
        CustomizationQuery customizationQuery = new CustomizationQuery(customization);
        List<VSMElementCustomization> vsmElementCustomizations = customizationQuery.getVSMElementCustomizations();
        for (VSMElementCustomization vsmElementCustomization : vsmElementCustomizations) {
            List<EStructuralFeatureCustomization> featureCustomizations = vsmElementCustomization.getFeatureCustomizations();
            for (EStructuralFeatureCustomization featureCustomization : featureCustomizations) {

                // If the feature customization is "apply on all" we do not
                // check whether the current style description is specifically
                // concerned by the "applied on" feature.
                if (featureCustomization.isApplyOnAll() || isAppliedOnOrOwnedContent(featureCustomization.getAppliedOn())) {
                    Set<EObject> appliedOn = eStructuralFeatureCustomizationsAppliedOn.get(featureCustomization);
                    if (appliedOn == null) {
                        appliedOn = new LinkedHashSet<EObject>();
                        eStructuralFeatureCustomizationsAppliedOn.put(featureCustomization, appliedOn);
                    }
                    if (featureCustomization.isApplyOnAll()) {
                        addStyleDescriptionAndAllSubStyles(appliedOn);
                    } else {
                        appliedOn.addAll(featureCustomization.getAppliedOn());
                    }
                }
            }
        }
        List<VSMElementCustomizationReuse> vsmElementCustomizationReuses = customizationQuery.getVSMElementCustomzationReuses();
        for (VSMElementCustomizationReuse vsmElementCustomizationReuse : vsmElementCustomizationReuses) {
            if (isAppliedOnOrOwnedContent(vsmElementCustomizationReuse.getAppliedOn())) {
                for (EStructuralFeatureCustomization featureCustomization : vsmElementCustomizationReuse.getReuse()) {
                    Set<EObject> appliedOn = eStructuralFeatureCustomizationsAppliedOn.get(featureCustomization);
                    if (appliedOn == null) {
                        appliedOn = new LinkedHashSet<EObject>();
                        eStructuralFeatureCustomizationsAppliedOn.put(featureCustomization, appliedOn);
                    }
                    appliedOn.addAll(vsmElementCustomizationReuse.getAppliedOn());
                }
            }
        }
        return eStructuralFeatureCustomizationsAppliedOn;
    }

    private void addStyleDescriptionAndAllSubStyles(Set<EObject> appliedOn) {
        appliedOn.add(this.styleDescription);
        Iterators.addAll(appliedOn, this.styleDescription.eAllContents());
    }

    private boolean isAppliedOnOrOwnedContent(List<EObject> appliedOn) {
        boolean isAppliedOnOrOwnedContent = appliedOn.contains(styleDescription);
        if (!isAppliedOnOrOwnedContent) {
            TreeIterator<EObject> styleDescriptionAllContents = styleDescription.eAllContents();
            while (styleDescriptionAllContents.hasNext()) {
                EObject styleDescriptionSubElt = styleDescriptionAllContents.next();
                if (appliedOn.contains(styleDescriptionSubElt)) {
                    isAppliedOnOrOwnedContent = true;
                    break;
                }
            }
        }
        return isAppliedOnOrOwnedContent;
    }

    private Map<EStructuralFeatureCustomization, Set<EObject>> getEnabledEStructuralFeatureCustomizations(Map<EStructuralFeatureCustomization, Set<EObject>> eStructuralFeatureCustomizationsAppliedOn,
            BestStyleDescriptionKey bestStyleDescriptionKey, IInterpreter interpreter) {
        Map<VSMElementCustomization, Boolean> enabledVSMElementCustomizations = new HashMap<VSMElementCustomization, Boolean>();
        Map<EStructuralFeatureCustomization, Set<EObject>> enaledFeatureCostumizations = new LinkedHashMap<EStructuralFeatureCustomization, Set<EObject>>();
        for (Entry<EStructuralFeatureCustomization, Set<EObject>> entry : eStructuralFeatureCustomizationsAppliedOn.entrySet()) {
            EStructuralFeatureCustomization featureCustomization = entry.getKey();
            Set<EObject> appliedOn = entry.getValue();
            EObject eContainer = featureCustomization.eContainer();
            if (enabledVSMElementCustomizations.containsKey(eContainer)) {
                if (enabledVSMElementCustomizations.get(eContainer)) {
                    enaledFeatureCostumizations.put(featureCustomization, appliedOn);
                }
            } else if (eContainer instanceof VSMElementCustomization) {
                VSMElementCustomization vsmElementCustomization = (VSMElementCustomization) eContainer;
                VSMElementCustomizationQuery vsmElementCustomizationQuery = new VSMElementCustomizationQuery(vsmElementCustomization);
                boolean enabled = vsmElementCustomizationQuery.isEnabled(styleDescription, bestStyleDescriptionKey, interpreter);
                enabledVSMElementCustomizations.put(vsmElementCustomization, enabled);
                if (enabled) {
                    enaledFeatureCostumizations.put(featureCustomization, appliedOn);
                }
            }
        }
        return enaledFeatureCostumizations;
    }

    /**
     * Get the {@link DiagramDescription} parent of the {@link StyleDescription} .
     * 
     * @return the {@link DiagramDescription} parent of the {@link StyleDescription}
     */
    public DiagramDescription getDiagramDescription() {
        DiagramDescription diagramDescription = null;
        EObject container = styleDescription;
        while (!(container instanceof DiagramDescription) && container != null) {
            container = container.eContainer();
        }
        if (container instanceof Layer) {
            diagramDescription = (DiagramDescription) container;
        }
        return diagramDescription;
    }
}
