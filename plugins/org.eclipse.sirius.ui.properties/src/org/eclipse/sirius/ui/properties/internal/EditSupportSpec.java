/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.edit.EditingDomainServices;
import org.eclipse.sirius.properties.core.api.preferences.SiriusPropertiesCorePreferences;
import org.eclipse.sirius.properties.impl.EditSupportImpl;

/**
 * Contains the actual implementation of the EditSupport EOperations.
 * 
 * @author pcdavid
 */
public class EditSupportSpec extends EditSupportImpl {

    private static final String JAVA_LANG_STRING = "java.lang.String"; //$NON-NLS-1$

    private static final String INT = "int"; //$NON-NLS-1$

    private static final String JAVA_LANG_INTEGER = "java.lang.Integer"; //$NON-NLS-1$

    private static final String DOUBLE = "double"; //$NON-NLS-1$

    private static final String JAVA_LANG_DOUBLE = "java.lang.Double"; //$NON-NLS-1$

    private static final String CHAR = "char"; //$NON-NLS-1$

    private static final String JAVA_LANG_CHARACTER = "java.lang.Character"; //$NON-NLS-1$

    private static final String SHORT = "short"; //$NON-NLS-1$

    private static final String JAVA_LANG_SHORT = "java.lang.Short"; //$NON-NLS-1$

    private static final String LONG = "long"; //$NON-NLS-1$

    private static final String JAVA_LANG_LONG = "java.lang.Long"; //$NON-NLS-1$

    private static final String FLOAT = "float"; //$NON-NLS-1$

    private static final String JAVA_LANG_FLOAT = "java.lang.Float"; //$NON-NLS-1$

    private static final String JAVA_UTIL_DATE = "java.util.Date"; //$NON-NLS-1$

    private static final String BOOLEAN = "boolean"; //$NON-NLS-1$

    private static final String JAVA_LANG_BOOLEAN = "java.lang.Boolean"; //$NON-NLS-1$

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
        if (self instanceof EObject) {
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
        return elide(result, SiriusPropertiesCorePreferences.INSTANCE.getMaxLengthTabName());
    }

    private String elide(String s, int maxLength) {
        final String dots = "..."; //$NON-NLS-1$
        if (dots.length() <= maxLength && maxLength < s.length()) {
            return s.substring(0, maxLength - dots.length()) + dots;
        } else {
            return s;
        }
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
    public boolean needsTextWidget(EStructuralFeature eStructuralFeature) {
        boolean needsTextWidget = false;

        needsTextWidget = needsTextWidget || JAVA_LANG_STRING.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || INT.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || JAVA_LANG_INTEGER.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || DOUBLE.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || JAVA_LANG_DOUBLE.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || CHAR.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || JAVA_LANG_CHARACTER.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || SHORT.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || JAVA_LANG_SHORT.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || LONG.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || JAVA_LANG_LONG.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || FLOAT.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || JAVA_LANG_FLOAT.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsTextWidget = needsTextWidget || JAVA_UTIL_DATE.equals(eStructuralFeature.getEType().getInstanceTypeName());

        return needsTextWidget && !eStructuralFeature.isMany();
    }

    @Override
    public boolean needsCheckboxWidget(EStructuralFeature eStructuralFeature) {
        boolean needsCheckboxWidget = false;

        needsCheckboxWidget = needsCheckboxWidget || BOOLEAN.equals(eStructuralFeature.getEType().getInstanceTypeName());
        needsCheckboxWidget = needsCheckboxWidget || JAVA_LANG_BOOLEAN.equals(eStructuralFeature.getEType().getInstanceTypeName());

        return needsCheckboxWidget && !eStructuralFeature.isMany();
    }

    @Override
    public EList<EStructuralFeature> getEStructuralFeatures() {
        EList<EStructuralFeature> visibleFeatures = new BasicEList<>();
        for (EStructuralFeature eStructuralFeature : this.getTargetEObject().eClass().getEAllStructuralFeatures()) {
            if (!eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !(eStructuralFeature instanceof EReference && ((EReference) eStructuralFeature).isContainment())) {
                visibleFeatures.add(eStructuralFeature);
            }
        }
        return visibleFeatures;
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
