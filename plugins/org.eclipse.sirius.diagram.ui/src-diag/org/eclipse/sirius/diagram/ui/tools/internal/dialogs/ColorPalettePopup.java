/******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation and others.
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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManager;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorCategoryManagerProvider;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.sirius.diagram.ui.tools.api.color.IColorPalettePopup;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;

/**
 * This class define the popup invoked when a button to change the colors is clicked. This {@link ColorPalettePopup}
 * display four color categories:
 * <p>
 * This {@link ColorPalettePopup} display four color categories:
 * <ul>
 * <li><b>Last used colors</b> display the ten last colors used by the user. These colors are persisted in
 * preferences.</li>
 * <li><b>Custom colors</b> display the ten first colors defined by the user through the {@link ColorSelectionDialog}.
 * These colors are persisted in the aird file.</li>
 * <li><b>Suggested colors</b> display the ten "preferred colors" defined by the user which are a subset of the colors
 * defined in the VSM. These colors are persisted in the aird file.</li>
 * <li><b>Basic colors</b> display ten useful colors.</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public class ColorPalettePopup implements IColorPalettePopup {

    /**
     * The height of the popup used to compute its location to open it.
     */
    private static final int POPUP_HEIGHT = 192;

    /**
     * The width of the popup used to compute its location to open it.
     */
    private static final int POPUP_WIDTH = 343;

    /**
     * The maximum number of colors to display for each category.
     */
    private static final int POPUP_MAX_NB_COLORS = 10;

    /**
     * The variable used to store the previous color. Converting to RGB is possible with
     * {@link org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities#integerToRGB(Integer)}.
     */
    private int previousColor;

    /**
     * This field allows to dispose the current popup (and associated Shell and buttons) on deactivation (see
     * "shell.addListener(SWT.Deactivate,...)"). It is set to false when the button "Custom..." is clicked. Indeed, in
     * this case, the dispose is done just after the click on "OK" or "Cancel" button of the "ColorDialog" dialog.
     */
    private boolean shouldBeDisposedOnDeactivation = true;

    /**
     * The {@link Shell} used by this popup.
     */
    private Shell shell;

    /**
     * The color selected in the popup.
     */
    private RGB selectedColor;

    /**
     * The current sirius session.
     */
    private Session session;

    /**
     * The palette for the "Last Used" color category.
     */
    private ColorPaletteComposite lastUsedCategory;

    /**
     * The palette for the "Custom" color category.
     */
    private ColorPaletteComposite customCategory;

    /**
     * The palette for the "Suggested" color category.
     */
    private ColorPaletteComposite suggestedCategory;

    /**
     * The palette for the "Basic" color category.
     */
    private ColorPaletteComposite basicCategory;

    /**
     * The {@link ColorCategoryManager} used to get and modify persisted colors for a category.
     */
    private ColorCategoryManager colorCategoryManager;

    /**
     * Creates an instance of {@link ColorPalettePopup}.
     * 
     * @param parent
     *            the parent shell where the popup should appear on.
     * @param session
     *            the current sirius session.
     * @param editParts
     *            the list of selected edit parts.
     * @param propertyId
     *            the propertyID, which could be "notation.FillStyle.fillColor", "notation.LineStyle.lineColor", or
     *            "notation.FontStyle.fontColor".
     */
    public ColorPalettePopup(Shell parent, Session session, List<IGraphicalEditPart> editParts, String propertyId) {
        this.session = session;
        this.shell = new Shell(parent, SWT.NONE & (SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT));
        this.colorCategoryManager = new ColorCategoryManagerProvider().getColorCategoryManager(session, editParts, propertyId);
    }

    /**
     * Initializes the layout of the popup, creates and configure the four color palette categories.
     */
    @Override
    public void init() {
        shell.setText(Messages.ColorPalettePopup_title);
        GridLayout layout = new GridLayout(1, true);
        layout.horizontalSpacing = 0;
        layout.marginWidth = 0;
        layout.marginHeight = 5;
        layout.verticalSpacing = 0;
        this.shell.setLayout(layout);
        // Hide dialog if user clicks on "More..." button or dispose it if user selects outside of the shell (without
        // clicking on any button)
        this.shell.addListener(SWT.Deactivate, new Listener() {
            @Override
            public void handleEvent(Event e) {
                if (shouldBeDisposedOnDeactivation) {
                    disposePalettePopup();
                } else {
                    shell.setVisible(false);
                }
            }
        });

        createLastUsedCategory(this.shell);
        createCustomCategory(this.shell);
        final IPreferenceStore preferenceStore = SiriusEditPlugin.getPlugin().getPreferenceStore();
        if (preferenceStore != null && preferenceStore.getBoolean(SiriusUIPreferencesKeys.PREF_DISPLAY_VSM_USER_FIXED_COLOR_IN_PALETTE.name())) {
            createSuggestedCategory(this.shell);
        }
        createBasicCategory(this.shell);
    }

    /**
     * Creates and configure the color palette for the "Last Used" color category.
     * 
     * @param parent
     *            the parent {@link Composite}.
     */
    private void createLastUsedCategory(Composite parent) {
        List<RGB> lastUsedColors = colorCategoryManager.getLastUsedColors();
        lastUsedCategory = configureColorCategory(parent, Messages.ColorPalettePopup_lastUsedCategoryLabel, Messages.ColorPalettePopup_lastUsedCategoryTooltip, lastUsedColors, null, new String());
        if (lastUsedColors.isEmpty()) {
            final GridLayout layout = new GridLayout(POPUP_MAX_NB_COLORS, true);
            layout.marginHeight = 0;
            layout.horizontalSpacing = 2;
            layout.marginBottom = 25;
            lastUsedCategory.setLayout(layout);
        }
    }

    /**
     * Creates and configure the color palette for the "Custom" color category.
     * 
     * @param parent
     *            the parent {@link Composite}.
     */
    private void createCustomCategory(Composite parent) {
        List<RGB> customColors = colorCategoryManager.getCustomColors();
        int lastIndex = customColors.size() < POPUP_MAX_NB_COLORS ? customColors.size() : POPUP_MAX_NB_COLORS;
        customCategory = configureColorCategory(parent, Messages.ColorPalettePopup_customCategoryLabel, Messages.ColorPalettePopup_customCategoryTooltip, customColors.subList(0, lastIndex),
                () -> invokeCustomCategoryDialog(), Messages.ColorPalettePopup_customCategoryMoreButtonTooltip);
    }

    /**
     * Creates and configure the color palette for the "Suggested" color category.
     * 
     * @param parent
     *            the parent {@link Composite}.
     */
    private void createSuggestedCategory(Composite parent) {
        List<RGB> suggestedColors = colorCategoryManager.getSuggestedColors();
        if (!ColorManager.getDefault().collectVsmColors(session).values().isEmpty()) {
            suggestedCategory = configureColorCategory(parent, Messages.ColorPalettePopup_suggestedCategoryLabel, Messages.ColorPalettePopup_suggestedCategoryTooltip, suggestedColors,
                    () -> invokeSuggestedCategoryDialog(), Messages.ColorPalettePopup_suggestedCategoryMoreButtonTooltip);
        }
    }

    /**
     * Creates and configure the color palette for the "Basic" color category.
     * 
     * @param parent
     *            the parent {@link Composite}.
     */
    private void createBasicCategory(Composite parent) {
        List<RGB> basicColors = colorCategoryManager.getBasicColors();
        List<RGB> sortedBasicColors = ColorManager.getDefault().sortColors(basicColors);
        basicCategory = configureColorCategory(parent, Messages.ColorPalettePopup_basicCategoryLabel, Messages.ColorPalettePopup_basicCategoryTooltip, sortedBasicColors, null, new String());
    }

    /**
     * Common method used to create and configure a {@link ColorPaletteComposite} for a category.
     * 
     * @param parent
     *            the parent {@link Composite}.
     * @param separatorLabel
     *            the label of the separator for the corresponding color category.
     * @param categoryToolTip
     *            the tooltip for the help icon near the separator
     * @param colorsDisplayed
     *            the list of colors to display for the corresponding color category.
     * @param clickOnMoreButton
     *            the action invoked when clicking on the "More..." button.
     * @param moreButtonTooltip
     *            the tooltip for the "More..." button
     * @return the configured {@link ColorPaletteComposite}.
     */
    private ColorPaletteComposite configureColorCategory(Composite parent, String separatorLabel, String categoryToolTip, List<RGB> colorsDisplayed, Runnable clickOnMoreButton,
            String moreButtonTooltip) {
        createSeparator(separatorLabel, categoryToolTip);
        Composite colorsAndButtonComposite;
        if (clickOnMoreButton != null) {
            colorsAndButtonComposite = configureColorsAndButtonsComposite(parent, 2);
        } else {
            colorsAndButtonComposite = configureColorsAndButtonsComposite(parent, 1);
        }
        ColorPaletteComposite colorPaletteComposite = new ColorPaletteComposite(colorsAndButtonComposite, colorsDisplayed, POPUP_MAX_NB_COLORS, false, false) {
            /**
             * {@inheritDoc} Overridden to dispose the popup when a color is selected.
             */
            @Override
            public void selectColor(RGB colorToSet) {
                super.selectColor(colorToSet);
                selectedColor = getPaletteSelectedColor();
                disposePalettePopup();
            }
        };
        if (clickOnMoreButton != null) {
            configureMoreButton(colorsAndButtonComposite, clickOnMoreButton, moreButtonTooltip);
        }
        return colorPaletteComposite;
    }

    /**
     * Common method used to configure the "More..." button.
     * 
     * @param parent
     *            the parent {@link Composite}.
     * @param invokeDialogMethod
     *            the method to be invoked when clicking on the button.
     * @param moreButtonTooltip
     *            the tooltip for the "More..." button
     * @return the configured {@link Button}.
     */
    private Button configureMoreButton(Composite parent, Runnable invokeDialogMethod, String moreButtonTooltip) {
        Button moreColors = new Button(parent, SWT.PUSH);
        moreColors.setText(Messages.ColorPalettePopup_moreButtonLabel);
        moreColors.setToolTipText(moreButtonTooltip);
        moreColors.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, true, 1, 1));
        moreColors.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                invokeDialogMethod.run();
            }
        });
        return moreColors;
    }

    /**
     * Used to configure the intermediate {@link Composite} wrapping the {@link ColorPaletteComposite} and the "More..."
     * button (if it exists).
     * 
     * @param parent
     *            the parent {@link Composite}
     * @param nbColumns
     *            the number of columns for the layout of the composite. Two columns if there is a "More..." button; one
     *            column otherwise.
     * @return the configured composite.
     */
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

    /**
     * Creates a separator for a color category.
     * 
     * @param separatorLabel
     *            the label of the color category
     * @param categoryToolTip
     *            the tooltip for the help icon near the separator
     */
    private void createSeparator(String separatorLabel, String categoryToolTip) {
        Composite separatorComposite = new Composite(shell, SWT.NONE);
        GridLayout separatorLayout = new GridLayout(3, false);
        separatorLayout.marginHeight = 2;
        separatorComposite.setLayout(separatorLayout);
        GridData separatorLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        separatorComposite.setLayoutData(separatorLayoutData);

        Label separatorText = new Label(separatorComposite, SWT.NONE);
        separatorText.setText(separatorLabel);
        separatorText.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, true, 1, 1));

        Label helpIcon = new Label(separatorComposite, SWT.NONE);
        ImageDescriptor findImageDescriptor = DiagramUIPlugin.Implementation.findImageDescriptor("icons/help.gif"); //$NON-NLS-1$
        Image helpImage = DiagramUIPlugin.getPlugin().getImage(findImageDescriptor);
        helpIcon.setImage(helpImage);
        helpIcon.setToolTipText(categoryToolTip);
        helpIcon.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, true, 1, 1));

        Label separator = new Label(separatorComposite, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.FILL);
        separator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
    }

    /**
     * Used to invoke the {@link ColorSelectionDialog} for the "Custom" color category. This dialog doesn't have a
     * "Displayed Colors" group, and the "All Colors" group have a remove and add button.
     */
    private void invokeCustomCategoryDialog() {
        List<RGB> customColors = colorCategoryManager.getCustomColors();
        ColorSelectionDialog customCategoryDialog = new ColorSelectionDialog((Shell) shell.getParent()) {

            /**
             * {@inheritDoc} Overridden to configure add and remove buttons for the "All Colors" group.
             */
            @Override
            protected void initAllColorsGroup(Composite container) {
                Group allColorsGroup = configureAllColorsGroup(container);
                Composite compositeButtons = configurePaletteButtonsComposite(allColorsGroup);
                configureAddButton(compositeButtons, getAllColorsWrapper());
                configureRemoveButton(compositeButtons, getAllColorsWrapper());
            }

            /**
             * {@inheritDoc} Overridden to hide the "Displayed Colors" group.
             */
            @Override
            protected void initDisplayedColorsGroup(Composite container) {
            }

        };
        customCategoryDialog.setAllColors(customColors);
        if (!colorCategoryManager.getSelectedColorsByPropertyId().isEmpty()) {
            customCategoryDialog.setDialogSelectedColor(colorCategoryManager.getSelectedColorsByPropertyId().get(0));
        }
        customCategoryDialog.setDialogTitle(Messages.ColorSelectionDialog_customColorsDialogTitle);
        customCategoryDialog.setAllColorsGroupLabel(Messages.ColorSelectionDialog_groupAllCustomColorsLabel);
        customCategoryDialog.setAllColorsTooltip(Messages.ColorSelectionDialog_customAllColorsTooltip);
        customCategoryDialog.setAllColorsReorderAllowed(true);
        if (customCategoryDialog.open() == Window.OK) {
            if (customCategoryDialog.getSelectedColor() != null) {
                selectedColor = customCategoryDialog.getSelectedColor();
            }
            colorCategoryManager.setCustomColors(customCategoryDialog.getAllColors());
        }
    }

    /**
     * Used to invoke the {@link ColorSelectionDialog} for the "Suggested" color category.
     */
    private void invokeSuggestedCategoryDialog() {
        List<RGB> suggestedColors = colorCategoryManager.getSuggestedColors();
        Collection<RGB> allSuggestedColors = new HashSet<>(ColorManager.getDefault().collectVsmColors(session).values());
        List<RGB> sortedAllSuggestedColors = ColorManager.getDefault().sortColors(allSuggestedColors);

        ColorSelectionDialog suggestedCategoryDialog = new ColorSelectionDialog((Shell) shell.getParent());
        suggestedCategoryDialog.setDisplayedColors(suggestedColors);
        suggestedCategoryDialog.setAllColors(sortedAllSuggestedColors);
        suggestedCategoryDialog.setDialogSelectedColor(null);
        suggestedCategoryDialog.setDialogTitle(Messages.ColorSelectionDialog_suggestedColorsDialogTitle);
        suggestedCategoryDialog.setDisplayedColorsGroupLabel(Messages.ColorSelectionDialog_groupDisplayedSuggestedColorsLabel);
        suggestedCategoryDialog.setAllColorsGroupLabel(Messages.ColorSelectionDialog_groupAllSuggestedColorsLabel);
        suggestedCategoryDialog.setAllColorsTooltip(Messages.ColorSelectionDialog_suggestedAllColorsTooltip);
        suggestedCategoryDialog.setDisplayedColorsTooltip(Messages.ColorSelectionDialog_suggestedDisplayedColorsTooltip);

        if (suggestedCategoryDialog.open() == Window.OK) {
            if (suggestedCategoryDialog.getSelectedColor() != null) {
                selectedColor = suggestedCategoryDialog.getSelectedColor();
            }
            colorCategoryManager.setSuggestedColors(suggestedCategoryDialog.getDisplayedColors());
        }
    }

    /**
     * Used to get a valid location to open the {@link ColorPalettePopup} using a {@link MenuItem}.
     * 
     * @param menuItem
     *            the clicked {@link MenuItem}.
     * @return the top-left location to open the {@link ColorPalettePopup}.
     */
    public static Point getValidPopupLocation(MenuItem menuItem) {
        Point location = new Point(0, 0);
        location = Display.getCurrent().getCursorLocation();
        Rectangle screenBounds = Display.getCurrent().getBounds();
        if (!screenBounds.contains(location.x + POPUP_WIDTH, location.y + POPUP_HEIGHT)) {
            location = new Point(location.x - POPUP_WIDTH, location.y - POPUP_HEIGHT);
        }
        return location;
    }

    /**
     * Used to get a valid location to open the {@link ColorPalettePopup} using a {@link ToolItem}.
     * 
     * @param toolItem
     *            the clicked {@link ToolItem}.
     * @return the top-left location to open the {@link ColorPalettePopup}.
     */
    public static Point getValidPopupLocation(ToolItem toolItem) {
        Rectangle itemBounds = toolItem.getBounds();
        Point itemLocation = toolItem.getParent().toDisplay(itemBounds.x, itemBounds.y);
        return getValidPopupLocation(itemBounds, itemLocation);
    }

    /**
     * Used to get a valid location to open the {@link ColorPalettePopup} using a {@link Button}.
     * 
     * @param button
     *            the clicked {@link Button}.
     * @return the top-left location to open the {@link ColorPalettePopup}.
     */
    public static Point getValidPopupLocation(Button button) {
        Rectangle buttonBounds = button.getBounds();
        Point buttonLocation = button.getParent().toDisplay(buttonBounds.x, buttonBounds.y);
        return getValidPopupLocation(buttonBounds, buttonLocation);
    }

    /**
     * Used to get a valid location to open the {@link ColorPalettePopup} basing on the location of the clicked button.
     * 
     * @param buttonBounds
     *            the bounds of the clicked button.
     * @param buttonLocation
     *            the location of the button
     * @return the top-left location to open the {@link ColorPalettePopup}.
     */
    public static Point getValidPopupLocation(Rectangle buttonBounds, Point buttonLocation) {
        Point location = new Point(0, 0);
        Rectangle eclipseShellBounds = Display.getCurrent().getActiveShell().getBounds();
        int locationX = buttonLocation.x;
        int locationY = buttonLocation.y + buttonBounds.height;
        int shellMargin = 7;
        Point expectedRightBottomPopup = new Point(buttonLocation.x + POPUP_WIDTH, buttonLocation.y + POPUP_HEIGHT);
        if (!eclipseShellBounds.contains(expectedRightBottomPopup)) {
            Point rightBottomShell = new Point(eclipseShellBounds.x + eclipseShellBounds.width, eclipseShellBounds.y + eclipseShellBounds.height);
            if (expectedRightBottomPopup.x > rightBottomShell.x) {
                locationX = rightBottomShell.x - POPUP_WIDTH - shellMargin;
            }
            if (expectedRightBottomPopup.y > rightBottomShell.y) {
                locationY = rightBottomShell.y - POPUP_HEIGHT - shellMargin;
            }
        }
        location = new Point(locationX, locationY);
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RGB open(Point location) {
        Point listSize = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
        shell.setBounds(location.x, location.y, listSize.x, listSize.y);
        shell.open();
        shell.setFocus();
        Display display = shell.getDisplay();
        while (!shell.isDisposed() && shell.isVisible()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        RGB previousRGB = FigureUtilities.integerToRGB(getPreviousColor());
        if (!previousRGB.equals(selectedColor)) {
            colorCategoryManager.addLastUsedColor(selectedColor);
        }
        return selectedColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RGB getSelectedColor() {
        return selectedColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPreviousColor() {
        return previousColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPreviousColor(int previousColor) {
        this.previousColor = previousColor;
    }

    /**
     * Dispose the popup.
     */
    public void disposePalettePopup() {
        if (lastUsedCategory != null && !lastUsedCategory.isDisposed()) {
            lastUsedCategory.dispose();
        }
        if (customCategory != null && !customCategory.isDisposed()) {
            customCategory.dispose();
        }
        if (suggestedCategory != null && !suggestedCategory.isDisposed()) {
            suggestedCategory.dispose();
        }
        if (basicCategory != null && !basicCategory.isDisposed()) {
            basicCategory.dispose();
        }
        // Dispose the associated shell
        shell.dispose();
    }
}
