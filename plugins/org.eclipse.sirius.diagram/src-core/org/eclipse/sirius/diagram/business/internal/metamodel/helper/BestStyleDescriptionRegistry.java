/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.ConditionalStyleSpecOperations;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramInternalQuery;
import org.eclipse.sirius.diagram.business.internal.query.EAttributeCustomizationQuery;
import org.eclipse.sirius.diagram.business.internal.query.StyleDescriptionQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

import com.google.common.primitives.Primitives;

/**
 * A registry of {@link StyleDescription}. Its lifecycle must be valid only
 * during one refresh.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BestStyleDescriptionRegistry extends HashMap<BestStyleDescriptionKey, StyleDescription> {

    /**
     * The key of the annotation containing the cache of computed
     * {@link StyleDescription}.
     */
    public static final String DANNOTATION_CUSTOMIZATION_KEY = "DANNOTATION_CUSTOMIZATION_KEY"; //$NON-NLS-1$

    private static final long serialVersionUID = 1L;

    private IInterpreter interpreter;

    /**
     * Default constructor.
     * 
     * @param interpreter
     *            the {@link IInterpreter} used to evaluate some
     *            InterpretedExpression of style description
     */
    public BestStyleDescriptionRegistry(IInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public StyleDescription get(Object key) {
        StyleDescription bestStyleDescription = super.get(key);
        if (bestStyleDescription == null && key instanceof BestStyleDescriptionKey) {
            BestStyleDescriptionKey bestStyleDescriptionKey = (BestStyleDescriptionKey) key;
            bestStyleDescription = getBestStyleDescription(bestStyleDescriptionKey);
            if (bestStyleDescription != null) {
                put(bestStyleDescriptionKey, bestStyleDescription);
            }
        }
        return bestStyleDescription;
    }

    /**
     * Returns the best style description to use for the given mapping.
     * 
     * @param bestStyleDescriptionKey
     *            the {@link BestStyleDescriptionKey} identifying the best
     *            {@link StyleDescription} to use.
     * @return the best style description to use for the given
     *         {@link BestStyleDescriptionKey}.
     */
    private StyleDescription getBestStyleDescription(BestStyleDescriptionKey bestStyleDescriptionKey) {
        StyleDescription result = null;
        DiagramElementMapping diagramElementMapping = bestStyleDescriptionKey.getDiagramElementMapping();
        DDiagram dDiagram = bestStyleDescriptionKey.getDDiagram();
        EObject modelElement = bestStyleDescriptionKey.getModelElement();
        EObject viewVariable = bestStyleDescriptionKey.getViewVariable();
        EObject containerVariable = bestStyleDescriptionKey.getContainerVariable();
        final Iterator<? extends ConditionalStyleDescription> it = getConditionalStyles(diagramElementMapping, dDiagram).iterator();
        while (it.hasNext() && result == null) {
            final ConditionalStyleDescription condStyle = it.next();
            if (ConditionalStyleSpecOperations.checkPredicate(condStyle, modelElement, viewVariable, containerVariable, interpreter)) {
                result = getStyleDescription(condStyle);
            }
        }
        if (result == null) {
            result = MappingHelper.getDefaultStyleDescription(diagramElementMapping);
        }
        if (result != null) {
            // Apply customization
            result = getCustomizedBestStyleDescription(result, bestStyleDescriptionKey);
        }
        return result;
    }

    /**
     * Get a customized release of the best {@link StyleDescription} if
     * Customization exists, the same as in parameter else.
     * 
     * @param styleDescription
     *            the {@link StyleDescription} to be customized
     * @param bestStyleDescriptionKey
     *            the {@link BestStyleDescriptionKey} associated to the best
     *            {@link StyleDescription}
     * @return a customized release of the {@link StyleDescription} in parameter
     */
    private StyleDescription getCustomizedBestStyleDescription(StyleDescription styleDescription, BestStyleDescriptionKey bestStyleDescriptionKey) {
        StyleDescription customizedStyleDescription = styleDescription;
        StyleDescriptionQuery styleDescriptionQuery = new StyleDescriptionQuery(styleDescription);
        Map<EStructuralFeatureCustomization, Set<EObject>> eStructuralFeatureCustomizationAppliedOn = styleDescriptionQuery.getEStructuralFeatureCustomizationAppliedOn(bestStyleDescriptionKey,
                interpreter);
        if (!eStructuralFeatureCustomizationAppliedOn.isEmpty()) {
            customizedStyleDescription = EcoreUtil.copy(styleDescription);

            for (java.util.Map.Entry<EStructuralFeatureCustomization, Set<EObject>> entry : eStructuralFeatureCustomizationAppliedOn.entrySet()) {
                EStructuralFeatureCustomization featureCustomization = entry.getKey();
                Set<EObject> appliedOn = entry.getValue();
                if (featureCustomization instanceof EAttributeCustomization) {
                    EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) featureCustomization;
                    applyEAttributeCustomization(eAttributeCustomization, styleDescription, customizedStyleDescription, appliedOn, bestStyleDescriptionKey);
                } else if (featureCustomization instanceof EReferenceCustomization) {
                    EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) featureCustomization;
                    applyEReferenceCustomization(eReferenceCustomization, styleDescription, customizedStyleDescription, appliedOn);
                }
            }
            customizedStyleDescription = storeInDDiagram(customizedStyleDescription, bestStyleDescriptionKey);
            // Computed StyleDescription no more used will be garbage collected
            // by the DDiagramSynchronizer
        }
        return customizedStyleDescription;
    }

    private StyleDescription storeInDDiagram(StyleDescription customizedStyleDescription, BestStyleDescriptionKey bestStyleDescriptionKey) {
        DDiagram dDiagram = bestStyleDescriptionKey.getDDiagram();
        ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = new DDiagramInternalQuery(dDiagram).getComputedStyleDescriptionRegistry(true);
        StyleDescription found = null;
        for (StyleDescription computedStyleDescription : computedStyleDescriptionRegistry.getComputedStyleDescriptions()) {
            if (EcoreUtil.equals(customizedStyleDescription, computedStyleDescription)) {
                found = computedStyleDescription;
                break;
            }
        }
        if (found == null) {
            computedStyleDescriptionRegistry.getComputedStyleDescriptions().add(customizedStyleDescription);
            found = customizedStyleDescription;
        }
        return found;
    }

    private void applyEAttributeCustomization(EAttributeCustomization eAttributeCustomization, StyleDescription styleDescription, StyleDescription customizedStyleDescription, Set<EObject> appliedOn,
            BestStyleDescriptionKey bestStyleDescriptionKey) {
        String attributeName = eAttributeCustomization.getAttributeName();
        Set<EObject> realEltsToCustomize = getRealElementsToCustomize(styleDescription, customizedStyleDescription, appliedOn, eAttributeCustomization);
        if (!realEltsToCustomize.isEmpty()) {
            EAttributeCustomizationQuery eAttributeCustomizationQuery = new EAttributeCustomizationQuery(eAttributeCustomization);
            String newAttributeValue = eAttributeCustomizationQuery.getNewAttributeValue(bestStyleDescriptionKey, interpreter);
            for (EObject realEltToCustomize : realEltsToCustomize) {
                EStructuralFeature eStructuralFeature = realEltToCustomize.eClass().getEStructuralFeature(attributeName);
                if (eStructuralFeature instanceof EAttribute && newAttributeValue != null) {
                    EAttribute eAttribute = (EAttribute) eStructuralFeature;
                    EDataType eAttributeType = eAttribute.getEAttributeType();
                    Class<?> instanceClass = eAttributeType.getInstanceClass();
                    if (instanceClass.isPrimitive()) {
                        instanceClass = Primitives.wrap(instanceClass);
                    }
                    Object convertedObject = EcoreUtil.createFromString(eAttributeType, newAttributeValue);
                    if (eAttribute.isMany() && convertedObject != null && instanceClass.isAssignableFrom(convertedObject.getClass())) {
                        List<Object> manyConvertedObject = new ArrayList<Object>();
                        manyConvertedObject.add(convertedObject);
                        realEltToCustomize.eSet(eAttribute, manyConvertedObject);
                    } else if (convertedObject != null && instanceClass.isAssignableFrom(convertedObject.getClass())) {
                        realEltToCustomize.eSet(eAttribute, convertedObject);
                    }
                }
            }
        }
    }

    private void applyEReferenceCustomization(EReferenceCustomization eReferenceCustomization, StyleDescription styleDescription, StyleDescription customizedStyleDescription, Set<EObject> appliedOn) {
        Set<EObject> realEltsToCustomize = getRealElementsToCustomize(styleDescription, customizedStyleDescription, appliedOn, eReferenceCustomization);
        for (EObject realEltToCustomize : realEltsToCustomize) {
            EStructuralFeature eStructuralFeature = realEltToCustomize.eClass().getEStructuralFeature(eReferenceCustomization.getReferenceName());
            if (eStructuralFeature instanceof EReference) {
                realEltToCustomize.eSet(eStructuralFeature, eReferenceCustomization.getValue());
            }
        }
    }

    private Set<EObject> getRealElementsToCustomize(StyleDescription styleDescription, StyleDescription customizedStyleDescription, Set<EObject> appliedOn,
            EStructuralFeatureCustomization eStructuralFeatureCustomization) {
        Set<EObject> realEltsToCustomize = new LinkedHashSet<EObject>();
        if (appliedOn.contains(styleDescription) && hasFeatureName(styleDescription, eStructuralFeatureCustomization)) {
            realEltsToCustomize.add(customizedStyleDescription);
        }
        TreeIterator<EObject> styleDescriptionContent = styleDescription.eAllContents();
        while (styleDescriptionContent.hasNext()) {
            EObject next = styleDescriptionContent.next();
            if (appliedOn.contains(next) && hasFeatureName(next, eStructuralFeatureCustomization)) {
                Object object = customizedStyleDescription.eGet(next.eContainingFeature());
                if (object instanceof EObject) {
                    realEltsToCustomize.add((EObject) object);
                } else if (object instanceof List<?>) {
                    List<?> list = (List<?>) object;
                    for (Object obj : list) {
                        if (obj instanceof EObject) {
                            realEltsToCustomize.add((EObject) obj);
                        }
                    }
                }
            }
        }
        return realEltsToCustomize;
    }

    private boolean hasFeatureName(EObject eObject, EStructuralFeatureCustomization eStructuralFeatureCustomization) {
        boolean hasFeatureName = false;
        if (eStructuralFeatureCustomization instanceof EAttributeCustomization) {
            EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) eStructuralFeatureCustomization;
            String attributeName = eAttributeCustomization.getAttributeName();
            hasFeatureName = eObject.eClass().getEStructuralFeature(attributeName) instanceof EAttribute;
        } else if (eStructuralFeatureCustomization instanceof EReferenceCustomization) {
            EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) eStructuralFeatureCustomization;
            String referenceName = eReferenceCustomization.getReferenceName();
            hasFeatureName = eObject.eClass().getEStructuralFeature(referenceName) instanceof EReference;
        }
        return hasFeatureName;
    }

    /**
     * Returns the conditional style of the given mapping.
     * 
     * @param mapping
     *            the mapping.
     * @param diagram
     *            the current diagram (for calculate imported mapping)
     * @return the conditional style of the given mapping.
     */
    private List<? extends ConditionalStyleDescription> getConditionalStyles(final DiagramElementMapping mapping, final DDiagram diagram) {
        return new GetConditionalStyle(diagram).doSwitch(mapping);
    }

    /**
     * Returns the style description owned by the given conditional style.
     * 
     * @param conditionalStyleDescription
     *            the conditional style.
     * @return the style description owned by the given conditional style.
     */
    private StyleDescription getStyleDescription(final ConditionalStyleDescription conditionalStyleDescription) {
        return new GetStyleDescription().doSwitch(conditionalStyleDescription);
    }
}
