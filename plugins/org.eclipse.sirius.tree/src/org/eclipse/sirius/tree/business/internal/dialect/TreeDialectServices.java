/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.dialect;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.business.api.dialect.AbstractRepresentationDialectServices;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.TreeFactory;
import org.eclipse.sirius.tree.business.api.interaction.DTreeUserInteraction;
import org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint.GlobalContext;
import org.eclipse.sirius.tree.business.internal.dialect.description.TreeInterpretedExpressionQuery;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.tools.internal.command.TreeCommandFactory;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * The {@link AbstractRepresentationDialectServices} implementation for the Tree
 * dialect.
 * 
 * @author pcdavid
 */
public class TreeDialectServices extends AbstractRepresentationDialectServices {
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSupported(RepresentationDescription description) {
        return description instanceof TreeDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSupported(DRepresentation representation) {
        return representation instanceof DTree;
    }

    /**
     * {@inheritDoc}
     */
    public DRepresentation createRepresentation(String name, EObject semantic, RepresentationDescription description, IProgressMonitor monitor) {
        DTree tree = null;
        try {
            monitor.beginTask("Create tree : " + name, 11);
            monitor.subTask("Create tree : " + name);
            final Session session = SessionManager.INSTANCE.getSession(semantic);
            final EditingDomain domain = session.getTransactionalEditingDomain();
            /*
             * Let's check if the description resource is in the session
             * resource set.
             */
            /*
             * FIXME we should certainly not remove from the previous resource
             * set. The viewpoint registry for instance has is own resource set
             * !
             */
            final Resource descriptionRes = description.eResource();
            if (!domain.getResourceSet().getResources().contains(descriptionRes)) {
                domain.getResourceSet().getResources().add(descriptionRes);
            }

            tree = TreeFactory.eINSTANCE.createDTree();
            tree.setName(name);
            tree.setDescription((TreeDescription) description);
            tree.setTarget(semantic);
            monitor.worked(1);

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

    /**
     * {@inheritDoc}
     */
    public void initRepresentations(Viewpoint vp, EObject semantic, IProgressMonitor monitor) {
        super.initRepresentations(semantic, vp, TreeDescription.class, monitor);
    }

    /**
     * {@inheritDoc}
     */
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
                            monitor.beginTask("Initialize tree of type " + new IdentifiedElementQuery(representationDescription).getLabel(), 1);
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
     * {@inheritDoc}
     */
    @Override
    public DRepresentation copyRepresentation(final DRepresentation representation, final String name, final Session session, final IProgressMonitor monitor) {
        DRepresentation newRepresentation = super.copyRepresentation(representation, name, session, monitor);
        /* associate the one */
        session.getServices().putCustomData(CustomDataConstants.DREPRESENTATION, ((DSemanticDecorator) representation).getTarget(), newRepresentation);
        return newRepresentation;
    }

    /**
     * Initializes the Representation described by the given description,
     * according the the given target value.
     * 
     * @param description
     *            the RepresentationDescription describing the representation to
     *            initialize
     * @param target
     *            the semantic target to use for initialize this representation
     */
    protected void initRepresentation(RepresentationDescription description, EObject target) {
        // TODO
    }

    /**
     * {@inheritDoc}
     */
    public void refresh(DRepresentation representation, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Tree refresh", 1);
            if (canRefresh(representation)) {
                refreshTree((DTree) representation, monitor);
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * {@inheritDoc}
     */
    public RepresentationDescription getDescription(DRepresentation representation) {
        if (isSupported(representation)) {
            return ((DTree) representation).getDescription();
        } else {
            return null;
        }
    }

    private void refreshTree(DTree tree, IProgressMonitor monitor) {
        Session session = SessionManager.INSTANCE.getSession(tree.getTarget());
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(tree.getTarget());
        InterpreterRegistry.prepareImportsFromSession(interpreter, session);
        ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(tree.getTarget());

        DTreeUserInteraction interaction = new DTreeUserInteraction(tree, new GlobalContext(accessor, session));
        interaction.refreshContent(new SubProgressMonitor(monitor, 1));

    }

    /**
     * {@inheritDoc}
     */
    public boolean canCreate(EObject semantic, RepresentationDescription desc) {
        boolean result = false;
        if (semantic != null && isSupported(desc)) {
            TreeDescription treeDesc = (TreeDescription) desc;
            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semantic);
            if (accessor != null) {
                result = checkDomainClass(accessor, semantic, treeDesc.getDomainClass());
            }
            result = result && checkPrecondition(semantic, treeDesc.getPreconditionExpression());
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#createInterpretedExpressionQuery(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature)
     */
    public IInterpretedExpressionQuery createInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        return new TreeInterpretedExpressionQuery(target, feature);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectServices#handles(org.eclipse.sirius.viewpoint.description.RepresentationDescription)
     */
    public boolean handles(RepresentationDescription representationDescription) {
        return representationDescription instanceof TreeDescription;
    }

}
