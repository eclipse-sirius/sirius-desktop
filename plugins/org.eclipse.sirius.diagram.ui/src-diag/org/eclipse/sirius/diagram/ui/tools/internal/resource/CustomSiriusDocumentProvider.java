/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.resource.ResourceLoaderListener;
import org.eclipse.sirius.common.tools.api.resource.ResourceTools;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.part.Messages;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * .
 * 
 * @author cbrun
 */
public class CustomSiriusDocumentProvider extends AbstractDocumentProvider implements IDiagramDocumentProvider {

    private TransactionalEditingDomain transactionalEditingDomain;

    /**
     * Default constructor.
     */
    public CustomSiriusDocumentProvider() {
        super();
    }

    /**
     * Default constructor.
     * 
     * @param transactionalEditingDomain
     *            the {@link TransactionalEditingDomain}
     */
    public CustomSiriusDocumentProvider(TransactionalEditingDomain transactionalEditingDomain) {
        super();
        this.transactionalEditingDomain = transactionalEditingDomain;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider#createDocument(java.lang.Object)
     */
    @Override
    protected IDocument createDocument(final Object element) throws CoreException {
        if (!(element instanceof FileEditorInput) && !(element instanceof URIEditorInput)) {
            throw new CoreException(new Status(IStatus.ERROR, DiagramPlugin.ID, 0, NLS.bind(Messages.SiriusDocumentProvider_IncorrectInputError, new Object[] { element,
                    "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput", }), //$NON-NLS-1$ //$NON-NLS-2$ 
                    null));
        }
        final IDocument document = createEmptyDocument();
        setDocumentContent(document, (IEditorInput) element);
        setupDocument(element, document);
        return document;
    }

    /**
     * .
     * 
     * @param document
     *            .
     * @param element
     *            .
     * @throws CoreException .
     */
    protected void setDocumentContent(final IDocument document, final IEditorInput element) throws CoreException {
        final IDiagramDocument diagramDocument = (IDiagramDocument) document;
        final TransactionalEditingDomain domain = diagramDocument.getEditingDomain();
        IPermissionAuthority authority = null;
        if (domain != null) {
            authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(domain.getResourceSet());
        }
        if (authority != null) {
            authority.setListening(false);
        }
        try {
            doSetDocumentContent(document, element, domain);
        } finally {
            if (authority != null) {
                authority.setListening(true);
            }
        }
    }

    private void doSetDocumentContent(final IDocument document, final IEditorInput element, final TransactionalEditingDomain domain) throws CoreException {
        IPermissionAuthority authority = null;

        if (domain != null) {
            authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(domain.getResourceSet());
            authority.setReportIssues(false);
            try {
                if (element instanceof FileEditorInput) {
                    caseFileEditorInput(document, (FileEditorInput) element, domain);
                } else if (element instanceof URIEditorInput) {
                    caseURIEditorInput(document, (URIEditorInput) element, domain);
                } else {
                    throw new CoreException(new Status(IStatus.ERROR, DiagramPlugin.ID, 0, NLS.bind(Messages.SiriusDocumentProvider_IncorrectInputError, new Object[] { element,
                            "org.eclipse.ui.part.FileEditorInput", "org.eclipse.emf.common.ui.URIEditorInput", }), //$NON-NLS-1$ //$NON-NLS-2$ 
                            null));
                }
            } finally {
                authority.setReportIssues(true);
            }
        }
    }

    private void caseFileEditorInput(final IDocument document, final FileEditorInput fileEditorInput, final TransactionalEditingDomain domain) throws CoreException {
        final IStorage storage = fileEditorInput.getStorage();
        final ResourceLoaderListener listener = ResourceTools.attachResourceLoaderListener(domain.getResourceSet());
        final Diagram diagram = DiagramIOUtil.load(domain, storage, true, getProgressMonitor());
        Iterator<Resource> it = listener.getLoadedResources().iterator();
        while (it.hasNext()) {
            final Resource res = it.next();
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.RESOLVE_ALL_KEY);
            ModelUtils.resolveAll(res);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        }
        ResourceTools.detachResourceLoaderListener(domain.getResourceSet(), listener);
        /*
         * Init the stuffs so that the resource is ready and up.
         */
        document.setContent(diagram);
    }

    private void caseURIEditorInput(final IDocument document, final URIEditorInput element, final TransactionalEditingDomain domain) throws CoreException {
        final URI uri = element.getURI();
        Resource resource = null;
        try {
            resource = domain.getResourceSet().getResource(uri.trimFragment(), false);
            if (resource == null) {
                resource = domain.getResourceSet().createResource(uri.trimFragment());
            }
            if (!resource.isLoaded()) {
                try {
                    @SuppressWarnings("unchecked")
                    final Map<?, ?> options = new HashMap<Object, Object>(GMFResourceFactory.getDefaultLoadOptions());
                    // @see 171060
                    // options.put(org.eclipse.emf.ecore.xmi.XMLResource.
                    // OPTION_RECORD_UNKNOWN_FEATURE,
                    // Boolean.TRUE);
                    resource.load(options);
                } catch (final IOException e) {
                    resource.unload();
                    throw e;
                }
            }
            if (uri.fragment() != null) {
                final EObject rootElement = resource.getEObject(uri.fragment());
                if (rootElement instanceof Diagram) {
                    // loads the odesign in the resource set
                    final EObject viewpointDiagram = ViewUtil.resolveSemanticElement((Diagram) rootElement);
                    if (viewpointDiagram instanceof DRepresentation) {
                        DialectManager.INSTANCE.getDescription((DRepresentation) viewpointDiagram);
                    }

                    final ModelAccessor dummy = new ModelAccessor();
                    dummy.init(((Diagram) rootElement).eResource().getResourceSet());
                    document.setContent(rootElement);
                    /*
                     * Init the extension stuffs
                     */
                } else {
                    DiagramUIPlugin.getPlugin().getResourceMissingDocumentProvider().setDocumentContent(document, element);
                }
                return;
            } else {
                for (final EObject rootElement : resource.getContents()) {
                    if (rootElement instanceof Diagram) {
                        document.setContent(rootElement);
                        /*
                         * Init the extension stuffs
                         */
                        final ModelAccessor dummy = new ModelAccessor();
                        dummy.init(((Diagram) rootElement).eResource().getResourceSet());
                        return;
                    }
                }
                DiagramUIPlugin.getPlugin().getResourceMissingDocumentProvider()
                        .setDocumentContent(document, element, "You should use the Model Explorer view and the Design perspective to open aird files.");
            }
            // We do not throw a RuntimeException anymore if there is no
            // diagram. We open an empty diagram.
            // throw new
            // RuntimeException(Messages.SiriusDocumentProvider_NoDiagramInResourceError);
            // CHECKSTYLE:OFF
        } catch (final Exception e) {
            CoreException thrownExcp = null;
            if (e instanceof CoreException) {
                thrownExcp = (CoreException) e;
            } else {
                final String msg = e.getLocalizedMessage();
                thrownExcp = new CoreException(new Status(IStatus.ERROR, DiagramPlugin.ID, 0, msg != null ? msg : Messages.SiriusDocumentProvider_DiagramLoadingError, e));
            }
            throw thrownExcp;
        }
        // CHECKSTYLE:ON
    }

    /**
     * Sets up the given document as it would be provided for the given element.
     * The content of the document is not changed. This default implementation
     * is empty. Subclasses may reimplement.
     * 
     * @param element
     *            the blue-print element
     * @param document
     *            the document to set up
     */
    protected void setupDocument(final Object element, final IDocument document) {

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider#createEmptyDocument()
     */
    @Override
    protected IDocument createEmptyDocument() {
        final DiagramDocument document = new DiagramDocument();
        document.setEditingDomain(transactionalEditingDomain);
        return document;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider#doSaveDocument(org.eclipse.core.runtime.IProgressMonitor,
     *      java.lang.Object,
     *      org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument,
     *      boolean)
     */
    @Override
    protected void doSaveDocument(final IProgressMonitor monitor, final Object element, final IDocument document, final boolean overwrite) throws CoreException {
        if (element instanceof SessionEditorInput) {
            fireElementStateChanging(element);
            ((SessionEditorInput) element).getSession().save(monitor);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider#getOperationRunner(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IRunnableContext getOperationRunner(final IProgressMonitor monitor) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocumentProvider#createInputWithEditingDomain(org.eclipse.ui.IEditorInput,
     *      org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    @Override
    public IEditorInput createInputWithEditingDomain(final IEditorInput editorInput, final TransactionalEditingDomain domain) {
        return editorInput;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocumentProvider#getDiagramDocument(java.lang.Object)
     */
    @Override
    public IDiagramDocument getDiagramDocument(final Object element) {
        final IDocument doc = getDocument(element);
        if (doc instanceof IDiagramDocument) {
            return (IDiagramDocument) doc;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider#isReadOnly(java.lang.Object)
     */
    @Override
    public boolean isReadOnly(final Object element) {

        // TODO
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider#isModifiable(java.lang.Object)
     */
    @Override
    public boolean isModifiable(final Object element) {
        boolean isModifiable = true;
        if (element instanceof SessionEditorInput) {
            SessionEditorInput sessionEditorInput = (SessionEditorInput) element;
            Session session = sessionEditorInput.getSession(false);
            if (session != null) {
                ResourceSet resourceSet = session.getTransactionalEditingDomain().getResourceSet();
                URI inputURI = sessionEditorInput.getURI();
                URI resourceURI = inputURI.trimFragment();
                Resource resource = resourceSet.getResource(resourceURI, false);
                if (resource == null) {
                    DiagramPlugin.getDefault().logError("No resource in resourceSet corresponding to " + resourceURI.toString());
                    return isModifiable;
                }
                String fragment = inputURI.fragment();
                if (fragment != null) {
                    EObject intputEObject = resource.getEObject(fragment);
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resourceSet);
                    isModifiable = intputEObject != null && permissionAuthority.canEditInstance(intputEObject);
                    if (isModifiable && intputEObject instanceof Diagram) {
                        Diagram diagram = (Diagram) intputEObject;
                        EObject diagramElt = diagram.getElement();
                        isModifiable = permissionAuthority.canEditInstance(diagramElt);
                    }
                }
            }
        }
        return isModifiable;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider#canSaveDocument(java.lang.Object)
     */
    @Override
    public boolean canSaveDocument(final Object element) {
        if (element instanceof SessionEditorInput) {
            Session session = ((SessionEditorInput) element).getSession(false);
            return session != null && session.isOpen() && session.getStatus() == SessionStatus.DIRTY;
        }
        return super.canSaveDocument(element);
    }

}
