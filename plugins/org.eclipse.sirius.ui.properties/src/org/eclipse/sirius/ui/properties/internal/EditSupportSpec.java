/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.eclipse.sirius.properties.impl.EditSupportImpl;

/**
 * Contains the actual implementation of the EditSupport EOperations.
 * 
 * @author pcdavid
 */
public class EditSupportSpec extends EditSupportImpl {
    private final EditingDomainServices editServices = new EditingDomainServices();

    private final SiriusContext context;

    private final Object self;

    /**
     * Creates an instance configured to work on the specified target element as
     * implicit "self" for all operations.
     * 
     * @param ctx
     *            the context
     * @param target
     *            the target element.
     */
    public EditSupportSpec(SiriusContext ctx, Object target) {
        this.context = ctx;
        this.self = target;
    }

    /**
     * Returns the actual target model element, unwrapping the
     * {@link EEFViewCategory} if needed.
     * 
     * @return the actual target model element, or <code>null</code> if self is
     *         not an {@link EObject}.
     */
    private EObject getTargetEObject() {
        EObject result = null;
        if (self instanceof EEFViewCategory) {
            result = ((EEFViewCategory) self).getWrappedEObject();
        } else if (self instanceof EObject) {
            result = (EObject) self;
        }
        return result;
    }

    @Override
    public Object getImage() {
        EObject target = getTargetEObject();
        if (target != null) {
            return this.editServices.getLabelProviderImage(target);
        } else {
            return null;
        }
    }

    @Override
    public String getText() {
        EObject target = getTargetEObject();
        if (target != null) {
            return this.editServices.getLabelProviderText(target);
        } else {
            return String.valueOf(self);
        }
    }

    @Override
    public Object getText(EStructuralFeature feature) {
        EObject target = getTargetEObject();
        if (target != null) {
            String result = this.editServices.getPropertyDescriptorDisplayName(target, feature.getName());
            if (Util.isBlank(result)) {
                result = this.editServices.getLabelProviderText(feature);
            }
            if (Util.isBlank(result)) {
                result = feature.getName();
            }
            return result;
        } else {
            return ""; //$NON-NLS-1$
        }
    }

    @Override
    public String getTabName() {
        EObject target = getTargetEObject();
        final String result;
        if (target != null) {
            Option<EObject> mainSemanticElement = context.getMainSemanticElement();
            if (mainSemanticElement.some() && mainSemanticElement.get().equals(target)) {
                result = Messages.SiriusToolServices_MainTabLabel;
            } else {
                result = this.editServices.getLabelProviderText(target);
            }
        } else {
            result = String.valueOf(target);
        }
        return result;
    }

    @Override
    public EList<Object> getChoiceOfValues(EStructuralFeature feature) {
        BasicEList<Object> result = new BasicEList<Object>();
        EObject target = getTargetEObject();
        if (target != null) {
            result.addAll(this.editServices.getPropertyDescriptorChoiceOfValues(target, feature.getName()));
        }
        return result;

    }

    @Override
    public boolean isMultiline(EStructuralFeature eStructuralFeature) {
        EObject target = getTargetEObject();
        if (target != null) {
            return this.editServices.isPropertyDescriptorMultiLine(target, eStructuralFeature.getName());
        } else {
            return false;
        }
    }

    @Override
    public String getDescription(EStructuralFeature eStructuralFeature) {
        EObject target = getTargetEObject();
        if (target != null) {
            return this.editServices.getPropertyDescriptorDescription(target, eStructuralFeature.getName());
        } else {
            return ""; //$NON-NLS-1$
        }
    }

    @Override
    public String getCategory() {
        if (self instanceof EEFViewCategory) {
            return ((EEFViewCategory) self).getCategory();
        } else {
            return Messages.SiriusToolServices_DefaultCategoryName;
        }
    }

    @Override
    public EList<EObject> getCategories() {
        if (self instanceof EObject) {
            EObject eObject = (EObject) self;
            EList<EObject> categories = new BasicEList<>();

            // Get all the visible features associated to an eObject
            Collection<EStructuralFeature> features = getVisibleEStructuralFeatures(eObject);

            // Get all the categories defined in the genmodel for all the
            // features
            // of the given EObject
            Set<String> propertyDescriptorCategories = new HashSet<String>();
            String defaultCategoryName = Messages.SiriusToolServices_DefaultCategoryName;
            for (EStructuralFeature feature : features) {
                String category = this.editServices.getPropertyDescriptorCategory(eObject, feature.getName(), defaultCategoryName);
                if (category != null) {
                    propertyDescriptorCategories.add(category);
                } else {
                    propertyDescriptorCategories.add(defaultCategoryName);
                }
            }

            // Sort the categories by alphabetical order
            List<String> sortedPropertyDescriptorCategories = new ArrayList<String>(propertyDescriptorCategories);
            Collections.sort(sortedPropertyDescriptorCategories);

            // Put the default category at the end of the list
            if (sortedPropertyDescriptorCategories.contains(defaultCategoryName)) {
                sortedPropertyDescriptorCategories.remove(defaultCategoryName);
                sortedPropertyDescriptorCategories.add(defaultCategoryName);
            }

            // Create the EObjects associated to the visible categories
            for (String category : sortedPropertyDescriptorCategories) {
                if (eObject instanceof InternalEObject) {
                    EEFViewCategory eefViewCategory = new EEFViewCategory((InternalEObject) eObject, category);
                    categories.add(eefViewCategory);
                }
            }

            return categories;
        } else {
            return new BasicEList<EObject>();
        }
    }

    /**
     * Compute all the visible features (not derived, not transient, not a
     * containment reference) associated to a given EObject.
     *
     * @param eObject
     *            The EObject
     * @return List of visible features.
     */
    private Collection<EStructuralFeature> getVisibleEStructuralFeatures(EObject eObject) {
        List<EStructuralFeature> visibleFeaturesCache = new ArrayList<EStructuralFeature>();
        for (EStructuralFeature eStructuralFeature : eObject.eClass().getEAllStructuralFeatures()) {
            if (!eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !(eStructuralFeature instanceof EReference && ((EReference) eStructuralFeature).isContainment())) {
                visibleFeaturesCache.add(eStructuralFeature);
            }
        }

        return visibleFeaturesCache;
    }

    @Override
    public EList<EStructuralFeature> getEStructuralFeatures() {
        EList<EStructuralFeature> result = new BasicEList<EStructuralFeature>();
        // Get all the features associated to the eObject and filtered by
        // category
        if (self instanceof EEFViewCategory) {
            EEFViewCategory category = (EEFViewCategory) self;
            String groupCategory = category.getCategory();
            for (EStructuralFeature eStructuralFeature : getVisibleEStructuralFeatures(category.getWrappedEObject())) {
                String featureCategory = this.editServices.getPropertyDescriptorCategory(category.getWrappedEObject(), eStructuralFeature.getName(), Messages.SiriusToolServices_DefaultCategoryName);
                if (groupCategory.equals(featureCategory)) {
                    result.add(eStructuralFeature);
                }
            }
        }
        return result;
    }
    
    @Override
    public Object setValue(EStructuralFeature feature, Object newValue) {
        EObject target = getTargetEObject();
        if (target != null) {
            Object finalValue = newValue;
            if (feature instanceof EAttribute && newValue instanceof String) {
                finalValue = EcoreUtil.createFromString(((EAttribute) feature).getEAttributeType(), (String) newValue);
            }
            target.eSet(feature, finalValue);
        }
        return self;
    }

}
