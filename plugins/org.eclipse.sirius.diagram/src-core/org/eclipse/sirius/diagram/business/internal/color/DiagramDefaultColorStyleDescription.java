/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.color;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.util.StyleSwitch;
import org.eclipse.sirius.tools.api.ui.color.EnvironmentSystemColorFactory;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;

/**
 * Class responsible for setting default color values on style descriptions.
 * 
 * @author cbrun
 * 
 */
public class DiagramDefaultColorStyleDescription extends StyleSwitch<EObject> {

    private static final String BLACK = "black"; //$NON-NLS-1$

    private static final String GRAY = "gray"; //$NON-NLS-1$

    /**
     * Set the default color descriptions on the given EObject.
     * 
     * @param theEObject
     *            the object to update.
     */
    public void setDefaultColors(final EObject theEObject) {
        doSwitch(theEObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseBorderedStyleDescription(final BorderedStyleDescription object) {
        object.setBorderColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseBorderedStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseLabelStyleDescription(final LabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseLabelStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseBasicLabelStyleDescription(BasicLabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseBasicLabelStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseBeginLabelStyleDescription(BeginLabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseBeginLabelStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseCenterLabelStyleDescription(CenterLabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseCenterLabelStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseEndLabelStyleDescription(EndLabelStyleDescription object) {
        object.setLabelColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseEndLabelStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseSquareDescription(final SquareDescription object) {
        object.setColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(GRAY));
        return super.caseSquareDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseBundledImageDescription(final BundledImageDescription object) {
        object.setColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        return super.caseBundledImageDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseNoteDescription(final NoteDescription object) {
        object.setColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("yellow")); //$NON-NLS-1$
        return super.caseNoteDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseDotDescription(final DotDescription object) {
        object.setBackgroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(GRAY));
        return super.caseDotDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseFlatContainerStyleDescription(final FlatContainerStyleDescription object) {
        object.setForegroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("light_gray")); //$NON-NLS-1$
        object.setBackgroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("white")); //$NON-NLS-1$
        return super.caseFlatContainerStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseShapeContainerStyleDescription(final ShapeContainerStyleDescription object) {
        object.setBackgroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("light_gray")); //$NON-NLS-1$
        return super.caseShapeContainerStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseEdgeStyleDescription(final EdgeStyleDescription object) {
        object.setStrokeColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(GRAY));
        return super.caseEdgeStyleDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseGaugeSectionDescription(final GaugeSectionDescription object) {
        object.setBackgroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(BLACK));
        object.setForegroundColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription("green")); //$NON-NLS-1$
        return super.caseGaugeSectionDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseEllipseNodeDescription(final EllipseNodeDescription object) {
        object.setColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(GRAY));
        return super.caseEllipseNodeDescription(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EObject caseLozengeNodeDescription(final LozengeNodeDescription object) {
        object.setColor(EnvironmentSystemColorFactory.getDefault().getSystemColorDescription(GRAY));
        return super.caseLozengeNodeDescription(object);
    }

}
