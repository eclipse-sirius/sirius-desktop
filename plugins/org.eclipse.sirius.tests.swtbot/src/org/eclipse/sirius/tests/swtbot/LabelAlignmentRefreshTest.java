/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.ui.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IStyleEditPart;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * Test label alignment refresh (VP-2033).
 * 
 * @author edugueperoux
 */
// NOTE : style editPart (EllipseEditPart, etc) are used only for DNodeEditPart
// (and DNode2EditPart, DNode3EditPart, DNode4EditPart).
// Label alignment on AbstractDiagramNameEditPart of BorderedNodes doesn't have
// sense, then we doesn't checks them.
public class LabelAlignmentRefreshTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/refresh/labelAlignment/"; //$NON-NLS-1$

    private static final String SEMANTIC_RESOURCE_FILENAME = "VP-2033.ecore"; //$NON-NLS-1$

    private static final String SESSION_RESOURCE_FILENAME = "VP-2033.aird"; //$NON-NLS-1$

    private static final String MODELER_RESOURCE_FILENAME = "VP-2033.odesign"; //$NON-NLS-1$

    private static final String REPRESENTATION_INSTANCE_NAME = "new VP-2033_Diagram"; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME = "VP-2033_Diagram"; //$NON-NLS-1$

    private AbstractDiagramNameEditPart ePackage1NameEditPart1;

    private AbstractDiagramNameEditPart ePackage1NameEditPart2;

    private AbstractDiagramNameEditPart ePackage2NameEditPart1;

    private AbstractDiagramNameEditPart ePackage2NameEditPart2;

    private AbstractDiagramNameEditPart ePackage3NameEditPart1;

    private AbstractDiagramNameEditPart ePackage3NameEditPart2;

    private AbstractDiagramNameEditPart ePackage4NameEditPart1;

    private AbstractDiagramNameEditPart ePackage4NameEditPart2;

    private AbstractDiagramNameEditPart ePackage11EditPart;

    private AbstractDiagramNameEditPart ePackage21EditPart;

    private AbstractDiagramNameEditPart ePackage22EditPart;

    private AbstractDiagramNameEditPart node1EditPart;

    private IStyleEditPart nodes2EditPart;

    private IStyleEditPart nodes3EditPart;

    private IStyleEditPart classWithNoteStyleEditPart;

    private IStyleEditPart nodeWithAttributeAsBorderedNodes1EditPart1;

    private AbstractDiagramNameEditPart nodeWithAttributeAsBorderedNodes1EditPart2;

    private IStyleEditPart eClass1EditPart1;

    private IStyleEditPart eClass1EditPart2;

    private IStyleEditPart eClass211EditPart1;

    private IStyleEditPart eClass221EditPart1;

    private IStyleEditPart eClass31EditPart1;

    private AbstractDiagramNameEditPart eClass31EditPart2;

    private IStyleEditPart eClass41EditPart1;

    private IStyleEditPart eClass41EditPart2;

    private IStyleEditPart myDataTypeEditPart1;

    private IStyleEditPart myDataTypeEditPart2;

    private AbstractDiagramNameEditPart att1OfNode1EditPart2;

    private AbstractDiagramNameEditPart att1OfNodeWithAttributeAsBorderedNodes1EditPart1;

    private IStyleEditPart att1OfNodeWithAttributeAsBorderedNodes1EditPart2;

    private IStyleEditPart att2OfNodeWithAttributeAsBorderedNodes1EditPart1;

    private AbstractDiagramNameEditPart att2OfNodeWithAttributeAsBorderedNodes1EditPart2;

    private NodeStyleDescription squareStyleOfNodesMapping;

    private NodeStyleDescription squareStyleOfAbstractNodesMapping;

    private NodeStyleDescription noteStyleOfClassWithNoteStyleNodeMapping;

    private NodeStyleDescription squareStyleOfAttributeNodeMappingOfAbstractNodes;

    private NodeStyleDescription dotStyleOfEDataTypeBorderedNodeMappingOfAbstractNodes;

    private ContainerStyleDescription flatContainerStyleDescOfPackageCompartmentContainerMappingWithEClassAsBorderedNodeMapping;

    private NodeStyleDescription lozengeStyleOfEClassAsBorderedNodeMappingOfMappAsBorderedNodeMapping;

    private NodeStyleDescription ellipseStyleOfEAttributeAsBorderedNodeMappingOfMappAsBorderedNodeMapping;

    private ContainerStyleDescription flatContainerStyleDescOfPackageCompartmentContainerMappingZ;

    private ContainerStyleDescription flatContainerStyleDescOfSubPackageCompartmentContainerMapping;

    private NodeStyleDescription lozengeStyleOfEClassAsBorderedNodeMappingOfContainerMappingZ;

    private NodeStyleDescription ellipseStyleOfEAttributeAsBorderedNodeMappingOfContainerMappingZ;

    private ContainerStyleDescription flatContainerStyleDescOfEClassListContainerMapping;

    private NodeStyleDescription squareStyleDescOfAttributeNodeMapping;

    private NodeStyleDescription dotStyleOfEDataTypeBorderedNodeMappingOfEClassListContainerMapping;

    private Iterator<EditPart> getParentStyleEditPartsIterator(SWTBotGefEditPart swtBotGefEditPart, EObject semanticElt) {
        List<SWTBotGefEditPart> semanticEltEditPartBots = swtBotGefEditPart.descendants(WithSemantic.withSemantic(semanticElt));
        Iterator<EditPart> semanticEltEditPartBotsNameEditPartsIterator = Iterables.transform(semanticEltEditPartBots, Bot2EditPartFunction.function()).iterator();
        return semanticEltEditPartBotsNameEditPartsIterator;
    }

    private Iterator<AbstractDiagramNameEditPart> getAbstractDiagramNameEditPartsIterator(SWTBotGefEditPart swtBotGefEditPart, EObject semanticElt) {
        List<SWTBotGefEditPart> semanticEltEditPartBots = swtBotGefEditPart.descendants(WithSemantic.withSemantic(semanticElt));
        Iterator<AbstractDiagramNameEditPart> semanticEltEditPartBotsNameEditPartsIterator = Iterables
                .filter(Iterables.transform(semanticEltEditPartBots, Bot2EditPartFunction.function()), AbstractDiagramNameEditPart.class).iterator();
        return semanticEltEditPartBotsNameEditPartsIterator;
    }

    static class Bot2EditPartFunction<F extends SWTBotGefEditPart, T extends EditPart> implements Function<F, T> {

        public static Bot2EditPartFunction<SWTBotGefEditPart, EditPart> function() {
            return new Bot2EditPartFunction<SWTBotGefEditPart, EditPart>();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @SuppressWarnings("unchecked")
        public T apply(F from) {
            return (T) from.part();
        }

    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_FILENAME, SESSION_RESOURCE_FILENAME, MODELER_RESOURCE_FILENAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILENAME); //$NON-NLS-1$
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        initEditor();

        SWTBotGefEditPart rootEditPartBot = editor.rootEditPart();
        EPackage rootEPackage = (EPackage) ((DSemanticDiagram) ((View) ((EditPart) rootEditPartBot.part().getChildren().get(0)).getModel()).getElement()).getTarget();
        EPackage ePackage1 = rootEPackage.getESubpackages().get(0);
        EPackage ePackage2 = rootEPackage.getESubpackages().get(1);
        EPackage ePackage3 = rootEPackage.getESubpackages().get(2);
        EPackage ePackage4 = rootEPackage.getESubpackages().get(3);
        EPackage ePackage11 = ePackage1.getESubpackages().get(0);
        EPackage ePackage21 = ePackage2.getESubpackages().get(0);
        EPackage ePackage22 = ePackage2.getESubpackages().get(1);
        EClass node1 = (EClass) rootEPackage.getEClassifier("Node1"); //$NON-NLS-1$
        EClass nodes2 = (EClass) rootEPackage.getEClassifier("Nodes2"); //$NON-NLS-1$
        EClass nodes3 = (EClass) rootEPackage.getEClassifier("Nodes3"); //$NON-NLS-1$
        EClass classWithNoteStyle = (EClass) rootEPackage.getEClassifier("ClassWithNoteStyle"); //$NON-NLS-1$
        EClass nodeWithAttributeAsBorderedNodes1 = (EClass) rootEPackage.getEClassifier("NodeWithAttributeAsBorderedNodes1"); //$NON-NLS-1$
        EClass eClass1 = (EClass) ePackage1.getEClassifier("EClass1"); //$NON-NLS-1$
        EClass eClass211 = (EClass) ePackage21.getEClassifier("EClass211"); //$NON-NLS-1$
        EClass eClass221 = (EClass) ePackage22.getEClassifier("EClass221"); //$NON-NLS-1$
        EClass eClass31 = (EClass) ePackage3.getEClassifier("EClass31"); //$NON-NLS-1$
        EClass eClass41 = (EClass) ePackage4.getEClassifier("EClass41"); //$NON-NLS-1$
        EDataType myDataType = (EDataType) rootEPackage.getEClassifier("MyDataType"); //$NON-NLS-1$
        EAttribute att1OfNodeWithAttributeAsBorderedNodes1 = nodeWithAttributeAsBorderedNodes1.getEAttributes().get(0);
        EAttribute att1OfNode1 = node1.getEAttributes().get(0);
        EAttribute att2OfNodeWithAttributeAsBorderedNodes1 = nodeWithAttributeAsBorderedNodes1.getEAttributes().get(1);

        Viewpoint viewpoint = localSession.getOpenedSession().getSelectedViewpoints(false).iterator().next();
        DiagramDescription diagramDescription = (DiagramDescription) viewpoint.getOwnedRepresentations().iterator().next();
        Layer defaultLayer = diagramDescription.getDefaultLayer();

        NodeMapping nodesNodeMapping = defaultLayer.getNodeMappings().get(0);
        NodeMapping abstractNodesNodeMapping = defaultLayer.getNodeMappings().get(1);
        NodeMapping classWithNoteStyleNodeMapping = defaultLayer.getNodeMappings().get(2);
        NodeMapping attributeNodeMappingOfAbstractNodes = abstractNodesNodeMapping.getBorderedNodeMappings().get(0);
        NodeMapping eDataTypeBorderedNodeMappingOfAbstractNodes = attributeNodeMappingOfAbstractNodes.getBorderedNodeMappings().get(0);

        ContainerMapping packageCompartmentContainerMappingWithEClassAsBorderedNodeMapping = defaultLayer.getContainerMappings().get(0);
        NodeMapping eClassAsBorderedNodeMappingOfMappAsBorderedNodeMapping = packageCompartmentContainerMappingWithEClassAsBorderedNodeMapping.getBorderedNodeMappings().get(0);
        NodeMapping eAttributeAsBorderedNodeMappingOfMappAsBorderedNodeMapping = eClassAsBorderedNodeMappingOfMappAsBorderedNodeMapping.getBorderedNodeMappings().get(0);

        ContainerMapping packageCompartmentContainerMappingZ = defaultLayer.getContainerMappings().get(1);
        ContainerMapping subPackageCompartmentContainerMapping = packageCompartmentContainerMappingZ.getSubContainerMappings().get(0);
        NodeMapping eClassAsBorderedNodeMappingOfContainerMappingZ = subPackageCompartmentContainerMapping.getBorderedNodeMappings().get(0);
        NodeMapping eAttributeAsBorderedNodeMappingOfContainerMappingZ = eClassAsBorderedNodeMappingOfContainerMappingZ.getBorderedNodeMappings().get(0);

        ContainerMapping eClassListContainerMapping = defaultLayer.getContainerMappings().get(2);
        NodeMapping attributeNodeMapping = eClassListContainerMapping.getSubNodeMappings().get(0);
        NodeMapping eDataTypeBorderedNodeMappingOfEClassListContainerMapping = attributeNodeMapping.getBorderedNodeMappings().get(0);

        squareStyleOfNodesMapping = nodesNodeMapping.getStyle();

        squareStyleOfAbstractNodesMapping = abstractNodesNodeMapping.getStyle();
        noteStyleOfClassWithNoteStyleNodeMapping = classWithNoteStyleNodeMapping.getStyle();
        squareStyleOfAttributeNodeMappingOfAbstractNodes = attributeNodeMappingOfAbstractNodes.getStyle();
        dotStyleOfEDataTypeBorderedNodeMappingOfAbstractNodes = eDataTypeBorderedNodeMappingOfAbstractNodes.getStyle();

        flatContainerStyleDescOfPackageCompartmentContainerMappingWithEClassAsBorderedNodeMapping = packageCompartmentContainerMappingWithEClassAsBorderedNodeMapping.getStyle();
        lozengeStyleOfEClassAsBorderedNodeMappingOfMappAsBorderedNodeMapping = eClassAsBorderedNodeMappingOfMappAsBorderedNodeMapping.getStyle();
        ellipseStyleOfEAttributeAsBorderedNodeMappingOfMappAsBorderedNodeMapping = eAttributeAsBorderedNodeMappingOfMappAsBorderedNodeMapping.getStyle();

        flatContainerStyleDescOfPackageCompartmentContainerMappingZ = packageCompartmentContainerMappingZ.getStyle();
        flatContainerStyleDescOfSubPackageCompartmentContainerMapping = subPackageCompartmentContainerMapping.getStyle();
        lozengeStyleOfEClassAsBorderedNodeMappingOfContainerMappingZ = eClassAsBorderedNodeMappingOfContainerMappingZ.getStyle();
        ellipseStyleOfEAttributeAsBorderedNodeMappingOfContainerMappingZ = eAttributeAsBorderedNodeMappingOfContainerMappingZ.getStyle();

        flatContainerStyleDescOfEClassListContainerMapping = eClassListContainerMapping.getStyle();
        squareStyleDescOfAttributeNodeMapping = attributeNodeMapping.getStyle();
        dotStyleOfEDataTypeBorderedNodeMappingOfEClassListContainerMapping = eDataTypeBorderedNodeMappingOfEClassListContainerMapping.getStyle();

        Iterator<AbstractDiagramNameEditPart> ePackage1NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, ePackage1);
        ePackage1NameEditPart1 = ePackage1NameEditPartsIterator.next();
        ePackage1NameEditPart2 = ePackage1NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> ePackage2NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, ePackage2);
        ePackage2NameEditPart1 = ePackage2NameEditPartsIterator.next();
        ePackage2NameEditPart2 = ePackage2NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> ePackage3NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, ePackage3);
        ePackage3NameEditPart1 = ePackage3NameEditPartsIterator.next();
        ePackage3NameEditPart2 = ePackage3NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> ePackage4NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, ePackage4);
        ePackage4NameEditPart1 = ePackage4NameEditPartsIterator.next();
        ePackage4NameEditPart2 = ePackage4NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> ePackage11NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, ePackage11);
        ePackage11EditPart = ePackage11NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> ePackage21NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, ePackage21);
        ePackage21EditPart = ePackage21NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> ePackage22NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, ePackage22);
        ePackage22EditPart = ePackage22NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> node1NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, node1);
        node1EditPart = node1NameEditPartsIterator.next();

        Iterator<EditPart> nodes2NameEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, nodes2);
        nodes2EditPart = (IStyleEditPart) nodes2NameEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> nodes3NameEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, nodes3);
        nodes3EditPart = (IStyleEditPart) nodes3NameEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> classWithNoteStyleNameEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, classWithNoteStyle);
        classWithNoteStyleEditPart = (IStyleEditPart) classWithNoteStyleNameEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> nodeWithAttributeAsBorderedNodes1StyleEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, nodeWithAttributeAsBorderedNodes1);
        nodeWithAttributeAsBorderedNodes1EditPart1 = (IStyleEditPart) nodeWithAttributeAsBorderedNodes1StyleEditPartsIterator.next().getChildren().get(0);
        Iterator<AbstractDiagramNameEditPart> nodeWithAttributeAsBorderedNodes1NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, nodeWithAttributeAsBorderedNodes1);
        nodeWithAttributeAsBorderedNodes1EditPart2 = nodeWithAttributeAsBorderedNodes1NameEditPartsIterator.next();

        Iterator<EditPart> eClass1StyleEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, eClass1);
        eClass1EditPart1 = (IStyleEditPart) eClass1StyleEditPartsIterator.next().getChildren().get(0);
        eClass1EditPart2 = (IStyleEditPart) eClass1StyleEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> eClass211NameEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, eClass211);
        eClass211EditPart1 = (IStyleEditPart) eClass211NameEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> eClass221NameEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, eClass221);
        eClass221EditPart1 = (IStyleEditPart) eClass221NameEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> eClass31StyleEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, eClass31);
        eClass31StyleEditPartsIterator.next();
        eClass31StyleEditPartsIterator.next();
        eClass31StyleEditPartsIterator.next();
        eClass31EditPart1 = (IStyleEditPart) eClass31StyleEditPartsIterator.next().getChildren().get(0);
        Iterator<AbstractDiagramNameEditPart> eClass31NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, eClass31);
        eClass31EditPart2 = eClass31NameEditPartsIterator.next();

        Iterator<EditPart> eClass41StyleEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, eClass41);
        eClass41EditPart1 = (IStyleEditPart) eClass41StyleEditPartsIterator.next().getChildren().get(0);
        eClass41EditPart2 = (IStyleEditPart) eClass41StyleEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> myDataTypeNameEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, myDataType);
        myDataTypeEditPart1 = (IStyleEditPart) myDataTypeNameEditPartsIterator.next().getChildren().get(0);
        myDataTypeEditPart2 = (IStyleEditPart) myDataTypeNameEditPartsIterator.next().getChildren().get(0);

        Iterator<AbstractDiagramNameEditPart> att1OfNode1NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot, att1OfNode1);
        att1OfNode1EditPart2 = att1OfNode1NameEditPartsIterator.next();

        Iterator<AbstractDiagramNameEditPart> att1OfNodeWithAttributeAsBorderedNodes1NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot,
                att1OfNodeWithAttributeAsBorderedNodes1);
        att1OfNodeWithAttributeAsBorderedNodes1EditPart1 = att1OfNodeWithAttributeAsBorderedNodes1NameEditPartsIterator.next();
        Iterator<EditPart> att1OfNodeWithAttributeAsBorderedNodes1StyleEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, att1OfNodeWithAttributeAsBorderedNodes1);
        att1OfNodeWithAttributeAsBorderedNodes1StyleEditPartsIterator.next();
        att1OfNodeWithAttributeAsBorderedNodes1EditPart2 = (IStyleEditPart) att1OfNodeWithAttributeAsBorderedNodes1StyleEditPartsIterator.next().getChildren().get(0);

        Iterator<EditPart> att2OfNodeWithAttributeAsBorderedNodes1StyleEditPartsIterator = getParentStyleEditPartsIterator(rootEditPartBot, att2OfNodeWithAttributeAsBorderedNodes1);
        att2OfNodeWithAttributeAsBorderedNodes1StyleEditPartsIterator.next();
        att2OfNodeWithAttributeAsBorderedNodes1EditPart1 = (IStyleEditPart) att2OfNodeWithAttributeAsBorderedNodes1StyleEditPartsIterator.next().getChildren().get(0);
        Iterator<AbstractDiagramNameEditPart> att2OfNodeWithAttributeAsBorderedNodes1NameEditPartsIterator = getAbstractDiagramNameEditPartsIterator(rootEditPartBot,
                att2OfNodeWithAttributeAsBorderedNodes1);
        att2OfNodeWithAttributeAsBorderedNodes1EditPart2 = att2OfNodeWithAttributeAsBorderedNodes1NameEditPartsIterator.next();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean getAutoRefreshMode() {
        return true;
    }

    private void initEditor() {
        if (editor != null) {
            editor.setSnapToGrid(false);

            editor.setFocus();
        }
    }

    /**
     * Test that label Alignment defined in *.aird is correct according to the modeler labelAlignement definition, after
     * test that changing the labelAlignement definition defined in the modeler (*.odesign)
     */
    public void testLabelAlignmentRefresh() {

        // Checks at the diagram opening
        assertLabelAlignmentEquality();

        changeLabelAlignments(LabelAlignment.CENTER);

        SWTBotUtils.waitAllUiEvents();

        // Checks label alignment refresh after odesign label alignment changes
        assertLabelAlignmentEquality();

        changeLabelAlignments(LabelAlignment.LEFT);

        SWTBotUtils.waitAllUiEvents();

        // Checks label alignment refresh after odesign label alignment changes
        assertLabelAlignmentEquality();
    }

    /**
     * Assert that label alignment all AbstractDiagramNameEditParts in field corresponds to the label alignment defined
     * in the odesign.
     */
    private void assertLabelAlignmentEquality() {

        Assert.assertEquals(getExpectedLabelAlignment(ePackage1NameEditPart1), getActualLabelAlignment(ePackage1NameEditPart1));
        Assert.assertEquals(getExpectedLabelAlignment(ePackage1NameEditPart2), getActualLabelAlignment(ePackage1NameEditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(ePackage2NameEditPart1), getActualLabelAlignment(ePackage2NameEditPart1));
        Assert.assertEquals(getExpectedLabelAlignment(ePackage2NameEditPart2), getActualLabelAlignment(ePackage2NameEditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(ePackage3NameEditPart1), getActualLabelAlignment(ePackage3NameEditPart1));
        Assert.assertEquals(getExpectedLabelAlignment(ePackage3NameEditPart2), getActualLabelAlignment(ePackage3NameEditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(ePackage4NameEditPart1), getActualLabelAlignment(ePackage4NameEditPart1));
        Assert.assertEquals(getExpectedLabelAlignment(ePackage4NameEditPart2), getActualLabelAlignment(ePackage4NameEditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(ePackage11EditPart), getActualLabelAlignment(ePackage11EditPart));

        Assert.assertEquals(getExpectedLabelAlignment(ePackage21EditPart), getActualLabelAlignment(ePackage21EditPart));

        Assert.assertEquals(getExpectedLabelAlignment(ePackage22EditPart), getActualLabelAlignment(ePackage22EditPart));

        Assert.assertEquals(getExpectedLabelAlignment(node1EditPart), getActualLabelAlignment(node1EditPart));

        Assert.assertEquals(getExpectedLabelAlignment(nodes2EditPart), getActualLabelAlignment(nodes2EditPart));

        Assert.assertEquals(getExpectedLabelAlignment(nodes3EditPart), getActualLabelAlignment(nodes3EditPart));

        Assert.assertEquals(getExpectedLabelAlignment(classWithNoteStyleEditPart), getActualLabelAlignment(classWithNoteStyleEditPart));

        Assert.assertEquals(getExpectedLabelAlignment(nodeWithAttributeAsBorderedNodes1EditPart1), getActualLabelAlignment(nodeWithAttributeAsBorderedNodes1EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(nodeWithAttributeAsBorderedNodes1EditPart2), getActualLabelAlignment(nodeWithAttributeAsBorderedNodes1EditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(eClass1EditPart1), getActualLabelAlignment(eClass1EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(eClass1EditPart2), getActualLabelAlignment(eClass1EditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(eClass211EditPart1), getActualLabelAlignment(eClass211EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(eClass221EditPart1), getActualLabelAlignment(eClass221EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(eClass31EditPart1), getActualLabelAlignment(eClass31EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(eClass31EditPart2), getActualLabelAlignment(eClass31EditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(eClass41EditPart1), getActualLabelAlignment(eClass41EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(eClass41EditPart2), getActualLabelAlignment(eClass41EditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(myDataTypeEditPart1), getActualLabelAlignment(myDataTypeEditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(myDataTypeEditPart2), getActualLabelAlignment(myDataTypeEditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(att1OfNode1EditPart2), getActualLabelAlignment(att1OfNode1EditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(att1OfNodeWithAttributeAsBorderedNodes1EditPart1), getActualLabelAlignment(att1OfNodeWithAttributeAsBorderedNodes1EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(att1OfNodeWithAttributeAsBorderedNodes1EditPart2), getActualLabelAlignment(att1OfNodeWithAttributeAsBorderedNodes1EditPart2));

        Assert.assertEquals(getExpectedLabelAlignment(att2OfNodeWithAttributeAsBorderedNodes1EditPart1), getActualLabelAlignment(att2OfNodeWithAttributeAsBorderedNodes1EditPart1));

        Assert.assertEquals(getExpectedLabelAlignment(att2OfNodeWithAttributeAsBorderedNodes1EditPart2), getActualLabelAlignment(att2OfNodeWithAttributeAsBorderedNodes1EditPart2));

    }

    private int getExpectedLabelAlignment(IGraphicalEditPart graphicalEditPart) {
        int expectedLabelAlignment = -1;
        EObject semanticElement = graphicalEditPart.resolveSemanticElement();
        if (semanticElement instanceof LabelStyle) {
            semanticElement = semanticElement.eContainer();
        }
        if (semanticElement instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) semanticElement;
            DiagramElementMapping mapping = dDiagramElement.getDiagramElementMapping();
            LabelStyleDescription labelStyleDescription = null;
            if (mapping instanceof ContainerMapping) {
                ContainerMapping containerMapping = (ContainerMapping) mapping;
                labelStyleDescription = containerMapping.getStyle();
            } else if (mapping instanceof NodeMapping) {
                NodeMapping nodeMapping = (NodeMapping) mapping;
                labelStyleDescription = nodeMapping.getStyle();
            }
            if (labelStyleDescription != null) {
                if (graphicalEditPart instanceof IStyleEditPart) {
                    expectedLabelAlignment = LabelAlignmentHelper.getAsPositionConstant(labelStyleDescription.getLabelAlignment());
                } else if (graphicalEditPart instanceof AbstractDiagramNameEditPart) {
                    expectedLabelAlignment = LabelAlignmentHelper.getAsCTLMinorAlignment(labelStyleDescription.getLabelAlignment());
                }
            }
        }
        return expectedLabelAlignment;
    }

    /**
     * Return the actual label alignment of the given part. See LabelAlignmentHelper.
     * 
     * @param graphicalEditPart
     *            the given part
     * @return the label alignment of the given part
     */
    public static int getActualLabelAlignment(IGraphicalEditPart graphicalEditPart) {
        int actualLabelAlignment = 0;
        if (graphicalEditPart instanceof IStyleEditPart) {
            IStyleEditPart styleEditPart = (IStyleEditPart) graphicalEditPart;
            IFigure styleEditPartFigure = styleEditPart.getFigure();
            if (styleEditPartFigure.getChildren().size() > 0) {
                IFigure child = (IFigure) styleEditPartFigure.getChildren().get(0);
                if (child instanceof SiriusWrapLabel) {
                    SiriusWrapLabel wrappingLabel = (SiriusWrapLabel) child;
                    actualLabelAlignment = wrappingLabel.getLabelAlignment2();
                } else if (child.getChildren().size() > 0) {
                    IFigure childOfChild = (IFigure) child.getChildren().get(0);
                    if (childOfChild instanceof SiriusWrapLabel) {
                        SiriusWrapLabel wrappingLabel = (SiriusWrapLabel) childOfChild;
                        actualLabelAlignment = wrappingLabel.getLabelAlignment2();
                    }
                }
            }

        } else if (graphicalEditPart instanceof AbstractDiagramNameEditPart) {
            AbstractDiagramNameEditPart abstractDiagramNameEditPart = (AbstractDiagramNameEditPart) graphicalEditPart;
            IFigure abstractGraphicalEditPartFigure = abstractDiagramNameEditPart.getFigure();
            Assert.assertTrue("This figure should be a SiriusWrapLabel.", abstractGraphicalEditPartFigure instanceof SiriusWrapLabel); //$NON-NLS-1$
            SiriusWrapLabel abstractGraphicalEditPartWrappingLabel = (SiriusWrapLabel) abstractGraphicalEditPartFigure;
            // if (abstractGraphicalEditPart.getParent() instanceof
            // AbstractDiagramContainerEditPart) {
            LayoutManager layoutManager = abstractGraphicalEditPartWrappingLabel.getParent().getLayoutManager();
            if (layoutManager instanceof ToolbarLayout) {
                ToolbarLayout ePackage1WrappingLabelParentLayoutManager = (ToolbarLayout) abstractGraphicalEditPartWrappingLabel.getParent().getLayoutManager();
                actualLabelAlignment = ePackage1WrappingLabelParentLayoutManager.getMinorAlignment();

                // Check figure consistency with its parent layout manager
                // (refreshed)
                int labelRefX = 0;
                int parentRefX = 0;
                if (ToolbarLayout.ALIGN_TOPLEFT == actualLabelAlignment) {
                    labelRefX = abstractGraphicalEditPartWrappingLabel.getBounds().getLeft().x;
                    parentRefX = abstractGraphicalEditPartWrappingLabel.getParent().getBounds().getLeft().x;
                } else if (ToolbarLayout.ALIGN_BOTTOMRIGHT == actualLabelAlignment) {
                    labelRefX = abstractGraphicalEditPartWrappingLabel.getBounds().getRight().x;
                    parentRefX = abstractGraphicalEditPartWrappingLabel.getParent().getBounds().getRight().x;
                } else if (ToolbarLayout.ALIGN_CENTER == actualLabelAlignment) {
                    labelRefX = abstractGraphicalEditPartWrappingLabel.getBounds().getCenter().x;
                    parentRefX = abstractGraphicalEditPartWrappingLabel.getParent().getBounds().getCenter().x;
                }
                assertEquals("Figure alignement is not consitent with style", parentRefX, labelRefX, 5); //$NON-NLS-1$

            } else {
                Assert.fail(" a AbstractGraphicalEditPart should have a ToolbarLayout on its main figure"); //$NON-NLS-1$
            }
        }
        return actualLabelAlignment;
    }

    /**
     * @param center
     */
    private void changeLabelAlignments(LabelAlignment labelAlignment) {

        ICondition operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(squareStyleOfNodesMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(squareStyleOfAbstractNodesMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(noteStyleOfClassWithNoteStyleNodeMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(squareStyleOfAttributeNodeMappingOfAbstractNodes, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(dotStyleOfEDataTypeBorderedNodeMappingOfAbstractNodes, labelAlignment);
        bot.waitUntil(operationDoneCondition);

        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(flatContainerStyleDescOfPackageCompartmentContainerMappingWithEClassAsBorderedNodeMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(lozengeStyleOfEClassAsBorderedNodeMappingOfMappAsBorderedNodeMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(ellipseStyleOfEAttributeAsBorderedNodeMappingOfMappAsBorderedNodeMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);

        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(flatContainerStyleDescOfPackageCompartmentContainerMappingZ, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(flatContainerStyleDescOfSubPackageCompartmentContainerMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(lozengeStyleOfEClassAsBorderedNodeMappingOfContainerMappingZ, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(ellipseStyleOfEAttributeAsBorderedNodeMappingOfContainerMappingZ, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(flatContainerStyleDescOfEClassListContainerMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(squareStyleDescOfAttributeNodeMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
        operationDoneCondition = new OperationDoneCondition();
        changeLabelAlignment(dotStyleOfEDataTypeBorderedNodeMappingOfEClassListContainerMapping, labelAlignment);
        bot.waitUntil(operationDoneCondition);
    }

    /**
     * Does explicitly changes in same EditingDomain as the representation editor because changes in another TED like
     * with Odesign model editor has the consequence to refresh all representation through ResourceSetSync.
     * 
     * @param labelStyleDescription
     * @param center
     */
    private void changeLabelAlignment(LabelStyleDescription labelStyleDescription, LabelAlignment labelAlignment) {
        if (!labelStyleDescription.getLabelAlignment().equals(labelAlignment)) {

            EditingDomain editingDomain = localSession.getOpenedSession().getTransactionalEditingDomain();
            Command changeLabelAlignment = SetCommand.create(editingDomain, labelStyleDescription, StylePackage.Literals.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT, labelAlignment);
            editingDomain.getCommandStack().execute(changeLabelAlignment);
        }
    }
}
