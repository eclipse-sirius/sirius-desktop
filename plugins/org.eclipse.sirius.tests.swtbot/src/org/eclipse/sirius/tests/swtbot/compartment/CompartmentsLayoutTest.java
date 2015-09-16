/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.compartment;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.ui.business.internal.edit.helpers.LabelAlignmentHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDiagramElementContainerNameEditPart;
import org.eclipse.sirius.tests.swtbot.LabelAlignmentRefreshTest;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests defined to ensure that changing layouts in Compartment are correctly
 * applied.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class CompartmentsLayoutTest extends AbstractCompartmentTest {

    /**
     * Ensure that labels in a horizontal compartment stack are corrected
     * layouted.
     */
    public void testLabelAlignmentInHorizontalStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        doTestLabelAlignment();
    }

    /**
     * Ensure that labels in a vertical compartment stack are corrected
     * layouted.
     */
    public void testLabelAlignmentInVerticalStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        doTestLabelAlignment();
    }

    private void doTestLabelAlignment() {
        checkAndChangeLabelAlignment(REGION_CONTAINER_NAME, LabelAlignment.CENTER, LabelAlignment.LEFT);
        checkAndChangeLabelAlignment(LEFT_CLASS_NAME, LabelAlignment.LEFT, LabelAlignment.RIGHT);
        checkAndChangeLabelAlignment(CENTER_CLASS_NAME, LabelAlignment.CENTER, LabelAlignment.LEFT);
        checkAndChangeLabelAlignment(RIGHT_CLASS_NAME, LabelAlignment.RIGHT, LabelAlignment.CENTER);
        checkAndChangeLabelAlignment(LEFT_PKG_NAME, LabelAlignment.LEFT, LabelAlignment.CENTER);
        checkAndChangeLabelAlignment(CENTER_PKG_NAME, LabelAlignment.CENTER, LabelAlignment.RIGHT);
        checkAndChangeLabelAlignment(RIGHT_PKG_NAME, LabelAlignment.RIGHT, LabelAlignment.LEFT);
    }

    /**
     * Ensure that borders size in a horizontal compartment stack are corrected
     * layouted.
     */
    public void testBorderSizeInHorizontalStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        doTestBorderSize(ContainerLayout.HORIZONTAL_STACK, 4, 2, LineStyle.DASH_LITERAL);
    }

    /**
     * Ensure that borders size in a vertical compartment stack are corrected
     * layouted.
     */
    public void testBorderSizeInVerticalStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        doTestBorderSize(ContainerLayout.VERTICAL_STACK, 2, 1, LineStyle.SOLID_LITERAL);
    }

    private void doTestBorderSize(ContainerLayout stackDirection, int regionContainerBorderSize, int regionBorderSize, LineStyle regionBorderLineStyle) {
        checkBorderSize(REGION_CONTAINER_NAME, regionContainerBorderSize, LineStyle.SOLID_LITERAL);
        checkBorderSize(LEFT_CLASS_NAME, regionBorderSize, regionBorderLineStyle);
        checkBorderSize(CENTER_PKG_NAME, regionBorderSize, regionBorderLineStyle);
        checkBorderSize(RIGHT_PKG_NAME, regionBorderSize, regionBorderLineStyle);
        checkBorderSize(LEFT_PKG_NAME, regionBorderSize, regionBorderLineStyle);
        checkBorderSize(CENTER_PKG_NAME, regionBorderSize, regionBorderLineStyle);
        checkBorderSize(RIGHT_PKG_NAME, regionBorderSize, regionBorderLineStyle);

        ContainerStyle containerStyle = getDiagramElement(REGION_CONTAINER_NAME).getOwnedStyle();
        changeBorderSpecification(containerStyle, 10, LineStyle.DASH_DOT_LITERAL);
        checkBorderSize(REGION_CONTAINER_NAME, 10, LineStyle.DASH_DOT_LITERAL);

        containerStyle = getDiagramElement(CENTER_PKG_NAME).getOwnedStyle();
        changeBorderSpecification(containerStyle, 10, LineStyle.DASH_DOT_LITERAL);
        checkBorderSize(REGION_CONTAINER_NAME, 10, LineStyle.DASH_DOT_LITERAL);

        containerStyle = getDiagramElement(CENTER_PKG_NAME).getOwnedStyle();
        changeBorderSpecification(containerStyle, 10, LineStyle.DASH_DOT_LITERAL);
        checkBorderSize(REGION_CONTAINER_NAME, 10, LineStyle.DASH_DOT_LITERAL);

    }

    private void checkLabelAlignment(String editPartName, LabelAlignment labelAlignement) {
        assertEquals("Style label alignment is not consistent.", labelAlignement, getStyleLabelAlignment(editPartName));

        SWTBotGefEditPart labelEditPart = editor.getEditPart(editPartName, AbstractDiagramElementContainerNameEditPart.class);
        AbstractDiagramElementContainerNameEditPart namePart = (AbstractDiagramElementContainerNameEditPart) labelEditPart.part();
        int actualLabelAlignment = LabelAlignmentRefreshTest.getActualLabelAlignment(namePart);
        assertEquals("Figure alignment is not consistent with style.", LabelAlignmentHelper.getAsCTLMinorAlignment(labelAlignement), actualLabelAlignment);
    }

    private void checkAndChangeLabelAlignment(String editPartName, LabelAlignment initialExpectedValue, LabelAlignment labelAlignement) {
        checkLabelAlignment(editPartName, initialExpectedValue);

        ContainerStyle containerStyle = getDiagramElement(editPartName).getOwnedStyle();
        changeLabelAlignement(containerStyle, labelAlignement);
        checkLabelAlignment(editPartName, labelAlignement);
    }

    /**
     * Get the {@link LabelAlignment} of the given edit part name, null if not
     * exists.
     * 
     * @param editPartName
     *            the edit part name
     */
    private LabelAlignment getStyleLabelAlignment(String editPartName) {
        EObject element = getDiagramElement(editPartName);
        if (element instanceof DDiagramElementContainer) {
            return ((DDiagramElementContainer) element).getOwnedStyle().getLabelAlignment();
        }
        return null;
    }

    /**
     * Change the label alignment of the given style.
     */
    private void changeLabelAlignement(final ContainerStyle containerStyle, final LabelAlignment labelAlignement) {
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                containerStyle.setLabelAlignment(labelAlignement);
                containerStyle.getCustomFeatures().add(ViewpointPackage.eINSTANCE.getLabelStyle_LabelAlignment().getName());
            }
        });
    }

    private void checkBorderSize(String editPartName, int expectedBorderSize, LineStyle expectedBorderStyle) {
        assertEquals("Container border size is not consistent with style", expectedBorderSize, getBorderSize(editPartName));
    }

    private void changeBorderSpecification(final ContainerStyle containerStyle, final int borderSize, final LineStyle borderLineStyle) {
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                containerStyle.setBorderSize(borderSize);
                containerStyle.setBorderLineStyle(borderLineStyle);
                containerStyle.getCustomFeatures().add(DiagramPackage.eINSTANCE.getBorderedStyle_BorderSize().getName());
                containerStyle.getCustomFeatures().add(DiagramPackage.eINSTANCE.getBorderedStyle_BorderLineStyle().getName());
            }
        });
    }

    /**
     * Get the Border size of the given edit part name, 0 if there is no border.
     * 
     * @param editPartName
     *            the edit part name
     */
    private int getBorderSize(String editPartName) {
        EObject element = getDiagramElement(editPartName);
        if (element instanceof DDiagramElementContainer) {
            return ((DDiagramElementContainer) element).getOwnedStyle().getBorderSize();
        }
        return 0;
    }

}
