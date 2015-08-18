/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.AbstractMetamodelExtender;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtensionFeatureDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ext.emf.EReferencePredicate;

import com.google.common.base.Predicates;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multimap;

/**
 * This metamodel Extender accesses the intrinsic data of an EObject.
 * 
 * @author cbrun
 * 
 */
public class EcoreIntrinsicExtender extends AbstractMetamodelExtender {

    private static final String SEPARATOR = "."; //$NON-NLS-1$

    private static PackageRegistryIndex platformIndex = new PackageRegistryIndex(EPackage.Registry.INSTANCE, Predicates.<EPackage> alwaysTrue());

    private Multimap<String, EClass> viewpointIndex = HashMultimap.create();

    private Collection<? extends MetamodelDescriptor> lastDescriptors;

    @Override
    public EObject createInstance(final String name) {
        EObject result = null;
        final Iterator<EClass> it = getEClassesFromName(name);
        while (result == null && it.hasNext()) {
            final EClass eClass = it.next();
            if (!eClass.isAbstract()) {
                result = EcoreUtil.create(eClass);
            }
        }
        if (result == null && name != null && name.indexOf("#") > 0) { //$NON-NLS-1$
            final URI eClassURI = URI.createURI(name);
            final EObject eobj = new ResourceSetImpl().getEObject(eClassURI, true);
            if (eobj instanceof EClass) {
                result = EcoreUtil.create((EClass) eobj);
            }
        }
        return result;
    }

    @Override
    public boolean eIsKnownType(final String name) {
        return findFirstEClassFromName(name) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object eAdd(final EObject instance, final String name, final Object value) {
        if (eValid(instance, name)) {
            if (eIsMany(instance, name).booleanValue()) {
                final Object object = instance.eGet(instance.eClass().getEStructuralFeature(name));
                if (object instanceof Collection && value instanceof Collection) {
                    ((Collection<Object>) object).addAll((Collection<Object>) value);
                } else if (object instanceof Collection && value != null) {
                    ((Collection<Object>) object).add(value);
                }
            } else {
                eSet(instance, name, value);
            }
            return instance;
        }
        return null;

    }

    @Override
    public Iterator<EObject> eContents(final EObject root) {
        return root.eContents().iterator();
    }

    @Override
    public Object eClear(final EObject instance, final String name) {
        if (eValid(instance, name)) {
            final EStructuralFeature feat = instance.eClass().getEStructuralFeature(name);
            final Object value = instance.eGet(feat);
            if (value != null) {
                if (value instanceof Collection) {
                    ((Collection<?>) value).clear();
                } else {
                    instance.eSet(feat, null);
                }
            }
            return instance;
        }
        return null;

    }

    @Override
    public EObject eContainer(final EObject instance) {
        return instance.eContainer();
    }

    @Override
    public String eContainingFeatureName(final EObject objectToRemove) {
        if (objectToRemove.eContainingFeature() != null) {
            return objectToRemove.eContainingFeature().getName();
        }
        return null;
    }

    @Override
    public EObject eDelete(final EObject objectToRemove, final ECrossReferenceAdapter xref) {
        return eDelete(objectToRemove, xref, null);
    }

    @Override
    public EObject eDelete(EObject objectToRemove, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
        if (xref == null) {
            // If no cross referencer can be found,
            // we simply remove the element from its container
            EcoreUtil.delete(objectToRemove);
        } else {
            // Step 1: remove all references to the element to delete
            eRemoveInverseCrossReferences(objectToRemove, xref, isReferencesToIgnorePredicate);

            // Step 2: remove the element from its container
            EcoreUtil.remove(objectToRemove);
        }
        return objectToRemove;
    }
    
    @Override
    public Collection<EObject> eRemoveInverseCrossReferences(EObject eObject, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
        Collection<EObject> impactedEObjects = new LinkedHashSet<EObject>();
        Collection<Setting> inverseReferences = xref.getInverseReferences(eObject, true);
        Collection<Setting> containmentReferences = getContainmentReferences(inverseReferences);
        Collection<Setting> otherReferences = getNonContainmentReferences(inverseReferences);
        for (EStructuralFeature.Setting setting : otherReferences) {

            // If reference is changeable and should not be ignored
            boolean isChangeableFeature = setting.getEStructuralFeature().isChangeable();
            boolean isFeatureToIgnore = isReferencesToIgnorePredicate != null && setting.getEStructuralFeature() instanceof EReference
                    && isReferencesToIgnorePredicate.apply((EReference) setting.getEStructuralFeature());

            if (isChangeableFeature && !isFeatureToIgnore) {
                // we delete it
                EcoreUtil.remove(setting, eObject);
                impactedEObjects.add(setting.getEObject());
            }
        }
        for (EStructuralFeature.Setting setting : containmentReferences) {

            // If reference is changeable and should not be ignored
            boolean isChangeableFeature = setting.getEStructuralFeature().isChangeable();
            boolean isFeatureToIgnore = isReferencesToIgnorePredicate != null && setting.getEStructuralFeature() instanceof EReference
                    && isReferencesToIgnorePredicate.apply((EReference) setting.getEStructuralFeature());

            if (isChangeableFeature && !isFeatureToIgnore) {
                // we delete it
                EcoreUtil.remove(setting, eObject);
                impactedEObjects.add(setting.getEObject());
            }
        }
        return impactedEObjects;
    }

    private Collection<Setting> getContainmentReferences(Collection<Setting> inverseReferences) {
        Collection<Setting> containmentReferences = new ArrayList<EStructuralFeature.Setting>();
        for (EStructuralFeature.Setting setting : inverseReferences) {
            EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
            if (eStructuralFeature instanceof EReference) {
                EReference eReference = (EReference) eStructuralFeature;
                if (eReference.isContainment()) {
                    containmentReferences.add(setting);
                }
            }
        }
        return containmentReferences;
    }

    private Collection<Setting> getNonContainmentReferences(Collection<Setting> inverseReferences) {
        Collection<Setting> containmentReferences = new ArrayList<EStructuralFeature.Setting>();
        for (EStructuralFeature.Setting setting : inverseReferences) {
            EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
            if (eStructuralFeature instanceof EReference) {
                EReference eReference = (EReference) eStructuralFeature;
                if (!eReference.isContainment()) {
                    containmentReferences.add(setting);
                }
            }
        }
        return containmentReferences;
    }

    @Override
    public Object eGet(final EObject instance, final String name) {
        if (instance.eClass().getEStructuralFeature(name) != null) {
            return instance.eGet(instance.eClass().getEStructuralFeature(name));
        }
        // do nothing on error if we don't find the value, then may be
        // another extender will !
        return null;
    }

    @Override
    public boolean eInstanceOf(final EObject instance, final String name) {
        if ("EObject".equals(name) && instance != null) { //$NON-NLS-1$
            return true;
        }
        boolean result = eInstanceOf(instance.eClass(), name);
        if (!result && name != null && name.indexOf("#") > 0) { //$NON-NLS-1$
            try {
                final URI eClassURI = URI.createURI(name);
                final EObject eobj = new ResourceSetImpl().getEObject(eClassURI, true);
                if (eobj instanceof EClass) {
                    result = ((EClass) eobj).isInstance(instance);
                }
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // then it's not a valid URI, nothing more to do ! We really
                // can't know what exception will pops up :'(
            }
            // CHECKSTYLE:ONN
        }
        return result;
    }

    private boolean eInstanceOf(final EClass eClass, final String superClassName) {
        boolean result = false;
        if (eClass.getName().equals(superClassName)) {
            return true;
        }
        Iterator<EClass> it = getEClassesFromName(superClassName);
        while (!result && it.hasNext()) {
            EClass possibleSuperType = it.next();
            result = possibleSuperType.isSuperTypeOf(eClass);
        }
        return result;
    }

    @Override
    public Boolean eIsContainment(final EObject instance, final String featureName) {
        Boolean result = null;
        if (instance.eClass().getEStructuralFeature(featureName) instanceof EReference) {
            result = new Boolean(((EReference) instance.eClass().getEStructuralFeature(featureName)).isContainment());
        }
        if (result == null && instance.eClass().getEStructuralFeature(featureName) instanceof EAttribute) {
            result = Boolean.TRUE;
        }
        return result;
    }

    @Override
    public Boolean eIsMany(final EObject instance, final String featureName) {
        if (instance.eClass().getEStructuralFeature(featureName) != null) {
            return new Boolean(instance.eClass().getEStructuralFeature(featureName).isMany());
        }
        return null;
    }

    @Override
    public Object eRemove(final EObject instance, final String name, final Object value) {
        if (eValid(instance, name)) {
            EcoreUtil.remove(instance, instance.eClass().getEStructuralFeature(name), value);
            return value;
        }
        return null;
    }

    @Override
    public Object eSet(final EObject instance, final String name, final Object value) {
        if (eValid(instance, name)) {
            final EStructuralFeature feature = instance.eClass().getEStructuralFeature(name);
            if (feature.getEType() instanceof EEnum && (value instanceof String || value instanceof Integer)) {
                // Try to retrieve the expected literal.
                EEnumLiteral literal = getEnumLiteral((EEnum) feature.getEType(), value);
                if (literal != null) {
                    instance.eSet(feature, literal.getInstance());
                }
            } else if (feature instanceof EAttribute && value instanceof String) {
                final EDataType attributeType = ((EAttribute) feature).getEAttributeType();
                final Object objectValue = EcoreUtil.createFromString(attributeType, (String) value);
                instance.eSet(feature, objectValue);
            } else {
                instance.eSet(feature, value);
            }
            return instance;
        }
        return null;
    }

    private EEnumLiteral getEnumLiteral(final EEnum eenum, final Object value) {
        EEnumLiteral literal = null;
        if (value instanceof Integer) {
            literal = eenum.getEEnumLiteral((Integer) value);
        } else if (value instanceof String) {
            literal = eenum.getEEnumLiteral((String) value);
            if (literal == null) {
                literal = eenum.getEEnumLiteralByLiteral((String) value);
            }
        }
        return literal;
    }

    @Override
    public boolean eValid(final EObject object, final String name) {
        return object.eClass().getEStructuralFeature(name) != null;
    }

    @Override
    public Iterator<String> getContributedAttributeNames(final EObject next) {
        final Collection<String> attributeNames = new ArrayList<String>();
        final Iterator<EAttribute> it = next.eClass().getEAllAttributes().iterator();
        while (it.hasNext()) {
            final EAttribute attr = it.next();
            attributeNames.add(attr.getName());
        }
        return attributeNames.iterator();
    }

    @Override
    public Iterator<String> getContributedReferenceNames(final EObject instance) {
        final Collection<String> referencesNames = new ArrayList<String>();
        final Iterator<EReference> it = instance.eClass().getEAllReferences().iterator();
        while (it.hasNext()) {
            final EReference ref = it.next();
            referencesNames.add(ref.getName());
        }
        return referencesNames.iterator();
    }

    @Override
    public boolean hasExtension(final EObject next) {
        return false;
    }

    @Override
    public void init(final ResourceSet set) {
        // platformIndex.init();
    }

    private void addTypesToSiriusIndex(final EPackage value) {
        for (EClass cur : Iterables.filter(value.getEClassifiers(), EClass.class)) {
            viewpointIndex.put(cur.getName(), cur);
            viewpointIndex.put(value.getName() + EcoreIntrinsicExtender.SEPARATOR + cur.getName(), cur);
        }
    }

    @Override
    public boolean isExtension(final EObject next) {
        return false;
    }

    private Iterator<EClass> getEClassesFromName(final String name) {
        return Iterators.concat(viewpointIndex.get(name).iterator(), platformIndex.getEClassesFromName(name).iterator());
    }

    private EClass findFirstEClassFromName(final String name) {
        Iterator<EClass> it = getEClassesFromName(name);
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    /**
     * Create a new instance.
     * 
     * @param eClass
     *            any EClass.
     * @return instance built from the EClass.
     */
    public EObject createInstance(final EClass eClass) {
        return EcoreUtil.create(eClass);
    }

    @Override
    public void dispose() {
        // platformIndex.clear();
    }

    @Override
    public String getQualifiedName(final EObject element, final boolean b) {
        return EMFUtil.getQualifiedName(element, b);
    }

    @Override
    public boolean isExtension(final EObject next, final String name) {
        return false;
    }

    @Override
    public Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(final EObject target) {
        return Collections.emptyList();
    }

    @Override
    public boolean preventFromBrowsing(final EObject root) {
        return false;
    }

    @Override
    public void updateMetamodels(final Collection<? extends MetamodelDescriptor> metamodelDescriptors) {
        if (lastDescriptors != null) {
            metamodelDescriptors.removeAll(lastDescriptors);
        }
        final Iterator<? extends MetamodelDescriptor> it = metamodelDescriptors.iterator();
        while (it.hasNext()) {
            final Object obj = it.next();
            if (obj instanceof EcoreMetamodelDescriptor) {
                final EPackage pak = ((EcoreMetamodelDescriptor) obj).resolve();
                if (pak != null) {
                    addTypesToSiriusIndex(pak);
                    Iterator<EPackage> subPack = Iterators.filter(pak.eAllContents(), EPackage.class);
                    while (subPack.hasNext()) {
                        addTypesToSiriusIndex(subPack.next());
                    }
                }
            }

        }
        lastDescriptors = metamodelDescriptors;

    }

}
