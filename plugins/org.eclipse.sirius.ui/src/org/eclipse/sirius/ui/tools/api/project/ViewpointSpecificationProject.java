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
package org.eclipse.sirius.ui.tools.api.project;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.lang.model.SourceVersion;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.tools.internal.assist.ProposalProviderRegistry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

/**
 * Manage viewpoint specification projects.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @since 0.9.0
 */
public final class ViewpointSpecificationProject {

    /** The extension use for viewpoint model. */
    public static final String VIEWPOINT_MODEL_EXTENSION = SiriusEditPlugin.getPlugin().getString("_UI_SiriusEditorFilenameExtension");

    /**
     * The default initial object name for a odesign.
     */
    public static final String INITIAL_OBJECT_NAME = "Group";

    /**
     * The default encoding.
     */
    public static final String ENCODING_DEFAULT = "UTF-8"; //$NON-NLS-1$

    /**
     * The line separator to use on the running platform.
     */
    private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$

    /**
     * This caches an instance of the model package.
     */
    private static final DescriptionPackage DESCRIPTION_PACKAGE = DescriptionPackage.eINSTANCE;

    /**
     * This caches an instance of the model factory.
     */
    private static final DescriptionFactory DESCRIPTION_FACTORY = DESCRIPTION_PACKAGE.getDescriptionFactory();

    /**
     * This package name is used in case of the project name is invalid to a
     * package name.
     */
    private static final String DEFAULT_PACKAGE_NAME = "defaultpackage"; //$NON-NLS-1$

    private static final String UNAUTHORIZED_CHARACTER = "[^a-zA-Z0-9_\\.-]"; //$NON-NLS-1$

    /**
     * Default constructor.
     */
    private ViewpointSpecificationProject() {

    }

    /**
     * Create a new viewpoint specification project.
     * 
     * @param projectName
     *            The project name
     * @param modelName
     *            The model name
     * @return The new project.
     * @throws CoreException .
     */
    public static IProject createNewViewpointSpecificationProject(final String projectName, final String modelName) throws CoreException {
        final IPath projectLocationPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();

        final Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);

        return ViewpointSpecificationProject.createNewViewpointSpecificationProject(PlatformUI.getWorkbench(), projectName, projectLocationPath, modelName, INITIAL_OBJECT_NAME, ENCODING_DEFAULT,
                monitorDialog);
    }

    /**
     * Create a new viewpoint specification project.
     * 
     * @param workbench
     *            The workbench use to create this project
     * @param projectName
     *            The project name
     * @param projectLocationPath
     *            The project location path
     * @param modelName
     *            The model name
     * @param modelInitialObjectName
     *            The model initial object name
     * @param encoding
     *            The encoding of the model
     * @param runnable
     *            The runnable to launch the operation
     * @return The new project.
     * @throws CoreException .
     */
    public static IProject createNewViewpointSpecificationProject(final IWorkbench workbench, final String projectName, final IPath projectLocationPath, final String modelName,
            final String modelInitialObjectName, final String encoding, final IRunnableContext runnable) throws CoreException {
        final IWorkspaceRunnable create = new IWorkspaceRunnable() {
            public void run(final IProgressMonitor monitor) throws CoreException {
                final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                if (!project.exists()) {
                    final IProjectDescription desc = project.getWorkspace().newProjectDescription(projectName);
                    IPath projectLocationPathTemp = projectLocationPath;
                    if (projectLocationPath != null && ResourcesPlugin.getWorkspace().getRoot().getLocation().equals(projectLocationPath)) {
                        projectLocationPathTemp = null;
                    }
                    desc.setLocation(projectLocationPathTemp);
                    project.create(desc, monitor);
                    project.open(monitor);

                    ViewpointSpecificationProject.createFolder(project, monitor, "src"); //$NON-NLS-1$
                    ViewpointSpecificationProject.createFolder(project, monitor, "description"); //$NON-NLS-1$

                    IFile modelFile;
                    try {
                        modelFile = ViewpointSpecificationProject.createODesignFile(project, modelName, modelInitialObjectName, encoding, runnable);
                    } catch (final IOException e) {
                        throw new RuntimeException(e);
                    } catch (final InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (final InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    ViewpointSpecificationProject.selectAndOpen(workbench, modelFile);
                    ViewpointSpecificationProject.convert(project, modelName, monitor);
                }
                if (!project.isOpen()) {
                    project.open(monitor);
                }
            }
        };
        ResourcesPlugin.getWorkspace().run(create, null);
        return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);

    }

    /**
     * Create a new folder in a project.
     * 
     * @param prj
     *            A project
     * @param monitor
     *            The progress monitor
     * @param folderName
     *            The folder name
     * @throws CoreException
     *             if this method fails
     */
    protected static void createFolder(final IProject prj, final IProgressMonitor monitor, final String folderName) throws CoreException {
        final IFolder folder = prj.getFolder(new Path(folderName));
        if (!folder.exists()) {
            folder.create(true, true, monitor);
        }
    }

    /**
     * Create a new odesign file.
     * 
     * @param prj
     *            The project in which create the odesign
     * @param modelName
     *            The model name
     * @param modelInitialObjectName
     *            The model initial object name
     * @param encoding
     *            The encoding of the model
     * @param runnable
     *            The runnable to launch the operation
     * @return the new odesign file.
     * @throws IOException .
     * @throws InvocationTargetException .
     * @throws InterruptedException .
     */
    protected static IFile createODesignFile(final IProject prj, final String modelName, final String modelInitialObjectName, final String encoding, final IRunnableContext runnable)
            throws IOException, InvocationTargetException, InterruptedException {
        final IFile modelFile = ViewpointSpecificationProject.getModelFile(prj, modelName);
        final WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            @Override
            protected void execute(final IProgressMonitor progressMonitor) {
                try {
                    final ResourceSet resourceSet = new ResourceSetImpl();
                    final URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
                    final Resource resource = resourceSet.createResource(fileURI);
                    final EObject rootObject = ViewpointSpecificationProject.createInitialModel(modelInitialObjectName);
                    if (rootObject != null) {
                        if (rootObject instanceof Group) {
                            ((Group) rootObject).setName(modelName.replaceAll("." + VIEWPOINT_MODEL_EXTENSION, "")); //$NON-NLS-1$ //$NON-NLS-2$
                        }
                        resource.getContents().add(rootObject);
                    }

                    // Save the contents of the resource to the file system.
                    //
                    final Map<String, String> options = new HashMap<String, String>();
                    options.put(XMLResource.OPTION_ENCODING, encoding);
                    resource.save(options);
                } catch (final IOException ioe) {
                    final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, ioe.getMessage(), ioe);
                    SiriusEditPlugin.getPlugin().getLog().log(status);
                } finally {
                    progressMonitor.done();
                }
            }
        };

        try {
            runnable.run(false, false, operation);
        } catch (final InvocationTargetException exception) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, exception.getMessage(), exception);
            SiriusEditPlugin.getPlugin().getLog().log(status);
        } catch (final InterruptedException exception) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, exception.getMessage(), exception);
            SiriusEditPlugin.getPlugin().getLog().log(status);
        }

        return modelFile;
    }

    private static void selectAndOpen(final IWorkbench workbench, final IFile modelFile) {
        final IWorkbenchPage activePage = workbench.getActiveWorkbenchWindow().getActivePage();
        final IWorkbenchPart activePart = activePage.getActivePart();
        if (activePart instanceof ISetSelectionTarget) {
            final ISelection targetSelection = new StructuredSelection(modelFile);
            ViewpointSpecificationProject.getShell().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    ((ISetSelectionTarget) activePart).selectReveal(targetSelection);
                }
            });
        }

        try {
            activePage.openEditor(new FileEditorInput(modelFile), workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
        } catch (final PartInitException exception) {
            MessageDialog.openError(ViewpointSpecificationProject.getShell(), SiriusEditPlugin.getPlugin().getString("_UI_OpenEditorError_label"), exception.getMessage());
        }
    }

    /**
     * Returns the current's shell.
     * 
     * @return Shell
     */
    private static Shell getShell() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    /**
     * Converts the given project to Designer project.
     * 
     * @param prj
     *            is the project to convert
     * @param modelName
     *            The model name
     * @param monitor
     *            is the monitor
     */
    private static void convert(final IProject prj, final String modelName, final IProgressMonitor monitor) {
        final String modelNameWithoutExtension = modelName.replaceAll("\\." + VIEWPOINT_MODEL_EXTENSION + "$", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        // WARNING: variable names should not share any common prefix.
        // applyReplacements() does not deal with this case.
        final Map<String, String> replacements = new HashMap<String, String>();
        final String projectName = prj.getName().replaceAll(UNAUTHORIZED_CHARACTER, "_"); //$NON-NLS-1$

        final String packageName;
        if (SourceVersion.isName(prj.getName())) {
            packageName = prj.getName();
        } else if (SourceVersion.isName(projectName)) {
            packageName = projectName;
        } else {
            packageName = DEFAULT_PACKAGE_NAME;
        }

        replacements.put("pluginId", projectName); //$NON-NLS-1$
        replacements.put("projectName", projectName); //$NON-NLS-1$
        replacements.put("modelName", modelNameWithoutExtension); //$NON-NLS-1$
        replacements.put("packageName", packageName); //$NON-NLS-1$

        ViewpointSpecificationProject.createFileFromTemplate(prj, "build.properties", "resources/build.properties", replacements, monitor); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
        ViewpointSpecificationProject.createFileFromTemplate(prj, "src/" + packageName.replaceAll("\\.", "/") + "/Activator.java", "resources/Activator.java_", replacements, monitor); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        ViewpointSpecificationProject.createFileFromTemplate(prj, ".classpath", "resources/classpath.xml", replacements, monitor); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
        ViewpointSpecificationProject.createFileFromTemplate(prj, "META-INF/MANIFEST.MF", "resources/MANIFEST.MF", replacements, monitor); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
        ViewpointSpecificationProject.createFileFromTemplate(prj, ".project", "resources/project.xml", replacements, monitor); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$
        ViewpointSpecificationProject.createFileFromTemplate(prj, "plugin.xml", "resources/plugin.xml", replacements, monitor); //$NON-NLS-1$ //$NON-NLS-2$ $NON-NLS-2$

        addAcceleoNature(prj);
    }

    private static void addAcceleoNature(IProject projet) {
        ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
        IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
        Command addAcceleoNatureCommand = commandService.getCommand("org.eclipse.sirius.common.acceleo.mtl.ide.internal.convert"); //$NON-NLS-1$

        // If the acceleo interpreter is not present, do not configure.
        // the acceleo conversion command is not API yet, so, it
        // is declared by the org.eclipse.sirius.common.acceleo.mtl.ide plugin,
        // to
        // avoid dependencies from viewpoint.ui to Acceleo.
        if (addAcceleoNatureCommand != null && addAcceleoNatureCommand.isDefined()) {
            // Force org.eclipse.sirius.common.acceleo.mtl.ide plugin
            // inialization.
            ProposalProviderRegistry.getAllProviders();
            ParameterizedCommand parmCommand = new ParameterizedCommand(addAcceleoNatureCommand, null);
            try {
                EvaluationContext evaluationContext = new EvaluationContext(null, Collections.singletonList(projet));
                handlerService.executeCommandInContext(parmCommand, null, evaluationContext);
            } catch (CommandException e) {
                final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, e.getMessage(), e);
                SiriusEditPlugin.getPlugin().getLog().log(status);
            }
        }
    }

    /**
     * Creates a new file in the workspace from a template file inside the
     * bundle, applying the specified variable replacements to the template's
     * contents.
     * 
     * @param prj
     *            the project in which to create the new file. Must be
     *            accessible.
     * @param newFilePath
     *            the path of the file to create, relative to the
     *            <code>project</code>'s root. If the file already exists its
     *            content is replaced.
     * @param templatePath
     *            the path of the template file, relative to this bundle's root.
     * @param replacements
     *            the values of the variables to replace in the template.
     * @param monitor
     *            the task's {@link IProgressMonitor}
     */
    private static void createFileFromTemplate(final IProject prj, final String newFilePath, final String templatePath, final Map<String, String> replacements, final IProgressMonitor monitor) {
        try {
            final String templateContent = ViewpointSpecificationProject.getTemplateFileContents(templatePath);
            final String finalContent = ViewpointSpecificationProject.applyReplacements(templateContent, replacements);
            ViewpointSpecificationProject.createFile(prj, new Path(newFilePath), finalContent, monitor);
        } catch (final IOException e) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, e.getMessage(), e);
            SiriusEditPlugin.getPlugin().getLog().log(status);
        }
    }

    /**
     * Returns the raw contents of a template file stored inside this bundle.
     * The file must be a text file. It is read using the JVM's default
     * encoding. Line terminators are adapted to the default on the running
     * platform.
     * 
     * @param path
     *            the bundle-relative path of the file to read.
     * @return the text content of the specified template file.
     * @throws IOException
     *             if the specified file does not exist or could not be read as
     *             a text file.
     */
    private static String getTemplateFileContents(final String path) throws IOException {
        final InputStream is = FileLocator.openStream(SiriusEditPlugin.getPlugin().getBundle(), new Path(path), false);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(NL);
            }
        } finally {
            is.close();
        }
        return sb.toString();
    }

    /**
     * Expands variables inside a template text specified as
     * <code>"%varName"</code>to their values specified in
     * <code>replacements</code>.
     * <p>
     * This replacement is very basic: the result is unspecified if variables
     * values contains "%varNames" or if variable names share a common prefix.
     * 
     * @param text
     * @param replacements
     * @return
     */
    private static String applyReplacements(final String text, final Map<String, String> replacements) {
        String result = text;
        for (Entry<String, String> replacement : replacements.entrySet()) {
            result = result.replaceAll("%" + replacement.getKey(), replacement.getValue()); //$NON-NLS-1$
        }
        return result;
    }

    /**
     * Creates a file and its content.
     * 
     * @param prj
     *            is the project
     * @param projectRelativePath
     *            is the path of the file to create, relative to the project
     * @param content
     *            is the content of the new file
     * @param monitor
     *            is the monitor
     */
    private static void createFile(final IProject prj, final IPath projectRelativePath, final String content, final IProgressMonitor monitor) {
        try {
            final IContainer container = ViewpointSpecificationProject.createContainer(prj, projectRelativePath, monitor);
            IFile file = container.getFile(new Path(projectRelativePath.lastSegment()));
            if (!file.exists() && file.getParent().exists()) {
                final IResource[] members = file.getParent().members(IResource.FILE);
                for (final IResource element : members) {
                    if (element instanceof IFile && file.getName().toLowerCase().equals(element.getName().toLowerCase())) {
                        file = (IFile) element;
                        break;
                    }
                }
            }
            final ByteArrayInputStream javaStream = new ByteArrayInputStream(content.getBytes("UTF8")); //$NON-NLS-1$
            if (!file.exists()) {
                file.create(javaStream, true, monitor);
            } else {
                file.setContents(javaStream, true, false, monitor);
            }
        } catch (final CoreException e) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, e.getMessage(), e);
            SiriusEditPlugin.getPlugin().getLog().log(status);
        } catch (final UnsupportedEncodingException e) {
            final IStatus status = new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, e.getMessage(), e);
            SiriusEditPlugin.getPlugin().getLog().log(status);
        }
    }

    private static IContainer createContainer(final IProject prj, final IPath projectRelativePath, final IProgressMonitor monitor) throws CoreException {
        IContainer container = prj;
        final String[] folders = projectRelativePath.removeLastSegments(1).segments();
        for (final String element : folders) {
            container = container.getFolder(new Path(element));
            if (!container.exists()) {
                ((IFolder) container).create(true, true, monitor);
            }
        }
        return container;
    }

    private static IFile getModelFile(final IProject project, final String modelName) {
        return ResourcesPlugin.getWorkspace().getRoot().getFile(project.getFullPath().append("description/" + modelName)); //$NON-NLS-1$
    }

    /**
     * Create a new model.
     * 
     * @param modelInitialObjectName
     *            The name's root object of the model
     * @return the new model.
     */
    private static EObject createInitialModel(final String modelInitialObjectName) {
        final EClass eClass = (EClass) DESCRIPTION_PACKAGE.getEClassifier(modelInitialObjectName);
        final EObject rootObject = DESCRIPTION_FACTORY.create(eClass);
        return rootObject;
    }
}
