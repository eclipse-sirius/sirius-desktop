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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * Configuration page.
 * 
 * @author Joao Martins
 * 
 */
public class ClassDiagramPatternConfigurationPage extends WizardPage {

    private Composite container;

    private ClassDiagramPattern selectedPattern;

    private Text text;

    private boolean globalDirectEdit = true;

    private boolean globalDelete = true;

    private Button singleDET;

    private Button manyDET;

    private Button singleDT;

    private Button manyDT;

    /**
     * Constructor.
     */
    public ClassDiagramPatternConfigurationPage() {
        super("PatternSelection");
        setTitle("Pattern Creation");
        setDescription("Configure the creation of your Class Diagram skeleton.");
    }

    @Override
    public void createControl(Composite parent) {
        setComposite(parent);
        setControl(container);
    }

    private void setComposite(Composite parent) {
        container = new Composite(parent, SWT.NONE);
        container.addListener(SWT.Show, new Listener() {
            @Override
            public void handleEvent(Event event) {
                text.setText(selectedPattern.getBaseId());
                getContainer().updateButtons();

            }
        });

        Group grpTools = new Group(container, SWT.NONE);
        grpTools.setText("Tools");
        grpTools.setBounds(10, 121, 480, 169);

        singleDET = new Button(grpTools, SWT.RADIO);
        singleDET.setSelection(globalDirectEdit);
        singleDET.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                setRadioButtons(manyDET, singleDET);
                globalDirectEdit = false;
                getContainer().updateButtons();
                selectedPattern.setPatternConfiguration(globalDirectEdit, globalDelete);
            }
        });
        singleDET.setBounds(235, 109, 53, 16);
        singleDET.setText("Single");

        manyDET = new Button(grpTools, SWT.RADIO);
        manyDET.setBounds(290, 109, 53, 16);
        manyDET.setText("Many");
        manyDET.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                setRadioButtons(singleDET, manyDET);
                globalDirectEdit = true;
                getContainer().updateButtons();
                selectedPattern.setPatternConfiguration(globalDirectEdit, globalDelete);
            }
        });

        Label lblDirectEditTool = new Label(grpTools, SWT.NONE);
        lblDirectEditTool.setBounds(140, 109, 90, 15);
        lblDirectEditTool.setText("Direct Edit Tool:");
        lblDirectEditTool.setToolTipText("Single Direct Edit Tool will create one tool for all mappings.\nMany Direct Edit Tools will create one tool for mapping.");

        singleDT = new Button(grpTools, SWT.RADIO);
        singleDT.setBounds(235, 71, 53, 16);
        singleDT.setText("Single");
        singleDT.setSelection(globalDelete);
        singleDT.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                setRadioButtons(manyDT, singleDT);
                globalDelete = false;
                getContainer().updateButtons();
                selectedPattern.setPatternConfiguration(globalDirectEdit, globalDelete);
            }
        });

        manyDT = new Button(grpTools, SWT.RADIO);
        manyDT.setBounds(290, 71, 53, 16);
        manyDT.setText("Many");
        manyDT.setCapture(true);
        manyDT.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                setRadioButtons(singleDT, manyDT);
                globalDelete = true;
                getContainer().updateButtons();
                selectedPattern.setPatternConfiguration(globalDirectEdit, globalDelete);
            }
        });

        Label lblDeleteTool = new Label(grpTools, SWT.NONE);
        lblDeleteTool.setBounds(140, 71, 63, 15);
        lblDeleteTool.setText("Delete Tool:");
        lblDeleteTool.setToolTipText("Single Delete Tool will create one tool for all mappings.\nMany Delete Tools will create one tool for mapping.");

        Group grpId = new Group(container, SWT.NONE);
        grpId.setText("ID");
        grpId.setBounds(10, 10, 480, 55);

        text = new Text(grpId, SWT.BORDER);
        text.setBounds(61, 21, 359, 21);
        text.addListener(SWT.Modify, new Listener() {
            @Override
            public void handleEvent(Event event) {
                getContainer().updateButtons();
                selectedPattern.setBaseId(text.getText());

            }
        });

        Label lblPrefixId = new Label(grpId, SWT.NONE);
        lblPrefixId.setText("Prefix ID:");
        lblPrefixId.setBounds(10, 24, 45, 15);
    }

    private void setRadioButtons(Button buttonToFalse, Button buttonToTrue) {
        buttonToFalse.setSelection(false);
        buttonToTrue.setSelection(true);
    }

    public void setSelectedPattern(ClassDiagramPattern classDiagramPattern) {
        this.selectedPattern = classDiagramPattern;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

}
