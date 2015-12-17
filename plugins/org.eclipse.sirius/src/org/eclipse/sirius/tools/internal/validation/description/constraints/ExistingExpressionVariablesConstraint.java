/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tools.internal.validation.AbstractConstraint;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch;
import org.eclipse.sirius.viewpoint.description.util.DescriptionSwitch;

/**
 * This constraint validates that all expressions use.
 * 
 * @author ymortier
 */
public class ExistingExpressionVariablesConstraint extends AbstractConstraint {

    /** contains all variables names for a vpeMapping precondition expression. */
    private static final Set<String> VPE_MAPPING_PRECONDITION_VARIABLES;

    /**
     * contains all variables names for a vpeMapping semantic candidates
     * expression.
     */
    private static final Set<String> VPE_MAPPING_SEMANTIC_CANDIDATES_VARIABLES;

    /**
     * contains all variables names for an edgeMapping target finder expression.
     */
    private static final Set<String> EDGE_MAPPING_TARGET_FINDER_VARIABLES;

    /**
     * contains all variables names for an edgeMapping source finder expression.
     */
    private static final Set<String> EDGE_MAPPING_SOURCE_FINDER_VARIABLES;

    /** contains all variables names for an edgeMapping target expression. */
    private static final Set<String> EDGE_MAPPING_TARGET_VARIABLES;

    /** contains all variables names for an edgeMapping path expression. */
    private static final Set<String> EDGE_MAPPING_PATH_VARIABLES;

    /** contains all variables names for a tool precondition (default). */
    private static final Set<String> TOOL_DESC_PRECONDITION_VARIABLES_DEF;

    /** contains all variables names for a tool precondition (edge). */
    private static final Set<String> TOOL_DESC_PRECONDITION_VARIABLES_EDGE;

    /** contains all variables names for a tool precondition (delete). */
    private static final Set<String> TOOL_DESC_PRECONDITION_VARIABLES_DELETE;

    /** contains all variables names for a tool precondition (d&d diagram). */
    private static final Set<String> TOOL_DESC_PRECONDITION_VARIABLES_DDDIAG;

    /**
     * contains all variables names for a tool precondition (Select Model
     * Element Wizard).
     */
    private static final Set<String> TOOL_DESC_PRECONDITION_VARIABLE_SMEW;

    /**
     * contains all variables names for a tool precondition (d&d project
     * explorer).
     */
    private static final Set<String> TOOL_DESC_PRECONDITION_VARIABLES_DDPEXPL;

    /**
     * contains all variables names for a conditional style predicate
     * expression.
     */
    private static final Set<String> COND_STYLE_PREDICATE_VARIABLES;

    /** Initialize variables. */
    static {
        VPE_MAPPING_PRECONDITION_VARIABLES = new HashSet<String>(2);
        VPE_MAPPING_PRECONDITION_VARIABLES.add(IInterpreterSiriusVariables.CONTAINER);
        VPE_MAPPING_PRECONDITION_VARIABLES.add(IInterpreterSiriusVariables.CONTAINER_VIEW);

        VPE_MAPPING_SEMANTIC_CANDIDATES_VARIABLES = new HashSet<String>(2);
        VPE_MAPPING_SEMANTIC_CANDIDATES_VARIABLES.add(IInterpreterSiriusVariables.VIEWPOINT);
        VPE_MAPPING_SEMANTIC_CANDIDATES_VARIABLES.add(IInterpreterSiriusVariables.DIAGRAM);

        EDGE_MAPPING_TARGET_FINDER_VARIABLES = new HashSet<String>(1);
        EDGE_MAPPING_TARGET_FINDER_VARIABLES.add(IInterpreterSiriusVariables.VIEWPOINT_2);

        EDGE_MAPPING_SOURCE_FINDER_VARIABLES = new HashSet<String>(1);
        EDGE_MAPPING_SOURCE_FINDER_VARIABLES.add(IInterpreterSiriusVariables.VIEWPOINT_2);

        EDGE_MAPPING_TARGET_VARIABLES = new HashSet<String>(3);
        EDGE_MAPPING_TARGET_VARIABLES.add(IInterpreterSiriusVariables.VIEWPOINT);
        EDGE_MAPPING_TARGET_VARIABLES.add(IInterpreterSiriusVariables.DIAGRAM);
        EDGE_MAPPING_TARGET_VARIABLES.add(IInterpreterSiriusVariables.TARGET_SEMANTIC_NODE);

        EDGE_MAPPING_PATH_VARIABLES = new HashSet<String>(5);
        EDGE_MAPPING_PATH_VARIABLES.add(IInterpreterSiriusVariables.VIEWPOINT);
        EDGE_MAPPING_PATH_VARIABLES.add(IInterpreterSiriusVariables.DIAGRAM);
        EDGE_MAPPING_PATH_VARIABLES.add(IInterpreterSiriusVariables.ELEMENT);
        EDGE_MAPPING_PATH_VARIABLES.add(IInterpreterSiriusVariables.SOURCE);
        EDGE_MAPPING_PATH_VARIABLES.add(IInterpreterSiriusVariables.TARGET);

        COND_STYLE_PREDICATE_VARIABLES = new HashSet<String>(2);
        COND_STYLE_PREDICATE_VARIABLES.add(IInterpreterSiriusVariables.CONTAINER);
        COND_STYLE_PREDICATE_VARIABLES.add(IInterpreterSiriusVariables.VIEW);

        TOOL_DESC_PRECONDITION_VARIABLES_DEF = new HashSet<String>(1);
        TOOL_DESC_PRECONDITION_VARIABLES_DEF.add(IInterpreterSiriusVariables.CONTAINER);

        TOOL_DESC_PRECONDITION_VARIABLES_EDGE = new HashSet<String>(5);
        TOOL_DESC_PRECONDITION_VARIABLES_EDGE.add(IInterpreterSiriusVariables.SOURCE_PRE);
        TOOL_DESC_PRECONDITION_VARIABLES_EDGE.add(IInterpreterSiriusVariables.TARGET_PRE);
        TOOL_DESC_PRECONDITION_VARIABLES_EDGE.add(IInterpreterSiriusVariables.SOURCE_VIEW_PRE);
        TOOL_DESC_PRECONDITION_VARIABLES_EDGE.add(IInterpreterSiriusVariables.TARGET_VIEW_PRE);
        TOOL_DESC_PRECONDITION_VARIABLES_EDGE.add(IInterpreterSiriusVariables.CONTAINER);

        TOOL_DESC_PRECONDITION_VARIABLES_DELETE = new HashSet<String>(2);
        TOOL_DESC_PRECONDITION_VARIABLES_DELETE.add(IInterpreterSiriusVariables.CONTAINER_VIEW);
        TOOL_DESC_PRECONDITION_VARIABLES_DELETE.add(IInterpreterSiriusVariables.ELEMENT);

        TOOL_DESC_PRECONDITION_VARIABLES_DDDIAG = new HashSet<String>(4);
        TOOL_DESC_PRECONDITION_VARIABLES_DDDIAG.add(IInterpreterSiriusVariables.CONTAINER_OLD);
        TOOL_DESC_PRECONDITION_VARIABLES_DDDIAG.add(IInterpreterSiriusVariables.CONTAINER_NEW);
        TOOL_DESC_PRECONDITION_VARIABLES_DDDIAG.add(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW);
        TOOL_DESC_PRECONDITION_VARIABLES_DDDIAG.add(IInterpreterSiriusVariables.ELEMENT);

        TOOL_DESC_PRECONDITION_VARIABLES_DDPEXPL = new HashSet<String>(3);
        TOOL_DESC_PRECONDITION_VARIABLES_DDPEXPL.add(IInterpreterSiriusVariables.CONTAINER_NEW);
        TOOL_DESC_PRECONDITION_VARIABLES_DDPEXPL.add(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW);
        TOOL_DESC_PRECONDITION_VARIABLES_DDPEXPL.add(IInterpreterSiriusVariables.ELEMENT);

        TOOL_DESC_PRECONDITION_VARIABLE_SMEW = new HashSet<String>(1);
        TOOL_DESC_PRECONDITION_VARIABLE_SMEW.add(IInterpreterSiriusVariables.CONTAINER_VIEW);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    public IStatus validate(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        final EMFEventType eventType = ctx.getEventType();
        //
        // In the case of batch mode.
        if (shouldValidate(eObj) && eventType == EMFEventType.NULL) {
            final IStatus status = ctx.createSuccessStatus();
            final DescriptionSwitchValidator descriptionSwitchValidator = new DescriptionSwitchValidator(ctx, status);
            IStatus result = descriptionSwitchValidator.doSwitch(eObj);
            result = validateTool(eObj, ctx, result);
            return result;
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Validate tools expressions.
     * 
     * @param eObj
     *            the object to validate.
     * @param ctx
     *            the validation context.
     * @param statusInput
     *            the current status.
     * @return the resulted status.
     */
    private IStatus validateTool(final EObject eObj, final IValidationContext ctx, final IStatus statusInput) {
        final ToolSwitchValidator toolSwitchValidator = new ToolSwitchValidator(ctx, statusInput);
        final IStatus status = toolSwitchValidator.doSwitch(eObj);
        return status;
    }

    /**
     * Checks that the feature <code>precondition</code> of the instance of
     * <code>AbstractToolDescription</code> doesn't contain invalid acceleo
     * variables.
     * 
     * @param ctx
     *            the validation context.
     * @param abstractToolDescription
     *            the tool to check.
     * @return the validation status.
     */
    protected IStatus checkAbstractToolDescriptionPrecondition(final IValidationContext ctx, final AbstractToolDescription abstractToolDescription) {
        Set<String> variables = TOOL_DESC_PRECONDITION_VARIABLES_DEF;
        if (abstractToolDescription instanceof SelectionWizardDescription) {
            variables = TOOL_DESC_PRECONDITION_VARIABLE_SMEW;
        }
        return this.checkVariables(ctx, abstractToolDescription.getPrecondition(), variables, abstractToolDescription, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition().getName());
    }

    /**
     * Checks that the feature <code>predicateExpression</code> of the instance
     * of <code>ConditionalStyleDescription</code> doesn't contain invalid
     * acceleo variables.
     * 
     * @param ctx
     *            the validation context.
     * @param conditionalStyle
     *            the style to check.
     * @return the validation status.
     */
    protected IStatus checkConditionalStylePredicate(final IValidationContext ctx, final ConditionalStyleDescription conditionalStyle) {
        return this.checkVariables(ctx, conditionalStyle.getPredicateExpression(), COND_STYLE_PREDICATE_VARIABLES, conditionalStyle,
                org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getConditionalStyleDescription_PredicateExpression().getName());
    }

    /**
     * Checks that the feature <code>candidatesExpression</code> of the instance
     * of <code>SelectModelElementVariable</code> doesn't contain invalid
     * acceleo variables.
     * 
     * @param ctx
     *            the validation context.
     * @param selectModelElementVariableOp
     *            the variable to check.
     * @return the validation status.
     */
    protected IStatus checkSelectModelElementCandidates(final IValidationContext ctx, final SelectModelElementVariable selectModelElementVariableOp) {
        return this.checkVariables(ctx, selectModelElementVariableOp.getCandidatesExpression(), Collections.<String> emptySet(), selectModelElementVariableOp,
                org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getSelectionDescription_CandidatesExpression().getName());
    }

    /**
     * Checks that the feature <code>referenceName</code> of the instance of
     * <code>CreateInstance</code> doesn't contain invalid acceleo variables.
     * 
     * @param ctx
     *            the validation context.
     * @param createInstanceOp
     *            the operation to check.
     * @return the validation status.
     */
    protected IStatus checkCreateInstanceReferenceName(final IValidationContext ctx, final CreateInstance createInstanceOp) {
        if (createInstanceOp.getReferenceName() != null) {
            return this.checkVariables(ctx, createInstanceOp.getReferenceName(), getDeclaredVariables(createInstanceOp), createInstanceOp, ToolPackage.eINSTANCE.getCreateInstance_ReferenceName()
                    .getName());
        }
        return ctx.createSuccessStatus();
    }

    /**
     * Checks that the feature <code>browseExpression</code> of the instance of
     * <code>ChangeContext</code> doesn't contain invalid acceleo variables.
     * 
     * @param ctx
     *            the validation context.
     * @param changeContextOp
     *            the tool to check.
     * @return the validation status.
     */
    protected IStatus checkChangeContextBrowse(final IValidationContext ctx, final ChangeContext changeContextOp) {
        return this.checkVariables(ctx, changeContextOp.getBrowseExpression(), getDeclaredVariables(changeContextOp), changeContextOp, ToolPackage.eINSTANCE.getChangeContext_BrowseExpression()
                .getName());
    }

    /**
     * Checks that the feature <code>valueExpression</code> of the instance of
     * <code>SetValue</code> doesn't contain invalid acceleo variables.
     * 
     * @param ctx
     *            the validation context.
     * @param setValueOp
     *            the tool to check.
     * @return the validation status.
     */
    protected IStatus checkSetValueValue(final IValidationContext ctx, final SetValue setValueOp) {
        return this.checkVariables(ctx, setValueOp.getValueExpression(), getDeclaredVariables(setValueOp), setValueOp, ToolPackage.eINSTANCE.getSetValue_ValueExpression().getName());
    }

    /**
     * Checks that the feature <code>newContainerExpression</code> of the
     * instance of <code>MoveElement</code> doesn't contain invalid acceleo
     * variables.
     * 
     * @param ctx
     *            the validation context.
     * @param moveElementOp
     *            the tool to check.
     * @return the validation status.
     */
    protected IStatus checkMoveElementNewContainer(final IValidationContext ctx, final MoveElement moveElementOp) {
        return this.checkVariables(ctx, moveElementOp.getNewContainerExpression(), getDeclaredVariables(moveElementOp), moveElementOp, ToolPackage.eINSTANCE.getMoveElement_NewContainerExpression()
                .getName());
    }

    /**
     * Checks that the feature <code>expression</code> of the instance of
     * <code>For</code> doesn't contain invalid acceleo variables.
     * 
     * @param ctx
     *            the validation context.
     * @param forOp
     *            the tool to check.
     * @return the validation status.
     */
    protected IStatus checkFor(final IValidationContext ctx, final For forOp) {
        return this.checkVariables(ctx, forOp.getExpression(), getDeclaredVariables(forOp), forOp, ToolPackage.eINSTANCE.getFor_Expression().getName());
    }

    /**
     * Checks that the feature <code>computationExpression</code> of the
     * instance of <code>AcceleoVariable</code> doesn't contain invalid acceleo
     * variables.
     * 
     * @param ctx
     *            the validation context.
     * @param acceleoVariableOp
     *            the tool to check.
     * @return the validation status.
     */
    protected IStatus checkAcceleoVariableComputation(final IValidationContext ctx, final AcceleoVariable acceleoVariableOp) {
        return this.checkVariables(ctx, acceleoVariableOp.getComputationExpression(), getDeclaredVariables(acceleoVariableOp), acceleoVariableOp, ToolPackage.eINSTANCE
                .getAcceleoVariable_ComputationExpression().getName());
    }

    /**
     * Return all variables that can be used for the specified object.
     * 
     * @param context
     *            the context.
     * @return all variables that can be used for the specified object.
     */
    protected Set<String> getDeclaredVariables(final EObject context) {
        final Set<String> result = new HashSet<String>();
        //
        // 1st, search the tool.
        AbstractToolDescription tool = null;
        EObject current = context;
        while (tool == null && current.eContainer() != null) {
            if (current instanceof AbstractToolDescription) {
                tool = (AbstractToolDescription) current;
            }
            current = current.eContainer();
        }
        if (tool != null) {
            //
            // 2nd, find all variables.
            final Iterator<EObject> iterAllContents = tool.eAllContents();
            while (iterAllContents.hasNext()) {
                final EObject next = iterAllContents.next();
                if (next instanceof AbstractVariable) {
                    result.add(((AbstractVariable) next).getName());
                } else if (next instanceof For) {
                    result.add(((For) next).getIteratorName());
                } else if (next instanceof CreateInstance) {
                    final String name = ((CreateInstance) next).getVariableName();
                    if (!StringUtil.isEmpty(name)) {
                        result.add(name);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Validates
     * {@link org.eclipse.sirius.viewpoint.description.DescriptionPackage}
     * acceleo expressions.
     * 
     * @author ymortier
     */
    public class DescriptionSwitchValidator extends DescriptionSwitch<IStatus> {

        /** The ecurrent status. */
        private IStatus currentStatus;

        /** The validation context. */
        private final IValidationContext ctx;

        /**
         * Creates a new validator.
         * 
         * @param ctx
         *            the validation context (mandatory).
         * @param currentStatus
         *            the current status (optional).
         */
        public DescriptionSwitchValidator(final IValidationContext ctx, final IStatus currentStatus) {
            this.currentStatus = currentStatus;
            this.ctx = ctx;
            if (this.currentStatus == null) {
                this.currentStatus = ctx.createSuccessStatus();
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see DescriptionSwitch#caseConditionalStyleDescription(ConditionalStyleDescription)
         */
        @Override
        public IStatus caseConditionalStyleDescription(final ConditionalStyleDescription object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseConditionalStyleDescription(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkConditionalStylePredicate(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * {@inheritDoc}
         * 
         * @see DescriptionSwitch#doSwitch(EObject)
         */
        @Override
        public IStatus doSwitch(final EObject theEObject) {
            if (this.currentStatus.isOK()) {
                this.currentStatus = super.doSwitch(theEObject);
            }
            return this.currentStatus;
        }

    }

    /**
     * Validates {@link ToolPackage} acceleo expressions.
     * 
     * @author ymortier
     */
    public class ToolSwitchValidator extends ToolSwitch<IStatus> {

        /** The ecurrent status. */
        private IStatus currentStatus;

        /** The validation context. */
        private final IValidationContext ctx;

        /**
         * Creates a new validator.
         * 
         * @param ctx
         *            the validation context (mandatory).
         * @param currentStatus
         *            the current status (optional).
         */
        public ToolSwitchValidator(final IValidationContext ctx, final IStatus currentStatus) {
            this.currentStatus = currentStatus;
            this.ctx = ctx;
            if (this.currentStatus == null) {
                this.currentStatus = ctx.createSuccessStatus();
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseAbstractToolDescription(org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription)
         */
        @Override
        public IStatus caseAbstractToolDescription(final AbstractToolDescription object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseAbstractToolDescription(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkAbstractToolDescriptionPrecondition(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * 
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseSelectModelElementVariable(org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable)
         */
        @Override
        public IStatus caseSelectModelElementVariable(final SelectModelElementVariable object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseSelectModelElementVariable(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkSelectModelElementCandidates(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * 
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseCreateInstance(org.eclipse.sirius.viewpoint.description.tool.CreateInstance)
         */
        @Override
        public IStatus caseCreateInstance(final CreateInstance object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseCreateInstance(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkCreateInstanceReferenceName(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseChangeContext(org.eclipse.sirius.viewpoint.description.tool.ChangeContext)
         */
        @Override
        public IStatus caseChangeContext(final ChangeContext object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseChangeContext(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkChangeContextBrowse(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseSetValue(org.eclipse.sirius.viewpoint.description.tool.SetValue)
         */
        @Override
        public IStatus caseSetValue(final SetValue object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseSetValue(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkSetValueValue(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseMoveElement(org.eclipse.sirius.viewpoint.description.tool.MoveElement)
         */
        @Override
        public IStatus caseMoveElement(final MoveElement object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseMoveElement(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkMoveElementNewContainer(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseFor(org.eclipse.sirius.viewpoint.description.tool.For)
         */
        @Override
        public IStatus caseFor(final For object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseFor(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkFor(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#caseAcceleoVariable(org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable)
         */
        @Override
        public IStatus caseAcceleoVariable(final AcceleoVariable object) {
            if (this.currentStatus.isOK()) {
                final IStatus superStatus = super.caseAcceleoVariable(object);
                if (superStatus == null || superStatus.isOK()) {
                    this.currentStatus = checkAcceleoVariableComputation(ctx, object);
                } else {
                    this.currentStatus = superStatus;
                }
            }
            return this.currentStatus;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.util.ToolSwitch#doSwitch(org.eclipse.emf.ecore.EObject)
         */
        @Override
        public IStatus doSwitch(final EObject theEObject) {
            if (this.currentStatus == null || this.currentStatus.isOK()) {
                this.currentStatus = super.doSwitch(theEObject);
            }
            return this.currentStatus;
        }

    }
}
