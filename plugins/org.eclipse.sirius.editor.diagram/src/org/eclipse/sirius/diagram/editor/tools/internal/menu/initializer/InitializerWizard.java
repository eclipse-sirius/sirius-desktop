/*******************************************************************************
a * Copyright (c) 2014 - Joao Martins and others.
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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.wizard.Wizard;

/**
 * Initializer Wizard.
 * 
 * @author Joao Martins
 * 
 */
public class InitializerWizard extends Wizard {

    private InitializerPatternSelectionPage selectionPage;

    private ClassDiagramPatternConfigurationPage configurePage;

    /**
     * Constructor.
     */
    public InitializerWizard() {
        super();
    }

    @Override
    public String getWindowTitle() {
        return "Initializer";
    }

    @Override
    public void addPages() {
        selectionPage = new InitializerPatternSelectionPage(getPatternsList());
        addPage(selectionPage);
        configurePage = new ClassDiagramPatternConfigurationPage();
        addPage(configurePage);

    }

    @Override
    public boolean performFinish() {
        return true;
    }

    /**
     * Insert all patterns in a Collection.
     * 
     * @return the collection of all patterns.
     */
    public Collection<IPatternProvider> getPatternsList() {
        Collection<IPatternProvider> newPatternList = new ArrayList<IPatternProvider>();

        // creating a class Diagram pattern
        ClassDiagramPattern pattern = new ClassDiagramPattern();

        newPatternList.add(pattern);

        return newPatternList;
    }

    /**
     * Getter to selected command on selectionPage.
     * 
     * @return the pattern that will be initialized.
     */
    public IPatternProvider getPattern() {
        return selectionPage.getComboSelection();
    }

    @Override
    public boolean canFinish() {
        return !(getContainer().getCurrentPage() == selectionPage);

    }

}
