/******************************************************************************
 * Copyright (c) 2005, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gmf.runtime.common.ui.util.WindowUtil;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.l10n.DiagramUIPropertiesMessages;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Copy of gmf code.
 */
public class ColorPalettePopup {

    /**
     * A map associating a RGB color with the corresponding Image.
     */

    /** variable to store previous color */
    private int previousColor;

    private Button customColorButton;

    private Map<RGB, Button> buttonMap = new LinkedHashMap<>();

    /**
     * This field allows to dispose the current popup (and associated Shell and buttons) on deactivation (see
     * "shell.addListener(SWT.Deactivate,...)"). It is set to false when the button "Custom..." is clicked. Indeed, in
     * this case, the dispose is done just after the click on "OK" or "Cancel" button of the "ColorDialog" dialog.
     */
    private boolean shouldBeDisposedOnDeactivation = true;

    private static final String CUSTOM_COLOR_STRING = DiagramUIPropertiesMessages.ColorPalettePopup_custom;

    /** default color icon width. */
    public static final Point ICON_SIZE = new Point(20, 20);

    private Shell shell;

    private RGB selectedColor;

    /**
     * The default color to be used if the user presses the default button.
     */
    private boolean useDefaultColor;

    /**
     * Creates a PopupList above the specified shell.
     * 
     * @param parent
     *            a widget which will be the parent of the new instance (cannot be null)
     * @param rowHeight
     *            the row height
     * @param defaultColors
     *            color used to fill the default colors
     */
    public ColorPalettePopup(Shell parent, int rowHeight, Map<String, RGB> defaultColors) {
        shell = new Shell(parent, ColorPalettePopup.checkStyle(SWT.NONE));
        GridLayout layout = new GridLayout(1, true);
        layout.horizontalSpacing = 0;
        layout.marginWidth = 0;
        layout.marginHeight = 5;
        layout.verticalSpacing = 0;
        shell.setLayout(layout);

        createLastUsedCategory(shell);
        createCustomizedCategory(shell);
        createSuggestedCategory(shell);
        createDefaultCategory(shell);

        // Button moreColors = new Button(shell, SWT.PUSH);
        // moreColors.setText(CUSTOM_COLOR_STRING);
        // // GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        // GridData data = new GridData(SWT.BEGINNING, SWT.CENTER, true, true, 10, 1);
        // data.horizontalSpan = 4;
        // data.heightHint = rowHeight;
        // moreColors.setLayoutData(data);
        //
        // moreColors.addSelectionListener(new SelectionAdapter() {
        //
        // @Override
        // public void widgetSelected(SelectionEvent event) {
        // shouldBeDisposedOnDeactivation = false;
        // ColorDialog dialog = new ColorDialog(Display.getCurrent().getActiveShell());
        // dialog.setRGB(FigureUtilities.integerToRGB(getPreviousColor()));
        // WindowUtil.centerDialog(dialog.getParent(), Display.getCurrent().getActiveShell());
        // RGB returnedSelectedColor = dialog.open();
        // if (returnedSelectedColor != null) { // case of cancel
        // selectedColor = dialog.getRGB();
        // }
        // dispose();
        //
        // }
        // });
        // Hide dialog if user clicks on "Custom..." button or dispose it if user selects outside of the shell (without
        // clicking on any button)
        shell.addListener(SWT.Deactivate, new Listener() {

            @Override
            public void handleEvent(Event e) {
                if (shouldBeDisposedOnDeactivation) {
                    disposePalettePopup();
                } else {
                    shell.setVisible(false);
                }
            }
        });
        // customColorButton = moreColors;

    }

    private void createLastUsedCategory(Composite parent) {
        createSeparator(Messages.ColorPalettePopup_lastUsedCategoryLabel);
        List<RGB> colorsList = new ArrayList<>();
        colorsList.add(new RGB(255, 0, 0));
        colorsList.add(new RGB(0, 255, 0));
        colorsList.add(new RGB(0, 0, 255));
        colorsList.add(new RGB(255, 0, 255));

        Composite colorsAndButtonComposite = configureColorsAndButtonsComposite(parent, 1);

        new ColorPaletteComposite(colorsAndButtonComposite, colorsList, 10, true) {
            @Override
            public void setPaletteSelectedColor(RGB colorToSet) {
                super.setPaletteSelectedColor(colorToSet);
                selectedColor = getPaletteSelectedColor();
                disposePalettePopup();
            }
        };

    }

    private void createCustomizedCategory(Composite parent) {
        createSeparator(Messages.ColorPalettePopup_customizedCategoryLabel);
        List<RGB> colorsList2 = new ArrayList<>();
        colorsList2.add(new RGB(255, 255, 255));
        colorsList2.add(new RGB(0, 0, 0));
        colorsList2.add(new RGB(255, 255, 0));
        colorsList2.add(new RGB(0, 255, 255));

        Composite colorsAndButtonComposite = configureColorsAndButtonsComposite(parent, 2);

        new ColorPaletteComposite(colorsAndButtonComposite, colorsList2, 10, false) {
            @Override
            public void setPaletteSelectedColor(RGB colorToSet) {
                super.setPaletteSelectedColor(colorToSet);
                selectedColor = getPaletteSelectedColor();
                disposePalettePopup();
            }
        };
        Button moreColors = configureMoreButton(colorsAndButtonComposite);
        moreColors.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorSelectionDialog dialog = new ColorSelectionDialog((Shell) shell.getParent(), null, true, null, Messages.ColorSelectionDialog_dialogTitle, "", Messages.ColorSelectionDialog_groupAllCustomizedColorsLabel) { //$NON-NLS-1$
                    @Override
                    protected void configureDisplayedColorsButtons(Composite parent) {
                        // TODO: oops, we should set the add and remove buttons for the "all colors" group, not the "displayed colors group.
                        Composite buttonsComposite = new Composite(parent, SWT.NONE);
                        GridLayout buttonsGridLayout = new GridLayout(2, false);
                        buttonsComposite.setLayout(buttonsGridLayout);
                        GridData buttonsGridData = new GridData(SWT.END, SWT.CENTER, true, true, 1, 1);
                        buttonsComposite.setLayoutData(buttonsGridData);

                        Button addButton = new Button(buttonsComposite, SWT.PUSH);
                        addButton.setText(Messages.ColorSelectionDialog_addButtonLabel);
                        addButton.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, true, 1, 1));
                        addButton.addSelectionListener(new SelectionAdapter() {
                            @Override
                            public void widgetSelected(SelectionEvent e) {
                                ColorDialog dialog = new ColorDialog(Display.getCurrent().getActiveShell());
                                // dialog.setRGB(FigureUtilities.integerToRGB(getPreviousColor()));
                                WindowUtil.centerDialog(dialog.getParent(), Display.getCurrent().getActiveShell());
                                RGB returnedSelectedColor = dialog.open();
                                if (returnedSelectedColor != null) { // case of cancel
                                    selectedColor = dialog.getRGB();
                                    // TODO: we should add this color to "all colors", but a refactoring is required
                                    // here: we should move the confioguration of the add button into the
                                    // ColorSelectionDialog.
                                }
                            }
                        });
                        super.configureDisplayedColorsButtons(buttonsComposite);
                    }
                };
                if (dialog.open() == Window.OK) {
                    selectedColor = dialog.getSelectedColor();
                }
            }
        });
    }

    private void createSuggestedCategory(Composite parent) {
        createSeparator(Messages.ColorPalettePopup_suggestedCategoryLabel);
        List<RGB> colorsList3 = new ArrayList<>();
        colorsList3.add(new RGB(120, 120, 120));
        colorsList3.add(new RGB(42, 63, 158));
        colorsList3.add(new RGB(120, 120, 0));
        colorsList3.add(new RGB(0, 120, 120));
        colorsList3.add(new RGB(255, 0, 0));
        colorsList3.add(new RGB(0, 255, 0));
        colorsList3.add(new RGB(0, 0, 255));
        colorsList3.add(new RGB(255, 0, 255));
        colorsList3.add(new RGB(255, 255, 255));
        colorsList3.add(new RGB(0, 0, 0));
        colorsList3.add(new RGB(255, 255, 0));
        colorsList3.add(new RGB(0, 255, 255));

        Composite colorsAndButtonComposite = configureColorsAndButtonsComposite(parent, 2);

        new ColorPaletteComposite(colorsAndButtonComposite, colorsList3, 10, false) {
            @Override
            public void setPaletteSelectedColor(RGB colorToSet) {
                super.setPaletteSelectedColor(colorToSet);
                selectedColor = getPaletteSelectedColor();
                disposePalettePopup();
            }
        };
        Button moreColors = configureMoreButton(colorsAndButtonComposite);
        moreColors.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorSelectionDialog dialog = new ColorSelectionDialog((Shell) shell.getParent(), null, true, null, Messages.ColorSelectionDialog_dialogTitle, Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel, Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel);
                if (dialog.open() == Window.OK) {
                    selectedColor = dialog.getSelectedColor();
                }
            }
        });
    }

    private void createDefaultCategory(Composite parent) {
        createSeparator(Messages.ColorPalettePopup_defaultCategoryLabel);
        List<RGB> colorsList4 = new ArrayList<>();
        colorsList4.add(new RGB(120, 0, 0));
        colorsList4.add(new RGB(0, 120, 0));
        colorsList4.add(new RGB(0, 0, 120));
        colorsList4.add(new RGB(120, 0, 120));

//        Composite colorsAndButtonComposite = new Composite(parent, SWT.NONE);
//        GridLayout colorsAndButtonLayout = new GridLayout(2, false);
//        colorsAndButtonLayout.marginWidth = 2;
//        colorsAndButtonLayout.marginRight = 5;
//        colorsAndButtonLayout.marginHeight = 0;
//        colorsAndButtonComposite.setLayout(colorsAndButtonLayout);
//        GridData colorsAndButtonLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
//        colorsAndButtonComposite.setLayoutData(colorsAndButtonLayoutData);
        Composite colorsAndButtonComposite = configureColorsAndButtonsComposite(parent, 2);

        new ColorPaletteComposite(colorsAndButtonComposite, colorsList4, 10, true) {
            @Override
            public void setPaletteSelectedColor(RGB colorToSet) {
                super.setPaletteSelectedColor(colorToSet);
                selectedColor = getPaletteSelectedColor();
                disposePalettePopup();
            }
        };
        Button moreColors = configureMoreButton(colorsAndButtonComposite);
        moreColors.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ColorSelectionDialog dialog = new ColorSelectionDialog((Shell) shell.getParent(), null, true, null, Messages.ColorSelectionDialog_dialogTitle, Messages.ColorSelectionDialog_groupDisplayedDefaultColorsLabel, Messages.ColorSelectionDialog_groupAllDefaultColorsLabel);
                if (dialog.open() == Window.OK) {
                    selectedColor = dialog.getSelectedColor();
                }
            }
        });
    }
    
    private Button configureMoreButton(Composite parent) {
        Button moreColors = new Button(parent, SWT.PUSH);
        moreColors.setText(Messages.ColorPalettePopup_moreButtonLabel);
        moreColors.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, true, 1, 1));
        
        return moreColors;
    }
    
    private Composite configureColorsAndButtonsComposite(Composite parent, int nbColumns) {
        Composite colorsAndButtonComposite = new Composite(parent, SWT.NONE);
        GridLayout colorsAndButtonLayout = new GridLayout(nbColumns, false);
        colorsAndButtonLayout.marginWidth = 2;
        colorsAndButtonLayout.marginRight = 5;
        colorsAndButtonLayout.marginHeight = 0;
        colorsAndButtonComposite.setLayout(colorsAndButtonLayout);
        GridData colorsAndButtonLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        colorsAndButtonComposite.setLayoutData(colorsAndButtonLayoutData);
        return colorsAndButtonComposite;
    }

    private Composite createSeparator(String separatorLabel) {
        Composite separatorComposite = new Composite(shell, SWT.NONE);
        GridLayout separatorLayout = new GridLayout(2, false);
        separatorLayout.marginHeight = 2;
        separatorComposite.setLayout(separatorLayout);
        GridData separatorLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        separatorComposite.setLayoutData(separatorLayoutData);

        Label separatorText = new Label(separatorComposite, SWT.NONE);
        separatorText.setText(separatorLabel);
        separatorText.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, true, 1, 1));
        Label separator = new Label(separatorComposite, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.FILL);
        separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
        return separatorComposite;
    }

    /**
     * @param style
     * @return
     */
    private static int checkStyle(int style) {
        int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
        return style & mask;
    }

    /**
     * Launches the Popup List, waits for an item to be selected and then closes PopupList.
     * 
     * @param location
     *            the initial size and location of the PopupList; the dialog will be positioned so that it does not run
     *            off the screen and the largest number of items are visible
     * 
     * @return the text of the selected item or null if no item is selected
     */
    public RGB open(Point location) {

        Point listSize = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
        shell.setBounds(location.x, location.y, listSize.x, listSize.y);

        shell.open();
        shell.setFocus();
        Display display = shell.getDisplay();
        Button prevButton = (Button) buttonMap.get(FigureUtilities.integerToRGB(getPreviousColor()));
        if (prevButton != null) {
            shell.setDefaultButton(prevButton);
            prevButton.setFocus();
        } else {
            shell.setDefaultButton(customColorButton);
        }
        while (!shell.isDisposed() && shell.isVisible()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return getSelectedColor();
    }

    /**
     * Gets the color the user selected. Could be null as the user may have cancelled the gesture or they may have
     * selected the default color button. See {@link #useDefaultColor()}.
     * 
     * @return the selected color or null
     */
    public RGB getSelectedColor() {
        return selectedColor;
    }

    /**
     * Returns true if the user selected to use the default color.
     * 
     * @return true if the default color is to be used; false otherwise
     */
    public boolean useDefaultColor() {
        return useDefaultColor;
    }

    /**
     * Returns the previous color.
     * 
     * @return the previous color.
     */
    public int getPreviousColor() {
        return previousColor;
    }

    /**
     * Sets the previous color.
     * 
     * @param previousColor
     *            the previous color.
     */
    public void setPreviousColor(int previousColor) {
        this.previousColor = previousColor;
    }

    /**
     * Dispose the popup.
     */
    public void disposePalettePopup() {
        // Dispose all SWT Buttons
        buttonMap.values().forEach(b -> b.dispose());
        // Clear the map
        buttonMap.clear();
        // Dispose the associated shell
        shell.dispose();
    }
}
