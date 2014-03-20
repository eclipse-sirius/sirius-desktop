/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
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
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection.Callback;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.junit.Assert;
import org.osgi.framework.Version;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * The main test case for viewpoint unit tests.
 * 
 * @author mchauvin
 */
public abstract class SiriusTestCase extends TestCase {
    /** Initialization error message. */
    public static final String INIT_ERROR_MSG = "An error occurs during tests initialization";

    /**
     * name of the project created in the test workspace.
     */
    protected static final String TEMPORARY_PROJECT_NAME = "DesignerTestProject";

    /**
     * The default session URI used when there is no session path passed to the
     * generic setup.
     */
    protected static final URI DEFAULT_MODELING_PROJECT_REPRESENTATIONS_FILE_URI = URI.createPlatformResourceURI(File.separator + SiriusTestCase.TEMPORARY_PROJECT_NAME + File.separator
            + ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME, true);

    private static final String DOT = ".";

    /**
     * The local session.
     */
    protected Session session;

    /**
     * The model request interpreter.
     */
    protected IInterpreter interpreter;

    /**
     * The model accessor.
     */
    protected ModelAccessor accessor;

    /**
     * Semantic model.
     */
    protected EObject semanticModel;

    /**
     * Indicates if the workspace project created during the setup should be a
     * modeling one.
     */
    protected boolean createModelingProject;

    /**
     * Registered viewpoints.
     */
    protected final Set<Viewpoint> viewpoints = new HashSet<Viewpoint>();

    /**
     * The viewpoint selection callback to use.
     */
    protected Callback selectionCallback = new ViewpointSelectionCallback();

    /**
     * The reported errors.
     */
    protected final LinkedHashMultimap<String, IStatus> errors = LinkedHashMultimap.create();

    /**
     * A default progress monitor test code can use when one is needed.
     */
    protected IProgressMonitor defaultProgress = new NullProgressMonitor();

    /**
     * The unchaught exceptions handler.
     */
    private UncaughtExceptionHandler exceptionHandler;

    /**
     * The platform error listener.
     */
    private ILogListener logListener;

    private boolean errorCatchActive;

    /**
     * HashMaps to store the initial values of preferences before changes.
     */
    private final HashMap<String, Object> oldValueDiagramPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldValueSiriusUIPreferences = new HashMap<String, Object>();

    private final HashMap<String, Object> oldPlatformUIPreferences = new HashMap<String, Object>();

    /**
     * Overridden to create the project.
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // CHECKSTYLE:OFF
        System.out.println("Setup of " + this.getClass().getName() + SiriusTestCase.DOT + getName() + "()");
        // CHECKSTYLE:ON
        setErrorCatchActive(true);
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
     *            the modeler description path (PlatformPlugin or
     *            PlatformResource)
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
     *            the modeler description paths (PlatformPlugin or
     *            PlatformResource)
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
     *            the modeler description path (PlatformPlugin or
     *            PlatformResource)
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
     *            the modeler description paths (PlatformPlugin or
     *            PlatformResource)
     * @param representationsModelPath
     *            the aird path
     * @throws Exception
     *             any exception
     */
    protected void genericSetUp(final Collection<String> semanticModelPaths, final Collection<String> modelerDescriptionPaths, final String representationsModelPath) throws Exception {

        final List<URI> semanticModelUris = Lists.newArrayList();
        for (final String semanticModelPath : semanticModelPaths) {
            semanticModelUris.add(toURI(semanticModelPath));
        }

        final List<URI> modelerDescUris = Lists.newArrayList();
        for (final String modelerDescriptionPath : modelerDescriptionPaths) {
            modelerDescUris.add(toURI(modelerDescriptionPath));
        }
        genericSetUp(semanticModelUris, modelerDescUris, true, toURI(representationsModelPath));
    }

    private URI toURI(final String path) {
        if (path != null) {
            URI uri;
            /*
             * if path starts with the temporary project name, then we have a
             * local resource uri
             */
            if (path.startsWith(SiriusTestCase.TEMPORARY_PROJECT_NAME)) {
                uri = URI.createPlatformResourceURI('/' + path, true);
            } else if (path.startsWith('/' + SiriusTestCase.TEMPORARY_PROJECT_NAME)) {
                uri = URI.createPlatformResourceURI(path, true);
            } else {
                uri = URI.createPlatformPluginURI(path, true);
            }
            return uri;
        }
        return null;
    }

    private void genericSetUp(final List<URI> semanticResourceURIs, final List<URI> modelerResourceURIs, boolean createSession, final URI sessionResourceURI) throws Exception {
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

        // Set the auto-refresh to false because it's historically the default
        // value
        DefaultScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        InstanceScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        IDiagramCommandFactory commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        commandFactory.setUserInterfaceCallBack(new NoUICallback());

        closeWelcomePage();

        TestsUtil.emptyEventsFromUIThread();

        /* Initialize error log and uncaught exception handlers */
        initErrorLoggers();
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
     * The default session URI used when there is no session path passed to the
     * generic setup. The name of the aird used the name of the test case to
     * easily debug.
     * 
     * @return default session URI.
     */
    protected URI getDefaultRepresentationsFileURI() {

        return URI.createPlatformResourceURI(
                File.separator + SiriusTestCase.TEMPORARY_PROJECT_NAME + File.separator + this.getClass().getSimpleName() + "_" + getName() + SiriusTestCase.DOT + ".aird", true);
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

    private void loadModeler(final URI modelerResourceURI, EditingDomain domain) throws Exception {
        Group group = null;
        try {
            group = (Group) ModelUtils.load(modelerResourceURI, domain.getResourceSet());

        } catch (final IOException exception) {
            /*
             * if an IOException occurs here, its probably because we try to
             * create a plaftorm plugin URI and it was a local one
             */
            String uri = modelerResourceURI.toString();
            if (uri.startsWith("platform:/plugin/")) {
                URI alternativeURI = URI.createPlatformResourceURI(uri.substring(17), true);
                group = (Group) ModelUtils.load(alternativeURI, domain.getResourceSet());
            } else {
                Assert.fail(exception.getMessage());
            }
        }
        if (group != null) {
            viewpoints.addAll(group.getOwnedViewpoints());
        }
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
     * Get a {@link ICommandFactory}. To override to return a custom
     * {@link ICommandFactory}.
     * 
     * @return a custom {@link ICommandFactory}
     */
    protected abstract ICommandFactory getCommandFactory();

    /**
     * Close the welcomePage.
     */
    protected void closeWelcomePage() {
        IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        if (activePart != null && "Welcome".equals(activePart.getTitle()) && activePart instanceof IViewPart) {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView((IViewPart) activePart);
        }
        TestsUtil.synchronizationWithUIThread();
    }

    private void initErrorLoggers() {

        logListener = new ILogListener() {

            @Override
            public void logging(IStatus status, String plugin) {
                if (status.getSeverity() == IStatus.ERROR) {
                    errorOccurs(status, plugin);
                }
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

        // setErrorCatchActive(true);
    }

    private void disposeErrorLoggers() {
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
        if (errors != null) {
            return errors.values().size() != 0;
        }
        return false;
    }

    /**
     * Clear the list of errors. Can be useful when some messages are expected.
     */
    protected synchronized void clearErrors() {
        errors.clear();
    }

    private synchronized void errorOccurs(IStatus status, String sourcePlugin) {
        if (errorCatchActive) {
            errors.put(sourcePlugin, status);
        }
    }

    protected synchronized void setErrorCatchActive(boolean errorCatchActive) {
        this.errorCatchActive = errorCatchActive;
    }

    protected synchronized boolean isErrorCatchActive() {
        return errorCatchActive;
    }

    private void checkErrors() {
        /* an exception occurs in another thread */

        /*
         * TODO: skip checkErrors when we are in a shouldSkipUnreliableTests
         * mode. We have some unwanted resource notifications during the
         * teardown on jenkins.
         */
        if (!TestsUtil.shouldSkipUnreliableTests() && doesAnErrorOccurs()) {
            Assert.fail(getErrorLoggersMessage());
        }
    }

    /**
     * Compute an error message from the detected errors.
     * 
     * @return the error message.
     */
    protected synchronized String getErrorLoggersMessage() {
        StringBuilder log1 = new StringBuilder();
        String br = "\n";

        String testName = getClass().getName();

        log1.append("Error(s) raised during test : " + testName).append(br);
        for (Entry<String, Collection<IStatus>> entry : errors.asMap().entrySet()) {
            String reporter = entry.getKey();
            log1.append(". Log Plugin : " + reporter).append(br);

            for (IStatus status : entry.getValue()) {
                log1.append("  . " + getSeverity(status) + " from plugin:" + status.getPlugin() + ", message: " + status.getMessage() + ", exception: " + status.getException()).append(br);
                appendStackTrace(log1, br, status);
            }
            log1.append(br);
        }
        return log1.toString();
    }

    /**
     * Convert the <code>status</code> exception in String and add it at the end
     * of the <code>stringBuilder</code>. Add the
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
    }

    /**
     * Initialize a viewpoint (and force the initialization of the description
     * to true).
     * 
     * @param viewpointName
     *            the name of the viewpoint to initialize.
     */
    protected final void initViewpoint(final String viewpointName) {
        initSirius(viewpointName, session, semanticModel);
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
     */
    protected final void initSirius(final String viewpointName, final Session alternateSession, final EObject alternateSemanticModel) {
        Viewpoint localSessionSirius = null;
        for (final Viewpoint viewpoint : viewpoints) {
            if (viewpointName != null && viewpointName.equals(viewpoint.getName())) {
                localSessionSirius = getViewpointFromName(viewpointName, alternateSession);
                break;
            }
        }
        if (localSessionSirius != null) {
            Command changeSiriussSelectionCmd = new ChangeViewpointSelectionCommand(alternateSession, new ViewpointSelectionCallback(), Collections.singleton(localSessionSirius),
                    Collections.<Viewpoint> emptySet(), new NullProgressMonitor());
            alternateSession.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelectionCmd);
        }
    }

    /**
     * Activate a viewpoint.
     * 
     * @param name
     *            the viewpoint name to activate
     */
    protected final void activateViewpoint(final String name) {
        boolean activatedSirius = false;
        for (final Viewpoint viewpoint : viewpoints) {
            if (name.equals(viewpoint.getName())) {
                Viewpoint viewpointFromName = getViewpointFromName(name, session);
                Command changeSiriussSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.singleton(viewpointFromName), Collections.<Viewpoint> emptySet(),
                        new NullProgressMonitor());
                session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelection);
                activatedSirius = true;
                break;
            }
        }
        if (!activatedSirius) {
            for (final Viewpoint viewpoint : ViewpointRegistry.getInstance().getViewpoints()) {
                if (name.equals(viewpoint.getName())) {
                    Viewpoint viewpointFromName = getViewpointFromName(name, session);
                    if (viewpointFromName == null) {
                        viewpoints.add(viewpoint);
                        viewpointFromName = getViewpointFromName(name, session);
                    }
                    Command changeSiriussSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.singleton(viewpointFromName), Collections.<Viewpoint> emptySet(),
                            new NullProgressMonitor());
                    session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelection);
                    activatedSirius = true;
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
    protected final void deactivateSirius(final String name) {
        boolean deactivatedSirius = false;
        for (final Viewpoint viewpoint : viewpoints) {
            if (name.equals(viewpoint.getName())) {
                Viewpoint viewpointFromName = getViewpointFromName(name, session);
                Command changeSiriussSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.<Viewpoint> emptySet(), Collections.singleton(viewpointFromName),
                        new NullProgressMonitor());
                session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelection);
                deactivatedSirius = true;
                break;
            }
        }
        if (!deactivatedSirius) {
            for (final Viewpoint viewpoint : ViewpointRegistry.getInstance().getViewpoints()) {
                if (name.equals(viewpoint.getName())) {
                    Viewpoint viewpointFromName = getViewpointFromName(name, session);
                    Command changeSiriussSelection = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.<Viewpoint> emptySet(), Collections.singleton(viewpointFromName),
                            new NullProgressMonitor());
                    session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelection);
                    deactivatedSirius = true;
                    break;
                }
            }
        }
    }

    /**
     * Create a new representation from the contextual semanticModel & the
     * contextual Session.
     * 
     * @param representationDescriptionName
     *            the representation description name
     * @return the created representation, or <code>null</code> if the
     *         representation description could not be found
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
     * @return the created representation, or <code>null</code> if the
     *         representation description could not be found
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
     * @return the created representation, or <code>null</code> if the
     *         representation description could not be found
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
     * @return the created representation, or <code>null</code> if the
     *         representation description could not be found
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
     * @return the created representation, or <code>null</code> if the
     *         representation description could not be found
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
                        Viewpoint localSirius = getViewpointFromName(viewpointOfRegistry.getName());
                        RepresentationDescription localDescription = getLocalSessionRepresentationDescription(localSirius, representationDescriptionName);
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
        final Collection<?> result = cmd.getResult();
        return result != null ? (DRepresentation) result.toArray()[0] : null;
    }

    /**
     * Get a {@link RepresentationDescription} ref from the current session
     * ResourceSet.
     * 
     * @param localSirius
     *            the Sirius local to the current session ResourceSet containing
     *            the {@link RepresentationDescription} to get
     * 
     * @param representationDescriptionName
     *            the name of the {@link RepresentationDescription} to get
     * 
     * @return the {@link RepresentationDescription} to get
     */
    private RepresentationDescription getLocalSessionRepresentationDescription(Viewpoint localSirius, String representationDescriptionName) {
        Iterable<RepresentationDescription> candidates = new ViewpointQuery(localSirius).getAllRepresentationDescriptions();
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
     *            the Session from which ResourceSet to return the Sirius
     * 
     * @return the first {@link Viewpoint} of the viewpoints Set, return a
     *         logical Sirius from the session's ResourceSet and not from the
     *         SiriusRegistry's ResourceSet
     */
    public Viewpoint getViewpointFromName(String viewpointName, Session sessionToUse) {
        Viewpoint localViewpoint = null;
        for (Viewpoint viewpoint : viewpoints) {
            if (viewpoint.getName() != null && viewpoint.getName().equals(viewpointName)) {
                URI viewpointResourceURI = viewpoint.eResource().getURI();
                Resource newSiriusResource = sessionToUse.getTransactionalEditingDomain().getResourceSet().getResource(viewpointResourceURI, true);
                if (!newSiriusResource.getContents().isEmpty() && newSiriusResource.getContents().get(0) instanceof Group) {
                    Group group = (Group) newSiriusResource.getContents().get(0);
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
     * Get a {@link RepresentationDescription} named
     * <code>representationDescriptionName</code> owned by
     * <code>viewpoint</code>.
     * 
     * @param representationDescriptionName
     *            the name of the {@link RepresentationDescription} to search
     * 
     * @param viewpoint
     *            the {@link Viewpoint} in which to search
     * 
     * @return the found {@link RepresentationDescription} or null if nothing
     *         found
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
     * Get all the representation with the given representation description
     * name.
     * 
     * @param representationDescriptionName
     *            the name of the representation description. <code>null</code>
     *            is not excepted.
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
     * Get all the representation with the given representation description name
     * in the given session.
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
     * Get all the representation with the given representation description name
     * in the given session.
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
     * Get all the representation with the given representation description
     * name.
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
     * Refresh a representation.
     * 
     * @param representation
     *            the representation to refresh.
     */
    protected void refresh(final DRepresentation representation) {
        RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(representation);
        RefreshRepresentationsCommand command = new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), new NullProgressMonitor(), representation);
        command.setLabel("refresh from testcase");
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
    }

    /**
     * Get the representation element from the semantic one.
     * 
     * @param representation
     *            the representation
     * @param semanticElement
     *            the semantic element
     * @return the first representation element which has as target the semantic
     *         element given as parameter
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
     * @return the first representation element which has as target the semantic
     *         element given as parameter
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
     * Get all representation elements which target a given semantic element
     * from a representation.
     * 
     * @param representation
     *            the representation
     * @param semanticElement
     *            the target semantic element
     * @return all the graphical elements with the given semantic element as
     *         target
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
     * @return <code>true</code> if the execution succeed, <code>false</code>
     *         otherwise
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
        for (final IProject proj : Lists.newArrayList(ResourcesPlugin.getWorkspace().getRoot().getProjects())) {
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
     * Change a preference and store the old value. It will be automatically
     * reset during tear down.
     * 
     * TO CALL ONLY ONCE PER TEST (set up + test)
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramPreference(String preferenceKey, Integer newValue) {
        final IPreferenceStore prefs = DiagramPlugin.getInstance().getPreferenceStore();
        oldValueDiagramPreferences.put(preferenceKey, prefs.getInt(preferenceKey));
        prefs.setValue(preferenceKey, newValue);
    }

    /**
     * Change a boolean preference and store the old value. It will be
     * automatically reset during tear down.
     * 
     * TO CALL ONLY ONCE PER TEST (set up + test)
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeDiagramPreference(String preferenceKey, Boolean newValue) {
        final IPreferenceStore prefs = DiagramPlugin.getInstance().getPreferenceStore();
        oldValueDiagramPreferences.put(preferenceKey, prefs.getBoolean(preferenceKey));
        prefs.setValue(preferenceKey, newValue);
    }

    /**
     * Restore this preference to its initial value. Should be called after
     * {@link #changeDiagramPreference(String, Boolean)} of
     * {@link #changeDiagramPreference(String, Integer)} to have effect.
     * 
     * @param preferenceKey
     *            The key of the preference.
     */
    protected void resetDiagramPreference(String preferenceKey) {
        final IPreferenceStore prefs = DiagramPlugin.getInstance().getPreferenceStore();
        for (String key : oldValueDiagramPreferences.keySet()) {
            if (key.equals(preferenceKey)) {
                if (oldValueDiagramPreferences.get(key) instanceof Boolean) {
                    prefs.setValue(key, (Boolean) oldValueDiagramPreferences.get(key));
                } else if (oldValueDiagramPreferences.get(key) instanceof Integer) {
                    prefs.setValue(key, (Integer) oldValueDiagramPreferences.get(key));
                }
            }
        }
    }

    /**
     * Change a boolean preference and store the old value to reset it after the
     * test.
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
     * Change a boolean preference and store the old value. It will be
     * automatically reset during tear down.
     * 
     * TO CALL ONLY ONCE PER TEST (set up + test)
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusPreference(String preferenceKey, Boolean newValue) {
        boolean oldValue = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);
        oldValueSiriusPreferences.put(preferenceKey, oldValue);

        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        corePreferences.putBoolean(preferenceKey, newValue);

        String message = "The " + preferenceKey + " preference value was not changed for plugin " + SiriusPlugin.ID;
        boolean valueToCheck = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, preferenceKey, false, null);
        TestCase.assertEquals(message, newValue.booleanValue(), valueToCheck);
    }

    /**
     * Change a boolean preference and store the old value. It will be
     * automatically reset during tear down.
     * 
     * TO CALL ONLY ONCE PER TEST (set up + test)
     * 
     * @param preferenceKey
     *            The key of the preference.
     * @param newValue
     *            The new value.
     */
    protected void changeSiriusUIPreference(String preferenceKey, Boolean newValue) {
        Collection<SiriusPreferencesKeys> coreValues = Lists.newArrayList(SiriusPreferencesKeys.values());
        Function<SiriusPreferencesKeys, String> prefToName = new Function<SiriusPreferencesKeys, String>() {
            @Override
            public String apply(SiriusPreferencesKeys input) {
                return input.name();
            }
        };
        TestCase.assertFalse("The DesignerPreferenceKey named " + preferenceKey + " should not be modified in the UI store.",
                Lists.newArrayList(Iterables.transform(coreValues, prefToName)).contains(preferenceKey));

        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        oldValueSiriusUIPreferences.put(preferenceKey, viewpointUIPrefs.getBoolean(preferenceKey));
        viewpointUIPrefs.setValue(preferenceKey, newValue);
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
                        System.err.println(e.getMessage());
                    }
                    // CHECKSTYLE:ON
                }
                TestsUtil.synchronizationWithUIThread();
            }
            session = null;
        }
    }

    private void closeAllSessions() {
        for (final Session managerSession : Lists.newArrayList(SessionManager.INSTANCE.getSessions())) {
            if (managerSession.isOpen()) {
                managerSession.close(new NullProgressMonitor());
                TestsUtil.synchronizationWithUIThread();
            }
        }
    }

    /**
     * This will undo the last Command if possible and return <code>True</code>
     * if it is, <code>False</code> otherwise.
     * 
     * @return <code>True</code> if the command was undoable, <code>False</code>
     *         otherwise.
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
     * This will redo the last undone Command if possible and return
     * <code>True</code> if it is, <code>False</code> otherwise.
     * 
     * @return <code>True</code> if the command was undoable, <code>False</code>
     *         otherwise.
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
        createModelingProject = false;
        setErrorCatchActive(false);

        TransactionalEditingDomain domain = null;

        /* close the session */
        if (session != null) {
            domain = session.getTransactionalEditingDomain();
            cleanCurrentSession();
        }

        closeAllSessions();
        // Add a emptyEventsFromUIThread here to wait the closing of all editors
        // before disposing the editing domain.
        TestsUtil.emptyEventsFromUIThread();

        if (domain != null) {
            LinkedHashSet<Group> groups = Sets.<Group> newLinkedHashSet();

            for (Viewpoint vp : viewpoints) {
                if (vp.eContainer() instanceof Group) {
                    groups.add((Group) vp.eContainer());
                }
            }

            for (final Resource resource : Lists.newArrayList(domain.getResourceSet().getResources())) {
                resource.unload();
            }

            for (final Group modelerModele : groups) {
                domain.getResourceSet().getResources().remove(modelerModele.eResource());
            }

            domain.getResourceSet().getResources().clear();
            if (domain.getCommandStack() != null) {
                domain.getCommandStack().flush();
            }
            /* dispose the editing domain */    /**
             * {@inheritDoc}
             */
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

        disposeErrorLoggers();
        TestsUtil.emptyEventsFromUIThread();
        // Reset the preferences changed during the test with the method
        // changePreference
        IPreferenceStore diagramPreferences = DiagramPlugin.getInstance().getPreferenceStore();
        for (String key : oldValueDiagramPreferences.keySet()) {
            if (oldValueDiagramPreferences.get(key) instanceof Boolean) {
                diagramPreferences.setValue(key, (Boolean) oldValueDiagramPreferences.get(key));
            } else if (oldValueDiagramPreferences.get(key) instanceof Integer) {
                diagramPreferences.setValue(key, (Integer) oldValueDiagramPreferences.get(key));
            }
        }
        IEclipsePreferences corePreferences = InstanceScope.INSTANCE.getNode(SiriusPlugin.ID);
        for (String key : oldValueSiriusPreferences.keySet()) {
            corePreferences.putBoolean(key, (Boolean) oldValueSiriusPreferences.get(key));
        }
        IPreferenceStore viewpointUIPrefs = SiriusEditPlugin.getPlugin().getPreferenceStore();
        for (String key : oldValueSiriusUIPreferences.keySet()) {
            viewpointUIPrefs.setValue(key, (Boolean) oldValueSiriusUIPreferences.get(key));
        }
        IPreferenceStore platformUIPrefs = PlatformUI.getPreferenceStore();
        for (String key : oldPlatformUIPreferences.keySet()) {
            platformUIPrefs.setValue(key, (Boolean) oldPlatformUIPreferences.get(key));
        }

        checkErrors();

        new TestCaseCleaner(this).clearAllFields();

        super.tearDown();
    }

    /**
     * Disable the specific dialect editor dialogs.
     * 
     * @param editor
     *            The editor on which disable the dialogs.
     */
    protected void disableUICallBackOnDialectEditor(DialectEditor editor) {
        editor.setDialogFactory(new DialectEditorDialogFactory() {

            @Override
            public void editorWillBeClosedInformationDialog(Shell parent) {
                // do nothing
            }

            @Override
            public void informUserOfEvent(int severity, String message) {
                // do nothing
            }
        });
    }

    /**
     * Find a viewpoint by name from the global registry.
     * 
     * @param name
     *            name of the Sirius to look for.
     * @return the first Sirius found in the registry with the specified name,
     *         if any. The instance returned is the one from the Sirius
     *         registry's editing domain.
     */
    protected Option<Viewpoint> findSirius(String name) {
        for (Viewpoint vp : ViewpointRegistry.getInstance().getViewpoints()) {
            if (vp.getName().equals(name)) {
                return Options.newSome(vp);
            }
        }
        return Options.newNone();
    }

    /**
     * Return all resource type ecore or aird in resource set passed in
     * parameter.
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
     * It can be used to verify that a file has not be migrated before the test.
     * And then it allows to check the effect of the migration in the other
     * test.
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
     * It can be used to verify that a file has not be migrated before the test.
     * And then it allows to check the effect of the migration in the other
     * test.
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
     * Check that the data were not migrated before the test. It allows to check
     * the effect of the migration in the other test.
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
