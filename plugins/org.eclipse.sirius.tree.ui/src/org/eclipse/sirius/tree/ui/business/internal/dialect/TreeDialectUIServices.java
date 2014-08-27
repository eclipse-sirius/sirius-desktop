/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.business.internal.dialect;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
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
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.internal.metamodel.TreeToolVariables;
import org.eclipse.sirius.tree.description.DescriptionFactory;
import org.eclipse.sirius.tree.description.TreeCreationDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeNavigationDescription;
import org.eclipse.sirius.tree.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.tree.provider.TreeItemProviderAdapterFactory;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentation;
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
import com.google.common.collect.Sets;

/**
 * Implementation of the UI services for the tree dialect.
 * 
 * @author pcdavid
 */
public class TreeDialectUIServices implements DialectUIServices {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandle(org.eclipse.sirius.viewpoint.DRepresentation)
     */
    public boolean canHandle(DRepresentation representation) {
        return representation instanceof DTree;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandle(org.eclipse.sirius.viewpoint.description.RepresentationDescription)
     *      )
     */
    public boolean canHandle(final RepresentationDescription representation) {
        return representation instanceof TreeDescription;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandle(org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription)
     *      )
     */
    public boolean canHandle(final RepresentationExtensionDescription description) {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandleEditor(org.eclipse.ui.IEditorPart)
     */
    public boolean canHandleEditor(IEditorPart editorPart) {
        return editorPart instanceof DTreeEditor;
    }

    /**
     * {@inheritDoc}
     */
    public IEditorPart openEditor(Session session, DRepresentation dRepresentation, IProgressMonitor monitor) {
        IEditorPart editorPart = null;
        try {
            monitor.beginTask("tree opening", 10);
            if (dRepresentation instanceof DTree) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_TREE_KEY);
                URI uri = EcoreUtil.getURI(dRepresentation);
                final IEditorInput editorInput = new SessionEditorInput(uri, getEditorName(dRepresentation), session);
                monitor.worked(2);
                monitor.subTask("tree opening : " + dRepresentation.getName());
                RunnableWithResult<IEditorPart> runnable = new RunnableWithResult.Impl<IEditorPart>() {
                    public void run() {
                        final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                        try {
                            setResult(page.openEditor(editorInput, DTreeEditor.ID));
                        } catch (final PartInitException e) {
                            // silent catch
                        }
                    }
                };
                PlatformUI.getWorkbench().getDisplay().syncExec(runnable);
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getEditorName(org.eclipse.sirius.viewpoint.DRepresentation)
     */
    public String getEditorName(DRepresentation representation) {
        String editorName = representation.getName();
        if (StringUtil.isEmpty(editorName)) {
            editorName = "New Tree";
        }
        return editorName;
    }

    /**
     * {@inheritDoc}
     */
    public boolean closeEditor(IEditorPart editorPart, boolean save) {
        boolean result = false;
        if (canHandleEditor(editorPart)) {
            try {
                ((DTreeEditor) editorPart).close(save);
            } catch (NullPointerException e) {
                // we might have an exception closing an editor which is
                // already in trouble
            }
            // We suppose it is closed.
            result = true;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#isRepresentationManagedByEditor(org.eclipse.sirius.viewpoint.DRepresentation,
     *      org.eclipse.ui.IEditorPart)
     */
    public boolean isRepresentationManagedByEditor(DRepresentation representation, IEditorPart editorPart) {
        boolean isRepresentationManagedByEditor = false;
        if (canHandleEditor(editorPart)) {
            DTreeEditor dTreeEditor = (DTreeEditor) editorPart;
            isRepresentationManagedByEditor = dTreeEditor.getRepresentation() != null && dTreeEditor.getRepresentation().equals(representation);
        }
        return isRepresentationManagedByEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#isRepresentationDescriptionManagedByEditor(org.eclipse.sirius.viewpoint.description.RepresentationDescription,
     *      org.eclipse.ui.IEditorPart)
     */
    public boolean isRepresentationDescriptionManagedByEditor(RepresentationDescription representationDescription, IEditorPart editorPart) {
        if (canHandleEditor(editorPart)) {
            DTreeEditor dtreeEditor = (DTreeEditor) editorPart;
            return EcoreUtil.equals(dtreeEditor.getTreeModel().getDescription(), representationDescription);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#createAdapterFactory()
     */
    public AdapterFactory createAdapterFactory() {
        final ComposedAdapterFactory factory = new ComposedAdapterFactory();
        factory.addAdapterFactory(new DescriptionItemProviderAdapterFactory());
        factory.addAdapterFactory(new TreeItemProviderAdapterFactory());
        return factory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canExport(org.eclipse.sirius.ui.business.api.dialect.ExportFormat)
     */
    public boolean canExport(ExportFormat format) {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#export(org.eclipse.sirius.viewpoint.DRepresentation,
     *      org.eclipse.sirius.business.api.session.Session,
     *      org.eclipse.core.runtime.IPath,
     *      org.eclipse.sirius.ui.business.api.dialect.ExportFormat,
     *      org.eclipse.core.runtime.IProgressMonitor)
     */
    public void export(DRepresentation representation, Session session, IPath path, ExportFormat format, IProgressMonitor monitor) {
        // Nothing to do for trees.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#provideNewChildDescriptors()
     */
    public Collection<CommandParameter> provideNewChildDescriptors() {
        Collection<CommandParameter> newChilds = Lists.newArrayList();
        TreeDescription treeDescription = org.eclipse.sirius.tree.description.DescriptionFactory.eINSTANCE.createTreeDescription();
        newChilds.add(new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, treeDescription));
        return newChilds;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#provideRepresentationCreationToolDescriptors(java.lang.Object)
     */
    public Collection<CommandParameter> provideRepresentationCreationToolDescriptors(Object feature) {
        Collection<CommandParameter> newChilds = Lists.newArrayList();
        TreeCreationDescription treeCreationDescription = DescriptionFactory.eINSTANCE.createTreeCreationDescription();
        new TreeToolVariables().doSwitch(treeCreationDescription);
        newChilds.add(new CommandParameter(null, feature, treeCreationDescription));
        return newChilds;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#provideRepresentationNavigationToolDescriptors(java.lang.Object)
     */
    public Collection<CommandParameter> provideRepresentationNavigationToolDescriptors(Object feature) {
        Collection<CommandParameter> newChilds = Lists.newArrayList();
        TreeNavigationDescription treeNavigationDescription = DescriptionFactory.eINSTANCE.createTreeNavigationDescription();
        new TreeToolVariables().doSwitch(treeNavigationDescription);
        newChilds.add(new CommandParameter(null, feature, treeNavigationDescription));
        return newChilds;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#provideTools(org.eclipse.emf.ecore.EObject)
     */
    public Collection<CommandParameter> provideTools(EObject object) {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#provideAdditionalMappings(org.eclipse.emf.ecore.EObject)
     */
    public Collection<CommandParameter> provideAdditionalMappings(EObject object) {
        return Collections.emptyList();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getHierarchyLabelProvider(ILabelProvider)
     */
    public ILabelProvider getHierarchyLabelProvider(ILabelProvider currentLabelProvider) {
        return new HierarchyLabelTreeProvider(currentLabelProvider);
    }

    /**
     * {@inheritDoc}
     */
    public void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        if (dialectEditor instanceof DTreeEditor) {
            Viewer viewer = ((DTreeEditor) dialectEditor).getViewer();
            Iterable<DTreeItem> treeElements = Iterables.filter(selection, DTreeItem.class);
            if (viewer != null) {
                viewer.setSelection(new StructuredSelection(Lists.newArrayList(treeElements)));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getSelection(org.eclipse.sirius.ui.business.api.dialect.DialectEditor)
     */
    public Collection<DSemanticDecorator> getSelection(DialectEditor editor) {
        Collection<DSemanticDecorator> selection = Sets.newLinkedHashSet();
        if (editor instanceof DTreeEditor) {
            DTreeEditor dEditor = (DTreeEditor) editor;
            if (editor.getSite() != null && editor.getSite().getSelectionProvider() != null) {
                ISelection sel = dEditor.getSite().getSelectionProvider().getSelection();
                if (sel instanceof IStructuredSelection) {
                    Iterables.addAll(selection, Iterables.filter(((IStructuredSelection) sel).toList(), DSemanticDecorator.class));
                }
            }
        }
        return selection;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#completeToolTipText(String,
     *      EObject)
     */
    public String completeToolTipText(String toolTipText, EObject eObject) {
        return toolTipText;
    }
}
