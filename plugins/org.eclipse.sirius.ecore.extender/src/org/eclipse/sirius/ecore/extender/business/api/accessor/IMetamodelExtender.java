/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.ext.emf.EReferencePredicate;

/**
 * This Interface defines a metamodel extender. An extender add new information
 * on a given metamodel using any kind of mechanism. It should provide basic
 * primitive operations like eSet, eGet or eAdd.
 * 
 * @author cbrun
 * 
 */
public interface IMetamodelExtender {

    /**
     * Initialize the extender with the model.
     * 
     * @param set
     *            the model.
     */
    void init(ResourceSet set);

    /**
     * Activates this Meta model extender.
     * 
     */
    void activate();

    /**
     * Activates this Meta model extender.
     * 
     * @param metamodelDescriptors
     *            list of descriptor the extender might use.
     */
    void updateMetamodels(Collection<? extends MetamodelDescriptor> metamodelDescriptors);

    /**
     * Deactivates this extender.
     */
    void deactivate();

    /**
     * Returns <code>true</code> if this extender is active.
     * 
     * @return <code>true</code> if this extender is active.
     */
    boolean isActive();

    /**
     * Should clear and empty the extender.
     */
    void dispose();

    /**
     * Return the new instance or null if this name is not known or cannot be
     * instantiated.
     * 
     * @param name
     *            name of the type to create.
     * @return the new instance or null if this name is not known.
     */
    EObject createInstance(String name);

    /**
     * Return true if the given name corresponds to a known type.
     * 
     * @param name
     *            name of the type to check.
     * @return true if the given name corresponds to a known type.
     * @since 0.9.0
     */
    boolean eIsKnownType(String name);

    /**
     * Return true if the given {@link EObject} has an attribute or reference
     * with the given name.
     * 
     * @param object
     *            a model EObject or an ExtendedInstance.
     * @param name
     *            the attribute/reference name.
     * @return true if the given {@link EObject} has an attribute or reference
     *         with the given name.
     * 
     */
    boolean eValid(EObject object, String name);

    /**
     * Retrieves the value of the attribute/reference <code>name</code> of the
     * instance <code>instance</code>.
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the attribute/reference to get.
     * @return the attribute/reference value.
     */
    Object eGet(EObject instance, String name);

    /**
     * Return the set value if the feature was found, null otherwise.
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the feature to set.
     * @param value
     *            value to set
     * @return the set value if the feature was found, null otherwise.
     */
    Object eSet(EObject instance, String name, Object value);

    /**
     * Adds the value <code>value</code> into the collection <code>name</code>
     * of the instance <code>instance</code>. Auto adapt if the feature name is
     * not a collection.
     * 
     * @param instance
     *            the instance.
     * @param name
     *            name of the reference.
     * @param value
     *            the value to add.
     * @return the added value if the feature was found, null otherwise.
     */
    Object eAdd(EObject instance, String name, Object value);

    /**
     * Clears an extension value.
     * 
     * @param instance
     *            the instance.
     * @param name
     *            the name of the extension.
     * @return the cleared instance if the feature was found, null otherwise.
     */
    Object eClear(EObject instance, String name);

    /**
     * Removes <code>value</code> from the collection <code>name</code> of
     * <code>instance</code>.
     * 
     * @param instance
     *            the instance.
     * @param name
     *            the name of the reference.
     * @param value
     *            the value to remove.
     * @return the removed value if the feature was found, null otherwise.
     */
    Object eRemove(EObject instance, String name, Object value);

    /**
     * Return <code>true</code> if the feature <code>featureName</code> of the
     * object <code>instance</code> is a collection.
     * 
     * @param instance
     *            the instance.
     * @param featureName
     *            the name of the feature.
     * @return <code>true</code> if the feature <code>featureName</code> of the
     *         object <code>instance</code> is a collection.
     */
    Boolean eIsMany(EObject instance, String featureName);

    /**
     * Return <code>true</code> if the feature <code>featureName</code> of the
     * object <code>instance</code> is a containment collection.
     * 
     * @param instance
     *            the instance.
     * @param featureName
     *            the name of the feature.
     * @return <code>true</code> if the feature <code>featureName</code> of the
     *         object <code>instance</code> is a containment collection.
     */
    Boolean eIsContainment(EObject instance, String featureName);

    /**
     * Return the container of <code>instance</code>.
     * 
     * @param instance
     *            the instance.
     * @return the container of <code>instance</code>.
     */
    EObject eContainer(EObject instance);

    /**
     * Return the set value if the feature was found, null otherwise.
     * 
     * @param instance
     *            any model element.
     * @param typeName
     *            name of a type, might be qualified with EPackageURI#/TypeName
     * @return true if the given element is of the given type.
     */
    boolean eInstanceOf(EObject instance, String typeName);

    /**
     * Return an Iterator browsing the whole model.
     * 
     * @param root
     *            root model element.
     * 
     * @return an Iterator browsing the whole model.
     */
    Iterator<EObject> eContents(EObject root);

    /**
     * Return the name of the feature containing this instance.
     * 
     * @param objectToRemove
     *            any {@link EObject}.
     * @return the name of the feature containing this instance.
     * 
     */
    String eContainingFeatureName(EObject objectToRemove);

    /**
     * Remove the Object from its container and delete all the dangling
     * references.
     * 
     * @param objectToRemove
     *            object to delete.
     * @param xref
     *            the optional cross-referencer to use to locate all the
     *            dangling references.
     * @return the deleted instance or null if it was not able to delete it.
     */
    EObject eDelete(EObject objectToRemove, ECrossReferenceAdapter xref);

    /**
     * Remove the Object from its container and delete all the dangling
     * references.
     * 
     * @param objectToRemove
     *            object to delete.
     * @param xref
     *            the optional cross-referencer to use to locate all the
     *            dangling references.
     * @param isReferencesToIgnorePredicate
     *            a predicate indicating if a given reference should be ignored
     *            during deletion or not (can be null if all references should
     *            be considered)
     * @return the deleted instance or null if it was not able to delete it.
     */
    EObject eDelete(EObject objectToRemove, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate);

    /**
     * Remove inverse cross references of the specified {@link EObject}. This
     * method will not remove an element from its container except if the used
     * {@link ECrossReferenceAdapter} return
     * {@link org.eclipse.emf.ecore.EStructuralFeature.Setting} for the
     * containment feature (this is not the case for the default
     * implementation). This method is called from
     * {@link IMetamodelExtender#eDelete(EObject, ECrossReferenceAdapter, isReferencesToIgnorePredicate)} during the
     * deletion of an element.
     * 
     * @param eObject
     *            the {@link EObject} for which remove cross references
     * @param xref
     *            the optional cross-referencer to use to locate all the cross
     *            references
     * @param isReferencesToIgnorePredicate
     *            a predicate indicating if a given reference should be ignored
     *            during removal or not (can be null if all references should be
     *            considered)
     * @return a Collection of impacted {@link EObject objects} of this inverse
     *         cross references removal
     */
    Collection<EObject> eRemoveInverseCrossReferences(EObject eObject, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate);

    /**
     * Return true if the given instance comes from a metamodel extension
     * mechanism, false otherwise.
     * 
     * @param next
     *            an instance.
     * @return true if the given instance comes from a metamodel extension
     *         mechanism, false otherwise.
     */
    boolean isExtension(EObject next);

    /**
     * Return true if the instance have extended features.
     * 
     * @param next
     *            an instance.
     * @return true if the instance have extended features.
     */
    boolean hasExtension(EObject next);

    /**
     * Return a list of String representing all the attribute names the
     * extension bring.
     * 
     * @param instance
     *            instance to consider.
     * @return a list of String representing all the attribute names the
     *         extension bring.
     */
    Iterator<String> getContributedAttributeNames(EObject instance);

    /**
     * Return a list of String representing all the references names the
     * extension bring.
     * 
     * @param instance
     *            instance to consider.
     * @return a list of String representing all the references names the
     *         extension bring.
     */
    Iterator<String> getContributedReferenceNames(EObject instance);

    /**
     * Return true if the feature named on the given {@link EObject} is an
     * extension feature and not an intrinsic one.
     * 
     * @param next
     *            any {@link EObject}.
     * @param name
     *            any feature name.
     * @return true if the feature named on the given {@link EObject} is an
     *         extension feature and not an intrinsic one.
     */
    boolean isExtension(EObject next, String name);

    /**
     * Return a {@link String} representing the qualified name.
     * 
     * @param element
     *            an {@link EObject}.
     * @param useTypeWhenNoName
     *            tell whether we should use the type of an Element when we
     *            don't have the name or not.
     * @return a {@link String}
     */
    String getQualifiedName(EObject element, boolean useTypeWhenNoName);

    /**
     * Return a list composed of {@link ExtensionFeatureDescription} helpful to
     * handle the extended feature. It will help in getting the features
     * containment or not, the cardinality or the fact that it is mandatory or
     * not for instance.
     * 
     * @param target
     *            any {@link EObject}.
     * @return a list composed of {@link ExtensionFeatureDescription} helpful to
     *         handle the extended feature. It will help in getting the features
     *         containment or not, the cardinality or the fact that it is
     *         mandatory or not for instance.
     */
    Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(EObject target);

    /**
     * Return true if this {@link EObject} should not be browsed by the other
     * {@link IMetamodelExtender}.
     * 
     * @param root
     *            any EObject.
     * @return true if this {@link EObject} should not be browsed by the other
     *         {@link IMetamodelExtender}.
     */
    boolean preventFromBrowsing(EObject root);

}
