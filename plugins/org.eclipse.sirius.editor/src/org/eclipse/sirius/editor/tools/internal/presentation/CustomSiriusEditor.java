/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.sirius.business.api.logger.MarkerRuntimeLogger;
import org.eclipse.sirius.business.api.query.SiriusQuery;
import org.eclipse.sirius.business.internal.movida.DynamicVSMLoader;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry;
import org.eclipse.sirius.business.internal.movida.registry.SiriusURIConverter;
import org.eclipse.sirius.business.internal.movida.registry.SiriusURIHandler;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.ui.tools.api.editor.IEObjectNavigable;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.properties.validation.SiriusInterpreterErrorDecorator;
import org.eclipse.sirius.editor.tools.internal.actions.ValidateAction;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEditManager;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * The advanced Sirius Specification Model (*.odesign) Editor, base on the
 * standard EMF-generated editor, but with Sirius-specific customizations.
 */
public class CustomSiriusEditor extends SiriusEditor implements IEObjectNavigable {

    private final RepresentationTemplateUpdateTrigger templateUpdateTrigger = new RepresentationTemplateUpdateTrigger();

    private final SiriusURIHandler vsmURIHandler;

    private CommandStackListener cmdStackListener = new CommandStackListener() {
        public void commandStackChanged(EventObject event) {
            getContainer().getDisplay().asyncExec(new Runnable() {
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
        vsmURIHandler = new SiriusURIHandler(editingDomain.getResourceSet());
        if (Movida.isEnabled()) {
            SiriusRegistry registry = (SiriusRegistry) org.eclipse.sirius.business.api.componentization.SiriusRegistry.getInstance();
            editingDomain.getResourceSet().setURIConverter(new SiriusURIConverter(registry));
            loader = new DynamicVSMLoader(editingDomain.getResourceSet(), registry);
            loader.setErrorHandler(new Runnable() {
                public void run() {
                    getSite().getShell().getDisplay().asyncExec(new Runnable() {
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    public boolean navigateToEObject(final URI uri) {
        final EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
        if (eObject != null) {
            setSelectionToViewer(Collections.singleton(editingDomain.getWrapper(eObject)));
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPages() {
        super.createPages();
        if (selectionViewer != null) {
            selectionViewer.setLabelProvider(new CustomSiriusAdapterFactoryLabelProvider(adapterFactory));
            selectionViewer.addFilter(new ViewerFilter() {
                @Override
                public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                    return !isEnvironmentResource(element) && !isMigrationAnnotation(element);
                }

                private boolean isEnvironmentResource(final Object element) {
                    return (element instanceof Resource && ((Resource) element).getURI() != null && "environment:/viewpoint".equals(((Resource) element).getURI().toString()));
                }

                private boolean isMigrationAnnotation(final Object element) {
                    return (element instanceof DAnnotation && ((DAnnotation) element).eContainer() instanceof Group);
                }
            });
            validationDecorator = new ValidationDecoration();
            if (Movida.isEnabled()) {
                /*
                 * Customize the label provider to add the URI of the
                 * corresponding resource for Siriuss loaded by dependency.
                 */
                decoratingLabelProvider = new GeneratedElementsLabelProvider((ILabelProvider) selectionViewer.getLabelProvider(), validationDecorator) {
                    public String getText(Object element) {
                        String result = super.getText(element);
                        if (element instanceof Viewpoint && ((Viewpoint) element).eResource() != null) {
                            Resource res = ((Viewpoint) element).eResource();
                            if (res.getResourceSet().getResources().indexOf(res) != 0) {
                                result = result + " (" + res.getURI() + ")";
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
                /*
                 * Customize the content provider not to show the whole
                 * resources loaded by dependency, but only the Siriuss (inside
                 * these resources) which the main VSM depend on.
                 */
                final SiriusRegistry registry = (SiriusRegistry) org.eclipse.sirius.business.api.componentization.SiriusRegistry.getInstance();
                selectionViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory) {
                    @Override
                    public Object[] getElements(Object object) {
                        if (object instanceof ResourceSet) {
                            Set<EObject> viewpoints = getRequiredSiriuss(registry, (ResourceSet) object);
                            List<Object> elements = Lists.newArrayList();
                            Resource mainResource = ((ResourceSet) object).getResources().get(0);
                            elements.add(mainResource);
                            for (Viewpoint additionalVP : Iterables.filter(viewpoints, Viewpoint.class)) {
                                if (additionalVP.eResource() != null && additionalVP.eResource() != mainResource) {
                                    elements.add(additionalVP);
                                }
                            }
                            return elements.toArray();
                        } else {
                            return super.getElements(object);
                        }
                    }

                    private Set<EObject> getRequiredSiriuss(final SiriusRegistry registry, ResourceSet resourceSet) {
                        Set<EObject> viewpoints = Sets.newHashSet();
                        for (final URI uri : loader.getRequiredSiriuss()) {
                            Option<URI> provider = registry.getProvider(uri);
                            if (provider.some()) {
                                Resource res = resourceSet.getResource(provider.get(), true);
                                viewpoints.add(Iterables.find(registry.getSiriusResourceHandler().collectSiriusDefinitions(res), new Predicate<Viewpoint>() {
                                    public boolean apply(Viewpoint input) {
                                        Option<URI> inputURI = new SiriusQuery(input).getSiriusURI();
                                        return inputURI.some() && inputURI.get().equals(uri);
                                    }
                                }));
                            }
                        }
                        return viewpoints;
                    }
                });
            }

            selectionViewer.setLabelProvider(decoratingLabelProvider);
            selectionViewer.addDoubleClickListener(new IDoubleClickListener() {

                public void doubleClick(DoubleClickEvent event) {
                    ISelection selection = selectionViewer.getSelection();
                    if (selection instanceof StructuredSelection) {
                        Object selected = ((StructuredSelection) selection).getFirstElement();
                        if (selected instanceof EObject) {
                            EObject selectedEObject = (EObject) selected;
                            if (RepresentationTemplateEditManager.INSTANCE.isGenerated(selectedEObject)) {
                                EObject original = RepresentationTemplateEditManager.INSTANCE.getSourceElement(selectedEObject);
                                if (original != null) {
                                    setSelectionToViewer(Collections.singleton(editingDomain.getWrapper(original)));
                                }
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

    /**
     * {@inheritDoc} Override to handle other editor input.
     * 
     * @see org.eclipse.sirius.editor.editorPlugin.SiriusEditor#createModel()
     */
    @Override
    public void createModel() {
        URI resourceURI = getURIFromInput(getEditorInput());
        Exception exception = null;
        Resource resource = null;
        try {
            resource = editingDomain.getResourceSet().getResource(resourceURI, true);
        } catch (Exception e) {
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
        if (input == null) {
            return null;
        }
        if (input instanceof IFileEditorInput) {
            return URI.createPlatformResourceURI(((IFileEditorInput) getEditorInput()).getFile().getFullPath().toString(), false);
        }
        if (input instanceof URIEditorInput) {
            return ((URIEditorInput) input).getURI();
        }
        if (input instanceof IPathEditorInput) {
            return URI.createFileURI(((IPathEditorInput) input).getPath().toOSString());
        }
        URI uri = (URI) input.getAdapter(URI.class);
        if (uri != null) {
            return uri;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();

        editingDomain.getCommandStack().removeCommandStackListener(cmdStackListener);

        editingDomain.getResourceSet().eAdapters().remove(templateUpdateTrigger);
        decoratingLabelProvider.dispose();

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
                        } catch (Exception exception) {
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
        } catch (Exception exception) {
            SiriusEditorPlugin.INSTANCE.log(exception);
        }
        updateProblemIndication = true;
        updateProblemIndication();
    }
}
