/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.refresh;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.model.business.internal.helper.MappingHelper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * Tests the style customization on a {@link WorkspaceImage} coming from a {@link ConditionalStyleDescription}.
 * 
 * See VP-4407.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@SuppressWarnings("restriction")
public class StyleCustomizationAndConditionalStyleDescriptionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/refresh/style/VP-4407/";

    private static final String IMAGE1_RESOURCE_NAME = "InFlowPort.png";

    private static final String IMAGE2_RESOURCE_NAME = "StandardPortSmall.png";

    private static final String MODELER_RESOURCE_NAME = "VP-4407.odesign";

    private static final String SESSION_RESOURCE_NAME = "VP-4407.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-4407.ecore";

    private DDiagram dDiagram;

    private DDiagramEditor dDiagramEditor;

    private NodeStyleDescription nodeStyleDescriptionOfConditionalStyle;

    private IGraphicalEditPart dNodeAEditPart;

    private DNode dNodeA;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + IMAGE1_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE1_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + IMAGE2_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE2_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID + PATH + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        dDiagram = (DDiagram) allRepresentations.iterator().next();
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        dNodeA = getDiagramElementsFromLabel(dDiagram, "a", DNode.class).get(0);
        dNodeAEditPart = getEditPart(dNodeA, dDiagramEditor);

        NodeMapping nodeMapping = MappingHelper.getAllBorderedNodeMappings(dDiagram.getDescription().getDefaultLayer().getContainerMappings().get(0)).get(0);
        nodeStyleDescriptionOfConditionalStyle = nodeMapping.getConditionnalStyles().get(0).getStyle();
    }

    /**
     * Tests that the style customization of a {@link WorkspaceImage} coming from a {@link ConditionalStyleDescription}
     * keep the same {@link WorkspaceImage} instead of setting a style default from the default
     * {@link StyleDescription}.
     */
    public void testWorkspaceImageStyleCustomizationWithConditionalStyleDescription() {
        StyleDescription styleDescriptionBeforeCusto = dNodeA.getStyle().getDescription();
        assertEquals("The StyleDescription of the node should corresponds to the condtionnal one", nodeStyleDescriptionOfConditionalStyle, styleDescriptionBeforeCusto);

        // Customize the line color
        Integer yellowInteger = FigureUtilities.colorToInteger(ColorConstants.yellow);
        ChangePropertyValueRequest changePropertyValueRequest = new ChangePropertyValueRequest(DiagramUIActionsMessages.PropertyDescriptorFactory_LineColor, Properties.ID_LINECOLOR);
        changePropertyValueRequest.setValue(yellowInteger);
        org.eclipse.gef.commands.Command command = dNodeAEditPart.getCommand(changePropertyValueRequest);
        assertTrue(command.canExecute());
        dNodeAEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(command);
        TestsUtil.synchronizationWithUIThread();

        // Check that the StyleDescription used comes always from the
        // conditionalStyle
        StyleDescription styleDescriptionAfterCusto = dNodeA.getStyle().getDescription();
        assertEquals("The StyleDescription of the node should corresponds to the condtionnal one", nodeStyleDescriptionOfConditionalStyle, styleDescriptionAfterCusto);

    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(dDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        dDiagramEditor = null;
        dDiagram = null;
        dNodeA = null;
        dNodeAEditPart = null;
        nodeStyleDescriptionOfConditionalStyle = null;
        super.tearDown();
    }
}
