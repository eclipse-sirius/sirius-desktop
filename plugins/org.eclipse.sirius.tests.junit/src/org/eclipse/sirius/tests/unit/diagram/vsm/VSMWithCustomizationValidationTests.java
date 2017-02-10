/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.vsm;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tools.internal.validation.description.constraints.EmptyAppliedOnListConstraint;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import junit.framework.TestCase;

/**
 * A {@link TestCase} to test VSM with Customization validation.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class VSMWithCustomizationValidationTests extends TestCase {

    private static final String PATH = "/data/unit/refresh/StyleCustomizations/";

    private static final String MODELER_RESOURCE_NAME = "StyleCustomizations.odesign";

    private static final String MODELER_EXTA_RESOURCE_NAME = "StyleCustomizations_ExtensionA.odesign";

    private static final String MODELER_EXTB_RESOURCE_NAME = "StyleCustomizations_ExtensionB.odesign";

    private Group group;

    private Group groupA;

    private Group groupB;

    private DiagramDescription diagramDescription1;

    private DiagramDescription diagramDescription2;

    private EAttributeCustomization eAttributeCustomization;

    private EReferenceCustomization eReferenceCustomization;

    private VSMElementCustomizationReuse vsmElementCustomizationReuse1;

    private VSMElementCustomizationReuse vsmElementCustomizationReuse2;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        URI modelerResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_RESOURCE_NAME, true);
        URI modelerAResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_EXTA_RESOURCE_NAME, true);
        URI modelerBResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_EXTB_RESOURCE_NAME, true);

        ResourceSet resourceSet = new ResourceSetImpl();
        Resource modelerResource = resourceSet.getResource(modelerResourceURI, true);
        group = (Group) modelerResource.getContents().get(0);

        Resource modelerResourceA = resourceSet.getResource(modelerAResourceURI, true);
        groupA = (Group) modelerResourceA.getContents().get(0);

        Resource modelerResourceB = resourceSet.getResource(modelerBResourceURI, true);
        groupB = (Group) modelerResourceB.getContents().get(0);

        Viewpoint viewpoint = group.getOwnedViewpoints().get(0);
        diagramDescription1 = (DiagramDescription) viewpoint.getOwnedRepresentations().get(0);
        diagramDescription2 = (DiagramDescription) viewpoint.getOwnedRepresentations().get(1);
        AdditionalLayer optionalLayer = diagramDescription1.getAdditionalLayers().get(0);
        Customization customization = optionalLayer.getCustomization();
        VSMElementCustomization vsmElementCustomization1 = (VSMElementCustomization) customization.getVsmElementCustomizations().get(0);
        VSMElementCustomization vsmElementCustomization2 = (VSMElementCustomization) customization.getVsmElementCustomizations().get(1);
        eAttributeCustomization = (EAttributeCustomization) vsmElementCustomization1.getFeatureCustomizations().get(0);
        eReferenceCustomization = (EReferenceCustomization) vsmElementCustomization2.getFeatureCustomizations().get(0);

        Customization customization2 = diagramDescription2.getDefaultLayer().getCustomization();
        vsmElementCustomizationReuse1 = (VSMElementCustomizationReuse) customization2.getVsmElementCustomizations().get(0);
        vsmElementCustomizationReuse2 = (VSMElementCustomizationReuse) customization2.getVsmElementCustomizations().get(1);
    }

    /**
     * Test that validation pass successfully.
     */
    public void testCustomizationValidation() {
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should be successful", IStatus.OK, diagnostic.getSeverity());
    }

    /**
     * Test that validation pass successfully with diagram extension using
     * regular expression with only custom style or decorations inside his
     * additional layers.
     */
    public void testDiagramExtensionRegexWithOnlyCustomStyle() {
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(groupB);
        assertEquals("Validation should be successful", IStatus.OK, diagnostic.getSeverity());
    }

    /**
     * Test that validation detect diagram extension using regular expression
     * with not only custom style inside its additional layers and then that the
     * same diagram extension but without regular expression is OK.
     */
    public void testDiagramExtensionRegexWithNotOnlyCustumStyle() {
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(groupA);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        // Replace the regular expression by a standard Viewpoint URI
        groupA.getOwnedViewpoints().get(0).getOwnedRepresentationExtensions().get(0).setViewpointURI("viewpoint:/StyleCustomizations/StyleCustomizations");
        diagnostic = diagnostician.validate(groupA);
        assertEquals("Validation should be successful", IStatus.OK, diagnostic.getSeverity());
    }

    /**
     * Test the {@link EmptyAppliedOnListConstraint} that fails if the appliedOn
     * list is empty and applyOnAll is false.
     */
    public void testEmptyAppliedOnValidation() {
        // Get the EStructuralFeatureCustomization to check
        EStructuralFeatureCustomization toCheck = ((VSMElementCustomization) ((DiagramExtensionDescription) groupB.getOwnedViewpoints().get(0).getOwnedRepresentationExtensions().get(0)).getLayers()
                .get(0).getCustomization().getVsmElementCustomizations().get(0)).getFeatureCustomizations().get(0);
        // Set to false the applyOnAll, so that this customization become
        // invalid.
        toCheck.setApplyOnAll(false);
        // Validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(toCheck);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
    }

    /**
     * Test that validation detects {@link EAttributeCustomization} with
     * appliedOn not having all the labelSize attribute.
     */
    public void testIncorrectEAttributeCustomizationAppliedOnValidation() {
        // Add a style description element not having the labelSize attribute to
        // have validation detects this issue
        GaugeCompositeStyleDescription gaugeCompositeStyleDescription = (GaugeCompositeStyleDescription) diagramDescription1.getDefaultLayer().getContainerMappings().get(1).getSubNodeMappings().get(0)
                .getStyle();
        GaugeSectionDescription gaugeSectionDescription = gaugeCompositeStyleDescription.getSections().get(0);
        eAttributeCustomization.getAppliedOn().add(gaugeSectionDescription);

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 1, diagnostic.getChildren().size());
        diagnostic = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", eAttributeCustomization, diagnostic.getData().get(0));
    }

    /**
     * Test that validation detects {@link EReferenceCustomization} with
     * appliedOn not having all the backgroundColor attribute.
     */
    public void testIncorrectEReferenceCustomizationAppliedOnValidation() {
        // Add a style description element not having the backgroundColor
        // reference to
        // have validation detects this issue
        GaugeCompositeStyleDescription gaugeCompositeStyleDescription = (GaugeCompositeStyleDescription) diagramDescription1.getDefaultLayer().getContainerMappings().get(1).getSubNodeMappings().get(0)
                .getStyle();
        eReferenceCustomization.getAppliedOn().add(gaugeCompositeStyleDescription);

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 1, diagnostic.getChildren().size());
        diagnostic = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", eReferenceCustomization, diagnostic.getData().get(0));
    }

    /**
     * Test that validation detects {@link EAttributeCustomization} with
     * attributeName not existing in all referenced style description elements.
     */
    public void testIncorrectEAttributeCustomizationAttributeNameValidation() {
        // Change the attributeName from labelSize to
        // GaugeCompositeStyleDescription.alignment to
        // have validation detects this issue
        eAttributeCustomization.setAttributeName(StylePackage.Literals.GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT.getName());

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 2, diagnostic.getChildren().size());
        Diagnostic subDiagnostic1 = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", eAttributeCustomization, subDiagnostic1.getData().get(0));
        Diagnostic subDiagnostic2 = diagnostic.getChildren().get(1);
        assertEquals("Validation should fail", vsmElementCustomizationReuse1, subDiagnostic2.getData().get(0));
    }

    /**
     * Test that validation detects {@link EReferenceCustomization} with
     * referenceName not existing in all referenced style description elements.
     */
    public void testIncorrectEReferenceCustomizationReferenceNameValidation() {
        // Change the referenceName from backgroundColor to
        // FlatContainerStyleDescription.labelBorderStyle to
        // have validation detects this issue
        eReferenceCustomization.setReferenceName(StylePackage.Literals.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE.getName());

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 2, diagnostic.getChildren().size());
        Diagnostic subDiagnostic1 = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", eReferenceCustomization, subDiagnostic1.getData().get(0));
        Diagnostic subDiagnostic2 = diagnostic.getChildren().get(1);
        assertEquals("Validation should fail", vsmElementCustomizationReuse2, subDiagnostic2.getData().get(0));
    }

    /**
     * Test that validation detects {@link VSMElementCustomizationReuse} reusing
     * a {@link EAttributeCustomization} with appliedOn not having all the
     * labelSize attribute.
     */
    public void testIncorrectVSMElementCustomizationReuseReusingEAttributeCustomizationValidation() {
        // Add a style description element not having the labelSize
        // attribute to
        // have validation detects this issue
        EdgeStyleDescription edgeStyleDescription = diagramDescription1.getDefaultLayer().getEdgeMappings().get(0).getStyle();
        vsmElementCustomizationReuse1.getAppliedOn().add(edgeStyleDescription);

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 1, diagnostic.getChildren().size());
        diagnostic = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", vsmElementCustomizationReuse1, diagnostic.getData().get(0));
    }

    /**
     * Test that validation detects {@link VSMElementCustomizationReuse} reusing
     * a {@link EReferenceCustomization} with appliedOn not having all the
     * backgroundColor attribute.
     */
    public void testIncorrectVSMElementCustomizationReuseReusingEReferenceCustomizationValidation() {
        // Add a style description element not having the backgroundColor
        // reference to
        // have validation detects this issue
        EdgeStyleDescription edgeStyleDescription = diagramDescription1.getDefaultLayer().getEdgeMappings().get(0).getStyle();
        vsmElementCustomizationReuse2.getAppliedOn().add(edgeStyleDescription);

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 1, diagnostic.getChildren().size());
        diagnostic = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", vsmElementCustomizationReuse2, diagnostic.getData().get(0));
    }

    /**
     * Test that validation detects {@link VSMElementCustomizationReuse} reusing
     * a {@link EReferenceCustomization} in addition to the
     * {@link EAttributeCustomization} with appliedOn not having both EAttribute
     * name and EReference name.
     */
    public void testIncorrectVSMElementCustomizationReuseReusingBothEStructuralFeatureCustomizationValidation1() {
        // Add a EReferenceCustomization to reused
        // EStructuralFeatureCustomizations to
        // have validation detects the issue that the appliedOn have not both
        // EAttribute name and EReference name
        vsmElementCustomizationReuse1.getReuse().add(eReferenceCustomization);

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 1, diagnostic.getChildren().size());
        diagnostic = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", vsmElementCustomizationReuse1, diagnostic.getData().get(0));
    }

    /**
     * Test that validation detects {@link VSMElementCustomizationReuse} reusing
     * a {@link EAttributeCustomization} in addition to the
     * {@link EReferenceCustomization} with appliedOn not having both EAttribute
     * name and EReference name.
     */
    public void testIncorrectVSMElementCustomizationReuseReusingBothEStructuralFeatureCustomizationValidation2() {
        // Add a EAttributeCustomization to reused
        // EStructuralFeatureCustomizations to
        // have validation detects the issue that the appliedOn have not both
        // EAttribute name and EReference name
        vsmElementCustomizationReuse2.getReuse().add(eAttributeCustomization);
        GaugeCompositeStyleDescription gaugeCompositeStyleDescription = (GaugeCompositeStyleDescription) diagramDescription1.getDefaultLayer().getContainerMappings().get(1).getSubNodeMappings().get(0)
                .getStyle();
        GaugeSectionDescription gaugeSectionDescription = gaugeCompositeStyleDescription.getSections().get(0);
        vsmElementCustomizationReuse2.getAppliedOn().clear();
        vsmElementCustomizationReuse2.getAppliedOn().add(gaugeSectionDescription);

        // Test validate
        Diagnostician diagnostician = new Diagnostician();
        Diagnostic diagnostic = diagnostician.validate(group);
        assertEquals("Validation should fail", IStatus.ERROR, diagnostic.getSeverity());
        assertEquals("Validation should fail", 1, diagnostic.getChildren().size());
        diagnostic = diagnostic.getChildren().get(0);
        assertEquals("Validation should fail", vsmElementCustomizationReuse2, diagnostic.getData().get(0));
    }

    @Override
    protected void tearDown() throws Exception {
        group = null;
        diagramDescription1 = null;
        diagramDescription2 = null;
        eAttributeCustomization = null;
        eReferenceCustomization = null;
        vsmElementCustomizationReuse1 = null;
        vsmElementCustomizationReuse2 = null;
        super.tearDown();
    }
}
