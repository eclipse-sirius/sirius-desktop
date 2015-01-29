/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.benchmark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.tests.sample.benchmark.BenchmarkFactory;
import org.eclipse.sirius.tests.sample.benchmark.InputData;
import org.eclipse.sirius.tests.sample.benchmark.Property;
import org.eclipse.sirius.tests.sample.benchmark.Scenario;
import org.eclipse.sirius.tests.sample.benchmark.TestCase;
import org.eclipse.sirius.tests.sample.benchmark.TimeResult;
import org.eclipse.sirius.tests.sample.benchmark.Variant;
import org.eclipse.sirius.tools.api.command.EditingDomainUndoContext;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.operations.IWorkbenchOperationSupport;

import com.google.common.collect.Maps;

/**
 * This class launch the scenario of the benchmark.
 * 
 * @author ymortier
 */
public class BenchmarkScenario {
    private enum Size {
        SHORT, NOMINAL, LARGE;
    }

    /** Name of the project we'll create to hold the temporary files. */
    private static final String TEMPORARY_PROJECT_NAME = "temp";

    /** Path to the temporary aird model. */
    private static final String TEMPORARY_AIRD_FILE_PATH = '/' + TEMPORARY_PROJECT_NAME + '/' + "temp.aird";

    /** path of all aird models. */
    private static final List<String> TEMPORARY_AIRD_FILE_PATHS = new ArrayList<String>();

    /** Total element count of the large models. */
    public static final int LARGE_MODEL_SIZE = 200000;

    /** Total element count of the nominal models. */
    public static final int NOMINAL_MODEL_SIZE = 10000;

    /** Total element count of the small models. */
    public static final int SHORT_MODEL_SIZE = 1000;

    private final TransactionalEditingDomain domain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain();

    /** This command factory will allow us to create benchmarked commands. */
    private final ProfiledCommandFactory profiledCommandFactory;

    /** The metamodel specific data. */
    private IMMSpecificBenchmarckTest specificBenchmarckTest;

    /** The workbench operation support. */
    private IWorkbenchOperationSupport operationSupport;

    /**
     * A map to store undo contexts limits per context
     */
    private Set<IUndoContext> undoContexts = new HashSet<IUndoContext>();

    /**
     * Creates the scenario.
     */
    public BenchmarkScenario() {
        profiledCommandFactory = new ProfiledCommandFactory(this);
        final IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(TEMPORARY_PROJECT_NAME);
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME);
        operationSupport = PlatformUI.getWorkbench().getOperationSupport();

        try {
            project.create(projectDescription, new NullProgressMonitor());
            project.open(new NullProgressMonitor());
        } catch (final CoreException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
    }

    public void fillAirdPaths(Collection<String> paths) {
        TEMPORARY_AIRD_FILE_PATHS.addAll(paths);
    }

    public List<URI> getAirdUris() {
        final List<URI> uris = new ArrayList<URI>();
        for (final String pathname : TEMPORARY_AIRD_FILE_PATHS) {
            uris.add(URI.createURI(pathname));
        }
        return uris;
    }

    public Resource getAIRDResource() {
        return getTemporaryResource(TEMPORARY_AIRD_FILE_PATH);
    }

    public Resource getSemanticResource() {
        return getTemporaryResource('/' + TEMPORARY_PROJECT_NAME + '/' + specificBenchmarckTest.getSemanticModelName());
    }

    /**
     * Returns a temporary resource.
     * 
     * @return a temporary resource.
     */
    private Resource getTemporaryResource(String path) {
        final Resource res = this.domain.getResourceSet().createResource(URI.createPlatformResourceURI(path, true));
        this.addResourceUndoContext(res);
        return res;
    }

    /**
     * Attachs the given EObject to the temporary resource as a root.
     * 
     * @param resource
     *            The resource to attach <code>model</code> to.
     * @param model
     *            The model to be attached to a resource.
     */
    public void attachToTemporaryResource(final Resource resource, final EObject model) {
        final Command command = new RecordingCommand(this.domain) {
            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
             */
            @Override
            protected void doExecute() {
                resource.getContents().add(model);
            }
        };
        this.domain.getCommandStack().execute(command);
    }

    /**
     * Returns the domain.
     * 
     * @return the domain.
     */
    public TransactionalEditingDomain getDomain() {
        return domain;
    }

    /**
     * Returns the resource set to use.
     * 
     * @return the resource set to use.
     */
    public ResourceSet getResourceSet() {
        return domain.getResourceSet();
    }

    /**
     * @return the specificBenchmarckTest
     */
    public IMMSpecificBenchmarckTest getSpecificBenchmarckTest() {
        return specificBenchmarckTest;
    }

    /**
     * @return the operationSupport
     */
    public IWorkbenchOperationSupport getOperationSupport() {
        return operationSupport;
    }

    private void configureOperationHistory() {
        if (operationSupport != null) {
            addOperationHistoryUndoContext(operationSupport.getUndoContext());

            final CommandStack stack = domain.getCommandStack();
            if (stack instanceof IWorkspaceCommandStack) {
                addOperationHistoryUndoContext(((IWorkspaceCommandStack) stack).getDefaultUndoContext());
            } else {
                addOperationHistoryUndoContext(new EditingDomainUndoContext(domain));
            }
        }
    }

    private void disposeOperationHistoryContexts() {
        if (operationSupport == null)
            return;
        for (IUndoContext undoContext : undoContexts) {
            operationSupport.getOperationHistory().dispose(undoContext, true, true, true);
        }
        undoContexts.clear();
    }

    public void addResourceUndoContext(final Resource res) {
        if (operationSupport != null) {
            final ResourceUndoContext undoContext = new ResourceUndoContext(domain, res);
            addOperationHistoryUndoContext(undoContext);
        }
    }

    private void addOperationHistoryUndoContext(final IUndoContext undoContext) {
        operationSupport.getOperationHistory().setLimit(undoContext, 0);
        undoContexts.add(undoContext);
    }

    public void addEditorUndoContext(IEditorPart editor) {
        IUndoContext undoContext = null;
        if (editor instanceof DiagramEditor)
            undoContext = (IUndoContext) ((DiagramEditor) editor).getAdapter(IUndoContext.class);
        if (undoContext != null)
            addOperationHistoryUndoContext(undoContext);
    }

    private Property createBenchmarkProperty(String name, String value) {
        final Property property = BenchmarkFactory.eINSTANCE.createProperty();
        property.setName(name);
        property.setValue(value);
        return property;
    }

    /**
     * Creates a TestCase instance.
     * 
     * @param name
     *            Name of the TestCase to create.
     * @return Created TestCase.
     */
    private TestCase createTestCase(String name, final int max) {
        final TestCase testCase = BenchmarkFactory.eINSTANCE.createTestCase();
        testCase.setName(name);
        final Property maxProperty = createMaxProperty();
        maxProperty.setValue(String.valueOf(max));
        testCase.getProperties().add(maxProperty);
        System.out.println(name);
        return testCase;
    }

    private Property createMaxProperty() {
        final Property property = BenchmarkFactory.eINSTANCE.createProperty();
        property.setName("Max");
        return property;
    }

    private void minimalWarmup(IMMSpecificBenchmarckTest specificBenchmarckTest) {
        // We need to have GMF loaded for our first test not to take a huge
        // amount of time
        profiledCommandFactory.warmUpRun(specificBenchmarckTest.getAcceleoSirius(), specificBenchmarckTest.getShortModelRootContainer(getSemanticResource()));
        // OCL also needs to be loaded
        profiledCommandFactory.warmUpRun(specificBenchmarckTest.getOclSirius(), specificBenchmarckTest.getShortModelRootContainer(getSemanticResource()));
    }

    private void persistResultModel(Scenario scenario) {
        try {
            final ResourceSet resourceSet = new ResourceSetImpl();
            final Resource resource = resourceSet.createResource(URI.createFileURI(specificBenchmarckTest.getOutputLocation().getAbsolutePath()));
            resource.getContents().add(scenario);
            resource.save(Collections.EMPTY_MAP);
            resource.unload();
        } catch (final IOException e) {
            // TODO Couldn't save results. As it could have been real lengthy, a
            // serialization should be printed out to standard error.
            System.err.println("couldn't save benchmark results at the specified location.");
        }
    }

    private void saveModel(EObject model) {
        if (model != null && model.eResource() != null) {
            try {
                final Map<Object, Object> options = Maps.newHashMap();
                options.put(XMLResource.OPTION_USE_FILE_BUFFER, true);

                model.eResource().save(Collections.EMPTY_MAP);
            } catch (IOException e) {
            }
        }
    }

    private EObject getModel(final IMMSpecificBenchmarckTest specificBenchmarckTest, final Size size) {
        switch (size) {
        case LARGE:
            return specificBenchmarckTest.getLargeModelRootContainer(getSemanticResource());
        case NOMINAL:
            return specificBenchmarckTest.getNominalModelRootContainer(getSemanticResource());
        case SHORT:
            return specificBenchmarckTest.getShortModelRootContainer(getSemanticResource());
        default:
            return null;
        }
    }

    /**
     * Creates a Variant given the name it is to be affected.
     * 
     * @param name
     *            Name of the newly created variant.
     * @return The created Variant.
     */
    private Variant createVariant(String name) {
        final Variant variant = BenchmarkFactory.eINSTANCE.createVariant();
        variant.setName(name);
        return variant;
    }

    /**
     * Warm up the scenario.
     * 
     * @param specificBenchmarckTest
     */
    public void nominalWarmup(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.warmup(specificBenchmarckTest);
        this.disposeOperationHistoryContexts();
    }

    /**
     * Warm up the large scenario.
     * 
     * @param specificBenchmarckTest
     */
    public void largeWarmup(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.warmup(specificBenchmarckTest);
        this.disposeOperationHistoryContexts();
    }

    /**
     * Warm up the scenario.
     * 
     * @param specificBenchmarckTest
     * @param model
     */
    public void warmup(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.minimalWarmup(specificBenchmarckTest);

        final Variant acceleoVariant = createVariant("Acceleo Expressions");
        final Variant oclVariant = createVariant("OCL Expressions");

        EObject model = specificBenchmarckTest.getShortModelRootContainer(getSemanticResource());
        testSessionCreation(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant);
        model = specificBenchmarckTest.getShortModelRootContainer(getSemanticResource());
        testSessionCreation(specificBenchmarckTest.getOclSirius(), model, oclVariant);
        model = specificBenchmarckTest.getShortModelRootContainer(getSemanticResource());
        testContainerDeletion(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant);
        model = specificBenchmarckTest.getShortModelRootContainer(getSemanticResource());
        testContainerDeletion(specificBenchmarckTest.getOclSirius(), model, oclVariant);
    }

    /**
     * Runs the nominal scenario.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     */
    public void nominalRun(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.minimalWarmup(specificBenchmarckTest);

        final Size size = Size.NOMINAL;
        final EObject nominalModel = getModel(specificBenchmarckTest, size);
        final InputData nominalModelInputData = specificBenchmarckTest.createSemanticModelInputData("Nominal semantic model", nominalModel);
        this.saveModel(nominalModel);

        final Scenario scenario = BenchmarkFactory.eINSTANCE.createScenario();
        scenario.setName("Sirius Benchmark");
        scenario.getInputData().add(nominalModelInputData);

        final Variant acceleoVariant = createVariant("Acceleo Expressions");
        scenario.getVariants().add(acceleoVariant);
        final Variant oclVariant = createVariant("OCL Expressions");
        scenario.getVariants().add(oclVariant);

        this.commonRun(specificBenchmarckTest, scenario, size, nominalModelInputData, acceleoVariant, oclVariant);

        this.nodeDeletionRun(specificBenchmarckTest, scenario, size, nominalModelInputData, acceleoVariant, oclVariant);

        this.containerDeletionRun(specificBenchmarckTest, scenario, size, nominalModelInputData, acceleoVariant, oclVariant);

        this.extendedRun(specificBenchmarckTest, scenario, size, nominalModelInputData, acceleoVariant);

        // persist the result model.
        this.disposeOperationHistoryContexts();
        this.persistResultModel(scenario);
    }

    /**
     * Runs the container deletion test of the scenario.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     * @param scenario
     * @param model
     * @param modelInputData
     * @param oclVariant
     * @param acceleoVariant
     * @param size
     */
    private void containerDeletionRun(IMMSpecificBenchmarckTest specificBenchmarckTest, Scenario scenario, Size size, InputData modelInputData, Variant acceleoVariant, Variant oclVariant) {
        final TestCase containerDeletion = createTestCase("Container Deletion", 3000);
        containerDeletion.setInputData(modelInputData);
        containerDeletion.getResults().add(testContainerDeletion(specificBenchmarckTest.getAcceleoSirius(), getModel(specificBenchmarckTest, size), acceleoVariant));
        containerDeletion.getResults().add(testContainerDeletion(specificBenchmarckTest.getOclSirius(), getModel(specificBenchmarckTest, size), oclVariant));
        scenario.getTestCases().add(containerDeletion);
    }

    /**
     * Runs the scenario.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     * @param scenario
     * @param size
     * @param model
     * @param modelInputData
     * @param oclVariant
     * @param acceleoVariant
     */
    private void nodeDeletionRun(IMMSpecificBenchmarckTest specificBenchmarckTest, Scenario scenario, Size size, InputData modelInputData, Variant acceleoVariant, Variant oclVariant) {
        final TestCase nodeDeletion = createTestCase("Node Deletion", 3000);
        nodeDeletion.setInputData(modelInputData);
        nodeDeletion.getResults().add(testNodeDeletion(specificBenchmarckTest.getAcceleoSirius(), getModel(specificBenchmarckTest, size), acceleoVariant));
        nodeDeletion.getResults().add(testNodeDeletion(specificBenchmarckTest.getOclSirius(), getModel(specificBenchmarckTest, size), oclVariant));
        scenario.getTestCases().add(nodeDeletion);
    }

    /**
     * Runs the large scenario.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     */
    public void largeRun(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.minimalWarmup(specificBenchmarckTest);

        final Size size = Size.LARGE;
        EObject largeModel = getModel(specificBenchmarckTest, size);
        final InputData largeModelInputData = specificBenchmarckTest.createSemanticModelInputData("Nominal semantic model", largeModel);
        this.saveModel(largeModel);

        final Scenario scenario = BenchmarkFactory.eINSTANCE.createScenario();
        scenario.setName("Sirius Benchmark");
        scenario.getInputData().add(largeModelInputData);

        final Variant acceleoVariant = createVariant("Acceleo Expressions");
        scenario.getVariants().add(acceleoVariant);
        final Variant oclVariant = createVariant("OCL Expressions");
        scenario.getVariants().add(oclVariant);

        this.commonRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant, oclVariant);

        this.nodeDeletionRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant, oclVariant);

        this.containerDeletionRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant, oclVariant);

        this.extendedRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant);

        this.disposeOperationHistoryContexts();
        this.persistResultModel(scenario);
    }

    /**
     * Runs the minimum scenario for large models.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     */
    public void minimalLargeRun(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.minimalWarmup(specificBenchmarckTest);

        final Size size = Size.LARGE;
        EObject largeModel = getModel(specificBenchmarckTest, size);
        final InputData largeModelInputData = specificBenchmarckTest.createSemanticModelInputData("Nominal semantic model", largeModel);
        this.saveModel(largeModel);

        final Scenario scenario = BenchmarkFactory.eINSTANCE.createScenario();
        scenario.setName("Sirius Benchmark");
        scenario.getInputData().add(largeModelInputData);

        final Variant acceleoVariant = createVariant("Acceleo Expressions");
        scenario.getVariants().add(acceleoVariant);
        final Variant oclVariant = createVariant("OCL Expressions");
        scenario.getVariants().add(oclVariant);

        this.commonRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant, oclVariant);

        this.disposeOperationHistoryContexts();
        this.persistResultModel(scenario);
    }

    /**
     * Runs the node deletion scenario for large model.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     */
    public void nodeDeletionLargeRun(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.minimalWarmup(specificBenchmarckTest);

        final Size size = Size.LARGE;
        final EObject largeModel = getModel(specificBenchmarckTest, size);
        final InputData largeModelInputData = specificBenchmarckTest.createSemanticModelInputData("Nominal semantic model", largeModel);
        this.saveModel(largeModel);

        final Scenario scenario = BenchmarkFactory.eINSTANCE.createScenario();
        scenario.setName("Sirius Benchmark");
        scenario.getInputData().add(largeModelInputData);

        final Variant acceleoVariant = createVariant("Acceleo Expressions");
        scenario.getVariants().add(acceleoVariant);
        final Variant oclVariant = createVariant("OCL Expressions");
        scenario.getVariants().add(oclVariant);

        this.nodeDeletionRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant, oclVariant);

        this.disposeOperationHistoryContexts();
        this.persistResultModel(scenario);
    }

    /**
     * Runs the container deletion scenario for large model.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     */
    public void containerDeletionLargeRun(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.minimalWarmup(specificBenchmarckTest);

        final Size size = Size.LARGE;
        final EObject largeModel = getModel(specificBenchmarckTest, size);
        final InputData largeModelInputData = specificBenchmarckTest.createSemanticModelInputData("Nominal semantic model", largeModel);
        this.saveModel(largeModel);

        final Scenario scenario = BenchmarkFactory.eINSTANCE.createScenario();
        scenario.setName("Sirius Benchmark");
        scenario.getInputData().add(largeModelInputData);

        final Variant acceleoVariant = createVariant("Acceleo Expressions");
        scenario.getVariants().add(acceleoVariant);
        final Variant oclVariant = createVariant("OCL Expressions");
        scenario.getVariants().add(oclVariant);

        this.containerDeletionRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant, oclVariant);

        this.disposeOperationHistoryContexts();
        this.persistResultModel(scenario);
    }

    /**
     * Runs the extended scenario for large models.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     */
    public void largeExtendedRun(final IMMSpecificBenchmarckTest specificBenchmarckTest) {
        this.specificBenchmarckTest = specificBenchmarckTest;
        this.configureOperationHistory();
        this.minimalWarmup(specificBenchmarckTest);

        final Size size = Size.LARGE;
        final EObject largeModel = getModel(specificBenchmarckTest, size);
        final InputData largeModelInputData = specificBenchmarckTest.createSemanticModelInputData("Nominal semantic model", largeModel);
        this.saveModel(largeModel);

        final Scenario scenario = BenchmarkFactory.eINSTANCE.createScenario();
        scenario.setName("Sirius Benchmark");
        scenario.getInputData().add(largeModelInputData);

        final Variant acceleoVariant = createVariant("Acceleo Expressions");
        scenario.getVariants().add(acceleoVariant);

        this.extendedRun(specificBenchmarckTest, scenario, size, largeModelInputData, acceleoVariant);

        this.disposeOperationHistoryContexts();
        this.persistResultModel(scenario);
    }

    /**
     * Runs the common tests of the scenario.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     * @param scenario
     * @param size
     * @param model
     * @param modelInputData
     * @param oclVariant
     * @param acceleoVariant
     */
    private void commonRun(final IMMSpecificBenchmarckTest specificBenchmarckTest, Scenario scenario, Size size, InputData modelInputData, Variant acceleoVariant, Variant oclVariant) {

        final TestCase sessionCreation = createTestCase("Create session", 20000);
        sessionCreation.setInputData(modelInputData);
        EObject model = getModel(specificBenchmarckTest, size);
        sessionCreation.getResults().add(testSessionCreation(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        sessionCreation.getResults().add(testSessionCreation(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(sessionCreation);

        final TestCase representationsCreation = createTestCase("Create representations", 5000);
        representationsCreation.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        representationsCreation.getResults().add(testRepresentationsCreation(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        representationsCreation.getResults().add(testRepresentationsCreation(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(representationsCreation);

        final TestCase representationsOpening = createTestCase("Open representations", 5000);
        representationsOpening.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        representationsOpening.getResults().add(testOpenRepresentations(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        representationsOpening.getResults().add(testOpenRepresentations(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(representationsOpening);

        final TestCase representationsRefresh = createTestCase("Refresh representations", 5000);
        representationsRefresh.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        representationsRefresh.getResults().add(testRefreshRepresentations(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        representationsRefresh.getResults().add(testRefreshRepresentations(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(representationsRefresh);

        final TestCase sessionClosing = createTestCase("Close session", 30000);
        sessionClosing.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        sessionClosing.getResults().add(testCloseSession(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        sessionClosing.getResults().add(testCloseSession(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(sessionClosing);

        final TestCase sessionSaving = createTestCase("Save session", 10000);
        sessionSaving.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        sessionSaving.getResults().add(testSaveSession(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        sessionSaving.getResults().add(testSaveSession(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(sessionSaving);

        final TestCase filterActivation = createTestCase("Activate Filter", 2000);
        filterActivation.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        filterActivation.getResults().add(testActivateFilter(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        filterActivation.getResults().add(testActivateFilter(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(filterActivation);

        final TestCase layerActivation = createTestCase("Activate Layer", 2000);
        layerActivation.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        layerActivation.getResults().add(testActivateLayer(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        layerActivation.getResults().add(testActivateLayer(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(layerActivation);

        final TestCase containerCreation = createTestCase("Container Creation", 1000);
        containerCreation.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        containerCreation.getResults().add(testContainerCreation(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        containerCreation.getResults().add(testContainerCreation(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(containerCreation);

        final TestCase containerCreationAR = createTestCase("Container Creation Auto Refresh", 2000);
        containerCreationAR.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        containerCreationAR.getResults().add(testContainerCreationAutoRefresh(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        containerCreationAR.getResults().add(testContainerCreationAutoRefresh(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(containerCreationAR);

        final TestCase nodeCreation = createTestCase("Node Creation", 1000);
        nodeCreation.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        nodeCreation.getResults().add(testNodeCreation(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        nodeCreation.getResults().add(testNodeCreation(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(nodeCreation);

        final TestCase nodeCreationAR = createTestCase("Node Creation Auto Refresh", 2000);
        nodeCreationAR.setInputData(modelInputData);
        model = getModel(specificBenchmarckTest, size);
        nodeCreationAR.getResults().add(testNodeCreationAutoRefresh(specificBenchmarckTest.getAcceleoSirius(), model, acceleoVariant));
        model = getModel(specificBenchmarckTest, size);
        nodeCreationAR.getResults().add(testNodeCreationAutoRefresh(specificBenchmarckTest.getOclSirius(), model, oclVariant));
        scenario.getTestCases().add(nodeCreationAR);
    }

    /**
     * Runs the scenario.
     * 
     * @param specificBenchmarckTest
     *            the Metamodel specific data.
     * @param scenario
     * @param size
     * @param model
     * @param modelInputData
     * @param variant
     */
    private void extendedRun(final IMMSpecificBenchmarckTest specificBenchmarckTest, Scenario scenario, Size size, InputData modelInputData, Variant variant) {

        final TestCase saveRefreshedSession = createTestCase("SaveRefreshed session", 10000);
        saveRefreshedSession.setInputData(modelInputData);
        saveRefreshedSession.getResults().add(testSaveRefreshedSession(specificBenchmarckTest.getAcceleoSirius(), getModel(specificBenchmarckTest, size), variant));
        scenario.getTestCases().add(saveRefreshedSession);

        final TestCase reOpenSession = createTestCase("Reopen session", 20000);
        reOpenSession.setInputData(modelInputData);
        reOpenSession.getResults().add(testReOpenSession(specificBenchmarckTest.getAcceleoSirius(), variant));
        scenario.getTestCases().add(reOpenSession);
    }

    /**
     * Benchmarks the analysis refreshing performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testRefreshRepresentations(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createRefreshRepresentationsBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of refreshed representations", Integer.valueOf(ProfiledCommandFactory.MAX_OPENED_EDITORS).toString()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(
                createBenchmarkProperty("Description", "Open a new editor for each representation and launch a refresh to synchronize graphical widgets with model element, then close editor."));
        return result;
    }

    /**
     * Benchmarks the session creation performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testSessionCreation(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createSessionCreationBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Create a new session."));
        return result;
    }

    /**
     * Benchmarks the representations creation performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testRepresentationsCreation(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createRepresentationsCreationBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Create representations and synchronize graphical models elements with semantic model elements."));
        return result;
    }

    /**
     * Benchmarks the analysis opening performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testOpenRepresentations(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createOpenRepresentationsBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of opened representations", Integer.valueOf(ProfiledCommandFactory.MAX_OPENED_EDITORS).toString()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Open a new editor for each representation and close it."));
        return result;
    }

    /**
     * Benchmarks the analysis closing performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testCloseSession(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createCloseSessionBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Close a session."));
        return result;
    }

    /**
     * Benchmarks the filter activation performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testActivateFilter(final Viewpoint viewpoint, final EObject semanticModel, final Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createActivateFilterBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Activate a filter on a representation."));
        return result;
    }

    /**
     * Benchmarks the layer activation performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testActivateLayer(final Viewpoint viewpoint, final EObject semanticModel, final Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createActivateLayerBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Activate a layer on a representation."));
        return result;
    }

    /**
     * Benchmarks the performance of the container creation operation.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testContainerCreation(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createContainerCreationBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Create a container on a representation."));
        return result;
    }

    /**
     * Benchmarks the performance of the container creation operation (with auto
     * refresh.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testContainerCreationAutoRefresh(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createContainerCreationAutoRefreshBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Create a container on a representation (with auto refresh property set to true)."));
        return result;
    }

    /**
     * Benchmarks the performance of the container deletion operation.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testContainerDeletion(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createContainerDeletionBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Delete a container on a representation."));
        return result;
    }

    /**
     * Benchmarks the performance of the node creation operation.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testNodeCreation(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createNodeCreationBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Create a node on a representation."));
        return result;
    }

    /**
     * Benchmarks the performance of the node creation operation.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testNodeCreationAutoRefresh(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createNodeCreationAutoRefreshBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Create a node on a representation (with auto refresh property set to true)"));
        return result;
    }

    /**
     * Benchmarks the performance of the node deletion operation.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testNodeDeletion(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createNodeDeletionBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Delete a node on a representation"));
        return result;
    }

    /**
     * Benchmarks the performance of the resources saving operation.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testSaveSession(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createSaveSessionBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Save a session"));
        return result;
    }

    /**
     * Benchmarks the session saving performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param semanticModel
     *            Model on which tests are to be performed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testSaveRefreshedSession(Viewpoint viewpoint, EObject semanticModel, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);

        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createSaveRefreshedSessionBenchedTest(viewpoint, semanticModel);
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Save a session after representations have been refreshed"));
        return result;
    }

    /**
     * Benchmarks the open session performance.
     * 
     * @param viewpoint
     *            The viewpoint on which all tests will be executed.
     * @param variant
     *            Variant which is tested here.
     * @return Result of this particular test case.
     */
    private TimeResult testReOpenSession(Viewpoint viewpoint, Variant variant) {
        final TimeResult result = BenchmarkFactory.eINSTANCE.createTimeResult();
        result.setVariant(variant);
        final AbstractProfiledCommand nominalCommand = profiledCommandFactory.createReOpenSessionBenchedTest();
        nominalCommand.profiledTest();
        result.setElapsedTime(nominalCommand.getAverageElaspedTime());
        result.setElapsedMaxTime(nominalCommand.getMaxElapsedTime());
        result.getProperties().add(createBenchmarkProperty("Profiler status", DslCommonPlugin.PROFILER.getStatus()));
        result.getProperties().add(createBenchmarkProperty("Number of representations", String.valueOf(profiledCommandFactory.getNumberOfRepresentations())));
        result.getProperties().add(createBenchmarkProperty("Description", "Load a previously created session."));
        return result;
    }
}
