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
package org.eclipse.sirius.business.internal.metamodel.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.primitives.Primitives;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.internal.metamodel.description.operations.ConditionalStyleSpecOperations;
import org.eclipse.sirius.business.internal.query.EAttributeCustomizationQuery;
import org.eclipse.sirius.business.internal.query.StyleDescriptionQuery;
import org.eclipse.sirius.description.AnnotationEntry;
import org.eclipse.sirius.description.ConditionalStyleDescription;
import org.eclipse.sirius.description.DescriptionFactory;
import org.eclipse.sirius.description.DiagramElementMapping;
import org.eclipse.sirius.description.EAttributeCustomization;
import org.eclipse.sirius.description.EReferenceCustomization;
import org.eclipse.sirius.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.description.style.StyleDescription;

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
    public static final String DANNOTATION_CUSTOMIZATION_KEY = "DANNOTATION_CUSTOMIZATION_KEY";

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
        } else {
            removeComputedStyleDescriptionFromCache(bestStyleDescriptionKey);
        }
        return customizedStyleDescription;
    }

    private StyleDescription storeInDDiagram(StyleDescription customizedStyleDescription, BestStyleDescriptionKey bestStyleDescriptionKey) {
        DDiagram dDiagram = bestStyleDescriptionKey.getDDiagram();
        ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = getComputedStyleDescriptionRegistry(dDiagram, true);
        EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> modelElementsMap = computedStyleDescriptionRegistry.getCache().get(bestStyleDescriptionKey.getDiagramElementMapping());
        if (modelElementsMap == null) {
            // modelElementsMap = new EcoreEMap<EObject, EMap<EObject,
            // EMap<EObject,
            // StyleDescription>>>(ComputedStyleDescriptionPackage.Literals.MODEL_ELEMENT2_VIEW_VARIABLE,
            // ModelElement2ViewVariableImpl.class, (InternalEObject)
            // bestStyleDescriptionKey.getDiagramElementMapping(),
            // ComputedStyleDescriptionPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT);
            modelElementsMap = new BasicEMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>();
            computedStyleDescriptionRegistry.getCache().put(bestStyleDescriptionKey.getDiagramElementMapping(), modelElementsMap);
            modelElementsMap = computedStyleDescriptionRegistry.getCache().get(bestStyleDescriptionKey.getDiagramElementMapping());
        }
        EMap<EObject, EMap<EObject, StyleDescription>> viewVariablesMap = modelElementsMap.get(bestStyleDescriptionKey.getModelElement());
        if (viewVariablesMap == null) {
            // viewVariablesMap = new EcoreEMap<EObject, EMap<EObject,
            // StyleDescription>>(ComputedStyleDescriptionPackage.Literals.VIEW_VARIABLE2_CONTAINER_VARIABLE,
            // ViewVariable2ContainerVariableImpl.class, (InternalEObject)
            // bestStyleDescriptionKey.getModelElement(),
            // ComputedStyleDescriptionPackage.MODEL_ELEMENT2_VIEW_VARIABLE);
            viewVariablesMap = new BasicEMap<EObject, EMap<EObject, StyleDescription>>();
            modelElementsMap.put(bestStyleDescriptionKey.getModelElement(), viewVariablesMap);
            viewVariablesMap = modelElementsMap.get(bestStyleDescriptionKey.getModelElement());
        }
        EMap<EObject, StyleDescription> containerVariablesMap = viewVariablesMap.get(bestStyleDescriptionKey.getViewVariable());
        if (containerVariablesMap == null) {
            // containerVariablesMap = new EcoreEMap<EObject,
            // StyleDescription>(ComputedStyleDescriptionPackage.Literals.CONTAINER_VARIABLE2_STYLE_DESCRIPTION,
            // ContainerVariable2StyleDescriptionImpl.class, (InternalEObject)
            // bestStyleDescriptionKey.getViewVariable(),
            // ComputedStyleDescriptionPackage.VIEW_VARIABLE2_CONTAINER_VARIABLE);
            containerVariablesMap = new BasicEMap<EObject, StyleDescription>();
            viewVariablesMap.put(bestStyleDescriptionKey.getViewVariable(), containerVariablesMap);
            containerVariablesMap = viewVariablesMap.get(bestStyleDescriptionKey.getViewVariable());
        }
        StyleDescription computedStyleDescriptionFromCache = containerVariablesMap.get(bestStyleDescriptionKey.getContainerVariable());

        if (computedStyleDescriptionFromCache == null) {
            computedStyleDescriptionRegistry.getComputedStyleDescriptions().add(customizedStyleDescription);
            containerVariablesMap.put(bestStyleDescriptionKey.getContainerVariable(), customizedStyleDescription);
        } else {
            if (!EcoreUtil.equals(computedStyleDescriptionFromCache, customizedStyleDescription)) {
                computedStyleDescriptionRegistry.getComputedStyleDescriptions().remove(computedStyleDescriptionFromCache);
                computedStyleDescriptionRegistry.getComputedStyleDescriptions().add(customizedStyleDescription);
                containerVariablesMap.put(bestStyleDescriptionKey.getContainerVariable(), customizedStyleDescription);
            } else {
                return computedStyleDescriptionFromCache;
            }
        }
        return customizedStyleDescription;
    }

    /**
     * Get the {@link ComputedStyleDescriptionRegistry} of the specified
     * {@link DDiagram}.
     * 
     * @param dDiagram
     *            the {@link DDiagram} for which we want
     *            {@link ComputedStyleDescriptionRegistry}
     * @param createIfNotExists
     *            true if we want to create a
     *            {@link ComputedStyleDescriptionRegistry} for the specified
     *            {@link DDiagram} if there is not one, false otherwise
     * @return the {@link ComputedStyleDescriptionRegistry} of the
     *         {@link DDiagram} or null if this last has not one
     */
    public static ComputedStyleDescriptionRegistry getComputedStyleDescriptionRegistry(DDiagram dDiagram, boolean createIfNotExists) {
        ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = null;
        AnnotationEntry annotationEntry = null;
        Collection<AnnotationEntry> annotationEntries = new DRepresentationQuery(dDiagram).getAnnotation(DANNOTATION_CUSTOMIZATION_KEY);
        if (annotationEntries == null || annotationEntries.isEmpty()) {
            annotationEntry = DescriptionFactory.eINSTANCE.createAnnotationEntry();
            annotationEntry.setSource(DANNOTATION_CUSTOMIZATION_KEY);
            dDiagram.getOwnedAnnotationEntries().add(annotationEntry);
        } else {
            annotationEntry = annotationEntries.iterator().next();
        }
        if (annotationEntry.getData() == null || !(annotationEntry.getData() instanceof ComputedStyleDescriptionRegistry)) {
            computedStyleDescriptionRegistry = SiriusFactory.eINSTANCE.createComputedStyleDescriptionRegistry();
            annotationEntry.setData(computedStyleDescriptionRegistry);
        } else {
            computedStyleDescriptionRegistry = (ComputedStyleDescriptionRegistry) annotationEntry.getData();
        }
        return computedStyleDescriptionRegistry;
    }

    private void removeComputedStyleDescriptionFromCache(BestStyleDescriptionKey bestStyleDescriptionKey) {
        StyleDescription styleDescription = null;
        DDiagram dDiagram = bestStyleDescriptionKey.getDDiagram();
        Collection<AnnotationEntry> annotationEntries = new DRepresentationQuery(dDiagram).getAnnotation(DANNOTATION_CUSTOMIZATION_KEY);
        if (annotationEntries != null && !annotationEntries.isEmpty()) {
            AnnotationEntry annotationEntry = annotationEntries.iterator().next();
            if (annotationEntry.getData() instanceof ComputedStyleDescriptionRegistry) {
                ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = (ComputedStyleDescriptionRegistry) annotationEntry.getData();
                EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> modelElementsMap = computedStyleDescriptionRegistry.getCache().get(bestStyleDescriptionKey.getDiagramElementMapping());
                if (modelElementsMap != null) {
                    EMap<EObject, EMap<EObject, StyleDescription>> viewVariablesMap = modelElementsMap.get(bestStyleDescriptionKey.getModelElement());
                    if (viewVariablesMap != null) {
                        EMap<EObject, StyleDescription> containerVariablesMap = viewVariablesMap.get(bestStyleDescriptionKey.getViewVariable());
                        // CHECKSTYLE:OFF
                        if (containerVariablesMap != null) {
                            styleDescription = containerVariablesMap.get(bestStyleDescriptionKey.getContainerVariable());
                            containerVariablesMap.remove(bestStyleDescriptionKey.getContainerVariable());
                            if (containerVariablesMap.isEmpty()) {
                                viewVariablesMap.remove(bestStyleDescriptionKey.getViewVariable());
                            }
                        }
                        if (viewVariablesMap.isEmpty()) {
                            modelElementsMap.remove(bestStyleDescriptionKey.getModelElement());
                        }
                        // CHECKSTYLE:ON
                    }
                    if (modelElementsMap.isEmpty()) {
                        computedStyleDescriptionRegistry.getCache().remove(bestStyleDescriptionKey.getDiagramElementMapping());
                    }
                }
                if (styleDescription != null) {
                    computedStyleDescriptionRegistry.getComputedStyleDescriptions().remove(styleDescription);
                }
            }
        }
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
                    if (convertedObject != null && instanceClass.isAssignableFrom(convertedObject.getClass())) {
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
