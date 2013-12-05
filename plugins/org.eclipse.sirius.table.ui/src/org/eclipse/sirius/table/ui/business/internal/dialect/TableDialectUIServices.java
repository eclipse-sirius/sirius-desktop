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
package org.eclipse.sirius.table.ui.business.internal.dialect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.business.internal.metamodel.TableToolVariables;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.table.metamodel.table.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.table.metamodel.table.provider.TableItemProviderAdapterFactory;
import org.eclipse.sirius.table.tools.api.export.TableExportHelper;
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
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * The table dialect ui services.
 * 
 * @author lredor
 */
public class TableDialectUIServices implements DialectUIServices {
    /**
     * {@inheritDoc}
     */
    public IEditorPart openEditor(Session session, DRepresentation dRepresentation, IProgressMonitor monitor) {
        IEditorPart editorPart = null;
        try {
            monitor.beginTask("table opening", 10);
            if (dRepresentation instanceof DTable) {
                DTable dTable = (DTable) dRepresentation;
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_TABLE_KEY);
                final URI uri = EcoreUtil.getURI(dTable);
                final String editorName = DialectUIManager.INSTANCE.getEditorName(dTable);
                monitor.worked(2);
                final IEditorInput editorInput = new SessionEditorInput(uri, editorName, session);

                final String editorId;
                if (dTable.getDescription() instanceof EditionTableDescription) {
                    editorId = DTableEditionEditor.ID;
                } else if (dTable.getDescription() instanceof CrossTableDescription) {
                    editorId = DTableCrossEditor.ID;
                } else {
                    editorId = null;
                }
                if (editorId != null) {
                    monitor.subTask("table opening : " + dRepresentation.getName());
                    RunnableWithResult<IEditorPart> runnable = new RunnableWithResult.Impl<IEditorPart>() {

                        public void run() {
                            final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                            try {
                                setResult(page.openEditor(editorInput, editorId));
                            } catch (final PartInitException e) {
                                // silent catch
                            }
                        }
                    };
                    PlatformUI.getWorkbench().getDisplay().syncExec(runnable);
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

    /**
     * 
     * {@inheritDoc}
     */
    public Collection<CommandParameter> provideNewChildDescriptors() {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.add(new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, DescriptionFactory.eINSTANCE.createEditionTableDescription()));
        newChilds.add(new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, DescriptionFactory.eINSTANCE.createCrossTableDescription()));
        return newChilds;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Collection<CommandParameter> provideRepresentationCreationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        TableCreationDescription tableCreationDescription = DescriptionFactory.eINSTANCE.createTableCreationDescription();
        new TableToolVariables().doSwitch(tableCreationDescription);
        newChilds.add(new CommandParameter(null, feature, tableCreationDescription));
        return newChilds;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public Collection<CommandParameter> provideRepresentationNavigationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        TableNavigationDescription tableNavigationDescription = DescriptionFactory.eINSTANCE.createTableNavigationDescription();
        new TableToolVariables().doSwitch(tableNavigationDescription);
        newChilds.add(new CommandParameter(null, feature, tableNavigationDescription));
        return newChilds;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public AdapterFactory createAdapterFactory() {
        final ComposedAdapterFactory factory = new ComposedAdapterFactory();
        factory.addAdapterFactory(new DescriptionItemProviderAdapterFactory());
        factory.addAdapterFactory(new TableItemProviderAdapterFactory());
        return factory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandleEditor(org.eclipse.ui.IEditorPart)
     */
    public boolean canHandleEditor(final IEditorPart editorPart) {
        return editorPart instanceof AbstractDTableEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#closeEditor(org.eclipse.ui.IEditorPart,
     *      boolean)
     */
    public boolean closeEditor(final IEditorPart editorPart, final boolean save) {
        final boolean result = true;
        if (editorPart instanceof AbstractDTableEditor) {
            // We launch the close in asyncExec to avoid the problem with the
            // WorkspaceSynchronizer
            final Display display = editorPart.getSite().getShell().getDisplay();
            display.asyncExec(new Runnable() {
                public void run() {

                    try {
                        DTable dTable = ((AbstractDTableEditor) editorPart).getTableModel();
                        if (dTable != null) {
                            dTable.deactivate();
                        }
                    } catch (IllegalStateException e) {
                        // Can occur when Eobject has been disposed
                    } finally {
                        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(editorPart, save);
                    }
                }
            });
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#isRepresentationManagedByEditor(org.eclipse.sirius.viewpoint.DRepresentation,
     *      org.eclipse.ui.IEditorPart)
     */
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#isRepresentationDescriptionManagedByEditor(org.eclipse.sirius.viewpoint.description.RepresentationDescription,
     *      org.eclipse.ui.IEditorPart)
     */
    public boolean isRepresentationDescriptionManagedByEditor(final RepresentationDescription representationDescription, final IEditorPart editor) {
        if (editor instanceof AbstractDTableEditor) {
            final AbstractDTableEditor tableEditor = (AbstractDTableEditor) editor;
            return EcoreUtil.equals(tableEditor.getTableModel().getDescription(), representationDescription);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandle(org.eclipse.sirius.viewpoint.DRepresentation)
     */
    public boolean canHandle(final DRepresentation representation) {
        return representation instanceof DTable;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canExport(ExportFormat format) {
        return format.getDocumentFormat().equals(ExportDocumentFormat.CSV);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#export(org.eclipse.sirius.viewpoint.DRepresentation,
     *      org.eclipse.sirius.business.api.session.Session)
     */
    public void export(final DRepresentation representation, final Session session, final IPath path, final ExportFormat exportFormat, final IProgressMonitor monitor) {
        String content = null;
        if (exportFormat.getDocumentFormat().equals(ExportDocumentFormat.CSV)) {
            content = TableExportHelper.INSTANCE.exportToCsv((DTable) representation);
        }
        if (!StringUtil.isEmpty(content)) {
            TableExportHelper.INSTANCE.saveContent(content, path.toOSString());
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getEditorName(DRepresentation representation) {
        String editorName = representation.getName();
        if (StringUtil.isEmpty(editorName)) {
            editorName = "New Table";
        }
        return editorName;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<CommandParameter> provideTools(EObject context) {
        return Lists.newArrayList();
    }

    /**
     * {@inheritDoc}
     */
    public Collection<CommandParameter> provideAdditionalMappings(EObject object) {
        return Lists.newArrayList();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getHierarchyLabelProvider(ILabelProvider)
     */
    public ILabelProvider getHierarchyLabelProvider(ILabelProvider labelProvider) {
        return new HierarchyLabelTableProvider(labelProvider);

    }

    /**
     * {@inheritDoc}
     */
    public void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        if (dialectEditor instanceof DTableEditor && dialectEditor instanceof IViewerProvider) {
            Viewer viewer = ((IViewerProvider) dialectEditor).getViewer();
            Iterable<DTableElement> tableElements = Iterables.filter(selection, DTableElement.class);
            viewer.setSelection(new StructuredSelection(Lists.newArrayList(tableElements)));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getSelection(org.eclipse.sirius.ui.business.api.dialect.DialectEditor)
     */
    public Collection<DSemanticDecorator> getSelection(DialectEditor editor) {
        Collection<DSemanticDecorator> selection = Sets.newLinkedHashSet();
        if (editor instanceof DTableEditor) {
            DTableEditor dEditor = (DTableEditor) editor;
            ISelection sel = dEditor.getSite().getSelectionProvider().getSelection();

            if (sel instanceof IStructuredSelection) {
                Iterables.addAll(selection, Iterables.filter(((IStructuredSelection) sel).toList(), DSemanticDecorator.class));
            }
        }
        return selection;
    }

}
