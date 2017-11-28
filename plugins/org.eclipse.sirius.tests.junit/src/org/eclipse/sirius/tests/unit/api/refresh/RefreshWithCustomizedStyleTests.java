/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.refresh;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.ui.business.internal.query.CustomizableQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.ui.IEditorPart;

/**
 * Test the style customization features with conditional style description.
 * 
 * See VP-3535.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshWithCustomizedStyleTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/VP-3535/";

    private static final String MODELER_RESOURCE_NAME = "VP-3535.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3535.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-3535.aird";

    private static final String IMAGE_NAME = "image.bmp";

    private static final String NEW_IMAGE_NAME = "image.jpg";

    private Map<EStructuralFeature, EStructuralFeature> styleFeatureToDescriptionFeatureMap;

    private DDiagram dDiagram;

    private DDiagramElement eClass1WithSquareStyleDNode;

    private DDiagramElement eClass1WithLozengeStyleDNode;

    private DDiagramElement eClass1WithEllipseStyleDNode;

    private DDiagramElement eClass1WithBundledImageStyleDNode;

    private DDiagramElement eClass1WithNoteStyleDNode;

    private DDiagramElement eClass1WithDotStyleDNode;

    private DDiagramElement eClass1WithGaugeStyleDNode;

    private DDiagramElement eClass1WithWorkspaceImageStyleDNode;

    private DDiagramElement package1WithFlatContainerStyleDContainer;

    private DDiagramElement package1WithShapeContainerStyleDContainer;

    private DDiagramElement package1WithWorkspaceImageStyleDContainer;

    private DDiagramElement superTypeDEdge;

    private DDiagramElement referenceDEdge;

    private IEditorPart editorPart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        styleFeatureToDescriptionFeatureMap = new HashMap<EStructuralFeature, EStructuralFeature>();
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.ELLIPSE__HORIZONTAL_DIAMETER, StylePackage.Literals.ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.ELLIPSE__VERTICAL_DIAMETER, StylePackage.Literals.ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.GAUGE_SECTION__MIN, StylePackage.Literals.GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.GAUGE_SECTION__MAX, StylePackage.Literals.GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.GAUGE_SECTION__VALUE, StylePackage.Literals.GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.EDGE_STYLE__SIZE, StylePackage.Literals.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.EDGE_STYLE__BEGIN_LABEL_STYLE, StylePackage.Literals.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.EDGE_STYLE__CENTER_LABEL_STYLE, StylePackage.Literals.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.EDGE_STYLE__END_LABEL_STYLE, StylePackage.Literals.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION);
        styleFeatureToDescriptionFeatureMap.put(DiagramPackage.Literals.EDGE_STYLE__CENTERED, StylePackage.Literals.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + IMAGE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + NEW_IMAGE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + NEW_IMAGE_NAME);

        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SESSION_RESOURCE_NAME);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        dDiagram = (DDiagram) DialectManager.INSTANCE.getAllRepresentations(session).iterator().next();

        eClass1WithSquareStyleDNode = dDiagram.getNodes().get(0);
        eClass1WithLozengeStyleDNode = dDiagram.getNodes().get(0);
        eClass1WithEllipseStyleDNode = dDiagram.getNodes().get(0);
        eClass1WithBundledImageStyleDNode = dDiagram.getNodes().get(0);
        eClass1WithNoteStyleDNode = dDiagram.getNodes().get(0);
        eClass1WithDotStyleDNode = dDiagram.getNodes().get(0);
        eClass1WithGaugeStyleDNode = dDiagram.getNodes().get(0);
        eClass1WithWorkspaceImageStyleDNode = dDiagram.getNodes().get(0);

        package1WithFlatContainerStyleDContainer = dDiagram.getContainers().get(0);
        package1WithShapeContainerStyleDContainer = dDiagram.getContainers().get(1);
        package1WithWorkspaceImageStyleDContainer = dDiagram.getContainers().get(2);

        superTypeDEdge = dDiagram.getEdges().get(0);
        referenceDEdge = dDiagram.getEdges().get(1);

        editorPart = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

    }

    /**
     * Test all square style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testSquareStyleCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithSquareStyleDNode);
        }
    }

    /**
     * Test all lozenge style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testLozengeStyleCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithLozengeStyleDNode);
        }
    }

    /**
     * Test all ellipse style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testEllipseStyleCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithEllipseStyleDNode);
        }
    }

    /**
     * Test all bundled image style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testBundledImageStyleCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithBundledImageStyleDNode);
        }
    }

    /**
     * Test all note style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testNoteStyleCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithNoteStyleDNode);
        }
    }

    /**
     * Test all dot style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testDotStyleCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithDotStyleDNode);
        }
    }

    /**
     * Test all gauge composite style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testGaugeCompositeStyleCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithGaugeStyleDNode);
        }
    }

    /**
     * Test all workspace image style features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testWorkspaceImageStyleOnNodeCustomization() throws Exception {
        if (!TestsUtil.shouldSkipLongTests()) {
            testStyleCustomization(eClass1WithWorkspaceImageStyleDNode);
        }
    }

    /**
     * Test all flatContainerStyle features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testFlatContainerStyleCustomization() throws Exception {
        testStyleCustomization(package1WithFlatContainerStyleDContainer);
    }

    /**
     * Test all shapeContainerStyle features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testShapeContainerStyleCustomization() throws Exception {
        testStyleCustomization(package1WithShapeContainerStyleDContainer);
    }

    /**
     * Test all workspaceImageStyle features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testWorkspaceImageStyleOnContainerCustomization() throws Exception {
        testStyleCustomization(package1WithWorkspaceImageStyleDContainer);
    }

    /**
     * Test all edgeStyle features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testEdgeStyleCustomization() throws Exception {
        testStyleCustomization(referenceDEdge);
    }

    /**
     * Test all bracketEdgeStyle features customization.
     * 
     * @throws Exception
     *             thrown cause failure
     */
    public void testBracketEdgeStyleCustomization() throws Exception {
        testStyleCustomization(superTypeDEdge);
    }

    private void testStyleCustomization(DDiagramElement dDiagramElement) throws Exception {
        Style originalStyle = dDiagramElement.getStyle();
        CustomizableQuery customizableQuery = new CustomizableQuery(originalStyle);
        Collection<String> customizableFeatureNames = customizableQuery.getCustomizableFeatureNames();
        Collection<ConditionalStyleDescription> conditionalStyleDescriptions = getConditionalStyleDescriptions(dDiagramElement);
        for (String customizableFeatureName : customizableFeatureNames) {
            EStructuralFeature feature = originalStyle.eClass().getEStructuralFeature(customizableFeatureName);
            customizeFeature(dDiagramElement, feature);

            Object newValue = originalStyle.eGet(feature);
            String assertMessage = "Only one feature should be customized : " + customizableFeatureName;
            assertEquals(assertMessage, 1, originalStyle.getCustomFeatures().size());
            assertEquals(assertMessage, customizableFeatureName, originalStyle.getCustomFeatures().get(0));

            for (ConditionalStyleDescription conditionalStyleDescription : conditionalStyleDescriptions) {
                enableConditionalStyle(conditionalStyleDescription);
                StyleDescription newStyleDescription = getOwnedStyleDescription(conditionalStyleDescription);
                assertEquals(assertMessage, 1, originalStyle.getCustomFeatures().size());
                assertEquals(assertMessage, customizableFeatureName, originalStyle.getCustomFeatures().get(0));
                assertEquals("The original style should yet exists because we haven't yet refreshed the diagram", originalStyle, dDiagramElement.getStyle());

                // Check that a refresh doesn't change customizations
                refresh(dDiagram);
                TestsUtil.synchronizationWithUIThread();

                Style newStyle = dDiagramElement.getStyle();
                // If we customize the WorkspaceImage.workspacePath feature the
                // the refresh doesn't apply other style because the
                // WorkspaceImage is considered as customized itself
                if (!isWorkspaceImageWithWorkspacePathFeatureCustomized(originalStyle)) {
                    assertNotSame("customization of " + customizableFeatureName + ", A refresh should create a new style corresponding to the conditional style description : " + newStyleDescription,
                            originalStyle, newStyle);
                } else {
                    assertEquals("customization of " + customizableFeatureName + ", A refresh should not create a new style corresponding to the conditional style description : "
                            + newStyleDescription + " because it is a WorkspaceImage with workspacePath feature customized", originalStyle, newStyle);
                }
                EStructuralFeature correspondingFeature = getCorrespondingFeature(feature, originalStyle, newStyle);
                // TODO : make a mapping between Square.color and
                // Dot.backgroundColor
                if (correspondingFeature != null) {
                    assertEquals("The originally customized feature " + correspondingFeature.getName() + " value should be keeped with the new StyleDescription : " + newStyleDescription, newValue,
                            newStyle.eGet(correspondingFeature));
                    // Test that others features not customized are same value
                    // as
                    // before
                    if (!isWorkspaceImageWithWorkspacePathFeatureCustomized(originalStyle)) {
                        Collection<String> otherFeatureNames = new CustomizableQuery(newStyle).getCustomizableFeatureNames();
                        otherFeatureNames.remove(correspondingFeature.getName());
                        assertStyleFeatureValuesCorrespondToStyleDescriptionOne(otherFeatureNames, newStyle, newStyleDescription,
                                "The feature {0} value should corresponding to the new StyleDescription " + newStyleDescription);
                        assertEquals(assertMessage, newStyleDescription, newStyle.getDescription());
                    }
                    assertEquals(assertMessage, 1, newStyle.getCustomFeatures().size());
                    assertEquals(assertMessage, customizableFeatureName, newStyle.getCustomFeatures().get(0));

                    // Cancel style customizations
                    resetStylePropertiesToDefaultValues(dDiagramElement, dDiagram);

                    refresh(dDiagram);
                    TestsUtil.synchronizationWithUIThread();
                } else {
                    correspondingFeature = feature;
                }
                newStyle = dDiagramElement.getStyle();

                Collection<String> featureNames = new CustomizableQuery(newStyle).getCustomizableFeatureNames();
                assertStyleFeatureValuesCorrespondToStyleDescriptionOne(featureNames, newStyle, newStyleDescription,
                        "After style customizations reset we should be as at the beginning, the feature {0} value should corresponding to the new StyleDescription one " + newStyleDescription);
                assertEquals("After cancel custom style we should be as at the beginning", 0, dDiagramElement.getStyle().getCustomFeatures().size());

                resetConditionalStyle();
                refresh(dDiagram);
                TestsUtil.synchronizationWithUIThread();
                originalStyle = dDiagramElement.getStyle();
                correspondingFeature = getCorrespondingFeature(correspondingFeature, newStyle, originalStyle);
                customizeFeature(dDiagramElement, correspondingFeature);
                newValue = originalStyle.eGet(correspondingFeature);
            }
            resetStylePropertiesToDefaultValues(dDiagramElement, dDiagram);
            refresh(dDiagram);
            TestsUtil.synchronizationWithUIThread();
            originalStyle = dDiagramElement.getStyle();
        }
    }

    private boolean isWorkspaceImageWithWorkspacePathFeatureCustomized(Style style) {
        boolean isWorkspaceImageWithWorkspacePathFeatureCustomized = style instanceof WorkspaceImage
                && style.getCustomFeatures().contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
        return isWorkspaceImageWithWorkspacePathFeatureCustomized;
    }

    private void assertStyleFeatureValuesCorrespondToStyleDescriptionOne(Collection<String> featureNames, Style newStyle, StyleDescription newStyleDescription, String assertMessage) throws Exception {
        for (String featureName : featureNames) {
            EStructuralFeature newStyleFeature = newStyle.eClass().getEStructuralFeature(featureName);
            EStructuralFeature newStyleDescriptionFeature = newStyleDescription.eClass().getEStructuralFeature(featureName);
            if (newStyleDescriptionFeature == null) {
                newStyleDescriptionFeature = styleFeatureToDescriptionFeatureMap.get(newStyleFeature);
            }
            assertNotNull("A StyleDescription feature should corresponds to the style feature : " + featureName, newStyleDescriptionFeature);
            Object newStyleDescriptionFeatureValue = newStyleDescription.eGet(newStyleDescriptionFeature);

            if (DescriptionPackage.Literals.INTERPRETED_EXPRESSION == newStyleDescriptionFeature.getEType() && styleFeatureToDescriptionFeatureMap.containsKey(newStyleFeature)) {
                newStyleDescriptionFeatureValue = interpreter.evaluateInteger(((DSemanticDecorator) newStyle.eContainer()).getTarget(), (String) newStyleDescriptionFeatureValue);
            }
            Object newStyleFeatureValue = newStyle.eGet(newStyleFeature);
            if (newStyleFeature instanceof EStructuralFeature && newStyleDescriptionFeature instanceof EReference) {
                if (newStyleDescriptionFeatureValue instanceof EObject && newStyleFeatureValue instanceof EObject) {
                    EObject newStyleDescriptionFeatureEObjectValue = (EObject) newStyleDescriptionFeatureValue;
                    EObject newStyleFeatureEObjectValue = (EObject) newStyleFeatureValue;
                    if (newStyleFeatureEObjectValue instanceof Customizable) {
                        Customizable newStyleFeatureEObjectCustomizableValue = (Customizable) newStyleFeatureEObjectValue;
                        CustomizableQuery customizableQuery = new CustomizableQuery(newStyleFeatureEObjectCustomizableValue);
                        for (String newStyleFeatureEObjectCustomizableValueFeatureName : customizableQuery.getCustomizableFeatureNames()) {
                            EStructuralFeature newStyleFeatureEObjectValueFeature = newStyleFeatureEObjectValue.eClass().getEStructuralFeature(newStyleFeatureEObjectCustomizableValueFeatureName);
                            EStructuralFeature newStyleDescriptionFeatureEObjectValueFeature = newStyleDescriptionFeatureEObjectValue.eClass().getEStructuralFeature(
                                    newStyleFeatureEObjectCustomizableValueFeatureName);
                            Object descAttributeValue = newStyleDescriptionFeatureEObjectValue.eGet(newStyleDescriptionFeatureEObjectValueFeature);
                            Object attributeValue = newStyleFeatureEObjectCustomizableValue.eGet(newStyleFeatureEObjectValueFeature);
                            if (descAttributeValue instanceof EObject && attributeValue instanceof EObject) {
                                EObject descAttributeEObjectValue = (EObject) descAttributeValue;
                                EObject attributeEObjectValue = (EObject) attributeValue;
                                for (EAttribute eAttribute : attributeEObjectValue.eClass().getEAllAttributes()) {
                                    assertEquals(MessageFormat.format(assertMessage, featureName), descAttributeEObjectValue.eGet(eAttribute), attributeEObjectValue.eGet(eAttribute));
                                }
                            } else {
                                assertEquals(MessageFormat.format(assertMessage, featureName), descAttributeValue, attributeValue);
                            }
                        }
                    } else {
                        for (EAttribute eAttribute : newStyleFeatureEObjectValue.eClass().getEAllAttributes()) {
                            Object descAttributeValue = newStyleDescriptionFeatureEObjectValue.eGet(newStyleDescriptionFeatureEObjectValue.eClass().getEStructuralFeature(eAttribute.getName()));
                            Object attributeValue = newStyleFeatureEObjectValue.eGet(eAttribute);
                            assertEquals(MessageFormat.format(assertMessage, featureName), descAttributeValue, attributeValue);
                        }
                    }
                } else if (newStyleDescriptionFeatureValue instanceof List<?> && newStyleFeatureValue instanceof List<?>) {
                    List<?> newStyleDescriptionFeatureListValue = (List<?>) newStyleDescriptionFeatureValue;
                    List<?> newStyleFeatureListValue = (List<?>) newStyleFeatureValue;
                    assertEquals(MessageFormat.format(assertMessage, featureName), newStyleDescriptionFeatureListValue.size(), newStyleFeatureListValue.size());
                    for (int i = 0; i < newStyleDescriptionFeatureListValue.size(); i++) {
                        Object descValue = newStyleDescriptionFeatureListValue.get(i);
                        Object value = newStyleFeatureListValue.get(i);
                        if (descValue instanceof EObject && value instanceof EObject) {
                            EObject newStyleDescriptionFeatureEObjectValue = (EObject) descValue;
                            EObject newStyleFeatureEObjectValue = (EObject) value;
                            Collection<String> customizableFeatureNames = new ArrayList<String>();
                            if (newStyleFeatureEObjectValue instanceof Customizable) {
                                Customizable customizable = (Customizable) newStyleFeatureEObjectValue;
                                customizableFeatureNames = new CustomizableQuery(customizable).getCustomizableFeatureNames();
                            }
                            for (String customizableFeatureName : customizableFeatureNames) {
                                EStructuralFeature newSubStyleFeature = newStyleFeatureEObjectValue.eClass().getEStructuralFeature(customizableFeatureName);
                                EStructuralFeature newSubStyleDescriptionFeature = newStyleDescriptionFeatureEObjectValue.eClass().getEStructuralFeature(customizableFeatureName);
                                if (newSubStyleDescriptionFeature == null) {
                                    newSubStyleDescriptionFeature = styleFeatureToDescriptionFeatureMap.get(newSubStyleFeature);
                                }
                                assertNotNull("A StyleDescription feature should corresponds to the style feature : " + customizableFeatureName, newSubStyleDescriptionFeature);

                                Object newSubStyleDescriptionFeatureValue = newStyleDescriptionFeatureEObjectValue.eGet(newSubStyleDescriptionFeature);

                                if (DescriptionPackage.Literals.INTERPRETED_EXPRESSION == newSubStyleDescriptionFeature.getEType()
                                        && styleFeatureToDescriptionFeatureMap.containsKey(newSubStyleFeature)) {
                                    newSubStyleDescriptionFeatureValue = interpreter.evaluateInteger(((DSemanticDecorator) newStyle.eContainer()).getTarget(),
                                            (String) newSubStyleDescriptionFeatureValue);
                                }
                                Object newSubStyleFeatureValue = newStyleFeatureEObjectValue.eGet(newSubStyleFeature);
                                if (newSubStyleDescriptionFeatureValue instanceof EObject && newSubStyleFeatureValue instanceof EObject) {
                                    EObject descSubValue = (EObject) newSubStyleDescriptionFeatureValue;
                                    EObject subValue = (EObject) newSubStyleFeatureValue;
                                    for (EAttribute eAttribute : subValue.eClass().getEAllAttributes()) {
                                        Object descAttributeValue = descSubValue.eGet(eAttribute);
                                        Object attributeValue = subValue.eGet(eAttribute);
                                        assertEquals(MessageFormat.format(assertMessage, featureName), descAttributeValue, attributeValue);
                                    }
                                } else {
                                    assertEquals(MessageFormat.format(assertMessage, featureName), newSubStyleDescriptionFeatureValue, newSubStyleFeatureValue);
                                }
                            }
                        } else {
                            assertEquals(MessageFormat.format(assertMessage, featureName), descValue, value);
                        }
                    }
                } else if (newStyleDescriptionFeatureValue instanceof FixedColor && newStyleFeatureValue instanceof RGBValues) {
                    FixedColor colorFromDescription = (FixedColor) newStyleDescriptionFeatureValue;
                    assertEquals(RGBValues.create(colorFromDescription.getRed(), colorFromDescription.getGreen(), colorFromDescription.getBlue()), (RGBValues) newStyleFeatureValue);
                }
            } else {
                assertEquals(MessageFormat.format(assertMessage, featureName), convertToDiagramValues(newStyleDescriptionFeatureValue), newStyleFeatureValue);
            }
        }
    }

    private Object convertToDiagramValues(Object newStyleDescriptionFeatureValue) {
        if (newStyleDescriptionFeatureValue instanceof FixedColor) {
            FixedColor colorDef = (FixedColor) newStyleDescriptionFeatureValue;
            return RGBValues.create(colorDef.getRed(), colorDef.getGreen(), colorDef.getBlue());
        }
        return newStyleDescriptionFeatureValue;
    }

    private EStructuralFeature getCorrespondingFeature(EStructuralFeature feature, Style originalStyle, Style newStyle) {
        // TODO : find the corresponding feature between different style
        return newStyle.eClass().getEStructuralFeature(feature.getName());
    }

    private StyleDescription getOwnedStyleDescription(ConditionalStyleDescription conditionalStyleDescription) {
        StyleDescription ownedStyleDescription = null;
        if (conditionalStyleDescription instanceof ConditionalNodeStyleDescription) {
            ConditionalNodeStyleDescription conditionalNodeStyleDescription = (ConditionalNodeStyleDescription) conditionalStyleDescription;
            ownedStyleDescription = conditionalNodeStyleDescription.getStyle();
        } else if (conditionalStyleDescription instanceof ConditionalContainerStyleDescription) {
            ConditionalContainerStyleDescription conditionalContainerStyleDescription = (ConditionalContainerStyleDescription) conditionalStyleDescription;
            ownedStyleDescription = conditionalContainerStyleDescription.getStyle();
        } else if (conditionalStyleDescription instanceof ConditionalEdgeStyleDescription) {
            ConditionalEdgeStyleDescription conditionalEdgeStyleDescription = (ConditionalEdgeStyleDescription) conditionalStyleDescription;
            ownedStyleDescription = conditionalEdgeStyleDescription.getStyle();
        }
        return ownedStyleDescription;
    }

    private void enableConditionalStyle(ConditionalStyleDescription conditionalStyleDescription) {
        String id = conditionalStyleDescription.getPredicateExpression().split("\'")[1];
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command enableConditionalStyleCmd = SetCommand.create(domain, semanticModel, EcorePackage.Literals.EPACKAGE__NS_URI, id);
        domain.getCommandStack().execute(enableConditionalStyleCmd);
    }

    private void resetConditionalStyle() {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command enableConditionalStyleCmd = SetCommand.create(domain, semanticModel, EcorePackage.Literals.EPACKAGE__NS_URI, null);
        domain.getCommandStack().execute(enableConditionalStyleCmd);
    }

    private Collection<ConditionalStyleDescription> getConditionalStyleDescriptions(DDiagramElement dDiagramElement) {
        Collection<ConditionalStyleDescription> conditionalStyleDescriptions = new ArrayList<ConditionalStyleDescription>();
        DiagramElementMapping diagramElementMapping = dDiagramElement.getDiagramElementMapping();
        if (diagramElementMapping instanceof NodeMapping) {
            NodeMapping nodeMapping = (NodeMapping) diagramElementMapping;
            conditionalStyleDescriptions.addAll(nodeMapping.getConditionnalStyles());
        } else if (diagramElementMapping instanceof ContainerMapping) {
            ContainerMapping containerMapping = (ContainerMapping) diagramElementMapping;
            conditionalStyleDescriptions.addAll(containerMapping.getConditionnalStyles());
        } else if (diagramElementMapping instanceof EdgeMapping) {
            EdgeMapping edgeMapping = (EdgeMapping) diagramElementMapping;
            conditionalStyleDescriptions.addAll(edgeMapping.getConditionnalStyles());
        }
        return conditionalStyleDescriptions;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void customizeFeature(DDiagramElement dDiagramElement, EStructuralFeature feature) {
        Style style = dDiagramElement.getStyle();
        Object currentValue = style.eGet(feature);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        if (feature instanceof EAttribute) {
            if (feature.getEType() == ViewpointPackage.Literals.RGB_VALUES) {
                RGBValues red = RGBValues.create(255, 0, 0);
                Command customizeStyleCmd = SetCommand.create(domain, style, feature, red);
                domain.getCommandStack().execute(customizeStyleCmd);
            } else if (feature.getEType() instanceof EEnum && currentValue instanceof List && ((List) currentValue).isEmpty()) {
                List newList = new ArrayList<>();
                EEnum eEnum = (EEnum) feature.getEType();
                for (EEnumLiteral literal : eEnum.getELiterals()) {
                    Enumerator newEnumerator = literal.getInstance();
                    newList.add(newEnumerator);
                }
                Command customizeStyleCmd = SetCommand.create(domain, style, feature, newList);
                domain.getCommandStack().execute(customizeStyleCmd);
            } else if (feature.getEType() instanceof EEnum && currentValue instanceof Enumerator) {
                EEnum eEnum = (EEnum) feature.getEType();
                Enumerator enumerator = (Enumerator) currentValue;
                int newEnumeratorIndex = (eEnum.getELiterals().indexOf(eEnum.getEEnumLiteral(enumerator.getName())) + 1) % eEnum.getELiterals().size();
                Enumerator newEnumerator = eEnum.getELiterals().get(newEnumeratorIndex).getInstance();
                Command customizeStyleCmd = SetCommand.create(domain, style, feature, newEnumerator);
                domain.getCommandStack().execute(customizeStyleCmd);
            } else if (feature.getEType() == EcorePackage.Literals.EBOOLEAN) {
                Boolean currentBoolean = (Boolean) currentValue;
                Boolean newBoolean = !currentBoolean;
                Command customizeStyleCmd = SetCommand.create(domain, style, feature, newBoolean);
                domain.getCommandStack().execute(customizeStyleCmd);
            } else if (feature == ViewpointPackage.Literals.BASIC_LABEL_STYLE__ICON_PATH || feature == DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH) {
                Command customizeStyleCmd = SetCommand.create(domain, style, feature, "/" + TEMPORARY_PROJECT_NAME + "/" + NEW_IMAGE_NAME);
                domain.getCommandStack().execute(customizeStyleCmd);
            } else if (feature == DiagramPackage.Literals.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION) {
                Command customizeStyleCmd = SetCommand.create(domain, style, feature, "10");
                domain.getCommandStack().execute(customizeStyleCmd);
            } else if (feature.getEType() == EcorePackage.Literals.EINTEGER_OBJECT || feature.getEType() == EcorePackage.Literals.EINT) {
                int currentIntValue = (Integer) currentValue;
                Command customizeStyleCmd = SetCommand.create(domain, style, feature, currentIntValue + 1);
                domain.getCommandStack().execute(customizeStyleCmd);
            }
        } else if (feature instanceof EReference) {
            if (currentValue instanceof EObject) {
                EObject currentEObjectValue = (EObject) currentValue;
                EAttribute eAttribute = currentEObjectValue.eClass().getEAllAttributes().get(0);
                if (eAttribute.getEType() == EcorePackage.Literals.EINT) {
                    int attributeValue = (Integer) currentEObjectValue.eGet(eAttribute);
                    Command customizeStyleCmd = SetCommand.create(domain, currentEObjectValue, eAttribute, attributeValue + 1);
                    domain.getCommandStack().execute(customizeStyleCmd);
                }
            }
        }
        Command customizeFeatureCmd = AddCommand.create(domain, style, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, feature.getName());
        domain.getCommandStack().execute(customizeFeatureCmd);
    }

    @Override
    protected void tearDown() throws Exception {
        styleFeatureToDescriptionFeatureMap = null;
        dDiagram = null;
        eClass1WithSquareStyleDNode = null;
        eClass1WithLozengeStyleDNode = null;
        eClass1WithEllipseStyleDNode = null;
        eClass1WithBundledImageStyleDNode = null;
        eClass1WithNoteStyleDNode = null;
        eClass1WithDotStyleDNode = null;
        eClass1WithGaugeStyleDNode = null;
        eClass1WithWorkspaceImageStyleDNode = null;

        package1WithFlatContainerStyleDContainer = null;
        package1WithShapeContainerStyleDContainer = null;
        package1WithWorkspaceImageStyleDContainer = null;

        superTypeDEdge = null;
        referenceDEdge = null;
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
        editorPart = null;
        super.tearDown();
    }
}
