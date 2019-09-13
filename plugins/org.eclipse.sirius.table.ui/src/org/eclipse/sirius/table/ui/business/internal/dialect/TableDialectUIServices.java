/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.business.internal.dialect;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.table.business.internal.metamodel.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.table.metamodel.table.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.metamodel.table.provider.TableItemProviderAdapterFactory;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.table.tools.api.export.TableExportHelper;
import org.eclipse.sirius.table.ui.business.internal.refresh.TableRefresherHelper;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableCrossEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableEditionEditor;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportResult;
import org.eclipse.sirius.ui.business.api.dialect.HierarchyLabelProvider;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The table dialect ui services.
 *
 * @author lredor
 */
public class TableDialectUIServices implements DialectUIServices {

    @Override
    public IEditorPart openEditor(Session session, DRepresentation dRepresentation, IProgressMonitor monitor) {
        IEditorPart editorPart = null;
        try {
            monitor.beginTask(Messages.TableDialectUIServices_tableOpening, 10);
            if (dRepresentation instanceof DTable) {
                DTable dTable = (DTable) dRepresentation;
                DRepresentationQuery query = new DRepresentationQuery(dTable);
                DRepresentationDescriptor representationDescriptor = query.getRepresentationDescriptor();
                URI repDescURI = Optional.ofNullable(representationDescriptor).map(repDesc -> EcoreUtil.getURI(repDesc)).orElse(null);
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_TABLE_KEY);
                final URI uri = EcoreUtil.getURI(dTable);
                final String editorName = DialectUIManager.INSTANCE.getEditorName(dTable);
                monitor.worked(2);
                final IEditorInput editorInput = new SessionEditorInput(uri, repDescURI, editorName, session);

                final String editorId;
                if (dTable.getDescription() instanceof EditionTableDescription) {
                    editorId = DTableEditionEditor.ID;
                } else if (dTable.getDescription() instanceof CrossTableDescription) {
                    editorId = DTableCrossEditor.ID;
                } else {
                    editorId = null;
                }
                if (editorId != null) {
                    monitor.subTask(MessageFormat.format(Messages.TableDialectUIServices_tableOpeningVar, representationDescriptor.getName()));
                    RunnableWithResult<IEditorPart> runnable = new RunnableWithResult.Impl<IEditorPart>() {

                        @Override
                        public void run() {
                            final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                            try {
                                setResult(page.openEditor(editorInput, editorId));
                            } catch (final PartInitException e) {
                                TableUIPlugin.getPlugin().log(new Status(IStatus.ERROR, TableUIPlugin.ID, Messages.TableDialectUIServices_tableOpeningError, e));
                            }
                        }
                    };
                    EclipseUIUtil.displaySyncExec(runnable);
                    monitor.worked(8);
                    if (runnable.getResult() instanceof AbstractDTableEditor) {
                        editorPart = runnable.getResult();
                    }
                }
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_TABLE_KEY);
            }
        } finally {
            monitor.done();
        }
        return editorPart;
    }

    @Override
    public Collection<CommandParameter> provideNewChildDescriptors() {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.add(new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, DescriptionFactory.eINSTANCE.createEditionTableDescription()));
        newChilds.add(new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, DescriptionFactory.eINSTANCE.createCrossTableDescription()));
        return newChilds;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationCreationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        TableCreationDescription tableCreationDescription = DescriptionFactory.eINSTANCE.createTableCreationDescription();
        new TableToolVariables().doSwitch(tableCreationDescription);
        newChilds.add(new CommandParameter(null, feature, tableCreationDescription));
        return newChilds;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationNavigationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        TableNavigationDescription tableNavigationDescription = DescriptionFactory.eINSTANCE.createTableNavigationDescription();
        new TableToolVariables().doSwitch(tableNavigationDescription);
        newChilds.add(new CommandParameter(null, feature, tableNavigationDescription));
        return newChilds;
    }

    @Override
    public AdapterFactory createAdapterFactory() {
        final ComposedAdapterFactory factory = new ComposedAdapterFactory();
        factory.addAdapterFactory(new DescriptionItemProviderAdapterFactory());
        factory.addAdapterFactory(new TableItemProviderAdapterFactory());
        return factory;
    }

    @Override
    public boolean canHandleEditor(final IEditorPart editorPart) {
        return editorPart instanceof AbstractDTableEditor;
    }

    @Override
    public boolean closeEditor(final IEditorPart editorPart, final boolean save) {
        final boolean result = true;
        if (editorPart instanceof AbstractDTableEditor) {
            // We launch the close in asyncExec to avoid the problem with the
            // WorkspaceSynchronizer
            EclipseUIUtil.displayAsyncExec(() -> {
                IWorkbenchPage page = EclipseUIUtil.getActivePage();
                if (page != null) {
                    page.closeEditor(editorPart, save);
                }
            });
        }
        return result;
    }

    @Override
    public boolean isRepresentationManagedByEditor(final DRepresentation representation, final IEditorPart editorPart) {
        boolean isRepresentationManagedByEditor = false;
        if (editorPart instanceof AbstractDTableEditor) {
            AbstractDTableEditor tableEditor = (AbstractDTableEditor) editorPart;
            if (tableEditor.getTableModel() != null && tableEditor.getTableModel().equals(representation)) {
                isRepresentationManagedByEditor = true;
            }
        }
        return isRepresentationManagedByEditor;
    }

    @Override
    public boolean isRepresentationDescriptionManagedByEditor(final RepresentationDescription representationDescription, final IEditorPart editor) {
        if (editor instanceof AbstractDTableEditor) {
            final AbstractDTableEditor tableEditor = (AbstractDTableEditor) editor;
            return EcoreUtil.equals(tableEditor.getTableModel().getDescription(), representationDescription);
        }
        return false;
    }

    @Override
    public boolean canHandle(final DRepresentation representation) {
        return representation instanceof DTable;
    }

    @Override
    public boolean canHandle(final DRepresentationDescriptor representationDescriptor) {
        return representationDescriptor.getDescription() instanceof TableDescription;
    }

    @Override
    public boolean canHandle(final RepresentationDescription representation) {
        return representation instanceof TableDescription;
    }

    @Override
    public boolean canHandle(final RepresentationExtensionDescription description) {
        return false;
    }

    @Override
    public boolean canExport(ExportFormat format) {
        return format.getDocumentFormat().equals(ExportDocumentFormat.CSV);
    }

    @Override
    public ExportResult exportWithResult(DRepresentation representation, Session session, IPath path, ExportFormat format, IProgressMonitor monitor) throws SizeTooLargeException {
        return exportWithResult(representation, session, path, format, monitor, true);
    }

    @Override
    public ExportResult exportWithResult(final DRepresentation representation, final Session session, final IPath path, final ExportFormat exportFormat, final IProgressMonitor monitor,
            boolean exportDecorations) {
        String content = null;
        if (exportFormat.getDocumentFormat().equals(ExportDocumentFormat.CSV)) {
            content = TableExportHelper.INSTANCE.exportToCsv((DTable) representation);
        }
        if (!StringUtil.isEmpty(content)) {
            TableExportHelper.INSTANCE.saveContent(content, path.toOSString());
        }
        return new ExportResult(representation, Arrays.asList(path));
    }

    @Override
    public String getEditorName(DRepresentation representation) {
        String editorName = representation.getName();
        if (StringUtil.isEmpty(editorName)) {
            editorName = Messages.TableDialectUIServices_newTableName;
        }
        return editorName;
    }

    @Override
    public Collection<CommandParameter> provideTools(EObject context) {
        return new ArrayList<>();
    }

    @Override
    public Collection<CommandParameter> provideAdditionalMappings(EObject object) {
        return new ArrayList<>();
    }

    @Override
    public ILabelProvider getHierarchyLabelProvider(ILabelProvider labelProvider) {
        return new HierarchyLabelProvider(labelProvider);

    }

    @Override
    public void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        if (dialectEditor instanceof DTableEditor && dialectEditor instanceof IViewerProvider) {
            Viewer viewer = ((IViewerProvider) dialectEditor).getViewer();
            Iterable<DTableElement> tableElements = Iterables.filter(selection, DTableElement.class);
            if (viewer != null) {
                viewer.setSelection(new StructuredSelection(Lists.newArrayList(tableElements)));
            }
        }
    }

    @Override
    public void selectAndReveal(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        setSelection(dialectEditor, selection);
    }

    @Override
    public Collection<DSemanticDecorator> getSelection(DialectEditor editor) {
        Collection<DSemanticDecorator> selection = new LinkedHashSet<>();
        if (editor instanceof DTableEditor) {
            DTableEditor dEditor = (DTableEditor) editor;
            if (editor.getSite() != null && editor.getSite().getSelectionProvider() != null) {
                ISelection sel = dEditor.getSite().getSelectionProvider().getSelection();
                if (sel instanceof IStructuredSelection) {
                    Iterables.addAll(selection, Iterables.filter(((IStructuredSelection) sel).toList(), DSemanticDecorator.class));
                }
            }
        }
        return selection;
    }

    @Override
    public String completeToolTipText(String toolTipText, EObject eObject, EStructuralFeature feature) {
        return toolTipText;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#completeToolTipText(String, EObject)
     * @deprecated this method has not access to the feature of eObject. This is supported in
     *             org.eclipse.sirius.table.ui.business.internal.dialect
     *             .TableDialectUIServices.completeToolTipText(String, EObject, EStructuralFeature)
     */
    @Deprecated
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject) {
        return toolTipText;
    }

    @Override
    public void refreshEditor(DialectEditor dialectEditor, IProgressMonitor monitor) {
        if (dialectEditor instanceof AbstractDTableEditor) {
            final AbstractDTableEditor tableEditor = (AbstractDTableEditor) dialectEditor;
            TableRefresherHelper.refreshEditor(tableEditor, monitor);
        }
    }
}
