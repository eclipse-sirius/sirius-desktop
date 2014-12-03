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
package org.eclipse.sirius.diagram.tools.api.validation.constraint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.tools.api.validation.constraint.RuleWrappingStatus;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;

/**
 * Common class for all the DDiagram constraints. This class wrapp the base
 * behavior of getting the first failling rule, clients should define the
 * "isValid" method to say wether the rule match the current constraint or not.
 * 
 * @author cbrun
 * 
 */
public abstract class AbstractDDiagramConstraint extends AbstractModelConstraint {
    @Override
    public IStatus validate(final IValidationContext ctx) {
        final EObject objectToValidate = ctx.getTarget();
        final EMFEventType typeEvenement = ctx.getEventType();
        if (typeEvenement == EMFEventType.NULL) {
            final Collection<ValidationRule> failures = getFailingRules(objectToValidate);
            if (failures.size() > 0) {
                final MultiStatus parentStatus = new MultiStatus(SiriusPlugin.ID, getHighestStatusCode(failures), "Validation issues", null);
                for (ValidationRule failedRule : failures) {
                    EObject target = objectToValidate;

                    // Change the target for SemanticValidationRule
                    if (failedRule instanceof SemanticValidationRule && objectToValidate instanceof DSemanticDecorator) {
                        target = ((DSemanticDecorator) objectToValidate).getTarget();
                    }

                    final ConstraintStatus emfStatus = (ConstraintStatus) ctx.createFailureStatus(new Object[] { failedRule.getMessage(target) });
                    parentStatus.add(new RuleWrappingStatus(emfStatus, failedRule));
                }
                return parentStatus;
            }
        }
        return ctx.createSuccessStatus();
    }

    private int getHighestStatusCode(final Collection<ValidationRule> rules) {
        int highestCode = IStatus.OK;
        final Iterator<ValidationRule> it = rules.iterator();
        while (it.hasNext() && highestCode < IStatus.ERROR) {
            final int currentCode = mapLevelToStatusCode(it.next().getLevel());
            if (currentCode > highestCode) {
                highestCode = currentCode;
            }
        }
        return highestCode;
    }

    private int mapLevelToStatusCode(final ERROR_LEVEL level) {
        int code = IStatus.OK;
        if (level == ERROR_LEVEL.ERROR_LITERAL) {
            code = IStatus.ERROR;
        } else if (level == ERROR_LEVEL.WARNING_LITERAL) {
            code = IStatus.WARNING;
        } else if (level == ERROR_LEVEL.INFO_LITERAL) {
            code = IStatus.INFO;
        }
        return code;
    }

    /**
     * 
     * @param objectToValidate
     *            object we are testing.
     * @return the first {@link ValidationRule} conform to isValid() which
     *         fails.
     */
    private Collection<ValidationRule> getFailingRules(final EObject objectToValidate) {
        final Collection<ValidationRule> failingRules = new ArrayList<ValidationRule>();

        // Get the DDiagram
        DDiagram diagram = null;
        if (objectToValidate instanceof DDiagram) {
            diagram = (DDiagram) objectToValidate;
        } else if (objectToValidate instanceof DDiagramElement) {
            diagram = ((DDiagramElement) objectToValidate).getParentDiagram();
        }

        if (diagram != null) {
            /*
             * If some rules are manually activated, then we'll pick in these
             * ones, otherwhise we'll use all the rules.
             */
            if (diagram.getActivatedRules().size() > 0) {
                failingRules.addAll(getFaillingRulesFromCollection(objectToValidate, diagram.getActivatedRules().iterator()));
            } else if (diagram.getDescription() != null) {
                final DiagramDescription desc = diagram.getDescription();
                final ValidationSet validationSet = desc.getValidationSet();
                if (validationSet != null) {
                    failingRules.addAll(getFaillingRulesFromCollection(objectToValidate, validationSet.getAllRules().iterator()));
                }
            }
            failingRules.addAll(checkRulesFromActivatedViewpoints(objectToValidate, diagram));
        }

        return failingRules;
    }

    private Collection<ValidationRule> checkRulesFromActivatedViewpoints(final EObject objectToValidate, final DDiagram diagram) {
        if (objectToValidate instanceof DSemanticDecorator) {
            final EObject semantic = ((DSemanticDecorator) objectToValidate).getTarget();
            final Session session = SessionManager.INSTANCE.getSession(semantic);
            if (session != null) {
                final Iterator<Viewpoint> it = session.getSelectedViewpoints(false).iterator();
                while (it.hasNext()) {
                    final Viewpoint vp = it.next();
                    if (vp.getValidationSet() != null) {
                        return getFaillingRulesFromCollection(objectToValidate, vp.getValidationSet().getAllRules().iterator());
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private Collection<ValidationRule> getFaillingRulesFromCollection(final EObject objectToValidate, final Iterator<ValidationRule> it) {
        final Collection<ValidationRule> failingRules = new ArrayList<ValidationRule>();
        /*
         * Iterate and return the first failling rule. null if no rule is
         * failling.
         */
        while (it.hasNext()) {
            final ValidationRule rule = it.next();
            if (isValid(rule)) {
                if (objectToValidate instanceof DSemanticDecorator && rule instanceof SemanticValidationRule && ((SemanticValidationRule) rule).getTargetClass() != null
                        && !StringUtil.isEmpty(((SemanticValidationRule) rule).getTargetClass().trim())) {
                    EObject semanticTargetElement = ((DSemanticDecorator) objectToValidate).getTarget();
                    if (isSemanticElementToValidate(objectToValidate, semanticTargetElement, ((SemanticValidationRule) rule).getTargetClass())) {
                        if (!rule.checkRule(semanticTargetElement)) {
                            failingRules.add(rule);
                        }
                    }
                } else if (objectToValidate instanceof DDiagramElement && rule instanceof ViewValidationRule) {
                    final DiagramElementMapping objMapping = ((DDiagramElement) objectToValidate).getDiagramElementMapping();
                    if (objMapping != null && ((ViewValidationRule) rule).getTargets().contains(objMapping)) {
                        if (!rule.checkRule(objectToValidate)) {
                            failingRules.add(rule);
                        }
                    }
                }
            }
        }
        return failingRules;
    }

    /**
     * Check if this element must be validated.
     * 
     * @param objectToValidate
     *            The object to validate
     * @param semanticElement
     *            The semantic element associated with the
     *            <code>objectToValidate</code>.
     * @param expectedClass
     *            The expected class for the semantic element.
     * @return true if the semantic element must be validate, false otherwise.
     */
    private boolean isSemanticElementToValidate(EObject objectToValidate, EObject semanticElement, String expectedClass) {
        boolean result = false;
        if (SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticElement).eInstanceOf(semanticElement, expectedClass)) {
            if (objectToValidate instanceof DEdge) {
                // We must only check the target of Edge if the Edge is
                // a domain based edge. Indeed, if not, the target will
                // be check twice (one for the edge.getTarget() that
                // return the source.getTarget() and one for the source
                // Node sourceNode.getTarget())
                if (((DEdge) objectToValidate).getSourceNode() instanceof DSemanticDecorator && !semanticElement.equals(((DSemanticDecorator) ((DEdge) objectToValidate).getSourceNode()).getTarget())) {
                    result = true;
                }
            } else {
                result = true;
            }
        }
        return result;
    }

    /**
     * return true if this validation rule apply to the current context, false
     * otherwise.
     * 
     * @param rule
     *            a validation rule.
     * @return true if this validation rule apply to the current context, false
     *         otherwise.
     */
    protected abstract boolean isValid(ValidationRule rule);
}
