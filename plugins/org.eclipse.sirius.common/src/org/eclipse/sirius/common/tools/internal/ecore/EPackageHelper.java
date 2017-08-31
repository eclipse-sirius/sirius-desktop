/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.ecore.EPackageMetaData;

/**
 * Helper providing methods to extract {@link EClass} fitting some constraints from {@link EPackage}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class EPackageHelper {

    /**
     * Private constructor for utility class.
     */
    private EPackageHelper() {
    }

    /**
     * Get the concrete classes from the given {@link EPackage} (classes that are not abstract and that are not
     * interfaces).
     * 
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the concrete classes from the given {@link EPackage}.
     */
    public static List<EClass> getConcreteClasses(EPackage ePackage) {
        List<EClass> concreteClasses = new ArrayList<>();
        if (ePackage != null) {
            for (EClassifier eClassifier : ePackage.getEClassifiers()) {
                if (eClassifier instanceof EClass && !((EClass) eClassifier).isAbstract() && !((EClass) eClassifier).isInterface()
                        && ((EClass) eClassifier).getEAllReferences().stream().anyMatch(EReference::isContainment)) {
                    concreteClasses.add((EClass) eClassifier);
                }
            }
        }
        return concreteClasses;
    }

    /**
     * Return a list of potential root element {@link EClass} for a given {@link EPackage}. This list is ordered from
     * the most likely root element to the least.
     * 
     * @param ePackage
     *            the given {@link EPackage}.
     * @return a list of potential root element {@link EClass} for a given {@link EPackage}.
     */
    public static List<EClass> getEClassRootElements(EPackage ePackage) {
        /*
         * We'll only consider actually instanciable classes.
         */
        List<EClass> concreteClasses = getConcreteClasses(ePackage);

        /*
         * If we have explicit metadata associated with the EPackage, use the suggested roots.
         */
        if (ePackage != null) {
            String nsURI = ePackage.getNsURI();
            EPackageMetaData metaData = DslCommonPlugin.INSTANCE.getEPackageMetaData(nsURI);
            List<EClass> roots = new ArrayList<>();
            if (metaData != null && !metaData.getSuggestedRoots().isEmpty()) {
                roots = concreteClasses.stream().filter(c -> metaData.getSuggestedRoots().contains(c.getName())).collect(Collectors.toList());
            }
            if (!roots.isEmpty()) {
                return roots;
            }
        }

        /*
         * Otherwise, or if there is no instanciable suggested root, try to infer good candidates from the metamodel's
         * structure (i.e. prefer elements which are not contained by anything).
         */
        Map<EClass, Integer> inferredRootElementsCandidates = inferRootElementsCandidates(ePackage, concreteClasses);
        List<EClass> sortedResults = new ArrayList<EClass>();
        sortedResults.addAll(inferredRootElementsCandidates.keySet());
        Collections.sort(sortedResults, new Comparator<EClass>() {
            @Override
            public int compare(EClass eClass1, EClass eClass2) {
                return inferredRootElementsCandidates.get(eClass2).compareTo(inferredRootElementsCandidates.get(eClass1));
            }
        });
        return sortedResults;
    }

    private static Map<EClass, Integer> inferRootElementsCandidates(EPackage ePackage, Collection<EClass> concreteClasses) {
        Map<EClass, Integer> eClassToRootScore = new HashMap<>();
        if (ePackage != null) {
            List<EClass> allClasses = ePackage.getEClassifiers().stream().filter(c -> c instanceof EClass).map(c -> (EClass) c).collect(Collectors.toList());
            for (EClass eClass : concreteClasses) {
                // Element without containment relations are not considered as root elements.
                if (eClass.getEAllReferences().stream().anyMatch(EReference::isContainment)) {
                    int importance = getReferenceScore(eClass, allClasses);
                    if (importance != -1) {
                        eClassToRootScore.put(eClass, importance);
                    }
                }
            }
        }
        return eClassToRootScore;
    }

    /**
     * Returns the reference score of the given EClass among given EClasses.
     * <ul>
     * <li>A score of -1 means the given EClass is referenced by another EClass among given EClasses. This other EClass
     * is not a super type of target EClass. Target EClass has not a containment reference from its concrete or super
     * types to the other one.</li>
     * <li>A score greater than -1 means the given EClass is not referenced by given EClasses and that it contains n
     * containment references from concrete or super types and recursively through the ETypes of the references.</li>
     * </ul>
     * 
     * @param eClassToTest
     *            the EClass from which we want to know if it is contained.
     * @param allEClasses
     *            all the ECLass from which we check if they have an EReference to the given EClass.
     * @return the reference score of the given EClass among given EClasses.
     */
    private static int getReferenceScore(EClass eClassToTest, Collection<EClass> allEClasses) {
        EList<EReference> targetReferences = eClassToTest.getEAllReferences();

        int score = 0;
        Iterator<EClass> allEClassesIte = allEClasses.iterator();
        while (allEClassesIte.hasNext() && score != -1) {
            EClass eClass = allEClassesIte.next();
            Set<EReference> references = eClass.getEAllReferences().stream().filter(EReference::isContainment).collect(Collectors.toSet());
            Iterator<EReference> referencesIte = references.iterator();
            while (referencesIte.hasNext() && score != -1) {
                EReference eReference = referencesIte.next();
                EClassifier referedClassifier = eReference.getEType();
                boolean typeReferenced = eClassToTest.getEAllSuperTypes().contains(referedClassifier) || eClassToTest.equals(referedClassifier);
                boolean notReferencedByEClassSuperType = !(eClass.isSuperTypeOf(eClassToTest));
                boolean notReferencedByAContainedEClass = !targetReferences.stream()
                        .anyMatch(ref -> ref.isContainment() && (eClass.getEAllSuperTypes().contains(ref.getEType()) || eClass.equals(ref.getEType())));
                boolean isReferenced = typeReferenced && notReferencedByEClassSuperType && notReferencedByAContainedEClass;
                if (isReferenced) {
                    Set<EClass> eClassCheckedSet = new HashSet<EClass>();
                    if (!eClassContainsRecursively(eClassToTest, eClass, eClassCheckedSet)) {
                        score = -1;
                    }
                }
            }
        }
        if (score == 0) {
            Set<EClass> eClassCheckedSet = new HashSet<EClass>();
            score = computeScoreRecursively(eClassToTest, eClassCheckedSet);
        }
        return score;
    }

    /**
     * Compute and return the score of an element considered as a root element. The score is the total number of
     * containment EReference of the element and all the elements that are EType of these references recursively.
     * 
     * @param targetEClass
     *            EClass from which we compute the root likely score.
     * @param eClassCheckedSet
     *            A set of EClass that has been visited while computing recursively containment link.
     * @return the score of an element considered as a root element.
     */
    private static int computeScoreRecursively(EClass targetEClass, Set<EClass> eClassCheckedSet) {
        int score = 0;
        // if we visited already the EClass we stop to avoid infinite computing.
        if (!eClassCheckedSet.contains(targetEClass)) {
            eClassCheckedSet.add(targetEClass);
            List<EReference> eAllReferences = targetEClass.getEAllReferences();
            for (EReference eReference : eAllReferences) {
                if (eReference.isContainment()) {
                    score += 1;
                    EClassifier eType = eReference.getEType();
                    if (eType instanceof EClass) {
                        score += computeScoreRecursively((EClass) eType, eClassCheckedSet);
                    }
                }
            }
        }
        return score;
    }

    /**
     * Return true if the given target EClass contains the given other EClass from recursive containment references
     * flow.
     * 
     * @param targetEClass
     *            the EClass from which we want to know if it contains the other from recursive containment references
     *            flow.
     * @param eClassContainedRecursively
     *            the EClass from which we want to know if it contained by the other from recursive containment
     *            references flow.
     * @param eClassCheckedSet
     *            A set of EClass that has been visited while computing recursively containment link.
     * @return true if the given target EClass contains the given other EClass from recursive containment references
     *         flow. False otherwise.
     */
    private static boolean eClassContainsRecursively(EClass targetEClass, EClass eClassContainedRecursively, Set<EClass> eClassCheckedSet) {
        boolean containedRecursively = false;
        // if we visited already the EClass we stop to avoid infinite computing.
        if (!eClassCheckedSet.contains(targetEClass)) {
            eClassCheckedSet.add(targetEClass);
            List<EReference> eAllReferences = targetEClass.getEAllReferences();
            Set<EClass> referencedEClasses = new HashSet<>();
            for (EReference eReference : eAllReferences) {
                EClassifier eType = eReference.getEType();
                if (eReference.isContainment() && eType instanceof EClass) {
                    EClass referencedEClass = (EClass) eType;
                    referencedEClasses.add(referencedEClass);
                    if (eClassContainedRecursively.equals(referencedEClass) || eClassContainedRecursively.getEAllSuperTypes().contains(referencedEClass)) {
                        return true;
                    }
                }
            }
            Iterator<EClass> referecedEClassesIte = referencedEClasses.iterator();
            while (referecedEClassesIte.hasNext() && !containedRecursively) {
                EClass referencedEClass = referecedEClassesIte.next();
                containedRecursively = eClassContainsRecursively(referencedEClass, eClassContainedRecursively, eClassCheckedSet);
            }
        }
        return containedRecursively;
    }

    /**
     * Get the preferred root element for the given {@link EPackage} from the registry of EPackageExtraData.
     * 
     * @see EPackageExtraDataRegistry
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the preferred root element for the given {@link EPackage}, or null if it can't be found.
     */
    public static EClass getPreferredRootElementFromEPackageExtraData(EPackage ePackage) {
        if (ePackage != null) {
            String nsURI = ePackage.getNsURI();
            EPackageMetaData metaData = DslCommonPlugin.INSTANCE.getEPackageMetaData(nsURI);
            if (metaData != null && !metaData.getSuggestedRoots().isEmpty()) {
                EClassifier result = ePackage.getEClassifier(metaData.getSuggestedRoots().get(0));
                if (result instanceof EClass) {
                    return (EClass) result;
                }
            }
        }
        return null;
    }
}
