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
package org.eclipse.sirius.diagram.ui.internal.providers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ITraversalStrategy;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.part.ValidateAction;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * @was-generated
 */
public class SiriusValidationProvider extends AbstractContributionItemProvider {

    /**
     * @was-generated
     */
    private static boolean constraintsActive = false;

    /**
     * @was-generated
     */
    public static boolean shouldConstraintsBePrivate() {
        return false;
    }

    /**
     * @was-generated
     */
    @Override
    protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {
        if (ValidateAction.VALIDATE_ACTION_KEY.equals(actionId)) {
            return new ValidateAction(partDescriptor);
        }
        return super.createAction(actionId, partDescriptor);
    }

    /**
     * @was-generated
     */
    public static void runWithConstraints(View view, Runnable op) {
        final Runnable fop = op;
        Runnable task = new Runnable() {

            @Override
            public void run() {
                try {
                    constraintsActive = true;
                    fop.run();
                } finally {
                    constraintsActive = false;
                }
            }
        };
        TransactionalEditingDomain txDomain = TransactionUtil.getEditingDomain(view);
        if (txDomain != null) {
            try {
                txDomain.runExclusive(task);
            } catch (Exception e) {
                DiagramPlugin.getDefault().logError(Messages.SiriusValidationProvider_validationFailed, e);
            }
        } else {
            task.run();
        }
    }

    /**
     * @was-generated
     */
    static boolean isInDefaultEditorContext(Object object) {
        if (shouldConstraintsBePrivate() && !constraintsActive) {
            return false;
        }
        if (object instanceof View) {
            return constraintsActive && DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID((View) object));
        }
        return true;
    }

    /**
     * @was-generated
     */
    static final Map semanticCtxIdMap = new HashMap();

    /**
     * @was-generated
     */
    public static ITraversalStrategy getNotationTraversalStrategy(IBatchValidator validator) {
        return new CtxSwitchStrategy(validator);
    }

    /**
     * @was-generated
     */
    private static class CtxSwitchStrategy implements ITraversalStrategy {

        /**
         * @was-generated
         */
        private ITraversalStrategy defaultStrategy;

        /**
         * @was-generated
         */
        private String currentSemanticCtxId;

        /**
         * @was-generated
         */
        private boolean ctxChanged = true;

        /**
         * @was-generated
         */
        private EObject currentTarget;

        /**
         * @was-generated
         */
        private EObject preFetchedNextTarget;

        /**
         * @was-generated
         */
        CtxSwitchStrategy(IBatchValidator validator) {
            this.defaultStrategy = validator.getDefaultTraversalStrategy();
        }

        /**
         * @was-generated
         */
        @Override
        public void elementValidated(EObject element, IStatus status) {
            defaultStrategy.elementValidated(element, status);
        }

        /**
         * @was-generated
         */
        @Override
        public boolean hasNext() {
            return defaultStrategy.hasNext();
        }

        /**
         * @was-generated
         */
        @Override
        public boolean isClientContextChanged() {
            if (preFetchedNextTarget == null) {
                preFetchedNextTarget = next();
                prepareNextClientContext(preFetchedNextTarget);
            }
            return ctxChanged;
        }

        /**
         * @was-generated
         */
        @Override
        public EObject next() {
            EObject nextTarget = preFetchedNextTarget;
            if (nextTarget == null) {
                nextTarget = defaultStrategy.next();
            }
            this.preFetchedNextTarget = null;
            return this.currentTarget = nextTarget;
        }

        /**
         * @was-generated
         */
        @Override
        public void startTraversal(Collection traversalRoots, IProgressMonitor monitor) {
            defaultStrategy.startTraversal(traversalRoots, monitor);
        }

        /**
         * @was-generated
         */
        private void prepareNextClientContext(EObject nextTarget) {
            if (nextTarget != null && currentTarget != null) {
                if (nextTarget instanceof View) {
                    String id = ((View) nextTarget).getType();
                    String nextSemanticId = id != null && semanticCtxIdMap.containsKey(id) ? id : null;
                    if ((currentSemanticCtxId != null && !currentSemanticCtxId.equals(nextSemanticId)) || (nextSemanticId != null && !nextSemanticId.equals(currentSemanticCtxId))) {
                        this.ctxChanged = true;
                    }
                    currentSemanticCtxId = nextSemanticId;
                } else {
                    // context of domain model
                    this.ctxChanged = currentSemanticCtxId != null;
                    currentSemanticCtxId = null;
                }
            } else {
                this.ctxChanged = false;
            }
        }
    }

} // SiriusValidationProvider
