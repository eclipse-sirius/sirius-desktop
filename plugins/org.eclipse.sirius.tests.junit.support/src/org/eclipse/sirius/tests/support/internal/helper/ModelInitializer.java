/**
 * Copyright (c) 2012, 2024 THALES GLOBAL SERVICES and others
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.internal.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

//CHECKSTYLE:OFF

/**
 * Helper to initialize a model from a seed and a collection of EPackage.
 * 
 * @author pcd, mpo, ala
 * 
 */
public class ModelInitializer {
    private final Scope scope;

    private final Predicate<EClass> isInstanciable = (EClass input) -> !input.isAbstract() && !input.isInterface();

    private final Map<EReference, List<EClass>> refToCandidatesMap = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param scope
     */
    public ModelInitializer(Scope scope) {
        this.scope = scope;
    }

    /**
     * 
     * @param acc
     *            elements to link.
     */
    public void linkElements(List<EObject> acc) {
        for (EObject current : acc) {
            for (EReference ref : current.eClass().getEAllReferences()) {
                boolean containmentTouch = ref.isContainment() || ref.isContainer();
                boolean safeSettable = !ref.isDerived() && !ref.isUnsettable() && ref.getEOpposite() == null;
                if (!containmentTouch && safeSettable && !current.eIsSet(ref)) {
                    boolean selfValue = false;
                    EObject possibleValue = null;
                    for (EObject potentialValue : acc) {
                        if (ref.getEReferenceType().isSuperTypeOf(potentialValue.eClass())) {
                            if (potentialValue == current) {
                                selfValue = true;
                            } else {
                                possibleValue = potentialValue;
                                break;
                            }
                        }
                    }

                    if (possibleValue != null) {
                        current.eSet(ref, ref.isMany() ? Collections.singletonList(possibleValue) : possibleValue);
                    } else if (selfValue) {
                        current.eSet(ref, ref.isMany() ? Collections.singletonList(current) : current);
                    }
                }
            }
        }
    }

    /**
     * Fill the model: try to create all possible values for containment
     * references.
     * 
     * @param root
     *            the seed.
     * @return the created objects.
     */
    public List<EObject> initializeContents(EObject root) {
        Objects.requireNonNull(root);
        refToCandidatesMap.clear();
        List<EObject> created = new ArrayList<>();
        initializeContents(root, created);
        return created;
    }

    private void initializeContents(EObject element, List<EObject> acc) {
        for (EReference ref : element.eClass().getEAllReferences()) {
            if (ref.isContainment() && needsInitialization(element, ref)) {
                initializeContents(element, ref, acc);
            }
        }
    }

    private void initializeContents(EObject root, EReference ref, List<EObject> acc) {
        Set<EClass> candidates = findCompatibleCandidates(root, ref);
        Set<EClass> instanciableCandidates = candidates.stream().filter(isInstanciable).collect(Collectors.toCollection(LinkedHashSet::new));
        Collection<EClass> refToCandidates = refToCandidatesMap.get(ref);
        Predicate<EClass> newCreationType = (EClass input) -> refToCandidates == null || !refToCandidates.contains(input);
        Set<EClass> neverCreatedCandidates = instanciableCandidates.stream().filter(newCreationType).collect(Collectors.toCollection(LinkedHashSet::new));
        initializeContents(root, ref, neverCreatedCandidates, acc);
    }

    private void initializeContents(EObject element, EReference ref, Set<EClass> instanciableCandidates, List<EObject> acc) {
        // Step 1: create an instance of the given types
        List<EObject> instances = new ArrayList<EObject>(instanciableCandidates.size());
        for (EClass klass : instanciableCandidates) {
            EObject instance = klass.getEPackage().getEFactoryInstance().create(klass);
            instances.add(instance);
        }

        if (instances.isEmpty()) {
            return;
        }

        // Step 2: fill reference with all created instances
        if (ref.isMany()) {
            acc.addAll(instances);
            if (!refToCandidatesMap.containsKey(ref)) {
                refToCandidatesMap.put(ref, new ArrayList<>());
            }
            refToCandidatesMap.get(ref).addAll(instanciableCandidates);
            element.eSet(ref, instances);
        } else {
            EObject instance = instances.iterator().next();
            if (instances.size() != 1) {
                // If there is ambiguity, let the subclasses choose.
                instance = multiCandidateSingleRef(element, ref, instances);
            }

            if (instance != null) {
                if (!refToCandidatesMap.containsKey(ref)) {
                    refToCandidatesMap.put(ref, new ArrayList<>());
                }
                refToCandidatesMap.get(ref).add(instance.eClass());
                element.eSet(ref, instance);
                acc.add(instance);

                instances = Collections.singletonList(instance);
            } else {
                // No child added to the model
                instances.clear();
            }
        }

        for (EObject instance : instances) {
            // Step 3: perform additional operations after element creation
            customizeCreatedElement(instance);

            // Step 4: fill the created element
            initializeContents(instance, acc);
        }
    }

    /**
     * Indicates whether the given reference of the given element should be
     * initialized (default behavior: containment, required and not set).
     * Subclasses should override this method
     * 
     * @param element
     *            the element to fill
     * @param containmentRef
     *            a containment reference reference to set (or not)
     * @return true if the given reference of the given element should be
     *         initialized, false otherwise
     */
    protected boolean needsInitialization(EObject element, EReference containmentRef) {
        return containmentRef.isContainment() && containmentRef.isRequired() && !element.eIsSet(containmentRef);
    }

    /**
     * There are several candidates for a single valued reference. Subclasses
     * should handle the ambiguity. The defaut behavior is to return null to
     * stop.
     * 
     * @param element
     *            the current element.
     * @param ref
     *            the single valued ref to set.
     * @param instanciableCandidates
     *            the computed compatible candidates.
     * @return null to stop or one of the candidates.
     */
    protected EObject multiCandidateSingleRef(EObject element, EReference ref, Collection<EObject> instances) {
        // Resolve the ambiguity by adding the first found element
        // Sub classes can do something else
        return null;
    }

    /**
     * This method is called right after each element creation. Subclasses can
     * override this method to set specific features to the created element.
     * 
     * @param createdElement
     *            the element that has just been created
     */
    protected void customizeCreatedElement(EObject createdElement) {
        // Default behavior is to do nothing
    }

    /**
     * Finds legitimate candidates for the given element's reference. Subclasses
     * can extend this method to customize how can candidates be retrieved.
     * 
     * @param container
     *            the container element to fill
     * @param containmentReference
     *            the reference to set
     * @return all legitimate candidates (i.e. EClasses having a type
     *         compatabible with the given reference's type)
     */
    protected Set<EClass> findCompatibleCandidates(EObject container, EReference containmentReference) {
        Set<EClass> candidates = new LinkedHashSet<>();
        for (EPackage pkg : scope.getScope()) {
            candidates.addAll(findCompatibleCandidates(container, containmentReference, pkg));
        }
        candidates.removeAll(scope.getEclassesToAvoid());
        return candidates;
    }

    /**
     * Finds legitimate candidates for the given element's reference in the
     * scope of the given {@link EPackage}.
     * 
     * @param container
     *            the container element to fill
     * @param reference
     *            the reference to set
     * @param currentScope
     *            the {@link EPackage} in which candidates will be browsed
     * @return all legitimate candidates (i.e. EClasses having a type
     *         compatabible with the given reference's type)
     */
    protected Collection<? extends EClass> findCompatibleCandidates(EObject container, EReference reference, EPackage currentScope) {
        EClass type = reference.getEReferenceType();
        Set<EClass> result = new LinkedHashSet<>();
        for (EClassifier classifier : currentScope.getEClassifiers()) {
            if (classifier instanceof EClass) {
                EClass klass = (EClass) classifier;
                boolean isCompatible = klass.equals(type) || klass.getEAllSuperTypes().contains(type);
                if (isCompatible) {
                    result.add(klass);
                }
            }
        }
        return result;
    }

    public static class Scope {
        private final Set<EPackage> scope = new LinkedHashSet<>();

        private final Set<EClass> eclassesToAvoid = new HashSet<>();

        public Scope(Collection<? extends EPackage> packages, Collection<EClass> doNotIns) {
            this.scope.addAll(Objects.requireNonNull(packages));
            this.eclassesToAvoid.addAll(Objects.requireNonNull(doNotIns));
        }

        public Scope(Collection<? extends EPackage> packages) {
            this.scope.addAll(Objects.requireNonNull(packages));
        }

        public Set<EClass> getEclassesToAvoid() {
            return eclassesToAvoid;
        }

        public Set<EPackage> getScope() {
            return scope;
        }
    }
}
