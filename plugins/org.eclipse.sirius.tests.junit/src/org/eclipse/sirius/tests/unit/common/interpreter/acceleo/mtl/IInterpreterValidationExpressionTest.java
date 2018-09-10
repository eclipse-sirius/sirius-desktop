/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.editor.tools.internal.marker.SiriusEditorInterpreterMarkerService;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * 
 * Ensures that Acceleo3 expressions validation raises the expected errors.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class IInterpreterValidationExpressionTest extends SiriusDiagramTestCase {

    private static final String TEST_FOLDER_PATH = "/data/unit/interpreter/validateExpression/";

    private static final String MODELER_PLUGIN_PATH = TEST_FOLDER_PATH + "ecore_acceleo3.odesign";

    private static final String MODELER_WORKSPACE_PATH = "/" + TEMPORARY_PROJECT_NAME + "/" + "ecore_acceleo3.odesign";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + TEST_FOLDER_PATH + "ValidateExpression.ecore";

    private IFile odesignFile;

    private DiagramDescription diagramEntitiesAcceleo2;

    private DiagramDescription diagramEntitiesAcceleo3;

    private IEditorPart odesignEditor;

    private EditionTableDescription tableClassesAcceleo3;

    private TreeDescription treeClassesAcceleo3;

    private SequenceDiagramDescription sequenceDiagramAcceleo3;

    private IWorkbenchPage page;

    /**
     * 
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, MODELER_PLUGIN_PATH, MODELER_WORKSPACE_PATH);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_WORKSPACE_PATH);
        interpreter = CompoundInterpreter.INSTANCE;
        page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        odesignFile = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getFile("ecore_acceleo3.odesign");
        odesignEditor = page.openEditor(new FileEditorInput(odesignFile), "org.eclipse.sirius.editor.editorPlugin.SiriusEditorID");

        ResourceSet rs = new ResourceSetImpl();
        Resource r = rs.getResource(URI.createPlatformResourceURI(odesignFile.getFullPath().toOSString(), true), true);
        // A CrossReferenceAdapter is needed for validation to work. In the
        // standard VSM editor, one is installed on the constructor (see
        // org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor.CustomSiriusEditor()).
        rs.eAdapters().add(new ECrossReferenceAdapter());
        diagramEntitiesAcceleo2 = (DiagramDescription) ((Group) r.getContents().get(0)).getOwnedViewpoints().iterator().next().getOwnedRepresentations().get(0);
        diagramEntitiesAcceleo3 = (DiagramDescription) ((Group) r.getContents().get(0)).getOwnedViewpoints().iterator().next().getOwnedRepresentations().get(1);
        tableClassesAcceleo3 = (EditionTableDescription) ((Group) r.getContents().get(0)).getOwnedViewpoints().iterator().next().getOwnedRepresentations().get(2);
        treeClassesAcceleo3 = (TreeDescription) ((Group) r.getContents().get(0)).getOwnedViewpoints().iterator().next().getOwnedRepresentations().get(3);
        sequenceDiagramAcceleo3 = (SequenceDiagramDescription) ((Group) r.getContents().get(0)).getOwnedViewpoints().iterator().next().getOwnedRepresentations().get(4);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        TestsUtil.emptyEventsFromUIThread();
        page.closeEditor(odesignEditor, false);
        super.tearDown();
    }

    /**
     * Ensures that AQL expressions validation are raising error
     */
    public void testAValidationExpressionWithAQL() {
        Layer acceleo2Layer = getLayer(diagramEntitiesAcceleo2, "Default");
        ContainerMapping nodeMapping = getContainerMapping(acceleo2Layer, "EC EClass");
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "semanticCandidatesExpression", "aql:self.invalidFeatureExpression",
                "Feature invalidFeatureExpression not found in EClass EPackage");
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "semanticElements", "aql:self.invalidFeatureExpression", "Feature invalidFeatureExpression not found in EClass EPackage",
                "Feature invalidFeatureExpression not found in EClass EClass");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnNodeMappings() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerMapping nodeMapping = getContainerMapping(acceleo3Layer, "EC EClass");
        // Valid expression => no marker should have been created
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "semanticCandidatesExpression", "[eClassifiers/]");
        // invalidFeature expression => marker should have been created
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "semanticCandidatesExpression", "[eStructuralFeatures/]", "Unrecognized variable: (eStructuralFeatures)");
        // Valid expression => all markers should have been deleted
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "semanticCandidatesExpression", "[nsURI/]");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnNodeMappingsWithinvalidFeatureDomainClass() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerMapping nodeMapping = getContainerMapping(acceleo3Layer, "EC EClass");
        // invalidFeature ECLass : a validation error should be raised, but not
        // InterpreterException
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "domainClass", "Nothing", "The Class Nothing does not exist.",
                "The type name Nothing used in domainClass does not have a package prefix.");
        // invalidFeature ECLass : a validation error should be raised
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "semanticElements", "[self.invalidFeature/]", "Invalid Type: Nothing",
                "The type name Nothing used in domainClass does not have a package prefix.", "Unrecognized variable: (invalidFeature)", "The Class Nothing does not exist.");
        // valid EClass : interpreter exceptions should now be raised
        ensureExpressionValidationRaisedExpectedErrors(nodeMapping, "domainClass", "EClass", "Unrecognized variable: (invalidFeature)",
                "The type name EClass used in domainClass does not have a package prefix.");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnRelationBasedEdgeMapping() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerMapping nodeMapping = getContainerMapping(acceleo3Layer, "EC EDataType");
        EdgeMapping edgeMapping = getEdgeMapping(acceleo3Layer, "EC ESupertypes");
        ensureExpressionValidationRaisedExpectedErrors(edgeMapping, "targetFinderExpression", "[self.eSuperTypes/]");
        // eClassifiers is undefined on ECLass => interpreter error should be
        // raised
        ensureExpressionValidationRaisedExpectedErrors(edgeMapping, "targetFinderExpression", "[self.eClassifiers/]", "Unrecognized variable: (eClassifiers)");
        ensureExpressionValidationRaisedExpectedErrors(edgeMapping, "targetFinderExpression", "[self.eAttributes/]");
        // changing Source Mapping to EC EDataType => [self.eAttributes/]
        // becomes incorrect
        ensureExpressionValidationRaisedExpectedErrors(edgeMapping, "sourceMapping", new ArrayList<ContainerMapping>(Arrays.asList(nodeMapping)), "Unrecognized variable: (eAttributes)");
        ensureExpressionValidationRaisedExpectedErrors(edgeMapping, "targetFinderExpression", "[self.serializable/]");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnConditionalStyle() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerMapping nodeMapping = getContainerMapping(acceleo3Layer, "EC EClass");
        ConditionalContainerStyleDescription conditionalStyle = nodeMapping.getConditionnalStyles().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(conditionalStyle, "predicateExpression", "[self.eAttributes->size()>0/]");
        ensureExpressionValidationRaisedExpectedErrors(conditionalStyle, "predicateExpression", "[self.candidateMappings/]", "Unrecognized variable: (candidateMappings)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithTool() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerCreationDescription tool = (ContainerCreationDescription) getTool(acceleo3Layer, "Class");
        ensureExpressionValidationRaisedExpectedErrors(tool, "precondition", "[self.eAttributes/]");
        ensureExpressionValidationRaisedExpectedErrors(tool, "precondition", "[self.candidatesMapping/]", "Unrecognized variable: (candidatesMapping)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnGoToInsideCreateTool() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerMapping nodeMapping = getContainerMapping(acceleo3Layer, "EC EClass");
        ContainerDropDescription tool = (ContainerDropDescription) getTool(acceleo3Layer, "Drop attribute");
        ModelOperation goTo = tool.getInitialOperation().getFirstModelOperations();
        ensureExpressionValidationRaisedExpectedErrors(goTo, "browseExpression", "[self.eAttributeType/]");
        ensureExpressionValidationRaisedExpectedErrors(goTo, "browseExpression", "[self.eAttributes/]", "Unrecognized variable: (eAttributes)");
        ensureExpressionValidationRaisedExpectedErrors(goTo, "browseExpression", "[self.eAttributeType/]");
        tool.getMappings().clear();
        tool.getMappings().add(nodeMapping);
        ensureExpressionValidationRaisedExpectedErrors(goTo, "browseExpression", "[self.eAttributeType/]", "Unrecognized variable: (eAttributeType)");
        ensureExpressionValidationRaisedExpectedErrors(goTo, "browseExpression", "[self.eAttributes/]");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3AndVariables() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerCreationDescription tool = (ContainerCreationDescription) getTool(acceleo3Layer, "Class");
        ModelOperation firstModelOperations = (ModelOperation) tool.getInitialOperation().getFirstModelOperations().eContents().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(firstModelOperations, "valueExpression", "[self.eAttributes/]");
        ensureExpressionValidationRaisedExpectedErrors(firstModelOperations, "valueExpression", "[self.eClassifiers/]", "Unrecognized variable: (eClassifiers)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3SetValueInsideGoto() {
        Layer acceleo3Layer = getLayer(diagramEntitiesAcceleo3, "Default");
        ContainerDropDescription tool = (ContainerDropDescription) getTool(acceleo3Layer, "Drop attribute");
        ChangeContext goTo = (ChangeContext) tool.getInitialOperation().getFirstModelOperations();
        ModelOperation setValue = goTo.getSubModelOperations().iterator().next();
        // a warning should be raised as this operation is inside a change
        // context
        ensureExpressionValidationRaisedExpectedErrors(setValue, "valueExpression", "[self/]");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnAudits() {
        SemanticValidationRule rule = Iterables.filter(diagramEntitiesAcceleo3.getValidationSet().getAllRules(), SemanticValidationRule.class).iterator().next();
        RuleAudit audit = rule.getAudits().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(audit, "auditExpression", "[self.eAttributes->size() > 0/]");
        ensureExpressionValidationRaisedExpectedErrors(audit, "auditExpression", "[self.eAttributes->size()/]");
        ensureExpressionValidationRaisedExpectedErrors(audit, "auditExpression", "[self.invalidFeature->size() > 0/]", "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnLineMapping() {
        LineMapping lineMapping = tableClassesAcceleo3.getOwnedLineMappings().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(lineMapping, "semanticCandidatesExpression", "[self.eClassifiers/]");
        ensureExpressionValidationRaisedExpectedErrors(lineMapping, "semanticCandidatesExpression", "[self.invalidFeature/]", "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnFeatureColumnMapping() {
        FeatureColumnMapping featureColumnMapping = tableClassesAcceleo3.getOwnedColumnMappings().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(featureColumnMapping, "labelExpression", "[self.eSuperTypes->first().name/]");
        ensureExpressionValidationRaisedExpectedErrors(featureColumnMapping, "labelExpression", "[self.invalidFeature/]", "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnLineForegroundConditionalStyle() {
        ForegroundConditionalStyle foregroundStyle = tableClassesAcceleo3.getOwnedLineMappings().iterator().next().getForegroundConditionalStyle().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(foregroundStyle, "predicateExpression", "[self.abstract/]");
        ensureExpressionValidationRaisedExpectedErrors(foregroundStyle, "predicateExpression", "[self.invalidFeature->size()>1/]", "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnTreeItemMapping() {
        TreeItemMapping treeItemMapping = treeClassesAcceleo3.getSubItemMappings().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(treeItemMapping, "semanticCandidatesExpression", "[self.eClassifiers/]");
        ensureExpressionValidationRaisedExpectedErrors(treeItemMapping, "semanticCandidatesExpression", "[self.invalidFeature/]", "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnTreeItemConditionalStyle() {
        ConditionalTreeItemStyleDescription treeItemStyle = treeClassesAcceleo3.getSubItemMappings().iterator().next().getConditionalStyles().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(treeItemStyle, "predicateExpression", "[self.abstract/]");
        ensureExpressionValidationRaisedExpectedErrors(treeItemStyle, "predicateExpression", "[self.invalidFeature->size()>1/]", "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnSequenceDiagramInstanceRole() {
        InstanceRoleMapping instanceRole = (InstanceRoleMapping) sequenceDiagramAcceleo3.getDefaultLayer().getNodeMappings().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(instanceRole, "semanticCandidatesExpression", "[self/]",
                "The EClass interactions.Participant used in domainClass is not accessible. You are most likely trying to use an EClass without having a dependency to its plugin.");
        ensureExpressionValidationRaisedExpectedErrors(instanceRole, "semanticCandidatesExpression", "[self.invalidFeature/]",
                "The EClass interactions.Participant used in domainClass is not accessible. You are most likely trying to use an EClass without having a dependency to its plugin.",
                "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnSequenceDiagramExecution() {
        InstanceRoleMapping instanceRole = (InstanceRoleMapping) sequenceDiagramAcceleo3.getDefaultLayer().getNodeMappings().iterator().next();
        
        
        BasicEList<DiagramElementMapping> allMappings = new BasicEList<DiagramElementMapping>();
        allMappings.addAll(MappingHelper.getAllBorderedNodeMappings(instanceRole));
        
        ExecutionMapping executionMapping = (ExecutionMapping) allMappings.iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(executionMapping, "startingEndFinderExpression", "[self/]",
                "The EClass interactions.Participant used in domainClass is not accessible. You are most likely trying to use an EClass without having a dependency to its plugin.");
        ensureExpressionValidationRaisedExpectedErrors(executionMapping, "startingEndFinderExpression", "[self.invalidFeature/]",
                "The EClass interactions.Participant used in domainClass is not accessible. You are most likely trying to use an EClass without having a dependency to its plugin.",
                "Unrecognized variable: (invalidFeature)");
    }

    /**
     * Ensures that Acceleo3 expressions validation raises the expected errors.
     */
    public void testValidationExpressionWithAcceleo3OnSequenceDiagramMessageEnd() {
        MessageMapping messageMapping = (MessageMapping) sequenceDiagramAcceleo3.getDefaultLayer().getEdgeMappings().iterator().next();
        ensureExpressionValidationRaisedExpectedErrors(messageMapping, "sendingEndFinderExpression", "[self.sendingEnd/]",
                "The EClass interactions.FeatureAccessMessage used in domainClass is not accessible. You are most likely trying to use an EClass without having a dependency to its plugin.");
        ensureExpressionValidationRaisedExpectedErrors(messageMapping, "sendingEndFinderExpression", "[self.invalidFeature/]",
                "The EClass interactions.FeatureAccessMessage used in domainClass is not accessible. You are most likely trying to use an EClass without having a dependency to its plugin.",
                "Unrecognized variable: (invalidFeature)");
    }

    protected void ensureExpressionValidationRaisedExpectedErrors(EObject target, String featureName, Object newValue, String... message) {
        // Step 1 : setting the new expression to the node Mapping
        EStructuralFeature feature = target.eClass().getEStructuralFeature(featureName);
        target.eSet(feature, newValue);
        TestsUtil.synchronizationWithUIThread();
        // Step 2 : launching validation
        if (target instanceof RuleAudit) {
            launchValidation(target.eContainer().eContainer());
        } else {
            launchValidation(target.eContainer());
        }

        // Step 3 : ensure that the expected markers have been created
        String resourceURI = target.eResource().getURI().toString();
        String uriFragment = target.eResource().getURIFragment(target);
        Collection<IMarker> allMarkersForElement = SiriusEditorInterpreterMarkerService.getValidationMarkersForElement(odesignFile, resourceURI + "#" + uriFragment);
        Collection<String> errorMessages = new ArrayList<String>(Arrays.asList(message));
        Collection<String> remainingMessages = new ArrayList<String>(Arrays.asList(message));
        for (IMarker marker : allMarkersForElement) {
            String errorMessage = marker.getAttribute(IMarker.MESSAGE, null);
            assertTrue("The error '" + errorMessage + "' should not have been raised by validation : expecting one of " + errorMessages, errorMessages.contains(errorMessage));
            remainingMessages.remove(errorMessage);
        }
        assertTrue("The errors " + errorMessages + " should have been raised by validation", remainingMessages.isEmpty());
    }

    private void launchValidation(EObject elementToValidate) {
        IStructuredSelection selection = new StructuredSelection(elementToValidate);

        // We launch the validate action without opening pop-up
        ValidateAction validateAction = new ValidateAction() {
            @Override
            protected void handleDiagnostic(Diagnostic diagnostic) {
                Resource resource = eclipseResourcesUtil != null ? domain.getResourceSet().getResources().get(0) : null;
                if (resource != null) {
                    eclipseResourcesUtil.deleteMarkers(resource);
                }

                if (!diagnostic.getChildren().isEmpty()) {
                    List<?> data = (diagnostic.getChildren().get(0)).getData();
                    if (!data.isEmpty() && data.get(0) instanceof EObject) {
                        Object part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
                        if (part instanceof ISetSelectionTarget) {
                            ((ISetSelectionTarget) part).selectReveal(new StructuredSelection(data.get(0)));
                        } else if (part instanceof IViewerProvider) {
                            Viewer viewer = ((IViewerProvider) part).getViewer();
                            if (viewer != null) {
                                viewer.setSelection(new StructuredSelection(data.get(0)), true);
                            }
                        }
                    }
                }

                if (resource != null) {
                    for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
                        eclipseResourcesUtil.createMarkers(resource, childDiagnostic);
                    }
                }
            }
        };
        validateAction.updateSelection(selection);
        validateAction.setActiveWorkbenchPart(odesignEditor);
        TestsUtil.synchronizationWithUIThread();
        validateAction.run();
        TestsUtil.synchronizationWithUIThread();
    }

    private Layer getLayer(DiagramDescription diagramDescription, final String expectedLayerName) {
        Iterable<Layer> layers = Iterables.filter(LayerHelper.getAllLayers(diagramDescription), new Predicate<Layer>() {
            @Override
            public boolean apply(Layer input) {
                return input.getName().equals(expectedLayerName);
            }
        });
        assertTrue(layers.iterator().hasNext());
        return layers.iterator().next();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#doesAnErrorOccurs()
     */
    @Override
    protected synchronized boolean doesAnErrorOccurs() {
        // Only validation errors should have been logged
        boolean onlyValidationErrors = true;
        for (IStatus status : errors.values()) {
            onlyValidationErrors = onlyValidationErrors && status.getMessage().startsWith("Compilation error");
        }
        return !onlyValidationErrors;
    }
}
