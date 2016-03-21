/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.refresh.customization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.color.AbstractColorUpdater;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.GetDefaultStyle;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.IVSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.ui.IEditorPart;

/**
 * A test case to test style description customization.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshWithCustomizationTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/StyleCustomizations/";

    private static final String MODELER_RESOURCE_NAME = "StyleCustomizations.odesign";

    private static final String MODELER_EXTA_RESOURCE_NAME = "StyleCustomizations_ExtensionA.odesign";

    private static final String MODELER_EXTB_RESOURCE_NAME = "StyleCustomizations_ExtensionB.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "StyleCustomizations.ecore";

    private static final String SESSION_RESOURCE_NAME = "StyleCustomizations.aird";

    private DDiagram dDiagram;

    private DNodeContainer p1;

    private DNodeList eClass1;

    private DNodeList eClass11;

    private DNodeListElement eClass1Attribute;

    private DNodeListElement eClass11Attribute;

    private DEdge dEdge;

    private DiagramDescription diagramDescription;

    private AdditionalLayer layerContributingCustomization;

    private VSMElementCustomization vsmElementCustomization1;

    private VSMElementCustomization vsmElementCustomization2;

    private EAttributeCustomization eAttributeCustomizationLabelSize;

    private EAttributeCustomization eAttributeCustomizationLabelFormat;

    private EReferenceCustomization eReferenceCustomization;

    private IEditorPart diagramEditor;

    private DDiagram dDiagramBis;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Collection<String> modelerList = new ArrayList<String>();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_EXTA_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_EXTA_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_EXTB_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_EXTB_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
        Collections.addAll(modelerList, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_EXTA_RESOURCE_NAME,
                TEMPORARY_PROJECT_NAME + "/" + MODELER_EXTB_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), modelerList, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        Iterator<DRepresentation> iterator = DialectManager.INSTANCE.getAllRepresentations(session).iterator();
        dDiagram = (DDiagram) iterator.next();
        dDiagramBis = (DDiagram) iterator.next();
        p1 = (DNodeContainer) dDiagram.getOwnedDiagramElements().get(0);
        eClass1 = (DNodeList) dDiagram.getOwnedDiagramElements().get(1);
        eClass11 = (DNodeList) p1.getOwnedDiagramElements().get(0);
        eClass1Attribute = eClass1.getOwnedElements().get(0);
        eClass11Attribute = eClass11.getOwnedElements().get(0);
        dEdge = dDiagram.getEdges().get(0);

        diagramDescription = dDiagram.getDescription();
        layerContributingCustomization = diagramDescription.getAdditionalLayers().get(0);
        Customization customization = layerContributingCustomization.getCustomization();
        List<IVSMElementCustomization> vsmElementCustomizations = customization.getVsmElementCustomizations();
        vsmElementCustomization1 = (VSMElementCustomization) vsmElementCustomizations.get(0);
        vsmElementCustomization2 = (VSMElementCustomization) vsmElementCustomizations.get(1);
        eAttributeCustomizationLabelSize = (EAttributeCustomization) vsmElementCustomization1.getFeatureCustomizations().get(0);
        eAttributeCustomizationLabelFormat = (EAttributeCustomization) vsmElementCustomization1.getFeatureCustomizations().get(1);
        eReferenceCustomization = (EReferenceCustomization) vsmElementCustomization2.getFeatureCustomizations().get(0);

        diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test a style description element {@link EAttribute} customization through
     * a {@link EAttributeCustomization}.
     */
    public void testEAttributeStyleCustomization() {
        Integer edgeBeginStyleOriginalLabelSize = dEdge.getOwnedStyle().getBeginLabelStyle().getLabelSize();
        Integer edgeCenterStyleOriginalLabelSize = dEdge.getOwnedStyle().getCenterLabelStyle().getLabelSize();
        Integer edgeEndStyleOriginalLabelSize = dEdge.getOwnedStyle().getEndLabelStyle().getLabelSize();

        RGBValues p1OriginalBackgroundColor = (RGBValues) p1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        RGBValues eClass1OriginalBackgroundColor = (RGBValues) eClass1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        RGBValues eClass11OriginalBackgroundColor = (RGBValues) eClass11.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        RGBValues eClass1AttributeOriginalBackgroundColor = (RGBValues) ((GaugeCompositeStyle) eClass1Attribute.getStyle()).getSections().get(0)
                .eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR);
        RGBValues eClass11AttributeOriginalBackgroundColor = (RGBValues) ((GaugeCompositeStyle) eClass11Attribute.getStyle()).getSections().get(0)
                .eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR);

        // Disable the EReferenceCustomization (enabled by default)
        enableVSMElementCustomization(vsmElementCustomization2, false);
        // Activate layer that contains customizations
        activateLayer(dDiagram, layerContributingCustomization.getName());
        TestsUtil.synchronizationWithUIThread();

        String assertMessage = "The new label size must be that of the EAttributeCustomization.value";
        Integer customizedLabelSize = Integer.valueOf(eAttributeCustomizationLabelSize.getValue());
        FontFormat customizedLabelFormat = FontFormat.get(eAttributeCustomizationLabelFormat.getValue());
        List<FontFormat> fontFormat = new ArrayList<FontFormat>();
        fontFormat.add(customizedLabelFormat);
        assertEquals(assertMessage, customizedLabelSize, p1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass11.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass1Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass11Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertMessage = "The new label Font Format must be that of the EAttributeCustomization.value";
        assertEquals(assertMessage, fontFormat, eClass11Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT));
        assertMessage = "As the edge labels are not customized, their size must be unchanged";
        assertEquals(assertMessage, edgeBeginStyleOriginalLabelSize, dEdge.getOwnedStyle().getBeginLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, edgeCenterStyleOriginalLabelSize, dEdge.getOwnedStyle().getCenterLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, edgeEndStyleOriginalLabelSize, dEdge.getOwnedStyle().getEndLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));

        assertMessage = "The backgroundColor mustn't have changed as the EReferenceCustomization is disabled with the predicateExpression";
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(p1OriginalBackgroundColor, (RGBValues) p1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(eClass1OriginalBackgroundColor, (RGBValues) eClass1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage,
                AbstractColorUpdater.areEquals(eClass11OriginalBackgroundColor, (RGBValues) eClass11.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(eClass1AttributeOriginalBackgroundColor,
                (RGBValues) ((GaugeCompositeStyle) eClass1Attribute.getStyle()).getSections().get(0).eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(eClass11AttributeOriginalBackgroundColor,
                (RGBValues) ((GaugeCompositeStyle) eClass11Attribute.getStyle()).getSections().get(0).eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR)));

        session.save(new NullProgressMonitor());
        refresh(dDiagram);
        assertEquals("A new refresh call should not change the session status", SessionStatus.SYNC, session.getStatus());

    }

    public void testMultiValuatedEAttribute() {
        changeEAttributeCustomization(eAttributeCustomizationLabelFormat, "aql:OrderedSet{viewpoint::FontFormat::bold, viewpoint::FontFormat::italic}");
        // Disable the EReferenceCustomization (enabled by default)
        vsmElementCustomization1 = (VSMElementCustomization) EcoreUtil.resolve(vsmElementCustomization1, session.getTransactionalEditingDomain().getResourceSet());
        vsmElementCustomization2 = (VSMElementCustomization) EcoreUtil.resolve(vsmElementCustomization2, session.getTransactionalEditingDomain().getResourceSet());
        enableVSMElementCustomization(vsmElementCustomization2, false);
        layerContributingCustomization = (AdditionalLayer) EcoreUtil.resolve(layerContributingCustomization, session.getTransactionalEditingDomain().getResourceSet());
        // Activate layer that contains customizations
        activateLayer(dDiagram, layerContributingCustomization.getName());
        TestsUtil.synchronizationWithUIThread();
        eAttributeCustomizationLabelFormat = (EAttributeCustomization) vsmElementCustomization1.getFeatureCustomizations().get(1);

        List<FontFormat> fontFormat = new ArrayList<FontFormat>();
        fontFormat.add(FontFormat.ITALIC_LITERAL);
        fontFormat.add(FontFormat.BOLD_LITERAL);
        String assertMessage = "The new label Font Format must be that of the EAttributeCustomization.value interpretation result";
        assertEquals(assertMessage, fontFormat, eClass11Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT));
    }

    /**
     * Test a style description element {@link EReference} customization through
     * a {@link EReferenceCustomization}.
     */
    public void testEReferenceStyleCustomization() {
        Integer p1OriginalLabelSize = (Integer) p1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass1OriginalLabelSize = (Integer) eClass1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass11OriginalLabelSize = (Integer) eClass11.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass1AttributeOriginalLabelSize = (Integer) eClass1Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass11AttributeOriginalLabelSize = (Integer) eClass11Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer edgeBeginStyleOriginalLabelSize = dEdge.getOwnedStyle().getBeginLabelStyle().getLabelSize();
        Integer edgeCenterStyleOriginalLabelSize = dEdge.getOwnedStyle().getCenterLabelStyle().getLabelSize();
        Integer edgeEndStyleOriginalLabelSize = dEdge.getOwnedStyle().getEndLabelStyle().getLabelSize();

        // Disable the EAttributeCustomization (enabled by default)
        enableVSMElementCustomization(vsmElementCustomization1, false);
        // Activate layer that contains customizations
        activateLayer(dDiagram, layerContributingCustomization.getName());
        TestsUtil.synchronizationWithUIThread();

        String assertMessage = "The label size mustn't be changed";
        assertEquals(assertMessage, p1OriginalLabelSize, p1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, eClass1OriginalLabelSize, eClass1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, eClass11OriginalLabelSize, eClass11.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, eClass1AttributeOriginalLabelSize, eClass1Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, eClass11AttributeOriginalLabelSize, eClass11Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertMessage = "As the edge labels are not customized, their size must be unchanged";
        assertEquals(assertMessage, edgeBeginStyleOriginalLabelSize, dEdge.getOwnedStyle().getBeginLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, edgeCenterStyleOriginalLabelSize, dEdge.getOwnedStyle().getCenterLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, edgeEndStyleOriginalLabelSize, dEdge.getOwnedStyle().getEndLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));

        assertMessage = "The backgroundColor must have changed to EReferenceCustomization.value";
        RGBValues customizedRGBValues = new AbstractColorUpdater().getRGBValuesFromColorDescription(null, (ColorDescription) eReferenceCustomization.getValue());
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues, (RGBValues) p1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues, (RGBValues) eClass1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues, (RGBValues) eClass11.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues,
                (RGBValues) ((GaugeCompositeStyle) eClass1Attribute.getStyle()).getSections().get(0).eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues,
                (RGBValues) ((GaugeCompositeStyle) eClass11Attribute.getStyle()).getSections().get(0).eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR)));

        session.save(new NullProgressMonitor());
        refresh(dDiagram);
        assertEquals("A new refresh call should not change the session status", SessionStatus.SYNC, session.getStatus());
    }

    /**
     * Test a style description element {@link EReference} and
     * {@link EAttribute} customization .
     */
    public void testEAttributeAndEReferenceStyleCustomization() {
        Integer edgeBeginStyleOriginalLabelSize = dEdge.getOwnedStyle().getBeginLabelStyle().getLabelSize();
        Integer edgeCenterStyleOriginalLabelSize = dEdge.getOwnedStyle().getCenterLabelStyle().getLabelSize();
        Integer edgeEndStyleOriginalLabelSize = dEdge.getOwnedStyle().getEndLabelStyle().getLabelSize();

        activateLayer(dDiagram, layerContributingCustomization.getName());
        TestsUtil.synchronizationWithUIThread();

        String assertMessage = "The new label size must be that of the EAttributeCustomization.value";
        Integer customizedLabelSize = Integer.valueOf(eAttributeCustomizationLabelSize.getValue());
        List<FontFormat> customizedLabelFormat = new ArrayList<FontFormat>();
        customizedLabelFormat.add(FontFormat.get(eAttributeCustomizationLabelFormat.getValue()));
        assertEquals(assertMessage, customizedLabelSize, p1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass1.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass11.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass1Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, customizedLabelSize, eClass11Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertMessage = "The new label Font Format must be that of the EAttributeCustomization.value";
        assertEquals(assertMessage, customizedLabelFormat, eClass11Attribute.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT));
        assertMessage = "As the edge labels are not customized, their size must be unchanged";
        assertEquals(assertMessage, edgeBeginStyleOriginalLabelSize, dEdge.getOwnedStyle().getBeginLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, edgeCenterStyleOriginalLabelSize, dEdge.getOwnedStyle().getCenterLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));
        assertEquals(assertMessage, edgeEndStyleOriginalLabelSize, dEdge.getOwnedStyle().getEndLabelStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE));

        assertMessage = "The new backgroundColor must be that of the EReferenceCustomization.value";
        RGBValues customizedRGBValues = new AbstractColorUpdater().getRGBValuesFromColorDescription(null, (ColorDescription) eReferenceCustomization.getValue());
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues, (RGBValues) p1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues, (RGBValues) eClass1.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues, (RGBValues) eClass11.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues,
                (RGBValues) ((GaugeCompositeStyle) eClass1Attribute.getStyle()).getSections().get(0).eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR)));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues,
                (RGBValues) ((GaugeCompositeStyle) eClass11Attribute.getStyle()).getSections().get(0).eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR)));

        session.save(new NullProgressMonitor());
        refresh(dDiagram);
        assertEquals("A new refresh call should not change the session status", SessionStatus.SYNC, session.getStatus());
    }

    /**
     * Test a style description element {@link EReference} customization through
     * a {@link EReferenceCustomization}.
     */
    public void testEAttributeAndEReferenceStyleCustomizationReuse() {
        IEditorPart dDiagramBisEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagramBis, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        DNodeContainer p1Bis = (DNodeContainer) dDiagramBis.getOwnedDiagramElements().get(0);
        DNodeList eClass1Bis = (DNodeList) dDiagramBis.getOwnedDiagramElements().get(1);
        DNodeList eClass11Bis = (DNodeList) p1Bis.getOwnedDiagramElements().get(0);
        DNodeListElement eClass1AttributeBis = eClass1Bis.getOwnedElements().get(0);
        DNodeListElement eClass11AttributeBis = eClass11Bis.getOwnedElements().get(0);
        DEdge dEdgeBis = dDiagramBis.getEdges().get(0);

        Integer p1BisLabelSize = (Integer) p1Bis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass1BisLabelSize = (Integer) eClass1Bis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass11BisLabelSize = (Integer) eClass11Bis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass1AttributeBisLabelSize = (Integer) eClass1AttributeBis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer eClass11AttributeBisLabelSize = (Integer) eClass11AttributeBis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_SIZE);
        Integer edgeBeginBisLabelSize = dEdgeBis.getOwnedStyle().getBeginLabelStyle().getLabelSize();
        Integer edgeCenterBisLabelSize = dEdgeBis.getOwnedStyle().getCenterLabelStyle().getLabelSize();
        Integer edgeEndBisLabelSize = dEdgeBis.getOwnedStyle().getEndLabelStyle().getLabelSize();

        RGBValues p1BisBackgroundColor = (RGBValues) p1Bis.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        RGBValues eClass1BisBackgroundColor = (RGBValues) eClass1Bis.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        RGBValues eClass11BisBackgroundColor = (RGBValues) eClass11Bis.getStyle().eGet(DiagramPackage.Literals.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        RGBValues eClass1AttributeBisBackgroundColor = (RGBValues) ((GaugeCompositeStyle) eClass1AttributeBis.getStyle()).getSections().get(0)
                .eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR);
        RGBValues eClass11AttributeBisBackgroundColor = (RGBValues) ((GaugeCompositeStyle) eClass11AttributeBis.getStyle()).getSections().get(0)
                .eGet(DiagramPackage.Literals.GAUGE_SECTION__BACKGROUND_COLOR);

        // Asserts
        String assertMessage = "Only the label size of the begin style of the edge must be changed to be the one of the reused EAttributeCustomization.value";
        Integer customizedLabelSize = Integer.valueOf(eAttributeCustomizationLabelSize.getValue());
        Integer originalLabelSize = 8;
        assertEquals(assertMessage, originalLabelSize, p1BisLabelSize);
        assertEquals(assertMessage, originalLabelSize, eClass1BisLabelSize);
        assertEquals(assertMessage, originalLabelSize, eClass11BisLabelSize);
        assertEquals(assertMessage, originalLabelSize, eClass1AttributeBisLabelSize);
        assertEquals(assertMessage, originalLabelSize, eClass11AttributeBisLabelSize);
        assertEquals(assertMessage, customizedLabelSize, edgeBeginBisLabelSize);
        assertEquals(assertMessage, originalLabelSize, edgeCenterBisLabelSize);
        assertEquals(assertMessage, originalLabelSize, edgeEndBisLabelSize);

        assertMessage = "Only the backgroundColor of the p1 package must be changed to be the one of the EReferenceCustomization.value";
        RGBValues customizedRGBValues = new AbstractColorUpdater().getRGBValuesFromColorDescription(null, (ColorDescription) eReferenceCustomization.getValue());
        RGBValues eClass1BisDefaultBackgroundColor = getDefaultBackgroundColor(eClass1Bis);
        RGBValues eClass11BisDefaultBackgroundColor = getDefaultBackgroundColor(eClass11Bis);
        RGBValues eClass1AttributeBisDefaultBackgroundColor = getDefaultBackgroundColor(eClass1AttributeBis);
        RGBValues eClass11AttributeBisDefaultBackgroundColor = getDefaultBackgroundColor(eClass11AttributeBis);
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(customizedRGBValues, p1BisBackgroundColor));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(eClass1BisDefaultBackgroundColor, eClass1BisBackgroundColor));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(eClass11BisDefaultBackgroundColor, eClass11BisBackgroundColor));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(eClass1AttributeBisDefaultBackgroundColor, eClass1AttributeBisBackgroundColor));
        assertTrue(assertMessage, AbstractColorUpdater.areEquals(eClass11AttributeBisDefaultBackgroundColor, eClass11AttributeBisBackgroundColor));

        session.save(new NullProgressMonitor());
        refresh(dDiagramBis);
        assertEquals("A new refresh call should not change the session status", SessionStatus.SYNC, session.getStatus());

        DialectUIManager.INSTANCE.closeEditor(dDiagramBisEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test a style description element with "apply on all" setted. In this
     * case, all labels font style should be bold.
     */
    @SuppressWarnings("unchecked")
    public void testAppliedOnAllPropertyCustomization() {

        activateViewpoint("StyleCustomizations_ExtensionB");

        TestsUtil.synchronizationWithUIThread();

        activateLayer(dDiagram, "ExtB");

        TestsUtil.synchronizationWithUIThread();

        DNodeContainer p1Bis = (DNodeContainer) dDiagram.getOwnedDiagramElements().get(0);
        DNodeList eClass1Bis = (DNodeList) dDiagram.getOwnedDiagramElements().get(1);
        DNodeList eClass11Bis = (DNodeList) p1Bis.getOwnedDiagramElements().get(0);
        DNodeListElement eClass1AttributeBis = eClass1Bis.getOwnedElements().get(0);
        DNodeListElement eClass11AttributeBis = eClass11Bis.getOwnedElements().get(0);
        DEdge dEdgeBis = dDiagram.getEdges().get(0);

        List<FontFormat> p1BisLabelFormat = (List<FontFormat>) p1Bis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        List<FontFormat> eClass1BisLabelFormat = (List<FontFormat>) eClass1Bis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        List<FontFormat> eClass11BisLabelFormat = (List<FontFormat>) eClass11Bis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        List<FontFormat> eClass1AttributeBisLabelFormat = (List<FontFormat>) eClass1AttributeBis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        List<FontFormat> eClass11AttributeBisLabelFormat = (List<FontFormat>) eClass11AttributeBis.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT);
        List<FontFormat> edgeBeginBisLabelFormat = dEdgeBis.getOwnedStyle().getBeginLabelStyle().getLabelFormat();
        List<FontFormat> edgeCenterBisLabelFormat = dEdgeBis.getOwnedStyle().getCenterLabelStyle().getLabelFormat();
        List<FontFormat> edgeEndBisLabelFormat = dEdgeBis.getOwnedStyle().getEndLabelStyle().getLabelFormat();

        // Asserts
        String assertMessage = "All label font format should be in Bold";

        List<FontFormat> fontFormat = new ArrayList<FontFormat>();
        fontFormat.add(FontFormat.BOLD_LITERAL);
        assertEquals(assertMessage, fontFormat, p1BisLabelFormat);
        assertEquals(assertMessage, fontFormat, eClass1BisLabelFormat);
        assertEquals(assertMessage, fontFormat, eClass11BisLabelFormat);
        assertEquals(assertMessage, fontFormat, eClass1AttributeBisLabelFormat);
        assertEquals(assertMessage, fontFormat, eClass11AttributeBisLabelFormat);
        assertEquals(assertMessage, fontFormat, edgeBeginBisLabelFormat);
        assertEquals(assertMessage, fontFormat, edgeCenterBisLabelFormat);
        assertEquals(assertMessage, fontFormat, edgeEndBisLabelFormat);

        session.save(new NullProgressMonitor());
        refresh(dDiagram);
        assertEquals("A new refresh call should not change the session status", SessionStatus.SYNC, session.getStatus());

    }

    /**
     * Get the default background color of the specified {@link DDiagramElement}
     * .
     * 
     * @param dDiagramElement
     *            the specified {@link DDiagramElement}
     * @return the default background color of the specified
     *         {@link DDiagramElement}
     */
    private RGBValues getDefaultBackgroundColor(DDiagramElement dDiagramElement) {
        RGBValues defaultRGBValues = null;
        DiagramElementMapping diagramElementMapping = dDiagramElement.getDiagramElementMapping();
        StyleDescription styleDescription = new GetDefaultStyle().doSwitch(diagramElementMapping);

        EStructuralFeature eStructuralFeature = styleDescription.eClass().getEStructuralFeature(StylePackage.Literals.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR.getName());
        TreeIterator<EObject> styleDescriptionContent = styleDescription.eAllContents();
        EObject styleDescElt = styleDescription;
        while (styleDescriptionContent.hasNext() && !(eStructuralFeature instanceof EReference)) {
            EObject next = styleDescriptionContent.next();
            eStructuralFeature = next.eClass().getEStructuralFeature(StylePackage.Literals.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR.getName());
            if (eStructuralFeature instanceof EReference) {
                styleDescElt = next;
                break;
            }
        }
        if (eStructuralFeature instanceof EReference) {
            Object value = styleDescElt.eGet(eStructuralFeature);
            if (value instanceof ColorDescription) {
                ColorDescription colorDescription = (ColorDescription) value;
                defaultRGBValues = new AbstractColorUpdater().getRGBValuesFromColorDescription(null, colorDescription);
            }
        }
        return defaultRGBValues;
    }

    /**
     * Change the {@link VSMElementCustomization#getPredicateExpression()} value
     * to enable/disable the specified {@link VSMElementCustomization}.
     * 
     * @param vsmElementCustomization
     *            the specified {@link VSMElementCustomization}
     * @param enable
     *            true to enable, false to disable
     */
    private void enableVSMElementCustomization(VSMElementCustomization vsmElementCustomization, boolean enable) {
        Resource resource = vsmElementCustomization.eResource();
        String uriFragment = resource.getURIFragment(vsmElementCustomization);
        URI uri = resource.getURI().appendFragment(uriFragment);
        EObject eObject = new ResourceSetImpl().getEObject(uri, true);
        eObject.eSet(DescriptionPackage.Literals.VSM_ELEMENT_CUSTOMIZATION__PREDICATE_EXPRESSION, Boolean.valueOf(enable).toString());
        try {
            eObject.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Change the {@link EAttributeCustomization#getValue()}.
     * 
     * @param eAttributeCustomization
     *            the specified {@link EAttributeCustomization}
     * @param valueExpression
     *            the value expression to compute the new value
     */
    private void changeEAttributeCustomization(EAttributeCustomization eAttributeCustomization, String valueExpression) {
        Resource resource = eAttributeCustomization.eResource();
        String uriFragment = resource.getURIFragment(eAttributeCustomization);
        URI uri = resource.getURI().appendFragment(uriFragment);
        EObject eObject = new ResourceSetImpl().getEObject(uri, true);
        eObject.eSet(DescriptionPackage.Literals.EATTRIBUTE_CUSTOMIZATION__VALUE, valueExpression);
        try {
            eObject.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            fail(e.getLocalizedMessage());
        }
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        dDiagram = null;
        p1 = null;
        eClass1 = null;
        eClass11 = null;
        eClass1Attribute = null;
        eClass11Attribute = null;
        dEdge = null;
        diagramDescription = null;
        layerContributingCustomization = null;
        vsmElementCustomization1 = null;
        vsmElementCustomization2 = null;
        eAttributeCustomizationLabelSize = null;
        eAttributeCustomizationLabelFormat = null;
        eReferenceCustomization = null;
        diagramEditor = null;
        dDiagramBis = null;
        super.tearDown();
    }
}
