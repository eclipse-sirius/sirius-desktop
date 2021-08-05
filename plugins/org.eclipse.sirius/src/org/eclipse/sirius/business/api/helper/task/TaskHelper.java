/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.helper.task;

import static org.eclipse.sirius.viewpoint.ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;
import static org.eclipse.sirius.viewpoint.ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.helper.task.ExecuteToolOperationTask;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * Helper for get tasks from ModelOperation. Provide some utilities reused in different EMFCommandFactory
 * 
 * @author lredor
 * 
 */
public class TaskHelper {

    /** The model accessor. */
    private ModelAccessor modelAccessor;

    /** the user interface callback. */
    private UICallBack uiCallback;

    /**
     * The default constructor.
     * 
     * @param modelAccessor
     *            The model accessor
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public TaskHelper(final ModelAccessor modelAccessor, final UICallBack uiCallback) {
        this.modelAccessor = modelAccessor;
        this.uiCallback = uiCallback;
    }

    /**
     * Create an {@link ExecuteToolOperationTask} with the specified context and operation.
     * 
     * @param target
     *            the context.
     * @param op
     *            the operation.
     * @return the created {@link ExecuteToolOperationTask}.
     * @since 4.0.0
     */
    public ICommandTask buildTaskFromModelOperation(final EObject target, final ModelOperation op) {
        return new ExecuteToolOperationTask(modelAccessor, target, op, uiCallback);
    }

    /**
     * Create an {@link ExecuteToolOperationTask} with the specified context and operation.
     * 
     * @param representation
     *            the representation
     * @param target
     *            the context.
     * @param op
     *            the operation.
     * @return the created {@link ExecuteToolOperationTask}.
     * @since 0.9.0
     */
    public ICommandTask buildTaskFromModelOperation(final DRepresentation representation, final EObject target, final ModelOperation op) {
        final ExecuteToolOperationTask task = new ExecuteToolOperationTask(modelAccessor, target, representation, op, uiCallback);
        return task;
    }

    /**
     * Returns all the {@link DSemanticDecorator} elements to delete inside a context object.
     * 
     * @param context
     *            the context can be a {@link DAnalysis}, a {@link DView} or a {@link DRepresentation}.
     * @param semanticElements
     *            semantic elements.
     * @return all the {@link DSemanticDecorator} elements to delete.
     */
    public Set<DSemanticDecorator> getDElementToClearFromSemanticElements(final EObject context, final Set<EObject> semanticElements) {
        Set<DSemanticDecorator> decoratorsToDestroy = new HashSet<>();
        if (context != null) {
            final ECrossReferenceAdapter xref = getSemanticCrossReferencer(context);
            if (xref != null) {
                decoratorsToDestroy = getDElementToClearWithXref(context, semanticElements, xref);
            } else {
                decoratorsToDestroy = getDElementToClearWithoutXref(context, semanticElements);
            }
        }
        return decoratorsToDestroy;
    }

    private Set<DSemanticDecorator> getDElementToClearWithXref(final EObject context, final Set<EObject> semanticElements, final ECrossReferenceAdapter xref) {
        final Set<DSemanticDecorator> decoratorsToDestroy = new HashSet<DSemanticDecorator>();
        if (context instanceof DAnalysis || context instanceof DView || context instanceof DRepresentation) {
            for (EObject semElt : semanticElements) {
                for (Setting setting : xref.getInverseReferences(semElt)) {
                    EObject eObj = setting.getEObject();
                    if (setting.getEStructuralFeature().equals(DSEMANTIC_DECORATOR__TARGET) && isAncestor(context, eObj)) {
                        decoratorsToDestroy.add((DSemanticDecorator) eObj);
                    } else if (!decoratorsToDestroy.contains(eObj) && setting.getEStructuralFeature().equals(DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS) && isAncestor(context, eObj)) {
                        DRepresentationElement repElt = (DRepresentationElement) eObj;
                        if (semanticElements.containsAll(repElt.getSemanticElements())) {
                            decoratorsToDestroy.add(repElt);
                        }
                    }
                }
            }
        }
        return decoratorsToDestroy;
    }

    private boolean isAncestor(EObject context, EObject object) {
        EObject son = object;
        if (context instanceof DAnalysis || context instanceof DView) {
            Option<DRepresentation> representation = new EObjectQuery(object).getRepresentation();
            DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation.get()).getRepresentationDescriptor();
            son = representationDescriptor;
        }
        return EcoreUtil.isAncestor(context, son);
    }

    private ECrossReferenceAdapter getSemanticCrossReferencer(EObject root) {
        Session session = null;
        if (root instanceof DAnalysis) {
            for (Session tempSession : SessionManager.INSTANCE.getSessions()) {
                if (tempSession.getAllSessionResources().contains(root.eResource())) {
                    session = tempSession;
                }
            }
        } else if (root instanceof DSemanticDecorator) {
            session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) root).getTarget());
        } else {
            session = SessionManager.INSTANCE.getSession(root);
        }

        ECrossReferenceAdapter xref = null;
        if (session != null && session.getSemanticCrossReferencer() != null) {
            xref = session.getSemanticCrossReferencer();
        }
        return xref;
    }

    private Set<DSemanticDecorator> getDElementToClearWithoutXref(final EObject root, final Set<EObject> semanticElements) {
        final Set<DSemanticDecorator> decoratorsToDestroy = new HashSet<DSemanticDecorator>();
        final Iterator<EObject> it = root.eAllContents();
        while (it.hasNext()) {
            final EObject eObj = it.next();

            boolean toDestroy = false;
            if (eObj instanceof DSemanticDecorator) {
                final DSemanticDecorator decorator = (DSemanticDecorator) eObj;
                if (semanticElements.contains(decorator.getTarget())) {
                    // if the element is already in the set, to destroy will
                    // not change.
                    toDestroy = decoratorsToDestroy.add(decorator);
                }
            }

            if (!toDestroy && eObj instanceof DRepresentationElement) {
                final DRepresentationElement representationElement = (DRepresentationElement) eObj;
                if (semanticElements.containsAll(representationElement.getSemanticElements())) {
                    decoratorsToDestroy.add(representationElement);
                }
            }
        }

        return decoratorsToDestroy;
    }

    /**
     * Check the precondition of the specified tool with the specified container.
     * 
     * @param container
     *            the container variable.
     * @param toolDescription
     *            the {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription} .
     * @return <code>true</code> if the predicate is <code>true</code>.
     */
    public boolean checkPrecondition(final EObject container, final AbstractToolDescription toolDescription) {
        boolean result = true;
        if (toolDescription.getPrecondition() != null && !StringUtil.isEmpty(toolDescription.getPrecondition().trim())) {
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);
            // acceleoInterpreter.clearVariables();
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
            try {
                result = false;
                result = interpreter.evaluateBoolean(container, toolDescription.getPrecondition());
            } catch (final EvaluationException e) {
                // silent.
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        }
        return result;
    }

}
