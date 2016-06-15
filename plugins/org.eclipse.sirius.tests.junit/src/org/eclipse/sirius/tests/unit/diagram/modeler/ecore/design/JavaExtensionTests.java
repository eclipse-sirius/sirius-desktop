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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.design;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.api.project.ViewpointSpecificationProject;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

/**
 * Tests the Java Extension (template and/or java services).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class JavaExtensionTests extends SiriusDiagramTestCase {

    private static final String DESCRIPTION_FOLDER_NAME = "description";

    private static final String SOURCE_FOLDER_NAME = "src";

    private static final String DEFAULT_PACKAGE_NAME = "org/eclipse/sirius";

    private static final String DEFAULT_TEMPLATE_PACKAGE_NAME = "template";

    private static final String DEFAULT_JAVA_SERVICE_PACKAGE_NAME = "service";

    private static final String PROJECT_BASE1_NAME = "SiriusTestProjectBase1";

    private static final String PROJECT_BASE1_DESCRIPTION_PATH = "data/unit/modelers/ecore/javaextension/description/SiriusTestProjectBase1/description/base1.odesign";

    private static final String PROJECT_BASE1_TEMPLATE_PATH = "data/unit/modelers/ecore/javaextension/template/Base1.mt";

    private static final String PROJECT_BASE1_JAVA_SERVICE_PATH = "data/unit/modelers/ecore/javaextension/service/Base1.java";

    private static final String PROJECT_BASE1_EXT_A_NAME = "SiriusTestProjectBase1ExtA";

    private static final String PROJECT_BASE1_EXT_A_DESCRIPTION_PATH = "data/unit/modelers/ecore/javaextension/description/SiriusTestProjectBase1ExtA/description/base1ExtA.odesign";

    private static final String PROJECT_BASE1_EXT_A_TEMPLATE_PATH = "data/unit/modelers/ecore/javaextension/template/Base1ExtA.mt";

    private static final String PROJECT_BASE1_EXT_A_JAVA_SERVICE_PATH = "data/unit/modelers/ecore/javaextension/service/Base1ExtA.java";

    private static final String PROJECT_BASE1_EXT_A_JAVA_SERVICE_SPECIFIC_PATH = "data/unit/modelers/ecore/javaextension/service/Base1ExtASpecific.java";

    private static final String PROJECT_BASE1_EXT_B_NAME = "SiriusTestProjectBase1ExtB";

    private static final String PROJECT_BASE1_EXT_B_DESCRIPTION_PATH = "data/unit/modelers/ecore/javaextension/description/SiriusTestProjectBase1ExtB/description/base1ExtB.odesign";

    private static final String PROJECT_BASE1_EXT_B_TEMPLATE_PATH = "data/unit/modelers/ecore/javaextension/template/Base1ExtB.mt";

    private static final String PROJECT_BASE1_EXT_B_JAVA_SERVICE_PATH = "data/unit/modelers/ecore/javaextension/service/Base1ExtB.java";

    private static final String PROJECT_BASE2_NAME = "SiriusTestProjectBase2";

    private static final String PROJECT_BASE2_DESCRIPTION_PATH = "data/unit/modelers/ecore/javaextension/description/SiriusTestProjectBase2/description/base2.odesign";

    private static final String PROJECT_BASE2_TEMPLATE_PATH = "data/unit/modelers/ecore/javaextension/template/Base2.mt";

    private static final String PROJECT_BASE2_JAVA_SERVICE_PATH = "data/unit/modelers/ecore/javaextension/service/Base2.java";

    private static final String PROJECT_BASE2_EXT_C_NAME = "SiriusTestProjectBase2ExtC";

    private static final String PROJECT_BASE2_EXT_C_DESCRIPTION_PATH = "data/unit/modelers/ecore/javaextension/description/SiriusTestProjectBase2ExtC/description/base2ExtC.odesign";

    private static final String PROJECT_BASE2_EXT_C_TEMPLATE_PATH = "data/unit/modelers/ecore/javaextension/template/Base2ExtC.mt";

    private static final String PROJECT_BASE2_EXT_C_JAVA_SERVICE_PATH = "data/unit/modelers/ecore/javaextension/service/Base2ExtC.java";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/javaextension/model/UseCase1.ecore";

    private static final String SEMANTIC_CLASS_BASE1_NAME = "Base1";

    private static final String SEMANTIC_CLASS_BASE1_EXT_A_NAME = "Base1ExtA";

    private static final String SEMANTIC_CLASS_BASE1_EXT_B_NAME = "Base1ExtB";

    private static final String SEMANTIC_CLASS_BASE2_NAME = "Base2";

    private static final String VIEWPOINT_BASE1_NAME = "Base1WithTemplate";

    private static final String VIEWPOINT_BASE1_CLASS_MAPPING_NAME = "TEC EClass Base1";

    private static final String VIEWPOINT_BASE1_EXT_A_NAME = "Base1ExtA_withAcceleoTemplate";

    private static final String VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME = "Doc Class A";

    private static final String VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME_2 = "Doc Class A2";

    private static final String VIEWPOINT_BASE1_EXT_B_NAME = "Base1ExtB_withAcceleoTemplate";

    private static final String VIEWPOINT_BASE2_NAME = "Base2WithTemplate";

    private static final String VIEWPOINT_BASE2_CLASS_MAPPING_NAME = "TEC EClass Base2";

    private static final String REPRESENTATION_BASE1_DESC_NAME = "Entities1";

    private static final String REPRESENTATION_BASE2_DESC_NAME = "Entities2";

    private static final String ERR_MSG_BAD_MAPPING = "Bad mapping";

    private static final String ERR_MSG_BAD_STYLE = "Bad style";

    private static final String ERR_MSG_BAD_COLOR_TYPE = "Bad color type";

    private static final String ERR_MSG_BAD_BORDER_COLOR = "Bad border color";

    private static final String ERR_MSG_INVALID_DATA = "The input data is incorrect";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        cleanWorkspace();
        // Deactivate the auto build to avoid problem of test before build is
        // finish.
        ResourcesPlugin.getWorkspace().getDescription().setAutoBuilding(false);
        // Create project Base1
        final IProject projectBase1 = createModelingProjectAndInitializeIt(PROJECT_BASE1_NAME, PROJECT_BASE1_DESCRIPTION_PATH, PROJECT_BASE1_JAVA_SERVICE_PATH, PROJECT_BASE1_TEMPLATE_PATH);
        // Create project Base1
        final IProject projectBase2 = createModelingProjectAndInitializeIt(PROJECT_BASE2_NAME, PROJECT_BASE2_DESCRIPTION_PATH, PROJECT_BASE2_JAVA_SERVICE_PATH, PROJECT_BASE2_TEMPLATE_PATH);
        // Create project Base1ExtA
        final IProject projectBase1ExtA = createModelingProjectAndInitializeIt(PROJECT_BASE1_EXT_A_NAME, PROJECT_BASE1_EXT_A_DESCRIPTION_PATH, PROJECT_BASE1_EXT_A_JAVA_SERVICE_PATH,
                PROJECT_BASE1_EXT_A_TEMPLATE_PATH);
        // Copy of java class
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PROJECT_BASE1_EXT_A_JAVA_SERVICE_SPECIFIC_PATH, PROJECT_BASE1_EXT_A_NAME + File.separator + SOURCE_FOLDER_NAME
                + File.separator + DEFAULT_PACKAGE_NAME + File.separator + DEFAULT_JAVA_SERVICE_PACKAGE_NAME + File.separator + new Path(PROJECT_BASE1_EXT_A_JAVA_SERVICE_SPECIFIC_PATH).lastSegment());
        // Create project Base1ExtB
        final IProject projectBase1ExtB = createModelingProjectAndInitializeIt(PROJECT_BASE1_EXT_B_NAME, PROJECT_BASE1_EXT_B_DESCRIPTION_PATH, PROJECT_BASE1_EXT_B_JAVA_SERVICE_PATH,
                PROJECT_BASE1_EXT_B_TEMPLATE_PATH);
        // Create project Base2ExtC
        final IProject projectBase2ExtC = createModelingProjectAndInitializeIt(PROJECT_BASE2_EXT_C_NAME, PROJECT_BASE2_EXT_C_DESCRIPTION_PATH, PROJECT_BASE2_EXT_C_JAVA_SERVICE_PATH,
                PROJECT_BASE2_EXT_C_TEMPLATE_PATH);
        // Launch a manual build and wait the end of the workspace build
        ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());

        final List<String> modelerDescriptionPaths = new ArrayList<String>();
        modelerDescriptionPaths.add(projectBase1.getFile(new Path(DESCRIPTION_FOLDER_NAME).append(new Path(PROJECT_BASE1_DESCRIPTION_PATH).lastSegment())).getFullPath().toOSString());
        modelerDescriptionPaths.add(projectBase1ExtA.getFile(new Path(DESCRIPTION_FOLDER_NAME).append(new Path(PROJECT_BASE1_EXT_A_DESCRIPTION_PATH).lastSegment())).getFullPath().toOSString());
        modelerDescriptionPaths.add(projectBase1ExtB.getFile(new Path(DESCRIPTION_FOLDER_NAME).append(new Path(PROJECT_BASE1_EXT_B_DESCRIPTION_PATH).lastSegment())).getFullPath().toOSString());
        modelerDescriptionPaths.add(projectBase2.getFile(new Path(DESCRIPTION_FOLDER_NAME).append(new Path(PROJECT_BASE2_DESCRIPTION_PATH).lastSegment())).getFullPath().toOSString());
        modelerDescriptionPaths.add(projectBase2ExtC.getFile(new Path(DESCRIPTION_FOLDER_NAME).append(new Path(PROJECT_BASE2_EXT_C_DESCRIPTION_PATH).lastSegment())).getFullPath().toOSString());

        super.genericSetUp(SEMANTIC_MODEL_PATH, modelerDescriptionPaths);

        initViewpoint(VIEWPOINT_BASE1_NAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_BASE1_DESC_NAME).toArray()[0];
    }

    private IProject createModelingProjectAndInitializeIt(final String projectName, final String viewpointDescriptionPath, final String javaServicePath, final String templatePath) {
        try {
            ViewpointSpecificationProject.createNewViewpointSpecificationProject(projectName, new Path(viewpointDescriptionPath).lastSegment());
        } catch (final CoreException e) {
            fail(e.getMessage());
        }
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        assertTrue("The project " + projectName + " was not created correctly.", project.exists());
        assertTrue("The project " + projectName + " should contain Service.java class.", project.getFile("src/" + projectName + "/Services.java").exists());
        // Copy of representation description file
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, viewpointDescriptionPath, projectName + File.separator + DESCRIPTION_FOLDER_NAME + File.separator
                + new Path(viewpointDescriptionPath).lastSegment());
        // Copy of java class
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, javaServicePath, projectName + File.separator + SOURCE_FOLDER_NAME + File.separator + DEFAULT_PACKAGE_NAME
                + File.separator + DEFAULT_JAVA_SERVICE_PACKAGE_NAME + File.separator + new Path(javaServicePath).lastSegment());
        // Copy of acceleo template
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, templatePath, projectName + File.separator + SOURCE_FOLDER_NAME + File.separator + DEFAULT_PACKAGE_NAME
                + File.separator + DEFAULT_TEMPLATE_PACKAGE_NAME + File.separator + new Path(templatePath).lastSegment());

        return project;
    }

    /**
     * Check access form diagramExtension to Acceleo service import from the
     * extended Diagram.
     */
    public void testExternalAcceleoServiceAccessFromDiagramExtension() {
        assertNotNull(ERR_MSG_INVALID_DATA, diagram);
        // Open the editor (and refresh it)
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final EPackage root = (EPackage) semanticModel;
        final EClassifier classifierBase1 = root.getEClassifier(SEMANTIC_CLASS_BASE1_NAME);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        final EClassifier classifierBase1ExtA = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_A_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase1ExtB = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_B_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        // Activate Base1ExtA
        initViewpoint(VIEWPOINT_BASE1_EXT_A_NAME);
        // Activate the layer
        activateLayer(diagram, "Extension with acceleo template A");
        // Refresh the diagram
        refresh(diagram);
        // Check that the Acceleo service available in project base is
        // accessible from extA without directly importing this Acceleo service.
        diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME, SystemColors.CHOCOLATE_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);
    }

    /**
     * Check override of templates and alphabetic priority for brothers.
     * 
     * @throws Exception
     */
    public void testOverrideTemplateOnSiriusActivation() throws Exception {
        assertNotNull(ERR_MSG_INVALID_DATA, diagram);
        // Open the editor (and refresh it)
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final EPackage root = (EPackage) semanticModel;
        final EClassifier classifierBase1 = root.getEClassifier(SEMANTIC_CLASS_BASE1_NAME);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        final EClassifier classifierBase1ExtA = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_A_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase1ExtB = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_B_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        // Activate Base1ExtB viewpoint and verify that the border color change
        // for Base1 and Base1ExtB.
        // Because the template is override by Base1ExtB
        initViewpoint(VIEWPOINT_BASE1_EXT_B_NAME);

        TestsUtil.synchronizationWithUIThread();
        // Refresh the diagram
        refresh(diagram);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        // Activate Base1ExtA viewpoint and verify that the border color change
        // for Base1, Base1ExtA and Base1ExtB.
        // Because the template is override by Base1ExtA and has priority on
        // Base1ExtB cause of alphabetic order.
        initViewpoint(VIEWPOINT_BASE1_EXT_A_NAME);
        // Refresh the diagram
        refresh(diagram);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);
    }

    /**
     * Check override of templates and alphabetic priority for brothers.
     * 
     * @throws Exception
     */
    public void testImportPreparation() throws Exception {
        assertNotNull(ERR_MSG_INVALID_DATA, diagram);
        // Open the editor (and refresh it)
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final EPackage root = (EPackage) semanticModel;
        final EClassifier classifierBase1 = root.getEClassifier(SEMANTIC_CLASS_BASE1_NAME);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        final EClassifier classifierBase1ExtA = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_A_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase1ExtB = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_B_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        Collection<String> dependenciesStep0 = new ArrayList<String>(this.interpreter.getImports());
        assertEquals(2, dependenciesStep0.size());
        Iterator<String> it = dependenciesStep0.iterator();
        assertEquals("org.eclipse.sirius.template.Base1", it.next());
        assertEquals("org.eclipse.sirius.service.Base1", it.next());

        // Activate Base1ExtB viewpoint and verify that the border color change
        // for Base1 and Base1ExtB.
        // Because the template is override by Base1ExtB
        initViewpoint(VIEWPOINT_BASE1_EXT_B_NAME);
        TestsUtil.synchronizationWithUIThread();

        Collection<String> dependenciesStep1 = new ArrayList<String>(this.interpreter.getImports());
        assertFalse(Iterables.elementsEqual(dependenciesStep0, dependenciesStep1));
        assertEquals(3, dependenciesStep1.size());

        // Refresh the diagram
        refresh(diagram);
        Collection<String> dependenciesStep2 = new ArrayList<String>(this.interpreter.getImports());
        assertTrue(Iterables.elementsEqual(dependenciesStep2, dependenciesStep1));

        it = dependenciesStep1.iterator();
        assertEquals("org.eclipse.sirius.template.Base1ExtB", it.next());
        assertEquals("org.eclipse.sirius.template.Base1", it.next());
        assertEquals("org.eclipse.sirius.service.Base1", it.next());

        diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        // Activate Base1ExtA viewpoint and verify that the border color change
        // for Base1, Base1ExtA and Base1ExtB.
        // Because the template is override by Base1ExtA and has priority on
        // Base1ExtB cause of alphabetic order.
        initViewpoint(VIEWPOINT_BASE1_EXT_A_NAME);
        Collection<String> dependenciesStep3 = new ArrayList<String>(this.interpreter.getImports());
        assertEquals(5, dependenciesStep3.size());
        assertFalse(Iterables.elementsEqual(dependenciesStep0, dependenciesStep3));
        assertFalse(Iterables.elementsEqual(dependenciesStep1, dependenciesStep3));

        it = dependenciesStep3.iterator();
        assertEquals("org.eclipse.sirius.template.Base1ExtA", it.next());
        assertEquals("org.eclipse.sirius.service.Base1ExtASpecific", it.next());
        assertEquals("org.eclipse.sirius.template.Base1ExtB", it.next());
        assertEquals("org.eclipse.sirius.template.Base1", it.next());
        assertEquals("org.eclipse.sirius.service.Base1", it.next());

        // Refresh the diagram
        refresh(diagram);
        Collection<String> dependenciesStep4 = new ArrayList<String>(this.interpreter.getImports());
        assertTrue(Iterables.elementsEqual(dependenciesStep4, dependenciesStep3));

        diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);
    }

    /**
     * @param diagramElement
     *            The element to check
     * @param expectedMappingName
     *            The expected mapping name
     * @param expectedCOlor
     *            The expected color or null if any color is expected.
     */
    private void checkMappingAndColor(final DDiagramElement diagramElement, final String expectedMappingName, final SystemColors expectedColor) {
        assertEquals(ERR_MSG_BAD_MAPPING, expectedMappingName, diagramElement.getDiagramElementMapping().getName());
        assertTrue(ERR_MSG_BAD_STYLE, diagramElement.getStyle().getDescription() instanceof FlatContainerStyleDescription);
        RGBValues borderColor = (RGBValues) diagramElement.getStyle().eGet(diagramElement.getStyle().eClass().getEStructuralFeature("borderColor"));
        final ColorDescription colorMapping = ((FlatContainerStyleDescription) diagramElement.getStyle().getDescription()).getBorderColor();
        if (expectedColor == null) {
            assertNull(ERR_MSG_BAD_BORDER_COLOR, colorMapping);
        } else {
            assertTrue(ERR_MSG_BAD_COLOR_TYPE, colorMapping instanceof SystemColor);
            assertNotNull(borderColor);
            assertEquals(ERR_MSG_BAD_BORDER_COLOR, expectedColor, VisualBindingManager.getDefault().findClosestStandardColor(borderColor));
        }
    }

    /**
     * Check override of templates and alphabetic priority for two independents
     * viewpoint.
     */
    public void testOverrideTemplateOnSiriusActivationForIndependentsSiriuss() {
        // Activate Base1ExtB viewpoint and verify that the border color change
        // for Base1 and Base1ExtB.
        // Because the template is override by Base1ExtB
        initViewpoint(VIEWPOINT_BASE2_NAME);
        final DDiagram diagram2 = (DDiagram) getRepresentations(REPRESENTATION_BASE2_DESC_NAME).toArray()[0];

        assertNotNull(ERR_MSG_INVALID_DATA, diagram2);
        // Open the editor (and refresh it)
        DialectUIManager.INSTANCE.openEditor(session, diagram2, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final EPackage root = (EPackage) semanticModel;
        final EClassifier classifierBase1 = root.getEClassifier(SEMANTIC_CLASS_BASE1_NAME);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram2, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE2_CLASS_MAPPING_NAME, SystemColors.YELLOW_LITERAL);

        final EClassifier classifierBase1ExtA = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_A_NAME);
        diagramElement = getFirstDiagramElement(diagram2, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE2_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase1ExtB = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_B_NAME);
        diagramElement = getFirstDiagramElement(diagram2, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE2_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase2 = root.getEClassifier(SEMANTIC_CLASS_BASE2_NAME);
        diagramElement = getFirstDiagramElement(diagram2, classifierBase2);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE2_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase2ExtC = root.getEClassifier("Base2ExtC");
        diagramElement = getFirstDiagramElement(diagram2, classifierBase2ExtC);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE2_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);
    }

    /**
     * Check access form diagramExtension to Acceleo service import only in
     * odesign which declare this diagramExtension.
     */
    public void testInternalAcceleoServiceAccessFromDiagramExtension() {
        assertNotNull(ERR_MSG_INVALID_DATA, diagram);
        // Open the editor (and refresh it)
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        final EPackage root = (EPackage) semanticModel;
        final EClassifier classifierBase1 = root.getEClassifier(SEMANTIC_CLASS_BASE1_NAME);
        DDiagramElement diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.GREEN_LITERAL);

        final EClassifier classifierBase1ExtA = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_A_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase1ExtB = root.getEClassifier(SEMANTIC_CLASS_BASE1_EXT_B_NAME);
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        final EClassifier classifierBase1ExtAOnly = root.getEClassifier("Base1ExtAOnly");
        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtAOnly);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_CLASS_MAPPING_NAME, SystemColors.BLACK_LITERAL);

        // Activate Base1ExtA
        initViewpoint(VIEWPOINT_BASE1_EXT_A_NAME);
        // Activate the layer
        activateLayer(diagram, "Extension A - Use service define only in ExtA");
        // Refresh the diagram
        refresh(diagram);
        // Check that the Acceleo service available in project base is
        // accessible from extA without directly importing this Acceleo service.
        diagramElement = getFirstDiagramElement(diagram, classifierBase1);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME_2, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtA);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME_2, SystemColors.GREEN_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtB);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME_2, SystemColors.BLACK_LITERAL);

        diagramElement = getFirstDiagramElement(diagram, classifierBase1ExtAOnly);
        checkMappingAndColor(diagramElement, VIEWPOINT_BASE1_EXT_A_CLASS_MAPPING_NAME_2, SystemColors.ORANGE_LITERAL);
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {

        diagram = null;

        // Close all open editors (because the odesign were opened during the
        // Modeling project creation)
        final IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
        for (int i = 0; i < editorReferences.length; i++) {
            final IEditorPart editorPart = editorReferences[i].getEditor(false);
            editorPart.getSite().getPage().closeEditor(editorPart, false);
        }

        EclipseTestsSupportHelper.INSTANCE.deleteProject(PROJECT_BASE1_NAME);
        EclipseTestsSupportHelper.INSTANCE.deleteProject(PROJECT_BASE1_EXT_A_NAME);
        EclipseTestsSupportHelper.INSTANCE.deleteProject(PROJECT_BASE1_EXT_B_NAME);
        EclipseTestsSupportHelper.INSTANCE.deleteProject(PROJECT_BASE2_NAME);
        EclipseTestsSupportHelper.INSTANCE.deleteProject(PROJECT_BASE2_EXT_C_NAME);
        // Activate the auto build.
        ResourcesPlugin.getWorkspace().getDescription().setAutoBuilding(true);

        super.tearDown();
    }
}
