/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.business.api.color.RGBValuesProvider;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.ui.IEditorPart;
import org.osgi.framework.Version;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Test that the repair process restore only customizations and leave the
 * refresh update the non customized features.
 * 
 * See VP-3643.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RepairOnStyleCustomizationsTest extends AbstractRepairMigrateTest {

    private static final String PATH = "/data/unit/repair/customizations/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3643.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3643.aird";

    private static final String IMAGE = "image.bmp";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + REPRESENTATIONS_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + IMAGE, "/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE);
    }

    /**
     * same test than testRepairOnStyleCustomizations but with an unsynchronized
     * diagram.
     * 
     * @throws Exception
     */
    public void testRepairOnStyleCustomizationsUnsynchronized() throws Exception {
        testRepairOnStyleCustomizations(true);

    }

    /**
     * Test that the repair process restore only customizations and leave the
     * refresh update the non customized features. Test also that the repair
     * process doesn't reset a WorkspaceImage set to display a background image.
     * 
     * @throws Exception
     */
    public void testRepairOnStyleCustomizations() throws Exception {
        testRepairOnStyleCustomizations(false);
    }

    @SuppressWarnings("unchecked")
    private void testRepairOnStyleCustomizations(boolean unsynchronized) throws Exception {
        // Check that the representation file migration is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + REPRESENTATIONS_RESOURCE_NAME, true), true);
        assertTrue("The test case has been migrated but should be in 8.0.0 version", Version.parseVersion("8.0.0").compareTo(loadedVersion) == 0);

        URI representationsResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME, true);
        if (unsynchronized) {
            session = SessionManager.INSTANCE.getSession(representationsResourceURI, defaultProgress);
            DDiagram dDiagram = (DDiagram) getRepresentations("Entities").iterator().next();
            unsynchronizeDiagram(dDiagram);
            session.save(defaultProgress);
        }
        // Launch a repair
        runRepairProcess(REPRESENTATIONS_RESOURCE_NAME);
        session = SessionManager.INSTANCE.getSession(representationsResourceURI, defaultProgress);
        session.open(defaultProgress);
        DDiagram dDiagram = (DDiagram) getRepresentations("Entities").iterator().next();
        assertEquals("The DDiagram after repair should always contains 4 direct child DDiagramElements", 4, dDiagram.getOwnedDiagramElements().size());

        DDiagramElement dDiagramElementOfEPackage = getDiagramElementsFromLabel(dDiagram, "newPackage1").get(0);
        DDiagramElement dDiagramElementOfEPackageWithWorkspaceImage = getDiagramElementsFromLabel(dDiagram, "newPackage2").get(0);
        DDiagramElement dDiagramElementOfEClass = getDiagramElementsFromLabel(dDiagram, "NewEClass1").get(0);
        DDiagramElement dDiagramElementOfEClass2 = getDiagramElementsFromLabel(dDiagram, "NewEClass2").get(0);
        DDiagramElement dDiagramElementOfEReference = getDiagramElementsFromLabel(dDiagram, "[0..1] newEReference1").get(0);

        assertTrue(dDiagramElementOfEPackage instanceof DNodeContainer);
        assertTrue(dDiagramElementOfEPackageWithWorkspaceImage instanceof DNodeContainer);
        assertTrue(dDiagramElementOfEClass instanceof DNodeList);
        assertTrue(dDiagramElementOfEClass2 instanceof DNodeList);
        assertTrue(dDiagramElementOfEReference instanceof DEdge);

        DNodeContainer dNodeContainerOfEPackage = (DNodeContainer) dDiagramElementOfEPackage;
        DNodeContainer dNodeContainerOfEPackageWithWorkspaceImage = (DNodeContainer) dDiagramElementOfEPackageWithWorkspaceImage;
        DNodeList dNodeListOfEClass = (DNodeList) dDiagramElementOfEClass;
        DNodeList dNodeListOfEClass2 = (DNodeList) dDiagramElementOfEClass2;
        DEdge dEdgeOfEReference = (DEdge) dDiagramElementOfEReference;

        RGBValuesProvider rgbValuesProvider = new RGBValuesProvider();
        RGBValues foregroundColorRGBValuesFromDescriptionForEClass = rgbValuesProvider
                .getRGBValues((SystemColor) ((FlatContainerStyleDescription) dNodeListOfEClass.getStyle().getDescription()).getForegroundColor());
        RGBValues foregroundColorRGBValuesFromDescriptionForEPackage = rgbValuesProvider
                .getRGBValues((SystemColor) ((FlatContainerStyleDescription) dNodeContainerOfEPackage.getStyle().getDescription()).getForegroundColor());
        RGBValues strokeColorRGBValuesFromDescriptionForEReference = rgbValuesProvider
                .getRGBValues((SystemColor) ((EdgeStyleDescription) dEdgeOfEReference.getStyle().getDescription()).getStrokeColor());

        String assertMessage = "The new color should be the new one computed by the refresh from the VSM";
        assertEquals(assertMessage, strokeColorRGBValuesFromDescriptionForEReference.getRed(), dEdgeOfEReference.getOwnedStyle().getStrokeColor().getRed());
        assertEquals(assertMessage, strokeColorRGBValuesFromDescriptionForEReference.getGreen(), dEdgeOfEReference.getOwnedStyle().getStrokeColor().getGreen());
        assertEquals(assertMessage, strokeColorRGBValuesFromDescriptionForEReference.getBlue(), dEdgeOfEReference.getOwnedStyle().getStrokeColor().getBlue());

        assertEquals(assertMessage, foregroundColorRGBValuesFromDescriptionForEClass.getRed(), ((FlatContainerStyle) dNodeListOfEClass.getOwnedStyle()).getForegroundColor().getRed());
        assertEquals(assertMessage, foregroundColorRGBValuesFromDescriptionForEClass.getGreen(), ((FlatContainerStyle) dNodeListOfEClass.getStyle()).getForegroundColor().getGreen());
        assertEquals(assertMessage, foregroundColorRGBValuesFromDescriptionForEClass.getBlue(), ((FlatContainerStyle) dNodeListOfEClass.getStyle()).getForegroundColor().getBlue());

        assertEquals(assertMessage, foregroundColorRGBValuesFromDescriptionForEPackage.getRed(), ((FlatContainerStyle) dNodeContainerOfEPackage.getOwnedStyle()).getForegroundColor().getRed());
        assertEquals(assertMessage, foregroundColorRGBValuesFromDescriptionForEPackage.getGreen(), ((FlatContainerStyle) dNodeContainerOfEPackage.getStyle()).getForegroundColor().getGreen());
        assertEquals(assertMessage, foregroundColorRGBValuesFromDescriptionForEPackage.getBlue(), ((FlatContainerStyle) dNodeContainerOfEPackage.getStyle()).getForegroundColor().getBlue());

        assertMessage = "After a migration and a repair the Style.customFeatures should be unchanged";
        EAttribute labelFormatFeature = ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_FORMAT;
        assertEquals(assertMessage, Collections.emptyList(), dEdgeOfEReference.getOwnedStyle().getCustomFeatures());
        assertEquals(assertMessage, Collections.singletonList(labelFormatFeature.getName()), dEdgeOfEReference.getOwnedStyle().getCenterLabelStyle().getCustomFeatures());
        assertEquals(assertMessage, FontFormat.BOLD_LITERAL, ((List<FontFormat>) dEdgeOfEReference.getOwnedStyle().getCenterLabelStyle().eGet(labelFormatFeature)).get(1));
        assertEquals(assertMessage, FontFormat.ITALIC_LITERAL, ((List<FontFormat>) dEdgeOfEReference.getOwnedStyle().getCenterLabelStyle().eGet(labelFormatFeature)).get(0));
        List<String> expectedDnodeListCustomFeatures = Lists.newArrayList();
        expectedDnodeListCustomFeatures.add(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR.getName());
        expectedDnodeListCustomFeatures.add(labelFormatFeature.getName());
        assertEquals(assertMessage, Sets.newHashSet(expectedDnodeListCustomFeatures), Sets.newHashSet(dNodeListOfEClass.getStyle().getCustomFeatures()));
        assertEquals(assertMessage, 4, ((List<FontFormat>) dNodeListOfEClass.getStyle().eGet(labelFormatFeature)).size());
        assertEquals(assertMessage, FontFormat.STRIKE_THROUGH_LITERAL, ((List<FontFormat>) dNodeListOfEClass.getStyle().eGet(labelFormatFeature)).get(3));
        assertEquals(assertMessage, FontFormat.UNDERLINE_LITERAL, ((List<FontFormat>) dNodeListOfEClass.getStyle().eGet(labelFormatFeature)).get(2));
        assertEquals(assertMessage, FontFormat.BOLD_LITERAL, ((List<FontFormat>) dNodeListOfEClass.getStyle().eGet(labelFormatFeature)).get(1));
        assertEquals(assertMessage, FontFormat.ITALIC_LITERAL, ((List<FontFormat>) dNodeListOfEClass.getStyle().eGet(labelFormatFeature)).get(0));
        assertEquals(assertMessage, RGBValues.create(228, 179, 229), dNodeListOfEClass.getStyle().eGet(ViewpointPackage.Literals.BASIC_LABEL_STYLE__LABEL_COLOR));

        assertEquals(assertMessage, Collections.singletonList(labelFormatFeature.getName()), dNodeListOfEClass2.getStyle().getCustomFeatures());
        assertEquals(assertMessage, FontFormat.UNDERLINE_LITERAL, ((List<FontFormat>) dNodeListOfEClass2.getStyle().eGet(labelFormatFeature)).get(0));
        assertEquals(assertMessage, FontFormat.STRIKE_THROUGH_LITERAL, ((List<FontFormat>) dNodeListOfEClass2.getStyle().eGet(labelFormatFeature)).get(1));

        assertEquals(assertMessage, Collections.singletonList(labelFormatFeature.getName()), dNodeContainerOfEPackage.getStyle().getCustomFeatures());
        assertEquals(assertMessage, FontFormat.BOLD_LITERAL, ((List<FontFormat>) dNodeContainerOfEPackage.getStyle().eGet(labelFormatFeature)).get(1));
        assertEquals(assertMessage, FontFormat.ITALIC_LITERAL, ((List<FontFormat>) dNodeContainerOfEPackage.getStyle().eGet(labelFormatFeature)).get(0));

        assertMessage = "The repair process shouldn't reset the WorkspaceImage seted to display a background image";
        assertTrue(assertMessage, dNodeContainerOfEPackageWithWorkspaceImage.getStyle() instanceof WorkspaceImage);
        WorkspaceImage workspaceImage = (WorkspaceImage) dNodeContainerOfEPackageWithWorkspaceImage.getStyle();
        assertEquals(assertMessage, 2, dNodeContainerOfEPackageWithWorkspaceImage.getStyle().getCustomFeatures().size());
        assertTrue(assertMessage, dNodeContainerOfEPackageWithWorkspaceImage.getStyle().getCustomFeatures().contains(labelFormatFeature.getName()));
        assertTrue(assertMessage, dNodeContainerOfEPackageWithWorkspaceImage.getStyle().getCustomFeatures().contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName()));
        assertEquals(assertMessage, FontFormat.BOLD_LITERAL, ((List<FontFormat>) workspaceImage.getLabelFormat()).get(1));
        assertEquals(assertMessage, FontFormat.ITALIC_LITERAL, ((List<FontFormat>) workspaceImage.getLabelFormat()).get(0));

        assertEquals(assertMessage, TEMPORARY_PROJECT_NAME + "/" + IMAGE, workspaceImage.getWorkspacePath());

        // Open a session and a diagram editor
        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, EcoreModeler.MODELER_PATH, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);
        DRepresentation dRepresentation = getRepresentations(EcoreModeler.ENTITIES_DESC_NAME, semanticModel).iterator().next();
        IEditorPart diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dRepresentation, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check the dirty status of the editor
        assertEquals("At diagram opening after a repair, the session shouldn't be dirty", SessionStatus.SYNC, session.getStatus());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
