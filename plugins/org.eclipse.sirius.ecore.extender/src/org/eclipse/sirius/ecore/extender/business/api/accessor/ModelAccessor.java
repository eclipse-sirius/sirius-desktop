/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;
import org.eclipse.sirius.ext.emf.EReferencePredicate;

/**
 * This class is the common layer to access emf models. You may want to use it
 * when you want to extend Ecore meta-model or if you want to handle the
 * permissions of software trying to access the elements.
 * 
 * @author cbrun
 * 
 */
public class ModelAccessor {

    /**
     * Responsible to handle meta-model extensions.
     */
    CompositeMetamodelExtender extender;

    /**
     * Is able to tell if yes, or no, the user is able to set a value.
     */
    IPermissionAuthority authority = PermissionService.createDefaultPermissionAuthority();

    private boolean silentMode;

    /**
     * Create a new ModelAccessor ready for initialization, you may want to add
     * a new {@link IMetamodelExtender} bringing new capabilities of accessing
     * the data.
     */
    public ModelAccessor() {
        extender = new CompositeMetamodelExtender();
        extender.activate();
    }

    /**
     * Activate new metamodels on the Accessor.
     * 
     * @param mmDescriptors
     *            metamodel descriptors.
     */
    public void activateMetamodels(final Collection<? extends MetamodelDescriptor> mmDescriptors) {
        extender.updateMetamodels(mmDescriptors);
    }

    /**
     * Initialize the ModelAccessor with the current {@link ResourceSet}.
     * 
     * @param set
     *            {@link ResourceSet} containing the data we'll use.
     */
    public void init(final ResourceSet set) {
        extender.init(set);
        authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(set);
    }

    /**
     * the {@link IPermissionAuthority} is used to know if a user may or may not
     * access to an attribute or create an instance. It is also used to keep
     * track of the newly created and modified instances.
     * 
     * @return the current {@link IPermissionAuthority}.
     */
    public IPermissionAuthority getPermissionAuthority() {
        return authority;
    }

    /**
     * The {@link ModelAccessor} may use more than one extender to bring new
     * capabilities, add new {@link IMetamodelExtender} through this method and
     * then init the accessor.
     * 
     * @param newExtender
     *            extender to add to the current accessor.
     * @param priority
     *            priority of this extender considering the others, you'll find
     *            the priority constants in the {@link ExtenderConstants} class.
     * 
     * 
     */
    public void addExtender(final IMetamodelExtender newExtender, final int priority) {
        extender.add(newExtender, priority);
    }

    /**
     * Create a new instance of a given type.
     * 
     * @param name
     *            name of the type to create.
     * @return the new instance or null if this name is not known.
     * @throws MetaClassNotFoundException
     *             if the meta class is not found
     */
    public EObject createInstance(final String name) throws MetaClassNotFoundException {
        final EObject value = extender.createInstance(name);
        if (value != null) {
            authority.notifyNewInstanceCreation(value);
            return value;
        }
        throw new MetaClassNotFoundException(name);
    }

    /**
     * return true if the given name corresponds to a known type.
     * 
     * @param name
     *            name of the type to check.
     * @return true if the given name corresponds to a known type.
     * @since 0.9.0
     */
    public boolean eIsKnownType(final String name) {
        return extender.eIsKnownType(name);
    }

    /**
     * Tell whether the given feature name is valid or not on the instance.
     * 
     * @param object
     *            a model EObject or an ExtendedInstance.
     * @param name
     *            the attribute/reference name.
     * @return true if the given {@link EObject} has an attribute or reference
     *         with the given name.
     * 
     */
    public boolean eValid(final EObject object, final String name) {
        return extender.eValid(object, name);
    }

    /**
     * Retrieves the value of the attribute/reference <code>name</code> of the
     * instance <code>instance</code>.
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the attribute/reference to get.
     * @return the attribute/reference value.
     * @throws FeatureNotFoundException
     *             if the feature is not found.
     */
    public Object eGet(final EObject instance, final String name) throws FeatureNotFoundException {
        if (!eValid(instance, name)) {
            throw new FeatureNotFoundException();
        }
        return extender.eGet(instance, name);
    }

    /**
     * Set a value on an instance feature. <BR>
     * <B>Warning</B> : When you call this method on a multiple eReference, the
     * value is added to the existing values (the eReference is not clear before
     * the adding).
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the feature to set.
     * @param value
     *            value to set
     * @return the set value.
     * @throws FeatureNotFoundException
     *             if the feature is not found.
     * @throws LockedInstanceException
     *             when an instance is locked and should not be modified.
     */
    public Object eSet(final EObject instance, final String name, final Object value) throws FeatureNotFoundException, LockedInstanceException {
        if (authority.canEditFeature(instance, name)) {
            Object feedback = null;
            if (!eIsMany(instance, name)) {
                feedback = extender.eSet(instance, name, value);
            } else {
                feedback = extender.eAdd(instance, name, value);
            }

            if (feedback == null) {
                throw new FeatureNotFoundException(name);
            }
            authority.notifyInstanceChange(instance);
            return feedback;
        } else if (!silentMode) {
            throw new LockedInstanceException(instance);
        }
        return null;
    }

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
     * @throws FeatureNotFoundException
     *             if the feature is not found.
     * @throws LockedInstanceException
     *             when an instance is locked and should not be modified.
     */
    public void eAdd(final EObject instance, final String name, final Object value) throws FeatureNotFoundException, LockedInstanceException {
        if (authority.canEditFeature(instance, name)) {
            Object result = null;
            if (value instanceof EObject) {
                if (authority.canEditInstance((EObject) value)) {
                    result = extender.eAdd(instance, name, value);
                }
            } else {
                result = extender.eAdd(instance, name, value);
            }
            if (result == null) {
                throw new FeatureNotFoundException(name);
            }
            authority.notifyInstanceChange(instance);
        } else if (!silentMode) {
            throw new LockedInstanceException(instance);
        }
    }

    /**
     * Clears an extension value.
     * 
     * @param instance
     *            the instance.
     * @param name
     *            the name of the extension.
     * @throws LockedInstanceException
     *             when an instance is locked and should not be modified.
     */
    public void eClear(final EObject instance, final String name) throws LockedInstanceException {
        if (authority.canEditFeature(instance, name)) {
            final Object value = extender.eClear(instance, name);
            if (value != null) {
                authority.notifyInstanceChange(instance);
            }
        } else if (!silentMode) {
            throw new LockedInstanceException(instance);
        }
    }

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
     * @return the removed value.
     * @throws LockedInstanceException
     *             when an instance is locked and should not be modified.
     */
    public Object eRemove(final EObject instance, final String name, final Object value) throws LockedInstanceException {
        if (authority.canEditFeature(instance, name)) {
            final Object result = extender.eRemove(instance, name, value);
            if (result != null) {
                authority.notifyInstanceChange(instance);
            }
            return result;
        } else if (!silentMode) {
            throw new LockedInstanceException(instance);
        }

        return null;
    }

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
     * @throws FeatureNotFoundException
     *             if the feature is not found on the given instance.
     */
    public boolean eIsMany(final EObject instance, final String featureName) throws FeatureNotFoundException {
        final Boolean result = extender.eIsMany(instance, featureName);
        if (result != null) {
            return result.booleanValue();
        }
        throw new FeatureNotFoundException(MessageFormat.format(Messages.ModelAccessor_error_featureNotFound, featureName, instance));
    }

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
     * @throws FeatureNotFoundException
     *             if the feature is not found on the instance.
     */
    public boolean eIsContainment(final EObject instance, final String featureName) throws FeatureNotFoundException {
        final Boolean result = extender.eIsContainment(instance, featureName);
        if (result != null) {
            return result.booleanValue();
        }
        throw new FeatureNotFoundException(featureName);
    }

    /**
     * Return the container of <code>instance</code>.
     * 
     * @param instance
     *            the instance.
     * @return the container of <code>instance</code>.
     */
    public EObject eContainer(final EObject instance) {
        return extender.eContainer(instance);
    }

    /**
     * Check whether the given instance is compatible with the given type name.
     * 
     * @param instance
     *            any model element.
     * @param typeName
     *            name of a type.
     * @return true if the given element is of the given type.
     * @throws MetaClassNotFoundException
     */
    public boolean eInstanceOf(final EObject instance, final String typeName) {
        return extender.eInstanceOf(instance, typeName);
    }

    /**
     * Return an {@link Iterator} browsing the whole model.
     * 
     * @param root
     *            root model element.
     * 
     * @return an Iterator browsing the whole model.
     */
    public TreeIterator<EObject> eAllContents(final EObject root) {
        return extender.eAllContents(root);
    }

    /**
     * 
     * Remove the object without deleting the references going to this object.
     * Basically it means removing it from it's container.
     * 
     * @param objectToRemove
     *            object to remove.
     * 
     */
    public void eRemove(final EObject objectToRemove) {
        if (authority.canDeleteInstance(objectToRemove)) {
            final EObject container = extender.eContainer(objectToRemove);
            if (container != null) {
                if (authority.canEditInstance(container)) {
                    final String featureName = extender.eContainingFeatureName(objectToRemove);
                    final Object value = extender.eRemove(container, featureName, objectToRemove);
                    if (value != null) {
                        authority.notifyInstanceDeletion(objectToRemove);
                    }
                }
            }
        }
    }

    /**
     * Remove the Object from its container and delete all the dangling
     * references.
     * 
     * @param objectToRemove
     *            object to delete.
     * @param xref
     *            the optional cross-referencer to use to locate all the
     *            dangling references.
     */
    public void eDelete(final EObject objectToRemove, final ECrossReferenceAdapter xref) {
        eDelete(objectToRemove, xref, null);
    }

    /**
     * Removes the Object from its container and delete all the dangling
     * references, except the one held by an object which EClass is listed in
     * the given Types to ignore list.
     * 
     * @param objectToRemove
     *            object to delete.
     * @param xref
     *            the optional cross-referencer to use to locate all the
     *            dangling references
     * @param isReferencesToIgnorePredicate
     *            a predicate indicating if a given reference should be ignored
     *            during deletion or not (can be null if all references should
     *            be considered)
     */
    public void eDelete(EObject objectToRemove, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
        eDelete(objectToRemove, xref, isReferencesToIgnorePredicate, true);
    }

    /**
     * Removes the Object from its container and delete all the dangling
     * references, except the one held by an object which EClass is listed in
     * the given Types to ignore list.
     * 
     * @param objectToRemove
     *            object to delete.
     * @param xref
     *            the optional cross-referencer to use to locate all the
     *            dangling references
     * @param isReferencesToIgnorePredicate
     *            a predicate indicating if a given reference should be ignored
     *            during deletion or not (can be null if all references should
     *            be considered)
     * 
     * @param simpleRemoveShouldBePerformedIfDanglingReferenceIsNotChangeable
     *            indicates whether a "simple" remove should be performed if one
     *            of the features referencing the element to delete is not
     *            editable. If false, then we will delete all references that
     *            can be deleted and leave the not changeable features
     *            unchanged.
     */
    private void eDelete(EObject objectToRemove, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate, boolean simpleRemoveShouldBePerformedIfDanglingReferenceIsNotChangeable) {
        // Step 1: getting cross referencer for the adapters of the object to
        // remove (if needed)
        final ECrossReferenceAdapter effectiveXRef;
        if (xref == null) {
            effectiveXRef = ECrossReferenceAdapter.getCrossReferenceAdapter(objectToRemove);
        } else {
            effectiveXRef = xref;
        }

        // Step 2: determine which kind of deletion should be performed
        // checking for authority and feature
        boolean authorityAllowsDeletion = false;
        boolean allFeaturesAreChangeable = true;
        if (authority.canDeleteInstance(objectToRemove)) {
            final EObject container = extender.eContainer(objectToRemove);
            // If container is null, then element has already been deleted.
            // No need to delete
            if (container == null) {
                authorityAllowsDeletion = true;
            } else {
                if (authority.canEditInstance(container)) {
                    // Determine if all features can be edited.
                    // This can throw a Locked Instance exception if one of the
                    // element holding the features is not editable according to
                    // the
                    // authority
                    allFeaturesAreChangeable = allReferencesCanBeEdited(objectToRemove, effectiveXRef, isReferencesToIgnorePredicate);
                    authorityAllowsDeletion = true;
                }
            }
        }

        // Step 3: perform deletion
        if (authorityAllowsDeletion) {
            // If all features are changeable or if when all features are not
            // changeable we should try to delete every reference we can
            if (allFeaturesAreChangeable || !allFeaturesAreChangeable && !simpleRemoveShouldBePerformedIfDanglingReferenceIsNotChangeable) {
                // We properly delete the element and all cross-references
                Object deletedElement = extender.eDelete(objectToRemove, effectiveXRef, isReferencesToIgnorePredicate);
                if (deletedElement != null) {
                    authority.notifyInstanceDeletion(objectToRemove);
                }
            } else {
                // Here a "simple" remove should be performed
                eRemove(objectToRemove);
            }
        } else {
            // If the authority does not allow to perform deletion, we must
            // throw a Locked Instance exception
            throw new LockedInstanceException(objectToRemove);
        }
    }

    /**
     * Remove inverse cross references of the specified {@link EObject}. This
     * method will not remove an element from its container except if the used
     * {@link ECrossReferenceAdapter} return
     * {@link org.eclipse.emf.ecore.EStructuralFeature.Setting} for the
     * containment feature (this is not the case for the default implementation)
     * . See
     * {@link ModelAccessor#eDelete(EObject, ECrossReferenceAdapter, EReferencePredicate)}
     * to delete an element, i.e. remove it from its container and then remove
     * its inverse cross references.
     * 
     * This method will throw a {@link LockedInstanceException} if the
     * permission authority does not allow to edit one of the feature to modify.
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
    public Collection<EObject> eRemoveInverseCrossReferences(EObject eObject, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
        // Step 1: getting cross referencer for the adapters of the object to
        // remove (if needed)
        final ECrossReferenceAdapter effectiveXRef;
        if (xref == null) {
            effectiveXRef = ECrossReferenceAdapter.getCrossReferenceAdapter(eObject);
        } else {
            effectiveXRef = xref;
        }

        // Step 2: determine if all inverse cross references can be removed by
        // checking for authority and feature
        // Determine if all features can be edited.
        // This can throw a Locked Instance exception if one of the
        // element holding a changeable features is not editable according to
        // the authority
        allReferencesCanBeEdited(eObject, effectiveXRef, isReferencesToIgnorePredicate);

        // Remove all inverse cross references on non-ignored changeable
        // features
        return extender.eRemoveInverseCrossReferences(eObject, effectiveXRef, isReferencesToIgnorePredicate);
    }

    /**
     * Check with the authority that all the references to the specified target
     * object accessible from the cross-referencer can be edited. Default to
     * <code>true</code> if no cross-referencer is specified.
     * 
     * @param isReferencesToIgnorePredicate
     *            a predicate indicating if a given reference should be ignored
     *            during deletion or not
     */
    private boolean allReferencesCanBeEdited(final EObject target, final ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
        boolean allReferencesCanBeEdited = true;
        if (xref != null) {
            final Collection<Setting> refs = xref.getInverseReferences(target, true);
            for (Setting setting : refs) {
                final EObject eObj = setting.getEObject();
                final EStructuralFeature feature = setting.getEStructuralFeature();
                // For each reference to the element to deleten we check that it
                // can be deleted
                // Check 1: if the containing type of the reference is a type to
                // ignore, we consider that we can delete it
                boolean isFeatureToIgnore = isReferencesToIgnorePredicate != null && feature instanceof EReference && isReferencesToIgnorePredicate.apply((EReference) feature);

                if (!isFeatureToIgnore) {
                    // Check 2: we first check if the reference is changeable
                    // (and if not, that it is derived)
                    allReferencesCanBeEdited = allReferencesCanBeEdited && (feature.isChangeable() || !feature.isDerived());

                    // Check 3: we then check that we have permission to edit
                    // this feature
                    if (!authority.canEditFeature(eObj, feature.getName())) {
                        // If it is not the case, we throw a locked instance
                        // exception
                        throw new LockedInstanceException(eObj);
                    }
                }
            }
        }
        return allReferencesCanBeEdited;
    }

    /**
     * Tell whether an {@link EObject} is a pure extension or if it should be
     * considered as a model element.
     * 
     * @param next
     *            any {@link EObject}.
     * @return true if the given instance is an extension instance opposed to
     *         the model intrinsic instances.
     */
    public boolean isExtension(final EObject next) {
        return extender.isExtension(next);
    }

    /**
     * return true if the feature named on the given {@link EObject} is an
     * extension feature and not an intrinsic one.
     * 
     * @param next
     *            any {@link EObject}.
     * @param name
     *            any feature name.
     * @return true if the feature named on the given {@link EObject} is an
     *         extension feature and not an intrinsic one.
     */
    public boolean isExtension(final EObject next, final String name) {
        return extender.isExtension(next, name);
    }

    /**
     * return true if the given {@link EObject} has extension features.
     * 
     * @param next
     *            any {@link EObject}.
     * @return true if the given {@link EObject} has extension features.
     */
    public boolean hasExtension(final EObject next) {
        return extender.hasExtension(next);
    }

    /**
     * return a list of {@link String} composed of all the attribute names
     * associated with this instance.
     * 
     * @param instance
     *            any {@link EObject}.
     * @return a list of {@link String} composed of all the attribute names
     *         associated with this instance.
     */
    public Iterator<String> getAllAttributesName(final EObject instance) {
        return extender.getContributedAttributeNames(instance);
    }

    /**
     * return a list of {@link String} composed of all the references names
     * associated with this instance.
     * 
     * @param instance
     *            any {@link EObject}.
     * @return a list of {@link String} composed of all the references names
     *         associated with this instance.
     */
    public Iterator<String> getAllReferencesName(final EObject instance) {
        return extender.getContributedReferenceNames(instance);
    }

    /**
     * return a list of {@link String} composed of all the extended attribute
     * names associated with this instance.
     * 
     * @param instance
     *            any {@link EObject}.
     * @return a list of {@link String} composed of all the extended attribute
     *         names associated with this instance.
     */
    public Iterator<String> getAllExtendedAttributesName(final EObject instance) {
        final Collection<String> result = new ArrayList<String>();
        final Iterator<String> it = extender.getContributedAttributeNames(instance);
        while (it.hasNext()) {
            final String name = it.next();
            if (extender.isExtension(instance, name)) {
                result.add(name);
            }
        }
        return result.iterator();
    }

    /**
     * return a list of {@link String} composed of all the extended references
     * names associated with this instance.
     * 
     * @param instance
     *            any {@link EObject}.
     * @return a list of {@link String} composed of all the extended references
     *         names associated with this instance.
     */
    public Iterator<String> getAllExtendedReferencesName(final EObject instance) {
        final Collection<String> result = new ArrayList<String>();
        final Iterator<String> it = extender.getContributedReferenceNames(instance);
        while (it.hasNext()) {
            final String name = it.next();
            if (extender.isExtension(instance, name)) {
                result.add(name);
            }
        }
        return result.iterator();
    }

    /**
     * Iterate over the model starting from the given root and return a list of
     * elements of the given type name.
     * 
     * @param root
     *            the place we'll start to browse.
     * @param typeName
     *            the type name of the elements we are looking for.
     * @return a collection of {@link EObject}.
     */
    public Collection<EObject> eAllContents(final EObject root, final String typeName) {
        return extender.eAllContents(root, typeName);
    }

    /**
     * return a nice string representing the EObject name.
     * 
     * @param element
     *            an {@link EObject}.
     * @param useTypeWhenNoName
     *            tell whether we should use the type of an Element when we
     *            don't have the name or not.
     * @return a {@link String}
     */
    public String getQualifiedName(final EObject element, final boolean useTypeWhenNoName) {
        return extender.getQualifiedName(element, useTypeWhenNoName);
    }

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
    public Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(final EObject target) {
        return extender.getAllExtensionFeatureDescriptions(target);
    }

    /**
     * If the model Accessor is not in silent mode then
     * {@link LockedInstanceException} will be thrown on invalid accesses.
     * 
     * @param silent
     *            true if you want to enable the silent mode, false otherwise.
     */
    public void enableSilentMode(final boolean silent) {
        this.silentMode = silent;
    }

    /**
     * Clear all the stateful information a ModelAccessor may have kept.
     */
    public void dispose() {
        extender.dispose();
    }

}
