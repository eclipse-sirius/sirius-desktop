/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.support.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.business.internal.migration.AbstractVersionSAXParser;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMVersionSAXParser;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.CommandStackUtil;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.support.internal.helper.CrossReferenceAdapterDetector;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection.Callback;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.junit.Assert;
import org.osgi.framework.Version;

import junit.framework.TestCase;

/**
 * The main test case for viewpoint unit tests.
 * 
 * @author mchauvin
 */
public abstract class SiriusTestCase extends TestCase {
    /** Type of the URI. */
    public enum ResourceURIType {
        /** URI of type platform. */
        RESOURCE_PLATFORM_URI,
        /** URI of type plugin. */
        RESOURCE_PLUGIN_URI
    }

    /** Initialization error message. */
    public static final String INIT_ERROR_MSG = "An error occurs during tests initialization";

    /** name of the project created in the test workspace. */
    protected static final String TEMPORARY_PROJECT_NAME = "DesignerTestProject";

    /** The default session URI used when there is no session path passed to the generic setup. */
    protected static final URI DEFAULT_MODELING_PROJECT_REPRESENTATIONS_FILE_URI = URI
            .createPlatformResourceURI(File.separator + SiriusTestCase.TEMPORARY_PROJECT_NAME + File.separator + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME, true);

    private static final String DOT = ".";

    /** The local session. */
    protected Session session;

    /** The model request interpreter. */
    protected IInterpreter interpreter;

    /** The model accessor. */
    protected ModelAccessor accessor;

    /** Semantic model. */
    protected EObject semanticModel;

    /** Indicates if the workspace project created during the setup should be a modeling one. */
    protected boolean createModelingProject;

    /** Registered viewpoints. */
    protected final Set<Viewpoint> viewpoints = new LinkedHashSet<Viewpoint>();

    /** The viewpoint selection callback to use. */
    protected Callback selectionCallback = new ViewpointSelectionCallback();

    /** The reported errors. */
    protected final Map<String, List<IStatus>> errors = new LinkedHashMap<>();

    /** The reported warnings. */
    protected final Map<String, List<IStatus>> warnings = new LinkedHashMap<>();

    /** The reported infos. */
    protected final Map<String, List<IStatus>> infos = new LinkedHashMap<>();

    /** A default progress monitor test code can use when one is needed. */
    protected IProgressMonitor defaultProgress = new NullProgressMonitor();

    /** The unchaught exceptions handler. */
    private UncaughtExceptionHandler exceptionHandler;

    /** The platform log listener. */
    private ILogListener logListener;

    /** Boolean to activate error catch. */
    private boolean errorCatchActive;

    /** Boolean to activate warning catch. */
    private boolean warningCatchActive;

    /** Boolean to activate info catch. */
    private boolean infoCatchActive;

    /** HashMaps to store the initial values of preferences before changes. */
    private final HashMap<String, Object> oldValueDiagramPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueDiagramUiPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusUIPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldPlatformUIPreferences = new HashMap<String, Object>();

    /** Overridden to create the project. {@inheritDoc} */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // CHECKSTYLE:OFF
        System.out.println("Setup of " + this.getClass().getName() + SiriusTestCase.DOT + getName() + "()");
        // CHECKSTYLE:ON
        setErrorCatchActive(true);
        setWarningCatchActive(false);
        setInfoCatchActive(false);
        Display.getCurrent().syncExec(() -> {
            IPreferenceStore preferenceStore = DiagramUIPlugin.getPlugin().getPreferenceStore();
            preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, false);
            preferenceStore.setValue(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, false);
        });
        if (createModelingProject) {
            EclipseTestsSupportHelper.INSTANCE.createModelingProject(SiriusTestCase.TEMPORARY_PROJECT_NAME, false);
        } else {
            EclipseTestsSupportHelper.INSTANCE.createProject(SiriusTestCase.TEMPORARY_PROJECT_NAME);
        }
    }

    /**
     * Generic set up to create a session.
     * 
     * @throws Exception
     *             any exception
     */
    protected void genericSetUp() throws Exception {
        genericSetUp(Collections.<String> emptySet(), Collections.<String> emptySet(), null);
    }

    /**
     * Generic set up to create a session.
     * 
     * @param semanticModelPath
     *            the semantic model path
     * @param modelerDescriptionPath
     *            the modeler description path (PlatformPlugin or PlatformResource)
     * @throws Exception
     *             any exception
     */
    protected void genericSetUp(final String semanticModelPath, final String modelerDescriptionPath) throws Exception {
        genericSetUp(semanticModelPath, modelerDescriptionPath, null);
    }

    /**
     * Generic set up to create a session.
     * 
     * @param semanticModelPath
     *            the semantic model path
     * @param modelerDescriptionPaths
     *            the modeler description paths (PlatformPlugin or PlatformResource)
     * @throws Exception
     *             any exception
     */
    protected void genericSetUp(final String semanticModelPath, final Collection<String> modelerDescriptionPaths) throws Exception {
        genericSetUp(Collections.singleton(semanticModelPath), modelerDescriptionPaths, null);
    }

    /**
     * Generic set up to open session.
     * 
     * @param semanticModelPath
     *            the semantic model path
     * @param modelerDescriptionPath
     *            the modeler description path (PlatformPlugin or PlatformResource)
     * @param representationsModelPath
     *            the aird path
     * @throws Exception
     *             any exception
     */
    protected void genericSetUp(final String semanticModelPath, final String modelerDescriptionPath, final String representationsModelPath) throws Exception {
        genericSetUp(Collections.singleton(semanticModelPath), Collections.singleton(modelerDescriptionPath), representationsModelPath);
    }

    /**
     * Generic set up.
     * 
     * @param semanticModelPaths
     *            the semantic model paths
     * @param modelerDescriptionPaths
     *            the modeler description paths (PlatformPlugin or PlatformResource)
     * @param representationsModelPath
     *            the aird path
     * @throws Exception
     *             any exception
     */
    protected void genericSetUp(final Collection<String> semanticModelPaths, final Collection<String> modelerDescriptionPaths, final String representationsModelPath) throws Exception {

        final List<URI> semanticModelUris = new ArrayList<>();
        for (final String semanticModelPath : semanticModelPaths) {
            semanticModelUris.add(toURI(semanticModelPath));
        }

        final List<URI> modelerDescUris = new ArrayList<>();
        for (final String modelerDescriptionPath : modelerDescriptionPaths) {
            modelerDescUris.add(toURI(modelerDescriptionPath));
        }
        genericSetUp(semanticModelUris, modelerDescUris, true, toURI(representationsModelPath));
    }

    /**
     * Convert path to URI.
     * 
     * @param path
     *            the path
     * @param uriType
     *            type of the URI to create
     * @return the URI
     */
    protected URI toURI(final String path, ResourceURIType uriType) {
        URI uri = null;
        if (path != null) {
            String consistentPath = path;
            if (!path.startsWith("/")) {
                consistentPath = '/' + path;
            }
            if (uriType.equals(ResourceURIType.RESOURCE_PLATFORM_URI)) {
                uri = URI.createPlatformResourceURI(consistentPath, true);
            } else if (uriType.equals(ResourceURIType.RESOURCE_PLUGIN_URI)) {
                uri = URI.createPlatformPluginURI(consistentPath, true);
            }
        }
        return uri;
    }

    /**
     * Create an URI from a path.
     * 
     * @param path
     *            path to a file in plugin or temporary project
     * @return the URI
     */
    protected URI toURI(final String path) {
        if (path != null) {
            URI uri;
            // if path starts with the temporary project name, then we have a local resource uri
            if (path.startsWith(SiriusTestCase.TEMPORARY_PROJECT_NAME) || (path.startsWith('/' + SiriusTestCase.TEMPORARY_PROJECT_NAME))) {
                uri = toURI(path, ResourceURIType.RESOURCE_PLATFORM_URI);
            } else {
                uri = toURI(path, ResourceURIType.RESOURCE_PLUGIN_URI);
            }
            return uri;
        }
        return null;
    }

    /**
     * Generic set up.
     * 
     * @param semanticResourceURIs
     *            the semantic model paths
     * @param modelerResourceURIs
     *            the modeler description paths (PlatformPlugin or PlatformResource)
     * @param createSession
     *            force session creation even if it already exists
     * @param sessionResourceURI
     *            the aird path
     * @throws Exception
     *             any exception
     */
    protected void genericSetUp(final List<URI> semanticResourceURIs, final List<URI> modelerResourceURIs, boolean createSession, final URI sessionResourceURI) throws Exception {
        TestsUtil.emptyEventsFromUIThread();
        /* Set no ui callbacks for tests */
        SiriusEditPlugin.getPlugin().setUiCallback(new NoUICallback());
        createOrLoadAndOpenSession(createSession, sessionResourceURI);
        /* Load the modeler description in the editing domain resource set */
        if (modelerResourceURIs != null) {
            for (final URI modelerResourceURI : modelerResourceURIs) {
                loadModeler(modelerResourceURI, session.getTransactionalEditingDomain());
            }
        }
        if (semanticResourceURIs != null && !semanticResourceURIs.isEmpty()) {
            for (URI semanticResourceURI : semanticResourceURIs) {
                if (!hasAlreadySemanticResourceLoaded(session, semanticResourceURI)) {
                    Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
                    session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);
                }
            }
            Iterator<Resource> resourcesIterator = session.getSemanticResources().iterator();
            if (resourcesIterator.hasNext()) {
                Resource semanticResource = resourcesIterator.next();
                if (!semanticResource.getContents().isEmpty()) {
                    semanticModel = semanticResource.getContents().get(0);
                }
            }
        }

        accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(session.getTransactionalEditingDomain().getResourceSet());
        if (semanticModel != null) {
            interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticModel);
            InterpreterRegistry.prepareImportsFromSession(interpreter, session);
        }

        // Set the auto-refresh to false because it's historically the default value
        DefaultScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        InstanceScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        closeWelcomePage();
        TestsUtil.emptyEventsFromUIThread();

        // Initialize error/warning log and uncaught exception handlers
        initLoggers();

        waitSaveSessionJob();
    }

    private void createOrLoadAndOpenSession(final boolean createSession, final URI sessionResourceURI) {
        if (createSession) {
            if (sessionResourceURI == null) {
                if (createModelingProject) {
                    createSession(SiriusTestCase.DEFAULT_MODELING_PROJECT_REPRESENTATIONS_FILE_URI);
                } else {
                    createSession(getDefaultRepresentationsFileURI());
                }
            } else {
                createSession(sessionResourceURI);
            }
        } else {
            session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        }
        if (!session.isOpen()) {
            session.open(new NullProgressMonitor());
        }
    }

    /**
     * The default session URI used when there is no session path passed to the generic setup. The name of the aird used
     * the name of the test case to easily debug.
     *
     * @return default session URI.
     */
    protected URI getDefaultRepresentationsFileURI() {
        return URI.createPlatformResourceURI(File.separator + SiriusTestCase.TEMPORARY_PROJECT_NAME + File.separator + this.getClass().getSimpleName() + "_" + getName() + SiriusTestCase.DOT
                + SiriusUtil.SESSION_RESOURCE_EXTENSION, true);
    }

    /**
     * Create and open a session from this URI.
     *
     * @param sessionResourceURI
     *            the URI of the session to create.
     */
    protected void createSession(final URI sessionResourceURI) {
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            @Override
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(sessionResourceURI, new NullProgressMonitor());
                sessionCreationOperation.execute();
                session = sessionCreationOperation.getCreatedSession();
                // open UI session part
                final IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
                editingSession.open();
            }
        };
        try {
            operation.run(new NullProgressMonitor());
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Impossible to create the session.", e.getCause());
        } catch (InterruptedException e) {
            throw new RuntimeException("Impossible to create the session.", e);
        }
    }

    /**
     * Load the VSM at the specified URI and register all its Viewpoints in the testcase.
     * 
     * @param modelerResourceURI
     *            the URI of the VSM.
     * @param domain
     *            the editing domain into which the VSM should be loaded.
     * @throws Exception
     *             if an error occurs while trying to load the VSM.
     */
    protected void loadModeler(final URI modelerResourceURI, EditingDomain domain) throws Exception {
        loadModeler(modelerResourceURI, domain, true);
    }

    /**
     * Load the VSM at the specified URI and optionally register all its Viewpoints in the testcase.
     * 
     * @param modelerResourceURI
     *            the URI of the VSM.
     * @param domain
     *            the editing domain into which the VSM should be loaded.
     * @param registerViewpoints
     *            true to register viewpoints of the loaded VSM, ie add it in the <code>viewpoints</code> list.
     * @return The list of Viewpoints of the loaded modeler
     * @throws Exception
     *             if an error occurs while trying to load the VSM.
     */
    protected List<Viewpoint> loadModeler(final URI modelerResourceURI, EditingDomain domain, boolean registerViewpoints) throws Exception {
        List<Viewpoint> viewpointsOfLoadedModeler = new ArrayList<>();
        Group group = null;
        try {
            group = (Group) ModelUtils.load(modelerResourceURI, domain.getResourceSet());
        } catch (final IOException exception) {
            // if an IOException occurs here, its probably because we try to create a plaftorm plugin URI and it was a
            // local one
            String uri = modelerResourceURI.toString();
            if (uri.startsWith("platform:/plugin/")) {
                URI alternativeURI = URI.createPlatformResourceURI(uri.substring(17), true);
                group = (Group) ModelUtils.load(alternativeURI, domain.getResourceSet());
            } else {
                Assert.fail(exception.getMessage());
            }
        }
        if (group != null) {
            viewpointsOfLoadedModeler.addAll(group.getOwnedViewpoints());
            if (registerViewpoints) {
                viewpoints.addAll(group.getOwnedViewpoints());
            }
        }
        return viewpointsOfLoadedModeler;
    }

    private boolean hasAlreadySemanticResourceLoaded(Session newSession, URI semanticResourceURI) {
        for (Resource semanticResource : newSession.getSemanticResources()) {
            if (semanticResourceURI.equals(semanticResource.getURI())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a {@link ICommandFactory}. To override to return a custom {@link ICommandFactory}.
     * 
     * @return a custom {@link ICommandFactory}
     */
    protected abstract ICommandFactory getCommandFactory();

    /** Close the welcomePage. */
    protected void closeWelcomePage() {
        IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        if (activePart != null && "Welcome".equals(activePart.getTitle()) && activePart instanceof IViewPart) {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView((IViewPart) activePart);
        }
        TestsUtil.synchronizationWithUIThread();
    }

    /** Initialize the log listener. */
    protected void initLoggers() {
        logListener = (status, plugin) -> {
            switch (status.getSeverity()) {
            case IStatus.ERROR:
                errorOccurs(status, plugin);
                break;
            case IStatus.WARNING:
                warningOccurs(status, plugin);
                break;
            case IStatus.INFO:
                infoOccurs(status, plugin);
                break;
            default:
                // nothing to do
            }
        };
        Platform.addLogListener(logListener);

        exceptionHandler = new UncaughtExceptionHandler() {
            private final String sourcePlugin = "Uncaught exception";

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                IStatus status = new Status(IStatus.ERROR, sourcePlugin, sourcePlugin, e);
                errorOccurs(status, sourcePlugin);
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    }

    /** Dispose the log listener. */
    protected void disposeLoggers() {
        if (logListener != null) {
            Platform.removeLogListener(logListener);
        }
    }

    /**
     * check if an error occurs.
     * 
     * @return true if an error occurs.
     */
    protected synchronized boolean doesAnErrorOccurs() {
        return errorsCount() > 0;
    }
    
    /**
     * Returns the total number of errors recorded.
     * 
     * @return the total number of errors recorded.
     */
    protected int errorsCount() {
        return errors.values().stream().mapToInt(List::size).sum();
    }
    
    /**
     * check if a warning occurs.
     *
     * @return true if a warning occurs.
     */
    protected synchronized boolean doesAWarningOccurs() {
        return warningsCount() > 0;
    }

    /**
     * Returns the total number of warnings recorded.
     * 
     * @return the total number of warnings recorded.
     */
    protected int warningsCount() {
        return warnings.values().stream().mapToInt(List::size).sum();
    }

    /**
     * check if an info occurs.
     * 
     * @return true if an info occurs.
     */
    protected synchronized boolean doesAnInfoOccurs() {
        return infosCount() > 0;
    }
    
    /**
     * Returns the total number of infos recorded.
     * 
     * @return the total number of infos recorded.
     */
    protected int infosCount() {
        return infos.values().stream().mapToInt(List::size).sum();
    }

    /**
     * Clear the list of errors. Can be useful when some messages are expected.
     */
    protected synchronized void clearErrors() {
        errors.clear();
    }

    /**
     * Clear the list of warnings. Can be useful when some messages are expected.
     */
    protected synchronized void clearWarnings() {
        warnings.clear();
    }

    /**
     * Clear the list of infos. Can be useful when some messages are expected.
     */
    protected synchronized void clearInfos() {
        infos.clear();
    }

    /**
     * Records the error.
     * 
     * @param status
     *            error status to record
     * @param sourcePlugin
     *            source plugin in which the error occurred
     */
    protected synchronized void errorOccurs(IStatus status, String sourcePlugin) {
        if (isErrorCatchActive()) {
            boolean ignoreMessage = false;
            if ("org.eclipse.core.runtime".equals(sourcePlugin) && status != null && "Could not acquire INavigatorContentService: Project Explorer not found.".equals(status.getMessage())) {
                // Ignore error caused by bugzilla 489335 when tests are launched with product
                // "org.eclipse.platform.ide".
                ignoreMessage = true;
            }
            if (!ignoreMessage) {
                errors.putIfAbsent(sourcePlugin, new ArrayList<>());
                errors.get(sourcePlugin).add(status);
            }
        }
    }

    /**
     * Records the warning.
     * 
     * @param status
     *            warning status to record
     * @param sourcePlugin
     *            source plugin in which the warning occurred
     */
    protected synchronized void warningOccurs(IStatus status, String sourcePlugin) {
        if (isWarningCatchActive()) {
            warnings.putIfAbsent(sourcePlugin, new ArrayList<>());
            warnings.get(sourcePlugin).add(status);
        }
    }

    /**
     * Records the infos.
     * 
     * @param status
     *            info status to record
     * @param sourcePlugin
     *            source plugin in which the info occurred
     */
    protected synchronized void infoOccurs(IStatus status, String sourcePlugin) {
        if (isInfoCatchActive()) {
            infos.putIfAbsent(sourcePlugin, new ArrayList<>());
            infos.get(sourcePlugin).add(status);
        }
    }

    /**
     * Activate or deactivate the external error detection: the test will fail in an error is logged or uncaught.
     * 
     * @param errorCatchActive
     *            boolean to indicate if we activate or deactivate the external error detection
     */
    protected synchronized void setErrorCatchActive(boolean errorCatchActive) {
        this.errorCatchActive = errorCatchActive;
    }

    /**
     * Activate or deactivate the external warning detection: the test will fail in a warning is logged or uncaught.
     * 
     * @param warningCatchActive
     *            boolean to indicate if we activate or deactivate the external warning detection
     */
    protected synchronized void setWarningCatchActive(boolean warningCatchActive) {
        this.warningCatchActive = warningCatchActive;
    }

    /**
     * Activate or deactivate the external info detection: the test will fail in a warning is logged or uncaught.
     * 
     * @param infoCatchActive
     *            boolean to indicate if we activate or deactivate the external info detection
     */
    protected synchronized void setInfoCatchActive(boolean infoCatchActive) {
        this.infoCatchActive = infoCatchActive;
    }

    /**
     * check if an error catch is active.
     * 
     * @return true if an error catch is active.
     */
    protected synchronized boolean isErrorCatchActive() {
        return errorCatchActive;
    }

    /**
     * check if a warning catch is active.
     * 
     * @return true if a warning catch is active.
     */
    protected synchronized boolean isWarningCatchActive() {
        return warningCatchActive;
    }

    /**
     * check if a info catch is active.
     * 
     * @return true if a warning catch is active.
     */
    protected synchronized boolean isInfoCatchActive() {
        return infoCatchActive;
    }

    /**
     * Check that there is no existing error or warning.
     */
    private void checkLogs() {
        /*
         * Skip checkLoggers when we are in a shouldSkipUnreliableTests mode. We have some unwanted resource
         * notifications during the teardown on jenkins.
         */
        if (!TestsUtil.shouldSkipUnreliableTests()) {
            if (doesAnErrorOccurs()) {
                Assert.fail(getErrorLoggersMessage());
            }
            if (doesAWarningOccurs()) {
                Assert.fail(getWarningLoggersMessage());
            }
        }
    }

    /**
     * Compute a message from the detected warnings/errors/infos.
     * 
     * @param type
     *            type of message : Error/Warning/Info
     * @param messages
     *            map with message reported and their status
     * @return the message
     */
    private synchronized String getLoggersMessage(String type, Map<String, List<IStatus>> messages) {
        StringBuilder log1 = new StringBuilder();
        String br = "\n";
        String testName = getClass().getName();
        log1.append(type + "(s) raised during test : " + testName).append(br);
        for (Entry<String, List<IStatus>> entry : messages.entrySet()) {
            String reporter = entry.getKey();
            log1.append(". Log Plugin : " + reporter).append(br);

            for (IStatus status : entry.getValue()) {
                log1.append("  . " + getSeverity(status) + " from plugin:" + status.getPlugin() + ", message: " + status.getMessage() + ", info: " + status.getException()).append(br);
                appendStackTrace(log1, br, status);
            }
            log1.append(br);
        }
        return log1.toString();
    }

    /**
     * Compute an error message from the detected errors.
     * 
     * @return the error message.
     */
    protected synchronized String getErrorLoggersMessage() {
        return getLoggersMessage("Error", errors);
    }

    /**
     * Compute an error message from the detected warnings.
     * 
     * @return the error message.
     */
    protected synchronized String getWarningLoggersMessage() {
        return getLoggersMessage("Warning", warnings);
    }

    /**
     * Compute an error message from the detected warnings.
     * 
     * @return the error message.
     */
    protected synchronized String getInfoLoggersMessage() {
        return getLoggersMessage("Info", infos);
    }

    /**
     * Convert the <code>status</code> exception in String and add it at the end of the <code>stringBuilder</code>. Add
     * the
     * 
     * @param stringBuilder
     *            The string build to use
     * @param endLineDelimiter
     *            The end line delimiter to use
     * @param status
     *            The status to convert
     */
    protected void appendStackTrace(StringBuilder stringBuilder, String endLineDelimiter, IStatus status) {
        PrintWriter pw = null;
        String stacktrace = null;
        if (status.getException() != null) {
            try {
                StringWriter sw = new StringWriter();
                pw = new PrintWriter(sw);
                // CHECKSTYLE:OFF
                status.getException().printStackTrace(pw);
                // CHECKSTYLE:ON
                stacktrace = sw.toString();
            } finally {
                if (pw != null) {
                    pw.close();
                }
                if (stacktrace == null) {
                    stacktrace = status.getException().toString();
                }
                stringBuilder.append("   . Stack trace: " + stacktrace).append(endLineDelimiter);
            }
        }
    }

    /**
     * Convert the severity of the <code>status</code> in string.
     * 
     * @param status
     *            The status to convert.
     * @return a string representation of the severity of the status
     */
    protected String getSeverity(IStatus status) {
        String severity;
        switch (status.getSeverity()) {
        case IStatus.OK:
            severity = "Ok";
            break;
        case IStatus.INFO:
            severity = "Info";
            break;
        case IStatus.WARNING:
            severity = "Warning";
            break;
        case IStatus.CANCEL:
            severity = "Cancel";
            break;
        case IStatus.ERROR:
            severity = "Error";
            break;
        default:
            severity = "Unspecified";
        }
        return severity;
    }

    /**
     * Initialize all viewpoints.
     */
    protected final void initViewpoints() {
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final Command command = new ChangeViewpointSelectionCommand(session, selectionCallback, viewpoints, Collections.<Viewpoint> emptySet(), new NullProgressMonitor());
        domain.getCommandStack().execute(command);
        waitSaveSessionJob();
    }

    /**
     * Ensure that the SaveSessionJob potentially triggered by
     * org.eclipse.sirius.ui.business.internal.session.SaveSessionWhenNoDialectEditorsListener.statusChangedInternal(Collection<ResourceStatusChange>)
     * is finished before continue.
     */
    protected void waitSaveSessionJob() {
        try {
            Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
        } catch (OperationCanceledException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Initialize a viewpoint (and force the initialization of the description to true).
     * 
     * @param viewpointName
     *            the name of the viewpoint to initialize.
     */
    protected final void initViewpoint(final String viewpointName) {
        initViewpoint(viewpointName, session, true);
    }

    /**
     * Initialize a viewpoint.
     * 
     * @param viewpointName
     *            the name of the viewpoint to initialize.
     * @param alternateSession
     *            the session to use to initialize the viewpoint
     * @param alternateSemanticModel
     *            the model to use to initialize the viewpoint
     * @since 1.1
     * @deprecated use {@link SiriusTestCase#initViewpoint(String, Session, boolean)} instead
     */
    @Deprecated
    protected final void initViewpoint(final String viewpointName, final Session alternateSession, final EObject alternateSemanticModel) {
        initViewpoint(viewpointName, alternateSession, true);
    }

    /**
     * Initialize a viewpoint.
     * 
     * @param viewpointName
     *            the name of the viewpoint to initialize.
     * @param alternateSession
     *            the session to use to initialize the viewpoint
     * @param initRepresentations
     *            true to init representations at viewpoint activation, false to not do it
     * @since 1.1
     */
    protected final void initViewpoint(final String viewpointName, final Session alternateSession, final boolean initRepresentations) {
        Viewpoint localSessionViewpoint = null;
        for (final Viewpoint viewpoint : viewpoints) {
            if (viewpointName != null && viewpointName.equals(viewpoint.getName())) {
                localSessionViewpoint = getViewpointFromName(viewpointName, alternateSession);
                break;
            }
        }
        if (localSessionViewpoint != null) {
            Command changeViewpointsSelectionCmd = new ChangeViewpointSelectionCommand(alternateSession, new ViewpointSelectionCallback(), Collections.singleton(localSessionViewpoint),
                    Collections.<Viewpoint> emptySet(), initRepresentations, new NullProgressMonitor());
            alternateSession.getTransactionalEditingDomain().getCommandStack().execute(changeViewpointsSelectionCmd);
        }
        waitSaveSessionJob();
    }

    /**
     * Activate a viewpoint.
     * 
     * @param name
     *            the viewpoint name to activate
     */
    protected final void activateViewpoint(final String name) {
        boolean activatedViewpoint = false;
        for (final Viewpoint viewpoint : viewpoints) {
            if (name.equals(viewpoint.getName())) {
                Viewpoint viewpointFromName = getViewpointFromName(name, session);
                Command changeViewpointsSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.singleton(viewpointFromName), Collections.<Viewpoint> emptySet(),
                        new NullProgressMonitor());
                session.getTransactionalEditingDomain().getCommandStack().execute(changeViewpointsSelection);
                activatedViewpoint = true;
                waitSaveSessionJob();
                break;
            }
        }
        if (!activatedViewpoint) {
            for (final Viewpoint viewpoint : ViewpointRegistry.getInstance().getViewpoints()) {
                if (name.equals(viewpoint.getName())) {
                    Viewpoint viewpointFromName = getViewpointFromName(name, session);
                    if (viewpointFromName == null) {
                        viewpoints.add(viewpoint);
                        viewpointFromName = getViewpointFromName(name, session);
                    }
                    Command changeViewpointsSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.singleton(viewpointFromName), Collections.<Viewpoint> emptySet(),
                            new NullProgressMonitor());
                    session.getTransactionalEditingDomain().getCommandStack().execute(changeViewpointsSelection);
                    activatedViewpoint = true;
                    waitSaveSessionJob();
                    break;
                }
            }
        }
    }

    /**
     * Deactivate a viewpoint.
     * 
     * @param name
     *            the viewpoint name to deactivate
     */
    protected final void deactivateViewpoint(final String name) {
        boolean deactivatedViewpoint = false;
        for (final Viewpoint viewpoint : viewpoints) {
            if (name.equals(viewpoint.getName())) {
                Viewpoint viewpointFromName = getViewpointFromName(name, session);
                Command changeSiriussSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.<Viewpoint> emptySet(), Collections.singleton(viewpointFromName),
                        new NullProgressMonitor());
                session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelection);
                deactivatedViewpoint = true;
                waitSaveSessionJob();
                break;
            }
        }
        if (!deactivatedViewpoint) {
            for (final Viewpoint viewpoint : ViewpointRegistry.getInstance().getViewpoints()) {
                if (name.equals(viewpoint.getName())) {
                    Viewpoint viewpointFromName = getViewpointFromName(name, session);
                    Command changeSiriussSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.<Viewpoint> emptySet(), Collections.singleton(viewpointFromName),
                            new NullProgressMonitor());
                    session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelection);
                    waitSaveSessionJob();
                    break;
                }
            }
        }
    }

    /**
     * Create a new representation from the contextual semanticModel & the contextual Session.
     * 
     * @param representationDescriptionName
     *            the representation description name
     * @return the created representation, or <code>null</code> if the representation description could not be found
     */
    protected final DRepresentation createRepresentation(final String representationDescriptionName) {
        return createRepresentation(representationDescriptionName, representationDescriptionName, semanticModel, session);
    }

    /**
     * Create a new representation from the contextual Session.
     * 
     * @param representationDescriptionName
     *            the representation description name
     * @param semantic
     *            the semantic root object
     * @return the created representation, or <code>null</code> if the representation description could not be found
     */
    protected final DRepresentation createRepresentation(final String representationDescriptionName, final EObject semantic) {
        return createRepresentation(representationDescriptionName, representationDescriptionName, semantic, session);
    }

    /**
     * Create a new representation.
     * 
     * @param representationDescriptionName
     *            the representation description name
     * @param semantic
     *            the semantic root object
     * @param sessionToUse
     *            the session to use instead of the contextual Session
     * @return the created representation, or <code>null</code> if the representation description could not be found
     */
    protected final DRepresentation createRepresentation(final String representationDescriptionName, final EObject semantic, final Session sessionToUse) {
        return createRepresentation(representationDescriptionName, representationDescriptionName, semantic, sessionToUse);
    }

    /**
     * Create a new representation with a specific name.
     * 
     * @param representationDescriptionName
     *            the representation description name
     * @param name
     *            the name of the new representation
     * @param semantic
     *            the semantic root object
     * @return the created representation, or <code>null</code> if the representation description could not be found
     */
    protected final DRepresentation createRepresentation(final String representationDescriptionName, final String name, final EObject semantic) {
        return createRepresentation(representationDescriptionName, representationDescriptionName, semantic, session);
    }

    /**
     * Create a new representation with a specific name.
     * 
     * @param representationDescriptionName
     *            the representation description name
     * @param name
     *            the name of the new representation
     * @param semantic
     *            the semantic root object
     * @param sessionToUse
     *            the session to use instead of the contextual Session
     * @return the created representation, or <code>null</code> if the representation description could not be found
     */
    protected final DRepresentation createRepresentation(final String representationDescriptionName, final String name, final EObject semantic, final Session sessionToUse) {
        final Collection<RepresentationDescription> descriptions = new ArrayList<RepresentationDescription>();
        descriptions.addAll(DialectManager.INSTANCE.getAvailableRepresentationDescriptions(viewpoints, semantic));

        final Command cmd = new RecordingCommand(sessionToUse.getTransactionalEditingDomain()) {
            private DRepresentation representation;

            @Override
            protected void doExecute() {
                for (final RepresentationDescription description : descriptions) {
                    if (description.getName().equals(representationDescriptionName)) {
                        Viewpoint viewpointOfRegistry = (Viewpoint) description.eContainer();
                        Viewpoint localViewpoint = getViewpointFromName(viewpointOfRegistry.getName());
                        RepresentationDescription localDescription = getLocalSessionRepresentationDescription(localViewpoint, representationDescriptionName);
                        representation = DialectManager.INSTANCE.createRepresentation(name, semantic, localDescription, sessionToUse, new NullProgressMonitor());
                        return;
                    }
                }
            }

            @Override
            public Collection<?> getResult() {
                return Collections.singletonList(representation);
            }
        };
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(semantic);
        domain.getCommandStack().execute(cmd);
        return (DRepresentation) cmd.getResult().iterator().next();
    }

    /**
     * Get a {@link RepresentationDescription} ref from the current session ResourceSet.
     * 
     * @param localViewpoint
     *            the Sirius local to the current session ResourceSet containing the {@link RepresentationDescription}
     *            to get
     * 
     * @param representationDescriptionName
     *            the name of the {@link RepresentationDescription} to get
     * 
     * @return the {@link RepresentationDescription} to get
     */
    private RepresentationDescription getLocalSessionRepresentationDescription(Viewpoint localViewpoint, String representationDescriptionName) {
        Iterable<RepresentationDescription> candidates = new ViewpointQuery(localViewpoint).getAllRepresentationDescriptions();
        RepresentationDescription result = null;
        for (RepresentationDescription localDescription : candidates) {
            if (representationDescriptionName.equals(localDescription.getName())) {
                result = localDescription;
                break;
            }
        }
        return result;
    }

    /**
     * Get a viewpoint in the viewpoints Set named viewpointName, null else.
     * 
     * @param viewpointName
     *            the viewpointName to look for
     * 
     * @return the first {@link Viewpoint} of the viewpoints Set
     */
    public Viewpoint getViewpointFromName(String viewpointName) {
        return getViewpointFromName(viewpointName, session);
    }

    /**
     * Get a viewpoint in the viewpoints Set named viewpointName, null else.
     * 
     * @param viewpointName
     *            the viewpointName to look for
     * 
     * @param sessionToUse
     *            the Session from which ResourceSet to return the Viewpoint
     * 
     * @return the first {@link Viewpoint} of the viewpoints Set, return a logical Viewpoint from the session's
     *         ResourceSet and not from the {@link ViewpointRegistry}'s ResourceSet
     */
    public Viewpoint getViewpointFromName(String viewpointName, Session sessionToUse) {
        Viewpoint localViewpoint = null;
        for (Viewpoint viewpoint : viewpoints) {
            if (viewpoint.eResource() != null && viewpoint.getName() != null && viewpoint.getName().equals(viewpointName)) {
                URI viewpointResourceURI = viewpoint.eResource().getURI();
                Resource newViewpointResource = sessionToUse.getTransactionalEditingDomain().getResourceSet().getResource(viewpointResourceURI, true);
                if (!newViewpointResource.getContents().isEmpty() && newViewpointResource.getContents().get(0) instanceof Group) {
                    Group group = (Group) newViewpointResource.getContents().get(0);
                    Iterator<Viewpoint> iter = group.getOwnedViewpoints().iterator();

                    while (iter.hasNext() && localViewpoint == null) {
                        Viewpoint someLocalViewpoint = iter.next();
                        if (someLocalViewpoint.getName() != null && someLocalViewpoint.getName().equals(viewpointName)) {
                            localViewpoint = someLocalViewpoint;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return localViewpoint;
    }

    /**
     * Get a {@link RepresentationDescription} named <code>representationDescriptionName</code> owned by
     * <code>viewpoint</code>.
     * 
     * @param representationDescriptionName
     *            the name of the {@link RepresentationDescription} to search
     * 
     * @param viewpoint
     *            the {@link Viewpoint} in which to search
     * 
     * @return the found {@link RepresentationDescription} or null if nothing found
     */
    public RepresentationDescription getRepresentationDescription(String representationDescriptionName, Viewpoint viewpoint) {
        RepresentationDescription result = null;
        for (RepresentationDescription ownedRepresentationDescription : viewpoint.getOwnedRepresentations()) {
            if (representationDescriptionName.equals(ownedRepresentationDescription.getName())) {
                result = ownedRepresentationDescription;
                break;
            }
        }
        return result;
    }

    /**
     * Get all the representation with the given representation description name.
     * 
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @return a {@link Collection} with all representations retrieved.
     */
    protected final Collection<DRepresentation> getRepresentations(final String representationDescriptionName) {
        final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);

        final Collection<DRepresentation> representations = new HashSet<DRepresentation>();

        for (final DRepresentation representation : allRepresentations) {
            final RepresentationDescription desc = DialectManager.INSTANCE.getDescription(representation);
            if (representationDescriptionName.equals(desc.getName())) {
                representations.add(representation);
            }
        }
        return representations;
    }

    /**
     * Get the representation with the given name.</br>
     * The search scope is the representations already loaded in the session.
     * 
     * @param representationName
     *            the name of the representation. <code>null</code> is not expected.
     * @return a {@link List} with all representations retrieved.
     */
    protected final List<DRepresentation> getRepresentationsByName(final String representationName) {
        final List<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentationDescriptors(session).stream().filter(rep -> representationName.equals(rep.getName()))
                .map(DRepresentationDescriptor::getRepresentation).collect(Collectors.toList());
        return representations;
    }

    /**
     * Get all representation descriptors with the given representation description name.
     * 
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @return a {@link Collection} with all representation descriptors retrieved.
     */
    protected final Collection<DRepresentationDescriptor> getRepresentationDescriptors(final String representationDescriptionName) {
        return getRepresentationDescriptors(representationDescriptionName, session);
    }

    /**
     * Get all representations with the given representation description name in the given session.
     * 
     * @param name
     *            the name. <code>null</code> is not excepted.
     * @param alternateSession
     *            the session to look for representation
     * @return a {@link Collection} with all representations retrieved.
     */
    protected final Collection<DRepresentation> getRepresentations(final String name, final Session alternateSession) {
        final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(alternateSession);

        final Collection<DRepresentation> representations = new HashSet<DRepresentation>();

        for (final DRepresentation representation : allRepresentations) {
            final RepresentationDescription desc = DialectManager.INSTANCE.getDescription(representation);
            if (name.equals(desc.getName())) {
                representations.add(representation);
            }
        }
        return representations;
    }

    /**
     * Get all representation descriptors with the given representation description name in the given session.
     * 
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code> is not excepted.
     * @param alternateSession
     *            the session to look for representation
     * @return a {@link Collection} with all representations retrieved.
     */
    protected final Collection<DRepresentationDescriptor> getRepresentationDescriptors(final String representationDescriptionName, final Session alternateSession) {
        final Collection<DRepresentationDescriptor> allRepDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(alternateSession);

        final Collection<DRepresentationDescriptor> repDescriptors = new HashSet<DRepresentationDescriptor>();

        for (final DRepresentationDescriptor repDescriptor : allRepDescriptors) {
            final RepresentationDescription desc = repDescriptor.getDescription();
            if (representationDescriptionName.equals(desc.getName())) {
                repDescriptors.add(repDescriptor);
            }
        }
        return repDescriptors;
    }

    /**
     * Get all representations with the given representation description name in the given session.
     * 
     * @param name
     *            the name. <code>null</code> is not excepted.
     * @param semantic
     *            the semantic target of the representation.
     * @param alternateSession
     *            the session to look for representation
     * @return a {@link Collection} with all representations retrieved.
     */
    protected final Collection<DRepresentation> getRepresentations(final String name, final EObject semantic, final Session alternateSession) {
        final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getRepresentations(semantic, alternateSession);
        final Collection<DRepresentation> representations = new HashSet<DRepresentation>();
        for (final DRepresentation representation : allRepresentations) {
            final RepresentationDescription desc = DialectManager.INSTANCE.getDescription(representation);
            if (name.equals(desc.getName())) {
                representations.add(representation);
            }
        }
        return representations;
    }

    /**
     * Get all the representation with the given representation description name.
     * 
     * @param name
     *            the name. <code>null</code> is not excepted.
     * @param semantic
     *            the semantic target of the representation.
     * @return a {@link Collection} with all representations retrieved.
     */
    protected final Collection<DRepresentation> getRepresentations(final String name, final EObject semantic) {
        final Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getRepresentations(semantic, session);
        final Collection<DRepresentation> representations = new HashSet<DRepresentation>();
        for (final DRepresentation representation : allRepresentations) {
            final RepresentationDescription desc = DialectManager.INSTANCE.getDescription(representation);
            if (name.equals(desc.getName())) {
                representations.add(representation);
            }
        }
        return representations;
    }

    /**
     * Refresh a representation. Do a partial refresh by default.
     * 
     * @param representation
     *            the representation to refresh.
     */
    protected void refresh(DRepresentation representation) {
        refresh(representation, false);
    }

    /**
     * Refresh a representation.
     * 
     * @param representation
     *            the representation to refresh.
     * @param fullRefresh
     *            true to do a full refresh, false to do a partial refresh
     */
    protected void refresh(DRepresentation representation, boolean fullRefresh) {
        RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(representation);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        RefreshRepresentationsCommand command = new RefreshRepresentationsCommand(domain, fullRefresh, new NullProgressMonitor(), representation);
        command.setLabel("refresh from testcase");
        domain.getCommandStack().execute(command);
    }

    /**
     * Get the representation element from the semantic one.
     * 
     * @param representation
     *            the representation
     * @param semanticElement
     *            the semantic element
     * @return the first representation element which has as target the semantic element given as parameter
     */
    protected final DRepresentationElement getFirstRepresentationElement(final DRepresentation representation, final EObject semanticElement) {
        for (final DRepresentationElement element : representation.getRepresentationElements()) {
            if (element.getTarget() == semanticElement) {
                return element;
            }
        }
        return null;
    }

    /**
     * Get the representation element from the semantic one.
     * 
     * @param <T>
     *            generic type
     * @param representation
     *            the representation
     * @param semanticElement
     *            the semantic element
     * @param clazz
     *            the type of representation element
     * @return the first representation element which has as target the semantic element given as parameter
     */
    @SuppressWarnings("unchecked")
    protected final <T> T getFirstRepresentationElement(final DRepresentation representation, final EObject semanticElement, final Class<T> clazz) {
        for (final DRepresentationElement element : representation.getRepresentationElements()) {
            if (clazz.isInstance(element) && element.getTarget() == semanticElement) {
                return (T) element;
            }
        }
        return null;
    }

    /**
     * Get all representation elements which target a given semantic element from a representation.
     * 
     * @param representation
     *            the representation
     * @param semanticElement
     *            the target semantic element
     * @return all the graphical elements with the given semantic element as target
     */
    protected final Collection<DRepresentationElement> getAllRepresentationElements(final DRepresentation representation, final EObject semanticElement) {
        final Collection<DRepresentationElement> elements = new HashSet<DRepresentationElement>();
        for (final DRepresentationElement element : representation.getRepresentationElements()) {
            if (element.getTarget() == semanticElement) {
                elements.add(element);
            }
        }
        return elements;
    }

    /**
     * Execute a command.
     * 
     * @param cmd
     *            the command to execute
     * @return <code>true</code> if the execution succeed, <code>false</code> otherwise
     */
    protected boolean executeCommand(final Command cmd) {
        boolean result = cmd.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        return result;
    }

    /**
     * Delete every project in the workspace.
     */
    protected void cleanWorkspace() {
        for (final IProject proj : Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects())) {
            EclipseTestsSupportHelper.INSTANCE.deleteProject(proj.getName());
        }
    }

    /**
     * Copies the files located at the given paths into the test project.
     * 
     * @param pluginID
     *            the plugin id
     * @param pluginCommonPath
     *            the relative path in plugin
     * @param filePaths
     *            the paths of the files to copy
     */
    protected void copyFilesToTestProject(String pluginID, String pluginCommonPath, String... filePaths) {
        EclipseTestsSupportHelper.INSTANCE.createProject(SiriusTestCase.TEMPORARY_PROJECT_NAME);
        for (final String path : filePaths) {
            final String pluginFilePath = pluginCommonPath + path;
            final String wksPath = SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + path;
            EclipseTestsSupportHelper.INSTANCE.copyFile(pluginID, pluginFilePath, wksPath);
        }
    }

    /**
     * Copies the files located at the given paths into the targetPath.
     * 
     * @param pluginID
     *            the plugin id
     * @param pluginCommonPath
     *            the relative path in plugin
     * @param targetPath
     *            the target path in junit workspace
     * @param filePaths
     *            the paths of the files to copy
     */
    protected void copyFiles(String pluginID, String pluginCommonPath, String targetPath, String... filePaths) {
        for (final String path : filePaths) {
            final String pluginFilePath = pluginCommonPath + path;
            final String wksPath = targetPath + File.separator + path;
            EclipseTestsSupportHelper.INSTANCE.copyFile(pluginID, pluginFilePath, wksPath);
        }
    }

    /**
     * Copy all files recursively from the source project to the project in the junit workspace.
     * 
     * @param pluginID
     *            the plugin id
     * @param sourceProjecPathInPlugin
     *            the relative path in plugin of the project to copy
     * @param projectName
     *            the destination project name that will be copied in the junit workspace
     */
    protected void copyAllFiles(String pluginID, String sourceProjecPathInPlugin, String projectName) {
        // It is needed because the platform URI of the aird can not be resolved.
        EclipseTestsSupportHelper.INSTANCE.createProject(projectName);
        final File sourceFile = FileProvider.getDefault().getFile(pluginID, new Path(sourceProjecPathInPlugin));
        String sourceAbsolutePath = sourceFile.getAbsolutePath();
        IPath destinationPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().append(projectName);
        String targetAbsolutePath = destinationPath.toOSString();
        try {
            EclipseTestsSupportHelper.INSTANCE.copyDirectory(sourceAbsolutePath, targetAbsolutePath);
        } catch (IOException exception) {
            Assert.fail(exception.getMessage());
        }
        try {
            ResourcesPlugin.getWorkspace().getRoot().refreshLocal(org.eclipse.core.resources.IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
        }
    }

    /**
     * Close UI session.
     * 
     * @param sessionToClose
     *            the session to close.
     */
    protected void closeSession(final Session sessionToClose) {
        if (sessionToClose != null) {
            final IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(sessionToClose);
            if (sessionUI != null) {
                SessionUIManager.INSTANCE.remove(sessionUI);
                sessionUI.close();
                TestsUtil.synchronizationWithUIThread();
            }
            sessionToClose.close(new NullProgressMonitor());
            TestsUtil.synchronizationWithUIThread();
            for (final Session s : SessionManager.INSTANCE.getSessions()) {
                Assert.assertFalse("Remove failed", s.equals(sessionToClose));
            }
            if (sessionToClose.isOpen()) {
                sessionToClose.close(new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
            }
        }
    }

    /**
     * Close and reopen a UI session with saving.
     * 
     * @throws Exception
     *             exception
     */
    protected void closeAndReloadSession() throws Exception {
        final URI sessionResourceURI = session.getSessionResource().getURI();
        /* close session */
        TestsUtil.synchronizationWithUIThread();
        closeSession(session);
        TestsUtil.synchronizationWithUIThread();
        /* reload session */
        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        if (!session.isOpen()) {
            session.open(new NullProgressMonitor());
        }
        SessionUIManager.INSTANCE.getOrCreateUISession(session).open();
        TestsUtil.synchronizationWithUIThread();
        Assert.assertNotNull(session);
        /* Set again the variables that have been lost during the unload. */
        interpreter = session.getInterpreter();
        if (!session.getSemanticResources().isEmpty()) {
            Resource resource = session.getSemanticResources().iterator().next();
            if (resource.getContents() != null && !resource.getContents().isEmpty()) {
                semanticModel = resource.getContents().get(0);
                accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(semanticModel);
            }
        }
    }

    /**
     * Change a preference and store the old value. It will be automatically reset during tear down.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramPreference(String preferenceKey, Integer newValue) {
        SiriusAssert.assertNoDiagramUIPreferenceChangedinDiagramCoreStore(preferenceKey);

        int oldValue = Platform.getPreferencesService().getInt(DiagramPlugin.ID, preferenceKey, 0, null);
        oldValueDiagramPreferences.putIfAbsent(preferenceKey, oldValue);

        IEclipsePreferences diagramCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        diagramCorePreferences.putInt(preferenceKey, newValue);

        int valueToCheck = Platform.getPreferencesService().getInt(DiagramPlugin.ID, preferenceKey, 0, null);
        TestCase.assertEquals(getErrorMessage(preferenceKey, DiagramPlugin.ID), newValue.intValue(), valueToCheck);
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramPreference(String preferenceKey, Boolean newValue) {
        SiriusAssert.assertNoDiagramUIPreferenceChangedinDiagramCoreStore(preferenceKey);

        boolean oldValue = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, preferenceKey, false, null);
        oldValueDiagramPreferences.putIfAbsent(preferenceKey, oldValue);

        IEclipsePreferences diagramCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        diagramCorePreferences.putBoolean(preferenceKey, newValue);

        boolean valueToCheck = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, preferenceKey, false, null);
        TestCase.assertEquals(getErrorMessage(preferenceKey, DiagramPlugin.ID), newValue.booleanValue(), valueToCheck);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramPreference(String, Boolean)} of {@link #changeDiagramPreference(String, Integer)} to have
     * effect.
     * 
     * @param preferenceKey
     *            The key of the preference.
     */
    protected void resetDiagramPreference(String preferenceKey) {
        IEclipsePreferences diagramCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        resetDiagramPreference(preferenceKey, diagramCorePreferences);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramPreference(String, Boolean)} of {@link #changeDiagramPreference(String, Integer)} to have
     * effect.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param diagramCorePreferences
     *            The {@link IEclipsePreferences} to use.
     */
    private void resetDiagramPreference(String preferenceKey, IEclipsePreferences diagramCorePreferences) {
        Object initialValue = oldValueDiagramPreferences.get(preferenceKey);
        if (initialValue instanceof Boolean) {
            diagramCorePreferences.putBoolean(preferenceKey, (Boolean) initialValue);
        } else if (initialValue instanceof Integer) {
            diagramCorePreferences.putInt(preferenceKey, (Integer) initialValue);
        }
    }

    /**
     * Change a preference and store the old value. It will be automatically reset during tear down.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramUIPreference(String preferenceKey, Integer newValue) {
        SiriusAssert.assertNoDiagramCorePreferenceChangedinDiagramUIStore(preferenceKey);

        final IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        oldValueDiagramUiPreferences.putIfAbsent(preferenceKey, prefs.getInt(preferenceKey));
        prefs.setValue(preferenceKey, newValue);
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramUIPreference(String preferenceKey, Boolean newValue) {
        SiriusAssert.assertNoDiagramCorePreferenceChangedinDiagramUIStore(preferenceKey);

        final IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        oldValueDiagramUiPreferences.putIfAbsent(preferenceKey, prefs.getBoolean(preferenceKey));
        prefs.setValue(preferenceKey, newValue);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramUIPreference(String, Boolean)} of {@link #changeDiagramUIPreference(String, Integer)} to
     * have effect.
     * 
     * @param preferenceKey
     *            The key of the preference.
     */
    protected void resetDiagramUiPreference(String preferenceKey) {
        final IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();
        resetDiagramUiPreference(preferenceKey, prefs);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramUIPreference(String, Boolean)} of {@link #changeDiagramUIPreference(String, Integer)} to
     * have effect.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param diagramUIPreferences
     *            The preference store to use.
     */
    private void resetDiagramUiPreference(String preferenceKey, IPreferenceStore diagramUIPreferences) {
        Object initialValue = oldValueDiagramUiPreferences.get(preferenceKey);
        if (initialValue instanceof Boolean) {
            diagramUIPreferences.setValue(preferenceKey, (Boolean) initialValue);
        } else if (initialValue instanceof Integer) {
            diagramUIPreferences.setValue(preferenceKey, (Integer) initialValue);
        }
    }

    /**
     * Change a boolean preference and store the old value to reset it after the test.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changePlatformUIPreference(String preferenceKey, Boolean newValue) {
        IPreferenceStore platformUIPrefs = PlatformUI.getPreferenceStore();
        oldPlatformUIPreferences.put(preferenceKey, platformUIPrefs.getBoolean(preferenceKey));
        platformUIPrefs.setValue(preferenceKey, newValue);
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusPreference(String preferenceKey, Boolean newValue) {
        boolean oldValue = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);

        oldValueSiriusPreferences.putIfAbsent(preferenceKey, oldValue);

        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        corePreferences.putBoolean(preferenceKey, newValue);

        boolean valueToCheck = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);
        TestCase.assertEquals(getErrorMessage(preferenceKey, SiriusPlugin.ID), newValue.booleanValue(), valueToCheck);
    }

    /**
     * Change a boolean preference and store the old value. It will be automatically reset during tear down.
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusUIPreference(String preferenceKey, Boolean newValue) {
        SiriusAssert.assertNoSiriusCorePreferenceChangedinSiriusUIStore(preferenceKey);

        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        oldValueSiriusUIPreferences.putIfAbsent(preferenceKey, viewpointUIPrefs.getBoolean(preferenceKey));
        viewpointUIPrefs.setValue(preferenceKey, newValue);
    }

    private String getErrorMessage(String preferenceKey, String pluginId) {
        return "The " + preferenceKey + " preference value was not changed for plugin " + pluginId;
    }

    /**
     * Clean the current session
     */
    private void cleanCurrentSession() {
        /* close the session */
        if (session != null) {
            final IEditingSession sessionUI = SessionUIManager.INSTANCE.getUISession(session);
            if (sessionUI != null) {
                SessionUIManager.INSTANCE.remove(sessionUI);
                sessionUI.close();
                TestsUtil.synchronizationWithUIThread();
            }
            if (session.isOpen()) {
                /* close the session */

                if (!TestsUtil.shouldSkipUnreliableTests()) {
                    session.close(new NullProgressMonitor());
                } else {
                    // CHECKSTYLE:OFF
                    try {
                        session.close(new NullProgressMonitor());
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                    // CHECKSTYLE:ON
                }
                TestsUtil.synchronizationWithUIThread();
            }
            session = null;
        }
    }

    private void closeAllSessions() {
        for (final Session managerSession : new ArrayList<Session>(SessionManager.INSTANCE.getSessions())) {
            if (managerSession.isOpen()) {
                managerSession.close(new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
            }
        }
    }

    /**
     * This will undo the last Command if possible and return <code>True</code> if it is, <code>False</code> otherwise.
     * 
     * @return <code>True</code> if the command was undoable, <code>False</code> otherwise.
     * @throws Exception
     *             In case of problem
     */
    protected boolean undo() throws Exception {
        CommandStack commandStack = session.getTransactionalEditingDomain().getCommandStack();
        final boolean result = commandStack.canUndo();
        if (result) {
            commandStack.undo();
        }
        return result;
    }

    /**
     * This will redo the last undone Command if possible and return <code>True</code> if it is, <code>False</code>
     * otherwise.
     * 
     * @return <code>True</code> if the command was undoable, <code>False</code> otherwise.
     * @throws Exception
     *             In case of problem
     */
    protected boolean redo() throws Exception {
        CommandStack commandStack = session.getTransactionalEditingDomain().getCommandStack();
        final boolean result = commandStack.canRedo();
        if (result) {
            commandStack.redo();
        }
        return result;
    }

    @Override
    protected void tearDown() throws Exception {
        CrossReferenceAdapterDetector crossRefDetector = new CrossReferenceAdapterDetector();
        createModelingProject = false;
        setErrorCatchActive(false);
        setWarningCatchActive(false);

        TransactionalEditingDomain domain = null;

        /* close the session */
        if (session != null) {
            domain = session.getTransactionalEditingDomain();
            cleanCurrentSession();
        }

        crossRefDetector.checkNoCrossReferenceAdapter();

        closeAllSessions();
        // Add a emptyEventsFromUIThread here to wait the closing of all editors
        // before disposing the editing domain.
        TestsUtil.emptyEventsFromUIThread();

        if (domain != null) {
            LinkedHashSet<Group> groups = new LinkedHashSet<>();

            for (Viewpoint vp : viewpoints) {
                if (vp.eContainer() instanceof Group) {
                    groups.add((Group) vp.eContainer());
                }
            }

            List.copyOf(domain.getResourceSet().getResources()).forEach(Resource::unload);

            for (final Group modelerModele : groups) {
                domain.getResourceSet().getResources().remove(modelerModele.eResource());
            }

            if (domain.getCommandStack() != null) {
                CommandStackUtil.flushOperations(domain.getCommandStack());
            }
            domain.getResourceSet().getResources().clear();
            /* dispose the editing domain */
            // CHECKSTYLE:OFF
            try {
                domain.dispose();
            } catch (final Exception e) {
                /* don't worry, that's normal :D */
            }
            // CHECKSTYLE:ON
        }
        TestsUtil.synchronizationWithUIThread();
        viewpoints.clear();

        /* Delete the temporary project */
        cleanWorkspace();

        disposeLoggers();
        TestsUtil.emptyEventsFromUIThread();
        // Reset the preferences changed during the test with the method
        // changePreference
        IEclipsePreferences diagamCorePreferences = InstanceScope.INSTANCE.getNode(DiagramPlugin.ID);
        for (String key : oldValueDiagramPreferences.keySet()) {
            resetDiagramPreference(key, diagamCorePreferences);
        }
        IPreferenceStore diagramUIPreferences = DiagramUIPlugin.getPlugin().getPreferenceStore();
        for (String key : oldValueDiagramUiPreferences.keySet()) {
            resetDiagramUiPreference(key, diagramUIPreferences);
        }
        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        for (Entry<String, Object> pref : oldValueSiriusPreferences.entrySet()) {
            corePreferences.putBoolean(pref.getKey(), (Boolean) pref.getValue());
        }
        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        for (Entry<String, Object> pref : oldValueSiriusUIPreferences.entrySet()) {
            viewpointUIPrefs.setValue(pref.getKey(), (Boolean) pref.getValue());
        }
        IPreferenceStore platformUIPrefs = PlatformUI.getPreferenceStore();
        for (Entry<String, Object> pref : oldPlatformUIPreferences.entrySet()) {
            platformUIPrefs.setValue(pref.getKey(), (Boolean) pref.getValue());
        }

        crossRefDetector.assertNoCrossReferenceAdapterFound();
        checkLogs();

        new TestCaseCleaner(this).clearAllFields();

        super.tearDown();
        ResourcesPlugin.getWorkspace().save(true, null);
    }

    /**
     * Disable the specific dialect editor dialogs.
     * 
     * @param editor
     *            The editor on which disable the dialogs.
     */
    protected void disableUICallBackOnDialectEditor(DialectEditor editor) {
        editor.setDialogFactory(new DummyDialectEditorDialogFactory());
    }

    /**
     * Find a viewpoint by name from the global registry.
     * 
     * @param name
     *            name of the {@link Viewpoint} to look for.
     * @return the first {@link Viewpoint} found in the registry with the specified name, if any. The instance returned
     *         is the one from the {@link Viewpoint} registry's editing domain.
     */
    protected Option<Viewpoint> findViewpoint(String name) {
        for (Viewpoint vp : ViewpointRegistry.getInstance().getViewpoints()) {
            if (vp.getName().equals(name)) {
                return Options.newSome(vp);
            }
        }
        return Options.newNone();
    }

    /**
     * Return all resource type ecore or aird in resource set passed in parameter.
     * 
     * @param rs
     *            the resource set.
     * @return list of aird and ecore contains in resource set.
     */
    protected List<Resource> getResourceTypeAirdOrEcore(ResourceSet rs) {
        List<Resource> resourcesAirdAndEcore = new ArrayList<Resource>();
        for (Resource resource : rs.getResources()) {
            if (resource instanceof AirdResource) {
                resourcesAirdAndEcore.add(resource);
            } else if ("ecore".equals(resource.getURI().fileExtension())) {
                resourcesAirdAndEcore.add(resource);
            }
        }
        return resourcesAirdAndEcore;
    }

    /**
     * Check that the data has the expected migration need.
     * 
     * It can be used to verify that a file has not be migrated before the test. And then it allows to check the effect
     * of the migration in the other test.
     * 
     * @param representationFileURI
     *            the uri of the representation file to check.
     * @param needsMigration
     *            indicates the expected migration need.
     * @return the loaded @link {@link Version} for convenience
     */
    protected Version checkRepresentationFileMigrationStatus(URI representationFileURI, boolean needsMigration) {
        RepresentationsFileVersionSAXParser parser = new RepresentationsFileVersionSAXParser(representationFileURI);
        return checkMigrationStatusOnData(representationFileURI, parser, RepresentationsFileMigrationService.getInstance(), needsMigration);
    }

    /**
     * Check that the data has the expected migration need.
     * 
     * It can be used to verify that a file has not be migrated before the test. And then it allows to check the effect
     * of the migration in the other test.
     * 
     * @param vsmFileURI
     *            the uri of the VSM file to check.
     * @param needsMigration
     *            indicates the expected migration need.
     * @return the loaded @link {@link Version} for convenience
     */
    protected Version checkVsmFileMigrationStatus(URI vsmFileURI, boolean needsMigration) {
        VSMVersionSAXParser parser = new VSMVersionSAXParser(vsmFileURI);
        return checkMigrationStatusOnData(vsmFileURI, parser, VSMMigrationService.getInstance(), needsMigration);
    }

    /**
     * Check that the data were not migrated before the test. It allows to check the effect of the migration in the
     * other test.
     * 
     * @param fileURI
     *            the uri of the file to check.
     * 
     * @param needsMigration
     *            indicates the expected migration need.
     * @return the loaded @link {@link Version} for convenience
     */
    private Version checkMigrationStatusOnData(URI fileURI, AbstractVersionSAXParser versionSaxPArser, AbstractSiriusMigrationService migrationService, boolean needsMigration) {
        // Get the version before the migration.
        String sLoadedVersion = versionSaxPArser.getVersion(new NullProgressMonitor());

        // String version can be null for old models or models not created with
        // the tool.
        Version loadedVersion = Version.parseVersion(sLoadedVersion);
        TestCase.assertNotNull("The parsed version is null, check the file: " + fileURI.toPlatformString(true), loadedVersion);

        // Check that the migration service detect if the migration is needed.
        boolean migrationIsNeeded = migrationService.isMigrationNeeded(loadedVersion);

        if (needsMigration) {
            TestCase.assertTrue("The current test case checks a migration behavior, please revert the manual migration on : " + fileURI.toPlatformString(true), migrationIsNeeded);
        } else {
            TestCase.assertFalse("The current test case expect a file which does not need migration : " + fileURI.toPlatformString(true), migrationIsNeeded);
        }
        return loadedVersion;
    }
}
