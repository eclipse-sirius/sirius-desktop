/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.tools.internal.validation.description.constraints;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * Validate that a DomainClass exist.
 * 
 * @author ymortier
 */
public class ExistingDomainClassConstraint extends AbstractConstraint {

    /** The name of the DomainClass feature. */
    private static final String DOMAIN_CLASS_FEATURE = "domainClass"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public IStatus validate(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        final EMFEventType eventType = ctx.getEventType();
        // In the case of batch mode.
        if (eventType == EMFEventType.NULL) {
            if (isValidNsURI(eObj) && isElementContainedInAKnownMetamodel(eObj)) {
                final EStructuralFeature domainClassFeature = eObj.eClass().getEStructuralFeature(DOMAIN_CLASS_FEATURE);
                if (domainClassFeature != null) {
                    final Object[] result = checkError(domainClassFeature, eObj);
                    if (result != null) {
                        return ctx.createFailureStatus(result);
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private Object[] checkError(final EStructuralFeature domainClassFeature, final EObject eObj) {

        Object[] result = null;

        final Object value = eObj.eGet(domainClassFeature);
        final boolean canHaveNullDomainClass = eObj instanceof EdgeMapping && !((EdgeMapping) eObj).isUseDomainElement();
        if (value == null && !canHaveNullDomainClass) {
            //
            // The domain class can not be empty.
            // FIXED use another validator for mandatory domain class.
            // result = new Object[] { eObj.eClass().getName() };
            // See ticket #658
        } else if (value instanceof String) {
            final String className = (String) value;
            if (StringUtil.isEmpty(className) && !canHaveNullDomainClass) {
                //
                // The domain class can not be empty.
                // FIXED use another validator for mandatory domain class.
                // result = new Object[] { eObj.eClass().getName() };
                // See ticket #658
            } else if (!StringUtil.isEmpty(className)) {
                final ModelAccessor extPackage = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(eObj);
                if (!extPackage.eIsKnownType(className.trim())) {
                    // The domain class doesn't exist.
                    result = new Object[] { value };
                }
            }
        }
        return result;
    }

    @Override
    protected EObject getParentDescription(final EObject instance) {
        if (isValidNsURI(instance)) {
            EObject container = instance.eContainer();
            while (container != null) {
                if (container instanceof DiagramDescription || container instanceof DiagramExtensionDescription) {
                    return container;
                }
                container = container.eContainer();
            }
        }
        return null;
    }

    private boolean isValidNsURI(EObject eObj) {
        String nsURI = eObj.eClass().getEPackage().getNsURI();
        return nsURI.startsWith(ViewpointPackage.eINSTANCE.getNsURI()) || nsURI.startsWith(DescriptionPackage.eINSTANCE.getNsURI())
                || nsURI.startsWith(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getNsURI()) || nsURI.startsWith(DiagramPackage.eINSTANCE.getNsURI());
    }
}
