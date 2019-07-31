/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.table.business.internal.dialect;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.AbstractRepresentationDialectServices;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.query.DRepresentationDescriptorInternalHelper;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.business.api.refresh.DTableSynchronizer;
import org.eclipse.sirius.table.business.internal.dialect.description.TableInterpretedExpressionQuery;
import org.eclipse.sirius.table.business.internal.refresh.DTableElementSynchronizer;
import org.eclipse.sirius.table.business.internal.refresh.DTableSynchronizerImpl;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.TableFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.tools.internal.Messages;
import org.eclipse.sirius.table.tools.internal.command.TableCommandFactory;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Services for the table dialect.
 *
 * @author cbrun
 */
public class TableDialectServices extends AbstractRepresentationDialectServices {

    @Override
    protected boolean isSupported(DRepresentation representation) {
        return representation instanceof DTable;
    }

    @Override
    protected boolean isSupported(DRepresentationDescriptor representationDescriptor) {
        return representationDescriptor.getDescription() instanceof TableDescription;
    }

    @Override
    protected boolean isSupported(RepresentationDescription description) {
        return description instanceof TableDescription;
    }

    @Override
    public boolean canCreate(EObject semantic, RepresentationDescription desc, boolean checkSelectedViewpoint) {
        boolean result = false;
        if (semantic != null && isSupported(desc)) {
            Session session = new EObjectQuery(semantic).getSession();
            // If the semantic doesn't belong to a session we don't check
            // viewpoint selection but only others things like domainClass
            if (session == null || (checkSelectedViewpoint && isRelatedViewpointSelected(session, desc)) || !checkSelectedViewpoint) {
                TableDescription tableDesc = (TableDescription) desc;
                ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semantic);
                if (accessor != null) {
                    result = checkDomainClass(accessor, semantic, tableDesc.getDomainClass());
                }
                result = result && checkPrecondition(semantic, tableDesc.getPreconditionExpression());
            }
        }
        return result;
    }

    @Override
    public DRepresentation createRepresentation(String name, EObject semantic, RepresentationDescription description, IProgressMonitor monitor) {
        DTable table = null;
        try {
            monitor.beginTask(MessageFormat.format(Messages.TableDialectServices_CreateTable, name), 11);
            monitor.subTask(MessageFormat.format(Messages.TableDialectServices_CreateTable, name));
            table = TableFactory.eINSTANCE.createDTable();
            table.setTarget(semantic);
            table.setDescription((TableDescription) description);
            monitor.worked(1);

            refresh(table, new SubProgressMonitor(monitor, 10));
        } finally {
            monitor.done();
        }
        return table;
    }

    /**
     * The <code>fullRefresh</code> is not taken into account for table dialect.
     *
     * {@inheritDoc}
     */
    @Override
    public void refresh(final DRepresentation representation, final boolean fullRefresh, final IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.TableDialectServices_RefreshTable, 1);
            DTable table = (DTable) representation;
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(table.getTarget());
            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);
            TableDescription description = table.getDescription();
            DTableSynchronizer sync = new DTableSynchronizerImpl(description, accessor, interpreter);
            sync.setTable(table);
            sync.refresh(new SubProgressMonitor(monitor, 1));
        } finally {
            monitor.done();
        }
    }

    @Override
    public void refreshImpactedElements(DRepresentation representation, Collection<Notification> notifications, IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.TableDialectServices_RefreshTable, 10);
            DTable table = (DTable) representation;
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(table.getTarget());
            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);

            Set<DTableElement> dTableElements = getTableElementsToRefresh(notifications, table);
            monitor.worked(2);
            DTableElementSynchronizer synchronizer = new DTableElementSynchronizer(accessor, interpreter);
            IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 8);
            try {
                subMonitor.beginTask(Messages.TableDialectServices_RefreshImpactedElements, dTableElements.size());
                for (DTableElement dTableElement : dTableElements) {
                    if (dTableElement instanceof DCell) {
                        synchronizer.refresh((DCell) dTableElement);
                    } else if (dTableElement instanceof DLine) {
                        synchronizer.refresh((DLine) dTableElement);
                        synchronizer.refreshSemanticElements(dTableElement, ((DLine) dTableElement).getOriginMapping());
                    } else if (dTableElement instanceof DColumn) {
                        synchronizer.refresh((DColumn) dTableElement);
                        synchronizer.refreshSemanticElements(dTableElement, ((DColumn) dTableElement).getOriginMapping());
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

    private Set<DTableElement> getTableElementsToRefresh(Collection<Notification> notifications, DTable table) {
        Set<DTableElement> tableElementsToRefresh = new HashSet<>();
        Session session = new org.eclipse.sirius.business.api.query.EObjectQuery(table.getTarget()).getSession();
        if (session != null) {
            ECrossReferenceAdapter xref = session.getSemanticCrossReferencer();
            // Deal with each notifier only one time.
            Set<EObject> alreadyDoneNotifiers = new HashSet<>();
            for (Notification notification : notifications) {
                Object notifier = notification.getNotifier();
                if (notifier instanceof EObject) {
                    EObject eObjectNotifier = (EObject) notifier;
                    if (alreadyDoneNotifiers.add(eObjectNotifier)) {
                        tableElementsToRefresh.addAll(getTableElementsToRefresh(eObjectNotifier, table, xref));
                    }
                }
            }
        }
        return tableElementsToRefresh;
    }

    private Set<DTableElement> getTableElementsToRefresh(EObject notifier, DTable table, ECrossReferenceAdapter xref) {
        Set<DTableElement> tableElementsToRefresh = new HashSet<>();
        Collection<EObject> inverseReferencers = new org.eclipse.sirius.business.api.query.EObjectQuery(notifier, xref).getInverseReferences(REPRESENTATION_ELEMENTS_INVERSE_REFERENCES);
        for (EObject inverseReferencer : inverseReferencers) {
            if (inverseReferencer instanceof DTableElement) {
                DTableElement tableElement = (DTableElement) inverseReferencer;
                if (isContainedWithinCurrentTable(tableElement, table)) {
                    tableElementsToRefresh.add(tableElement);
                }
            }
        }
        return tableElementsToRefresh;
    }

    private boolean isContainedWithinCurrentTable(DTableElement tableElement, DTable table) {
        return table == new DRepresentationElementQuery(tableElement).getParentRepresentation();
    }

    @Override
    public RepresentationDescription getDescription(DRepresentation representation) {
        if (isSupported(representation)) {
            return ((DTable) representation).getDescription();
        } else {
            return null;
        }
    }

    @Override
    public void initRepresentations(Viewpoint vp, EObject semantic, IProgressMonitor monitor) {
        super.initRepresentations(semantic, vp, TableDescription.class, monitor);
    }

    @Override
    protected <T extends RepresentationDescription> void initRepresentationForElement(T representationDescription, EObject semanticElement, IProgressMonitor monitor) {
        if (representationDescription instanceof TableDescription) {
            TableDescription tableDescription = (TableDescription) representationDescription;
            if (shouldInitializeRepresentation(semanticElement, tableDescription, tableDescription.getDomainClass())) {

                final ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticElement);
                final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(semanticElement);

                if (modelAccessor.eInstanceOf(semanticElement, tableDescription.getDomainClass())) {
                    boolean canCreate = true;
                    if (tableDescription.getPreconditionExpression() != null && !StringUtil.isEmpty(tableDescription.getPreconditionExpression())) {
                        try {
                            canCreate = InterpreterUtil.getInterpreter(semanticElement).evaluateBoolean(semanticElement, tableDescription.getPreconditionExpression());
                        } catch (final EvaluationException e) {
                            canCreate = false;
                        }
                    }
                    if (canCreate) {
                        try {
                            monitor.beginTask(MessageFormat.format(Messages.TableDialectServices_InitializeTable, new IdentifiedElementQuery(representationDescription).getLabel()), 1);
                            final TableCommandFactory tableCommandFactory = new TableCommandFactory(domain);
                            tableCommandFactory.setModelAccessor(modelAccessor);
                            DCommand command = tableCommandFactory.buildCreateTableFromDescription(tableDescription, semanticElement, new SubProgressMonitor(monitor, 1));
                            domain.getCommandStack().execute(command);
                        } finally {
                            monitor.done();
                        }
                    }
                }
            }
        }
    }

    @Override
    public DRepresentation copyRepresentation(DRepresentationDescriptor representationDescriptor, String name, Session session, IProgressMonitor monitor) {
        DRepresentation newRepresentation = super.copyRepresentation(representationDescriptor, name, session, monitor);
        DRepresentationDescriptorInternalHelper.createDRepresentationDescriptor(representationDescriptor.getRepresentation(), (DAnalysisSessionImpl) session,
                ((DSemanticDecorator) representationDescriptor.getRepresentation()).getTarget().eResource(), name, ""); //$NON-NLS-1$
        return newRepresentation;
    }

    @Override
    public IInterpretedExpressionQuery createInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        return new TableInterpretedExpressionQuery(target, feature);
    }

    @Override
    public boolean handles(RepresentationDescription representationDescription) {
        return representationDescription instanceof TableDescription;
    }

    @Override
    public boolean handles(RepresentationExtensionDescription representationExtensionDescription) {
        return false;
    }

}
