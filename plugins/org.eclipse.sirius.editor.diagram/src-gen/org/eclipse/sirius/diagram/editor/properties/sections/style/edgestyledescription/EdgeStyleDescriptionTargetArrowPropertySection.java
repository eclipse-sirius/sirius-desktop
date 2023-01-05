/*******************************************************************************
 * Copyright (c) 2007, 2018, 2023 THALES GLOBAL SERVICES, CEA LIST.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    Vincent Lorenzo (CEA LIST) <vincent.lorenzo@cea.fr> - Bug 581287
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.properties.sections.style.edgestyledescription;

// Start of user code imports

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractTablePropertySection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the targetArrow property of a EdgeStyleDescription object.
 */
public class EdgeStyleDescriptionTargetArrowPropertySection extends AbstractTablePropertySection {
    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTablePropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "TargetArrow"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTablePropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTablePropertySection#getFeature()
     */
    @Override
    protected EAttribute getFeature() {
        return StylePackage.eINSTANCE.getEdgeStyleDescription_TargetArrow();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTablePropertySection#getFeatureValue(int)
     */
    @Override
    protected Object getFeatureValue(int index) {
        return ((ImageItem) getChoiceOfValues().get(index)).getEnum();
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTablePropertySection#isEqual(int)
     */
    @Override
    protected boolean isEqual(int index) {
        return ((ImageItem) getChoiceOfValues().get(index)).getEnum().equals(eObject.eGet(getFeature()));
    }

    /**
     * @see org.eclipse.sirius.diagram.editor.properties.sections.AbstractTablePropertySection#getEnumerationFeatureValues()
     */
    @Override
    protected List<?> getChoiceOfValues() {
        List<ImageItem> result = new ArrayList<ImageItem>();
        for (EdgeArrows edgeArrows : EdgeArrows.VALUES) {
            if (edgeArrows.equals(EdgeArrows.NO_DECORATION_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/noDecoration.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.OUTPUT_ARROW_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/outputArrow.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_ARROW_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputArrow.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/outputClosedArrow.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_CLOSED_ARROW_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputClosedArrow.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.OUTPUT_FILL_CLOSED_ARROW_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/outputFillClosedArrow.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputFillClosedArrow.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.DIAMOND_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/diamond.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.FILL_DIAMOND_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/fillDiamond.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_ARROW_WITH_DIAMOND_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputArrowWithDiamond.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputArrowWithFillDiamond.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.CIRCLE_PLUS_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/circlePlus.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.DOT_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/dot.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_ARROW_WITH_DOT_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputArrowWithDot.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.DIAMOND_WITH_DOT_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/diamondWithDot.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.FILL_DIAMOND_WITH_DOT_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/fillDiamondWithDot.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_ARROW_WITH_DIAMOND_AND_DOT_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputArrowWithDiamondAndDot.gif")); //$NON-NLS-1$
            }
            if (edgeArrows.equals(EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT_LITERAL)) {
                result.add(new ImageItem(edgeArrows, "icons/full/decorator/inputArrowWithFillDiamondAndDot.gif")); //$NON-NLS-1$
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required")); //$NON-NLS-1$

    }
}
