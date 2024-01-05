/*******************************************************************************
@ * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.ui.business.internal.dialect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
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
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.internal.dialect.description.TreeToolVariables;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.tree.provider.TreeItemProviderAdapterFactory;
import org.eclipse.sirius.tree.ui.business.internal.refresh.TreeRefresherHelper;
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportResult;
import org.eclipse.sirius.ui.business.api.dialect.HierarchyLabelProvider;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
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

/**
 * Implementation of the UI services for the tree dialect.
 *
 * @author pcdavid
 */
public class TreeDialectUIServices implements DialectUIServices {

    @Override
    public boolean canHandle(DRepresentation representation) {
        return representation instanceof DTree;
    }

    @Override
    public boolean canHandle(DRepresentationDescriptor representationDescriptor) {
        return representationDescriptor.getDescription() instanceof TreeDescription;
    }

    @Override
    public boolean canHandle(final RepresentationDescription representation) {
        return representation instanceof TreeDescription;
    }

    @Override
    public boolean canHandle(final RepresentationExtensionDescription description) {
        return false;
    }

    @Override
    public boolean canHandleEditor(IEditorPart editorPart) {
        return editorPart instanceof DTreeEditor;
    }

    @Override
    public IEditorPart openEditor(Session session, DRepresentation dRepresentation, IProgressMonitor monitor) {
        IEditorPart editorPart = null;
        try {
            monitor.beginTask(Messages.TreeDialectUIServices_treeOpening, 10);
            if (dRepresentation instanceof DTree) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_TREE_KEY);
                URI uri = EcoreUtil.getURI(dRepresentation);
                DRepresentationQuery query = new DRepresentationQuery(dRepresentation);
                DRepresentationDescriptor representationDescriptor = query.getRepresentationDescriptor();
                URI repDescURI = Optional.ofNullable(representationDescriptor).map(repDesc -> EcoreUtil.getURI(repDesc)).orElse(null);
                final IEditorInput editorInput = new SessionEditorInput(uri, repDescURI, getEditorName(dRepresentation), session);
                monitor.worked(2);
                monitor.subTask(Messages.TreeDialectUIServices_treeOpening + " : " + representationDescriptor.getName()); //$NON-NLS-1$
                RunnableWithResult<IEditorPart> runnable = new RunnableWithResult.Impl<IEditorPart>() {
                    @Override
                    public void run() {
                        final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                        try {
                            setResult(page.openEditor(editorInput, DTreeEditor.ID));
                        } catch (final PartInitException e) {
                            TreeUIPlugin.getPlugin().log(new Status(IStatus.ERROR, TreeUIPlugin.ID, Messages.TreeDialectUIServices_errorOpeningEditor, e));
                        }
                    }
                };
                EclipseUIUtil.displaySyncExec(runnable);
                monitor.worked(8);
                IEditorPart result = runnable.getResult();
                if (canHandleEditor(result)) {
                    DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_TREE_KEY);
                    editorPart = result;
                }
            }
        } finally {
            monitor.done();
        }
        return editorPart;
    }

    @Override
    public String getEditorName(DRepresentation representation) {
        String editorName = representation.getName();
        if (StringUtil.isEmpty(editorName)) {
            editorName = Messages.TreeDialectUIServices_newTree;
        }
        return editorName;
    }

    @Override
    public boolean closeEditor(IEditorPart editorPart, boolean save) {
        boolean result = false;
        if (canHandleEditor(editorPart)) {
            try {
                ((DTreeEditor) editorPart).close(save);
            } catch (NullPointerException e) {
                // we might have an exception closing an editor which is
                // already in trouble
                TreeUIPlugin.getPlugin().log(new Status(IStatus.ERROR, TreeUIPlugin.ID, Messages.TreeDialectUIServices_errorOpeningEditor, e));
            }
            // We suppose it is closed.
            result = true;
        }
        return result;
    }

    @Override
    public boolean isRepresentationManagedByEditor(DRepresentation representation, IEditorPart editorPart) {
        boolean isRepresentationManagedByEditor = false;
        if (canHandleEditor(editorPart)) {
            DTreeEditor dTreeEditor = (DTreeEditor) editorPart;
            isRepresentationManagedByEditor = dTreeEditor.getRepresentation() != null && dTreeEditor.getRepresentation().equals(representation);
        }
        return isRepresentationManagedByEditor;
    }

    @Override
    public boolean isRepresentationDescriptionManagedByEditor(RepresentationDescription representationDescription, IEditorPart editorPart) {
        if (canHandleEditor(editorPart)) {
            DTreeEditor dtreeEditor = (DTreeEditor) editorPart;
            return EcoreUtil.equals(dtreeEditor.getTreeModel().getDescription(), representationDescription);
        } else {
            return false;
        }
    }

    @Override
    public AdapterFactory createAdapterFactory() {
        final ComposedAdapterFactory factory = new ComposedAdapterFactory();
        factory.addAdapterFactory(new DescriptionItemProviderAdapterFactory());
        factory.addAdapterFactory(new TreeItemProviderAdapterFactory());
        return factory;
    }

    @Override
    public boolean canExport(ExportFormat format) {
        return false;
    }

    @Override
    public ExportResult exportWithResult(DRepresentation representation, Session session, IPath path, ExportFormat format, IProgressMonitor monitor) {
        // Nothing to do for trees.
        return new ExportResult(representation, Collections.emptySet());
    }

    @Override
    public Collection<CommandParameter> provideNewChildDescriptors() {
        Collection<CommandParameter> newChilds = new ArrayList<>();
        TreeDescription treeDescription = org.eclipse.sirius.tree.description.DescriptionFactory.eINSTANCE.createTreeDescription();
        newChilds.add(new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, treeDescription));
        return newChilds;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationCreationToolDescriptors(Object feature) {
        Collection<CommandParameter> newChilds = new ArrayList<>();
        TreeCreationDescription treeCreationDescription = DescriptionFactory.eINSTANCE.createTreeCreationDescription();
        new TreeToolVariables().doSwitch(treeCreationDescription);
        newChilds.add(new CommandParameter(null, feature, treeCreationDescription));
        return newChilds;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationNavigationToolDescriptors(Object feature) {
        Collection<CommandParameter> newChilds = new ArrayList<>();
        TreeNavigationDescription treeNavigationDescription = DescriptionFactory.eINSTANCE.createTreeNavigationDescription();
        new TreeToolVariables().doSwitch(treeNavigationDescription);
        newChilds.add(new CommandParameter(null, feature, treeNavigationDescription));
        return newChilds;
    }

    @Override
    public Collection<CommandParameter> provideTools(EObject object) {
        return Collections.emptyList();
    }

    @Override
    public Collection<CommandParameter> provideAdditionalMappings(EObject object) {
        return Collections.emptyList();
    }

    @Override
    public ILabelProvider getHierarchyLabelProvider(ILabelProvider currentLabelProvider) {
        return new HierarchyLabelProvider(currentLabelProvider);
    }

    @Override
    public void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        if (dialectEditor instanceof DTreeEditor) {
            Viewer viewer = ((DTreeEditor) dialectEditor).getViewer();
            if (viewer != null) {
                // @formatter:off
                List<DTreeItem> treeElements = selection.stream()
                                                        .filter(DTreeItem.class::isInstance)
                                                        .map(DTreeItem.class::cast)
                                                        .toList();
                // @formatter:on
                viewer.setSelection(new StructuredSelection(treeElements));
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
        if (editor instanceof DTreeEditor) {
            DTreeEditor dEditor = (DTreeEditor) editor;
            if (editor.getSite() != null && editor.getSite().getSelectionProvider() != null) {
                ISelection sel = dEditor.getSite().getSelectionProvider().getSelection();
                if (sel instanceof IStructuredSelection) {
                    for (Object element : ((IStructuredSelection) sel).toList()) {
                        if (element instanceof DSemanticDecorator) {
                            selection.add((DSemanticDecorator) element);
                        }
                    }
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
     *             org.eclipse.sirius.tree.ui.business.internal.dialect
     *             .TreeDialectUIServices.completeToolTipText(String, EObject, EStructuralFeature)
     */
    @Deprecated
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject) {
        return toolTipText;
    }

    @Override
    public void refreshEditor(DialectEditor dialectEditor, IProgressMonitor monitor) {
        if (dialectEditor instanceof DTreeEditor) {
            final DTreeEditor treeEditor = (DTreeEditor) dialectEditor;
            TreeRefresherHelper.refreshEditor(treeEditor, new StructuredSelection(), monitor);
        }
    }

}
