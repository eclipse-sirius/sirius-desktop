/*******************************************************************************
 * Copyright (c) 2011, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.contribution;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.viewpoint.Messages;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

/**
 * An updater can modify a model in place to match the structure of a new
 * version of that model.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class Updater {
    /**
     * The root element of the model to update.
     */
    private final EObject element;

    /**
     * The root element of the reference model to match.
     */
    private final EObject reference;

    /**
     * The matcher used to identify logically equivalent elements between the
     * two models.
     */
    private final Matcher matcher;

    /**
     * <code>true</code> if this is a root updater, <code>false</code> if it is
     * an Updater created by another one as part of the recursive process.
     */
    private boolean isRoot;

    /**
     * The set of elements which were contained in the initial model but were
     * removed during the update because they do not exist in the reference
     * model to match.
     */
    private Set<EObject> orphaned;

    private List<EStructuralFeature> ignored = Collections.emptyList();

    /**
     * Constructor.
     * 
     * @param matcher
     *            the matcher to use to identify equivalent elements between the
     *            target and reference models.
     * @param element
     *            the old version of the model to update.
     * @param reference
     *            the new version of the model to update to.
     */
    public Updater(Matcher matcher, EObject element, EObject reference) {
        this.matcher = Preconditions.checkNotNull(matcher);
        this.element = Preconditions.checkNotNull(element);
        this.reference = Preconditions.checkNotNull(reference);
        this.isRoot = true;
    }

    /**
     * Performs the update.
     * 
     * @return the old model, updated to match the structure of the new one.
     */
    public EObject update() {
        orphaned = Sets.newHashSet();
        if (!matcher.areSameLogicalElement(element, reference)) {
            throw new IllegalArgumentException(Messages.Updater_updateElementLogicallyDifferentErrorMsg);
        } else if (element.eClass() != reference.eClass()) {
            String msg = MessageFormat.format(Messages.Updater_updateElementDifferentReferenceTypeErrorMsg, element.eClass(), reference.eClass());
            throw new IllegalArgumentException(msg);
        } else {
            updateAttributes();
            updateReferences();
            if (isRoot) {
                fixReferences();
            }
            return element;
        }
    }

    private void updateAttributes() {
        for (EAttribute attr : element.eClass().getEAllAttributes()) {
            if (attr.isChangeable() && !attr.isDerived() && !ignored.contains(attr)) {
                updateAttribute(attr);
            }
        }
    }

    private void updateAttribute(EAttribute attr) {
        if (element.eIsSet(attr) && !reference.eIsSet(attr)) {
            element.eUnset(attr);
        } else if (attr.isMany()) {
            updateManyValuesAttribute(attr);
        } else {
            updateSingleValueAttribute(attr);
        }
    }

    private void updateSingleValueAttribute(EAttribute attr) {
        Object oldValue = element.eGet(attr);
        Object newValue = reference.eGet(attr);
        if (!Objects.equal(oldValue, newValue)) {
            element.eSet(attr, reference.eGet(attr));
        }
    }

    private void updateManyValuesAttribute(EAttribute attr) {
        Collection<Object> oldValues = getMany(element, attr);
        Collection<Object> newValues = getMany(reference, attr);
        if (!Iterables.elementsEqual(oldValues, newValues)) {
            oldValues.clear();
            oldValues.addAll(newValues);
        }
    }

    private void updateReferences() {
        for (EReference ref : element.eClass().getEAllReferences()) {
            if (ref.isChangeable() && !ref.isDerived() && !ignored.contains(ref)) {
                updateReference(ref);
            }
        }
    }

    private void updateReference(EReference ref) {
        if (ref.isMany()) {
            updateManyValuesReference(ref);
        } else if (element.eIsSet(ref) && !reference.eIsSet(ref)) {
            element.eUnset(ref);
        } else {
            updateSingleValueReference(ref);
        }
    }

    private void updateSingleValueReference(EReference ref) {
        EObject oldValue = (EObject) element.eGet(ref);
        EObject newValue = (EObject) reference.eGet(ref);
        if (oldValue != null && newValue != null && matcher.areSameLogicalElement(oldValue, newValue) && ref.isContainment()) {
            recursiveUpdate(oldValue, newValue);
        } else if (oldValue != newValue && !matcher.areSameLogicalElement(oldValue, newValue)) {
            element.eSet(ref, newValue);
        }
    }

    private void updateManyValuesReference(EReference ref) {
        EList<EObject> oldValues = getManyEObjects(element, ref);
        EList<EObject> newValues = getManyEObjects(reference, ref);
        BiMap<EObject, EObject> matches = matcher.computeMatches(oldValues, newValues);
        removeObsoleteElements(oldValues, matches.keySet());
        reorderMatchingElements(oldValues, newValues, matches);
        addMissingElements(oldValues, newValues, matches);
        if (ref.isContainment()) {
            updateMatchingElements(oldValues, matches);
        }
    }

    /**
     * Removes from the list the elements which are not present in the specified
     * set.
     */
    private void removeObsoleteElements(EList<EObject> values, Set<EObject> tokeep) {
        for (Iterator<EObject> iter = values.iterator(); iter.hasNext(); /**/) {
            EObject obj = iter.next();
            if (!tokeep.contains(obj)) {
                iter.remove();
                orphaned.add(obj);
            }
        }
    }

    /**
     * Reorders the elements in oldValues so that their relative order matches
     * the one of their equivalent in newValues.
     * <p>
     * We do this simply by sorting the oldValues list according to the index of
     * the corresponding element in the newValues list.
     */
    private void reorderMatchingElements(EList<EObject> oldValues, EList<EObject> newValues, BiMap<EObject, EObject> matches) {
        Map<EObject, Integer> targetIndices = Maps.newHashMapWithExpectedSize(newValues.size());
        for (int i = 0; i < newValues.size(); i++) {
            targetIndices.put(newValues.get(i), i);
        }
        Function<EObject, Integer> matchingElementIndex = Functions.compose(Functions.forMap(targetIndices), Functions.forMap(matches));
        ECollections.sort(oldValues, Ordering.natural().onResultOf(matchingElementIndex));
    }

    /**
     * Adds to oldValues the elements from newValues which are do not already
     * have an equivalent. The elements are added at the correct location.
     * <p>
     * The ELists we are are containment ELists, so adding an element from one
     * to the other modifies the source list, messing the index computation. We
     * do this in two passes: compute which elements are missing and the
     * locations they should be inserted in into a simple array, and then apply
     * the necessary changes.
     */
    private void addMissingElements(EList<EObject> oldValues, EList<EObject> newValues, BiMap<EObject, EObject> matches) {
        EObject[] missing = new EObject[newValues.size()];
        for (int i = 0; i < newValues.size(); i++) {
            EObject newValue = newValues.get(i);
            boolean isMissingElement = !matches.containsValue(newValue);
            if (isMissingElement) {
                missing[i] = newValue;
            }
        }
        for (int i = 0; i < missing.length; i++) {
            if (missing[i] != null) {
                oldValues.add(i, missing[i]);
            }
        }
    }

    /**
     * Recursively updates the elements from oldValues which have a matching
     * element using that matching elements as a reference.
     */
    private void updateMatchingElements(EList<EObject> oldValues, BiMap<EObject, EObject> matches) {
        for (Map.Entry<EObject, EObject> entry : matches.entrySet()) {
            recursiveUpdate(entry.getKey(), entry.getValue());
        }
    }

    private void recursiveUpdate(EObject obj, EObject ref) {
        Updater updater = new Updater(matcher, obj, ref);
        updater.isRoot = false;
        updater.setFeaturesToIgnore(this.ignored);
        updater.update();
        this.orphaned.addAll(updater.orphaned);
    }

    private void fixReferences() {
        BiMap<EObject, EObject> matches = matcher.computeMatches(Lists.newArrayList(AllContents.of(reference, true)), Lists.newArrayList(AllContents.of(element, true)));
        for (EObject obj : AllContents.of(element, true)) {
            fixReferences(obj, matches);
        }
    }

    private void fixReferences(EObject obj, BiMap<EObject, EObject> matches) {
        for (EReference ref : obj.eClass().getEAllReferences()) {
            if (!ref.isContainment() && ref.isChangeable() && !ref.isDerived() && ref.getEOpposite() == null) {
                fixReference(obj, ref, matches);
            }
        }
    }

    private void fixReference(EObject obj, EReference ref, BiMap<EObject, EObject> matches) {
        if (ref.isMany()) {
            EList<EObject> values = getManyEObjects(obj, ref);
            // Update remaining references to the reference model to point to
            // the equivalent element in the updated model.
            for (int i = 0; i < values.size(); i++) {
                EObject value = values.get(i);
                if (matches.containsKey(value)) {
                    values.set(i, matches.get(value));
                }
            }
            // Remove references to orphaned elements.
            for (Iterator<EObject> iter = values.iterator(); iter.hasNext(); /**/) {
                EObject eObject = iter.next();
                if (orphaned.contains(eObject)) {
                    iter.remove();
                }
            }
        } else {
            Object value = obj.eGet(ref);
            if (matches.containsKey(value)) {
                obj.eSet(ref, matches.get(value));
            } else if (orphaned.contains(value)) {
                obj.eUnset(ref);
            }
        }
    }

    /**
     * Get the values of a many-valued feature as a collection.
     */
    @SuppressWarnings("unchecked")
    private Collection<Object> getMany(EObject target, EStructuralFeature targetFeature) {
        Object rawValue = target.eGet(targetFeature);
        if (rawValue != null && !(rawValue instanceof Collection<?>)) {
            throw new RuntimeException(MessageFormat.format(Messages.FeatureContributor_unexpectedTypeErrorMsg, targetFeature.getName(), rawValue.getClass()));
        }
        return (Collection<Object>) rawValue;
    }

    /**
     * Get the values of a many-valued feature as a collection.
     */
    @SuppressWarnings("unchecked")
    private EList<EObject> getManyEObjects(EObject target, EReference ref) {
        Object rawValue = target.eGet(ref);
        if (rawValue != null && !(rawValue instanceof EList<?>)) {
            throw new RuntimeException(MessageFormat.format(Messages.FeatureContributor_unexpectedTypeErrorMsg, ref.getName(), rawValue.getClass()));
        }
        return (EList<EObject>) rawValue;
    }

    /**
     * Indicate a list of features which should not be updated.
     * 
     * @param ignore
     *            the features to ignore during the update.
     */
    public void setFeaturesToIgnore(List<EStructuralFeature> ignore) {
        this.ignored = ignore;
    }
}
