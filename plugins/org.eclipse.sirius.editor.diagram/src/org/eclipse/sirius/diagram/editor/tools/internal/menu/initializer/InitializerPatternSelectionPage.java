/*******************************************************************************
 * Copyright (c) 2014 - Joao Martins and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Joao Martins <joaomartins27396@gmail.com>  - initial API and implementation 
 *   Maxime Porhel <maxime.porhel@obeo.fr> Obeo - Bug 438074, remarks and correction during review.
 *******************************************************************************/

package org.eclipse.sirius.diagram.editor.tools.internal.menu.initializer;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.Lists;

/**
 * Select Pattern page.
 * 
 * @author Joao Martins
 * 
 */
public class InitializerPatternSelectionPage extends WizardPage {

    private Composite container;

    private Combo combo;

    private List<IPatternProvider> patterns;

    private IPatternProvider comboSelection;

    private Canvas canvas;

    private Label lblDescription;

    private Label lblParagra;

    /**
     * Constructor.
     * 
     * @param collection
     *            selected items.
     */
    public InitializerPatternSelectionPage(Collection<IPatternProvider> collection) {
        super("PatternSelection");
        setTitle("Pattern Selection");
        setDescription("Select a pattern to init a representation.");
        setPageComplete(false);
        patterns = Lists.newArrayList(collection);
    }

    @Override
    public void createControl(Composite parent) {

        setComposite(parent);
        setControl(container);

    }

    private void setComposite(Composite parent) {
        container = new Composite(parent, SWT.NONE);        
        
        Label lblSelectPattern = new Label(container, SWT.NONE);
        lblSelectPattern.setBounds(10, 20, 76, 15);
        lblSelectPattern.setText("Select Pattern");

        combo = new Combo(container, SWT.READ_ONLY);
        combo.setBounds(88, 17, 199, 23);
        addPatternToCombo(combo);
        combo.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                setPageComplete(getComboSelect());

                canvas = new Canvas(container, SWT.NONE);
                canvas.setBounds(240, 130, 200, 160);
                ImageDescriptor image = AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.sirius.editor.diagram", comboSelection.getImagePath());
                Image img = new Image(container.getDisplay(), image.getImageData());
                canvas.setBackgroundImage(img);

                lblParagra = new Label(container, SWT.WRAP);
                lblParagra.setBounds(10, 132, 220, 170);
                lblParagra.setText(comboSelection.getDescription());

                if (getComboSelection() instanceof ClassDiagramPattern)
                    ((ClassDiagramPatternConfigurationPage) getNextPage()).setSelectedPattern((ClassDiagramPattern) getComboSelection());

                getContainer().updateButtons();
            }
        });

        lblDescription = new Label(container, SWT.NONE);
        lblDescription.setBounds(10, 90, 76, 15);
        lblDescription.setText("Description:");

    }

    /**
     * to get the selection of the combo.
     * 
     * @return true or false if get a valid pattern.
     */
    public boolean getComboSelect() {
        int index = combo.getSelectionIndex();
        if (combo != null && index >= 0) {
            comboSelection = patterns.get(index);
            return true;
        } else
            return false;
    }

    /**
     * Add patterns to combo.
     * 
     * @param combo
     *            Combo on page.
     */
    private void addPatternToCombo(Combo comb) {
        for (IPatternProvider pattern : patterns) {
            comb.add(pattern.getLabel());
        }
    }

    /**
     * get selection.
     * 
     * @return command.
     */
    public IPatternProvider getComboSelection() {
        return this.comboSelection;
    }

}
