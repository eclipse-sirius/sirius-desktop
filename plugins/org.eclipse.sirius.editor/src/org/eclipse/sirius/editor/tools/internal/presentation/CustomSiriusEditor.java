/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.logger.MarkerRuntimeLogger;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.internal.migration.description.VSMExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceHandler;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.eclipse.sirius.business.internal.movida.DynamicVSMLoader;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointURIConverter;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointURIHandler;
import org.eclipse.sirius.common.ui.tools.api.editor.IEObjectNavigable;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.validation.SiriusInterpreterErrorDecorator;
import org.eclipse.sirius.editor.tools.internal.actions.ValidateAction;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEditManager;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.contexts.IContextService;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * The advanced Viewpoint Specification Model (*.odesign) Editor, base on the
 * standard EMF-generated editor, but with Sirius-specific customizations.
 */
public class CustomSiriusEditor extends SiriusEditor implements IEObjectNavigable {

    /**
     * ID of the context.
     */
    public static final String CONTEXT_ID = "org.eclipse.sirius.editor.siriusEditorContext"; //$NON-NLS-1$

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

    private DynamicVSMLoader loader;

    private VSMRequirementChangeAdapter tracker;

    /**
     * Constructor.
     */
    public CustomSiriusEditor() {
        editingDomain.getCommandStack().addCommandStackListener(cmdStackListener);
        editingDomain.getResourceSet().eAdapters().add(new ECrossReferenceAdapter());
        editingDomain.getResourceSet().eAdapters().add(new ModificationTrackingEnabler(editingDomain.getResourceSet()));
        vsmURIHandler = new ViewpointURIHandler(editingDomain.getResourceSet());
        if (Movida.isEnabled()) {
            ViewpointRegistry registry = (ViewpointRegistry) org.eclipse.sirius.business.api.componentization.ViewpointRegistry.getInstance();
            editingDomain.getResourceSet().setURIConverter(new ViewpointURIConverter(registry));
            loader = new DynamicVSMLoader(editingDomain.getResourceSet(), registry);
            loader.setErrorHandler(new Runnable() {
                @Override
                public void run() {
                    getSite().getShell().getDisplay().asyncExec(new Runnable() {
                        @Override
                        public void run() {
                            MessageDialog.openError(getSite().getShell(), "Error", "The editor will be closed.");
                            getSite().getPage().closeEditor(CustomSiriusEditor.this, false);
                            CustomSiriusEditor.this.dispose();
                        }
                    });
                }
            });
        }
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
        IContextService contextService = (IContextService) getSite().getService(IContextService.class);
        contextService.activateContext(CONTEXT_ID);

        if (selectionViewer != null) {
            selectionViewer.setLabelProvider(new CustomSiriusAdapterFactoryLabelProvider(adapterFactory));
            selectionViewer.addFilter(new ViewerFilter() {
                @Override
                public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                    return !isEnvironmentResource(element) && !isMigrationAnnotation(element);
                }

                private boolean isEnvironmentResource(final Object element) {
                    return element instanceof Resource && ((Resource) element).getURI() != null && SiriusUtil.ENVIRONMENT_URI_SCHEME.equals(((Resource) element).getURI().scheme());
                }

                private boolean isMigrationAnnotation(final Object element) {
                    return element instanceof DAnnotation && ((DAnnotation) element).eContainer() instanceof Group;
                }
            });
            validationDecorator = new ValidationDecoration();
            if (Movida.isEnabled()) {
                /*
                 * Customize the label provider to add the URI of the
                 * corresponding resource for Viewpoints loaded by dependency.
                 */
                decoratingLabelProvider = new GeneratedElementsLabelProvider((ILabelProvider) selectionViewer.getLabelProvider(), validationDecorator) {
                    @Override
                    public String getText(Object element) {
                        String result = super.getText(element);
                        if (element instanceof Viewpoint) {
                            Viewpoint viewpoint = (Viewpoint) element;
                            Resource resource = viewpoint.eResource();
                            if (resource != null) {
                                // CHECKSTYLE:OFF
                                if (resource.getResourceSet().getResources().indexOf(resource) != 0) {
                                    result = result + " (" + resource.getURI() + ")";
                                }
                                // CHECKSTYLE:ON
                            }
                        }
                        return result;
                    };
                };
            } else {
                decoratingLabelProvider = new GeneratedElementsLabelProvider((ILabelProvider) selectionViewer.getLabelProvider(), validationDecorator);
            }
            decoratingLabelProvider.setLabelDecorator(new SiriusInterpreterErrorDecorator(this.getURIFromInput(getEditorInput())));

            if (Movida.isEnabled()) {
                customizeContentProvider();
            }

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
                        }
                    }
                }
            });

            final ToolBarManager tbm = getToolBarManager();
            tbm.add(new ValidateAction(selectionViewer, this));
            tbm.update(true);
        }
        editingDomain.getResourceSet().eAdapters().add(templateUpdateTrigger);
    }

    /**
     * Customize the content provider not to show the whole resources loaded by
     * dependency, but only the Viewpoints (inside these resources) which the
     * main VSM depend on.
     */
    private void customizeContentProvider() {
        final ViewpointRegistry registry = (ViewpointRegistry) org.eclipse.sirius.business.api.componentization.ViewpointRegistry.getInstance();
        selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory) {
            @Override
            public Object[] getElements(Object object) {
                if (object instanceof ResourceSet) {
                    Set<EObject> viewpoints = getRequiredViewpoints(registry, (ResourceSet) object);
                    List<Object> elements = Lists.newArrayList();
                    Resource mainResource = ((ResourceSet) object).getResources().get(0);
                    elements.add(mainResource);
                    for (Viewpoint additionalVP : Iterables.filter(viewpoints, Viewpoint.class)) {
                        Resource resource = additionalVP.eResource();
                        if (resource != null && resource != mainResource) {
                            elements.add(additionalVP);
                        }
                    }
                    return elements.toArray();
                } else {
                    return super.getElements(object);
                }
            }

            private Set<EObject> getRequiredViewpoints(final ViewpointRegistry registry, ResourceSet resourceSet) {
                Set<EObject> viewpoints = Sets.newHashSet();
                for (final URI uri : loader.getRequiredViewpoints()) {
                    Option<URI> provider = registry.getProvider(uri);
                    if (provider.some()) {
                        Resource res = resourceSet.getResource(provider.get(), true);
                        viewpoints.add(Iterables.find(registry.getSiriusResourceHandler().collectViewpointDefinitions(res), new Predicate<Viewpoint>() {
                            @Override
                            public boolean apply(Viewpoint input) {
                                Option<URI> inputURI = new ViewpointQuery(input).getViewpointURI();
                                return inputURI.some() && inputURI.get().equals(uri);
                            }
                        }));
                    }
                }
                return viewpoints;
            }
        });
    }

    private ToolBarManager getToolBarManager() {
        return currentViewerPane.getToolBarManager();
    }

    private ValidateAction getValidateAction(ToolBarManager tbm) {
        IContributionItem[] contributions = tbm.getItems();
        for (IContributionItem contribution : contributions) {
            if (contribution instanceof ActionContributionItem && contribution.getId().equals(ValidateAction.ID)) {
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

        Diagnostic diagnostic = analyzeResourceProblems(resource, exception);
        if (diagnostic.getSeverity() != Diagnostic.OK) {
            resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
        }
        editingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);

        if (Movida.isEnabled()) {
            loader.protect(resource.getURI());
            tracker = new VSMRequirementChangeAdapter(resource, loader);
            tracker.install();
        }
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
        } else {
            result = (URI) input.getAdapter(URI.class);
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

        if (Movida.isEnabled()) {
            loader.dispose();
            tracker.uninstall();
        }
    }

    /**
     * {@inheritDoc}.
     * 
     * Overridden to add our own vsmURIHandler in save options. it's about
     * cascading odesign references and to save using logical URIs.
     */
    @Override
    public void doSave(IProgressMonitor progressMonitor) {
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            @Override
            public void execute(IProgressMonitor monitor) {
                boolean first = true;
                for (Resource resource : editingDomain.getResourceSet().getResources()) {
                    if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !editingDomain.isReadOnly(resource)) {
                        if (resource.isTrackingModification() && !resource.isModified()) {
                            continue;
                        }
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
