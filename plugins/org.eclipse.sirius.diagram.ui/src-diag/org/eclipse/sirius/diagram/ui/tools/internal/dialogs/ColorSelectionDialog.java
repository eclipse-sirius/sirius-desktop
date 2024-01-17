/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.util.List;

import org.eclipse.gmf.runtime.common.ui.util.WindowUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This {@link Dialog} is used to display sets of colors. Two groups are displayed in UI: the "Displayed Colors" group
 * and the "All Colors" group; the first one is intended to be a subset of the second one. The UI provides some buttons
 * to remove or add colors in a group, drag and drop is possible from "All Colors" to "Displayed Colors" to complete
 * this subset of colors.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class ColorSelectionDialog extends Dialog {

    /**
     * An invalid color data used to identify a forbidden drag and drop.
     */
    private static final String INVALID_COLOR_SUBSTITUTE = "{-1,-1,-1}"; //$NON-NLS-1$

    /**
     * The maximum number of colors in one line of the palette.
     */
    private static final int NB_COLORS_PER_LINE = 10;

    /**
     * The color selected in the dialog.
     */
    private RGB dialogSelectedColor;

    /**
     * The title of the dialog.
     */
    private String dialogTitle;

    /**
     * The label of the "Displayed Colors" group.
     */
    private String displayedColorsGroupLabel;

    /**
     * The label of the "All Colors" group.
     */
    private String allColorsGroupLabel;

    /**
     * The wrapper for the "Displayed Colors" group.
     */
    private final PaletteAndButtonsWrapper displayedColorsWrapper = new PaletteAndButtonsWrapper();

    /**
     * The wrapper for the "All Colors" group.
     */
    private final PaletteAndButtonsWrapper allColorsWrapper = new PaletteAndButtonsWrapper();

    /**
     * The list of displayed colors; which should be a subset of allColors.
     */
    private List<RGB> displayedColors;

    /**
     * The list of all colors.
     */
    private List<RGB> allColors;

    /**
     * The tooltip to display for the "Displayed Colors" group.
     */
    private String displayedColorsTooltip;

    /**
     * The tooltip to display for the "All Colors" group.
     */
    private String allColorsTooltip;

    /**
     * The Image used to display a tooltip if needed, for UX facilities.
     */
    private Image helpImage;

    /**
     * Used to change the behavior of the "All Colors" group with the reordering of colors. By default, reordering of
     * colors is not allowed for this group.
     */
    private boolean allColorsReorderAllowed;

    /**
     * Creates an instance of {@link ColorSelectionDialog}.
     * 
     * @param parentShell
     *            the parent shell.
     */
    public ColorSelectionDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        initDisplayedColorsGroup(container);
        initAllColorsGroup(container);
        return container;
    }

    /**
     * Used to initialize the "Displayed Colors" group: the layout, color palette and buttons.
     * 
     * @param container
     *            the parent {@link Composite}.
     */
    protected void initDisplayedColorsGroup(Composite container) {
        configureDisplayedColorsGroup(container);
    }

    /**
     * Used to initialize the "All Colors" group: the layout, color palette and buttons.
     * 
     * @param container
     *            the parent {@link Composite}.
     */
    protected void initAllColorsGroup(Composite container) {
        configureAllColorsGroup(container);
    }

    /**
     * Used to configure the "Displayed Colors" color palette and group. Some specific behavior are needed to select,
     * remove, add or drag and drop colors between the two groups.
     * 
     * @param container
     *            the parent {@link Composite}.
     * @return the "Displayed Colors" Group.
     */
    protected Group configureDisplayedColorsGroup(Composite container) {
        Group displayedColorsGroup = new Group(container, SWT.NONE);
        displayedColorsGroup.setLayout(new GridLayout());
        GridData displayedColorsGroupLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        displayedColorsGroup.setLayoutData(displayedColorsGroupLayoutData);
        displayedColorsGroup.setText(this.displayedColorsGroupLabel);

        Composite paletteAndHelpIcon = new Composite(displayedColorsGroup, SWT.NONE);
        GridLayout paletteAndHelpIconGridLayout = new GridLayout(2, false);
        paletteAndHelpIcon.setLayout(paletteAndHelpIconGridLayout);
        GridData paletteAndIconLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        paletteAndHelpIcon.setLayoutData(paletteAndIconLayoutData);

        ColorPaletteComposite displayedColorsPalette = createDisplayedColorsPalette(container, paletteAndHelpIcon);
        this.getDisplayedColorsWrapper().setColorPaletteComposite(displayedColorsPalette);

        Label helpIcon = new Label(paletteAndHelpIcon, SWT.NONE);
        helpImage = getHelpIcon();
        helpIcon.setImage(helpImage);
        helpIcon.setToolTipText(displayedColorsTooltip);
        helpIcon.setLayoutData(new GridData(SWT.END, SWT.CENTER, false, true, 1, 1));

        return displayedColorsGroup;
    }

    private ColorPaletteComposite createDisplayedColorsPalette(Composite compositeToRefresh, Composite parent) {
        // CHECKSTYLE:OFF
        ColorPaletteComposite displayedColorsPalette = new ColorPaletteComposite(parent, this.displayedColors, NB_COLORS_PER_LINE, true) {
            // CHECKSTYLE:ON
            /**
             * {@inheritDoc} Overridden to enable or disable the remove button of the "Displayed Colors" group.
             */
            @Override
            public void selectColor(RGB colorToSet) {
                super.selectColor(colorToSet);
                dialogSelectedColor = colorToSet;
                Button displayedColorsRemoveButton = getDisplayedColorsWrapper().getColorPaletteRemoveButton();
                if (displayedColorsRemoveButton != null) {
                    displayedColorsRemoveButton.setEnabled(colorToSet != null && getColors().contains(colorToSet));
                }
            }

            @Override
            protected void handleDoubleClick(Button button) {
                String colorString = (String) button.getData(BUTTON_COLOR_DATA_KEY);
                RGB convertedColor = ColorManager.getDefault().stringToRGB(colorString);
                removeColor(convertedColor);
            }

            /**
             * {@inheritDoc} When a color is dropped to the "Displayed Colors" group, it may be added to the palette if
             * the RGB string value is valid and if the color doesn't already exist or the palette is not full.
             */
            @Override
            protected void dropColorOnButton(Button targetButton, String droppedColorString) {
                if (!INVALID_COLOR_SUBSTITUTE.equals(droppedColorString)
                        && (getColors().size() < getNumberOfColumns() || getColors().contains(ColorManager.getDefault().stringToRGB(droppedColorString)))) {
                    super.dropColorOnButton(targetButton, droppedColorString);
                    Button displayedColorsRemoveButton = getDisplayedColorsWrapper().getColorPaletteRemoveButton();
                    if (displayedColorsRemoveButton != null) {
                        displayedColorsRemoveButton.setEnabled(false);
                    }
                }
            }

            @Override
            protected void dropColorOnPalette(String droppedColorString) {
                if (!INVALID_COLOR_SUBSTITUTE.equals(droppedColorString)
                        && (getColors().size() < getNumberOfColumns() || getColors().contains(ColorManager.getDefault().stringToRGB(droppedColorString)))) {
                    super.dropColorOnPalette(droppedColorString);
                    Button displayedColorsRemoveButton = getDisplayedColorsWrapper().getColorPaletteRemoveButton();
                    if (displayedColorsRemoveButton != null) {
                        displayedColorsRemoveButton.setEnabled(false);
                    }
                }
            }
        };
        return displayedColorsPalette;
    }

    /**
     * Used to configure the "All Colors" color palette and group. Some specific behavior are needed to select, remove,
     * add or drag and drop colors between the two groups.
     * 
     * @param container
     *            the parent {@link Composite}.
     * @return the "All Colors" Group.
     */
    protected Group configureAllColorsGroup(Composite container) {
        Group allColorsGroup = new Group(container, SWT.NONE);
        allColorsGroup.setLayout(new GridLayout());
        GridData allColorsGroupLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        allColorsGroup.setLayoutData(allColorsGroupLayoutData);
        allColorsGroup.setText(this.allColorsGroupLabel);

        Composite paletteAndHelpIcon = new Composite(allColorsGroup, SWT.NONE);
        GridLayout paletteAndHelpIconGridLayout = new GridLayout(2, false);
        paletteAndHelpIcon.setLayout(paletteAndHelpIconGridLayout);
        GridData paletteAndIconLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
        paletteAndHelpIcon.setLayoutData(paletteAndIconLayoutData);

        ColorPaletteComposite allColorsPalette = createAllColorsPalette(allColorsGroup, paletteAndHelpIcon);
        this.getAllColorsWrapper().setColorPaletteComposite(allColorsPalette);

        Label helpIcon = new Label(paletteAndHelpIcon, SWT.NONE);
        helpImage = getHelpIcon();
        helpIcon.setImage(helpImage);
        helpIcon.setToolTipText(allColorsTooltip);
        helpIcon.setLayoutData(new GridData(SWT.END, SWT.CENTER, false, true, 1, 1));

        return allColorsGroup;
    }

    // CHECKSTYLE:OFF
    private ColorPaletteComposite createAllColorsPalette(Composite compositeToRefresh, Composite parent) {
        ColorPaletteComposite allColorsPalette = new ColorPaletteComposite(parent, allColors, NB_COLORS_PER_LINE, true) {
            // CHECKSTYLE:ON
            /**
             * {@inheritDoc} Overridden to enable or disable the remove button of the "Displayed Colors" group and the
             * add button of the "All Colors" group.
             */
            @Override
            public void selectColor(RGB colorToSet) {
                super.selectColor(colorToSet);
                dialogSelectedColor = colorToSet;
                Button displayedColorsRemoveButton = getDisplayedColorsWrapper().getColorPaletteRemoveButton();
                if (displayedColorsRemoveButton != null) {
                    displayedColorsRemoveButton.setEnabled(false);
                }
                Button allColorsRemoveButton = getAllColorsWrapper().getColorPaletteRemoveButton();
                if (allColorsRemoveButton != null) {
                    allColorsRemoveButton.setEnabled(colorToSet != null && getColors().contains(colorToSet));
                }
            };

            /**
             * {@inheritDoc} When a color is double clicked in the "All Colors" group, it may be added in the "Displayed
             * Colors" group if it is not full.
             */
            @Override
            protected void handleDoubleClick(Button button) {
                ColorPaletteComposite displayedColorPalette = getDisplayedColorsWrapper().getColorPaletteComposite();
                if (displayedColorPalette != null && displayedColorPalette.getColors().size() < displayedColorPalette.getNumberOfColumns()) {
                    String colorString = (String) button.getData(BUTTON_COLOR_DATA_KEY);
                    RGB convertedColor = ColorManager.getDefault().stringToRGB(colorString);
                    displayedColorPalette.addColor(convertedColor);
                }
            }

            /**
             * {@inheritDoc} When a color is dragged from the "All Colors" group, the appropriate RGB string value data
             * is used to dropping in the "Displayed Colors" group; if the "Displayed Colors" is full, an invalid RGB
             * string value is used to identify a forbidden DnD.
             */
            @Override
            protected void dragColor(Button button, String draggedColorString, DragSourceEvent dragEvent) {
                ColorPaletteComposite displayedColorPalette = getDisplayedColorsWrapper().getColorPaletteComposite();
                if ((displayedColorPalette != null && displayedColorPalette.getColors().size() < displayedColorPalette.getNumberOfColumns())
                        || (displayedColorPalette == null && isDroppingAllowed())) {
                    super.dragColor(button, draggedColorString, dragEvent);
                } else {
                    super.dragColor(button, INVALID_COLOR_SUBSTITUTE, dragEvent);
                }
            }

            /**
             * {@inheritDoc} Overridden to increase the size of the dialog when the maximum number of colors per column
             * is reached for the last row and a new color is added.
             */
            @Override
            public boolean addColor(int index, RGB colorToAdd) {
                boolean resizePaletteNeeded = getColors().size() % getNumberOfColumns() == 0;
                boolean paletteIsEmpty = getColors().isEmpty();
                boolean hasBeenAdded = super.addColor(index, colorToAdd);
                if (resizePaletteNeeded && hasBeenAdded && !paletteIsEmpty) {
                    resizeDialog(compositeToRefresh, ColorPaletteComposite.BUTTON_SIZE);
                }
                return hasBeenAdded;
            }

            /**
             * {@inheritDoc} Overridden to reduce the size of the dialog when the maximum number of colors per column is
             * reached for the last row and an existing color is removed.
             */
            @Override
            public boolean removeColor(RGB colorToRemove) {
                boolean hasBeenRemoved = super.removeColor(colorToRemove);
                boolean resizePaletteNeeded = getColors().size() % getNumberOfColumns() == 0;
                boolean paletteIsEmpty = getColors().isEmpty();
                if (resizePaletteNeeded && hasBeenRemoved && !paletteIsEmpty) {
                    resizeDialog(compositeToRefresh, -ColorPaletteComposite.BUTTON_SIZE);
                }
                return hasBeenRemoved;
            }

            /**
             * {@inheritDoc} Overridden to handle two cases:
             * <ul>
             * <li>When a color is DnD from "Displayed" to "All" colors, the color is removed from
             * "Displayed"colors.</li>
             * <li>When there is no "Displayed" colors group, we should be able to reorder "All" colors group if it is
             * allowed.</li>
             * </ul>
             */
            @Override
            protected void dropColorOnButton(Button targetButton, String droppedColorString) {
                ColorPaletteComposite displayedColorPalette = getDisplayedColorsWrapper().getColorPaletteComposite();
                if (displayedColorPalette != null) {
                    final RGB sourceDroppedColor = ColorManager.getDefault().stringToRGB(droppedColorString);
                    displayedColorPalette.basicRemoveColor(sourceDroppedColor);
                } else {
                    if (allColorsReorderAllowed) {
                        super.dropColorOnButton(targetButton, droppedColorString);
                    }
                }
            }

            /**
             * {@inheritDoc} When a color is DnD from "Displayed" to "All" colors, the color is removed from
             * "Displayed"colors.
             */
            @Override
            protected void dropColorOnPalette(String droppedColorString) {
                ColorPaletteComposite displayedColorPalette = getDisplayedColorsWrapper().getColorPaletteComposite();
                if (displayedColorPalette != null) {
                    final RGB sourceDroppedColor = ColorManager.getDefault().stringToRGB(droppedColorString);
                    displayedColorPalette.basicRemoveColor(sourceDroppedColor);
                }
            }
        };
        return allColorsPalette;
    }

    /**
     * Used to configure the layout of the intermediate composite that wraps the add and remove buttons.
     * 
     * @param colorsGroup
     *            the parent {@link Group}.
     * @return the intermediate composite that wraps the add and remove buttons.
     */
    protected Composite configurePaletteButtonsComposite(Group colorsGroup) {
        Composite buttonsComposite = new Composite(colorsGroup, SWT.NONE);
        GridLayout buttonsGridLayout = new GridLayout(2, false);
        buttonsGridLayout.marginHeight = 0;
        buttonsComposite.setLayout(buttonsGridLayout);
        GridData buttonsGridData = new GridData(SWT.END, SWT.CENTER, true, true, 1, 1);
        buttonsComposite.setLayoutData(buttonsGridData);
        return buttonsComposite;
    }

    /**
     * Used to configure the layout and behavior of the add button.
     * 
     * @param container
     *            the parent {@link Composite}.
     * @param wrapper
     *            the wrapper associated with the add button to be configured; the wrapper is used to associate a
     *            palette with buttons to modify it
     */
    protected void configureAddButton(Composite container, PaletteAndButtonsWrapper wrapper) {
        Button addButton = new Button(container, SWT.PUSH);
        addButton.setText(Messages.ColorSelectionDialog_addButtonLabel);
        addButton.setToolTipText(Messages.ColorSelectionDialog_addButtonTooltip);
        addButton.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, true, 1, 1));
        addButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                addClicked(wrapper);
            }
        });
        wrapper.setColorPaletteAddButton(addButton);
    }

    /**
     * When the add button is clicked.
     * 
     * @param wrapper
     *            the wrapper associated with the clicked add button; the wrapper is used to associate a palette with
     *            buttons to modifying it
     */
    protected void addClicked(PaletteAndButtonsWrapper wrapper) {
        ColorDialog systemDialog = new ColorDialog(Display.getCurrent().getActiveShell());
        systemDialog.setRGB(getSelectedColor());
        if (allColors != null && !allColors.isEmpty()) {
            systemDialog.setRGBs(allColors.toArray(new RGB[allColors.size()]));
        }
        WindowUtil.centerDialog(systemDialog.getParent(), Display.getCurrent().getActiveShell());
        RGB returnedSelectedColor = systemDialog.open();
        if (returnedSelectedColor != null) {
            dialogSelectedColor = systemDialog.getRGB();
            wrapper.getColorPaletteComposite().addColor(returnedSelectedColor);
        }
    }

    /**
     * Used to configure the layout and behavior of the remove button.
     * 
     * @param container
     *            the parent {@link Composite}.
     * @param wrapper
     *            the wrapper associated with the remove button to be configured; the wrapper is used to associate a
     *            palette with buttons to modify it
     */
    protected void configureRemoveButton(Composite container, PaletteAndButtonsWrapper wrapper) {
        Button removeButton = new Button(container, SWT.PUSH);
        removeButton.setText(Messages.ColorSelectionDialog_removeButtonLabel);
        removeButton.setToolTipText(Messages.ColorSelectionDialog_removeButtonTooltip);
        removeButton.setLayoutData(new GridData(SWT.END, SWT.CENTER, true, true, 1, 1));
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                removeClicked(wrapper);
            }
        });
        removeButton.setEnabled(false);
        wrapper.setColorPaletteRemoveButton(removeButton);
    }

    /**
     * When the remove button is clicked.
     * 
     * @param wrapper
     *            the wrapper associated with the clicked removed button; the wrapper is used to associate a palette
     *            with buttons to modifying it
     */
    protected void removeClicked(PaletteAndButtonsWrapper wrapper) {
        RGB colorToRemove = wrapper.getColorPaletteComposite().getPaletteSelectedColor();
        wrapper.getColorPaletteComposite().removeColor(colorToRemove);
        wrapper.getColorPaletteRemoveButton().setEnabled(false);
        dialogSelectedColor = null;
    }

    /**
     * {@inheritDoc} Overridden to set a minimum size and add a title for this dialog. The size is computed depending
     * the width of the color palette and the minimum height to display "All Colors" palette.
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(this.dialogTitle);
        newShell.setMinimumSize(363, 219);
    }

    /**
     * {@inheritDoc} Overridden to dispose the "Displayed Colors" and "All Colors" palette.
     */
    @Override
    public boolean close() {
        ColorPaletteComposite displayedPaletteColors = displayedColorsWrapper.getColorPaletteComposite();
        if (displayedPaletteColors != null) {
            displayedPaletteColors.dispose();
        }
        ColorPaletteComposite allPaletteColors = allColorsWrapper.getColorPaletteComposite();
        if (allPaletteColors != null) {
            allPaletteColors.dispose();
        }
        return super.close();
    }

    private void resizeDialog(Composite composite, int additionalHeight) {
        Rectangle oldBounds = getShell().getBounds();
        getShell().setBounds(new Rectangle(oldBounds.x, oldBounds.y, oldBounds.width, oldBounds.height + additionalHeight));
        composite.layout(true);
    }

    private Image getHelpIcon() {
        if (helpImage == null) {
            ImageDescriptor findImageDescriptor = DiagramUIPlugin.Implementation.findImageDescriptor("icons/help.gif"); //$NON-NLS-1$
            helpImage = DiagramUIPlugin.getPlugin().getImage(findImageDescriptor);
        }
        return helpImage;
    }

    /**
     * Returns the selected color.
     * 
     * @return the dialogSelectedColor
     */
    public RGB getSelectedColor() {
        return dialogSelectedColor;
    }

    /**
     * Returns the wrapper for the "Displayed Colors" group.
     * 
     * @return the displayedColorsWrapper
     */
    public PaletteAndButtonsWrapper getDisplayedColorsWrapper() {
        return displayedColorsWrapper;
    }

    /**
     * Returns the wrapper for the "All Colors" group.
     * 
     * @return the allColorsWrapper
     */
    public PaletteAndButtonsWrapper getAllColorsWrapper() {
        return allColorsWrapper;
    }

    /**
     * Sets the selected color.
     * 
     * @param dialogSelectedColor
     *            the selected color to set.
     */
    public void setDialogSelectedColor(RGB dialogSelectedColor) {
        this.dialogSelectedColor = dialogSelectedColor;
    }

    /**
     * Sets the title of the dialog.
     * 
     * @param dialogTitle
     *            the title to set.
     */
    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    /**
     * Sets the label of the "Displayed Colors" group.
     * 
     * @param displayedColorsGroupLabel
     *            the label to set.
     */
    public void setDisplayedColorsGroupLabel(String displayedColorsGroupLabel) {
        this.displayedColorsGroupLabel = displayedColorsGroupLabel;
    }

    /**
     * Sets the label of the "Displayed Colors" group.
     * 
     * @param allColorsGroupLabel
     *            the label to set.
     */
    public void setAllColorsGroupLabel(String allColorsGroupLabel) {
        this.allColorsGroupLabel = allColorsGroupLabel;
    }

    /**
     * Sets the colors to display.
     * 
     * @param displayedColors
     *            the list of colors to set.
     */
    public void setDisplayedColors(List<RGB> displayedColors) {
        this.displayedColors = displayedColors;
    }

    /**
     * Returns the "displayed colors" list.
     * 
     * @return displayedColors
     */
    public List<RGB> getDisplayedColors() {
        return this.displayedColors;
    }

    /**
     * Sets the "all colors" list.
     * 
     * @param allColors
     *            the list of colors to set.
     */
    public void setAllColors(List<RGB> allColors) {
        this.allColors = allColors;
    }

    /**
     * Returns the "all colors" list.
     * 
     * @return allColors
     */
    public List<RGB> getAllColors() {
        return this.allColors;
    }

    /**
     * Sets the tooltip to display for the "Displayed Colors" group.
     * 
     * @param displayedColorsTooltip
     *            the tooltip to use.
     */
    public void setDisplayedColorsTooltip(String displayedColorsTooltip) {
        this.displayedColorsTooltip = displayedColorsTooltip;
    }

    /**
     * Sets the tooltip to display for the "All Colors" group.
     * 
     * @param allColorsTooltip
     *            the tooltip to use.
     */
    public void setAllColorsTooltip(String allColorsTooltip) {
        this.allColorsTooltip = allColorsTooltip;
    }

    /**
     * Defines whether color reordering is allowed or not for the "All Colors" group.
     * 
     * @param allColorsReorderAllowed
     *            the boolean used to define the behavior for reordering on the "All Colors" group.
     */
    public void setAllColorsReorderAllowed(boolean allColorsReorderAllowed) {
        this.allColorsReorderAllowed = allColorsReorderAllowed;
    }

    /**
     * This class is used to wrap a palette with its associated add and remove buttons.
     * 
     * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
     */
    public class PaletteAndButtonsWrapper {

        /**
         * The {@link ColorPaletteComposite} associated to add and remove buttons.
         */
        private ColorPaletteComposite colorPaletteComposite;

        /**
         * The remove button associated with the {@link ColorPaletteComposite} to modify.
         */
        private Button colorPaletteRemoveButton;

        /**
         * The add button associated with the {@link ColorPaletteComposite} to modify.
         */
        private Button colorPaletteAddButton;

        /**
         * Creates an instance of {@link PaletteAndButtonsWrapper}.
         */
        public PaletteAndButtonsWrapper() {
        }

        /**
         * Returns the wrapped {@link ColorPaletteComposite}.
         * 
         * @return the colorPaletteComposite.
         */
        public ColorPaletteComposite getColorPaletteComposite() {
            return colorPaletteComposite;
        }

        /**
         * Sets the {@link ColorPaletteComposite} to wrap.
         * 
         * @param colorPaletteComposite
         *            the {@link ColorPaletteComposite} to wrap.
         */
        public void setColorPaletteComposite(ColorPaletteComposite colorPaletteComposite) {
            this.colorPaletteComposite = colorPaletteComposite;
        }

        /**
         * Returns the remove button associated with the {@link ColorPaletteComposite} to modify.
         * 
         * @return colorPaletteRemoveButton.
         */
        public Button getColorPaletteRemoveButton() {
            return colorPaletteRemoveButton;
        }

        /**
         * Sets the remove button to be associated with the {@link ColorPaletteComposite}.
         * 
         * @param colorPaletteRemoveButton
         *            the remove button to be associated with the {@link ColorPaletteComposite}.
         */
        public void setColorPaletteRemoveButton(Button colorPaletteRemoveButton) {
            this.colorPaletteRemoveButton = colorPaletteRemoveButton;
        }

        /**
         * Returns the add button associated with the {@link ColorPaletteComposite} to modify.
         * 
         * @return colorPaletteAddButton
         */
        public Button getColorPaletteAddButton() {
            return colorPaletteAddButton;
        }

        /**
         * Sets the add button to be associated with the {@link ColorPaletteComposite}.
         * 
         * @param colorPaletteAddButton
         *            the add button to be associated with the {@link ColorPaletteComposite}.
         */
        public void setColorPaletteAddButton(Button colorPaletteAddButton) {
            this.colorPaletteAddButton = colorPaletteAddButton;
        }
    }
}
