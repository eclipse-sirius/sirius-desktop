/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.dialect;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.concern.provider.ConcernItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.description.filter.provider.FilterItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.description.style.provider.StyleItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.provider.DiagramItemProviderAdapterFactory;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.command.CreateAndStoreGMFDiagramCommand;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * The default diagram ui services.
 * 
 * @author cbrun
 */
public class DiagramDialectUIServices implements DialectUIServices {

    /**
     * The label used for the action which refreshes a diagram.
     */
    public static final String REFRESH_DIAGRAM = "Refresh diagram";

    private static final String EXPORT_DIAGRAM_AS_IMAGE_ERROR_ON_CREATE_IMAGE = "The program was not able to create image file ";

    /**
     * {@inheritDoc}
     */
    @Override
    public IEditorPart openEditor(Session session, DRepresentation dRepresentation, IProgressMonitor monitor) {
        DialectEditor result = null;
        try {
            monitor.beginTask("diagram opening", 15);
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_DIAGRAM_KEY);

            if (dRepresentation instanceof DSemanticDiagram) {
                final DSemanticDiagram diag = (DSemanticDiagram) dRepresentation;
                final Collection<EObject> gmfDiags = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diag);

                if (gmfDiags.isEmpty()) {
                    /*
                     * we have our diagrams but not the gmf ones => old aird
                     * version or corrupted file
                     */
                    TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                    domain.getCommandStack().execute(new CreateAndStoreGMFDiagramCommand(session, diag));

                    gmfDiags.addAll(session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diag));
                    monitor.worked(1);
                }

                // If the current DDiagram is shared on a CDO repository and
                // some
                // needed Viewpoints are not activated (for example a
                // contributed
                // activated layer)
                Set<Viewpoint> viewpointsActivated = null;
                if (URIQuery.CDO_URI_SCHEME.equals(diag.eResource().getURI().scheme())) {
                    viewpointsActivated = activateNeededViewpoints(session, diag, monitor);
                }
                for (final EObject object : gmfDiags) {
                    final Diagram gmfDiag = (Diagram) object;
                    if (gmfDiag != null) {
                        result = openEditor(session, gmfDiag, dRepresentation, monitor);
                        new DiagramDialectArrangeOperation().arrange(result, diag);
                        monitor.worked(3);
                    }
                }
                if (viewpointsActivated != null && !viewpointsActivated.isEmpty()) {
                    informOfActivateNeededViewpoints(viewpointsActivated);
                }
            }
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_DIAGRAM_KEY);
        } finally {
            monitor.done();
        }
        return result;
    }

    private DialectEditor openEditor(Session session, Diagram gmfDiag, DRepresentation dRepresentation, IProgressMonitor monitor) {
        DialectEditor dialectEditor = null;
        URI uri = EcoreUtil.getURI(gmfDiag);
        String editorName = DialectUIManager.INSTANCE.getEditorName(dRepresentation);
        monitor.worked(1);
        final IEditorInput editorInput = new SessionEditorInput(uri, editorName, session);
        monitor.subTask("diagram editor opening : " + dRepresentation.getName());
        RunnableWithResult<DialectEditor> runnable = new RunnableWithResult.Impl<DialectEditor>() {

            @Override
            public void run() {
                final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    IEditorPart editorPart = page.openEditor(editorInput, DDiagramEditor.EDITOR_ID);
                    if (editorPart instanceof DialectEditor) {
                        setResult((DialectEditor) editorPart);
                    }
                } catch (final PartInitException e) {
                    DiagramPlugin.getDefault().logError("diagram editor opening error", e);
                }
            }

        };
        PlatformUI.getWorkbench().getDisplay().syncExec(runnable);
        monitor.worked(10);
        if (runnable.getResult() != null) {
            dialectEditor = runnable.getResult();
        }
        return dialectEditor;
    }

    private Set<Viewpoint> activateNeededViewpoints(Session session, DDiagram dDiagram, IProgressMonitor monitor) {
        List<Layer> activatedLayers = dDiagram.getActivatedLayers();
        Set<Viewpoint> neededViewpoints = new LinkedHashSet<Viewpoint>();
        for (Layer activatedLayer : activatedLayers) {
            if (!activatedLayer.eIsProxy() && activatedLayer.eContainer() != null) {
                Viewpoint viewpoint = (Viewpoint) activatedLayer.eContainer().eContainer();
                neededViewpoints.add(viewpoint);
            }
        }
        Set<Viewpoint> selectedViewpoints = new LinkedHashSet<Viewpoint>();
        for (Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
            selectedViewpoints.add(SiriusResourceHelper.getCorrespondingViewpoint(session, viewpoint));
        }
        neededViewpoints.removeAll(selectedViewpoints);
        if (!neededViewpoints.isEmpty()) {
            Command changeViewpointsSelectionCmd = new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(), neededViewpoints, Collections.<Viewpoint> emptySet(),
                    new SubProgressMonitor(monitor, neededViewpoints.size()));
            session.getTransactionalEditingDomain().getCommandStack().execute(changeViewpointsSelectionCmd);
            monitor.worked(1);
        }
        return neededViewpoints;
    }

    private void informOfActivateNeededViewpoints(Set<Viewpoint> viewpointsActivated) {
        Iterator<Viewpoint> iterator = viewpointsActivated.iterator();
        Viewpoint neededSirius = iterator.next();
        String viewpointsName = neededSirius.getName();
        while (iterator.hasNext()) {
            neededSirius = iterator.next();
            viewpointsName += ", " + neededSirius.getName(); //$NON-NLS-1$
        }
        final String description = viewpointsName;
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Viewpoints selection", "The current diagram requires some viewpoints selected (" + description
                        + "), because some activated layers are contributed by these viewpoints");
            }

        });
    }

    /**
     * Synchronizes the GMF diagram model according to the viewpoint
     * DSemanticDiagram model.
     * 
     * @param diagram
     *            the GMF diagram model to synchronize.
     */
    private void synchronizeDiagram(final Diagram diagram) {
        CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(diagram);
        canonicalSynchronizer.storeViewsToArrange(false);
        canonicalSynchronizer.synchronize();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandleEditor(org.eclipse.ui.IEditorPart)
     */
    @Override
    public boolean canHandleEditor(final IEditorPart editorPart) {
        return editorPart instanceof DiagramDocumentEditor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#closeEditor(org.eclipse.ui.IEditorPart,
     *      boolean)
     */
    @Override
    public boolean closeEditor(final IEditorPart editorPart, final boolean save) {
        boolean result = false;
        if (editorPart instanceof DiagramDocumentEditor) {
            try {
                ((DiagramDocumentEditor) editorPart).getDiagramEditPart().deactivate();
            } catch (final NullPointerException e) {
                // we might have an exception closing an editor which is
                // already in trouble
                DiagramPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, DiagramPlugin.ID, "Error while deactivating the representation, the remote server may be unreachable."));
            }

            try {
                ((DiagramDocumentEditor) editorPart).close(save);
            } catch (final NullPointerException e) {
                // we might have an exception closing an editor which is
                // already in trouble
                if (DiagramUIPlugin.getPlugin().isDebugging()) {
                    DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, "Error while closing the representation, the remote server may be unreachable."));
                }
            }

            // We suppose it is closed.
            result = true;
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Collection<CommandParameter> provideNewChildDescriptors() {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.addAll(this.getDiagramTypesCreation());
        return newChilds;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Collection<CommandParameter> provideRepresentationCreationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.add(new CommandParameter(null, feature, ToolFactory.eINSTANCE.createDiagramCreationDescription()));
        return newChilds;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public Collection<CommandParameter> provideRepresentationNavigationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.add(new CommandParameter(null, feature, ToolFactory.eINSTANCE.createDiagramNavigationDescription()));
        return newChilds;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public AdapterFactory createAdapterFactory() {
        final ComposedAdapterFactory factory = new ComposedAdapterFactory();
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            AdapterFactory aFactory = diagramTypeDescriptor.getDiagramDescriptionProvider().getAdapterFactory();
            if (aFactory != null) {
                factory.addAdapterFactory(aFactory);
            }
        }
        factory.addAdapterFactory(new DescriptionItemProviderAdapterFactory());
        factory.addAdapterFactory(new DiagramItemProviderAdapterFactory());
        factory.addAdapterFactory(new StyleItemProviderAdapterFactory());
        factory.addAdapterFactory(new ToolItemProviderAdapterFactory());
        factory.addAdapterFactory(new FilterItemProviderAdapterFactory());
        factory.addAdapterFactory(new ConcernItemProviderAdapterFactory());
        return factory;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#isRepresentationManagedByEditor(org.eclipse.sirius.viewpoint.DRepresentation,
     *      org.eclipse.ui.IEditorPart)
     */
    @Override
    public boolean isRepresentationManagedByEditor(final DRepresentation representation, final IEditorPart editorPart) {
        boolean isRepresentationManagedByEditor = false;
        if (editorPart instanceof DiagramDocumentEditor) {
            final DiagramDocumentEditor diagramDocumentEditor = (DiagramDocumentEditor) editorPart;
            DiagramEditPart diagramEditPart = diagramDocumentEditor.getDiagramEditPart();
            if (diagramEditPart instanceof IDDiagramEditPart) {
                IDDiagramEditPart idDiagramEditPart = (IDDiagramEditPart) diagramEditPart;
                if (idDiagramEditPart.resolveSemanticElement().equals(representation)) {
                    isRepresentationManagedByEditor = true;
                }
            }
        }
        return isRepresentationManagedByEditor;
    }

    // FXIME unit test this
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#isRepresentationDescriptionManagedByEditor(org.eclipse.sirius.viewpoint.description.RepresentationDescription,
     *      org.eclipse.ui.IEditorPart)
     */
    @Override
    public boolean isRepresentationDescriptionManagedByEditor(final RepresentationDescription representationDescription, final IEditorPart editor) {
        if (editor instanceof DiagramDocumentEditor) {
            final DiagramDocumentEditor diagramDocumentEditor = (DiagramDocumentEditor) editor;
            final EObject element = diagramDocumentEditor.getDiagram().getElement();
            if (element instanceof DSemanticDiagram) {
                final DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) element;
                final DiagramDescription description = dSemanticDiagram.getDescription();
                return EcoreUtil.equals(description, representationDescription);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandle(org.eclipse.sirius.viewpoint.DRepresentation)
     */
    @Override
    public boolean canHandle(final DRepresentation representation) {
        return representation instanceof DSemanticDiagram;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandle(org.eclipse.sirius.viewpoint.description.RepresentationDescription)
     */
    @Override
    public boolean canHandle(final RepresentationDescription description) {
        return description instanceof DiagramDescription;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#canHandle(org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription)
     *      )
     */
    @Override
    public boolean canHandle(final RepresentationExtensionDescription description) {
        return description instanceof DiagramExtensionDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExport(ExportFormat format) {
        if (format.getDocumentFormat().equals(ExportDocumentFormat.NONE) || (format.getDocumentFormat().equals(ExportDocumentFormat.HTML) && DiagramEditPartService.canExportToHtml())) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#export(org.eclipse.sirius.viewpoint.DRepresentation,
     *      org.eclipse.sirius.business.api.session.Session)
     */
    @Override
    public void export(final DRepresentation representation, final Session session, final IPath path, final ExportFormat format, final IProgressMonitor monitor) throws SizeTooLargeException {

        final boolean exportToHtml = exportToHtml(format);
        final String imageFileExtension = getImageFileExtension(format);
        final IPath correctPath = getRealPath(path, exportToHtml);

        final Shell shell = new Shell();
        try {

            final Collection<EObject> data = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, representation);
            for (final EObject dataElement : data) {
                if (dataElement instanceof Diagram) {
                    final Diagram diagram = (Diagram) dataElement;
                    synchronizeDiagram(diagram);

                    final DiagramEditPartService tool = new DiagramEditPartService();
                    if (exportToHtml) {
                        tool.exportToHtml();
                    }
                    final DiagramEditPart diagramEditPart = tool.createDiagramEditPart(diagram, shell, PreferencesHint.USE_DEFAULTS);

                    try {

                        /* refresh to avoid blank images */
                        diagramEditPart.getRoot().refresh();

                        /* validate to have all nodes in the right position */
                        diagramEditPart.getFigure().validate();
                        /*
                         * In the case of connection on EditParts created during
                         * first Refresh they will not appear until we refresh a
                         * second time Example of such cases are exchanges on
                         * DFI (mch)
                         */
                        diagramEditPart.getRoot().refresh();
                        /*
                         * flush the viewer to have all connections and ports
                         */
                        diagramEditPart.getRoot().getViewer().flush();

                        /* do the effective export */
                        tool.copyToImage(diagramEditPart, correctPath, ImageFileFormat.resolveImageFormat(imageFileExtension), monitor);

                        // We finally ensure that the image has been created
                        if (!new File(correctPath.toOSString()).exists()) {
                            throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, EXPORT_DIAGRAM_AS_IMAGE_ERROR_ON_CREATE_IMAGE + correctPath));
                        }
                    } catch (final CoreException exception) {
                        if (exception instanceof SizeTooLargeException) {
                            throw (SizeTooLargeException) exception;
                        }
                        SiriusPlugin.getDefault().error(EXPORT_DIAGRAM_AS_IMAGE_ERROR_ON_CREATE_IMAGE + correctPath, exception);
                    } finally {
                        diagramEditPart.deactivate();
                        // Memory leak : also disposing the
                        // DiagramGraphicalViewer associated to this
                        // DiagramEditPart
                        diagramEditPart.getViewer().flush();
                        diagramEditPart.getViewer().getEditDomain().getCommandStack().flush();
                        diagramEditPart.getViewer().getControl().dispose();
                        ((DiagramEditDomain) diagramEditPart.getViewer().getEditDomain()).removeViewer(diagramEditPart.getViewer());
                    }
                }
            }

        } finally {
            disposeShell(shell);
        }
    }

    private void disposeShell(final Shell shell) {
        Display.getCurrent().asyncExec(new Runnable() {
            @Override
            public void run() {
                shell.dispose();
            }
        });
    }

    private boolean exportToHtml(final ExportFormat format) {
        return format.getDocumentFormat().equals(ExportDocumentFormat.HTML);
    }

    private String getImageFileExtension(final ExportFormat format) {
        return format.getImageFormat().getName();
    }

    private IPath getRealPath(final IPath path, final boolean exportToHtml) {
        if (exportToHtml) {
            return path.removeFileExtension().addFileExtension("html"); //$NON-NLS-1$
        } else {
            return path;
        }
    }

    /**
     * Returns command parameters to create diagram type description.
     * 
     * @return command parameters to create diagram type description.
     */
    private Collection<CommandParameter> getDiagramTypesCreation() {
        final Collection<CommandParameter> result = new HashSet<CommandParameter>();
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            DiagramDescription specificDiagramDescription = diagramTypeDescriptor.getDiagramDescriptionProvider().createDiagramDescription();
            specificDiagramDescription.setEnablePopupBars(true);
            if (specificDiagramDescription.getDefaultLayer() == null) {
                Layer layer = DescriptionFactory.eINSTANCE.createLayer();
                layer.setName("Default");
                specificDiagramDescription.setDefaultLayer(layer);
            }
            final CommandParameter typeCommandParameter = new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, specificDiagramDescription);
            result.add(typeCommandParameter);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getEditorName(org.eclipse.sirius.viewpoint.DRepresentation)
     */
    @Override
    public String getEditorName(final DRepresentation representation) {
        String editorName = representation.getName();
        if (StringUtil.isEmpty(editorName)) {
            editorName = "New Diagram";
        }
        return editorName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<CommandParameter> provideTools(EObject context) {
        Collection<CommandParameter> toolsParameters = Lists.newArrayList();
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            DiagramDescription diagramType = diagramTypeDescriptor.getDiagramDescriptionProvider().createDiagramDescription();
            if (hasParentOfType(context, diagramType.eClass())) {
                toolsParameters.addAll(diagramTypeDescriptor.getDiagramDescriptionProvider().collectToolCommands(context));
            }
        }
        return toolsParameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<CommandParameter> provideAdditionalMappings(EObject context) {
        Collection<CommandParameter> mappings = Lists.newArrayList();
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            DiagramDescription diagramType = diagramTypeDescriptor.getDiagramDescriptionProvider().createDiagramDescription();
            if (hasParentOfType(context, diagramType.eClass())) {
                mappings.addAll(diagramTypeDescriptor.getDiagramDescriptionProvider().collectMappingsCommands());
            }
        }
        return mappings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getHierarchyLabelProvider(ILabelProvider)
     * 
     */
    @Override
    public ILabelProvider getHierarchyLabelProvider(ILabelProvider currentLabelProvider) {
        return new HierarchyLabelProvider(currentLabelProvider);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        if (dialectEditor instanceof DiagramEditor && selection != null) {
            DiagramEditor diagramEditor = (DiagramEditor) dialectEditor;
            List<EditPart> selectedParts = Lists.newArrayList();
            final EditPartViewer graphicalViewer = diagramEditor.getDiagramGraphicalViewer();

            Iterable<DDiagramElement> ddeSelection = Iterables.filter(selection, DDiagramElement.class);
            if (!Iterables.isEmpty(ddeSelection) && graphicalViewer != null) {
                Session session = SessionManager.INSTANCE.getSession(ddeSelection.iterator().next().getTarget());
                for (DDiagramElement dRepresentationElement : ddeSelection) {
                    EditPart selectedEditPart = getEditPart(dRepresentationElement, graphicalViewer, session);
                    if (selectedEditPart != null && selectedEditPart.isSelectable()) {
                        selectedParts.add(selectedEditPart);
                    }
                }
            }
            if (graphicalViewer != null) {
                graphicalViewer.setSelection(new StructuredSelection(selectedParts));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#getSelection(org.eclipse.sirius.ui.business.api.dialect.DialectEditor)
     */
    @Override
    public Collection<DSemanticDecorator> getSelection(DialectEditor editor) {
        Collection<DSemanticDecorator> selection = Sets.newLinkedHashSet();
        if (editor instanceof DiagramEditor) {
            DiagramEditor dEditor = (DiagramEditor) editor;
            IDiagramGraphicalViewer graphicalViewer = dEditor.getDiagramGraphicalViewer();

            if (graphicalViewer != null) {
                for (IGraphicalEditPart ep : Iterables.filter(graphicalViewer.getSelectedEditParts(), IGraphicalEditPart.class)) {
                    View view = ep.getNotationView();
                    if (view != null && view.getElement() instanceof DSemanticDecorator) {
                        selection.add((DSemanticDecorator) view.getElement());
                    }
                }
            }
        }
        return selection;
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param diagramElement
     *            the diagram element
     * @param graphicalViewer
     *            the editor containing the editPart
     * @param session
     *            the current session
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    protected IGraphicalEditPart getEditPart(final DDiagramElement diagramElement, final EditPartViewer graphicalViewer, Session session) {
        IGraphicalEditPart result = null;
        final View gmfView = SiriusGMFHelper.getGmfView(diagramElement, session);
        final Map<?, ?> editPartRegistry = graphicalViewer.getEditPartRegistry();
        if (editPartRegistry != null) {
            final Object editPart = editPartRegistry.get(gmfView);
            if (editPart instanceof IGraphicalEditPart) {
                result = (IGraphicalEditPart) editPart;
            }
        }
        return result;
    }

    private boolean hasParentOfType(EObject element, EClass eClass) {
        EObject context = element;
        boolean found = context.eClass() == eClass;
        while (!found && context != null) {
            found = context.eClass() == eClass;
            context = context.eContainer();
        }
        return found;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#completeToolTipText(String,
     *      EObject, EStructuralFeature)
     */
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject, EStructuralFeature feature) {
        String toolTip = toolTipText;
        if (eObject instanceof EdgeCreationDescription) {
            if (feature != null && feature.equals(ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION)) {
                StringBuilder sb = new StringBuilder();
                sb.append(toolTipText);
                String cr = "\n . "; //$NON-NLS-1$
                sb.append(cr + IInterpreterSiriusVariables.SOURCE_VIEW_PRE + ": diagram.EdgeTarget | (edge only) the source view of the current potential edge.");
                sb.append(cr + IInterpreterSiriusVariables.SOURCE_PRE + ": ecore.EObject | (edge only) the semantic element of $preSourceView.");
                sb.append(cr + IInterpreterSiriusVariables.TARGET_VIEW_PRE + ": diagram.EdgeTarget | (edge only) the target view of the current potential edge.");
                sb.append(cr + IInterpreterSiriusVariables.TARGET_PRE + ": ecore.EObject | (edge only) the semantic element of $preTargetView.");
                sb.append(cr + IInterpreterSiriusVariables.DIAGRAM + ": diagram.DDiagram | the diagram of the current potential edge");
                toolTip = sb.toString();
            }
        }
        EPackage parentPackage = null;
        Option<EObject> parentDiagramDescription = new EObjectQuery(eObject).getFirstAncestorOfType(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramDescription());
        if (parentDiagramDescription.some()) {
            parentPackage = parentDiagramDescription.get().eClass().getEPackage();
        } else {
            Option<EObject> parentDiagramExtensionDescription = new EObjectQuery(eObject).getFirstAncestorOfType(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
                    .getDiagramExtensionDescription());
            if (parentDiagramExtensionDescription.some()) {
                parentPackage = parentDiagramExtensionDescription.get().eClass().getEPackage();
            }
        }

        if (parentPackage != null) {
            for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
                if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(parentPackage)) {
                    toolTip = diagramTypeDescriptor.getDiagramDescriptionProvider().completeToolTipText(toolTip, eObject, feature);
                }
            }
        }
        return toolTip;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#completeToolTipText(String,
     *      EObject)
     * @deprecated this method has not access to the feature of eObject. This is
     *             supported in
     *             org.eclipse.sirius.diagram.ui.business.internal.dialect
     *             .DiagramDialectUIServices.completeToolTipText(String,
     *             EObject, EStructuralFeature)
     */
    @Deprecated
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject) {
        return completeToolTipText(toolTipText, eObject, null);
    }

}
