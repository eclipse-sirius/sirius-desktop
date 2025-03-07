/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.ecore.extender.business.internal.common.ExtenderDescriptor;
import org.eclipse.sirius.ext.emf.EReferencePredicate;

/**
 * Aggregate more {@link IMetamodelExtender}'s using a priority system.
 * 
 * @author cbrun
 * 
 */
public class CompositeMetamodelExtender extends AbstractMetamodelExtender {

    private List<ExtenderDescriptor> extenderDescs = new ArrayList<>();

    private List<IMetamodelExtender> extenders = new ArrayList<>();

    private Iterable<IMetamodelExtender> activeExtenders;

    private Collection<? extends MetamodelDescriptor> metamodelDescriptors = Collections.emptyList();

    /**
     * Add a new extender in the composite instance.
     * 
     * @param ext
     *            new extender.
     * @param priority
     *            priority value taken from {@link ExtenderConstants}.
     */
    public void add(final IMetamodelExtender ext, final int priority) {
        extenderDescs.add(new ExtenderDescriptor(ext, priority));
        Collections.sort(extenderDescs);
        extenders.clear();
        for (final ExtenderDescriptor desc : extenderDescs) {
            extenders.add(desc.getExtender());
        }

        if (isActive()) {
            ext.activate();
            ext.updateMetamodels(metamodelDescriptors);
        }
    }

    /**
     * return an ordered iterator on the extenders considering priority.
     * 
     * @return an ordered iterator on the extenders considering priority.
     */
    protected Iterator<IMetamodelExtender> getExtenders() {
        return extenders.iterator();
    }

    @Override
    public void init(final ResourceSet set) {
        for (final IMetamodelExtender extender : extenders) {
            extender.init(set);
        }
    }

    /**
     * Returns an iterator browsing the model through the containment
     * references.
     * 
     * @param root
     *            element to start browsing from.
     * @return an iterator browsing the model through the containment
     *         references.
     */
    public TreeIterator<EObject> eAllContents(final EObject root) {
        return new AbstractTreeIterator<EObject>(root, false) {
            private static final long serialVersionUID = 1L;

            @Override
            public Iterator<EObject> getChildren(final Object object) {
                if (object instanceof EObject) {
                    return eContents((EObject) object);
                }
                return Collections.<EObject> emptyList().iterator();
            }
        };
    }

    @Override
    public EObject createInstance(final String name) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final EObject instance = extender.createInstance(name);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

    @Override
    public boolean eIsKnownType(final String name) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final boolean isKnown = extender.eIsKnownType(name);
            if (isKnown) {
                return true;
            }
        }
        return false;

    }

    @Override
    public void dispose() {
        for (final IMetamodelExtender extender : extenders) {
            if (isActive()) {
                extender.deactivate();
            }
            extender.dispose();
        }
    }

    @Override
    public Object eAdd(final EObject instance, final String name, final Object value) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            Object addedValue = extender.eAdd(instance, name, value);
            if (addedValue != null) {
                return addedValue;
            }
        }
        return null;
    }

    @Override
    public Object eClear(final EObject instance, final String name) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final Object clearedInstance = extender.eClear(instance, name);
            if (clearedInstance != null) {
                return clearedInstance;
            }
        }
        return null;
    }

    @Override
    public EObject eContainer(final EObject instance) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final EObject eContainer = extender.eContainer(instance);
            if (eContainer != null) {
                return eContainer;
            }
        }
        return null;
    }

    @Override
    public String eContainingFeatureName(final EObject instance) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final String feature = extender.eContainingFeatureName(instance);
            if (feature != null) {
                return feature;
            }
        }
        return null;
    }


    @Override
    public Iterator<EObject> eContents(final EObject root) {
        final List<Iterator<EObject>> iterators = new ArrayList<>();
        final IMetamodelExtender browsingBlocker = hasBrowsingBlocker(root);
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            if (browsingBlocker == null || extender == browsingBlocker) {
                iterators.add(extender.eContents(root));
            }
        }
        if (!iterators.isEmpty()) {
            return iterators.stream()
                    .flatMap(iterator -> StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false))
                    .iterator();
        }
        return Collections.<EObject> emptyList().iterator();
    }
    /**
     * Check whether the given instance should block the eAllContent browsing or
     * not.
     * 
     * @param root
     *            any instance.
     * @return a {@link IMetamodelExtender} willing to block the eAllContents
     *         browsing, null if no extenders want to do that.
     */
    private IMetamodelExtender hasBrowsingBlocker(final EObject root) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            if (extender.preventFromBrowsing(root)) {
                return extender;
            }
        }
        return null;
    }

    @Override
    public EObject eDelete(final EObject objectToRemove, final ECrossReferenceAdapter xref) {
        return eDelete(objectToRemove, xref, null);
    }

    @Override
    public EObject eDelete(EObject objectToRemove, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
        EObject result = null;
        /*
         * we want every extender to be notified when an instance should be
         * removed.
         */
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            result = extender.eDelete(objectToRemove, xref, isReferencesToIgnorePredicate);
        }
        return result;
    }

    @Override
    public Collection<EObject> eRemoveInverseCrossReferences(EObject eObject, ECrossReferenceAdapter xref, EReferencePredicate isReferencesToIgnorePredicate) {
        Collection<EObject> impactedEObjects = new LinkedHashSet<>();
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            impactedEObjects.addAll(extender.eRemoveInverseCrossReferences(eObject, xref, isReferencesToIgnorePredicate));
        }
        return impactedEObjects;
    }

    @Override
    public Object eGet(final EObject instance, final String name) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final Object result = extender.eGet(instance, name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public boolean eInstanceOf(final EObject instance, final String typeName) {
        /*
         * If any extenders says it is an instance of, then it is
         */
        if (typeName != null) {
            for (final IMetamodelExtender extender : getActivatedExtenders()) {
                if (extender.eInstanceOf(instance, typeName.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean eIsContainment(final EObject instance, final String featureName) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final Boolean result = extender.eIsContainment(instance, featureName);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public Boolean eIsMany(final EObject instance, final String featureName) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final Boolean result = extender.eIsMany(instance, featureName);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public Object eRemove(final EObject instance, final String name, final Object value) {
        Object result = null;
        /*
         * we want every extender to be notified when an instance should be
         * removed.
         */
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            result = extender.eRemove(instance, name, value);
        }
        return result;
    }

    @Override
    public Object eSet(final EObject instance, final String name, final Object value) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final Object result = extender.eSet(instance, name, value);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public boolean eValid(final EObject object, final String name) {
        /*
         * If any extenders says it is valid, then it is.
         */
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            if (extender.eValid(object, name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<String> getContributedAttributeNames(final EObject next) {
        final List<Iterator<String>> iterators = new ArrayList<>();
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            iterators.add(extender.getContributedAttributeNames(next));
        }
        return iterators.stream()
                .flatMap(iterator -> StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false))
                .iterator();
    }

    @Override
    public Iterator<String> getContributedReferenceNames(final EObject instance) {
        final List<Iterator<String>> iterators = new ArrayList<>();
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            iterators.add(extender.getContributedReferenceNames(instance));
        }
        return iterators.stream()
                .flatMap(iterator -> StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false))
                .iterator();
    }

    @Override
    public boolean hasExtension(final EObject next) {
        /*
         * If any extenders says it has an extension, then it has one.
         */
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            if (extender.hasExtension(next)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExtension(final EObject next) {
        /*
         * If any extenders says it is an extension, then it has one.
         */
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            if (extender.isExtension(next)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a {@link Collection} of children of the given type.
     * 
     * @param target
     *            element to start browsing from.
     * @param typeName
     *            Type to keep.
     * @return an iterator browsing the model through the containment
     *         references.
     */
    public Collection<EObject> eAllContents(final EObject target, final String typeName) {
        final Collection<EObject> result = new ArrayList<>();
        final Iterator<EObject> it = eAllContents(target);
        while (it.hasNext()) {
            final EObject cur = it.next();
            if (eInstanceOf(cur, typeName)) {
                result.add(cur);
            }
        }
        return result;
    }

    @Override
    public boolean isExtension(final EObject next, final String name) {
        /*
         * If any extenders says it is an extension, then it has one.
         */
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            if (extender.isExtension(next, name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getQualifiedName(final EObject element, final boolean b) {
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            final String result = extender.getQualifiedName(element, b);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public Collection<ExtensionFeatureDescription> getAllExtensionFeatureDescriptions(final EObject target) {
        final Collection<ExtensionFeatureDescription> result = new ArrayList<>();
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            result.addAll(extender.getAllExtensionFeatureDescriptions(target));
        }
        return result;
    }

    @Override
    public boolean preventFromBrowsing(final EObject root) {
        /*
         * If any extenders says it is an extension, then it has one.
         */
        for (final IMetamodelExtender extender : getActivatedExtenders()) {
            if (extender.preventFromBrowsing(root)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMetamodels(final Collection<? extends MetamodelDescriptor> mmDescriptors) {
        super.updateMetamodels(mmDescriptors);
        this.metamodelDescriptors = mmDescriptors;

        for (final IMetamodelExtender extender : extenders) {
            extender.updateMetamodels(metamodelDescriptors);
        }
    }

    @Override
    public void activate() {
        super.activate();
        for (final IMetamodelExtender extender : extenders) {
            extender.activate();
        }
    }

    @Override
    public void deactivate() {
        for (final IMetamodelExtender extender : extenders) {
            extender.deactivate();
        }
    }

    /**
     * return an ordered iterator on the activated extenders considering
     * priority.
     * 
     * @return an ordered iterator on the activated extenders considering
     *         priority.
     */
    protected synchronized Iterable<IMetamodelExtender> getActivatedExtenders() {
        if (activeExtenders == null) {
            activeExtenders = () -> extenders.stream().filter(IMetamodelExtender::isActive).iterator();
        }
        return activeExtenders;
    }

}
