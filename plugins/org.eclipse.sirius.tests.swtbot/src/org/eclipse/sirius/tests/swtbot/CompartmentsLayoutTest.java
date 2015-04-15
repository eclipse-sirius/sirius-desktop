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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.LabelAlignment;

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
        checkLabelAlignment(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, COMPARTMENT_NAME);
        checkLabelAlignment(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, LEFT_CLASS_NAME);
        checkLabelAlignment(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, CENTER_CLASS_NAME);
        checkLabelAlignment(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, RIGHT_CLASS_NAME);
        checkLabelAlignment(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, PACKAGE_3_NAME);
        checkLabelAlignment(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME, PACKAGE_4_NAME);
    }

    /**
     * Ensure that labels in a vertical compartment stack are corrected
     * layouted.
     */
    public void testLabelAlignmentInVerticalStack() {
        checkLabelAlignment(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, COMPARTMENT_NAME);
        checkLabelAlignment(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, LEFT_CLASS_NAME);
        checkLabelAlignment(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, CENTER_CLASS_NAME);
        checkLabelAlignment(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, RIGHT_CLASS_NAME);
        checkLabelAlignment(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, PACKAGE_3_NAME);
        checkLabelAlignment(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME, PACKAGE_4_NAME);
    }

    /**
     * Ensure that borders size in a horizontal compartment stack are corrected
     * layouted.
     */
    public void testBorderSizeInHorizontalStack() {
        openRepresentation(HORIZONTAL_STACK_REPRESENTATION_NAME, HORIZONTAL_STACK_REPRESENTATION_INSTANCE_NAME);
        checkBorderSize(COMPARTMENT_NAME);
        checkBorderSize(LEFT_CLASS_NAME);
        checkBorderSize(PACKAGE_4_NAME);
    }

    /**
     * Ensure that borders size in a vertical compartment stack are corrected
     * layouted.
     */
    public void testBorderSizeInVerticalStack() {
        openRepresentation(VERTICAL_STACK_REPRESENTATION_NAME, VERTICAL_STACK_REPRESENTATION_INSTANCE_NAME);
        checkBorderSize(COMPARTMENT_NAME);
        checkBorderSize(LEFT_CLASS_NAME);
        checkBorderSize(PACKAGE_4_NAME);
    }

    /**
     * Edit and check label alignment.
     * 
     * @param representationName
     *            representation name
     * @param representationInstanceName
     *            representation instance name
     * @param editPartName
     *            the edit part name
     */
    private void checkLabelAlignment(String representationName, String representationInstanceName, String editPartName) {
        openRepresentation(representationName, representationInstanceName);
        LabelAlignment newLabelAlignmentValue = null;
        if (getLabelAlignment(editPartName).equals(LabelAlignment.LEFT)) {
            newLabelAlignmentValue = LabelAlignment.CENTER;
        } else if (getLabelAlignment(editPartName).equals(LabelAlignment.CENTER)) {
            newLabelAlignmentValue = LabelAlignment.RIGHT;
        } else if (getLabelAlignment(editPartName).equals(LabelAlignment.RIGHT)) {
            newLabelAlignmentValue = LabelAlignment.LEFT;
        }
        ContainerStyle containerStyle = getDiagramElement(editPartName).getOwnedStyle();
        changeLabelAlignement(containerStyle, newLabelAlignmentValue);
        assertEquals("Figure alignment is not consistent with style", newLabelAlignmentValue, getLabelAlignment(editPartName));
    }

    /**
     * Get the {@link LabelAlignment} of the given edit part name, null if not
     * exists.
     * 
     * @param editPartName
     *            the edit part name
     */
    private LabelAlignment getLabelAlignment(String editPartName) {
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
            }
        });
    }

    private void checkBorderSize(String editPartName) {
        int newBorderSizeValue;
        if (editPartName.equals(COMPARTMENT_NAME)) {
            newBorderSizeValue = 1;
        } else {
            newBorderSizeValue = 10;
        }

        ContainerStyle containerStyle = getDiagramElement(editPartName).getOwnedStyle();
        changeBorderSize(containerStyle, newBorderSizeValue);
        assertEquals("Container border size is not consistent with style", newBorderSizeValue, getBorderSize(editPartName));
    }

    /**
     * Change container border size.
     */
    private void changeBorderSize(final ContainerStyle containerStyle, final int borderSize) {
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            @Override
            protected void doExecute() {
                containerStyle.setBorderSize(borderSize);
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
