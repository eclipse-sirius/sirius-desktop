/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.util.DescriptionSwitch;
import org.eclipse.sirius.diagram.tools.api.Messages;

/**
 * Validate that the node mapping, container mapping, edgeMapping, the
 * conditional node style description, the conditional container style
 * description and the conditional edge style description had a style define.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class ValidStyleConstraint extends AbstractModelConstraint {

    /**
     * Validate that nodeMapping, edgeMapping, containerMapping,
     * conditionalEdgeDescription, conditionalNodeDescription and
     * conditionalContainerDescription had a style define. If nodeImportMapping;
     * edgeMappingIMport or ContainerMappingImport had no style define it's OK.
     * 
     * @param ctx
     *            the validation context that provides access to the current
     *            constraint evaluation environment. The framework will never
     *            pass a <code>null</code> value
     * @return the status of validation of the target object. The
     *         {@link IStatus#getSeverity()} of the record is either
     *         {@link IStatus#OK} to indicate success, or some other value to
     *         indicate that validation failed. Must not return
     *         <code>null</code>
     * 
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public IStatus validate(IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();

        IStatus constraintStatus = null;

        constraintStatus = ElementMappingSwitch.INSTANCE.doSwitch(eObj, ctx);

        return constraintStatus != null ? constraintStatus : ctx.createSuccessStatus();
    }

    /**
     * Switch class to validate nodeMapping, edgeMapping, containerMapping,
     * conditionalEdgeDescription, conditionalNodeDescription and
     * conditionalContainerDescription had a style defines.
     * 
     * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
     * 
     */
    private static class ElementMappingSwitch extends DescriptionSwitch<Object> {

        public static final ElementMappingSwitch INSTANCE = new ElementMappingSwitch();

        /**
         * Switch object is valid.
         * 
         * @param object
         *            object to switch
         * @param ctx
         *            the validation context that provides access to the current
         *            constraint evaluation environment
         * @param modelConstraint
         *            the constraint that failed
         * @return the constraint status.
         */
        public IStatus doSwitch(EObject object, IValidationContext ctx) {
            return doSwitch(object.eClass(), object, ctx);
        }

        protected IStatus doSwitch(EClass theEClass, EObject theEObject, IValidationContext ctx) {
            if (theEClass.eContainer() == modelPackage) {
                return doSwitch(theEClass.getClassifierID(), theEObject, ctx);
            } else {
                List<EClass> eSuperTypes = theEClass.getESuperTypes();
                return eSuperTypes.isEmpty() ? (ConstraintStatus) defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject, ctx);
            }
        }

        protected IStatus doSwitch(int classifierID, EObject object, IValidationContext ctx) {
            IStatus constraint = null;
            switch (classifierID) {
            case DescriptionPackage.NODE_MAPPING:
                constraint = caseNodeMapping((NodeMapping) object, ctx);
                break;
            case DescriptionPackage.CONTAINER_MAPPING:
                constraint = caseContainerMapping((ContainerMapping) object, ctx);
                break;
            case DescriptionPackage.EDGE_MAPPING:
                constraint = caseEdgeMapping((EdgeMapping) object, ctx);
                break;
            case DescriptionPackage.CONDITIONAL_NODE_STYLE_DESCRIPTION:
                constraint = caseConditionalNodeStyleDescription((ConditionalNodeStyleDescription) object, ctx);
                break;
            case DescriptionPackage.CONDITIONAL_EDGE_STYLE_DESCRIPTION:
                constraint = caseConditionalEdgeStyleDescription((ConditionalEdgeStyleDescription) object, ctx);
                break;
            case DescriptionPackage.CONDITIONAL_CONTAINER_STYLE_DESCRIPTION:
                constraint = caseConditionalContainerStyleDescription((ConditionalContainerStyleDescription) object, ctx);
                break;
            default:
                constraint = (ConstraintStatus) defaultCase(object);
                break;
            }
            return constraint;
        }

        private IStatus caseNodeMapping(NodeMapping object, IValidationContext ctx) {
            if (object.getStyle() == null) {
                return ctx.createFailureStatus(new Object[] { object.getName() });
            }
            return null;
        }

        private IStatus caseEdgeMapping(EdgeMapping object, IValidationContext ctx) {
            if (object.getStyle() == null) {
                return ctx.createFailureStatus(new Object[] { object.getName() });
            }
            return null;
        }

        private IStatus caseContainerMapping(ContainerMapping object, IValidationContext ctx) {
            if (object.getStyle() == null) {
                return ctx.createFailureStatus(new Object[] { object.getName() });
            }
            return null;
        }

        public IStatus caseConditionalEdgeStyleDescription(ConditionalEdgeStyleDescription object, IValidationContext ctx) {
            if (object.getStyle() == null) {
                return ctx.createFailureStatus(
                        new Object[] { MessageFormat.format(Messages.ValidStyleConstraint_validationErrorMsg, object.getPredicateExpression(), ((EdgeMapping) object.eContainer()).getName()) });
            }
            return null;
        }

        private IStatus caseConditionalNodeStyleDescription(ConditionalNodeStyleDescription object, IValidationContext ctx) {
            if (object.getStyle() == null) {
                return ctx.createFailureStatus(
                        new Object[] { MessageFormat.format(Messages.ValidStyleConstraint_validationErrorMsg, object.getPredicateExpression(), ((NodeMapping) object.eContainer()).getName()) });
            }
            return null;
        }

        private IStatus caseConditionalContainerStyleDescription(ConditionalContainerStyleDescription object, IValidationContext ctx) {
            if (object.getStyle() == null) {
                return ctx.createFailureStatus(
                        new Object[] { MessageFormat.format(Messages.ValidStyleConstraint_validationErrorMsg, object.getPredicateExpression(), ((ContainerMapping) object.eContainer()).getName()) });
            }
            return null;
        }
    }
}
