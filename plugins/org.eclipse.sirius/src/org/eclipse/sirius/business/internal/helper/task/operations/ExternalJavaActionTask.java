/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskExecutor;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.helper.task.DeleteDDiagramElementTask;
import org.eclipse.sirius.business.internal.helper.task.DeleteDDiagramTask;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.listener.ChangeListenerFactory;
import org.eclipse.sirius.tools.api.command.listener.IChangeListener;
import org.eclipse.sirius.tools.api.command.listener.TriggerOperation;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction2;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A task which call an external Java Action.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ExternalJavaActionTask extends AbstractOperationTask {

    private ExternalJavaAction externalJavaAction;

    private IExternalJavaAction javaAction;

    private Collection<EObject> selections = new ArrayList<EObject>();

    private ModelAccessor accessor;

    private TaskHelper taskHelper;

    private Session session;

    /**
     * Default constructor.
     * 
     * @param context
     *            the command context
     * @param extPackage
     *            the extended package
     * @param op
     *            the operation
     * @param session
     *            the {@link Session} to be used to this task
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public ExternalJavaActionTask(final CommandContext context, final ModelAccessor extPackage, final ExternalJavaAction op, final Session session, final UICallBack uiCallback) {
        super(context, extPackage, session.getInterpreter());
        externalJavaAction = op;
        this.accessor = extPackage;
        this.session = session;
        if (context.getCurrentTarget() != null) {
            selections.add(context.getCurrentTarget());
        }
        javaAction = ExternalJavaActionProvider.INSTANCE.getJavaActionById(externalJavaAction.getId());
        this.taskHelper = new TaskHelper(accessor, uiCallback);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        final Map<String, Object> parameters = new HashMap<String, Object>();

        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(session.getInterpreter());

        if (taskHelper.checkPrecondition(context.getCurrentTarget(), externalJavaAction)) {
            // Evaluate the parameters
            for (final ExternalJavaActionParameter parameter : externalJavaAction.getParameters()) {
                final Object value = safeInterpreter.evaluate(context.getCurrentTarget(), parameter, ToolPackage.eINSTANCE.getExternalJavaActionParameter_Value());
                parameters.put(parameter.getName(), value);
            }

            IChangeListener listener = null;
            try {
                if (!(javaAction instanceof IExternalJavaAction2) || ((IExternalJavaAction2) javaAction).mayDeleteElements()) {
                    listener = ChangeListenerFactory.INSTANCE.getNewChangeListener();
                    startListenSemanticChange(listener);
                }
                javaAction.execute(selections, parameters);
            } finally {
                if (listener != null) {
                    stopListenSemanticChange(listener);
                }
            }
        }
    }

    private void startListenSemanticChange(IChangeListener listener) {

        listener.setTriggerOperation(new DeleteDiagramElementsTriggerOperation());
        // Add this listener on each resources that is not VSM or aird files (to
        // improve perf).
        for (Resource resource : session.getTransactionalEditingDomain().getResourceSet().getResources()) {
            ResourceQuery resourceQuery = new ResourceQuery(resource);
            if (!resourceQuery.isRepresentationsResource() && !resourceQuery.isModelerResource()) {
                resource.eAdapters().add(listener);
            }
        }
        listener.activate();
    }

    private void stopListenSemanticChange(IChangeListener listener) {
        listener.launchTriggerOperation();
        // Remove this listener on each resources that is not VSM or aird files.
        for (Resource resource : session.getTransactionalEditingDomain().getResourceSet().getResources()) {
            ResourceQuery resourceQuery = new ResourceQuery(resource);
            if (!resourceQuery.isRepresentationsResource() && !resourceQuery.isModelerResource()) {
                resource.eAdapters().remove(listener);
            }
        }
        listener.deactivate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "Execute external java action \"" + externalJavaAction.getName() + "\"";
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.AbstractCommandTask#canExecute()
     */
    @Override
    public boolean canExecute() {
        return javaAction != null && javaAction.canExecute(selections);
    }

    private class DeleteDiagramElementsTriggerOperation implements TriggerOperation {

        public void run(Collection<Object> createdElements, Collection<Object> modifiedElements, Collection<Object> deletedElements) {
            if (!deletedElements.isEmpty()) {
                Set<EObject> allDeletedElements = elementsAndChildren(deletedElements);
                final Set<DSemanticDecorator> decoratorsToDelete = getDecoratorsInRepresentationToDelete(allDeletedElements);
                decoratorsToDelete.addAll(getRepresentationsToDelete(allDeletedElements));
                /*
                 * if the external java action do a real delete, then
                 * representation elements will lost their reference to target
                 * semantic element they decorate
                 */
                decoratorsToDelete.addAll(getOrphanedDecorators());
                getCommand(decoratorsToDelete);
            }
            if (!createdElements.isEmpty() || !modifiedElements.isEmpty()) {
                /* do nothing right now */
            }
        }

        private Set<DSemanticDecorator> getDecoratorsInRepresentationToDelete(Set<EObject> deletedElements) {
            return taskHelper.getDElementToClearFromSemanticElements(context.getRepresentation(), deletedElements);
        }

        private Set<DSemanticDecorator> getRepresentationsToDelete(Set<EObject> deletedElements) {
            final Set<DSemanticDecorator> representationsToDelete = Sets.newHashSet();

            if (context.getRepresentation() instanceof DSemanticDecorator) {
                for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
                    if (representation instanceof DSemanticDecorator && deletedElements.contains(((DSemanticDecorator) representation).getTarget())) {
                        representationsToDelete.add((DSemanticDecorator) representation);
                    }
                }
            }
            return representationsToDelete;
        }

        private Set<DSemanticDecorator> getOrphanedDecorators() {
            Set<DSemanticDecorator> orhanedElements = Sets.newHashSet();
            final Iterator<EObject> it = context.getRepresentation().eAllContents();
            while (it.hasNext()) {
                final EObject eObj = it.next();
                if (isOrphaned(eObj)) {
                    orhanedElements.add((DSemanticDecorator) eObj);
                }
            }
            if (context.getRepresentation() instanceof DSemanticDecorator) {
                for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
                    if (isOrphaned(representation)) {
                        orhanedElements.add((DSemanticDecorator) representation);
                    }
                }
            }
            return orhanedElements;
        }

        private boolean isOrphaned(EObject eObject) {
            /*
             * DFeatureColumn should not be a DSemanticDecorator, until the
             * change filter instance of that class
             */
            return eObject instanceof DSemanticDecorator && ((DSemanticDecorator) eObject).getTarget() == null && (!"DFeatureColumn".equals(eObject.eClass().getName()));
        }

        private void getCommand(final Collection<DSemanticDecorator> dElements) {
            List<ICommandTask> tasks = new ArrayList<ICommandTask>();
            for (final DSemanticDecorator dElement : dElements) {
                if (dElement instanceof DRepresentationElement) {
                    ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(dElement);
                    DeleteDDiagramElementTask task = new DeleteDDiagramElementTask(dElement, modelAccessor);
                    tasks.add(task);
                } else if (dElement instanceof DRepresentation) {
                    DeleteDDiagramTask task = new DeleteDDiagramTask((DRepresentation) dElement);
                    task.setDeleteIncomingReferences(true);
                    tasks.add(task);
                }
            }
            TaskExecutor.execute(tasks);
        }

        private Set<EObject> elementsAndChildren(final Collection<Object> semantics) {
            final Set<EObject> elementsAndChildren = new LinkedHashSet<EObject>();
            for (final Object object : semantics) {
                if (object instanceof EObject) {
                    final EObject eObject = (EObject) object;
                    elementsAndChildren.add(eObject);
                    Iterators.addAll(elementsAndChildren, eObject.eAllContents());
                }
            }
            return elementsAndChildren;
        }
    }

}
