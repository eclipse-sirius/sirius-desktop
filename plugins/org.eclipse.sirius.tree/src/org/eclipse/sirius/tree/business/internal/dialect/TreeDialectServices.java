/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.business.internal.dialect;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.AbstractRepresentationDialectServices;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.query.DRepresentationDescriptorInternalHelper;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeElementSynchronizer;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.business.api.interaction.DTreeUserInteraction;
import org.eclipse.sirius.tree.business.internal.dialect.common.tree.TreeRefreshContext;
import org.eclipse.sirius.tree.business.internal.dialect.description.TreeInterpretedExpressionQuery;
import org.eclipse.sirius.tree.business.internal.refresh.DTreeElementSynchronizerSpec;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.tools.internal.Messages;
import org.eclipse.sirius.tree.tools.internal.command.TreeCommandFactory;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * The {@link AbstractRepresentationDialectServices} implementation for the Tree dialect.
 * 
 * @author pcdavid
 */
public class TreeDialectServices extends AbstractRepresentationDialectServices {
    @Override
    protected boolean isSupported(RepresentationDescription description) {
        return description instanceof TreeDescription;
    }

    @Override
    protected boolean isSupported(DRepresentation representation) {
        return representation instanceof DTree;
    }

    @Override
    protected boolean isSupported(DRepresentationDescriptor representationDescriptor) {
        return representationDescriptor.getDescription() instanceof TreeDescription;
    }

    @Override
    public DRepresentation createRepresentation(String name, EObject semantic, RepresentationDescription description, IProgressMonitor monitor) {
        DTree tree = null;
        try {
            monitor.beginTask(MessageFormat.format(Messages.TreeDialectServices_createTree, name), 11);
            monitor.subTask(MessageFormat.format(Messages.TreeDialectServices_createTree, name));
            final Session session = SessionManager.INSTANCE.getSession(semantic);
            final EditingDomain domain = session.getTransactionalEditingDomain();
            /*
             * Let's check if the description resource is in the session resource set.
             */
            /*
             * FIXME we should certainly not remove from the previous resource set. The viewpoint registry for instance
             * has is own resource set !
             */
            final Resource descriptionRes = description.eResource();
            if (!domain.getResourceSet().getResources().contains(descriptionRes)) {
                domain.getResourceSet().getResources().add(descriptionRes);
            }

            tree = TreeFactory.eINSTANCE.createDTree();
            tree.setDescription((TreeDescription) description);
            tree.setTarget(semantic);
            monitor.worked(1);

            DRepresentationDescriptorInternalHelper.createDRepresentationDescriptor(tree, (DAnalysisSessionImpl) session, semantic.eResource(), name, ""); //$NON-NLS-1$
            refresh(tree, new SubProgressMonitor(monitor, 10));
        } finally {
            monitor.done();
        }
        return tree;
    }

    /**
     * {@inheritDoc}
     */
    public void initRepresentations(Viewpoint vp, EObject semantic) {
        super.initRepresentations(semantic, vp, TreeDescription.class);
    }

    @Override
    public void initRepresentations(Viewpoint vp, EObject semantic, IProgressMonitor monitor) {
        super.initRepresentations(semantic, vp, TreeDescription.class, monitor);
    }

    @Override
    protected <T extends RepresentationDescription> void initRepresentationForElement(T representationDescription, EObject semanticElement, IProgressMonitor monitor) {
        if (representationDescription instanceof TreeDescription) {
            TreeDescription treeDescription = (TreeDescription) representationDescription;
            if (shouldInitializeRepresentation(semanticElement, treeDescription, treeDescription.getDomainClass())) {

                final ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticElement);
                final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(semanticElement);

                if (modelAccessor.eInstanceOf(semanticElement, treeDescription.getDomainClass())) {
                    boolean canCreate = true;
                    if (treeDescription.getPreconditionExpression() != null && !StringUtil.isEmpty(treeDescription.getPreconditionExpression())) {
                        try {
                            canCreate = InterpreterUtil.getInterpreter(semanticElement).evaluateBoolean(semanticElement, treeDescription.getPreconditionExpression());
                        } catch (final EvaluationException e) {
                            canCreate = false;
                        }
                    }
                    if (canCreate) {
                        try {
                            monitor.beginTask(MessageFormat.format(Messages.TreeDialectServices_initializeTree, new IdentifiedElementQuery(representationDescription).getLabel()), 1);
                            final TreeCommandFactory treeCommandFactory = new TreeCommandFactory(domain);
                            treeCommandFactory.setModelAccessor(modelAccessor);
                            DCommand command = treeCommandFactory.buildCreateTreeFromDescription(treeDescription, semanticElement, new SubProgressMonitor(monitor, 1));
                            domain.getCommandStack().execute(command);
                        } finally {
                            monitor.done();
                        }
                    }
                }
            }
        }
    }

    /**
     * Initializes the Representation described by the given description, according the the given target value.
     * 
     * @param description
     *            the RepresentationDescription describing the representation to initialize
     * @param target
     *            the semantic target to use for initialize this representation
     */
    protected void initRepresentation(RepresentationDescription description, EObject target) {
        // TODO
    }

    @Override
    public void refresh(DRepresentation representation, boolean fullRefresh, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.TreeDialectServices_treeRefresh, 1);
            if (canRefresh(representation)) {
                refreshTree((DTree) representation, fullRefresh, monitor);
            }
        } finally {
            monitor.done();
        }
    }

    @Override
    public RepresentationDescription getDescription(DRepresentation representation) {
        if (isSupported(representation)) {
            return ((DTree) representation).getDescription();
        } else {
            return null;
        }
    }

    private void refreshTree(DTree tree, boolean fullRefresh, IProgressMonitor monitor) {
        Session session = SessionManager.INSTANCE.getSession(tree.getTarget());
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(tree.getTarget());
        InterpreterRegistry.prepareImportsFromSession(interpreter, session);
        ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(tree.getTarget());

        DTreeUserInteraction interaction = new DTreeUserInteraction(tree,
                new TreeRefreshContext(accessor, session.getInterpreter(), session.getSemanticResources(), session.getTransactionalEditingDomain()));
        interaction.refreshContent(fullRefresh, new SubProgressMonitor(monitor, 1));

    }

    @Override
    public boolean canCreate(EObject semantic, RepresentationDescription desc, boolean checkSelectedViewpoint) {
        boolean result = false;
        if (semantic != null && isSupported(desc)) {
            Session session = new EObjectQuery(semantic).getSession();
            // If the semantic doesn't belong to a session we don't check
            // viewpoint selection but only others things like domainClass
            if (session == null || (checkSelectedViewpoint && isRelatedViewpointSelected(session, desc)) || !checkSelectedViewpoint) {
                TreeDescription treeDesc = (TreeDescription) desc;
                ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semantic);
                if (accessor != null) {
                    result = checkDomainClass(accessor, semantic, treeDesc.getDomainClass());
                }
                result = result && checkPrecondition(semantic, treeDesc.getPreconditionExpression());
            }
        }
        return result;
    }

    @Override
    public IInterpretedExpressionQuery createInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        return new TreeInterpretedExpressionQuery(target, feature);
    }

    @Override
    public boolean handles(RepresentationDescription representationDescription) {
        return representationDescription instanceof TreeDescription;
    }

    @Override
    public boolean handles(RepresentationExtensionDescription representationExtensionDescription) {
        return false;
    }

    @Override
    public void refreshImpactedElements(DRepresentation representation, Collection<Notification> notifications, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DTreeUserInteraction_treeRefresh, 10);
            DTree tree = (DTree) representation;
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(tree.getTarget());
            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);

            Set<DTreeElement> dTreeElements = getTreeElementsToRefresh(notifications, tree);
            monitor.worked(2);
            DTreeElementSynchronizer synchronizer = new DTreeElementSynchronizerSpec(interpreter, accessor);
            IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 8);
            try {
                subMonitor.beginTask(Messages.TreeDialectServices_refreshImpactedElements, dTreeElements.size());
                for (DTreeElement dTreeElement : dTreeElements) {
                    if (dTreeElement instanceof DTreeItem) {
                        synchronizer.refresh((DTreeItem) dTreeElement);
                    }
                    subMonitor.worked(1);
                }
            } finally {
                subMonitor.done();
            }
        } finally {
            monitor.done();
        }
    }

    private Set<DTreeElement> getTreeElementsToRefresh(Collection<Notification> notifications, DTree tree) {
        Set<DTreeElement> treeElementsToRefresh = new HashSet<>();
        Session session = new org.eclipse.sirius.business.api.query.EObjectQuery(tree.getTarget()).getSession();
        if (session != null) {
            ECrossReferenceAdapter xref = session.getSemanticCrossReferencer();
            // Deal with each notifier only one time.
            Set<EObject> alreadyDoneNotifiers = new HashSet<>();
            for (Notification notification : notifications) {
                Object notifier = notification.getNotifier();
                if (notifier instanceof EObject) {
                    EObject eObjectNotifier = (EObject) notifier;
                    if (alreadyDoneNotifiers.add(eObjectNotifier)) {
                        treeElementsToRefresh.addAll(getTreeElementsToRefresh(eObjectNotifier, tree, xref));
                    }
                }
            }
        }
        return treeElementsToRefresh;
    }

    private Set<DTreeElement> getTreeElementsToRefresh(EObject notifier, DTree tree, ECrossReferenceAdapter xref) {
        Set<DTreeElement> treeElementsToRefresh = new HashSet<>();
        Collection<EObject> inverseReferencers = new EObjectQuery(notifier, xref).getInverseReferences(REPRESENTATION_ELEMENTS_INVERSE_REFERENCES);
        for (EObject inverseReferencer : inverseReferencers) {
            if (inverseReferencer instanceof DTreeElement) {
                DTreeElement treeElement = (DTreeElement) inverseReferencer;
                if (isContainedWithinCurrentTree(treeElement, tree)) {
                    treeElementsToRefresh.add(treeElement);
                }
            }
        }
        return treeElementsToRefresh;
    }

    private boolean isContainedWithinCurrentTree(DTreeElement treeElement, DTree tree) {
        return tree == new DRepresentationElementQuery(treeElement).getParentRepresentation();
    }

}
