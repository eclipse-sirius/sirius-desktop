/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.design.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.importer.ecore.EcoreImporter;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.viewpoint.SiriusSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeSiriusSelectionCommand;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.ecore.design.service.EcoreSamplePlugin;

/**
 * A {@link WorkspaceModifyOperation} to create a new Ecore Modeling Project
 * from a Empty EMF project.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class EcoreModelingProjectCreationOperation extends WorkspaceModifyOperation {

    private IProject project;

    private EObject rootObject;

    private String ecoreResourceName;

    private String genModelResourceName;

    private String representationsResourceName;

    private Set<Viewpoint> selectedSiriuss;

    private IFile ecoreModel;

    /**
     * Default constructor.
     * 
     * @param project
     *            the empty EMF project
     * @param rootObject
     *            the root object of the semantic resource (i.e. the metamodel)
     * @param representationsResourceName
     *            the name of the representations resource
     * @param selectedSiriuss
     *            the set of {@link Viewpoint} to have selected on this created
     *            Modeling Project
     */
    public EcoreModelingProjectCreationOperation(IProject project, EObject rootObject, String ecoreResourceName, String genModelResourceName, String representationsResourceName,
            Set<Viewpoint> selectedSiriuss) {
        super();
        this.project = project;
        this.rootObject = rootObject;
        this.ecoreResourceName = ecoreResourceName;
        this.genModelResourceName = genModelResourceName;
        this.representationsResourceName = representationsResourceName;
        this.selectedSiriuss = selectedSiriuss;
    }

    @Override
    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        try {
            monitor.beginTask("Create modeling resources: ", 100); //$NON-NLS-1$ 
            /*
             * Create modeling resources: ecore, genmodel and aird : 50
             */
            ecoreModel = createModelingResources(project, monitor);

            monitor.subTask("prepare the modeling project..."); //$NON-NLS-1$ 
            convertToModelingProject(new SubProgressMonitor(monitor, 20));
        } finally {
            monitor.done();
        }
    }

    /**
     * .
     * 
     * @param project
     * @return the semantic ecore model.
     */
    private IFile createModelingResources(IProject project, IProgressMonitor monitor) {

        /* create the ecore file */
        monitor.subTask("create the ecore model."); //$NON-NLS-1$ 
        String modelPath = '/' + project.getName() + "/model/"; //$NON-NLS-1$ 
        String ecorePath = createEcoreResource(modelPath);
        IFile ecoreFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(ecorePath));
        monitor.worked(10);

        /* create the genmodel file */
        monitor.subTask("create the genmodel..."); //$NON-NLS-1$ 
        createGenModel(ecoreFile, modelPath, new SubProgressMonitor(monitor, 15));

        /*
         * Create the aird file and create a Session from the session model URI
         */
        monitor.subTask("create the representation model..."); //$NON-NLS-1$ 
        final Session session = createAird(project, URI.createPlatformResourceURI(modelPath + representationsResourceName, true), monitor);
        monitor.worked(15);

        if (session == null) {
            return null;
        } else {
            /* prepare session ressource set for ecore models */
            session.getTransactionalEditingDomain().getResourceSet().getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
        }

        monitor.subTask("prepare ecore modeling project..."); //$NON-NLS-1$
        CompoundCommand cc = new CompoundCommand("Prepare Ecore Modeling Project"); //$NON-NLS-1$ 
        cc.append(new AddSemanticResourceCommand(session, URI.createPlatformResourceURI(ecorePath, true), new SubProgressMonitor(monitor, 1)));
        cc.append(new ChangeSiriusSelectionCommand(session, new SiriusSelectionCallback(), selectedSiriuss, Collections.<Viewpoint> emptySet(), new SubProgressMonitor(monitor, 1)));

        monitor.subTask("link the created models..."); //$NON-NLS-1$ 
        session.getTransactionalEditingDomain().getCommandStack().execute(cc);
        monitor.worked(10);

        session.save(monitor);
        monitor.worked(15);

        return ecoreFile;
    }

    private void convertToModelingProject(IProgressMonitor monitor) {
        /* Convert the created emf project to a modeling project. */
        try {
            ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
        } catch (CoreException e) {
            final IStatus status = new Status(IStatus.ERROR, EcoreSamplePlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e);
            EcoreSamplePlugin.getDefault().getLog().log(status);
        }
    }

    private Session createAird(IProject project, URI representationsURI, IProgressMonitor monitor) {
        final Session session;
        Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
        if (modelingProject.some()) {
            session = modelingProject.get().getSession();
        } else {
            Session tempSession = null;
            SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(representationsURI, monitor);
            try {
                sessionCreationOperation.execute();
                tempSession = sessionCreationOperation.getCreatedSession();
            } catch (CoreException e) {
                IStatus status = new Status(IStatus.ERROR, EcoreSamplePlugin.PLUGIN_ID, IStatus.ERROR, e.getMessage(), e);
                EcoreSamplePlugin.getDefault().getLog().log(status);
            }
            if (tempSession != null) {
                session = tempSession;
            } else {
                session = null;
            }
        }
        return session;
    }

    private void createGenModel(IFile ecoreModel, String modelPath, IProgressMonitor progressMonitor) {
        final EcoreImporter genModelCreator = new EcoreImporter();

        genModelCreator.setGenModelContainerPath(new Path(modelPath));
        genModelCreator.setGenModelFileName(genModelResourceName);
        genModelCreator.setModelFile(ecoreModel);
        genModelCreator.getFileExtensions().clear();
        genModelCreator.getFileExtensions().add(ecoreModel.getFileExtension());

        Monitor monitor = BasicMonitor.toMonitor(progressMonitor);
        try {
            genModelCreator.computeEPackages(monitor);
            genModelCreator.adjustEPackages(monitor);
            genModelCreator.prepareGenModelAndEPackages(monitor);
            genModelCreator.saveGenModelAndEPackages(monitor);
        } catch (Exception exception) {
            IStatus status = new Status(IStatus.ERROR, EcoreSamplePlugin.PLUGIN_ID, IStatus.ERROR, "Error occurs during generating the genmodel file.", exception);//$NON-NLS-1$
            EcoreSamplePlugin.getDefault().getLog().log(status);
        } finally {
            monitor.done();
        }

    }

    private String createEcoreResource(String modelPath) {
        /*
         * Create a resource for this file. Don't specify acontent type, as it
         * could be Ecore or EMOF.Create in a other resourceset and let the
         * workspace monitor for modeling project add it as semantic resource.
         */
        final ResourceSet rs = new ResourceSetImpl();
        rs.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
        String platformPath = modelPath + ecoreResourceName;
        final URI semanticModelURI = URI.createPlatformResourceURI(platformPath, true);
        final Resource resource = rs.createResource(semanticModelURI);

        /* Add the initial model object to the contents. */
        if (rootObject != null) {
            resource.getContents().add(rootObject);
        }

        try {
            Map<Object, Object> options = new HashMap<Object, Object>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
            resource.save(options);
        } catch (IOException e) {
            /* do nothing it should always work */
        }
        return platformPath;
    }

    public IFile getEcoreModel() {
        return ecoreModel;
    }
}
