/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.dialect;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import org.eclipse.emf.common.util.WrappedException;
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
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
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
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.DiagramExportResult;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.internal.command.CreateAndStoreGMFDiagramCommand;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.DiagramRefresherHelper;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.decoration.SiriusDecoratorProvider;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportResult;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

/**
 * The default diagram ui services.
 *
 * @author cbrun
 */
public class DiagramDialectUIServices implements DialectUIServices {

    /**
     * The label used for the action which refreshes a diagram.
     */
    public static final String REFRESH_DIAGRAM = Messages.DiagramDialectUIServices_refreshDiagram;

    @Override
    public IEditorPart openEditor(Session session, DRepresentation dRepresentation, IProgressMonitor monitor) {
        DialectEditor result = null;
        try {
            monitor.beginTask(Messages.DiagramDialectUIServices_diagramOpeningMonitorTaskName, 15);
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_DIAGRAM_KEY);

            if (dRepresentation instanceof DSemanticDiagram) {
                final DSemanticDiagram diag = (DSemanticDiagram) dRepresentation;
                final Collection<EObject> gmfDiags = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diag);

                if (gmfDiags.isEmpty()) {
                    /*
                     * we have our diagrams but not the gmf ones => old aird version or corrupted file
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
        DRepresentationQuery query = new DRepresentationQuery(dRepresentation);
        DRepresentationDescriptor representationDescriptor = query.getRepresentationDescriptor();
        URI repDescURI = Optional.ofNullable(representationDescriptor).map(repDesc -> EcoreUtil.getURI(repDesc)).orElse(null);
        monitor.worked(1);
        final SessionEditorInput editorInput = new SessionEditorInput(uri, repDescURI, editorName, session);
        String representationName = representationDescriptor.getName();
        monitor.subTask(MessageFormat.format(Messages.DiagramDialectUIServices_diagramEditorOpeningMonitorTaskName, representationName));
        RunnableWithResult<DialectEditor> runnable = new RunnableWithResult.Impl<DialectEditor>() {

            @Override
            public void run() {
                IWorkbenchPage activePage = EclipseUIUtil.getActivePage();
                if (activePage != null) {
                    try {
                        IEditorPart editorPart = activePage.openEditor(editorInput, DDiagramEditor.EDITOR_ID);
                        if (editorPart instanceof DialectEditor) {
                            setResult((DialectEditor) editorPart);
                        }
                    } catch (final PartInitException e) {
                        DiagramPlugin.getDefault().logError(Messages.DiagramDialectUIServices_diagramEditorOpeningError, e);
                    } catch (IllegalStateException e) {
                        // Do no log this error that might be caused by an unreachable distant resource.
                    }
                }
            }

        };
        EclipseUIUtil.displaySyncExec(runnable);
        monitor.worked(10);
        if (runnable.getResult() != null) {
            dialectEditor = runnable.getResult();
        }
        return dialectEditor;
    }

    private Set<Viewpoint> activateNeededViewpoints(Session session, DDiagram dDiagram, IProgressMonitor monitor) {
        Set<Viewpoint> neededViewpoints = DialectManager.INSTANCE.getRequiredViewpoints(dDiagram);
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
        EclipseUIUtil.displayAsyncExec(new Runnable() {

            @Override
            public void run() {
                MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), Messages.DiagramDialectUIServices_requiredViewpointsDialogTitle,
                        MessageFormat.format(Messages.DiagramDialectUIServices_requiredViewpointsDialogMessage, description));
            }

        });
    }

    @Override
    public boolean canHandleEditor(final IEditorPart editorPart) {
        return editorPart instanceof DiagramDocumentEditor;
    }

    @Override
    public boolean closeEditor(final IEditorPart editorPart, final boolean save) {
        boolean result = false;
        if (editorPart instanceof DiagramDocumentEditor) {
            try {
                DiagramEditPart diagramEditPart = ((DiagramDocumentEditor) editorPart).getDiagramEditPart();
                if (diagramEditPart != null) {
                    diagramEditPart.deactivate();
                }
            } catch (final NullPointerException | IllegalStateException e) {
                // we might have an exception closing an editor which is
                // already in trouble
                DiagramPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, DiagramPlugin.ID, Messages.DiagramDialectUIServices_diagramEditPartDeactivationError));
            }

            try {
                // Call a preClose in sync mode before calling the real close that is done in async
                if (editorPart instanceof DialectEditor) {
                    Display display = Display.getCurrent();
                    if (display == null) {
                        display = PlatformUI.getWorkbench().getDisplay();
                    }
                    if (!display.isDisposed()) {
                        display.syncExec(new Runnable() {
                            @Override
                            public void run() {
                                if (!PlatformUI.getWorkbench().isClosing()) {
                                    ((DialectEditor) editorPart).preClose();
                                }
                            }
                        });
                    }
                }
                ((DiagramDocumentEditor) editorPart).close(save);
            } catch (final NullPointerException e) {
                // we might have an exception closing an editor which is
                // already in trouble
                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, Messages.DiagramDialectUIServices_diagramEditorClosingError, e));
            }

            // We suppose it is closed.
            result = true;
        }
        return result;
    }

    @Override
    public Collection<CommandParameter> provideNewChildDescriptors() {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.addAll(this.getDiagramTypesCreation());
        return newChilds;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationCreationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.add(new CommandParameter(null, feature, ToolFactory.eINSTANCE.createDiagramCreationDescription()));
        return newChilds;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationNavigationToolDescriptors(final Object feature) {
        final Collection<CommandParameter> newChilds = new ArrayList<CommandParameter>();
        newChilds.add(new CommandParameter(null, feature, ToolFactory.eINSTANCE.createDiagramNavigationDescription()));
        return newChilds;
    }

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

    @Override
    public boolean canHandle(final DRepresentation representation) {
        return representation instanceof DSemanticDiagram;
    }

    @Override
    public boolean canHandle(final DRepresentationDescriptor repDescriptor) {
        return repDescriptor.getDescription() instanceof DiagramDescription;
    }

    @Override
    public boolean canHandle(final RepresentationDescription description) {
        return description instanceof DiagramDescription;
    }

    @Override
    public boolean canHandle(final RepresentationExtensionDescription description) {
        return description instanceof DiagramExtensionDescription;
    }

    @Override
    public boolean canExport(ExportFormat format) {
        if (format.getDocumentFormat().equals(ExportDocumentFormat.NONE) || (format.getDocumentFormat().equals(ExportDocumentFormat.HTML) && DiagramEditPartService.canExportToHtml())) {
            return true;
        }
        return false;
    }

    @Override
    public ExportResult exportWithResult(final DRepresentation representation, final Session session, final IPath path, final ExportFormat format, final IProgressMonitor monitor)
            throws SizeTooLargeException {
        return exportWithResult(representation, session, path, format, monitor, true);
    }

    @Override
    public ExportResult exportWithResult(final DRepresentation representation, final Session session, final IPath path, final ExportFormat format, final IProgressMonitor monitor,
            boolean exportDecorations) throws SizeTooLargeException {

        final boolean exportToHtml = exportToHtml(format);
        final String imageFileExtension = getImageFileExtension(format);
        final IPath correctPath = getRealPath(path, exportToHtml);
        DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation).getRepresentationDescriptor();
        final Shell shell = new Shell();
        try {
            DiagramExportResult result = null;
            final Collection<EObject> data = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, representation);
            for (final EObject dataElement : data) {
                if (dataElement instanceof Diagram) {
                    final Diagram diagram = (Diagram) dataElement;

                    final DiagramEditPartService tool = new DiagramEditPartService(format.isSemanticTraceabilityEnabled());
                    configureScalingPolicy(tool, format.getScalingPolicy(), format.getScalingLevel());
                    if (exportToHtml) {
                        tool.exportToHtml();
                    }

                    boolean isActivateSiriusDecorationPrevious = SiriusDecoratorProvider.isActivateSiriusDecoration();
                    SiriusDecoratorProvider.setActivateSiriusDecoration(exportDecorations);
                    IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
                    boolean printDecoration = prefs.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name());
                    // to be exported, decorations have to be in a printable layer
                    prefs.setValue(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name(), exportDecorations);

                    final DiagramEditPart diagramEditPart = tool.createDiagramEditPart(diagram, shell, PreferencesHint.USE_DEFAULTS);
                    try {
                        /* refresh to avoid blank images */
                        diagramEditPart.getRoot().refresh();
                        /* validate to have all nodes in the right position */
                        diagramEditPart.getFigure().validate();
                        /*
                         * In the case of connection on EditParts created during first Refresh they will not appear
                         * until we refresh a second time Example of such cases are exchanges on DFI (mch)
                         */
                        diagramEditPart.getRoot().refresh();
                        /* flush the viewer to have all connections and ports */
                        diagramEditPart.getRoot().getViewer().flush();
                        // Process any event waiting in the user-interface queue, among others, to refresh connections
                        // of collapse compartment and hide them (see ShapeCompartmentEditPart.refreshConnections())
                        EclipseUIUtil.synchronizeWithUIThread();

                        /* do the effective export */
                        DiagramGenerator diagramGenerator = tool.copyToImage(diagramEditPart, correctPath, ImageFileFormat.resolveImageFormat(imageFileExtension), monitor);
                        result = new DiagramExportResult((DDiagram) representation, tool.getScalingFactor(), diagramGenerator.getImageMargin(), new HashSet<>(Arrays.asList(path, correctPath)));

                        // We finally ensure that the image has been created
                        if (!new File(correctPath.toOSString()).exists()) {
                            throw new FileNotFoundException(MessageFormat.format(Messages.DiagramDialectUIServices_exportedDiagramImageCreationError, correctPath));
                        }
                    } catch (final CoreException exception) {
                        if (exception instanceof SizeTooLargeException) {
                            throw (SizeTooLargeException) exception;
                        } else if (exception.getStatus() != null && exception.getStatus().getException() instanceof SWTException) {
                            /* Case that can occurs on Windows. */
                            throw new SizeTooLargeException(new Status(IStatus.ERROR, SiriusPlugin.ID, representationDescriptor.getName()));
                        } else {
                            SiriusPlugin.getDefault().error(MessageFormat.format(Messages.DiagramDialectUIServices_exportedDiagramImageCreationError, correctPath), exception);
                            throw new WrappedException(exception.getMessage(), exception);
                        }
                    } catch (final ArrayIndexOutOfBoundsException e) {
                        /*
                         * On linux and when using Cairo it might happen that the image creation fails with an
                         * ArrayIndexOutOfBoundsException from the Image.init() method. This happens when a cairo
                         * surface gets created correctly but for some reason (most likely a size limitation from cairo)
                         * the cairo_image_surface_get_stride() call returns 0, leading to the creation of a 0 sized
                         * buffer to copy the image to.
                         */
                        throw new SizeTooLargeException(new Status(IStatus.ERROR, SiriusPlugin.ID, representationDescriptor.getName()));
                    } finally {
                        SiriusDecoratorProvider.setActivateSiriusDecoration(isActivateSiriusDecorationPrevious);
                        prefs.setValue(SiriusDiagramUiPreferencesKeys.PREF_PRINT_DECORATION.name(), printDecoration);

                        diagramEditPart.deactivate();
                        // Memory leak : also disposing the DiagramGraphicalViewer associated to this DiagramEditPart
                        diagramEditPart.getViewer().flush();
                        diagramEditPart.getViewer().getEditDomain().getCommandStack().flush();
                        diagramEditPart.getViewer().getControl().dispose();
                        ((DiagramEditDomain) diagramEditPart.getViewer().getEditDomain()).removeViewer(diagramEditPart.getViewer());
                    }
                }
            }
            return result;
        }
        // The GMF refresh was added because of a possible ClassCastException during the diagram edit parts creation.
        // Since we decide to avoid any model modification during the export as image action, we catch this possible
        // exception here.
        catch (ClassCastException | NullPointerException e) {
            DiagramUIPlugin.getPlugin().error(MessageFormat.format(Messages.DiagramDialectUIServices_exportedDiagramImageClassCastError, representationDescriptor.getName()), e);
            // To avoid API break, we throw an unchecked exception that need to be caught and handled by the caller.
            throw new WrappedException(MessageFormat.format(Messages.DiagramDialectUIServices_exportedDiagramImageClassCastError, representationDescriptor.getName()), e);
        } catch (FileNotFoundException e) {
            DiagramUIPlugin.getPlugin().error(MessageFormat.format(Messages.DiagramDialectUIServices_exportedDiagramImageCreationError, correctPath), e);
            throw new WrappedException(e.getMessage(), e);
        } finally {
            disposeShell(shell);
        }
    }

    private void configureScalingPolicy(DiagramEditPartService tool, ExportFormat.ScalingPolicy policy, Integer scalingLevel) {
        Integer workspaceLevelDefault = null;
        switch (policy) {
        case AUTO_SCALING:
            tool.setAutoScalingEnabled(true);
            if (scalingLevel == null) {
                workspaceLevelDefault = SiriusEditPlugin.getPlugin().getPreferenceStore().getInt(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name()) * 10;
                tool.setDiagramScaleLevel(workspaceLevelDefault);
            } else {
                tool.setDiagramScaleLevel(scalingLevel);
            }
            break;
        case NO_SCALING:
            tool.setAutoScalingEnabled(false);
            tool.setDiagramScaleLevel(0);
            break;
        case WORKSPACE_DEFAULT:
            boolean workspaceDefault = DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiPreferencesKeys.PREF_SCALE_DIAGRAMS_ON_EXPORT.name());
            tool.setAutoScalingEnabled(workspaceDefault);
            if (workspaceDefault) {
                workspaceLevelDefault = SiriusEditPlugin.getPlugin().getPreferenceStore().getInt(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name()) * 10;
                tool.setDiagramScaleLevel(workspaceLevelDefault);
            }
            break;
        case AUTO_SCALING_IF_LARGER:
            tool.setAutoScalingEnabled(true);
            if (scalingLevel == null) {
                workspaceLevelDefault = SiriusEditPlugin.getPlugin().getPreferenceStore().getInt(SiriusUIPreferencesKeys.PREF_SCALE_LEVEL_DIAGRAMS_ON_EXPORT.name());
                tool.setDiagramScaleLevel(workspaceLevelDefault);
            } else {
                tool.setDiagramScaleLevel(scalingLevel);
            }
            tool.setAllowDownScaling(false);
            break;
        default:
            throw new UnsupportedOperationException();
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
                layer.setName(Messages.DefaultLayerName);
                specificDiagramDescription.setDefaultLayer(layer);
            }
            final CommandParameter typeCommandParameter = new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, specificDiagramDescription);
            result.add(typeCommandParameter);
        }
        return result;
    }

    @Override
    public String getEditorName(final DRepresentation representation) {
        String editorName = representation.getName();
        if (StringUtil.isEmpty(editorName)) {
            editorName = Messages.DiagramDialectUIServices_representationWithEmptyNameEditorName;
        }
        return editorName;
    }

    @Override
    public Collection<CommandParameter> provideTools(EObject context) {
        Collection<CommandParameter> toolsParameters = new ArrayList<>();
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            DiagramDescription diagramType = diagramTypeDescriptor.getDiagramDescriptionProvider().createDiagramDescription();
            if (hasParentOfType(context, diagramType.eClass())) {
                toolsParameters.addAll(diagramTypeDescriptor.getDiagramDescriptionProvider().collectToolCommands(context));
            }
        }
        return toolsParameters;
    }

    @Override
    public Collection<CommandParameter> provideAdditionalMappings(EObject context) {
        Collection<CommandParameter> mappings = new ArrayList<>();
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            DiagramDescription diagramType = diagramTypeDescriptor.getDiagramDescriptionProvider().createDiagramDescription();
            if (hasParentOfType(context, diagramType.eClass())) {
                mappings.addAll(diagramTypeDescriptor.getDiagramDescriptionProvider().collectMappingsCommands());
            }
        }
        return mappings;
    }

    @Override
    public ILabelProvider getHierarchyLabelProvider(ILabelProvider currentLabelProvider) {
        return new HierarchyLabelProvider(currentLabelProvider);
    }

    @Override
    public void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        setSelection(dialectEditor, selection, false);
    }

    @Override
    public void selectAndReveal(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        setSelection(dialectEditor, selection, true);

    }

    private void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection, boolean reveal) {
        if (dialectEditor instanceof DiagramEditor && selection != null) {
            DiagramEditor diagramEditor = (DiagramEditor) dialectEditor;
            List<EditPart> selectedParts = new ArrayList<>();
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
                if (reveal && !selectedParts.isEmpty()) {
                    graphicalViewer.reveal(selectedParts.get(0));
                }
            }
        }
    }

    @Override
    public Collection<DSemanticDecorator> getSelection(DialectEditor editor) {
        Collection<DSemanticDecorator> selection = new LinkedHashSet<>();
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
     * @return the editPart corresponding to the diagram element given as parameter or null if any
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

    @Override
    public String completeToolTipText(String toolTipText, EObject eObject, EStructuralFeature feature) {
        String toolTip = toolTipText;
        if (eObject instanceof EdgeCreationDescription) {
            if (feature != null && feature.equals(ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION)) {
                StringBuilder sb = new StringBuilder();
                sb.append(toolTipText);
                sb.append(MessageFormat.format(Messages.DiagramDialectUIServices_sourceViewPreDescription, IInterpreterSiriusVariables.SOURCE_VIEW_PRE));
                sb.append(MessageFormat.format(Messages.DiagramDialectUIServices_sourcePreDescription, IInterpreterSiriusVariables.SOURCE_PRE));
                sb.append(MessageFormat.format(Messages.DiagramDialectUIServices_targetViewPreDescription, IInterpreterSiriusVariables.TARGET_VIEW_PRE));
                sb.append(MessageFormat.format(Messages.DiagramDialectUIServices_targetPreDescription, IInterpreterSiriusVariables.TARGET_PRE));
                sb.append(MessageFormat.format(Messages.DiagramDialectUIServices_diagramDescription, IInterpreterSiriusVariables.DIAGRAM));
                toolTip = sb.toString();
            }
        }
        EPackage parentPackage = null;
        Option<EObject> parentDiagramDescription = new EObjectQuery(eObject).getFirstAncestorOfType(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramDescription());
        if (parentDiagramDescription.some()) {
            parentPackage = parentDiagramDescription.get().eClass().getEPackage();
        } else {
            Option<EObject> parentDiagramExtensionDescription = new EObjectQuery(eObject)
                    .getFirstAncestorOfType(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getDiagramExtensionDescription());
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
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#completeToolTipText(String, EObject)
     * @deprecated this method has not access to the feature of eObject. This is supported in
     *             org.eclipse.sirius.diagram.ui.business.internal.dialect
     *             .DiagramDialectUIServices.completeToolTipText(String, EObject, EStructuralFeature)
     */
    @Deprecated
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject) {
        return completeToolTipText(toolTipText, eObject, null);
    }

    @Override
    public void refreshEditor(DialectEditor dialectEditor, IProgressMonitor monitor) {
        if (dialectEditor.getRepresentation() instanceof DSemanticDiagram && dialectEditor instanceof DiagramEditor) {
            DSemanticDiagram diagram = (DSemanticDiagram) dialectEditor.getRepresentation();

            Set<EditPart> editPartsToRefresh = new HashSet<EditPart>(1);
            editPartsToRefresh.add(((DiagramEditor) dialectEditor).getDiagramEditPart());
            DiagramRefresherHelper.refreshEditParts(diagram, editPartsToRefresh);
        }
    }

}
