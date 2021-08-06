/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.logger.MarkerRuntimeLogger;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceHandler;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.eclipse.sirius.common.ui.tools.api.editor.IEObjectNavigable;
import org.eclipse.sirius.common.ui.tools.api.util.SWTUtil;
import org.eclipse.sirius.common.ui.tools.internal.util.MigrationUIUtil;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.validation.SiriusInterpreterErrorDecorator;
import org.eclipse.sirius.editor.tools.internal.actions.ValidateAction;
import org.eclipse.sirius.model.business.api.helper.ViewpointUtil;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEditManager;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Version;

import com.google.common.base.Preconditions;

/**
 * The advanced Viewpoint Specification Model (*.odesign) Editor, base on the standard EMF-generated editor, but with
 * Sirius-specific customizations.
 */
public class CustomSiriusEditor extends SiriusEditor implements IEObjectNavigable {
    private static class ViewpointURIHandler extends URIHandlerImpl.PlatformSchemeAware {
        private final ResourceSet resourceSet;

        /**
         * Constructor.
         * 
         * @param resourceSet
         *            the resource set to use for the conversion of physical URIs to logical <code>viewpoint:/</code>
         *            URIs on save..
         */
        ViewpointURIHandler(ResourceSet resourceSet) {
            this.resourceSet = Preconditions.checkNotNull(resourceSet);
        }

        @Override
        public URI deresolve(URI uri) {
            if (!baseURI.isPlatform() && "viewpoint".equals(baseURI.scheme())) { //$NON-NLS-1$
                baseURI = resourceSet.getURIConverter().normalize(baseURI);
            }
            return super.deresolve(uri);
        }
    }

    /**
     * ID of the context.
     */
    public static final String CONTEXT_ID = "org.eclipse.sirius.editor.siriusEditorContext"; //$NON-NLS-1$

    private static final ImageDescriptor EXPAND_IMAGE_DESCRIPTOR = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditorPlugin.PLUGIN_ID, "icons/full/expandall.gif");

    private static final ImageDescriptor COLLAPSE_IMAGE_DESCRIPTOR = AbstractUIPlugin.imageDescriptorFromPlugin(SiriusEditorPlugin.PLUGIN_ID, "icons/full/collapseall.gif");

    private final RepresentationTemplateUpdateTrigger templateUpdateTrigger = new RepresentationTemplateUpdateTrigger();

    private final ViewpointURIHandler vsmURIHandler;

    private CommandStackListener cmdStackListener = new CommandStackListener() {
        @Override
        public void commandStackChanged(EventObject event) {
            getContainer().getDisplay().asyncExec(new Runnable() {
                @Override
                public void run() {
                    if (isDirty()) {
                        ToolBarManager tbm = getToolBarManager();
                        ValidateAction action = getValidateAction(tbm);
                        action.setImageDescriptor(ValidateAction.HIGHLIGHT_DESC);
                        tbm.update(true);
                    }
                }
            });
        }
    };

    private GeneratedElementsLabelProvider decoratingLabelProvider;

    private ValidationDecoration validationDecorator;

    /**
     * Constructor.
     */
    public CustomSiriusEditor() {
        editingDomain.getCommandStack().addCommandStackListener(cmdStackListener);
        editingDomain.getResourceSet().eAdapters().add(new ECrossReferenceAdapter());
        editingDomain.getResourceSet().eAdapters().add(new ModificationTrackingEnabler(editingDomain.getResourceSet()));
        vsmURIHandler = new ViewpointURIHandler(editingDomain.getResourceSet());
    }

    public Control getControl() {
        return getControl(0);
    }

    @Override
    public void gotoMarker(final IMarker marker) {
        super.gotoMarker(marker);
        try {
            if (MarkerRuntimeLogger.MARKER_TYPE.equals(marker.getType())) {
                String uriAttribute = marker.getAttribute(MarkerRuntimeLogger.URI_MARKER_ATTRIBUTE, null);
                if (uriAttribute != null) {
                    URI uri = URI.createURI(uriAttribute);
                    EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
                    if (eObject != null) {
                        setSelectionToViewer(Collections.singleton(editingDomain.getWrapper(eObject)));
                    }
                }
            }
        } catch (final CoreException e) {
            SiriusEditorPlugin.INSTANCE.log(e);
        } catch (org.eclipse.emf.common.util.WrappedException e) {
            // Marker will be deleted as its target does not exist any more
            try {
                marker.delete();
            } catch (CoreException e1) {
                // Cannot do anything
            }
        }
    }

    @Override
    public boolean navigateToEObject(final URI uri) {
        final EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
        if (eObject != null) {
            setSelectionToViewer(Collections.singleton(editingDomain.getWrapper(eObject)));
            return true;
        }
        return false;
    }

    @Override
    public void createPages() {
        super.createPages();

        // Activate context
        IContextService contextService = getSite().getService(IContextService.class);
        contextService.activateContext(CONTEXT_ID);

        if (selectionViewer != null) {
            selectionViewer.setLabelProvider(new CustomSiriusAdapterFactoryLabelProvider(adapterFactory, selectionViewer));
            selectionViewer.addFilter(new ViewerFilter() {
                @Override
                public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                    return !isEnvironmentResource(element) && !isMigrationAnnotation(element);
                }

                private boolean isEnvironmentResource(final Object element) {
                    return element instanceof Resource && ((Resource) element).getURI() != null && ViewpointUtil.ENVIRONMENT_URI_SCHEME.equals(((Resource) element).getURI().scheme());
                }

                private boolean isMigrationAnnotation(final Object element) {
                    return element instanceof DAnnotation && ((DAnnotation) element).eContainer() instanceof Group;
                }
            });
            validationDecorator = new ValidationDecoration();
            decoratingLabelProvider = new GeneratedElementsLabelProvider((IStyledLabelProvider) selectionViewer.getLabelProvider(), validationDecorator);
            decoratingLabelProvider.setLabelDecorator(new SiriusInterpreterErrorDecorator(this.getURIFromInput(getEditorInput())));

            selectionViewer.setLabelProvider(decoratingLabelProvider);
            selectionViewer.addDoubleClickListener(new IDoubleClickListener() {

                @Override
                public void doubleClick(DoubleClickEvent event) {
                    ISelection selection = selectionViewer.getSelection();
                    if (selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof EObject) {
                        EObject selectedEObject = (EObject) ((StructuredSelection) selection).getFirstElement();
                        if (RepresentationTemplateEditManager.INSTANCE.isGenerated(selectedEObject)) {
                            EObject original = RepresentationTemplateEditManager.INSTANCE.getSourceElement(selectedEObject);
                            if (original != null) {
                                setSelectionToViewer(Collections.singleton(editingDomain.getWrapper(original)));
                            }
                        } else if (selectedEObject instanceof JavaExtension) {
                            navigateToJavaExtension((JavaExtension) selectedEObject);
                        }

                    }
                }

            });

            revealRepresentationDescriptions();

            final ToolBarManager tbm = getToolBarManager();
            addTooBarActions(tbm);
            tbm.update(true);
        }
        editingDomain.getResourceSet().eAdapters().add(templateUpdateTrigger);
    }

    private void navigateToJavaExtension(JavaExtension ext) {
        String className = ext.getQualifiedClassName();
        Optional<IJavaProject> enclosingJavaProject = getEnclosingJavaProject(ext);
        enclosingJavaProject.ifPresent(prj -> {
            try {
                IType serviceClass = prj.findType(className);
                if (serviceClass != null && serviceClass.exists()) {
                    JavaUI.openInEditor(serviceClass);
                } else {
                    MessageDialog.openError(Display.getCurrent().getActiveShell(), Messages.CustomSiriusEditor_failedNavigationTitle,
                            MessageFormat.format(Messages.CustomSiriusEditor_failedNavigationMessage, className));
                }
            } catch (PartInitException | JavaModelException e) {
                SiriusEditorPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, SiriusEditorPlugin.PLUGIN_ID, e.getMessage(), e));
                MessageDialog.openError(Display.getCurrent().getActiveShell(), Messages.CustomSiriusEditor_failedNavigationTitle,
                        MessageFormat.format(Messages.CustomSiriusEditor_failedNavigationExceptionMessage, className));
            }
        });

    }

    private Optional<IJavaProject> getEnclosingJavaProject(EObject element) {
        Resource vsm = element.eResource();
        if (vsm.getURI().isPlatformResource()) {
            String projectName = vsm.getURI().segment(1);
            IProject rawProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
            if (rawProject != null && rawProject.exists()) {
                return Optional.ofNullable(JavaCore.create(rawProject));
            }
        }
        return Optional.empty();
    }

    private void addTooBarActions(final ToolBarManager tbm) {
        // Expand All
        tbm.add(new Action() {
            @Override
            public ImageDescriptor getImageDescriptor() {
                return EXPAND_IMAGE_DESCRIPTOR;
            }

            @Override
            public String getToolTipText() {
                return "Expand All";
            }

            @Override
            public String getId() {
                return "ExpandActionFromEditor";
            }

            @Override
            public void run() {
                try {
                    selectionViewer.getControl().setRedraw(false);
                    selectionViewer.expandAll();
                } finally {
                    selectionViewer.getControl().setRedraw(true);
                }

            }
        });
        // Collapse All
        tbm.add(new Action() {

            @Override
            public ImageDescriptor getImageDescriptor() {
                return COLLAPSE_IMAGE_DESCRIPTOR;
            }

            @Override
            public String getToolTipText() {
                return "Collapse All";
            }

            @Override
            public String getId() {
                return "CollapseActionFromEditor";
            }

            @Override
            public void run() {
                try {
                    selectionViewer.getControl().setRedraw(false);
                    selectionViewer.collapseToLevel(selectionViewer.getInput(), AbstractTreeViewer.ALL_LEVELS);
                } finally {
                    selectionViewer.getControl().setRedraw(true);
                }
            }
        });
        // Validate
        tbm.add(new ValidateAction(selectionViewer, this));
    }

    /**
     * Auto-expand just enough to make the representation descriptions initially visible, but nothing else. They are the
     * main point of focus of the specifier.
     */
    private void revealRepresentationDescriptions() {
        Optional<Group> group = getTopLevelGroup();
        if (group.isPresent()) {
            group.get().getOwnedViewpoints().stream().flatMap(v -> {
                // All elements inside the Viewpoint that we want to be revealed
                return Stream.concat(v.getOwnedRepresentations().stream(), v.getOwnedRepresentationExtensions().stream());
            }).forEach(selectionViewer::reveal);
        }
    }

    /**
     * Locate the main logical element (the top-level Group) of the VSM.
     * 
     * @return the main logical element of the VSM.
     */
    private Optional<Group> getTopLevelGroup() {
        EList<Resource> resources = editingDomain.getResourceSet().getResources();
        if (resources != null && !resources.isEmpty()) {
            Resource mainResource = resources.get(0);
            EList<EObject> roots = mainResource.getContents();
            if (!roots.isEmpty()) {
                EObject root = roots.get(0);
                if (root instanceof Group) {
                    return Optional.of((Group) root);
                }
            }
        }
        return Optional.empty();
    }

    private ToolBarManager getToolBarManager() {
        return currentViewerPane.getToolBarManager();
    }

    private ValidateAction getValidateAction(ToolBarManager tbm) {
        IContributionItem[] contributions = tbm.getItems();
        for (IContributionItem contribution : contributions) {
            if (contribution instanceof ActionContributionItem && ValidateAction.ID.equals(contribution.getId())) {
                IAction action = ((ActionContributionItem) contribution).getAction();
                if (action instanceof ValidateAction) {
                    return (ValidateAction) action;
                }
            }
        }
        return null;
    }

    @Override
    public void createModel() {
        URI resourceURI = getURIFromInput(getEditorInput());
        Exception exception = null;
        Resource resource = null;
        try {
            resource = editingDomain.getResourceSet().getResource(resourceURI, true);
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            exception = e;
            resource = editingDomain.getResourceSet().getResource(resourceURI, false);
        }

        if (resource instanceof XMLResource) {
            ((XMLResource) resource).getDefaultLoadOptions().put(AbstractSiriusMigrationService.OPTION_RESOURCE_NON_BATCH_MIGRATION, true);
        }

        if (isVSMMigrated(resource, getURIFromInput(getEditorInput())) && askUserToSaveMigration(resource)) {
            doSave(new NullProgressMonitor());
        }

        Diagnostic diagnostic = analyzeResourceProblems(resource, exception);
        if (diagnostic.getSeverity() != Diagnostic.OK) {
            resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
        }
        editingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);
    }

    private URI getURIFromInput(IEditorInput input) {
        final URI result;
        if (input == null) {
            result = null;
        } else if (input instanceof IFileEditorInput) {
            result = URI.createPlatformResourceURI(((IFileEditorInput) getEditorInput()).getFile().getFullPath().toString(), false);
        } else if (input instanceof URIEditorInput) {
            result = ((URIEditorInput) input).getURI();
        } else if (input instanceof IPathEditorInput) {
            result = URI.createFileURI(((IPathEditorInput) input).getPath().toOSString());
        } else if (input instanceof IURIEditorInput) {
            result = URI.createURI(((IURIEditorInput) input).getURI().toString());
        } else {
            result = input.getAdapter(URI.class);
        }
        return result;
    }

    @Override
    public void dispose() {
        super.dispose();

        editingDomain.getCommandStack().removeCommandStackListener(cmdStackListener);

        editingDomain.getResourceSet().eAdapters().remove(templateUpdateTrigger);
        if (decoratingLabelProvider != null) {
            decoratingLabelProvider.dispose();
        }
    }

    /**
     * {@inheritDoc}.
     * 
     * Overridden to add our own vsmURIHandler in save options. it's about cascading odesign references and to save
     * using logical URIs.
     */
    @Override
    public void doSave(IProgressMonitor progressMonitor) {
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            @Override
            public void execute(IProgressMonitor monitor) {
                boolean first = true;
                for (Resource resource : editingDomain.getResourceSet().getResources()) {
                    if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !editingDomain.isReadOnly(resource)) {
                        boolean vsmMigrated = isVSMMigrated(resource, getURIFromInput(getEditorInput()));
                        if (vsmMigrated || !resource.isTrackingModification() || resource.isModified()) {
                            try {
                                savedResources.add(resource);
                                if (resource instanceof XMLResource) {
                                    ((XMLResource) resource).getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, vsmURIHandler);
                                }
                                resource.save(Collections.emptyMap());
                                // CHECKSTYLE:OFF
                            } catch (Exception exception) {
                                // CHECKSTYLE:ON
                                resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
                            }
                            first = false;
                        }
                    }
                }
            }
        };

        updateProblemIndication = false;
        try {
            new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

            ((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
            firePropertyChange(IEditorPart.PROP_DIRTY);
            // CHECKSTYLE:OFF
        } catch (Exception exception) {
            // CHECKSTYLE:ON
            SiriusEditorPlugin.INSTANCE.log(exception);
        }
        updateProblemIndication = true;
        updateProblemIndication();
    }

    /**
     * Asks the user if the automatic migration must be save.
     * 
     * @param resource
     *            The resource to test
     * @return <code>true</code> if the user want to save the session, <code>false</code> otherwise
     */
    public boolean askUserToSaveMigration(Resource resource) {
        if (MigrationUIUtil.shouldUserBeWarnedAboutMigration(resource)) {
            String message = MessageFormat.format(org.eclipse.sirius.common.ui.Messages.MigrationUIUtil_askToSaveChanges, resource.getURI().lastSegment());
            return SWTUtil.showSaveDialogWithMessage(resource, message, false) == ISaveablePart2.YES;
        }
        return false;
    }

    /**
     * Returns true if the VSM has been migrated. False otherwise.
     * 
     * @param resource
     *            resource containing the VSM instance potentially migrated.
     * @param uri
     *            URI of the VSM to check against the instance.
     * @return true if the VSM has been migrated. False otherwise.
     */
    private boolean isVSMMigrated(Resource resource, URI uri) {
        VSMVersionSAXParser vsmVersionSAXParser = new VSMVersionSAXParser(uri);
        if (!VSMMigrationService.getInstance().getLastMigrationVersion().equals(Version.parseVersion(vsmVersionSAXParser.getVersion(new NullProgressMonitor())))) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * Trigger migration if required.
     */
    @Override
    protected void beforeReload(Resource resource) {
        if (resource instanceof DescriptionResourceImpl) {
            VSMVersionSAXParser parser = new VSMVersionSAXParser(resource.getURI());
            String loadedVersion = parser.getVersion(new NullProgressMonitor());
            boolean migrationIsNeeded = true;
            if (loadedVersion != null) {
                migrationIsNeeded = VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(loadedVersion));
            }
            if (migrationIsNeeded) {
                VSMExtendedMetaData extendedMetaData = new VSMExtendedMetaData(loadedVersion);
                VSMResourceHandler resourceHandler = new VSMResourceHandler(loadedVersion);

                // set load options
                final Map<Object, Object> loadOptions = new HashMap<Object, Object>();
                loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
                loadOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
                ((DescriptionResourceImpl) resource).getDefaultLoadOptions().putAll(loadOptions);

                // set save options
                final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
                saveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
                saveOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, resourceHandler);
                ((DescriptionResourceImpl) resource).getDefaultSaveOptions().putAll(saveOptions);
            }
        }
    }
}
