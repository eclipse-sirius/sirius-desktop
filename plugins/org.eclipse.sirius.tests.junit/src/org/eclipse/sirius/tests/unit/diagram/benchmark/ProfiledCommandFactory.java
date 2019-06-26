/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.benchmark;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirDestroyElementRequest;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import junit.framework.TestCase;

/**
 * This class has the responsibility to create the profiled commands of a
 * scenario.
 * 
 * @author ymortier
 */
public class ProfiledCommandFactory {
    /**
     * Avoiding too many opened editors at a given time prevents OutOfMemory
     * errors.
     */
    public static final int MAX_OPENED_EDITORS = 30;

    /**
     * Avoiding too many representation in the same aird to prevent OutOfMemory
     * errors.
     */
    public static final int MAX_REPRESENTATION_IN_SAME_AIRD = 1000;

    /**
     * Store the created aird resources.
     */
    private URI mainAirdUri = null;

    /** The current scenario. */
    private final BenchmarkScenario benchmarkScenario;

    /**
     * Keeps track of the representations that have been created in the current
     * session.
     */
    private final List<DRepresentation> representations = new ArrayList<DRepresentation>();

    /** Keeps a reference to the latest created analysis. */
    private DAnalysis analysis;

    /**
     * @param benchmarkScenario
     */
    public ProfiledCommandFactory(final BenchmarkScenario benchmarkScenario) {
        this.benchmarkScenario = benchmarkScenario;
    }

    private Session prepareAnalysisSession(final EObject model) {
        representations.clear();
        analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
        benchmarkScenario.attachToTemporaryResource(benchmarkScenario.getAIRDResource(), analysis);
        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (final IOException e) {
            TestCase.fail("Couldn't save analysis to file.");
        }
        Session session = null;
        try {
            session = SessionFactory.INSTANCE.createSession(analysis.eResource().getURI(), new NullProgressMonitor());
        } catch (CoreException e) {
            TestCase.fail("Problem during Session creation:" + e.getMessage());
        }
        session.open(new NullProgressMonitor());

        URI semanticResourceURI = model.eResource().getURI();
        Command addSemanticResourceCmd = new AddSemanticResourceCommand(session, semanticResourceURI, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(addSemanticResourceCmd);

        return session;
    }

    private int executeRefreshRepresentationsCommands(final Session session, final int maxOpenedEditors) {

        int openEditorCount = 0;

        for (final DRepresentation rep : representations) {
            executeRefreshRepresentationCommand(session, rep);
            openEditorCount++;
            /* This test is really lengthy. Stop at a given count */
            if (openEditorCount == maxOpenedEditors) {
                break;
            }
        }
        return openEditorCount;
    }

    private void executeRefreshRepresentationCommand(final Session session, final DRepresentation representation) {
        final AbstractCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                refreshRepresentation(session, representation);
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
    }

    private void refreshRepresentation(final Session session, final DRepresentation representation) {
        EditorManagementHelper helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
        helper.openEditor(session, representation);
        final SiriusDiagramEditor editor = (SiriusDiagramEditor) helper.getEditor();

        DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
        synchronizationWithUIThread();

        editor.getDiagramEditPart().deactivate();
        helper.closeEditor();
    }

    private void createRepresentations(final EObject model, final Viewpoint viewpoint, final Session session) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                for (final Object description : viewpoint.getOwnedRepresentations()) {
                    if (description instanceof DiagramDescription) {
                        final Iterator<EObject> it = model.eAllContents();
                        while (it.hasNext()) {
                            final EObject nextElement = it.next();
                            if (DialectManager.INSTANCE.canCreate(nextElement, (DiagramDescription) description)) {
                                final DRepresentation representation = DialectManager.INSTANCE.createRepresentation("Diagram for " + nextElement.toString(), nextElement,
                                        (DiagramDescription) description,
                                        session, new NullProgressMonitor());
                                TestCase.assertNotNull("The representation hasn't been created.", representation);
                                TestCase.assertNotNull("Representation hasn't been added to its resource.", representation.eResource());
                                representations.add(representation);
                            }
                        }
                    }
                }
            }
        });
    }

    public AbstractProfiledCommand createRefreshRepresentationsBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledRefreshCommand = new AbstractProfiledCommandWithUnit("") {
            private Session session;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);
                setName("Refreshing " + MAX_OPENED_EDITORS + " DRepresentations");
            }

            @Override
            public void postTest() {
                super.postTest();
                session.save(new NullProgressMonitor());
                session.close(new NullProgressMonitor());
            }

            @Override
            protected void doTest() {
                int openEditorCount = 0;
                for (final DRepresentation representation : representations) {
                    final AbstractProfiledCommandUnit unit = new AbstractProfiledCommandUnit() {
                        @Override
                        protected void doTest() {
                            executeRefreshRepresentationCommand(session, representation);
                        }
                    };
                    this.doUnit(unit);
                    openEditorCount++;
                    // This test is really lengthy. Stop at a given
                    // count
                    if (openEditorCount == MAX_OPENED_EDITORS) {
                        break;
                    }
                }
            }

        };
        return profiledRefreshCommand;
    }

    public AbstractProfiledCommand createActivateFilterBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledActivateLayernCommand = new AbstractProfiledCommand("Container creation") {
            private Session session;

            private FilterDescription filterToActivate;

            private EditorManagementHelper helper;

            private SiriusDiagramEditor editor;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
                    final RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
                    if (description instanceof DiagramDescription) {
                        final DiagramDescription diagramDescription = (DiagramDescription) description;
                        if (!diagramDescription.getFilters().isEmpty()) {
                            final FilterDescription filterDescription = diagramDescription.getFilters().iterator().next();
                            this.filterToActivate = filterDescription;

                            helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                            helper.openEditor(session, representation);
                            editor = (SiriusDiagramEditor) helper.getEditor();
                            synchronizationWithUIThread();
                            break;
                        }
                    }
                }

            }

            @Override
            public void postTest() {
                super.postTest();
                synchronizationWithUIThread();
                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                filterToActivate = null;

            }

            @Override
            protected void doTest() {
                final FilterDescription filterDescription = filterToActivate;

                final DiagramEditPart diaEditPart = editor.getDiagramEditPart();
                final Object obj = diaEditPart.getModel();
                if (obj instanceof View) {

                    final EObject designerElement = ((View) obj).getElement();
                    final PaletteViewer paletteViewer = editor.getDiagramGraphicalViewer().getEditDomain().getPaletteViewer();

                    if (designerElement instanceof DDiagram && paletteViewer != null) {

                        final DDiagram diagram = (DDiagram) designerElement;

                        final List<FilterDescription> activatedFilters = diagram.getActivatedFilters();

                        final DCommand command = new SiriusCommand(session.getTransactionalEditingDomain(), null);
                        command.getTasks().add(new AbstractCommandTask() {

                            /**
                             * {@inheritDoc}
                             * 
                             * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
                             */
                            @Override
                            public void execute() {
                                handleActivation();
                            }

                            private void handleActivation() {
                                activatedFilters.add(filterDescription);
                                NotificationUtil.sendNotification(diagram, org.eclipse.sirius.common.tools.api.listener.Notification.Kind.START,
                                        org.eclipse.sirius.common.tools.api.listener.Notification.VISIBILITY_UPDATE);
                            }

                            /**
                             * {@inheritDoc}
                             * 
                             * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
                             */
                            @Override
                            public String getLabel() {
                                return "hide or show filter";
                            }

                        });
                        SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget()).getRefreshEditorsListener().setForceRefresh(true);
                        command.setLabel("Activate " + filterDescription.getName() + " filter");
                        executeCommand(editor, command, session.getTransactionalEditingDomain());
                        synchronizationWithUIThread();
                    }
                }
            }
        };
        return profiledActivateLayernCommand;
    }

    public AbstractProfiledCommand createActivateLayerBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledActivateLayernCommand = new AbstractProfiledCommand("Container creation") {
            private Session session;

            private Layer layerToActivate;

            private SiriusDiagramEditor editor;

            private EditorManagementHelper helper;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                for (final DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
                    final RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
                    if (description instanceof DiagramDescription) {
                        final DiagramDescription diagramDescription = (DiagramDescription) description;
                        if (diagramDescription.getDefaultLayer() != null && diagramDescription.getAdditionalLayers().size() > 0) {
                            final Layer layer = diagramDescription.getAdditionalLayers().iterator().next();
                            final DDiagram diagram = (DDiagram) representation;
                            // Take a nominal diagram.
                            if (diagram.getNodes().size() > 100 || diagram.getEdges().size() > 100 || diagram.getContainers().size() > 100) {
                                continue;
                            }
                            if (!diagram.getActivatedLayers().contains(layer)) {
                                this.layerToActivate = layer;
                                helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                                helper.openEditor(session, representation);
                                editor = (SiriusDiagramEditor) helper.getEditor();
                                synchronizationWithUIThread();
                                break;
                            }
                        }
                    }
                }

            }

            @Override
            public void postTest() {
                super.postTest();
                synchronizationWithUIThread();
                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                layerToActivate = null;

            }

            @Override
            protected void doTest() {
                final Layer layer = this.layerToActivate;

                final DiagramEditPart diaEditPart = this.editor.getDiagramEditPart();
                final Object obj = diaEditPart.getModel();
                if (obj instanceof View) {

                    final EObject designerElement = ((View) obj).getElement();
                    final PaletteViewer paletteViewer = editor.getDiagramGraphicalViewer().getEditDomain().getPaletteViewer();

                    if (designerElement instanceof DDiagram && paletteViewer != null) {
                        final List<Layer> activatedLayers = ((DDiagram) designerElement).getActivatedLayers();

                        final DCommand command = new SiriusCommand(session.getTransactionalEditingDomain(), null);
                        command.getTasks().add(new AbstractCommandTask() {

                            /**
                             * {@inheritDoc}
                             * 
                             * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
                             */
                            @Override
                            public void execute() {
                                activatedLayers.add(layer);
                            }

                            /**
                             * {@inheritDoc}
                             * 
                             * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
                             */
                            @Override
                            public String getLabel() {
                                return "hide or show layer";
                            }

                        });
                        // command.getTasks().add(new
                        // RefreshDElementTask((DDiagram) designerElement));
                        SessionManager.INSTANCE.getSession(((DSemanticDiagram) designerElement).getTarget()).getRefreshEditorsListener().setForceRefresh(true);

                        command.getTasks().add(new AbstractCommandTask() {

                            /**
                             * {@inheritDoc}
                             */
                            @Override
                            public void execute() {
                                DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility((DDiagram) designerElement);
                            }

                            /**
                             * {@inheritDoc}
                             */
                            @Override
                            public String getLabel() {
                                return "Refresh visibility";
                            }
                        });

                        command.setLabel("Show " + new IdentifiedElementQuery(layerToActivate).getLabel() + " layer");
                        executeCommand(editor, command, session.getTransactionalEditingDomain());
                        synchronizationWithUIThread();
                    }
                }
            }
        };
        return profiledActivateLayernCommand;
    }

    public AbstractProfiledCommand createCloseSessionBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledSessionCloseCommand = new AbstractProfiledCommand("Closing session") {
            private Session session;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);
            }

            @Override
            protected void doTest() {
                session.close(new NullProgressMonitor());
            }
        };
        return profiledSessionCloseCommand;
    }

    public AbstractProfiledCommand createContainerCreationBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledContainerCreationCommand = new AbstractProfiledCommand("Container creation") {
            private Session session;

            private DummyCreationTool creationTool;

            private SiriusDiagramEditor editor;

            private EditorManagementHelper helper;

            @Override
            public void preTest() {
                super.preTest();
                SiriusEditPlugin.getPlugin().getPreferenceStore().setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                final DRepresentation representation = representations.get(0);
                session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
                    }
                });
                helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                helper.openEditor(session, representation);
                editor = (SiriusDiagramEditor) helper.getEditor();
                synchronizationWithUIThread();

                final DiagramDescription description = ((DSemanticDiagram) representation).getDescription();
                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof ContainerCreationDescription) {
                        final ContainerCreationDescription containerCreation = (ContainerCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(editor.getDiagramEditPart().getViewer());
                        creationTool.setTargetEditPart(editor.getDiagramEditPart());
                        break;
                    }
                }
            }

            @Override
            public void postTest() {
                super.postTest();
                synchronizationWithUIThread();
                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                creationTool = null;

            }

            @Override
            protected void doTest() {
                final List<EObject> oldElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                final Request request = creationTool.getTargetRequest();
                final org.eclipse.gef.commands.Command command = editor.getDiagramEditPart().getCommand(request);
                executeCommand(editor, command);
                final List<EObject> newElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                newElements.removeAll(oldElements);
                TestCase.assertEquals(1, newElements.size());
            }
        };
        return profiledContainerCreationCommand;
    }

    public AbstractProfiledCommand createContainerCreationAutoRefreshBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledContainerCreationCommand = new AbstractProfiledCommand("Container creation") {
            private Session session;

            private DummyCreationTool creationTool;

            private SiriusDiagramEditor editor;

            private EditorManagementHelper helper;

            @Override
            public void preTest() {
                super.preTest();
                InstanceScope.INSTANCE.getNode(SiriusPlugin.ID).putBoolean(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
                SiriusEditPlugin.getPlugin().getPreferenceStore().setValue(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                final DRepresentation representation = representations.get(0);
                session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
                    }
                });
                helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                helper.openEditor(session, representation);
                editor = (SiriusDiagramEditor) helper.getEditor();

                synchronizationWithUIThread();

                final DiagramDescription description = ((DSemanticDiagram) representation).getDescription();
                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof ContainerCreationDescription) {
                        final ContainerCreationDescription containerCreation = (ContainerCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(editor.getDiagramEditPart().getViewer());
                        creationTool.setTargetEditPart(editor.getDiagramEditPart());
                        break;
                    }
                }
            }

            @Override
            public void postTest() {
                super.postTest();
                synchronizationWithUIThread();
                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                creationTool = null;

                SiriusPlugin.getDefault().getPluginPreferences().setValue(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
            }

            @Override
            protected void doTest() {
                final List<EObject> oldElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                final Request request = creationTool.getTargetRequest();
                final org.eclipse.gef.commands.Command command = editor.getDiagramEditPart().getCommand(request);
                executeCommand(editor, command);
                final List<EObject> newElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                newElements.removeAll(oldElements);
                TestCase.assertEquals(1, newElements.size());
            }
        };
        return profiledContainerCreationCommand;
    }

    public AbstractProfiledCommand createContainerDeletionBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledContainerDeletionCommand = new AbstractProfiledCommand("Container deletion") {
            private Session session;

            private DummyCreationTool creationTool;

            private IGraphicalEditPart createdElementEditPart;

            private SiriusDiagramEditor editor;

            private EditorManagementHelper helper;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                final DRepresentation representation = representations.get(0);
                session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
                    }
                });
                helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                helper.openEditor(session, representation);
                editor = (SiriusDiagramEditor) helper.getEditor();

                synchronizationWithUIThread();

                final DiagramDescription description = ((DSemanticDiagram) representation).getDescription();
                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof ContainerCreationDescription) {
                        final ContainerCreationDescription containerCreation = (ContainerCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(editor.getDiagramEditPart().getViewer());
                        creationTool.setTargetEditPart(editor.getDiagramEditPart());
                        break;
                    }
                }

                final List<EObject> oldElement = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());

                final Request request = creationTool.getTargetRequest();
                final org.eclipse.gef.commands.Command command = editor.getDiagramEditPart().getCommand(request);
                executeCommand(editor, command);
                final List<EObject> newElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                newElements.removeAll(oldElement);

                createdElementEditPart = (IGraphicalEditPart) newElements.get(0);
            }

            @Override
            public void postTest() {
                super.postTest();

                synchronizationWithUIThread();

                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                creationTool = null;

            }

            @Override
            protected void doTest() {
                final List<EObject> oldElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                final Request request = new EditCommandRequestWrapper(new AirDestroyElementRequest(session.getTransactionalEditingDomain(), false, true));
                final org.eclipse.gef.commands.Command command = createdElementEditPart.getCommand(request);
                executeCommand(editor, command);
                final List<EObject> newElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                oldElements.removeAll(newElements);
                TestCase.assertEquals(1, oldElements.size());
            }
        };
        return profiledContainerDeletionCommand;
    }

    public AbstractProfiledCommand createNodeCreationBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledNodeCreationCommand = new AbstractProfiledCommand("Node creation") {
            private Session session;

            private DummyCreationTool creationTool;

            private IGraphicalEditPart createdElementEditPart;

            private SiriusDiagramEditor editor;

            private EditorManagementHelper helper;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                final DRepresentation representation = representations.get(0);
                session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
                    }
                });
                helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                helper.openEditor(session, representation);
                editor = (SiriusDiagramEditor) helper.getEditor();

                synchronizationWithUIThread();

                final DiagramDescription description = ((DSemanticDiagram) representation).getDescription();

                // We need a container to create a node. Create it now.
                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof ContainerCreationDescription) {
                        final ContainerCreationDescription containerCreation = (ContainerCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(editor.getDiagramEditPart().getViewer());
                        creationTool.setTargetEditPart(editor.getDiagramEditPart());
                        break;
                    }
                }

                final List<EObject> oldElement = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());

                final Request request = creationTool.getTargetRequest();
                final org.eclipse.gef.commands.Command command = editor.getDiagramEditPart().getCommand(request);
                executeCommand(editor, command);

                final List<EObject> newElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                newElements.removeAll(oldElement);

                createdElementEditPart = (IGraphicalEditPart) newElements.get(0);

                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof NodeCreationDescription) {
                        final NodeCreationDescription containerCreation = (NodeCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(createdElementEditPart.getViewer());
                        creationTool.setTargetEditPart(createdElementEditPart);
                        break;
                    }
                }
            }

            @Override
            public void postTest() {
                super.postTest();
                synchronizationWithUIThread();
                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                creationTool = null;

            }

            @Override
            protected void doTest() {
                final List<EObject> oldElement = new ArrayList<EObject>(((IGraphicalEditPart) createdElementEditPart.getChildren().get(1)).getChildren());
                final Request request = creationTool.getTargetRequest();
                final org.eclipse.gef.commands.Command command = createdElementEditPart.getCommand(request);
                createdElementEditPart.getViewer().getEditDomain().getCommandStack().execute(command);
                final List<EObject> newElements = new ArrayList<EObject>(((IGraphicalEditPart) createdElementEditPart.getChildren().get(1)).getChildren());
                newElements.removeAll(oldElement);
                TestCase.assertEquals(1, newElements.size());
            }
        };
        return profiledNodeCreationCommand;
    }

    public AbstractProfiledCommand createNodeCreationAutoRefreshBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledNodeCreationCommand = new AbstractProfiledCommand("Node creation") {
            private Session session;

            private DummyCreationTool creationTool;

            private IGraphicalEditPart createdElementEditPart;

            private SiriusDiagramEditor editor;

            private EditorManagementHelper helper;

            @Override
            public void preTest() {
                super.preTest();
                SiriusPlugin.getDefault().getPluginPreferences().setValue(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                final DRepresentation representation = representations.get(0);
                session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        DialectManager.INSTANCE.refresh(representation, new NullProgressMonitor());
                    }
                });
                helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                helper.openEditor(session, representation);
                editor = (SiriusDiagramEditor) helper.getEditor();
                final DiagramDescription description = ((DSemanticDiagram) representation).getDescription();

                // We need a container to create a node. Create it now.
                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof ContainerCreationDescription) {
                        final ContainerCreationDescription containerCreation = (ContainerCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(editor.getDiagramEditPart().getViewer());
                        creationTool.setTargetEditPart(editor.getDiagramEditPart());
                        break;
                    }
                }

                final List<EObject> oldElement = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());

                final Request request = creationTool.getTargetRequest();
                final org.eclipse.gef.commands.Command command = editor.getDiagramEditPart().getCommand(request);
                executeCommand(editor, command);

                final List<EObject> newElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                newElements.removeAll(oldElement);

                createdElementEditPart = (IGraphicalEditPart) newElements.get(0);

                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof NodeCreationDescription) {
                        final NodeCreationDescription containerCreation = (NodeCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(createdElementEditPart.getViewer());
                        creationTool.setTargetEditPart(createdElementEditPart);
                        break;
                    }
                }
            }

            @Override
            public void postTest() {
                super.postTest();
                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                creationTool = null;

                SiriusPlugin.getDefault().getPluginPreferences().setValue(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
            }

            @Override
            protected void doTest() {
                final List<EObject> oldElement = new ArrayList<EObject>(((IGraphicalEditPart) createdElementEditPart.getChildren().get(1)).getChildren());
                final Request request = creationTool.getTargetRequest();
                final org.eclipse.gef.commands.Command command = createdElementEditPart.getCommand(request);
                createdElementEditPart.getViewer().getEditDomain().getCommandStack().execute(command);
                final List<EObject> newElements = new ArrayList<EObject>(((IGraphicalEditPart) createdElementEditPart.getChildren().get(1)).getChildren());
                newElements.removeAll(oldElement);
                TestCase.assertEquals(1, newElements.size());
            }
        };
        return profiledNodeCreationCommand;
    }

    public AbstractProfiledCommand createNodeDeletionBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledNodeDeletionCommand = new AbstractProfiledCommand("Node deletion") {
            private Session session;

            private DummyCreationTool creationTool;

            private IGraphicalEditPart createdElementEditPart;

            private SiriusDiagramEditor editor;

            private EditorManagementHelper helper;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);

                final DRepresentation representation = representations.get(0);
                helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
                helper.openEditor(session, representation);
                editor = (SiriusDiagramEditor) helper.getEditor();

                synchronizationWithUIThread();

                final DiagramDescription description = ((DSemanticDiagram) representation).getDescription();

                // We need a container to create a node. Create it now.
                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof ContainerCreationDescription) {
                        final ContainerCreationDescription containerCreation = (ContainerCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(containerCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(containerCreation.getName(), containerCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(editor.getDiagramEditPart().getViewer());
                        creationTool.setTargetEditPart(editor.getDiagramEditPart());
                        break;
                    }
                }

                List<EObject> oldElement = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());

                Request request = creationTool.getTargetRequest();
                org.eclipse.gef.commands.Command command = editor.getDiagramEditPart().getCommand(request);
                executeCommand(editor, command);

                List<EObject> newElements = new ArrayList<EObject>(editor.getDiagramEditPart().getChildren());
                newElements.removeAll(oldElement);

                createdElementEditPart = (IGraphicalEditPart) newElements.get(0);

                for (final ToolEntry tool : description.getToolSection().getOwnedTools()) {
                    if (tool instanceof NodeCreationDescription) {
                        final NodeCreationDescription nodeCreation = (NodeCreationDescription) tool;
                        final CreationFactory creationFactory = new ToolBasedCreationFactory(nodeCreation);

                        final CreationToolEntry paletteEntry = new CreationToolEntry(nodeCreation.getName(), nodeCreation.getDocumentation(), creationFactory, null, null);
                        paletteEntry.setToolClass(DummyCreationTool.class);

                        creationTool = (DummyCreationTool) paletteEntry.createTool();
                        creationTool.setViewer(createdElementEditPart.getViewer());
                        creationTool.setTargetEditPart(createdElementEditPart);
                        break;
                    }
                }

                oldElement = new ArrayList<EObject>(((IGraphicalEditPart) createdElementEditPart.getChildren().get(1)).getChildren());

                request = creationTool.getTargetRequest();
                command = createdElementEditPart.getCommand(request);
                executeCommand(editor, command);

                newElements = new ArrayList<EObject>(((IGraphicalEditPart) createdElementEditPart.getChildren().get(1)).getChildren());
                newElements.removeAll(oldElement);

                createdElementEditPart = (IGraphicalEditPart) newElements.get(0);
            }

            @Override
            public void postTest() {
                super.postTest();
                synchronizationWithUIThread();
                editor.getDiagramEditPart().deactivate();
                helper.closeEditor();

                session.close(new NullProgressMonitor());
                creationTool = null;
            }

            @Override
            protected void doTest() {
                final Request request = new EditCommandRequestWrapper(new AirDestroyElementRequest(session.getTransactionalEditingDomain(), false, true));
                final org.eclipse.gef.commands.Command command = createdElementEditPart.getCommand(request);
                executeCommand(editor, command);
                TestCase.assertTrue(createdElementEditPart.getParent() == null);
            }
        };
        return profiledNodeDeletionCommand;
    }

    public AbstractProfiledCommand createSessionCreationBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledSessionCreationCommand = new AbstractProfiledCommand("Creating session") {
            private Session session;

            @Override
            public void postTest() {
                super.postTest();
                session.close(new NullProgressMonitor());
            }

            @Override
            protected void doTest() {
                session = prepareAnalysisSession(model);
            }
        };
        return profiledSessionCreationCommand;
    }

    public AbstractProfiledCommand createRepresentationsCreationBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledSessionCreationCommand = new AbstractProfiledCommand("Creating representations") {
            private Session session;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
            }

            @Override
            public void postTest() {
                super.postTest();
                session.close(new NullProgressMonitor());
            }

            @Override
            protected void doTest() {
                createRepresentations(model, viewpoint, session);
            }
        };
        return profiledSessionCreationCommand;
    }

    public AbstractProfiledCommand createOpenRepresentationsBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledOpenCommand = new AbstractProfiledCommandWithUnit("Opening " + MAX_OPENED_EDITORS + " DRepresentations") {
            private Session session;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);
            }

            @Override
            public void postTest() {
                super.postTest();
                session.close(new NullProgressMonitor());
            }

            @Override
            protected void doTest() {
                int openEditorCount = 0;
                for (final DRepresentation representation : representations) {

                    final AbstractProfiledCommandUnit unit = new AbstractProfiledCommandUnit() {
                        @Override
                        protected void doTest() {
                            openAndCloseEditor(session, representation);
                        }
                    };
                    this.doUnit(unit);

                    openEditorCount++;

                    if (openEditorCount == MAX_OPENED_EDITORS) {
                        break;
                    }
                }
            }
        };
        return profiledOpenCommand;
    }

    private void openAndCloseEditor(final Session session, final DRepresentation representation) {
        EditorManagementHelper helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
        helper.openEditor(session, representation);
        synchronizationWithUIThread();
        helper.closeEditor();
    }

    public AbstractProfiledCommand createSaveSessionBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledSaveCommand = new AbstractProfiledCommand("Save session") {
            private Session session;

            @Override
            public void preTest() {
                super.preTest();
                session = prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);
            }

            @Override
            public void postTest() {
                super.postTest();
                session.close(new NullProgressMonitor());
            }

            @Override
            protected void doTest() {
                if (semantic.eResource() != null) {
                    // The resource is in plugin
                    semantic.eResource().setModified(false);
                }
                session.save(new NullProgressMonitor());
            }
        };
        return profiledSaveCommand;
    }

    public AbstractProfiledCommand createSaveRefreshedSessionBenchedTest(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);

        final AbstractProfiledCommand profiledRefreshCommand = new AbstractProfiledCommand() {
            private DAnalysisSession session;

            @Override
            public void preTest() {
                super.preTest();
                session = (DAnalysisSession) prepareAnalysisSession(model);
                createRepresentations(model, viewpoint, session);
                setName("Reopen session after creation of representations");

                executeRefreshRepresentationsCommands(session, representations.size() / 2);
                moveRepresentationsToOtherAird(session);
            }

            @Override
            public void postTest() {
                final List<Resource> resources = new ArrayList<Resource>(session.getAllSessionResources());
                super.postTest();
                session.save(new NullProgressMonitor());
                session.close(new NullProgressMonitor());
                session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        if (model.eResource() != null)
                            resources.add(model.eResource());
                        for (Resource resource : resources) {
                            if (resource.isLoaded())
                                resource.unload();
                        }
                    }
                });
                resources.clear();
            }

            @Override
            protected void doTest() {

                final AbstractCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {

                    @Override
                    protected void doExecute() {

                        final List<Resource> resources = session.getTransactionalEditingDomain().getResourceSet().getResources();
                        for (Resource res : resources) {
                            if (res.isTrackingModification()) {
                                if (res.isModified() && (res.getURI().isFile() || res.getURI().isPlatformResource()) && !SiriusUtil.isModelerDescriptionFile(res)) {
                                    saveResource(res, getSaveOptions());
                                }
                            } else {
                                if (res.getURI().isFile() || res.getURI().isPlatformResource() && !SiriusUtil.isModelerDescriptionFile(res))
                                    saveResource(res, getSaveOptions());
                            }
                        }
                    }
                };
                session.getTransactionalEditingDomain().getCommandStack().execute(command);
            }
        };
        return profiledRefreshCommand;
    }

    public AbstractProfiledCommand createReOpenSessionBenchedTest() {

        final AbstractProfiledCommand profiledRefreshCommand = new AbstractProfiledCommand() {
            private Session session;

            @Override
            public void preTest() {
                super.preTest();
            }

            @Override
            public void postTest() {
                super.postTest();
                session.close(new NullProgressMonitor());
            }

            @Override
            protected void doTest() {

                DslCommonPlugin.PROFILER.init();

                final List<Resource> loadedResources = new ArrayList<Resource>();

                for (final URI uri : benchmarkScenario.getAirdUris()) {

                    final AbstractCommand loadRessourceCommand = new RecordingCommand(benchmarkScenario.getDomain()) {
                        @Override
                        protected void doExecute() {
                            ResourceSet resourceSet = benchmarkScenario.getDomain().getResourceSet();
                            EObject root = null;

                            ProfilerTask loadModel = new ProfilerTask("ModelUtils", "Load Model");

                            DslCommonPlugin.PROFILER.startWork(loadModel);

                            try {
                                root = ModelUtils.load(uri, resourceSet);
                            } catch (final IOException e) {
                                // Shouldn't happen. consider it a failure
                                TestCase.fail("Couldn't reload aird file");
                            }

                            DslCommonPlugin.PROFILER.stopWork(loadModel);

                            TestCase.assertNotNull(root);
                            TestCase.assertNotNull(root.eResource());

                            loadedResources.add(root.eResource());
                        }
                    };
                    benchmarkScenario.getDomain().getCommandStack().execute(loadRessourceCommand);
                    System.out.println(uri + "was loaded with success");
                }

                final AbstractCommand openSessionCommand = new RecordingCommand(benchmarkScenario.getDomain()) {
                    @Override
                    protected void doExecute() {

                        final EObject root = loadedResources.get(0).getContents().get(0);
                        TestCase.assertTrue(root instanceof DAnalysis);

                        URI sessionResourceURI = root.eResource().getURI();
                        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
                        session.open(new NullProgressMonitor());
                        IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
                        editingSession.open();
                        TestCase.assertNotNull(session);
                        TestCase.assertTrue(SessionManager.INSTANCE.getSessions().size() == 1);
                    }
                };
                try {
                    ((TransactionalCommandStack) benchmarkScenario.getDomain().getCommandStack()).execute(openSessionCommand, lowMemoryOptions());
                } catch (InterruptedException e) {
                    TestCase.fail(e.getMessage());
                    e.printStackTrace();
                } catch (RollbackException e) {
                    TestCase.fail(e.getMessage());
                    e.printStackTrace();
                }
                System.out.println("session opened with success");

            }

            /**
             * Makes a map from one option.
             * 
             * @param option
             *            the option to enable
             * 
             * @return the map
             */
            protected Map<Object, Object> lowMemoryOptions() {

                Map<Object, Object> options = new HashMap<>();
                options.put(Transaction.OPTION_NO_UNDO, Boolean.TRUE);
                options.put(Transaction.OPTION_NO_TRIGGERS, Boolean.TRUE);
                options.put(Transaction.OPTION_NO_NOTIFICATIONS, Boolean.TRUE);
                options.put(Transaction.OPTION_NO_VALIDATION, Boolean.TRUE);
                return options;
            }
        };
        return profiledRefreshCommand;
    }

    /**
     * This will create, initialize and open an analysis so as to load all
     * required plugins for our tests.
     * 
     * @param viewpoint
     *            We'll need a viewpoint to initialize the analysis contents.
     * @param semantic
     *            This will provide the semantic objects for which to create
     *            graphical representations.
     */
    public void warmUpRun(final Viewpoint viewpoint, final EObject semantic) {
        final EObject model = resolve(semantic);
        try {
            model.eResource().save(getSaveOptions());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final Session session = prepareAnalysisSession(model);
        createRepresentations(model, viewpoint, session);

        EditorManagementHelper helper = new EditorManagementHelper(benchmarkScenario.getOperationSupport().getOperationHistory());
        helper.openEditor(session, representations.get(0));

        synchronizationWithUIThread();

        helper.closeEditor();
        session.close(new NullProgressMonitor());
    }

    private EObject resolve(final EObject eObj) {
        return eObj;
    }

    public int getNumberOfRepresentations() {
        if (representations == null)
            return 0;
        else {
            return representations.size();
        }
    }

    private void saveResource(Resource res, Map<?, ?> options) {
        try {
            res.save(options);
        } catch (IOException e) {
        }
    }

    private Map<?, ?> getSaveOptions() {
        final Map<Object, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_USE_FILE_BUFFER, true);
        options.put(XMLResource.OPTION_FLUSH_THRESHOLD, true);
        return options;
    }

    private void moveRepresentationsToOtherAird(final DAnalysisSession session) {

        int nbAird = representations.size() / MAX_REPRESENTATION_IN_SAME_AIRD;
        mainAirdUri = session.getSessionResource().getURI();

        final List<String> allAirdPaths = new ArrayList<String>();
        allAirdPaths.add(mainAirdUri.toString());

        if (nbAird <= 1) {
            benchmarkScenario.fillAirdPaths(allAirdPaths);
            return;
        }

        final Collection<Resource> otherAirdResources = new ArrayList<Resource>(nbAird - 1);

        allAirdPaths.add(mainAirdUri.toString());

        for (int i = 0; i < nbAird - 1; i++) {
            StringBuilder pathName = new StringBuilder();
            pathName.append(mainAirdUri.trimFileExtension().toString());
            pathName.append(i);
            pathName.append(".");
            pathName.append(mainAirdUri.fileExtension());

            URI newAird = URI.createURI(pathName.toString());
            allAirdPaths.add(pathName.toString());

            final DiagramFileCreationOperation op = new DiagramFileCreationOperation(newAird);
            try {
                op.run(new NullProgressMonitor());
                otherAirdResources.add(op.getPickedResource());
            } catch (final InvocationTargetException e) {
                e.printStackTrace();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

        benchmarkScenario.fillAirdPaths(allAirdPaths);

        int fromIndex = MAX_REPRESENTATION_IN_SAME_AIRD;
        for (Iterator<Resource> iterator = otherAirdResources.iterator(); iterator.hasNext();) {
            Resource resource = iterator.next();
            DAnalysis slaveAnalysis = prepareNewAnalysis(resource);

            int toIndex = fromIndex + MAX_REPRESENTATION_IN_SAME_AIRD;
            if (toIndex > representations.size()) {
                toIndex = representations.size();
            }

            Collection<DRepresentation> representationsToMove = representations.subList(fromIndex, toIndex);
            moveRepresentations(slaveAnalysis, session, representationsToMove);

            fromIndex = toIndex;
        }

    }

    private void moveRepresentations(final DAnalysis slaveAnalysis, final DAnalysisSession session, final Collection<DRepresentation> representationsToMove) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                session.addReferencedAnalysis(slaveAnalysis);
                for (final Resource resource : session.getSemanticResources()) {
                    if (!resource.getContents().isEmpty()) {
                        slaveAnalysis.getSemanticResources().add(new ResourceDescriptor(resource.getURI()));
                    }
                }
                for (final DRepresentation representation : representationsToMove) {
                    DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation).getRepresentationDescriptor();
                    session.moveRepresentation(slaveAnalysis, representationDescriptor);
                }
            }

            @Override
            public boolean canUndo() {
                /*
                 * We don't want to let people undo that !
                 */
                return false;
            }
        });
    }

    private DAnalysis prepareNewAnalysis(final Resource resource) {
        final DAnalysis slaveAnalysis = ViewpointFactory.eINSTANCE.createDAnalysis();

        benchmarkScenario.getDomain().getCommandStack().execute(new RecordingCommand(benchmarkScenario.getDomain()) {

            @Override
            protected void doExecute() {
                resource.getContents().add(slaveAnalysis);
            }

            @Override
            public boolean canUndo() {
                return false;
            }

        });
        return slaveAnalysis;
    }

    private void executeCommand(final IDiagramWorkbenchPart part, final Command command, final TransactionalEditingDomain domain) {
        executeCommand(part, new ICommandProxy(new GMFCommandWrapper(domain, command)));
    }

    private void executeCommand(final IDiagramWorkbenchPart part, final org.eclipse.gef.commands.Command command) {
        final EditDomain ed = part.getDiagramGraphicalViewer().getEditDomain();
        ed.getCommandStack().execute(command);
        synchronizationWithUIThread();
    }

    /**
     * Force UIThread to execute all runnables on its stack.
     */
    private void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
        } ;
    }

    private class ToolBasedCreationFactory implements CreationFactory {

        AbstractToolDescription toolDesc;

        public ToolBasedCreationFactory(AbstractToolDescription toolDescription) {
            this.toolDesc = toolDescription;
        }

        @Override
        public Object getNewObject() {
            return toolDesc;
        }

        @Override
        public Object getObjectType() {
            return toolDesc.getClass();
        }

    }

    private class DiagramFileCreationOperation extends WorkspaceModifyOperation {

        private Resource pickedResource = null;

        private final URI pickedUri;

        public DiagramFileCreationOperation(final URI uri) {
            super(null);
            pickedUri = uri;
        }

        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InterruptedException {
            final ResourceSet set = benchmarkScenario.getDomain().getResourceSet();
            pickedResource = set.createResource(pickedUri);
            benchmarkScenario.addResourceUndoContext(pickedResource);
        }

        public Resource getPickedResource() {
            return pickedResource;
        }
    }
}
